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
package org.talend.commons.ui.swt.colorstyledtext;

import java.util.ArrayList;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Mode;
import org.talend.commons.ui.swt.colorstyledtext.jedit.Modes;
import org.talend.commons.ui.swt.colorstyledtext.rules.CToken;
import org.talend.commons.ui.swt.colorstyledtext.scanner.ColoringScanner;

/**
 * This component is an adaptation of a Color Editor for a StyledText.
 * 
 * The original editor can be found on http://www.gstaff.org/colorEditor/ <br/>
 * 
 * <b>How to use it, example :</b> <br/> <i> ColorManager colorManager = new
 * ColorManager(CoreActivator.getDefault().getPreferenceStore());<br/> ColorStyledText text = new
 * ColorStyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL, colorManager, ECodeLanguage.PERL.getName());</i> <br/><br/>
 * 
 * $Id$
 * 
 */
public class ColorStyledText extends StyledText {

    private ColorManager colorManager;

    private ColoringScanner scanner;

    private String languageMode;

    public ColorStyledText(Composite parent, int style, ColorManager colorManager, String languageMode) {
        super(parent, style);
        this.languageMode = languageMode;
        this.colorManager = colorManager;

        ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        Image image = sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY).createImage();
        Menu popupMenu = new Menu(this);
        MenuItem copyItem = new MenuItem(popupMenu, SWT.PUSH);
        copyItem.setText("Copy");
        copyItem.setImage(image);
        copyItem.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                copy();
            }
        });
        this.setMenu(popupMenu);
        MenuItem selectAllItem = new MenuItem(popupMenu, SWT.PUSH);
        selectAllItem.setText("Select All");
        selectAllItem.addListener(SWT.Selection, new Listener() {

            public void handleEvent(Event event) {
                selectAll();
            }
        });
        this.setMenu(popupMenu);

        Listener selectAllListener = new Listener() {

            public void handleEvent(Event event) {
                if (event.character == '\u0001') { // CTRL + A
                    selectAll();
                }
            }
        };

        addListener(SWT.KeyDown, selectAllListener);

        Mode mode = Modes.getMode(languageMode + ".xml");
        scanner = new ColoringScanner(mode, colorManager);

        addExtendedModifyListener(modifyListener);
    }

    @Override
    public void setText(String text) {
        super.setText(text);

        colorize(scanner);
    }

    protected void colorize(ColoringScanner scanner) {
        ArrayList<StyleRange> styles = new ArrayList<StyleRange>();
        scanner.parse(this.getText());
        IToken token;

        do {
            token = scanner.nextToken();
            if (!token.isEOF()) {
                if (token instanceof CToken) {
                    CToken ctoken = (CToken) token;
                    StyleRange styleRange;
                    styleRange = new StyleRange();
                    styleRange.start = scanner.getTokenOffset();
                    styleRange.length = scanner.getTokenLength();
                    if (ctoken.getType() == null) {
                        styleRange.fontStyle = colorManager.getStyleFor(ctoken.getColor());
                        styleRange.foreground = colorManager.getColor(ctoken.getColor());
                    } else {
                        styleRange.fontStyle = colorManager.getStyleForType(ctoken.getColor());
                        styleRange.foreground = colorManager.getColorForType(ctoken.getColor());
                    }
                    styles.add(styleRange);
                }
            }
        } while (!token.isEOF());

        setStyles(styles);
    }

    public void setStyles(final ArrayList<StyleRange> styles) {
        getDisplay().asyncExec(new Runnable() {

            public void run() {
                if (ColorStyledText.this.isDisposed()) {
                    return;
                }
                try {
                    int countChars = getCharCount();
                    for (int i = 0; i < styles.size(); i++) {
                        StyleRange styleRange = styles.get(i);
//                    System.out.println("styleRange.start="+styleRange.start);
//                    System.out.println("styleRange.length="+styleRange.length);
                        if (!(0 <= styleRange.start && styleRange.start + styleRange.length <= countChars)) {
                            continue;
                        }
                        setStyleRange(styleRange);
                    }
                } catch (RuntimeException t) {
//                    System.out.println(t);
                    ExceptionHandler.process(t);
                }
            }
        });
    }

    ExtendedModifyListener modifyListener = new ExtendedModifyListener() {

        public void modifyText(ExtendedModifyEvent event) {
            colorize(scanner);
        }
    };

    public ColorManager getColorManager() {
        return this.colorManager;
    }

    public String getLanguageMode() {
        return this.languageMode;
    }

    public ColoringScanner getScanner() {
        return this.scanner;
    }

}
