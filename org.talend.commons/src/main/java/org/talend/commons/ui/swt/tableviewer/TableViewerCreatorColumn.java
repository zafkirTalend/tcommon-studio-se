// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.swt.tableviewer;

import java.util.Comparator;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;
import org.talend.commons.ui.swt.tableviewer.behavior.CellEditorValueAdapter;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnColorProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnImageProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.IColumnLabelProvider;
import org.talend.commons.ui.swt.tableviewer.behavior.ITableCellValueModifiedListener;
import org.talend.commons.ui.swt.tableviewer.selection.ITableColumnSelectionListener;
import org.talend.commons.ui.swt.tableviewer.tableeditor.TableEditorContent;
import org.talend.commons.utils.data.bean.IBeanPropertyAccessors;

/**
 * <code>TableViewerCreatorColumn</code> represents a column to add to <code>TableViewerCreator</code>.
 * 
 * 
 * 
 * <br/>
 * 
 * $Id$
 * 
 */
public class TableViewerCreatorColumn implements Cloneable {

    private TableColumn tableColumn;

    private ITableColumnSelectionListener tableColumnSelectionListener;

    private IBeanPropertyAccessors beanPropertyAccessors;

    private IColumnImageProvider imageProvider;

    private IColumnLabelProvider labelProvider;

    private int index = -1;

    private static int generatedId;

    private int unactiveKeysForEditor;
    
    /**
     * . <br/>
     * 
     * @see SWT.LEFT
     * @see SWT.RIGHT
     * @see SWT.CENTER
     */
    public enum ALIGNMENT {
        LEFT(SWT.LEFT),
        RIGHT(SWT.RIGHT),
        CENTER(SWT.CENTER);

        private int swtAlignment = SWT.NONE;

        ALIGNMENT(int swtAlignment) {
            this.swtAlignment = swtAlignment;
        }

        public int getSwtAlignment() {
            return swtAlignment;
        }
    }

    private String id;

    private String title;

    private boolean resizable;

    private boolean sortable;

    private boolean moveable;

    private boolean modifiable;

    private ALIGNMENT alignment = ALIGNMENT.LEFT;

    private int width;

    private int weight;

    private int minimumWidth;

    private String toolTipHeader;

    private Image imageHeader;

    private Comparator comparator;

    private boolean orderWithIgnoreCase = true;

    private CellEditor cellEditor;

    private CellEditorValueAdapter retrieverValue;

    private TableEditorContent tableEditorContent;

    private String defaultDisplayedValue;

    private Object defaultInternalValue;

    private String displayedValue;

    private TableViewerCreator tableViewerCreator;

    private ITableCellValueModifiedListener cellEditorAppliedListener;

    private IColumnColorProvider colorProvider;

    /**
     * 
     * Use this constructor if you want add columns manually to <code>TableViewerCreator</code>.
     * 
     * @see TableViewerCreator#addColumn(TableViewerCreatorColumn)
     */
    public TableViewerCreatorColumn() {
        super();
        this.id = TableViewerCreatorColumn.getNewId();
    }

    /**
     * 
     * @param tableViewerCreator
     */
    public TableViewerCreatorColumn(TableViewerCreator tableViewerCreator) {
        super();
        this.tableViewerCreator = tableViewerCreator;
        this.moveable = this.tableViewerCreator.isColumnsMoveableByDefault();
        this.resizable = this.tableViewerCreator.isColumnsResizableByDefault();
        this.sortable = this.tableViewerCreator.isColumnsSortableByDefault();
        this.id = TableViewerCreatorColumn.getNewId();
        tableViewerCreator.addColumn(this);
    }

    public ALIGNMENT getAlignment() {
        return alignment;
    }

    /**
     * Field used when column is created.
     * 
     * @param alignment
     */
    public void setAlignment(ALIGNMENT alignment) {
        this.alignment = alignment;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * 
     * DOC amaumont Comment method "getImage".
     * 
     * @return
     */
    public Image getImageHeader() {
        return imageHeader;
    }

    /**
     * DOC amaumont Comment method "setImageHeader".
     * 
     * Note: imageHeader is replaced by up or down arrow image when sorting.
     * 
     * @param imageHeader
     * @see TableColumn#setImage(Image)
     */
    public void setImageHeader(Image imageHeader) {
        this.imageHeader = imageHeader;
    }

    public int getMinimumWidth() {
        return minimumWidth;
    }

    /**
     * Note: used only when the weight is set.
     * 
     * @param minimumWidth
     */
    public void setMinimumWidth(int minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public boolean isModifiable() {
        if (tableViewerCreator != null) {
            return modifiable && !tableViewerCreator.isReadOnly();
        } else {
            return modifiable;
        }
    }

    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToolTipHeader() {
        return toolTipHeader;
    }

    public void setToolTipHeader(String toolTipText) {
        this.toolTipHeader = toolTipText;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Read the TableColumn 'width' if exists, else read the property of this instance.
     * 
     * @return
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public TableColumn getTableColumn() {
        return tableColumn;
    }

    public CellEditor getCellEditor() {
        return cellEditor;
    }

    public void setCellEditor(CellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    /**
     * Use this method whether the type handled by CellEditor is not <code>String</code> .
     * 
     * @param cellEditor
     * @param typeValueAdapter
     */
    public void setCellEditor(CellEditor newCellEditor, CellEditorValueAdapter typeValueAdapter) {
        setCellEditor(newCellEditor);
        this.retrieverValue = typeValueAdapter;
    }

    public CellEditorValueAdapter getRetrieverValue() {
        return retrieverValue;
    }

    /**
     * @return the compareToIgnoreCase
     */
    public boolean isOrderWithIgnoreCase() {
        return orderWithIgnoreCase;
    }

    /**
     * @param compareToIgnoreCase the compareToIgnoreCase to set
     */
    public void setOrderWithIgnoreCase(boolean compareToIgnoreCase) {
        this.orderWithIgnoreCase = compareToIgnoreCase;
    }

    /**
     * @param retrieverValue the retrieverValue to set
     */
    public void setRetrieverValue(CellEditorValueAdapter retrieverValue) {
        this.retrieverValue = retrieverValue;
    }

    /**
     * Should'nt be use by user of this class. This method is used by TableEditor
     * 
     * @param tableColumn
     */
    public void setTableColumn(TableColumn tableColumn) {
        this.tableColumn = tableColumn;
    }

    // /**
    // * @return the orderWithNullsFirst
    // */
    // public boolean isOrderWithNullsFirst() {
    // return orderWithNullsFirst;
    // }
    //
    // /**
    // * @param orderWithNullsFirst the orderWithNullsFirst to set
    // */
    // public void setOrderWithNullsFirst(boolean orderWithNullsFirst) {
    // this.orderWithNullsFirst = orderWithNullsFirst;
    // }

    /**
     * 
     * Clone superficially the current instance. Then you must add explicitly the new instance to TableEditor.
     * 
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        TableViewerCreatorColumn tableEditorColumn = null;
        try {
            tableEditorColumn = (TableViewerCreatorColumn) super.clone();
        } catch (CloneNotSupportedException e) {
            throw e;
        }
        return tableEditorColumn;
    }

    public TableEditorContent getTableEditorContent() {
        return this.tableEditorContent;
    }

    public void setTableEditorContent(TableEditorContent tableEditorColumn) {
        this.tableEditorContent = tableEditorColumn;
    }

    public ITableColumnSelectionListener getTableColumnSelectionListener() {
        return this.tableColumnSelectionListener;
    }

    public void setTableColumnSelectionListener(ITableColumnSelectionListener tableColumnSelectionListener) {
        this.tableColumnSelectionListener = tableColumnSelectionListener;
    }

    public ITableCellValueModifiedListener getCellEditorAppliedListener() {
        return this.cellEditorAppliedListener;
    }

    public void setCellEditorAppliedListener(ITableCellValueModifiedListener cellEditorAppliedListener) {
        this.cellEditorAppliedListener = cellEditorAppliedListener;
    }

    public String getId() {
        if (this.id == null) {
            this.id = this.title;
        }
        return this.id;
    }

    /**
     * 
     * This value is optional. It is obligatory if you use the same property in several columns.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getDefaultDisplayedValue() {
        return this.defaultDisplayedValue;
    }

    public void setDefaultDisplayedValue(String defaultValue) {
        this.defaultDisplayedValue = defaultValue;
    }

    public Object getDefaultInternalValue() {
        return this.defaultInternalValue;
    }

    public void setDefaultInternalValue(Object defaultInternalValue) {
        this.defaultInternalValue = defaultInternalValue;
    }

    /**
     * DOC amaumont Comment method "setDisplayedValue".
     * 
     * @param object
     */
    public void setDisplayedValue(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String getDisplayedValue() {
        return this.displayedValue;
    }

    public TableViewerCreator getTableViewerCreator() {
        return this.tableViewerCreator;
    }

    public IBeanPropertyAccessors getBeanPropertyAccessors() {
        return this.beanPropertyAccessors;
    }

    public void setBeanPropertyAccessors(IBeanPropertyAccessors beanPropertyAccessors) {
        this.beanPropertyAccessors = beanPropertyAccessors;
    }

    public IColumnImageProvider getImageProvider() {
        return this.imageProvider;
    }

    /**
     * 
     * <code>IColumnImageProvider</code> allow to set an Image provider for the current column.
     * 
     * <p>
     * <b>Warning</b>: this Image provider won't work if you set a custom LabelProvider to
     * <code>TableViewerCreator</code>. So you can inherit <code>DefaultTableLabelProvider</code> and call super
     * methods into.
     * 
     * @param columnImageProvider
     * @see IColumnImageProvider#getImage(Object, int) is used
     */
    public void setImageProvider(IColumnImageProvider columnImageProvider) {
        this.imageProvider = columnImageProvider;
    }

    public IColumnLabelProvider getLabelProvider() {
        return this.labelProvider;
    }

    /**
     * 
     * <code>IColumnImageProvider</code> allow to set an Image provider for the current column.
     * 
     * <p>
     * <b>Warning</b>: this Image provider won't work if you set a custom LabelProvider to
     * <code>TableViewerCreator</code>. So you can inherit <code>DefaultTableLabelProvider</code> and call super
     * methods into.
     * 
     * @param columnImageProvider
     * @see IColumnLabelProvider#getLabel(Object, int) is used
     */
    public void setLabelProvider(IColumnLabelProvider columnLabelProvider) {
        this.labelProvider = columnLabelProvider;
    }

    /**
     * "getIndex" method.
     * 
     * @return the index of the column
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Index is set by <code>TableViewerCreator</code> at initialization.
     * 
     * @param index
     */
    protected void setIndex(int index) {
        this.index = index;
    }

    private static synchronized String getNewId() {
        return String.valueOf(generatedId++);
    }

    /**
     * Gets the colorProvider.
     * 
     * @return
     */
    public IColumnColorProvider getColorProvider() {
        return colorProvider;
    }

    /**
     * Sets the colorProvider.
     * 
     * @param colorProvider the colorProvider to set
     */
    public void setColorProvider(IColumnColorProvider colorProvider) {
        this.colorProvider = colorProvider;
    }
    
}
