package io.github.krzyzyb.reader;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.StringUtil;

import io.github.krzyzyb.reader.entities.HeaderTemplate;
import io.github.krzyzyb.reader.exceptions.DuplicatedColumnsException;
import io.github.krzyzyb.reader.exceptions.InvalidConfigException;
import io.github.krzyzyb.writer.OutputFileConfig;

public class XlsFileValidator {

  public static void validateHeader(HeaderTemplate header) {
    try {
      checkIfColumnsAreDuplicated(header);
    } catch (DuplicatedColumnsException e){
      e.getDuplicatedColumnNames().forEach(columnName -> System.err.println("Duplicated column name: "+ columnName));
    }
  }

  public static void validateConfig(List<String> fileColumnNames, OutputFileConfig config) {
    try {
      checkNumberOfColumnNames(fileColumnNames, config);
    } catch (InvalidConfigException e){
      System.err.println(e.getMessage());
    }
  }

  private static void checkIfColumnsAreDuplicated(HeaderTemplate header) throws DuplicatedColumnsException {
    List<String> columnNames = getAllColumnNames(header);
    Set<String> duplicatedColumnNames = findDuplicatedColumns(columnNames);
    if (!duplicatedColumnNames.isEmpty()) {
      throw new DuplicatedColumnsException(duplicatedColumnNames);
    }
  }

  private static void checkNumberOfColumnNames(List<String> fileColumnNames, OutputFileConfig config)
      throws InvalidConfigException {
    List<String> configColumnNames = config.getColumnNames();
    if (!Objects.equals(fileColumnNames.size(), configColumnNames.size())) {
      throw new InvalidConfigException("Columns provided in config do not match number of columns in file.");
    }
  }

  private static List<String> getAllColumnNames(HeaderTemplate header) {
    return header.columns().stream()
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
