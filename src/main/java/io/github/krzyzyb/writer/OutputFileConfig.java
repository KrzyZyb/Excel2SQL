package io.github.krzyzyb.writer;

import java.util.List;

public record OutputFileConfig(List<String> columnNames, String tableName) {
}
