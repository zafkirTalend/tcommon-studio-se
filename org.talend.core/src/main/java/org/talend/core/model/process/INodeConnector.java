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
package org.talend.core.model.process;

/**
 * Interface for Links between Nodes in a Process. <br/>
 * 
 * $Id$
 * 
 */
public interface INodeConnector {

    public EConnectionType getDefaultConnectionType();

    public void setDefaultConnectionType(final EConnectionType defaultConnectionType);

    public IConnectionProperty getConnectionProperty(EConnectionType type);

    public int getMaxLinkOutput();

    public void setMaxLinkOutput(final int maxLinkOutput);

    public int getMaxLinkInput();

    public void setMaxLinkInput(final int maxLinkInput);

    public int getMinLinkInput();

    public void setMinLinkInput(int minLinkInput);

    public int getMinLinkOutput();

    public void setMinLinkOutput(int minLinkOutput);

    public boolean isBuiltIn();

    public void setBuiltIn(final boolean builtIn);

    public int getCurLinkNbInput();

    public void setCurLinkNbInput(final int curLinkNb);

    public int getCurLinkNbOutput();

    public void setCurLinkNbOutput(final int curLinkNb);

    public String getName();

    public void setName(String name);

    public String getLinkName();

    public void setLinkName(String linkName);

    public String getMenuName();

    public void setMenuName(String menuName);

    public String getBaseSchema();

    public void setBaseSchema(String baseSchema);
}
