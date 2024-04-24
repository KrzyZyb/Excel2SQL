package io.github.krzyzyb.writer;

import java.util.List;

public class OutputFileConfig {
  private final List<String> columnNames;
  private final String tableName;

  public OutputFileConfig(List<String> columnNames, String tableName) {
    this.columnNames = columnNames;
    this.tableName = tableName;
  }

  public List<String> getColumnNames() {
    return columnNames;
  }

  public String getTableName() {
    return tableName;
  }
}
