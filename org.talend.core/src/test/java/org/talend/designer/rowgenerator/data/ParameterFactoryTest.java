// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the  agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//   
// ============================================================================
package org.talend.designer.rowgenerator.data;

import junit.framework.Assert;

import org.junit.Test;

/**
 * DOC bqian class global comment. Detailled comment <br/>
 * 
 */
public class ParameterFactoryTest {

    /**
     * Test method for {@link org.talend.designer.rowgenerator.data.ParameterFactory#getParameter(java.lang.String)}.
     */
    @Test
    public void testGetParameter() {
        String string = "string(\"2007-01-01\") min : minimum date";
        ParameterFactory pf = new ParameterFactory();
        Parameter parameter = pf.getParameter(string);
        Assert.assertEquals("min", parameter.getName());
        Assert.assertEquals("minimum date", parameter.getComment());
        Assert.assertEquals("\"2007-01-01\"", parameter.getValue());

        string = "string(new Date()) min : minimum date";
        parameter = pf.getParameter(string);
        Assert.assertEquals("min", parameter.getName());
        Assert.assertEquals("minimum date", parameter.getComment());
        Assert.assertEquals("new Date()", parameter.getValue());

        string = "string(\"CCYY-MM-DD hh:mm:ss\") pattern : date pattern + CC for century + YY for year + MM for month + DD";
        parameter = pf.getParameter(string);
        Assert.assertEquals("pattern", parameter.getName());
        Assert.assertEquals("date pattern + CC for century + YY for year + MM for month + DD", parameter.getComment());
        Assert.assertEquals("\"CCYY-MM-DD hh:mm:ss\"", parameter.getValue());

        string = "double(0.15)";
        parameter = pf.getParameter(string);
        Assert.assertEquals("", parameter.getName());
        Assert.assertEquals("", parameter.getComment());
        Assert.assertEquals("0.15", parameter.getValue());

        string = "string(\"hello world!\") string:";
        parameter = pf.getParameter(string);
        Assert.assertEquals("string", parameter.getName());
        Assert.assertEquals("", parameter.getComment());
        Assert.assertEquals("\"hello world!\"", parameter.getValue());

    }
}
