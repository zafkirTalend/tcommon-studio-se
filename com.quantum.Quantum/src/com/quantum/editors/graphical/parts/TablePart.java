/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.parts;

import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import com.quantum.ImageStore;
import com.quantum.QuantumPlugin;
import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.ValidationEnabledGraphicalViewer;
import com.quantum.editors.graphical.directedit.AliasNameCellEditorValidator;
import com.quantum.editors.graphical.directedit.ExtendedDirectEditManager;
import com.quantum.editors.graphical.directedit.LabelCellEditorLocator;
import com.quantum.editors.graphical.directedit.TableNameCellEditorValidator;
import com.quantum.editors.graphical.directedit.ValidationMessageHandler;
import com.quantum.editors.graphical.figures.EditableLabel;
import com.quantum.editors.graphical.figures.TableFigure;
import com.quantum.editors.graphical.model.Table;
import com.quantum.editors.graphical.parts.connectors.BottomAnchor;
import com.quantum.editors.graphical.parts.connectors.LeftAnchor;
import com.quantum.editors.graphical.parts.connectors.RightAnchor;
import com.quantum.editors.graphical.parts.connectors.TopAnchor;
import com.quantum.editors.graphical.policy.TableContainerEditPolicy;
import com.quantum.editors.graphical.policy.TableDirectEditPolicy;
import com.quantum.editors.graphical.policy.TableEditPolicy;
import com.quantum.editors.graphical.policy.TableLayoutEditPolicy;
import com.quantum.editors.graphical.policy.TableNodeEditPolicy;
import com.quantum.model.Bookmark;
import com.quantum.sql.MultiSQLServer;
import com.quantum.sql.SQLResultSetResults;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.wizards.SQLRowWizard;
import com.quantum.wizards.SortFilterPage;


/**
 * Represents the editable/resizable table which can have columns added,
 * removed, renamed etc.
 * 
 * @author Phil Zoio
 */
public class TablePart extends PropertyAwarePart implements NodeEditPart//, IPropertySource
{
    private boolean editingName;
    private TableFigureActionListener tfal;
    EditableLabel label;
    EditableLabel alias;
    Button filter;
    String whereClause;
	//******************* Life-cycle related methods *********************/

	/**
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate()
	{
		super.activate();
	}

	/**
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate()
	{
		super.deactivate();
	}

	//******************* Model related methods *********************/

	/**
	 * Returns the Table model object represented by this EditPart
	 */
	public Table getTable()
	{
		return (Table) getModel();
	}

	/**
	 * @return the children Model objects as a new ArrayList
	 */
	protected List getModelChildren()
	{
		return getTable().getModelColumns();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	protected List getModelSourceConnections()
	{
		return getTable().getForeignKeyRelationships();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	protected List getModelTargetConnections()
	{
		return getTable().getPrimaryKeyRelationships();
	}

	//******************* Editing related methods *********************/

	/**
	 * Creates edit policies and associates these with roles
	 */
	protected void createEditPolicies()
	{

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new TableNodeEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TableLayoutEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TableContainerEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new TableEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TableDirectEditPolicy());
	}

	//******************* Direct editing related methods *********************/

	/**
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	public void performRequest(Request request)
	{
//        System.out.println(request.getType() + " " + request.toString());
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
		{
			if (request instanceof DirectEditRequest)
            {
		        if(directEditHitTestName(((DirectEditRequest) request).getLocation().getCopy())){
		            editingName = true;
                    performDirectEdit(((TableFigure)getFigure()).getNameLabel());
                }else if(directEditHitTestAlias(((DirectEditRequest) request).getLocation().getCopy())){
                    editingName = false;
                    performDirectEdit(((TableFigure)getFigure()).getAliasLabel());
                }
            }
			return;
		}
	}

    public boolean isNameBeingEdited()
    {
        return editingName;
    }
    
	private boolean directEditHitTestName(Point requestLoc)
	{
		TableFigure figure = (TableFigure) getFigure();
		EditableLabel nameLabel = figure.getNameLabel();
		nameLabel.translateToRelative(requestLoc);
		if (nameLabel.containsPoint(requestLoc))
			return true;
		return false;
	}

    private boolean directEditHitTestAlias(Point requestLoc)
    {
        TableFigure figure = (TableFigure) getFigure();
        EditableLabel aliasLabel = figure.getAliasLabel();
        aliasLabel.translateToRelative(requestLoc);
        if (aliasLabel.containsPoint(requestLoc))
            return true;
        return false;
    }

	protected void performDirectEdit(EditableLabel label)
	{
		ValidationEnabledGraphicalViewer viewer = (ValidationEnabledGraphicalViewer) getViewer();
        ValidationMessageHandler handler = viewer.getValidationHandler();

        // or should we have one manager per editable label, and keep those?
        DirectEditManager manager = null;
        if(isNameBeingEdited()){
            manager = new ExtendedDirectEditManager(this, TextCellEditor.class,
                new LabelCellEditorLocator(label), label,
                new TableNameCellEditorValidator(handler));
        }else{
            manager = new ExtendedDirectEditManager(this, TextCellEditor.class,
                    new LabelCellEditorLocator(label), label,
                    new AliasNameCellEditorValidator(handler));
        }
        manager.show();
	}

	/**
	 * @param handles
	 *            the name change during an edit
	 */
	public void handleNameChange(String value)
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		EditableLabel label = tableFigure.getNameLabel();
		label.setVisible(false);
		refreshVisuals();
	}

	/**
	 * Reverts to existing name in model when exiting from a direct edit
	 * (possibly before a commit which will result in a change in the label
	 * value)
	 */
	public void revertNameChange()
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		EditableLabel label = tableFigure.getNameLabel();
		Table table = getTable();
		label.setText(table.getTableName());
		label.setVisible(true);
		refreshVisuals();
	}
    /**
     * @param handles
     *            the name change during an edit
     */
    public void handleAliasChange(String value)
    {
        TableFigure tableFigure = (TableFigure) getFigure();
        EditableLabel label = tableFigure.getAliasLabel();
        label.setVisible(false);
        refreshVisuals();
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit
     * (possibly before a commit which will result in a change in the label
     * value)
     */
    public void revertAliasChange()
    {
        TableFigure tableFigure = (TableFigure) getFigure();
        EditableLabel label = tableFigure.getAliasLabel();
        Table table = getTable();
        label.setText(table.getAlias());
        label.setVisible(true);
        refreshVisuals();
    }

	//******************* Miscellaneous stuff *********************/

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#toString()
	 */
	public String toString()
	{
		return getModel().toString();
	}

	//******************* Listener related methods *********************/

	/**
	 * Handles change in name when committing a direct edit
	 */
	protected void commitNameChange(PropertyChangeEvent evt)
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		EditableLabel label = tableFigure.getNameLabel();
		label.setText(getTable().getTableName());
		label.setVisible(true);
		refreshVisuals();
	}
    /**
     * Handles change in name when committing a direct edit
     */
    protected void commitAliasChange(PropertyChangeEvent evt)
    {
        TableFigure tableFigure = (TableFigure) getFigure();
        EditableLabel label = tableFigure.getAliasLabel();
        label.setText(getTable().getAlias());
        label.setVisible(true);
        refreshVisuals();
    }

	/**
	 * handles change in bounds, to be overridden by subclass
	 */
	protected void handleBoundsChange(PropertyChangeEvent evt)
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		Rectangle constraint = (Rectangle) evt.getNewValue();
		EntityRelationDiagramEditPart parent = (EntityRelationDiagramEditPart) getParent();
		parent.setLayoutConstraint(this, tableFigure, constraint);
	}

	//******************* Layout related methods *********************/

	/**
	 * Creates a figure which represents the table
	 */
	protected IFigure createFigure()
	{
		Table table = getTable();
		label = new EditableLabel(table.getTableName());
        alias = new EditableLabel(table.getAlias());
        filter = new Button(ImageStore.getImage(ImageStore.FILTER));
		TableFigure tableFigure = new TableFigure(label, alias, filter);
        tfal = new TableFigureActionListener(this);
        filter.addActionListener(tfal);
        Rectangle rect = table.getBounds();
        if(rect != null)tableFigure.setBounds(table.getBounds());
		return tableFigure;
	}

	/**
	 * Reset the layout constraint, and revalidate the content pane
	 */
	protected void refreshVisuals()
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		Point location = tableFigure.getLocation();
        EntityRelationDiagramEditPart parent = (EntityRelationDiagramEditPart) getParent();
		Rectangle constraint = new Rectangle(location.x, location.y, -1, -1);
		parent.setLayoutConstraint(this, tableFigure, constraint);
	}

	/**
	 * @return the Content pane for adding or removing child figures
	 */
	public IFigure getContentPane()
	{
		TableFigure figure = (TableFigure) getFigure();
		return figure.getColumnsFigure();
	}

	/**
	 * @see NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection)
	{
		return new RightAnchor(getFigure(), connection);
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request)
	{
		return new BottomAnchor(getFigure());
	}

	/**
	 * @see NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection)
	{
		return new LeftAnchor(getFigure(), connection);
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request)
	{
		return new TopAnchor(getFigure());
	}

	/**
	 * Sets the width of the line when selected
	 */
	public void setSelected(int value)
	{
		TableFigure tableFigure = (TableFigure) getFigure();
		if (value != EditPart.SELECTED_NONE)
			tableFigure.setSelected(true);
		else
			tableFigure.setSelected(false);
		tableFigure.repaint();
        super.setSelected(value);
	}
    
    public String getWhereClause()
    {
        return getTable().getWhereClause();
    }
    
    public void setWhereClause(String clause)
    {
        getTable().setWhereClause(clause);
    }
    
    private class TableFigureActionListener implements ActionListener{

        private TablePart tp;

        TableFigureActionListener(TablePart tp){
            this.tp = tp;
        }
        
        public void actionPerformed(ActionEvent event) {
            SortFilterPage p = new SortFilterPage("page1");
            SQLRowWizard wizard = new SQLRowWizard();
            Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
            try {
                SQLResultSetResults results = 
                    (SQLResultSetResults) MultiSQLServer.getInstance().execute(
                            bm, bm.getConnection(),"SELECT * FROM " + label.getText(), 1);
                wizard.init("Define WHERE clause", p, results, null);                
            } catch (NotConnectedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            IWorkbenchPage page = QuantumPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IEditorPart part = page.getActiveEditor();
            if(part instanceof EntityRelationEditor)
            {
                WizardDialog dialog = new WizardDialog(
                        part.getSite().getShell(), wizard);
                // I just hope this is the right place to insert this...
                p.setQuery(tp.getWhereClause());
                p.setTable(getTable().getName());
                dialog.open();
                tp.setWhereClause(p.getQuery());
            }
        }
    }
}