/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.DEFAULTType;
import org.talend.designer.core.model.utils.emf.component.ITEMSType;
import org.talend.designer.core.model.utils.emf.component.PARAMETERType;

import org.talend.designer.core.model.utils.emf.component.TABLEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PARAMETER Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getDEFAULT <em>DEFAULT</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getITEMS <em>ITEMS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getTABLE <em>TABLE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getFIELD <em>FIELD</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getNAME <em>NAME</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getNBLINES <em>NBLINES</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getNOTSHOWIF <em>NOTSHOWIF</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getNUMROW <em>NUMROW</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#isREADONLY <em>READONLY</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getREPOSITORYVALUE <em>REPOSITORYVALUE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#isREQUIRED <em>REQUIRED</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#isSHOW <em>SHOW</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.PARAMETERTypeImpl#getSHOWIF <em>SHOWIF</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PARAMETERTypeImpl extends EObjectImpl implements PARAMETERType {
    /**
     * The cached value of the '{@link #getDEFAULT() <em>DEFAULT</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDEFAULT()
     * @generated
     * @ordered
     */
    protected EList dEFAULT = null;

    /**
     * The cached value of the '{@link #getITEMS() <em>ITEMS</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getITEMS()
     * @generated
     * @ordered
     */
    protected ITEMSType iTEMS = null;

    /**
     * The cached value of the '{@link #getTABLE() <em>TABLE</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTABLE()
     * @generated
     * @ordered
     */
    protected TABLEType tABLE = null;

    /**
     * The default value of the '{@link #getFIELD() <em>FIELD</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFIELD()
     * @generated
     * @ordered
     */
    protected static final String FIELD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFIELD() <em>FIELD</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFIELD()
     * @generated
     * @ordered
     */
    protected String fIELD = FIELD_EDEFAULT;

    /**
     * The default value of the '{@link #getNAME() <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNAME()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNAME() <em>NAME</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNAME()
     * @generated
     * @ordered
     */
    protected String nAME = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getNBLINES() <em>NBLINES</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNBLINES()
     * @generated
     * @ordered
     */
    protected static final int NBLINES_EDEFAULT = 3;

    /**
     * The cached value of the '{@link #getNBLINES() <em>NBLINES</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNBLINES()
     * @generated
     * @ordered
     */
    protected int nBLINES = NBLINES_EDEFAULT;

    /**
     * This is true if the NBLINES attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean nBLINESESet = false;

    /**
     * The default value of the '{@link #getNOTSHOWIF() <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNOTSHOWIF()
     * @generated
     * @ordered
     */
    protected static final String NOTSHOWIF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNOTSHOWIF() <em>NOTSHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNOTSHOWIF()
     * @generated
     * @ordered
     */
    protected String nOTSHOWIF = NOTSHOWIF_EDEFAULT;

    /**
     * The default value of the '{@link #getNUMROW() <em>NUMROW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNUMROW()
     * @generated
     * @ordered
     */
    protected static final int NUMROW_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNUMROW() <em>NUMROW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNUMROW()
     * @generated
     * @ordered
     */
    protected int nUMROW = NUMROW_EDEFAULT;

    /**
     * This is true if the NUMROW attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean nUMROWESet = false;

    /**
     * The default value of the '{@link #isREADONLY() <em>READONLY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREADONLY()
     * @generated
     * @ordered
     */
    protected static final boolean READONLY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isREADONLY() <em>READONLY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREADONLY()
     * @generated
     * @ordered
     */
    protected boolean rEADONLY = READONLY_EDEFAULT;

    /**
     * This is true if the READONLY attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean rEADONLYESet = false;

    /**
     * The default value of the '{@link #getREPOSITORYVALUE() <em>REPOSITORYVALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getREPOSITORYVALUE()
     * @generated
     * @ordered
     */
    protected static final String REPOSITORYVALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getREPOSITORYVALUE() <em>REPOSITORYVALUE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getREPOSITORYVALUE()
     * @generated
     * @ordered
     */
    protected String rEPOSITORYVALUE = REPOSITORYVALUE_EDEFAULT;

    /**
     * The default value of the '{@link #isREQUIRED() <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREQUIRED()
     * @generated
     * @ordered
     */
    protected static final boolean REQUIRED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isREQUIRED() <em>REQUIRED</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isREQUIRED()
     * @generated
     * @ordered
     */
    protected boolean rEQUIRED = REQUIRED_EDEFAULT;

    /**
     * This is true if the REQUIRED attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean rEQUIREDESet = false;

    /**
     * The default value of the '{@link #isSHOW() <em>SHOW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSHOW()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSHOW() <em>SHOW</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSHOW()
     * @generated
     * @ordered
     */
    protected boolean sHOW = SHOW_EDEFAULT;

    /**
     * This is true if the SHOW attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean sHOWESet = false;

    /**
     * The default value of the '{@link #getSHOWIF() <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSHOWIF()
     * @generated
     * @ordered
     */
    protected static final String SHOWIF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSHOWIF() <em>SHOWIF</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSHOWIF()
     * @generated
     * @ordered
     */
    protected String sHOWIF = SHOWIF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PARAMETERTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.PARAMETER_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDEFAULT() {
        if (dEFAULT == null) {
            dEFAULT = new EObjectContainmentEList(DEFAULTType.class, this, ComponentPackage.PARAMETER_TYPE__DEFAULT);
        }
        return dEFAULT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ITEMSType getITEMS() {
        return iTEMS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetITEMS(ITEMSType newITEMS, NotificationChain msgs) {
        ITEMSType oldITEMS = iTEMS;
        iTEMS = newITEMS;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__ITEMS, oldITEMS, newITEMS);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setITEMS(ITEMSType newITEMS) {
        if (newITEMS != iTEMS) {
            NotificationChain msgs = null;
            if (iTEMS != null)
                msgs = ((InternalEObject)iTEMS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PARAMETER_TYPE__ITEMS, null, msgs);
            if (newITEMS != null)
                msgs = ((InternalEObject)newITEMS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PARAMETER_TYPE__ITEMS, null, msgs);
            msgs = basicSetITEMS(newITEMS, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__ITEMS, newITEMS, newITEMS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TABLEType getTABLE() {
        return tABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTABLE(TABLEType newTABLE, NotificationChain msgs) {
        TABLEType oldTABLE = tABLE;
        tABLE = newTABLE;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__TABLE, oldTABLE, newTABLE);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTABLE(TABLEType newTABLE) {
        if (newTABLE != tABLE) {
            NotificationChain msgs = null;
            if (tABLE != null)
                msgs = ((InternalEObject)tABLE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PARAMETER_TYPE__TABLE, null, msgs);
            if (newTABLE != null)
                msgs = ((InternalEObject)newTABLE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.PARAMETER_TYPE__TABLE, null, msgs);
            msgs = basicSetTABLE(newTABLE, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__TABLE, newTABLE, newTABLE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFIELD() {
        return fIELD;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFIELD(String newFIELD) {
        String oldFIELD = fIELD;
        fIELD = newFIELD;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__FIELD, oldFIELD, fIELD));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNAME() {
        return nAME;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNAME(String newNAME) {
        String oldNAME = nAME;
        nAME = newNAME;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__NAME, oldNAME, nAME));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNBLINES() {
        return nBLINES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNBLINES(int newNBLINES) {
        int oldNBLINES = nBLINES;
        nBLINES = newNBLINES;
        boolean oldNBLINESESet = nBLINESESet;
        nBLINESESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__NBLINES, oldNBLINES, nBLINES, !oldNBLINESESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNBLINES() {
        int oldNBLINES = nBLINES;
        boolean oldNBLINESESet = nBLINESESet;
        nBLINES = NBLINES_EDEFAULT;
        nBLINESESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.PARAMETER_TYPE__NBLINES, oldNBLINES, NBLINES_EDEFAULT, oldNBLINESESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNBLINES() {
        return nBLINESESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getNOTSHOWIF() {
        return nOTSHOWIF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNOTSHOWIF(String newNOTSHOWIF) {
        String oldNOTSHOWIF = nOTSHOWIF;
        nOTSHOWIF = newNOTSHOWIF;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__NOTSHOWIF, oldNOTSHOWIF, nOTSHOWIF));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getNUMROW() {
        return nUMROW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNUMROW(int newNUMROW) {
        int oldNUMROW = nUMROW;
        nUMROW = newNUMROW;
        boolean oldNUMROWESet = nUMROWESet;
        nUMROWESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__NUMROW, oldNUMROW, nUMROW, !oldNUMROWESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetNUMROW() {
        int oldNUMROW = nUMROW;
        boolean oldNUMROWESet = nUMROWESet;
        nUMROW = NUMROW_EDEFAULT;
        nUMROWESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.PARAMETER_TYPE__NUMROW, oldNUMROW, NUMROW_EDEFAULT, oldNUMROWESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNUMROW() {
        return nUMROWESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isREADONLY() {
        return rEADONLY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREADONLY(boolean newREADONLY) {
        boolean oldREADONLY = rEADONLY;
        rEADONLY = newREADONLY;
        boolean oldREADONLYESet = rEADONLYESet;
        rEADONLYESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__READONLY, oldREADONLY, rEADONLY, !oldREADONLYESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetREADONLY() {
        boolean oldREADONLY = rEADONLY;
        boolean oldREADONLYESet = rEADONLYESet;
        rEADONLY = READONLY_EDEFAULT;
        rEADONLYESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.PARAMETER_TYPE__READONLY, oldREADONLY, READONLY_EDEFAULT, oldREADONLYESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetREADONLY() {
        return rEADONLYESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getREPOSITORYVALUE() {
        return rEPOSITORYVALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREPOSITORYVALUE(String newREPOSITORYVALUE) {
        String oldREPOSITORYVALUE = rEPOSITORYVALUE;
        rEPOSITORYVALUE = newREPOSITORYVALUE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__REPOSITORYVALUE, oldREPOSITORYVALUE, rEPOSITORYVALUE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isREQUIRED() {
        return rEQUIRED;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setREQUIRED(boolean newREQUIRED) {
        boolean oldREQUIRED = rEQUIRED;
        rEQUIRED = newREQUIRED;
        boolean oldREQUIREDESet = rEQUIREDESet;
        rEQUIREDESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__REQUIRED, oldREQUIRED, rEQUIRED, !oldREQUIREDESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetREQUIRED() {
        boolean oldREQUIRED = rEQUIRED;
        boolean oldREQUIREDESet = rEQUIREDESet;
        rEQUIRED = REQUIRED_EDEFAULT;
        rEQUIREDESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.PARAMETER_TYPE__REQUIRED, oldREQUIRED, REQUIRED_EDEFAULT, oldREQUIREDESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetREQUIRED() {
        return rEQUIREDESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSHOW() {
        return sHOW;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSHOW(boolean newSHOW) {
        boolean oldSHOW = sHOW;
        sHOW = newSHOW;
        boolean oldSHOWESet = sHOWESet;
        sHOWESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__SHOW, oldSHOW, sHOW, !oldSHOWESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSHOW() {
        boolean oldSHOW = sHOW;
        boolean oldSHOWESet = sHOWESet;
        sHOW = SHOW_EDEFAULT;
        sHOWESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.PARAMETER_TYPE__SHOW, oldSHOW, SHOW_EDEFAULT, oldSHOWESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSHOW() {
        return sHOWESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSHOWIF() {
        return sHOWIF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSHOWIF(String newSHOWIF) {
        String oldSHOWIF = sHOWIF;
        sHOWIF = newSHOWIF;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PARAMETER_TYPE__SHOWIF, oldSHOWIF, sHOWIF));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentPackage.PARAMETER_TYPE__DEFAULT:
                return ((InternalEList)getDEFAULT()).basicRemove(otherEnd, msgs);
            case ComponentPackage.PARAMETER_TYPE__ITEMS:
                return basicSetITEMS(null, msgs);
            case ComponentPackage.PARAMETER_TYPE__TABLE:
                return basicSetTABLE(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.PARAMETER_TYPE__DEFAULT:
                return getDEFAULT();
            case ComponentPackage.PARAMETER_TYPE__ITEMS:
                return getITEMS();
            case ComponentPackage.PARAMETER_TYPE__TABLE:
                return getTABLE();
            case ComponentPackage.PARAMETER_TYPE__FIELD:
                return getFIELD();
            case ComponentPackage.PARAMETER_TYPE__NAME:
                return getNAME();
            case ComponentPackage.PARAMETER_TYPE__NBLINES:
                return new Integer(getNBLINES());
            case ComponentPackage.PARAMETER_TYPE__NOTSHOWIF:
                return getNOTSHOWIF();
            case ComponentPackage.PARAMETER_TYPE__NUMROW:
                return new Integer(getNUMROW());
            case ComponentPackage.PARAMETER_TYPE__READONLY:
                return isREADONLY() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.PARAMETER_TYPE__REPOSITORYVALUE:
                return getREPOSITORYVALUE();
            case ComponentPackage.PARAMETER_TYPE__REQUIRED:
                return isREQUIRED() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.PARAMETER_TYPE__SHOW:
                return isSHOW() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.PARAMETER_TYPE__SHOWIF:
                return getSHOWIF();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked") //$NON-NLS-1$
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentPackage.PARAMETER_TYPE__DEFAULT:
                getDEFAULT().clear();
                getDEFAULT().addAll((Collection)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__ITEMS:
                setITEMS((ITEMSType)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__TABLE:
                setTABLE((TABLEType)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__FIELD:
                setFIELD((String)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__NAME:
                setNAME((String)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__NBLINES:
                setNBLINES(((Integer)newValue).intValue());
                return;
            case ComponentPackage.PARAMETER_TYPE__NOTSHOWIF:
                setNOTSHOWIF((String)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__NUMROW:
                setNUMROW(((Integer)newValue).intValue());
                return;
            case ComponentPackage.PARAMETER_TYPE__READONLY:
                setREADONLY(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.PARAMETER_TYPE__REPOSITORYVALUE:
                setREPOSITORYVALUE((String)newValue);
                return;
            case ComponentPackage.PARAMETER_TYPE__REQUIRED:
                setREQUIRED(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.PARAMETER_TYPE__SHOW:
                setSHOW(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.PARAMETER_TYPE__SHOWIF:
                setSHOWIF((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case ComponentPackage.PARAMETER_TYPE__DEFAULT:
                getDEFAULT().clear();
                return;
            case ComponentPackage.PARAMETER_TYPE__ITEMS:
                setITEMS((ITEMSType)null);
                return;
            case ComponentPackage.PARAMETER_TYPE__TABLE:
                setTABLE((TABLEType)null);
                return;
            case ComponentPackage.PARAMETER_TYPE__FIELD:
                setFIELD(FIELD_EDEFAULT);
                return;
            case ComponentPackage.PARAMETER_TYPE__NAME:
                setNAME(NAME_EDEFAULT);
                return;
            case ComponentPackage.PARAMETER_TYPE__NBLINES:
                unsetNBLINES();
                return;
            case ComponentPackage.PARAMETER_TYPE__NOTSHOWIF:
                setNOTSHOWIF(NOTSHOWIF_EDEFAULT);
                return;
            case ComponentPackage.PARAMETER_TYPE__NUMROW:
                unsetNUMROW();
                return;
            case ComponentPackage.PARAMETER_TYPE__READONLY:
                unsetREADONLY();
                return;
            case ComponentPackage.PARAMETER_TYPE__REPOSITORYVALUE:
                setREPOSITORYVALUE(REPOSITORYVALUE_EDEFAULT);
                return;
            case ComponentPackage.PARAMETER_TYPE__REQUIRED:
                unsetREQUIRED();
                return;
            case ComponentPackage.PARAMETER_TYPE__SHOW:
                unsetSHOW();
                return;
            case ComponentPackage.PARAMETER_TYPE__SHOWIF:
                setSHOWIF(SHOWIF_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ComponentPackage.PARAMETER_TYPE__DEFAULT:
                return dEFAULT != null && !dEFAULT.isEmpty();
            case ComponentPackage.PARAMETER_TYPE__ITEMS:
                return iTEMS != null;
            case ComponentPackage.PARAMETER_TYPE__TABLE:
                return tABLE != null;
            case ComponentPackage.PARAMETER_TYPE__FIELD:
                return FIELD_EDEFAULT == null ? fIELD != null : !FIELD_EDEFAULT.equals(fIELD);
            case ComponentPackage.PARAMETER_TYPE__NAME:
                return NAME_EDEFAULT == null ? nAME != null : !NAME_EDEFAULT.equals(nAME);
            case ComponentPackage.PARAMETER_TYPE__NBLINES:
                return isSetNBLINES();
            case ComponentPackage.PARAMETER_TYPE__NOTSHOWIF:
                return NOTSHOWIF_EDEFAULT == null ? nOTSHOWIF != null : !NOTSHOWIF_EDEFAULT.equals(nOTSHOWIF);
            case ComponentPackage.PARAMETER_TYPE__NUMROW:
                return isSetNUMROW();
            case ComponentPackage.PARAMETER_TYPE__READONLY:
                return isSetREADONLY();
            case ComponentPackage.PARAMETER_TYPE__REPOSITORYVALUE:
                return REPOSITORYVALUE_EDEFAULT == null ? rEPOSITORYVALUE != null : !REPOSITORYVALUE_EDEFAULT.equals(rEPOSITORYVALUE);
            case ComponentPackage.PARAMETER_TYPE__REQUIRED:
                return isSetREQUIRED();
            case ComponentPackage.PARAMETER_TYPE__SHOW:
                return isSetSHOW();
            case ComponentPackage.PARAMETER_TYPE__SHOWIF:
                return SHOWIF_EDEFAULT == null ? sHOWIF != null : !SHOWIF_EDEFAULT.equals(sHOWIF);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (fIELD: "); //$NON-NLS-1$
        result.append(fIELD);
        result.append(", nAME: "); //$NON-NLS-1$
        result.append(nAME);
        result.append(", nBLINES: "); //$NON-NLS-1$
        if (nBLINESESet) result.append(nBLINES); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", nOTSHOWIF: "); //$NON-NLS-1$
        result.append(nOTSHOWIF);
        result.append(", nUMROW: "); //$NON-NLS-1$
        if (nUMROWESet) result.append(nUMROW); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", rEADONLY: "); //$NON-NLS-1$
        if (rEADONLYESet) result.append(rEADONLY); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", rEPOSITORYVALUE: "); //$NON-NLS-1$
        result.append(rEPOSITORYVALUE);
        result.append(", rEQUIRED: "); //$NON-NLS-1$
        if (rEQUIREDESet) result.append(rEQUIRED); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", sHOW: "); //$NON-NLS-1$
        if (sHOWESet) result.append(sHOW); else result.append("<unset>"); //$NON-NLS-1$
        result.append(", sHOWIF: "); //$NON-NLS-1$
        result.append(sHOWIF);
        result.append(')');
        return result.toString();
    }

} //PARAMETERTypeImpl