/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.analysis.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.talend.dataquality.analysis.AnalysisContext;
import org.talend.dataquality.analysis.AnalysisPackage;
import org.talend.dataquality.analysis.AnalysisParameters;

import org.talend.dataquality.indicators.Indicator;

import orgomg.cwm.foundation.softwaredeployment.DataManager;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwmx.analysis.informationreporting.impl.ReportGroupImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.analysis.impl.AnalysisContextImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.impl.AnalysisContextImpl#getAnalysedElement <em>Analysed Element</em>}</li>
 *   <li>{@link org.talend.dataquality.analysis.impl.AnalysisContextImpl#getPathToElement <em>Path To Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalysisContextImpl extends ReportGroupImpl implements AnalysisContext {
    /**
     * The cached value of the '{@link #getConnection() <em>Connection</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnection()
     * @generated
     * @ordered
     */
    protected DataManager connection;

    /**
     * The cached value of the '{@link #getAnalysedElement() <em>Analysed Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnalysedElement()
     * @generated
     * @ordered
     */
    protected ModelElement analysedElement;

    /**
     * The default value of the '{@link #getPathToElement() <em>Path To Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPathToElement()
     * @generated
     * @ordered
     */
    protected static final String PATH_TO_ELEMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPathToElement() <em>Path To Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPathToElement()
     * @generated
     * @ordered
     */
    protected String pathToElement = PATH_TO_ELEMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AnalysisContextImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AnalysisPackage.Literals.ANALYSIS_CONTEXT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataManager getConnection() {
        if (connection != null && connection.eIsProxy()) {
            InternalEObject oldConnection = (InternalEObject)connection;
            connection = (DataManager)eResolveProxy(oldConnection);
            if (connection != oldConnection) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION, oldConnection, connection));
            }
        }
        return connection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataManager basicGetConnection() {
        return connection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnection(DataManager newConnection) {
        DataManager oldConnection = connection;
        connection = newConnection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION, oldConnection, connection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelElement getAnalysedElement() {
        if (analysedElement != null && analysedElement.eIsProxy()) {
            InternalEObject oldAnalysedElement = (InternalEObject)analysedElement;
            analysedElement = (ModelElement)eResolveProxy(oldAnalysedElement);
            if (analysedElement != oldAnalysedElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT, oldAnalysedElement, analysedElement));
            }
        }
        return analysedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelElement basicGetAnalysedElement() {
        return analysedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAnalysedElement(ModelElement newAnalysedElement) {
        ModelElement oldAnalysedElement = analysedElement;
        analysedElement = newAnalysedElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT, oldAnalysedElement, analysedElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPathToElement() {
        return pathToElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPathToElement(String newPathToElement) {
        String oldPathToElement = pathToElement;
        pathToElement = newPathToElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, AnalysisPackage.ANALYSIS_CONTEXT__PATH_TO_ELEMENT, oldPathToElement, pathToElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION:
                if (resolve) return getConnection();
                return basicGetConnection();
            case AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT:
                if (resolve) return getAnalysedElement();
                return basicGetAnalysedElement();
            case AnalysisPackage.ANALYSIS_CONTEXT__PATH_TO_ELEMENT:
                return getPathToElement();
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
            case AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION:
                setConnection((DataManager)newValue);
                return;
            case AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT:
                setAnalysedElement((ModelElement)newValue);
                return;
            case AnalysisPackage.ANALYSIS_CONTEXT__PATH_TO_ELEMENT:
                setPathToElement((String)newValue);
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
            case AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION:
                setConnection((DataManager)null);
                return;
            case AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT:
                setAnalysedElement((ModelElement)null);
                return;
            case AnalysisPackage.ANALYSIS_CONTEXT__PATH_TO_ELEMENT:
                setPathToElement(PATH_TO_ELEMENT_EDEFAULT);
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
            case AnalysisPackage.ANALYSIS_CONTEXT__CONNECTION:
                return connection != null;
            case AnalysisPackage.ANALYSIS_CONTEXT__ANALYSED_ELEMENT:
                return analysedElement != null;
            case AnalysisPackage.ANALYSIS_CONTEXT__PATH_TO_ELEMENT:
                return PATH_TO_ELEMENT_EDEFAULT == null ? pathToElement != null : !PATH_TO_ELEMENT_EDEFAULT.equals(pathToElement);
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
        result.append(" (pathToElement: ");
        result.append(pathToElement);
        result.append(')');
        return result.toString();
    }

} //AnalysisContextImpl
