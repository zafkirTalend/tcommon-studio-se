package com.quantum.sql.grammar.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.quantum.QuantumPlugin;

public class MismatchedColumnTable implements IMarkerResolutionGenerator2 {

    private IDocument document;
    private String columnName;
    private String tableName;

    public MismatchedColumnTable() {
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

    public boolean hasResolutions(IMarker marker) {
        return true;
    }

    public IMarkerResolution[] getResolutions(IMarker marker) {
        List ars = new ArrayList();
        try
        {
            columnName = (String)marker.getAttribute("ColumnName");
            tableName = (String)marker.getAttribute("TableName");
            int k = 1;
            while(true)
            {
                String label = (String)marker.getAttribute("QuickFixTable " + k);
                if(label == null)break;
                if(label.equalsIgnoreCase("Switch tables"))
                {
                    String newTableName = (String)marker.getAttribute("NewTableName " + k);
                    ars.add(new MismatchedColumnQuickFix(document, tableName, newTableName, true));
                }
                k++;
            }
            k = 1;
            while(true)
            {
                String label = (String)marker.getAttribute("QuickFixColumn " + k);
                if(label == null)break;
                if(label.equalsIgnoreCase("Switch columns"))
                {
                    String newColumnName = (String)marker.getAttribute("NewColumnName " + k);
                    ars.add(new MismatchedColumnQuickFix(document, columnName, newColumnName, false));
                }
                k++;
            }
        }catch(Exception e){}
        return (IMarkerResolution[])ars.toArray(new IMarkerResolution[ars.size()]);
    }
}
