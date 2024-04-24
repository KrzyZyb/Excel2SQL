package io.github.krzyzyb.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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
      String tableName = removeExtension(outputFile.getFileName().toString());
      prepareStructure(tableName, writer, xlsTemplate.header());
      prepareContent(writer, xlsTemplate);
      System.out.println("Content successfully written to " + outputFile);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  private static void prepareStructure(String tableName, FileWriter writer, HeaderTemplate header) throws IOException {
    writer.write(writeHeaderLine(tableName, header));
  }

  private static void prepareContent(FileWriter writer, XlsTemplate xlsTemplate) throws IOException {
    List<Cell> columns = xlsTemplate.header().columns();

    for (Row row : xlsTemplate.rows()) {
        writer.write(writeRowLine(row, columns.size()));
      }
  }

  private static String writeHeaderLine(String tableName, HeaderTemplate header){
    List<String> columnNames = getAllColumnNames(header);
    return String.format("INSERT INTO `%s` (%s) VALUES \n", tableName, String.join(", ", columnNames));
  }

  private static List<String> getAllColumnNames(HeaderTemplate header) {
    return header.columns().stream()
        .map(Cell::getStringCellValue)
        .filter(StringUtil::isNotBlank)
        .collect(Collectors.toList());
  }

  private static String writeRowLine(Row row, int numberOfColumns){
    List<String> columnValues = new ArrayList<>();
    for(int i=0; i<numberOfColumns; i++){
      columnValues.add(String.format("'%s'", row.getCell(i).toString()));
    }
    return String.format("(%s),\n", String.join(",", columnValues));
  }

  private static String removeExtension(String fileName) {
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex != -1) {
      return fileName.substring(0, lastDotIndex);
    }
    return fileName;
  }
}
