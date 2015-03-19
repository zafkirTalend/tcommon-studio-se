package org.talend.utils.string;

import java.nio.charset.Charset;

/**
 * This class can be used to figure out problems with wrong text encoding.
 * <p>
 * The result tables can show the displayed text with different Encode/Decode charset pair.
 * <p>
 * You can add you text with wrong encoding into the TEST_CASES to find out what's happening.
 * <p>
 * For example: ºÜºÃºÜÇ¿´ó
 * <p>
 * In the result table, 很好很强大 looks consistent to a human language, so it means if we write this text with a west
 * European charset(iso8859-1) and read it with a Chinese charset (gb2312), it will look fine. This implies that the
 * text was in Chinese and encoded with gb2312, and we need to read that out with the same charset.
 * 
 * @author sizhaoliu
 */
public class EncodingExplainer {

    private static final String[] TEST_CASES = { "éàùïô", "中国", "ºÜºÃºÜÇ¿´ó" };

    private static final String[] CHARSETS = { "iso8859-1", "gb2312", "utf-8" };

    private static final String TOP_LEFT_LABEL = "Encode\\Decode";

    private static final int MAX_LENGTH = 14;

    private static void printEncodingTableFor(String text) {

        System.out.println("----------Test for <" + text + ">-----------");
        for (int i = 0; i < CHARSETS.length + 1; i++) { // rows
            for (int j = 0; j < CHARSETS.length + 1; j++) { // columns
                if (i == 0) { // print table head
                    if (j == 0) {
                        System.out.print(appendWhitespaces(TOP_LEFT_LABEL));
                    } else {
                        System.out.print(appendWhitespaces(CHARSETS[j - 1]));
                    }
                } else { // print table content
                    if (j == 0) {
                        System.out.print(appendWhitespaces(CHARSETS[i - 1]));
                    } else {
                        System.out.print(appendWhitespaces(decodeWith(encodeWith(text, CHARSETS[i - 1]), CHARSETS[j - 1])));
                    }
                }
                if (j == CHARSETS.length) {
                    System.out.println();
                }

            }
        }
        System.out.println();
    }

    private static byte[] encodeWith(String text, String charsetName) {
        return text.getBytes(Charset.forName(charsetName));
    }

    private static String decodeWith(byte[] bytes, String charsetName) {
        return new String(bytes, Charset.forName(charsetName));
    }

    private static String appendWhitespaces(String text) {
        int num = MAX_LENGTH - text.length();
        for (int i = 0; i < num; i++) {
            text += " ";
        }
        text += "\t";
        return text;
    }

    public static void main(String[] args) {
        for (String text : TEST_CASES) {
            printEncodingTableFor(text);
        }
    }
}
