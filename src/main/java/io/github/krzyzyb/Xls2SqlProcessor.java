package io.github.krzyzyb;

import static io.github.krzyzyb.reader.XlsFileReader.read;
import static io.github.krzyzyb.writer.OutputFileWriter.write;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import io.github.krzyzyb.reader.XlsFileValidator;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;
import io.github.krzyzyb.writer.OutputFileConfig;

public class Xls2SqlProcessor {
  public static void process(Path inputFile, Path outputFile){
      ImportedFile file = readFile(inputFile);
      XlsFileValidator.validateHeader(file.getHeaderRow());
      XlsTemplate xlsTemplate = new XlsTemplate(file.getHeaderRow(), file.getRows());
      write(xlsTemplate, outputFile);
  }

  public static void process(Path inputFile, Path outputFile, OutputFileConfig config){
    ImportedFile file = readFile(inputFile);
    XlsFileValidator.validateHeader(file.getHeaderRow());
    XlsTemplate xlsTemplate = new XlsTemplate(file.getHeaderRow(), file.getRows());
    write(xlsTemplate, outputFile);
  }

  private static ImportedFile readFile(Path inputFile){
    try {
      return read(inputFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Xls file not found");
    }
  }
}
