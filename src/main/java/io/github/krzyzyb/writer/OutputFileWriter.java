package io.github.krzyzyb.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class OutputFileWriter {
  public void writeRowsToFile(List<Row> rows) {
    try (FileWriter writer = new FileWriter("Rows.txt")) {
      for (Row row : rows) {
        writer.write(writeRowLine(row));
      }
      System.out.println("Content successfully written to " + "Rows.txt");
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  private String writeRowLine(Row row){
    String failureLocation = row.getCell(0).toString();
    String baseDtcOriginal = row.getCell(1).toString();
    String baseDtc = row.getCell(1).toString().substring(0, baseDtcOriginal.length() - 2);

    return "UPDATE `FAILURE_LOCATION` SET FAILURE_LOCATION = '"+failureLocation+"' WHERE BASEDTC = '"+baseDtc+"';\n";
  }

  public void writeHexagonalsToFile(List<String> hexagonals) {
    try (FileWriter writer = new FileWriter("Hexagonals.txt")) {
      writer.write(hexagonalInsert());
      for (int i = 0; i < hexagonals.size(); i++) {
        String element = hexagonals.get(i);
        if (i == 0) {
          writer.write(hexagonalFirst(element));
        }
        else if (i == hexagonals.size() - 1) {
          writer.write(hexagonalLast(element));
        } else {
          writer.write(hexagonalRow(element));
        }
      }
      System.out.println("Content successfully written to " + "Hexagonals.txt");
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  private String hexagonalInsert(){
    return "INSERT INTO `FAILURELOCATION` (BASEDTC) \n";
  }

  private String hexagonalFirst(String first){
    return "VALUES ("+first+"),\n";
  }

  private String hexagonalRow(String row){
    return "("+row+"),\n";
  }

  private String hexagonalLast(String last){
    return "("+last+");\n";
  }
}
