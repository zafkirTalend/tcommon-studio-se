/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.talend.core.model.properties.BusinessProcessItem;
import org.talend.core.model.properties.NotationHolder;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.designer.business.model.business.BusinessProcess;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Business Process Item</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl#getNotation <em>Notation</em>}</li>
 * <li>{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl#getSemantic <em>Semantic</em>}</li>
 * <li>{@link org.talend.core.model.properties.impl.BusinessProcessItemImpl#getNotationHolder <em>Notation Holder</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BusinessProcessItemImpl extends ItemImpl implements BusinessProcessItem {

    /**
     * The cached value of the '{@link #getNotation() <em>Notation</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getNotation()
     * @generated
     * @ordered
     */
    protected Diagram notation = null;

    /**
     * The cached value of the '{@link #getSemantic() <em>Semantic</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSemantic()
     * @generated
     * @ordered
     */
    protected BusinessProcess semantic = null;

    /**
     * The cached value of the '{@link #getNotationHolder() <em>Notation Holder</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getNotationHolder()
     * @generated
     * @ordered
     */
    protected NotationHolder notationHolder = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BusinessProcessItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.BUSINESS_PROCESS_ITEM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public Diagram getNotation() {
        if (notation != null && notation.eIsProxy()) {
            // migration from notation to notationHolder

            InternalEObject oldNotation = (InternalEObject) notation;
            notation = (Diagram) eResolveProxy(oldNotation);

            BusinessProcess semantic = getSemantic();

            Resource resource = semantic.eResource();
            resource.getContents().add(notationHolder);
            computeNotationHolder();
            resource.getContents().remove(notation);
        }
        if (notation == null) {
            ByteArrayInputStream in = new ByteArrayInputStream(getNotationHolder().getNotationString().getBytes());
            Resource resource = new XMIResourceImpl();
            try {
                resource.load(in, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            notation = (Diagram) resource.getContents().get(0);
            EcoreUtil.resolveAll(notationHolder.eResource());
        }
        return notation;
    }

    public void computeNotationHolder() {
        Diagram notation = getNotation();

        Resource resource = new ResourceSetImpl().createResource(semantic.eResource().getURI());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resource.getContents().add(notation);
        try {
            resource.save(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        notationHolder.setNotationString(out.toString());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Diagram basicGetNotation() {
        return notation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setNotation(Diagram newNotation) {
        Diagram oldNotation = notation;
        notation = newNotation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION, oldNotation,
                    notation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BusinessProcess getSemantic() {
        if (semantic != null && semantic.eIsProxy()) {
            InternalEObject oldSemantic = (InternalEObject) semantic;
            semantic = (BusinessProcess) eResolveProxy(oldSemantic);
            if (semantic != oldSemantic) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC,
                            oldSemantic, semantic));
            }
        }
        return semantic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BusinessProcess basicGetSemantic() {
        return semantic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSemantic(BusinessProcess newSemantic) {
        BusinessProcess oldSemantic = semantic;
        semantic = newSemantic;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC, oldSemantic,
                    semantic));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotationHolder getNotationHolder() {
        if (notationHolder != null && notationHolder.eIsProxy()) {
            InternalEObject oldNotationHolder = (InternalEObject) notationHolder;
            notationHolder = (NotationHolder) eResolveProxy(oldNotationHolder);
            if (notationHolder != oldNotationHolder) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER, oldNotationHolder, notationHolder));
            }
        }
        return notationHolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotationHolder basicGetNotationHolder() {
        return notationHolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setNotationHolder(NotationHolder newNotationHolder) {
        NotationHolder oldNotationHolder = notationHolder;
        notationHolder = newNotationHolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER,
                    oldNotationHolder, notationHolder));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
            if (resolve)
                return getNotation();
            return basicGetNotation();
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
            if (resolve)
                return getSemantic();
            return basicGetSemantic();
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER:
            if (resolve)
                return getNotationHolder();
            return basicGetNotationHolder();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
            setNotation((Diagram) newValue);
            return;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
            setSemantic((BusinessProcess) newValue);
            return;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER:
            setNotationHolder((NotationHolder) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
            setNotation((Diagram) null);
            return;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
            setSemantic((BusinessProcess) null);
            return;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER:
            setNotationHolder((NotationHolder) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION:
            return notation != null;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__SEMANTIC:
            return semantic != null;
        case PropertiesPackage.BUSINESS_PROCESS_ITEM__NOTATION_HOLDER:
            return notationHolder != null;
        }
        return super.eIsSet(featureID);
    }

} // BusinessProcessItemImpl
