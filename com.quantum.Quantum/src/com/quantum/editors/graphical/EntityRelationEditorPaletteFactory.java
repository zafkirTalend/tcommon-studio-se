/*******************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elias Volanakis - initial API and implementation
 *******************************************************************************/
package com.quantum.editors.graphical;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.quantum.ImageStore;
import com.quantum.QuantumPlugin;
import com.quantum.editors.graphical.dnd.DataElementFactoryFromPalette;
import com.quantum.editors.graphical.model.Column;
import com.quantum.editors.graphical.model.Relationship;
import com.quantum.editors.graphical.model.Table;
/**
 * Utility class that can create a GEF Palette.
 * @see #createPalette() 
 * @author Elias Volanakis
 */
public class EntityRelationEditorPaletteFactory {

	/** Preference ID used to persist the palette location. */
	private  final static String PALETTE_DOCK_LOCATION = "EntityRelationEditorPaletteFactory.Location";
	/** Preference ID used to persist the palette size. */
	private  final static String PALETTE_SIZE = "EntityRelationEditorPaletteFactory.Size";
	/** Preference ID used to persist the flyout palette's state. */
	private  final static String PALETTE_STATE = "EntityRelationEditorPaletteFactory.State";


	public static PaletteContainer createERDrawer() {
		PaletteDrawer componentsDrawer = new PaletteDrawer("Entity");
		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry(
				"Table", 
				"Create a table", 
				Table.class,
				new DataElementFactoryFromPalette(Table.class, ""), 
				ImageStore.getImageDescriptor(ImageStore.TABLE),
                ImageStore.getImageDescriptor(ImageStore.TABLE)
		);
		componentsDrawer.add(component);
	
        component = new CombinedTemplateCreationEntry(
                "Column",
                "Add a column", 
                Column.class,
                new DataElementFactoryFromPalette(Column.class, ""), 
                ImageStore.getImageDescriptor(ImageStore.COLUMN),
                ImageStore.getImageDescriptor(ImageStore.COLUMN)
                );
        componentsDrawer.add(component);
        return componentsDrawer;
	
	}
		
	/**
	 * Creates the PaletteRoot and adds all palette elements.
	 * Use this factory method to create a new palette for your graphical editor.
	 * @return a new PaletteRoot
	 */
	public static PaletteRoot createPalette() {
		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(createERDrawer());
		return palette;
	}
	
	/**
	 * Return a FlyoutPreferences instance used to save/load the preferences of a flyout palette.
	 */
	public static FlyoutPreferences createPalettePreferences() {
		return new FlyoutPreferences() {
			private IPreferenceStore getPreferenceStore() {
				return QuantumPlugin.getDefault().getPreferenceStore();
			}
			public int getDockLocation() {
				return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
			}
			public int getPaletteState() {
				return getPreferenceStore().getInt(PALETTE_STATE);
			}
			public int getPaletteWidth() {
				return getPreferenceStore().getInt(PALETTE_SIZE);
			}
			public void setDockLocation(int location) {
				getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
			}
			public void setPaletteState(int state) {
				getPreferenceStore().setValue(PALETTE_STATE, state);
			}
			public void setPaletteWidth(int width) {
				getPreferenceStore().setValue(PALETTE_SIZE, width);
			}
		};
	}
	
	/** Create the "Tools" group. */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteGroup toolGroup = new PaletteGroup("Tools");
	
		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolGroup.add(tool);
		palette.setDefaultEntry(tool);
		
		// Add a marquee tool to the group
		toolGroup.add(new MarqueeToolEntry());
	
		// Add a (unnamed) separator to the group
		toolGroup.add(new PaletteSeparator());
	
		// Add (solid-line) connection tool 
		tool = new ConnectionCreationToolEntry(
				"Relation",
				"Create a foreign key/primary key relation",
				new CreationFactory() {
					public Object getNewObject() { return null; }
					public Object getObjectType() { return Relationship.SOLID_CONNECTION; }
				},
                ImageStore.getImageDescriptor(ImageStore.RELATIONSHIPSON),
                ImageStore.getImageDescriptor(ImageStore.RELATIONSHIPSON)
                );
                
		toolGroup.add(tool);
		
		return toolGroup;
	}
	
	/** Utility class. */
	public EntityRelationEditorPaletteFactory() {
		// Utility class
	}

}