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
package org.talend.core.prefs.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.osgi.framework.Bundle;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.preferences.TableEditor;
import org.talend.commons.utils.io.FilesUtils;
import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.utils.XSDValidater;

/**
 * bqian. <br/>
 * 
 * $Id: MetadataTalendTypeEditor.java 2738 2007-05-11 13:12:27Z bqian $
 * 
 */
public class MetadataTalendTypeEditor extends TableEditor {

    public static final String ID = "org.talend.core.prefs.ui.MetadataTalendTypeEditor"; //$NON-NLS-1$

    public static final String INTERAL_XSD_FILE = MetadataTalendType.INTERNAL_MAPPINGS_FOLDER + "/mapping_validate.xsd"; //$NON-NLS-1$

    /**
     * MetadataTalendTypeEditor constructor.
     * 
     * @param name
     * @param labelText
     * @param parent
     */
    public MetadataTalendTypeEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent);
    }

    @Override
    protected Table createTable(Composite parent) {
        Table contextTable = new Table(parent, SWT.BORDER | SWT.SINGLE);
        contextTable.setLinesVisible(true);
        contextTable.setHeaderVisible(true);

        TableColumn engineName = new TableColumn(contextTable, SWT.NONE);
        engineName.setText(Messages.getString("MetadataTalendTypeEditor.column1.Name"));  //$NON-NLS-1$
        engineName.setWidth(300);

        return contextTable;
    }

    @Override
    protected IStructuredContentProvider createContentProvider() {
        return new IStructuredContentProvider() {

            public Object[] getElements(Object inputElement) {
                return ((List) inputElement).toArray();
            }

            public void dispose() {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

        };
    }

    @Override
    protected ITableLabelProvider createLabelProvider() {
        return new ITableLabelProvider() {

            public Image getColumnImage(Object element, int columnIndex) {
                return null;
            }

            public String getColumnText(Object element, int columnIndex) {
                if (element instanceof String) {
                    String file = (String) element;
                    return file;
                }
                throw new IllegalStateException();
            }

            public void addListener(ILabelProviderListener listener) {
            }

            public void dispose() {
            }

            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            public void removeListener(ILabelProviderListener listener) {
            }
        };
    }

    @Override
    protected String writeString(List<String> items) {
        boolean needReload = false;

        List<String> allFileNames = this.getList();
        // add the new files;
        for (File newFile : newFiles) {
            if (allFileNames.contains(newFile.getName())) {
                try {
                    importFileIntoTalend(newFile);
                    needReload = true;
                } catch (Exception e) {
                    ExceptionHandler.process(e);
                }
            }
        }

        // delete the removed files
        List<File> files = MetadataTalendType.getMetadataMappingFiles();
        for (File file : files) {
            if (!allFileNames.contains(file.getName())) {
                file.delete();
                needReload = true;
            }
        }

        if (needReload) {
            try {
                MetadataTalendType.loadCommonMappings();
            } catch (Exception e) {
                ExceptionHandler.process(e);
            }
        }

        return "no need to store the data"; //$NON-NLS-1$
    }

    // store the new imported files.
    public List<File> newFiles = new ArrayList<File>();

    @Override
    protected String getNewInputObject() {
        Shell shell = this.getShell();
        FileDialog dialog = new FileDialog(shell);
        dialog.setFilterExtensions(new String[] { "*.xml" }); //$NON-NLS-1$
        String fileName = dialog.open();
        if (fileName == null) {
            return null;
        }
        File xmlFile = new File(fileName);
        if (super.getList().contains(xmlFile.getName())) {
            MessageDialog
                    .openWarning(
                            shell,
                            Messages.getString("MetadataTalendTypeEditor.error.message"), Messages.getString("MetadataTalendTypeEditor.fileIsImported")); //$NON-NLS-1$ //$NON-NLS-2$
            return null;
        }

        if (!xmlFile.getName().startsWith("mapping_")) { //$NON-NLS-1$
            MessageDialog
                    .openWarning(
                            shell,
                            Messages.getString("MetadataTalendTypeEditor.error.message"), Messages.getString("MetadataTalendTypeEditor.fileNameStartRule")); //$NON-NLS-1$ //$NON-NLS-2$
            return null;
        }

        try {
            validateMetadata(xmlFile);
            newFiles.add(xmlFile);
            return xmlFile.getName();
        } catch (Exception e) {
            IStatus status = new Status(IStatus.ERROR, CorePlugin.PLUGIN_ID, IStatus.ERROR, Messages
                    .getString("MetadataTalendTypeEditor.fileIsInvalid"), e); //$NON-NLS-1$
            ErrorDialog.openError(shell, Messages.getString("MetadataTalendTypeEditor.error.message"), null, status); //$NON-NLS-1$
        }

        return null;
    }

    /**
     * Import the selected file into talend.
     * 
     * @param xmlFile
     */
    private File importFileIntoTalend(File xmlFile) throws IOException {
        String fileName = xmlFile.getName();

        IPath filePath = new Path(MetadataTalendType.INTERNAL_MAPPINGS_FOLDER);

        Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
        URL url = FileLocator.toFileURL(FileLocator.find(b, filePath, null));

        File targetFile = new File(url.getPath(), fileName);

        FilesUtils.copyFile(xmlFile, targetFile);
        return targetFile;
    }

    /**
     * Use the internal xsd to validate the matadata mapping file.
     * 
     * @param xmlFile
     */
    private void validateMetadata(File xmlFile) throws Exception {
        Path filePath = new Path(INTERAL_XSD_FILE);

        Bundle b = Platform.getBundle(CorePlugin.PLUGIN_ID);
        URL url = FileLocator.toFileURL(FileLocator.find(b, filePath, null));
        File xsdFile = new File(url.getFile());

        new XSDValidater().validateWithDom(xsdFile, xmlFile);
    }

    @Override
    protected String getExistingInputObject(String obj) {
        // prevent editing the existing item.
        return null;
    }

    @Override
    protected List<String> readString(String stringList) {
        // no need to pasrse data here.
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.preferences.TableEditor#doLoad()
     */
    @Override
    protected void doLoad() {
        List<String> fileNames = super.getList();
        fileNames.clear();
        try {
            List<File> files = MetadataTalendType.getMetadataMappingFiles();
            for (File file : files) {
                fileNames.add(file.getName());
            }
            viewer.setInput(fileNames);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.commons.ui.swt.preferences.TableEditor#doLoadDefault()
     */
    @Override
    protected void doLoadDefault() {
        doLoad();
    }

}
