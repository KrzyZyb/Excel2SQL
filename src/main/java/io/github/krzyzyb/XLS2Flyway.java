package io.github.krzyzyb;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import io.github.krzyzyb.reader.XlsFileReader;
import io.github.krzyzyb.reader.entities.ImportedFile;
import io.github.krzyzyb.writer.OutputFileWriter;

/**
 * Hello world!
 *
 */
public class XLS2Flyway
{
    public static void main(String[] args) throws FileNotFoundException {
        XlsFileReader reader = new XlsFileReader();
        OutputFileWriter writer = new OutputFileWriter();
        Path path = Path.of("/Users/kzybul/IdeaProjects/XLS2Flyway/Failure_Types.xlsx");
        ImportedFile importedFile = reader.read(path);
        Row header = importedFile.getHeader();
        List<Row> rows = importedFile.getRows();
        System.out.println();
    }
}
