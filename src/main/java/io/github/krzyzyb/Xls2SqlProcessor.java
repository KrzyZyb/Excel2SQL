package io.github.krzyzyb;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import io.github.krzyzyb.mapper.RowMapper;
import io.github.krzyzyb.reader.ExcelFileReader;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;
import io.github.krzyzyb.writer.OutputFileWriter;

public class Xls2SqlProcessor {
  private ExcelFileReader reader;
  private RowMapper mapper;
  private OutputFileWriter writer;

  public void process(Path inputFile, Path outputFile){
      ImportedFile file = read(inputFile);
      XlsTemplate xlsTemplate = new XlsTemplate(file.getHeader(), file.getRows());


  }

  private ImportedFile read(Path inputFile){
    try {
      return reader.read(inputFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Xls file not found");
    }
  }
}
