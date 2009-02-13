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
package org.talend.core.ui.branding;

import java.util.List;

import org.eclipse.ui.IPageLayout;
import org.talend.repository.model.RepositoryNode;

/**
 * BrandingConfigure for each application's customize setting E.g /perspective/menus/repository
 * view/palletteview/coolbars etc.
 */
public interface IBrandingConfiguration extends IActionBarHelper {

    /**
     * 
     * init perspective
     * 
     * @param layout
     */
    void initPerspective(IPageLayout layout);

    /**
     * 
     * get the repositorynode you want to hide in repositoryview
     * 
     * @return
     */
    List<RepositoryNode> getHiddenRepositoryCategory(RepositoryNode parent);

    /**
     * 
     * hide components in paletteview
     * 
     * @return
     */
    void hideComponents();

    void setHelper(IActionBarHelper helper);

    IActionBarHelper getHelper();
}
