package com.quantum.actions;

import com.quantum.model.Bookmark;


/**
 * @author BC Holmes
 */
public interface BookmarkAction {
	public void execute(Bookmark bookmark);

	/**
	 * @param bookmark
	 * @return
	 */
	public boolean isEnabled(Bookmark bookmark);
}
