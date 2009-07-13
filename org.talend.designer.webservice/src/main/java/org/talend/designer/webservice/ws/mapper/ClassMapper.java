/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */

package org.talend.designer.webservice.ws.mapper;

import org.apache.ws.commons.schema.XmlSchemaType;

/**
 * 
 * @author rlamarche
 */
public interface ClassMapper {

    public Class<?> getClassForType(XmlSchemaType xmlSchemaType);
}
