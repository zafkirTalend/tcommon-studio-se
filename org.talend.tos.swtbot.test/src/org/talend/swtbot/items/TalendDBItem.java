package org.talend.swtbot.items;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.talend.swtbot.Utilities;
import org.talend.swtbot.Utilities.DbConnectionType;

public class TalendDBItem extends TalendMetadataItem {

    private DbConnectionType dbType;

    public TalendDBItem() {
        super(Utilities.TalendItemType.DB_CONNECTIONS);
    }

    public TalendDBItem(String itemName, DbConnectionType dbType) {
        super(itemName, Utilities.TalendItemType.DB_CONNECTIONS);
        this.dbType = dbType;
    }

    public DbConnectionType getDbType() {
        return dbType;
    }

    public void setDbType(DbConnectionType dbType) {
        this.dbType = dbType;
    }

    public TalendSchemaItem getSchema(String name) {
        SWTBotTreeItem schemaNode = item.expandNode("Table schemas");
        TalendSchemaItem schemaItem = new TalendSchemaItem();
        schemaItem.setItem(schemaNode.getNode(name));
        schemaItem.setParentNode(schemaNode);
        schemaItem.setItemName("\"" + schemaItem.getItemName() + "\"");
        return schemaItem;
    }

    @Override
    public void create() {
        SWTBotTreeItem item = Utilities.createDbConnection(getParentNode(), dbType, itemName);
        setItem(item);
    }

    public void executeSQL(String sql) {
        Utilities.executeSQL(getItem(), sql);
    }

    public void retrieveDbSchema(String... schemas) {
        Utilities.retrieveDbSchema(getParentNode(), itemName, schemas);
    }

    public SWTBotShell editQueries() {
        item.contextMenu("Edit queries").click();
        SWTBotShell shell = new SWTGefBot().shell("SQL Builder [Repository Mode]").activate();
        return shell;
    }
}
