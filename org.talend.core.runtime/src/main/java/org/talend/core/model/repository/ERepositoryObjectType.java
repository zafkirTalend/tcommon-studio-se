// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.AbstractDQModelService;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.PluginChecker;
import org.talend.core.model.properties.BRMSConnectionItem;
import org.talend.core.model.properties.BeanItem;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.CSVFileConnectionItem;
import org.talend.core.model.properties.ContextItem;
import org.talend.core.model.properties.DatabaseConnectionItem;
import org.talend.core.model.properties.DelimitedFileConnectionItem;
import org.talend.core.model.properties.DocumentationItem;
import org.talend.core.model.properties.EbcdicConnectionItem;
import org.talend.core.model.properties.ExcelFileConnectionItem;
import org.talend.core.model.properties.FTPConnectionItem;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.GenericSchemaConnectionItem;
import org.talend.core.model.properties.HL7ConnectionItem;
import org.talend.core.model.properties.HeaderFooterConnectionItem;
import org.talend.core.model.properties.Item;
import org.talend.core.model.properties.JobDocumentationItem;
import org.talend.core.model.properties.JobScriptItem;
import org.talend.core.model.properties.JobletDocumentationItem;
import org.talend.core.model.properties.JobletProcessItem;
import org.talend.core.model.properties.LDAPSchemaConnectionItem;
import org.talend.core.model.properties.LdifFileConnectionItem;
import org.talend.core.model.properties.LinkDocumentationItem;
import org.talend.core.model.properties.LinkRulesItem;
import org.talend.core.model.properties.MDMConnectionItem;
import org.talend.core.model.properties.PositionalFileConnectionItem;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.RegExFileConnectionItem;
import org.talend.core.model.properties.RoutineItem;
import org.talend.core.model.properties.RulesItem;
import org.talend.core.model.properties.SAPConnectionItem;
import org.talend.core.model.properties.SQLPatternItem;
import org.talend.core.model.properties.SVGBusinessProcessItem;
import org.talend.core.model.properties.SalesforceSchemaConnectionItem;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SnippetVariable;
import org.talend.core.model.properties.TDQItem;
import org.talend.core.model.properties.ValidationRulesConnectionItem;
import org.talend.core.model.properties.WSDLSchemaConnectionItem;
import org.talend.core.model.properties.XmlFileConnectionItem;
import org.talend.core.model.properties.util.PropertiesSwitch;
import org.talend.core.repository.IExtendRepositoryNode;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.core.runtime.i18n.Messages;
import org.talend.core.ui.branding.IBrandingService;

/**
 * DOC hywang class global comment. Detailled comment
 */
public class ERepositoryObjectType extends DynaEnum<ERepositoryObjectType> {

    public final static ERepositoryObjectType SVN_ROOT = new ERepositoryObjectType("repository.svnroot", true, 1);

    public final static ERepositoryObjectType BUSINESS_PROCESS = new ERepositoryObjectType("repository.businessProcess", true, 2);

    public final static ERepositoryObjectType SVG_BUSINESS_PROCESS = new ERepositoryObjectType("repository.svgBusinessProcess",
            true, 3);

    public final static ERepositoryObjectType PROCESS = new ERepositoryObjectType("repository.process", true, 4);

    public final static ERepositoryObjectType ROUTES = new ERepositoryObjectType("repository.routes", true, 5);

    public final static ERepositoryObjectType CONTEXT = new ERepositoryObjectType("repository.context", true, 6);

    public final static ERepositoryObjectType ROUTINES = new ERepositoryObjectType("repository.routines", true, 7);

    public final static ERepositoryObjectType BEANS = new ERepositoryObjectType("repository.beans", true, 8);

    public final static ERepositoryObjectType JOB_SCRIPT = new ERepositoryObjectType("repository.jobscript", true, 9);

    public final static ERepositoryObjectType SNIPPETS = new ERepositoryObjectType("repository.snippets", true, 10);

    public final static ERepositoryObjectType DOCUMENTATION = new ERepositoryObjectType("repository.documentation", true, 11);

    public final static ERepositoryObjectType METADATA = new ERepositoryObjectType("repository.metadata", true, 12);

    public final static ERepositoryObjectType METADATA_CON_TABLE = new ERepositoryObjectType("repository.metadataTable", 13,
            true, true);

    public final static ERepositoryObjectType METADATA_CON_COLUMN = new ERepositoryObjectType("repository.metadataColumn", 14,
            true, true);

    public final static ERepositoryObjectType METADATA_CON_VIEW = new ERepositoryObjectType("repository.metadataView", 15, true,
            true);

    public final static ERepositoryObjectType METADATA_CON_CATALOG = new ERepositoryObjectType("repository.metadataCatalog", 16,
            true, true);

    public final static ERepositoryObjectType METADATA_CON_SCHEMA = new ERepositoryObjectType("repository.metadataSchema", 17,
            true, true);

    public final static ERepositoryObjectType METADATA_CON_SYNONYM = new ERepositoryObjectType("repository.synonym", 18, true,
            true);

    public final static ERepositoryObjectType METADATA_CON_QUERY = new ERepositoryObjectType("repository.query", 19, true, true);

    public final static ERepositoryObjectType METADATA_CON_CDC = new ERepositoryObjectType("repository.CDC", 20, true, true);

    public final static ERepositoryObjectType METADATA_SAP_FUNCTION = new ERepositoryObjectType(
            "repository.SAPFunction", 21, true, true); //$NON-NLS-1$

    public final static ERepositoryObjectType METADATA_SAP_IDOC = new ERepositoryObjectType("repository.SAPIDoc", 22, true, true);

    public final static ERepositoryObjectType MDM_CONCEPT = new ERepositoryObjectType("repository.concept", 23, true, true); //$NON-NLS-1$

    public final static ERepositoryObjectType MDM_SCHEMA = new ERepositoryObjectType("repository.xmlSchema", 24, true, true); //$NON-NLS-1$

    public final static ERepositoryObjectType MDM_ELEMENT_TYPE = new ERepositoryObjectType(
            "repository.xmlElementType", 25, true, true);//$NON-NLS-1$

    public final static ERepositoryObjectType RECYCLE_BIN = new ERepositoryObjectType("repository.recyclebin", true, 26);

    public final static ERepositoryObjectType METADATA_COLUMN = new ERepositoryObjectType("repository.column", true, 27);

    // feature 0006484 add
    public final static ERepositoryObjectType METADATA_FILE_RULES = new ERepositoryObjectType(
            "repository.metadataFileRules", 28, true, "repository.metadataFileRules.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_LINKRULES = new ERepositoryObjectType(
            "repository.metadataLinkFileRules", 29, true, "repository.metadataLinkFileRules.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_RULES_MANAGEMENT = new ERepositoryObjectType(
            "repository.metadataRulesManagement", 30, true, "repository.metadataRulesManagement.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_CONNECTIONS = new ERepositoryObjectType(
            "repository.metadataConnections", 31, true, "repository.metadataConnections.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_SAPCONNECTIONS = new ERepositoryObjectType(
            "repository.metadataSAPConnections", 32, true, "repository.metadataSAPConnections.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SQLPATTERNS = new ERepositoryObjectType(
            "repository.metadataSQLPatterns", 33, true, "repository.metadataSQLPatterns.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_EBCDIC = new ERepositoryObjectType(
            "repository.metadataFileEDCDIC", 34, true, "repository.metadataFileEDCDIC.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_HL7 = new ERepositoryObjectType(
            "repository.metadataFileHL7", 35, true, "repository.metadataFileHL7.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_FTP = new ERepositoryObjectType(
            "repository.metadataFileFTP", 36, true, "repository.metadataFileFTP.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    // 0015169 added
    public final static ERepositoryObjectType METADATA_FILE_BRMS = new ERepositoryObjectType(
            "repository.metadataFileBRMS", 37, true, "repository.metadataFileBRMS.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_MDMCONNECTION = new ERepositoryObjectType(
            "repository.metadataMDMConnections", 38, true, "repository.metadataMDMConnections.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_DELIMITED = new ERepositoryObjectType(
            "repository.metadataFileDelimited", 39, true, "repository.metadataFileDelimited.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_POSITIONAL = new ERepositoryObjectType(
            "repository.metadataFilePositional", 40, true, "repository.metadataFilePositional.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_REGEXP = new ERepositoryObjectType(
            "repository.metadataFileRegexp", 41, true, "repository.metadataFileRegexp.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_XML = new ERepositoryObjectType(
            "repository.metadataFileXml", 42, true, "repository.metadataFileXml.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_LDIF = new ERepositoryObjectType(
            "repository.metadataFileLdif", 43, true, "repository.metadataFileLdif.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_FILE_EXCEL = new ERepositoryObjectType(
            "repository.metadataFileExcel", 44, true, "repository.metadataFileExcel.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_SALESFORCE_SCHEMA = new ERepositoryObjectType(
            "repository.metadataSalesforceSchema", 45, true, "repository.metadataSalesforceSchema.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_GENERIC_SCHEMA = new ERepositoryObjectType(
            "repository.metadataGenericSchema", 46, true, "repository.metadataGenericSchema.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_LDAP_SCHEMA = new ERepositoryObjectType(
            "repository.metadataLDAPSchema", 47, true, "repository.metadataLDAPSchema.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_VALIDATION_RULES = new ERepositoryObjectType(
            "repository.metadataValidationRules", 48, true, "repository.metadataValidationRules.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType METADATA_VALIDATIONS_RULES_FOLDER = new ERepositoryObjectType(
            "repository.metadataValidationRulesFolder", 49, true, "repository.metadataValidationRulesFolder.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType FOLDER = new ERepositoryObjectType("repository.folder", true, 50); //$NON-NLS-1$

    public final static ERepositoryObjectType REFERENCED_PROJECTS = new ERepositoryObjectType(
            "repository.referencedProjects", 51, true, "repository.referencedProjects.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType GENERATED = new ERepositoryObjectType("repository.generated", true, 52); //$NON-NLS-1$

    public final static ERepositoryObjectType JOBS = new ERepositoryObjectType("repository.jobs", true, 53); //$NON-NLS-1$

    public final static ERepositoryObjectType JOB_DOC = new ERepositoryObjectType("repository.jobdoc", true, 54); //$NON-NLS-1$

    public final static ERepositoryObjectType JOBLET = new ERepositoryObjectType("repository.joblet", true, 55); //$NON-NLS-1$

    public final static ERepositoryObjectType LIBS = new ERepositoryObjectType("repository.libs", true, 56); //$NON-NLS-1$

    public final static ERepositoryObjectType METADATA_WSDL_SCHEMA = new ERepositoryObjectType(
            "repository.metadataWSDLSchema", 57, true, "repository.metadataWSDLSchema.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType JOBLETS = new ERepositoryObjectType("repository.joblets", true, 58); //$NON-NLS-1$

    public final static ERepositoryObjectType JOBLET_DOC = new ERepositoryObjectType("repository.jobletdoc", true, 59); //$NON-NLS-1$

    public final static ERepositoryObjectType METADATA_HEADER_FOOTER = new ERepositoryObjectType(
            "repository.headerFooterConnections", 60, true, "repository.headerFooterConnections.alias"); //$NON-NLS-1$

    public final static ERepositoryObjectType COMPONENTS = new ERepositoryObjectType("repository.components", true, 61); //$NON-NLS-1$

    // MOD mzhao feature 9207
    public final static ERepositoryObjectType TDQ_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement", 62, true, "repository.tdqelement.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    // MOD mzhao feature 13114, 2010-05-19
    public final static ERepositoryObjectType TDQ_ANALYSIS_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.analysis", 63, true, "repository.tdqelement.analysis.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_REPORT_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.report", 64, true, "repository.tdqelement.report.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_BUSINESSRULE_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.businessrule", 65, true, "repository.tdqelement.businessrule.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_INDICATOR_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.indicator", 66, true, "repository.tdqelement.indicator.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_PATTERN_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.pattern", 67, true, "repository.tdqelement.pattern.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_SOURCE_FILE_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.sourceFile", 68, true, "repository.tdqelement.sourceFile.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    // MOD zqin feature 14507
    public final static ERepositoryObjectType TDQ_JRAXML_ELEMENT = new ERepositoryObjectType(
            "repository.tdqelement.jrxml", 69, true, "repository.tdqelement.jrxml.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_FOLDER_NODE = new ERepositoryObjectType(
            "repository.tdqelement.folderNode", 70, true, "repository.tdqelement.folderNode.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    // MOD klliu feature 15750
    public final static ERepositoryObjectType TDQ_DATA_PROFILING = new ERepositoryObjectType(
            "repository.dataprofiling", 71, true, "repository.dataprofiling.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_ANALYSIS = new ERepositoryObjectType(
            "repository.analysis", 72, true, "repository.analysis.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_REPORTS = new ERepositoryObjectType(
            "repository.reports", 73, true, "repository.reports.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_LIBRARIES = new ERepositoryObjectType(
            "repository.libraries", 74, true, "repository.libraries.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_PATTERNS = new ERepositoryObjectType(
            "repository.patterns", 75, true, "repository.patterns.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_PATTERN_REGEX = new ERepositoryObjectType(
            "repository.patternRegex", 76, true, "repository.patternRegex.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_PATTERN_SQL = new ERepositoryObjectType(
            "repository.patternSql", 77, true, "repository.patternSql.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_SOURCE_FILES = new ERepositoryObjectType(
            "repository.sourceFile", 78, true, "repository.sourceFile.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_RULES = new ERepositoryObjectType(
            "repository.rules", 79, true, "repository.rules.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_RULES_SQL = new ERepositoryObjectType(
            "repository.rulesSql", 80, true, "repository.rulesSql.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_JRXMLTEMPLATE = new ERepositoryObjectType(
            "repository.jrxmlTemplate", 81, true, "repository.jrxmlTemplate.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_INDICATORS = new ERepositoryObjectType(
            "repository.indicators", 82, true, "repository.indicators.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    // MOD klliu 2010-11-26 definition type
    public final static ERepositoryObjectType TDQ_SYSTEM_INDICATORS = new ERepositoryObjectType(
            "repository.systemIndicators", 83, true, "repository.systemIndicators.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_USERDEFINE_INDICATORS = new ERepositoryObjectType(
            "repository.userDefineIndicators", 84, true, "repository.userDefineIndicators.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_USERDEFINE_INDICATORS_LIB = new ERepositoryObjectType(
            "repository.userDefineIndicators.lib", 85, true, "repository.userDefineIndicators.lib.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_ADVANCED_STATISTICS = new ERepositoryObjectType(
            "repository.systemIndicators.advancedStatistics", 86, true, "repository.systemIndicators.advancedStatistics.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_BUSINESS_RULES = new ERepositoryObjectType(
            "repository.systemIndicators.businessRules", 87, true, "repository.systemIndicators.businessRules.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_CORRELATION = new ERepositoryObjectType(
            "repository.systemIndicators.correlation", 88, true, "repository.systemIndicators.correlation.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_FUNCTIONAL_DEPENDENCY = new ERepositoryObjectType(
            "repository.systemIndicators.functionalDependency", 89, true, "repository.systemIndicators.functionalDependency.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_OVERVIEW = new ERepositoryObjectType(
            "repository.systemIndicators.overview, repository.systemIndicators.overview.alias", true, 90); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_PATTERN_FINDER = new ERepositoryObjectType(
            "repository.systemIndicators.patternFinder", 91, true, "repository.systemIndicators.patternFinder.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_PATTERN_MATCHING = new ERepositoryObjectType(
            "repository.systemIndicators.patternMatching", 92, true, "repository.systemIndicators.patternMatching.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_ROW_COMPARISON = new ERepositoryObjectType(
            "repository.systemIndicators.rowComparison", 93, true, "repository.systemIndicators.rowComparison.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_SIMPLE_STATISTICS = new ERepositoryObjectType(
            "repository.systemIndicators.simpleStatistics", 94, true, "repository.systemIndicators.simpleStatistics.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_SOUNDEX = new ERepositoryObjectType(
            "repository.systemIndicators.soundex", 95, true, "repository.systemIndicators.soundex.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_SUMMARY_STATISTICS = new ERepositoryObjectType(
            "repository.systemIndicators.summaryStatistics", 96, true, "repository.systemIndicators.summaryStatistics.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType SYSTEM_INDICATORS_TEXT_STATISTICS = new ERepositoryObjectType(
            "repository.systemIndicators.textStatistics", 97, true, "repository.systemIndicators.textStatistics.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    public final static ERepositoryObjectType TDQ_EXCHANGE = new ERepositoryObjectType(
            "repository.tdqExchange", 98, true, "repository.tdqExchange.alias"); //$NON-NLS-1$ //$NON-NLS-2$

    private String alias;

    private boolean subItem;

    static {

        try {
            initDynamicNodes(ERepositoryObjectType.class);
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    ERepositoryObjectType(String key, boolean isStaticNode, int ordinal) {
        this(key, ordinal, isStaticNode, false);
    }

    ERepositoryObjectType(String key, int ordinal, boolean isStaticNode, boolean subItem) {
        super(key, isStaticNode, ordinal);
        this.subItem = subItem;
    }

    ERepositoryObjectType(String key, int ordinal, boolean isStaticNode, String alias) {
        this(key, isStaticNode, ordinal);
        this.alias = alias;
    }

    public static <E> DynaEnum<? extends DynaEnum<?>>[] values() {
        return values(ERepositoryObjectType.class);
    }

    /* if node from extension point,add the new RepositoryObjectType dynamiclly by using the java reflection */
    private static <E> void initDynamicNodes(Class<E> clazz) throws Exception {
        // String rcName = clazz.getName().replace('.', '/') + ".properties";
        // BufferedReader reader = new BufferedReader(new
        // InputStreamReader(Thread.currentThread().getContextClassLoader()
        // .getResourceAsStream(rcName)));

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] configurationElements = registry
                .getConfigurationElementsFor("org.talend.core.repository.repository_node_provider"); //$NON-NLS-1$
        try {
            for (int i = 0; i < configurationElements.length; i++) {
                IConfigurationElement element = configurationElements[i];
                Object extensionNode = element.createExecutableExtension("class");
                if (extensionNode instanceof IExtendRepositoryNode) {
                    IExtendRepositoryNode diyNode = (IExtendRepositoryNode) extensionNode;
                    String type = diyNode.getNodeType();
                    int ordinal = diyNode.getOrdinal();
                    Constructor<E> minimalConstructor = getConstructor(clazz, new Class[] { String.class, boolean.class,
                            int.class });
                    minimalConstructor.newInstance(type, false, ordinal);

                }
            }
        } catch (CoreException e) {
            ExceptionHandler.process(e);
        }
        /* this constructor is used to create one system_folder node */
        // Constructor<E> additionalConstructor = getConstructor(clazz, new Class[] { String.class, int.class,
        // String.class });
        // int ordinal = 0;
        // for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        // line = line.replaceFirst("#.*", "").trim();
        // if (line.equals("")) {
        // continue;
        // }
        // String[] parts = line.split("\\s*=\\s*");
        // if (parts.length == 1 || additionalConstructor == null) {
        // minimalConstructor.newInstance(parts[0], ordinal);
        // } else {
        // additionalConstructor.newInstance(parts[0], ordinal, parts[1]);
        // }
        // }
    }

    @SuppressWarnings("unchecked")
    private static <E> Constructor<E> getConstructor(Class<E> clazz, Class<?>[] argTypes) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            try {
                return (Constructor<E>) c.getDeclaredConstructor(argTypes);
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    public String getAlias() {
        if (alias == null) {
            return null;
        }
        return Messages.getString(alias);
    }

    public static ERepositoryObjectType getTypeFromKey(String key) {
        for (ERepositoryObjectType type : (ERepositoryObjectType[]) values()) {
            if (type.getKey().equals(key)) {
                return type;
            }
        }
        return null;
    }

    public boolean hasFolder() {
        try {
            String folderName = getFolderName(this);
            if (folderName != null && !"".equals(folderName)) { //$NON-NLS-1$
                return true;
            }
        } catch (IllegalArgumentException e) { // not found
            // nothing to do
        }
        return false;
    }

    public static String getFolderName(ERepositoryObjectType type) {
        if (type == BUSINESS_PROCESS) {
            return "businessModel"; //$NON-NLS-1$
        } else if (type == SVG_BUSINESS_PROCESS) {
            return "businessProcessSVG"; //$NON-NLS-1$
        } else if (type == PROCESS) {
            return "process"; //$NON-NLS-1$
        } else if (type == ROUTES) {
            return "routes";
        } else if (type == JOBLET) {
            return "joblets"; //$NON-NLS-1$
        } else if (type == LIBS) {
            return "libs";
        } else if (type == CONTEXT) {
            return "context"; //$NON-NLS-1$
        } else if (type == ROUTINES) {
            return "code/routines"; //$NON-NLS-1$
        } else if (type == BEANS) {
            return "code/beans"; //$NON-NLS-1$  
        } else if (type == JOB_SCRIPT) {
            return "code/jobscripts"; //$NON-NLS-1$
        } else if (type == SNIPPETS) {
            return "code/snippets"; //$NON-NLS-1$
        } else if (type == DOCUMENTATION) {
            return "documentations"; //$NON-NLS-1$
        } else if (type == SQLPATTERNS) {
            return "sqlPatterns"; //$NON-NLS-1$
        } else if (type == METADATA) {
            return "metadata"; //$NON-NLS-1$
        } else if (type == METADATA_CONNECTIONS) {
            return "metadata/connections"; //$NON-NLS-1$
        } else if (type == METADATA_SAPCONNECTIONS) {
            return "metadata/sapconnections"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_EBCDIC) {
            return "metadata/fileEBCDIC"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_HL7) {
            return "metadata/fileHL7"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_FTP) {
            return "metadata/FTPconnections";
        } else if (type == METADATA_FILE_BRMS) {
            return "metadata/BRMSconnections";
        } else if (type == METADATA_MDMCONNECTION) {
            return "metadata/MDMconnections";
        } else if (type == METADATA_FILE_DELIMITED) {
            return "metadata/fileDelimited";
        } else if (type == METADATA_FILE_POSITIONAL) {
            return "metadata/filePositional";
        } else if (type == METADATA_FILE_REGEXP) {
            return "metadata/fileRegex"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_XML) {
            return "metadata/fileXml"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_EXCEL) {
            return "metadata/fileExcel"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_LDIF) {
            return "metadata/fileLdif"; //$NON-NLS-1$
        } else if (type == METADATA_LDAP_SCHEMA) {
            return "metadata/LDAPSchema"; //$NON-NLS-1$
        } else if (type == METADATA_GENERIC_SCHEMA) {
            return "metadata/genericSchema"; //$NON-NLS-1$
        } else if (type == METADATA_WSDL_SCHEMA) {
            return "metadata/WSDLSchema"; //$NON-NLS-1$
        } else if (type == METADATA_SALESFORCE_SCHEMA) {
            return "metadata/SalesforceSchema"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_RULES) {
            return "metadata/rules"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_LINKRULES) {
            return "metadata/rules"; //$NON-NLS-1$
        } else if (type == METADATA_VALIDATION_RULES) {
            return "metadata/validationRules";
        } else if (type == TDQ_DATA_PROFILING) {
            return "TDQ_Data Profiling"; //$NON-NLS-1$
        } else if (type == TDQ_ANALYSIS) {
            return "TDQ_Data Profiling/Analyses";
        } else if (type == TDQ_REPORTS) {
            return "TDQ_Data Profiling/Reports"; //$NON-NLS-1$
        } else if (type == TDQ_LIBRARIES) {
            return "TDQ_Libraries"; //$NON-NLS-1$
        } else if (type == TDQ_EXCHANGE) {
            return "TDQ_Libraries/Exchange"; //$NON-NLS-1$
        } else if (type == TDQ_INDICATORS) {
            return "TDQ_Libraries/Indicators";
        } else if (type == TDQ_JRXMLTEMPLATE) {
            return "TDQ_Libraries/JRXML Template";
        } else if (type == TDQ_RULES) {
            return "TDQ_Libraries/Rules"; //$NON-NLS-1$
        } else if (type == TDQ_RULES_SQL) {
            return "TDQ_Libraries/Rules/SQL";
        } else if (type == TDQ_PATTERNS) {
            return "TDQ_Libraries/Patterns";
        } else if (type == TDQ_PATTERN_REGEX) {
            return "TDQ_Libraries/Patterns/Regex";
        } else if (type == TDQ_PATTERN_SQL) {
            return "TDQ_Libraries/Patterns/SQL";
        } else if (type == TDQ_SOURCE_FILES) {
            return "TDQ_Libraries/Source Files";
        } else if (type == TDQ_SYSTEM_INDICATORS) {
            return "TDQ_Libraries/Indicators/System Indicators"; //$NON-NLS-1$
        } else if (type == TDQ_USERDEFINE_INDICATORS) {
            return "TDQ_Libraries/Indicators/User Defined Indicators";
        } else if (type == SYSTEM_INDICATORS_ADVANCED_STATISTICS) {
            return "TDQ_Libraries/Indicators/System Indicators/Advanced Statistics"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_BUSINESS_RULES) {
            return "TDQ_Libraries/Indicators/System Indicators/Business Rules";
        } else if (type == SYSTEM_INDICATORS_CORRELATION) {
            return "TDQ_Libraries/Indicators/System Indicators/Correlation"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_FUNCTIONAL_DEPENDENCY) {
            return "TDQ_Libraries/Indicators/System Indicators/Functional Dependency"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_OVERVIEW) {
            return "TDQ_Libraries/Indicators/System Indicators/Overview"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_PATTERN_FINDER) {
            return "TDQ_Libraries/Indicators/System Indicators/Pattern Finder"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_PATTERN_MATCHING) {
            return "TDQ_Libraries/Indicators/System Indicators/Pattern Matching"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_ROW_COMPARISON) {
            return "TDQ_Libraries/Indicators/System Indicators/Row Comparison"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_SIMPLE_STATISTICS) {
            return "TDQ_Libraries/Indicators/System Indicators/Simple Statistics"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_SOUNDEX) {
            return "TDQ_Libraries/Indicators/System Indicators/Soundex"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_SUMMARY_STATISTICS) {
            return "TDQ_Libraries/Indicators/System Indicators/Summary Statistics"; //$NON-NLS-1$
        } else if (type == SYSTEM_INDICATORS_TEXT_STATISTICS) {
            return "TDQ_Libraries/Indicators/System Indicators/Text Statistics"; //$NON-NLS-1$
        } else if (type == METADATA_HEADER_FOOTER) {
            return "metadata/header_footer"; //$NON-NLS-1$
        } else if (type == TDQ_ANALYSIS_ELEMENT) {
            return "TDQ_Data Profiling/Analyses"; //$NON-NLS-1$
        } else if (type == TDQ_BUSINESSRULE_ELEMENT) {
            return "TDQ_Libraries/Rules/SQL"; //$NON-NLS-1$
        } else if (type == TDQ_INDICATOR_ELEMENT) {
            return "TDQ_Libraries/Indicators"; //$NON-NLS-1$
        } else if (type == TDQ_PATTERN_ELEMENT) {
            return "TDQ_Libraries/Patterns"; //$NON-NLS-1$ 
        } else if (type == TDQ_JRAXML_ELEMENT) {
            return "TDQ_Libraries/JRXML Template"; //$NON-NLS-1$
        } else if (type == TDQ_REPORT_ELEMENT) {
            return "TDQ_Data Profiling/Reports"; //$NON-NLS-1$
        } else if (type == TDQ_SOURCE_FILE_ELEMENT) {
            return "TDQ_Libraries/Source Files"; //$NON-NLS-1$
        } else if (type == TDQ_ELEMENT) {
            return "";//$NON-NLS-1$
        } else if (type == COMPONENTS) {
            return "components";
        } else {
            if (PluginChecker.isDocumentationPluginLoaded()) {
                if (type == GENERATED) {
                    return "documentations/generated"; //$NON-NLS-1$
                }
                if (type == JOBS) {
                    return "documentations/generated/jobs"; //$NON-NLS-1$
                }
                if (type == JOB_DOC) {
                    return "documentations/generated/jobs"; //$NON-NLS-1$
                }
                if (PluginChecker.isJobLetPluginLoaded() && type == JOBLETS) {
                    return "documentations/generated/joblets"; //$NON-NLS-1$
                }
                if (PluginChecker.isJobLetPluginLoaded() && type == JOBLET_DOC) {
                    return "documentations/generated/joblets"; //$NON-NLS-1$
                }
            }
            throw new IllegalArgumentException(Messages.getString("ERepositoryObjectType.FolderNotFound", type)); //$NON-NLS-1$ //$NON-NLS-2$
        }
        // switch (type) {
        // case BUSINESS_PROCESS:
        //            return "businessProcess"; //$NON-NLS-1$
        // case SVG_BUSINESS_PROCESS:
        //            return "businessProcessSVG"; //$NON-NLS-1$
        // case PROCESS:
        //            return "process"; //$NON-NLS-1$
        // case ROUTES:
        // return "routes";
        // case JOBLET:
        //            return "joblets"; //$NON-NLS-1$
        // case LIBS:
        // return "libs";
        // case CONTEXT:
        //            return "context"; //$NON-NLS-1$
        // case ROUTINES:
        //            return "code/routines"; //$NON-NLS-1$
        // case BEAN:
        //            return "code/bean"; //$NON-NLS-1$    
        // case JOB_SCRIPT:
        //            return "code/jobscripts"; //$NON-NLS-1$
        // case SNIPPETS:
        //            return "code/snippets"; //$NON-NLS-1$
        // case DOCUMENTATION:
        //            return "documentations"; //$NON-NLS-1$
        // case SQLPATTERNS:
        //            return "sqlPatterns"; //$NON-NLS-1$
        // case METADATA:
        //            return "metadata"; //$NON-NLS-1$
        // case METADATA_CONNECTIONS:
        //            return "metadata/connections"; //$NON-NLS-1$
        // case METADATA_SAPCONNECTIONS:
        //            return "metadata/sapconnections"; //$NON-NLS-1$
        // case METADATA_FILE_EBCDIC:
        //            return "metadata/fileEBCDIC"; //$NON-NLS-1$
        // case METADATA_FILE_HL7:
        //            return "metadata/fileHL7"; //$NON-NLS-1$
        // case METADATA_FILE_FTP:
        //            return "metadata/FTPconnections"; //$NON-NLS-1$
        // case METADATA_FILE_BRMS:
        //            return "metadata/BRMSconnections"; //$NON-NLS-1$
        // case METADATA_MDMCONNECTION:
        //            return "metadata/MDMconnections"; //$NON-NLS-1$
        // case METADATA_FILE_DELIMITED:
        //            return "metadata/fileDelimited"; //$NON-NLS-1$
        // case METADATA_FILE_POSITIONAL:
        //            return "metadata/filePositional"; //$NON-NLS-1$
        // case METADATA_FILE_REGEXP:
        //            return "metadata/fileRegex"; //$NON-NLS-1$
        // case METADATA_FILE_XML:
        //            return "metadata/fileXml"; //$NON-NLS-1$
        // case METADATA_FILE_EXCEL:
        //            return "metadata/fileExcel"; //$NON-NLS-1$
        // case METADATA_FILE_LDIF:
        //            return "metadata/fileLdif"; //$NON-NLS-1$
        // case METADATA_LDAP_SCHEMA:
        //            return "metadata/LDAPSchema"; //$NON-NLS-1$
        // case METADATA_GENERIC_SCHEMA:
        //            return "metadata/genericSchema"; //$NON-NLS-1$
        // case METADATA_WSDL_SCHEMA:
        //            return "metadata/WSDLSchema"; //$NON-NLS-1$
        // case METADATA_SALESFORCE_SCHEMA:
        //            return "metadata/SalesforceSchema"; //$NON-NLS-1$
        // case METADATA_FILE_RULES:
        //            return "metadata/rules"; //$NON-NLS-1$
        // case METADATA_FILE_LINKRULES:
        //            return "metadata/rules"; //$NON-NLS-1$
        // // MOD klliu feature 15750,2010-11-18
        // case METADATA_VALIDATION_RULES:
        //            return "metadata/validationRules"; //$NON-NLS-1$
        // case TDQ_DATA_PROFILING:
        //            return "TDQ_Data Profiling"; //$NON-NLS-1$
        // case TDQ_ANALYSIS:
        //            return "TDQ_Data Profiling/Analyses"; //$NON-NLS-1$
        // case TDQ_REPORTS:
        //            return "TDQ_Data Profiling/Reports"; //$NON-NLS-1$
        // case TDQ_LIBRARIES:
        //            return "TDQ_Libraries"; //$NON-NLS-1$
        // case TDQ_EXCHANGE:
        //            return "TDQ_Libraries/Exchange"; //$NON-NLS-1$
        // case TDQ_INDICATORS:
        //            return "TDQ_Libraries/Indicators"; //$NON-NLS-1$
        // case TDQ_JRXMLTEMPLATE:
        //            return "TDQ_Libraries/JRXML Template"; //$NON-NLS-1$
        // case TDQ_RULES:
        //            return "TDQ_Libraries/Rules"; //$NON-NLS-1$
        // case TDQ_RULES_SQL:
        //            return "TDQ_Libraries/Rules/SQL"; //$NON-NLS-1$
        // case TDQ_PATTERNS:
        //            return "TDQ_Libraries/Patterns"; //$NON-NLS-1$
        // case TDQ_PATTERN_REGEX:
        //            return "TDQ_Libraries/Patterns/Regex"; //$NON-NLS-1$
        // case TDQ_PATTERN_SQL:
        //            return "TDQ_Libraries/Patterns/SQL"; //$NON-NLS-1$
        // case TDQ_SOURCE_FILES:
        //            return "TDQ_Libraries/Source Files"; //$NON-NLS-1$
        // case TDQ_SYSTEM_INDICATORS:
        //            return "TDQ_Libraries/Indicators/System Indicators"; //$NON-NLS-1$
        // case TDQ_USERDEFINE_INDICATORS:
        //            return "TDQ_Libraries/Indicators/User Defined Indicators"; //$NON-NLS-1$
        // // MOD klliu 2010-11-26 definition type
        // case SYSTEM_INDICATORS_ADVANCED_STATISTICS:
        //            return "TDQ_Libraries/Indicators/System Indicators/Advanced Statistics"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_BUSINESS_RULES:
        //            return "TDQ_Libraries/Indicators/System Indicators/Business Rules"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_CORRELATION:
        //            return "TDQ_Libraries/Indicators/System Indicators/Correlation"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_FUNCTIONAL_DEPENDENCY:
        //            return "TDQ_Libraries/Indicators/System Indicators/Functional Dependency"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_OVERVIEW:
        //            return "TDQ_Libraries/Indicators/System Indicators/Overview"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_PATTERN_FINDER:
        //            return "TDQ_Libraries/Indicators/System Indicators/Pattern Finder"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_PATTERN_MATCHING:
        //            return "TDQ_Libraries/Indicators/System Indicators/Pattern Matching"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_ROW_COMPARISON:
        //            return "TDQ_Libraries/Indicators/System Indicators/Row Comparison"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_SIMPLE_STATISTICS:
        //            return "TDQ_Libraries/Indicators/System Indicators/Simple Statistics"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_SOUNDEX:
        //            return "TDQ_Libraries/Indicators/System Indicators/Soundex"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_SUMMARY_STATISTICS:
        //            return "TDQ_Libraries/Indicators/System Indicators/Summary Statistics"; //$NON-NLS-1$
        // case SYSTEM_INDICATORS_TEXT_STATISTICS:
        //            return "TDQ_Libraries/Indicators/System Indicators/Text Statistics"; //$NON-NLS-1$
        // case METADATA_HEADER_FOOTER:
        //            return "metadata/header_footer"; //$NON-NLS-1$
        // case TDQ_ANALYSIS_ELEMENT:
        //            return "TDQ_Data Profiling/Analyses"; //$NON-NLS-1$
        // case TDQ_BUSINESSRULE_ELEMENT:
        //            return "TDQ_Libraries/Rules/SQL"; //$NON-NLS-1$
        // case TDQ_INDICATOR_ELEMENT:
        //            return "TDQ_Libraries/Indicators"; //$NON-NLS-1$
        // case TDQ_PATTERN_ELEMENT:
        //            return "TDQ_Libraries/Patterns"; //$NON-NLS-1$ 
        // case TDQ_JRAXML_ELEMENT:
        //            return "TDQ_Libraries/JRXML Template"; //$NON-NLS-1$
        // case TDQ_REPORT_ELEMENT:
        //            return "TDQ_Data Profiling/Reports"; //$NON-NLS-1$
        // case TDQ_SOURCE_FILE_ELEMENT:
        //            return "TDQ_Libraries/Source Files"; //$NON-NLS-1$
        // // MOD mzhao feature 9207
        // case TDQ_ELEMENT:
        //            return "";//$NON-NLS-1$
        // case COMPONENTS:
        // return "components";
        // default:
        // if (PluginChecker.isDocumentationPluginLoaded()) {
        // if (type == GENERATED) {
        //                    return "documentations/generated"; //$NON-NLS-1$
        // }
        // if (type == JOBS) {
        //                    return "documentations/generated/jobs"; //$NON-NLS-1$
        // }
        // if (type == JOB_DOC) {
        //                    return "documentations/generated/jobs"; //$NON-NLS-1$
        // }
        // if (PluginChecker.isJobLetPluginLoaded() && type == JOBLETS) {
        //                    return "documentations/generated/joblets"; //$NON-NLS-1$
        // }
        // if (PluginChecker.isJobLetPluginLoaded() && type == JOBLET_DOC) {
        //                    return "documentations/generated/joblets"; //$NON-NLS-1$
        // }
        // }
        //
        //            throw new IllegalArgumentException(Messages.getString("ERepositoryObjectType.FolderNotFound", type)); //$NON-NLS-1$ //$NON-NLS-2$
        // }
    }

    public static String getDeleteFolderName(ERepositoryObjectType type) {
        if (type == BUSINESS_PROCESS) {
            return "businessModel"; //$NON-NLS-1$
        } else if (type == SVG_BUSINESS_PROCESS) {
            return "businessProcessSVG"; //$NON-NLS-1$
        } else if (type == PROCESS) {
            return "job"; //$NON-NLS-1$
        } else if (type == JOBLET) {
            return "joblet";
        } else if (type == CONTEXT) {
            return "context"; //$NON-NLS-1$
        } else if (type == ROUTINES) {
            return "routine"; //$NON-NLS-1$
        } else if (type == JOB_SCRIPT) {
            return "jobscript"; //$NON-NLS-1$
        } else if (type == SNIPPETS) {
            return "snippet"; //$NON-NLS-1$
        } else if (type == DOCUMENTATION) {
            return "documentation"; //$NON-NLS-1$
        } else if (type == SQLPATTERNS) {
            return "sqlPattern"; //$NON-NLS-1$
        } else if (type == METADATA) {
            return "metadata"; //$NON-NLS-1$
        } else if (type == METADATA_CONNECTIONS) {
            return "DB connection"; //$NON-NLS-1$
        } else if (type == METADATA_SAPCONNECTIONS) {
            return "SAPconnection"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_EBCDIC) {
            return "fileEBCDIC"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_HL7) {
            return "fileHL7"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_FTP) {
            return "FTPconnection"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_BRMS) {
            return "BRMSconnection"; //$NON-NLS-1$
        } else if (type == METADATA_MDMCONNECTION) {
            return "MDMconnection"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_DELIMITED) {
            return "fileDelimited"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_POSITIONAL) {
            return "filePositional"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_REGEXP) {
            return "fileRegex"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_XML) {
            return "fileXml"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_EXCEL) {
            return "fileExcel"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_LDIF) {
            return "fileLdif"; //$NON-NLS-1$
        } else if (type == METADATA_LDAP_SCHEMA) {
            return "LDAPSchema"; //$NON-NLS-1$
        } else if (type == METADATA_GENERIC_SCHEMA) {
            return "genericSchema"; //$NON-NLS-1$
        } else if (type == METADATA_WSDL_SCHEMA) {
            return "WSDLSchema"; //$NON-NLS-1$
        } else if (type == METADATA_SALESFORCE_SCHEMA) {
            return "SalesforceSchema"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_RULES) {
            return "rules"; //$NON-NLS-1$
        } else if (type == METADATA_FILE_LINKRULES) {
            return "rules"; //$NON-NLS-1$
        } else if (type == METADATA_HEADER_FOOTER) {
            return "header_footer";
        } else if (type == TDQ_ANALYSIS_ELEMENT) {
            return "Analyses"; //$NON-NLS-1$
        } else if (type == TDQ_BUSINESSRULE_ELEMENT) {
            return "Rules"; //$NON-NLS-1$
        } else if (type == TDQ_INDICATOR_ELEMENT) {
            return "Indicators"; //$NON-NLS-1$
        } else if (type == TDQ_PATTERN_ELEMENT) {
            return "Patterns"; //$NON-NLS-1$ 
        } else if (type == TDQ_JRAXML_ELEMENT) {
            return "JRXML Template";
        } else if (type == TDQ_REPORT_ELEMENT) {
            return "Reports"; //$NON-NLS-1$
        } else if (type == TDQ_ELEMENT) {
            return "";//$NON-NLS-1$
        } else if (type == COMPONENTS) {
            return "components";
        } else {
            return "job";
        }
        // switch (type) {
        // case BUSINESS_PROCESS:
        //            return "businessModel"; //$NON-NLS-1$
        // case SVG_BUSINESS_PROCESS:
        //            return "businessProcessSVG"; //$NON-NLS-1$
        // case PROCESS:
        //            return "job"; //$NON-NLS-1$
        // case JOBLET:
        // return "joblet";
        // case CONTEXT:
        //            return "context"; //$NON-NLS-1$
        // case ROUTINES:
        //            return "routine"; //$NON-NLS-1$
        // case JOB_SCRIPT:
        //            return "jobscript"; //$NON-NLS-1$
        // case SNIPPETS:
        //            return "snippet"; //$NON-NLS-1$
        // case DOCUMENTATION:
        //            return "documentation"; //$NON-NLS-1$
        // case SQLPATTERNS:
        //            return "sqlPattern"; //$NON-NLS-1$
        // case METADATA:
        //            return "metadata"; //$NON-NLS-1$
        // case METADATA_CONNECTIONS:
        //            return "DB connection"; //$NON-NLS-1$
        // case METADATA_SAPCONNECTIONS:
        //            return "SAPconnection"; //$NON-NLS-1$
        // case METADATA_FILE_EBCDIC:
        //            return "fileEBCDIC"; //$NON-NLS-1$
        // case METADATA_FILE_HL7:
        //            return "fileHL7"; //$NON-NLS-1$
        // case METADATA_FILE_FTP:
        //            return "FTPconnection"; //$NON-NLS-1$
        // case METADATA_FILE_BRMS:
        //            return "BRMSconnection"; //$NON-NLS-1$
        // case METADATA_MDMCONNECTION:
        //            return "MDMconnection"; //$NON-NLS-1$
        // case METADATA_FILE_DELIMITED:
        //            return "fileDelimited"; //$NON-NLS-1$
        // case METADATA_FILE_POSITIONAL:
        //            return "filePositional"; //$NON-NLS-1$
        // case METADATA_FILE_REGEXP:
        //            return "fileRegex"; //$NON-NLS-1$
        // case METADATA_FILE_XML:
        //            return "fileXml"; //$NON-NLS-1$
        // case METADATA_FILE_EXCEL:
        //            return "fileExcel"; //$NON-NLS-1$
        // case METADATA_FILE_LDIF:
        //            return "fileLdif"; //$NON-NLS-1$
        // case METADATA_LDAP_SCHEMA:
        //            return "LDAPSchema"; //$NON-NLS-1$
        // case METADATA_GENERIC_SCHEMA:
        //            return "genericSchema"; //$NON-NLS-1$
        // case METADATA_WSDL_SCHEMA:
        //            return "WSDLSchema"; //$NON-NLS-1$
        // case METADATA_SALESFORCE_SCHEMA:
        //            return "SalesforceSchema"; //$NON-NLS-1$
        // case METADATA_FILE_RULES:
        //            return "rules"; //$NON-NLS-1$
        // case METADATA_FILE_LINKRULES:
        //            return "rules"; //$NON-NLS-1$
        // // MOD mzhao feature 13114, 2010-05-19
        // case METADATA_HEADER_FOOTER:
        // return "header_footer";
        // case TDQ_ANALYSIS_ELEMENT:
        //            return "Analyses"; //$NON-NLS-1$
        // case TDQ_BUSINESSRULE_ELEMENT:
        //            return "Rules"; //$NON-NLS-1$
        // case TDQ_INDICATOR_ELEMENT:
        //            return "Indicators"; //$NON-NLS-1$
        // case TDQ_PATTERN_ELEMENT:
        //            return "Patterns"; //$NON-NLS-1$ 
        // case TDQ_JRAXML_ELEMENT:
        // return "JRXML Template";
        // case TDQ_REPORT_ELEMENT:
        //            return "Reports"; //$NON-NLS-1$
        // // MOD mzhao feature 9207
        // case TDQ_ELEMENT:
        //            return "";//$NON-NLS-1$
        // case COMPONENTS:
        // return "components";
        // default:
        // return "job";
        // }
    }

    public static ERepositoryObjectType getItemType(Item item) {

        ERepositoryObjectType repObjType = getTDQRepObjType(item);
        if (repObjType != null) {
            return repObjType;
        }
        return (ERepositoryObjectType) new PropertiesSwitch() {

            @Override
            public Object caseFolderItem(FolderItem object) {
                return FOLDER;
            }

            public Object caseDocumentationItem(DocumentationItem object) {
                return DOCUMENTATION;
            }

            @Override
            public Object caseLinkDocumentationItem(LinkDocumentationItem object) {
                return DOCUMENTATION;
            }

            @Override
            public Object caseRulesItem(RulesItem object) {
                return METADATA_FILE_RULES;
            }

            @Override
            public Object caseLinkRulesItem(LinkRulesItem object) {
                return METADATA_FILE_LINKRULES;
            }

            /*
             * (non-Javadoc)
             * 
             * @seeorg.talend.core.model.properties.util.PropertiesSwitch# caseJobDocumentationItem
             * (org.talend.core.model.properties.JobDocumentationItem)
             */
            public Object caseJobDocumentationItem(JobDocumentationItem object) {
                return JOB_DOC;
            }

            /*
             * (non-Javadoc)
             * 
             * @seeorg.talend.core.model.properties.util.PropertiesSwitch# caseJobletDocumentationItem
             * (org.talend.core.model.properties.JobletDocumentationItem)
             */
            public Object caseJobletDocumentationItem(JobletDocumentationItem object) {
                return JOBLET_DOC;
            }

            public Object caseRoutineItem(RoutineItem object) {
                return ROUTINES;
            }

            public Object caseBeanItem(BeanItem object) {
                return BEANS;
            }

            public Object caseJobScriptItem(JobScriptItem object) {
                return JOB_SCRIPT;
            }

            /*
             * (non-Javadoc)
             * 
             * @seeorg.talend.core.model.properties.util.PropertiesSwitch# caseSQLPatternItem
             * (org.talend.core.model.properties.SQLPatternItem)
             */
            @Override
            public Object caseSQLPatternItem(SQLPatternItem object) {
                return SQLPATTERNS;
            }

            public Object caseProcessItem(ProcessItem object) {
                IBrandingService breaningService = (IBrandingService) GlobalServiceRegister.getDefault().getService(
                        IBrandingService.class);
                String processLabel = breaningService.getBrandingConfiguration().getJobDesignName();
                if (processLabel.equals("Routes")) {
                    return ROUTES;
                }
                return PROCESS;
            }

            /*
             * (non-Javadoc)
             * 
             * @seeorg.talend.core.model.properties.util.PropertiesSwitch# caseJobletProcessItem
             * (org.talend.core.model.properties.JobletProcessItem)
             */
            @Override
            public Object caseJobletProcessItem(JobletProcessItem object) {
                return JOBLET;
            }

            public Object caseContextItem(ContextItem object) {
                return CONTEXT;
            }

            public Object caseSnippetItem(SnippetItem object) {
                return SNIPPETS;
            }

            public Object caseSnippetVariable(SnippetVariable object) {
                return SNIPPETS;
            }

            public Object caseBusinessProcessItem(BusinessProcessItem object) {
                return BUSINESS_PROCESS;
            }

            public Object caseCSVFileConnectionItem(CSVFileConnectionItem object) {
                throw new IllegalStateException(Messages.getString("ERepositoryObjectType.NotImplemented")); //$NON-NLS-1$
            }

            public Object caseDatabaseConnectionItem(DatabaseConnectionItem object) {
                return METADATA_CONNECTIONS;
            }

            @Override
            public Object caseSAPConnectionItem(SAPConnectionItem object) {
                return METADATA_SAPCONNECTIONS;
            }

            public Object caseDelimitedFileConnectionItem(DelimitedFileConnectionItem object) {
                return METADATA_FILE_DELIMITED;
            }

            public Object casePositionalFileConnectionItem(PositionalFileConnectionItem object) {
                return METADATA_FILE_POSITIONAL;
            }

            public Object caseRegExFileConnectionItem(RegExFileConnectionItem object) {
                return METADATA_FILE_REGEXP;
            }

            public Object caseXmlFileConnectionItem(XmlFileConnectionItem object) {
                return METADATA_FILE_XML;
            }

            public Object caseExcelFileConnectionItem(ExcelFileConnectionItem object) {
                return METADATA_FILE_EXCEL;
            }

            public Object caseLdifFileConnectionItem(LdifFileConnectionItem object) {
                return METADATA_FILE_LDIF;
            }

            public Object caseLDAPSchemaConnectionItem(LDAPSchemaConnectionItem object) {
                return METADATA_LDAP_SCHEMA;
            }

            public Object caseGenericSchemaConnectionItem(GenericSchemaConnectionItem object) {
                return METADATA_GENERIC_SCHEMA;
            }

            public Object caseSalesforceSchemaConnectionItem(SalesforceSchemaConnectionItem object) {
                return METADATA_SALESFORCE_SCHEMA;
            }

            public Object caseWSDLSchemaConnectionItem(WSDLSchemaConnectionItem object) {
                return METADATA_WSDL_SCHEMA;
            }

            @Override
            public Object caseEbcdicConnectionItem(EbcdicConnectionItem object) {
                return METADATA_FILE_EBCDIC;
            }

            public Object caseHL7ConnectionItem(HL7ConnectionItem object) {
                return METADATA_FILE_HL7;
            }

            public Object caseFTPConnectionItem(FTPConnectionItem object) {
                return METADATA_FILE_FTP;
            }

            @Override
            public Object caseBRMSConnectionItem(BRMSConnectionItem object) {
                return METADATA_FILE_BRMS;
            }

            public Object caseMDMConnectionItem(MDMConnectionItem object) {
                return METADATA_MDMCONNECTION;
            }

            @Override
            public Object caseSVGBusinessProcessItem(SVGBusinessProcessItem object) {
                return SVG_BUSINESS_PROCESS;
            }

            public Object caseHeaderFooterConnectionItem(HeaderFooterConnectionItem object) {
                return METADATA_HEADER_FOOTER;
            }

            // MOD mzhao feature 9207
            @Override
            public Object caseTDQItem(TDQItem object) {
                return TDQ_ELEMENT;
            }

            public Object caseValidationRulesConnectionItem(ValidationRulesConnectionItem object) {
                return METADATA_VALIDATION_RULES;
            }

            public Object defaultCase(EObject object) {
                throw new IllegalStateException();
            }
        }.doSwitch(item);

    }

    private static ERepositoryObjectType getTDQRepObjType(Item item) {
        AbstractDQModelService dqModelService = CoreRuntimePlugin.getInstance().getDQModelService();
        if (dqModelService != null) {
            return dqModelService.getTDQRepObjType(item);
        }
        return null;
    }

    public boolean isSubItem() {
        return this.subItem;
    }

    public boolean isResourceItem() {
        return !subItem && !this.equals(ERepositoryObjectType.FOLDER) && !this.equals(ERepositoryObjectType.REFERENCED_PROJECTS)
                && !this.equals(ERepositoryObjectType.SNIPPETS) && !this.equals(ERepositoryObjectType.GENERATED)
                && !this.equals(ERepositoryObjectType.JOBS) && !this.equals(ERepositoryObjectType.JOBLETS)
                && !this.equals(ERepositoryObjectType.METADATA) && !this.equals(ERepositoryObjectType.SVN_ROOT);
    }

    /**
     * DOC bZhou Comment method "isDQItemType".
     * 
     * This method is to ensure a type is a TDQ item or not.
     * 
     * @return
     */
    public boolean isDQItemType() {
        return this.name().indexOf("TDQ") == 0 || this.name().indexOf("SYSTEM_INDICATORS") == 0;
    }

    /**
     * DOC bZhou Comment method "getParent".
     * 
     * @return
     */
    public ERepositoryObjectType getParent() {
        if (this == TDQ_PATTERN_ELEMENT || this == TDQ_ANALYSIS_ELEMENT || this == TDQ_BUSINESSRULE_ELEMENT
                || this == TDQ_INDICATOR_ELEMENT || this == METADATA_CONNECTIONS || this == METADATA_MDMCONNECTION
                || this == TDQ_REPORT_ELEMENT || this == TDQ_JRAXML_ELEMENT) {
            return TDQ_ELEMENT;
        } else
            return null;
    }

    /**
     * DOC bZhou Comment method "getAllDQItemType".
     * 
     * @return
     */
    public static ERepositoryObjectType[] getAllDQItemType() {
        List<ERepositoryObjectType> allTypeList = new ArrayList<ERepositoryObjectType>();
        for (ERepositoryObjectType type : (ERepositoryObjectType[]) values()) {
            if (type.isDQItemType()) {
                allTypeList.add(type);
            }
        }

        return allTypeList.toArray(new ERepositoryObjectType[allTypeList.size()]);
    }

    public String name() {
        if (isStaticNode()) {
            Field[] allFields = ERepositoryObjectType.class.getDeclaredFields();
            for (Field f : allFields) {
                try {
                    Object object = f.get(null);
                    if (object == this) {
                        return f.getName();
                    }
                } catch (IllegalArgumentException e) {
                    //
                } catch (IllegalAccessException e) {
                    //
                }
            }
        }
        return super.name();
    }
}
