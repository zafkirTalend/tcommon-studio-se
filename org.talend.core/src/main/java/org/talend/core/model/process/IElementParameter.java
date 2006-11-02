// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.core.model.process;

import java.util.List;

import org.talend.core.model.temp.ECodeLanguage;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IElementParameter {

    public void setName(final String s);

    public String getVariableName();

    public void setCategory(final EComponentCategory cat);

    public EComponentCategory getCategory();

    public void setDisplayName(final String s);

    public void setField(final EParameterFieldType type);

    public void setValue(final Object o);

    public String getName();

    public String getDisplayName();

    public EParameterFieldType getField();

    public Object getValue();

    public int getNbLines();

    public void setNbLines(final int nbLines);

    public int getNumRow();

    public void setNumRow(final int numRow);

    public boolean isReadOnly();

    public void setReadOnly(final boolean readOnly);

    public boolean isRequired();

    public void setRequired(final boolean required);

    public void setShow(final boolean show);

    // for combo box (CLOSED_LIST)
    public void setDefaultClosedListValue(ECodeLanguage language, final Object o);

    public void setListItemsDisplayName(final String[] list);

    public void setListItemsDisplayName(ECodeLanguage language, final String[] list);

    public void setListItemsDisplayCodeName(final String[] list);

    public void setListItemsDisplayCodeName(ECodeLanguage language, final String[] list);

    public void setListItemsValue(final Object[] list);

    public void setListItemsValue(ECodeLanguage language, final Object[] list);

    public Object getDefaultClosedListValue(ECodeLanguage language);

    public String[] getListItemsDisplayName(ECodeLanguage language);

    public String[] getListItemsDisplayCodeName(ECodeLanguage language);

    public Object[] getListItemsValue(ECodeLanguage language);

    public String getRepositoryValue();

    public void setRepositoryValue(String repositoryValue);

    public boolean isRepositoryValueUsed();

    public void setRepositoryValueUsed(boolean repositoryUsed);

    public String[] getListRepositoryItems(ECodeLanguage language);

    public void setListRepositoryItems(ECodeLanguage language, final String[] list);

    public String getShowIf();

    public void setShowIf(String showIf);

    public String getNotShowIf();

    public void setNotShowIf(String notShowIf);

    public boolean isShow(List<? extends IElementParameter> listParam);

    public void setListItemsShowIf(final String[] list);

    public void setListItemsShowIf(ECodeLanguage language, final String[] list);

    public String[] getListItemsShowIf(ECodeLanguage language);

    public void setListItemsNotShowIf(final String[] list);

    public void setListItemsNotShowIf(ECodeLanguage language, final String[] list);

    public String[] getListItemsNotShowIf(ECodeLanguage language);

    public boolean isShow(String conditionShowIf, String conditionNotShowIf, List<? extends IElementParameter> listParam);

    public List<IElementParameterDefaultValue> getDefaultValues();

    public void setDefaultValues(List<IElementParameterDefaultValue> defaultValues);

    public void setValueToDefault(List<? extends IElementParameter> listParam);
}
