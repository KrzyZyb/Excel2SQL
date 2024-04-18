package io.github.krzyzyb.reader;

import java.io.Serial;
import java.util.Set;

public class DuplicatedColumnsException extends Throwable {

  private final Set<String> duplicatedColumnNames;
  @Serial
  private static final long serialVersionUID = 5098607177785661122L;

  public DuplicatedColumnsException(Set<String> duplicatedColumnNames) {
    this.duplicatedColumnNames = duplicatedColumnNames;
  }

  public String[] getDuplicatedColumnNames() {
    return duplicatedColumnNames.toArray(new String[duplicatedColumnNames.size()]);
  }
}
