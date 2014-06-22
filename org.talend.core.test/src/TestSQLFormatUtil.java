// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
import java.util.Collection;

import org.apache.oro.text.regex.MalformedPatternException;
import org.junit.Test;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.core.utils.SQLFormatUtil;

/**
 * DOC Administrator class global comment. Detailled comment
 */
public class TestSQLFormatUtil {

    /**
     * Test method for {@link org.talend.core.utils.SQLFormatUtil#separate(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSeparate() {
        SQLFormatUtil util = new SQLFormatUtil();
        try {
            Collection<String> res = util.separate("select * from table", "sel|table"); //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println(res);
        } catch (MalformedPatternException e) {
            // e.printStackTrace();
            ExceptionHandler.process(e);
        }
    }

}
