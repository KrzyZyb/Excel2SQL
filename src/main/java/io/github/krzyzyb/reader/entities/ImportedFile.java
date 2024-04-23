package io.github.krzyzyb.reader.entities;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;

import io.github.krzyzyb.reader.exceptions.DuplicatedColumnsException;

public class ImportedFile {
  public static final int SHEET_IDX = 0;
  public static final int FIRST_COLUMN_IDX = 0;
  private int numberOfColumns;
  private final Workbook file;
  private final Row headerRow;
  private final List<Row> rows;

  public ImportedFile(Workbook file) {
    validateFile(file);
    this.headerRow = this.findHeader(file);
    this.numberOfColumns = headerRow.getLastCellNum();
    this.file = file;
    this.rows = findRows(file);
  }

  public Row getHeader() {
    return headerRow;
  }

  public List<Row> getRows() {
    return rows;
  }

  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  private void validateFile(Workbook file) {
    checkHeaderLine(file);
  }

  private void checkHeaderLine(Workbook file) {
    Row header = findHeader(file);
    try {
      checkIfColumnsAreDuplicated(header);
    } catch (DuplicatedColumnsException e){
      e.getDuplicatedColumnNames().forEach(columnName -> System.err.println("Duplicated column name: "+ columnName));
    }
  }

  private Row findHeader(Workbook file) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    return StreamSupport.stream(sheet.spliterator(), false)
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
  }

  private List<Row> findRows(Workbook file) {
    Sheet sheet = file.getSheetAt(SHEET_IDX);
    return StreamSupport.stream(sheet.spliterator(), false)
        .skip(1)
        .collect(Collectors.toList());
  }

  private void checkIfColumnsAreDuplicated(Row header) throws DuplicatedColumnsException {
    List<String> columnNames = getAllColumnNames(header);
    Set<String> duplicatedColumnNames = findDuplicatedColumns(columnNames);
    if (!duplicatedColumnNames.isEmpty()) {
      throw new DuplicatedColumnsException(duplicatedColumnNames);
    }
  }

  private List<String> getAllColumnNames(Row header) {
    return StreamSupport.stream(header.spliterator(), false)
        .map(Cell::getStringCellValue)
        .filter(StringUtil::isNotBlank)
        .collect(Collectors.toList());
  }

  private Set<String> findDuplicatedColumns(List<String> elements) {
    Set<String> uniqueElements = new HashSet<>();
    return elements.stream()
        .filter(element -> !uniqueElements.add(element))
        .collect(Collectors.toSet());
  }
}
