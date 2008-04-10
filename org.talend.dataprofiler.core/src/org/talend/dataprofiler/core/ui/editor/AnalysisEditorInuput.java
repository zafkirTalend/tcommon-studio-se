// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.talend.commons.emf.EMFUtil;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.analysis.AnalysisType;
import org.talend.dataquality.analysis.util.AnalysisSwitch;
import org.talend.dataquality.helpers.AnalysisHelper;

/**
 * @author rli
 * 
 */
public class AnalysisEditorInuput implements IStorageEditorInput, IPersistableElement {

    private static Logger log = Logger.getLogger(AnalysisEditorInuput.class);

    private IStorage fStorage;

    private File fFile;

    private String fName;

    private Analysis analysis;

    public AnalysisEditorInuput(String name) {
        fName = name;
        createStorage();
    }

    public AnalysisEditorInuput(File file) {
        fFile = file;
        createStorage();
    }

    private void createStorage() {
        fStorage = new IStorage() {

            public InputStream getContents() throws CoreException {
                try {
                    return fFile != null ? new FileInputStream(fFile) : getClearStream();
                } catch (IOException e) {
                    return getClearStream();
                }
            }

            private InputStream getClearStream() {
                return new ByteArrayInputStream(new byte[0]);
            }

            public IPath getFullPath() {
                return null;
            }

            public String getName() {
                return AnalysisEditorInuput.this.getName();
            }

            public boolean isReadOnly() {
                return false;
            }

            @SuppressWarnings("unchecked")
            public Object getAdapter(Class adapter) {
                return null;
            }
        };
    }

    public boolean exists() {
        return fFile != null ? fFile.exists() : false;
    }

    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    public String getName() {
        return fFile != null ? fFile.getName() : fName;
    }

    public IPersistableElement getPersistable() {
        return fFile != null ? this : null;
    }

    public String getToolTipText() {
        return fFile != null ? fFile.getAbsolutePath() : getName();
    }

    public Object getAdapter(Class adapter) {
        return null;
    }

    public IStorage getStorage() throws CoreException {
        return fStorage;
    }

    public File getFile() {
        return fFile;
    }

    public String getFactoryId() {
        return "org.talend.dataprofiler.core.ui.editor.DocumentEditorInput";
    }

    public void saveState(IMemento memento) {
        if (fFile == null) {
            return;
        }
        memento.putString(PluginConstant.PATH_SAVE, fFile.getAbsolutePath());
    }

    public Analysis getAnalysis() {
        if (analysis != null) {
            return analysis;
        }
        EMFUtil util = new EMFUtil();
        log.info("Loading file " + fFile.getAbsolutePath());
        ResourceSet rs = util.getResourceSet();
        Resource r = rs.getResource(URI.createFileURI(fFile.getAbsolutePath()), true);

        EList<EObject> contents = r.getContents();
        if (contents.isEmpty()) {
            log.error("No content in " + r);
        }
        log.info("Nb elements in contents " + contents.size());
        AnalysisSwitch<Analysis> mySwitch = new AnalysisSwitch<Analysis>() {

            public Analysis caseAnalysis(Analysis object) {
                return object;
            }
        };
        if (contents != null) {
            analysis = mySwitch.doSwitch(contents.get(0));
        }
        return analysis;
    }

    public AnalysisType getAnalysisType() {
        return AnalysisHelper.getAnalysisType(getAnalysis());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fFile == null) ? 0 : fFile.hashCode());
        result = prime * result + ((fName == null) ? 0 : fName.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj)
    // return true;
    // if (obj == null)
    // return false;
    // if (getClass() != obj.getClass())
    // return false;
    // final AnalysisEditorInuput other = (AnalysisEditorInuput) obj;
    // if (fFile == null) {
    // if (other.fFile != null)
    // return false;
    // } else if (!fFile.equals(other.fFile))
    // return false;
    // if (fName == null) {
    // if (other.fName != null)
    // return false;
    // } else if (!fName.equals(other.fName))
    // return false;
    // return true;
    // }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnalysisEditorInuput)) {
            return false;
        }
        AnalysisEditorInuput input = (AnalysisEditorInuput) obj;
        return fFile == null && input.fFile == null ? fName == input.fName || fName != null && fName.equals(input.fName)
                : fFile == input.fFile || fFile != null && fFile.equals(input.fFile);
    }

}
