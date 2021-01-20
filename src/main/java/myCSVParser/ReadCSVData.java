package myCSVParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by babydeveloper on 1/16/21.
 *
 * two kinds of parsing logic are used in here to compare the performance:
 *  1) parseLineUsingRegex
 *  2) parseLineUsingCode
 *
 * starbucks.csv has 1400+ records
 * starbucks_sample.csv has 3 records
 */
public class ReadCSVData {
    public static final String FILE_PATH = "/Users/babydeveloper/intellij-workspace/Insight/myCSVparser/src/main/resources/starbucks.csv";

    static final Logger logger = LoggerFactory.getLogger(ReadCSVData.class);
    //TODO:
    // change parse logic in here: parseLineUsingRegex, parseLineUsingCode
    private static String parser = "parseLineUsingRegex";

    public static void process() {
        List<String[]> records = new ArrayList<>();
        String[] fields = new String[0];

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // header, ignoring

            long startTime = System.nanoTime();
            while ((line = br.readLine()) != null) { // read from the 2nd row till the end

                if(parser.equals("parseLineUsingRegex")) {
                    fields = Parser.parseLineUsingRegex(line);
                } else if(parser.equals("parseLineUsingCode")){
                    fields = Parser.parseLineUsingCode(line);
                }
                records.add(fields);
            }

            // printing the execution time
            long endtTime = System.nanoTime();
            logger.info("{} execution time: {} milliseconds"
            + " for {} records", parser,(endtTime - startTime) / 1000000, records.size());


        } catch (Exception e) {
            e.printStackTrace();
        }

        // printing the result
        for(String[] record: records) {
            for(int i = 0; i < record.length; i++){
                if(i == record.length-1){
                    System.out.print(record[i] );
                } else{
                    // replace the delimiter from , to :
                    System.out.print(record[i] + ": ");
                }
            }
            System.out.println();
        }

    }
}
