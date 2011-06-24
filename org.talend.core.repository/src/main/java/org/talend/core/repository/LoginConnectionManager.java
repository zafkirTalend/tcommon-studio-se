package org.talend.core.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.workbench.extensions.ExtensionImplementationProvider;
import org.talend.commons.utils.workbench.extensions.ExtensionPointLimiterImpl;
import org.talend.commons.utils.workbench.extensions.IExtensionPointLimiter;

public class LoginConnectionManager {

    private static final IExtensionPointLimiter CONNECT_PROVIDER = new ExtensionPointLimiterImpl(
            "org.talend.core.repository.connection_provider", "connectionValidate", 1, -1);

    public static List<ILoginConnectionService> getRemoteConnectionService() {
        List<ILoginConnectionService> uniservConnectionServices = new ArrayList<ILoginConnectionService>();
        List<IConfigurationElement> extension = ExtensionImplementationProvider.getInstanceV2(CONNECT_PROVIDER);

        for (IConfigurationElement current : extension) {
            try {
                Object object = current.createExecutableExtension("class");
                if (object instanceof ILoginConnectionService) {
                    uniservConnectionServices.add((ILoginConnectionService) object);
                }
            } catch (CoreException e) {
                ExceptionHandler.process(e);
            }
        }
        return uniservConnectionServices;
    }
}
