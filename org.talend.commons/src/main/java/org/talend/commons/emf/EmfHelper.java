// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.emf;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/***/
public class EmfHelper {

    /**
     * WARNING : you may face StackOverflowError if you have bidirectional relationships or circular references it was
     * only tested with containment references.
     */
    @SuppressWarnings("unchecked")
    public static void visitChilds(EObject object) {
        for (Iterator iterator = object.eClass().getEAllReferences().iterator(); iterator.hasNext();) {
            EReference reference = (EReference) iterator.next();
            if (reference.isMany()) {
                List list = (List) object.eGet(reference);
                for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
                    Object get = iterator2.next();
                    if (get instanceof EObject) {
                        visitChilds((EObject) get);
                    }
                }
            } else {
                Object get = object.eGet(reference);
                if (get instanceof EObject) {
                    visitChilds((EObject) get);
                }
            }

        }
    }

}
