package com.quantum.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.quantum.adapters.AdapterFactory;
import com.quantum.model.JDBCDriver;

import org.eclipse.swt.widgets.Composite;


/**
 * @author BC Holmes
 */
public class URLSetupControlFactory {
	
	public static boolean hasControl(JDBCDriver driver) {
		return null != getControlClass(driver);
	}

	public static URLSetupControl create(JDBCDriver driver, Composite composite) {
		Class controlClass = getControlClass(driver);
		
		if (BasicThreePartURLSetupControl.class == controlClass) {
			URLSetupControl control =  new BasicThreePartURLSetupControl(composite, driver);
			control.createPart();
			return control;
		} else if (BasicOnePartURLSetupControl.class == controlClass) {
			String[] properties = getProperties(driver);
			URLSetupControl control = new BasicOnePartURLSetupControl(composite, driver, properties[0]);
			control.createPart();
			return control;
		} else if (InformixURLSetupControl.class == controlClass) {
			URLSetupControl control = new InformixURLSetupControl(composite, driver);
			control.createPart();
			return control;
		} else {
			return null;
		}
	}
	
	private static Class getControlClass(JDBCDriver driver) {
		String[] parameters = getProperties(driver);
		List parametersList = new ArrayList();
		if (parameters != null) {
			parametersList.addAll(Arrays.asList(parameters));
		}
		
		if (parametersList.size() == 3 
			&& parametersList.contains("dbname")
			&& parametersList.contains("hostname")
			&& parametersList.contains("port")) {
				return BasicThreePartURLSetupControl.class;
		} else if (parametersList.size() == 4 
			&& parametersList.contains("dbname")
			&& parametersList.contains("hostname")
			&& parametersList.contains("informixserver")
			&& parametersList.contains("port")) {
				return InformixURLSetupControl.class;
		} else if (parametersList.size() == 1 
			&& (parametersList.contains("dbname")
					|| parametersList.contains("datasource"))) {
				return BasicOnePartURLSetupControl.class;
		} else if (parametersList.size() == 2
			&& parametersList.contains("hostname")
			&& parametersList.contains("port")) {
				return BasicThreePartURLSetupControl.class;
		} else {
			return null;
		}
	}

	/**
	 * @param driver
	 * @return
	 */
	private static String[] getProperties(JDBCDriver driver) {
		String driverClassName = driver.getClassName();
		String urlPattern = AdapterFactory.getInstance().getURLPattern(driverClassName);
		String[] parameters = URLBuilder.getVariableNames(urlPattern);
		return parameters;
	}
}
