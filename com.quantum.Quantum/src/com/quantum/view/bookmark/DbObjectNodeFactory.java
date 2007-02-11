/*
 * Created on Oct 19, 2004
 */
package com.quantum.view.bookmark;

import com.quantum.model.DatabaseObject;
import com.quantum.model.Entity;
import com.quantum.model.Package;

/**
 * @author lleavitt
 */
public class DbObjectNodeFactory {
    public static DbObjectNode create( TreeNode parent, DatabaseObject object )
    {
        if( DatabaseObject.PACKAGE_TYPE.equals( object.getType() ) )
        {
            return new PackageNode( parent, (Package)object );
        }
        else
        {
            return new EntityNode( parent, (Entity)object, false );
        }
    }
}
