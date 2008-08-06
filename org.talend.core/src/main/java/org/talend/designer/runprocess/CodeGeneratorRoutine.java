// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.runprocess;

import java.util.ArrayList;
import java.util.List;

import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.model.general.Project;
import org.talend.core.model.properties.InformationLevel;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.core.model.repository.IRepositoryObject;
import org.talend.repository.model.IProxyRepositoryFactory;

/**
 * Build RoutineName for PerlHeader.
 * 
 * $Id: CodeGeneratorRoutine.java 14854 2008-06-06 16:05:36Z mhelleboid $
 * 
 */
public final class CodeGeneratorRoutine {
	private static List<String> routinesName;

	/**
	 * Default Constructor. Must not be used.
	 */
	private CodeGeneratorRoutine() {
	}

	/**
	 * Actually used in ProcessorUtilities.generateCode.
	 */
	public static void initializeRoutinesName() {
		ECodeLanguage currentLanguage = ((RepositoryContext) CorePlugin
				.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY))
				.getProject().getLanguage();
		List<String> toReturn = new ArrayList<String>();

		RepositoryContext repositoryContext = (RepositoryContext) CorePlugin
				.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY);
		Project project = repositoryContext.getProject();

		IProxyRepositoryFactory repositoryFactory = CorePlugin.getDefault()
				.getRepositoryService().getProxyRepositoryFactory();

		String builtInPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER
				+ "::" + "system" + "::";
		String userPath = ILibrariesService.SOURCE_PERL_ROUTINES_FOLDER + "::"
				+ project.getTechnicalLabel() + "::";

		try {
			List<IRepositoryObject> routines = repositoryFactory
					.getAll(ERepositoryObjectType.ROUTINES);
			for (IRepositoryObject routine : routines) {
				if (currentLanguage.equals(ECodeLanguage.JAVA)) {
					InformationLevel level = routine.getProperty()
							.getMaxInformationLevel();
					if (level.getValue() == InformationLevel.ERROR) {
						continue;
					}
					toReturn.add(routine.getLabel());
				} else {
					RoutineItem item = (RoutineItem) routine.getProperty()
							.getItem();
					if (item.isBuiltIn()) {
						toReturn.add(builtInPath + routine.getLabel());
					} else {
						toReturn.add(userPath + routine.getLabel());
					}
				}
			}
		} catch (PersistenceException e) {
			ExceptionHandler.process(e);
		}
		routinesName = toReturn;
	}

	/**
	 * initializeRoutinesName must be called first or it might return wrong list
	 * name or null.
	 */
	public static List<String> getRoutineName() {
		return routinesName;
	}
}
