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
package org.talend.dq.javasql;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.talend.commons.emf.EMFUtil;
import org.talend.cwm.management.connection.DatabaseContentRetriever;
import org.talend.dataquality.indicators.FrequencyIndicator;
import org.talend.dataquality.indicators.Indicator;
import org.talend.dataquality.indicators.IndicatorsFactory;
import org.talend.dataquality.indicators.IndicatorsPackage;
import org.talend.dataquality.indicators.IntegerSumIndicator;
import org.talend.dataquality.indicators.MedianIndicator;
import org.talend.dataquality.indicators.impl.IntegerSumIndicatorImpl;
import org.talend.dataquality.indicators.util.IndicatorsSwitch;
import org.talend.utils.collections.MultiMapHelper;
import org.talend.utils.properties.PropertiesLoader;
import org.talend.utils.properties.TypedProperties;
import org.talend.utils.sql.ConnectionUtils;
import org.talend.utils.sql.ResultSetUtils;
import orgomg.cwm.foundation.datatypes.DatatypesFactory;
import orgomg.cwm.foundation.datatypes.QueryExpression;
import orgomg.cwm.resource.relational.QueryColumnSet;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class IndicatorEvaluator {

    private static final boolean COMPUTE = true;

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
            final int fetchSize = 100; // TODO scorreia add this as a parameter.

            Connection connection = ConnectionUtils.createConnection(dbUrl, driverClassName, connectionParams);
            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            statement.setFetchSize(fetchSize);

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

            // --- create indicators

            MedianIndicator medianIndicator = IndicatorsFactory.eINSTANCE.createMedianIndicator();
            FrequencyIndicator textFrequencyIndicator = IndicatorsFactory.eINSTANCE.createFrequencyIndicator();

            // storeIndicator(columnsArray[0], IndicatorsFactory.eINSTANCE.createFrequencyIndicator());
            // storeIndicator(columnsArray[0], medianIndicator);
            // storeIndicator(columnsArray[1], IndicatorsFactory.eINSTANCE.createDoubleMeanIndicator());
            // storeIndicator(columnsArray[2], IndicatorsFactory.eINSTANCE.createBlankCountIndicator());
            storeIndicator(columnsArray[2], textFrequencyIndicator);
            // storeIndicator(columnsArray[3], IndicatorsFactory.eINSTANCE.createRowCountIndicator());
            // storeIndicator(columnsArray[5], IndicatorsFactory.eINSTANCE.createIntegerSumIndicator());
            // storeIndicator(columnsArray[5], IndicatorsFactory.eINSTANCE.createIntegerMeanIndicator());

            // build query on columns
            // TODO scorreia add filter somewhere here...
            String selectCols = sqlSelectColumns(database, tableName, columns);
            statement.execute(selectCols);

            ResultSet resultSet = statement.getResultSet();

            // --- create a description of the column set
            QueryExpression queryExpression = DatatypesFactory.eINSTANCE.createQueryExpression();
            queryExpression.setBody(selectCols);
            queryExpression.setLanguage("SQL"); // TODO scorreia externalize this as a constant

            if (COMPUTE) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                QueryColumnSet columnSet = DatabaseContentRetriever.getQueryColumnSet(metaData);

                int columnCount = metaData.getColumnCount();

                assert columnCount == columns.size();

                // --- fill an array of SQL types
                // int[] types = new int[columnCount];
                // for (int i = 0; i < columnCount; i++) {
                // types[i] = metaData.getColumnType(i);
                // }

                while (resultSet.next()) {

                    // --- for each column
                    for (int i = 0; i < columnCount; i++) {
                        // get indicator for this column
                        String col = columns.get(i);
                        List<Indicator> indicators = getIndicators(col);

                        // --- get content of column
                        Calendar cal = Calendar.getInstance();
                        // boolean isDate = col.equals(col3);
                        boolean isDate = false;
                        // TODO scorreia see how to better handle default dates
                        Object object = isDate ? getDate(resultSet, col, cal) : resultSet.getObject(col);

                        // --- give it to indicators
                        for (Indicator indicator : indicators) {
                            handle(indicator, object);
                        }
                    }

                }

                // Print indicators the median
                System.out.println("Median=" + medianIndicator.getMedian());
                System.out.println("# Unique values= " + textFrequencyIndicator.getUniqueValueCount());
                System.out.println("# Distinct values= " + textFrequencyIndicator.getDistinctValueCount());

                for (String col : columns) {
                    printIndicators(getIndicators(col));
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

            } else {
                ResultSetUtils.printResultSet(resultSet, 13);
            }

            // --- single statement ---
            boolean singleStatement = false;
            if (singleStatement) {
                String sql = sqlDistinctValuesCountStatement(database, tableName, "my_int");
                statement.execute(sql);
                resultSet = statement.getResultSet();
                ResultSetUtils.printResultSet(resultSet, 60);
            }

            // --- release resultset
            resultSet.close();
            // --- close
            connection.close();

            boolean trace = false;
            if (trace) {
                System.out.println(sqlRowCountStatement(database, tableName));
                System.out.println(sqlDistinctValuesCountStatement(database, tableName, "my_int"));
                System.out.println(sqlNullCountStatement(database, tableName, "my_int"));
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
     * DOC scorreia Comment method "getDate".
     * 
     * @param resultSet
     * @param col
     * @param cal
     * @return
     * @throws SQLException
     */
    private static Date getDate(ResultSet resultSet, String col, Calendar cal) throws SQLException {
        return resultSet.getDate(col, cal);
    }

    private static boolean storeIndicator(String columnName, Indicator indicator) {
        return MultiMapHelper.addUniqueObjectToListMap(columnName, indicator, columnToIndicators);
    }

    static Map<String, List<Indicator>> columnToIndicators = new HashMap<String, List<Indicator>>();

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

    private static final IndicatorsSwitch<IntegerSumIndicator> MY_SWITCH = new IndicatorsSwitch<IntegerSumIndicator>() {

        @Override
        public IntegerSumIndicator caseIntegerSumIndicator(IntegerSumIndicator object) {
            return object;
        }

    };

    /**
     * DOC scorreia Comment method "handle".
     * 
     * @param indicator
     * @param object
     */
    private static boolean handle(Indicator indicator, Object object) {
        // TODO Auto-generated method stub
        return indicator.handle(object);

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

    private static final char SEP = ',';

    /**
     * DOC scorreia Comment method "getIndicators".
     * 
     * @param columnName
     * @return
     */
    private static List<Indicator> getIndicators(String columnName) {
        List<Indicator> indics = columnToIndicators.get(columnName);
        return (indics != null) ? indics : new ArrayList<Indicator>();
    }

    // long countDistinctValues(){}
    static String sqlSelectAll(String databaseName, String tableName) {
        return "select * from " + databaseName + DOT + tableName;
    }

    static String sqlRowCountStatement(String databaseName, String tableName) {
        return "select count(*) from " + databaseName + DOT + tableName;
    }

    static String sqlDistinctValuesCountStatement(String databaseName, String tableName, String columnName) {
        return "select count(distinct " + columnName + ") from " + databaseName + DOT + tableName;
    }

    static String sqlNullCountStatement(String databaseName, String tableName, String columnName) {
        return sqlRowCountStatement(databaseName, tableName) + " where " + columnName + " IS NULL";
    }

    static String sqlBlankCountStatement(String databaseName, String tableName, String columnName) {
        return sqlRowCountStatement(databaseName, tableName) + " where " + columnName + " IS NULL";
    }

    private static final char DOT = '.';

    private static final char EOR = ';';
}
