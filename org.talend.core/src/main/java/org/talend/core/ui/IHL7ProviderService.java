// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui;

import java.util.Map;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.core.IService;
import org.talend.core.model.process.INode;
import org.talend.core.model.properties.HL7ConnectionItem;
import org.talend.repository.model.RepositoryNode;

/**
 * gcui class global comment. Detailled comment
 */
public interface IHL7ProviderService extends IService {

    public IWizard newHL7Wizard(IWorkbench workbench, boolean creation, RepositoryNode node, String[] existingNames);

    public boolean isHL7Node(final INode node);

    public boolean isHL7OutputNode(final INode node);

    public HL7ConnectionItem getRepositoryItem(final INode node);

    public boolean isRepositorySchemaLine(INode node, Map<String, Object> lineValue);

}
