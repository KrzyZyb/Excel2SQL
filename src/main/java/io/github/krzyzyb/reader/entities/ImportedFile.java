package io.github.krzyzyb.reader.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportedFile {
  public static final int SHEET_IDX = 0;
  private final HeaderTemplate headerRow;
  private final List<Row> rows;

  public ImportedFile(Workbook file) {
    this.headerRow = this.findHeader(file);
    this.rows = findRows(file);
  }

  private HeaderTemplate findHeader(Workbook file) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    List<String> headerCells = new ArrayList<>();

    Row headerRow = StreamSupport.stream(sheet.spliterator(), false)
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(NoSuchElementException::new);

    headerRow.forEach(cell -> headerCells.add(cell.getStringCellValue()));
    return new HeaderTemplate(headerCells);
  }

  private List<Row> findRows(Workbook file) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    return StreamSupport.stream(sheet.spliterator(), false)
        .skip(1)
        .collect(Collectors.toList());
  }

  public HeaderTemplate getHeaderRow() {
    return headerRow;
  }

  public List<Row> getRows() {
    return rows;
  }
}
