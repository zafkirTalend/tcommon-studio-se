/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package org.talend.designer.webservice.ws.mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.talend.ws.exception.IllegalPropertyAccessException;
import org.talend.ws.exception.InvocationTargetPropertyAccessor;
import org.talend.ws.exception.LocalizedException;
import org.talend.ws.exception.NoSuchPropertyException;

/**
 * 
 * @author rlamarche
 */
public class SimplePropertyMapper implements PropertyMapper {

    private TypeMapper xmlBeanMapper;

    private String propertyName;

    private PropertyDescriptor propertyDescriptor;

    public SimplePropertyMapper(Class<?> clazz, TypeMapper xmlBeanMapper, String propertyName) throws LocalizedException {
        this.xmlBeanMapper = xmlBeanMapper;
        this.propertyName = propertyName;
        try {
            propertyDescriptor = PropertyUtils.getPropertyDescriptor(clazz.newInstance(), propertyName);
        } catch (IllegalAccessException ex) {
            throw new IllegalPropertyAccessException(propertyName, clazz.getName(), ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationTargetPropertyAccessor(propertyName, clazz.getName(), ex.getTargetException());
        } catch (NoSuchMethodException ex) {
            throw new NoSuchPropertyException(propertyName, clazz.getName(), ex);
        } catch (InstantiationException ex) {
            throw new LocalizedException("org.talend.ws.exception.Instantiation", new String[] { clazz.getName() }, ex);
        }
    }

    public String getMappedPropertyName() {
        return propertyName;
    }

    public void setValueTo(Object destination, Object value) throws LocalizedException {
        try {
            propertyDescriptor.getWriteMethod().invoke(destination, xmlBeanMapper.convertToType(value));
        } catch (IllegalAccessException ex) {
            throw new IllegalPropertyAccessException(propertyDescriptor.getName(), destination.getClass().getName(), ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationTargetPropertyAccessor(propertyDescriptor.getName(), destination.getClass().getName(), ex
                    .getTargetException());
        }
    }

    public Object getValueFrom(Object source) throws LocalizedException {
        try {
            return xmlBeanMapper.typeToValue(propertyDescriptor.getReadMethod().invoke(source));
        } catch (IllegalAccessException ex) {
            throw new IllegalPropertyAccessException(propertyDescriptor.getName(), source.getClass().getName(), ex);
        } catch (InvocationTargetException ex) {
            throw new InvocationTargetPropertyAccessor(propertyDescriptor.getName(), source.getClass().getName(), ex
                    .getTargetException());
        }
    }

    public Class<?> getMappedClass() {
        return xmlBeanMapper.getClazz();
    }

    public Object createProperty(Object value) throws LocalizedException {
        return xmlBeanMapper.convertToType(value);
    }

    public Object createValue(Object property) throws LocalizedException {
        return xmlBeanMapper.typeToValue(property);
    }
}
