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
package org.talend.core.model.metadata.types;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public final class JavaType {

    private String label;

    private String id;

    private Class nullableClass;

    private Class primitiveClass;

    private boolean generateWithCanonicalName;

    /**
     * @param label
     * @param primitiveType
     * @param clazzType
     */
    protected JavaType(Class nullableClass, Class primitiveClass) {
        super();
        this.nullableClass = nullableClass;
        this.primitiveClass = primitiveClass;
        this.label = primitiveClass.getSimpleName() + " | " + nullableClass.getSimpleName();
        this.id = createId(nullableClass.getSimpleName());
    }

    /**
     * DOC amaumont Comment method "createId".
     * 
     * @param simpleName
     * @return
     */
    private String createId(String value) {
        return "id_" + value;
    }

    /**
     * @param label
     * @param nullableClass
     */
    public JavaType(Class nullableClass) {
        super();
        this.nullableClass = nullableClass;
        this.label = nullableClass.getSimpleName();
        this.id = createId(nullableClass.getSimpleName());
    }

    /**
     * @param label
     * @param nullableClass
     */
    public JavaType(Class nullableClass, boolean generateWithCanonicalName) {
        super();
        this.nullableClass = nullableClass;
        this.label = nullableClass.getSimpleName();
        this.id = createId(nullableClass.getSimpleName());
        this.generateWithCanonicalName = generateWithCanonicalName;
    }

    /**
     * @param label
     * @param id
     * @param nullableClass
     * @param primitiveClass
     */
    protected JavaType(String id, Class nullableClass, Class primitiveClass, String label) {
        super();
        this.label = label;
        this.nullableClass = nullableClass;
        this.primitiveClass = primitiveClass;
        this.id = createId(nullableClass.getSimpleName());
    }

    /**
     * DOC amaumont JavaType constructor comment.
     * 
     * @param id
     * @param nullableClass
     * @param primitiveClass
     */
    protected JavaType(String id, Class nullableClass, Class primitiveClass) {
        super();
        this.id = id;
        this.nullableClass = nullableClass;
        this.primitiveClass = primitiveClass;
    }

    /**
     * DOC amaumont JavaType constructor comment.
     * 
     * @param id
     * @param nullableClass
     */
    public JavaType(String id, Class nullableClass) {
        super();
        this.id = id;
        this.nullableClass = nullableClass;
    }

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Getter for id.
     * 
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for objectClass.
     * 
     * @return the objectClass
     */
    public Class getNullableClass() {
        return this.nullableClass;
    }

    /**
     * Getter for primitiveClass. Can be null.
     * 
     * @return the primitiveClass
     */
    public Class getPrimitiveClass() {
        return this.primitiveClass;
    }

    
    
    
    /**
     * Getter for generateWithCanonicalName.
     * @return the generateWithCanonicalName
     */
    public boolean isGenerateWithCanonicalName() {
        return this.generateWithCanonicalName;
    }

    /**
     * 
     * @return
     * @author
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("JavaType[");
        buffer.append("label = ").append(label);
        buffer.append(" nullableClass = ").append(nullableClass);
        buffer.append(" primitiveClass = ").append(primitiveClass);
        buffer.append("]");
        return buffer.toString();
    }

}
