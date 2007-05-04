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
package org.talend.core.model.genhtml;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.talend.commons.exception.ExceptionHandler;

/**
 * DOC Administrator class global comment. Detailled comment <br/>
 * 
 */
public class XMLHandler {

    /**
     * Generates xml file base on inputted path, file path and an instance of <code>Document</code>
     * 
     * @param tempFolderPath
     * @param filePath
     * @param document
     */
    public static void generateXMLFile(String tempFolderPath, String filePath, Document document) {
        XMLWriter output = null;
        FileOutputStream out = null;
        Writer writer = null;

        try {
            // OutputFormat format = OutputFormat.createPrettyPrint();
            out = new java.io.FileOutputStream(filePath);
            writer = new OutputStreamWriter(out, "UTF-8");
            document.write(writer);
            writer.close();
            out.close();
        } catch (Exception e) {
            ExceptionHandler.process(e);
        } finally {

            if (writer != null) {
                try {

                } catch (Exception e) {
                    ExceptionHandler.process(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    ExceptionHandler.process(e);
                }
            }
            if (output != null)
                try {
                    output.close();
                } catch (Exception e) {
                    ExceptionHandler.process(e);
                }
        }
    }

}
