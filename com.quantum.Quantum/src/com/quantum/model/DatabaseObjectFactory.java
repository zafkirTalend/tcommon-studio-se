/*
 * Created on Oct 19, 2004
 *
 */
package com.quantum.model;

/**
 * @author lleavitt
 */
public class DatabaseObjectFactory {
    
    private static DatabaseObjectFactory instance = new DatabaseObjectFactory();
    
    private DatabaseObjectFactory() {
    }
    
    public static DatabaseObjectFactory getInstance() {
        return DatabaseObjectFactory.instance;
    }

    public DatabaseObject create(Bookmark bookmark, String schema, String name, String type, boolean isSynonym) {
        return createObject( bookmark, schema, name, type, isSynonym );
    }
    
    public DatabaseObject createObject(Bookmark bookmark, String schema, String name, String type, boolean isSynonym) {
        if (type == null )
        {
            return null;
        }

        type = type.toUpperCase().trim();

    	if (Entity.PACKAGE_TYPE.equals(type)) {
    	    return new PackageImpl(bookmark, schema, name );
    	}
    	
    	return createEntity( bookmark, schema, name, type, isSynonym );
    }

    public Entity createEntity(Bookmark bookmark, String schema, String name, String type, boolean isSynonym) {
        if (type == null )
        {
            return null;
        }

        type = type.toUpperCase().trim();

        if (Entity.TABLE_TYPE.equals(type)) {
            return new TableImpl(bookmark, schema, name, isSynonym);
        } else if (Entity.VIEW_TYPE.equals(type)) {
            return new ViewImpl(bookmark, schema, name, isSynonym);
        } else if (Entity.SEQUENCE_TYPE.equals(type)) {
            return new SequenceImpl(bookmark, schema, name, isSynonym);
        } else {
            return null;
        }
    }

}
