import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/*
 * Created by babydeveloper on 1/15/21.
 */
public class ReadCSVOpencsv {
    public static void readLineByLine(String file) {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // use readNext() to read line by line
            while((nextRecord = csvReader.readNext()) !=null){
                for(String s: nextRecord){
                    System.out.println(s + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void readAll(String file) {
        try{
            FileReader fileReader = new FileReader(file);
            CSVReader allCSVData = new CSVReaderBuilder(fileReader)
                                    .withSkipLines(1)
                                    .build();
            List<String[]> allData = allCSVData.readAll();

            for (String[] row : allData) {
                for (String s : row) {
                    System.out.print(s + "\t");
                }
                System.out.println();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

