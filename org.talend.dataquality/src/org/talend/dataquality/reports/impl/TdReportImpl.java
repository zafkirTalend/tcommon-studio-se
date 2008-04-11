/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.reports.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.dataquality.analysis.Analysis;
import org.talend.dataquality.reports.PresentationParameter;
import org.talend.dataquality.reports.ReportsPackage;
import org.talend.dataquality.reports.TdReport;
import orgomg.cwmx.analysis.informationreporting.impl.ReportImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Td Report</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.reports.impl.TdReportImpl#getPresentationParams <em>Presentation Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TdReportImpl extends ReportImpl implements TdReport {

    /**
     * The cached value of the '{@link #getPresentationParams() <em>Presentation Params</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getPresentationParams()
     * @generated
     * @ordered
     */
    protected EList<PresentationParameter> presentationParams;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TdReportImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReportsPackage.Literals.TD_REPORT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<PresentationParameter> getPresentationParams() {
        if (presentationParams == null) {
            presentationParams = new EObjectContainmentEList<PresentationParameter>(PresentationParameter.class, this, ReportsPackage.TD_REPORT__PRESENTATION_PARAMS);
        }
        return presentationParams;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean addAnalysis(Analysis analysis) {
        return this.getComponent().add(analysis);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ReportsPackage.TD_REPORT__PRESENTATION_PARAMS:
                return ((InternalEList<?>)getPresentationParams()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ReportsPackage.TD_REPORT__PRESENTATION_PARAMS:
                return getPresentationParams();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ReportsPackage.TD_REPORT__PRESENTATION_PARAMS:
                getPresentationParams().clear();
                getPresentationParams().addAll((Collection<? extends PresentationParameter>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ReportsPackage.TD_REPORT__PRESENTATION_PARAMS:
                getPresentationParams().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ReportsPackage.TD_REPORT__PRESENTATION_PARAMS:
                return presentationParams != null && !presentationParams.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TdReportImpl
