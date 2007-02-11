package com.quantum.sql.grammar.quickfix;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.model.Bookmark;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.connection.NotConnectedException;
//import com.quantum.sql.grammar.quickfix.UnknownColumn;

public class UnknownColumn implements IMarkerResolutionGenerator2 {

	private IDocument document;
	private String columnName;
	
	public UnknownColumn() {
		super();
		IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if(part instanceof AbstractTextEditor)
		{
			ITextEditor editor = (ITextEditor) part;
			IDocumentProvider dp = editor.getDocumentProvider();
			document = dp.getDocument(editor.getEditorInput());
		}
	}

	public IMarkerResolution[] getResolutions(IMarker marker) {
		List ars = new ArrayList();
		try
		{
			columnName = (String)marker.getAttribute("ColumnName");
			Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
			int k = 1;
			while(true)
			{
				String label = (String)marker.getAttribute("QuickFix " + k);
				if(label == null)break;
				if(label.equals("{All tables}"))
				{
					// try to list all tables having a column named columnName
					// 
					String[] tableNames = null;
					try {
						tableNames = bm.getDatabase().getTablesForColumn(columnName);
					} catch (NotConnectedException e) {
					}
					for(int i=0; i<tableNames.length; i++)
					{
						ars.add(new UnknownColumnQuickFix(document, tableNames[i], tableNames[i]));
					}
				}else{
					// find a match based on the column names of the table because of a typo
					String tableName = (String)marker.getAttribute("Table " + k);
					String alias = (String)marker.getAttribute("Alias " + k);
					DatabaseMetaData dmd = null;
					List suggestions = new ArrayList();
					try {
						dmd = bm.getDatabase().getMetaData();
						for(int i=1; i<columnName.length();i++)
						{
							String tryThis = null;// = columnName;
							String suggestion = null;
							String leftSide = columnName.substring(0, i - 1);
							String rightSide = columnName.substring(i + 1);
					        tryThis = leftSide + "__" + rightSide;
//							tryThis = "%" + tryThis + "%";
							ResultSet rsO = dmd.getColumns(null, null, tableName, tryThis);
							try {
								while (rsO.next()) {
									suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME);
									String qualifiedColumnName = ((alias == "")? tableName : alias) + "." + suggestion;
									if(!suggestions.contains(qualifiedColumnName))
									{
										suggestions.add(qualifiedColumnName);
										ars.add(new UnknownColumnQuickFix2(document, tableName + "." + suggestion, qualifiedColumnName));
									}
								}
							} finally {
								rsO.close();
							}
						}
					} catch (NotConnectedException e) {
					} catch (SQLException e) {
					}
					
				}
				k++;
			}
		}catch(CoreException e){
		}
		return (IMarkerResolution[])ars.toArray(new IMarkerResolution[ars.size()]);
	}

    public boolean hasResolutions(IMarker marker) {
        return true;
    }
}
