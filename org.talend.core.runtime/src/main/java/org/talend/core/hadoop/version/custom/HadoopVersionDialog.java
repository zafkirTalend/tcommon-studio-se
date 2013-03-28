package org.talend.core.hadoop.version.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.ui.swt.formtools.LabelledCombo;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.hadoop.IHadoopService;
import org.talend.core.hadoop.version.EHadoopDistributions;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;
import org.talend.core.runtime.i18n.Messages;

/**
 * created by ycbai on 2013-3-15 Detailled comment
 * 
 */
public class HadoopVersionDialog extends Dialog {

    private static final int VISIBLE_DISTRIBUTION_COUNT = 5;

    private static final int VISIBLE_VERSION_COUNT = 6;

    private LabelledCombo distributionCombo;

    private LabelledCombo versionCombo;

    private Button importFromZipBtn;

    private Button importFromVersion;

    private boolean isFromExistVersion = true;;

    private boolean isFromZip;

    private String distribution;

    private String version;

    Map<ECustomVersionGroup, String> groupsAndDispaly;

    private List<Button> existVersionCheckBoxList;

    private List<Button> fromZipCheckBoxList;

    private Text zipLocationText;

    private String zipLocation;

    private Button browseButton;

    private Text messageLabel;

    private HadoopCustomLibrariesUtil customLibUtil;

    private Map<ECustomVersionGroup, Boolean> existVersionSelectionMap = new HashMap<ECustomVersionGroup, Boolean>();

    private Map<ECustomVersionGroup, Boolean> fromZipSelectionMap = new HashMap<ECustomVersionGroup, Boolean>();

    public HadoopVersionDialog(Shell parentShell, Map<ECustomVersionGroup, String> groupsAndDispaly,
            HadoopCustomLibrariesUtil customLibUtil) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MIN | SWT.APPLICATION_MODAL);
        this.groupsAndDispaly = groupsAndDispaly;
        this.customLibUtil = customLibUtil;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("HadoopVersionDialog.title")); //$NON-NLS-1$
        newShell.setSize(400, 400);
        newShell.addListener(SWT.Resize, new Listener() {

            @Override
            public void handleEvent(Event event) {
                layoutTitle(true);
            }

        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, 0);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        applyDialogFont(composite);
        // initialize the dialog units
        initializeDialogUnits(composite);

        Composite contents = new Composite(composite, SWT.NONE);
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        contents.setLayoutData(layoutData);
        FormLayout formLayout = new FormLayout();
        contents.setLayout(formLayout);
        messageLabel = new Text(contents, SWT.WRAP | SWT.READ_ONLY | SWT.BORDER);
        messageLabel.setText(Messages.getString("HadoopVersionDialog.msg"));//$NON-NLS-1$
        messageLabel.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
        layoutTitle(false);
        // create the dialog area and button bar
        dialogArea = createDialogArea(composite);
        buttonBar = createButtonBar(composite);
        return composite;
    }

    private void layoutTitle(boolean forceLayout) {
        FormData messageLabelData = new FormData();
        int messageLabelHeight = messageLabel.computeSize(getShell().getSize().x, SWT.DEFAULT).y;
        messageLabelHeight = Math.max(40, messageLabelHeight);
        messageLabelData.top = new FormAttachment(0, 0);
        messageLabelData.right = new FormAttachment(100, 0);
        messageLabelData.left = new FormAttachment(0, 0);
        messageLabelData.height = messageLabelHeight;
        messageLabel.setLayoutData(messageLabelData);
        if (forceLayout) {
            getShell().layout();
        }
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createVersionFields(composite);
        addListener();
        init();

        return composite;
    }

    private void createVersionFields(Composite parent) {
        GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
        importFromVersion = new Button(parent, SWT.RADIO);
        importFromVersion.setText(Messages.getString("HadoopVersionDialog.importFromExistVersion"));//$NON-NLS-1$
        importFromVersion.setLayoutData(layoutData);

        Composite existVersionGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginLeft = 10;
        existVersionGroup.setLayout(layout);

        distributionCombo = new LabelledCombo(existVersionGroup, Messages.getString("HadoopVersionDialog.distribution"), //$NON-NLS-1$
                Messages.getString("HadoopVersionDialog.distribution.tooltip"), new String[0], 2, true); //$NON-NLS-1$
        distributionCombo.setVisibleItemCount(VISIBLE_DISTRIBUTION_COUNT);
        versionCombo = new LabelledCombo(existVersionGroup, Messages.getString("HadoopVersionDialog.version"), //$NON-NLS-1$
                Messages.getString("HadoopVersionDialog.version.tooltip"), new String[0], 2, true); //$NON-NLS-1$
        versionCombo.setVisibleItemCount(VISIBLE_VERSION_COUNT);
        // typse checkbox
        Composite checkParent = new Composite(existVersionGroup, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        layoutData.horizontalSpan = 3;
        checkParent.setLayoutData(layoutData);
        GridLayout layout2 = new GridLayout(groupsAndDispaly.size(), false);
        checkParent.setLayout(layout2);
        layout2.marginWidth = 0;
        existVersionCheckBoxList = new ArrayList<Button>();
        for (ECustomVersionGroup group : groupsAndDispaly.keySet()) {
            final Button button = new Button(checkParent, SWT.CHECK);
            button.setData(group);
            button.setText(groupsAndDispaly.get(group));
            button.setSelection(true);
            existVersionCheckBoxList.add(button);
            existVersionSelectionMap.put(group, true);
            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    existVersionSelectionMap.put((ECustomVersionGroup) button.getData(), button.getSelection());
                }
            });
        }
        // import from zip
        importFromZipBtn = new Button(parent, SWT.RADIO);
        importFromZipBtn.setText(Messages.getString("HadoopVersionDialog.importFromZip"));//$NON-NLS-1$
        layoutData = new GridData(GridData.FILL);
        importFromZipBtn.setLayoutData(layoutData);
        Composite zipGroup = new Composite(parent, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        zipGroup.setLayoutData(layoutData);
        layout = new GridLayout();
        layout.numColumns = 3;
        layout.marginLeft = 10;
        zipGroup.setLayout(layout);
        Label label = new Label(zipGroup, SWT.NONE);
        label.setText(Messages.getString("HadoopVersionDialog.zipLocation"));//$NON-NLS-1$
        zipLocationText = new Text(zipGroup, SWT.BORDER);
        zipLocationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        browseButton = new Button(zipGroup, SWT.PUSH);
        browseButton.setText(Messages.getString("HadoopVersionDialog.browseBtn"));//$NON-NLS-1$

        // typse checkbox
        checkParent = new Composite(zipGroup, SWT.NONE);
        layoutData = new GridData(GridData.FILL_HORIZONTAL);
        layoutData.horizontalSpan = 3;
        checkParent.setLayoutData(layoutData);
        layout2 = new GridLayout(groupsAndDispaly.size(), false);
        checkParent.setLayout(layout2);
        layout2.marginWidth = 0;
        fromZipCheckBoxList = new ArrayList<Button>();
        for (ECustomVersionGroup group : groupsAndDispaly.keySet()) {
            final Button button = new Button(checkParent, SWT.CHECK);
            button.setData(group);
            button.setText(groupsAndDispaly.get(group));
            button.setSelection(true);
            fromZipCheckBoxList.add(button);
            fromZipSelectionMap.put(group, true);
            button.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    fromZipSelectionMap.put((ECustomVersionGroup) button.getData(), button.getSelection());
                }
            });
        }
        importFromVersion.setSelection(true);
        enableZipGroupe(false);

    }

    private void enableExistGroup(boolean enable) {
        isFromExistVersion = enable;
        distributionCombo.setEnabled(enable);
        versionCombo.setEnabled(enable);
        for (Button button : existVersionCheckBoxList) {
            button.setEnabled(enable);
        }
    }

    private void enableZipGroupe(boolean enable) {
        isFromZip = enable;
        zipLocationText.setEnabled(enable);
        browseButton.setEnabled(enable);
        for (Button button : fromZipCheckBoxList) {
            button.setEnabled(enable);
        }
    }

    private void addListener() {
        distributionCombo.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                String newDistributionDisplayName = distributionCombo.getText();
                EHadoopDistributions newDistribution = EHadoopDistributions
                        .getDistributionByDisplayName(newDistributionDisplayName);
                if (newDistribution != null) {
                    distribution = newDistribution.getName();
                    updateVersionPart();
                }
            }
        });

        versionCombo.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                String newVersionDisplayName = versionCombo.getText();
                EHadoopVersion4Drivers newVersion4Drivers = EHadoopVersion4Drivers.indexOfByVersionDisplay(newVersionDisplayName);
                if (newVersion4Drivers != null) {
                    version = newVersion4Drivers.getVersionValue();
                }
            }
        });

        // add listners
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getParentShell());
                dialog.setFilterExtensions(HadoopCustomLibrariesUtil.FILE__MASK);
                String path = dialog.open();
                if (path != null) {
                    zipLocationText.setText(path);
                }
            }
        });
        importFromVersion.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                enableExistGroup(true);
                enableZipGroupe(false);
            }
        });
        importFromZipBtn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                enableExistGroup(false);
                enableZipGroupe(true);
            }
        });
        zipLocationText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                zipLocation = zipLocationText.getText();
            }
        });
    }

    private void init() {
        distributionCombo.getCombo().setItems(getDistributions().toArray(new String[0]));
        distributionCombo.select(0);
    }

    protected List<String> getDistributions() {
        List<String> distributions = EHadoopDistributions.getAllDistributionDisplayNames();
        distributions.remove(EHadoopDistributions.CUSTOM.getDisplayName());

        return distributions;
    }

    protected List<String> getVersions(EHadoopDistributions dis) {
        List<String> result = new ArrayList<String>();
        List<EHadoopVersion4Drivers> v4dList = EHadoopVersion4Drivers.indexOfByDistribution(dis);
        for (EHadoopVersion4Drivers v4d : v4dList) {
            result.add(v4d.getVersionDisplay());
        }
        return result;
    }

    private void updateVersionPart() {
        EHadoopDistributions dis = EHadoopDistributions.getDistributionByName(distribution, false);
        List<String> items = getVersions(dis);
        String[] versions = new String[items.size()];
        items.toArray(versions);
        versionCombo.getCombo().setItems(versions);
        if (versions.length > 0) {
            versionCombo.getCombo().select(0);
        }
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();

        Point size = getShell().getSize();
        Point location = getInitialLocation(size);
        getShell().setBounds(getConstrainedShellBounds(new Rectangle(location.x, location.y, size.x, size.y)));
    }

    public String getDistribution() {
        return this.distribution;
    }

    public String getVersion() {
        return this.version;
    }

    @Override
    protected void okPressed() {
        boolean openQuestion = MessageDialog.openQuestion(getParentShell(), "Warning",
                Messages.getString("HadoopVersionDialog.confirmMsg"));//$NON-NLS-1$
        if (openQuestion) {
            super.okPressed();
        } else {
            super.cancelPressed();
        }
    }

    public Map<ECustomVersionGroup, Set<LibraryFile>> getImportLibLibraries() {
        Map<ECustomVersionGroup, Set<LibraryFile>> libMap = new HashMap<ECustomVersionGroup, Set<LibraryFile>>();
        if (isFromExistVersion) {
            IHadoopService hadoopService = null;
            if (GlobalServiceRegister.getDefault().isServiceRegistered(IHadoopService.class)) {
                hadoopService = (IHadoopService) GlobalServiceRegister.getDefault().getService(IHadoopService.class);
            }
            if (hadoopService != null) {
                Set<String> hadoopLibraries = hadoopService.getHadoopLibraries(getDistribution(), getVersion());
                Set<LibraryFile> convertToLibraryFile = customLibUtil.convertToLibraryFile(hadoopLibraries);
                for (ECustomVersionGroup group : existVersionSelectionMap.keySet()) {
                    if (existVersionSelectionMap.get(group)) {
                        libMap.put(group, new HashSet<LibraryFile>(convertToLibraryFile));
                    }
                }

            }

        } else if (isFromZip) {
            Set<ECustomVersionGroup> groups = new HashSet<ECustomVersionGroup>();
            for (ECustomVersionGroup group : fromZipSelectionMap.keySet()) {
                if (fromZipSelectionMap.get(group)) {
                    groups.add(group);
                }
            }
            return customLibUtil.readZipFile(zipLocation, groups);
        }

        return libMap;
    }
}