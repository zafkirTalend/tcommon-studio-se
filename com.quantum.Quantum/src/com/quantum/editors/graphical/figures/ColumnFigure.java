package com.quantum.editors.graphical.figures;

import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;

public class ColumnFigure extends Figure {

    
    public ColumnFigure(EditableLabel name, EditableLabel alias, Button type)
    {
        ToolbarLayout layout = new ToolbarLayout();
        layout.setVertical(false);
        setLayoutManager(layout);
        setOpaque(true);
        setBackgroundColor(new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.BACKGROUND_COLOR)));
        setForegroundColor(new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.COLUMN_COLOR)));
        add(name);
        add(type);
        add(alias);
    }
    
}
