package com.quantum.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.quantum.ImageStore;
import com.quantum.model.Bookmark;
import com.quantum.model.Entity;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.bookmark.BookmarkView;
import com.quantum.view.bookmark.DbObjectNode;
import com.quantum.view.bookmark.EntityNode;
import com.quantum.view.bookmark.GroupNode;
import com.quantum.view.bookmark.PackageNode;
import com.quantum.view.bookmark.ProcedureNode;
import com.quantum.view.bookmark.QueryListNode;
import com.quantum.view.bookmark.QuickListNode;
import com.quantum.view.bookmark.SchemaNode;
import com.quantum.view.bookmark.TreeNode;


public class QuantumSearchPage extends DialogPage implements ISearchPage {

    private Text txtString;
    private Text txtNumber;
    private Text txtLike;
    private Text txtDate;
    private Text txtDate2;
    private Button btnMatch;
    private String likeSearch;
    private boolean exactly;
    private String toSearch;
    private String numberToSearch;
    private String dateToSearch;
    private String dateToSearch2;
    private int nItems;
    private boolean isLikeSearch;
    private boolean isNumberSearch;
    private boolean isTextSearch;
    private boolean isDateSearch;
    
    private ISearchPageContainer scontainer = null;

    public QuantumSearchPage() {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuantumSearchPage(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    public QuantumSearchPage(String title, ImageDescriptor image) {
        super(title, image);
        // TODO Auto-generated constructor stub
    }

    public void setVisible(boolean visible) {
        BookmarkView vw = BookmarkView.getInstance();
        StructuredSelection s = vw.getSelection();
        Entity entity = null;

        final List list = new ArrayList(s.toList());
        if(list.size()==0)
        {
            visible = false;
        }else{
            Object selection = list.get(0);
            Bookmark bookmark = null;
            if (selection instanceof TreeNode)
                bookmark = ((TreeNode)selection).getBookmark();
            if (bookmark == null) {
                visible = false;
            }
    
            String iconName = null;
            if (selection instanceof QuickListNode) {
            } else if (selection instanceof GroupNode) {
            } else if (selection instanceof SchemaNode) {
            } else if (selection instanceof EntityNode) {
            } else if (selection instanceof QueryListNode) {
            } else if (selection instanceof ProcedureNode) {
            } else if (selection instanceof PackageNode) {
            } else {
                visible = false;
            }
        }
        super.setVisible(visible);
    }
 
    private int getListItems(List list)
    {
        // Iterate the given list and count the items in it.
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Object object = iter.next();
            // If it's a DbObject, we can directly extract the metadata
            if ( object instanceof DbObjectNode ) {
                nItems++;
            // if it's a group of nodes, iterate the group of nodes
            } else if ( object instanceof GroupNode || object instanceof SchemaNode || object instanceof QuickListNode || object instanceof PackageNode ){
                TreeNode groupNode = (TreeNode) object;
                try{
                    Object[] children = groupNode.getChildren();
                    for (int i = 0; i < children.length; i++) {
                        if ( children[i] instanceof DbObjectNode ) {
                            nItems++;
                        } else if ( children[i] instanceof GroupNode) {
                            // If it's another group, recurse on it
                            getListItems( Arrays.asList(new GroupNode[] {(GroupNode)children[i]}));
                        } else if ( children[i] instanceof PackageNode) {
                            // If it's another group, recurse on it
                            getListItems( Arrays.asList(new PackageNode[] {(PackageNode)children[i]}));
                        }
                    }
                }catch(NotConnectedException e){
                }catch(SQLException e){}
            }
        }
        
        return nItems;
    }

    public boolean performAction() {
        BookmarkView vw = BookmarkView.getInstance();
        StructuredSelection s = vw.getSelection();
        toSearch = txtString.getText();
        exactly = btnMatch.getSelection();
        if (!exactly) {
            toSearch = toSearch.toLowerCase();
        }
        likeSearch = txtLike.getText();
        numberToSearch = txtNumber.getText();
        dateToSearch = txtDate.getText();
        dateToSearch2 = txtDate2.getText();
        if(dateToSearch2 == null) dateToSearch2="";
        isLikeSearch = (likeSearch != "");
        isTextSearch = (toSearch != ""); // Julen I did not quite get your thinking setting isTextSearch = isLikeSearch...
        isNumberSearch = (numberToSearch != "");
        isDateSearch = (dateToSearch != "");

        nItems = 0;
        Entity entity = null;

        final List list = new ArrayList(s.toList());
        Object selection = list.get(0);
        Bookmark bookmark = null;
        if (selection instanceof TreeNode)
            bookmark = ((TreeNode)selection).getBookmark();
        if (bookmark == null) {
            MessageDialog.openInformation(null,"Unsupported action","Search is unavailable for this selection");
            return true; // false has no use here because nothing can be repaired.
        }

        String iconName = null;
        // I am thinking of adding the COLUMN here: SELECT COLUMN FROM TABLE WHERE COLUMN (=|LIKE etcetera...
        
        // I would like to show this icon somewhere...
        if(selection instanceof QuickListNode){
            iconName = ImageStore.GROUP;
        }else if(selection instanceof GroupNode){
            iconName = ImageStore.ENTITYGROUP;
        }else if(selection instanceof SchemaNode){
            iconName = ImageStore.SCHEMA;
        }else if(selection instanceof EntityNode){
            entity = ((EntityNode) list.get(0)).getEntity();
            if(Entity.VIEW_TYPE.equals(entity.getType()))
            {
                iconName = ImageStore.VIEW;
            }else if(Entity.TABLE_TYPE.equals(entity.getType()))
            {
                iconName = ImageStore.TABLE;
            }else if(entity.isSynonym()){
                iconName = ImageStore.SYNONYM_TABLE;
            }
        }else if(selection instanceof QueryListNode){
            iconName = ImageStore.GROUP;
        }else if(selection instanceof ProcedureNode){
            iconName = ImageStore.PROCEDURE;
        }else if(selection instanceof PackageNode){
            iconName = ImageStore.ALL_PROCEDURES;
        }else{
            MessageDialog.openInformation(null, "Unsupported action", "Search is unavailable for this selection");
            return true;
        }
        nItems = getListItems(list);
        // show the result view
        
        // I think we cannot use the standard search result page because we
        // have no file based result. Even if we map file to table and so
        // on, due to the strong typing in Eclipse, we will not be able to
        // make it work. So we open our own tree....
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        try
        {
            page.showView("com.quantum.view.sqlsearchresultstree");
        }catch(PartInitException e){
        }
        if(isTextSearch){
            SearchResults.getInstance().addToResults("Search", "Text;" + toSearch + ";" + ((exactly)?"true;":"false;"), iconName);
        }else if(isLikeSearch){
            SearchResults.getInstance().addToResults("Search", "Like;" + likeSearch, iconName);
        }else if(isNumberSearch){
            SearchResults.getInstance().addToResults("Search", "Numeric;" + numberToSearch, iconName);
        }else if(isDateSearch){
            SearchResults.getInstance().addToResults("Search", "Date;" + dateToSearch + ((dateToSearch2!=null)?"-":"") + dateToSearch2, iconName);
        }
        SearchDatabaseQuery sdq = new SearchDatabaseQuery(list, toSearch, likeSearch, numberToSearch, dateToSearch, dateToSearch2, isLikeSearch, isTextSearch, isNumberSearch, isDateSearch, exactly, nItems);
        NewSearchUI.runQueryInForeground(scontainer.getRunnableContext(), sdq);
        return true;
    }

    public void setContainer(ISearchPageContainer container) {
        scontainer = container;
    }

    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, 0);
        GridLayout layout = new GridLayout(1, true);
        
        //layout.numColumns = 1;
        
        composite.setLayout(layout);
 
        GridData gd= new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        composite.setLayoutData(gd);

        Label lblHelp = new Label(composite, SWT.NONE);
        lblHelp.setText("Select just one search criterium");
        lblHelp.setFont(parent.getFont());

        Group strings = new Group(composite,SWT.SHADOW_ETCHED_OUT);
        strings.setText("Search for strings");
        
        GridLayout layoutStrings = new GridLayout(2, false);
        strings.setLayout(layoutStrings);
        
        Label lblString = new Label(strings, SWT.NONE);
        lblString.setText("&String");
        lblString.setFont(parent.getFont());
        GridData lblStringData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblStringData.widthHint = 115;
        lblString.setLayoutData(lblStringData);
        
        txtString = new Text(strings, SWT.BORDER);
        txtString.setText("");
        txtString.setFont(parent.getFont());
        GridData stringsData = new GridData(SWT.FILL, SWT.FILL, true, true);
        stringsData.widthHint = 250;
        txtString.setLayoutData(stringsData);
        
        Label lblMatch = new Label(strings, SWT.NONE);
        lblMatch.setText("&Match case");
        lblMatch.setFont(parent.getFont());
        GridData lblMatchData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblMatchData.widthHint = 115;
        lblMatch.setLayoutData(lblMatchData);
        
        btnMatch = new Button(strings, SWT.CHECK);
        btnMatch.setSelection(true);
        btnMatch.setFont(parent.getFont());
        
        Label likeRegexp = new Label(strings, SWT.NONE);
        GridData lblLikeData = new GridData(SWT.FILL, SWT.FILL, true, true);
        lblLikeData.widthHint = 115;
        likeRegexp.setLayoutData(lblLikeData);
        likeRegexp.setText("&LIKE regexp (%_)");
        likeRegexp.setFont(parent.getFont());
        
        txtLike = new Text(strings, SWT.BORDER);
        txtLike.setText("");
        txtLike.setFont(parent.getFont());
        GridData likeData = new GridData(SWT.FILL, SWT.FILL, true, true);
        likeData.widthHint = 250;
        txtLike.setLayoutData(likeData);
        
        Group numbers = new Group(composite, SWT.SHADOW_ETCHED_OUT);
        numbers.setText("Search for numeric values");
        GridLayout layoutNumbers = new GridLayout(2, false);
        numbers.setLayout(layoutNumbers);
        
        Label lblNumber = new Label(numbers, SWT.NONE);
        GridData numberData = new GridData(SWT.FILL, SWT.FILL, true, true);
        numberData.widthHint = 115;
        lblNumber.setLayoutData(numberData);
        lblNumber.setText("&Number");
        lblNumber.setFont(parent.getFont());
        
        txtNumber= new Text(numbers, SWT.BORDER);
        txtNumber.setText("");
        txtNumber.setFont(parent.getFont());
        GridData numericData = new GridData(SWT.FILL, SWT.FILL, true, true);
        numericData.widthHint = 250;
        txtNumber.setLayoutData(numericData);
    
        // Some day, dates search should be added
        Group dates = new Group(composite, SWT.SHADOW_ETCHED_OUT);
        dates.setText("Search for date values");
        GridLayout layoutDates = new GridLayout(3, false);
        dates.setLayout(layoutDates);
        Label lblDate = new Label(dates, SWT.NONE);
        GridData datesData = new GridData(SWT.FILL, SWT.FILL, true, true);
        datesData.widthHint = 115;
        lblDate.setLayoutData(datesData);
        lblDate.setText("&Start date");
        lblDate.setFont(parent.getFont());
        txtDate= new Text(dates, SWT.BORDER);
        txtDate.setText("");
        txtDate.setFont(parent.getFont());
        GridData dateData = new GridData(SWT.FILL, SWT.FILL, true, true);
        dateData.widthHint = 250;
        txtDate.setLayoutData(dateData);
        new Label(dates, SWT.NONE);
        Label lblDate2 = new Label(dates, SWT.NONE);
        GridData datesData2 = new GridData(SWT.FILL, SWT.FILL, true, true);
        datesData.widthHint = 115;
        lblDate2.setLayoutData(datesData);
        lblDate2.setText("&End date");
        lblDate2.setFont(parent.getFont());
        txtDate2= new Text(dates, SWT.BORDER);
        txtDate2.setText("");
        txtDate2.setFont(parent.getFont());
        GridData dateData2 = new GridData(SWT.FILL, SWT.FILL, true, true);
        dateData2.widthHint = 250;
        txtDate2.setLayoutData(dateData2);
        new Label(dates, SWT.NONE);

        setControl(composite);

    }
}
