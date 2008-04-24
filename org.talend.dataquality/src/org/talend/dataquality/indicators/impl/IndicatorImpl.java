/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.dataquality.indicators.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.talend.cwm.helper.SwitchHelpers;
import org.talend.cwm.relational.TdColumn;
import org.talend.dataquality.helpers.IndicatorDocumentationHandler;
import org.talend.dataquality.helpers.MetadataHelper;
import org.talend.dataquality.indicators.DataminingType;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorParameters;
import org.talend.dataquality.indicators.IndicatorType;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.definition.IndicatorDefinition;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Indicator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getIndicatorType <em>Indicator Type</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getCount <em>Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getNullCount <em>Null Count</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getAnalyzedElement <em>Analyzed Element</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getDataminingType <em>Datamining Type</em>}</li>
 *   <li>{@link org.talend.dataquality.indicators.impl.IndicatorImpl#getIndicatorDefinition <em>Indicator Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndicatorImpl extends ModelElementImpl implements Indicator {

    /**
     * The cached value of the '{@link #getIndicatorType() <em>Indicator Type</em>}' reference.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getIndicatorType()
     * @generated
     * @ordered
     */
    protected IndicatorType indicatorType;

    /**
     * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    protected static final long COUNT_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    protected long count = COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getNullCount() <em>Null Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNullCount()
     * @generated
     * @ordered
     */
    protected static final long NULL_COUNT_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getNullCount() <em>Null Count</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNullCount()
     * @generated
     * @ordered
     */
    protected long nullCount = NULL_COUNT_EDEFAULT;

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected IndicatorParameters parameters;

    /**
     * The cached value of the '{@link #getAnalyzedElement() <em>Analyzed Element</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAnalyzedElement()
     * @generated
     * @ordered
     */
    protected ModelElement analyzedElement;

    /**
     * The default value of the '{@link #getDataminingType() <em>Datamining Type</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getDataminingType()
     * @generated
     * @ordered
     */
    protected static final DataminingType DATAMINING_TYPE_EDEFAULT = DataminingType.NOMINAL;

    /**
     * The cached value of the '{@link #getDataminingType() <em>Datamining Type</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getDataminingType()
     * @generated
     * @ordered
     */
    protected DataminingType dataminingType = DATAMINING_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIndicatorDefinition() <em>Indicator Definition</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndicatorDefinition()
     * @generated
     * @ordered
     */
    protected IndicatorDefinition indicatorDefinition;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected IndicatorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IndicatorsPackage.Literals.INDICATOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorType getIndicatorType() {
        if (indicatorType != null && indicatorType.eIsProxy()) {
            InternalEObject oldIndicatorType = (InternalEObject)indicatorType;
            indicatorType = (IndicatorType)eResolveProxy(oldIndicatorType);
            if (indicatorType != oldIndicatorType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__INDICATOR_TYPE, oldIndicatorType, indicatorType));
            }
        }
        return indicatorType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorType basicGetIndicatorType() {
        return indicatorType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIndicatorType(IndicatorType newIndicatorType) {
        IndicatorType oldIndicatorType = indicatorType;
        indicatorType = newIndicatorType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__INDICATOR_TYPE, oldIndicatorType, indicatorType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public long getCount() {
        return count;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setCount(long newCount) {
        long oldCount = count;
        count = newCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__COUNT, oldCount, count));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public long getNullCount() {
        return nullCount;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setNullCount(long newNullCount) {
        long oldNullCount = nullCount;
        nullCount = newNullCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__NULL_COUNT, oldNullCount, nullCount));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IndicatorParameters getParameters() {
        return parameters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParameters(IndicatorParameters newParameters, NotificationChain msgs) {
        IndicatorParameters oldParameters = parameters;
        parameters = newParameters;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__PARAMETERS, oldParameters, newParameters);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setParameters(IndicatorParameters newParameters) {
        if (newParameters != parameters) {
            NotificationChain msgs = null;
            if (parameters != null)
                msgs = ((InternalEObject)parameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR__PARAMETERS, null, msgs);
            if (newParameters != null)
                msgs = ((InternalEObject)newParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IndicatorsPackage.INDICATOR__PARAMETERS, null, msgs);
            msgs = basicSetParameters(newParameters, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__PARAMETERS, newParameters, newParameters));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ModelElement getAnalyzedElement() {
        if (analyzedElement != null && analyzedElement.eIsProxy()) {
            InternalEObject oldAnalyzedElement = (InternalEObject)analyzedElement;
            analyzedElement = (ModelElement)eResolveProxy(oldAnalyzedElement);
            if (analyzedElement != oldAnalyzedElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT, oldAnalyzedElement, analyzedElement));
            }
        }
        return analyzedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ModelElement basicGetAnalyzedElement() {
        return analyzedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setAnalyzedElement(ModelElement newAnalyzedElement) {
        ModelElement oldAnalyzedElement = analyzedElement;
        analyzedElement = newAnalyzedElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT, oldAnalyzedElement, analyzedElement));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public DataminingType getDataminingType() {
        ModelElement elt = getAnalyzedElement();
        if (elt == null) {
            return getDataminingTypeGen();
        }
        TdColumn col = SwitchHelpers.COLUMN_SWITCH.doSwitch(elt);
        if (col == null) {
            return getDataminingTypeGen();
        }
        DataminingType type = MetadataHelper.getDataminingType(col);
        if (type == null) {
            // try the default code
            return getDataminingTypeGen();
        }
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DataminingType getDataminingTypeGen() {
        return dataminingType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDataminingType(DataminingType newDataminingType) {
        DataminingType oldDataminingType = dataminingType;
        dataminingType = newDataminingType == null ? DATAMINING_TYPE_EDEFAULT : newDataminingType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__DATAMINING_TYPE, oldDataminingType, dataminingType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorDefinition getIndicatorDefinition() {
        if (indicatorDefinition != null && indicatorDefinition.eIsProxy()) {
            InternalEObject oldIndicatorDefinition = (InternalEObject)indicatorDefinition;
            indicatorDefinition = (IndicatorDefinition)eResolveProxy(oldIndicatorDefinition);
            if (indicatorDefinition != oldIndicatorDefinition) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION, oldIndicatorDefinition, indicatorDefinition));
            }
        }
        return indicatorDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IndicatorDefinition basicGetIndicatorDefinition() {
        return indicatorDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIndicatorDefinition(IndicatorDefinition newIndicatorDefinition) {
        IndicatorDefinition oldIndicatorDefinition = indicatorDefinition;
        indicatorDefinition = newIndicatorDefinition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION, oldIndicatorDefinition, indicatorDefinition));
    }

    /**
     * <!-- begin-user-doc --> Increments counts for each given data. <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean handle(Object data) {
        if (data == null) {
            nullCount++;
        }
        count++;
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean reset() {
        count = COUNT_EDEFAULT;
        nullCount = NULL_COUNT_EDEFAULT;
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getPurpose() {
        return IndicatorDocumentationHandler.getPurpose(this.eClass().getClassifierID());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getLongDescription() {
        return IndicatorDocumentationHandler.getLongDescription(this.eClass().getClassifierID());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean prepare() {
        return this.reset();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public boolean finalizeComputation() {
        return true;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return basicSetParameters(null, msgs);
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
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                if (resolve) return getIndicatorType();
                return basicGetIndicatorType();
            case IndicatorsPackage.INDICATOR__COUNT:
                return new Long(getCount());
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                return new Long(getNullCount());
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return getParameters();
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                if (resolve) return getAnalyzedElement();
                return basicGetAnalyzedElement();
            case IndicatorsPackage.INDICATOR__DATAMINING_TYPE:
                return getDataminingType();
            case IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION:
                if (resolve) return getIndicatorDefinition();
                return basicGetIndicatorDefinition();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                setIndicatorType((IndicatorType)newValue);
                return;
            case IndicatorsPackage.INDICATOR__COUNT:
                setCount(((Long)newValue).longValue());
                return;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                setNullCount(((Long)newValue).longValue());
                return;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                setParameters((IndicatorParameters)newValue);
                return;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                setAnalyzedElement((ModelElement)newValue);
                return;
            case IndicatorsPackage.INDICATOR__DATAMINING_TYPE:
                setDataminingType((DataminingType)newValue);
                return;
            case IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION:
                setIndicatorDefinition((IndicatorDefinition)newValue);
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
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                setIndicatorType((IndicatorType)null);
                return;
            case IndicatorsPackage.INDICATOR__COUNT:
                setCount(COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                setNullCount(NULL_COUNT_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                setParameters((IndicatorParameters)null);
                return;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                setAnalyzedElement((ModelElement)null);
                return;
            case IndicatorsPackage.INDICATOR__DATAMINING_TYPE:
                setDataminingType(DATAMINING_TYPE_EDEFAULT);
                return;
            case IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION:
                setIndicatorDefinition((IndicatorDefinition)null);
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
            case IndicatorsPackage.INDICATOR__INDICATOR_TYPE:
                return indicatorType != null;
            case IndicatorsPackage.INDICATOR__COUNT:
                return count != COUNT_EDEFAULT;
            case IndicatorsPackage.INDICATOR__NULL_COUNT:
                return nullCount != NULL_COUNT_EDEFAULT;
            case IndicatorsPackage.INDICATOR__PARAMETERS:
                return parameters != null;
            case IndicatorsPackage.INDICATOR__ANALYZED_ELEMENT:
                return analyzedElement != null;
            case IndicatorsPackage.INDICATOR__DATAMINING_TYPE:
                return dataminingType != DATAMINING_TYPE_EDEFAULT;
            case IndicatorsPackage.INDICATOR__INDICATOR_DEFINITION:
                return indicatorDefinition != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (count: ");
        result.append(count);
        result.append(", nullCount: ");
        result.append(nullCount);
        result.append(", dataminingType: ");
        result.append(dataminingType);
        result.append(')');
        return result.toString();
    }

} // IndicatorImpl
