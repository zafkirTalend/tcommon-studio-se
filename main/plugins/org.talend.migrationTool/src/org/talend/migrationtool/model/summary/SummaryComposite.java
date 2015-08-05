// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.migrationtool.model.summary;

import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.talend.commons.ui.runtime.image.ECoreImage;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.migration.IProjectMigrationTask;

/**
 * DOC smallet class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class SummaryComposite extends Composite {

    private static final Color BCK_COLOR = new Color(null, 255, 255, 255);

    private static final int HORIZONTAL_MERGE = 5;

    private static final int VERTICAL_MERGE = 5;

    private static final int HORIZONTAL_SPACE = 5;

    private static final int VERTICAL_SPACE = 5;

    public SummaryComposite(Composite parent, int style, List<IProjectMigrationTask> tasks, int size) {
        super(parent, style);
        parent.setBackground(BCK_COLOR);
        this.setLayout(new FillLayout());
        this.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.setBackground(BCK_COLOR);

        FormData data;

        ScrolledComposite scrolled = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        scrolled.setLayout(new GridLayout());
        scrolled.setExpandHorizontal(true);
        scrolled.setBackground(BCK_COLOR);

        Composite container = new Composite(scrolled, SWT.NONE);
        scrolled.setContent(container);
        FormLayout layout2 = new FormLayout();
        container.setLayout(layout2);
        container.setBackground(BCK_COLOR);

        Control lastControl = null;

        int i = 0;
        for (IProjectMigrationTask task : tasks) {
            Label imageLabel = new Label(container, SWT.NONE);
            imageLabel.setImage(ImageProvider.getImage(ECoreImage.TALEND_PICTO));
            imageLabel.setBackground(BCK_COLOR);
            data = new FormData();
            data.left = new FormAttachment(HORIZONTAL_MERGE);
            if (lastControl == null) {
                data.top = new FormAttachment(VERTICAL_MERGE);
            } else {
                data.top = new FormAttachment(lastControl, VERTICAL_SPACE, SWT.BOTTOM);
            }
            imageLabel.setLayoutData(data);

            Label taskNameLabel = new Label(container, SWT.NONE);
            taskNameLabel.setText(task.getName());
            taskNameLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
            taskNameLabel.setBackground(BCK_COLOR);
            data = new FormData();
            data.left = new FormAttachment(imageLabel, HORIZONTAL_SPACE);
            data.top = new FormAttachment(imageLabel, 0, SWT.CENTER);
            taskNameLabel.setLayoutData(data);

            Label taskDescLabel = new Label(container, SWT.WRAP);
            taskDescLabel.setText(task.getDescription());
            taskDescLabel.setBackground(BCK_COLOR);
            data = new FormData();
            data.left = new FormAttachment(HORIZONTAL_MERGE);
            data.right = new FormAttachment(100, -HORIZONTAL_MERGE);
            data.top = new FormAttachment(taskNameLabel, VERTICAL_SPACE, SWT.BOTTOM);
            taskDescLabel.setLayoutData(data);
            lastControl = taskDescLabel;

            if (i + 1 < tasks.size()) {
                Label separator = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
                data = new FormData();
                data.left = new FormAttachment(HORIZONTAL_SPACE);
                data.right = new FormAttachment(100, -HORIZONTAL_SPACE);
                data.top = new FormAttachment(taskDescLabel, HORIZONTAL_SPACE);
                separator.setLayoutData(data);
                lastControl = separator;
            }

            i++;
        }

        container.setSize(container.computeSize(size, SWT.DEFAULT));
    }
}
