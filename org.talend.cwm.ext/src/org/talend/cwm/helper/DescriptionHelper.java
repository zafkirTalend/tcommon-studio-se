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
