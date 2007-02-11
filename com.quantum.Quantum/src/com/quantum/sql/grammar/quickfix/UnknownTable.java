package com.quantum.sql.grammar.quickfix;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
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

public class UnknownTable implements IMarkerResolutionGenerator2 {

	private IDocument document;
    private ITextEditor editor;
    
	public UnknownTable() {
		super();
		IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if(part instanceof AbstractTextEditor)
		{
			editor = (ITextEditor) part;
			IDocumentProvider dp = editor.getDocumentProvider();
			document = dp.getDocument(editor.getEditorInput());
		}
	}

	public IMarkerResolution[] getResolutions(IMarker marker) {
		// the table name cannot be matched to any table in the database
		List ars = new ArrayList();
		try
		{
			Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
            String tableName = (String)marker.getAttribute("TableName");
            String aliasName = (String)marker.getAttribute("AliasName");
			DatabaseMetaData dmd = null;
			List suggestions = new ArrayList();
			try {
				dmd = bm.getDatabase().getMetaData();
                
                // catch a typo, when two characters are interchanged.
				for(int i=1; i<tableName.length();i++)
				{
					String tryThis = null;// = columnName;
					String suggestion = null;
					String leftSide = tableName.substring(0, i - 1);
					String rightSide = tableName.substring(i + 1);
			        tryThis = leftSide + "__" + rightSide;
					ResultSet rsO = dmd.getTables(null, null, tryThis, null);
					try {
						while (rsO.next()) {
							suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME);
                            if(aliasName != null)
                            {
                                suggestion = suggestion + " " + aliasName;
                            }
							if(!suggestions.contains(suggestion))
							{
								suggestions.add(suggestion);
								ars.add(new UnknownTableResolution(document,"Change to: " + suggestion, suggestion));
							}
						}
					} finally {
						rsO.close();
					}
				}
                if(ars.size() != 0)
                {
                    // the typo fix worked, we leave now
                    return (IMarkerResolution[])ars.toArray(new IMarkerResolution[ars.size()]);
                }
                // do we have any column names?
                String[] columnNames = new String[10];
                String tmp = null;
                int idx=0;
                tmp = (String)marker.getAttribute("ColumnName" + idx);
                while(tmp != null){
                    columnNames[idx] = tmp;
                    idx++;
                    tmp = (String)marker.getAttribute("ColumnName" + idx);
                }
                int nColumns = idx;
                if(nColumns == 0)
                {
                    // we use the name again and remove from the end until we find a match
                    int i = tableName.length() - 1;
                    while(i >= 0)
                    {
                        String suggestion = null;
                        String tryThis = tableName.substring(0, i) + "%";
                        ResultSet rsO = dmd.getTables(null, null, tryThis, null);
                        boolean found = false;
                        try {
                            while (rsO.next()) {
                                suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME);
                                if(aliasName != null)
                                {
                                    suggestion = suggestion + " " + aliasName;
                                    if(!suggestions.contains(suggestion))
                                    {
                                        suggestions.add(suggestion);
                                        ars.add(new UnknownTableResolution(document, "Change to " + suggestion, suggestion));
                                        found = true;
                                    }
                                }else{
                                    if(!suggestions.contains(suggestion))
                                    {
                                        suggestions.add(suggestion);
                                        ars.add(new UnknownTableIsAliasResolution(tableName + " is alias for " + suggestion, suggestion, editor));
                                        ars.add(new UnknownTableResolution(document, "Change to " + suggestion, suggestion));
                                        found = true;
                                    }
                                }
                            }
                        } finally {
                            rsO.close();
                        }
                        if(found)
                            break;
                        else
                            i--;
                    }
                }else{
                    String suggestion = null;
                    ResultSet rsO = dmd.getTables(null, null, "%", null); // TODO: I still wonder about the schema here.
                    try {
                        while (rsO.next()) {
                            suggestion = rsO.getString(JDBCUtil.COLUMN_METADATA_TABLE_NAME);
                            // does this table contain our columns?
                            int columnCounter = 0;
                            for(int j=0; j < nColumns; j++)
                            {
                                ResultSet rs1 = dmd.getColumns(null, null, suggestion, columnNames[j]);
                                if(rs1.next())
                                {
                                    columnCounter++;
                                }
                                rs1.close();
                            }
                            if(nColumns == columnCounter)
                            {
                                if(aliasName != null)
                                {
                                    suggestion = suggestion + " " + aliasName;
                                    if(!suggestions.contains(suggestion))
                                    {
                                        suggestions.add(suggestion);
                                        ars.add(new UnknownTableResolution(document, "Change to " + suggestion, suggestion));
                                    }
                                }else{
                                    if(!suggestions.contains(suggestion))
                                    {
                                        suggestions.add(suggestion);
                                        ars.add(new UnknownTableIsAliasResolution(tableName + " is alias for " + suggestion, suggestion, editor));
                                        ars.add(new UnknownTableResolution(document, "Change to " + suggestion, suggestion));
                                    }
                                }
                            }
                        }
                    } finally {
                        rsO.close();
                    }
                }
			} catch (NotConnectedException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(CoreException e){
		}
		return (IMarkerResolution[])ars.toArray(new IMarkerResolution[ars.size()]);
	}
	
    private class UnknownTableResolution extends QuantumQuickFix
    {
        String insert;
    
        UnknownTableResolution(IDocument document, String label, String insert)
        {
            super(document, label);
            this.insert = insert;
        }
        
        public void run(IMarker marker)
        {
            int start = 0;
            int end = 0;
            try {
                start = ((Integer)marker.getAttribute(IMarker.CHAR_START)).intValue();
                end = ((Integer)marker.getAttribute(IMarker.CHAR_END)).intValue();
            } catch (CoreException e) {
                e.printStackTrace();
            }
            try {
                document.replace(start, end - start, insert);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            // do the bookkeeping
            super.setCharStart(start);
            super.setNumberOfCharsAdded(insert.length() - end + start);
            super.run(marker);
       }
    }
    
    private class UnknownTableIsAliasResolution implements IMarkerResolution
    {
        String label;
        String insert;
        AbstractTextEditor part;
        
        UnknownTableIsAliasResolution(String label, String insert, ITextEditor part)
        {
            this.label = label;
            this.insert = insert;
            this.part = (AbstractTextEditor) part;
        }
        
        public String getLabel() {
            return label;
        }
    
        public void run(IMarker marker) {
            int start = 0;
            try {
                start = ((Integer) marker.getAttribute(IMarker.CHAR_START))
                        .intValue();
            } catch (CoreException e) {
                e.printStackTrace();
            }
            try {
                part.getSelectionProvider().setSelection(null);
                document.replace(start - 1, 0, " " + insert);
                // if the quick fix has been applied the error should disappear.
                IResource res = marker.getResource();
                try {
                    marker.delete();
                    // here we need to update the start and end of any marker
                    // after the one we have just deleted.
                    IMarker[] markers = res.findMarkers(IMarker.PROBLEM, false, IResource.DEPTH_INFINITE);
                    int added = insert.length() + 1;
                    for(int i=0; i<markers.length; i++){
                        int oldStart = ((Integer) markers[i].getAttribute(IMarker.CHAR_START)).intValue(); 
                        if(oldStart>(start - 1))
                        {
                            int oldEnd = ((Integer) markers[i].getAttribute(IMarker.CHAR_END)).intValue();
                            markers[i].setAttribute(IMarker.CHAR_START, oldStart + added );
                            markers[i].setAttribute(IMarker.CHAR_END, oldEnd + added);
                        }
                    }
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasResolutions(IMarker marker) {
        return true;
    }
}
