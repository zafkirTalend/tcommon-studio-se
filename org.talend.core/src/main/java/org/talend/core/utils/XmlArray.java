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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.talend.core.CorePlugin;
import org.talend.core.i18n.Messages;
import org.talend.core.prefs.ITalendCorePrefConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOC chuger class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class XmlArray {

    private static final String TAG_ARRAY = "array"; //$NON-NLS-1$

    private static final String TAG_ROW = "row"; //$NON-NLS-1$

    private static final String TAG_FIELD = "field"; //$NON-NLS-1$

    private static int rowLimit = CorePlugin.getDefault().getPreferenceStore().getInt(
            ITalendCorePrefConstants.PREVIEW_LIMIT);

    private List<XmlRow> rows;

    /**
     * Set the value of row limit back to default in preference.
     * 
     * yzhang Comment method "setLimitToDefault".
     */
    public static void setLimitToDefault() {
        XmlArray.rowLimit = CorePlugin.getDefault().getPreferenceStore().getInt(ITalendCorePrefConstants.PREVIEW_LIMIT);
    }

    /**
     * Sets the rowLimit.
     * 
     * @param rowLimit the rowLimit to set
     */
    public static void setRowLimit(int limit) {
        if (limit < 0) {
            setLimitToDefault();
        } else {
            XmlArray.rowLimit = limit;
        }
    }

    /**
     * Getter for rowLimit.
     * 
     * @return the rowLimit
     */
    public static int getRowLimit() {
        return rowLimit;
    }

    /**
     * Constructs a new XmlArray.
     */
    private XmlArray() {
        super();

        rows = new ArrayList<XmlRow>();
    }

    /**
     * Getter for rows.
     * 
     * @return the rows
     */
    public List<XmlRow> getRows() {
        return this.rows;
    }

    /**
     * Adds a row at the end of the array.
     * 
     * @param row Row to add.
     */
    public void add(XmlRow row) {
        rows.add(row);
    }

    public static XmlArray createFrom(InputStream is) throws IOException, ParserConfigurationException, SAXException {
        XmlArray array = new XmlArray();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        Node root = document.getFirstChild();
        if (!TAG_ARRAY.equals(root.getNodeName())) {
            throw new IOException(Messages.getString("XmlArray.InvalidFile")); //$NON-NLS-1$
        }

        // Rows
        NodeList rows = root.getChildNodes();
        int counter = 0;
        for (int r = 0; r < rows.getLength(); r++) {
            Node row = rows.item(r);
            if (TAG_ROW.equals(row.getNodeName())) {
                XmlRow xRow = new XmlRow();

                NodeList fields = row.getChildNodes();
                for (int f = 0; f < fields.getLength(); f++) {
                    Node field = fields.item(f);
                    if (TAG_FIELD.equals(field.getNodeName())) {
                        XmlField xField = new XmlField(field.getTextContent());
                        xRow.add(xField);
                    }
                }

                array.add(xRow);
                counter++;
                if (counter >= rowLimit) {
                    break;
                }
            }
        }

        return array;
    }
}
