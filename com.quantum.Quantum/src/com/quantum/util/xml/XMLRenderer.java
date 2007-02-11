package com.quantum.util.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author BC Holmes
 */
public class XMLRenderer {

    protected boolean insertNewLine = false;
    protected int numberOfTabs;

    protected final static String LINE_SEPARATOR = System
            .getProperty("line.separator");

    protected final static String INDENT_STRING = "\t";

    /** Returns a sorted list of attributes. */
    protected Attr[] sortAttributes(NamedNodeMap attrs) {

        int len = (attrs != null) ? attrs.getLength() : 0;
        Attr array[] = new Attr[len];
        for (int i = 0; i < len; i++) {
            array[i] = (Attr) attrs.item(i);
        }
        for (int i = 0; i < len - 1; i++) {
            String name = array[i].getNodeName();
            int index = i;
            for (int j = i + 1; j < len; j++) {
                String curName = array[j].getNodeName();
                if (curName.compareTo(name) < 0) {
                    name = curName;
                    index = j;
                }
            }
            if (index != i) {
                Attr temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        return (array);

    }

    protected XMLRenderer() {
        this.numberOfTabs = 0;
        this.insertNewLine = true;
    }

    protected void newLine(StringBuffer buffer) {
        if (this.insertNewLine) {
            buffer.append(LINE_SEPARATOR);
            for (int i = 0; i < this.numberOfTabs; i++) {
                buffer.append(INDENT_STRING);
            }
        }
    }

    /** Prints the specified node, recursively. */
    protected void print(Node node, StringBuffer buffer) {
        // is there anything to do?
        if (node != null) {
            int type = node.getNodeType();
            switch (type) {

            case Node.DOCUMENT_NODE:
                printDocumentNode(node, buffer);
                break;

            // print element with attributes
            case Node.ELEMENT_NODE:
                printElementNode(node, buffer);
                break;
            // handle entity reference nodes
            case Node.ENTITY_REFERENCE_NODE:
                printEntityReferenceNode(node, buffer);
                break;

            // print cdata sections
            case Node.CDATA_SECTION_NODE:
                printCDataSectionNode(node, buffer);
                break;

            // print text
            case Node.TEXT_NODE:
                printTextNode(node, buffer);
                break;

            // print processing instruction
            case Node.PROCESSING_INSTRUCTION_NODE:
                printProcessingInstructionNode(node, buffer);
                break;
            }
        }
    }

    protected void printProcessingInstructionNode(Node node, StringBuffer buffer) {
        buffer.append("<?");
        buffer.append(node.getNodeName());
        String data = node.getNodeValue();
        if (data != null && data.length() > 0) {
            buffer.append(' ');
            buffer.append(data);
        }
        buffer.append("?>");
    }

    protected void printTextNode(Node node, StringBuffer buffer) {
        printString(node.getNodeValue(), buffer);
        this.insertNewLine = false;
    }

    protected void printCDataSectionNode(Node node, StringBuffer buffer) {
        buffer.append("<![CDATA[");
        buffer.append(node.getNodeValue());
        buffer.append("]]>");
    }

    protected void printEntityReferenceNode(Node node, StringBuffer buffer) {
        buffer.append('&');
        buffer.append(node.getNodeName());
        buffer.append(';');
    }

    protected void printElementNode(Node node, StringBuffer buffer) {
        newLine(buffer);
        this.numberOfTabs++;
        buffer.append('<');
        buffer.append(node.getNodeName());
        Attr attrs[] = sortAttributes(node.getAttributes());
        for (int i = 0; i < attrs.length; i++) {
            Attr attr = attrs[i];
            buffer.append(' ');
            buffer.append(attr.getNodeName());
            buffer.append("=\"");
            printString(attr.getNodeValue(), buffer);
            buffer.append('"');
        }

        if (!node.hasChildNodes()) {
            buffer.append(" />");
            this.numberOfTabs--;
        } else {
            buffer.append(">");
            printAllChildNodes(node, buffer);
            this.numberOfTabs--;
            newLine(buffer);

            buffer.append("</");
            buffer.append(node.getNodeName());
            buffer.append(">");
        }
        this.insertNewLine = true;
    }

    protected void printDocumentNode(Node node, StringBuffer buffer) {
        buffer.append("<?xml version=\"1.0\" ?>");

        printAllChildNodes(node, buffer);
    }

    protected void printAllChildNodes(Node node, StringBuffer buffer) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            print(children.item(i), buffer);
        }
    }

    /** Normalizes the given string. */
    protected void printString(String s, StringBuffer buffer) {

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '<': {
                buffer.append("&lt;");
                break;
            }
            case '>': {
                buffer.append("&gt;");
                break;
            }
            case '&': {
                buffer.append("&amp;");
                break;
            }
            case '"':
                buffer.append("&quot;");
                break;
            case '\r':
            case '\n':
            default: {
                buffer.append(ch);
            }
            }
        }
    }

    public static String render(Node node) {
        XMLRenderer renderer = new XMLRenderer();
        StringBuffer buffer = new StringBuffer();
        renderer.print(node, buffer);

        return buffer.toString();
    }

    /**
     * <p>
     * Renders a String in a format that it would appear in a text node. That is
     * to say, special characters (such as &amp;) are converted into entity
     * references (&amp;amp;).
     */
    public static String render(String string) {
        XMLRenderer renderer = new XMLRenderer();
        StringBuffer buffer = new StringBuffer();
        renderer.printString(string, buffer);
        return buffer.toString();
    }
}