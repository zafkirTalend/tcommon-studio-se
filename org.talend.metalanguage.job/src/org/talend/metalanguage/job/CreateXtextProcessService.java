package org.talend.metalanguage.job;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.components.IODataComponent;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.MetadataTable;
import org.talend.core.model.process.IExternalNode;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.routines.RoutinesUtil;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.core.ui.ICreateXtextProcessService;
import org.talend.designer.core.model.components.ExternalUtilities;
import org.talend.designer.core.model.utils.emf.talendfile.ElementValueType;
import org.talend.designer.core.model.utils.emf.talendfile.ItemInforType;
import org.talend.designer.core.model.utils.emf.talendfile.NodeType;
import org.talend.designer.core.model.utils.emf.talendfile.ProcessType;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFileFactory;
import org.talend.designer.core.model.utils.emf.talendfile.impl.ElementValueTypeImpl;
import org.talend.designer.core.model.utils.emf.talendfile.impl.MetadataTypeImpl;
import org.talend.designer.core.ui.editor.cmd.ConnectionCreateCommand;
import org.talend.designer.core.ui.editor.cmd.ExternalNodeChangeCommand;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainer;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.dbmap.model.emf.dbmap.DBMapperTableEntry;
import org.talend.designer.mapper.external.data.ExternalMapperData;
import org.talend.designer.mapper.external.data.ExternalMapperTable;
import org.talend.designer.mapper.external.data.ExternalMapperTableEntry;
import org.talend.designer.mapper.external.data.ExternalMapperUiProperties;
import org.talend.designer.mapper.model.emf.mapper.MapperTableEntry;
import org.talend.designer.mapper.model.emf.mapper.OutputTable;
import org.talend.repository.model.ComponentsFactoryProvider;
import org.talend.repository.model.IProxyRepositoryFactory;

public class CreateXtextProcessService implements ICreateXtextProcessService {

    private ProcessItem processItem;

    private Property property;

    private ProcessType processType;

    private IProxyRepositoryFactory repositoryFactory;

    private org.talend.designer.core.ui.editor.process.Process process;

    private HashMap<String, Node> nodeMap = new HashMap<String, Node>();

    private HashMap<String, NodeType> nodeTypeMap = new HashMap<String, NodeType>();

    private final String defaultVersion = "0.1";

    public CreateXtextProcessService() {
    }

    public void createProcessItem(String jobName, String path) {
        property = PropertiesFactory.eINSTANCE.createProperty();
        processType = TalendFileFactory.eINSTANCE.createProcessType();
        processItem = PropertiesFactory.eINSTANCE.createProcessItem();
        repositoryFactory = CorePlugin.getDefault().getRepositoryService().getProxyRepositoryFactory();
        this.addProperty(jobName);
        process = new org.talend.designer.core.ui.editor.process.Process(property);
        this.addProcess(path);
    }

    // create job's property and give parameters default values except label.
    public void addProperty(String jobName) {
        property.setId(repositoryFactory.getNextId());
        property.setLabel(jobName);
        property.setVersion(defaultVersion);
        property.setStatusCode("");
        property.setAuthor(((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getUser());
        processItem.setProperty(property);
    }

    private void checkNodes() {
        // node
        EList nodeList = processType.getNode();
        for (Object eNode : nodeList) {
            org.talend.designer.core.model.utils.emf.talendfile.NodeType nodeType = (org.talend.designer.core.model.utils.emf.talendfile.NodeType) eNode;
            nodeType.setPosX(nodeType.getPosX() * 50);
            nodeType.setPosY(nodeType.getPosY() * 50);

            for (Object metaTable : nodeType.getMetadata()) {
                // MetadataTypeImpl
                org.talend.designer.core.model.utils.emf.talendfile.MetadataType metadata = (MetadataTypeImpl) metaTable;
                if (metadata.getLabel() == null && metadata.getName() != null)
                    metadata.setLabel(metadata.getName());
                else if (metadata.getLabel() != null && metadata.getName() == null)
                    metadata.setName(metadata.getLabel());
            }

            EList elementsParametersElist = nodeType.getElementParameter();
            String nodeUniqueName = "";
            for (Object element : elementsParametersElist) {
                org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType elementParameterType = (org.talend.designer.core.model.utils.emf.talendfile.ElementParameterType) element;
                // UNIQUE_NAME is not like other elementParameters. it is set in setComponentSetting.
                if (elementParameterType.getValue() != null && elementParameterType.getValue().startsWith("\"")
                        && !((nodeType.getComponentName().startsWith("tELT") && nodeType.getComponentName().endsWith("Map")))) {
                    elementParameterType.setValue(TalendTextUtils.removeQuotes(elementParameterType.getValue()));
                }

                if (elementParameterType.getName() == null && elementParameterType.getValue() != null) {
                    elementParameterType.setName("UNIQUE_NAME");
                    nodeUniqueName = elementParameterType.getValue();
                }
                if (elementParameterType.getName() != null && elementParameterType.getName().equals("UNIQUE_NAME")) {
                    nodeUniqueName = elementParameterType.getValue();
                }
                if (elementParameterType.getElementValue().size() > 0) {
                    List newElementList = new ArrayList<Object>();
                    for (Object elementValue : elementParameterType.getElementValue()) {
                        ElementValueType newElementValue = new ElementValueTypeImpl();
                        String SCHEMA_COLUMN = ((ElementValueTypeImpl) elementValue).getElementRef();
                        newElementValue.setElementRef("SCHEMA_COLUMN");
                        newElementValue.setValue(SCHEMA_COLUMN);
                        newElementList.add(newElementValue);

                        ElementValueType newElementValue2 = new ElementValueTypeImpl();
                        String ARRAY = TalendTextUtils.removeQuotes(((ElementValueTypeImpl) elementValue).getValue());
                        newElementValue2.setElementRef("ARRAY");
                        newElementValue2.setValue(ARRAY);

                        newElementList.add(newElementValue2);
                    }
                    elementParameterType.getElementValue().clear();
                    elementParameterType.getElementValue().addAll(newElementList);
                }
            }
            nodeTypeMap.put(nodeUniqueName, nodeType);
            Node node = new Node(ComponentsFactoryProvider.getInstance().get(nodeType.getComponentName()), process);
            nodeMap.put(nodeUniqueName, node);
            final NodeContainer nodeContainer = new NodeContainer(node);
            process.addNodeContainer(nodeContainer);
            node.setProcess(process);// must do this
        }
    }

    private void checkConnections() {
        // add connections
        EList connectionList = processType.getConnection();
        for (Object eConnection : connectionList) {
            org.talend.designer.core.model.utils.emf.talendfile.ConnectionType connectionType = (org.talend.designer.core.model.utils.emf.talendfile.ConnectionType) eConnection;
            Node sourceNode = nodeMap.get(connectionType.getSource());
            NodeType sourceNodeType = nodeTypeMap.get(connectionType.getSource());
            Node targetNode = nodeMap.get(connectionType.getTarget());
            List<Object> listArgs = new ArrayList<Object>();
            listArgs.add(sourceNode.getUniqueName());
            listArgs.add(connectionType.getLabel()); //$NON-NLS-1$
            listArgs.add(null);
            ConnectionCreateCommand command = null;
            // true is insertTmap
            if (sourceNode.getComponent().getName().equals("tMap") || sourceNode.getComponent().getName().startsWith("tELT")
                    && sourceNode.getComponent().getName().endsWith("Map")) {
                command = new ConnectionCreateCommand(sourceNode, connectionType.getConnectorName(), listArgs, true);
            } else {
                command = new ConnectionCreateCommand(sourceNode, connectionType.getConnectorName(), listArgs);
            }
            command.setTarget(targetNode);
            execCommandStack((org.talend.designer.core.ui.editor.process.Process) sourceNode.getProcess(), command);
            // set externalData of the tMap node
            if (sourceNode.getComponent().getName().equals("tMap")) {
                sourceNodeType.setStringData(this.setTMapMessage(sourceNode, sourceNodeType, process));
            }

            // dbmap
            if (sourceNode.getComponent().getName().startsWith("tELT") && sourceNode.getComponent().getName().endsWith("Map")) {
                sourceNodeType.setStringData(this.setDBTMapMessage(sourceNode, sourceNodeType, process));
            }
        }
    }

    // create the processType with a .job file created by metalanguage.
    public void addProcess(String path) {
        try {
            JobStandaloneSetup.doSetup();
            URI uri = URI.createFileURI(path);
            Resource resource = new ResourceSetImpl().getResource(uri, true);
            processType = (ProcessType) resource.getContents().get(0);

            this.checkNodes();
            this.checkConnections();

            // add depended routines.
            List<ItemInforType> dependenciesInPreference = RoutinesUtil.createDependenciesInPreference();
            processType.getRoutinesDependencies().addAll(dependenciesInPreference);

            // write .itme directly
            processItem.setProcess(processType);
            repositoryFactory.create(processItem, new Path(""));
        } catch (PersistenceException e) {
            ExceptionHandler.process(e);
        }
    }

    private void execCommandStack(final org.talend.designer.core.ui.editor.process.Process process, final Command command) {

        CommandStack cs = process.getCommandStack();// editor.getCommandStack();
        if (cs != null) {
            cs.execute(command);
        } else {
            command.execute();
        }
    }

    private String setDBTMapMessage(org.talend.designer.core.ui.editor.nodes.Node source, NodeType sourceNodeType,
            org.talend.designer.core.ui.editor.process.Process process) {
        IExternalNode externalNode = ExternalUtilities.getExternalNodeReadyToOpen(source);
        org.talend.designer.dbmap.external.data.ExternalDbMapData externalMapperData = new org.talend.designer.dbmap.external.data.ExternalDbMapData();
        org.talend.designer.dbmap.model.emf.dbmap.impl.DBMapDataImpl mapperData = (org.talend.designer.dbmap.model.emf.dbmap.impl.DBMapDataImpl) sourceNodeType
                .getNodeData();

        // set DBMap's input metatable
        for (int i = 0; i < externalNode.getIODataComponents().getInputs().size(); i++) {
            IODataComponent iData = externalNode.getIODataComponents().getInputs().get(i);
            org.talend.designer.dbmap.external.data.ExternalDbMapTable externalInputMapperTable = new org.talend.designer.dbmap.external.data.ExternalDbMapTable();
            org.talend.core.model.metadata.MetadataTable metaDataTable = new MetadataTable();
            for (org.talend.designer.dbmap.model.emf.dbmap.InputTable inputTable : mapperData.getInputTables()) {
                if (iData.getName().equals(inputTable.getName())) {
                    metaDataTable.setTableName(iData.getName());
                    externalInputMapperTable.setName(inputTable.getName());
                    externalInputMapperTable.setAlias(inputTable.getAlias());
                    externalInputMapperTable.setJoinType(inputTable.getJoinType());
                    externalInputMapperTable.setTableName(inputTable.getTableName());
                    externalInputMapperTable.setMinimized(inputTable.isMinimized());
                    List<org.talend.designer.dbmap.external.data.ExternalDbMapEntry> entryList = new ArrayList<org.talend.designer.dbmap.external.data.ExternalDbMapEntry>();
                    for (org.talend.designer.dbmap.model.emf.dbmap.DBMapperTableEntry mapperTableEntry : inputTable
                            .getDBMapperTableEntries()) {
                        org.talend.designer.dbmap.external.data.ExternalDbMapEntry entry = new org.talend.designer.dbmap.external.data.ExternalDbMapEntry(
                                mapperTableEntry.getName(), TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                        entry.setJoin(mapperTableEntry.isJoin());
                        entry.setOperator(mapperTableEntry.getOperator());
                        entryList.add(entry);

                        MetadataColumn mc = new MetadataColumn();
                        mc.setLabel(mapperTableEntry.getName());
                        mc.setNullable(mapperTableEntry.isNullable());
                        mc.setType(mapperTableEntry.getType());
                        metaDataTable.getListColumns().add(mc);
                    }
                    externalInputMapperTable.setMetadataTableEntries(entryList);
                    externalMapperData.getInputTables().add(externalInputMapperTable);
                    externalNode.getIODataComponents().getInputs().get(i).setTable(metaDataTable);
                    break;
                }
            }
        }

        // set DBMap's output metatable
        for (int i = 0; i < externalNode.getIODataComponents().getOuputs().size(); i++) {
            IODataComponent oData = externalNode.getIODataComponents().getOuputs().get(i);
            org.talend.designer.dbmap.external.data.ExternalDbMapTable externalOutputMapperTable = new org.talend.designer.dbmap.external.data.ExternalDbMapTable();
            org.talend.core.model.metadata.MetadataTable metaDataTable = new MetadataTable();
            org.talend.designer.core.model.utils.emf.talendfile.MetadataType metadata = new org.talend.designer.core.model.utils.emf.talendfile.impl.MetadataTypeImpl();

            for (org.talend.designer.dbmap.model.emf.dbmap.OutputTable outputTable : mapperData.getOutputTables()) {
                if (oData.getName().equals(outputTable.getName())) {
                    metaDataTable.setTableName(oData.getName());
                    externalOutputMapperTable.setName(outputTable.getName());
                    externalOutputMapperTable.setMinimized(outputTable.isMinimized());

                    metadata.setLabel(outputTable.getName());
                    metadata.setName(outputTable.getName());

                    List<org.talend.designer.dbmap.external.data.ExternalDbMapEntry> entryList = new ArrayList<org.talend.designer.dbmap.external.data.ExternalDbMapEntry>();
                    for (org.talend.designer.dbmap.model.emf.dbmap.DBMapperTableEntry mapperTableEntry : outputTable
                            .getDBMapperTableEntries()) {
                        org.talend.designer.dbmap.external.data.ExternalDbMapEntry entry = new org.talend.designer.dbmap.external.data.ExternalDbMapEntry(
                                mapperTableEntry.getName(), TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                        entryList.add(entry);

                        MetadataColumn mc = new MetadataColumn();
                        mc.setLabel(mapperTableEntry.getName());
                        mc.setNullable(mapperTableEntry.isNullable());
                        mc.setType(mapperTableEntry.getType());
                        metaDataTable.getListColumns().add(mc);

                        org.talend.designer.core.model.utils.emf.talendfile.ColumnType column = new org.talend.designer.core.model.utils.emf.talendfile.impl.ColumnTypeImpl();
                        column.setNullable(mapperTableEntry.isNullable());
                        column.setName(mapperTableEntry.getName());
                        column.setType(mapperTableEntry.getType());
                        metadata.getColumn().add(column);
                    }
                    externalOutputMapperTable.setMetadataTableEntries(entryList);

                    // check filter entries
                    List<org.talend.designer.dbmap.external.data.ExternalDbMapEntry> filterEntryList = new ArrayList<org.talend.designer.dbmap.external.data.ExternalDbMapEntry>();
                    for (org.talend.designer.dbmap.model.emf.dbmap.FilterEntry filterEntry : outputTable.getFilterEntries()) {
                        org.talend.designer.dbmap.external.data.ExternalDbMapEntry entry = new org.talend.designer.dbmap.external.data.ExternalDbMapEntry(
                                filterEntry.getName(), TalendTextUtils.removeQuotes(filterEntry.getExpression()));
                        filterEntryList.add(entry);
                    }
                    externalOutputMapperTable.setCustomConditionsEntries(filterEntryList);

                    externalMapperData.getOutputTables().add(externalOutputMapperTable);
                    externalNode.getIODataComponents().getOuputs().get(i).setTable(metaDataTable);
                    externalNode.getOutgoingConnections().get(i).getMetadataTable()
                            .setListColumns(metaDataTable.getListColumns());

                    sourceNodeType.getMetadata().add(metadata);
                    externalNode.getMetadataList().add(metaDataTable);
                    for (int j = 0; j < externalNode.getMetadataList().size(); j++) {
                        if (externalNode.getMetadataList().get(j).getTableName().equals(metaDataTable.getTableName())) {
                            externalNode.getMetadataList().get(j).getListColumns().addAll(metaDataTable.getListColumns());
                            break;
                        }
                    }

                    for (int j = 0; j < source.getMetadataList().size(); j++) {
                        if (source.getMetadataList().get(j).getTableName().equals(metaDataTable.getTableName())) {
                            source.getMetadataList().get(j).getListColumns().addAll(metaDataTable.getListColumns());
                            break;
                        }
                    }
                    break;
                }
            }
        }

        // set tMap's var metatable
        if (mapperData.getVarTables().size() > 0) {
            org.talend.designer.dbmap.external.data.ExternalDbMapTable externalVarMapperTable = new org.talend.designer.dbmap.external.data.ExternalDbMapTable();
            if (mapperData.getVarTables().get(0).getName().equals("Var")) {
                externalVarMapperTable.setName(mapperData.getVarTables().get(0).getName());
                List<org.talend.designer.dbmap.external.data.ExternalDbMapEntry> entryList = new ArrayList<org.talend.designer.dbmap.external.data.ExternalDbMapEntry>();
                for (DBMapperTableEntry mapperTableEntry : mapperData.getVarTables().get(0).getDBMapperTableEntries()) {
                    org.talend.designer.dbmap.external.data.ExternalDbMapEntry entry = new org.talend.designer.dbmap.external.data.ExternalDbMapEntry(
                            mapperTableEntry.getName(), TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                    entryList.add(entry);
                }
                externalVarMapperTable.setMetadataTableEntries(entryList);
                externalMapperData.getVarsTables().add(externalVarMapperTable);
            }
        }
        externalNode.setExternalData(externalMapperData);
        // get the serialized tables info, StringData value
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new StringWriter();
        try {
            externalNode.loadDataOut(out, writer);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }
        externalNode.setMetadataList(source.getMetadataList());
        ExternalNodeChangeCommand cmd = new ExternalNodeChangeCommand(source, externalNode);
        CommandStack cmdStack = process.getCommandStack();
        cmdStack.execute(cmd);
        return writer.toString();
    }

    private String setTMapMessage(org.talend.designer.core.ui.editor.nodes.Node source, NodeType sourceNodeType,
            org.talend.designer.core.ui.editor.process.Process process) {
        IExternalNode externalNode = ExternalUtilities.getExternalNodeReadyToOpen(source);
        // add tMap's metaData tables
        org.talend.designer.mapper.external.data.ExternalMapperData externalMapperData = new ExternalMapperData();
        org.talend.designer.mapper.model.emf.mapper.impl.MapperDataImpl mapperData = (org.talend.designer.mapper.model.emf.mapper.impl.MapperDataImpl) sourceNodeType
                .getNodeData();
        // set tMap's input metatable
        for (int i = 0; i < externalNode.getIODataComponents().getInputs().size(); i++) {
            IODataComponent iData = externalNode.getIODataComponents().getInputs().get(i);
            org.talend.designer.mapper.external.data.ExternalMapperTable externalInputMapperTable = new ExternalMapperTable();
            org.talend.core.model.metadata.MetadataTable metaDataTable = new MetadataTable();
            for (org.talend.designer.mapper.model.emf.mapper.InputTable inputTable : mapperData.getInputTables()) {
                if (iData.getName().equals(inputTable.getName())) {
                    metaDataTable.setTableName(iData.getName());
                    externalInputMapperTable.setName(inputTable.getName());
                    externalInputMapperTable.setExpressionFilter(inputTable.getExpressionFilter());
                    externalInputMapperTable.setLookupMode(inputTable.getLookupMode());
                    externalInputMapperTable.setMatchingMode(inputTable.getMatchingMode());
                    externalInputMapperTable.setSizeState(inputTable.getSizeState());
                    List<ExternalMapperTableEntry> entryList = new ArrayList<ExternalMapperTableEntry>();
                    for (MapperTableEntry mapperTableEntry : inputTable.getMapperTableEntries()) {
                        ExternalMapperTableEntry entry = new ExternalMapperTableEntry(mapperTableEntry.getName(),
                                TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                        entry.setType(mapperTableEntry.getType());
                        entry.setNullable(mapperTableEntry.isNullable());
                        entryList.add(entry);

                        MetadataColumn mc = new MetadataColumn();
                        mc.setLabel(mapperTableEntry.getName());
                        mc.setNullable(mapperTableEntry.isNullable());
                        mc.setType(mapperTableEntry.getType());
                        metaDataTable.getListColumns().add(mc);
                    }
                    externalInputMapperTable.setMetadataTableEntries(entryList);
                    externalMapperData.getInputTables().add(externalInputMapperTable);
                    externalNode.getIODataComponents().getInputs().get(i).setTable(metaDataTable);
                    break;
                }
            }
        }

        // set tMap's output metatable
        for (int i = 0; i < externalNode.getIODataComponents().getOuputs().size(); i++) {
            IODataComponent oData = externalNode.getIODataComponents().getOuputs().get(i);
            org.talend.designer.mapper.external.data.ExternalMapperTable externalOutputMapperTable = new ExternalMapperTable();
            org.talend.core.model.metadata.MetadataTable metaDataTable = new MetadataTable();
            org.talend.designer.core.model.utils.emf.talendfile.MetadataType metadata = new org.talend.designer.core.model.utils.emf.talendfile.impl.MetadataTypeImpl();

            for (OutputTable outputTable : mapperData.getOutputTables()) {
                if (oData.getName().equals(outputTable.getName())) {
                    metaDataTable.setTableName(oData.getName());
                    externalOutputMapperTable.setName(outputTable.getName());
                    externalOutputMapperTable.setExpressionFilter(outputTable.getExpressionFilter());
                    externalOutputMapperTable.setSizeState(outputTable.getSizeState());
                    metadata.setLabel(outputTable.getName());
                    metadata.setName(outputTable.getName());

                    List<ExternalMapperTableEntry> entryList = new ArrayList<ExternalMapperTableEntry>();
                    for (MapperTableEntry mapperTableEntry : outputTable.getMapperTableEntries()) {
                        ExternalMapperTableEntry entry = new ExternalMapperTableEntry(mapperTableEntry.getName(),
                                TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                        entry.setType(mapperTableEntry.getType());
                        entry.setNullable(mapperTableEntry.isNullable());
                        entryList.add(entry);

                        MetadataColumn mc = new MetadataColumn();
                        mc.setLabel(mapperTableEntry.getName());
                        mc.setNullable(mapperTableEntry.isNullable());
                        mc.setType(mapperTableEntry.getType());
                        metaDataTable.getListColumns().add(mc);

                        org.talend.designer.core.model.utils.emf.talendfile.ColumnType column = new org.talend.designer.core.model.utils.emf.talendfile.impl.ColumnTypeImpl();
                        column.setNullable(mapperTableEntry.isNullable());
                        column.setName(mapperTableEntry.getName());
                        column.setType(mapperTableEntry.getType());
                        metadata.getColumn().add(column);
                    }
                    externalOutputMapperTable.setMetadataTableEntries(entryList);
                    externalMapperData.getOutputTables().add(externalOutputMapperTable);
                    externalNode.getIODataComponents().getOuputs().get(i).setTable(metaDataTable);

                    sourceNodeType.getMetadata().add(metadata);
                    for (int j = 0; j < externalNode.getMetadataList().size(); j++) {
                        if (externalNode.getMetadataList().get(j).getTableName().equals(metaDataTable.getTableName())) {
                            externalNode.getMetadataList().get(j).getListColumns().addAll(metaDataTable.getListColumns());
                            break;
                        }
                    }

                    for (int j = 0; j < source.getMetadataList().size(); j++) {
                        if (source.getMetadataList().get(j).getTableName().equals(metaDataTable.getTableName())) {
                            source.getMetadataList().get(j).getListColumns().addAll(metaDataTable.getListColumns());
                            break;
                        }
                    }
                    break;
                }
            }
        }

        // set tMap's var metatable
        org.talend.designer.mapper.external.data.ExternalMapperTable externalVarMapperTable = new ExternalMapperTable();
        if (mapperData.getVarTables().get(0).getName().equals("Var")) {
            externalVarMapperTable.setName(mapperData.getVarTables().get(0).getName());
            externalVarMapperTable.setSizeState(mapperData.getVarTables().get(0).getSizeState());
            List<ExternalMapperTableEntry> entryList = new ArrayList<ExternalMapperTableEntry>();
            for (MapperTableEntry mapperTableEntry : mapperData.getVarTables().get(0).getMapperTableEntries()) {
                ExternalMapperTableEntry entry = new ExternalMapperTableEntry(mapperTableEntry.getName(),
                        TalendTextUtils.removeQuotes(mapperTableEntry.getExpression()));
                entry.setType(mapperTableEntry.getType());
                entry.setNullable(mapperTableEntry.isNullable());
                entryList.add(entry);
            }
            externalVarMapperTable.setMetadataTableEntries(entryList);
            externalMapperData.getVarsTables().add(externalVarMapperTable);
        }

        // ui properties
        org.talend.designer.mapper.external.data.ExternalMapperUiProperties externalMapperUiProperties = new ExternalMapperUiProperties();
        if (mapperData.getUiProperties() != null)
            externalMapperUiProperties.setShellMaximized(mapperData.getUiProperties().isShellMaximized());
        externalMapperData.setUiProperties(externalMapperUiProperties);

        externalNode.setExternalData(externalMapperData);
        // get the serialized tables info, StringData value
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Writer writer = new StringWriter();
        try {
            externalNode.loadDataOut(out, writer);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }

        externalNode.setMetadataList(source.getMetadataList());

        ExternalNodeChangeCommand cmd = new ExternalNodeChangeCommand(source, externalNode);
        CommandStack cmdStack = process.getCommandStack();
        cmdStack.execute(cmd);
        return writer.toString();
    }

    public ProcessItem getProcessItem() {
        return this.processItem;
    }

}
