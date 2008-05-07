// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.persistence.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class GenerateTimeOfDay {

    private static final DecimalFormat formatter = new DecimalFormat("00");

    private static final String NOTIME = "0,99,99,N/A\n";

    /**
     * DOC scorreia Comment method "main".
     * 
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("data/daytime.csv");
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(file));
            generateTime(output);
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * DOC scorreia Comment method "generateTime".
     * 
     * @param output
     * @throws IOException
     */
    private static void generateTime(Writer output) throws IOException {
        final int nbHours = 24;
        final int nbMinutes = 60;
        int key = 0;
        // create a "non-time" row
        output.write(NOTIME);
        for (int h = 0; h < nbHours; h++) {
            for (int m = 0; m < nbMinutes; m++) {
                key++;
                String row = "" + key + "," + h + "," + m + "," + formatter.format(h) + ":" + formatter.format(m) + "\n";
                output.write(row);
                System.out.print(row);
            }
        }

    }

}
