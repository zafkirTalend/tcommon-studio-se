package com.quantum.model;

import java.io.StringReader;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.MarkerUtilities;

import antlr.CharScanner;
import antlr.LLkParser;
import antlr.MismatchedTokenException;
import antlr.NoViableAltException;
import antlr.RecognitionException;
import antlr.SemanticException;
import antlr.TokenStreamException;

import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.actions.SQLFormatAction;
import com.quantum.editors.QToken;
import com.quantum.editors.SQLColorConstants;
import com.quantum.editors.SQLEditor;
import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.sql.grammar.Sql92ParserTokenTypes;
import com.quantum.sql.grammar.Sql92SelectLexer;
import com.quantum.sql.grammar.Sql92SelectParser;
import com.quantum.sql.grammar.Sql92SelectParserTokenTypes;
import com.quantum.sql.grammar.Sql92SyntaxLexer;
import com.quantum.sql.grammar.SqlAST;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.util.StringUtil;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.util.sql.TypesHelper;

public class SQLSyntaxModelImpl implements ISQLSyntaxModel{

	private Bookmark bm = null;
	
	private AbstractDecoratedTextEditor  editor = null;
	
	private CharScanner lexer = null;
	
	private LLkParser parser = null;
	private boolean parserOk = false;
	// ---------------------------------------------------------------
    // the antlr token with possibly promoted types
    private antlr.Token aToken;
    private int lastTokenLength;
    private int lastTokenOffset;
    boolean keepToken = false;
    // the list of tokens (with coloring and format from SQLSourceViewerConfig
    private Map tokens;
    private int lastTokenIndex = 0;
    private int returnEOFWhenOffset = -1;
    // ---------------------------------------------------------------
    // this is the only quick fixable error when the syntax is not ok
    // although the content assistant will provide it too...
	private Integer mismatchedToken = new Integer(7);

	// ---------------------------------------------------------------
	// these data structures are used when the syntax of the statement
	// passes the grammar check.
	SqlAST ast = null;
	SqlAST root = null;
	// recognised columns
	private ArrayList columns = new ArrayList();
	// recognised tables
	private ArrayList tables = new ArrayList();
	// recognised operations
	private ArrayList operations = new ArrayList();
	// tables in the from clause
	private ArrayList fromTables = new ArrayList();
	// what was the last object
	Object last;
	// what was the last operation
	Operation lastOperation;
	// the left hand side of the operation has been identified,
	// now find the right hand side
	int lookingForRhs = 0;
	// where we are in the statement
	String state = null;
	// if these essential syntax elements are already present
	boolean fromFound = false;
	boolean whereFound = false;
//	 * Alias can only be used in select list. Use alias.column
	private Integer aliasOutsideSelectList= new Integer(2);
//	 * Column is referenced by table, but alias is defined: alias
	private Integer columnReferencedByTable = new Integer(4);
//	 * Column not found in any table
	private Integer unknownColumn = new Integer(5);
//	 * Ambiguous column name: [table1|table2|table3]
	private Integer ambiguousColumnName = new Integer(6);
////	 * Literal too large for column (max size = Rhs.size)
//	private Integer literalTooLarge = new Integer(7);
//	 * Unknown table
	private Integer unknownTable = new Integer(8);
//	 * Unknown table in from clause
	private Integer unknownTableInFromClause = new Integer(9);
//   * Mismatched column/table pair
    private Integer mismatchedColumnTablePair = new Integer(10);
	// ---------------------------------------------------------------------
	// list of dataTypes supported by the bookmark's database
	DataType[] dataTypes;
	// meta data for column check
	DatabaseMetaData dmd;
	// --------------------------------------------------------------------
	// for context assist
	String tableName = null;
	// the text we will insert or replace
	String toInsert = null;
	// the list of all database objects of the last used bookmark
	private DatabaseObject[] databaseTables = null;
	// whether or not the list has been loaded yet
	private boolean tablesLoaded = false; // because checking for tables == null not working
	// the last thing in the statement recognized as a table
	private String lastTable;
	// a list of tables that might participate in a new join
	private ArrayList joinedTables = new ArrayList();
	private ArrayList aliases = new ArrayList();
	// ---------------------------------------------------------------------
	// QToken collection
	private ArrayList qTokens = new ArrayList();
	// offset and length refer to the location of the Token in the document
    
    private int nErrors;
	
	// this class checks for data type and data size mismatches
	private class ColumnAttribute
	{
		int type;
		int size;
		int decimalDigits;
		boolean literal;
		String name;
		
		public ColumnAttribute(String name, int type, int size, int digits, boolean literal)
		{
			this.type = type;
			this.size = size;
			this.literal = literal;
			this.decimalDigits = digits;
			this.name = name;
		}
	}
	
	// an Operation
	private class Operation
	{
		int operator;
		Object lhs; // String, Numeric, Column, Table
		Object rhs;
		int offset;
		int length;
		
		public Operation(Object lhs, int operator, Object rhs, int offset, int length)
		{
			this.lhs = lhs;
			this.operator = operator;
			this.rhs = rhs;
			this.offset = offset;
			this.length = length;
		}
	}
	
	// The representation of the table, its alias
	private class Table
	{
		String table;
		String alias;
		int offset;
		int length;
		String state;
		
		public Table(String t, String a, int offset, int length, String state)
		{
			table = strip(t);
			alias = strip(a);
			this.offset = offset;
			this.length = length;
			this.state = state;
		}
		
		public Table(String t, int offset, int length, String state)
		{
			table = strip(t);
			this.offset = offset;
			this.length = length;
			this.state = state;
		}
	}
	
	// The representation of the column, its alias, its table 
	// and the table aliases and the attributes and the other tables this column is defined in.
	private class Column
	{
		String column;
		String table;
		String alias;
		String columnAlias;
        String state;
		int offset;
		int length;
		ArrayList tables = new ArrayList(); // these tables also contain column

		public Column(String c, String t, String a, String ca, int offset, int length, String state)
		{
			column = strip(c);
			table = strip(t);
			alias = strip(a);
			this.offset = offset;
			this.length = length;
			columnAlias = ca;
            this.state = state;
		}
		
		public Column(String c, String t, String a, int offset, int length, String state)
		{
			column = strip(c);
			table = strip(t);
			alias = strip(a);
			this.offset = offset;
			this.length = length;
            this.state = state;
		}
		
		public Column(String c, String t, int offset, int length, String state)
		{
			column = strip(c);
			table = strip(t);
			this.offset = offset;
			this.length = length;
            this.state = state;
		}
		
		public Column(String c, int offset, int length, String state)
		{
			column = strip(c);
			this.offset = offset;
			this.length = length;
            this.state = state;
		}
		
		public Column(String c, String a, boolean isAlias, int offset, int length, String state)
		{
			column = strip(c);
			if(isAlias)
			{
				alias = strip(a);
			}else{
				table = strip(a);
			}
			this.offset = offset;
			this.length = length;
            this.state = state;
		}
	}

	public SQLSyntaxModelImpl(AbstractDecoratedTextEditor  editor) {
		super();
		this.editor = editor;
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List proposals = new ArrayList();
		tableName = null;
		String prefix = lastWord(getDocument(), offset); // what the user has typed already
//		String indent = lastIndent(getDocument(), offset); // do not know
		// http://jaen.saul.ee/
		// Lexer   -> Parser -> Simplifier -> Emitter  -> Runtime
		// Strings -> Tokens -> AST        -> Core AST -> CIL
		// now to link this to the database model.'
		addEntityProposals(prefix, offset, proposals);
//		if(state == Sql92SelectParser.STATE_UNKNOWN && toInsert == null)
//		{
//			addKeywordProposals(prefix, indent, offset, proposals);
//		}
		
		// return the answers.
		return (ICompletionProposal[]) proposals.toArray(new ICompletionProposal[proposals.size()]);
	}
	
	private void handleFrom(String prefix, int offset, List proposals) {
		// looking for tables or joins
		if (prefix.length() == 0){
			// return a list of all tables in the schema
			getDatabaseObjects(false);
			for( int i=0; i < databaseTables.length; i++)
			{
				proposals.add(new CompletionProposal(databaseTables[i].getName(), offset, 0, databaseTables[i].getName().length()));
			}
		}else{
			// a part of the table name has been entered
			getDatabaseObjects(false);
			for( int i=0; i < databaseTables.length; i++)
			{
				if (StringUtil.startsWithIgnoreCase(databaseTables[i].getName(), prefix))
				{
					String replacement = databaseTables[i].getName().substring(prefix.length(), databaseTables[i].getName().length());
					if(prefix.length() != 0)
					{
						char c = prefix.charAt(prefix.length()-1);
						if(Character.isLowerCase(c))
						{
							replacement = replacement.toLowerCase();
						}else{
							replacement = replacement.toUpperCase();
						}
					}
					proposals.add(new CompletionProposal(replacement, offset, 0, replacement.length(), null, databaseTables[i].getName(), null, null));
				}
			}
		}
	}

	private void handleTarget(String prefix, int offset, List proposals) {
		// looking for columns belonging to tableName
		if(tableName != null)
		{
			// return the matching columns belonging to tableName
            getMatchingColumns(dmd, proposals, prefix, offset);
		}else{
			// we will try to propose something if prefix has a value, otherwise we would
			// have to return them all.
			if(!prefix.equals(""))
			{
				dmd = getDatabaseMetaData();
				// first look for column names starting with prefix
				String tryThis = prefix + "%";
				String suggestion = null;
				List suggestions = new ArrayList();
				ResultSet rsO = null;
				try {
					try {
						// TODO: See if we need a SQL Statement instead of the metadata
						rsO = dmd.getColumns(null, null, null, tryThis);
						while (rsO.next()) {
							suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME);
							suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME) + "." + suggestion;
							if(!suggestions.contains(suggestion))
							{
								suggestions.add(suggestion);
								String replacement = suggestion;//.substring(prefix.length(), suggestion.length());
								char c = prefix.charAt(prefix.length()-1);
								if(Character.isLowerCase(c))
								{
									replacement = replacement.toLowerCase();
								}else{
									replacement = replacement.toUpperCase();
								}
								proposals.add(new CompletionProposal(replacement, offset - prefix.length(), prefix.length(), replacement.length(), null, suggestion, null, null));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						rsO.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// then look for tablenames starting with prefix
				try {
					try {
						rsO = dmd.getTables(null, null, tryThis, null);
						while (rsO.next()) {
							suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME);
							if(!suggestions.contains(suggestion))
							{
								suggestions.add(suggestion);
								String replacement = suggestion.substring(prefix.length(), suggestion.length());
								char c = prefix.charAt(prefix.length()-1);
								if(Character.isLowerCase(c))
								{
									replacement = replacement.toLowerCase();
								}else{
									replacement = replacement.toUpperCase();
								}
								proposals.add(new CompletionProposal(replacement, offset, 0, replacement.length(), null, suggestion, null, null));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						rsO.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				// TODO: What to do if the user wants all columns...
				// I think we will need a SQL Statement returning this information.
				// But I also like doing nothing... 
			}
		}
	}

	private String isTableAlias(String aliasName) {
		// which set of data to check first?
		// if the statement has not been successfully syntax checked
		// the tables array is not there.
		int i;
		for(i=0;i<tables.size();i++)
		{
			Table t = (Table) tables.get(i);
			if(t.alias != null)
			{
				if(t.alias.equalsIgnoreCase(aliasName))
				{
					if(t.table!="" && t.table != null)
					{
						return t.table;
					}
				}
			}
		}
		aliasName = getTableFromJoinedAlias(aliasName);
		return aliasName;
	}

    private void getMatchingColumns(DatabaseMetaData dmd, List proposals, String prefix, int offset){
        try{
            dmd = getDatabaseMetaData();
            String aliasedTable = isTableAlias(tableName);
            ResultSet rsO = dmd.getColumns(null, null, aliasedTable, prefix + "%");
            String columnName = null;
            try {
                while (rsO.next()) {
                    columnName = rsO.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME);
                    if (prefix.length() == 0)
                    {
                        proposals.add(new CompletionProposal(columnName, offset, 0, columnName.length()));
                    }else{
                        String replacement = columnName.substring(prefix.length(), columnName.length());
                        char c = prefix.charAt(prefix.length()-1);
                        if(Character.isLowerCase(c))
                        {
                            replacement = replacement.toLowerCase();
                        }else{
                            replacement = replacement.toUpperCase();
                        }
                        proposals.add(new CompletionProposal(replacement, offset, 0, replacement.length(), null, columnName, null, null));
                    }
                }
            } finally {
                rsO.close();
            }
        }catch(SQLException e){
            System.out.println("getMatchingColumns: " + e.getMessage());
            return;
        }finally{
            
        }
    }

    private void handleWhere(String prefix, int offset, List proposals) {
		// looking for conditions: a.b = b.a
		if(tableName != null)
		{
			// return all matching columns belonging to tableName
            getMatchingColumns(dmd, proposals, prefix, offset);
		}else{
			// the user has to select from the tables in the from clause...
			getJoinedTables(offset);
			// now suggest those:
			for(int i=0; i<joinedTables.size(); i++)
			{
				String[] replacement = getAliasFromJoinedTable((String)joinedTables.get(i));
				for(int j=0; j<replacement.length; j++)
				{
                    if(prefix.length() == 0)
                    {
    					CompletionProposal cp = new CompletionProposal(replacement[j], offset, 0, replacement[j].length(), null, replacement[j], null, null);
    					// duplicate proposals should not be inserted.
    					if(!proposals.contains(cp))proposals.add(cp);
                    }else{
                        String rep= replacement[j].substring(prefix.length(), replacement[j].length());
                        char c = prefix.charAt(prefix.length()-1);
                        if(Character.isLowerCase(c))
                        {
                            rep = rep.toLowerCase();
                        }else{
                            rep = rep.toUpperCase();
                        }
                        CompletionProposal cp = new CompletionProposal(rep, offset, 0, replacement[j].length(), null, replacement[j], null, null);
                        if(!proposals.contains(cp))proposals.add(cp);
                    }
				}
			}
		}
	}
	
	private void handleOn(String prefix, int offset, List proposals) {
		// the grammar check has decided we need to insert an "on"
		// so it has detected at least two tables. But the AST is not available...
		// the latter one must be the 'current' token
		// and the previous one could be: select a from b [[as] d] [inner] join c [[as] h]
		// a varying lot of tokens ago.
		
		// So we do it the hard way. Duplicating a lot of effort.
		getJoinedTables(offset);
		if(joinedTables.size()>1)
		{
			Bookmark bm = getBookmark();
			ArrayList list = new ArrayList();
			// see if there are relations between the tables.
			// in the relation at least one table should be the 'last' one.
			try
			{
				for(int i=0; i<joinedTables.size();i++)
				{
					ForeignKey[] fk = bm.getDatabase().getExportedKeys(null, (String)joinedTables.get(i));
					for(int j=0; j<fk.length; j++)
					{
						for(int k=0; k<joinedTables.size();k++)
						{
							String referenced = (String)joinedTables.get(k);
							if(fk[j].getForeignEntityName().equalsIgnoreCase(referenced))
							{
								// we have a completion
								// but a table may be referred more than once
								String[] aliasReffering = getAliasFromJoinedTable((String)joinedTables.get(i));
								for(int l=0; l<aliasReffering.length; l++)
								{
									String toInsert = aliasReffering[l] + "." + fk[j].getLocalColumnName(0); 
									toInsert += " = ";
									String aliasReferenced[] = getAliasFromJoinedTable(referenced);
									for(int m=0; m<aliasReferenced.length; m++)
									{
										String toInsertThis = toInsert + aliasReferenced[m] + "." + fk[j].getForeignColumnName(0);
										if(lastTable.equalsIgnoreCase(referenced)||lastTable.equalsIgnoreCase((String)joinedTables.get(i)))
										{
											if(!list.contains(toInsertThis))
											{
												list.add(toInsertThis);
                                                proposals.add(new CompletionProposal("ON\r\n    " + toInsertThis, offset, 0, toInsertThis.length() + 9, null, "ON " + toInsertThis, null, null));
                                                 
											}
										}
									}
								}
							}
						}
					}
				}
			}catch(NotConnectedException e){
				
			}catch(SQLException e){
				
			}
		}
	}
	private String[] getAliasFromJoinedTable(String joinedTableName)
	{
		JoinedTable jt = null;
		ArrayList liases = new ArrayList();
		for(int i=0; i<aliases.size(); i++)
		{
			jt = (JoinedTable) aliases.get(i);
			if(jt.table.equalsIgnoreCase(joinedTableName))
			{
				if(!liases.contains(jt.alias))liases.add(jt.alias);
			}
		}
		if(liases.size()==0)liases.add(joinedTableName); // add the table itself
		return (String[])liases.toArray(new String[liases.size()]);
	}

	private String getTableFromJoinedAlias(String joinedAliasName)
	{
		JoinedTable jt = null;
		for(int i=0; i<aliases.size(); i++)
		{
			jt = (JoinedTable) aliases.get(i);
			if(jt.alias.equalsIgnoreCase(joinedAliasName))
			{
				return jt.table;
			}
		}
		return joinedAliasName;
	}
	
	public DatabaseObject[] getDatabaseObjects(boolean fromStatementOnly)
	{
		if(!fromStatementOnly)
		{
			if(databaseTables == null)
			{
				tablesLoaded = false;
			}else{
				if(databaseTables.length == 0)
				{
					tablesLoaded = false;
				}
			}
			if(!tablesLoaded)
			{
				Bookmark bm = getBookmark();
				try
				{
					// get all table types by passing null
					databaseTables = bm.getObjectsForSchema(null, null);
					tablesLoaded = true;
				}catch(SQLException e){}
				return databaseTables;
			}else{
				return databaseTables;
			}
		}else{
			return null;
		}
	}
	
	public boolean isTable(String name)
	{
		int i = 0;
		// get the table defs from the dmd at the latest possible time...
		getDatabaseObjects(false);
		boolean found = false;
		while(i<databaseTables.length && !found)
		{
			if(databaseTables[i].getName().equalsIgnoreCase(name))
			{
				found = true;
				break;
			}
			i++;
		}
		return found;
	}

	private boolean isWhiteSpace(int offset)
	{
		if(offset <= 0)return true;
		try
		{
			String c = getDocument().get(offset,1);
			char ch = c.charAt(0);
			if(Character.isWhitespace(ch)){
				return true;
			}
		}catch(BadLocationException e){}
		return false;
	}
	
	private String addEntityProposals(String prefix, int offset, List proposals) {
		// parse to see where we are.
		toInsert = null;
		String expected = parseStatement(getResource(), false);
		if (toInsert != null)
		{
			// When there is no whitespace between the cursor and the previous character
			// this should propose table names
			if (toInsert.equalsIgnoreCase(" on"))
			{
				if(isWhiteSpace(offset - 1))
				{
					handleOn(prefix, offset, proposals);
					return expected;
				}
			}else{
				// It should replace everything if the toInsert text
				// matches part of the already typed stuff before the caret
				if (prefix == null) 
				{
					proposals.add(new CompletionProposal(toInsert, offset, 0, toInsert.length()));
				} else if (StringUtil.startsWithIgnoreCase(toInsert, prefix) || StringUtil.startsWithIgnoreCase(toInsert, " " + prefix)) {
					String replacement = toInsert.substring(prefix.length(), toInsert.length());
					if(prefix.length() != 0)
					{
						char c = prefix.charAt(prefix.length()-1);
						if(Character.isLowerCase(c))
						{
							replacement = replacement.toLowerCase();
						}else{
							replacement = replacement.toUpperCase();
						}
					}
					proposals.add(new CompletionProposal(replacement, offset, 0, replacement.length(), null, toInsert, null, null));
				}
				return expected;
			}
		}
		if(parserOK())
		{
			// the parser is happy, but we have lost the place where we were editing
			int i=0;
			while(i<qTokens.size())
			{
				if(((QToken) qTokens.get(i)).getOffset()>=offset)break;
				i++;
			}
			if(i==0)
			{
				expected = Sql92SelectParser.STATE_UNKNOWN;
			}else{
				expected = ((QToken) qTokens.get(i-1)).getState();
			}
		}
		if(expected == Sql92SelectParser.STATE_UNKNOWN){
			// do nothing
		}else if (expected == Sql92SelectParser.STATE_TARGET){
			handleTarget(prefix, offset, proposals);
		}else if (expected == Sql92SelectParser.STATE_FROM){
			handleFrom(prefix, offset, proposals);
		}else if (expected == Sql92SelectParser.STATE_WHERE){
			handleWhere(prefix, offset, proposals);
		}else if (expected == Sql92SelectParser.STATE_CONDITION){ // the condition of a where
			handleWhere(prefix, offset, proposals);
		}else if (expected == Sql92SelectParser.STATE_JOIN_CONDITION){
			handleFrom(prefix, offset, proposals);
			handleWhere(prefix, offset, proposals);
		}
//		public static final String STATE_GROUP = "Group By";
//		public static final String STATE_ORDER = "Order By";)
		return expected;
	}
	
//	private void addKeywordProposals(String prefix, String indent, int offset, List proposals) {
//		for (int i = 0, length = SQLGrammar.KEYWORDS == null ? 0 : SQLGrammar.KEYWORDS.length; i < length; i++)
//		{
//			if (prefix == null) {
//				proposals.add(new CompletionProposal(SQLGrammar.KEYWORDS[i], offset, SQLGrammar.KEYWORDS[i].length(), SQLGrammar.KEYWORDS[i].length()));
//			} else if (StringUtil.startsWithIgnoreCase(SQLGrammar.KEYWORDS[i], prefix)) {
//				String replacement = SQLGrammar.KEYWORDS[i].substring(prefix.length(), SQLGrammar.KEYWORDS[i].length());
//				if(prefix.length() != 0)
//				{
//					char c = prefix.charAt(prefix.length()-1);
//					if(Character.isLowerCase(c))
//					{
//						replacement = replacement.toLowerCase();
//					}else{
//						replacement = replacement.toUpperCase();
//					}
//				}
//				proposals.add(new CompletionProposal(replacement, offset, 0, replacement.length(), null, SQLGrammar.KEYWORDS[i], null, null));
//			}
//		}
//	}
	
	private class JoinedTable
	{
		String table = null;
		String alias = null;
		
		JoinedTable(String table, String alias)
		{
			this.alias = alias;
			this.table = table;
		}
	}
	
	private void getJoinedTables(int offset)
	{
		/*
		 * This does it the hard way: find each token that is an identifier
		 * and compare to the table list.
		 * TODO: Speed optimization and data structure revamp.
		 * TODO: Might become part of the getDatabaseObjects(true)
		 */
		joinedTables.clear();
		aliases.clear();
		Sql92SelectLexer lexer = new Sql92SelectLexer(new StringReader(getDocument().get().toUpperCase()));
		boolean bEOF = false;
		antlr.Token theToken;
		while(!bEOF)
		{
			try
			{
				theToken = lexer.nextToken();
				if(theToken.getType() == Sql92ParserTokenTypes.EOF)bEOF = true;
				if(!bEOF)
				{
					switch(theToken.getType())
					{
						case Sql92ParserTokenTypes.NonQuotedIdentifier:
						case Sql92ParserTokenTypes.QuotedIdentifier:
						{
							// is this identifier a table?
							if(isTable(theToken.getText()))
							{
								String tableName = theToken.getText();
								// We need to figure out if the next token is also
								// an identifier, because then it would be the alias of the
								// table. We store that in the aliases Map.
								if(!joinedTables.contains(tableName))joinedTables.add(tableName);
								// the table before the cursor position should be part of our proposal
								if(theToken.getColumn() + tableName.length() <= offset)
								{
									lastTable = tableName;
								}
								theToken = lexer.nextToken();
								if(theToken.getType() == Sql92ParserTokenTypes.EOF)bEOF = true;
								if(!bEOF)
								{
									switch(theToken.getType())
									{
										case Sql92ParserTokenTypes.NonQuotedIdentifier:
										case Sql92ParserTokenTypes.QuotedIdentifier:
										{
											// the table just added has an alias
											aliases.add(new JoinedTable(tableName, theToken.getText()));
											break;
										}
                                        case Sql92SelectParserTokenTypes.AS:
                                        {
                                            theToken = lexer.nextToken();
                                            if(theToken.getType() == Sql92ParserTokenTypes.EOF)bEOF = true;
                                            if(!bEOF)
                                            {
                                                switch(theToken.getType())
                                                {
                                                    case Sql92ParserTokenTypes.NonQuotedIdentifier:
                                                    case Sql92ParserTokenTypes.QuotedIdentifier:
                                                    {
                                                        // the table just added has an alias
                                                        aliases.add(new JoinedTable(tableName, theToken.getText()));
                                                        break;
                                                    }
                                                }
                                            }
                                            
                                        }
									}
								}
							}
							break;
						}
					}
				}
			}catch(Exception e){}
		}
	}

	private String lastWord(IDocument doc, int offset)
	{
		try
		{
			int n;
			for(n = offset - 1; n>=0; n--)
			{
				char c = doc.getChar(n);
				if(!Character.isJavaIdentifierPart(c))
				{
					String toReturn=doc.get(n + 1, offset - n - 1);
					if (c == '.')
					{
						// preceding tokens could be a table name...
						tableName = lastWord(doc, n);
					}
					return toReturn;
				}
			}
			if(n == -1)
			{
				return doc.get(n + 1, offset - n - 1);
			}
		}catch(BadLocationException e){}
		return "";
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		// WE do not have this yet.
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '.', ' ' };
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	// and our own contracts
	
	public String parseStatement(IResource f, boolean generateMarkers)
	{
		Sql92SelectParser parser = null;
		ast=null;
		try
		{
			lexer = (Sql92SelectLexer) getNewLexer();
			lexer.setTokenObjectClass("antlr.CommonToken");// to get the location of the token
			parser = new Sql92SelectParser(lexer);
			parser.setASTNodeClass("com.quantum.sql.grammar.SqlAST");
			parser.statement();
			ast = (SqlAST)parser.getAST();
			parserOk = true;
			setASTRoot(ast);
			createQTokenList();
            lexer = null;
		}catch(NoViableAltException e){
			if(generateMarkers)
			{
				if(e.token.getType() != Sql92ParserTokenTypes.EOF)
				{
					reportError(f, e.column, e.token.getText().length(), e.getMessage()+ " " + parser.stateSQL, null);
				}else{
					reportError(f, e.column, 0, e.getMessage()+ " " + parser.stateSQL, null);
				}
			}
			return parser.stateSQL;
		}catch(MismatchedTokenException e){
			if(generateMarkers)
			{
				HashMap extra = new HashMap();
				extra.put("com.quantum.sql.grammar.error.id", mismatchedToken);
				extra.put("QuickFix 1", "Expected");
		
				// this reports what was expected
				String[] whatToExpect =  e.getMessage().split("[ |,]");
				for(int i=0; i<whatToExpect.length; i++)
				{
					if(whatToExpect[i].startsWith("\""))
					{
						// this is what was expected
						// when to replace and when to insert, that's the question...
						// depends on preceding and succeeding tokens.
						// ON will need an extra space at the front.
						// maybe we should let the content assistant figure this out..
						String a = whatToExpect[i].substring(1, whatToExpect[i].length()-1);
						extra.put("Replace",((a.equalsIgnoreCase("ON"))?" ":"") + a);
					}
				}
				if(e.token.getType() == 1 ) // eof
				{
					reportError(f, e.column, 0, e.getMessage()+ " " +  parser.stateSQL, extra);
				}else{
					reportError(f, e.column, e.token.getText().length(), e.getMessage()+ " " +  parser.stateSQL, extra);
				}
			}else{
				String[] whatToExpect =  e.getMessage().split("[ |,]");
				for(int i=0; i<whatToExpect.length; i++)
				{
					if(whatToExpect[i].startsWith("\""))
					{
						String a = whatToExpect[i].substring(1, whatToExpect[i].length()-1);
						toInsert = ((a.equalsIgnoreCase("ON"))?" ":"") + a;
					}
				}
			}
			return parser.stateSQL;
		}catch(SemanticException e){
			if(generateMarkers)
			{
				reportError(f, e.column, 1, e.getMessage()+ " " +  parser.stateSQL, null);
			}
			return parser.stateSQL;
		}catch(RecognitionException e) {
			if(generateMarkers)
			{
				reportError(f, e.column, 1, e.getMessage()+ " " +  parser.stateSQL, null);
			}
			return parser.stateSQL;
		}catch(TokenStreamException e){
			System.out.println("parseStatement: "+ e.getCause() + e.getMessage());
			return parser.stateSQL;
		}
		return parser.stateSQL;
	}

	public void createQTokenList()
	{
		qTokens.clear();
        
        lastTokenIndex = 0;
        lastTokenLength = 0;
        lastTokenOffset = 0;
        
		SqlAST ast = getASTRoot();
		QToken q = null;
		String state = Sql92SelectParser.STATE_TARGET;
		while(true)
		{
			q = new QToken();
			q.setType(ast.getType());
			q.setText(ast.getText());
			q.setOffset(ast.getColumn());
			switch(q.getType())
			{
			case Sql92SelectParserTokenTypes.FROM:
				state = Sql92SelectParser.STATE_FROM;
				break;
			case Sql92SelectParserTokenTypes.WHERE:
				state = Sql92SelectParser.STATE_WHERE;
				break;
			}
			q.setState(state);
			qTokens.add(q);
			ast = (SqlAST)ast.getNextSibling();
			if(ast.getType()==Sql92ParserTokenTypes.EOF)break;
		}
	}
	public void checkSyntax()
	{
		// clear the data structures. This we could do smarter I think...
		tables.clear();
		columns.clear();
		operations.clear();
        nErrors = 0;

		// First check whether the grammar is Ok
		IFile f = null;
		try
		{
			IFileEditorInput ei = (IFileEditorInput) editor.getEditorInput();
			f = (IFile) ei.getStorage();
			// remove the markers: note that the Problems view also removes them
			f.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		}catch(Exception e){
			System.out.println("checkSyntax: " + e.getMessage());
		}

		parseStatement(f, true);
		
		if(!parserOK())return;
		// now do some advanced syntax checking
		// first fill some handy data structures
		getColumnsTablesAndOperations();
		checkTables();
		checkColumns();
		checkOperations();
		ast = getASTRoot(); // allow others to read the tree 
		return;
	}

	public void getColumnsTablesAndOperations()
	{
		boolean done = false;
		SqlAST astp = null; // previous token
		SqlAST astpp = null; // previous previous token
		SqlAST astppp = null; // previous previous previous token
		SqlAST astpppp = null; // previous previous previous previous token
		String token=null;

		fromFound = false;
		whereFound = false;

		int type;
        while(!done)
        {
        	type = ast.getType();
        	if(type != Sql92SelectParserTokenTypes.EOF)
        	{
        		token = ast.getText();
        	}
        	switch(ast.getType())
        	{
        	case Sql92SelectParserTokenTypes.SELECT:
        		state = Sql92SelectParser.STATE_TARGET;
        		break;
        	case Sql92SelectParserTokenTypes.FROM:
        		state = Sql92SelectParser.STATE_FROM;
        		fromFound = true;
        		break;
        	case Sql92SelectParserTokenTypes.WHERE:
        		state = Sql92SelectParser.STATE_WHERE;
        		whereFound = true;
        		break;
        	case Sql92SelectParserTokenTypes.JOIN:
        		state = Sql92SelectParser.STATE_FROM;
        		break;
        	case Sql92SelectParserTokenTypes.ON:
        		state = Sql92SelectParser.STATE_CONDITION;
        		break;
            case Sql92SelectParserTokenTypes.COMMA:
                /* 
                 * this is to fix constructs like:
                 * SELECT * FROM A INNER JOIN B ON A.C = B.D, E
                 * which is not beautiful, but legal nonetheless
                */
                if(state == Sql92SelectParser.STATE_CONDITION){
                    state = Sql92SelectParser.STATE_FROM;
                }
                break;
        	case Sql92SelectParserTokenTypes.TABLE_IDENTIFIER:
        	case Sql92SelectParserTokenTypes.CONDITION_TABLE_IDENTIFIER:
        	{
        		last = new Table(token, ast.getColumn(), token.length(), state);
        		tables.add(last);
        		break;
        	}
        	case Sql92SelectParserTokenTypes.ALIAS_IDENTIFIER:
        	{
        		if(astp.getType() == Sql92SelectParserTokenTypes.TABLE_IDENTIFIER ||
        		   astp.getType() == Sql92SelectParserTokenTypes.CONDITION_TABLE_IDENTIFIER )
        		{
        			// we should remove the table named astp.getText() which is 'last'
                    int offset = ((Table)last).offset;
        			tables.remove(last);
        			last = new Table(astp.getText(), token, offset, token.length() + ast.getColumn() - offset, state);
        			tables.add(last);
        		}
        		if(astp.getType() == Sql92SelectParserTokenTypes.AS)
        		{
        			// we should remove the table named astp.getText() which is 'last'
                    int offset = ((Table)last).offset;
        			tables.remove(last);
                    last = new Table(astpp.getText(), token, offset, token.length() + ast.getColumn() - offset, state);
        			tables.add(last);
        		}
        		break;
        	}
        	case Sql92SelectParserTokenTypes.COLUMN_IDENTIFIER_ALIAS:
        	{
        		if(astp.getType() == Sql92SelectParserTokenTypes.AS &&
        			astpp.getType() == Sql92SelectParserTokenTypes.COLUMN_IDENTIFIER)
        		{
        			if(astpppp != null)
        			{
	        			if(astpppp.getType() == Sql92SelectParserTokenTypes.TABLE_IDENTIFIER ||
	        			   astpppp.getType() == Sql92SelectParserTokenTypes.CONDITION_TABLE_IDENTIFIER)
	        			{
	        				// complete definition, but the table name may still be an alias
	        				columns.remove(last);
	        				// we treat the column and its alias as one unit.
	        				last = new Column(astpp.getText(), astpppp.getText(), null, token, astpp.getColumn(), token.length() + ast.getColumn() - astpp.getColumn(), state);
	        				columns.add(last);
	        			}else{
	        			    // the table part is not there
                            columns.remove(last);
                            // we treat the column and its alias as one unit.
                            last = new Column(astpp.getText(), null, null, token, astpp.getColumn(), token.length() + ast.getColumn() - astpp.getColumn(), state);
                            columns.add(last);
                        }
        			}else{
        				columns.remove(last);
        				// we treat the column and its alias as one unit.
        				last = new Column(astpp.getText(), null, null, token, astpp.getColumn(), token.length() + ast.getColumn() - astpp.getColumn(), state);
            			columns.add(last);
        			}
        		}
        		break;
        	}
        	case Sql92SelectParserTokenTypes.COLUMN_IDENTIFIER:
        	case Sql92SelectParserTokenTypes.CONDITION_COLUMN_IDENTIFIER:
        	{
        		if(astp.getType()==Sql92SelectParserTokenTypes.DOT)
        		{
        			if(astpp.getType() == Sql92SelectParserTokenTypes.ALIAS_IDENTIFIER)
        			{
        				last = new Column(token, astpp.getText(), true, ast.getColumn(), token.length(), state);
        				columns.add(last);
        			}else if(astpp.getType() == Sql92SelectParserTokenTypes.TABLE_IDENTIFIER ||
        					astpp.getType() == Sql92SelectParserTokenTypes.CONDITION_TABLE_IDENTIFIER )
        			{
        				last = new Column(token, astpp.getText(), ast.getColumn(), token.length(), state);
        				columns.add(last);
        			}
        		}else{
        			last = new Column(token, ast.getColumn(), token.length(), state);
        			columns.add(last);
        		}
        		break;
        	}
        	case Sql92SelectParserTokenTypes.LIKE:
        	case Sql92SelectParserTokenTypes.ASSIGNEQUAL:
        	case Sql92SelectParserTokenTypes.NOTEQUAL1: 
        	case Sql92SelectParserTokenTypes.NOTEQUAL2: 
        	case Sql92SelectParserTokenTypes.LESSTHANOREQUALTO1:
        	case Sql92SelectParserTokenTypes.LESSTHANOREQUALTO2:
        	case Sql92SelectParserTokenTypes.LESSTHAN:
    		case Sql92SelectParserTokenTypes.GREATERTHANOREQUALTO1:
    		case Sql92SelectParserTokenTypes.GREATERTHANOREQUALTO2:
    		case Sql92SelectParserTokenTypes.GREATERTHAN:
    			lastOperation = new Operation(last, type, null, ast.getColumn(), token.length());
    			operations.add(lastOperation);
    			lookingForRhs = 1;
        		break;
    		case Sql92SelectParserTokenTypes.Integer:
    			try
    			{
        			last = new Integer(Integer.parseInt(ast.getText()));
    			}catch(NumberFormatException e){
    				last = null;
    				// inform user... or try real?
    			}
    			break;
    		case Sql92SelectParserTokenTypes.Number:
    		case Sql92SelectParserTokenTypes.Real:
    			last = new Float(Float.parseFloat(ast.getText()));
    			break;
    		case Sql92SelectParserTokenTypes.ASCIIStringLiteral:
    		case Sql92SelectParserTokenTypes.UnicodeStringLiteral:
    			last = new String(ast.getText());
    			break;
        	case Sql92SelectParserTokenTypes.EOF:
        		done = true;
        		break;
        	}
        	astpppp = astppp;
        	astppp = astpp;
        	astpp = astp;
        	astp = ast;
        	ast = (SqlAST) ast.getNextSibling();
        	if(lookingForRhs==1)
        	{
        		// we have just activated the search
        		lookingForRhs = 2;
        	}else if(lookingForRhs == 2){
        		if (last instanceof Table) {
				}else{
	        		lastOperation.rhs = last;
	        		lookingForRhs = 0;
				}
        	}
        }
	}
	
	// the Lexer will be a ITokenScanner and what is internal is not interesting
	// the Parser might be hidden but must be pluggable
	// if we commit to antlr: antlr.LLkParser
	// and antlr.CharScanner
	// Split into two functions because when the document is dirtied we need a new lexer.
	public CharScanner getNewLexer()
	{
		lexer = null;
		parserOk = false; 
		return getLexer();
	}

    public CharScanner getNewSyntaxLexer()
    {
        lexer = null;
        parserOk = false; 
        String text = getDocument().get().toUpperCase();
        lexer = new Sql92SyntaxLexer(new StringReader(text));
        lexer.setTokenObjectClass("antlr.CommonToken");// to get the location of 
        return lexer;
    }
	
	public CharScanner getLexer()
	{
		if(lexer == null)
		{
			String text = getDocument().get().toUpperCase();
			lexer = new Sql92SelectLexer(new StringReader(text));
			lexer.setTokenObjectClass("antlr.CommonToken");// to get the location of 
		}
		return lexer;
	}
	
	public void setLexer(CharScanner cs)
	{
		lexer = cs;
	}
	
	public LLkParser getParser()
	{
		if(parser == null)
		{
			parser = new Sql92SelectParser(lexer);
		}
		return parser;
	}
	
	public void setParser(LLkParser p)
	{
		parser = p;
	}
	
	public boolean isColumnAlias(String columnName)
	{
		return false;
	}
	
	public boolean columnBelongsToTable(String tableName, String columnName)
	{
		return false;
	}
	
	public QToken[] getTokens()
	{
		return (QToken[])qTokens.toArray(new QToken[qTokens.size()]);
	}
	
	public Bookmark getBookmark()
	{
//		if(bm == null)
//		{
//			bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
//		}
//		return bm;
	    bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        return bm;
	}
	
	public void setBookmark(Bookmark bm)
	{
		this.bm = bm;
	}
	
	public IDocument getDocument()
	{
        AbstractDecoratedTextEditor  _editor = getEditor();
		if(_editor != null)
		{
			IDocumentProvider dp = editor.getDocumentProvider();
			return dp.getDocument(editor.getEditorInput());
		}else{
			return null;
		}
	}
	
	public AbstractDecoratedTextEditor  getEditor()
	{
		return editor;
	}

	public IResource getResource() {
        AbstractDecoratedTextEditor  _editor = getEditor();
		IFileEditorInput ei = (IFileEditorInput) _editor.getEditorInput();
		IFile f = null;
		try
		{
			f = (IFile) ei.getStorage();
		}catch(Exception e){
			System.out.println("getResource: " + e.getMessage());
		}
		return (IResource) f;
	}
	
	/**
	 * The read member lexes the text in the editor window until
	 * the offset is reached. It uses the syntax lexer which does
     * not ignore comments and whitespace
	 * @param offset
	 */
    public void readUpTo(int begin, int end)
    {
        returnEOFWhenOffset = end;
        read(); 
        if (qTokens.size() > 0) {
            int size = 0;
            int i = 0; // you have to start here, because you do not know where the user typed.
            while (size <= begin && i < qTokens.size()) {
                QToken q = (QToken) qTokens.get(i);
                size = q.getOffset() + ((q.getType() == Sql92ParserTokenTypes.EOF)?0:q.getText().length());
                keepToken = false;
                i++;
            }
            lastTokenIndex = (i == 0)? 0: i - 1;
        }
    }
    
	public void read()
	{
        String text = getDocument().get().toUpperCase();
        if(text.indexOf("\t") != -1)
        {
            // if the user pasted some text that text might contain tabs, remove these before syntax highlighting
            SQLFormatAction a = new SQLFormatAction((SQLEditor) getEditor());
            a.run();
        }
        // include the comments and whitspace here.
    	lexer = getNewSyntaxLexer(); // this also gets the text out of the widget
        // fill the qTokens list with the tokens from the lexer
        QToken q = null;
        qTokens.clear();
        while(true)
        {
            try {
                aToken = lexer.nextToken();
            } catch (TokenStreamException e) {
                e.printStackTrace();
                break;
            }
            q = new QToken();
            q.setType(aToken.getType());
            if (aToken.getType() == Sql92ParserTokenTypes.EOF) {
                qTokens.add(q);
                break;
            }
            q.setText(aToken.getText());
            q.setOffset(aToken.getColumn());
            qTokens.add(q);
        }
        lexer = null; // force the syntax highlighter to use qTokens
	}

	public boolean parserOK() {
		return parserOk;
	}
	
	public void checkOperations()
	{
		// real is a subset of numeric
		for(int i=0; i<operations.size(); i++)
		{
			Operation o = (Operation)operations.get(i);
			ColumnAttribute caLhs = getColumnAttribute(o.lhs);
			ColumnAttribute caRhs = getColumnAttribute(o.rhs);
			if(TypesHelper.isNumeric(caLhs.type) && !TypesHelper.isReal(caLhs.type))
			{
				if(TypesHelper.isReal(caRhs.type))
				{
					reportWarning(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " might not be comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isText(caRhs.type)){
					reportError(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " is not comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isNumeric(caRhs.type)){
					// does the one fit in the other?
					checkSize(caLhs, caRhs, o);
				}
			}else if(TypesHelper.isText(caLhs.type)){
				if(TypesHelper.isReal(caRhs.type))
				{
					reportError(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " is not comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isNumeric(caRhs.type)){
					reportError(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " is not comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isText(caRhs.type)){
					// does the one fit in the other?
					checkSize(caLhs, caRhs, o);
				}
			}else if(TypesHelper.isReal(caLhs.type)){
				if(TypesHelper.isNumeric(caRhs.type) && !TypesHelper.isReal(caRhs.type))
				{
					reportWarning(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " might not be comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isText(caRhs.type)){
					reportError(getResource(), o.offset, o.length, "Data types are different: " + TypesHelper.getTypeName(caLhs.type) + " is not comparable to " + TypesHelper.getTypeName(caRhs.type));
				}else if(TypesHelper.isReal(caRhs.type)){
					// does the one fit in the other?
					checkSize(caLhs, caRhs, o);
				}
			}
		}
	}
	
	void checkSize(ColumnAttribute caLhs, ColumnAttribute caRhs, Operation o)
	{
		if(caLhs.literal) {
			// the left hand side is a column
			if(caRhs.literal)
			{
				if((caLhs.size - caLhs.decimalDigits) < caRhs.size)
				{
					// error
					reportError(getResource(), o.offset, o.length, "Literal too large for column (max size = " + (caLhs.size - caLhs.decimalDigits) + ")");
				}
			}else{
				// two columns
				if((caLhs.size -caLhs.decimalDigits) != (caRhs.size - caRhs.decimalDigits))
				{
					reportWarning(getResource(), o.offset, o.length, "Sizes are different: " + caLhs.name + ": "+ (caLhs.size -caLhs.decimalDigits) + " might not be comparable to " + caRhs.name + ": " + (caRhs.size - caRhs.decimalDigits));
				}
			}
		} else {
			if(caRhs.literal)
			{
				// comparing literals, cannot be wrong
			}else{
				// the right hand side is a column
				if(caLhs.size > (caRhs.size - caRhs.decimalDigits))
				{
					// error
					reportError(getResource(), o.offset, o.length, "Literal too large for column (max size = " + (caRhs.size - caRhs.decimalDigits) + ")");
				}
			}
		}
	}
	
	private ColumnAttribute getColumnAttribute(Object o)
	{
		int type = 0;
		int size = 0;
		int digits = 0;
		if(o instanceof Column)
		{
			Column c = (Column) o;
			try
			{
				ResultSet rsO = getDatabaseMetaData().getColumns(null, null, c.table, c.column);
				try {
					while (rsO.next()) {	
						type = rsO.getInt(JDBCUtil.COLUMN_METADATA_DATA_TYPE);
						size = rsO.getInt(JDBCUtil.COLUMN_METADATA_COLUMN_SIZE);
						digits = rsO.getInt(JDBCUtil.COLUMN_METADATA_DECIMAL_DIGITS);
					}
				}catch(NullPointerException e){
					// called without a connection to a database
				} finally {
					rsO.close();
				}
				return new ColumnAttribute(c.column, type, size, digits, false);
			}catch(SQLException e){
				System.out.println("getColumnAttribute: " + e.getMessage());
				return null;
			}finally{
				
			}
		}else if(o instanceof String){
			String s = (String) o;
			size = s.length() - 2; // do not count the quotes
			type = TypesHelper.VARCHAR;
			return new ColumnAttribute("Literal", type, size, 0, true);
		}else if(o instanceof Integer){
			Integer i = (Integer) o;
			double answer = Math.log(i.doubleValue())/Math.log(10.);
			size = (int)answer;
			type = TypesHelper.INTEGER;
			return new ColumnAttribute("Integer", type, size, 0, true);
		}else if(o instanceof Float){
			Float f = (Float) o;
			double answer = Math.log(f.doubleValue())/Math.log(10.);
			size = (int)answer;
			type = TypesHelper.FLOAT;
			return new ColumnAttribute("Float", type, size, 0, true);
		}
		return null;
	}

	public void checkColumns()
	{
		Database db = null;
		try
		{
			db = getBookmark().getDatabase();
		}catch(NotConnectedException e){
			return;
		}
		
		for(int i=0; i < columns.size(); i++)
		{
			Column c = (Column)columns.get(i);
			if(c.columnAlias == null)
			{
				standaloneColumn(c);
			}
			if(c.table!=null)
			{
				// is this column an alias?
                isAlias(db, c);
				if(db.columnExists(c.table, c.column)==false)
				{
					isColumnKnown(db, c);
				}else{
					isTableAliasDefined(c);
//					isColumnKnown(db, c);
				}
			}else{
				isColumnKnown(db, c);
			}
		}
	}

	protected Shell getShell() {
		// Can this be done simpler?
		IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if(part instanceof TextEditor)
		{
			return part.getSite().getShell();
		}
		return null;
    }

	private void isColumnKnown(Database db, Column c) {
		// loop over the tables in the statement
        boolean errorToReport = false;
		if(c.table != null)
		{
			if(db.columnExists(c.table, c.column))
			{
				return;
			}else{
			    // this table and column combination is false...
                errorToReport = true;
//                reportError(getResource(), c.offset, c.length, "Column does not belong to the table.");
            }
		}
		int found=0;
		for(int j=0; j < tables.size(); j++)
		{
			Table t = (Table)tables.get(j);
			// is this table actually an alias?
			Table table = checkForAlias(t.table);
			if(table != null)
			{
				if(table.table!=null)
				{
					t.alias = t.table;
					t.table = table.table;
				}
			}
			if(db.columnExists(t.table, c.column)==true)
			{
				if(t.alias != null)
				{
					if(!c.tables.contains(t.alias))
					{
						c.tables.add(t.alias);// might want to change this to the complete Table instance
						found++;
					}
				}else{
					if(!c.tables.contains(t.table))
					{
						c.tables.add(t.table); 
						found++;
					}
				}
			}
		}
		if(found == 0)
		{
			// a quick fix might be to look for tables that define this column...
			// another quick fix might check for typo's in the column name...
			// both should be executed when the user wants to see the quick fixes
			// because these take longer than most...
			HashMap extra = new HashMap();
			extra.put("com.quantum.sql.grammar.error.id", unknownColumn);
			extra.put("QuickFix 1", "{All tables}");
			extra.put("ColumnName", c.column);
			String tableName = null;
			int nTables = 2;
			for(int k=0; k<tables.size();k++)
			{
				tableName = ((Table) tables.get(k)).table;
				if(tableName != null && tableName != "")
				{
					if(!extra.containsValue(tableName))
					{
						extra.put("Table " + nTables, tableName);
						String alias = getAliasForTable(tableName);
						if(alias==null)
						{
							extra.put("QuickFix " + nTables, tableName);
							extra.put("Alias " + nTables, "");
						}else{
							extra.put("QuickFix " + nTables, alias + "(" + tableName + ")");
							extra.put("Alias " + nTables, alias);
						}
						nTables++;
					}
				}
			}
			reportError(getResource(), c.offset, c.length, "Column not found in any table used in the statement", extra);
		}else if(found == 1){
            if(errorToReport){
                // there is only one table that contains this column...
                HashMap extra = new HashMap();
                extra.put("com.quantum.sql.grammar.error.id", mismatchedColumnTablePair);
                extra.put("QuickFixTable 1", "Switch tables");
                extra.put("ColumnName", c.column);
                extra.put("TableName", ((c.alias==null)?c.table:c.alias));
                extra.put("NewTableName 1", (String)c.tables.get(0));
                // we can also suggest all columns belonging to the table:
                int idx = 1;
                String[] cs = getColumnsForTable(c.table);
                for (int iColumn = 0; iColumn < cs.length; iColumn++)
                {
                    extra.put("QuickFixColumn " + idx, "Switch columns");
                    extra.put("NewColumnName " + idx, cs[iColumn]);
                    idx++;
                }
                reportError(getResource(), c.offset, c.length, "Column does not belong to table.", extra);
            }else{
    			c.table=(String)c.tables.get(0);
    			c.tables.clear();
            }
		}else{
            if(errorToReport){
                HashMap extra = new HashMap();
                extra.put("com.quantum.sql.grammar.error.id", mismatchedColumnTablePair);
                extra.put("ColumnName", c.column);
                extra.put("TableName", ((c.alias==null)?c.table:c.alias));
                for(int k=0; k<c.tables.size();k++)
                {
                    extra.put("QuickFixTable " + (k + 1), "Switch tables");
                    extra.put("NewTableName " + (k + 1), (String) c.tables.get(k));
                }
                // we can also suggest all columns belonging to the table:
                int idx = 1;
                String[] cs = getColumnsForTable(c.table);
                for (int iColumn = 0; iColumn < cs.length; iColumn++)
                {
                    extra.put("QuickFixColumn " + idx, "Switch columns");
                    extra.put("NewColumnName " + idx, cs[iColumn]);
                    idx++;
                }
                reportError(getResource(), c.offset, c.length, "Column does not belong to table.", extra);
            }else{
    			String msg = "Ambiguous column name: [";
    			// report known aliases too
    			HashMap extra = new HashMap();
    			extra.put("com.quantum.sql.grammar.error.id", ambiguousColumnName);
    			for(int k=0; k<c.tables.size();k++)
    			{
    				String alias = null;//getAliasForTable((String)c.tables.get(k));
    				if(alias==null)
    				{
    					msg+=(String)c.tables.get(k) + "|";
    					extra.put("QuickFix " + k, "Prepend with " + (String) c.tables.get(k));
    					extra.put("Insert " + k, (String) c.tables.get(k));
    				}else{
    					msg += (String)c.tables.get(k) + "-->" + alias + "|";
    					extra.put("QuickFix " + k, "Prepend with " + alias + "(" + (String)c.tables.get(k) + ")");
    					extra.put("Insert " + k, alias);
    				}
    			}
    			msg = msg.substring(0, msg.length() - 1);
    			msg += "]";
    			msg += "." + c.column;
    			reportError(getResource(), c.offset, c.length, msg, extra);
            }
		}
	}

    private String[] getColumnsForTable(String tableName)
    {
        List list = new ArrayList();
        try
        {
            dmd = getDatabaseMetaData();
    
            String aliasedTable = isTableAlias(tableName);
            ResultSet rsO = dmd.getColumns(null, null, aliasedTable, "%");
            String columnName = null;
            try {
                while (rsO.next()) {
                    columnName = rsO.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME);
                    list.add(columnName);
                }
            } finally {
                rsO.close();
            }
        }catch(SQLException e){
            System.out.println("handleTarget: " + e.getMessage());
            return (String[]) list.toArray(new String[list.size()]);
        }finally{
            
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
    
	private void isTableAliasDefined(Column c) {
		// did the user define an alias for this table. If so, uh-uh
		// the table name may be added by some of the rule checking above.
		// Only if the symbol preceding this column is a dot, we may fire the error...
		// and the symbol in front of the dot is not the alias...
//        System.out.println(c.column + " " + c.table + " " + c.columnAlias + " " + c.alias);
		String[] alias = getAliasesForTable(c.table);
		if(alias[0]!=null)
		{
			// there is at least one table that uses an alias
			// if the table name is listed among the aliases then
			// the table is used standalone too.
			// we might issue a warning or point them in the direction of a style guide.
			boolean ok = false;
			for(int i = 0; i < alias.length; i++)
			{
				if(alias[i].equalsIgnoreCase(c.table))
				{
					ok = true;
				}
			}
			if(!ok)
			{
				// keep the info
				if(alias.length == 1)
				{
					c.alias = alias[0];
				}
				char ch = 0;
                int offset;
                String possibleAlias = null;
				try
				{
					ch = getDocument().getChar(c.offset - 2); // ? - 1 extra?
                    if(ch == '.'){
                        // move backwards until whitespace
                        offset = c.offset - 3;
                        while(!isWhiteSpace(offset) && offset>0)
                        {
                            offset--;
                        }
                        possibleAlias = getDocument().get(offset + 1, c.offset - 3 - offset);
                    }
				}catch(BadLocationException e){}
				if(ch == '.')
				{
                    for(int j=0; j<alias.length; j++){
                        if(alias[j].equalsIgnoreCase(possibleAlias))
                            ok = true;
                    }
                    if(!ok){
    					String msg = "Column is referenced by table, but alias is defined: ";
    					HashMap extra = new HashMap();
    					extra.put("com.quantum.sql.grammar.error.id", columnReferencedByTable);
    					for(int j=0; j<alias.length; j++)
    					{
    						extra.put("QuickFix" + j, "Replace " + c.table + " with " + alias[j]);
    						extra.put("Replace" + j, alias[j]);
    						msg+=alias[j] + ", ";
    					}
    					msg = msg.substring(0, msg.length()-2);
    					reportError(getResource(), c.offset - c.table.length() - 1, c.table.length() , msg, extra);
                    }
				}
			}
		}
	}

	private void isAlias(Database db, Column c) {
		// is this an alias name?
		Table table = checkForAlias(c.table);
		if(table != null)
		{
			if(table.table != null)
			{
				if(db.columnExists(table.table, c.column) == true)
				{
					c.alias = c.table;
					c.table = table.table;
				}
			}
		}
	}

	private void standaloneColumn(Column c) {
		// this means we have found a standalone column
		Column column = checkForColumnAlias(c.column);
		if(column!=null)
		{
			// this means our standalone column was actually an ... alias
			c.columnAlias = c.column;
			c.column = column.column;
			c.table = column.table;
			// if the table is an alias that should be suggested too.
			String alias = getAliasForTable(c.table);
			if(alias == null)
			{
				alias = c.table;
			}
			HashMap extra = new HashMap();
			extra.put("QuickFix", "Replace " + c.columnAlias + " with " + alias + "." + c.column);
			extra.put("Remove", c.columnAlias);
			extra.put("Insert", alias + "." + c.column);
			extra.put("com.quantum.sql.grammar.error.id", aliasOutsideSelectList);
			reportError(getResource(), c.offset, c.length, "Alias can only be used in select list. Use " + alias + "." + c.column, extra);
		}
	}

	public void checkTables()
	{
		Database db = null;
		try
		{
			db = getBookmark().getDatabase();
		}catch(NotConnectedException e){return;}
		fromTables.clear();
		for(int i=0; i < tables.size(); i++)
		{
			Table t = (Table)tables.get(i);
			try{
				// is it a known alias?
				Table table = checkForAlias(t.table);
				if(table!=null)
				{
					if(table.table!=null)
					{
						t.alias = t.table;
						t.table = table.table;
					}
				}
				if(db.tableExists(t.table)==false)
				{
					// should we do the same stuff as with the column?
					HashMap extra = new HashMap();
					extra.put("com.quantum.sql.grammar.error.id", unknownTable);
					extra.put("TableName", t.table);
                    extra.put("AliasName", t.alias);
                    // add the known columns to the marker
                    int columnCounter = 0;
                    for(int j=0; j<columns.size(); j++)
                    {
                        Column c = (Column) columns.get(j);
                        if(c.table != null)
                        {
                            if(c.table.equalsIgnoreCase(t.table))
                            {
                                extra.put("ColumnName" + columnCounter, c.column);
                                columnCounter++;
                            }
                        }
                    }
					reportError(getResource(), t.offset, t.length, "Unknown table", extra);
				}else{
					// is the table part of the from clause?
					boolean bFound = false;
					for(int j=0; j < tables.size(); j++)
					{
						Table tt = (Table)tables.get(j);
						if(tt.table.equals(t.table) && tt.state.equals(Sql92SelectParser.STATE_FROM)){
							bFound = true;
						}
					}
					if(!bFound)
					{
						HashMap extra = new HashMap();
						extra.put("com.quantum.sql.grammar.error.id", unknownTableInFromClause);
						if(fromFound)
						{
							extra.put("QuickFix 1", "Add inner join " + t.table);
							extra.put("InsertBeforeWhere 1", "\r\ninner join " + t.table + "\r\n");
							extra.put("QuickFix 2", "Add left outer join " + t.table);
							extra.put("InsertBeforeWhere 2", "\r\nleft outer join " + t.table + "\r\n");
							extra.put("QuickFix 3", "Add right outer join " + t.table);
							extra.put("InsertBeforeWhere 3", "\r\nright outer join " + t.table + "\r\n");
						}else{
							extra.put("QuickFix 1", "Add from " + t.table);
							extra.put("InsertBeforeWhere 1", "\r\nfrom " + t.table + "\r\n");
						}
						reportError(getResource(), t.offset, t.length, "Table not part of from clause", extra);
					}else{
						// is there another table in the from clause that is the same?
						if(t.state == Sql92SelectParser.STATE_FROM)
						{
							if(t.alias == null || t.alias.equals(""))
							{
								if(fromTables.contains(t.table))
								{
									// make sure this is not the same object as itself
									for(int k=0; k < fromTables.size(); k++)
									{
										String tt = (String)fromTables.get(k);
										if(tt.equalsIgnoreCase(t.table)){
											reportError(getResource(), t.offset, t.length, t.table + " interface is exposed more than once");
										}
									}
								}else{
									fromTables.add(t.table);
								}
							}else{
								if(fromTables.contains(t.alias))
								{
									for(int k=0; k < fromTables.size(); k++)
									{
										String tt = (String)fromTables.get(k);
										if(tt.equalsIgnoreCase(t.alias)){
											reportError(getResource(), t.offset, t.length, t.alias + " interface is exposed more than once");
										}
									}
								}else{
									fromTables.add(t.alias);
								}
							}
						}
					}
				}
			}catch(NullPointerException e){
			}
		}
	}	
	
	public void report(int severity,IResource res, int column, int size, String msg, HashMap extra)
	{
		HashMap m = new HashMap();
		m.put(IMarker.MESSAGE, msg);
		m.put(IMarker.CHAR_START, new Integer((column==0)?0:column-1));
		m.put(IMarker.CHAR_END, new Integer(column+size-1));
		m.put(IMarker.SEVERITY, new Integer(severity));
		if(extra != null)m.putAll(extra);
		try
		{
			MarkerUtilities.createMarker(res, m, IMarker.PROBLEM);
			return;
		}catch(CoreException e){}
		return ;
	}

	public void report(int severity,IResource res, int column, int size, String msg)
	{
		HashMap m = new HashMap();
		m.put(IMarker.MESSAGE, msg);
		m.put(IMarker.CHAR_START, new Integer((column==0)?0:column-1));
		m.put(IMarker.CHAR_END, new Integer(column+size-1));
		m.put(IMarker.SEVERITY, new Integer(severity));
		try
		{
			MarkerUtilities.createMarker(res, m, IMarker.PROBLEM);
			return;
		}catch(CoreException e){}
		return ;
	}
	
	public void reportWarning(IResource res, int column, int size, String msg)
	{
		report(IMarker.SEVERITY_WARNING, res, column, size, msg, null);
	}

	public void reportError(IResource res, int column, int size, String msg)
	{
		report(IMarker.SEVERITY_ERROR, res, column, size, msg, null);
        nErrors++;
	}

	public void reportWarning(IResource res, int column, int size, String msg, HashMap extra)
	{
		report(IMarker.SEVERITY_WARNING, res, column, size, msg, extra);
	}

	public void reportError(IResource res, int column, int size, String msg, HashMap extra)
	{
		report(IMarker.SEVERITY_ERROR, res, column, size, msg, extra);
        nErrors++;
	}

	Table checkForAlias(String tableName)
	// returns the table object
	{
		for(int i=0; i < tables.size(); i++)
		{
			Table t = (Table)tables.get(i);
			if(t.alias!=null)
			{
				if(t.alias.equalsIgnoreCase(tableName))return t;
			}
		}
		return null;
	}

	Column checkForColumnAlias(String columnName)
	// returns a column object
	{
		for(int i=0; i < columns.size(); i++)
		{
			Column c = (Column)columns.get(i);
			if(c.columnAlias!=null)
			{
				// what about the table name (not thinking about schema now, o yes I was)?
				if(c.columnAlias.equalsIgnoreCase(columnName)){
					// should we delete the alias from our columns list? Yes we should.
					return new Column(c.column, c.table, 0, 0, state);
				}
			}
		}
		return null;
	}
	
	String getAliasForTable(String tableName)
	{
		// This fails for statement like SELECT A.D, B.D, D FROM C A, B A, A
		// where A is a table containing the column D
		// what we want to know is not whether tableName is aliased
		// but if it is also used alone.
		// furthermore more than one alias might be defined, so this 
		// should return a list
		for(int i=0; i < tables.size(); i++)
		{
			Table t = (Table)tables.get(i);
			if(t.table.equalsIgnoreCase(tableName))
			{
				if(t.alias!=null)return t.alias;
			}
		}
		return null;
	}

	public String[] getAliasesForTable(String tableName)
	{
		// This fails for statement like SELECT A.D, B.D, D FROM C A, B A, A
		// where A is a table containing the column D
		// what we want to know is not whether tableName is aliased
		// but if it is also used alone.
		// furthermore more than one alias might be defined, so this 
		// should return a list
		ArrayList a = new ArrayList(); // i do not know how to name them anymore...
		for(int i=0; i < tables.size(); i++)
		{
			Table t = (Table)tables.get(i);
			if(t.state == Sql92SelectParser.STATE_FROM)
			{
				if(t.table.equalsIgnoreCase(tableName))
				{
					if(t.alias!=null)
					{
						if(!a.contains(t.alias))a.add(t.alias);
					}else{
						// it looks like this table is used without alias
						// let's just put it in the results and see what happens
						if(!a.contains(t.table))a.add(t.table);
					}
				}
			}
		}
		return (String []) a.toArray(new String[a.size()]);
	}
	
    String strip(String c)
    {
        // Only strip those characters that are supplied in the
        // Database Metadata: getIdentifierQuoteString()
        // funny enough this means no [] for SQL Server
        String d = c;
        if(bm != null)
        {
            try {
                String quotes = bm.getDatabase().getMetaData().getIdentifierQuoteString();
                for(int i=0; i<quotes.length(); i++){
                    d = removeCharFromString(d, quotes.substring(i,1));
                }
            } catch (NotConnectedException e) {
            } catch (SQLException e) {
            }
            
        }
//      d = removeCharFromString(c, "[");
//      d = removeCharFromString(d, "]");
//      d = removeCharFromString(d, "\"");
//        d = removeCharFromString(d, "`");
        return d;
    }
	
	public String removeCharFromString(String s, String c)
	{
		if(s==null)return s;
	    while(s.indexOf(c) != -1)
	    {
	        int position = s.indexOf(c);

	        s = s.substring(0, position).concat(s.substring(position + 1));
	    }
	    return s;
	}

	DatabaseMetaData getDatabaseMetaData()
	{
		if(dmd==null)
		{
			try
			{
				dmd = getBookmark().getDatabase().getMetaData();
				// These could be cached? Instantiated only once? Once per model is acceptable
                // TODO: Connect this model to the graphical editor.
				dataTypes = getBookmark().getDataTypes(); 
			}catch(NotConnectedException e){
	        	ExceptionDisplayDialog.openError(getShell(), "Not connected", "Please connect.\r\nTable and column name checks are disabled, \r\nwhen you are not connected.\r\nAnd content assist, too.\r\nAs well as quick fixes.", e);
			}catch(SQLException e){}
		}
		return dmd;
	}

	public void setTokens(Map tokens)
	{
		this.tokens = tokens;
	}

	public IToken nextToken() 
    {
		int type = 0;
        String tokenText = null;
		if(keepToken)
		{
			keepToken = false;
		}else{
            if(lexer != null)
            {
    			try
    			{
    				aToken = lexer.nextToken();
    				type = aToken.getType();
                    tokenText = aToken.getText();
                    lastTokenOffset = aToken.getColumn();
                    lastTokenLength = (tokenText == null)?0:tokenText.length();
    			}catch(Exception e){
//    				System.out.println("nextToken: " + e.getMessage());
    				type = Sql92ParserTokenTypes.EOF; // to force a quit.
                    lastTokenOffset = aToken.getColumn();
                    lastTokenLength = 0;
    			}
            }else{
                if(lastTokenIndex>=qTokens.size())
                {
                    type = Sql92ParserTokenTypes.EOF;
                }else{
                    QToken q = (QToken) qTokens.get(lastTokenIndex);
                    type = q.getType();
                    tokenText = q.getText();
                    lastTokenOffset = q.getOffset();
                    lastTokenLength = (tokenText==null)?0:tokenText.length();
                    lastTokenIndex++;
                }
            }
		}
        
        // the requested range has been handled..
        if (lastTokenOffset >= returnEOFWhenOffset) {
            return Token.EOF;
        }
		
        switch(type)
		{
		case Sql92ParserTokenTypes.QuotedIdentifier:
		case Sql92ParserTokenTypes.NonQuotedIdentifier:
		{
            if(isRecognizedTable(tokenText))
            {
                type = Sql92ParserTokenTypes.TABLE_IDENTIFIER;
            }else if(isRecognizedColumn(tokenText)){
                type = Sql92ParserTokenTypes.COLUMN_IDENTIFIER;
            }else if(isRecognizedTableAlias(tokenText)){
                type = Sql92ParserTokenTypes.ALIAS_IDENTIFIER;
            }else if(isRecognizedColumnAlias(tokenText)){
                type = Sql92ParserTokenTypes.COLUMN_IDENTIFIER_ALIAS;
            }
		}
		}
		switch(type)
		{
		case Sql92ParserTokenTypes.COLUMN_IDENTIFIER:
		case Sql92ParserTokenTypes.CONDITION_COLUMN_IDENTIFIER:
		case Sql92ParserTokenTypes.COLUMN_IDENTIFIER_ALIAS:
		{
			return (IToken)tokens.get(SQLColorConstants.sCOLUMN);
		}
		case Sql92ParserTokenTypes.TABLE_IDENTIFIER:
		case Sql92ParserTokenTypes.CONDITION_TABLE_IDENTIFIER:
		{
			return (IToken)tokens.get(SQLColorConstants.sTABLE);
		}
		case Sql92ParserTokenTypes.ALIAS_IDENTIFIER:
		{
			return (IToken)tokens.get(SQLColorConstants.sTABLE);
		}
		case Sql92ParserTokenTypes.ASCIIStringLiteral:
		case Sql92ParserTokenTypes.UnicodeStringLiteral:
		{
			return (IToken)tokens.get(SQLColorConstants.sNUMERIC);
		}
		case Sql92ParserTokenTypes.Number:
		case Sql92ParserTokenTypes.Real:
		case Sql92ParserTokenTypes.Integer:
		{
			return (IToken)tokens.get(SQLColorConstants.sNUMERIC);
		}
		case Sql92ParserTokenTypes.NonQuotedIdentifier:
		case Sql92ParserTokenTypes.QuotedIdentifier:
		{
			return (IToken)tokens.get((parserOK())?SQLColorConstants.sCOMMENT:SQLColorConstants.sDEFAULT);
		}
		case Sql92ParserTokenTypes.Variable:
		{
			return (IToken)tokens.get(SQLColorConstants.sVARIABLE);
		}
		case Sql92ParserTokenTypes.EOF:
		{
			return Token.EOF;
		}
		case Sql92ParserTokenTypes.MultiLineComment:
		case Sql92ParserTokenTypes.SingleLineComment:
		{
			return (IToken)tokens.get(SQLColorConstants.sCOMMENT);
		}
        case Sql92ParserTokenTypes.SELECT:
        case Sql92ParserTokenTypes.ALL:
        case Sql92ParserTokenTypes.DISTINCT:
        case Sql92ParserTokenTypes.ON:
        case Sql92ParserTokenTypes.FROM:
        case Sql92ParserTokenTypes.WHERE:
        case Sql92ParserTokenTypes.GROUP:
        case Sql92ParserTokenTypes.BY:
        case Sql92ParserTokenTypes.HAVING:
        case Sql92ParserTokenTypes.UNION:
        case Sql92ParserTokenTypes.INTERSECT:
        case Sql92ParserTokenTypes.EXCEPT:
        case Sql92ParserTokenTypes.ORDER:
        case Sql92ParserTokenTypes.ASC:
        case Sql92ParserTokenTypes.DESC:
        case Sql92ParserTokenTypes.LIKE:
        case Sql92ParserTokenTypes.AND:
        case Sql92ParserTokenTypes.OR:
        case Sql92ParserTokenTypes.LEFT:
        case Sql92ParserTokenTypes.RIGHT: 
        case Sql92ParserTokenTypes.INNER:
        case Sql92ParserTokenTypes.OUTER:
        case Sql92ParserTokenTypes.JOIN:
        case Sql92ParserTokenTypes.AS:
        case Sql92ParserTokenTypes.BETWEEN:
        case Sql92ParserTokenTypes.EXISTS:
        case Sql92ParserTokenTypes.IN:
        case Sql92ParserTokenTypes.IS:
        case Sql92ParserTokenTypes.NOT:
        case Sql92ParserTokenTypes.NULL:
            // non select statement keywords
        case Sql92ParserTokenTypes.ALTER:
        case Sql92ParserTokenTypes.CREATE:
        case Sql92ParserTokenTypes.UPDATE:
        case Sql92ParserTokenTypes.TABLE:
        case Sql92ParserTokenTypes.COLUMN:
        case Sql92ParserTokenTypes.PROCEDURE:
        case Sql92ParserTokenTypes.FUNCTION:
        case Sql92ParserTokenTypes.SET:
        case Sql92ParserTokenTypes.BEGIN:
        case Sql92ParserTokenTypes.END:
        case Sql92ParserTokenTypes.IF:
        case Sql92ParserTokenTypes.THEN:
        case Sql92ParserTokenTypes.ELSE:
        case Sql92ParserTokenTypes.COMMIT:
        case Sql92ParserTokenTypes.ROLLBACK:
        case Sql92ParserTokenTypes.CURSOR:
        case Sql92ParserTokenTypes.DECLARE:
        case Sql92ParserTokenTypes.DEFAULT:
        case Sql92ParserTokenTypes.DELETE:
        case Sql92ParserTokenTypes.INSERT:
        case Sql92ParserTokenTypes.DROP:
        case Sql92ParserTokenTypes.EXEC:
        case Sql92ParserTokenTypes.EXECUTE:
        case Sql92ParserTokenTypes.GRANT:
        case Sql92ParserTokenTypes.INDEX:
        case Sql92ParserTokenTypes.KEY:
        case Sql92ParserTokenTypes.OPEN:
        case Sql92ParserTokenTypes.CLOSE:
        case Sql92ParserTokenTypes.DEALLOCATE:
        case Sql92ParserTokenTypes.PRIMARY:
        case Sql92ParserTokenTypes.REFERENCES:
        case Sql92ParserTokenTypes.READ:
        case Sql92ParserTokenTypes.RETURN:
        case Sql92ParserTokenTypes.REVOKE:
        case Sql92ParserTokenTypes.ROWCOUNT:
        case Sql92ParserTokenTypes.SCHEMA:
        case Sql92ParserTokenTypes.TRANSACTION:
        case Sql92ParserTokenTypes.TRIGGER:
        case Sql92ParserTokenTypes.VIEW:
        {
        return (IToken)tokens.get(SQLColorConstants.sKEYWORD);
		}
        }
		return (IToken)tokens.get(SQLColorConstants.sDEFAULT);
	}
	
	public int getTokenOffset()
	{
        return lastTokenOffset - 1; // i do not understand this...
	}
	
	public int getTokenLength()
	{
        return lastTokenLength;
	}

    private boolean isRecognizedTableAlias(String name)
    {
        for(int i=0; i< tables.size(); i++)
        {
            Table t = (Table) tables.get(i);
            if(t.alias != null)
            {
                if(t.alias.equalsIgnoreCase(name))return true;
            }
        }
        return false;
    }
    
    private boolean isRecognizedTable(String name)
    {
        for(int i=0; i< tables.size(); i++)
        {
            Table t = (Table) tables.get(i);
            if(t.table != null)
            {
                if(t.table.equalsIgnoreCase(name))return true;
            }
        }
        return false;
    }
    
    private boolean isRecognizedColumnAlias(String name)
    {
        for(int i=0; i< columns.size(); i++)
        {
            Column c = (Column) columns.get(i);
            if(c.columnAlias != null)
            {
                if(c.columnAlias.equalsIgnoreCase(name))return true;
            }
        }
        return false;
    }
    
    private boolean isRecognizedColumn(String name)
    {
        for(int i=0; i< columns.size(); i++)
        {
            Column c = (Column) columns.get(i);
            if(c.column != null)
            {
                if(c.column.equalsIgnoreCase(name))return true;
            }
        }
        return false;
    }
        
	public String[] getTables()
	{
		// This will show the tables/aliases used in the statement
		//checkSyntax(); // this slows everything down...
		if(tables.size()==0)return new String[0];
		ArrayList names=new ArrayList();
		for(int i=0; i<tables.size(); i++)
		{
			Table t = (Table) tables.get(i);
			if(t.alias!=null)
			{
				if(!names.contains(t.alias + "(" + t.table + ")"))
				{
					names.add(t.alias + "(" + t.table + ")");
				}
			}else if(!names.contains(t.table)){
				names.add(t.table);
			}
		}
		return (String[])names.toArray(new String[names.size()]);
	}
	
	public String[] getColumns(String table)
	{
		if(columns.size()==0)return new String[0];
		int par = table.indexOf('(');
		if(par == -1)
		{
			// no alias
		}else{
			table = table.substring(0, par);
		}
		
		ArrayList names=new ArrayList();
		for(int i=0; i<columns.size(); i++)
		{
			Column c = (Column)columns.get(i);
			if(c.alias!=null)
			{
				if(c.alias.equalsIgnoreCase(table))
				{
					if(c.columnAlias!=null)
					{
						if(!names.contains(c.alias + "(" + c.column + ")"))names.add(c.alias + "(" + c.column + ")");
					}else{
						if(!names.contains(c.column))names.add(c.column);
					}
				}
			}else if(c.table!=null){
				if(c.table.equalsIgnoreCase(table)){
					if(c.columnAlias!=null)
					{
						if(!names.contains(c.columnAlias + "(" + c.column + ")"))names.add(c.columnAlias + "(" + c.column + ")");
					}else{
						if(!names.contains(c.column))names.add(c.column);
					}
				}
			}
		}
		return (String[])names.toArray(new String[names.size()]);
	}

    public String[] getTargetColumns(String table)
    {
        if(columns.size()==0)return new String[0];
        int par = table.indexOf('(');
        if(par == -1)
        {
            // no alias
        }else{
            table = table.substring(0, par);
        }
        
        ArrayList names=new ArrayList();
        for(int i=0; i<columns.size(); i++)
        {
            Column c = (Column)columns.get(i);
            if(c.state.equals(Sql92SelectParser.STATE_TARGET))
            {
                if(c.alias!=null)
                {
                    if(c.alias.equalsIgnoreCase(table))
                    {
                        if(c.columnAlias!=null)
                        {
                            if(!names.contains(c.alias + "(" + c.column + ")"))names.add(c.alias + "(" + c.column + ")");
                        }else{
                            if(!names.contains(c.column))names.add(c.column);
                        }
                    }
                }else if(c.table!=null){
                    if(c.table.equalsIgnoreCase(table)){
                        if(c.columnAlias!=null)
                        {
                            if(!names.contains(c.columnAlias + "(" + c.column + ")"))names.add(c.columnAlias + "(" + c.column + ")");
                        }else{
                            if(!names.contains(c.column))names.add(c.column);
                        }
                    }
                }
            }
        }
        return (String[])names.toArray(new String[names.size()]);
    }
	
	private void setASTRoot(SqlAST root)
	{
		this.root = root;
	}
	
	private SqlAST getASTRoot()
	{
		return root;
	}
    
    public int getNumberOfErrors()
    {
        return nErrors;
    }

    private class TableAndAlias{
        String table;
        String alias;
        
        TableAndAlias(String table, String alias)
        {
            this.table = table;
            this.alias = alias;
        }

        public boolean equals(Object o)
        {
            if (o == null || !(o instanceof TableAndAlias))
                return false;
            TableAndAlias t = (TableAndAlias) o;

            if (t.table.equals(table)){
                if(t.alias == null){
                    if (alias == null){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    if (alias == null){
                        return false;
                    }else{
                        return alias.equalsIgnoreCase(t.alias);
                    }
                }
            }else{
                return false;
            }
        }

    }
    
    public void createERDiagram(IFile f, String query) {
        /*
         * The file exists with no contents.
         * The query has been parsed successfully.
         * All objects of the model contain useful data.
         * The editor should be open.
         * Now what?
         * We can start by adding the tables.
         */
        IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IEditorPart part = page.getActiveEditor();
        EntityRelationEditor editor = null;
        List gTables = new ArrayList();
        if(part instanceof EntityRelationEditor)
        {
            editor = (EntityRelationEditor) part;
        }else{
            return;
        }
        EntityRelationDiagram diagram = editor.getModel();
        for(int i=0; i<this.tables.size(); i++){
            Table t = (Table) this.tables.get(i);
            TableAndAlias taa = new TableAndAlias(t.table, t.alias);
            if(gTables.contains(taa)){
                // ignore
            }else{
                com.quantum.editors.graphical.model.Table graphTable = new com.quantum.editors.graphical.model.Table(t.table, t.alias, diagram);
                diagram.addTable(graphTable);
                graphTable.modifyBounds(new Rectangle(gTables.size()*100, gTables.size()*100, -1, -1));
                gTables.add(taa);
                addColumns(graphTable, diagram);
            }
        }
    }

    private void addColumns(com.quantum.editors.graphical.model.Table graphTable, EntityRelationDiagram diagram) {
        Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        com.quantum.model.Table t = new TableImpl(bm, null, graphTable
                .getTableName(), false);
        try {
            com.quantum.model.Column[] cs = t.getColumns();
            ForeignKey[] ikeys = t.getImportedKeys();
            ForeignKey[] ekeys = t.getExportedKeys();

            for (int i = 0; i < cs.length; i++) {
                // is this column primary, foreign or selected?
                boolean bForeign = false;
                for (int j = 0; j < ikeys.length; j++) {
                    for (int k = 0; k < ikeys[j].getNumberOfColumns(); k++) {
                        if (ikeys[j].getForeignColumnName(k).equalsIgnoreCase(
                                cs[i].getName())) {
                            bForeign = true;
                        }
                    }
                }
                boolean bSelected = false;
                for (int j = 0; j < this.columns.size(); j++) {
                    Column c = (Column) this.columns.get(j);
                    if (c.table.equalsIgnoreCase(t.getName())) { // yes, same table
                        if(c.column.equalsIgnoreCase(cs[i].getName())){ // yes, same column
                            if(graphTable.getAlias() != null && c.alias != null){
                                if(graphTable.getAlias().equalsIgnoreCase(c.alias)){ // yes, same alias
                                    if (c.state.equalsIgnoreCase(Sql92SelectParser.STATE_TARGET)) {
                                        bSelected = true;
                                    }
                                }
                            }else if(graphTable.getAlias() == null && c.alias == null){
                                // no aliases defined, so it is a match
                                if (c.state.equalsIgnoreCase(Sql92SelectParser.STATE_TARGET)) {
                                    bSelected = true;
                                }
                            }
                        }
                    }
                }

                com.quantum.editors.graphical.model.Column c = new com.quantum.editors.graphical.model.Column(
                        cs[i].getName(), cs[i].getTypeName(), cs[i].isPrimaryKey(),
                        bForeign, cs[i].getSize(), cs[i].getNumberOfFractionalDigits(),cs[i].isNumeric(), cs[i].isReal(), bSelected);
                graphTable.addColumn(c);
            }
            // now the relations...
            // I think we should use the query instead of the stored database...
            // Maybe forcing to use relations will encourage proper definition of them.
//            if (graphTable.getName().equalsIgnoreCase(graphTable.getTableName())) {
//                // this is not an aliased table
                for (int i = 0; i < ekeys.length; i++) {
                    for (int j = 0; j < diagram.getTables().size(); j++) {
                        com.quantum.editors.graphical.model.Table t2 = (com.quantum.editors.graphical.model.Table) diagram.getTables().get(j);
                        // Using getName() here will result in aliased
                        // tables not getting any relations
                        if (t2.getTableName().equalsIgnoreCase(ekeys[i].getForeignEntityName())) {
                            for (int l = 0; l < ekeys[i].getNumberOfColumns(); l++) {
                                String foreignColumnName = ekeys[i].getForeignColumnName(l);
                                String localColumnName = ekeys[i].getLocalColumnName(l);
                                new com.quantum.editors.graphical.model.Relationship(ekeys[i].getName(), t2, foreignColumnName, graphTable, localColumnName);
                            }
                        }
                    }
                }
//            }
            for (int i = 0; i < ikeys.length; i++) {
                com.quantum.editors.graphical.model.Table matching = null;
                boolean bCreated = false;
                for (int j = 0; j < diagram.getTables().size(); j++) {
                    com.quantum.editors.graphical.model.Table t2 = (com.quantum.editors.graphical.model.Table) diagram.getTables().get(j);
                    if (t2.getTableName().equalsIgnoreCase(ikeys[i].getLocalEntityName())) {
                        matching = t2;
                        if (t2.getPrimaryKeyRelationships().size() == 0) {
                            // create only one
                            for (int l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                                String foreignColumnName = ikeys[i].getForeignColumnName(l);
                                String localColumnName = ikeys[i].getLocalColumnName(l);
                                new Relationship(ikeys[i].getName(), graphTable, foreignColumnName, t2, localColumnName);
                                bCreated = true;
                            }
                            break;
                        } else {
                            for (int k = 0; k < t2.getPrimaryKeyRelationships().size(); k++) {
                                Relationship r = (Relationship) t2.getPrimaryKeyRelationships().get(k);
                                if (!r.getPrimaryKeyTable().getName().equalsIgnoreCase(t2.getTableName())) {
                                    // create
                                    for (int l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                                        String foreignColumnName = ikeys[i].getForeignColumnName(l);
                                        String localColumnName = ikeys[i].getLocalColumnName(l);
                                        new Relationship(ikeys[i].getName(), graphTable, foreignColumnName, t2, localColumnName);
                                        bCreated = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                // if we get here no connection was possible to an aliased table.
                // so we add it to the last table that matched.
                if(matching != null && bCreated == false){
                    for (int l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                        String foreignColumnName = ikeys[i].getForeignColumnName(l);
                        String localColumnName = ikeys[i].getLocalColumnName(l);
                        new Relationship(ikeys[i].getName(), graphTable, foreignColumnName, matching, localColumnName);
                        bCreated = true;
                    }
                }
            }

        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }

    }
}
