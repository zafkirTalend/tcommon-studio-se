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
package org.talend.cwm.helper;

import java.util.Collection;

import orgomg.cwm.objectmodel.core.CoreFactory;
import orgomg.cwm.objectmodel.core.TaggedValue;

/**
 * @author scorreia
 * 
 * This class is a helper for handling TaggedValues.
 */
public final class TaggedValueHelper {

    private TaggedValueHelper() {
    }

    /**
     * Method "createTaggedValue".
     * 
     * @param tag the tag of the tagged value to create
     * @param value the value of the tagged value to create
     * @return the created tagged value
     */
    public static TaggedValue createTaggedValue(String tag, String value) {
        TaggedValue taggedValue = CoreFactory.eINSTANCE.createTaggedValue();
        taggedValue.setTag(tag);
        taggedValue.setValue(value);
        return taggedValue;
    }

    /**
     * Method "getValue" retrieves the value corresponding to the first matching tag.
     * 
     * @param tag the tag to match
     * @param taggedValues the tagged values in which to search for the given tag
     * @return the value (if found) or null
     */
    public static String getValue(String tag, Collection<TaggedValue> taggedValues) {
        if (tag == null) {
            return null;
        }
        String value = null;
        for (TaggedValue taggedValue : taggedValues) {
            if (taggedValue == null) {
                continue;
            }
            if (tag.compareTo(taggedValue.getTag()) == 0) {
                value = taggedValue.getValue();
            }
        }
        return value;
    }
}
