package com.quantum.actions;

import java.util.Arrays;

import com.quantum.ImageStore;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.util.DisplayableComparator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;


/**
 * Creates sub-menu items where each sub-menu item performs the same action against a 
 * different bookmark.
 * 
 * @author BC Holmes
 */
class BookmarkSubActionFactory {
	
	/**
	 * @param menu
	 * @return
	 */
	public static Menu populateDropDownMenu(final BookmarkAction bookmarkAction, Menu menu) {
		/**
		 * Add listener to repopulate the menu each time
		 * it is shown because the list may have changed.
		 */
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				Menu menu = (Menu)e.widget;
				MenuItem[] items = menu.getItems();
				for (int i=0; i < items.length; i++) {
					items[i].dispose();
				}
				BookmarkSubActionFactory.createSubActions(bookmarkAction, menu);
			}
		});
		return menu;
	}
	
	public static void createSubActions(BookmarkAction bookmarkAction, Menu menu) {
		Bookmark lastUsedBookmark = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
		
		if (lastUsedBookmark != null) {
			createSubAction(bookmarkAction, menu, lastUsedBookmark);
			Separator separator = new Separator();
			separator.fill(menu, -1);
		}
		
		Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarks();
		Arrays.sort(bookmarks, new DisplayableComparator());
		for (int i = 0, length = bookmarks == null ? 0 : bookmarks.length; i < length; i++) {
			final Bookmark bookmark = bookmarks[i];
			createSubAction(bookmarkAction, menu, bookmark);
		}
	}

	
	/**
	 * @param menu
	 * @param bookmark
	 */
	public static void createSubAction(
			final BookmarkAction bookmarkAction, Menu menu, final Bookmark bookmark) {
		Action action = new Action() {
			public void run() {
				bookmarkAction.execute(bookmark);
			}
		};
		action.setImageDescriptor(ImageStore.getImageDescriptor(ImageStore.BOOKMARK));
		action.setEnabled(bookmarkAction.isEnabled(bookmark));
		
		// The last '@' sign is treated specially, so if the 
		// bookmark name contains an '@', then add an extra one to the end
		if (bookmark.getName().indexOf('@') >= 0) {
			action.setText(bookmark.getName() + '@');
		} else {
			action.setText(bookmark.getName());
		}
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(menu, -1);
	}
}
