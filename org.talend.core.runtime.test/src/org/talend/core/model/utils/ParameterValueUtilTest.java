// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.utils;

import junit.framework.Assert;

import org.junit.Test;

/**
 * DOC cmeng class global comment. Detailled comment
 */
public class ParameterValueUtilTest {

    @Test
    public void testSplitQueryData() {
        String testString = null;
        String expectRetValue = null;
        String retValue = null;
        int i = 0;

        // test case 0
        // testString : context.operation+" "+context.schema+"."+context.table+";"
        testString = "context.operation+\" \"+context.schema+\".\"+context.table+\";\"";
        expectRetValue = "context.oper+\" \"+context.schema+\".\"+context.table+\";\"";
        retValue = ParameterValueUtil.splitQueryData("context.operation", "context.oper", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 1
        // testString (For bug:TDI-29092) : "drop table "+context.oracle_schema+".\"TDI_26803\""
        testString = "\"drop table \"+context.oracle_schema+\".\\\"TDI_26803\\\"\"";
        expectRetValue = "\"drop table \"+context.oracl_schema+\".\\\"TDI_26803\\\"\"";
        retValue = ParameterValueUtil.splitQueryData("context.oracle_schema", "context.oracl_schema", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 2
        // String which is same to the String to be replaced was contained in the testString
        // testString :
        // "insert into "+context.schema+"."+context.table+"(schema, table) values(\"context.schema\", \"context.table\")"
        testString = "\"insert into \"+context.schema+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        expectRetValue = "\"insert into \"+context.db+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        retValue = ParameterValueUtil.splitQueryData("context.schema", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 3
        // testString contains empty string
        // testString :
        // ""+"insert into "+context.schema+"."+context.table+"(schema, table) values(\"context.schema\", \"context.table\")"
        testString = "\"\"+\"insert into \"+context.schema+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        expectRetValue = "\"\"+\"insert into \"+context.db+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        retValue = ParameterValueUtil.splitQueryData("context.schema", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 4
        // testString :
        // "insert into "+context.schema+"."+context.table+"(schema, table) values(\"context.schema\", \"context.table\")"+""
        testString = "\"insert into \"+context.schema+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"+\"\"";
        expectRetValue = "\"insert into \"+context.db+\".\"+context.table+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"+\"\"";
        retValue = ParameterValueUtil.splitQueryData("context.schema", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 5
        // testString :
        // "insert into "+context.schema+"."+context.table+""+"(schema, table) values(\"context.schema\", \"context.table\")"
        testString = "\"insert into \"+context.schema+\".\"+context.table+\"\"+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        expectRetValue = "\"insert into \"+context.db+\".\"+context.table+\"\"+\"(schema, table) values(\\\"context.schema\\\", \\\"context.table\\\")\"";
        retValue = ParameterValueUtil.splitQueryData("context.schema", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 6
        // to ensure it can be replace the correct Strings
        // testString :
        // "context" + "context"+""+context+"context" + context+" "
        testString = "\"context\" + \"context\"+\"\"+context+\"context\" + context+\" \"";
        expectRetValue = "\"context\" + \"context\"+\"\"+context.db+\"context\" + context.db+\" \"";
        retValue = ParameterValueUtil.splitQueryData("context", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 7
        // all are empty
        // testString :
        // ""
        testString = "";
        expectRetValue = "";
        retValue = ParameterValueUtil.splitQueryData("", "", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 8
        // many same varibles
        // testString :
        // "contextA"+context+"contextB"+context+"contextC" + context+" "
        testString = "\"contextA\"+context+\"contextB\"+context+\"contextC\" + context+\" \"";
        expectRetValue = "\"contextA\"+context.db+\"contextB\"+context.db+\"contextC\" + context.db+\" \"";
        retValue = ParameterValueUtil.splitQueryData("context", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

        // test case 9
        // testString :
        // "contextA"+contextA+"contextB"+context+"contextC" + context+" "
        testString = "\"contextA\"+contextA+\"contextB\"+context+\"contextC\" + context+\" \"";
        expectRetValue = "\"contextA\"+contextA+\"contextB\"+context.db+\"contextC\" + context.db+\" \"";
        retValue = ParameterValueUtil.splitQueryData("context", "context.db", testString);
        Assert.assertTrue("testSplitQueryDataCase_" + i++, expectRetValue.equals(retValue));

    }

}
