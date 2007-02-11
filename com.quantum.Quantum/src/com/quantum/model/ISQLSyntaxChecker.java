package com.quantum.model;

import org.eclipse.core.resources.IMarker;

public interface ISQLSyntaxChecker {

	public IMarker[] checkSyntax();
}
