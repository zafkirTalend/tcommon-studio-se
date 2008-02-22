/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.domain.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.talend.dataquality.domain.Domain;
import org.talend.dataquality.domain.DomainPackage;
import org.talend.dataquality.domain.LengthRestriction;
import org.talend.dataquality.domain.RangeRestriction;

import org.talend.dataquality.domain.pattern.Pattern;

import orgomg.cwm.objectmodel.core.DataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getLengthRestriction <em>Length Restriction</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getRanges <em>Ranges</em>}</li>
 *   <li>{@link org.talend.dataquality.domain.impl.DomainImpl#getPatterns <em>Patterns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainImpl extends EObjectImpl implements Domain {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getDataType() <em>Data Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataType()
     * @generated
     * @ordered
     */
    protected DataType dataType;

    /**
     * The cached value of the '{@link #getLengthRestriction() <em>Length Restriction</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLengthRestriction()
     * @generated
     * @ordered
     */
    protected EList<LengthRestriction> lengthRestriction;

    /**
     * The cached value of the '{@link #getRanges() <em>Ranges</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRanges()
     * @generated
     * @ordered
     */
    protected EList<RangeRestriction> ranges;

    /**
     * The cached value of the '{@link #getPatterns() <em>Patterns</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatterns()
     * @generated
     * @ordered
     */
    protected EList<Pattern> patterns;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DomainImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DomainPackage.Literals.DOMAIN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.DOMAIN__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.DOMAIN__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType getDataType() {
        if (dataType != null && dataType.eIsProxy()) {
            InternalEObject oldDataType = (InternalEObject)dataType;
            dataType = (DataType)eResolveProxy(oldDataType);
            if (dataType != oldDataType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.DOMAIN__DATA_TYPE, oldDataType, dataType));
            }
        }
        return dataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType basicGetDataType() {
        return dataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDataType(DataType newDataType) {
        DataType oldDataType = dataType;
        dataType = newDataType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.DOMAIN__DATA_TYPE, oldDataType, dataType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<LengthRestriction> getLengthRestriction() {
        if (lengthRestriction == null) {
            lengthRestriction = new EObjectResolvingEList<LengthRestriction>(LengthRestriction.class, this, DomainPackage.DOMAIN__LENGTH_RESTRICTION);
        }
        return lengthRestriction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<RangeRestriction> getRanges() {
        if (ranges == null) {
            ranges = new EObjectResolvingEList<RangeRestriction>(RangeRestriction.class, this, DomainPackage.DOMAIN__RANGES);
        }
        return ranges;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Pattern> getPatterns() {
        if (patterns == null) {
            patterns = new EObjectResolvingEList<Pattern>(Pattern.class, this, DomainPackage.DOMAIN__PATTERNS);
        }
        return patterns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DomainPackage.DOMAIN__NAME:
                return getName();
            case DomainPackage.DOMAIN__DESCRIPTION:
                return getDescription();
            case DomainPackage.DOMAIN__DATA_TYPE:
                if (resolve) return getDataType();
                return basicGetDataType();
            case DomainPackage.DOMAIN__LENGTH_RESTRICTION:
                return getLengthRestriction();
            case DomainPackage.DOMAIN__RANGES:
                return getRanges();
            case DomainPackage.DOMAIN__PATTERNS:
                return getPatterns();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case DomainPackage.DOMAIN__NAME:
                setName((String)newValue);
                return;
            case DomainPackage.DOMAIN__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case DomainPackage.DOMAIN__DATA_TYPE:
                setDataType((DataType)newValue);
                return;
            case DomainPackage.DOMAIN__LENGTH_RESTRICTION:
                getLengthRestriction().clear();
                getLengthRestriction().addAll((Collection<? extends LengthRestriction>)newValue);
                return;
            case DomainPackage.DOMAIN__RANGES:
                getRanges().clear();
                getRanges().addAll((Collection<? extends RangeRestriction>)newValue);
                return;
            case DomainPackage.DOMAIN__PATTERNS:
                getPatterns().clear();
                getPatterns().addAll((Collection<? extends Pattern>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case DomainPackage.DOMAIN__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DomainPackage.DOMAIN__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case DomainPackage.DOMAIN__DATA_TYPE:
                setDataType((DataType)null);
                return;
            case DomainPackage.DOMAIN__LENGTH_RESTRICTION:
                getLengthRestriction().clear();
                return;
            case DomainPackage.DOMAIN__RANGES:
                getRanges().clear();
                return;
            case DomainPackage.DOMAIN__PATTERNS:
                getPatterns().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case DomainPackage.DOMAIN__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DomainPackage.DOMAIN__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case DomainPackage.DOMAIN__DATA_TYPE:
                return dataType != null;
            case DomainPackage.DOMAIN__LENGTH_RESTRICTION:
                return lengthRestriction != null && !lengthRestriction.isEmpty();
            case DomainPackage.DOMAIN__RANGES:
                return ranges != null && !ranges.isEmpty();
            case DomainPackage.DOMAIN__PATTERNS:
                return patterns != null && !patterns.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //DomainImpl
