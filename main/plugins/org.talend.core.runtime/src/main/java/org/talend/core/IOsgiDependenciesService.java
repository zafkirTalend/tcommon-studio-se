package org.talend.core;

import java.util.Map;

import org.osgi.framework.Constants;
import org.talend.core.model.properties.ProcessItem;

public interface IOsgiDependenciesService extends IService {

    String IMPORT_PACKAGE = Constants.IMPORT_PACKAGE;
    String REQUIRE_BUNDLE = Constants.REQUIRE_BUNDLE;
    String BUNDLE_CLASSPATH = Constants.BUNDLE_CLASSPATH;
    String EXPORT_PACKAGE = Constants.EXPORT_PACKAGE;
    char ITEM_SEPARATOR = ',';

    /**
     * key is {@link #IMPORT_PACKAGE} {@link #REQUIRE_BUNDLE}
     * {@link #BUNDLE_CLASSPATH} {@link #EXPORT_PACKAGE}
     * 
     * @return
     */
    Map<String, String> getBundleDependences(ProcessItem pi);

}
