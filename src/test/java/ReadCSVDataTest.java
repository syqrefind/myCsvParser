import myCSVParser.Parser;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * Created by babydeveloper on 1/15/21.
 */

public class ReadCSVDataTest {
    String testInput;
    String[] expectedOutput = new String[5];

    @Before
    public void setup(){
        testInput = ",11854   ,\"        Lunch,         Oven-warmed Food, Reserve Amenity\",6036798789,6/22/12  ";

        expectedOutput[0] = "";
        expectedOutput[1] = "11854";
        expectedOutput[2] = "Lunch, Oven-warmed Food, Reserve Amenity";
        expectedOutput[3] = "6036798789";
        expectedOutput[4] = "6/22/12";
    }
    

    @Test
    public void whenCellHasQuotedStringOrEmbededCommaParseLineUsingCode() {

        int lineAfterParserSize = Parser.parseLineUsingCode(testInput).length;
        int expectedSize = 5;
        assertThat(lineAfterParserSize).isEqualTo(expectedSize);

        String[] parseFirstRowUsingCode = Parser.parseLineUsingCode(testInput);
        assertThat(parseFirstRowUsingCode).isEqualTo(expectedOutput);
    }

    @Test
    public void whenCellHasEmptySpacesWithinQuotesUsingRegex(){

        String[] parseFirstRowUsingRegex = myCSVParser.Parser.parseLineUsingRegex(testInput);
        assertThat(parseFirstRowUsingRegex).isEqualTo(expectedOutput);

    }
}
