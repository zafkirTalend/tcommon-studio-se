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

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.businessinformation.BusinessinformationFactory;
import orgomg.cwm.foundation.businessinformation.Description;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * @author scorreia
 * 
 * Utility class for element Descriptions.
 */
public final class DescriptionHelper {

    public static final String DESCRIPTION = "Description";

    public static final String PURPOSE = "Purpose";

    private DescriptionHelper() {
    }

    /**
     * Method "addFunctionalDescription".
     * 
     * @param description the body of the Description to add to the element
     * @param element the described element
     * @return the added Description (its type is {@value DescriptionHelper#DESCRIPTION})
     */
    public static Description addFunctionalDescription(String description, ModelElement element) {
        Description descriptionObject = createDescription(DESCRIPTION, description);
        element.getDescription().add(descriptionObject);
        return descriptionObject;
    }

    /**
     * Method "addPurpose".
     * 
     * @param purpose the body of the Description to add to the element
     * @param element the described element
     * @return the added Description (its type is {@value DescriptionHelper#PURPOSE})
     */
    public static Description addPurpose(String purpose, ModelElement element) {
        Description descriptionObject = createDescription(PURPOSE, purpose);
        element.getDescription().add(descriptionObject);
        return descriptionObject;
    }

    /**
     * Method "getDescription".
     * 
     * @param element a CWM element
     * @return the description of the element or null
     */
    public static String getDescription(ModelElement element) {
        return getTypedDescriptionString(DESCRIPTION, element);
    }

    /**
     * Method "setDescription".
     * 
     * @param description the functional description to set or create
     * @param element a CWM element
     */
    public static void setDescription(String description, ModelElement element) {
        setTypedDescription(DESCRIPTION, description, element);
    }

    /**
     * Method "setPurpose".
     * 
     * @param purpose the purpose to set or create
     * @param element a CWM element
     */
    public static void setPurpose(String purpose, ModelElement element) {
        setTypedDescription(PURPOSE, purpose, element);
    }

    /**
     * Method "getPurpose".
     * 
     * @param element a CWM element
     * @return the purpose or null
     */
    public static String getPurpose(ModelElement element) {
        return getTypedDescriptionString(PURPOSE, element);
    }

    /**
     * Method "getTaggedDescription".
     * 
     * @param type the type of the searched description
     * @param element a CWM element
     * @return the description or null
     */
    private static String getTypedDescriptionString(String type, ModelElement element) {
        Description descr = getTypedDescription(type, element);
        return (descr != null) ? descr.getBody() : null;
    }

    private static Description getTypedDescription(String type, ModelElement element) {
        assert type != null;
        EList<Description> descriptions = element.getDescription();
        for (Description description : descriptions) {
            if (type.equals(description.getType())) {
                return description;
            }
        }
        return null;
    }

    private static void setTypedDescription(String type, String description, ModelElement element) {
        assert type != null;
        Description descr = getTypedDescription(type, element);
        if (descr != null) {
            descr.setBody(description);
        } else {
            element.getDescription().add(createDescription(type, description));
        }
    }

    /**
     * Method "createDescription".
     * 
     * @param type the type of the description
     * @param body the body of the description
     * @return the created description
     */
    public static Description createDescription(String type, String body) {
        Description description = BusinessinformationFactory.eINSTANCE.createDescription();
        description.setType(type);
        description.setBody(body);
        return description;
    }
}
