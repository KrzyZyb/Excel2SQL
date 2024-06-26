package io.github.krzyzyb.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;

import io.github.krzyzyb.reader.entities.Header;
import io.github.krzyzyb.reader.entities.XlsTemplate;

public class OutputFileWriter {
  public static void write(XlsTemplate xlsTemplate, Path outputFile) {
    try (FileWriter writer = new FileWriter(outputFile.toFile())) {
      prepareStructure(writer, xlsTemplate.header());
      prepareContent(writer, xlsTemplate);
      System.out.println("Content successfully written to " + outputFile);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  private static void prepareStructure(FileWriter writer, Header header) throws IOException {
    writer.write(writeHeaderLine(header));
  }

  private static void prepareContent(FileWriter writer, XlsTemplate xlsTemplate) throws IOException {
    List<String> columns = xlsTemplate.header().columns();
    for (Row row : xlsTemplate.content().rows()) {
        writer.write(writeRowLine(row, columns.size()));
      }
  }

  private static String writeHeaderLine(Header header){
    List<String> columnNames = getAllColumnNames(header);
    return String.format("INSERT INTO `%s` (%s) VALUES \n", header.tableName(), String.join(", ", columnNames));
  }

  private static List<String> getAllColumnNames(Header header) {
    return header.columns().stream()
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
}
