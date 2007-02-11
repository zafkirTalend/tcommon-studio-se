/*
 * Created on 22-jul-2003
 *
 */
package com.quantum.model;


/**
 * User interface components that can provide a connection to the database they
 * relate to implement this interface.  Such components must know how to obtain a 
 * password, as required.
 * 
 * @author panic
 */
public interface BookmarkHolder {
	public Bookmark getBookmark();
}
