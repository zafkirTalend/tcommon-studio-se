package org.talend.rcp.test;

import org.eclipse.core.expressions.PropertyTester;
import org.talend.core.repository.model.ProxyRepositoryFactory;

public class ProjectAuthorityTest extends PropertyTester {

    private static final String IS_CURRENT_USER_READONLY = "isCurrentUserReadOnly";

    public ProjectAuthorityTest() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (IS_CURRENT_USER_READONLY.equals(property)) {
            return !ProxyRepositoryFactory.getInstance().isUserReadOnlyOnCurrentProject();
        }
        return false;
    }
}
