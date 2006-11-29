// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.ui.swt.formtools;

import java.util.List;

import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

/**
 * Convenience method to fill list with a List instead of a String array.<br/>
 * 
 * $Id$
 * 
 */
public class ListCombo implements Drawable {

    private Combo combo;

    private List items;

    public ListCombo(Composite parent, int style) {
        combo = new Combo(parent, style);
    }

    public void setItems(List items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            Object object = items.get(i);
            String toString = object.toString();
            combo.add(toString, i);
        }
    }

    public Object getSelectedItem() {
        int selectionIndex = combo.getSelectionIndex();
        if (selectionIndex >= 0 && selectionIndex < items.size()) {
            return items.get(selectionIndex);
        }
        return null;
    }

    public Combo getCombo() {
        return combo;
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addControlListener(org.eclipse.swt.events.ControlListener)
     */
    public void addControlListener(ControlListener listener) {
        this.combo.addControlListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Widget#addDisposeListener(org.eclipse.swt.events.DisposeListener)
     */
    public void addDisposeListener(DisposeListener listener) {
        this.combo.addDisposeListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addFocusListener(org.eclipse.swt.events.FocusListener)
     */
    public void addFocusListener(FocusListener listener) {
        this.combo.addFocusListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addHelpListener(org.eclipse.swt.events.HelpListener)
     */
    public void addHelpListener(HelpListener listener) {
        this.combo.addHelpListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void addKeyListener(KeyListener listener) {
        this.combo.addKeyListener(listener);
    }

    /**
     * @param eventType
     * @param listener
     * @see org.eclipse.swt.widgets.Widget#addListener(int, org.eclipse.swt.widgets.Listener)
     */
    public void addListener(int eventType, Listener listener) {
        this.combo.addListener(eventType, listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#addModifyListener(org.eclipse.swt.events.ModifyListener)
     */
    public void addModifyListener(ModifyListener listener) {
        this.combo.addModifyListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addMouseListener(org.eclipse.swt.events.MouseListener)
     */
    public void addMouseListener(MouseListener listener) {
        this.combo.addMouseListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addMouseMoveListener(org.eclipse.swt.events.MouseMoveListener)
     */
    public void addMouseMoveListener(MouseMoveListener listener) {
        this.combo.addMouseMoveListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addMouseTrackListener(org.eclipse.swt.events.MouseTrackListener)
     */
    public void addMouseTrackListener(MouseTrackListener listener) {
        this.combo.addMouseTrackListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addPaintListener(org.eclipse.swt.events.PaintListener)
     */
    public void addPaintListener(PaintListener listener) {
        this.combo.addPaintListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#addSelectionListener(org.eclipse.swt.events.SelectionListener)
     */
    public void addSelectionListener(SelectionListener listener) {
        this.combo.addSelectionListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#addTraverseListener(org.eclipse.swt.events.TraverseListener)
     */
    public void addTraverseListener(TraverseListener listener) {
        this.combo.addTraverseListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#addVerifyListener(org.eclipse.swt.events.VerifyListener)
     */
    public void addVerifyListener(VerifyListener listener) {
        this.combo.addVerifyListener(listener);
    }

    /**
     * @param changed
     * @see org.eclipse.swt.widgets.Composite#changed(org.eclipse.swt.widgets.Control[])
     */
    public void changed(Control[] changed) {
        this.combo.changed(changed);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#clearSelection()
     */
    public void clearSelection() {
        this.combo.clearSelection();
    }

    /**
     * @param wHint
     * @param hHint
     * @param changed
     * @return
     * @see org.eclipse.swt.widgets.Combo#computeSize(int, int, boolean)
     */
    public Point computeSize(int wHint, int hHint, boolean changed) {
        return this.combo.computeSize(wHint, hHint, changed);
    }

    /**
     * @param wHint
     * @param hHint
     * @return
     * @see org.eclipse.swt.widgets.Control#computeSize(int, int)
     */
    public Point computeSize(int wHint, int hHint) {
        return this.combo.computeSize(wHint, hHint);
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @see org.eclipse.swt.widgets.Scrollable#computeTrim(int, int, int, int)
     */
    public Rectangle computeTrim(int x, int y, int width, int height) {
        return this.combo.computeTrim(x, y, width, height);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#copy()
     */
    public void copy() {
        this.combo.copy();
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#cut()
     */
    public void cut() {
        this.combo.cut();
    }

    /**
     * @param index
     * @see org.eclipse.swt.widgets.Combo#deselect(int)
     */
    public void deselect(int index) {
        this.combo.deselect(index);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#deselectAll()
     */
    public void deselectAll() {
        this.combo.deselectAll();
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
        this.combo.dispose();
    }

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return this.combo.equals(obj);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#forceFocus()
     */
    public boolean forceFocus() {
        return this.combo.forceFocus();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getAccessible()
     */
    public Accessible getAccessible() {
        return this.combo.getAccessible();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getBackground()
     */
    public Color getBackground() {
        return this.combo.getBackground();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getBackgroundImage()
     */
    public Image getBackgroundImage() {
        return this.combo.getBackgroundImage();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#getBackgroundMode()
     */
    public int getBackgroundMode() {
        return this.combo.getBackgroundMode();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getBorderWidth()
     */
    public int getBorderWidth() {
        return this.combo.getBorderWidth();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getBounds()
     */
    public Rectangle getBounds() {
        return this.combo.getBounds();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#getChildren()
     */
    public Control[] getChildren() {
        return this.combo.getChildren();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Scrollable#getClientArea()
     */
    public Rectangle getClientArea() {
        return this.combo.getClientArea();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Widget#getData()
     */
    public Object getData() {
        return this.combo.getData();
    }

    /**
     * @param key
     * @return
     * @see org.eclipse.swt.widgets.Widget#getData(java.lang.String)
     */
    public Object getData(String key) {
        return this.combo.getData(key);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Widget#getDisplay()
     */
    public Display getDisplay() {
        return this.combo.getDisplay();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getEnabled()
     */
    public boolean getEnabled() {
        return this.combo.getEnabled();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getFont()
     */
    public Font getFont() {
        return this.combo.getFont();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getForeground()
     */
    public Color getForeground() {
        return this.combo.getForeground();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Scrollable#getHorizontalBar()
     */
    public ScrollBar getHorizontalBar() {
        return this.combo.getHorizontalBar();
    }

    /**
     * @param index
     * @return
     * @see org.eclipse.swt.widgets.Combo#getItem(int)
     */
    public String getItem(int index) {
        return this.combo.getItem(index);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getItemCount()
     */
    public int getItemCount() {
        return this.combo.getItemCount();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getItemHeight()
     */
    public int getItemHeight() {
        return this.combo.getItemHeight();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getItems()
     */
    public String[] getItems() {
        return this.combo.getItems();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#getLayout()
     */
    public Layout getLayout() {
        return this.combo.getLayout();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getLayoutData()
     */
    public Object getLayoutData() {
        return this.combo.getLayoutData();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#getLayoutDeferred()
     */
    public boolean getLayoutDeferred() {
        return this.combo.getLayoutDeferred();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getLocation()
     */
    public Point getLocation() {
        return this.combo.getLocation();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getMenu()
     */
    public Menu getMenu() {
        return this.combo.getMenu();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getMonitor()
     */
    public Monitor getMonitor() {
        return this.combo.getMonitor();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getOrientation()
     */
    public int getOrientation() {
        return this.combo.getOrientation();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getParent()
     */
    public Composite getParent() {
        return this.combo.getParent();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getSelection()
     */
    public Point getSelection() {
        return this.combo.getSelection();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getSelectionIndex()
     */
    public int getSelectionIndex() {
        return this.combo.getSelectionIndex();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getShell()
     */
    public Shell getShell() {
        return this.combo.getShell();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getSize()
     */
    public Point getSize() {
        return this.combo.getSize();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Widget#getStyle()
     */
    public int getStyle() {
        return this.combo.getStyle();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#getTabList()
     */
    public Control[] getTabList() {
        return this.combo.getTabList();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getText()
     */
    public String getText() {
        return this.combo.getText();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getTextHeight()
     */
    public int getTextHeight() {
        return this.combo.getTextHeight();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getTextLimit()
     */
    public int getTextLimit() {
        return this.combo.getTextLimit();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getToolTipText()
     */
    public String getToolTipText() {
        return this.combo.getToolTipText();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Scrollable#getVerticalBar()
     */
    public ScrollBar getVerticalBar() {
        return this.combo.getVerticalBar();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#getVisible()
     */
    public boolean getVisible() {
        return this.combo.getVisible();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Combo#getVisibleItemCount()
     */
    public int getVisibleItemCount() {
        return this.combo.getVisibleItemCount();
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.combo.hashCode();
    }

    /**
     * @param string
     * @param start
     * @return
     * @see org.eclipse.swt.widgets.Combo#indexOf(java.lang.String, int)
     */
    public int indexOf(String string, int start) {
        return this.combo.indexOf(string, start);
    }

    /**
     * @param string
     * @return
     * @see org.eclipse.swt.widgets.Combo#indexOf(java.lang.String)
     */
    public int indexOf(String string) {
        return this.combo.indexOf(string);
    }

    /**
     * @param hDC
     * @param data
     * @see org.eclipse.swt.widgets.Control#internal_dispose_GC(int, org.eclipse.swt.graphics.GCData)
     */
    public void internal_dispose_GC(int hDC, GCData data) {
        this.combo.internal_dispose_GC(hDC, data);
    }

    /**
     * @param data
     * @return
     * @see org.eclipse.swt.widgets.Control#internal_new_GC(org.eclipse.swt.graphics.GCData)
     */
    public int internal_new_GC(GCData data) {
        return this.combo.internal_new_GC(data);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Widget#isDisposed()
     */
    public boolean isDisposed() {
        return this.combo.isDisposed();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#isEnabled()
     */
    public boolean isEnabled() {
        return this.combo.isEnabled();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#isFocusControl()
     */
    public boolean isFocusControl() {
        return this.combo.isFocusControl();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#isLayoutDeferred()
     */
    public boolean isLayoutDeferred() {
        return this.combo.isLayoutDeferred();
    }

    /**
     * @param eventType
     * @return
     * @see org.eclipse.swt.widgets.Widget#isListening(int)
     */
    public boolean isListening(int eventType) {
        return this.combo.isListening(eventType);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#isReparentable()
     */
    public boolean isReparentable() {
        return this.combo.isReparentable();
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Control#isVisible()
     */
    public boolean isVisible() {
        return this.combo.isVisible();
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Composite#layout()
     */
    public void layout() {
        this.combo.layout();
    }

    /**
     * @param changed
     * @param all
     * @see org.eclipse.swt.widgets.Composite#layout(boolean, boolean)
     */
    public void layout(boolean changed, boolean all) {
        this.combo.layout(changed, all);
    }

    /**
     * @param changed
     * @see org.eclipse.swt.widgets.Composite#layout(boolean)
     */
    public void layout(boolean changed) {
        this.combo.layout(changed);
    }

    /**
     * @param changed
     * @see org.eclipse.swt.widgets.Composite#layout(org.eclipse.swt.widgets.Control[])
     */
    public void layout(Control[] changed) {
        this.combo.layout(changed);
    }

    /**
     * @param control
     * @see org.eclipse.swt.widgets.Control#moveAbove(org.eclipse.swt.widgets.Control)
     */
    public void moveAbove(Control control) {
        this.combo.moveAbove(control);
    }

    /**
     * @param control
     * @see org.eclipse.swt.widgets.Control#moveBelow(org.eclipse.swt.widgets.Control)
     */
    public void moveBelow(Control control) {
        this.combo.moveBelow(control);
    }

    /**
     * @param eventType
     * @param event
     * @see org.eclipse.swt.widgets.Widget#notifyListeners(int, org.eclipse.swt.widgets.Event)
     */
    public void notifyListeners(int eventType, Event event) {
        this.combo.notifyListeners(eventType, event);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Control#pack()
     */
    public void pack() {
        this.combo.pack();
    }

    /**
     * @param changed
     * @see org.eclipse.swt.widgets.Control#pack(boolean)
     */
    public void pack(boolean changed) {
        this.combo.pack(changed);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#paste()
     */
    public void paste() {
        this.combo.paste();
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Control#redraw()
     */
    public void redraw() {
        this.combo.redraw();
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param all
     * @see org.eclipse.swt.widgets.Control#redraw(int, int, int, int, boolean)
     */
    public void redraw(int x, int y, int width, int height, boolean all) {
        this.combo.redraw(x, y, width, height, all);
    }

    /**
     * @param start
     * @param end
     * @see org.eclipse.swt.widgets.Combo#remove(int, int)
     */
    public void remove(int start, int end) {
        this.combo.remove(start, end);
    }

    /**
     * @param index
     * @see org.eclipse.swt.widgets.Combo#remove(int)
     */
    public void remove(int index) {
        this.combo.remove(index);
    }

    /**
     * @param string
     * @see org.eclipse.swt.widgets.Combo#remove(java.lang.String)
     */
    public void remove(String string) {
        this.combo.remove(string);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Combo#removeAll()
     */
    public void removeAll() {
        this.combo.removeAll();
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeControlListener(org.eclipse.swt.events.ControlListener)
     */
    public void removeControlListener(ControlListener listener) {
        this.combo.removeControlListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Widget#removeDisposeListener(org.eclipse.swt.events.DisposeListener)
     */
    public void removeDisposeListener(DisposeListener listener) {
        this.combo.removeDisposeListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeFocusListener(org.eclipse.swt.events.FocusListener)
     */
    public void removeFocusListener(FocusListener listener) {
        this.combo.removeFocusListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeHelpListener(org.eclipse.swt.events.HelpListener)
     */
    public void removeHelpListener(HelpListener listener) {
        this.combo.removeHelpListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeKeyListener(org.eclipse.swt.events.KeyListener)
     */
    public void removeKeyListener(KeyListener listener) {
        this.combo.removeKeyListener(listener);
    }

    /**
     * @param eventType
     * @param listener
     * @see org.eclipse.swt.widgets.Widget#removeListener(int, org.eclipse.swt.widgets.Listener)
     */
    public void removeListener(int eventType, Listener listener) {
        this.combo.removeListener(eventType, listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#removeModifyListener(org.eclipse.swt.events.ModifyListener)
     */
    public void removeModifyListener(ModifyListener listener) {
        this.combo.removeModifyListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeMouseListener(org.eclipse.swt.events.MouseListener)
     */
    public void removeMouseListener(MouseListener listener) {
        this.combo.removeMouseListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeMouseMoveListener(org.eclipse.swt.events.MouseMoveListener)
     */
    public void removeMouseMoveListener(MouseMoveListener listener) {
        this.combo.removeMouseMoveListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeMouseTrackListener(org.eclipse.swt.events.MouseTrackListener)
     */
    public void removeMouseTrackListener(MouseTrackListener listener) {
        this.combo.removeMouseTrackListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removePaintListener(org.eclipse.swt.events.PaintListener)
     */
    public void removePaintListener(PaintListener listener) {
        this.combo.removePaintListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#removeSelectionListener(org.eclipse.swt.events.SelectionListener)
     */
    public void removeSelectionListener(SelectionListener listener) {
        this.combo.removeSelectionListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Control#removeTraverseListener(org.eclipse.swt.events.TraverseListener)
     */
    public void removeTraverseListener(TraverseListener listener) {
        this.combo.removeTraverseListener(listener);
    }

    /**
     * @param listener
     * @see org.eclipse.swt.widgets.Combo#removeVerifyListener(org.eclipse.swt.events.VerifyListener)
     */
    public void removeVerifyListener(VerifyListener listener) {
        this.combo.removeVerifyListener(listener);
    }

    /**
     * @param index
     * @see org.eclipse.swt.widgets.Combo#select(int)
     */
    public void select(int index) {
        this.combo.select(index);
    }

    /**
     * @param color
     * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
     */
    public void setBackground(Color color) {
        this.combo.setBackground(color);
    }

    /**
     * @param image
     * @see org.eclipse.swt.widgets.Control#setBackgroundImage(org.eclipse.swt.graphics.Image)
     */
    public void setBackgroundImage(Image image) {
        this.combo.setBackgroundImage(image);
    }

    /**
     * @param mode
     * @see org.eclipse.swt.widgets.Composite#setBackgroundMode(int)
     */
    public void setBackgroundMode(int mode) {
        this.combo.setBackgroundMode(mode);
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @see org.eclipse.swt.widgets.Control#setBounds(int, int, int, int)
     */
    public void setBounds(int x, int y, int width, int height) {
        this.combo.setBounds(x, y, width, height);
    }

    /**
     * @param rect
     * @see org.eclipse.swt.widgets.Control#setBounds(org.eclipse.swt.graphics.Rectangle)
     */
    public void setBounds(Rectangle rect) {
        this.combo.setBounds(rect);
    }

    /**
     * @param capture
     * @see org.eclipse.swt.widgets.Control#setCapture(boolean)
     */
    public void setCapture(boolean capture) {
        this.combo.setCapture(capture);
    }

    /**
     * @param cursor
     * @see org.eclipse.swt.widgets.Control#setCursor(org.eclipse.swt.graphics.Cursor)
     */
    public void setCursor(Cursor cursor) {
        this.combo.setCursor(cursor);
    }

    /**
     * @param data
     * @see org.eclipse.swt.widgets.Widget#setData(java.lang.Object)
     */
    public void setData(Object data) {
        this.combo.setData(data);
    }

    /**
     * @param key
     * @param value
     * @see org.eclipse.swt.widgets.Widget#setData(java.lang.String, java.lang.Object)
     */
    public void setData(String key, Object value) {
        this.combo.setData(key, value);
    }

    /**
     * @param enabled
     * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
     */
    public void setEnabled(boolean enabled) {
        this.combo.setEnabled(enabled);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Composite#setFocus()
     */
    public boolean setFocus() {
        return this.combo.setFocus();
    }

    /**
     * @param font
     * @see org.eclipse.swt.widgets.Combo#setFont(org.eclipse.swt.graphics.Font)
     */
    public void setFont(Font font) {
        this.combo.setFont(font);
    }

    /**
     * @param color
     * @see org.eclipse.swt.widgets.Control#setForeground(org.eclipse.swt.graphics.Color)
     */
    public void setForeground(Color color) {
        this.combo.setForeground(color);
    }

    /**
     * @param index
     * @param string
     * @see org.eclipse.swt.widgets.Combo#setItem(int, java.lang.String)
     */
    public void setItem(int index, String string) {
        this.combo.setItem(index, string);
    }

    /**
     * @param items
     * @see org.eclipse.swt.widgets.Combo#setItems(java.lang.String[])
     */
    public void setItems(String[] items) {
        this.combo.setItems(items);
    }

    /**
     * @param layout
     * @see org.eclipse.swt.widgets.Composite#setLayout(org.eclipse.swt.widgets.Layout)
     */
    public void setLayout(Layout layout) {
        this.combo.setLayout(layout);
    }

    /**
     * @param layoutData
     * @see org.eclipse.swt.widgets.Control#setLayoutData(java.lang.Object)
     */
    public void setLayoutData(Object layoutData) {
        this.combo.setLayoutData(layoutData);
    }

    /**
     * @param defer
     * @see org.eclipse.swt.widgets.Composite#setLayoutDeferred(boolean)
     */
    public void setLayoutDeferred(boolean defer) {
        this.combo.setLayoutDeferred(defer);
    }

    /**
     * @param x
     * @param y
     * @see org.eclipse.swt.widgets.Control#setLocation(int, int)
     */
    public void setLocation(int x, int y) {
        this.combo.setLocation(x, y);
    }

    /**
     * @param location
     * @see org.eclipse.swt.widgets.Control#setLocation(org.eclipse.swt.graphics.Point)
     */
    public void setLocation(Point location) {
        this.combo.setLocation(location);
    }

    /**
     * @param menu
     * @see org.eclipse.swt.widgets.Control#setMenu(org.eclipse.swt.widgets.Menu)
     */
    public void setMenu(Menu menu) {
        this.combo.setMenu(menu);
    }

    /**
     * @param orientation
     * @see org.eclipse.swt.widgets.Combo#setOrientation(int)
     */
    public void setOrientation(int orientation) {
        this.combo.setOrientation(orientation);
    }

    /**
     * @param parent
     * @return
     * @see org.eclipse.swt.widgets.Control#setParent(org.eclipse.swt.widgets.Composite)
     */
    public boolean setParent(Composite parent) {
        return this.combo.setParent(parent);
    }

    /**
     * @param redraw
     * @see org.eclipse.swt.widgets.Control#setRedraw(boolean)
     */
    public void setRedraw(boolean redraw) {
        this.combo.setRedraw(redraw);
    }

    /**
     * @param selection
     * @see org.eclipse.swt.widgets.Combo#setSelection(org.eclipse.swt.graphics.Point)
     */
    public void setSelection(Point selection) {
        this.combo.setSelection(selection);
    }

    /**
     * @param width
     * @param height
     * @see org.eclipse.swt.widgets.Control#setSize(int, int)
     */
    public void setSize(int width, int height) {
        this.combo.setSize(width, height);
    }

    /**
     * @param size
     * @see org.eclipse.swt.widgets.Control#setSize(org.eclipse.swt.graphics.Point)
     */
    public void setSize(Point size) {
        this.combo.setSize(size);
    }

    /**
     * @param tabList
     * @see org.eclipse.swt.widgets.Composite#setTabList(org.eclipse.swt.widgets.Control[])
     */
    public void setTabList(Control[] tabList) {
        this.combo.setTabList(tabList);
    }

    /**
     * @param string
     * @see org.eclipse.swt.widgets.Combo#setText(java.lang.String)
     */
    public void setText(String string) {
        this.combo.setText(string);
    }

    /**
     * @param limit
     * @see org.eclipse.swt.widgets.Combo#setTextLimit(int)
     */
    public void setTextLimit(int limit) {
        this.combo.setTextLimit(limit);
    }

    /**
     * @param string
     * @see org.eclipse.swt.widgets.Control#setToolTipText(java.lang.String)
     */
    public void setToolTipText(String string) {
        this.combo.setToolTipText(string);
    }

    /**
     * @param visible
     * @see org.eclipse.swt.widgets.Control#setVisible(boolean)
     */
    public void setVisible(boolean visible) {
        this.combo.setVisible(visible);
    }

    /**
     * @param count
     * @see org.eclipse.swt.widgets.Combo#setVisibleItemCount(int)
     */
    public void setVisibleItemCount(int count) {
        this.combo.setVisibleItemCount(count);
    }

    /**
     * @param x
     * @param y
     * @return
     * @see org.eclipse.swt.widgets.Control#toControl(int, int)
     */
    public Point toControl(int x, int y) {
        return this.combo.toControl(x, y);
    }

    /**
     * @param point
     * @return
     * @see org.eclipse.swt.widgets.Control#toControl(org.eclipse.swt.graphics.Point)
     */
    public Point toControl(Point point) {
        return this.combo.toControl(point);
    }

    /**
     * @param x
     * @param y
     * @return
     * @see org.eclipse.swt.widgets.Control#toDisplay(int, int)
     */
    public Point toDisplay(int x, int y) {
        return this.combo.toDisplay(x, y);
    }

    /**
     * @param point
     * @return
     * @see org.eclipse.swt.widgets.Control#toDisplay(org.eclipse.swt.graphics.Point)
     */
    public Point toDisplay(Point point) {
        return this.combo.toDisplay(point);
    }

    /**
     * @return
     * @see org.eclipse.swt.widgets.Widget#toString()
     */
    public String toString() {
        return this.combo.toString();
    }

    /**
     * @param traversal
     * @return
     * @see org.eclipse.swt.widgets.Control#traverse(int)
     */
    public boolean traverse(int traversal) {
        return this.combo.traverse(traversal);
    }

    /**
     * 
     * @see org.eclipse.swt.widgets.Control#update()
     */
    public void update() {
        this.combo.update();
    }

}
