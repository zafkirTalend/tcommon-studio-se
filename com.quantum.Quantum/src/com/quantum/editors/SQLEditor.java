package com.quantum.editors;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;
import com.quantum.actions.CheckSyntaxAction;
import com.quantum.actions.CreateDiagramAction;
import com.quantum.actions.ExecuteAction;
import com.quantum.actions.ExplainAction;
import com.quantum.actions.SQLFormatAction;
import com.quantum.model.ISQLSyntaxModel;
import com.quantum.model.SQLSyntaxModelImpl;
import com.quantum.sql.parser.SQLProvider;
import com.quantum.util.versioning.VersioningHelper;

public class SQLEditor extends AbstractDecoratedTextEditor  implements SQLProvider {
	private SQLSourceViewerConfiguration config;
	private ColorManager colorManager;
	private ExecuteAction executeAction;
	private ExplainAction explainAction;
	public CheckSyntaxAction checkSyntaxAction;
    private CreateDiagramAction createDiagramAction;
	private SQLContentOutlinePage outlinePage = null;
	private ISQLSyntaxModel model = null;
    private SQLFormatAction formatAction;
    private TabReplacer tabReplacer = new TabReplacer();

	/**
	 * An editor capable of editing SQL scripts
	 */
	public SQLEditor() {
		super();
        model = getModel();
	}

	public SQLContentOutlinePage getOutlinePage()
	{
		return outlinePage;
	}
	
	public void createPartControl(Composite parent)
	{
        StyledText text = null;
        try
        {
            super.createPartControl(parent);
        }catch(Exception e){
            System.out.println("createPartControl: " + e.getMessage());
            return;
        }
        try{
        	while(text == null)
        	{
	            text = getSourceViewer().getTextWidget();
	        	java.lang.Thread.sleep(1000);
        	}
        }catch(Exception e){
            System.out.println("sleep: " + e.getMessage());
        }
        text.addVerifyListener(tabReplacer);

        /* Set preference values */
        IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();
        FontData data = PreferenceConverter.getFontData(store, "quantum.font");
        text.setFont(new Font(text.getDisplay(), data));
        
        // Strange users might want to change this to strange colors.
        RGB rgb = PreferenceConverter.getColor(store, PluginPreferences.BACKGROUND_COLOR);
        text.setBackground(colorManager.getColor(rgb));

        // dnd
		DropTarget target = new DropTarget(this.getSourceViewer().getTextWidget(), DND.DROP_COPY | DND.DROP_DEFAULT);
        target.setTransfer(new Transfer[] { TextTransfer.getInstance()/*, TemplateTransfer.getInstance() */});
        SQLDropTargetListener sqlDropTargetListener = new SQLDropTargetListener(this.getSourceViewer().getTextWidget());
        target.addDropListener(sqlDropTargetListener);

        // TODO: the globalScope is deprecated, I believe
        VersioningHelper.registerActionToKeyBindingService(getSite(), 
                new String[] { "org.eclipse.ui.globalScope", "com.quantum.editors.SQLEditor" }, 
                this.executeAction);

	}
    
	public ISQLSyntaxModel getModel()
	{
		// I created this function because sometimes the outline page
		// is the first to require the model and sometimes
		// the initializeEditor()
        
        // TODO: This really puzzles me.
        
		// However: even if the initialize has come first,
		// the outline still creates a new one...
		// because somewhere the same SQLEditor gets reinitialized again
		// I think with the getAdapter call...
        
        // It gets even weirder, when I use editor.getConfig().getModel in the
        // ContentOutline the data is present, but when I use editor.getModel
        // the model is empty... So there are two instances, but only one 
        // gets updated and it is not this one.
		if(model == null)
		{
			model = new SQLSyntaxModelImpl(this);
		}
		return model;
	}
	
	protected void initializeEditor() {
		super.initializeEditor();
		colorManager = new ColorManager();
		config = new SQLSourceViewerConfiguration(colorManager);
		setSourceViewerConfiguration(config);
		config.loadPrefs();
		setDocumentProvider(new SQLDocumentProvider());
        // here the model is passed to the configurator
		config.setModel(getModel());
		setRangeIndicator(new DefaultRangeIndicator());
	}
	
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (outlinePage == null) {
				outlinePage= new SQLContentOutlinePage(this);
				if (getEditorInput() != null)
				{
					outlinePage.setInput(getEditorInput());
				}
			}
			return outlinePage;
		}
		return super.getAdapter(required);
	}
	
	protected void createActions() {
		super.createActions();
		this.executeAction = new ExecuteAction(this, this);
		this.explainAction = new ExplainAction(this, this);
		this.checkSyntaxAction = new CheckSyntaxAction(this);
        this.formatAction = new SQLFormatAction(this);
        this.createDiagramAction = new CreateDiagramAction(this);
	}

    protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
        fAnnotationAccess = createAnnotationAccess();
        fOverviewRuler = createOverviewRuler(getSharedColors());
        ISourceViewer viewer = new SourceViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(), styles);
        getSourceViewerDecorationSupport(viewer);
        return viewer;
    }

	protected void editorContextMenuAboutToShow(IMenuManager menu) {
		menu.add(this.executeAction);
		menu.add(this.explainAction);
        menu.add(new Separator());
		menu.add(this.checkSyntaxAction);
        menu.add(this.formatAction);
        menu.add(this.createDiagramAction);
		menu.add(new Separator());
		super.editorContextMenuAboutToShow(menu);
	}

	public void dispose() {
		this.colorManager.dispose();
		this.executeAction.dispose();
		this.checkSyntaxAction.dispose();
		if(outlinePage != null) outlinePage.setInput(null);
//        StyledText text = getSourceViewer().getTextWidget();
//        text.removeVerifyListener(tabReplacer);
		super.dispose();
	}

	// I needed this to invalidate the text in the editor after the CheckSyntaxAction
	public ISourceViewer getMeTheSourceViewer()
	{
		return getSourceViewer();
	}
	
	public SQLSourceViewerConfiguration getConfig()
	{
		return config;
	}
	
	public String getQuery() {
		return getSourceViewer().getTextWidget().getText();
	}

	public void clear() {
		getSourceViewer().getTextWidget().setText("");
	}

	class SQLDropTargetListener extends DropTargetAdapter
	{
		private StyledText widget;

		SQLDropTargetListener(StyledText widget)
		{
			super();
			this.widget = widget;
		}
		
		public void dragEnter(DropTargetEvent event){
	        if (event.detail == DND.DROP_DEFAULT){
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
//				System.out.println(e.getMessage());
			}
		}
		
		public void dragOperationChanged(DropTargetEvent event){
		    if (event.detail == DND.DROP_DEFAULT) {
		    	event.detail = DND.DROP_COPY;
		    }
		}
        //This implementation of dragOver permits the default operation defined in event.detailto be performed on the current data type defined in event.currentDataType.
		public void drop(DropTargetEvent event){
			if(TextTransfer.getInstance().isSupportedType(event.currentDataType))
			{
				widget.insert((String)event.data);
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
	}
    
    class TabReplacer implements VerifyListener {
        public void verifyText(VerifyEvent e) {
            if (e.text.equals("\t")) {
                StringBuffer tab = new StringBuffer();
                for (int idx = 0; idx < 4; idx++)
                    tab.append(' ');
                e.text = tab.toString();
            }
        }
    }
}
