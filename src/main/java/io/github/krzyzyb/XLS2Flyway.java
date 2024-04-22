package io.github.krzyzyb;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import io.github.krzyzyb.generator.HexagonalContentGenerator;
import io.github.krzyzyb.reader.ExcelFileReader;
import io.github.krzyzyb.reader.ImportedFile;
import io.github.krzyzyb.writer.OutputFileWriter;

/**
 * Hello world!
 *
 */
public class XLS2Flyway
{
    public static void main(String[] args) throws FileNotFoundException {
        ExcelFileReader reader = new ExcelFileReader();
        OutputFileWriter writer = new OutputFileWriter();
        ImportedFile importedFile = reader.read();
        Row header = importedFile.getHeader();
        List<Row> rows = importedFile.getRows();
        List<String> hexadecimals = HexagonalContentGenerator.generateHexadecimal();
        writer.writeRowsToFile(rows);
        writer.writeHexagonalsToFile(hexadecimals);
        System.out.println("Done.");

    }
}
