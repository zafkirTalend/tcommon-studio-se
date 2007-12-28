/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.joblet.model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.talend.designer.joblet.model.AbstractJobletObject;
import org.talend.designer.joblet.model.JobletPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Joblet Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl#getPosX <em>Pos X</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl#getPosY <em>Pos Y</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl#isInput <em>Input</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractJobletObjectImpl extends EObjectImpl implements AbstractJobletObject {
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
     * The default value of the '{@link #getPosX() <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosX()
     * @generated
     * @ordered
     */
    protected static final int POS_X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosX() <em>Pos X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosX()
     * @generated
     * @ordered
     */
    protected int posX = POS_X_EDEFAULT;

    /**
     * The default value of the '{@link #getPosY() <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosY()
     * @generated
     * @ordered
     */
    protected static final int POS_Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPosY() <em>Pos Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosY()
     * @generated
     * @ordered
     */
    protected int posY = POS_Y_EDEFAULT;

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
     * The default value of the '{@link #isInput() <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInput()
     * @generated
     * @ordered
     */
    protected static final boolean INPUT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInput() <em>Input</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInput()
     * @generated
     * @ordered
     */
    protected boolean input = INPUT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractJobletObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return JobletPackage.Literals.ABSTRACT_JOBLET_OBJECT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, JobletPackage.ABSTRACT_JOBLET_OBJECT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPosX() {
        return posX;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosX(int newPosX) {
        int oldPosX = posX;
        posX = newPosX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_X, oldPosX, posX));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPosY() {
        return posY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosY(int newPosY) {
        int oldPosY = posY;
        posY = newPosY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_Y, oldPosY, posY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, JobletPackage.ABSTRACT_JOBLET_OBJECT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isInput() {
        return input;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInput(boolean newInput) {
        boolean oldInput = input;
        input = newInput;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, JobletPackage.ABSTRACT_JOBLET_OBJECT__INPUT, oldInput, input));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__NAME:
                return getName();
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_X:
                return new Integer(getPosX());
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_Y:
                return new Integer(getPosY());
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__DESCRIPTION:
                return getDescription();
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__INPUT:
                return isInput() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__NAME:
                setName((String)newValue);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_X:
                setPosX(((Integer)newValue).intValue());
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_Y:
                setPosY(((Integer)newValue).intValue());
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__INPUT:
                setInput(((Boolean)newValue).booleanValue());
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
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_X:
                setPosX(POS_X_EDEFAULT);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_Y:
                setPosY(POS_Y_EDEFAULT);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__INPUT:
                setInput(INPUT_EDEFAULT);
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
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_X:
                return posX != POS_X_EDEFAULT;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__POS_Y:
                return posY != POS_Y_EDEFAULT;
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case JobletPackage.ABSTRACT_JOBLET_OBJECT__INPUT:
                return input != INPUT_EDEFAULT;
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
        result.append(", posX: ");
        result.append(posX);
        result.append(", posY: ");
        result.append(posY);
        result.append(", description: ");
        result.append(description);
        result.append(", input: ");
        result.append(input);
        result.append(')');
        return result.toString();
    }

} //AbstractJobletObjectImpl
