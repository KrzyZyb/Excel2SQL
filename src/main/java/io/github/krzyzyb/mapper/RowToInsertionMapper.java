package io.github.krzyzyb.mapper;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class RowToInsertionMapper {
  public static String INSERT = "INSERT INTO";
  String TABLE_NAME = "";
  String SPACE = "";
  String DOUBLE_QUOTE = "\"";

  String TABLE_STRUCTURE = "()";
  StringBuilder builder = new StringBuilder();

  String[] of(List<Row> rows){
    String[] lines = new String[rows.size()];

    for(int i = 0; i < rows.size(); i++){
      Cell DEUTSCHFEHLERORT = rows.get(i).getCell(0);
      Cell SAECODEHEX = rows.get(i).getCell(1);
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

//  INSERT INTO PRIVILEGE (ID, NAME, TYPE)
//  VALUES (98, 'CREATE_WORKING_VERSION_OF_DO', 'PROJECT');

//  UPDATE PRIVILEGE
//  SET NAME = 'EDIT_PROJECT_GLOBAL_INFORMATION'
//  WHERE NAME = 'EDIT_PROJECT_PROPERTIES';

}
