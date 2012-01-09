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
package org.talend.core;

import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IElementParameter;

/**
 * DOC msjian class global comment. Detailled comment
 */
public interface ITDQRuleService extends IService {

    /**
     * This method is used in tos to get the rule list values.
     * 
     * for the return result, it's a two dimensionality string array. the first is represented for all rules. the
     * second is represented for expressions for each rule. 
     * 
     * @param dbType
     * 
     * @return rules defined in TDQ as String arrays.
     */
    public String[][] retrieveTDQRules(String dbType);

    /**
     * This method is to override the rule list.
     * 
     * @param dbType (value = SupportDBUrlType.dbkey)
     * @param catalog
     * @param schema
     * @param table
     * @param metadataTable
     * @param ruleParam
     */
    public void overrideRuleList(IElementParameter dbType, IElementParameter catalog, IElementParameter schema,
            IElementParameter table, IMetadataTable metadataTable, IElementParameter ruleParam);

}
