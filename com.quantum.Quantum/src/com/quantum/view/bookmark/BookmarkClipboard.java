package com.quantum.view.bookmark;

import com.quantum.model.Bookmark;

/**
 * 
 * TODO: There must be a better way to do this...
 * @author BC
 */
public interface BookmarkClipboard {
    public void setBookmark(Bookmark bookmark);
    public Bookmark getBookmark();
}
