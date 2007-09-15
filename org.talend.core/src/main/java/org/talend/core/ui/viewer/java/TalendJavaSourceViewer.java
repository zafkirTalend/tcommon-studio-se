// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.core.ui.viewer.java;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jdt.ui.text.JavaTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 */
public class TalendJavaSourceViewer extends JavaSourceViewer {

    public static final String VIEWER_CLASS_NAME = "TalendJavaSourceViewer";

    private TalendJavaSourceViewer(Composite parent, IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
            boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
        super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, store);
    }

    public static TalendJavaSourceViewer createViewer(Composite composite, String text, int styles) {
        IPreferenceStore store = JavaPlugin.getDefault().getCombinedPreferenceStore();
        final TalendJavaSourceViewer viewer = new TalendJavaSourceViewer(composite, null, null, false, styles, store);
        StringBuffer buff = new StringBuffer();
        buff.append("package routines;\n\n");
        buff.append("public class " + VIEWER_CLASS_NAME + " {\n");
        buff.append("public void myFunction(){\n");
        buff.append(text);
        Document doc = new Document(buff.toString());

        viewer.setDocument(doc, buff.toString().length() - text.length(), text.length());

        JavaTextTools tools = JavaPlugin.getDefault().getJavaTextTools();
        tools.setupJavaDocumentPartitioner(viewer.getDocument(), IJavaPartitions.JAVA_PARTITIONING);
        TalendJavaViewerConfiguration config = new TalendJavaViewerConfiguration(tools.getColorManager(), store);
        viewer.configure(config);

        viewer.getTextWidget().setFont(JFaceResources.getFont(PreferenceConstants.EDITOR_TEXT_FONT));

        viewer.enableOperation(ISourceViewer.CONTENTASSIST_PROPOSALS, true);
        viewer.setEditable(true);
        viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

        viewer.getControl().addKeyListener(new KeyAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.stateMask == SWT.CTRL && e.keyCode == 32) {
                    viewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                } else if (e.stateMask == SWT.CTRL && e.stateMask == SWT.SHIFT && e.keyCode == 'F') {
                    viewer.doOperation(ISourceViewer.FORMAT);
                }
            }
        });

        return viewer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.source.SourceViewer#getControl()
     */
    @Override
    public Control getControl() {
        return super.getControl();
    }
}
