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
package org.talend.designer.webservice.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.talend.commons.ui.image.EImage;
import org.talend.commons.ui.image.ImageProvider;
import org.talend.commons.ui.swt.advanced.dataeditor.AbstractDataTableEditorView;
import org.talend.commons.ui.swt.drawing.background.BackgroundRefresher;
import org.talend.commons.ui.swt.extended.table.ExtendedTableModel;
import org.talend.commons.ui.swt.formtools.LabelledFileField;
import org.talend.commons.ui.swt.linking.TableToTablesLinker;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreatorColumn;
import org.talend.commons.ui.swt.tableviewer.TableViewerCreator.CELL_EDITOR_STATE;
import org.talend.commons.ui.swt.tableviewer.celleditor.DialogErrorForCellEditorListener;
import org.talend.commons.ui.utils.PathUtils;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.talend.designer.webservice.WebServiceComponent;
import org.talend.designer.webservice.WebServiceComponentMain;
import org.talend.designer.webservice.data.ExternalWebServiceUIProperties;
import org.talend.designer.webservice.data.InputMappingData;
import org.talend.designer.webservice.data.OutPutMappingData;
import org.talend.designer.webservice.i18n.Messages;
import org.talend.designer.webservice.managers.UIManager;
import org.talend.designer.webservice.managers.WebServiceManager;
import org.talend.designer.webservice.ui.dialog.AddListDialog;
import org.talend.designer.webservice.ui.dialog.WebServiceEventListener;
import org.talend.designer.webservice.ui.dnd.DragAndDropForWebService;
import org.talend.designer.webservice.ui.link.WebServiceTableLiner;
import org.talend.designer.webservice.ws.WSDLDiscoveryHelper;
import org.talend.designer.webservice.ws.wsdlinfo.Function;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

/**
 * gcui class global comment. Detailled comment
 */
public class WebServiceUI {

    protected int maximumRowsToPreview = CorePlugin.getDefault().getPreferenceStore().getInt(
            ITalendCorePrefConstants.PREVIEW_LIMIT);

    private WebServiceManager webServiceManager;

    private Composite uiParent;

    private LabelledFileField wsdlField;

    private TableViewer wsdlTableViewer;

    private Label operationLabel;

    private CTabFolder tabFolder;

    private CTabItem wsdlTabItem;

    private CTabItem inputMappingTabItem;

    private CTabItem outputMappingTabItem;

    private Composite outputComposite;

    private String inComeName;

    private String outComeName;

    private Composite inputComposite;

    private Composite wsdlComposite;

    private SashForm allContentForm;

    private TableToTablesLinker<Object, Object> tabTotabLinkForin;

    private TableToTablesLinker<Object, Object> tabTotabLinkForout;

    private BackgroundRefresher backgroundRefresher;

    private AbstractDataTableEditorView<Function> listTableView;

    private AbstractDataTableEditorView<IMetadataColumn> rowinPutTableView;

    private AbstractDataTableEditorView<ParameterInfo> rowoutPutTableView;

    private AbstractDataTableEditorView<InputMappingData> expressinPutTableView;

    private AbstractDataTableEditorView<OutPutMappingData> expressoutPutTableView;

    private WebServiceEventListener listener;

    private TreeViewer treeView;

    private Tree tree;

    private Button refreshbut;

    private Table listTable;

    private Button addListButForIn;

    private Button removeButForIn;

    private Button removeButForout;

    private Button normalizeButForIn;

    private Button denorButForIn;

    private Button addListButForOut;

    private Button normalizeButForOut;

    private Button denorButForOut;

    private Table expressTableForIn;

    private Table expressTableForout;

    private Table rowTableForout;

    private Table rowTableForin;

    private WebServiceComponent connector;

    private static int DEFAULT_INDEX = 0;

    private int selectedColumnIndex = DEFAULT_INDEX;

    private List<Function> allfunList = new ArrayList<Function>();

    private List<ParameterInfo> inParaList = new ArrayList<ParameterInfo>();

    private List<ParameterInfo> outParaList = new ArrayList<ParameterInfo>();

    private List<InputMappingData> inputMappingList = new ArrayList<InputMappingData>();

    private List<IMetadataColumn> inPutcolumnList = new ArrayList<IMetadataColumn>();

    private List<OutPutMappingData> outPutcolumnList = new ArrayList<OutPutMappingData>();

    private String URLValue;

    private InputMappingData currentInputMappingData;

    private ParameterInfo currentSelectedInChildren;

    private ParameterInfo currentOutputParameter;

    private ParameterInfo currentSelectedOutChildren;

    private OutPutMappingData currentSelectedOutExpress;

    private Function currentFunction;

    private List<IMetadataColumn> forOutColumnList = new ArrayList<IMetadataColumn>();

    private int currentElementIndexForIn = -1;

    private int currentElementIndexForOut = -1;

    private int currentIndexForOutExpress = -1;

    private String originalProxyHost;

    private String originalProxyPort;

    private String originalProxyUser;

    private String originalProxyPassword;

    private boolean usingProxy = false;

    public int getSelectedColumnIndex() {
        return this.selectedColumnIndex;
    }

    public WebServiceUI(Composite uiParent, WebServiceComponentMain webServiceMain) {
        super();
        this.uiParent = uiParent;
        this.webServiceManager = webServiceMain.getWebServiceManager();
        this.connector = webServiceMain.getWebServiceComponent();
        URLValue = new String();
        getInConnList();
        getOutConnList();
        getLastFunction();
        getInputElementList();
        getOutputElementList();
    }

    private void getInConnList() {
        List<? extends IConnection> inConnList = connector.getIncomingConnections();
        // List<IMetadataTable> metaList = connector.getMetadataList();
        for (int i = 0; i < inConnList.size(); i++) {
            IConnection conn = inConnList.get(i);
            if (conn == null) {
                continue;
            }
            inComeName = conn.getUniqueName();
            IMetadataTable metaTable = conn.getMetadataTable();
            if (metaTable == null) {
                continue;
            }
            inPutcolumnList.addAll(metaTable.getListColumns());
        }
        if (inComeName == null) {
            inComeName = ""; //$NON-NLS-1$
        }
    }

    private void getOutConnList() {
        List<? extends IConnection> outConnList = connector.getOutgoingSortedConnections();
        List<IMetadataColumn> list = new ArrayList<IMetadataColumn>();
        for (int i = 0; i < outConnList.size(); i++) {
            IConnection conn = outConnList.get(i);
            if (conn == null) {
                continue;
            }
            IMetadataTable metaTable = conn.getMetadataTable();
            if (metaTable == null) {
                continue;
            }
            list.addAll(metaTable.getListColumns());
        }

        for (int i = 0; i < list.size(); i++) {
            IMetadataColumn col = list.get(i);
            forOutColumnList.add(col);
            OutPutMappingData outData = new OutPutMappingData();
            outData.setOutputColumnValue(col.getLabel());
            outData.setMetadataColumn(col);
            outPutcolumnList.add(outData);
        }

    }

    private void getLastFunction() {
        IElementParameter METHODPara = connector.getElementParameter("METHOD"); //$NON-NLS-1$
        Object obj = METHODPara.getValue();
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            String wsdlUrl = (String) connector.getElementParameter("ENDPOINT").getValue(); //$NON-NLS-1$
            WSDLDiscoveryHelper ws = new WSDLDiscoveryHelper();

            WebServiceComponent webServiceComponent = webServiceManager.getWebServiceComponent();
            boolean isUseProxy = webServiceComponent.getElementParameter("USE_PROXY").getValue().toString().equals("true");
            if (isUseProxy) {
                useProxy();
            }
            List<Function> funList = ws.getFunctionsAvailable(wsdlUrl);
            if (isUseProxy && usingProxy) {
                RemoveUseProxy();
            }
            for (Function fun : funList) {
                if (fun.getName().equals(str)) {
                    allfunList.clear();
                    allfunList.add(fun);
                    return;
                }
            }

        }

    }

    private void getInputElementList() {
        IElementParameter INPUT_PARAMSPara = connector.getElementParameter("INPUT_PARAMS"); //$NON-NLS-1$
        List<Map<String, String>> inputparaValue = (List<Map<String, String>>) INPUT_PARAMSPara.getValue();
        WebServiceExpressionParser webParser = new WebServiceExpressionParser("\\s*(\\w+)\\s*\\.\\s*(\\w+)\\s*"); //$NON-NLS-1$
        List<ParameterInfo> childList = new ArrayList<ParameterInfo>();
        for (Map<String, String> map : inputparaValue) {
            boolean mark = true;
            InputMappingData data = new InputMappingData();
            if (map.get("EXPRESSION") != null && map.get("EXPRESSION") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String expr = (String) map.get("EXPRESSION"); //$NON-NLS-1$
                if (inPutcolumnList == null || inPutcolumnList.size() <= 0) {
                    return;
                }

                Map<String, String> exPmap = webParser.parseInTableEntryLocations(expr);
                data.setInputColumnValue(expr);
                for (IMetadataColumn column : inPutcolumnList) {
                    Set<Entry<String, String>> set = exPmap.entrySet();
                    Iterator<Entry<String, String>> ite = set.iterator();
                    while (ite.hasNext()) {
                        if (ite.next().getKey().equals(column.getLabel())) {// (expr.contains(column.getLabel())) {
                            List<IMetadataColumn> columnList = data.getMetadataColumnList();
                            columnList.add(column);
                        }
                    }
                }
            }
            if (map.get("ELEMENT") != null && map.get("ELEMENT") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String paraName = map.get("ELEMENT"); //$NON-NLS-1$
                if (allfunList == null || allfunList.size() <= 0) {
                    return;
                }
                Function fun = allfunList.get(0);
                List<ParameterInfo> list = fun.getInputParameters();
                goin: for (ParameterInfo para : list) {
                    if (para.getName().equals(paraName)) {
                        mark = false;
                        childList.clear();
                        data.setParameter(para);
                        data.setParameterName(paraName);
                        if (para.getParameterInfos().size() > 0) {
                            childList.addAll(new ParameterInfoUtil().getAllChildren(para));
                        }
                        break goin;
                    }
                }
                if (mark) {
                    goout: for (ParameterInfo para : childList) {
                        if (para.getName().equals(paraName)) {
                            data.setParameter(para);
                            data.setParameterName(paraName);
                            // if (para.getParameterInfos().size() > 0) {
                            // childList.addAll(para.getParameterInfos());
                            // }
                            break goout;
                        }
                    }
                }
            }
            if (("".equals(data.getInputColumnValue()) || data.getInputColumnValue() == null) //$NON-NLS-1$
                    && ("".equals(data.getParameterName()) || data.getParameterName() == null)) { //$NON-NLS-1$
                continue;
            } else {
                inputMappingList.add(data);
            }
        }
    }

    private void getOutputElementList() {
        IElementParameter OUTPUT_PARAMSPara = connector.getElementParameter("OUTPUT_PARAMS"); //$NON-NLS-1$
        List<Map<String, String>> outputMap = (List<Map<String, String>>) OUTPUT_PARAMSPara.getValue();
        List<OutPutMappingData> list = new ArrayList<OutPutMappingData>();
        WebServiceExpressionParser webParser = new WebServiceExpressionParser("\\s*(\\w+)\\s*"); //$NON-NLS-1$
        List<ParameterInfo> childList = new ArrayList<ParameterInfo>();
        for (Map<String, String> map : outputMap) {
            boolean mark = true;
            OutPutMappingData data = new OutPutMappingData();

            if (map.get("ELEMENT") != null && map.get("ELEMENT") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String ele = (String) map.get("ELEMENT"); //$NON-NLS-1$
                if (allfunList == null || allfunList.size() <= 0) {
                    return;
                }
                Function fun = allfunList.get(0);
                List<ParameterInfo> outPaList = fun.getOutputParameters();
                goin: for (ParameterInfo para : outPaList) {
                    if (para.getName().equals(ele)) {
                        childList.clear();
                        mark = false;
                        data.getParameterList().add(para);
                        data.setParameterName(para.getName());
                        if (para.getParameterInfos().size() > 0) {
                            childList.addAll(new ParameterInfoUtil().getAllChildren(para));
                        }
                        if (para != null) {
                            outParaList.add(para);
                        }
                        break goin;
                    }
                }

                if (mark) {
                    goout: for (ParameterInfo para : childList) {
                        if (para.getName().equals(ele)) {
                            data.getParameterList().add(para);
                            data.setParameterName(para.getName());
                            // if (para.getParameterInfos().size() > 0) {
                            // childList.addAll(para.getParameterInfos());
                            // }
                            if (para != null) {
                                outParaList.add(para);
                            }
                            break goout;
                        }
                    }
                }

            }

            if (map.get("EXPRESSION") != null && map.get("EXPRESSION") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String exp = (String) map.get("EXPRESSION"); //$NON-NLS-1$
                if ("".equals(exp) || exp == null) { //$NON-NLS-1$
                    continue;
                }
                data.setParameterName(exp);
                Set<String> expList = webParser.parseOutTableEntryLocations(exp);
                for (ParameterInfo outInfo : outParaList) {
                    Iterator<String> ite = expList.iterator();
                    while (ite.hasNext()) {
                        if (ite.next().equals(outInfo.getName())) {
                            data.getParameterList().add(outInfo);
                        }
                    }
                }

                if (data != null && data.getParameterName() != null) {
                    list.add(data);
                }

            }

        }
        for (int i = 0; i < list.size(); i++) {
            OutPutMappingData outData = list.get(i);
            if (outPutcolumnList.size() > i) {
                OutPutMappingData outData2 = outPutcolumnList.get(i);
                outData.setOutputColumnValue(outData2.getOutputColumnValue());
                outData.setMetadataColumn(outData2.getMetadataColumn());
            }
        }
        if (outPutcolumnList.size() > list.size()) {
            for (int i = list.size(); i < outPutcolumnList.size(); i++) {
                OutPutMappingData outData = outPutcolumnList.get(i);
                if (outData == null) {
                    continue;
                }
                list.add(outData);

            }
        }
        if (list.size() > 0) {
            outPutcolumnList.clear();
            outPutcolumnList.addAll(list);
        }
    }

    private void getOutPutExpressionList() {
    }

    /**
     * DOC Comment method "useProxy".
     */
    private void useProxy() {
        String proxyHost = "";
        String proxyPort = "";
        String proxyUser = "";
        String proxyPassword = "";
        IElementParameter proxyHostParameter = webServiceManager.getWebServiceComponent().getElementParameter("PROXY_HOST");
        IElementParameter proxyPortParameter = webServiceManager.getWebServiceComponent().getElementParameter("PROXY_PORT");
        IElementParameter proxyUserParameter = webServiceManager.getWebServiceComponent().getElementParameter("PROXY_USERNAME");
        IElementParameter proxyPasswordParameter = webServiceManager.getWebServiceComponent().getElementParameter(
                "PROXY_PASSWORD");
        if (proxyHostParameter != null) {
            proxyHost = proxyHostParameter.toString();
        }
        if (proxyPortParameter != null) {
            proxyPort = proxyPortParameter.toString();
        }
        if (proxyUserParameter != null) {
            proxyUser = proxyUserParameter.toString();
        }
        if (proxyPasswordParameter != null) {
            proxyPassword = proxyPasswordParameter.toString();
        }

        originalProxyHost = System.getProperty("http.proxyHost");
        originalProxyPort = System.getProperty("http.proxyPort");
        originalProxyUser = System.getProperty("http.proxyUser");
        originalProxyPassword = System.getProperty("http.proxyPassword");

        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPort);
        System.setProperty("http.proxyUser", proxyUser);
        System.setProperty("http.proxyPassword", proxyPassword);
        usingProxy = true;

    }

    /**
     * DOC Comment method "UnUseProxy".
     */
    private void RemoveUseProxy() {
        System.setProperty("http.proxyHost", originalProxyHost);
        System.setProperty("http.proxyPort", originalProxyPort);
        System.setProperty("http.proxyUser", originalProxyUser);
        System.setProperty("http.proxyPassword", originalProxyPassword);
        usingProxy = false;

    }

    /**
     * DOC Comment method "useAuth".
     */
    private void useAuth() {
        try {
            URL url = new URL(URLValue);
            StringBuffer des = new StringBuffer();
            if ("http".equals(url.getProtocol())) {
                des.append("http://");
                des.append("user");
                des.append(":");
                des.append("ps");
                des.append("@");
                des.append(url.getHost());
                if (url.getPort() != -1) {
                    des.append(":");
                    des.append(url.getPort());
                }
                des.append(url.getPath());
                // System.out.println(des);

            }
            URLValue = des.toString();

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * Sets the listener.
     * 
     * @param listener the listener to set
     */
    public void addListener(WebServiceEventListener listener) {
        this.listener = listener;
    }

    private Shell getShell() {
        return this.uiParent.getShell();
    }

    public void init() {
        uiParent.setLayout(new GridLayout());

        Composite composite = new Composite(uiParent, SWT.NONE);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setLayout(new FormLayout());

        Composite fileGroup = new Composite(composite, SWT.NONE);
        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.left = new FormAttachment(0, 5);
        formData.right = new FormAttachment(100, -10);
        fileGroup.setLayoutData(formData);

        allContentForm = new SashForm(composite, SWT.NONE);
        formData = new FormData();
        formData.top = new FormAttachment(fileGroup, 5);
        formData.left = new FormAttachment(0, 5);
        formData.right = new FormAttachment(100, -5);
        formData.bottom = new FormAttachment(100, -5);
        allContentForm.setLayoutData(formData);
        createViewers(allContentForm);

    }

    protected WebServiceComponent getWebServiceComponent() {
        return getWebServiceManager().getWebServiceComponent();
    }

    protected WebServiceManager getWebServiceManager() {
        return this.webServiceManager;
    }

    private void createViewers(SashForm allContentForm) {
        allContentForm.setLayout(new FillLayout());
        allContentForm.setOrientation(SWT.VERTICAL);
        allContentForm.setSashWidth(ExternalWebServiceUIProperties.SASHFORM_WIDTH);

        createHeader(allContentForm);

    }

    private void createHeader(SashForm allContentForm) {
        //
        tabFolder = new CTabFolder(allContentForm, SWT.NULL);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

        wsdlTabItem = new CTabItem(tabFolder, SWT.NONE);
        wsdlTabItem.setText(ExternalWebServiceUIProperties.WSDL_LABEL);
        tabFolder.setSelection(wsdlTabItem);

        inputMappingTabItem = new CTabItem(tabFolder, SWT.BORDER);
        inputMappingTabItem.setText(ExternalWebServiceUIProperties.INPUT_LABEL);
        // inputComposite = Form.startNewGridLayout(tabFolder, 1);
        // inputMappingTabItem.setControl(inputComposite);

        outputMappingTabItem = new CTabItem(tabFolder, SWT.BORDER);
        outputMappingTabItem.setText(ExternalWebServiceUIProperties.OUTPUT_LABEL);
        // outputComposite = Form.startNewGridLayout(tabFolder, 2);
        // outputMappingTabItem.setControl(outputComposite);
        // getList();
        wsdlTabItem.setControl(createWSDLStatus());

        inputMappingTabItem.setControl(createInputMappingStatus());

        outputMappingTabItem.setControl(createOutputMappingStatus());

        tabFolder.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                if (expressinPutTableView == null) {
                    return;
                }

                if (currentFunction == null) {

                    tabFolder.setSelection(wsdlTabItem);

                    MessageBox box = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_WARNING | SWT.OK);
                    box.setText("WARNING"); //$NON-NLS-1$ 
                    box.setMessage("Please Select a Operation."); //$NON-NLS-1$
                    box.open();
                    return;
                }

                ExtendedTableModel<InputMappingData> inputModel = expressinPutTableView.getExtendedTableModel();
                boolean removeLinksIn = true;
                goin: for (InputMappingData indata : inputModel.getBeansList()) {
                    if (indata.getInputColumnValue() != null && !"".equals(indata.getInputColumnValue())) { //$NON-NLS-1$
                        removeLinksIn = false;
                        break goin;
                    }
                }

                ExtendedTableModel<OutPutMappingData> outputModel = expressoutPutTableView.getExtendedTableModel();
                boolean removeLinksOut = true;
                goout: for (OutPutMappingData outdata : outputModel.getBeansList()) {
                    if (outdata.getParameterName() != null && !"".equals(outdata.getParameterName())) { //$NON-NLS-1$
                        removeLinksOut = false;
                        break goout;
                    }
                }

                if (removeLinksIn && removeLinksOut && tabTotabLinkForin != null) {
                    tabTotabLinkForin.clearLinks();
                }
                expressinPutTableView.getTableViewerCreator().getTableViewer().refresh();
                rowoutPutTableView.getTableViewerCreator().getTableViewer().refresh();

                // else {
                // initLinksForIn();
                // }
            }

        });

    }

    private Composite createWSDLStatus() {

        wsdlComposite = new Composite(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginWidth = 20;
        layout.marginHeight = 20;
        wsdlComposite.setLayout(layout);
        wsdlComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

        // WSDL URL
        Composite wsdlUrlcomposite = new Composite(wsdlComposite, SWT.NULL);
        GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
        layoutData.verticalIndent = 2;
        wsdlUrlcomposite.setLayoutData(layoutData);
        layout = new GridLayout(4, false);
        wsdlUrlcomposite.setLayout(layout);

        wsdlField = new LabelledFileField(wsdlUrlcomposite, ExternalWebServiceUIProperties.FILE_LABEL,
                ExternalWebServiceUIProperties.FILE_EXTENSIONS, 1, SWT.BORDER) {

            protected void setFileFieldValue(String result) {
                if (result != null) {
                    getTextControl().setText(PathUtils.getPortablePath(result));
                    getDataFromNet();
                }
            }

        };
        String wsdlUrl = (String) connector.getElementParameter("ENDPOINT").getValue(); //$NON-NLS-1$
        if (wsdlUrl != null) {
            wsdlField.setText(wsdlUrl);
        }

        refreshbut = new Button(wsdlUrlcomposite, SWT.PUSH | SWT.CENTER);
        refreshbut.setImage(ImageProvider.getImage(EImage.REFRESH_ICON));
        GridData butData = new GridData();
        butData.verticalSpan = 1;
        refreshbut.setLayoutData(butData);

        // WSDL Operation
        Composite wsdlOperationcomposite = new Composite(wsdlComposite, SWT.NONE);
        GridData operationlayoutData = new GridData(GridData.FILL_HORIZONTAL);
        operationlayoutData.verticalIndent = 2;
        wsdlOperationcomposite.setLayoutData(operationlayoutData);
        layout = new GridLayout(2, false);
        layout.verticalSpacing = 2;
        wsdlOperationcomposite.setLayout(layout);
        wsdlOperationcomposite.setLayoutData(new GridData(GridData.FILL_BOTH));

        operationLabel = new Label(wsdlOperationcomposite, SWT.NONE);
        operationLabel.setText(Messages.getString("WebServiceUI.Operation")); //$NON-NLS-1$
        GridData opertionLabelGridData = new GridData();
        opertionLabelGridData.verticalAlignment = SWT.TOP;
        operationLabel.setLayoutData(opertionLabelGridData);

        Composite tabComposite = new Composite(wsdlOperationcomposite, SWT.NONE);
        tabComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        tabComposite.setLayout(new FillLayout());

        ExtendedTableModel<Function> funModel = new ExtendedTableModel<Function>("FUNCTIONLIST", allfunList); //$NON-NLS-1$
        listTableView = new AbstractDataTableEditorView<Function>(tabComposite, SWT.NONE, funModel, false, true, false) {

            protected void setTableViewerCreatorOptions(TableViewerCreator<Function> newTableViewerCreator) {
                super.setTableViewerCreatorOptions(newTableViewerCreator);
                newTableViewerCreator.setHeaderVisible(false);
                newTableViewerCreator.setVerticalScroll(true);
                newTableViewerCreator.setReadOnly(true);
            }

            protected void createColumns(TableViewerCreator<Function> tableViewerCreator, Table table) {
                TableViewerCreatorColumn rowColumn = new TableViewerCreatorColumn(tableViewerCreator);
                rowColumn.setTitle(Messages.getString("WebServiceUI.COLUMN")); //$NON-NLS-1$
                rowColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<Function, String>() {

                    public String get(Function bean) {
                        return bean.getName();
                    }

                    public void set(Function bean, String value) {
                        bean.setName(value);

                    }

                });
                rowColumn.setWeight(60);
                rowColumn.setModifiable(true);
                rowColumn.setMinimumWidth(60);
                rowColumn.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

            }
        };

        addListenerForURLCom();
        return wsdlComposite;
    }

    private void addListenerForURLCom() {
        refreshbut.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                getDataFromNet();
            }
        });

        listTable = listTableView.getTable();
        listTable.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] item = listTable.getSelection();
                currentFunction = (Function) item[0].getData();

                IElementParameter METHODPara = connector.getElementParameter("METHOD"); //$NON-NLS-1$
                Object obj = METHODPara.getValue();
                if (currentFunction.getName().equals(obj.toString())) {
                    return;
                }
                List<ParameterInfo> listIn = currentFunction.getInputParameters();
                List<ParameterInfo> listOut = currentFunction.getOutputParameters();
                ExtendedTableModel<InputMappingData> columnModel = expressinPutTableView.getExtendedTableModel();
                columnModel.removeAll();
                if (listIn == null) {
                    InputMappingData inData = new InputMappingData();
                    inData.setParameterName("NEED NOT INPUT!!");
                    columnModel.add(inData);

                } else {
                    for (int i = 0; i < listIn.size(); i++) {
                        // if (list.size() > 0) {
                        ParameterInfo pa = listIn.get(i);
                        InputMappingData inData = new InputMappingData();
                        inData.setParameterName(pa.getName());
                        inData.setParameter(pa);

                        // exForInput.removeAll();
                        // boolean canaddforIn = true;
                        // List<InputMappingData> paExList = columnModel.getBeansList();
                        // for (int j = 0; j < paExList.size(); j++) {
                        // InputMappingData currentPa = paExList.get(j);
                        // if (currentPa == pa) {
                        // canaddforIn = false;
                        // }
                        // }
                        // if (canaddforIn) {
                        columnModel.add(inData);
                        // }
                    }
                }

                ExtendedTableModel<ParameterInfo> rowForOutput = rowoutPutTableView.getExtendedTableModel();
                rowForOutput.removeAll();
                if (listOut == null) {
                    ParameterInfo pa = new ParameterInfo();
                    pa.setName("OUTPUT IS NULL!!");
                    rowForOutput.add(pa);
                } else {
                    for (int i = 0; i < listOut.size(); i++) {
                        ParameterInfo pa = listOut.get(i);
                        rowForOutput.add(pa);
                    }
                }
                ExtendedTableModel<OutPutMappingData> exforoutput = expressoutPutTableView.getExtendedTableModel();
                exforoutput.removeAll();
                for (IMetadataColumn column : forOutColumnList) {
                    OutPutMappingData outData = new OutPutMappingData();
                    outData.setOutputColumnValue(column.getLabel());
                    outData.setMetadataColumn(column);
                    exforoutput.add(outData);
                }

            }

        });
    }

    private void getDataFromNet() {
        List<Function> funList = new ArrayList<Function>();
        URLValue = wsdlField.getText();
        if (URLValue == null) {
            URLValue = ""; //$NON-NLS-1$
        }
        WSDLDiscoveryHelper ws = new WSDLDiscoveryHelper();

        WebServiceComponent webServiceComponent = webServiceManager.getWebServiceComponent();
        boolean isUseProxy = webServiceComponent.getElementParameter("USE_PROXY").getValue().toString().equals("true");
        if (isUseProxy) {
            useProxy();
        }

        boolean isUseAuth = webServiceComponent.getElementParameter("NEED_AUTH").getValue().toString().equals("true");
        if (isUseAuth) {
            useAuth();
        }

        funList = ws.getFunctionsAvailable(URLValue);

        if (isUseProxy && usingProxy) {
            RemoveUseProxy();
        }

        ExtendedTableModel<Function> listModel = listTableView.getExtendedTableModel();
        listModel.removeAll();
        listModel.addAll(funList);
    }

    private Composite createInputMappingStatus() {

        SashForm inputComposite = new SashForm(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout(10, false);
        layout.marginWidth = 20;
        layout.marginHeight = 20;
        layout.horizontalSpacing = 150;

        inputComposite.setLayout(layout);
        inputComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        inputComposite.setBackgroundMode(SWT.INHERIT_FORCE);

        // input mapping cloumn.
        SashForm comforRow = new SashForm(inputComposite, SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.widthHint = 220;
        data.horizontalSpan = 3;
        comforRow.setLayoutData(data);
        comforRow.setLayout(new FillLayout());
        tabTotabLinkForin = new WebServiceTableLiner(inputComposite);
        ExtendedTableModel<IMetadataColumn> model = new ExtendedTableModel<IMetadataColumn>("INPUTCOLUMN", inPutcolumnList); //$NON-NLS-1$
        rowinPutTableView = new AbstractDataTableEditorView<IMetadataColumn>(comforRow, SWT.BORDER, model, false, true, false) {

            protected void setTableViewerCreatorOptions(TableViewerCreator<IMetadataColumn> newTableViewerCreator) {
                super.setTableViewerCreatorOptions(newTableViewerCreator);
                newTableViewerCreator.setHeaderVisible(true);
                newTableViewerCreator.setVerticalScroll(true);
                newTableViewerCreator.setReadOnly(true);
            }

            protected void createColumns(TableViewerCreator<IMetadataColumn> tableViewerCreator, Table table) {
                TableViewerCreatorColumn rowColumn = new TableViewerCreatorColumn(tableViewerCreator);
                rowColumn.setTitle(Messages.getString("WebServiceUI.COLUMN")); //$NON-NLS-1$
                rowColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<IMetadataColumn, String>() {

                    public String get(IMetadataColumn bean) {
                        return bean.getLabel();
                    }

                    public void set(IMetadataColumn bean, String value) {
                        bean.setLabel(value);

                    }

                });
                rowColumn.setWeight(60);
                rowColumn.setModifiable(true);
                rowColumn.setMinimumWidth(60);
                rowColumn.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

            }

        };

        // input mapping right SashForm.
        rowTableForin = rowinPutTableView.getTable();
        new DragAndDropForWebService(webServiceManager, rowinPutTableView, null, connector, true, false);

        SashForm comforRow1 = new SashForm(inputComposite, SWT.NONE);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        comforRow1.setLayoutData(data);

        Composite comforExp = new Composite(inputComposite, SWT.BORDER);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 5;
        // data.widthHint = 420;
        // data.heightHint = 350;
        comforExp.setLayoutData(data);
        comforExp.setLayout(new GridLayout());

        Composite butCom = new Composite(comforExp, SWT.NONE);
        butCom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        butCom.pack();
        GridLayout butComLayout = new GridLayout();
        butComLayout.numColumns = 6;
        butComLayout.verticalSpacing = 6;
        butCom.setLayout(butComLayout);
        GridData butComData1 = new GridData(GridData.FILL_BOTH);
        butComData1.horizontalSpan = 1;
        GridData butComData2 = new GridData(GridData.FILL_BOTH);
        butComData2.horizontalSpan = 2;

        // input mapping add button.
        addListButForIn = new Button(butCom, SWT.PUSH);
        addListButForIn.setEnabled(false);
        addListButForIn.setImage(ImageProvider.getImage(EImage.ADD_ICON));
        addListButForIn.setToolTipText(Messages.getString("WebServiceUI.Add_list_element")); //$NON-NLS-1$
        addListButForIn.setLayoutData(butComData1);
        addListButForIn.pack();

        // input mapping remove button.
        removeButForIn = new Button(butCom, SWT.PUSH);
        removeButForIn.setEnabled(false);
        removeButForIn.setImage(ImageProvider.getImage(EImage.DELETE_ICON));
        removeButForIn.setToolTipText(Messages.getString("WebServiceUI.Remove_element")); //$NON-NLS-1$
        removeButForIn.setLayoutData(butComData1);
        removeButForIn.pack();

        // input mapping Normalize button.
        normalizeButForIn = new Button(butCom, SWT.PUSH);
        normalizeButForIn.setEnabled(false);
        normalizeButForIn.setText(Messages.getString("WebServiceUI.Normalize")); //$NON-NLS-1$
        normalizeButForIn.setLayoutData(butComData2);
        normalizeButForIn.pack();

        // input mapping Denormalize button.
        denorButForIn = new Button(butCom, SWT.PUSH);
        denorButForIn.setEnabled(false);
        denorButForIn.setText(Messages.getString("WebServiceUI.Denormalize")); //$NON-NLS-1$
        denorButForIn.setLayoutData(butComData2);
        denorButForIn.pack();

        SashForm safhEx = new SashForm(comforExp, SWT.NONE);
        safhEx.pack();
        safhEx.setLayoutData(new GridData(GridData.FILL_BOTH));
        safhEx.setLayout(new FillLayout());

        ExtendedTableModel<InputMappingData> targetModel = new ExtendedTableModel<InputMappingData>("INPUTMAPPING", //$NON-NLS-1$
                inputMappingList);
        expressinPutTableView = new AbstractDataTableEditorView<InputMappingData>(safhEx, SWT.NONE | SWT.MULTI
                | SWT.FULL_SELECTION | SWT.SINGLE, targetModel, false, true, false) {

            protected void setTableViewerCreatorOptions(TableViewerCreator<InputMappingData> newTableViewerCreator) {
                super.setTableViewerCreatorOptions(newTableViewerCreator);
                newTableViewerCreator.setHeaderVisible(true);
                newTableViewerCreator.setVerticalScroll(true);
                newTableViewerCreator.setReadOnly(false);
            }

            protected void createColumns(TableViewerCreator<InputMappingData> tableViewerCreator, final Table table) {
                // input mapping ecpress cloumn.
                TableViewerCreatorColumn<InputMappingData, String> expressionColumn = new TableViewerCreatorColumn<InputMappingData, String>(
                        tableViewerCreator);
                expressionColumn.setTitle(Messages.getString("WebServiceUI.EXPRESSION")); //$NON-NLS-1$
                expressionColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<InputMappingData, String>() {

                    public String get(InputMappingData bean) {
                        if ("".equals(bean.getInputColumnValue()) || bean.getInputColumnValue() == null) {
                            return "";
                        }
                        // else {
                        // StringBuffer expression = new StringBuffer();
                        // for (IMetadataColumn column : inPutcolumnList) {
                        // if (bean.getInputColumnValue().contains(column.getLabel())) {
                        // expression.append(inComeName + ".");
                        // expression.append(column.getLabel());
                        // expression.append(" ");
                        // }
                        // }
                        // return expression.toString();// inComeName + "." + bean.getInputColumnValue();
                        // }

                        return bean.getInputColumnValue();
                    }

                    public void set(InputMappingData bean, String value) {
                        bean.setInputColumnValue(value);

                    }

                });
                expressionColumn.setWeight(40);
                expressionColumn.setModifiable(true);
                expressionColumn.setMinimumWidth(40);
                TextCellEditor cellEditorForIn = new TextCellEditor(tableViewerCreator.getTable());
                expressionColumn.setCellEditor(cellEditorForIn);
                cellEditorForIn.addListener(new DialogErrorForCellEditorListener(cellEditorForIn, expressionColumn) {

                    public void newValidValueTyped(int itemIndex, Object previousValue, Object newValue, CELL_EDITOR_STATE state) {
                        if (state == CELL_EDITOR_STATE.EDITING) {
                            tabTotabLinkForin.onXPathValueChanged(rowTableForin, table, previousValue.toString(), newValue
                                    .toString(), inComeName, itemIndex);
                        }

                    }

                    public String validateValue(String value, int beanPosition) {
                        return null;
                    }

                });

                // input mapping element.
                TableViewerCreatorColumn<InputMappingData, String> elementColumn = new TableViewerCreatorColumn<InputMappingData, String>(
                        tableViewerCreator);
                elementColumn.setTitle(Messages.getString("WebServiceUI.ELEMENT")); //$NON-NLS-1$
                elementColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<InputMappingData, String>() {

                    public String get(InputMappingData bean) {
                        ParameterInfo para = bean.getParameter();
                        if (para != null) {
                            List<ParameterInfo> paraList = new ParameterInfoUtil().getAllChildren(para);// para.
                            // getParameterInfos
                            // ();
                            if (paraList != null && paraList.size() > 0 && para.getParent() == null) {
                                boolean flag = isExpanded(table, para, paraList, "IN"); //$NON-NLS-1$
                                if (flag) {
                                    return "[-] " + para.getName(); //$NON-NLS-1$
                                }
                                return "[+] " + para.getName();// bean.getParameterName(); //$NON-NLS-1$
                            }
                            // else if (paraList != null && paraList.size() > 0 && para.getParent() != null) {
                            // String name = new ParameterInfoUtil().getParentName(para);
                            // return " [-] " + name;
                            // }
                            else if (para.getParent() != null) {
                                String name = new ParameterInfoUtil().getParentName(para);
                                return " |-- " + name; //$NON-NLS-1$
                            }
                        }
                        return bean.getParameterName();
                    }

                    public void set(InputMappingData bean, String value) {
                        if (value.contains("[+] ")) { //$NON-NLS-1$
                            value.replace("[+] ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        } else if (value.contains("[-] ")) { //$NON-NLS-1$
                            value.replace("[-] ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        // else if (value.contains(" [-] ")) {
                        // value.replace(" [-] ", "");
                        // }
                        else if (value.contains(" |-- ")) { //$NON-NLS-1$
                            value.replace(" |-- ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        bean.setParameterName(value);

                    }

                });
                elementColumn.setWeight(40);
                elementColumn.setModifiable(false);
                elementColumn.setMinimumWidth(40);
                TextCellEditor cellElementEditorForin = new TextCellEditor(tableViewerCreator.getTable());
                elementColumn.setCellEditor(cellElementEditorForin);

            }

        };
        expressTableForIn = expressinPutTableView.getTable();
        new DragAndDropForWebService(webServiceManager, expressinPutTableView, tabTotabLinkForin, connector, false, true);

        backgroundRefresher = new BackgroundRefresher(tabTotabLinkForin);
        Table[] tabs = new Table[1];
        tabs[0] = expressTableForIn;
        tabTotabLinkForin.init(rowTableForin, tabs, backgroundRefresher);
        addListenerForInputCom();
        initLinksForIn();
        inputComposite.setWeights(new int[] { 5, 2, 5 });
        return inputComposite;
    }

    private void addListenerForInputCom() {
        rowTableForin.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {

            }

        });

        expressTableForIn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] selectedItem = expressTableForIn.getSelection();
                currentElementIndexForIn = expressTableForIn.getSelectionIndex();
                currentInputMappingData = (InputMappingData) selectedItem[0].getData();
                // currentSelectedInParameter = inputData.getParameter();
                if (currentInputMappingData.getParameter() == null) {
                    return;
                }
                List<ParameterInfo> infoList = currentInputMappingData.getParameter().getParameterInfos();
                ParameterInfo infoPa = currentInputMappingData.getParameter().getParent();

                if (infoList != null && infoList.size() > 0) {
                    addListButForIn.setEnabled(true);
                } else {
                    addListButForIn.setEnabled(false);
                }

                if (infoPa != null) {
                    removeButForIn.setEnabled(true);
                } else {
                    removeButForIn.setEnabled(false);
                }

                List<IMetadataColumn> columnList = currentInputMappingData.getMetadataColumnList();
                if (columnList != null && columnList.size() != 1) {
                    normalizeButForIn.setEnabled(false);
                    denorButForIn.setEnabled(false);
                } else if (columnList != null && columnList.size() == 1) {
                    IMetadataColumn column = columnList.get(0);
                    if (column == null) {
                        return;
                    }
                    if (column.getTalendType().equals("id_List") && (infoList == null || infoList.size() <= 0)) { //$NON-NLS-1$
                        denorButForIn.setEnabled(true);
                    } else {
                        denorButForIn.setEnabled(false);
                    }

                    if (!column.getTalendType().equals("id_List") && infoList != null && infoList.size() > 0) { //$NON-NLS-1$
                        normalizeButForIn.setEnabled(true);
                    } else {
                        normalizeButForIn.setEnabled(false);
                    }
                }

            }

        });
        addListButForIn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                Shell shell = uiParent.getShell();
                // if (currentInputMappingData.getParameter().getArraySize() != 0) {
                // AddArrayIndexDialog arrayDialog = new AddArrayIndexDialog(shell,
                // currentInputMappingData.getParameter());
                // if (arrayDialog.open() == AddArrayIndexDialog.OK) {
                // int index = arrayDialog.returnIndex();
                // if (index != -1) {
                // ParameterInfo paraInfo = currentInputMappingData.getParameter();
                // ParameterInfo childPara = paraInfo.getParameterInfos().get(index);
                // InputMappingData data = new InputMappingData();
                // data.setParameter(childPara);
                // data.setParameterName(childPara.getName());
                //
                // expressinPutTableView.getExtendedTableModel().add(data, currentElementIndexForIn + 1);
                // }
                // }
                // return;
                // }
                TableItem[] items = expressTableForIn.getSelection();
                if (items.length <= 0) {
                    return;
                }
                AddListDialog dialog = new AddListDialog(shell, ((InputMappingData) items[0].getData()).getParameter());
                dialog.setTitle(Messages.getString("WebServiceUI.ParameterTree")); //$NON-NLS-1$
                Rectangle boundsMapper = new Rectangle(50, 50, 100, 50);//ExternalWebServiceUIProperties.getBoundsMapper
                // ();
                if (ExternalWebServiceUIProperties.isShellMaximized()) {
                    dialog.setMaximized(ExternalWebServiceUIProperties.isShellMaximized());
                } else {
                    boundsMapper = ExternalWebServiceUIProperties.getBoundsMapper();
                    if (boundsMapper.x < 0) {
                        boundsMapper.x = 0;
                    }
                    if (boundsMapper.y < 0) {
                        boundsMapper.y = 0;
                    }
                    dialog.setSize(boundsMapper);
                    if (dialog.open() == AddListDialog.OK) {
                        currentSelectedInChildren = dialog.getSelectedParaInfo();
                        if (currentSelectedInChildren == null || currentSelectedInChildren.getName() == null) {
                            return;
                        }
                        if (currentElementIndexForIn >= 0) {
                            // if (currentSelectedInChildren.getParent() == currentInputMappingData.getParameter()) {
                            InputMappingData data = new InputMappingData();
                            data.setParameter(currentSelectedInChildren);
                            data.setParameterName(currentSelectedInChildren.getName());
                            expressinPutTableView.getExtendedTableModel().add(data, currentElementIndexForIn + 1);

                            expressinPutTableView.getTableViewerCreator().getTableViewer().refresh();
                            // } else {
                            // List<ParameterInfo> paraList = getAllMostParameterInfo(currentSelectedInChildren, "IN");
                            // int index = 1;
                            // for (int i = paraList.size() - 1; i >= 0; i--) {
                            // ParameterInfo parentPara = paraList.get(i);
                            // if (parentPara == null) {
                            // continue;
                            // }
                            // InputMappingData data = new InputMappingData();
                            // data.setParameter(parentPara);
                            // data.setParameterName(parentPara.getName());
                            // expressinPutTableView.getExtendedTableModel().add(data, currentElementIndexForIn +
                            // index);
                            // index++;
                            // }
                            // expressinPutTableView.getTableViewerCreator().getTableViewer().refresh();
                            // }
                        }
                    }

                }
            }

        });

        removeButForIn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = expressTableForIn.getSelection();
                for (int i = 0; i < items.length; i++) {
                    InputMappingData info = (InputMappingData) items[i].getData();
                    expressinPutTableView.getExtendedTableModel().remove(info);
                }

                items = expressTableForIn.getSelection();
                if ((((InputMappingData) items[0].getData()).getParameter()).getParameterInfos().size() == 0) {
                    addListButForIn.setEnabled(false);
                }
                expressinPutTableView.getTableViewerCreator().getTableViewer().refresh();
            }

        });

        normalizeButForIn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                String columnName = currentInputMappingData.getInputColumnValue();
                if (!(columnName.contains("normalize(") && columnName.contains(",':')"))) { //$NON-NLS-1$ //$NON-NLS-2$
                    ExtendedTableModel model = expressinPutTableView.getExtendedTableModel();
                    currentInputMappingData.setInputColumnValue("normalize(" + columnName + ",':')"); //$NON-NLS-1$ //$NON-NLS-2$
                    model.remove(currentElementIndexForIn);
                    model.add(currentInputMappingData, currentElementIndexForIn);
                }

            }

        });

        denorButForIn.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                String columnName = currentInputMappingData.getInputColumnValue();
                if (!(columnName.contains("denormalize(") && columnName.contains(",':')"))) { //$NON-NLS-1$ //$NON-NLS-2$
                    ExtendedTableModel model = expressinPutTableView.getExtendedTableModel();
                    currentInputMappingData.setInputColumnValue("denormalize(" + columnName + ",':')"); //$NON-NLS-1$ //$NON-NLS-2$
                    model.remove(currentElementIndexForIn);
                    model.add(currentInputMappingData, currentElementIndexForIn);
                }
            }

        });
    }

    private Composite createOutputMappingStatus() {
        SashForm outputComposite = new SashForm(tabFolder, SWT.NONE);
        GridLayout layout = new GridLayout(10, false);
        layout.marginWidth = 20;
        layout.marginHeight = 20;
        layout.horizontalSpacing = 150;

        outputComposite.setLayout(layout);
        outputComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        outputComposite.setBackgroundMode(SWT.INHERIT_FORCE);

        Composite comforRow = new Composite(outputComposite, SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        comforRow.setLayoutData(data);
        comforRow.setLayout(new GridLayout());

        Composite butComRow = new Composite(comforRow, SWT.NONE);
        butComRow.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        butComRow.pack();
        GridLayout butComLayoutRow = new GridLayout();
        butComLayoutRow.numColumns = 4;
        butComLayoutRow.verticalSpacing = 6;
        butComRow.setLayout(butComLayoutRow);
        GridData butComData = new GridData();
        butComData.horizontalSpan = 1;

        addListButForOut = new Button(butComRow, SWT.PUSH);
        addListButForOut.setEnabled(false);
        addListButForOut.setImage(ImageProvider.getImage(EImage.ADD_ICON));
        addListButForOut.setToolTipText(Messages.getString("WebServiceUI.Add_List_element")); //$NON-NLS-1$
        addListButForOut.setLayoutData(butComData);
        addListButForOut.pack();

        removeButForout = new Button(butComRow, SWT.PUSH);
        removeButForout.setEnabled(false);
        removeButForout.setImage(ImageProvider.getImage(EImage.DELETE_ICON));
        removeButForout.setToolTipText(Messages.getString("WebServiceUI.Remove_element")); //$NON-NLS-1$
        removeButForout.setLayoutData(butComData);
        removeButForout.pack();

        SashForm safhExRow = new SashForm(comforRow, SWT.NONE);
        safhExRow.pack();
        safhExRow.setLayoutData(new GridData(GridData.FILL_BOTH));
        safhExRow.setLayout(new FillLayout());

        ExtendedTableModel<ParameterInfo> model = new ExtendedTableModel<ParameterInfo>("OUTPUTELEMENT", outParaList); //$NON-NLS-1$
        rowoutPutTableView = new AbstractDataTableEditorView<ParameterInfo>(safhExRow, SWT.NONE, model, false, true, false) {

            protected void setTableViewerCreatorOptions(TableViewerCreator<ParameterInfo> newTableViewerCreator) {
                super.setTableViewerCreatorOptions(newTableViewerCreator);
                newTableViewerCreator.setHeaderVisible(true);
                newTableViewerCreator.setVerticalScroll(true);
                newTableViewerCreator.setReadOnly(true);
            }

            protected void createColumns(TableViewerCreator<ParameterInfo> tableViewerCreator, final Table table) {
                TableViewerCreatorColumn rowColumn = new TableViewerCreatorColumn(tableViewerCreator);
                rowColumn.setTitle(Messages.getString("WebServiceUI.ELEMENT")); //$NON-NLS-1$
                rowColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<ParameterInfo, String>() {

                    public String get(ParameterInfo bean) {

                        List<ParameterInfo> paraList = new ParameterInfoUtil().getAllChildren(bean);// bean.
                        // getParameterInfos
                        // ();
                        if (paraList != null && paraList.size() > 0 && bean.getParent() == null) {
                            boolean flag = isExpanded(table, bean, paraList, "OUT"); //$NON-NLS-1$
                            if (flag) {
                                return "[-] " + bean.getName(); //$NON-NLS-1$
                            }
                            return "[+] " + bean.getName();// bean.getParameterName(); //$NON-NLS-1$
                        }
                        // else if (paraList != null && paraList.size() > 0 && bean.getParent() != null) {
                        // String name = new ParameterInfoUtil().getParentName(bean);
                        // return " [-] " + name;
                        // }
                        else if (bean.getParent() != null) {
                            String name = new ParameterInfoUtil().getParentName(bean);
                            return " |-- " + name; //$NON-NLS-1$
                        }

                        return bean.getName();
                    }

                    public void set(ParameterInfo bean, String value) {
                        if (value.contains("[+] ")) { //$NON-NLS-1$
                            value.replace("[+] ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        } else if (value.contains("[-] ")) { //$NON-NLS-1$
                            value.replace("[-] ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        // else if (value.contains(" [-] ")) {
                        // value.replace(" [-] ", "");
                        // }
                        else if (value.contains(" |-- ")) { //$NON-NLS-1$
                            value.replace(" |-- ", ""); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        bean.setName(value);

                    }

                });
                rowColumn.setWeight(60);
                rowColumn.setModifiable(true);
                rowColumn.setMinimumWidth(60);
                rowColumn.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

            }

        };
        rowTableForout = rowoutPutTableView.getTable();
        new DragAndDropForWebService(webServiceManager, rowoutPutTableView, null, connector, true, false);

        SashForm comforRow1 = new SashForm(outputComposite, SWT.NONE);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        comforRow1.setLayoutData(data);

        Composite comforExp = new Composite(outputComposite, SWT.BORDER);
        data = new GridData(GridData.FILL_BOTH);
        // data.horizontalSpan = 5;
        // data.widthHint = 420;
        // data.heightHint = 350;
        comforExp.setLayoutData(data);
        comforExp.setLayout(new GridLayout());

        Composite butCom = new Composite(comforExp, SWT.NONE);
        butCom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        butCom.pack();
        GridLayout butComLayout = new GridLayout();
        butComLayout.numColumns = 2;
        butComLayout.verticalSpacing = 6;
        butCom.setLayout(butComLayout);
        butComData = new GridData(GridData.FILL_BOTH);
        butComData.horizontalSpan = 1;

        normalizeButForOut = new Button(butCom, SWT.PUSH);
        normalizeButForOut.setEnabled(false);
        normalizeButForOut.setText(Messages.getString("WebServiceUI.Normalize")); //$NON-NLS-1$
        normalizeButForOut.setLayoutData(butComData);
        normalizeButForOut.pack();
        denorButForOut = new Button(butCom, SWT.PUSH);
        denorButForOut.setEnabled(false);
        denorButForOut.setText(Messages.getString("WebServiceUI.Denormalize")); //$NON-NLS-1$
        denorButForOut.setLayoutData(butComData);
        denorButForOut.pack();

        SashForm safhEx = new SashForm(comforExp, SWT.NONE);
        safhEx.pack();
        safhEx.setLayoutData(new GridData(GridData.FILL_BOTH));
        safhEx.setLayout(new FillLayout());

        ExtendedTableModel<OutPutMappingData> targetModel = new ExtendedTableModel<OutPutMappingData>("OUTPUTMAPPING", //$NON-NLS-1$
                outPutcolumnList);
        expressoutPutTableView = new AbstractDataTableEditorView<OutPutMappingData>(safhEx, SWT.NONE | SWT.MULTI
                | SWT.FULL_SELECTION | SWT.SINGLE, targetModel, false, true, false) {

            protected void setTableViewerCreatorOptions(TableViewerCreator<OutPutMappingData> newTableViewerCreator) {
                super.setTableViewerCreatorOptions(newTableViewerCreator);
                newTableViewerCreator.setHeaderVisible(true);
                newTableViewerCreator.setVerticalScroll(true);
                newTableViewerCreator.setReadOnly(false);
            }

            protected void createColumns(TableViewerCreator<OutPutMappingData> tableViewerCreator, final Table table) {
                TableViewerCreatorColumn<OutPutMappingData, String> expressionColumn = new TableViewerCreatorColumn<OutPutMappingData, String>(
                        tableViewerCreator);
                expressionColumn.setTitle(Messages.getString("WebServiceUI.EXPRESSION")); //$NON-NLS-1$
                expressionColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<OutPutMappingData, String>() {

                    public String get(OutPutMappingData bean) {
                        StringBuffer paraName = new StringBuffer();
                        String paraNameof = bean.getParameterName();
                        List<ParameterInfo> paraList = bean.getParameterList();
                        if ("".equals(paraNameof) || paraNameof == null) {
                            return "";
                        }
                        for (ParameterInfo para : paraList) {
                            if (para.getParent() != null) {
                                String name = new ParameterInfoUtil().getParentName(para);
                                paraName.append(name);
                                paraName.append(" "); //$NON-NLS-1$
                            }
                        }
                        if (!"".equals(paraName.toString())) { //$NON-NLS-1$
                            if (paraNameof.contains("denormalize(") && paraNameof.contains(",':')")) { //$NON-NLS-1$ //$NON-NLS-2$
                                return "denormalize(" + paraName.toString() + ",':')"; //$NON-NLS-1$ //$NON-NLS-2$
                            } else if (paraNameof.contains("normalize(") && paraNameof.contains(",':')")) { //$NON-NLS-1$ //$NON-NLS-2$
                                return "normalize(" + paraName.toString() + ",':')"; //$NON-NLS-1$ //$NON-NLS-2$
                            }
                            return paraName.toString();
                        }
                        return bean.getParameterName();
                    }

                    public void set(OutPutMappingData bean, String value) {
                        bean.setParameterName(value);

                    }

                });
                expressionColumn.setWeight(40);
                expressionColumn.setModifiable(true);
                expressionColumn.setMinimumWidth(40);
                TextCellEditor cellEditorForOut = new TextCellEditor(tableViewerCreator.getTable());
                expressionColumn.setCellEditor(cellEditorForOut);
                cellEditorForOut.addListener(new DialogErrorForCellEditorListener(cellEditorForOut, expressionColumn) {

                    public void newValidValueTyped(int itemIndex, Object previousValue, Object newValue, CELL_EDITOR_STATE state) {
                        if (state == CELL_EDITOR_STATE.EDITING) {
                            tabTotabLinkForout.onXPathValueChanged(rowTableForout, table, previousValue.toString(), newValue
                                    .toString(), null, itemIndex);
                        }

                    }

                    public String validateValue(String value, int beanPosition) {
                        // TODO Auto-generated method stub
                        return null;
                    }

                });

                TableViewerCreatorColumn<OutPutMappingData, String> elementColumn = new TableViewerCreatorColumn<OutPutMappingData, String>(
                        tableViewerCreator);
                elementColumn.setTitle(Messages.getString("WebServiceUI.COLUMN")); //$NON-NLS-1$
                elementColumn.setBeanPropertyAccessors(new IBeanPropertyAccessors<OutPutMappingData, String>() {

                    public String get(OutPutMappingData bean) {
                        return bean.getOutputColumnValue();
                    }

                    public void set(OutPutMappingData bean, String value) {
                        bean.setOutputColumnValue(value);

                    }

                });
                elementColumn.setWeight(40);
                elementColumn.setModifiable(false);
                elementColumn.setMinimumWidth(40);
                elementColumn.setCellEditor(new TextCellEditor(tableViewerCreator.getTable()));

            }

        };

        expressTableForout = expressoutPutTableView.getTable();
        tabTotabLinkForout = new WebServiceTableLiner(outputComposite);
        new DragAndDropForWebService(webServiceManager, expressoutPutTableView, tabTotabLinkForout, connector, false, true);

        backgroundRefresher = new BackgroundRefresher(tabTotabLinkForout);
        Table[] tabs = new Table[1];
        tabs[0] = expressTableForout;
        tabTotabLinkForout.init(rowTableForout, tabs, backgroundRefresher);
        initLinksForOut();
        addListenerForOutputCom();
        outputComposite.setWeights(new int[] { 5, 2, 5 });
        return outputComposite;
    }

    private void addListenerForOutputCom() {
        rowTableForout.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = rowTableForout.getSelection();
                currentElementIndexForOut = rowTableForout.getSelectionIndex();
                currentOutputParameter = (ParameterInfo) items[0].getData();
                int arraySize = currentOutputParameter.getArraySize();
                List<ParameterInfo> infoList = currentOutputParameter.getParameterInfos();
                ParameterInfo infoPa = currentOutputParameter.getParent();

                if (infoList != null && infoList.size() > 0) {
                    addListButForOut.setEnabled(true);
                } else {
                    addListButForOut.setEnabled(false);
                }

                if (infoPa != null) {
                    removeButForout.setEnabled(true);
                } else {
                    removeButForout.setEnabled(false);
                }

            }

        });

        expressTableForout.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = expressTableForout.getSelection();
                currentIndexForOutExpress = expressTableForout.getSelectionIndex();
                currentSelectedOutExpress = (OutPutMappingData) items[0].getData();

                if (currentSelectedOutExpress.getParameterList().size() > 1) {
                    denorButForOut.setEnabled(false);
                    normalizeButForOut.setEnabled(false);
                    return;
                }
                List<ParameterInfo> list = currentSelectedOutExpress.getParameterList();
                if (list.size() <= 0) {
                    return;
                }
                List<ParameterInfo> infoList = currentSelectedOutExpress.getParameterList().get(0).getParameterInfos();
                IMetadataColumn column = currentSelectedOutExpress.getMetadataColumn();
                if (column == null) {
                    return;
                }
                if (column.getTalendType().equals("id_List") && (infoList == null || infoList.size() <= 0)) { //$NON-NLS-1$
                    denorButForOut.setEnabled(true);
                } else {
                    denorButForOut.setEnabled(false);
                }

                if (!column.getTalendType().equals("id_List") && infoList != null && infoList.size() > 0) { //$NON-NLS-1$
                    normalizeButForOut.setEnabled(true);
                } else {
                    normalizeButForOut.setEnabled(false);
                }

            }

        });

        addListButForOut.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                Shell shell = uiParent.getShell();

                // if (currentOutputParameter.getArraySize() != 0) {
                // AddArrayIndexDialog arrayDialog = new AddArrayIndexDialog(shell, currentOutputParameter);
                // if (arrayDialog.open() == AddArrayIndexDialog.OK) {
                // int index = arrayDialog.returnIndex();
                // if (index != -1) {
                // ParameterInfo info = currentOutputParameter.getParameterInfos().get(index);
                // if (info != null) {
                // rowoutPutTableView.getExtendedTableModel().add(info, currentElementIndexForOut + 1);
                // rowoutPutTableView.getTableViewerCreator().getTableViewer().refresh();
                // }
                // }
                // }
                // return;
                // }
                TableItem[] items = rowTableForout.getSelection();
                if (items.length <= 0) {
                    return;
                }
                AddListDialog dialog = new AddListDialog(shell, (ParameterInfo) items[0].getData());
                dialog.setTitle(Messages.getString("WebServiceUI.ParameterTree")); //$NON-NLS-1$
                Rectangle boundsMapper = new Rectangle(50, 50, 100, 50);//ExternalWebServiceUIProperties.getBoundsMapper
                // ();
                if (ExternalWebServiceUIProperties.isShellMaximized()) {
                    dialog.setMaximized(ExternalWebServiceUIProperties.isShellMaximized());
                } else {
                    boundsMapper = ExternalWebServiceUIProperties.getBoundsMapper();
                    if (boundsMapper.x < 0) {
                        boundsMapper.x = 0;
                    }
                    if (boundsMapper.y < 0) {
                        boundsMapper.y = 0;
                    }
                    dialog.setSize(boundsMapper);
                    if (dialog.open() == AddListDialog.OK) {
                        currentSelectedOutChildren = dialog.getSelectedParaInfo();
                        if (currentSelectedOutChildren == null || currentSelectedOutChildren.getName() == null) {
                            return;
                        }
                        if (currentElementIndexForOut >= 0) {
                            // if (currentSelectedOutChildren.getParent() == currentOutputParameter) {

                            rowoutPutTableView.getExtendedTableModel().add(currentSelectedOutChildren,
                                    currentElementIndexForOut + 1);

                            rowoutPutTableView.getTableViewerCreator().getTableViewer().refresh();
                            // } else {
                            // List<ParameterInfo> paraList = getAllMostParameterInfo(currentSelectedOutChildren,
                            // "OUT");
                            // int index = 1;
                            // for (int i = paraList.size() - 1; i >= 0; i--) {
                            // ParameterInfo parentPara = paraList.get(i);
                            // if (parentPara == null) {
                            // continue;
                            // }
                            //
                            // rowoutPutTableView.getExtendedTableModel().add(parentPara, currentElementIndexForOut +
                            // index);
                            // index++;
                            // }
                            // rowoutPutTableView.getTableViewerCreator().getTableViewer().refresh();
                            // }
                        }
                    }

                }
            }

        });

        removeButForout.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = rowTableForout.getSelection();
                for (int i = 0; i < items.length; i++) {
                    ParameterInfo info = (ParameterInfo) items[i].getData();
                    rowoutPutTableView.getExtendedTableModel().remove(info);
                    tabTotabLinkForout.updateLinksStyleAndControlsSelection(rowTableForout);
                }
                items = rowTableForout.getSelection();
                if (((ParameterInfo) items[0].getData()).getParameterInfos().size() == 0) {
                    addListButForOut.setEnabled(false);
                }
                rowoutPutTableView.getTableViewerCreator().getTableViewer().refresh();
            }
        });

        normalizeButForOut.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = expressTableForout.getSelection();
                OutPutMappingData currentData = (OutPutMappingData) items[0].getData();
                int currentIndex = expressTableForout.getSelectionIndex();
                String parameterName = currentData.getParameterName();
                if (!(parameterName.contains("normalize(") && parameterName.contains(",':')"))) { //$NON-NLS-1$ //$NON-NLS-2$
                    ExtendedTableModel model = expressoutPutTableView.getExtendedTableModel();
                    currentData.setParameterName("normalize(" + parameterName + ",':')"); //$NON-NLS-1$ //$NON-NLS-2$
                    model.remove(currentIndex);
                    model.add(currentData, currentIndex);
                }
            }
        });

        denorButForOut.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = expressTableForout.getSelection();
                OutPutMappingData currentData = (OutPutMappingData) items[0].getData();
                int currentIndex = expressTableForout.getSelectionIndex();
                String parameterName = ((OutPutMappingData) items[0].getData()).getParameterName();
                if (!(parameterName.contains("denormalize(") && parameterName.contains(",':')"))) { //$NON-NLS-1$ //$NON-NLS-2$
                    ExtendedTableModel model = expressoutPutTableView.getExtendedTableModel();
                    currentData.setParameterName("denormalize(" + parameterName + ",':')"); //$NON-NLS-1$ //$NON-NLS-2$
                    model.remove(currentIndex);
                    model.add(currentData, currentIndex);
                }
            }
        });

    }

    private UIManager getUIManager() {
        return getWebServiceManager().getUIManager();
    }

    public CTabFolder getTabFolder() {
        return this.tabFolder;
    }

    public CTabItem getOutputTabItem() {
        return this.outputMappingTabItem;
    }

    public Composite getOutputComposite() {
        return this.outputComposite;
    }

    public CTabItem getInputTabItem() {
        return this.inputMappingTabItem;
    }

    public Composite getInputComposite() {
        return this.inputComposite;
    }

    public Composite getWsdlComposite() {
        return this.wsdlComposite;
    }

    public String getURL() {
        return URLValue;
    }

    public Function getCurrentFunction() {
        return currentFunction;
    }

    public List<InputMappingData> getInputParams() {
        return expressinPutTableView.getExtendedTableModel().getBeansList();
    }

    public List<OutPutMappingData> getOutputParams() {
        return expressoutPutTableView.getExtendedTableModel().getBeansList();
    }

    public List<ParameterInfo> getOutputElement() {
        return rowoutPutTableView.getExtendedTableModel().getBeansList();
    }

    public Set<String> getInSourceList() {
        return tabTotabLinkForin.getInputSource();
    }

    public Set<String> getOutSourceList() {
        return tabTotabLinkForout.getOutputSource();
    }

    private void initLinksForIn() {
        IElementParameter INPUT_PARAMSPara = connector.getElementParameter("INPUT_PARAMS"); //$NON-NLS-1$
        List<Map<String, String>> inputparaValue = (List<Map<String, String>>) INPUT_PARAMSPara.getValue();
        TableItem[] items = rowTableForin.getItems();
        TableItem[] tarItems = expressTableForIn.getItems();

        WebServiceExpressionParser webParser = new WebServiceExpressionParser("\\s*(\\w+)\\s*\\.\\s*(\\w+)\\s*"); //$NON-NLS-1$
        for (Map<String, String> map : inputparaValue) {
            if (map.get("SOURCE") != null && map.get("SOURCE") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String source = (String) map.get("SOURCE"); //$NON-NLS-1$
                if ("".equals(source)) { //$NON-NLS-1$
                    continue;
                }

                TableItem sourceItem = null;
                Object sourceData = null;
                goin: for (int i = 0; i < items.length; i++) {
                    if (items[i].getData() != null && ((IMetadataColumn) items[i].getData()).getLabel() != null
                            && ((IMetadataColumn) items[i].getData()).getLabel().equals(source)) {
                        sourceItem = items[i];
                        sourceData = items[i].getData();
                        if (sourceItem == null) {
                            continue;
                        } else {
                            break goin;
                        }
                    }
                }

                if (sourceItem != null) {
                    TableItem targetItem = null;
                    Object targetData = null;
                    for (int i = 0; i < tarItems.length; i++) {
                        if (tarItems[i].getData() == null) {
                            continue;
                        }
                        String columnName = ((InputMappingData) tarItems[i].getData()).getInputColumnValue();
                        if (columnName == null || "".equals(columnName)) { //$NON-NLS-1$
                            continue;
                        }

                        Map<String, String> itemNamemap = webParser.parseInTableEntryLocations(columnName);
                        Set<Entry<String, String>> set = itemNamemap.entrySet();
                        Iterator<Entry<String, String>> ite = set.iterator();
                        while (ite.hasNext()) {
                            Entry<String, String> entry = ite.next();
                            String columnValue = entry.getKey();
                            String rowValue = entry.getValue();

                            if (columnValue.equals(source)) {
                                targetItem = tarItems[i];
                                targetData = tarItems[i].getData();
                                if (targetItem == null) {
                                    continue;
                                }
                                tabTotabLinkForin.addLinks(sourceItem, sourceData, targetItem.getParent(), targetData,
                                        "INPUTMAPPING"); //$NON-NLS-1$
                            }

                            if (!rowValue.equals(inComeName)) {

                            }
                        }

                    }
                }
            }

        }
    }

    private void initLinksForOut() {
        IElementParameter OUTPUT_PARAMSPara = connector.getElementParameter("OUTPUT_PARAMS"); //$NON-NLS-1$
        List<Map<String, String>> outputMap = (List<Map<String, String>>) OUTPUT_PARAMSPara.getValue();
        TableItem[] items = rowTableForout.getItems();
        TableItem[] tarItems = expressTableForout.getItems();
        WebServiceExpressionParser webParser = new WebServiceExpressionParser("\\s*(\\w+)\\s*"); //$NON-NLS-1$
        for (Map<String, String> map : outputMap) {
            if (map.get("SOURCE") != null && map.get("SOURCE") instanceof String) { //$NON-NLS-1$ //$NON-NLS-2$
                String source = (String) map.get("SOURCE"); //$NON-NLS-1$

                TableItem sourceItem = null;
                Object sourceData = null;
                goin: for (int i = 0; i < items.length; i++) {
                    if (items[i].getData() != null && ((ParameterInfo) items[i].getData()).getName() != null
                            && ((ParameterInfo) items[i].getData()).getName().equals(source)) {
                        sourceItem = items[i];
                        sourceData = items[i].getData();
                        if (sourceItem == null) {
                            continue;
                        } else {
                            break goin;
                        }
                    }
                }
                if (sourceItem != null) {
                    TableItem targetItem = null;
                    Object targetData = null;
                    for (int i = 0; i < tarItems.length; i++) {
                        if (tarItems[i].getData() == null) {
                            continue;
                        }
                        String columnName = ((OutPutMappingData) tarItems[i].getData()).getParameterName();
                        if (columnName == null || "".equals(columnName)) { //$NON-NLS-1$
                            continue;
                        }
                        Set<String> set = webParser.parseOutTableEntryLocations(columnName);
                        Iterator<String> ite = set.iterator();
                        while (ite.hasNext()) {
                            String columnValue = ite.next();
                            if (columnValue.equals(source)) {
                                targetItem = tarItems[i];
                                targetData = tarItems[i].getData();

                                if (targetItem == null) {
                                    continue;
                                }
                                tabTotabLinkForout.addLinks(sourceItem, sourceData, targetItem.getParent(), targetData,
                                        "OUTPUTMAPPING"); //$NON-NLS-1$
                            }
                        }

                    }
                }
            }
        }
    }

    public void saveProperties() {
        getWebServiceManager().savePropertiesToComponent();
    }

    public void prepareClosing(int dialogResponse) {

    }

    // private List<ParameterInfo> getAllMostParameterInfo(ParameterInfo para, String mark) {
    // ParameterInfo parentPara = para.getParent();
    // ParameterInfo paraToCom = null;
    // if (mark.equals("IN")) {
    // paraToCom = currentInputMappingData.getParameter();
    // } else if (mark.equals("OUT")) {
    // paraToCom = currentOutputParameter;
    // }
    // List<ParameterInfo> list = new ArrayList<ParameterInfo>();
    // if (parentPara != paraToCom) {
    // list.add(para);
    // List<ParameterInfo> pali = getAllMostParameterInfo(parentPara, mark);
    // list.addAll(pali);
    // } else {
    // list.add(para);
    // }
    // return list;
    // }
    //
    // private List<ParameterInfo> getAllParameterInfo(ParameterInfo para) {
    // ParameterInfo parentPara = para.getParent();
    // List<ParameterInfo> list = new ArrayList<ParameterInfo>();
    // if (parentPara != null) {
    // list.add(para);
    // List<ParameterInfo> pali = getAllParameterInfo(parentPara);
    // list.addAll(pali);
    // } else {
    // list.add(para);
    // }
    // return list;
    // }
    //
    // private String getParentName(ParameterInfo para) {
    // List<ParameterInfo> paraList = getAllParameterInfo(para);
    // StringBuffer buffer = new StringBuffer();
    // for (int i = paraList.size() - 1; i >= 0; i--) {
    // ParameterInfo parentPara = paraList.get(i);
    // if (parentPara == null) {
    // continue;
    // }
    // buffer.append(parentPara.getName());
    // if (i != 0) {
    // buffer.append(".");
    // }
    //
    // }
    // return buffer.toString();
    // }

    private boolean isExpanded(Table table, ParameterInfo para, List<ParameterInfo> paraList, String mark) {
        boolean flag = false;
        if (mark.equals("IN")) { //$NON-NLS-1$
            // if (currentInputMappingData == null) {
            // return false;
            // }

            TableItem[] items = table.getItems();
            int index = -1;
            for (int i = 0; i < items.length; i++) {
                if (((InputMappingData) items[i].getData()).getParameter() == para) {
                    index = i;
                }
            }
            if (index + 1 < items.length) {
                ParameterInfo paraToComp = ((InputMappingData) items[index + 1].getData()).getParameter();
                for (ParameterInfo info : paraList) {
                    if (paraToComp == info) {
                        flag = true;
                        return flag;
                    }
                }
            }

        } else if (mark.equals("OUT")) { //$NON-NLS-1$
            // if (currentOutputParameter == null) {
            // return false;
            // }

            TableItem[] items = table.getItems();
            int index = -1;
            for (int i = 0; i < items.length; i++) {
                if (items[i].getData() == para) {
                    index = i;
                }
            }
            if (index + 1 < items.length) {
                ParameterInfo paraToComp = (ParameterInfo) items[index + 1].getData();
                for (ParameterInfo info : paraList) {
                    if (paraToComp == info) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }

        // List<ParameterInfo> paraList = currentInputMappingData.getParameter().getParameterInfos();

        return flag;
    }

}
