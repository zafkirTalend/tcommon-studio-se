package org.talend.designer.core;

import org.talend.core.model.process.IProcess;
import org.talend.core.model.properties.Item;

public interface IProcessConvertService {

	public IProcess getProcessFromItem(Item item, boolean loadScreenshots);
	
}
