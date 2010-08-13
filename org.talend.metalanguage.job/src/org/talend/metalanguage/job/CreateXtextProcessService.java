package org.talend.metalanguage.job;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.routines.RoutinesUtil;
import org.talend.core.ui.ICreateXtextProcessService;
import org.talend.designer.core.model.utils.emf.talendfile.ItemInforType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.repository.model.IProxyRepositoryFactory;

public class CreateXtextProcessService implements ICreateXtextProcessService {

    private ProcessItem processItem;

    private Property property;

    private ProcessType process;

    private IProxyRepositoryFactory repositoryFactory;

    public CreateXtextProcessService() {
    }

    public void createProcessItem(String jobName, String path) {
        property = PropertiesFactory.eINSTANCE.createProperty();
        process = TalendFileFactory.eINSTANCE.createProcessType();
        processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();

        this.addProperty(jobName);
        this.addProcess(path);
    }

    // create job's property and give parameters default values except label.
    public void addProperty(String jobName) {
        property.setId(repositoryFactory.getNextId());
        property.setLabel(jobName);
        processItem.setProperty(property);
    }

    // create the processType with a .job file created by metalanguage.
    public void addProcess(String path) {
        try {
            JobStandaloneSetup.doSetup();
            URI uri = URI.createFileURI(path);
            Resource resource = new ResourceSetImpl().getResource(uri, true);
            process = (ProcessType) resource.getContents().get(0);
            EList nodeList = process.getNode();
            for (Object node : nodeList) {
                org.talend.designer.core.model.utils.emf.talendfile.NodeType nodeType = (org.talend.designer.core.model.utils.emf.talendfile.NodeType) node;
                nodeType.setPosX(nodeType.getPosX() * 50);
                nodeType.setPosY(nodeType.getPosY() * 50);
                EList elementsParametersElist = nodeType.getElementParameter();
                for (Object element : elementsParametersElist) {
                    org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType elementParameterType = (org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType) element;
                    // UNIQUE_NAME is not like other elementParameters. it is set in setComponentSetting.
                    if (elementParameterType.getName() == null) {
                        elementParameterType.setName("UNIQUE_NAME");
                        break;
                    }
                }
            }

            // add depended routines.
            List<ItemInforType> dependenciesInPreference = RoutinesUtil.createDependenciesInPreference();
            process.getRoutinesDependencies().addAll(dependenciesInPreference);
            processItem.setProcess(process);
            repositoryFactory.create(processItem, new org.eclipse.core.runtime.Path(""));
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }

    }

    public ProcessItem getProcessItem() {
        return this.processItem;
    }

}
