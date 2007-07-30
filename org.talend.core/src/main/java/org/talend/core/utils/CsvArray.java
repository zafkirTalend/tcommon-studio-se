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
package org.talend.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

/**
 * Detailled comment <br/>
 * 
 * @author ftang, 07-25-2007
 * 
 */
public class CsvArray {

    private static final String ENCODING = "ISO-8859-15";

    private  List<String[]> rows;

    public  CsvArray createFrom(File is) throws IOException {
        CsvArray array = new CsvArray();

        String[] row = null;
        CsvReader csvReader = new CsvReader(new BufferedReader(new InputStreamReader(new java.io.FileInputStream(is),
                ENCODING)), ';');
        csvReader.setRecordDelimiter('\n');
        csvReader.setSkipEmptyRecords(true);
        csvReader.setTextQualifier('"');
        csvReader.setEscapeMode(com.csvreader.CsvReader.ESCAPE_MODE_DOUBLED);
        while (csvReader.readRecord()) {
            array.add(csvReader.getValues());
        }
        this.add(row);
        return array;
    }

    public void add(String[] row) {
        rows.add(row);
    }

    public List<String[]> getRows() {
        return rows;
    }

    /**
     * Constructs a new XmlArray.
     */
    public CsvArray() {
        super();
        rows = new ArrayList<String[]>();
    }
    

    // public static void main(String[]args) throws IOException{
    // CsvArray c = new CsvArray();
    // c = c.createFrom(new File("d:/temp/in.csv"));
    // System.out.println(c.getRows().size());
    // for (String[] string : c.getRows()) {
    // for (String string2 : string) {
    // System.out.print(string2 + " ");
    // }
    // System.out.println();
    //            
    //        }
    //        
    //    }
}
