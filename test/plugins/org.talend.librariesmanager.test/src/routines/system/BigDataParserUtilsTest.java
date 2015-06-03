// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package routines.system;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * created by pbailly on 3 Jun 2015 Detailled comment
 *
 */
public class BigDataParserUtilsTest {

    String input_String = "3"; //$NON-NLS-1$

    String input_String_double = "3.0"; //$NON-NLS-1$

    String input_String_empty = ""; //$NON-NLS-1$

    String input_String_default = "0"; //$NON-NLS-1$

    String input_String_default_double = "0.0"; //$NON-NLS-1$

    String input_String_null = null;

    byte input_byte = 3;

    byte input_byte_default = 0;

    Byte input_Byte = 3;

    Byte input_Byte_null = null;

    Byte input_Byte_default = 0;

    double input_double = 3.0d;

    double input_double_default = 0d;

    Double input_Double = 3.0D;

    Double input_Double_null = null;

    Double input_Double_default = 0D;

    float input_float = 3.0f;

    float input_float_default = 0f;

    Float input_Float = 3.0F;

    Float input_Float_null = null;

    Float input_Float_default = 0F;

    BigDecimal input_BigDecimal = new BigDecimal(3);

    // weird error case
    BigDecimal input_BigDecimal_double = new BigDecimal(((Double) 3.000D).toString());

    BigDecimal input_BigDecimal_null = null;

    BigDecimal input_BigDecimal_default = new BigDecimal(0);

    int input_int = 3;

    int input_int_default = 0;

    Integer input_Integer = 3;

    Integer input_Integer_null = null;

    Integer input_Integer_default = 0;

    long input_long = 3l;

    long input_long_default = 0l;

    Long input_Long = 3L;

    Long input_Long_null = null;

    Long input_Long_default = 0L;

    short input_short = 3;

    short input_short_default = 0;

    Short input_Short = 3;

    Short input_Short_null = null;

    Short input_Short_default = 0;

    @Test
    public void test_String() {
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_String));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_byte));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_Byte));
        assertEquals(input_String_double, BigDataParserUtils.parseTo_String(input_double));
        assertEquals(input_String_double, BigDataParserUtils.parseTo_String(input_Double));
        assertEquals(input_String_double, BigDataParserUtils.parseTo_String(input_float));
        assertEquals(input_String_double, BigDataParserUtils.parseTo_String(input_Float));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_BigDecimal));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_int));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_Integer));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_long));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_Long));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_short));
        assertEquals(input_String, BigDataParserUtils.parseTo_String(input_Short));
    }

    @Test
    public void test_String_default() {
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_String_null));
        assertEquals(input_String_default, BigDataParserUtils.parseTo_String(input_byte_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Byte_null));
        assertEquals(input_String_default_double, BigDataParserUtils.parseTo_String(input_double_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Double_null));
        assertEquals(input_String_default_double, BigDataParserUtils.parseTo_String(input_float_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Float_null));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_BigDecimal_null));
        assertEquals(input_String_default, BigDataParserUtils.parseTo_String(input_int_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Integer_null));
        assertEquals(input_String_default, BigDataParserUtils.parseTo_String(input_long_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Long_null));
        assertEquals(input_String_default, BigDataParserUtils.parseTo_String(input_short_default));
        assertEquals(input_String_null, BigDataParserUtils.parseTo_String(input_Short_null));
    }

    @Test
    public void test_int() {
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_String));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_byte));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Byte));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_double));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Double));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_float));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Float));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_BigDecimal));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_int));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Integer));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_long));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Long));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_short));
        assertEquals(input_int, BigDataParserUtils.parseTo_int(input_Short));
    }

    @Test
    public void test_int_default() {
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_String_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_byte_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Byte_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_double_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Double_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_float_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Float_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_BigDecimal_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_int_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Integer_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_long_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Long_null));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_short_default));
        assertEquals(input_int_default, BigDataParserUtils.parseTo_int(input_Short_null));
    }

    @Test
    public void test_Integer() {
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_String));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_byte));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Byte));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_double));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Double));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_float));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Float));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_BigDecimal));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_int));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Integer));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_long));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Long));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_short));
        assertEquals(input_Integer, BigDataParserUtils.parseTo_Integer(input_Short));
    }

    @Test
    public void test_Integer_default() {
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_String_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_byte_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Byte_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_double_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Double_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_float_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Float_null));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_BigDecimal_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_int_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Integer_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_long_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Long_null));
        assertEquals(input_Integer_default, BigDataParserUtils.parseTo_Integer(input_short_default));
        assertEquals(input_Integer_null, BigDataParserUtils.parseTo_Integer(input_Short_null));
    }

    @Test
    public void test_byte() {
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_String));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_byte));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Byte));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_double));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Double));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_float));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Float));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_BigDecimal));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_int));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Integer));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_long));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Long));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_short));
        assertEquals(input_byte, BigDataParserUtils.parseTo_byte(input_Short));
    }

    @Test
    public void test_byte_default() {
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_String_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_byte_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Byte_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_double_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Double_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_float_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Float_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_BigDecimal_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_int_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Integer_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_long_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Long_null));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_short_default));
        assertEquals(input_byte_default, BigDataParserUtils.parseTo_byte(input_Short_null));
    }

    @Test
    public void test_Byte() {
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_String));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_byte));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Byte));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_double));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Double));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_float));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Float));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_BigDecimal));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_int));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Integer));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_long));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Long));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_short));
        assertEquals(input_Byte, BigDataParserUtils.parseTo_Byte(input_Short));
    }

    @Test
    public void test_Byte_default() {
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_String_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_byte_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Byte_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_double_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Double_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_float_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Float_null));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_BigDecimal_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_int_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Integer_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_long_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Long_null));
        assertEquals(input_Byte_default, BigDataParserUtils.parseTo_Byte(input_short_default));
        assertEquals(input_Byte_null, BigDataParserUtils.parseTo_Byte(input_Short_null));
    }

    @Test
    public void test_double() {
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_String), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_byte), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Byte), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_double), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Double), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_float), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Float), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_BigDecimal), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_int), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Integer), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_long), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Long), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_short), 0.1d);
        assertEquals(input_double, BigDataParserUtils.parseTo_double(input_Short), 0.1d);
    }

    @Test
    public void test_double_default() {
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_String_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_byte_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Byte_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_double_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Double_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_float_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Float_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_BigDecimal_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_int_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Integer_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_long_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Long_null), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_short_default), 0.1d);
        assertEquals(input_double_default, BigDataParserUtils.parseTo_double(input_Short_null), 0.1d);
    }

    @Test
    public void test_Double() {
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_String));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_byte));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Byte));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_double));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Double));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_float));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Float));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_BigDecimal));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_int));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Integer));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_long));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Long));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_short));
        assertEquals(input_Double, BigDataParserUtils.parseTo_Double(input_Short));
    }

    @Test
    public void test_Double_default() {
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_String_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_byte_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Byte_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_double_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Double_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_float_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Float_null));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_BigDecimal_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_int_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Integer_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_long_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Long_null));
        assertEquals(input_Double_default, BigDataParserUtils.parseTo_Double(input_short_default));
        assertEquals(input_Double_null, BigDataParserUtils.parseTo_Double(input_Short_null));
    }

    @Test
    public void test_float() {
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_String), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_byte), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Byte), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_double), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Double), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_float), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Float), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_BigDecimal), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_int), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Integer), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_long), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Long), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_short), 0.1f);
        assertEquals(input_float, BigDataParserUtils.parseTo_float(input_Short), 0.1f);
    }

    @Test
    public void test_float_default() {
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_String_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_byte_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Byte_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_double_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Double_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_float_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Float_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_BigDecimal_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_int_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Integer_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_long_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Long_null), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_short_default), 0.1f);
        assertEquals(input_float_default, BigDataParserUtils.parseTo_float(input_Short_null), 0.1f);
    }

    @Test
    public void test_Float() {
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_String));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_byte));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Byte));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_double));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Double));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_float));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Float));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_BigDecimal));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_int));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Integer));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_long));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Long));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_short));
        assertEquals(input_Float, BigDataParserUtils.parseTo_Float(input_Short));
    }

    @Test
    public void test_Float_default() {
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_String_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_byte_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Byte_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_double_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Double_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_float_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Float_null));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_BigDecimal_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_int_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Integer_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_long_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Long_null));
        assertEquals(input_Float_default, BigDataParserUtils.parseTo_Float(input_short_default));
        assertEquals(input_Float_null, BigDataParserUtils.parseTo_Float(input_Short_null));
    }

    @Test
    public void test_BigDecimal() {
        assertEquals(input_BigDecimal, new BigDecimal(3.0d));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_String));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_byte));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_Byte));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_double));
        assertEquals(input_BigDecimal_double, BigDataParserUtils.parseTo_BigDecimal(input_Double));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_float));
        assertEquals(input_BigDecimal_double, BigDataParserUtils.parseTo_BigDecimal(input_Float));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_BigDecimal));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_int));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_Integer));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_long));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_Long));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_short));
        assertEquals(input_BigDecimal, BigDataParserUtils.parseTo_BigDecimal(input_Short));
    }

    @Test
    public void test_BigDecimal_default() {
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_String_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_byte_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Byte_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_double_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Double_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_float_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Float_null));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_BigDecimal_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_int_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Integer_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_long_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Long_null));
        assertEquals(input_BigDecimal_default, BigDataParserUtils.parseTo_BigDecimal(input_short_default));
        assertEquals(input_BigDecimal_null, BigDataParserUtils.parseTo_BigDecimal(input_Short_null));
    }

    @Test
    public void test_long() {
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_String), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_byte), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Byte), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_double), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Double), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_float), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Float), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_BigDecimal), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_int), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Integer), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_long), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Long), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_short), 0.1f);
        assertEquals(input_long, BigDataParserUtils.parseTo_long(input_Short), 0.1f);
    }

    @Test
    public void test_long_default() {
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_String_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_byte_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Byte_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_double_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Double_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_float_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Float_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_BigDecimal_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_int_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Integer_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_long_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Long_null), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_short_default), 0.1f);
        assertEquals(input_long_default, BigDataParserUtils.parseTo_long(input_Short_null), 0.1f);
    }

    @Test
    public void test_Long() {
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_String));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_byte));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Byte));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_double));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Double));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_float));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Float));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_BigDecimal));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_int));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Integer));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_long));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Long));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_short));
        assertEquals(input_Long, BigDataParserUtils.parseTo_Long(input_Short));
    }

    @Test
    public void test_Long_default() {
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_String_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_byte_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Byte_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_double_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Double_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_float_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Float_null));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_BigDecimal_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_int_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Integer_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_long_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Long_null));
        assertEquals(input_Long_default, BigDataParserUtils.parseTo_Long(input_short_default));
        assertEquals(input_Long_null, BigDataParserUtils.parseTo_Long(input_Short_null));
    }

    @Test
    public void test_short() {
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_String), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_byte), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Byte), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_double), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Double), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_float), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Float), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_BigDecimal), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_int), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Integer), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_long), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Long), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_short), 0.1f);
        assertEquals(input_short, BigDataParserUtils.parseTo_short(input_Short), 0.1f);
    }

    @Test
    public void test_short_default() {
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_String_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_byte_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Byte_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_double_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Double_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_float_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Float_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_BigDecimal_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_int_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Integer_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_long_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Long_null), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_short_default), 0.1f);
        assertEquals(input_short_default, BigDataParserUtils.parseTo_short(input_Short_null), 0.1f);
    }

    @Test
    public void test_Short() {
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_String));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_byte));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Byte));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_double));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Double));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_float));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Float));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_BigDecimal));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_int));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Integer));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_long));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Long));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_short));
        assertEquals(input_Short, BigDataParserUtils.parseTo_Short(input_Short));
    }

    @Test
    public void test_Short_default() {
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_String_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_byte_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Byte_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_double_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Double_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_float_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Float_null));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_BigDecimal_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_int_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Integer_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_long_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Long_null));
        assertEquals(input_Short_default, BigDataParserUtils.parseTo_Short(input_short_default));
        assertEquals(input_Short_null, BigDataParserUtils.parseTo_Short(input_Short_null));
    }

}
