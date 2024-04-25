package io.github.krzyzyb;

import java.nio.file.Path;
import java.util.Arrays;

import io.github.krzyzyb.writer.OutputFileConfig;

/**
 * Hello world!
 *
 */
public class Xls2Sql
{
    public static void main(String[] args){
        Path inputPath = Path.of("/Users/kzybul/IdeaProjects/XLS2Flyway/Failure_Types.xlsx");
        Path outputPath = Path.of("/Users/kzybul/IdeaProjects/XLS2Flyway/Result.sql");
        OutputFileConfig config = new OutputFileConfig(Arrays.asList("COL1", "COL2", "COL3", "COL4"),"TABLE");
//        Xls2SqlProcessor.process(inputPath, outputPath, config);
        Xls2SqlProcessor.process(inputPath, outputPath);
    }
}
