/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.core.model.properties.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.core.model.properties.FolderItem;
import org.talend.core.model.properties.Project;
import org.talend.core.model.properties.ProjectComponentAuthorisation;
import org.talend.core.model.properties.ProjectReference;
import org.talend.core.model.properties.PropertiesPackage;
import org.talend.core.model.properties.Status;
import org.talend.core.model.properties.User;
import org.talend.core.model.properties.UserProjectAuthorization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Project</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getTechnicalStatus <em>Technical Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getDocumentationStatus <em>Documentation Status</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getTechnicalLabel <em>Technical Label</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getFolders <em>Folders</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#isDeleted <em>Deleted</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getDeleteDate <em>Delete Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getUserAuthorization <em>User Authorization</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getAllowedComponents <em>Allowed Components</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getReferencedProjects <em>Referenced Projects</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.ProjectImpl#getAvailableRefProject <em>Available Ref Project</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProjectImpl extends EObjectImpl implements Project {

    /**
     * The cached value of the '{@link #getTechnicalStatus() <em>Technical Status</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTechnicalStatus()
     * @generated
     * @ordered
     */
    protected EList technicalStatus = null;

    /**
     * The cached value of the '{@link #getDocumentationStatus() <em>Documentation Status</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getDocumentationStatus()
     * @generated
     * @ordered
     */
    protected EList documentationStatus = null;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final int ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected int id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

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
     * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected static final String LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected String language = LANGUAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getTechnicalLabel() <em>Technical Label</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTechnicalLabel()
     * @generated
     * @ordered
     */
    protected static final String TECHNICAL_LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTechnicalLabel() <em>Technical Label</em>}' attribute.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTechnicalLabel()
     * @generated
     * @ordered
     */
    protected String technicalLabel = TECHNICAL_LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isLocal()
     * @generated
     * @ordered
     */
    protected static final boolean LOCAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isLocal()
     * @generated
     * @ordered
     */
    protected boolean local = LOCAL_EDEFAULT;

    /**
     * The cached value of the '{@link #getFolders() <em>Folders</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFolders()
     * @generated
     * @ordered
     */
    protected EList folders = null;

    /**
     * The default value of the '{@link #isDeleted() <em>Deleted</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isDeleted()
     * @generated
     * @ordered
     */
    protected static final boolean DELETED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDeleted() <em>Deleted</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isDeleted()
     * @generated
     * @ordered
     */
    protected boolean deleted = DELETED_EDEFAULT;

    /**
     * The default value of the '{@link #getDeleteDate() <em>Delete Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeleteDate()
     * @generated
     * @ordered
     */
    protected static final Date DELETE_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDeleteDate() <em>Delete Date</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getDeleteDate()
     * @generated
     * @ordered
     */
    protected Date deleteDate = DELETE_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected static final Date CREATION_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreationDate()
     * @generated
     * @ordered
     */
    protected Date creationDate = CREATION_DATE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected User author = null;

    /**
     * The cached value of the '{@link #getUserAuthorization() <em>User Authorization</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUserAuthorization()
     * @generated
     * @ordered
     */
    protected EList userAuthorization = null;

    /**
     * The cached value of the '{@link #getAllowedComponents() <em>Allowed Components</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAllowedComponents()
     * @generated
     * @ordered
     */
    protected EList allowedComponents = null;

    /**
     * The cached value of the '{@link #getReferencedProjects() <em>Referenced Projects</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReferencedProjects()
     * @generated
     * @ordered
     */
    protected EList referencedProjects = null;

    /**
     * The cached value of the '{@link #getAvailableRefProject() <em>Available Ref Project</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAvailableRefProject()
     * @generated
     * @ordered
     */
    protected EList availableRefProject = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ProjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.PROJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList getTechnicalStatus() {
        if (technicalStatus == null) {
            technicalStatus = new EObjectContainmentEList(Status.class, this, PropertiesPackage.PROJECT__TECHNICAL_STATUS);
        }
        return technicalStatus;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList getDocumentationStatus() {
        if (documentationStatus == null) {
            documentationStatus = new EObjectContainmentEList(Status.class, this, PropertiesPackage.PROJECT__DOCUMENTATION_STATUS);
        }
        return documentationStatus;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(int newId) {
        int oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getLanguage() {
        return language;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLanguage(String newLanguage) {
        String oldLanguage = language;
        language = newLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__LANGUAGE, oldLanguage, language));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public User getAuthor() {
        if (author != null && author.eIsProxy()) {
            InternalEObject oldAuthor = (InternalEObject)author;
            author = (User)eResolveProxy(oldAuthor);
            if (author != oldAuthor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.PROJECT__AUTHOR, oldAuthor, author));
            }
        }
        return author;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public User basicGetAuthor() {
        return author;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setAuthor(User newAuthor) {
        User oldAuthor = author;
        author = newAuthor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__AUTHOR, oldAuthor, author));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getUserAuthorization() {
        if (userAuthorization == null) {
            userAuthorization = new EObjectWithInverseResolvingEList(UserProjectAuthorization.class, this, PropertiesPackage.PROJECT__USER_AUTHORIZATION, PropertiesPackage.USER_PROJECT_AUTHORIZATION__PROJECT);
        }
        return userAuthorization;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getAllowedComponents() {
        if (allowedComponents == null) {
            allowedComponents = new EObjectWithInverseResolvingEList(ProjectComponentAuthorisation.class, this, PropertiesPackage.PROJECT__ALLOWED_COMPONENTS, PropertiesPackage.PROJECT_COMPONENT_AUTHORISATION__PROJECT);
        }
        return allowedComponents;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getReferencedProjects() {
        if (referencedProjects == null) {
            referencedProjects = new EObjectWithInverseResolvingEList(ProjectReference.class, this, PropertiesPackage.PROJECT__REFERENCED_PROJECTS, PropertiesPackage.PROJECT_REFERENCE__PROJECT);
        }
        return referencedProjects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getAvailableRefProject() {
        if (availableRefProject == null) {
            availableRefProject = new EObjectWithInverseResolvingEList(ProjectReference.class, this, PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT, PropertiesPackage.PROJECT_REFERENCE__REFERENCED_PROJECT);
        }
        return availableRefProject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getTechnicalLabel() {
        return technicalLabel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setTechnicalLabel(String newTechnicalLabel) {
        String oldTechnicalLabel = technicalLabel;
        technicalLabel = newTechnicalLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__TECHNICAL_LABEL, oldTechnicalLabel, technicalLabel));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setLocal(boolean newLocal) {
        boolean oldLocal = local;
        local = newLocal;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__LOCAL, oldLocal, local));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList getFolders() {
        if (folders == null) {
            folders = new EObjectContainmentEList(FolderItem.class, this, PropertiesPackage.PROJECT__FOLDERS);
        }
        return folders;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDeleted(boolean newDeleted) {
        boolean oldDeleted = deleted;
        deleted = newDeleted;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__DELETED, oldDeleted, deleted));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Date getDeleteDate() {
        return deleteDate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setDeleteDate(Date newDeleteDate) {
        Date oldDeleteDate = deleteDate;
        deleteDate = newDeleteDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__DELETE_DATE, oldDeleteDate, deleteDate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setCreationDate(Date newCreationDate) {
        Date oldCreationDate = creationDate;
        creationDate = newCreationDate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PROJECT__CREATION_DATE, oldCreationDate, creationDate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                return ((InternalEList)getUserAuthorization()).basicAdd(otherEnd, msgs);
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                return ((InternalEList)getAllowedComponents()).basicAdd(otherEnd, msgs);
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                return ((InternalEList)getReferencedProjects()).basicAdd(otherEnd, msgs);
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                return ((InternalEList)getAvailableRefProject()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__TECHNICAL_STATUS:
                return ((InternalEList)getTechnicalStatus()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__DOCUMENTATION_STATUS:
                return ((InternalEList)getDocumentationStatus()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__FOLDERS:
                return ((InternalEList)getFolders()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                return ((InternalEList)getUserAuthorization()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                return ((InternalEList)getAllowedComponents()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                return ((InternalEList)getReferencedProjects()).basicRemove(otherEnd, msgs);
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                return ((InternalEList)getAvailableRefProject()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__TECHNICAL_STATUS:
                return getTechnicalStatus();
            case PropertiesPackage.PROJECT__DOCUMENTATION_STATUS:
                return getDocumentationStatus();
            case PropertiesPackage.PROJECT__ID:
                return new Integer(getId());
            case PropertiesPackage.PROJECT__LABEL:
                return getLabel();
            case PropertiesPackage.PROJECT__DESCRIPTION:
                return getDescription();
            case PropertiesPackage.PROJECT__LANGUAGE:
                return getLanguage();
            case PropertiesPackage.PROJECT__TECHNICAL_LABEL:
                return getTechnicalLabel();
            case PropertiesPackage.PROJECT__LOCAL:
                return isLocal() ? Boolean.TRUE : Boolean.FALSE;
            case PropertiesPackage.PROJECT__FOLDERS:
                return getFolders();
            case PropertiesPackage.PROJECT__DELETED:
                return isDeleted() ? Boolean.TRUE : Boolean.FALSE;
            case PropertiesPackage.PROJECT__DELETE_DATE:
                return getDeleteDate();
            case PropertiesPackage.PROJECT__CREATION_DATE:
                return getCreationDate();
            case PropertiesPackage.PROJECT__AUTHOR:
                if (resolve) return getAuthor();
                return basicGetAuthor();
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                return getUserAuthorization();
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                return getAllowedComponents();
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                return getReferencedProjects();
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                return getAvailableRefProject();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__TECHNICAL_STATUS:
                getTechnicalStatus().clear();
                getTechnicalStatus().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__DOCUMENTATION_STATUS:
                getDocumentationStatus().clear();
                getDocumentationStatus().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__ID:
                setId(((Integer)newValue).intValue());
                return;
            case PropertiesPackage.PROJECT__LABEL:
                setLabel((String)newValue);
                return;
            case PropertiesPackage.PROJECT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case PropertiesPackage.PROJECT__LANGUAGE:
                setLanguage((String)newValue);
                return;
            case PropertiesPackage.PROJECT__TECHNICAL_LABEL:
                setTechnicalLabel((String)newValue);
                return;
            case PropertiesPackage.PROJECT__LOCAL:
                setLocal(((Boolean)newValue).booleanValue());
                return;
            case PropertiesPackage.PROJECT__FOLDERS:
                getFolders().clear();
                getFolders().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__DELETED:
                setDeleted(((Boolean)newValue).booleanValue());
                return;
            case PropertiesPackage.PROJECT__DELETE_DATE:
                setDeleteDate((Date)newValue);
                return;
            case PropertiesPackage.PROJECT__CREATION_DATE:
                setCreationDate((Date)newValue);
                return;
            case PropertiesPackage.PROJECT__AUTHOR:
                setAuthor((User)newValue);
                return;
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                getUserAuthorization().clear();
                getUserAuthorization().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                getAllowedComponents().clear();
                getAllowedComponents().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                getReferencedProjects().clear();
                getReferencedProjects().addAll((Collection)newValue);
                return;
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                getAvailableRefProject().clear();
                getAvailableRefProject().addAll((Collection)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__TECHNICAL_STATUS:
                getTechnicalStatus().clear();
                return;
            case PropertiesPackage.PROJECT__DOCUMENTATION_STATUS:
                getDocumentationStatus().clear();
                return;
            case PropertiesPackage.PROJECT__ID:
                setId(ID_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__LANGUAGE:
                setLanguage(LANGUAGE_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__TECHNICAL_LABEL:
                setTechnicalLabel(TECHNICAL_LABEL_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__LOCAL:
                setLocal(LOCAL_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__FOLDERS:
                getFolders().clear();
                return;
            case PropertiesPackage.PROJECT__DELETED:
                setDeleted(DELETED_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__DELETE_DATE:
                setDeleteDate(DELETE_DATE_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__CREATION_DATE:
                setCreationDate(CREATION_DATE_EDEFAULT);
                return;
            case PropertiesPackage.PROJECT__AUTHOR:
                setAuthor((User)null);
                return;
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                getUserAuthorization().clear();
                return;
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                getAllowedComponents().clear();
                return;
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                getReferencedProjects().clear();
                return;
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                getAvailableRefProject().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiesPackage.PROJECT__TECHNICAL_STATUS:
                return technicalStatus != null && !technicalStatus.isEmpty();
            case PropertiesPackage.PROJECT__DOCUMENTATION_STATUS:
                return documentationStatus != null && !documentationStatus.isEmpty();
            case PropertiesPackage.PROJECT__ID:
                return id != ID_EDEFAULT;
            case PropertiesPackage.PROJECT__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case PropertiesPackage.PROJECT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case PropertiesPackage.PROJECT__LANGUAGE:
                return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
            case PropertiesPackage.PROJECT__TECHNICAL_LABEL:
                return TECHNICAL_LABEL_EDEFAULT == null ? technicalLabel != null : !TECHNICAL_LABEL_EDEFAULT.equals(technicalLabel);
            case PropertiesPackage.PROJECT__LOCAL:
                return local != LOCAL_EDEFAULT;
            case PropertiesPackage.PROJECT__FOLDERS:
                return folders != null && !folders.isEmpty();
            case PropertiesPackage.PROJECT__DELETED:
                return deleted != DELETED_EDEFAULT;
            case PropertiesPackage.PROJECT__DELETE_DATE:
                return DELETE_DATE_EDEFAULT == null ? deleteDate != null : !DELETE_DATE_EDEFAULT.equals(deleteDate);
            case PropertiesPackage.PROJECT__CREATION_DATE:
                return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
            case PropertiesPackage.PROJECT__AUTHOR:
                return author != null;
            case PropertiesPackage.PROJECT__USER_AUTHORIZATION:
                return userAuthorization != null && !userAuthorization.isEmpty();
            case PropertiesPackage.PROJECT__ALLOWED_COMPONENTS:
                return allowedComponents != null && !allowedComponents.isEmpty();
            case PropertiesPackage.PROJECT__REFERENCED_PROJECTS:
                return referencedProjects != null && !referencedProjects.isEmpty();
            case PropertiesPackage.PROJECT__AVAILABLE_REF_PROJECT:
                return availableRefProject != null && !availableRefProject.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", label: ");
        result.append(label);
        result.append(", description: ");
        result.append(description);
        result.append(", language: ");
        result.append(language);
        result.append(", technicalLabel: ");
        result.append(technicalLabel);
        result.append(", local: ");
        result.append(local);
        result.append(", deleted: ");
        result.append(deleted);
        result.append(", deleteDate: ");
        result.append(deleteDate);
        result.append(", creationDate: ");
        result.append(creationDate);
        result.append(')');
        return result.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
//    @Override
//    public int hashCode() {
//        final int PRIME = 31;
//        int result = 1;
//        result = PRIME * result + this.id;
//        return result;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        final ProjectImpl other = (ProjectImpl) obj;
//        if (this.id != other.id)
//            return false;
//        return true;
//    }

} // ProjectImpl
