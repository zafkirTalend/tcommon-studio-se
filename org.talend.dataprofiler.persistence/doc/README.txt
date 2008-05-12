TALEND_DQ.pdf: PDM schema for the talend_dq database.

Fact table: 
  - TDQ_INDICATOR_VALUE: the value of the indicator

Dimension tables:
  - TDQ_ANALYZED_ELEMENT: the analyzed element (mainly a column)
  - TDQ_ANALYSIS: the analysis instance in a report (meaning that the couple report and analysis the functional key)
  - TDQ_INDICATOR_DEFINITION: the indicator definition (Row count, Frequency table...)
  - TDQ_CALENDAR: the date dimension
  - TDQ_DAY_TIME: The day time dimension (hours, minutes)
  - TDQ_VALUES: the table listing the value when frequency table indicator is computed
  - TDQ_INDICATOR_OPTIONS: the options used by indicators
  - TDQ_INTERVAL: The intervals defined by the bins when aggregating data (for discrete analysis)

Detailed description:



TDQ_ANALYSIS
A row is a couple report, analysis at a given time.
Report are aggregations of analysis. 
A full report will be composed by several row (as many as the report contains analyses). 

  - AN_PK: Artificial primary key
  - AN_UUID: identifier of the analysis
  - AN_LABEL: the name of the analysis
  - AN_CREATION_DATE: the creation date of the analysis
  - AN_AUTHOR: the author of the analysis
  - AN_DATA_FILTER: the data filter of the analysis
  - AN_STATUS: the status of the analysis
  - REP_UUID: the identifier of the report
  - REP_CREATION_DATE: the creation date of the report
  - REP_LABEL: the name of the report
  - REP_AUTHOR: the author of the report
  - REP_STATUS: the status of the report
  - AN_BEGIN_DATE: the date when the row is created
  - AN_END_DATE: the date when the row is replaced by a new row.
  				 or for new rows, the far future default date.
  - AN_VERSION: the version number of the (report, analysis). 
  				must be incremented each time the couple's data change.
  
  
  
  
TDQ_INDICATOR_DEFINITION 
  - IND_PK: the primary key (artificial)
  - IND_LABEL: the label of the indicator
  - IND_TYPE: the data mining type 
  - IND_CATEGORY: the category 
  				  Simple indicators such as count indicators belong to "Simple Statistics" category, "Count" subcategory.
 				  Text indicators belong to "Text Statistics" category.
 				  Mean, Median, Lower quartile, Upper quartile, min value, max value belong to "Summary Statistics" category.
 				  Mode belong to "Advanced Statistics" category and "Mode" subcategory.
 				  Frequency table belong to "Advanced Statistics" category and "Frequencies" subcategory.
  - IND_SUBCATEGORY: the subcategory. see before.
then the 3 historization columns:
  - IND_BEGIN_DATE
  - IND_END_DATE
  - IND_VERSION  
  

TDQ_ANALYZED_ELEMENT
  - ELT_PK: the primary key (artificial)
  - ELT_UUID: the identifier of the analyzed element
  - ELT_CONNECTION_UUID: the identifier of the connection
  - ELT_CONNECTION_NAME: the name of the connection
  - ELT_CATALOG_NAME: The name of the catalog or the talend's default value corresponding to a null value (see SqlConstants.java)
  - ELT_SCHEMA_NAME: The name of the schema or the talend's default value corresponding to a null value (see SqlConstants.java)
  - ELT_TABLE_NAME: the name of the table or the talend's default value corresponding to a null value (see SqlConstants.java)
  - ELT_COLUMN_NAME: the name of the column or the talend's default value corresponding to a null value (see SqlConstants.java)
then the 3 historization columns:
  - ELT_BEGIN_DATE
  - ELT_END_DATE
  - ELT_VERSION
  
  
TDQ_DAY_TIME
  - TIME_PK: the primary key (artificial)
  - TIME_HOUR: the hour (0-23)
  - TIME_MINUTE: the minutes (0-59)
  
TDQ_VALUES
  - VAL_PK: the primary key (artificial)
  - VAL_STRING: the value (among the possible values in frequency tables)
  
  
TDQ_INTERVAL
  - INT_PK: the primary key (artificial)
  - INT_LOWER_REAL: the lower bound of an interval (with real numbers)
  - INT_UPPER_REAL: the upper bound of an interval (with real numbers)
  - INT_LOWER_INT: the lower bound of an interval (with integer numbers)
  - INT_UPPER_INT: the upper bound of an interval (with integer numbers)
  - INT_DATA_FLAG: a flag 'R' for real interval values, 'I' for integer values
  - INT_LABEL: the label of the interval (by default, it is simple "lower value, upper value"
  

TDQ_INDICATOR_OPTIONS
  - INO_PK: the primary key (artificial)
  - INO_CASE_SENSITIVE: case sensitive flag 'Y', 'N' 'U'
  - INO_REGEXP: regular expression
  - INO_COUNT_NULLS: flag for counting null values 'Y', 'N'
  - INO_COUNT_BLANKS : flag for counting blank values 'Y', 'N'
                
                
TDQ_CALENDAR
  - CAL_PK: the primary key
  - CAL_DATE 
  - CAL_DAY_IN_MONTH: the date
  - CAL_DAY_IN_YEAR: the day number in the year
  - CAL_LAST_WEEK_DAY:  the day number in year of the last day of the current week  
  - CAL_LAST_MONTH_DAY: the day number in year of the last day of the current month  
  - CAL_WEEK_DAY: the day number in the week
  - CAL_DAY_NAME: the name of the day
  - CAL_WEEK_NUM: the week number
  - CAL_MONTH: the month number
  - CAL_MONTH_NAME: the name of the month
  - CAL_YEAR: the year
  - CAL_MONTH_PERIOD: a string representing the period: (yyyymm) 
  - CAL_QUARTER: the quarter 1, 2, 3 or 4
  - CAL_QUARTER_PERIOD: the period (yyyyq)  where y is year, q is quarter among 1,2,3,4
  - CAL_SEMESTER: the semester (1 or 2)
  - CAL_DAY_OFF: a flag to indicate days-off
  - CAL_SPECIAL_DAY: a flag to indicate special dates. 'Y' is a special day, 'N' is not special.
  - CAL_SPEC_DAY_LABEL: the label of the special day
  
Some of the dates are pseudo-dates:
  - null date is represented by a specific date (whose CAL_DAY_NAME is the Talend's default null value) and the CAL_SPECIAL_DAY flag is set to Y

   
The fact table: 
TDQ_INDICATOR_VALUE 
  - INDV_PK: the primary key (artificial)
all foreign keys to the dimension tables
  - OPTION_PK
  - INTERVAL_PK
  - ANALYSIS_PK
  - ELEMENT_PK
  - INDICATOR_PK
  - TIME_PK
  - CALENDAR_PK
  - VALUE_PK

  - AN_DURATION: the duration analysis
  - INDV_REAL_VALUE: the real value of the indicator (or a default value when not applicable)
  - INDV_INT_VALUE: the integer value of the indicator (or a default value when not applicable)
  - INDV_ROW_COUNT: the number of rows when evaluating this indicator
  - INDV_VALUE_TYPE_INDICATOR: a flag to tell whether it is a real value indicator 'R', an integer value 'I' or an instance value 'V'.
  
  
  