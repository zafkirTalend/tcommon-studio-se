/**
 * 
 */
package org.talend.core.model.metadata.builder.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import org.talend.commons.utils.database.TeradataDataBaseMetadata;
import org.talend.core.database.EDatabaseTypeName;
import org.talend.core.model.metadata.builder.connection.DatabaseConnection;

import com.mysql.jdbc.DatabaseMetaData;
import com.teradata.jdbc.TeraDatabaseMetaData;

/**
 * @author zshen
 *
 */
public class ExtractMetaDataUtilsTester {

	/**
	 * Test method for {@link org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils#getDatabaseMetaData(java.sql.Connection, org.talend.core.model.metadata.builder.connection.DatabaseConnection, boolean)}.
	 */
	@Test
	public void testGetDatabaseMetaDataWithThreeParameters() {
		TeraDatabaseMetaData metaData=Mockito.mock(TeraDatabaseMetaData.class);
		Connection mockConn = Mockito.mock(java.sql.Connection.class);
		try {
			Mockito.when(mockConn.getMetaData()).thenReturn(metaData);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		
		DatabaseConnection mockDBConn = Mockito.mock(DatabaseConnection.class);
		Mockito.when(mockDBConn.getDatabaseType()).thenReturn(EDatabaseTypeName.TERADATA.getXmlName());
		Mockito.when(mockDBConn.getSID()).thenReturn("talendDB");//$NON-NLS-1$
		
		
		
		java.sql.DatabaseMetaData databaseMetaData1 = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, mockDBConn, true);
		java.sql.DatabaseMetaData databaseMetaData2 = ExtractMetaDataUtils.getDatabaseMetaData(mockConn, mockDBConn, false);
		
		try {
			Mockito.verify(mockConn).getMetaData();
			Mockito.verify(mockDBConn, VerificationModeFactory.times(2)).getDatabaseType();
			Mockito.verify(mockDBConn).getSID();
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		if(!(databaseMetaData1 instanceof TeradataDataBaseMetadata && databaseMetaData2 instanceof TeraDatabaseMetaData)){
			fail();
		}
		
	}

}
