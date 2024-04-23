package io.github.krzyzyb;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import io.github.krzyzyb.mapper.RowMapper;
import io.github.krzyzyb.reader.XlsFileReader;
import io.github.krzyzyb.reader.XlsFileValidator;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;
import io.github.krzyzyb.writer.OutputFileWriter;

public class Xls2SqlProcessor {
  private XlsFileReader reader;
  private RowMapper mapper;
  private OutputFileWriter writer;

  public void process(Path inputFile, Path outputFile){
      ImportedFile file = read(inputFile);
      XlsFileValidator.validateFile(file.getHeader());
      XlsTemplate xlsTemplate = new XlsTemplate(file.getHeader(), file.getRows());
      writer.write(xlsTemplate, outputFile);
  }

  private ImportedFile read(Path inputFile){
    try {
      return reader.read(inputFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Xls file not found");
    }
  }
}
