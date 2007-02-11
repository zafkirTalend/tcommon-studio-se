package com.quantum.view;

// commented out the grammar part, kept the drag and drop, and the extensions to the preference dialog
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.editors.ColorManager;
import com.quantum.model.Bookmark;
import com.quantum.sql.parser.SQLLexx;
import com.quantum.sql.parser.SQLProvider;
import com.quantum.sql.parser.Token;
import com.quantum.util.sql.SQLGrammar;
import com.quantum.util.versioning.VersioningHelper;

public class SQLQueryView extends ViewPart implements SQLProvider {
	
	
	private StyledText widget;
	private IPropertyChangeListener listener;
	private ColorManager colorManager = new ColorManager();
	private SQLQueryActionGroup actionGroup;
	
//	private String statusMessage="";
	
	private SyntaxHighlighter textUpdater = new SyntaxHighlighter(this.colorManager);
	
	
	public SQLQueryView() {
		super();
		this.listener = new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				setFont();
			}
		};
		QuantumPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(listener);
	}
	
	public void dispose() {
		QuantumPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this.listener);
		this.colorManager.dispose();
		super.dispose();
	}
	
	public static SQLQueryView getInstance() {
		return (SQLQueryView) QuantumPlugin.getDefault().getView("com.quantum.view.sqlqueryview");

	}

	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		this.widget = new StyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		setFont();

		widget.setEditable(true);
		widget.addExtendedModifyListener(modifyListener);

		widget.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		initActions();
		
	   	initializeColours(parent);
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		VersioningHelper.registerActionToKeyBindingService(getSite(), 
				new String[] { "org.eclipse.ui.globalScope", "com.quantum.view.sql" }, 
		        this.actionGroup.getExecuteAction());
		
		initializePopUpMenu();
		
		this.widget.setDoubleClickEnabled(false);
		this.widget.addMouseListener(new MouseAdapter(){
			public void mouseDoubleClick(MouseEvent e) {
				// When double-clicking, we'll select the command around the
				// clicking point, usually to be executed later.
				int caretOffset = widget.getCaretOffset();
//				SQLLexx lexer = (getLastUsedBookmark().getAdapter()).getLexer();
				Vector tokens = SQLLexx.parse(getQuery());
				int offsetIni = 0;
				int offsetEnd = 0;
				for (Iterator iter = tokens.iterator(); iter.hasNext();) {
					Token token = (Token) iter.next();
					if (token.getType() == Token.SEPARATOR) {
						if (token.getEnd() >= caretOffset) {
							offsetEnd = token.getEnd();
							break;
						} else {
							offsetIni = token.getEnd();
						}
					}
				}
				if (offsetEnd == 0 && offsetIni > 0)
					offsetEnd = widget.getCharCount();
				widget.setSelection(offsetIni, offsetEnd);
			}
			
		});

		DropTarget target = new DropTarget(widget, DND.DROP_COPY | DND.DROP_DEFAULT);
        target.setTransfer(new Transfer[] { TextTransfer.getInstance()/*, TemplateTransfer.getInstance() */});
        SQLDropTargetListener sqlDropTargetListener = new SQLDropTargetListener();
        target.addDropListener(sqlDropTargetListener);

	}

	private void setFont() {
		FontData font = PreferenceConverter.getFontData(
				QuantumPlugin.getDefault().getPreferenceStore(), 
				"quantum.font"); //$NON-NLS-1$
		if (font != null && this.widget != null) {
			this.widget.setFont(new Font(Display.getCurrent(), font));
		}
	}
	/**
	 * @param parent
	 */
	private void initializeColours(Composite parent) {
	    IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();

		parent.setBackground(this.colorManager.getColor(
				PreferenceConverter.getColor(store, PluginPreferences.BACKGROUND_COLOR)));
		this.textUpdater.initializeColours();
	}

    private void initializePopUpMenu() {
        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager menuManager) {
                SQLQueryView.this.actionGroup.fillContextMenu(menuManager);
            }
        });
        Menu contextMenu = manager.createContextMenu(this.widget);
        this.widget.setMenu(contextMenu);
        // register the menu to the site so that we can allow 
        // actions to be plugged in
//        getSite().registerContextMenu(manager, this.widget.???);
    }
	
	private void initActions() {
		this.actionGroup = new SQLQueryActionGroup(this);
		this.actionGroup.fillActionBars(getViewSite().getActionBars());
	}

	/**
	 * Returns the query to be executed. The query is either 1) the 
	 * text currently highlighted/selected in the editor or 2) all of 
     * the text in the editor. 
	 * @return query string to be executed
	 */
	public String getQuery() {
	    String query; 
	    
	    if (widget.getSelectionText().length() > 0)
	        query = widget.getSelectionText();
	    else    
	        query = widget.getText();
	    
		return query;
	}
	
	public void setQuery(String text) {
		widget.setText(text);
	}
	
	private class UpdateRequest {
		public UpdateRequest(String text, int start, int length) {
			this.text = text;
			this.start = start;
			this.length = length;
		}
		public String text;
		public int start;
		public int length;
	}
	
	private class SyntaxHighlighter extends Thread {

//		store.setDefault("quantum.text.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.keyword.bold", true); //$NON-NLS-1$
//		store.setDefault("quantum.string.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.comment.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.numeric.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.table.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.view.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.column.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.procedure.bold", false); //$NON-NLS-1$
//		store.setDefault("quantum.variable.bold", false); //$NON-NLS-1$
		
		private Color STRING_LITERAL;
		private Color KEYWORD;
		private Color COMMENT;
		private Color NUMERIC;
//		private Color COLUMN;
//		private Color TABLE;
//		private Color VIEW;
//		private Color PROCEDURE;
		private Color DEFAULT;
//		private Color VARIABLE;
		
//		private boolean boldText= false;
//		private boolean boldKeyword = false;
//		private boolean boldString= false;
//		private boolean boldComment = false;
//		private boolean boldNumeric = false;
//		private boolean boldTable = false;
//		private boolean boldView = false;
//		private boolean boldColumn = false;
//		private boolean boldProcedure = false;
//		private boolean boldVariable = false;
		
		private boolean running = true;
		private LinkedList requests = new LinkedList();
		private final ColorManager colorManager;
		
		public SyntaxHighlighter(ColorManager colorManager) {
			super();
			this.colorManager = colorManager;
			
			setPriority(Thread.NORM_PRIORITY);
			start();
		}
		public void initializeColours() {
		    IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();

			this.DEFAULT = this.colorManager.getColor(
					PreferenceConverter.getColor(store, PluginPreferences.TEXT_COLOR));
			this.KEYWORD = this.colorManager.getColor(
					PreferenceConverter.getColor(store, PluginPreferences.KEYWORD_COLOR)); 
			this.STRING_LITERAL = this.colorManager.getColor(
					PreferenceConverter.getColor(store, PluginPreferences.STRING_COLOR));
			this.COMMENT = this.colorManager.getColor(
					PreferenceConverter.getColor(store, PluginPreferences.COMMENT_COLOR));
			this.NUMERIC = this.colorManager.getColor(
					PreferenceConverter.getColor(store, PluginPreferences.NUMERIC_COLOR));
//			this.TABLE= this.colorManager.getColor(
//					PreferenceConverter.getColor(store, PluginPreferences.TABLE_COLOR));
//			this.COLUMN = this.colorManager.getColor(
//					PreferenceConverter.getColor(store, PluginPreferences.COLUMN_COLOR));
//			this.VIEW= this.colorManager.getColor(
//					PreferenceConverter.getColor(store, PluginPreferences.VIEW_COLOR));
//			this.PROCEDURE = this.colorManager.getColor(
//					PreferenceConverter.getColor(store, PluginPreferences.PROCEDURE_COLOR));
//			this.VARIABLE= this.colorManager.getColor(
//					PreferenceConverter.getColor(store, PluginPreferences.VARIABLE_COLOR));
//			this.boldKeyword = store.getBoolean("quantum.keyword.bold");
//			this.boldColumn= store.getBoolean("quantum.column.bold");
//			this.boldComment = store.getBoolean("quantum.comment.bold");
//			this.boldNumeric = store.getBoolean("quantum.numeric.bold");
//			this.boldProcedure = store.getBoolean("quantum.procedure.bold");
//			this.boldString= store.getBoolean("quantum.string.bold");
//			this.boldTable = store.getBoolean("quantum.table.bold");
//			this.boldText= store.getBoolean("quantum.text.bold");
//			this.boldVariable = store.getBoolean("quantum.variable.bold");
		}
		public synchronized void updateText(String text, int start, int length){
			requests.add(new UpdateRequest(text, start, length));
			notify();
		}

		public void run() {
			while (running) {
				try {
					synchronized (this) {
						if (requests.size() <= 0) {
							wait();
						} else {
							Thread.sleep(10);
						}
					}
					UpdateRequest request = (UpdateRequest) requests.removeFirst();
					String text = request.text.toUpperCase();
					//int dirtyStart = request.start;
					//int dirtyEnd = request.start + request.length;
					StyleRange styleRange;
					List tokens = SQLLexx.parse(text);
					List styles = new ArrayList();
					int min = Integer.MAX_VALUE;
					int max = 0;
					for (int i = 0; i < tokens.size(); i++) {
						Token t = (Token) tokens.get(i);
						String value = t.getValue();
						int start = t.getStart();
						int length = t.getEnd() - t.getStart();
						styleRange = new StyleRange();
						styleRange.start = start;
						styleRange.length = value.length();
						styleRange.fontStyle = SWT.NULL;
						styleRange.foreground = DEFAULT;
						//boolean upper = start <= dirtyEnd && start >= dirtyStart;
						//boolean lower = ((start + length) >= dirtyStart && (start + length) <= dirtyEnd);
						//boolean both = (start <= dirtyStart && (start + length) >= dirtyEnd);
						//if (upper || lower || both) {
						if (true) { // Let's update the whole text, as some comment changes can alter everything
							min = Math.min(start, min);
							max = Math.max(max, start + length);
							if (t.getType() == Token.IDENTIFIER) {
								Bookmark bookmark = getLastUsedBookmark();
								boolean keyword = false;
								// We try to improve a bit the syntax highlighting by using also 
								// the database keywords if the bookmark is open
								if (bookmark != null && bookmark.isConnected()) {
									keyword = bookmark.isKeyword(value);
								} else {
									keyword = SQLGrammar.getInstance().isKeyword(value);
								}
								if (keyword) {
									styleRange.fontStyle = SWT.BOLD;
									styleRange.foreground = KEYWORD;
								} else {
									styleRange.foreground = DEFAULT;
								}
								styles.add(styleRange);
							} else if (t.getType() == Token.COMMENT) {
								styleRange.foreground = COMMENT;
								styles.add(styleRange);
							} else if (t.getType() == Token.LITERAL) {
								styleRange.foreground = STRING_LITERAL;
								styles.add(styleRange);
							} else if (t.getType() == Token.NUMERIC) {
								styleRange.foreground = NUMERIC;
								styles.add(styleRange);
							} else {
								styles.add(styleRange);
							}
						}
					}
					StyleRange[] ranges = 
							(StyleRange[]) styles.toArray(new StyleRange[styles.size()]);
					if (max >= 0 && ranges.length > 0) {
						setStyles(ranges, min, max - min);
					}
				} catch (NoSuchElementException e) {
					// ignore a missing request
				} catch (InterruptedException e) {
					// ignore any interruptions
				}
			}
		}
	}

    public void setStyles(final StyleRange[] styles, final int start, final int length) {
		getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				try {
					for (int i = 0; i < styles.length; i++) {
						widget.setStyleRange(styles[i]);
					}
				} catch (Throwable t) {
					System.out.println("Error with styles: " + t.getClass().toString()); //$NON-NLS-1$
					// ignore any errors
				}
			}
		});
	}
	
	ExtendedModifyListener modifyListener = new ExtendedModifyListener() {
		public void modifyText(ExtendedModifyEvent event) {
			textUpdater.updateText(getQuery(), event.start, event.length);
//			setStatus();
		}
	};

	protected void updateStatusLine(String text) {
		IStatusLineManager statusLine = this.getViewSite().getActionBars().getStatusLineManager();
		statusLine.setMessage(text);
	}
	
	StyledText getControl() {
		return this.widget;
	}

	public void setFocus() {
	}
	
	private Bookmark getLastUsedBookmark() {
		return BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
	}

	public void clear() {
		setQuery("");
	}
	
	public boolean isAutoCommit() {
		return this.actionGroup.isAutoCommitPreference();
	}

	
	public boolean isPinTab() {
		return this.actionGroup.isPinTabPreference();
	}
	class SQLDropTargetListener extends DropTargetAdapter
	{
		public void dragEnter(DropTargetEvent event){
	        if (event.detail == DND.DROP_DEFAULT){
	        	event.detail = DND.DROP_COPY;
	        }
		}

		public void dragOperationChanged(DropTargetEvent event){
		    if (event.detail == DND.DROP_DEFAULT) {
		    	event.detail = DND.DROP_COPY;
		    }
		}

        public void dragOver(DropTargetEvent event)
        {
            Point p = new Point(event.x, event.y);
            p = widget.toControl(p);
            int offset = getWidgetOffset(p);
            try
            {
                widget.setCaretOffset(offset);
            }catch(Exception e){
//              System.out.println(e.getMessage());
            }
        }
        /**
         * Gets the character offset of the point in widget co-ordinates
         * 
         * 
         * @param pt - Point in wiget co-ordinates
         * @return
         */
        public int getWidgetOffset(Point pt) {
            int offset = -1;
            try {
                offset = widget.getOffsetAtLocation(pt);
            }
            catch (IllegalArgumentException e) {
                // Check if the cursor is past the end of the line
                Point startPt = new Point(0,pt.y);
                return getLineStart(startPt);
            }
            
            return offset;
        }
        
        
        /**
         * Returns the character offset of the line 
         * that corresponds to the given point in 
         * widget co-ordinates.
         * 
         * @param pt
         * @return
         */
        public int getLineStart(Point pt) {
            int offset = -1;
            try {
                offset = widget.getOffsetAtLocation(pt);
                int lineNumber = widget.getContent().getLineAtOffset(offset);
                offset += widget.getContent().getLine(lineNumber).length();
            }
            catch (IllegalArgumentException e) {
                // Assume we're past the end of the doc
                return widget.getCharCount();
            }
            
            return offset;
        }
		

        //This implementation of dragOver permits the default operation defined in event.detailto be performed on the current data type defined in event.currentDataType.
		public void drop(DropTargetEvent event){
			if(TextTransfer.getInstance().isSupportedType(event.currentDataType))
			{
				widget.insert((String)event.data);
			}
		}
	}

}
