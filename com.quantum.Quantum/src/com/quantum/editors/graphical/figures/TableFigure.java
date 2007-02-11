/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;

/**
 * Figure used to represent a table in the schema
 * @author Phil Zoio
 */
public class TableFigure extends Figure
{

    public static Color tableColor = new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.TABLE_COLOR));
    public static Color backColor = new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.BACKGROUND_COLOR));
	private ColumnsFigure columnsFigure = new ColumnsFigure();
	private EditableLabel nameLabel;
    private EditableLabel aliasLabel;
    private Button filter;
//    private TableFigureMouseListener tfml;
    
    public TableFigure(EditableLabel name, EditableLabel alias)
    {
        this(name, alias, null);
    }
	public TableFigure(EditableLabel name)
	{
		this(name, null, null);
	}

	public TableFigure(EditableLabel name, EditableLabel alias, Button filter)
	{

		nameLabel = name;
        aliasLabel = alias;
        this.filter = filter;
		ToolbarLayout layout = new ToolbarLayout();
		layout.setVertical(true);
		layout.setStretchMinorAxis(true);
		setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(backColor);
		setForegroundColor(tableColor);
		setOpaque(true);

//        filter = new Button(ImageStore.getImage(ImageStore.FILTER));
//        tfml = new TableFigureMouseListener(whereClause);
//        filter.addMouseListener(tfml);
        Figure header = new Figure();
        BorderLayout hLayout = new BorderLayout();
        header.setLayoutManager(hLayout);

        Figure names= new Figure();
        ToolbarLayout vLayout = new ToolbarLayout();
        vLayout.setVertical(true);
        vLayout.setStretchMinorAxis(true);
        names.setLayoutManager(vLayout);
        
        this.filter.setVisible(true);
        this.filter.setEnabled(true);

		name.setForegroundColor(tableColor);
		names.add(name);
        names.add(alias);
        header.add(filter, BorderLayout.RIGHT);
        header.add(names, BorderLayout.CENTER);
        add(header);
        add(columnsFigure);
	}

	public void setSelected(boolean isSelected)
	{
		LineBorder lineBorder = (LineBorder) getBorder();
		if (isSelected)
		{
			lineBorder.setWidth(2);
		}
		else
		{
			lineBorder.setWidth(1);
		}
	}

	
	/**
	 * @return returns the label used to edit the name
	 */
    public EditableLabel getNameLabel()
    {
        return nameLabel;
    }

    public EditableLabel getAliasLabel()
    {
        return aliasLabel;
    }

	/**
	 * @return the figure containing the column lables
	 */
	public ColumnsFigure getColumnsFigure()
	{
		return columnsFigure;
	}    
}