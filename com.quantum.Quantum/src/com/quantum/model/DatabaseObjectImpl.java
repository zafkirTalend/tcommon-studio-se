/*
 * Created on Oct 19, 2004
 */
package com.quantum.model;

import com.quantum.util.Displayable;

/**
 * @author lleavitt
 */
abstract class DatabaseObjectImpl implements DatabaseObject , Displayable {
	private String schema;
    private String name;
    private String type;
    private Bookmark bookmark;

    public DatabaseObjectImpl(Bookmark bookmark, String schema, String name, String type) {
        this.schema = schema;
        this.name = name;
        this.type = type;
        this.bookmark = bookmark;
    }
    public Bookmark getBookmark() {
        return this.bookmark;
    }
    public String getName() {
        return this.name;
    }
    public String getSchema() {
        return this.schema;
    }
    public String getType() {
        return this.type;
    }
    public boolean isSynonym() {
        return false;
    }
	
	public String getDisplayName() {
		return getQualifiedName();
	}

    public String getQualifiedName() {
        return (this.schema == null || this.schema.length() == 0) ?
            this.name : this.schema + "." + this.name;
    }
    public int compareTo(Object object) {
		Entity that = (Entity) object;
		if (that.getQualifiedName() == null && this.getQualifiedName() != null) {
			return 1;
		} else if (this.getQualifiedName() == null && that.getQualifiedName() != null) {
			return -1;
		} else if (this.getQualifiedName() == null && that.getQualifiedName() == null) {
			return 0;
		} else {
			return this.getQualifiedName().compareTo(that.getQualifiedName());
		}
	}
}
