package com.quantum.view.subset;

import java.sql.SQLException;

import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.Check;
import com.quantum.model.Column;
import com.quantum.model.Entity;
import com.quantum.model.ForeignKey;
import com.quantum.model.Index;
import com.quantum.model.Privilege;
import com.quantum.model.Trigger;
import com.quantum.util.connection.NotConnectedException;

/**
 * @author BC
 */
public class EntitySubset implements Entity {
    
    private String name;
    private String schema;
    private Column[] columns;
    private String bookmarkName;
    
    public EntitySubset(String name, String schema, Column[] columns, String bookmarkName) {
        this.name = name;
        this.schema = schema;
        this.columns = columns;
        this.bookmarkName = bookmarkName;
    }

    public String getName() {
        return this.name;
    }

    public String getSchema() {
        return schema;
    }

    public String getType() {
        return null;
    }

    public Column[] getColumns() {
        // TODO: limit the columns
    	return columns;
    }

    public Index[] getIndexes() {
        return new Index[0];
    }

	public String getDisplayName() {
		return getQualifiedName();
	}

    public Column getColumn(String columnName) {
    	for (int i = 0; i < columns.length; i++) {
			if (columnName.equals(columns[i].getName()))
				return columns[i];
		}
    	return null;
    }

    public String getQualifiedName() {
        return this.schema + "." + this.name;
    }

    public Boolean exists() {
        return null;
    }

    public Bookmark getBookmark() {
        return BookmarkCollection.getInstance().find(this.bookmarkName);
    }
    
//    private DatabaseObject getObjectFromBookmark() {
//        try {
//            return getBookmark().getObject(
//                new Schema(schema), name);
//        } catch (SQLException e) {
//            return null;
//        }
//    }
    /**
     * @see com.quantum.model.Entity#getQuotedTableName()
     */
    public String getQuotedTableName() {
        return getBookmark().filterQuoteName(getQualifiedName());
    }

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Entity#getExportedKeys()
	 */
	public ForeignKey[] getExportedKeys() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Entity#getImportedKeys()
	 */
	public ForeignKey[] getImportedKeys() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Entity#getReferences()
	 */
	public ForeignKey[] getReferences() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.Entity#isSynonym()
	 */
	public boolean isSynonym() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getCreateStatement()
	 */
	public String getCreateStatement() throws NotConnectedException, SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getPrimaryKeyColumns()
	 */
	public Column[] getPrimaryKeyColumns() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getTriggers()
	 */
	public Trigger[] getTriggers() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.quantum.model.TableMetadata#getChecks()
	 */
	public Check[] getChecks() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Privilege[] getPrivileges() throws NotConnectedException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
