package org.talend.designer.webservice.ui.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.webservice.WebServiceComponent;
import org.talend.designer.webservice.WebServiceComponentMain;
import org.talend.designer.webservice.data.ExternalWebServiceUIProperties;
import org.talend.designer.webservice.data.InputMappingData;
import org.talend.designer.webservice.data.OutPutMappingData;
import org.talend.designer.webservice.managers.UIManager;
import org.talend.designer.webservice.ui.WebServiceUI;
import org.talend.designer.webservice.ws.wsdlinfo.Function;
import org.talend.designer.webservice.ws.wsdlinfo.ParameterInfo;

public class WebServiceDialog extends Dialog implements WebServiceEventListener {

    private String title;

    private Rectangle size;

    private WebServiceUI webServiceUI;

    private WebServiceComponentMain webServiceComponentMain;

    private boolean maximized;

    private CTabFolder tabFolder;

    public WebServiceDialog(Shell parentShell, WebServiceComponentMain webServiceComponentMain) {
        super(parentShell);
        this.webServiceComponentMain = webServiceComponentMain;
        setShellStyle(ExternalWebServiceUIProperties.DIALOG_STYLE);
    }

    public WebServiceUI getWebServiceUI() {
        return this.webServiceUI;
    }

    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (title != null) {
            newShell.setText(title);
        }
        if (maximized) {
            newShell.setMaximized(true);
        } else {
            newShell.setBounds(size);
        }
    }

    public void setTitle(String title) {
        this.title = title;

    }

    /**
     * Sets the size.
     * 
     * @param size the size to set
     */
    public void setSize(Rectangle size) {
        this.size = size;
    }

    /**
     * Sets the maximizedSize.
     * 
     * @param maximizedSize the maxmimizedSize to set
     */
    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

    private UIManager getUIManager() {
        return webServiceComponentMain.getWebServiceManager().getUIManager();
    }

    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.OK_ID == buttonId) {
            okPressed();
        } else if (IDialogConstants.CANCEL_ID == buttonId) {
            cancelPressed();
        } else if (IDialogConstants.NEXT_ID == buttonId) {
            nextPressed();
        } else if (IDialogConstants.BACK_ID == buttonId) {
            backPressed();
        }
    }

    protected void cancelPressed() {
        super.cancelPressed();
        getUIManager().setDialogResponse(SWT.CANCEL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */

    protected void createButtonsForButtonBar(Composite parent) {
        // create OK and Cancel buttons by default
        createButton(parent, IDialogConstants.BACK_ID, IDialogConstants.BACK_LABEL, false);
        createButton(parent, IDialogConstants.NEXT_ID, IDialogConstants.NEXT_LABEL, false);
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    protected Control createDialogArea(Composite parent) {
        Composite panel = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        panel.setLayout(layout);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
        applyDialogFont(panel);

        webServiceUI = new WebServiceUI(panel, this.webServiceComponentMain);
        webServiceUI.addListener(this);
        webServiceUI.init();

        return panel;
    }

    protected void makeNextAndBackButton() {
        tabFolder = webServiceUI.getTabFolder();
        if (tabFolder.getSelectionIndex() == 0) {

        }

    }

    protected void backPressed() {
        tabFolder = webServiceUI.getTabFolder();
        int curreSelect = tabFolder.getSelectionIndex();
        if (curreSelect > 0) {
            tabFolder.setSelection(curreSelect - 1);

        }
    }

    protected void nextPressed() {
        tabFolder = webServiceUI.getTabFolder();
        Function function = webServiceUI.getCurrentFunction();
        int curreSelect = tabFolder.getSelectionIndex();
        if (function == null) {
            warningDialog("Please Select a Operation!");
        } else if (curreSelect < 2 && function != null) {
            tabFolder.setSelection(curreSelect + 1);
            if ((curreSelect + 1) == 3) {

            }

        }
    }

    protected void okPressed() {
        getWebServiceUI().saveProperties();
        // getWebServiceUI().prepareClosing(SWT.OK);
        saveValue();

    }

    private void saveValue() {
        List<InputMappingData> inputList = webServiceUI.getInputParams();
        List<OutPutMappingData> outputList = webServiceUI.getOutputParams();
        String currentURL = webServiceUI.getURL();
        Function function = webServiceUI.getCurrentFunction();
        List<ParameterInfo> outEleList = webServiceUI.getOutputElement();
        Set<String> insourceList = webServiceUI.getInSourceList();
        Set<String> outsourceList = webServiceUI.getOutSourceList();

        WebServiceComponent wenCom = webServiceComponentMain.getWebServiceComponent();

        if (!"".equals(currentURL) && currentURL != null) {
            IElementParameter ENDPOINTPara = wenCom.getElementParameter("ENDPOINT");
            ENDPOINTPara.setValue(currentURL);
        }

        if (function != null) {
            IElementParameter METHODPara = wenCom.getElementParameter("METHOD");
            METHODPara.setValue(function.getName());

            IElementParameter SOAPACTIONPara = wenCom.getElementParameter("SOAPACTION");
            SOAPACTIONPara.setValue(function.getSoapAction());
            System.out.print("-----" + function.getSoapAction());

            IElementParameter METHODNSPara = wenCom.getElementParameter("METHOD_NS");
            METHODNSPara.setValue(function.getNameSpaceURI());

            IElementParameter SOAPEncoding = wenCom.getElementParameter("SOAP_ENCODING");
            SOAPEncoding.setValue(function.getEncodingStyle());

            IElementParameter ADDRESSLocation = wenCom.getElementParameter("ADDRESS_LOCATION");
            ADDRESSLocation.setValue(function.getAddressLocation());

        }

        IElementParameter INPUT_PARAMSPara = wenCom.getElementParameter("INPUT_PARAMS");
        List<Map<String, String>> inputparaValue = (List<Map<String, String>>) INPUT_PARAMSPara.getValue();
        if (inputparaValue == null) {
            inputparaValue = new ArrayList<Map<String, String>>();
        } else {
            inputparaValue.clear();
        }

        for (InputMappingData inputData : inputList) {

            Map<String, String> inputMap = new HashMap<String, String>(2);
            if (inputData.getInputColumnValue() != null) {
                inputMap.put("EXPRESSION", inputData.getInputColumnValue());
            }
            // else if (inputData.getInputColumnValue() == null) {
            // warningDialog("Please Select a Input Item.");
            // return;
            // }

            if (inputData.getParameter() != null) {
                inputMap.put("ELEMENT", inputData.getParameter().getName());
                inputMap.put("NAMESPACE", inputData.getParameter().getNameSpace());
                inputMap.put("TYPE", inputData.getParameter().getKind());
            }
            // else if (inputData.getParameter() == null) {
            // warningDialog("Please Select a Input Item.");
            // return;
            // }

            inputparaValue.add(inputMap);
        }

        for (String insource : insourceList) {
            if (insource == null || "".equals(insource)) {
                continue;
            }
            Map<String, String> sourceMap = new HashMap<String, String>(1);
            sourceMap.put("SOURCE", insource);
            inputparaValue.add(sourceMap);
        }

        IElementParameter OUTPUT_PARAMSPara = wenCom.getElementParameter("OUTPUT_PARAMS");
        List<Map<String, String>> outputMap = (List<Map<String, String>>) OUTPUT_PARAMSPara.getValue();
        if (outputMap == null) {
            outputMap = new ArrayList<Map<String, String>>();
        } else {
            outputMap.clear();
        }
        for (ParameterInfo para : outEleList) {
            if (para.getName() == null || "".equals(para.getName())) {
                continue;
            }

            Map<String, String> eleMap = new HashMap<String, String>(3);
            eleMap.put("ELEMENT", para.getName());
            eleMap.put("NAMESPACE", para.getNameSpace());
            eleMap.put("TYPE", para.getKind());
            outputMap.add(eleMap);
        }

        for (OutPutMappingData data : outputList) {

            Map<String, String> dataMap = new HashMap<String, String>(2);
            if (data.getParameterName() != null) {
                dataMap.put("EXPRESSION", data.getParameterName());
            } else if (data.getParameterName() == null) {
                warningDialog("Please Select a Output Item.");
                return;
            }

            if (data.getMetadataColumn() != null) {
                dataMap.put("COLUMN", data.getMetadataColumn().getLabel());
            }
            // else if (data.getMetadataColumn() == null) {
            // warningDialog("Please Select a Output Item.");
            // return;
            // }

            // Map<String, String> dataMap2 = new HashMap<String, String>(2);
            // if (data.getParameterList().size() > 0) {
            // for (ParameterInfo para : data.getParameterList()) {
            // dataMap2.put("", para.getNameSpace());
            // dataMap2.put("", para.getKind());
            // }
            // }
            //
            // if (data.getOutputColumnValue() != null) {
            // dataMap.put(data.getParameterName(), data.getOutputColumnValue());
            // }

            outputMap.add(dataMap);
            // outputMap.add(dataMap2);
        }

        for (String outsource : outsourceList) {
            if (outsource == null || "".equals(outsource)) {
                continue;
            }
            Map<String, String> sourceMap = new HashMap<String, String>(1);
            sourceMap.put("SOURCE", outsource);
            outputMap.add(sourceMap);
        }

        super.okPressed();

    }

    private void warningDialog(String title) {
        MessageBox box = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_WARNING | SWT.OK);
        box.setText("WARNING"); //$NON-NLS-1$
        box.setMessage(title); //$NON-NLS-1$
        box.open();
    }

    public void checkPerformed(boolean enable) {
        final Button okBtn = getButton(IDialogConstants.OK_ID);
        if (okBtn != null) {
            okBtn.setEnabled(enable);
        }

    }

}
