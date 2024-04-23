package io.github.krzyzyb.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class RowMapper {
  public static String INSERT = "INSERT INTO";
  String TABLE_NAME = "";
  String SPACE = "";
  String DOUBLE_QUOTE = "\"";
  String TABLE_STRUCTURE = "()";
  StringBuilder builder = new StringBuilder();

  String[] of(List<Row> rows){
    String[] lines = new String[rows.size()];

    for(int i = 0; i < rows.size(); i++){
      String line = builder
          .append(DOUBLE_QUOTE)
          .append(INSERT)
          .append(SPACE)
          .append(TABLE_NAME)
          .append(DOUBLE_QUOTE)
          .toString();
      lines[i] = line;
    }
    return lines;
  }
}
