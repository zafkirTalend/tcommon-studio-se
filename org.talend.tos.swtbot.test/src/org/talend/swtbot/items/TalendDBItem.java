package org.talend.swtbot.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;
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
        name = getConvertedString(name);
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
        SWTGefBot gefBot = new SWTGefBot();
        SWTBotShell tempShell = null;
        try {
            getParentNode().getNode(itemName + " 0.1").contextMenu("Retrieve Schema").click();
            tempShell = gefBot.shell("Schema").activate();
            gefBot.button("Next >").click();
            List<String> schemaList = new ArrayList<String>(Arrays.asList(schemas));

            for (String schema : schemaList) {
                gefBot.treeInGroup("Select Schema to create").expandNode(System.getProperty(getSchemaProperty()))
                        .getNode(getConvertedString(schema)).check();
            }
            gefBot.button("Next >").click();
            gefBot.button("Finish").click();
        } catch (WidgetNotFoundException wnfe) {
            tempShell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            tempShell.close();
            Assert.fail(e.getMessage());
        }
    }

    public SWTBotShell editQueries() {
        item.contextMenu("Edit queries").click();
        SWTBotShell shell = new SWTGefBot().shell("SQL Builder [Repository Mode]").activate();
        return shell;
    }

    /**
     * Get property name of DB schema. Some DB did not has schema, so it's schema name is database name.
     * 
     * @return schema prperty name
     */
    private String getSchemaProperty() {
        String schemaProp = dbType.toString().toLowerCase();
        if (dbType == Utilities.DbConnectionType.MYSQL) {
            schemaProp += ".dataBase";
        } else {
            schemaProp += ".schema";
        }
        return schemaProp;
    }

    /**
     * Some DB schema name and table name should be uppercase, so need to convert.
     * 
     * @param str
     * @return converted string
     */
    private String getConvertedString(String str) {
        switch (dbType) {
        case DB2:
        case ORACLE:
            return str.toUpperCase();
        default:
            return str;
        }
    }
}
