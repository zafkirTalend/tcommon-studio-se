// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dq.indicators;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.talend.commons.emf.EMFUtil;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsFactory;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.IntegerSumIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl;
import org.talend.dataquality.indicators.util.IndicatorsSwitch;
import org.talend.dq.indicators.IndicatorEvaluator;
import org.talend.utils.properties.PropertiesLoader;
import org.talend.utils.properties.TypedProperties;
import org.talend.utils.sql.ConnectionUtils;
import orgomg.cwm.foundation.datatypes.DatatypesFactory;
import orgomg.cwm.foundation.datatypes.QueryExpression;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class IndicatorEvaluationMain {

    private static final char SEP = ',';

    private static final IndicatorsSwitch<IntegerSumIndicator> MY_SWITCH = new IndicatorsSwitch<IntegerSumIndicator>() {

        @Override
        public IntegerSumIndicator caseIntegerSumIndicator(IntegerSumIndicator object) {
            return object;
        }
    };

    private static final char DOT = '.';

    /**
     * DOC scorreia Comment method "main".
     * 
     * @param args
     */
    public static void main(String[] args) {

        TypedProperties connectionParams = PropertiesLoader.getProperties(IndicatorEvaluator.class, "db.properties");
        String driverClassName = connectionParams.getProperty("driver");
        String dbUrl = connectionParams.getProperty("url");
        try {
            // create connection
            Connection connection = ConnectionUtils.createConnection(dbUrl, driverClassName, connectionParams);

            String database = "test";
            String tableName = "my_test";

            // --- columns to analyze
            String[] columnsArray = new String[] { "my_int" // 0
                    , "my_double" // 1
                    , "my_text" // 2
                    , "my_string" // 3
                    , "my_date" // 4
                    , "my_int_null" // 5
            };

            List<String> columns = Arrays.asList(columnsArray);

            Evaluator evaluator = new IndicatorEvaluator();
            evaluator.setConnection(connection);

            // --- create indicators

            MedianIndicator medianIndicator = IndicatorsFactory.eINSTANCE.createMedianIndicator();
            FrequencyIndicator textFrequencyIndicator = IndicatorsFactory.eINSTANCE.createFrequencyIndicator();

            // storeIndicator(columnsArray[0], IndicatorsFactory.eINSTANCE.createFrequencyIndicator());
            // storeIndicator(columnsArray[0], medianIndicator);
            // storeIndicator(columnsArray[1], IndicatorsFactory.eINSTANCE.createDoubleMeanIndicator());
            // storeIndicator(columnsArray[2], IndicatorsFactory.eINSTANCE.createBlankCountIndicator());
            evaluator.storeIndicator(columnsArray[2], textFrequencyIndicator);
            // storeIndicator(columnsArray[3], IndicatorsFactory.eINSTANCE.createRowCountIndicator());
            // storeIndicator(columnsArray[5], IndicatorsFactory.eINSTANCE.createIntegerSumIndicator());
            // storeIndicator(columnsArray[5], IndicatorsFactory.eINSTANCE.createIntegerMeanIndicator());

            // build query on columns
            // TODO scorreia add filter somewhere here...
            String selectCols = sqlSelectColumns(database, tableName, columns);

            // --- create a description of the column set
            QueryExpression queryExpression = DatatypesFactory.eINSTANCE.createQueryExpression();
            queryExpression.setBody(selectCols);
            queryExpression.setLanguage("SQL"); // TODO scorreia externalize this as a constant

            evaluator.setFetchSize(100);
            evaluator.evaluateIndicators(selectCols);

            // Print indicators the median
            System.out.println("Median=" + medianIndicator.getMedian());
            System.out.println("# Unique values= " + textFrequencyIndicator.getUniqueValueCount());
            System.out.println("# Distinct values= " + textFrequencyIndicator.getDistinctValueCount());

            for (String col : columns) {
                printIndicators(evaluator.getIndicators(col));
            }

            // store in file
            File file = new File("out/myi." + IndicatorsPackage.eNAME);
            EMFUtil util = new EMFUtil();
            if (!util.addPoolToResourceSet(file.toURI().toString(), textFrequencyIndicator)) {
                System.err.println(util.getLastErrorMessage());
            }
            util.save();

            // test reload this file
            ResourceSet rs = new ResourceSetImpl();
            Resource r = rs.getResource(URI.createFileURI(file.getAbsolutePath()), true);

            EList<EObject> contents = r.getContents();
            if (contents.isEmpty()) {
                System.err.println("No content in " + r);
            }
            IndicatorsSwitch<FrequencyIndicator> mySwitch = new IndicatorsSwitch<FrequencyIndicator>() {

                @Override
                public FrequencyIndicator caseFrequencyIndicator(FrequencyIndicator object) {
                    return object;
                }

            };
            for (EObject object : contents) {
                FrequencyIndicator freqI = mySwitch.doSwitch(object);
                if (freqI != null) {
                    int uniqueValueCount = freqI.getUniqueValueCount();
                    System.out.println("nb unique values = " + uniqueValueCount);
                    EList<Object> uniqueValues = freqI.getUniqueValues();
                    for (Object data : uniqueValues) {
                        System.out.println("unique value= " + data);
                    }
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * DOC scorreia Comment method "printIndicators".
     * 
     * @param indicators
     */
    private static void printIndicators(List<Indicator> indicators) {
        for (Indicator indicator : indicators) {
            System.out.println(indicator);
            Object intIndic = MY_SWITCH.doSwitch(indicator);
            if (intIndic != null) {
                System.out.println(((IntegerSumIndicatorImpl) intIndic).getSum());
            }
        }
    }

    /**
     * DOC scorreia Comment method "sqlSelectColumns".
     * 
     * @param database
     * @param tableName
     * @param columns
     * @return
     */
    private static String sqlSelectColumns(String database, String tableName, List<String> columns) {
        StringBuffer buf = new StringBuffer();
        buf.append("select ");
        final int size = columns.size();
        int i = 0;
        for (String col : columns) {
            i++;
            buf.append(col);
            if (i != size) {
                buf.append(SEP);
            } else {
                buf.append(" from ");
            }
        }
        buf.append(database);
        buf.append(DOT);
        buf.append(tableName);
        return buf.toString();
    }

    static String sqlDistinctValuesCountStatement(String databaseName, String tableName, String columnName) {
        return "select count(distinct " + columnName + ") from " + databaseName + DOT + tableName;
    }

}
