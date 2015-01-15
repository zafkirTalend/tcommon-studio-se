package org.talend.repository.metadata;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbench;
import org.talend.core.model.metadata.IDatabaseConstant;
import org.talend.core.model.metadata.IEbcdicConstant;
import org.talend.core.model.process.EParameterFieldType;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.process.INode;
import org.talend.core.service.IDbProviderService;
import org.talend.repository.model.RepositoryNode;

public class DbProviderService implements IDbProviderService {

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.IProviderService#newWizard(org.eclipse.ui.IWorkbench, boolean,
     * org.talend.repository.model.RepositoryNode, java.lang.String[])
     */
    @Override
    public IWizard newWizard(IWorkbench workbench, boolean creation, RepositoryNode node, String[] existingNames) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.service.IDbProviderService#isRedShiftNode(org.talend.core.model.process.INode)
     */
    @Override
    public boolean isRedShiftNode(INode node) {
        if (node != null) {
            IElementParameter param = node.getElementParameter(IEbcdicConstant.PROPERTY);
            if (param != null && param.getFieldType() == EParameterFieldType.PROPERTY_TYPE
                    && IDatabaseConstant.REDSHIFT.equals(param.getRepositoryValue())) {
                return true;
            }
        }
        return false;
    }

}
