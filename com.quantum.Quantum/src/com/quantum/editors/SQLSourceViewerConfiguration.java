package com.quantum.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;
import com.quantum.model.ISQLSyntaxModel;

public class SQLSourceViewerConfiguration extends TextSourceViewerConfiguration{
	private PresentationReconciler reconciler = new PresentationReconciler();
	
	private Map tokens = new HashMap();
	
	private ISQLSyntaxModel model = null;
	private ColorManager colorManager;
	
	private boolean keywordBold = true;
	private boolean commentBold = false;
	private boolean numericBold = false;
	private boolean tableBold = false;
	private boolean columnBold = false;
	private boolean procedureBold = false;
	private boolean defaultBold = false;
	private boolean variableBold = false;
	
	private DefaultSQLTokenScanner scanner = null;
	
	public Map getTokens()
	{
		return tokens;
	}
	
	public SQLSourceViewerConfiguration(ColorManager colorManager) {
		super();
		this.colorManager = colorManager;
	}

	void setModel(ISQLSyntaxModel model)
	{
		this.model = model;
	}
	
	public ISQLSyntaxModel getModel()
	{
		return model;
	}
	
	private void generateToken(String key,  TextAttribute attr) {
		IToken token = new Token(attr);
		tokens.put(key, token);
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
        
		SyntaxCheckDamagerRepairer dr = new SyntaxCheckDamagerRepairer(getStatementScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}
	
	protected ITokenScanner getStatementScanner()
	{
		if(scanner == null)
		{
			return (scanner = new DefaultSQLTokenScanner(model, tokens));
		}
		return scanner;
	}
	
	public void loadPrefs() {
		// TODO: Add a listener to our preference store?
		IPreferenceStore store = QuantumPlugin.getDefault().getPreferenceStore();

		keywordBold = store.getBoolean("quantum.keyword.bold"); //$NON-NLS-1$
		commentBold = store.getBoolean("quantum.comment.bold"); //$NON-NLS-1$
		numericBold = store.getBoolean("quantum.numeric.bold"); //$NON-NLS-1$
		tableBold = store.getBoolean("quantum.table.bold");
		columnBold = store.getBoolean("quantum.column.bold");
        variableBold = store.getBoolean("quantum.variable.bold");
		
		SQLColorConstants.DEFAULT = PreferenceConverter.getColor(store, PluginPreferences.TEXT_COLOR); //$NON-NLS-1$
		SQLColorConstants.IDENTIFIER = PreferenceConverter.getColor(store, PluginPreferences.STRING_COLOR); //$NON-NLS-1$
		SQLColorConstants.KEYWORD = PreferenceConverter.getColor(store, PluginPreferences.KEYWORD_COLOR); //$NON-NLS-1$
		SQLColorConstants.STRING = PreferenceConverter.getColor(store, PluginPreferences.STRING_COLOR); //$NON-NLS-1$
		SQLColorConstants.COMMENT = PreferenceConverter.getColor(store, PluginPreferences.COMMENT_COLOR); //$NON-NLS-1$
		SQLColorConstants.NUMERIC = PreferenceConverter.getColor(store, PluginPreferences.NUMERIC_COLOR); //$NON-NLS-1$
		SQLColorConstants.COLUMN = PreferenceConverter.getColor(store, PluginPreferences.COLUMN_COLOR);
		SQLColorConstants.TABLE = PreferenceConverter.getColor(store, PluginPreferences.TABLE_COLOR);
		SQLColorConstants.VARIABLE= PreferenceConverter.getColor(store, PluginPreferences.VARIABLE_COLOR);
		
		generateToken(SQLColorConstants.sKEYWORD, getAttr(SQLColorConstants.KEYWORD, keywordBold, null));
		generateToken(SQLColorConstants.sCOMMENT, getAttr(SQLColorConstants.COMMENT, commentBold, null));
		generateToken(SQLColorConstants.sNUMERIC, getAttr(SQLColorConstants.NUMERIC, numericBold, null));
		generateToken(SQLColorConstants.sCOLUMN, getAttr(SQLColorConstants.COLUMN, columnBold, null));
		generateToken(SQLColorConstants.sTABLE, getAttr(SQLColorConstants.TABLE, tableBold, null));
		generateToken(SQLColorConstants.sVIEW, getAttr(SQLColorConstants.VIEW, tableBold, null));
		generateToken(SQLColorConstants.sPROCEDURE, getAttr(SQLColorConstants.PROCEDURE, procedureBold, null));
		generateToken(SQLColorConstants.sDEFAULT, getAttr(SQLColorConstants.DEFAULT, defaultBold, null));
		generateToken(SQLColorConstants.sVARIABLE, getAttr(SQLColorConstants.VARIABLE, variableBold, null));		
	}
	
	public TextAttribute getAttr(RGB color, boolean bold, RGB backg) {
        // do not specify a background here unless you are sure or let users decide.
		Color foreground = colorManager.getColor(color);
        Color background = null;
        if(backg != null) background = colorManager.getColor(backg);
		if (bold) {
			return new TextAttribute(foreground, background, SWT.BOLD);
		}else{
		    return new TextAttribute(foreground, background, SWT.NONE);
        }
	}

	public int getTabWidth(ISourceViewer sourceViewer) {
		return 4;
	}

	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(getModel(), IDocument.DEFAULT_CONTENT_TYPE);
		// everybody else is doin' it...
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500); // sometimes this is too long

		return assistant;
	}
}