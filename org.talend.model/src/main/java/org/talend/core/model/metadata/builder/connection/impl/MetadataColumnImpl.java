/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.MetadataColumn;
import org.talend.core.model.metadata.builder.connection.MetadataTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metadata Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getTalendType <em>Talend Type</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#isKey <em>Key</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getOriginalField <em>Original Field</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.MetadataColumnImpl#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetadataColumnImpl extends AbstractMetadataObjectImpl implements MetadataColumn 
{
    /**
     * The default value of the '{@link #getSourceType() <em>Source Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceType()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceType() <em>Source Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceType()
     * @generated
     * @ordered
     */
    protected String sourceType = SOURCE_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultValue()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_VALUE_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefaultValue()
     * @generated
     * @ordered
     */
    protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getTalendType() <em>Talend Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTalendType()
     * @generated
     * @ordered
     */
    protected static final String TALEND_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTalendType() <em>Talend Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTalendType()
     * @generated
     * @ordered
     */
    protected String talendType = TALEND_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isKey()
     * @generated
     * @ordered
     */
    protected static final boolean KEY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isKey() <em>Key</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isKey()
     * @generated
     * @ordered
     */
    protected boolean key = KEY_EDEFAULT;

    /**
     * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNullable()
     * @generated
     * @ordered
     */
    protected static final boolean NULLABLE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNullable()
     * @generated
     * @ordered
     */
    protected boolean nullable = NULLABLE_EDEFAULT;

    /**
     * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLength()
     * @generated
     * @ordered
     */
    protected static final int LENGTH_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLength()
     * @generated
     * @ordered
     */
    protected int length = LENGTH_EDEFAULT;

    /**
     * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrecision()
     * @generated
     * @ordered
     */
    protected static final int PRECISION_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrecision()
     * @generated
     * @ordered
     */
    protected int precision = PRECISION_EDEFAULT;

    /**
     * The default value of the '{@link #getOriginalField() <em>Original Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOriginalField()
     * @generated
     * @ordered
     */
	protected static final String ORIGINAL_FIELD_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getOriginalField() <em>Original Field</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOriginalField()
     * @generated
     * @ordered
     */
	protected String originalField = ORIGINAL_FIELD_EDEFAULT;

    /**
     * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected static final String PATTERN_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected String pattern = PATTERN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MetadataColumnImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.METADATA_COLUMN;
    }

    public boolean isReadOnly() {
        MetadataTable table = getTable();
        return table == null ? false: table.isReadOnly();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceType(String newSourceType) {
        String oldSourceType = sourceType;
        sourceType = newSourceType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__SOURCE_TYPE, oldSourceType, sourceType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDefaultValue(String newDefaultValue) {
        String oldDefaultValue = defaultValue;
        defaultValue = newDefaultValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__DEFAULT_VALUE, oldDefaultValue, defaultValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTalendType() {
        return talendType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTalendType(String newTalendType) {
        String oldTalendType = talendType;
        talendType = newTalendType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__TALEND_TYPE, oldTalendType, talendType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(boolean newKey) {
        boolean oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNullable(boolean newNullable) {
        boolean oldNullable = nullable;
        nullable = newNullable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__NULLABLE, oldNullable, nullable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLength() {
        return length;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLength(int newLength) {
        int oldLength = length;
        length = newLength;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__LENGTH, oldLength, length));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrecision(int newPrecision) {
        int oldPrecision = precision;
        precision = newPrecision;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__PRECISION, oldPrecision, precision));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MetadataTable getTable() {
        if (eContainerFeatureID != ConnectionPackage.METADATA_COLUMN__TABLE) return null;
        return (MetadataTable)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTable(MetadataTable newTable, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newTable, ConnectionPackage.METADATA_COLUMN__TABLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTable(MetadataTable newTable) {
        if (newTable != eInternalContainer() || (eContainerFeatureID != ConnectionPackage.METADATA_COLUMN__TABLE && newTable != null)) {
            if (EcoreUtil.isAncestor(this, newTable))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newTable != null)
                msgs = ((InternalEObject)newTable).eInverseAdd(this, ConnectionPackage.METADATA_TABLE__COLUMNS, MetadataTable.class, msgs);
            msgs = basicSetTable(newTable, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__TABLE, newTable, newTable));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getOriginalField() {
        return originalField;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOriginalField(String newOriginalField) {
        String oldOriginalField = originalField;
        originalField = newOriginalField;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__ORIGINAL_FIELD, oldOriginalField, originalField));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPattern(String newPattern) {
        String oldPattern = pattern;
        pattern = newPattern;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.METADATA_COLUMN__PATTERN, oldPattern, pattern));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetTable((MetadataTable)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                return basicSetTable(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                return eInternalContainer().eInverseRemove(this, ConnectionPackage.METADATA_TABLE__COLUMNS, MetadataTable.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__SOURCE_TYPE:
                return getSourceType();
            case ConnectionPackage.METADATA_COLUMN__DEFAULT_VALUE:
                return getDefaultValue();
            case ConnectionPackage.METADATA_COLUMN__TALEND_TYPE:
                return getTalendType();
            case ConnectionPackage.METADATA_COLUMN__KEY:
                return isKey() ? Boolean.TRUE : Boolean.FALSE;
            case ConnectionPackage.METADATA_COLUMN__NULLABLE:
                return isNullable() ? Boolean.TRUE : Boolean.FALSE;
            case ConnectionPackage.METADATA_COLUMN__LENGTH:
                return new Integer(getLength());
            case ConnectionPackage.METADATA_COLUMN__PRECISION:
                return new Integer(getPrecision());
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                return getTable();
            case ConnectionPackage.METADATA_COLUMN__ORIGINAL_FIELD:
                return getOriginalField();
            case ConnectionPackage.METADATA_COLUMN__PATTERN:
                return getPattern();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__SOURCE_TYPE:
                setSourceType((String)newValue);
                return;
            case ConnectionPackage.METADATA_COLUMN__DEFAULT_VALUE:
                setDefaultValue((String)newValue);
                return;
            case ConnectionPackage.METADATA_COLUMN__TALEND_TYPE:
                setTalendType((String)newValue);
                return;
            case ConnectionPackage.METADATA_COLUMN__KEY:
                setKey(((Boolean)newValue).booleanValue());
                return;
            case ConnectionPackage.METADATA_COLUMN__NULLABLE:
                setNullable(((Boolean)newValue).booleanValue());
                return;
            case ConnectionPackage.METADATA_COLUMN__LENGTH:
                setLength(((Integer)newValue).intValue());
                return;
            case ConnectionPackage.METADATA_COLUMN__PRECISION:
                setPrecision(((Integer)newValue).intValue());
                return;
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                setTable((MetadataTable)newValue);
                return;
            case ConnectionPackage.METADATA_COLUMN__ORIGINAL_FIELD:
                setOriginalField((String)newValue);
                return;
            case ConnectionPackage.METADATA_COLUMN__PATTERN:
                setPattern((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__SOURCE_TYPE:
                setSourceType(SOURCE_TYPE_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__DEFAULT_VALUE:
                setDefaultValue(DEFAULT_VALUE_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__TALEND_TYPE:
                setTalendType(TALEND_TYPE_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__KEY:
                setKey(KEY_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__NULLABLE:
                setNullable(NULLABLE_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__LENGTH:
                setLength(LENGTH_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__PRECISION:
                setPrecision(PRECISION_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                setTable((MetadataTable)null);
                return;
            case ConnectionPackage.METADATA_COLUMN__ORIGINAL_FIELD:
                setOriginalField(ORIGINAL_FIELD_EDEFAULT);
                return;
            case ConnectionPackage.METADATA_COLUMN__PATTERN:
                setPattern(PATTERN_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ConnectionPackage.METADATA_COLUMN__SOURCE_TYPE:
                return SOURCE_TYPE_EDEFAULT == null ? sourceType != null : !SOURCE_TYPE_EDEFAULT.equals(sourceType);
            case ConnectionPackage.METADATA_COLUMN__DEFAULT_VALUE:
                return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
            case ConnectionPackage.METADATA_COLUMN__TALEND_TYPE:
                return TALEND_TYPE_EDEFAULT == null ? talendType != null : !TALEND_TYPE_EDEFAULT.equals(talendType);
            case ConnectionPackage.METADATA_COLUMN__KEY:
                return key != KEY_EDEFAULT;
            case ConnectionPackage.METADATA_COLUMN__NULLABLE:
                return nullable != NULLABLE_EDEFAULT;
            case ConnectionPackage.METADATA_COLUMN__LENGTH:
                return length != LENGTH_EDEFAULT;
            case ConnectionPackage.METADATA_COLUMN__PRECISION:
                return precision != PRECISION_EDEFAULT;
            case ConnectionPackage.METADATA_COLUMN__TABLE:
                return getTable() != null;
            case ConnectionPackage.METADATA_COLUMN__ORIGINAL_FIELD:
                return ORIGINAL_FIELD_EDEFAULT == null ? originalField != null : !ORIGINAL_FIELD_EDEFAULT.equals(originalField);
            case ConnectionPackage.METADATA_COLUMN__PATTERN:
                return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sourceType: ");
        result.append(sourceType);
        result.append(", defaultValue: ");
        result.append(defaultValue);
        result.append(", talendType: ");
        result.append(talendType);
        result.append(", key: ");
        result.append(key);
        result.append(", nullable: ");
        result.append(nullable);
        result.append(", length: ");
        result.append(length);
        result.append(", precision: ");
        result.append(precision);
        result.append(", originalField: ");
        result.append(originalField);
        result.append(", pattern: ");
        result.append(pattern);
        result.append(')');
        return result.toString();
    }

} //MetadataColumnImpl