/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.component_cache.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.talend.core.model.component_cache.ComponentCachePackage;
import org.talend.core.model.component_cache.ComponentInfo;

import org.talend.designer.core.model.utils.emf.component.IMPORTType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getOriginalFamilyName <em>Original Family Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getPluginFullName <em>Plugin Full Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getTranslatedFamilyName <em>Translated Family Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#isIsTechnical <em>Is Technical</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getPluginDependencies <em>Plugin Dependencies</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getComponentNames <em>Component Names</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getImportType <em>Import Type</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#isIsVisibleInComponentDefinition <em>Is Visible In Component Definition</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getUriString <em>Uri String</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.impl.ComponentInfoImpl#getPathSource <em>Path Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentInfoImpl extends EObjectImpl implements ComponentInfo {
    /**
     * The default value of the '{@link #getOriginalFamilyName() <em>Original Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalFamilyName()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_FAMILY_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getOriginalFamilyName() <em>Original Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOriginalFamilyName()
     * @generated
     * @ordered
     */
    protected String originalFamilyName = ORIGINAL_FAMILY_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPluginFullName() <em>Plugin Full Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPluginFullName()
     * @generated
     * @ordered
     */
    protected static final String PLUGIN_FULL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPluginFullName() <em>Plugin Full Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPluginFullName()
     * @generated
     * @ordered
     */
    protected String pluginFullName = PLUGIN_FULL_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTranslatedFamilyName() <em>Translated Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTranslatedFamilyName()
     * @generated
     * @ordered
     */
    protected static final String TRANSLATED_FAMILY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTranslatedFamilyName() <em>Translated Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTranslatedFamilyName()
     * @generated
     * @ordered
     */
    protected String translatedFamilyName = TRANSLATED_FAMILY_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isIsTechnical() <em>Is Technical</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsTechnical()
     * @generated
     * @ordered
     */
    protected static final boolean IS_TECHNICAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsTechnical() <em>Is Technical</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsTechnical()
     * @generated
     * @ordered
     */
    protected boolean isTechnical = IS_TECHNICAL_EDEFAULT;

    /**
     * The cached value of the '{@link #getPluginDependencies() <em>Plugin Dependencies</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPluginDependencies()
     * @generated
     * @ordered
     */
    protected EList<String> pluginDependencies;

    /**
     * The cached value of the '{@link #getComponentNames() <em>Component Names</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentNames()
     * @generated
     * @ordered
     */
    protected EList<String> componentNames;

    /**
     * The cached value of the '{@link #getImportType() <em>Import Type</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImportType()
     * @generated
     * @ordered
     */
    protected EList<IMPORTType> importType;

    /**
     * The default value of the '{@link #isIsVisibleInComponentDefinition() <em>Is Visible In Component Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVisibleInComponentDefinition()
     * @generated
     * @ordered
     */
    protected static final boolean IS_VISIBLE_IN_COMPONENT_DEFINITION_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isIsVisibleInComponentDefinition() <em>Is Visible In Component Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsVisibleInComponentDefinition()
     * @generated
     * @ordered
     */
    protected boolean isVisibleInComponentDefinition = IS_VISIBLE_IN_COMPONENT_DEFINITION_EDEFAULT;

    /**
     * The default value of the '{@link #getUriString() <em>Uri String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUriString()
     * @generated
     * @ordered
     */
    protected static final String URI_STRING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUriString() <em>Uri String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUriString()
     * @generated
     * @ordered
     */
    protected String uriString = URI_STRING_EDEFAULT;

    /**
     * The default value of the '{@link #getPathSource() <em>Path Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPathSource()
     * @generated
     * @ordered
     */
    protected static final String PATH_SOURCE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPathSource() <em>Path Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPathSource()
     * @generated
     * @ordered
     */
    protected String pathSource = PATH_SOURCE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentInfoImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ComponentCachePackage.Literals.COMPONENT_INFO;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOriginalFamilyName() {
        return originalFamilyName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOriginalFamilyName(String newOriginalFamilyName) {
        String oldOriginalFamilyName = originalFamilyName;
        originalFamilyName = newOriginalFamilyName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__ORIGINAL_FAMILY_NAME, oldOriginalFamilyName, originalFamilyName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPluginFullName() {
        return pluginFullName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPluginFullName(String newPluginFullName) {
        String oldPluginFullName = pluginFullName;
        pluginFullName = newPluginFullName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__PLUGIN_FULL_NAME, oldPluginFullName, pluginFullName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__VERSION, oldVersion, version));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTranslatedFamilyName() {
        return translatedFamilyName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTranslatedFamilyName(String newTranslatedFamilyName) {
        String oldTranslatedFamilyName = translatedFamilyName;
        translatedFamilyName = newTranslatedFamilyName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__TRANSLATED_FAMILY_NAME, oldTranslatedFamilyName, translatedFamilyName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsTechnical() {
        return isTechnical;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsTechnical(boolean newIsTechnical) {
        boolean oldIsTechnical = isTechnical;
        isTechnical = newIsTechnical;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__IS_TECHNICAL, oldIsTechnical, isTechnical));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getPluginDependencies() {
        if (pluginDependencies == null) {
            pluginDependencies = new EDataTypeUniqueEList<String>(String.class, this, ComponentCachePackage.COMPONENT_INFO__PLUGIN_DEPENDENCIES);
        }
        return pluginDependencies;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getComponentNames() {
        if (componentNames == null) {
            componentNames = new EDataTypeUniqueEList<String>(String.class, this, ComponentCachePackage.COMPONENT_INFO__COMPONENT_NAMES);
        }
        return componentNames;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<IMPORTType> getImportType() {
        if (importType == null) {
            importType = new EObjectContainmentEList<IMPORTType>(IMPORTType.class, this, ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE);
        }
        return importType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsVisibleInComponentDefinition() {
        return isVisibleInComponentDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIsVisibleInComponentDefinition(boolean newIsVisibleInComponentDefinition) {
        boolean oldIsVisibleInComponentDefinition = isVisibleInComponentDefinition;
        isVisibleInComponentDefinition = newIsVisibleInComponentDefinition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__IS_VISIBLE_IN_COMPONENT_DEFINITION, oldIsVisibleInComponentDefinition, isVisibleInComponentDefinition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUriString() {
        return uriString;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUriString(String newUriString) {
        String oldUriString = uriString;
        uriString = newUriString;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__URI_STRING, oldUriString, uriString));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPathSource() {
        return pathSource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPathSource(String newPathSource) {
        String oldPathSource = pathSource;
        pathSource = newPathSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentCachePackage.COMPONENT_INFO__PATH_SOURCE, oldPathSource, pathSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE:
                return ((InternalEList<?>)getImportType()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentCachePackage.COMPONENT_INFO__ORIGINAL_FAMILY_NAME:
                return getOriginalFamilyName();
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_FULL_NAME:
                return getPluginFullName();
            case ComponentCachePackage.COMPONENT_INFO__VERSION:
                return getVersion();
            case ComponentCachePackage.COMPONENT_INFO__TRANSLATED_FAMILY_NAME:
                return getTranslatedFamilyName();
            case ComponentCachePackage.COMPONENT_INFO__IS_TECHNICAL:
                return isIsTechnical();
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_DEPENDENCIES:
                return getPluginDependencies();
            case ComponentCachePackage.COMPONENT_INFO__COMPONENT_NAMES:
                return getComponentNames();
            case ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE:
                return getImportType();
            case ComponentCachePackage.COMPONENT_INFO__IS_VISIBLE_IN_COMPONENT_DEFINITION:
                return isIsVisibleInComponentDefinition();
            case ComponentCachePackage.COMPONENT_INFO__URI_STRING:
                return getUriString();
            case ComponentCachePackage.COMPONENT_INFO__PATH_SOURCE:
                return getPathSource();
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
            case ComponentCachePackage.COMPONENT_INFO__ORIGINAL_FAMILY_NAME:
                setOriginalFamilyName((String)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_FULL_NAME:
                setPluginFullName((String)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__VERSION:
                setVersion((String)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__TRANSLATED_FAMILY_NAME:
                setTranslatedFamilyName((String)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__IS_TECHNICAL:
                setIsTechnical((Boolean)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_DEPENDENCIES:
                getPluginDependencies().clear();
                getPluginDependencies().addAll((Collection<? extends String>)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__COMPONENT_NAMES:
                getComponentNames().clear();
                getComponentNames().addAll((Collection<? extends String>)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE:
                getImportType().clear();
                getImportType().addAll((Collection<? extends IMPORTType>)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__IS_VISIBLE_IN_COMPONENT_DEFINITION:
                setIsVisibleInComponentDefinition((Boolean)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__URI_STRING:
                setUriString((String)newValue);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PATH_SOURCE:
                setPathSource((String)newValue);
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
            case ComponentCachePackage.COMPONENT_INFO__ORIGINAL_FAMILY_NAME:
                setOriginalFamilyName(ORIGINAL_FAMILY_NAME_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_FULL_NAME:
                setPluginFullName(PLUGIN_FULL_NAME_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__TRANSLATED_FAMILY_NAME:
                setTranslatedFamilyName(TRANSLATED_FAMILY_NAME_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__IS_TECHNICAL:
                setIsTechnical(IS_TECHNICAL_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_DEPENDENCIES:
                getPluginDependencies().clear();
                return;
            case ComponentCachePackage.COMPONENT_INFO__COMPONENT_NAMES:
                getComponentNames().clear();
                return;
            case ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE:
                getImportType().clear();
                return;
            case ComponentCachePackage.COMPONENT_INFO__IS_VISIBLE_IN_COMPONENT_DEFINITION:
                setIsVisibleInComponentDefinition(IS_VISIBLE_IN_COMPONENT_DEFINITION_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__URI_STRING:
                setUriString(URI_STRING_EDEFAULT);
                return;
            case ComponentCachePackage.COMPONENT_INFO__PATH_SOURCE:
                setPathSource(PATH_SOURCE_EDEFAULT);
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
            case ComponentCachePackage.COMPONENT_INFO__ORIGINAL_FAMILY_NAME:
                return ORIGINAL_FAMILY_NAME_EDEFAULT == null ? originalFamilyName != null : !ORIGINAL_FAMILY_NAME_EDEFAULT.equals(originalFamilyName);
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_FULL_NAME:
                return PLUGIN_FULL_NAME_EDEFAULT == null ? pluginFullName != null : !PLUGIN_FULL_NAME_EDEFAULT.equals(pluginFullName);
            case ComponentCachePackage.COMPONENT_INFO__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ComponentCachePackage.COMPONENT_INFO__TRANSLATED_FAMILY_NAME:
                return TRANSLATED_FAMILY_NAME_EDEFAULT == null ? translatedFamilyName != null : !TRANSLATED_FAMILY_NAME_EDEFAULT.equals(translatedFamilyName);
            case ComponentCachePackage.COMPONENT_INFO__IS_TECHNICAL:
                return isTechnical != IS_TECHNICAL_EDEFAULT;
            case ComponentCachePackage.COMPONENT_INFO__PLUGIN_DEPENDENCIES:
                return pluginDependencies != null && !pluginDependencies.isEmpty();
            case ComponentCachePackage.COMPONENT_INFO__COMPONENT_NAMES:
                return componentNames != null && !componentNames.isEmpty();
            case ComponentCachePackage.COMPONENT_INFO__IMPORT_TYPE:
                return importType != null && !importType.isEmpty();
            case ComponentCachePackage.COMPONENT_INFO__IS_VISIBLE_IN_COMPONENT_DEFINITION:
                return isVisibleInComponentDefinition != IS_VISIBLE_IN_COMPONENT_DEFINITION_EDEFAULT;
            case ComponentCachePackage.COMPONENT_INFO__URI_STRING:
                return URI_STRING_EDEFAULT == null ? uriString != null : !URI_STRING_EDEFAULT.equals(uriString);
            case ComponentCachePackage.COMPONENT_INFO__PATH_SOURCE:
                return PATH_SOURCE_EDEFAULT == null ? pathSource != null : !PATH_SOURCE_EDEFAULT.equals(pathSource);
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
        result.append(" (originalFamilyName: ");
        result.append(originalFamilyName);
        result.append(", pluginFullName: ");
        result.append(pluginFullName);
        result.append(", version: ");
        result.append(version);
        result.append(", translatedFamilyName: ");
        result.append(translatedFamilyName);
        result.append(", isTechnical: ");
        result.append(isTechnical);
        result.append(", PluginDependencies: ");
        result.append(pluginDependencies);
        result.append(", componentNames: ");
        result.append(componentNames);
        result.append(", isVisibleInComponentDefinition: ");
        result.append(isVisibleInComponentDefinition);
        result.append(", uriString: ");
        result.append(uriString);
        result.append(", pathSource: ");
        result.append(pathSource);
        result.append(')');
        return result.toString();
    }

} //ComponentInfoImpl
