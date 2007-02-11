package com.quantum.editors.graphical.model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.CommandStack;

import com.quantum.actions.BookmarkSelectionUtil;
import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.model.commands.ColumnCreateCommand;
import com.quantum.editors.graphical.model.commands.DeleteColumnCommand;
import com.quantum.editors.graphical.model.commands.DeleteTableCommand;
import com.quantum.editors.graphical.model.commands.EditColumnCommand;
import com.quantum.model.Bookmark;
import com.quantum.model.DatabaseObjectFactory;
import com.quantum.model.Entity;
import com.quantum.util.connection.JDBCUtil;
import com.quantum.util.connection.NotConnectedException;

public class EntityRelationDiagram extends PropertyAwareObject{

	private static final long serialVersionUID = 1;
	private List tables= new ArrayList(); 
    private Map tablesMap = new HashMap();
    private String name;
    private boolean layoutManualDesired = true;
    private boolean layoutManualAllowed = true;

    private boolean showRelationshipNames = false;
    private boolean showColumns = true;
    
    private String query;
    
    public EntityRelationDiagram(String name)
    {
    	super();
        this.name = name;
    }

    public String createDDLStatement()
    {
        /*
         * we follow this:
         * Does the table exist in the last used bookmark?
         * Yes: alter table
         * No: create table
         */
        int tableIterator;
        String answer = "";
        Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        for(tableIterator = 0; tableIterator<tables.size(); tableIterator++)
        {
            Table t = (Table) tables.get(tableIterator);
            try {
                if(bm.getDatabase().tableExists(t.getTableName())) 
                {
                    answer += handleExistingTable(bm, t);
                }else{
                    answer += handleNewTable(bm, t);
                }
                // relations are handled by the DatabaseAdapter
            } catch (NotConnectedException e) {
            }
        }
        if(!answer.equals("")){
            Date dt = new Date();
            answer = "-- Script created by Quantum DB on " + DateFormat.getDateTimeInstance().format(dt) + "\r\n" + answer;
        }
        return answer;
    }

    private String handleExistingTable(Bookmark bm, Table t)
    {
        String ddl = "";
        int columnIterator;
        for(columnIterator = 0; columnIterator < t.getModelColumns().size(); columnIterator++)
        {
            Column c = (Column) t.getModelColumns().get(columnIterator);
            try {
                if(bm.getDatabase().columnExists(t.getTableName(), c.getOriginalName())) 
                {
                    // this is an existing column
                    // modified columns
                    ddl += handleExistingColumn(bm, t, c);
                    System.out.println(ddl);
                }else{
                    // new column
                    // added columns
                    ddl += handleNewColumn(bm, t, c);
                }
            } catch (NotConnectedException e) {
            }
        }
        // deleted columns
        DatabaseMetaData dmd;
        try {
            dmd = bm.getDatabase().getMetaData();
            ResultSet rsO = dmd.getColumns(null, null, t.getTableName(), null);
            String columnName = null;
            try {
                while (rsO.next()) {
                    columnName = rsO.getString(JDBCUtil.COLUMN_METADATA_COLUMN_NAME);
                    boolean bFound = false;
                    for (columnIterator = 0; columnIterator < t.getModelColumns().size(); columnIterator++) {
                        Column c = (Column) t.getModelColumns().get(columnIterator);
                        if (columnName.equalsIgnoreCase(c.getName())) {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound) {
                        // this column was deleted in the editor
                        ResultSet rsKeys = dmd.getExportedKeys(null, null, t.getTableName());
                        while(rsKeys.next())
                        {
                            String refColumnName = rsKeys.getString(JDBCUtil.FOREIGN_KEY_METADATA_FKCOLUMN_NAME);
                            if(columnName.equalsIgnoreCase(refColumnName)){
                                ddl += "ALTER TABLE " + t.getTableName() + " DROP CONSTRAINT " + rsKeys.getString(JDBCUtil.FOREIGN_KEY_METADATA_FK_NAME);
                                ddl += ";\r\n";
                            }
                        }
                        rsKeys = dmd.getImportedKeys(null, null, t.getTableName());
                        while(rsKeys.next())
                        {
                            String refColumnName = rsKeys.getString(JDBCUtil.FOREIGN_KEY_METADATA_PKCOLUMN_NAME);
                            if(columnName.equalsIgnoreCase(refColumnName)){
                                ddl += "ALTER TABLE " + t.getTableName() + " DROP CONSTRAINT " + rsKeys.getString(JDBCUtil.FOREIGN_KEY_METADATA_FK_NAME);
                                ddl += ";\r\n";
                            }
                        }

                        ddl += "ALTER TABLE " + t.getTableName() + " DROP COLUMN " + columnName + ";\r\n";
                    }
                }
            } finally {
                rsO.close();
            }
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        return ddl;
    }
    
    private String handleExistingColumn(Bookmark bm, Table t, Column c) {
        String ddl = "";
        if(!(c.getOriginalName().equalsIgnoreCase(c.getName()))){
            // name change
            ddl += bm.getAdapter().buildAlterTable(bm, t.getTableName(), "ADD", c.getName(), c) + ";\r\n";
            ddl += "UPDATE " + t.getTableName() + " SET " + c.getName() + "=" + c.getOriginalName() + ";\r\n";
            // the drop is handled by the deleted columns check
        }else if (!(c.getOriginalSize() == c.getSize() &&
                   c.getOriginalPrecision() == c.getPrecision())){
            if((c.getOriginalSize() - c.getOriginalPrecision())>(c.getSize() - c.getOriginalSize())){
                ddl += "-- Note that the sizing differences could influence conversion" + "\r\n";
            }
            ddl += bm.getAdapter().buildAlterTable(bm, t.getTableName(), "ADD", c.getName() + "_temp", c) + ";\r\n";
            ddl += "UPDATE " + t.getTableName() + " SET ";
            ddl += c.getName() + "_temp = ";
            if(c.isNumeric()){
                ddl += c.getName() + ";\r\n";
            }else{
                ddl += "LEFT(" + c.getName() + "," + c.getSize() + ");\r\n";
            }
            ddl += "UPDATE " + t.getTableName() + " SET ";
            ddl += c.getName() + " = NULL" + ";\r\n";
            // TODO: I would like to build this statement based on the com.quantum.model.Column class, so we can extend it more easily...
            // but I think all of this is terribly wrong...
            // So, I choose my column definition instead.
            ddl += bm.getAdapter().buildAlterTable(bm, t.getTableName(), "ALTER", c.getName(), c) + ";\r\n";
            ddl += "UPDATE " + t.getTableName() + " SET ";
            ddl += c.getName() +" = " + c.getName()+ "_temp;\r\n";
            ddl += "--Remove comments from next line to complete the procedure.\r\n";
            ddl += bm.getAdapter().buildAlterTable(bm, t.getTableName(), "DROP", c.getName() + "_temp", c) + ";\r\n";
        }else if(!c.getOriginalType().equalsIgnoreCase(c.getType())){
            // TODO: Implement type changes...
            // TODO: warn for type change incompatibilities?
            ddl += bm.getAdapter().buildAlterTable(bm, t.getTableName(), "ALTER", c.getName(), c) + ";\r\n";
        }
        if(!ddl.equals("")){
            ddl = "\r\n-- Changes related to: " + t.getTableName() + "." + c.getName() + "\r\n" + ddl;
        }
        return ddl;
    }

    private String handleNewColumn(Bookmark bm, Table t, Column c)
    {
        String ddl = "";
        ddl = "ALTER TABLE " + t.getTableName() + " ADD ";
        ddl += c.getName() + " " + c.getLabelText() + ";\r\n";
        return ddl;
    }
    
    private String handleNewTable(Bookmark bm, Table t)
    {
        String ddl = "";
        ddl = t.createCreateStatement();
        return ddl;
    }
    
    public String createQueryStatement()
    {
        List nonLinkedTables = new ArrayList(); // these tables cannot be linked to previous tables.
        List linkedTables = new ArrayList(); // these tables have been linked to previous tables.
        String from = "";
        String where = "";
        int i, j, k, l;
        nonLinkedTables.addAll(tables); // assume all tables do not have links
        if(tables.size() == 1)
        {
            Table t = (Table) tables.get(0); 
            from = addTableToFrom(t);
            where = t.getWhereClause();
            nonLinkedTables.remove(t);
        }else{
            // except the first one that has:
            for(i=0; i < tables.size(); i++)
            {
                Table t = (Table) tables.get(i);
                if(t.getForeignKeyRelationships().size() != 0)
                {
                    // we will start with this one.
                    nonLinkedTables.remove(t);
                    linkedTables.add(t);
                    from = addTableToFrom(t);
                    where = t.getWhereClause();
                    break;
                }
            }
        }
        if(from == "")
        {
            Table t = (Table) tables.get(0); 
            from = addTableToFrom(t);
            nonLinkedTables.remove(t);
            where = t.getWhereClause();
        }

        int previousSize = -1; // bogus value
        while(nonLinkedTables.size() != 0)
        {
            if(previousSize == nonLinkedTables.size())break; // no improvement
            previousSize = nonLinkedTables.size(); // is this really as smart as I think it is?
            for(i=0; i<nonLinkedTables.size(); i++)
            {
                Table t = (Table) nonLinkedTables.get(i);
                // is this table linked to one of the tables already in the from
                int joined = 0;
                for(j=0; j<t.getForeignKeyRelationships().size(); j++)
                {
                    Relationship r = (Relationship) t.getForeignKeyRelationships().get(j);

                    // aliases are created by the Table class...
                    k=0;
                    l = linkedTables.size();
                    while(k<l)
                    {
                        Table ref = (Table) linkedTables.get(k);
                        String refAlias = ref.getAlias();
                        if(r.getPrimaryKeyTable().getTableName().equalsIgnoreCase(ref.getTableName()) &&
                                r.getForeignKeyTable().getName().equalsIgnoreCase(t.getName()) &&
                                r.getPrimaryKeyTable().getName().equalsIgnoreCase(ref.getName()))
                        {
                            String alias = t.getAlias();
                            String foreignKeyTable = getAliasedForeignKeyTable(r.getForeignKeyTable().getName(), alias, t.getName());
                            String primaryKeyTable = getAliasedPrimaryKeyTable(r.getPrimaryKeyTable().getName(), refAlias, ref.getName());
                            if(alias != null)
                            {
                                from += "\r\nINNER JOIN " + t.getTableName() + " " + alias + " ON ";
                            }else{
                                from += "\r\nINNER JOIN " + t.getName() + " ON ";
                            }
                            from += "\r\n\t" + foreignKeyTable  + "." + r.getForeignKeyName();
                            from += " = ";
                            from += primaryKeyTable + "." + r.getPrimaryKeyName();
                            String w = t.getWhereClause();
                            if(!w.equals("")){
                                if(!where.equals("")){
                                    where += " AND " + w;
                                }else{
                                    where += t.getWhereClause();
                                }
                            }
                            joined++;
                            if(!linkedTables.contains(t))linkedTables.add(t);
                            nonLinkedTables.remove(t);
                        }
                        k++;
                    }
                }
                    for(j=0; j<t.getPrimaryKeyRelationships().size(); j++)
                    {
                        Relationship r = (Relationship) t.getPrimaryKeyRelationships().get(j);
                        k=0;
                        l = linkedTables.size();
                        while(k<l)
                        {
                            Table ref = (Table) linkedTables.get(k);
                            String refAlias = ref.getAlias();
                            if(r.getForeignKeyTable().getTableName().equalsIgnoreCase(ref.getTableName()) 
                                    && r.getPrimaryKeyTable().getName().equalsIgnoreCase(t.getName())
                                    && r.getForeignKeyTable().getName().equalsIgnoreCase(ref.getName()))
                            {
                                String alias = t.getAlias();
                                String foreignKeyTable = getAliasedForeignKeyTable(r.getForeignKeyTable().getName(), alias, t.getName());
                                String primaryKeyTable = getAliasedPrimaryKeyTable(r.getPrimaryKeyTable().getName(), refAlias, ref.getName());
                                if(joined == 0){
                                    if(alias != null)
                                    {
                                        from += "\r\nINNER JOIN " + t.getTableName() + " " + alias + " ON ";
                                    }else{
                                        from += "\r\nINNER JOIN " + t.getName() + " ON ";
                                    }
                                    from += "\r\n\t";
                                }else{
                                    from +="\r\nAND ";
                                }
                                from += foreignKeyTable  + "." + r.getForeignKeyName();
                                from += " = ";
                                from += primaryKeyTable + "." + r.getPrimaryKeyName();
                                String w = t.getWhereClause();
                                if(!w.equals("")){
                                    if(!where.equals("")){
                                        where += " AND " + w;
                                    }else{
                                        where += t.getWhereClause();
                                    }
                                }
                                joined++;
                                if(!linkedTables.contains(t))linkedTables.add(t);
                                nonLinkedTables.remove(t);
                            }else{
                            }
                            k++;
                        }
                    }
            }
        }
        if(nonLinkedTables.size() != 0)
        {
            // we seem to be left with tables without relations
            for(i=0; i < nonLinkedTables.size(); i++)
            {
                Table t = (Table) nonLinkedTables.get(i);
                from += "\r\n, " + t.getTableName() ;
                String alias = t.getAlias();
                if(alias != null) from += " " + alias;
                String w = t.getWhereClause();
                if(!w.equals("")){
                    if(!where.equals("")){
                        where += " AND " + w;
                    }else{
                        where += t.getWhereClause();
                    }
                }
            }
        }
        String target = "";
        for(i=0; i < tables.size(); i++)
        {
            Table t = (Table) tables.get(i);
            for(j=0; j<t.getModelColumns().size(); j++)
            {
                Column c = (Column) t.getModelColumns().get(j);
                if(c.isSelected()){
                    target += "\t" + t.getName() + "." + c.getName();
                    if(!c.getAliasName().equals("")){
                        target += " AS " + c.getAliasName();
                    }
                    target += ",\r\n";
                }
            }
        }
        query = "SELECT\r\n";
        if(target != "")
        {
            target = target.substring(0, target.length()-3);
        }else{
            target = "\tCOUNT(*)";
        }
        query += target;
        query += "\r\nFROM ";
        query += from;
        if(!where.equalsIgnoreCase("")){
            query += "\r\nWHERE ";
            query += where;
        }
        query += ";";
        
        if(!query.equals("")){
            Date dt = new Date();
            query = "-- Script created by Quantum DB on " + DateFormat.getDateTimeInstance().format(dt) + "\r\n" + query;
        }
        
//        QuantumLog.getInstance().info(query);
        
        return query;
    }


    /**
     * @param from
     * @param t
     * @return
     */
    private String addAliasToFrom(String from, Table t) {
        if(t.getAlias() != null)
        {
            if(t.getAlias() != "")
            {
                from += " " + t.getAlias();
            }
        }
        return from;
    }


    /**
     * @return
     */
    private String addTableToFrom(Table t) {
        String from;
        from = t.getTableName();
        from = addAliasToFrom(from, t);
        return from;
    }
    
    private String getAliasedPrimaryKeyTable(String primaryKeyTableName, String refAlias, String tableName) {
        if(refAlias != null)
        {
            if(primaryKeyTableName.equalsIgnoreCase(refAlias)){
                return refAlias;
            }else{
                return primaryKeyTableName;
            }
        }
        return primaryKeyTableName;
    }


    private String getAliasedForeignKeyTable(String foreignKeyTableName, String alias, String tableName) {
        if(alias != null)
        {
            if(foreignKeyTableName.equalsIgnoreCase(alias)){
                return alias;
            }else{
                return foreignKeyTableName;
            }
        }else{
            return foreignKeyTableName;
        }
    }

    public void addTable(Table table)
    {
        table.setDiagram(this);
        tables.add(table);
        tablesMap.put(table.getName(), table);
        firePropertyChange(CHILD, null, table);
    }

    public void addTable(Table table, int i)
    {
        table.setDiagram(this);
        tables.add(i, table);
        tablesMap.put(table.getName(), table);
        firePropertyChange(CHILD, null, table);
    }
    
    public void removeTable(Table table)
    {
        tables.remove(table);
        tablesMap.remove(table.getName());
        firePropertyChange(CHILD, table, null);
    }

    /**
     * returns an individual named table
     */
    public Table getTable(String name)
    {
        return (Table) tablesMap.get(name);
    }

    /**
     * @return the Tables for the current schema
     */
    public List getTables()
    {
        return tables;
    }

    /**
     * @return the name of the schema
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param layoutManualAllowed
     *            The layoutManualAllowed to set.
     */
    public void setLayoutManualAllowed(boolean layoutManualAllowed)
    {
        this.layoutManualAllowed = layoutManualAllowed;
    }

    /**
     * @return Returns the layoutManualDesired.
     */
    public boolean isLayoutManualDesired()
    {
        return layoutManualDesired;
    }

    /**
     * @param layoutManualDesired
     *            The layoutManualDesired to set.
     */
    public void setLayoutManualDesired(boolean layoutManualDesired)
    {
        this.layoutManualDesired = layoutManualDesired;
        firePropertyChange(LAYOUT, null, new Boolean(layoutManualDesired));
    }

    /**
     * @return Returns whether we can lay out individual tables manually using the XYLayout
     */
    public boolean isLayoutManualAllowed()
    {
        return layoutManualAllowed;
    }

    public boolean areRelationshipNamesShown() {
        return showRelationshipNames;
    }

    public void setShowRelationshipNames(boolean showRelationshipNames) {
        this.showRelationshipNames = showRelationshipNames;
        firePropertyChange(RELATIONSHIPNAMES, null, new Boolean(showRelationshipNames));
    }

    public boolean areColumnsShown() {
        return showColumns;
    }

    public void setShowColumns(boolean showColumns) {
        this.showColumns = showColumns;
        firePropertyChange(SHOWCOLUMNS, null, new Boolean(showColumns));
    }

    /** 
     * This is a result of an action invoked by the user.
     * Situations may arise that the diagram is out-of-sync with
     * the current database. Sometimes this is desired (when you want
     * change the database for instance), and then again it is not.
     * This function strives to add missing columns, remove non existing columns
     * and tables and update type, size and precision of the columns.
     * 
     * Undo/Redo should work for this, so we are not going to ask for confirmation.
     * After the first one.
     * 
     * It will check relations too, sometime soon...
     */
    public void refresh(EntityRelationEditor schemaEditor)
    {
        Bookmark bm = BookmarkSelectionUtil.getInstance().getLastUsedBookmark();
        // Loop over all tables
        for(int i=0; i<getTables().size(); i++){
            Table t = (Table) getTables().get(i);
            // simple first question: Does the table exist?
            try {
                if (bm.getDatabase().tableExists(t.getTableName())) {
                    refreshColumns(bm, t, schemaEditor);
                }else{
                    DeleteTableCommand dtc = new DeleteTableCommand();
                    dtc.setTable(t);
                    schemaEditor.getMeTheCommandStack().execute(dtc);
//                    removeTable(t);
                }
            } catch (NotConnectedException e) {
                e.printStackTrace();
            }
            
        }
    }

    private void refreshColumns(Bookmark bm, Table t, EntityRelationEditor schemaEditor) {

        CommandStack cs = schemaEditor.getMeTheCommandStack(); // TODO: this is also bad
        
        com.quantum.model.Column[] databaseColumns = null;
        com.quantum.model.Table databaseTable = null;
        try {
            databaseTable = (com.quantum.model.Table) DatabaseObjectFactory.getInstance().createEntity(bm, null, t.getTableName(), Entity.TABLE_TYPE, false);
            databaseColumns = databaseTable.getColumns();
        } catch (NotConnectedException e) {
        } catch (SQLException e) {
        }
        for(int i=0; i<t.getModelColumns().size(); i++){
            Column c = (Column) t.getModelColumns().get(i);
            // does it exist in the database?
            boolean bFound = false;
            for(int j=0; j<databaseColumns.length; j++){
                if(c.getName().equalsIgnoreCase(databaseColumns[j].getName())){
                    bFound = true;
                    // does the type match?
                    EditColumnCommand ecc = new EditColumnCommand();
                    ecc.setColumn(c);
                    boolean doExecute = false;
                    if(c.getType().equalsIgnoreCase(databaseColumns[j].getTypeName())){
                        // does the size match?
                        if(c.getSize()!=databaseColumns[j].getSize() || c.getPrecision()!=databaseColumns[j].getNumberOfFractionalDigits()){
                            ecc.setAttributes(c.getType(), c.getSize(), databaseColumns[j].getNumberOfFractionalDigits());
                            ecc.setOldAttributes(c.getType(), c.getSize(), c.getPrecision());
                            doExecute = true;
                        }
                    }else{
                        ecc.setAttributes(databaseColumns[j].getTypeName(), databaseColumns[j].getSize(), databaseColumns[j].getNumberOfFractionalDigits());
                        ecc.setOldAttributes(c.getType(), c.getSize(), c.getPrecision());
                        doExecute = true;
                    }
                    if(doExecute)cs.execute(ecc);
                    break;
                }
            }
            if(!bFound){
                DeleteColumnCommand dcc = new DeleteColumnCommand();
                dcc.setColumn(c);
                dcc.setTable(t);
                cs.execute(dcc);
//                t.removeColumn(c);
            }
        }
        // did we remove any columns in our diagram?
        for(int i=0; i<databaseColumns.length; i++){
            // does it exist in the diagram?
            boolean bFound = false;
            for(int j=0; j<t.getModelColumns().size(); j++){
                Column c = (Column) t.getModelColumns().get(j);
                if(c.getName().equalsIgnoreCase(databaseColumns[i].getName())){
                    bFound = true;
                    break;
                }
            }
            if(!bFound){
                Column newColumn = new Column(
                        databaseColumns[i].getName(),
                        databaseColumns[i].getTypeName(),
                        databaseColumns[i].isPrimaryKey(),
                        false, // foreign key
                        databaseColumns[i].getSize(),
                        databaseColumns[i].getNumberOfFractionalDigits(),
                        databaseColumns[i].isNumeric(),
                        databaseColumns[i].isReal()
                        );
                ColumnCreateCommand ccc = new ColumnCreateCommand();
                ccc.setColumn(newColumn);
                ccc.setTable(t);
                ccc.setIndex(i); 
                cs.execute(ccc); // TODO: this makes it undoable
            }
        }
    }
}
