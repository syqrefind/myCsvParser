import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Created by babydeveloper on 1/17/21.
 */
public class WriteCSV {
    public static final String IN = "/Users/babydeveloper/intellij-workspace/Insight/myCSVParser/src/main/resources/starbucks_sample.csv";
    public static final String OUT = "/Users/babydeveloper/intellij-workspace/Insight/myCSVParser/src/main/resources/starbucks.csv";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void writeTimes500() {
        try (BufferedReader br = new BufferedReader(new FileReader(IN))) {
            String line = br.readLine(); //  header
            // write the header to destination
            FileWriter fileWriter = new FileWriter(OUT,false);
            fileWriter.append(line);
            fileWriter.append(NEW_LINE_SEPARATOR);

            while ((line = br.readLine()) != null) { // read from the 2nd row till the end
                // write each row 500 times
                for(int i = 1; i <= 500; i++){
                    fileWriter.append(line);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    Thread.sleep(3);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
