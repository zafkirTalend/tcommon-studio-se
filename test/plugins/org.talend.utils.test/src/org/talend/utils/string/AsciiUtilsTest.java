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
package org.talend.utils.string;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.talend.utils.time.TimeTracer;

public class AsciiUtilsTest {

    private static final boolean TRACE = false;

    static Random rg = new Random(12345643512L);

    String generateString(int length) {
        final int maxChar = 256;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = (char) rg.nextInt(maxChar);
            sb.append(c);
        }
        if (TRACE) {
            System.out.println("Generated string = " + sb);
        }
        return sb.toString();
    }

    String[] generateNStrings(int n, int length) {
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = generateString(length);
        }
        return strings;
    }

    /**
     * Test method for {@link org.talend.utils.string.AsciiUtils#unaccent(java.lang.String)}.
     */
    @Test
    @Ignore("ignore this performance test")
    public void testUnaccentPerformance() {
        int nbStrings = 1000000;
        int nbChars = 150;
        TimeTracer tt = new TimeTracer("unaccent", null);
        String[] generatedStrings = generateNStrings(nbStrings, nbChars);
        tt.start();
        for (String string : generatedStrings) {
            if (TRACE) {
                System.out.println(AsciiUtils.unaccent(string));
            } else {
                AsciiUtils.unaccent(string);
            }
        }
        tt.end("ASCIIUTIL time for removing accents in " + nbStrings + " strings");

        tt.start();
        for (String string : generatedStrings) {
            if (TRACE) {
                System.out.println(AsciiUtils.removeDiacriticalMarks(string));
            } else {
                AsciiUtils.removeDiacriticalMarks(string);
            }
        }
        tt.end("MURAL ENH time for removing accents in " + nbStrings + " strings");
        

        tt.start();
        for (String string : generatedStrings) {
            if (TRACE) {
                System.out.println(StringUtils.stripAccents(string));
            } else {
                StringUtils.stripAccents(string);
            }
        }
        tt.end("COMMONS LANG time for removing accents in " + nbStrings + " strings");

    }

    /**
     * Test method for
     * {@link org.talend.utils.string.AsciiUtils#replaceCharacters(java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testReplaceCharacters() {
        String toReplace = AsciiUtils.UNICODE;
        Assert.assertEquals(AsciiUtils.PLAIN_ASCII, AsciiUtils.replaceCharacters(toReplace,AsciiUtils.UNICODE,AsciiUtils.PLAIN_ASCII));

        String remove = "&@:;%^~£$€'\"\\/ ";
        String replacement = "EACSPHTLDEQDBS_";
        Assert.assertEquals(replacement,AsciiUtils.replaceCharacters(remove, remove, replacement) );
    }
    

    /**
     * Test method for
     * {@link org.talend.utils.string.AsciiUtils#removeDiacriticalMarks(java.lang.String)}
     * .
     */
    @Test
    public void testRemoveDiacriticalMarks() {
        String toReplace = AsciiUtils.UNICODE;
        StringBuilder sb= new StringBuilder();
        for (int i = (int)'\u00D0'; i <= (int)'\u024F';i++){
            sb.append((char)i);
        }
        Assert.assertEquals(AsciiUtils.PLAIN_ASCII, AsciiUtils.removeDiacriticalMarks(toReplace));
    }

}
