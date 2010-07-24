/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.metadata.builder.connection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.talend.core.model.metadata.builder.connection.ConnectionPackage;
import org.talend.core.model.metadata.builder.connection.WSDLParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>WSDL Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.WSDLParameterImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.WSDLParameterImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.WSDLParameterImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.talend.core.model.metadata.builder.connection.impl.WSDLParameterImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WSDLParameterImpl extends EObjectImpl implements WSDLParameter {

    /**
     * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected static final String ELEMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getElement()
     * @generated
     * @ordered
     */
    protected String element = ELEMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected String source = SOURCE_EDEFAULT;

    /**
     * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getColumn()
     * @generated
     * @ordered
     */
    protected static final String COLUMN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getColumn() <em>Column</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getColumn()
     * @generated
     * @ordered
     */
    protected String column = COLUMN_EDEFAULT;

    /**
     * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected String expression = EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected WSDLParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ConnectionPackage.Literals.WSDL_PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getElement() {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setElement(String newElement) {
        String oldElement = element;
        element = newElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.WSDL_PARAMETER__ELEMENT, oldElement, element));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSource(String newSource) {
        String oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.WSDL_PARAMETER__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getColumn() {
        return column;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setColumn(String newColumn) {
        String oldColumn = column;
        column = newColumn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.WSDL_PARAMETER__COLUMN, oldColumn, column));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectionPackage.WSDL_PARAMETER__EXPRESSION, oldExpression,
                    expression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ConnectionPackage.WSDL_PARAMETER__ELEMENT:
            return getElement();
        case ConnectionPackage.WSDL_PARAMETER__SOURCE:
            return getSource();
        case ConnectionPackage.WSDL_PARAMETER__COLUMN:
            return getColumn();
        case ConnectionPackage.WSDL_PARAMETER__EXPRESSION:
            return getExpression();
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
        case ConnectionPackage.WSDL_PARAMETER__ELEMENT:
            setElement((String) newValue);
            return;
        case ConnectionPackage.WSDL_PARAMETER__SOURCE:
            setSource((String) newValue);
            return;
        case ConnectionPackage.WSDL_PARAMETER__COLUMN:
            setColumn((String) newValue);
            return;
        case ConnectionPackage.WSDL_PARAMETER__EXPRESSION:
            setExpression((String) newValue);
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
        case ConnectionPackage.WSDL_PARAMETER__ELEMENT:
            setElement(ELEMENT_EDEFAULT);
            return;
        case ConnectionPackage.WSDL_PARAMETER__SOURCE:
            setSource(SOURCE_EDEFAULT);
            return;
        case ConnectionPackage.WSDL_PARAMETER__COLUMN:
            setColumn(COLUMN_EDEFAULT);
            return;
        case ConnectionPackage.WSDL_PARAMETER__EXPRESSION:
            setExpression(EXPRESSION_EDEFAULT);
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
        case ConnectionPackage.WSDL_PARAMETER__ELEMENT:
            return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
        case ConnectionPackage.WSDL_PARAMETER__SOURCE:
            return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
        case ConnectionPackage.WSDL_PARAMETER__COLUMN:
            return COLUMN_EDEFAULT == null ? column != null : !COLUMN_EDEFAULT.equals(column);
        case ConnectionPackage.WSDL_PARAMETER__EXPRESSION:
            return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (Element: ");
        result.append(element);
        result.append(", source: ");
        result.append(source);
        result.append(", Column: ");
        result.append(column);
        result.append(", Expression: ");
        result.append(expression);
        result.append(')');
        return result.toString();
    }

} // WSDLParameterImpl
