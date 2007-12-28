/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.joblet.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.talend.designer.core.model.utils.emf.talendfile.TalendFilePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.talend.designer.joblet.model.JobletFactory
 * @model kind="package"
 * @generated
 */
public interface JobletPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "model";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.talend.com/joblet.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "model";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    JobletPackage eINSTANCE = org.talend.designer.joblet.model.impl.JobletPackageImpl.init();

    /**
     * The meta object id for the '{@link org.talend.designer.joblet.model.impl.JobletProcessImpl <em>Process</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.designer.joblet.model.impl.JobletProcessImpl
     * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getJobletProcess()
     * @generated
     */
    int JOBLET_PROCESS = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__DESCRIPTION = TalendFilePackage.PROCESS_TYPE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Required</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__REQUIRED = TalendFilePackage.PROCESS_TYPE__REQUIRED;

    /**
     * The feature id for the '<em><b>Context</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__CONTEXT = TalendFilePackage.PROCESS_TYPE__CONTEXT;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__PARAMETERS = TalendFilePackage.PROCESS_TYPE__PARAMETERS;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__NODE = TalendFilePackage.PROCESS_TYPE__NODE;

    /**
     * The feature id for the '<em><b>Connection</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__CONNECTION = TalendFilePackage.PROCESS_TYPE__CONNECTION;

    /**
     * The feature id for the '<em><b>Note</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__NOTE = TalendFilePackage.PROCESS_TYPE__NOTE;

    /**
     * The feature id for the '<em><b>Logs</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__LOGS = TalendFilePackage.PROCESS_TYPE__LOGS;

    /**
     * The feature id for the '<em><b>Author</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__AUTHOR = TalendFilePackage.PROCESS_TYPE__AUTHOR;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__COMMENT = TalendFilePackage.PROCESS_TYPE__COMMENT;

    /**
     * The feature id for the '<em><b>Default Context</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__DEFAULT_CONTEXT = TalendFilePackage.PROCESS_TYPE__DEFAULT_CONTEXT;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__NAME = TalendFilePackage.PROCESS_TYPE__NAME;

    /**
     * The feature id for the '<em><b>Purpose</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__PURPOSE = TalendFilePackage.PROCESS_TYPE__PURPOSE;

    /**
     * The feature id for the '<em><b>Repository Context Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__REPOSITORY_CONTEXT_ID = TalendFilePackage.PROCESS_TYPE__REPOSITORY_CONTEXT_ID;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__STATUS = TalendFilePackage.PROCESS_TYPE__STATUS;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__VERSION = TalendFilePackage.PROCESS_TYPE__VERSION;

    /**
     * The feature id for the '<em><b>Joblet Links</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS__JOBLET_LINKS = TalendFilePackage.PROCESS_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Process</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_PROCESS_FEATURE_COUNT = TalendFilePackage.PROCESS_TYPE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl <em>Abstract Joblet Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl
     * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getAbstractJobletObject()
     * @generated
     */
    int ABSTRACT_JOBLET_OBJECT = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT__NAME = 0;

    /**
     * The feature id for the '<em><b>Pos X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT__POS_X = 1;

    /**
     * The feature id for the '<em><b>Pos Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT__POS_Y = 2;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT__DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT__INPUT = 4;

    /**
     * The number of structural features of the '<em>Abstract Joblet Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_JOBLET_OBJECT_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.talend.designer.joblet.model.impl.JobletConnectionImpl <em>Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.talend.designer.joblet.model.impl.JobletConnectionImpl
     * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getJobletConnection()
     * @generated
     */
    int JOBLET_CONNECTION = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__NAME = ABSTRACT_JOBLET_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Pos X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__POS_X = ABSTRACT_JOBLET_OBJECT__POS_X;

    /**
     * The feature id for the '<em><b>Pos Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__POS_Y = ABSTRACT_JOBLET_OBJECT__POS_Y;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__DESCRIPTION = ABSTRACT_JOBLET_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Input</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__INPUT = ABSTRACT_JOBLET_OBJECT__INPUT;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__SOURCE = ABSTRACT_JOBLET_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION__TARGET = ABSTRACT_JOBLET_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int JOBLET_CONNECTION_FEATURE_COUNT = ABSTRACT_JOBLET_OBJECT_FEATURE_COUNT + 2;

    /**
     * Returns the meta object for class '{@link org.talend.designer.joblet.model.JobletProcess <em>Process</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Process</em>'.
     * @see org.talend.designer.joblet.model.JobletProcess
     * @generated
     */
    EClass getJobletProcess();

    /**
     * Returns the meta object for the containment reference list '{@link org.talend.designer.joblet.model.JobletProcess#getJobletLinks <em>Joblet Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Joblet Links</em>'.
     * @see org.talend.designer.joblet.model.JobletProcess#getJobletLinks()
     * @see #getJobletProcess()
     * @generated
     */
    EReference getJobletProcess_JobletLinks();

    /**
     * Returns the meta object for class '{@link org.talend.designer.joblet.model.JobletConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connection</em>'.
     * @see org.talend.designer.joblet.model.JobletConnection
     * @generated
     */
    EClass getJobletConnection();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.JobletConnection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see org.talend.designer.joblet.model.JobletConnection#getSource()
     * @see #getJobletConnection()
     * @generated
     */
    EAttribute getJobletConnection_Source();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.JobletConnection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target</em>'.
     * @see org.talend.designer.joblet.model.JobletConnection#getTarget()
     * @see #getJobletConnection()
     * @generated
     */
    EAttribute getJobletConnection_Target();

    /**
     * Returns the meta object for class '{@link org.talend.designer.joblet.model.AbstractJobletObject <em>Abstract Joblet Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Joblet Object</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject
     * @generated
     */
    EClass getAbstractJobletObject();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.AbstractJobletObject#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject#getName()
     * @see #getAbstractJobletObject()
     * @generated
     */
    EAttribute getAbstractJobletObject_Name();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosX <em>Pos X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pos X</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject#getPosX()
     * @see #getAbstractJobletObject()
     * @generated
     */
    EAttribute getAbstractJobletObject_PosX();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.AbstractJobletObject#getPosY <em>Pos Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pos Y</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject#getPosY()
     * @see #getAbstractJobletObject()
     * @generated
     */
    EAttribute getAbstractJobletObject_PosY();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.AbstractJobletObject#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject#getDescription()
     * @see #getAbstractJobletObject()
     * @generated
     */
    EAttribute getAbstractJobletObject_Description();

    /**
     * Returns the meta object for the attribute '{@link org.talend.designer.joblet.model.AbstractJobletObject#isInput <em>Input</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Input</em>'.
     * @see org.talend.designer.joblet.model.AbstractJobletObject#isInput()
     * @see #getAbstractJobletObject()
     * @generated
     */
    EAttribute getAbstractJobletObject_Input();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    JobletFactory getJobletFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.talend.designer.joblet.model.impl.JobletProcessImpl <em>Process</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.designer.joblet.model.impl.JobletProcessImpl
         * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getJobletProcess()
         * @generated
         */
        EClass JOBLET_PROCESS = eINSTANCE.getJobletProcess();

        /**
         * The meta object literal for the '<em><b>Joblet Links</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference JOBLET_PROCESS__JOBLET_LINKS = eINSTANCE.getJobletProcess_JobletLinks();

        /**
         * The meta object literal for the '{@link org.talend.designer.joblet.model.impl.JobletConnectionImpl <em>Connection</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.designer.joblet.model.impl.JobletConnectionImpl
         * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getJobletConnection()
         * @generated
         */
        EClass JOBLET_CONNECTION = eINSTANCE.getJobletConnection();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute JOBLET_CONNECTION__SOURCE = eINSTANCE.getJobletConnection_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute JOBLET_CONNECTION__TARGET = eINSTANCE.getJobletConnection_Target();

        /**
         * The meta object literal for the '{@link org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl <em>Abstract Joblet Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.talend.designer.joblet.model.impl.AbstractJobletObjectImpl
         * @see org.talend.designer.joblet.model.impl.JobletPackageImpl#getAbstractJobletObject()
         * @generated
         */
        EClass ABSTRACT_JOBLET_OBJECT = eINSTANCE.getAbstractJobletObject();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_JOBLET_OBJECT__NAME = eINSTANCE.getAbstractJobletObject_Name();

        /**
         * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_JOBLET_OBJECT__POS_X = eINSTANCE.getAbstractJobletObject_PosX();

        /**
         * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_JOBLET_OBJECT__POS_Y = eINSTANCE.getAbstractJobletObject_PosY();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_JOBLET_OBJECT__DESCRIPTION = eINSTANCE.getAbstractJobletObject_Description();

        /**
         * The meta object literal for the '<em><b>Input</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_JOBLET_OBJECT__INPUT = eINSTANCE.getAbstractJobletObject_Input();

    }

} //JobletPackage
