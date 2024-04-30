package io.github.krzyzyb.writer;

import java.util.List;

/**
 * Configuration class for custom processing method of Xls2SqlProcessor. Output file created by the processor is customised
 * with provided data.
 * and content output with TableName and Column Names as provided in OutputFileConfig.
 *
 * @param columnNames Names of columns in proper order in which the data from xls rows will be inserted. Number of column
 *                    names provided must match the number of columns in xls file.
 * @param tableName Name of the table to which the data will be inserted.
 */
public record OutputFileConfig(List<String> columnNames, String tableName) {
}
