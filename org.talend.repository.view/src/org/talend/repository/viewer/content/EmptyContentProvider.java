// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * this class has been create because of eclipse bug : https://bugs.eclipse.org/bugs/show_bug.cgi?id=82973 this can be
 * used by a dummy content provider definition based on a dummy property tester, in order to start the pluging and
 * register all the necessary adapters.
 * 
 * here is the content provider defintion:
 * @formatter:off
 * <navigatorContent
            activeByDefault="true"
            contentProvider="org.talend.repository.view.di.viewer.content.DummyContentProvider"
            id="org.talend.repository.view.content.dummy.for.activation.di"
            name="name">
         <enablement>
            <and>
               <instanceof
                     value="org.talend.repository.navigator.TalendRepositoryRoot">
               </instanceof>
               <test
                     forcePluginActivation="true" <!-- this is what make the plugin to get started and there for activator called if adapter need to be registered -->
                     property="org.talend.repository.node.alwaysFalse">
               </test>
            </and>
         </enablement>
      </navigatorContent>
      
      and here is the property tester definition, the class must be in the plugin that needs to be started
      and manifest needs this line (Bundle-ActivationPolicy: lazy)
      <propertyTester
            class="org.talend.repository.tester.NodeTester"
            id="org.talend.repository.view.di.activation.tester"
            namespace="org.talend.repository.node"
            properties="alwaysFalse"
            type="org.talend.repository.navigator.TalendRepositoryRoot">
      </propertyTester>
      @formatter:on
      
 * 
 * 
 * */
public class EmptyContentProvider implements ITreeContentProvider {

    @Override
    public void dispose() {
        // left empty on purpose
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // left empty on purpose

    }

    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return new Object[0];

    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

}
