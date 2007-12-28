/**
 * <copyright> </copyright>
 * 
 * $Id$
 */
package org.talend.designer.joblet.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;
import org.talend.designer.joblet.model.AbstractJobletObject;
import org.talend.designer.joblet.model.JobletConnection;
import org.talend.designer.joblet.model.JobletFactory;
import org.talend.designer.joblet.model.JobletPackage;
import org.talend.designer.joblet.model.JobletProcess;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class JobletPackageImpl extends EPackageImpl implements JobletPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass jobletProcessEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass jobletConnectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractJobletObjectEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.talend.designer.joblet.model.JobletPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private JobletPackageImpl() {
        super(eNS_URI, JobletFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * Simple dependencies are satisfied by calling this method on all dependent packages before doing anything else.
     * This method drives initialization for interdependent packages directly, in parallel with this package, itself.
     * <p>
     * Of this package and its interdependencies, all packages which have not yet been registered by their URI values
     * are first created and registered. The packages are then initialized in two steps: meta-model objects for all of
     * the packages are created before any are initialized, since one package's meta-model objects may refer to those of
     * another.
     * <p>
     * Invocation of this method will not affect any packages that have already been initialized. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static JobletPackage init() {
        if (isInited)
            return (JobletPackage) EPackage.Registry.INSTANCE.getEPackage(JobletPackage.eNS_URI);

        // Obtain or create and register package
        JobletPackageImpl theJobletPackage = (JobletPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JobletPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(eNS_URI)
                : new JobletPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        TalendFilePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theJobletPackage.createPackageContents();

        // Initialize created meta-data
        theJobletPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theJobletPackage.freeze();

        return theJobletPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getJobletProcess() {
        return jobletProcessEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getJobletProcess_JobletLinks() {
        return (EReference) jobletProcessEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getJobletConnection() {
        return jobletConnectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getJobletConnection_Source() {
        return (EAttribute) jobletConnectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getJobletConnection_Target() {
        return (EAttribute) jobletConnectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractJobletObject() {
        return abstractJobletObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractJobletObject_Name() {
        return (EAttribute) abstractJobletObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractJobletObject_PosX() {
        return (EAttribute) abstractJobletObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractJobletObject_PosY() {
        return (EAttribute) abstractJobletObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractJobletObject_Description() {
        return (EAttribute) abstractJobletObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractJobletObject_Input() {
        return (EAttribute) abstractJobletObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public JobletFactory getJobletFactory() {
        return (JobletFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        jobletProcessEClass = createEClass(JOBLET_PROCESS);
        createEReference(jobletProcessEClass, JOBLET_PROCESS__JOBLET_LINKS);

        jobletConnectionEClass = createEClass(JOBLET_CONNECTION);
        createEAttribute(jobletConnectionEClass, JOBLET_CONNECTION__SOURCE);
        createEAttribute(jobletConnectionEClass, JOBLET_CONNECTION__TARGET);

        abstractJobletObjectEClass = createEClass(ABSTRACT_JOBLET_OBJECT);
        createEAttribute(abstractJobletObjectEClass, ABSTRACT_JOBLET_OBJECT__NAME);
        createEAttribute(abstractJobletObjectEClass, ABSTRACT_JOBLET_OBJECT__POS_X);
        createEAttribute(abstractJobletObjectEClass, ABSTRACT_JOBLET_OBJECT__POS_Y);
        createEAttribute(abstractJobletObjectEClass, ABSTRACT_JOBLET_OBJECT__DESCRIPTION);
        createEAttribute(abstractJobletObjectEClass, ABSTRACT_JOBLET_OBJECT__INPUT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        TalendFilePackage theTalendFilePackage = (TalendFilePackage) EPackage.Registry.INSTANCE
                .getEPackage(TalendFilePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        jobletProcessEClass.getESuperTypes().add(theTalendFilePackage.getProcessType());
        jobletConnectionEClass.getESuperTypes().add(this.getAbstractJobletObject());

        // Initialize classes and features; add operations and parameters
        initEClass(jobletProcessEClass, JobletProcess.class, "JobletProcess", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getJobletProcess_JobletLinks(), this.getJobletConnection(), null, "jobletLinks", null, 0, -1,
                JobletProcess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(jobletConnectionEClass, JobletConnection.class, "JobletConnection", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getJobletConnection_Source(), ecorePackage.getEString(), "source", null, 0, 1, JobletConnection.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getJobletConnection_Target(), ecorePackage.getEString(), "target", null, 0, 1, JobletConnection.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractJobletObjectEClass, AbstractJobletObject.class, "AbstractJobletObject", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractJobletObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, AbstractJobletObject.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractJobletObject_PosX(), ecorePackage.getEInt(), "posX", null, 0, 1, AbstractJobletObject.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractJobletObject_PosY(), ecorePackage.getEInt(), "posY", null, 0, 1, AbstractJobletObject.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractJobletObject_Description(), ecorePackage.getEString(), "description", null, 0, 1,
                AbstractJobletObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractJobletObject_Input(), ecorePackage.getEBoolean(), "input", null, 0, 1,
                AbstractJobletObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // JobletPackageImpl
