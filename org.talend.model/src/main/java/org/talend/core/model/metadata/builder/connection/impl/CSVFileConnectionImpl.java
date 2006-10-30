/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.ecore.EClass;

import org.talend.core.model.metadata.builder.connection.CSVFileConnection;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CSV File Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CSVFileConnectionImpl extends DelimitedFileConnectionImpl implements CSVFileConnection 
{
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected CSVFileConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return ConnectionPackage.Literals.CSV_FILE_CONNECTION;
	}

} //CSVFileConnectionImpl