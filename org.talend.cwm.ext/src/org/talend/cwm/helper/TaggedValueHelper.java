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
import java.util.Properties;

import org.eclipse.emf.common.util.EList;
import org.talend.cwm.constants.DevelopmentStatus;

import orgomg.cwm.objectmodel.core.CoreFactory;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.TaggedValue;

/**
 * @author scorreia
 * 
 * This class is a helper for handling TaggedValues.
 */
public final class TaggedValueHelper {

    /**
     * The tag used for setting a technical name.
     */
    public static final String TECH_NAME_TAGGED_VAL = "Technical Name";

    /**
     * The tag used when setting a column content type.
     */
    public static final String DATA_CONTENT_TYPE_TAGGED_VAL = "Content Nype";

    /**
     * The tag used when setting the development status of an object such as analysis, connection...
     */
    public static final String DEV_STATUS = "Status";

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
        TaggedValue taggedValue = getTaggedValue(tag, taggedValues);
        if (taggedValue == null) {
            return null;
        }
        return taggedValue.getValue();
    }

    /**
     * Method "getTaggedValue" retrieves the tagged value corresponding to the first matching tag.
     * 
     * @param tag the tag to match
     * @param taggedValues the tagged values in which to search for the given tag
     * @return the tagged value (if found) or null
     */
    public static TaggedValue getTaggedValue(String tag, Collection<TaggedValue> taggedValues) {
        if (tag == null) {
            return null;
        }
        TaggedValue value = null;
        for (TaggedValue taggedValue : taggedValues) {
            if (taggedValue == null) {
                continue;
            }
            if (tag.compareTo(taggedValue.getTag()) == 0) {
                value = taggedValue;
            }
        }
        return value;
    }

    /**
     * Method "createProperties".
     * 
     * @param taggedValues a list of pairs (key,value).
     * @return a properties from the given tag-value collection
     */
    public static Properties createProperties(Collection<TaggedValue> taggedValues) {
        Properties properties = new Properties();
        for (TaggedValue taggedValue : taggedValues) {
            if (taggedValue == null) {
                continue;
            }
            properties.put(taggedValue.getTag(), taggedValue.getValue());
        }
        return properties;
    }

    /**
     * Method "setTechnicalName".
     * 
     * @param element the CWM model element to which a tagged value with a technical name will be attached
     * @param technicalName the technical name of the given element
     * @return true if the technical name was not set before.
     */
    public static boolean setTechnicalName(ModelElement element, String technicalName) {
        return setTaggedValue(element, TECH_NAME_TAGGED_VAL, technicalName);
    }

    /**
     * Method "setDevStatus" sets the development status of the given element.
     * 
     * @param element
     * @param status the state to set.
     * @return
     */
    public static boolean setDevStatus(ModelElement element, DevelopmentStatus status) {
        return setTaggedValue(element, DEV_STATUS, status.getLiteral());
    }

    /**
     * Method "getDevStatus".
     * 
     * @param element such as Analysis, DataProvider...
     * @return the development status of the element
     */
    public static DevelopmentStatus getDevStatus(ModelElement element) {
        TaggedValue taggedValue = getTaggedValue(DEV_STATUS, element.getTaggedValue());
        if (taggedValue == null) {
            return DevelopmentStatus.DRAFT;
        }
        return DevelopmentStatus.get(taggedValue.getValue());
    }

    /**
     * Method "setTaggedValue".
     * 
     * @param element the CWM model element to which a tagged value will be attached (if not already set)
     * @param tag the tag
     * @param value the value to set
     * @return true if the value was not set before.
     */
    public static boolean setTaggedValue(ModelElement element, String tag, String value) {
        EList<TaggedValue> taggedValues = element.getTaggedValue();
        TaggedValue currentValue = TaggedValueHelper.getTaggedValue(tag, taggedValues);
        boolean create = (currentValue == null);
        if (create) {
            taggedValues.add(TaggedValueHelper.createTaggedValue(tag, value));
        } else {
            currentValue.setValue(value);
        }
        return create;
    }
}
