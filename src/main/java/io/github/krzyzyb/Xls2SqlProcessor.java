package io.github.krzyzyb;

import static io.github.krzyzyb.reader.XlsFileReader.readFile;
import static io.github.krzyzyb.reader.XlsFileReader.readFileName;
import static io.github.krzyzyb.writer.OutputFileWriter.write;

import java.nio.file.Path;
import java.util.List;

import io.github.krzyzyb.reader.XlsFileValidator;
import io.github.krzyzyb.reader.entities.Header;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.reader.entities.XlsTemplate;
import io.github.krzyzyb.writer.OutputFileConfig;

public class Xls2SqlProcessor {
  /**
   * In default processing method Xls2SqlProcessor requires source input file with xls format and path for
   * storing results of the processing (*.sql or *.txt). By default it creates an SQL output file with:
   * XLS header names as column names for SQL and Filename as TableName in which the
   * data will be inserted. E.G. providing input file:'/user/Source.xls' (Containing 'Col1' and 'Col2'),
   * and outputFile:'./user/Destiny.sql' will result in creating Destiny.sql file in required path that
   * contains insertions to table named Destiny with columns: 'Col1' and 'Col2'.
   *
   * @param inputFile Path to file containing data to insert. Column names in header will be persisted into
   *                  column names in the output file.
   * @param outputFile Path to file where the processed data will be stored. Output file name without extension
   *                   will be to persisted into table name where processed data will be inserted.
   */
  public static void process(Path inputFile, Path outputFile) {
    String outputFileName = readFileName(outputFile);
    ImportedFile file = readFile(inputFile, outputFileName);
    XlsFileValidator.validate(file.getHeader());
    XlsTemplate xlsTemplate = new XlsTemplate(file.getHeader(), file.getContent());
    write(xlsTemplate, outputFile);
  }

  /**
   * In custom processing method Xls2SqlProcessor requires source input file with xls format and path for
   * storing results of the processing (*.sql or *.txt). It creates an SQL with name as in 'Path outputFile'
   * and content output with TableName and Column Names as provided in OutputFileConfig.
   *
   * @param inputFile Path to file containing data to insert.
   * @param outputFile Path to file where the processed data will be stored.
   * @param config Configuration parameters for persisting input file. Can be customized by output table name
   *               and custom column names.
   */
  public static void process(Path inputFile, Path outputFile, OutputFileConfig config){
    ImportedFile file = readFile(inputFile, config.tableName());
    List<String> fileColumnNames = file.getHeader().columns();
    Header header = new Header(config.columnNames(), config.tableName());
    XlsFileValidator.validate(header, fileColumnNames, config);
    write(new XlsTemplate(header, file.getContent()), outputFile);
  }
}
