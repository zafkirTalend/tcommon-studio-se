package org.talend.commons.ui.utils;

public class Log4jUtil {

    public static boolean isEnable() {
        // temporary like this,maybe can improve later
        String log4jEnable = System.getProperty("LOG4J_ENABLED");// the special customer will config the property?
        if ("true".equals(log4jEnable)) {
            return true;
        }
        return false;
    }

}
