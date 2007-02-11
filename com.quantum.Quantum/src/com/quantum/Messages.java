package com.quantum;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	private static final String BUNDLE_NAME = "com.quantum.QuantumResources"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

    public static String getString(Class resourceClass, String key) {
        return getString(
            createKey(resourceClass, key));
    }

    private static String createKey(Class resourceClass, String key) {
        return resourceClass.getName() + (key.startsWith(".") ? key : "." + key);
    }

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

    public static String getString(Class resourceClass, String key, Object[] arguments) {
        return getString(createKey(resourceClass, key), arguments);
    }
    
    public static String getString(String key, Object[] arguments) {
        String string = getString(key);
        return MessageFormat.format(string, arguments);
    }
}
