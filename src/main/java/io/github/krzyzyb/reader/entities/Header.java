package io.github.krzyzyb.reader.entities;

import java.util.List;

public record Header(List<String> columns, String tableName) {
}
