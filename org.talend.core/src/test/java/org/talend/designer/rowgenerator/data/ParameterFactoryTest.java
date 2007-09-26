// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
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
