/*
 * Created on 15/02/2004
 *
 */
package com.quantum.extensions;

import org.w3c.dom.Document;

/**
 * @author panic
 *
 */
public interface IMetadataExtension extends IQuantumExtension{
	/**
	 * Callback method for metadata export
	 * 
	 * @param doc. An org.w3c.dom.Document with the XML data on the selected items 
	 */
	public void run(Document doc);

}
