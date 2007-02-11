/*******************************************************************************
 * Copyright (c) 2005 RadRails.org and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.radrails.org/legal/cpl-v10.html
 *******************************************************************************/
package com.quantum.editors;


/**
 * @author mkent/jhvdv
 * 
 */
public class IndentCodeFormatter {

	private static final String NEW_LINE = System.getProperty("line.separator");

	private static final String SPACE = " ";

	private static final String TAB = "\t";

	private static IndentCodeFormatter instance;

	private IndentCodeFormatter() {
	}

	public static IndentCodeFormatter getInstance() {
		if (instance == null) {
			instance = new IndentCodeFormatter();
		}
		return instance;
	}

	/**
	 * @param unformatted
	 * @return
	 */
	public String format(String unformatted, int spacesPerTab, boolean useTabs) {
		StringBuffer formatted = new StringBuffer();
		String[] lines = unformatted.split(NEW_LINE);

		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < spacesPerTab; j++) {
			sb.append(SPACE);
		}
		String spacesForTab = sb.toString();

		for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll(TAB, spacesForTab);
			formatted.append(lines[i]);
            formatted.append(NEW_LINE);
		}

		return formatted.toString();
	}
}
