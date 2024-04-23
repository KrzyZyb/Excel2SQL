package io.github.krzyzyb.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;

import io.github.krzyzyb.reader.entities.HeaderTemplate;
import io.github.krzyzyb.reader.entities.XlsTemplate;

public class OutputFileWriter {
  public static void write(XlsTemplate xlsTemplate, Path outputFile) {
    try (FileWriter writer = new FileWriter(outputFile.toFile())) {
      prepareStructure(writer, xlsTemplate.header());
      prepareContent(writer, xlsTemplate.rows());
      System.out.println("Content successfully written to " + outputFile);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  private static void prepareStructure(FileWriter writer, HeaderTemplate header) throws IOException {
    writer.write(writeHeaderLine(header));
  }

  private static void prepareContent(FileWriter writer, List<Row> rows) throws IOException {
      for (Row row : rows) {
        writer.write(writeRowLine(row));
      }
  }

  private static String writeHeaderLine(HeaderTemplate header){
    List<String> columnNames = getAllColumnNames(header);
    return "INSERT INTO `FAILURELOCATION` ("+String.join(", ", columnNames)+") VALUES\n";
  }

  private static List<String> getAllColumnNames(HeaderTemplate header) {
    return header.columns().stream()
        .map(Cell::getStringCellValue)
        .filter(StringUtil::isNotBlank)
        .collect(Collectors.toList());
  }

  private static String writeRowLine(Row row){
    String failureLocation = row.getCell(0).toString();
    String baseDtcOriginal = row.getCell(1).toString();
    String baseDtc = row.getCell(1).toString().substring(0, baseDtcOriginal.length() - 2);

    return "UPDATE `FAILURE_LOCATION` SET FAILURE_LOCATION = '"+failureLocation+"' WHERE BASEDTC = '"+baseDtc+"';\n";
  }
}
