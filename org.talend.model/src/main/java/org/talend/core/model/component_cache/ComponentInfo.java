/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.component_cache;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.talend.designer.core.model.utils.emf.component.IMPORTType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getOriginalFamilyName <em>Original Family Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getPluginFullName <em>Plugin Full Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getVersion <em>Version</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getTranslatedFamilyName <em>Translated Family Name</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#isIsTechnical <em>Is Technical</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getPluginDependencies <em>Plugin Dependencies</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getComponentNames <em>Component Names</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getImportType <em>Import Type</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#isIsVisibleInComponentDefinition <em>Is Visible In Component Definition</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getUriString <em>Uri String</em>}</li>
 *   <li>{@link org.talend.core.model.component_cache.ComponentInfo#getPathSource <em>Path Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo()
 * @model
 * @generated
 */
public interface ComponentInfo extends EObject {
    /**
     * Returns the value of the '<em><b>Original Family Name</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Original Family Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Original Family Name</em>' attribute.
     * @see #setOriginalFamilyName(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_OriginalFamilyName()
     * @model default=""
     * @generated
     */
    String getOriginalFamilyName();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getOriginalFamilyName <em>Original Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Family Name</em>' attribute.
     * @see #getOriginalFamilyName()
     * @generated
     */
    void setOriginalFamilyName(String value);

    /**
     * Returns the value of the '<em><b>Plugin Full Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plugin Full Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plugin Full Name</em>' attribute.
     * @see #setPluginFullName(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_PluginFullName()
     * @model
     * @generated
     */
    String getPluginFullName();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getPluginFullName <em>Plugin Full Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Plugin Full Name</em>' attribute.
     * @see #getPluginFullName()
     * @generated
     */
    void setPluginFullName(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Translated Family Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Translated Family Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Translated Family Name</em>' attribute.
     * @see #setTranslatedFamilyName(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_TranslatedFamilyName()
     * @model
     * @generated
     */
    String getTranslatedFamilyName();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getTranslatedFamilyName <em>Translated Family Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Translated Family Name</em>' attribute.
     * @see #getTranslatedFamilyName()
     * @generated
     */
    void setTranslatedFamilyName(String value);

    /**
     * Returns the value of the '<em><b>Is Technical</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Technical</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Technical</em>' attribute.
     * @see #setIsTechnical(boolean)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_IsTechnical()
     * @model default="false"
     * @generated
     */
    boolean isIsTechnical();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#isIsTechnical <em>Is Technical</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Technical</em>' attribute.
     * @see #isIsTechnical()
     * @generated
     */
    void setIsTechnical(boolean value);

    /**
     * Returns the value of the '<em><b>Plugin Dependencies</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plugin Dependencies</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Plugin Dependencies</em>' attribute list.
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_PluginDependencies()
     * @model
     * @generated
     */
    EList<String> getPluginDependencies();

    /**
     * Returns the value of the '<em><b>Component Names</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Names</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Names</em>' attribute list.
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_ComponentNames()
     * @model
     * @generated
     */
    EList<String> getComponentNames();

    /**
     * Returns the value of the '<em><b>Import Type</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.designer.core.model.utils.emf.component.IMPORTType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Import Type</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Import Type</em>' containment reference list.
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_ImportType()
     * @model containment="true"
     * @generated
     */
    EList<IMPORTType> getImportType();

    /**
     * Returns the value of the '<em><b>Is Visible In Component Definition</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Visible In Component Definition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Visible In Component Definition</em>' attribute.
     * @see #setIsVisibleInComponentDefinition(boolean)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_IsVisibleInComponentDefinition()
     * @model default="true"
     * @generated
     */
    boolean isIsVisibleInComponentDefinition();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#isIsVisibleInComponentDefinition <em>Is Visible In Component Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Visible In Component Definition</em>' attribute.
     * @see #isIsVisibleInComponentDefinition()
     * @generated
     */
    void setIsVisibleInComponentDefinition(boolean value);

    /**
     * Returns the value of the '<em><b>Uri String</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri String</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri String</em>' attribute.
     * @see #setUriString(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_UriString()
     * @model
     * @generated
     */
    String getUriString();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getUriString <em>Uri String</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uri String</em>' attribute.
     * @see #getUriString()
     * @generated
     */
    void setUriString(String value);

    /**
     * Returns the value of the '<em><b>Path Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Path Source</em>' attribute.
     * @see #setPathSource(String)
     * @see org.talend.core.model.component_cache.ComponentCachePackage#getComponentInfo_PathSource()
     * @model
     * @generated
     */
    String getPathSource();

    /**
     * Sets the value of the '{@link org.talend.core.model.component_cache.ComponentInfo#getPathSource <em>Path Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path Source</em>' attribute.
     * @see #getPathSource()
     * @generated
     */
    void setPathSource(String value);

} // ComponentInfo
