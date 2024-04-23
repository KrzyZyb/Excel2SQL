package io.github.krzyzyb;

import static io.github.krzyzyb.reader.XlsFileReader.read;
import static io.github.krzyzyb.writer.OutputFileWriter.write;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import io.github.krzyzyb.reader.XlsFileValidator;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;

public class Xls2SqlProcessor {
  public static void process(Path inputFile, Path outputFile){
      ImportedFile file = readFile(inputFile);
      XlsFileValidator.validateFile(file.getHeader());
      XlsTemplate xlsTemplate = new XlsTemplate(file.getHeader(), file.getRows(), file.getNumberOfColumns());
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
