/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.talend.dataquality.reports;

import org.eclipse.emf.common.util.EList;
import orgomg.cwmx.analysis.informationreporting.Report;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Td Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.talend.dataquality.reports.TdReport#getPresentationParams <em>Presentation Params</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.talend.dataquality.reports.ReportsPackage#getTdReport()
 * @model
 * @generated
 */
public interface TdReport extends Report {

    /**
     * Returns the value of the '<em><b>Presentation Params</b></em>' containment reference list.
     * The list contents are of type {@link org.talend.dataquality.reports.PresentationParameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Presentation Params</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Presentation Params</em>' containment reference list.
     * @see org.talend.dataquality.reports.ReportsPackage#getTdReport_PresentationParams()
     * @model containment="true"
     * @generated
     */
    EList<PresentationParameter> getPresentationParams();
} // TdReport
