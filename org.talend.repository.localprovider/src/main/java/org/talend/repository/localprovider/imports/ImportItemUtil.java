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
package org.talend.repository.localprovider.imports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.commons.exception.PersistenceException;
import org.talend.commons.utils.VersionUtils;
import org.talend.commons.utils.generation.JavaUtils;
import org.talend.core.CorePlugin;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.migration.IProjectMigrationTask;
import org.talend.core.model.migration.IProjectMigrationTask.ExecutionResult;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.FileItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.LinkDocumentationItem;
import org.talend.core.model.properties.LinkType;
import org.talend.core.model.properties.NotationHolder;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Property;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.helper.ByteArrayResource;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.designer.business.model.business.BusinessPackage;
import org.talend.designer.business.model.business.BusinessProcess;
import org.talend.designer.codegen.ICodeGeneratorService;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;
import org.talend.migrationtool.model.GetTasksHelper;
import org.talend.repository.ProjectManager;
import org.talend.repository.constants.FileConstants;
import org.talend.repository.localprovider.RepositoryLocalProviderPlugin;
import org.talend.repository.localprovider.i18n.Messages;
import org.talend.repository.localprovider.imports.ItemRecord.State;
import org.talend.repository.localprovider.imports.TreeBuilder.ProjectNode;
import org.talend.repository.localprovider.model.XmiResourceManager;
import org.talend.repository.model.ERepositoryStatus;
import org.talend.repository.model.ProxyRepositoryFactory;
import org.talend.repository.model.RepositoryNode;
import org.talend.repository.model.RepositoryNodeUtilities;
import org.talend.repository.model.RepositoryWorkUnit;
import org.talend.repository.model.RepositoryNode.ENodeType;
import org.talend.repository.model.RepositoryNode.EProperties;

/**
 */
public class ImportItemUtil {

    private static Logger log = Logger.getLogger(ImportItemUtil.class);

    private static final String SEGMENT_PARENT = ".."; //$NON-NLS-1$

    private XmiResourceManager xmiResourceManager = new XmiResourceManager();

    private boolean hasErrors = false;

    private int usedItems = 0;

    private RepositoryObjectCache cache = new RepositoryObjectCache();

    private TreeBuilder treeBuilder = new TreeBuilder();

    private Set<String> deletedItems = new HashSet<String>();

    ProjectManager projectManager = ProjectManager.getInstance();

    public void clear() {
        deletedItems.clear();
    }

    public void setErrors(boolean errors) {
        this.hasErrors = errors;
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    private static IPath getPath(RepositoryNode node) {

        if (node.getType() == ENodeType.STABLE_SYSTEM_FOLDER || node.getType() == ENodeType.SYSTEM_FOLDER) {
            String prefix = ""; //$NON-NLS-1$
            ERepositoryObjectType type = (ERepositoryObjectType) node.getProperties(EProperties.CONTENT_TYPE);
            switch (type) {
            case METADATA_FILE_DELIMITED:
            case METADATA_FILE_POSITIONAL:
            case METADATA_FILE_REGEXP:
            case METADATA_FILE_XML:
            case METADATA_FILE_LDIF:
            case METADATA_FILE_EXCEL:
            case METADATA_SALESFORCE_SCHEMA:
            case METADATA_GENERIC_SCHEMA:
            case METADATA_LDAP_SCHEMA:
            case METADATA_CONNECTIONS:
            case METADATA_SAPCONNECTIONS:
                prefix = ERepositoryObjectType.METADATA.toString();

            }
            return new Path(prefix).append(node.getLabel());
        }

        String label = node.getObject().getProperty().getLabel();
        return getPath(node.getParent()).append(label);
    }

    private boolean checkItem(ItemRecord itemRecord, boolean overwrite) {
        boolean result = false;

        try {
            boolean nameAvailable = ProxyRepositoryFactory.getInstance().isNameAvailable(itemRecord.getItem(),
                    itemRecord.getProperty().getLabel());
            org.talend.core.model.general.Project project = projectManager.getCurrentProject();
            IRepositoryObject itemWithSameId = ProxyRepositoryFactory.getInstance().getLastVersion(project,
                    itemRecord.getProperty().getId());
            boolean idAvailable = itemWithSameId == null;

            boolean isSystem = false;
            // we do not import built in routines
            if (itemRecord.getItem().eClass().equals(PropertiesPackage.eINSTANCE.getRoutineItem())) {
                RoutineItem routineItem = (RoutineItem) itemRecord.getItem();
                if (routineItem.isBuiltIn()) {
                    isSystem = true;
                }
            }

            // we do not import system sql patterns
            if (itemRecord.getItem().eClass().equals(PropertiesPackage.eINSTANCE.getSQLPatternItem())) {
                SQLPatternItem sqlPatternItem = (SQLPatternItem) itemRecord.getItem();
                if (sqlPatternItem.isSystem()) {
                    isSystem = true;
                }
            }

            if (nameAvailable) {
                if (idAvailable) {
                    if (!isSystem) {
                        result = true;
                    } else {
                        itemRecord.addError(Messages.getString("RepositoryUtil.isSystemRoutine")); //$NON-NLS-1$ 
                    }
                } else {
                    // same id but different name
                    itemRecord.setState(State.ID_EXISTED);
                    if (overwrite) {
                        result = true;
                    } else {
                        // see bug 0005222: [Import items] [Errors and Warnings]
                        // id is already in use
                        RepositoryNode nodeWithSameId = RepositoryNodeUtilities.getRepositoryNode(itemWithSameId);
                        IPath path = getPath(nodeWithSameId);
                        itemRecord.addError(Messages.getString(
                                "RepositoryUtil.idUsed", itemWithSameId.getLabel(), path.toOSString())); //$NON-NLS-1$
                    }
                }
            } else {
                if (idAvailable) {
                    // same name but different id
                    itemRecord.setState(State.NAME_EXISTED);
                } else {
                    // same name and same id
                    itemRecord.setState(State.ID_EXISTED);
                    if (overwrite) {
                        result = true;
                    }
                }
                if (!result) {
                    itemRecord.addError(Messages.getString("RepositoryUtil.nameUsed")); //$NON-NLS-1$
                }
            }

            if (result && overwrite && itemRecord.getState() == State.ID_EXISTED) {
                // if item is locked, cannot overwrite
                if (checkIfLocked(itemRecord)) {
                    itemRecord.addError(Messages.getString("RepositoryUtil.itemLocked")); //$NON-NLS-1$
                    result = false;
                }
            }
        } catch (Exception e) {
            // ignore
        }

        return result;
    }

    /**
     * DOC hcw Comment method "checkIfLocked".
     * 
     * @param itemRecord
     * @return
     * @throws PersistenceException
     */
    private boolean checkIfLocked(ItemRecord itemRecord) throws PersistenceException {
        Boolean lockState = cache.getItemLockState(itemRecord);
        if (lockState != null) {
            return lockState.booleanValue();
        }

        ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();
        List<IRepositoryObject> list = cache.findObjectsByItem(itemRecord);

        for (IRepositoryObject obj : list) {
            ERepositoryStatus status = factory.getStatus(obj);
            if (status == ERepositoryStatus.LOCK_BY_OTHER || status == ERepositoryStatus.LOCK_BY_USER) {
                itemRecord.setLocked(true);
                cache.setItemLockState(itemRecord, true);
                return true;
            }
        }

        cache.setItemLockState(itemRecord, false);
        return false;
    }

    @SuppressWarnings("unchecked")
    public List<ItemRecord> importItemRecords(final ResourcesManager manager, final List<ItemRecord> itemRecords,
            final IProgressMonitor monitor, final boolean overwrite) {
        monitor.beginTask(Messages.getString("ImportItemWizardPage.ImportSelectedItems"), itemRecords.size() + 1); //$NON-NLS-1$

        RepositoryWorkUnit repositoryWorkUnit = new RepositoryWorkUnit("Import Items") { //$NON-NLS-1$

            @Override
            public void run() throws PersistenceException {
                for (ItemRecord itemRecord : itemRecords) {
                    if (!monitor.isCanceled()) {
                        monitor.subTask(Messages.getString("ImportItemWizardPage.Importing") + itemRecord.getItemName()); //$NON-NLS-1$
                        if (itemRecord.isValid()) {
                            reinitRepository();
                            importItemRecord(manager, itemRecord, overwrite);
                            monitor.worked(1);
                        }
                    }
                }
                monitor.done();
                // cannot cancel this part
                monitor.beginTask(Messages.getString("ImportItemWizardPage.ApplyMigrationTasks"), itemRecords.size() + 1); //$NON-NLS-1$
                for (ItemRecord itemRecord : itemRecords) {
                    if (itemRecord.isImported()) {
                        reinitRepository();
                        applyMigrationTasks(itemRecord, monitor);
                    }
                    monitor.worked(1);
                }
            }
        };

        ProxyRepositoryFactory.getInstance().executeRepositoryWorkUnit(repositoryWorkUnit);

        monitor.done();
        deletedItems.clear();
        cache.clear();
        for (ItemRecord itemRecord : itemRecords) {
            itemRecord.clear();
             //bug 6252
            itemRecord.getResourceSet().getResources().clear();
        }
         //bug 6252
        XmiResourceManager.resourceSet.getResources().clear();
        XmiResourceManager.resetResourceSet();
        return itemRecords;
    }

    private void reinitRepository() {
        ProxyRepositoryFactory repFactory = ProxyRepositoryFactory.getInstance();
        if (usedItems++ > 2) {
            usedItems = 0;
            try {
                repFactory.initialize();
            } catch (PersistenceException e) {
            }
        }
    }

    private void importItemRecord(ResourcesManager manager, ItemRecord itemRecord, boolean overwrite) {
        resolveItem(manager, itemRecord);
        if (itemRecord.getItem() != null) {
            ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(itemRecord.getItem());
            IPath path = new Path(itemRecord.getItem().getState().getPath());
            ProxyRepositoryFactory repFactory = ProxyRepositoryFactory.getInstance();

            try {
                repFactory.createParentFoldersRecursively(itemType, path);
            } catch (Exception e) {
                logError(e);
                path = new Path(""); //$NON-NLS-1$
            }

            try {
                Item tmpItem = itemRecord.getItem();

                // delete existing items before importing, this should be done
                // once for a different id
                String id = itemRecord.getProperty().getId();
                if (overwrite && !itemRecord.isLocked() && itemRecord.getState() == State.ID_EXISTED
                        && !deletedItems.contains(id)) {
                    List<IRepositoryObject> list = cache.findObjectsByItem(itemRecord);
                    if (!list.isEmpty()) {
                        // this code will delete all version of item with same
                        // id
                        repFactory.forceDeleteObjectPhysical(list.get(0));
                        deletedItems.add(id);
                    }
                }

                org.talend.core.model.general.Project project = projectManager.getCurrentProject();
                IRepositoryObject lastVersion = repFactory.getLastVersion(project, tmpItem.getProperty().getId());

                User author = itemRecord.getProperty().getAuthor();
                if (author != null) {
                    if (!repFactory.setAuthorByLogin(tmpItem, author.getLogin())) {
                        tmpItem.getProperty().setAuthor(null); // author will be
                        // the logged
                        // user in
                        // create method
                    }
                }

                if (lastVersion == null) {
                    repFactory.create(tmpItem, path, true);
                    changeRoutinesPackage(tmpItem);
                    itemRecord.setImported(true);
                } else if (VersionUtils.compareTo(lastVersion.getProperty().getVersion(), tmpItem.getProperty().getVersion()) < 0) {
                    repFactory.forceCreate(tmpItem, path);
                    changeRoutinesPackage(tmpItem);
                    itemRecord.setImported(true);
                } else {
                    PersistenceException e = new PersistenceException(Messages.getString(
                            "ImportItemUtil.persistenceException", tmpItem.getProperty())); //$NON-NLS-1$
                    itemRecord.addError(e.getMessage());
                    logError(e);
                }
            } catch (Exception e) {
                itemRecord.addError(e.getMessage());
                logError(e);
            }
        }
    }

    public void changeRoutinesPackage(Item item) {
        if (item == null) {
            return;
        }
        ERepositoryObjectType itemType = ERepositoryObjectType.getItemType(item);
        if (ERepositoryObjectType.ROUTINES.equals(itemType) && item instanceof RoutineItem) {
            RoutineItem rItem = (RoutineItem) item;
            if (!rItem.isBuiltIn()) {
                //
                String routineContent = new String(rItem.getContent().getInnerContent());
                ProjectManager pManager = ProjectManager.getInstance();
                org.talend.core.model.general.Project currentProject = pManager.getCurrentProject();
                //
                // String curProjectName =
                // currentProject.getTechnicalLabel().toLowerCase();
                String oldPackage = "package(\\s)+" + JavaUtils.JAVA_ROUTINES_DIRECTORY + "\\.((\\w)+)(\\s)*;"; //$NON-NLS-1$ //$NON-NLS-2$
                // String newPackage = "package " +
                // JavaUtils.JAVA_ROUTINES_DIRECTORY + "." + curProjectName +
                // ";";

                String newPackage = "package " + JavaUtils.JAVA_ROUTINES_DIRECTORY + ";"; //$NON-NLS-1$ //$NON-NLS-2$
                try {
                    PatternCompiler compiler = new Perl5Compiler();
                    Perl5Matcher matcher = new Perl5Matcher();
                    matcher.setMultiline(true);
                    Pattern pattern = compiler.compile(oldPackage);

                    if (matcher.contains(routineContent, pattern)) {
                        String group = matcher.getMatch().group(2);
                        // if (!curProjectName.equals(group)) { // not same
                        Perl5Substitution substitution = new Perl5Substitution(newPackage, Perl5Substitution.INTERPOLATE_ALL);
                        routineContent = Util.substitute(matcher, pattern, substitution, routineContent, Util.SUBSTITUTE_ALL);

                        rItem.getContent().setInnerContent(routineContent.getBytes());
                        ProxyRepositoryFactory repFactory = ProxyRepositoryFactory.getInstance();

                        repFactory.save(rItem);
                        // }
                    }
                } catch (MalformedPatternException e) {
                    logError(e);
                } catch (PersistenceException e) {
                    logError(e);
                }
            }
        }
    }

    private void applyMigrationTasks(ItemRecord itemRecord, IProgressMonitor monitor) {
        Context ctx = CorePlugin.getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        ITalendSynchronizer routineSynchronizer = getRoutineSynchronizer();

        Item item = null;
        try {
            item = ProxyRepositoryFactory.getInstance().getUptodateProperty(itemRecord.getItem().getProperty()).getItem();
        } catch (Exception e) {
            logError(e);
        }

        for (String taskId : itemRecord.getMigrationTasksToApply()) {
            IProjectMigrationTask task = GetTasksHelper.getProjectTask(taskId);
            if (task == null) {
                log.warn(Messages.getString("ImportItemUtil.taskLogWarn", taskId)); //$NON-NLS-1$
            } else {
                monitor.subTask(Messages.getString("ImportItemUtil.taskMonitor", task.getName(), itemRecord.getItemName())); //$NON-NLS-1$

                try {
                    if (item != null) {
                        ExecutionResult executionResult = task.execute(repositoryContext.getProject(), item);
                        if (executionResult == ExecutionResult.FAILURE) {
                            log.warn(Messages.getString("ImportItemUtil.itemLogWarn", itemRecord.getItemName(), task.getName())); //$NON-NLS-1$
                            // TODO smallet add a warning/error to the job using
                            // model
                        }
                    }
                } catch (Exception e) {
                    log.warn(Messages.getString("ImportItemUtil.itemLogException", itemRecord.getItemName(), task.getName())); //$NON-NLS-1$

                }
            }
        }

        try {
            if (item != null && item instanceof RoutineItem) {
                RoutineItem routineItem = (RoutineItem) item;
                routineSynchronizer.forceSyncRoutine(routineItem);
                routineSynchronizer.syncRoutine(routineItem, true);
                routineSynchronizer.getFile(routineItem);
            }
        } catch (Exception e) {
            logError(e);
        }

    }

    private ITalendSynchronizer getRoutineSynchronizer() {

        ICodeGeneratorService service = (ICodeGeneratorService) GlobalServiceRegister.getDefault().getService(
                ICodeGeneratorService.class);

        ECodeLanguage lang = ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
                .getProject().getLanguage();
        ITalendSynchronizer routineSynchronizer = null;
        switch (lang) {
        case JAVA:
            routineSynchronizer = service.createJavaRoutineSynchronizer();
            break;
        case PERL:
            routineSynchronizer = service.createPerlRoutineSynchronizer();
            break;
        default:
            throw new UnsupportedOperationException(Messages.getString("ImportItemUtil.unknowException", lang)); //$NON-NLS-1$
        }

        return routineSynchronizer;

    }

    private void logError(Exception e) {
        hasErrors = true;
        IStatus status;
        String messageStatus = e.getMessage() != null ? e.getMessage() : ""; //$NON-NLS-1$
        status = new Status(IStatus.ERROR, RepositoryLocalProviderPlugin.PLUGIN_ID, IStatus.OK, messageStatus, e);
        RepositoryLocalProviderPlugin.getDefault().getLog().log(status);
    }

    public List<ProjectNode> getTreeViewInput() {
        return treeBuilder.getInput();
    }

    /**
     * need to returns sorted items by version to correctly import them later.
     */
    public List<ItemRecord> populateItems(ResourcesManager collector, boolean overwrite, IProgressMonitor progressMonitor) {
        treeBuilder.clear();
        cache.clear();
        List<ItemRecord> items = new ArrayList<ItemRecord>();

        int nbItems = 0;
        for (IPath path : collector.getPaths()) {
            if (isPropertyPath(path)) {
                nbItems++;
            }
        }

        progressMonitor.beginTask("Populate items to import", nbItems); //$NON-NLS-1$

        for (IPath path : collector.getPaths()) {
            if (isPropertyPath(path)) {
                IPath itemPath = getItemPath(path);
                if (collector.getPaths().contains(itemPath)) {
                    ItemRecord itemRecord = computeItemRecord(collector, path);
                    if (itemRecord.getProperty() != null) {
                        items.add(itemRecord);

                        if (checkItem(itemRecord, overwrite)) {
                            InternalEObject author = (InternalEObject) itemRecord.getProperty().getAuthor();
                            URI uri = null;
                            if (author != null) {
                                uri = author.eProxyURI();
                            }

                            IPath projectFilePath = getValidProjectFilePath(collector, path, uri);
                            if (projectFilePath != null) {
                                Project project = computeProject(collector, itemRecord, projectFilePath);
                                if (checkProject(project, itemRecord)) {
                                    treeBuilder.addItem(project, itemRecord);
                                    // we can try to import item
                                    // and we will try to resolve user
                                    User user = (User) project.eResource().getEObject(uri.fragment());
                                    itemRecord.getProperty().setAuthor(user);
                                }
                            } else {
                                itemRecord.addError(Messages.getString("RepositoryUtil.ProjectNotFound")); //$NON-NLS-1$
                            }
                        }
                    }
                }
                progressMonitor.worked(1);
            }
        }

        Collections.sort(items, new Comparator<ItemRecord>() {

            public int compare(ItemRecord o1, ItemRecord o2) {
                return VersionUtils.compareTo(o1.getProperty().getVersion(), o2.getProperty().getVersion());
            }
        });

        return items;
    }

    private boolean checkProject(Project project, ItemRecord itemRecord) {
        boolean checkProject = false;

        // Context ctx = CorePlugin.getContext();
        // RepositoryContext repositoryContext = (RepositoryContext)
        // ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        // Project currentProject =
        // repositoryContext.getProject().getEmfProject();
        Project currentProject = projectManager.getCurrentProject().getEmfProject();

        if (project != null) {
            if (project.getLanguage().equals(currentProject.getLanguage())) {
                if (checkMigrationTasks(project, itemRecord, currentProject)) {
                    checkProject = true;
                }
            } else {
                itemRecord.addError(Messages.getString("RepositoryUtil.DifferentLanguage", project.getLanguage(), currentProject //$NON-NLS-1$
                        .getLanguage()));
            }
        } else {
            itemRecord.addError(Messages.getString("RepositoryUtil.ProjectNotFound")); //$NON-NLS-1$
        }

        return checkProject;
    }

    private List<String> getOptionnalMigrationTasks() {
        List<String> toReturn = new ArrayList<String>();

        toReturn.add("org.talend.repository.documentation.migrationtask.generatejobdocmigrationtask"); //$NON-NLS-1$
        // old task, added for an old version of TOS, not used anymore.
        toReturn.add("org.talend.repository.migration.ReplaceOldContextScriptCodeMigrationTask"); //$NON-NLS-1$
        toReturn.add("org.talend.designer.core.model.process.migration.SynchronizeSchemaOnlyForPerlDemo"); //$NON-NLS-1$

        return toReturn;
    }

    @SuppressWarnings("unchecked")
    private boolean checkMigrationTasks(Project project, ItemRecord itemRecord, Project currentProject) {
        List<String> itemMigrationTasks = new ArrayList(project.getMigrationTasks());
        List<String> projectMigrationTasks = new ArrayList(currentProject.getMigrationTasks());

        itemMigrationTasks.removeAll(getOptionnalMigrationTasks());

        // 1. Check if all the migration tasks of the items are done in the
        // project:
        // if not, the item use a more recent version of TOS: impossible to
        // import (forward compatibility)
        if (!projectMigrationTasks.containsAll(itemMigrationTasks)) {
            itemMigrationTasks.removeAll(projectMigrationTasks);

            String message = Messages.getString("ImportItemUtil.message", itemRecord.getItemName(), itemMigrationTasks); //$NON-NLS-1$
            itemRecord.addError(message);
            log.info(message);

            return false;
        }

        // 2. Get all the migration tasks to apply on this item on import
        // (backwards compatibility)
        // (those that are in the project but not in the item)
        projectMigrationTasks.removeAll(itemMigrationTasks);
        itemRecord.setMigrationTasksToApply(projectMigrationTasks);

        return true;
    }

    private IPath getValidProjectFilePath(ResourcesManager collector, IPath path, URI uri) {
        IPath projectFilePath = getSiblingProjectFilePath(path);
        if (!collector.getPaths().contains(projectFilePath)) {
            projectFilePath = getAuthorProjectFilePath(uri, path);
            if (!collector.getPaths().contains(projectFilePath)) {
                return null;
            }
        }

        return projectFilePath;
    }

    private IPath getAuthorProjectFilePath(URI uri, IPath path) {
        IPath projectFilePath = path.removeLastSegments(1);

        if (uri != null) {
            for (int i = 0; i < uri.segments().length; i++) {
                String segment = uri.segments()[i];
                if (segment.equals(SEGMENT_PARENT)) {
                    projectFilePath = projectFilePath.removeLastSegments(1);
                } else {
                    projectFilePath = projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME);
                }
            }
        }

        return projectFilePath;
    }

    // usefull when you export a job with source in an archive
    private IPath getSiblingProjectFilePath(IPath path) {
        IPath projectFilePath = path.removeLastSegments(1);
        projectFilePath = projectFilePath.append(FileConstants.LOCAL_PROJECT_FILENAME);

        return projectFilePath;
    }

    private ItemRecord computeItemRecord(ResourcesManager collector, IPath path) {
        ItemRecord itemRecord = new ItemRecord(path);
        computeProperty(collector, itemRecord);
        return itemRecord;
    }

    private void computeProperty(ResourcesManager manager, ItemRecord itemRecord) {
        InputStream stream = null;
        try {
            stream = manager.getStream(itemRecord.getPath());
            Resource resource = createResource(itemRecord.getResourceSet(), itemRecord.getPath(), false);
            resource.load(stream, null);
            itemRecord.setProperty((Property) EcoreUtil.getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE
                    .getProperty()));
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    public void resolveItem(ResourcesManager manager, ItemRecord itemRecord) {
        if (itemRecord.isResolved()) {
            return;
        }

        InputStream stream = null;

        try {
            boolean byteArray = (itemRecord.getItem() instanceof FileItem);
            IPath itemPath = getItemPath(itemRecord.getPath());
            stream = manager.getStream(itemPath);
            Resource resource = createResource(itemRecord.getResourceSet(), itemPath, byteArray);
            resource.load(stream, null);
            resetItemReference(itemRecord, resource);
            EcoreUtil.resolveAll(itemRecord.getResourceSet());
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        itemRecord.setResolved(true);
    }

    /**
     * 
     * cLi Comment method "resetItemReference".
     * 
     * resolve the encode some special character(bug 6252), maybe, It's not better to resolve this by manually.
     * 
     * such as, "[" is "%5B", "]" is "%5D", etc.
     */
    @SuppressWarnings("unchecked")
    private void resetItemReference(ItemRecord itemRecord, Resource resource) {
        Item item = itemRecord.getItem();
        EList<EObject> contents = resource.getContents();
        /*
         * ignore job. no need, because it can't be allowed input special char for name.
         */
        if (item instanceof ProcessItem) {
            // ((ProcessItem) item).setProcess((ProcessType) EcoreUtil.getObjectByType(contents,
            // TalendFilePackage.eINSTANCE
            // .getProcessType()));
        } else
        /*
         * ignore joblet. no need, because it can't be allowed input special char for name.
         */
        if (item instanceof JobletProcessItem) {
            // JobletProcessItem jobletProcessItem = (JobletProcessItem) item;
            //
            // jobletProcessItem.setJobletProcess((JobletProcess) EcoreUtil.getObjectByType(contents,
            // JobletPackage.eINSTANCE
            // .getJobletProcess()));
            // jobletProcessItem
            // .setIcon((ByteArray) EcoreUtil.getObjectByType(contents, PropertiesPackage.eINSTANCE.getByteArray()));
        } else
        // connectionItem
        if (item instanceof ConnectionItem) {
            ((ConnectionItem) item).setConnection((Connection) EcoreUtil.getObjectByType(contents, ConnectionPackage.eINSTANCE
                    .getConnection()));
        } else
        // context
        if (item instanceof ContextItem) {
            EList contexts = ((ContextItem) item).getContext();
            contexts.clear();
            contexts.addAll(EcoreUtil.getObjectsByType(contents, TalendFilePackage.eINSTANCE.getContextType()));
        } else
        // file
        if (item instanceof FileItem) {
            /*
             * ignore routine, no need, because it can't be allowed input special char for name.
             */
            if (item instanceof RoutineItem) {
                return;
            }
            FileItem fileItem = (FileItem) item;
            fileItem.setContent((ByteArray) EcoreUtil.getObjectByType(contents, PropertiesPackage.eINSTANCE.getByteArray()));
        } else
        // snippet
        if (item instanceof SnippetItem) {
            EList variables = ((SnippetItem) item).getVariables();
            variables.clear();
            variables.addAll(EcoreUtil.getObjectsByType(contents, PropertiesPackage.eINSTANCE.getSnippetVariable()));
        } else
        // link doc
        if (item instanceof LinkDocumentationItem) {
            ((LinkDocumentationItem) item).setLink((LinkType) EcoreUtil.getObjectByType(contents, PropertiesPackage.eINSTANCE
                    .getLinkType()));
        } else
        // business
        if (item instanceof BusinessProcessItem) {
            BusinessProcessItem businessProcessItem = (BusinessProcessItem) item;

            businessProcessItem.setSemantic((BusinessProcess) EcoreUtil.getObjectByType(contents, BusinessPackage.eINSTANCE
                    .getBusinessProcess()));

            businessProcessItem.setNotationHolder((NotationHolder) EcoreUtil.getObjectByType(contents,
                    PropertiesPackage.eINSTANCE.getNotationHolder()));
        }

    }

    private Project computeProject(ResourcesManager manager, ItemRecord itemRecord, IPath path) {
        InputStream stream = null;

        try {
            stream = manager.getStream(path);
            Resource resource = createResource(itemRecord.getResourceSet(), path, false);
            resource.load(stream, null);
            return (Project) EcoreUtil.getObjectByType(resource.getContents(), PropertiesPackage.eINSTANCE.getProject());
        } catch (IOException e) {
            // ignore
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return null;
    }

    private Resource createResource(ResourceSet resourceSet, IPath path, boolean byteArrayResource) throws FileNotFoundException {
        Resource resource;
        if (byteArrayResource) {
            resource = new ByteArrayResource(getURI(path));
            resourceSet.getResources().add(resource);
        } else {
            resource = resourceSet.createResource(getURI(path));
        }
        return resource;
    }

    private URI getURI(IPath path) {
        return URI.createURI(path.lastSegment());
    }

    private boolean isPropertyPath(IPath path) {
        return xmiResourceManager.isPropertyFile(path.lastSegment());
    }

    private IPath getItemPath(IPath path) {
        return path.removeFileExtension().addFileExtension(FileConstants.ITEM_EXTENSION);
    }

    /**
     * 
     * DOC hcw ImportItemUtil class global comment. Detailled comment
     */
    static class RepositoryObjectCache {

        static ProxyRepositoryFactory factory = ProxyRepositoryFactory.getInstance();

        private Set<ERepositoryObjectType> types = new HashSet<ERepositoryObjectType>();

        private Map<String, Boolean> lockState = new HashMap<String, Boolean>();

        // key is id of IRepositoryObject, value is a list of IRepositoryObject
        // with same id
        private Map<String, List<IRepositoryObject>> cache = new HashMap<String, List<IRepositoryObject>>();

        public List<IRepositoryObject> findObjectsByItem(ItemRecord itemRecord) throws PersistenceException {
            Item item = itemRecord.getItem();
            ERepositoryObjectType type = ERepositoryObjectType.getItemType(item);
            if (!types.contains(type)) {
                types.add(type);
                // load object by type
                List<IRepositoryObject> list = factory.getAll(type, true, true);
                for (IRepositoryObject obj : list) {
                    // items with same id
                    List<IRepositoryObject> items = cache.get(obj.getId());
                    if (items == null) {
                        items = new ArrayList<IRepositoryObject>();
                        cache.put(obj.getId(), items);
                    }
                    items.add(obj);
                }
            }

            List<IRepositoryObject> result = cache.get(itemRecord.getProperty().getId());
            if (result == null) {
                result = Collections.EMPTY_LIST;
            }
            return result;
        }

        public void setItemLockState(ItemRecord itemRecord, boolean state) {
            lockState.put(itemRecord.getProperty().getId(), state);
        }

        public Boolean getItemLockState(ItemRecord itemRecord) {
            return lockState.get(itemRecord.getProperty().getId());
        }

        public void clear() {
            types.clear();
            cache.clear();
            lockState.clear();
        }
    }
}
