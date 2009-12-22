// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.relationship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.i18n.Messages;
import org.talend.core.model.components.EComponentType;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.ItemRelation;
import org.talend.core.model.properties.ItemRelations;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.runprocess.ItemCacheManager;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.ComponentsFactoryProvider;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;

/**
 * This class store all relationships between jobs/joblets and other items from the repository.
 * 
 * This actually can deal with: <br>
 * - Context (id = context id) <br>
 * - Property (id = connection id) <br>
 * - Schema (id = connection id + " - " + schema name)<br>
 * - Job (id = job id)<br>
 * - Joblet (id = joblet name)<br>
 * - Query (id = connection id + " - " + query name) <br>
 * - SQL Templates (id = SQLPattern id + "--" + SQLPattern name) <br>
 */
public class RelationshipItemBuilder {

    private static Logger log = Logger.getLogger(RelationshipItemBuilder.class);

    public static final String JOB_RELATION = "job"; //$NON-NLS-1$

    public static final String JOBLET_RELATION = "joblet"; //$NON-NLS-1$

    public static final String PROPERTY_RELATION = "property"; //$NON-NLS-1$

    public static final String SCHEMA_RELATION = "schema"; //$NON-NLS-1$

    public static final String QUERY_RELATION = "query"; //$NON-NLS-1$

    public static final String CONTEXT_RELATION = "context"; //$NON-NLS-1$

    public static final String SQLPATTERN_RELATION = "sqlPattern"; //$NON-NLS-1$

    public static RelationshipItemBuilder instance;

    private Map<Relation, List<Relation>> itemsRelations;

    private Project project;

    private boolean loaded = false;

    private boolean loading = false;

    public static RelationshipItemBuilder getInstance() {
        if (instance == null) {
            instance = new RelationshipItemBuilder();
        }
        return instance;
    }

    public List<Relation> getItemsRelatedTo(String itemId, String version, String relationType) {
        if (!loaded) {
            loadRelations(ProjectManager.getInstance().getCurrentProject());
        }

        Relation itemToTest = new Relation();

        itemToTest.setId(itemId);
        itemToTest.setType(relationType);
        itemToTest.setVersion(version);
        if (itemsRelations.containsKey(itemToTest)) {
            return itemsRelations.get(itemToTest);
        }

        List<Relation> relations = new ArrayList<Relation>();

        for (Relation baseItem : itemsRelations.keySet()) {
            for (Relation relatedItem : itemsRelations.get(baseItem)) {
                if (relatedItem.equals(itemToTest)) {
                    relations.add(baseItem);
                    break;
                }
            }
        }

        return relations;
    }

    private void loadRelations(Project project) {
        if (loading) {
            return;
        }
        loading = true;
        itemsRelations = new HashMap<Relation, List<Relation>>();

        for (Object o : project.getEmfProject().getItemsRelations()) {
            ItemRelations relations = (ItemRelations) o;
            Relation baseItem = new Relation();

            if (relations.getBaseItem() == null) {
                log.log(Level.WARN, "Error when load the relationship, so rebuild all..."); //$NON-NLS-1$
                loaded = false;
                project.getEmfProject().getItemsRelations().clear();
                buildIndex(project, new NullProgressMonitor());
                return;
            }

            baseItem.setId(relations.getBaseItem().getId());
            baseItem.setType(relations.getBaseItem().getType());
            baseItem.setVersion(relations.getBaseItem().getVersion());

            itemsRelations.put(baseItem, new ArrayList<Relation>());
            for (Object o2 : relations.getRelatedItems()) {
                ItemRelation emfRelatedItem = (ItemRelation) o2;

                Relation relatedItem = new Relation();
                relatedItem.setId(emfRelatedItem.getId());
                relatedItem.setType(emfRelatedItem.getType());
                relatedItem.setVersion(emfRelatedItem.getVersion());

                itemsRelations.get(baseItem).add(relatedItem);
            }
        }
        loading = false;
        loaded = true;
        this.project = project;
    }

    public void saveRelations() {
        if (!loaded) {
            return;
        }
        project.getEmfProject().getItemsRelations().clear();

        for (Relation relation : itemsRelations.keySet()) {
            ItemRelations itemRelations = PropertiesFactory.eINSTANCE.createItemRelations();

            ItemRelation baseItem = PropertiesFactory.eINSTANCE.createItemRelation();
            itemRelations.setBaseItem(baseItem);

            baseItem.setId(relation.getId());
            baseItem.setType(relation.getType());
            baseItem.setVersion(relation.getVersion());
            for (Relation relatedItem : itemsRelations.get(relation)) {
                ItemRelation emfRelatedItem = PropertiesFactory.eINSTANCE.createItemRelation();
                emfRelatedItem.setId(relatedItem.getId());
                emfRelatedItem.setType(relatedItem.getType());
                emfRelatedItem.setVersion(relatedItem.getVersion());

                itemRelations.getRelatedItems().add(emfRelatedItem);
            }
            project.getEmfProject().getItemsRelations().add(itemRelations);
        }
        try {
            IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                    IRepositoryService.class);
            IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
            factory.saveProject(project);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    private String getTypeFromItem(Item item) {
        String type = null;

        if (item instanceof ProcessItem) {
            type = JOB_RELATION;
        }
        if (item instanceof JobletProcessItem) {
            type = JOBLET_RELATION;
        }

        return type;
    }

    private void clearItemsRelations(Item baseItem) {
        Relation relation = new Relation();
        relation.setId(baseItem.getProperty().getId());
        relation.setType(getTypeFromItem(baseItem));
        relation.setVersion(baseItem.getProperty().getVersion());

        if (itemsRelations.containsKey(relation)) {
            itemsRelations.get(relation).clear();
        }
    }

    private void addRelationShip(Item baseItem, String relatedId, String relatedVersion, String type) {
        Relation relation = new Relation();
        relation.setId(baseItem.getProperty().getId());
        relation.setType(getTypeFromItem(baseItem));
        relation.setVersion(baseItem.getProperty().getVersion());

        Relation addedRelation = new Relation();
        addedRelation.setId(relatedId);
        addedRelation.setType(type);
        addedRelation.setVersion(relatedVersion);

        if (!itemsRelations.containsKey(relation)) {
            itemsRelations.put(relation, new ArrayList<Relation>());
        }
        itemsRelations.get(relation).add(addedRelation);
    }

    public boolean isAlreadyBuilt(Project project) {
        return !project.getEmfProject().getItemsRelations().isEmpty();
    }

    public void buildIndex(Project project, IProgressMonitor monitor) {
        this.project = project;

        if (!project.getEmfProject().getItemsRelations().isEmpty()) {
            loadRelations(project);
            if (loaded) { // check if already loaded successfully
                return;
            }
        }

        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
        List<IRepositoryObject> list = new ArrayList<IRepositoryObject>();
        try {
            for (ERepositoryObjectType curTyp : getTypes()) {
                list.addAll(factory.getAll(curTyp, true, true));
            }
            monitor.beginTask(Messages.getString("RelationshipItemBuilder.buildingIndex"), list.size()); //$NON-NLS-1$

            if (list.isEmpty()) {
                return;
            }

            for (IRepositoryObject object : list) {
                Item item = object.getProperty().getItem();
                monitor.subTask(Messages.getString("RelationshipItemBuilder.forItem") + item.getProperty().getLabel()); //$NON-NLS-1$
                addOrUpdateItem(item, true);
                monitor.worked(1);
                if (monitor.isCanceled()) {
                    return;
                }
            }
            saveRelations();
            monitor.done();
            loaded = true;

        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    private List<ERepositoryObjectType> getTypes() {
        List<ERepositoryObjectType> toReturn = new ArrayList<ERepositoryObjectType>();
        toReturn.add(ERepositoryObjectType.PROCESS);
        toReturn.add(ERepositoryObjectType.JOBLET);
        return toReturn;
    }

    public void addOrUpdateItem(Item item, boolean... fromMigration) {
        if (!loaded) {
            loadRelations(ProjectManager.getInstance().getCurrentProject());
        }
        ProcessType processType = null;
        if (item instanceof ProcessItem) {
            processType = ((ProcessItem) item).getProcess();
        }
        if (item instanceof JobletProcessItem) {
            processType = ((JobletProcessItem) item).getJobletProcess();
        }

        if (processType != null) {
            clearItemsRelations(item);

            Boolean builtIn = null;
            String currentValue = null;
            String relationType = null;
            // use a system of null value and relationType as the information repository / builtin can be stored
            // either before or after the informations of the repository value.

            for (Object o : processType.getContext()) {
                ContextType context = (ContextType) o;
                for (Object o2 : context.getContextParameter()) {
                    ContextParameterType contextParam = (ContextParameterType) o2;
                    if (!StringUtils.isEmpty(contextParam.getRepositoryContextId())) {
                        addRelationShip(item, contextParam.getRepositoryContextId(), ItemCacheManager.LATEST_VERSION,
                                CONTEXT_RELATION);
                    }
                }
            }

            for (Object o : processType.getParameters().getElementParameter()) {
                if (o instanceof ElementParameterType) {
                    ElementParameterType param = (ElementParameterType) o;
                    if (param.getName().startsWith("SCHEMA:")) { //$NON-NLS-1$ 
                        relationType = SCHEMA_RELATION;
                    } else if (param.getName().startsWith("PROPERTY:")) { //$NON-NLS-1$ 
                        relationType = PROPERTY_RELATION;
                    } else { // if no relation parameter, reset variables in case.
                        builtIn = null;
                        currentValue = null;
                    }
                    if (param.getName().endsWith(":PROPERTY_TYPE") || param.getName().endsWith(":SCHEMA_TYPE")) {//$NON-NLS-1$  //$NON-NLS-2$
                        builtIn = true;
                        if (param.getValue().equals("REPOSITORY")) { //$NON-NLS-1$
                            builtIn = false;
                        }
                    }
                    if (param.getName().endsWith(":REPOSITORY_PROPERTY_TYPE") || //$NON-NLS-1$
                            param.getName().endsWith(":REPOSITORY_SCHEMA_TYPE")) { //$NON-NLS-1$
                        currentValue = param.getValue();
                    }

                    if (builtIn != null && currentValue != null) { //$NON-NLS-1$
                        if (!builtIn) {
                            addRelationShip(item, currentValue, ItemCacheManager.LATEST_VERSION, relationType);
                        }
                        builtIn = null;
                        currentValue = null;
                    }
                }
            }

            List<String> jobletsComponentsList = new ArrayList<String>();
            for (IComponent component : ComponentsFactoryProvider.getInstance().getComponents()) {
                if (component.getComponentType() == EComponentType.JOBLET) {
                    jobletsComponentsList.add(component.getName());
                }
            }
            builtIn = null;
            currentValue = null;
            for (Object o : processType.getNode()) {
                if (o instanceof NodeType) {
                    NodeType currentNode = (NodeType) o;
                    for (Object o2 : currentNode.getElementParameter()) {
                        if (o2 instanceof ElementParameterType) {
                            ElementParameterType param = (ElementParameterType) o2;

                            if (param.getName().startsWith("QUERYSTORE:")) { //$NON-NLS-1$ 
                                relationType = QUERY_RELATION;
                            } else if (param.getName().startsWith("SCHEMA:")) { //$NON-NLS-1$ 
                                relationType = SCHEMA_RELATION;
                            } else if (param.getName().startsWith("PROPERTY:")) { //$NON-NLS-1$ 
                                relationType = PROPERTY_RELATION;
                            } else { // if no relation parameter, reset variables in case.
                                builtIn = null;
                                currentValue = null;
                            }
                            if (param.getName().endsWith(":PROPERTY_TYPE") || param.getName().endsWith(":SCHEMA_TYPE") //$NON-NLS-1$  //$NON-NLS-2$
                                    || param.getName().endsWith(":QUERYSTORE_TYPE")) { //$NON-NLS-1$
                                builtIn = true;
                                if (param.getValue().equals("REPOSITORY")) { //$NON-NLS-1$
                                    builtIn = false;
                                }
                            }
                            if (param.getName().endsWith(":REPOSITORY_PROPERTY_TYPE") || //$NON-NLS-1$
                                    param.getName().endsWith(":REPOSITORY_SCHEMA_TYPE") || //$NON-NLS-1$
                                    param.getName().endsWith(":REPOSITORY_QUERYSTORE_TYPE")) { //$NON-NLS-1$
                                currentValue = param.getValue();
                            }

                            if (builtIn != null && currentValue != null) { //$NON-NLS-1$
                                if (!builtIn) {
                                    addRelationShip(item, currentValue, ItemCacheManager.LATEST_VERSION, relationType);
                                }
                                builtIn = null;
                                currentValue = null;
                            }

                            // only for SQL Patterns
                            if (param.getName().equals("SQLPATTERN_VALUE")) { //$NON-NLS-1$
                                for (Object o3 : param.getElementValue()) {
                                    if (o3 instanceof ElementValueType
                                            && "SQLPATTERNLIST".equals(((ElementValueType) o3).getElementRef())) { //$NON-NLS-1$
                                        addRelationShip(item, ((ElementValueType) o3).getValue(),
                                                ItemCacheManager.LATEST_VERSION, SQLPATTERN_RELATION);
                                    }
                                }
                            }
                        }
                    }
                    if (jobletsComponentsList.contains(currentNode.getComponentName())) {
                        // in case of joblet
                        String version = ItemCacheManager.LATEST_VERSION;
                        for (Object o2 : currentNode.getElementParameter()) {
                            if (o2 instanceof ElementParameterType) {
                                ElementParameterType param = (ElementParameterType) o2;
                                if (param.getName().equals("PROCESS_TYPE_VERSION")) { //$NON-NLS-1$
                                    version = param.getValue();
                                }
                            }
                        }
                        addRelationShip(item, currentNode.getComponentName(), version, JOBLET_RELATION);
                    }
                    if ("tRunJob".equals(currentNode.getComponentName())) { //$NON-NLS-1$
                        // in case of tRunJob
                        String jobId = null;
                        String jobVersion = ItemCacheManager.LATEST_VERSION;
                        for (Object o2 : currentNode.getElementParameter()) {
                            if (o2 instanceof ElementParameterType) {
                                ElementParameterType param = (ElementParameterType) o2;
                                if (param.getName().equals("PROCESS:PROCESS_TYPE_PROCESS") //$NON-NLS-1$
                                        || param.getName().equals("PROCESS_TYPE_PROCESS")) { //$NON-NLS-1$
                                    jobId = param.getValue();
                                }
                                if (param.getName().equals("PROCESS:PROCESS_TYPE_VERSION") //$NON-NLS-1$
                                        || param.getName().equals("PROCESS_TYPE_VERSION")) { //$NON-NLS-1$
                                    jobVersion = param.getValue();
                                }
                            }
                        }
                        if (jobId != null) {
                            addRelationShip(item, jobId, jobVersion, JOB_RELATION);
                        }
                    }
                }
            }
        }
        if (fromMigration.length == 0) {
            saveRelations();
        }
    }

    public void removeItemRelations(Item item) {
        if (!loaded) {
            loadRelations(ProjectManager.getInstance().getCurrentProject());
        }
        ProcessType processType = null;
        if (item instanceof ProcessItem) {
            processType = ((ProcessItem) item).getProcess();
        }
        if (item instanceof JobletProcessItem) {
            processType = ((JobletProcessItem) item).getJobletProcess();
        }

        if (processType != null) {
            Relation relation = new Relation();
            relation.setId(item.getProperty().getId());
            relation.setType(getTypeFromItem(item));
            relation.setVersion(item.getProperty().getVersion());

            if (itemsRelations.containsKey(relation)) {
                itemsRelations.get(relation).clear();
                itemsRelations.remove(relation);
                saveRelations();
            }
        }
    }

    /**
     * 
     * Relation class global comment. Detailled comment.
     */
    public class Relation {

        private String type;

        private String id;

        private String version;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            result = prime * result + ((version == null) ? 0 : version.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Relation other = (Relation) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            if (type == null) {
                if (other.type != null)
                    return false;
            } else if (!type.equals(other.type))
                return false;
            if (version == null) {
                if (other.version != null)
                    return false;
            } else if (!version.equals(other.version))
                return false;
            return true;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    }

}
