package io.github.krzyzyb.reader.entities;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public record HeaderTemplate(List<Cell> columns, int width) {
}
