package com.quantum.view.tableview;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import com.quantum.Messages;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;


/**
 * 
 * <p>NOTE: This class uses JDK 1.4 features.  We need to ensure that it isn't invoked on 
 * machines that only have JDK 1.3 installed.
 * 
 * @author Julen Parra
 */
class OtherEncodingsAction extends Action implements IMenuCreator {
	
	private final TableView tableView;
	
	public OtherEncodingsAction(TableView tableView) {
		this.tableView = tableView;
		setText(Messages.getString(TableViewActionGroup.class, "otherEncodingsAction.text"));
		setMenuCreator(this);
	}
	public void dispose() {
	}
	public Menu getMenu(Control parent) {
		return null;
	}
	public Menu getMenu(Menu parent) {
		Menu menu = new Menu(parent);
		SortedMap mapCharsets = Charset.availableCharsets();
	    Set setCharsets = mapCharsets.keySet();		      
		for (Iterator iter = setCharsets.iterator(); iter.hasNext();) {
	         String key = (String) iter.next();
	         Charset charset = (Charset) mapCharsets.get(key);
	         if (!charset.canEncode()) continue;
	         ChangeEncodingAction charsetAction = new ChangeEncodingAction(
	         		this.tableView, this.tableView, charset.name(), charset.name());
			//charsetAction.selectionChanged(getStructuredSelection());
			ActionContributionItem item = new ActionContributionItem(charsetAction);
			item.fill(menu, -1);			
		}
		return menu;
	}
}