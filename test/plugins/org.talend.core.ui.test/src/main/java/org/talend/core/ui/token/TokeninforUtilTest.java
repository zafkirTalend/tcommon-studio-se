// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.token;

import static org.junit.Assert.*;

import org.junit.Test;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;


/**
 * created by nrousseau on Mar 28, 2016
 * Detailled comment
 *
 */
public class TokeninforUtilTest {

    @Test
    public void testMergeJSON() throws JSONException {
        JSONObject source = new JSONObject();
        source.put("key1", "test");
        JSONObject sub1 = new JSONObject();
        sub1.put("myComponent", 5);
        JSONObject sub2 = new JSONObject();
        sub2.put("nb.test", 3);
        sub1.put("subkey1", sub2);
        source.put("key2", sub1);
        JSONObject target = new JSONObject();
        
        TokenInforUtil.mergeJSON(source, target);
        assertEquals("test",target.get("key1"));
        assertEquals(5,target.getJSONObject("key2").getInt("myComponent"));
        assertEquals(3,target.getJSONObject("key2").getJSONObject("subkey1").getInt("nb.test"));

    
        TokenInforUtil.mergeJSON(source, target);
        assertEquals("test",target.get("key1"));
        assertEquals(10,target.getJSONObject("key2").getInt("myComponent"));
        assertEquals(6,target.getJSONObject("key2").getJSONObject("subkey1").getInt("nb.test"));
        
        source = new JSONObject();
        source.put("key1", "test2");
        source.put("key3", "test3");
        sub1 = new JSONObject();
        sub1.put("myComponent", 2);
        sub2 = new JSONObject();
        sub2.put("nb.test", 1);
        sub1.put("subkey1", sub2);
        source.put("key2", sub1);

        
        TokenInforUtil.mergeJSON(source, target);
        assertEquals("test2",target.get("key1"));
        assertEquals("test3",target.get("key3"));
        assertEquals(12,target.getJSONObject("key2").getInt("myComponent"));
        assertEquals(7,target.getJSONObject("key2").getJSONObject("subkey1").getInt("nb.test"));
    }
}
