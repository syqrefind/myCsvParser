import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseChar;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/*
 * Created by babydeveloper on 1/16/21.
 *
 * Reading CSV files with variable columns use listReader.
 */
public class ReadCSVSuperCSV {
    public static final String FILE_PATH = "/Users/babydeveloper/intellij-workspace/Insight/csv_parser/src/main/resources/starbucks_sample.csv";
    public static void readVariableColumnsWithCsvListReader() throws IOException {

        //This pattern will allow numbers like 2055550125, 202 555 0125, 202.555.0125, and 202-555-0125.
        final String phoneRegex ="^(\\d{3}[- .]?){2}\\d{4}$";
        final CellProcessor[] allProcessors = new CellProcessor[] {
                null,// name
                new UniqueHashCode(), // storeNo (must be unique)

                null, // features-products
                new Optional(new StrRegEx(phoneRegex)),  //phone Number
                new NotNull(),  //                new ParseDate("MM/dd/yyyy HH:mm"), // insert date
                null};  //geoff

        final CellProcessor[] noOwnerProcessors = new CellProcessor[] {
                allProcessors[0], // name
                allProcessors[1], // storeNo
                allProcessors[2], // features-products
                allProcessors[3], //phone num
                allProcessors[4]}; // insert date


        ICsvListReader listReader = null;
        try {
            listReader = new CsvListReader(new FileReader(FILE_PATH), CsvPreference.STANDARD_PREFERENCE);

            //First Column is header names- though we don't need it in runtime
            @SuppressWarnings("unused")
            final String[] headers = listReader.getHeader(true);

            while((listReader.read()) != null) {
                final CellProcessor[] processors;
                if( listReader.length() == noOwnerProcessors.length ) {
                    processors = noOwnerProcessors;
                } else {
                    processors = allProcessors;
                }

                final List<Object> starbucksList = listReader.executeProcessors(processors);
                System.out.println(String.format("lineNo=%s, rowNo=%s, columns=%s, starbucksList=%s",
                        listReader.getLineNumber(), listReader.getRowNumber(), starbucksList.size(), starbucksList));
            }

        }
        finally {
            if( listReader != null ) {
                listReader.close();
            }
        }
    }
}
