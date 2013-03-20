package org.talend.core.hadoop.custom;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.talend.commons.ui.swt.formtools.LabelledCombo;
import org.talend.core.hadoop.version.EHadoopDistributions;
import org.talend.core.hadoop.version.EHadoopVersion4Drivers;
import org.talend.core.i18n.Messages;

/**
 * created by ycbai on 2013-3-15 Detailled comment
 * 
 */
public class HadoopVersionDialog extends Dialog {

    private static final int VISIBLE_DISTRIBUTION_COUNT = 5;

    private static final int VISIBLE_VERSION_COUNT = 6;

    private LabelledCombo distributionCombo;

    private LabelledCombo versionCombo;

    private String distribution;

    private String version;

    public HadoopVersionDialog(Shell parentShell) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.MIN | SWT.APPLICATION_MODAL);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("HadoopVersionDialog.title")); //$NON-NLS-1$
        newShell.setSize(400, 220);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        GridLayout layout = (GridLayout) composite.getLayout();
        layout.numColumns = 2;

        createVersionFields(composite);
        addListener();
        init();

        return composite;
    }

    private void createVersionFields(Composite parent) {
        distributionCombo = new LabelledCombo(parent, Messages.getString("HadoopVersionDialog.distribution"), //$NON-NLS-1$
                Messages.getString("HadoopVersionDialog.distribution.tooltip"), new String[0], 1, true); //$NON-NLS-1$
        distributionCombo.setVisibleItemCount(VISIBLE_DISTRIBUTION_COUNT);
        versionCombo = new LabelledCombo(parent, Messages.getString("HadoopVersionDialog.version"), //$NON-NLS-1$
                Messages.getString("HadoopVersionDialog.version.tooltip"), new String[0], 1, true); //$NON-NLS-1$
        versionCombo.setVisibleItemCount(VISIBLE_VERSION_COUNT);
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
}