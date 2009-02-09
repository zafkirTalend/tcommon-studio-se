// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
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
        String string = "string(\"2007-01-01\") min : minimum date"; //$NON-NLS-1$
        ParameterFactory pf = new ParameterFactory();
        Parameter parameter = pf.getParameter(string);
        Assert.assertEquals("min", parameter.getName()); //$NON-NLS-1$
        Assert.assertEquals("minimum date", parameter.getComment()); //$NON-NLS-1$
        Assert.assertEquals("\"2007-01-01\"", parameter.getValue()); //$NON-NLS-1$

        string = "string(new Date()) min : minimum date"; //$NON-NLS-1$
        parameter = pf.getParameter(string);
        Assert.assertEquals("min", parameter.getName()); //$NON-NLS-1$
        Assert.assertEquals("minimum date", parameter.getComment()); //$NON-NLS-1$
        Assert.assertEquals("new Date()", parameter.getValue()); //$NON-NLS-1$

        string = "string(\"CCYY-MM-DD hh:mm:ss\") pattern : date pattern + CC for century + YY for year + MM for month + DD"; //$NON-NLS-1$
        parameter = pf.getParameter(string);
        Assert.assertEquals("pattern", parameter.getName()); //$NON-NLS-1$
        Assert.assertEquals("date pattern + CC for century + YY for year + MM for month + DD", parameter.getComment()); //$NON-NLS-1$
        Assert.assertEquals("\"CCYY-MM-DD hh:mm:ss\"", parameter.getValue()); //$NON-NLS-1$

        string = "double(0.15)"; //$NON-NLS-1$
        parameter = pf.getParameter(string);
        Assert.assertEquals("", parameter.getName()); //$NON-NLS-1$
        Assert.assertEquals("", parameter.getComment()); //$NON-NLS-1$
        Assert.assertEquals("0.15", parameter.getValue()); //$NON-NLS-1$

        string = "string(\"hello world!\") string:"; //$NON-NLS-1$
        parameter = pf.getParameter(string);
        Assert.assertEquals("string", parameter.getName()); //$NON-NLS-1$
        Assert.assertEquals("", parameter.getComment()); //$NON-NLS-1$
        Assert.assertEquals("\"hello world!\"", parameter.getValue()); //$NON-NLS-1$

    }
}
