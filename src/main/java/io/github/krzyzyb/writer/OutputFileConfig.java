package io.github.krzyzyb.writer;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OutputFileConfig {
  private final List<String> columnNames;
  private final String tableName;
}
