// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
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
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
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
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.ui.IJobletProviderService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.RoutinesParameterType;
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

    public static final String ROUTINE_RELATION = "routine"; //$NON-NLS-1$

    public static RelationshipItemBuilder instance;

    private Map<Relation, List<Relation>> currentProjectItemsRelations;

    private Map<Relation, List<Relation>> referencesItemsRelations;

    private boolean loaded = false;

    private boolean loading = false;

    private boolean modified = false;

    public static RelationshipItemBuilder getInstance() {
        if (instance == null) {
            instance = new RelationshipItemBuilder();
        }
        return instance;
    }

    public List<Relation> getItemsRelatedTo(String itemId, String version, String relationType) {
        if (!loaded) {
            loadRelations();
        }
        List<Relation> relations = new ArrayList<Relation>();
        List<Relation> itemsRelations = getItemsRelatedTo(currentProjectItemsRelations, itemId, version, relationType);
        if (itemsRelations != null) {
            relations.addAll(itemsRelations);
        }
        itemsRelations = getItemsRelatedTo(referencesItemsRelations, itemId, version, relationType);
        if (itemsRelations != null) {
            relations.addAll(itemsRelations);
        }
        return relations;
    }

    private List<Relation> getItemsRelatedTo(Map<Relation, List<Relation>> itemsRelations, String itemId, String version,
            String relationType) {

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

    public List<Relation> getItemsJobRelatedTo(String itemId, String version, String relationType) {
        if (!loaded) {
            loadRelations();
        }
        List<Relation> relations = new ArrayList<Relation>();
        List<Relation> itemsRelations = getItemsJobRelatedTo(currentProjectItemsRelations, itemId, version, relationType);
        if (itemsRelations != null) {
            relations.addAll(itemsRelations);
        }
        itemsRelations = getItemsJobRelatedTo(referencesItemsRelations, itemId, version, relationType);
        if (itemsRelations != null) {
            relations.addAll(itemsRelations);
        }
        return relations;
    }

    private List<Relation> getItemsJobRelatedTo(Map<Relation, List<Relation>> itemsRelations, String itemId, String version,
            String relationType) {

        Relation itemToTest = new Relation();
        List<Relation> jobRelations = new ArrayList<Relation>();

        itemToTest.setId(itemId);
        itemToTest.setType(relationType);
        itemToTest.setVersion(version);
        if (itemsRelations.containsKey(itemToTest)) {
            List<Relation> relations = itemsRelations.get(itemToTest);
            for (Relation relatedItem : relations) {
                if (relatedItem.getType().equals(relationType)) {
                    jobRelations.add(relatedItem);
                }
            }
            return jobRelations;
        }

        return jobRelations;
    }

    private void loadRelations() {
        if (loading) {
            return;
        }
        loading = true;
        currentProjectItemsRelations = new HashMap<Relation, List<Relation>>();
        referencesItemsRelations = new HashMap<Relation, List<Relation>>();

        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        loadRelations(currentProjectItemsRelations, currentProject);

        List<Project> referencedProjects = ProjectManager.getInstance().getReferencedProjects();
        for (Project p : referencedProjects) {
            loadRelations(referencesItemsRelations, p);
        }

        loading = false;
        loaded = true;

    }

    private void loadRelations(Map<Relation, List<Relation>> itemRelations, Project project) {

        for (Object o : project.getEmfProject().getItemsRelations()) {
            ItemRelations relations = (ItemRelations) o;
            Relation baseItem = new Relation();

            if (relations.getBaseItem() == null) {
                log.log(Level.WARN, "Error when load the relationship, so rebuild all..."); //$NON-NLS-1$
                loaded = false;
                project.getEmfProject().getItemsRelations().clear();
                buildIndex(itemRelations, project, new NullProgressMonitor());
                return;
            }

            baseItem.setId(relations.getBaseItem().getId());
            baseItem.setType(relations.getBaseItem().getType());
            baseItem.setVersion(relations.getBaseItem().getVersion());

            itemRelations.put(baseItem, new ArrayList<Relation>());
            for (Object o2 : relations.getRelatedItems()) {
                ItemRelation emfRelatedItem = (ItemRelation) o2;

                Relation relatedItem = new Relation();
                relatedItem.setId(emfRelatedItem.getId());
                relatedItem.setType(emfRelatedItem.getType());
                relatedItem.setVersion(emfRelatedItem.getVersion());

                itemRelations.get(baseItem).add(relatedItem);
            }
        }

    }

    public boolean isNeedSaveRelations() {
        if (loaded && modified) {
            return true;
        }
        return false;
    }

    public void saveRelations() {
        if (!loaded && !modified) {
            return;
        }
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        currentProject.getEmfProject().getItemsRelations().clear();

        for (Relation relation : currentProjectItemsRelations.keySet()) {
            ItemRelations itemRelations = PropertiesFactory.eINSTANCE.createItemRelations();

            ItemRelation baseItem = PropertiesFactory.eINSTANCE.createItemRelation();
            itemRelations.setBaseItem(baseItem);

            baseItem.setId(relation.getId());
            baseItem.setType(relation.getType());
            baseItem.setVersion(relation.getVersion());
            for (Relation relatedItem : currentProjectItemsRelations.get(relation)) {
                ItemRelation emfRelatedItem = PropertiesFactory.eINSTANCE.createItemRelation();
                emfRelatedItem.setId(relatedItem.getId());
                emfRelatedItem.setType(relatedItem.getType());
                emfRelatedItem.setVersion(relatedItem.getVersion());

                itemRelations.getRelatedItems().add(emfRelatedItem);
            }
            currentProject.getEmfProject().getItemsRelations().add(itemRelations);
        }
        try {
            IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(
                    IRepositoryService.class);
            IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
            factory.saveProject(currentProject);
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
        modified = false;
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

        if (currentProjectItemsRelations.containsKey(relation)) {
            currentProjectItemsRelations.get(relation).clear();
        }
        if (referencesItemsRelations.containsKey(relation)) {
            referencesItemsRelations.get(relation).clear();
        }
    }

    private void addRelationShip(Item baseItem, String relatedId, String relatedVersion, String type) {
        addRelationShip(baseItem, relatedId, relatedVersion, type, "");
    }

    private void addRelationShip(Item baseItem, String relatedId, String relatedVersion, String type, String relatedInSystem) {
        Relation relation = new Relation();
        relation.setId(baseItem.getProperty().getId());
        relation.setType(getTypeFromItem(baseItem));
        relation.setVersion(baseItem.getProperty().getVersion());

        Relation addedRelation = new Relation();
        addedRelation.setId(relatedId);
        addedRelation.setType(type);
        addedRelation.setVersion(relatedVersion);
        addedRelation.setName(relatedInSystem);
        Map<Relation, List<Relation>> itemRelations = getRelatedRelations(baseItem);

        if (!itemRelations.containsKey(relation)) {
            itemRelations.put(relation, new ArrayList<Relation>());
        }
        itemRelations.get(relation).add(addedRelation);
    }

    private Map<Relation, List<Relation>> getRelatedRelations(Item baseItem) {
        Map<Relation, List<Relation>> itemRelations = currentProjectItemsRelations;
        if (!ProjectManager.getInstance().isInCurrentMainProject(baseItem)) {
            itemRelations = referencesItemsRelations;
        }
        return itemRelations;

    }

    public boolean isAlreadyBuilt(Project project) {
        return !project.getEmfProject().getItemsRelations().isEmpty();
    }

    private void buildIndex(Map<Relation, List<Relation>> itemRelations, Project project, IProgressMonitor monitor) {
        modified = true;

        if (!project.getEmfProject().getItemsRelations().isEmpty()) {
            loadRelations(itemRelations, project);
            if (loaded) { // check if already loaded successfully
                return;
            }
        }

        IRepositoryService service = (IRepositoryService) GlobalServiceRegister.getDefault().getService(IRepositoryService.class);
        IProxyRepositoryFactory factory = service.getProxyRepositoryFactory();
        List<IRepositoryViewObject> list = new ArrayList<IRepositoryViewObject>();
        try {
            for (ERepositoryObjectType curTyp : getTypes()) {
                list.addAll(factory.getAll(curTyp, true, true));
            }
            monitor.beginTask(Messages.getString("RelationshipItemBuilder.buildingIndex"), list.size()); //$NON-NLS-1$

            if (list.isEmpty()) {
                return;
            }

            for (IRepositoryViewObject object : list) {
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

    public void updateItemVersion(Item baseItem, String oldVersion, String id, Map<String, String> versions)
            throws PersistenceException {
        updateItemVersion(baseItem, oldVersion, id, versions, false);
    }

    public void updateItemVersion(Item baseItem, String oldVersion, String id, Map<String, String> versions,
            boolean avoidSaveProject) throws PersistenceException {
        IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();
        IRepositoryViewObject obj = factory.getSpecificVersion(id, oldVersion, avoidSaveProject);
        Item item = obj.getProperty().getItem();
        // String itemVersion = item.getProperty().getVersion();
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        Project project = new Project(ProjectManager.getInstance().getProject(item));
        if (!loaded) {
            loadRelations();
        }
        if (!currentProject.equals(project)) { // only current project
            return;
        }
        ProcessType processType = null;
        if (item instanceof ProcessItem) {
            processType = ((ProcessItem) item).getProcess();
        }
        if (item instanceof JobletProcessItem) {
            processType = ((JobletProcessItem) item).getJobletProcess();
        }
        for (Object o : processType.getNode()) {
            if (o instanceof NodeType) {
                NodeType currentNode = (NodeType) o;
                if ("tRunJob".equals(currentNode.getComponentName())) { //$NON-NLS-1$
                    // in case of tRunJob
                    String jobId = null;
                    String jobVersion = ItemCacheManager.LATEST_VERSION;
                    String nowVersion = "";
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
                                if (jobVersion.equals(ItemCacheManager.LATEST_VERSION)) {
                                    if (!versions.isEmpty()) {
                                        nowVersion = versions.get(jobId);
                                        param.setValue(nowVersion);
                                    }
                                }
                            }
                        }
                    }
                    if (jobId != null) {
                        addRelationShip(item, jobId, nowVersion, JOB_RELATION);
                        // factory.save(project, item.getProperty());
                        factory.save(project, item);
                    }
                }
            }
        }
        if (!avoidSaveProject) {
            saveRelations();
        }
    }

    public void addOrUpdateItem(Item item) {
        addOrUpdateItem(item, false);
    }

    public void addOrUpdateItem(Item item, boolean fromMigration) {
        if (!loaded) {
            loadRelations();
        }
        modified = true;

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
            for (Object o : processType.getParameters().getRoutinesParameter()) {
                RoutinesParameterType itemInfor = (RoutinesParameterType) o;
                addRelationShip(item, itemInfor.getId(), ItemCacheManager.LATEST_VERSION, ROUTINE_RELATION, itemInfor.getName());
            }
            if (processType.getParameters() != null) {
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
                        IComponent cc = ComponentsFactoryProvider.getInstance().get(currentNode.getComponentName());
                        IJobletProviderService service = null;
                        if (PluginChecker.isJobLetPluginLoaded()) {
                            service = (IJobletProviderService) GlobalServiceRegister.getDefault().getService(
                                    IJobletProviderService.class);
                        }

                        Property property = service.getJobletComponentItem(cc);
                        if (property != null)
                            addRelationShip(item, property.getId(), version, JOBLET_RELATION);
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
        if (!fromMigration) {
            saveRelations();
        }
    }

    public void removeItemRelations(Item item) {
        if (!loaded) {
            loadRelations();
        }
        modified = true;

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

            Map<Relation, List<Relation>> itemRelations = getRelatedRelations(item);

            if (itemRelations.containsKey(relation)) {
                itemRelations.get(relation).clear();
                itemRelations.remove(relation);
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

        private String name;

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
            result = prime * result + ((name == null) ? 0 : name.hashCode());
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
            // if (name == null) {
            // if (other.name != null)
            // return false;
            // } else if (!name.equals(other.name)) {
            // return false;
            // }
            return true;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
