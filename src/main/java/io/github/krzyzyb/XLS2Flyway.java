package io.github.krzyzyb;

import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;

import io.github.krzyzyb.reader.ExcelFileReader;
import io.github.krzyzyb.reader.ImportedFile;

/**
 * Hello world!
 *
 */
public class XLS2Flyway
{
    public static void main(String[] args) throws FileNotFoundException {
        ExcelFileReader reader = new ExcelFileReader();
        ImportedFile importedFile = reader.read();
        Row header = importedFile.getHeader();
        System.out.println("Done.");

    }
}
