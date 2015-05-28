// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.template;

import org.talend.designer.maven.model.TalendMavenConstants;

/**
 * created by ggu on 2 Feb 2015 Detailled comment
 *
 */
@SuppressWarnings("nls")
public interface MavenTemplateConstants {

    final static String PATH_RESOURCES = "resources";

    final static String PATH_RESOURCES_TEMPLATES = PATH_RESOURCES + '/' + "templates";

    final static String PATH_OSGI_BUNDLE_TEMPLATE = PATH_RESOURCES_TEMPLATES + '/' + TalendMavenConstants.OSGI_BUNDLE_NAME;

    final static String POM_ROUTINGS_TEMPLATE_FILE_NAME = "pom_routines_template.xml";

    final static String POM_JOB_TEMPLATE_FILE_NAME = "pom_job_template.xml";

    final static String POM_TEST_TEMPLATE_FILE_NAME = "pom_test_template.xml";

    final static String ASSEMBLY_JOB_TEMPLATE_FILE_NAME = "assembly_job_template.xml";

    final static String PROJECT_TEMPLATE_FILE_NAME = "pom_project_template.xml";

    final static String JOB_RUN_BAT_TEMPLATE_FILE_NAME = "Job_run_template.bat";

    final static String JOB_RUN_SH_TEMPLATE_FILE_NAME = "Job_run_template.sh";

    final static String JOB_INFO_TEMPLATE_FILE_NAME = "jobInfo_template.properties";

    final static String TEST_JOB_TEMPLATE_FILE_NAME = "test_job_pom_template.xml";

}
