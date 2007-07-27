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
 * Enum for available Code Languages in the application.
 * 
 * $Id: EMetadataEncoding.java 3351 2007-05-04 12:14:00 +0000 (星期五, 04 五月 2007) plegall $
 * 
 */
public class DbDefaultLengthAndPrecision {
	private Integer defaultLength;
	private Integer defaultPrecision;
	private String dbTypeName;
	public String getDbTypeName() {
		return dbTypeName;
	}
	public void setDbTypeName(String dbTypeName) {
		this.dbTypeName = dbTypeName;
	}
	public Integer getDefaultLength() {
		return defaultLength;
	}
	public void setDefaultLength(Integer defaultLength) {
		this.defaultLength = defaultLength;
	}
	public Integer getDefaultPrecision() {
		return defaultPrecision;
	}
	public void setDefaultPrecision(Integer defaultPrecision) {
		this.defaultPrecision = defaultPrecision;
	}

}
