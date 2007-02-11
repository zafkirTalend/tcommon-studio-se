package com.quantum.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * <p>This class takes a URL pattern and creates a full URL.  A URL pattern might look
 * like this: <code>jdbc:postgresql://{hostname}:{port}/{dbname}</code>, and the 
 * properties should include "hostname", "port" and "dbname". 
 * 
 * @author BC Holmes
 */
public class URLBuilder {

	public static String createURL(String urlPattern, Map properties) {
		StringBuffer buffer = new StringBuffer();
		boolean isVariable = false;
		String variableName = null;
		for (StringTokenizer tokenizer = new StringTokenizer(urlPattern, "{}", true);
			tokenizer.hasMoreTokens(); ) {
			String token = tokenizer.nextToken();
			
			if ("{".equals(token) && !isVariable) {
				isVariable = true;
			} else if (isVariable && "}".equals(token) && variableName != null) {
				if (!properties.containsKey(variableName)) {
					buffer.append("{");
					buffer.append(variableName);
					buffer.append("}");
				} else {
					buffer.append(properties.get(variableName));
				}
				isVariable = false;
			} else if (isVariable) {
				variableName = token;
			} else {
				buffer.append(token);
			}
		}
		return buffer.toString();
	}

	public static String[] getVariableNames(String urlPattern) {
		List list = new ArrayList();
		if (urlPattern != null) {
			boolean isVariable = false;
			String variableName = null;
			for (StringTokenizer tokenizer = new StringTokenizer(urlPattern, "{}", true);
				tokenizer.hasMoreTokens(); ) {
				String token = tokenizer.nextToken();
				
				if ("{".equals(token) && !isVariable) {
					isVariable = true;
				} else if (isVariable && "}".equals(token) && variableName != null) {
					list.add(variableName);
					isVariable = false;
				} else if (isVariable) {
					variableName = token;
				}
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}
}
