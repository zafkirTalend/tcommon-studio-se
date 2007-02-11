/*
 * Created on Jul 13, 2004
 */
package com.quantum.editors.graphical.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;

import com.quantum.PluginPreferences;
import com.quantum.QuantumPlugin;

/**
 * Figure used to hold the column labels
 * @author Phil Zoio
 */
public class ColumnsFigure extends Figure
{

	public ColumnsFigure()
	{
		FlowLayout layout = new FlowLayout();
		layout.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layout.setStretchMinorAxis(false);
		layout.setHorizontal(false);
		setLayoutManager(layout);
		setBorder(new ColumnFigureBorder());
		setBackgroundColor(new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.BACKGROUND_COLOR)));
		setForegroundColor(new Color(null, PreferenceConverter.getColor(QuantumPlugin.getDefault().getPreferenceStore(), PluginPreferences.COLUMN_COLOR)));
		setOpaque(true);
	}

	class ColumnFigureBorder extends AbstractBorder
	{

		public Insets getInsets(IFigure figure)
		{
			return new Insets(5, 3, 3, 1);
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets)
		{
			graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(), tempRect.getTopRight());
		}
	}
}