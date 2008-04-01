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
package org.talend.dataquality.helpers;

import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.domain.DomainFactory;
import org.talend.dataquality.domain.RangeRestriction;

/**
 * @author scorreia
 * 
 * Helper class for Domain object.
 */
public class DomainHelper {

    private static final DomainFactory DOMAIN = DomainFactory.eINSTANCE;

    /**
     * Method "createDomain" creates a new Domain with the given name.
     * 
     * @param name the name of the domain (could be null)
     * @return the new domain.
     */
    public static Domain createDomain(String name) {
        Domain domain = DOMAIN.createDomain();
        domain.setName(name);

        return domain;
    }

    /**
     * Method "addRangeRestriction" creates a new range restriction and adds it to the given domain.
     * 
     * @param domain the domain to which a new range restriction will be added
     * @return the newly created range restriction
     */
    public static RangeRestriction addRangeRestriction(Domain domain) {
        RangeRestriction rangeRestriction = DOMAIN.createRangeRestriction();
        domain.getRanges().add(rangeRestriction);
        return rangeRestriction;
    }
}
