package com.quantum.util.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import com.quantum.QuantumPlugin;

import org.eclipse.swt.dnd.TextTransfer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * <p>This class constructs a pretty-printing XML Serializer
 * 
 * @author BC
 */
public class XMLHelper {
    
    private XMLHelper() {}
    
    public static Document createEmptyDocument() throws ParserConfigurationException {
        DocumentBuilder builder = createDocumentBuilder();
        return builder.newDocument();
    }
    
    public static Document createFromInputStream(InputStream stream) 
        throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = createDocumentBuilder();
        return builder.parse(stream);
    }

    private static DocumentBuilder createDocumentBuilder()
        throws FactoryConfigurationError, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder();
    }
    
    public static void write(Writer writer, Document document) 
        throws IOException {
        writer.write(XMLRenderer.render(document));
    }

    public static void write(OutputStream stream, Document document) 
        throws IOException {
        stream.write(XMLRenderer.render(document).getBytes());
    }
	/**
	 * @return A Document generated from the text in the Clipboard
	  * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static Document getDocumentFromClipboard() 
			throws ParserConfigurationException, SAXException, IOException {
		TextTransfer transfer = TextTransfer.getInstance();
		return getDocumentFromString((String) QuantumPlugin.getDefault().getSysClip().getContents(transfer));
		
	}

	/**
	 * @param xmlString	The String with the XML to be put into a Document
	 * @return	A Document generated from a String
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws FactoryConfigurationError 
	 */
	public static Document getDocumentFromString(String xmlString) 
	throws SAXException, IOException, ParserConfigurationException {
		if (xmlString == null) {
			return null;
		}
		StringReader text = new StringReader(xmlString);
		InputSource source = new InputSource(text);
		DocumentBuilder builder = createDocumentBuilder();
		return builder.parse(source);
	}
}
