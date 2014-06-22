package org.talend.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.talend.core.model.context.ContextUtils;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.utils.ContextParameterUtils;
import org.talend.cwm.mip.service.CWMService;
import org.talend.designer.core.model.utils.emf.talendfile.ContextParameterType;
import org.talend.designer.core.model.utils.emf.talendfile.ContextType;
import orgomg.cwm.foundation.softwaredeployment.DataManager;

public class TalendCWMService extends CWMService {

    private Logger log = Logger.getLogger(TalendCWMService.class);

    @Override
    public String getReadableName(DataManager dataManager, String contextualName) {
        if (contextualName == null) {
            log.error("Null context name in connection " + dataManager); //$NON-NLS-1$
            return contextualName;
        }
        if (dataManager instanceof Connection && ((Connection) dataManager).isContextMode()) {
            Connection conn = (Connection) dataManager;
            String contextId = conn.getContextId();

            ContextItem contextItem = ContextUtils.getContextItemById2(contextId);
            ContextType contextType = ContextUtils.getContextTypeByName(contextItem, conn.getContextName(), false);
            if (contextType != null && ContextParameterUtils.isContainContextParam(contextualName)) {
                ContextParameterType param = null;
                for (ContextParameterType paramType : (List<ContextParameterType>) contextType.getContextParameter()) {
                    if (paramType.getName().equals(ContextParameterUtils.getVariableFromCode(contextualName))) {
                        param = paramType;
                        break;
                    }
                }
                if (param != null) {
                    String value2 = param.getValue();
                    if (value2 != null) {
                        return value2;
                    }
                }
            }
        }
        return contextualName;
    }

}
