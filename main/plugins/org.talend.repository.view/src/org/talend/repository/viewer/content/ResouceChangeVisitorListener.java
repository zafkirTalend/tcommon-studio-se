// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.viewer.content;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

/**
 * DOC sgandon class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
 * 
 */
public class ResouceChangeVisitorListener implements IResourceChangeListener {

    private Viewer viewer;

    private final RunnableResourceVisitor visitor;

    /**
     * DOC sgandon class global comment. Detailled comment <br/>
     * 
     * $Id: talend.epf 55206 2011-02-15 17:32:14Z mhirt $
     * 
     */
    public abstract static class RunnableResourceVisitor implements IResourceDeltaVisitor {

        private Collection<Runnable> runnables;

        public void setRunnables(Collection<Runnable> runnables) {
            this.runnables = runnables;
        }

        @Override
        public boolean visit(IResourceDelta delta) throws CoreException {
            return visit(delta, runnables);
        }

        /**
         * DOC sgandon Comment method "visit".
         * 
         * @param delta
         * @param runnables2
         * @return
         */
        abstract protected boolean visit(IResourceDelta delta, Collection<Runnable> runnables2);
    }

    public ResouceChangeVisitorListener(Viewer viewer, RunnableResourceVisitor visitor) {
        this.viewer = viewer;
        this.visitor = visitor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent
     * )
     */
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        if (viewer == null) {
            return;
        }
        final Control ctrl = viewer.getControl();
        if (ctrl == null || ctrl.isDisposed()) {
            return;
        }
        final Collection<Runnable> runnables = new ArrayList<Runnable>();
        visitor.setRunnables(runnables);
        try {
            event.getDelta().accept(visitor);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (!runnables.isEmpty()) {
            // Are we in the UIThread? If so spin it until we are done
            if (ctrl.getDisplay().getThread() == Thread.currentThread()) {
                runUpdates(runnables);
            } else {
                ctrl.getDisplay().asyncExec(new Runnable() {

                    /*
                     * (non-Javadoc)
                     * 
                     * @see java.lang.Runnable#run()
                     */
                    @Override
                    public void run() {
                        // Abort if this happens after disposes
                        Control ctrl2 = viewer.getControl();
                        if (ctrl2 == null || ctrl2.isDisposed()) {
                            return;
                        }

                        runUpdates(runnables);
                    }
                });
            }
        }// else do nothing caus there is nothing to do.
    }

    /**
     * Run all of the runnables that are the widget updates
     * 
     * @param runnables
     */
    private void runUpdates(Collection<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            runnable.run();
        }

    }
}
