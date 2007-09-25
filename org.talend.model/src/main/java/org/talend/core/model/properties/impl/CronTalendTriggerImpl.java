/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.core.model.properties.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.talend.core.model.properties.CronTalendTrigger;
import org.talend.core.model.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cron Talend Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getDaysOfWeek <em>Days Of Week</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getDaysOfMonth <em>Days Of Month</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getMonths <em>Months</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getYears <em>Years</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getHours <em>Hours</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getMinutes <em>Minutes</em>}</li>
 *   <li>{@link org.talend.core.model.properties.impl.CronTalendTriggerImpl#getSeconds <em>Seconds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CronTalendTriggerImpl extends TalendTriggerImpl implements CronTalendTrigger {
    /**
     * The default value of the '{@link #getDaysOfWeek() <em>Days Of Week</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDaysOfWeek()
     * @generated
     * @ordered
     */
    protected static final String DAYS_OF_WEEK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDaysOfWeek() <em>Days Of Week</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDaysOfWeek()
     * @generated
     * @ordered
     */
    protected String daysOfWeek = DAYS_OF_WEEK_EDEFAULT;

    /**
     * The default value of the '{@link #getDaysOfMonth() <em>Days Of Month</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDaysOfMonth()
     * @generated
     * @ordered
     */
    protected static final String DAYS_OF_MONTH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDaysOfMonth() <em>Days Of Month</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDaysOfMonth()
     * @generated
     * @ordered
     */
    protected String daysOfMonth = DAYS_OF_MONTH_EDEFAULT;

    /**
     * The default value of the '{@link #getMonths() <em>Months</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMonths()
     * @generated
     * @ordered
     */
    protected static final String MONTHS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMonths() <em>Months</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMonths()
     * @generated
     * @ordered
     */
    protected String months = MONTHS_EDEFAULT;

    /**
     * The default value of the '{@link #getYears() <em>Years</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYears()
     * @generated
     * @ordered
     */
    protected static final String YEARS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getYears() <em>Years</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getYears()
     * @generated
     * @ordered
     */
    protected String years = YEARS_EDEFAULT;

    /**
     * The default value of the '{@link #getHours() <em>Hours</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHours()
     * @generated
     * @ordered
     */
    protected static final String HOURS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHours() <em>Hours</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHours()
     * @generated
     * @ordered
     */
    protected String hours = HOURS_EDEFAULT;

    /**
     * The default value of the '{@link #getMinutes() <em>Minutes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinutes()
     * @generated
     * @ordered
     */
    protected static final String MINUTES_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMinutes() <em>Minutes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinutes()
     * @generated
     * @ordered
     */
    protected String minutes = MINUTES_EDEFAULT;

    /**
     * The default value of the '{@link #getSeconds() <em>Seconds</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSeconds()
     * @generated
     * @ordered
     */
    protected static final String SECONDS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSeconds() <em>Seconds</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSeconds()
     * @generated
     * @ordered
     */
    protected String seconds = SECONDS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CronTalendTriggerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CRON_TALEND_TRIGGER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDaysOfWeek(String newDaysOfWeek) {
        String oldDaysOfWeek = daysOfWeek;
        daysOfWeek = newDaysOfWeek;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_WEEK, oldDaysOfWeek, daysOfWeek));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDaysOfMonth() {
        return daysOfMonth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDaysOfMonth(String newDaysOfMonth) {
        String oldDaysOfMonth = daysOfMonth;
        daysOfMonth = newDaysOfMonth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_MONTH, oldDaysOfMonth, daysOfMonth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMonths() {
        return months;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMonths(String newMonths) {
        String oldMonths = months;
        months = newMonths;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__MONTHS, oldMonths, months));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getYears() {
        return years;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setYears(String newYears) {
        String oldYears = years;
        years = newYears;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__YEARS, oldYears, years));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHours() {
        return hours;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHours(String newHours) {
        String oldHours = hours;
        hours = newHours;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__HOURS, oldHours, hours));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMinutes() {
        return minutes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinutes(String newMinutes) {
        String oldMinutes = minutes;
        minutes = newMinutes;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__MINUTES, oldMinutes, minutes));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSeconds() {
        return seconds;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSeconds(String newSeconds) {
        String oldSeconds = seconds;
        seconds = newSeconds;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CRON_TALEND_TRIGGER__SECONDS, oldSeconds, seconds));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_WEEK:
                return getDaysOfWeek();
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_MONTH:
                return getDaysOfMonth();
            case PropertiesPackage.CRON_TALEND_TRIGGER__MONTHS:
                return getMonths();
            case PropertiesPackage.CRON_TALEND_TRIGGER__YEARS:
                return getYears();
            case PropertiesPackage.CRON_TALEND_TRIGGER__HOURS:
                return getHours();
            case PropertiesPackage.CRON_TALEND_TRIGGER__MINUTES:
                return getMinutes();
            case PropertiesPackage.CRON_TALEND_TRIGGER__SECONDS:
                return getSeconds();
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
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_WEEK:
                setDaysOfWeek((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_MONTH:
                setDaysOfMonth((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__MONTHS:
                setMonths((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__YEARS:
                setYears((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__HOURS:
                setHours((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__MINUTES:
                setMinutes((String)newValue);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__SECONDS:
                setSeconds((String)newValue);
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
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_WEEK:
                setDaysOfWeek(DAYS_OF_WEEK_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_MONTH:
                setDaysOfMonth(DAYS_OF_MONTH_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__MONTHS:
                setMonths(MONTHS_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__YEARS:
                setYears(YEARS_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__HOURS:
                setHours(HOURS_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__MINUTES:
                setMinutes(MINUTES_EDEFAULT);
                return;
            case PropertiesPackage.CRON_TALEND_TRIGGER__SECONDS:
                setSeconds(SECONDS_EDEFAULT);
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
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_WEEK:
                return DAYS_OF_WEEK_EDEFAULT == null ? daysOfWeek != null : !DAYS_OF_WEEK_EDEFAULT.equals(daysOfWeek);
            case PropertiesPackage.CRON_TALEND_TRIGGER__DAYS_OF_MONTH:
                return DAYS_OF_MONTH_EDEFAULT == null ? daysOfMonth != null : !DAYS_OF_MONTH_EDEFAULT.equals(daysOfMonth);
            case PropertiesPackage.CRON_TALEND_TRIGGER__MONTHS:
                return MONTHS_EDEFAULT == null ? months != null : !MONTHS_EDEFAULT.equals(months);
            case PropertiesPackage.CRON_TALEND_TRIGGER__YEARS:
                return YEARS_EDEFAULT == null ? years != null : !YEARS_EDEFAULT.equals(years);
            case PropertiesPackage.CRON_TALEND_TRIGGER__HOURS:
                return HOURS_EDEFAULT == null ? hours != null : !HOURS_EDEFAULT.equals(hours);
            case PropertiesPackage.CRON_TALEND_TRIGGER__MINUTES:
                return MINUTES_EDEFAULT == null ? minutes != null : !MINUTES_EDEFAULT.equals(minutes);
            case PropertiesPackage.CRON_TALEND_TRIGGER__SECONDS:
                return SECONDS_EDEFAULT == null ? seconds != null : !SECONDS_EDEFAULT.equals(seconds);
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
        result.append(" (daysOfWeek: ");
        result.append(daysOfWeek);
        result.append(", daysOfMonth: ");
        result.append(daysOfMonth);
        result.append(", months: ");
        result.append(months);
        result.append(", years: ");
        result.append(years);
        result.append(", hours: ");
        result.append(hours);
        result.append(", minutes: ");
        result.append(minutes);
        result.append(", seconds: ");
        result.append(seconds);
        result.append(')');
        return result.toString();
    }

} //CronTalendTriggerImpl
