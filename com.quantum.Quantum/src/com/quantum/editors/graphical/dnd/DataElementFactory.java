/*
 * Created on Jul 14, 2004
 */
package com.quantum.editors.graphical.dnd;

import java.sql.SQLException;

import org.eclipse.gef.requests.CreationFactory;

import com.quantum.editors.graphical.EntityRelationEditor;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.EntityRelationDiagram;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.model.Table;
import com.quantum.model.ForeignKey;
import com.quantum.util.connection.NotConnectedException;
import com.quantum.view.bookmark.ColumnNode;
import com.quantum.view.bookmark.EntityNode;
/**
 * Factory for creating instances of new objects from a drop from the bookmark view
 * adapted from Phil Zoio
 * jhvdv
 */
public class DataElementFactory implements CreationFactory
{

	private Object template;
    private EntityRelationDiagram diagram;
    private String type;
    private String javaType;
    
	public DataElementFactory(EntityRelationEditor editor, Object o)
	{
        template = o;
        diagram = editor.getModel();
	}

    /**
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject()
	{
		try
		{
            if(template.getClass() == EntityNode.class)
            {
                Table t = new Table();
                EntityNode node = ((EntityNode) template);
                t.setDiagram(diagram);
                t.setName(node.getName());
                // are there any other tables with the same name?
                int count = 0;
                for (int i=0; i<diagram.getTables().size(); i++)
                {
                    Table x = (Table)diagram.getTables().get(i);
                    if(x.getTableName().equalsIgnoreCase(node.getName())){
                        count++;
                    }
                }
                if(count != 0)
                {
                    ++count; // we assume this will produce a new table alias
                    int i=0;
                    while(i < diagram.getTables().size())
                    {
                        Table x = (Table)diagram.getTables().get(i);
                        if(x.getName().equalsIgnoreCase(node.getName()+count)){
                            count++;
                            i = 0;
                        }else{
                            i++;
                        }
                    }
                    t.setAlias(node.getName() + (count));
                }
                ForeignKey[] ekeys = node.getEntity().getExportedKeys();
                ForeignKey[] ikeys = node.getEntity().getImportedKeys();
                try {
                    Object[] o = node.getChildren();
                    int i,j,k,l;
                    for(i=0; i<o.length; i++)
                    {
                        if(o[i] instanceof ColumnNode)
                        {
                            ColumnNode cn = (ColumnNode)o[i];
                            boolean bForeign = false;
                            for(j=0; j<ikeys.length; j++)
                            {
                                for(k=0; k< ikeys[j].getNumberOfColumns(); k++)
                                {
                                    if(ikeys[j].getForeignColumnName(k).equalsIgnoreCase(cn.getName())){
                                        bForeign = true;
                                    }
                                }
                            }
                            Column c = new Column(cn.getName(), 
                                    cn.getColumn().getTypeName(), 
                                    cn.getColumn().isPrimaryKey(),
                                    bForeign,
                                    cn.getColumn().getSize(), 
                                    cn.getColumn().getNumberOfFractionalDigits(), 
                                    cn.getColumn().isNumeric(), 
                                    cn.getColumn().isReal());
                            t.addColumn(c);
                        }
                    }
                    /*
                     * that takes care of the columns, now we want relations
                     * 
                     * We want a special handling of the relations. I am still unsure whether to
                     * add as many instances of a certain table as there are relations. This is nice
                     * but a bit unintuitive. So now I opt for one link to the primary table. When
                     * the user then adds a new table, the link could go to the next table.
                     * 
                     * Finally, this is probably the best: Add all links to the first primary table.
                     * The user than knows that there is more than one table related to the table at hand.
                     * Any other instances of this table receive no links. The user can drag the endpoint of
                     * the relations on the first table to the primary tables added later.
                     * 
                     * When the user is smart enough to add the necessary primary tables first,
                     * this endpoint repositioning is not needed.
                     */
                    if (t.getName().equalsIgnoreCase(t.getTableName())) {
                        // this is not an aliased table
                        for (i = 0; i < ekeys.length; i++) {
                            for (j = 0; j < diagram.getTables().size(); j++) {
                                Table t2 = (Table) diagram.getTables().get(j);
                                // Using getName() here will result in aliased
                                // tables not getting any relations
                                if (t2.getTableName().equalsIgnoreCase(ekeys[i].getForeignEntityName())) {
                                    for (l = 0; l < ekeys[i].getNumberOfColumns(); l++) {
                                        String foreignColumnName = ekeys[i].getForeignColumnName(l);
                                        String localColumnName = ekeys[i].getLocalColumnName(l);
                                        new Relationship(ekeys[i].getName(), t2, foreignColumnName, t, localColumnName);
                                    }
                                }
                            }
                        }
                    }
                    for (i = 0; i < ikeys.length; i++) {
                        Table matching = null;
                        boolean bCreated = false;
                        for (j = 0; j < diagram.getTables().size(); j++) {
                            Table t2 = (Table) diagram.getTables().get(j);
                            if (t2.getTableName().equalsIgnoreCase(ikeys[i].getLocalEntityName())) {
                                matching = t2;
                                if (t2.getPrimaryKeyRelationships().size() == 0) {
                                    // create only one
                                    for (l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                                        String foreignColumnName = ikeys[i].getForeignColumnName(l);
                                        String localColumnName = ikeys[i].getLocalColumnName(l);
                                        new Relationship(ikeys[i].getName(), t, foreignColumnName, t2, localColumnName);
                                        bCreated = true;
                                    }
                                    break;
                                } else {
                                    for (k = 0; k < t2.getPrimaryKeyRelationships().size(); k++) {
                                        Relationship r = (Relationship) t2.getPrimaryKeyRelationships().get(k);
                                        if (!r.getPrimaryKeyTable().getName().equalsIgnoreCase(t2.getName())) {
                                            // create
                                            for (l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                                                String foreignColumnName = ikeys[i].getForeignColumnName(l);
                                                String localColumnName = ikeys[i].getLocalColumnName(l);
                                                new Relationship(ikeys[i].getName(), t, foreignColumnName, t2, localColumnName);
                                                bCreated = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        // if we get here no connection was possible to an aliased table.
                        // so we add it to the last table that matched.
                        if(matching != null && bCreated == false){
                            for (l = 0; l < ikeys[i].getNumberOfColumns(); l++) {
                                String foreignColumnName = ikeys[i].getForeignColumnName(l);
                                String localColumnName = ikeys[i].getLocalColumnName(l);
                                new Relationship(ikeys[i].getName(), t, foreignColumnName, matching, localColumnName);
                                bCreated = true;
                            }
                        }
                    }
                } catch (NotConnectedException e) {
                } catch (SQLException e) {
                }
                return t;
            }else if(template.getClass() == ColumnNode.class){
                ColumnNode cn = (ColumnNode) template;
                Column c = new Column(cn.getName(), 
                        cn.getColumn().getTypeName(), 
                        cn.getColumn().isPrimaryKey(),
                        false,
                        cn.getColumn().getSize(), 
                        cn.getColumn().getNumberOfFractionalDigits(), 
                        cn.getColumn().isNumeric(), 
                        cn.getColumn().isReal());
                return c;
            }else if(template == Column.class)
            {
                Column c = new Column();
                c.setType(type);
                c.setJavaType(javaType);
                return c;
            }
			return ((Class) template).newInstance();
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType()
	{
		return template;
	}

    public void setColumnType(String type)
    {
        this.type = type;
    }
    
    public void setJavaColumnType(String type)
    {
        this.javaType = type;
    }
}