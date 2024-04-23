package io.github.krzyzyb.reader.exceptions;

import java.io.Serial;
import java.util.List;
import java.util.Set;

public class DuplicatedColumnsException extends Exception {

  private final Set<String> duplicatedColumnNames;
  @Serial
  private static final long serialVersionUID = 5098607177785661122L;

  public DuplicatedColumnsException(Set<String> duplicatedColumnNames) {
    this.duplicatedColumnNames = duplicatedColumnNames;
  }

  public Set<String> getDuplicatedColumnNames() {
    return duplicatedColumnNames;
  }
}
