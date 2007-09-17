// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
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
package org.talend.core.model.metadata;

/**
 * @author informix
 * 
 */
public class DbIgnoreLengthAndPrecision {
	private String ignoreLength;
	private String ignorePrecision;
	private String dbType;

	public String getIgnoreLength() {
		return ignoreLength;
	}

	public void setIgnoreLength(String ignoreLength) {
		this.ignoreLength = ignoreLength;
	}

	public String getIgnorePrecision() {
		return ignorePrecision;
	}

	public void setIgnorePrecision(String ignorePrecision) {
		this.ignorePrecision = ignorePrecision;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

}
