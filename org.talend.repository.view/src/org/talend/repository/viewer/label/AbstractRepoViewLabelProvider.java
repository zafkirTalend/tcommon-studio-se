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
package org.talend.repository.viewer.label;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public abstract class AbstractRepoViewLabelProvider extends LabelProvider implements ICommonLabelProvider, IFontProvider,
        IColorProvider {

    @Override
    public String getDescription(Object anElement) {
        return null;
    }

    @Override
    public void restoreState(IMemento aMemento) {

    }

    @Override
    public void saveState(IMemento aMemento) {

    }

    @Override
    public Color getForeground(Object element) {
        return null;
    }

    @Override
    public Color getBackground(Object element) {
        return null;
    }

    @Override
    public Font getFont(Object element) {
        return null;
    }

    @Override
    public void init(ICommonContentExtensionSite aConfig) {

    }

}
