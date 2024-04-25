package io.github.krzyzyb.reader.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportedFile {
  public static final int SHEET_IDX = 0;
  private final Header header;
  private final Content content;

  public ImportedFile(Workbook file, String tableName) {
    this.header = findHeader(file, tableName);
    this.content = findContent(file);
  }

  private Header findHeader(Workbook file, String tableName) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    List<String> headerCells = new ArrayList<>();

    Row headerRow = StreamSupport.stream(sheet.spliterator(), false)
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(NoSuchElementException::new);

    headerRow.forEach(cell -> headerCells.add(cell.getStringCellValue()));
    return new Header(headerCells, tableName);
  }

  private Content findContent(Workbook file) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    List<Row> rows = StreamSupport.stream(sheet.spliterator(), false)
        .skip(1)
        .toList();
    return new Content(rows);
  }

  public Header getHeader() {
    return header;
  }

  public Content getContent() {
    return content;
  }
}
