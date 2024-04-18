package io.github.krzyzyb.reader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileReader {

  public ImportedFile read() throws FileNotFoundException {
    String filePath = "/Users/kzybul/IdeaProjects/XLS2Flyway/";
    String fileName = "Failure_Types.xlsx";
    byte[] fileContent =  loadExcelFile(filePath+fileName);
    ImportedFile persistedFile;
    try {
      assert fileContent != null;
      try (InputStream inputStream = new ByteArrayInputStream(fileContent)) {
        persistedFile = new ImportedFile(new XSSFWorkbook(inputStream));
      }
    } catch (Exception ex) {
      throw new NotOfficeXmlFileException("Not XML file");
    }
    return persistedFile;
  }

  public static byte[] loadExcelFile(String filePath) throws FileNotFoundException {
    InputStream inputStream = new FileInputStream(filePath);
    try {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      int bytesRead;
      byte[] data = new byte[1024];
      while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, bytesRead);
      }
      buffer.flush();
      return buffer.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
