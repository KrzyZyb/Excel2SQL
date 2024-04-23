package io.github.krzyzyb.reader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;

import io.github.krzyzyb.reader.exceptions.DuplicatedColumnsException;

public class XlsFileValidator {

  public static void validateFile(Row header) {
    try {
      checkIfColumnsAreDuplicated(header);
    } catch (DuplicatedColumnsException e){
      e.getDuplicatedColumnNames().forEach(columnName -> System.err.println("Duplicated column name: "+ columnName));
    }
  }

  private static void checkIfColumnsAreDuplicated(Row header) throws DuplicatedColumnsException {
    List<String> columnNames = getAllColumnNames(header);
    Set<String> duplicatedColumnNames = findDuplicatedColumns(columnNames);
    if (!duplicatedColumnNames.isEmpty()) {
      throw new DuplicatedColumnsException(duplicatedColumnNames);
    }
  }

  private static List<String> getAllColumnNames(Row header) {
    return StreamSupport.stream(header.spliterator(), false)
        .map(Cell::getStringCellValue)
        .filter(StringUtil::isNotBlank)
        .collect(Collectors.toList());
  }

  private static Set<String> findDuplicatedColumns(List<String> elements) {
    Set<String> uniqueElements = new HashSet<>();
    return elements.stream()
        .filter(element -> !uniqueElements.add(element))
        .collect(Collectors.toSet());
  }
}
