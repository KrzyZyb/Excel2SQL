package io.github.krzyzyb.reader;

import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;

public class ImportedFile {
  private static final Logger logger = Logger.getLogger(ImportedFile.class.getName());
  public static final int HEADER_IDX = 0;
  public static final int FIRST_COLUMN_IDX = 0;
  private final Workbook file;

  public ImportedFile(Workbook file) {
    validateFile(file);
    this.file = file;
  }

  public Workbook getFile() {
    return file;
  }

  public Row getHeader() {
    return findHeader(file);
  }

  private void validateFile(Workbook file) {
    checkHeaderLine(file);
  }

  private void checkHeaderLine(Workbook file) {
    Row header = findHeader(file);
    try {
      checkIfColumnsAreDuplicated(header);
    } catch (DuplicatedColumnsException e){
      logger.log(Level.SEVERE, "Duplicated column", e.getDuplicatedColumnNames());
    }
  }

  private Row findHeader(Workbook file) {
    Sheet sheet = file.getSheetAt(HEADER_IDX);
    return StreamSupport.stream(sheet.spliterator(), false)
        .filter(Objects::nonNull)
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
  }

  private Row iterateThroughRows(Workbook file) {
    Sheet sheet = file.getSheetAt(HEADER_IDX);
    return StreamSupport.stream(sheet.spliterator(), false)
        .filter(currentRow -> {
          Cell diProIdCell = currentRow.getCell(FIRST_COLUMN_IDX);
          return nonNull(diProIdCell);})
        .findFirst()
        .orElseThrow(NoSuchElementException::new);
  }

  private void checkIfColumnsAreDuplicated(Row header) throws DuplicatedColumnsException {
    List<String> columnNames = getAllColumnNames(header);
    Set<String> duplicatedColumnNames = findDuplicatedElements(columnNames);
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

  private Set<String> findDuplicatedElements(List<String> elements) {
    Set<String> uniqueElements = new HashSet<>();
    return elements.stream()
        .filter(element -> !uniqueElements.add(element))
        .collect(Collectors.toSet());
  }
}
