package org.talend.swtbot.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
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
        name = convertString(name);
        SWTBotTreeItem schemaNode = item.expand().expandNode("Table schemas");
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
        SWTGefBot gefBot = new SWTGefBot();
        SWTBotShell shell = null;
        long defaultTimeout = SWTBotPreferences.TIMEOUT;
        SWTBotPreferences.TIMEOUT = 100;
        if (sql != null) {
            try {
                item.contextMenu("Edit queries").click();
                try {
                    if (gefBot.shell("Choose context").isActive())
                        gefBot.button("OK").click();
                } catch (WidgetNotFoundException wnfe) {
                    // ignor this, means it's not context mode, did not pop up context confirm dialog
                }
                shell = gefBot.shell("SQL Builder [Repository Mode]").activate();
                gefBot.styledText(0).setText(sql);
                gefBot.toolbarButtonWithTooltip("Execute SQL (Ctrl+Enter)").click();

                try {
                    if (gefBot.shell("Error Executing SQL").isActive())
                        gefBot.button("OK").click();
                    Assert.fail("execute sql fail");
                } catch (WidgetNotFoundException wnfe) {
                    // ignor this, means did not pop up error dialog, sql executed successfully.
                }
            } catch (WidgetNotFoundException wnfe) {
                Assert.fail(wnfe.getCause().getMessage());
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            } finally {
                shell.close();
            }
        }
        SWTBotPreferences.TIMEOUT = defaultTimeout;
    }

    public void retrieveDbSchema(String... schemas) {
        SWTGefBot gefBot = new SWTGefBot();
        SWTBotShell tempShell = null;
        try {
            getParentNode().getNode(itemName + " 0.1").contextMenu("Retrieve Schema").click();
            tempShell = gefBot.shell("Schema").activate();
            gefBot.button("Next >").click();
            List<String> schemaList = new ArrayList<String>(Arrays.asList(schemas));

            SWTBotTree root = gefBot.treeInGroup("Select Schema to create");
            SWTBotTreeItem treeNode = null;
            if (getCatalog() != null && getSchema() == null)
                treeNode = root.expandNode(getCatalog());
            if (getCatalog() == null && getSchema() != null)
                treeNode = root.expandNode(getSchema());
            if (getCatalog() != null && getSchema() != null)
                treeNode = root.expandNode(getCatalog(), getSchema());
            for (String schema : schemaList) {
                treeNode.getNode(convertString(schema)).check();
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
     * Some DB schema name and table name should be uppercase, so need to convert.
     * 
     * @param str
     * @return converted string
     */
    private String convertString(String str) {
        if (str == null)
            return null;
        switch (dbType) {
        case DB2:
        case ORACLE:
            return str.toUpperCase();
        default:
            return str;
        }
    }

    private String getCatalog() {
        if (dbType == Utilities.DbConnectionType.TERADATA || dbType == Utilities.DbConnectionType.DB2)
            return null;
        String databaseProp = dbType.toString().toLowerCase();
        databaseProp += ".dataBase";
        return convertString(System.getProperty(databaseProp));
    }

    private String getSchema() {
        String schemaProp = dbType.toString().toLowerCase();
        if (dbType == Utilities.DbConnectionType.TERADATA)
            schemaProp += ".dataBase";
        else
            schemaProp += ".schema";
        return convertString(System.getProperty(schemaProp));
    }
}
