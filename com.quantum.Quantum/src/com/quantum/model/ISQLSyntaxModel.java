package com.quantum.model;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

import antlr.CharScanner;
import antlr.LLkParser;

import com.quantum.editors.QToken;

public interface ISQLSyntaxModel extends IContentAssistProcessor
{

    public Bookmark bm = null;
	
	public ISQLSyntaxChecker syntaxChecker = null;

	public CharScanner lexer = null;
	
	public LLkParser parser = null;
	
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset);
	
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset);


	public char[] getCompletionProposalAutoActivationCharacters();

	public char[] getContextInformationAutoActivationCharacters();

	public String getErrorMessage();
	
	public IContextInformationValidator getContextInformationValidator();

	// and our own contracts
	public void checkSyntax();

    public String[] getAliasesForTable(String tableName);

	// we commit to antlr for now: antlr.LLkParser
	// and antlr.CharScanner
	
	public CharScanner getLexer();	

	public void setLexer(CharScanner cs);
	
	public LLkParser getParser();
	
	public void setParser(LLkParser p);
	
	//public String[] getTables(boolean fromStatementOnly);

	public DatabaseObject[] getDatabaseObjects(boolean fromStatementOnly);
	
	public boolean isColumnAlias(String columnName);
	
	public boolean isTable(String tableName);
	
	public boolean columnBelongsToTable(String tableName, String columnName);
	
	public QToken[] getTokens();
	
	public Bookmark getBookmark();
	
	public void setBookmark(Bookmark bm);
	
	public IDocument getDocument();
	
	public IResource getResource();
	
	public AbstractDecoratedTextEditor  getEditor();
	
	public void read();
    
    public void readUpTo(int begin, int end);

	public boolean parserOK();
	
	public void setTokens(Map tokens);

	public IToken nextToken();

	public int getTokenOffset();
	
	public int getTokenLength();
	
	public String[] getTables();

	public String[] getColumns(String table);
    
    public String[] getTargetColumns(String table);
    
    public int getNumberOfErrors();
    
    public void createERDiagram(IFile f, String query);

}
