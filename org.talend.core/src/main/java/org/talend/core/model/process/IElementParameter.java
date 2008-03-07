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
package org.talend.core.model.process;

import java.util.List;
import java.util.Map;

import org.talend.core.model.properties.Item;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public interface IElementParameter extends Cloneable {

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

    public void setLinkedRepositoryItem(final Item item);

    public Item getLinkedRepositoryItem();

    // for combo box (CLOSED_LIST)
    public void setDefaultClosedListValue(final Object o);

    public void setListItemsDisplayName(final String[] list);

    public void setListItemsDisplayCodeName(final String[] list);

    public void setListItemsValue(final Object[] list);

    public Object getDefaultClosedListValue();

    public String[] getListItemsDisplayName();

    public String[] getListItemsDisplayCodeName();

    public Object[] getListItemsValue();

    public String getRepositoryValue();

    public void setRepositoryValue(String repositoryValue);

    public boolean isRepositoryValueUsed();

    public void setRepositoryValueUsed(boolean repositoryUsed);

    public String[] getListRepositoryItems();

    public void setListRepositoryItems(final String[] list);

    public int getIndexOfItemFromList(String item);

    public String getShowIf();

    public void setShowIf(String showIf);

    public String getNotShowIf();

    public void setNotShowIf(String notShowIf);

    public boolean isShow(List<? extends IElementParameter> listParam);

    public void setListItemsShowIf(final String[] list);

    public String[] getListItemsShowIf();

    public void setListItemsNotShowIf(final String[] list);

    public String[] getListItemsNotShowIf();

    public boolean isShow(String conditionShowIf, String conditionNotShowIf, List<? extends IElementParameter> listParam);

    public List<IElementParameterDefaultValue> getDefaultValues();

    public void setDefaultValues(List<IElementParameterDefaultValue> defaultValues);

    public void setValueToDefault(List<? extends IElementParameter> listParam);

    public void setElement(IElement element);

    public IElement getElement();

    public boolean isBasedOnSchema();

    public void setBasedOnSchema(boolean basedOnSchema);

    public String getFilter();

    public void setFilter(String filter);

    public boolean isNoCheck();

    public void setNoCheck(boolean noCheck);

    public String getContext();

    public void setContext(String context);

    public String getGroup();

    public void setGroup(String groupName);

    public void setGroupDisplayName(String groupDisplayName);

    public String getGroupDisplayName();

    public Map<String, IElementParameter> getChildParameters();

    public IElementParameter getParentParameter();

    public void setParentParameter(IElementParameter parentParameter);
}
