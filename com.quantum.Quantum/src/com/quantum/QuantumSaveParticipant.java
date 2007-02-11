package com.quantum;

import java.io.File;

import com.quantum.model.BookmarkCollection;
import com.quantum.view.subset.SubsetContentProvider;

import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;


class QuantumSaveParticipant implements ISaveParticipant {
	
	/**
	* @see org.eclipse.core.resources.ISaveParticipant#doneSaving(ISaveContext)
	*/
	public void doneSaving(ISaveContext context) {
	}
	/**
	 * @see org.eclipse.core.resources.ISaveParticipant#prepareToSave(ISaveContext)
	 */
	public void prepareToSave(ISaveContext context) throws CoreException {
	}

	/**
	 * @see org.eclipse.core.resources.ISaveParticipant#rollback(ISaveContext)
	 */
	public void rollback(ISaveContext context) {
	}

	/**
	 * @see org.eclipse.core.resources.ISaveParticipant#saving(ISaveContext)
	 */
	public void saving(ISaveContext context) throws CoreException {
		switch (context.getKind()) {
			case ISaveContext.FULL_SAVE :
				QuantumPlugin quantumPluginInstance = QuantumPlugin.getDefault();
				// save the plug in state 
				if (BookmarkCollection.getInstance().isAnythingChanged()
					|| SubsetContentProvider.getInstance().hasChanged()) {

					int saveNumber = context.getSaveNumber();
					String saveFileName = Messages.getString("QuantumPlugin.saveDir") + "-" + Integer.toString(saveNumber) + Messages.getString("QuantumPlugin.saveFileExtension"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					File f = quantumPluginInstance.getStateLocation().append(saveFileName).toFile();

					// if we fail to write, an exception is thrown and we do not update the path 
					quantumPluginInstance.writeImportantState(f);

					context.map(new Path(Messages.getString("QuantumPlugin.saveDir")), new Path(saveFileName)); //$NON-NLS-1$
					context.needSaveNumber();

				} else {
					System.out.println("Not saving unchanged bookmarks"); //$NON-NLS-1$
				}
				break;
			case ISaveContext.PROJECT_SAVE :
				// get the project related to this save operation 
				//IProject project = context.getProject(); 
				// save its information, if necessary 
				break;
			case ISaveContext.SNAPSHOT :
				// This operation needs to be really fast because 
				// snapshots can be requested frequently by the 
				// workspace. 
				break;
		}
	}
}