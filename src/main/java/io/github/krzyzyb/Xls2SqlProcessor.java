package io.github.krzyzyb;

import static io.github.krzyzyb.reader.XlsFileReader.read;
import static io.github.krzyzyb.writer.OutputFileWriter.write;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import io.github.krzyzyb.reader.XlsFileValidator;
import io.github.krzyzyb.reader.entities.HeaderTemplate;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;
import io.github.krzyzyb.writer.OutputFileConfig;

public class Xls2SqlProcessor {
  public static void process(Path inputFile, Path outputFile) {
    String outputFileName = readFileName(outputFile);
    ImportedFile file = readFile(inputFile, outputFileName);
    XlsFileValidator.validateHeader(file.getHeaderRow());
    XlsTemplate xlsTemplate = new XlsTemplate(file.getHeaderRow(), file.getRows());
    write(xlsTemplate, outputFile);
  }

  public static void process(Path inputFile, Path outputFile, OutputFileConfig config){
    ImportedFile file = readFile(inputFile, config.getTableName());
    HeaderTemplate header = new HeaderTemplate(config.getColumnNames(), config.getTableName());
    XlsFileValidator.validateHeader(header);
    XlsTemplate xlsTemplate = new XlsTemplate(header, file.getRows());
    write(xlsTemplate, outputFile);
  }

  private static ImportedFile readFile(Path inputFile, String tableName){
    try {
      return read(inputFile, tableName);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Xls file not found");
    }
  }

  private static String readFileName(Path path){
    String fileName = path.getFileName().toString();
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex != -1) {
      return fileName.substring(0, lastDotIndex);
    }
    return fileName;
  }
}
