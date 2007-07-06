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
package routines;

/**
 * 
 * yzhang class global comment. Detailled comment <br/>
 * 
 * $Id: Mathematical.java 下午04:21:13 2007-7-2 +0000 (2007-7-2) yzhang $
 * 
 */
public class Mathematical {

    /**
     * Returns the absolute (positive) numeric value of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * {param} double (10) a:
     * 
     * {example} ABS(-10) # 10
     */
    public static double ABS(double a) {
        return Math.abs(a);
    }

    /**
     * ACOS( ) Calculates the trigonometric arc-cosine of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double ACOS(double a) {
        return Math.acos(a);
    }

    /**
     * ASIN( ) Calculates the trigonometric arc-sine of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double ASIN(double a) {
        return Math.asin(a);
    }

    /**
     * ATAN( ) Calculates the trigonometric arctangent of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double ATAN(double a) {
        return Math.atan(a);
    }

    /**
     * BITAND( ) Performs a bitwise AND of two integers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static int BITAND(int a, int b) {
        return a & b;
    }

    /**
     * BITNOT( ) Performs a bitwise NOT of a integers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     */
    public static int BITNOT(int a) {
        return ~a;
    }

    /**
     * BITOR( ) Performs a bitwise OR of two integers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     */
    public static int BITOR(int a, int b) {
        return a | b;
    }

    // BITRESET( ) Resets one bit of an integer.

    // BITSET( ) Sets one bit of an integer.
    // BITTEST( ) Tests one bit of an integer.

    /**
     * BITXOR( ) Performs a bitwise XOR of two integers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     */
    public static int BITXOR(int a, int b) {
        return a ^ b;
    }

    /**
     * COS( ) Calculates the trigonometric cosine of an angle.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double COS(double a) {
        return Math.cos(a);
    }

    /**
     * COSH( ) Calculates the hyperbolic cosine of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     */
    public static double COSH(double a) {
        return Math.cosh(a);
    }

    /**
     * DIV( ) Outputs the whole part of the real division of two real numbers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double DIV(double a, double b) {
        return a / b;
    }

    /**
     * EXP( ) Calculates the result of base 'e' raised to the power designated by the value of the expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     */
    public static double EXP(double a) {
        return Math.exp(a);
    }

    /**
     * INT( ) Calculates the integer numeric value of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     */
    public static int INT(String e) {
        return Integer.valueOf(e);
    }

    // FADD( ) Performs floating-point addition on two numeric values. This function is provided for compatibility with
    // existing software.
    // FDIV( ) Performs floating-point division on two numeric values.
    //

    /**
     * FFIX( ) Converts a floating-point number to a string with a fixed precision. FFIX is provided for compatibility
     * with existing software.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     */
    public static String FFIX(double d, int precision) {
        double p = Math.pow(10, precision);
        d = d * p;
        d = Math.round(d) / p;
        return Double.toString(d);
    }

    /**
     * FFLT( ) Rounds a number to a string with a precision of 14.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static String FFLT(double d) {
        return Mathematical.FFIX(d, 14);
    }

    // FMUL( ) Performs floating-point multiplication on two numeric values. This function is provided for compatibility
    // with existing software.
    // FSUB( ) Performs floating-point subtraction on two numeric values.

    /**
     * LN( ) Calculates the natural logarithm of an expression in base 'e'.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double LN(double a) {
        return Math.log(a) / Math.E;
    }

    /**
     * MOD( ) Calculates the modulo (the remainder) of two expressions.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double MOD(double a, double b) {
        return a % b;
    }

    /**
     * NEG( ) Returns the arithmetic additive inverse of the value of the argument.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     */
    public static double NEG(double a) {
        return -1 * a;
    }

    /**
     * NUM( ) Returns true (1) if the argument is a numeric data type; otherwise, returns false (0).
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static int NUM(String e) {
        if (e.matches("/d+")) {
            return 1;
        }
        return 0;
    }

    // PWR( ) Calculates the value of an expression when raised to a specified
    // power.

    /**
     * REAL( ) Converts a numeric expression into a real number without loss of accuracy.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double REAL(String e) {
        return Double.valueOf(e);
    }

    // REM( ) Calculates the value of the remainder after integer division is
    // performed.
    // 

    /**
     * RND( ) Generates a random number between zero and a specified number minus one.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double RND(double a) {
        return Math.random() * a;
    }

    /**
     * SADD( ) Adds two string numbers and returns the result as a string number.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double SADD(String a, String b) {
        return Double.valueOf(a) + Double.valueOf(b);
    }

    /**
     * SCMP( ) Compares two string numbers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static int SCMP(String a, String b) {
        double da = Double.valueOf(a);
        double db = Double.valueOf(b);
        if (da > db) {
            return 1;
        } else if (da == db) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * SDIV( ) Outputs the quotient of the whole division of two integers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static int SDIV(int a, int b) {
        return (int) (a / b);
    }

    /**
     * SIN( ) Calculates the trigonometric sine of an angle.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double SIN(double a) {
        return Math.sin(a);
    }

    /**
     * SINH( ) Calculates the hyperbolic sine of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double SINH(double a) {
        return Math.sinh(a);
    }

    /**
     * SMUL( ) Multiplies two string numbers.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     * 
     */
    public static double SMUL(String a, String b) {
        return Double.valueOf(a) * Double.valueOf(b);
    }

    /**
     * SQRT( ) Calculates the square root of a number.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double SQRT(double a) {
        return Math.sqrt(a);
    }

    /**
     * SSUB( ) Subtracts one string number from another and returns the result as a string number.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static String SSUB(String a, String b) {
        return Double.toString(Double.valueOf(a) - Double.valueOf(b));
    }

    /**
     * TAN( ) Calculates the trigonometric tangent of an angle.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     * 
     */
    public static double TAN(double a) {
        return Math.tan(a);
    }

    /**
     * TANH( ) Calculates the hyperbolic tangent of an expression.
     * 
     * {Category} Mathematical
     * 
     * {talendTypes} String
     * 
     */
    public static double TANH(double a) {
        return Math.tanh(a);
    }

    public static void main(String[] args) {
        System.out.println(Mathematical.FFIX(3.1475926575859643, 14));
    }
}
