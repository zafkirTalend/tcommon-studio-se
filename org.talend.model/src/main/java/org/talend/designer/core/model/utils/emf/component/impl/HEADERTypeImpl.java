/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.designer.core.model.utils.emf.component.impl;

import java.math.BigDecimal;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.talend.designer.core.model.utils.emf.component.ComponentPackage;
import org.talend.designer.core.model.utils.emf.component.HEADERType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>HEADER Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getSIGNATURE <em>SIGNATURE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getAUTHOR <em>AUTHOR</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getCOMPATIBILITY <em>COMPATIBILITY</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#isDATAAUTOPROPAGATE <em>DATAAUTOPROPAGATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getEXTENSION <em>EXTENSION</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getICON16 <em>ICON16</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getICON24 <em>ICON24</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getICON32 <em>ICON32</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getPLATEFORM <em>PLATEFORM</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getRELEASEDATE <em>RELEASEDATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#isSCHEMAAUTOPROPAGATE <em>SCHEMAAUTOPROPAGATE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getSERIAL <em>SERIAL</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#isSTARTABLE <em>STARTABLE</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getSTATUS <em>STATUS</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#isTECHNICAL <em>TECHNICAL</em>}</li>
 *   <li>{@link org.talend.designer.core.model.utils.emf.component.impl.HEADERTypeImpl#getVERSION <em>VERSION</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HEADERTypeImpl extends EObjectImpl implements HEADERType {
    /**
     * The default value of the '{@link #getSIGNATURE() <em>SIGNATURE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSIGNATURE()
     * @generated
     * @ordered
     */
    protected static final String SIGNATURE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSIGNATURE() <em>SIGNATURE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSIGNATURE()
     * @generated
     * @ordered
     */
    protected String sIGNATURE = SIGNATURE_EDEFAULT;

    /**
     * The default value of the '{@link #getAUTHOR() <em>AUTHOR</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAUTHOR()
     * @generated
     * @ordered
     */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAUTHOR() <em>AUTHOR</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAUTHOR()
     * @generated
     * @ordered
     */
    protected String aUTHOR = AUTHOR_EDEFAULT;

    /**
     * The default value of the '{@link #getCOMPATIBILITY() <em>COMPATIBILITY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCOMPATIBILITY()
     * @generated
     * @ordered
     */
    protected static final String COMPATIBILITY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCOMPATIBILITY() <em>COMPATIBILITY</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCOMPATIBILITY()
     * @generated
     * @ordered
     */
    protected String cOMPATIBILITY = COMPATIBILITY_EDEFAULT;

    /**
     * The default value of the '{@link #isDATAAUTOPROPAGATE() <em>DATAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDATAAUTOPROPAGATE()
     * @generated
     * @ordered
     */
    protected static final boolean DATAAUTOPROPAGATE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isDATAAUTOPROPAGATE() <em>DATAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDATAAUTOPROPAGATE()
     * @generated
     * @ordered
     */
    protected boolean dATAAUTOPROPAGATE = DATAAUTOPROPAGATE_EDEFAULT;

    /**
     * This is true if the DATAAUTOPROPAGATE attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean dATAAUTOPROPAGATEESet = false;

    /**
     * The default value of the '{@link #getEXTENSION() <em>EXTENSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEXTENSION()
     * @generated
     * @ordered
     */
    protected static final String EXTENSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEXTENSION() <em>EXTENSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEXTENSION()
     * @generated
     * @ordered
     */
    protected String eXTENSION = EXTENSION_EDEFAULT;

    /**
     * The default value of the '{@link #getICON16() <em>ICON16</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON16()
     * @generated
     * @ordered
     */
    protected static final String ICON16_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getICON16() <em>ICON16</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON16()
     * @generated
     * @ordered
     */
    protected String iCON16 = ICON16_EDEFAULT;

    /**
     * The default value of the '{@link #getICON24() <em>ICON24</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON24()
     * @generated
     * @ordered
     */
    protected static final String ICON24_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getICON24() <em>ICON24</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON24()
     * @generated
     * @ordered
     */
    protected String iCON24 = ICON24_EDEFAULT;

    /**
     * The default value of the '{@link #getICON32() <em>ICON32</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON32()
     * @generated
     * @ordered
     */
    protected static final String ICON32_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getICON32() <em>ICON32</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getICON32()
     * @generated
     * @ordered
     */
    protected String iCON32 = ICON32_EDEFAULT;

    /**
     * The default value of the '{@link #getPLATEFORM() <em>PLATEFORM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPLATEFORM()
     * @generated
     * @ordered
     */
    protected static final String PLATEFORM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPLATEFORM() <em>PLATEFORM</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPLATEFORM()
     * @generated
     * @ordered
     */
    protected String pLATEFORM = PLATEFORM_EDEFAULT;

    /**
     * The default value of the '{@link #getRELEASEDATE() <em>RELEASEDATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRELEASEDATE()
     * @generated
     * @ordered
     */
    protected static final String RELEASEDATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRELEASEDATE() <em>RELEASEDATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRELEASEDATE()
     * @generated
     * @ordered
     */
    protected String rELEASEDATE = RELEASEDATE_EDEFAULT;

    /**
     * The default value of the '{@link #isSCHEMAAUTOPROPAGATE() <em>SCHEMAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSCHEMAAUTOPROPAGATE()
     * @generated
     * @ordered
     */
    protected static final boolean SCHEMAAUTOPROPAGATE_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isSCHEMAAUTOPROPAGATE() <em>SCHEMAAUTOPROPAGATE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSCHEMAAUTOPROPAGATE()
     * @generated
     * @ordered
     */
    protected boolean sCHEMAAUTOPROPAGATE = SCHEMAAUTOPROPAGATE_EDEFAULT;

    /**
     * This is true if the SCHEMAAUTOPROPAGATE attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean sCHEMAAUTOPROPAGATEESet = false;

    /**
     * The default value of the '{@link #getSERIAL() <em>SERIAL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSERIAL()
     * @generated
     * @ordered
     */
    protected static final String SERIAL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSERIAL() <em>SERIAL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSERIAL()
     * @generated
     * @ordered
     */
    protected String sERIAL = SERIAL_EDEFAULT;

    /**
     * The default value of the '{@link #isSTARTABLE() <em>STARTABLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSTARTABLE()
     * @generated
     * @ordered
     */
    protected static final boolean STARTABLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSTARTABLE() <em>STARTABLE</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSTARTABLE()
     * @generated
     * @ordered
     */
    protected boolean sTARTABLE = STARTABLE_EDEFAULT;

    /**
     * This is true if the STARTABLE attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean sTARTABLEESet = false;

    /**
     * The default value of the '{@link #getSTATUS() <em>STATUS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSTATUS()
     * @generated
     * @ordered
     */
    protected static final String STATUS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSTATUS() <em>STATUS</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSTATUS()
     * @generated
     * @ordered
     */
    protected String sTATUS = STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #isTECHNICAL() <em>TECHNICAL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTECHNICAL()
     * @generated
     * @ordered
     */
    protected static final boolean TECHNICAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTECHNICAL() <em>TECHNICAL</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTECHNICAL()
     * @generated
     * @ordered
     */
    protected boolean tECHNICAL = TECHNICAL_EDEFAULT;

    /**
     * This is true if the TECHNICAL attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean tECHNICALESet = false;

    /**
     * The default value of the '{@link #getVERSION() <em>VERSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVERSION()
     * @generated
     * @ordered
     */
    protected static final BigDecimal VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVERSION() <em>VERSION</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVERSION()
     * @generated
     * @ordered
     */
    protected BigDecimal vERSION = VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected HEADERTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ComponentPackage.Literals.HEADER_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSIGNATURE() {
        return sIGNATURE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSIGNATURE(String newSIGNATURE) {
        String oldSIGNATURE = sIGNATURE;
        sIGNATURE = newSIGNATURE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__SIGNATURE, oldSIGNATURE, sIGNATURE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAUTHOR() {
        return aUTHOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAUTHOR(String newAUTHOR) {
        String oldAUTHOR = aUTHOR;
        aUTHOR = newAUTHOR;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__AUTHOR, oldAUTHOR, aUTHOR));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCOMPATIBILITY() {
        return cOMPATIBILITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCOMPATIBILITY(String newCOMPATIBILITY) {
        String oldCOMPATIBILITY = cOMPATIBILITY;
        cOMPATIBILITY = newCOMPATIBILITY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__COMPATIBILITY, oldCOMPATIBILITY, cOMPATIBILITY));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDATAAUTOPROPAGATE() {
        return dATAAUTOPROPAGATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDATAAUTOPROPAGATE(boolean newDATAAUTOPROPAGATE) {
        boolean oldDATAAUTOPROPAGATE = dATAAUTOPROPAGATE;
        dATAAUTOPROPAGATE = newDATAAUTOPROPAGATE;
        boolean oldDATAAUTOPROPAGATEESet = dATAAUTOPROPAGATEESet;
        dATAAUTOPROPAGATEESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE, oldDATAAUTOPROPAGATE, dATAAUTOPROPAGATE, !oldDATAAUTOPROPAGATEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetDATAAUTOPROPAGATE() {
        boolean oldDATAAUTOPROPAGATE = dATAAUTOPROPAGATE;
        boolean oldDATAAUTOPROPAGATEESet = dATAAUTOPROPAGATEESet;
        dATAAUTOPROPAGATE = DATAAUTOPROPAGATE_EDEFAULT;
        dATAAUTOPROPAGATEESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE, oldDATAAUTOPROPAGATE, DATAAUTOPROPAGATE_EDEFAULT, oldDATAAUTOPROPAGATEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetDATAAUTOPROPAGATE() {
        return dATAAUTOPROPAGATEESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEXTENSION() {
        return eXTENSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEXTENSION(String newEXTENSION) {
        String oldEXTENSION = eXTENSION;
        eXTENSION = newEXTENSION;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__EXTENSION, oldEXTENSION, eXTENSION));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getICON32() {
        return iCON32;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setICON32(String newICON32) {
        String oldICON32 = iCON32;
        iCON32 = newICON32;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__ICON32, oldICON32, iCON32));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPLATEFORM() {
        return pLATEFORM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPLATEFORM(String newPLATEFORM) {
        String oldPLATEFORM = pLATEFORM;
        pLATEFORM = newPLATEFORM;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__PLATEFORM, oldPLATEFORM, pLATEFORM));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRELEASEDATE() {
        return rELEASEDATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRELEASEDATE(String newRELEASEDATE) {
        String oldRELEASEDATE = rELEASEDATE;
        rELEASEDATE = newRELEASEDATE;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__RELEASEDATE, oldRELEASEDATE, rELEASEDATE));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSCHEMAAUTOPROPAGATE() {
        return sCHEMAAUTOPROPAGATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSCHEMAAUTOPROPAGATE(boolean newSCHEMAAUTOPROPAGATE) {
        boolean oldSCHEMAAUTOPROPAGATE = sCHEMAAUTOPROPAGATE;
        sCHEMAAUTOPROPAGATE = newSCHEMAAUTOPROPAGATE;
        boolean oldSCHEMAAUTOPROPAGATEESet = sCHEMAAUTOPROPAGATEESet;
        sCHEMAAUTOPROPAGATEESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE, oldSCHEMAAUTOPROPAGATE, sCHEMAAUTOPROPAGATE, !oldSCHEMAAUTOPROPAGATEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSCHEMAAUTOPROPAGATE() {
        boolean oldSCHEMAAUTOPROPAGATE = sCHEMAAUTOPROPAGATE;
        boolean oldSCHEMAAUTOPROPAGATEESet = sCHEMAAUTOPROPAGATEESet;
        sCHEMAAUTOPROPAGATE = SCHEMAAUTOPROPAGATE_EDEFAULT;
        sCHEMAAUTOPROPAGATEESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE, oldSCHEMAAUTOPROPAGATE, SCHEMAAUTOPROPAGATE_EDEFAULT, oldSCHEMAAUTOPROPAGATEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSCHEMAAUTOPROPAGATE() {
        return sCHEMAAUTOPROPAGATEESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSERIAL() {
        return sERIAL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSERIAL(String newSERIAL) {
        String oldSERIAL = sERIAL;
        sERIAL = newSERIAL;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__SERIAL, oldSERIAL, sERIAL));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSTARTABLE() {
        return sTARTABLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSTARTABLE(boolean newSTARTABLE) {
        boolean oldSTARTABLE = sTARTABLE;
        sTARTABLE = newSTARTABLE;
        boolean oldSTARTABLEESet = sTARTABLEESet;
        sTARTABLEESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__STARTABLE, oldSTARTABLE, sTARTABLE, !oldSTARTABLEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetSTARTABLE() {
        boolean oldSTARTABLE = sTARTABLE;
        boolean oldSTARTABLEESet = sTARTABLEESet;
        sTARTABLE = STARTABLE_EDEFAULT;
        sTARTABLEESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.HEADER_TYPE__STARTABLE, oldSTARTABLE, STARTABLE_EDEFAULT, oldSTARTABLEESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetSTARTABLE() {
        return sTARTABLEESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSTATUS() {
        return sTATUS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSTATUS(String newSTATUS) {
        String oldSTATUS = sTATUS;
        sTATUS = newSTATUS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__STATUS, oldSTATUS, sTATUS));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTECHNICAL() {
        return tECHNICAL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTECHNICAL(boolean newTECHNICAL) {
        boolean oldTECHNICAL = tECHNICAL;
        tECHNICAL = newTECHNICAL;
        boolean oldTECHNICALESet = tECHNICALESet;
        tECHNICALESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__TECHNICAL, oldTECHNICAL, tECHNICAL, !oldTECHNICALESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTECHNICAL() {
        boolean oldTECHNICAL = tECHNICAL;
        boolean oldTECHNICALESet = tECHNICALESet;
        tECHNICAL = TECHNICAL_EDEFAULT;
        tECHNICALESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ComponentPackage.HEADER_TYPE__TECHNICAL, oldTECHNICAL, TECHNICAL_EDEFAULT, oldTECHNICALESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTECHNICAL() {
        return tECHNICALESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigDecimal getVERSION() {
        return vERSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVERSION(BigDecimal newVERSION) {
        BigDecimal oldVERSION = vERSION;
        vERSION = newVERSION;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__VERSION, oldVERSION, vERSION));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getICON24() {
        return iCON24;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setICON24(String newICON24) {
        String oldICON24 = iCON24;
        iCON24 = newICON24;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__ICON24, oldICON24, iCON24));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getICON16() {
        return iCON16;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setICON16(String newICON16) {
        String oldICON16 = iCON16;
        iCON16 = newICON16;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.HEADER_TYPE__ICON16, oldICON16, iCON16));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ComponentPackage.HEADER_TYPE__SIGNATURE:
                return getSIGNATURE();
            case ComponentPackage.HEADER_TYPE__AUTHOR:
                return getAUTHOR();
            case ComponentPackage.HEADER_TYPE__COMPATIBILITY:
                return getCOMPATIBILITY();
            case ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE:
                return isDATAAUTOPROPAGATE() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.HEADER_TYPE__EXTENSION:
                return getEXTENSION();
            case ComponentPackage.HEADER_TYPE__ICON16:
                return getICON16();
            case ComponentPackage.HEADER_TYPE__ICON24:
                return getICON24();
            case ComponentPackage.HEADER_TYPE__ICON32:
                return getICON32();
            case ComponentPackage.HEADER_TYPE__PLATEFORM:
                return getPLATEFORM();
            case ComponentPackage.HEADER_TYPE__RELEASEDATE:
                return getRELEASEDATE();
            case ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE:
                return isSCHEMAAUTOPROPAGATE() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.HEADER_TYPE__SERIAL:
                return getSERIAL();
            case ComponentPackage.HEADER_TYPE__STARTABLE:
                return isSTARTABLE() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.HEADER_TYPE__STATUS:
                return getSTATUS();
            case ComponentPackage.HEADER_TYPE__TECHNICAL:
                return isTECHNICAL() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentPackage.HEADER_TYPE__VERSION:
                return getVERSION();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentPackage.HEADER_TYPE__SIGNATURE:
                setSIGNATURE((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__AUTHOR:
                setAUTHOR((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__COMPATIBILITY:
                setCOMPATIBILITY((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE:
                setDATAAUTOPROPAGATE(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.HEADER_TYPE__EXTENSION:
                setEXTENSION((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__ICON16:
                setICON16((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__ICON24:
                setICON24((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__ICON32:
                setICON32((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__PLATEFORM:
                setPLATEFORM((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__RELEASEDATE:
                setRELEASEDATE((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE:
                setSCHEMAAUTOPROPAGATE(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.HEADER_TYPE__SERIAL:
                setSERIAL((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__STARTABLE:
                setSTARTABLE(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.HEADER_TYPE__STATUS:
                setSTATUS((String)newValue);
                return;
            case ComponentPackage.HEADER_TYPE__TECHNICAL:
                setTECHNICAL(((Boolean)newValue).booleanValue());
                return;
            case ComponentPackage.HEADER_TYPE__VERSION:
                setVERSION((BigDecimal)newValue);
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
            case ComponentPackage.HEADER_TYPE__SIGNATURE:
                setSIGNATURE(SIGNATURE_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__AUTHOR:
                setAUTHOR(AUTHOR_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__COMPATIBILITY:
                setCOMPATIBILITY(COMPATIBILITY_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE:
                unsetDATAAUTOPROPAGATE();
                return;
            case ComponentPackage.HEADER_TYPE__EXTENSION:
                setEXTENSION(EXTENSION_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__ICON16:
                setICON16(ICON16_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__ICON24:
                setICON24(ICON24_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__ICON32:
                setICON32(ICON32_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__PLATEFORM:
                setPLATEFORM(PLATEFORM_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__RELEASEDATE:
                setRELEASEDATE(RELEASEDATE_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE:
                unsetSCHEMAAUTOPROPAGATE();
                return;
            case ComponentPackage.HEADER_TYPE__SERIAL:
                setSERIAL(SERIAL_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__STARTABLE:
                unsetSTARTABLE();
                return;
            case ComponentPackage.HEADER_TYPE__STATUS:
                setSTATUS(STATUS_EDEFAULT);
                return;
            case ComponentPackage.HEADER_TYPE__TECHNICAL:
                unsetTECHNICAL();
                return;
            case ComponentPackage.HEADER_TYPE__VERSION:
                setVERSION(VERSION_EDEFAULT);
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
            case ComponentPackage.HEADER_TYPE__SIGNATURE:
                return SIGNATURE_EDEFAULT == null ? sIGNATURE != null : !SIGNATURE_EDEFAULT.equals(sIGNATURE);
            case ComponentPackage.HEADER_TYPE__AUTHOR:
                return AUTHOR_EDEFAULT == null ? aUTHOR != null : !AUTHOR_EDEFAULT.equals(aUTHOR);
            case ComponentPackage.HEADER_TYPE__COMPATIBILITY:
                return COMPATIBILITY_EDEFAULT == null ? cOMPATIBILITY != null : !COMPATIBILITY_EDEFAULT.equals(cOMPATIBILITY);
            case ComponentPackage.HEADER_TYPE__DATAAUTOPROPAGATE:
                return isSetDATAAUTOPROPAGATE();
            case ComponentPackage.HEADER_TYPE__EXTENSION:
                return EXTENSION_EDEFAULT == null ? eXTENSION != null : !EXTENSION_EDEFAULT.equals(eXTENSION);
            case ComponentPackage.HEADER_TYPE__ICON16:
                return ICON16_EDEFAULT == null ? iCON16 != null : !ICON16_EDEFAULT.equals(iCON16);
            case ComponentPackage.HEADER_TYPE__ICON24:
                return ICON24_EDEFAULT == null ? iCON24 != null : !ICON24_EDEFAULT.equals(iCON24);
            case ComponentPackage.HEADER_TYPE__ICON32:
                return ICON32_EDEFAULT == null ? iCON32 != null : !ICON32_EDEFAULT.equals(iCON32);
            case ComponentPackage.HEADER_TYPE__PLATEFORM:
                return PLATEFORM_EDEFAULT == null ? pLATEFORM != null : !PLATEFORM_EDEFAULT.equals(pLATEFORM);
            case ComponentPackage.HEADER_TYPE__RELEASEDATE:
                return RELEASEDATE_EDEFAULT == null ? rELEASEDATE != null : !RELEASEDATE_EDEFAULT.equals(rELEASEDATE);
            case ComponentPackage.HEADER_TYPE__SCHEMAAUTOPROPAGATE:
                return isSetSCHEMAAUTOPROPAGATE();
            case ComponentPackage.HEADER_TYPE__SERIAL:
                return SERIAL_EDEFAULT == null ? sERIAL != null : !SERIAL_EDEFAULT.equals(sERIAL);
            case ComponentPackage.HEADER_TYPE__STARTABLE:
                return isSetSTARTABLE();
            case ComponentPackage.HEADER_TYPE__STATUS:
                return STATUS_EDEFAULT == null ? sTATUS != null : !STATUS_EDEFAULT.equals(sTATUS);
            case ComponentPackage.HEADER_TYPE__TECHNICAL:
                return isSetTECHNICAL();
            case ComponentPackage.HEADER_TYPE__VERSION:
                return VERSION_EDEFAULT == null ? vERSION != null : !VERSION_EDEFAULT.equals(vERSION);
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
        result.append(" (sIGNATURE: ");
        result.append(sIGNATURE);
        result.append(", aUTHOR: ");
        result.append(aUTHOR);
        result.append(", cOMPATIBILITY: ");
        result.append(cOMPATIBILITY);
        result.append(", dATAAUTOPROPAGATE: ");
        if (dATAAUTOPROPAGATEESet) result.append(dATAAUTOPROPAGATE); else result.append("<unset>");
        result.append(", eXTENSION: ");
        result.append(eXTENSION);
        result.append(", iCON16: ");
        result.append(iCON16);
        result.append(", iCON24: ");
        result.append(iCON24);
        result.append(", iCON32: ");
        result.append(iCON32);
        result.append(", pLATEFORM: ");
        result.append(pLATEFORM);
        result.append(", rELEASEDATE: ");
        result.append(rELEASEDATE);
        result.append(", sCHEMAAUTOPROPAGATE: ");
        if (sCHEMAAUTOPROPAGATEESet) result.append(sCHEMAAUTOPROPAGATE); else result.append("<unset>");
        result.append(", sERIAL: ");
        result.append(sERIAL);
        result.append(", sTARTABLE: ");
        if (sTARTABLEESet) result.append(sTARTABLE); else result.append("<unset>");
        result.append(", sTATUS: ");
        result.append(sTATUS);
        result.append(", tECHNICAL: ");
        if (tECHNICALESet) result.append(tECHNICAL); else result.append("<unset>");
        result.append(", vERSION: ");
        result.append(vERSION);
        result.append(')');
        return result.toString();
    }

} //HEADERTypeImpl