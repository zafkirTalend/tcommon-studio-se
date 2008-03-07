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
package org.talend.core.model.components;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.talend.core.model.general.ModuleNeeded;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.INodeConnector;
import org.talend.core.model.process.INodeReturn;
import org.talend.core.model.temp.ECodePart;

/**
 * Interface that describes the functions that a must implements a component manager. <br/>
 * 
 * $Id$
 */
public interface IComponent {

    String JOBLET_PID = "org.talend.designer.joblet";

    String PROP_NAME = "NAME"; //$NON-NLS-1$

    String PROP_LONG_NAME = "LONG_NAME"; //$NON-NLS-1$

    String PROP_FAMILY = "FAMILY"; //$NON-NLS-1$

    String PROP_MENU = "MENU"; //$NON-NLS-1$

    String PROP_LINK = "LINK"; //$NON-NLS-1$

    String PROP_HELP = "HELP"; //$NON-NLS-1$

    public String getName();

    public String getTranslatedName();

    public RGB getLableForegroundColor();

    public String getLongName();

    public String getFamily();

    public ImageDescriptor getIcon32();

    public void setIcon32(ImageDescriptor icon32);

    public ImageDescriptor getIcon24();

    public void setIcon24(ImageDescriptor icon24);

    public ImageDescriptor getIcon16();

    public void setIcon16(ImageDescriptor icon16);

    public List<? extends IElementParameter> createElementParameters(INode node);

    public List<? extends INodeReturn> createReturns();

    public List<? extends INodeConnector> createConnectors();

    public Boolean hasConditionalOutputs();

    public Boolean isMultiplyingOutputs();

    public String getPluginFullName();

    public boolean isSchemaAutoPropagated();

    public boolean isDataAutoPropagated();

    public boolean useMerge();

    public boolean useLookup();

    public String getVersion();

    public List<IMultipleComponentManager> getMultipleComponentManagers();

    public boolean isLoaded();

    public boolean isVisible();

    public List<ModuleNeeded> getModulesNeeded();

    public String getPathSource();

    public List<ECodePart> getAvailableCodeParts();

    public List<String> getPluginDependencies();

    public boolean isMultipleOutput();

    public boolean useImport();

    public EComponentType getComponentType();

}
