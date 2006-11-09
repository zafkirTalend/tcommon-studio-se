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
package org.talend.commons.ui.swt.drawing.background;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.talend.commons.utils.performance.IPerformanceEvaluatorListener;
import org.talend.commons.utils.performance.PerformanceEvaluator;
import org.talend.commons.utils.performance.PerformanceEvaluatorEvent;
import org.talend.commons.utils.threading.AsynchronousThreading;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public abstract class BackgroundRefresher {

    /**
     * in seconds.
     */
    private static final int TIME_BEFORE_REEVALUATE_PERFORMANCE = 30;

    protected Image bgImage1;

    protected Image bgImage2;

    private Image oldImage;

    private final PerformanceEvaluator performanceEvaluator = new PerformanceEvaluator();

    protected Composite commonParent;

    private boolean antialiasAllowed;

    private Color backgroundColor;

    private Thread threadToEvaluatePerformance;

    /**
     * DOC amaumont Linker constructor comment.
     * 
     * @param commonParent
     */
    public BackgroundRefresher(Composite commonParent) {
        super();
        this.commonParent = commonParent;
        init();
    }

    private void init() {
        initTimeLimitForBackgroundRefresh();
        commonParent.addControlListener(new ControlListener() {

            public void controlMoved(ControlEvent e) {
            }

            public void controlResized(ControlEvent e) {
                createBgImages();
                updateBackground();
//                updateBackground(true, false);
            }

        });


    }

    private void initTimeLimitForBackgroundRefresh() {
        (new Object() {

            void init() {
                performanceEvaluator.addListener(new IPerformanceEvaluatorListener() {

                    public void handleEvent(PerformanceEvaluatorEvent event) {
                        antialiasAllowed = event.getIndicePerformance() < 310;
                    }
                });
            }
        }).init();

        new AsynchronousThreading(50, new Runnable() {

            public void run() {

                launchEvaluatingPerformanceLoop();

            }
        }).start();

    }

    /**
     * Getter for commonParent.
     * 
     * @return the commonParent
     */
    public Composite getCommonParent() {
        return this.commonParent;
    }

    /**
     * DOC amaumont Comment method "updateBackground".
     */
    public void updateBackground() {
        if (commonParent.isDisposed()) {
            return;
        }

        oldImage = commonParent.getBackgroundImage();
        Image newImage = null;
        if (oldImage == null || oldImage.isDisposed()
//                || bgImage1 == null || bgImage1.isDisposed() 
//                || bgImage2 == null || bgImage2.isDisposed() 
        ) {
            createBgImages();
            newImage = bgImage1;
        } else {
            if (oldImage == bgImage1) {
                newImage = bgImage2;
            } else {
                newImage = bgImage1;
            }
        }

        if (newImage != null && !newImage.isDisposed()) {

            GC gc = new GC(newImage);
            
            drawBackground(gc);

            gc.dispose();

            commonParent.setBackgroundImage(newImage);

            clearImage(oldImage);
            oldImage = newImage;

        }

    }

    /**
     * DOC amaumont Comment method "drawBackground".
     * 
     * @param gc
     */
    public abstract void drawBackground(GC gc);

    protected void createBgImages() {
        Rectangle clientArea = commonParent.getClientArea();
        Rectangle imageArea = new Rectangle(0, 0, clientArea.width, clientArea.height);
        if (imageArea.width > 0 && imageArea.height > 0) {
            releaseBgImages();
            bgImage1 = new Image(commonParent.getDisplay(), imageArea);
            bgImage2 = new Image(commonParent.getDisplay(), imageArea);
            clearImage(bgImage1);
            clearImage(bgImage2);
        }
    }

    public void releaseBgImages() {
        if (bgImage1 != null) {
            bgImage1.dispose();
        }
        if (bgImage2 != null) {
            bgImage2.dispose();
        }
    }

    protected void clearImage(final Image image) {
        if (image != null && !image.isDisposed()) {
            GC gc = new GC(image);
            gc.setBackground(backgroundColor == null ? commonParent.getBackground() : backgroundColor);
            gc.fillRectangle(commonParent.getClientArea());
            gc.dispose();
        }
    }

    /**
     * This method must be call one time by shell opened.
     * 
     */
    protected void launchEvaluatingPerformanceLoop() {
        threadToEvaluatePerformance = new Thread() {

            @Override
            public void run() {
                performanceEvaluator.evaluate(); // first evaluation is not representative
                try {
                    // to start evaluation after window loaded
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                while (!commonParent.isDisposed()) {
                    performanceEvaluator.evaluate();
                    try {
                        // to start evaluation after window loaded
                        Thread.sleep(TIME_BEFORE_REEVALUATE_PERFORMANCE * 1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        threadToEvaluatePerformance.start();
    }

    /**
     * Getter for backgroundColor.
     * 
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets the backgroundColor.
     * 
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Point convertPointToCommonParentOrigin(Point point, Composite child) {
        Point returnedPoint = new Point(point.x, point.y);
        while (child != commonParent) {
            Rectangle bounds = child.getBounds();
            child = child.getParent();
            ScrollBar vScrollBar = child.getVerticalBar();
            if (vScrollBar != null) {
                returnedPoint.y += vScrollBar.getSelection();
            }
            returnedPoint.x += bounds.x;
            returnedPoint.y += bounds.y;
        }
        return returnedPoint;
    }

    
    /**
     * Getter for antialiasActivated.
     * @return the antialiasActivated
     */
    public boolean isAntialiasAllowed() {
        return this.antialiasAllowed;
    }

    
}
