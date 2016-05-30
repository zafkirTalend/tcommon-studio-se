package org.talend.designer.maven.tools.creator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This interface provides capabilities for third party bundles to contribute to the maven pom which is used to 
 * create jobs jar files.
 * @See {@link PomJobExtensionRegistry}
 * @author jclaude
 */
public interface IPomJobExtension {

	void updatePom(IProgressMonitor monitor, IFile pomFile);

}
