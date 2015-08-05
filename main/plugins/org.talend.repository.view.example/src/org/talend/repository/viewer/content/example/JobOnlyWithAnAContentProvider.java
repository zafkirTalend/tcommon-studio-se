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
package org.talend.repository.viewer.content.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.talend.repository.model.RepositoryNode;
import org.talend.repository.tester.example.ExampleTester;

/**
 * created by sgandon on 1 août 2012 Detailled comment
 * 
 */
public class JobOnlyWithAnAContentProvider extends org.talend.repository.view.di.viewer.content.JobDesignsContentProvider {

    ExampleTester jobTester = new ExampleTester();

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parentElement) {
        Object[] children = super.getChildren(parentElement);
        HashSet<Object> childrenSet = new HashSet<Object>();
        Collections.addAll(childrenSet, children);
        filterJobWithAnA(childrenSet);
        return childrenSet.toArray();
    }

    /**
     * DOC sgandon Comment method "filterJobWithAnA".
     * 
     * @param refreshTargets
     */
    private void filterJobWithAnA(Set potentialJobs) {
        List<Object> removedObjects = new ArrayList<Object>(potentialJobs.size());
        for (Object potentialJob : potentialJobs) {
            if (jobTester.isJob(potentialJob)) {
                RepositoryNode job = (RepositoryNode) potentialJob;
                String jobLabel = job.getLabel();
                if (jobLabel != null && !jobLabel.toLowerCase().contains("a")) {
                    removedObjects.add(potentialJob);
                }// else ignor the job
            }// else not a job.
        }
        potentialJobs.removeAll(removedObjects);
    }

}
