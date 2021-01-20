import myCSVParser.Parser;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
/*
 * Created by babydeveloper on 1/15/21.
 */

public class ReadCSVDataTest {
    String testInput1;
    String testInput2;

    String[] expectedOutput1 = new String[5];
    String[] expectedOutput2 = new String[5];

    @Before
    public void setup(){
        testInput1 = ",11854   ,\"        Lunch,         Oven-warmed Food, Reserve Amenity\",6036798789,6/22/12  ";
        testInput2 = "\"Seabrook, Lafayette Rd\", 13448,\"Lunch\",603-474-3674,\"Geoff     \"";

        expectedOutput1[0] = "";
        expectedOutput1[1] = "11854";
        expectedOutput1[2] = "Lunch, Oven-warmed Food, Reserve Amenity";
        expectedOutput1[3] = "6036798789";
        expectedOutput1[4] = "6/22/12";

        expectedOutput2[0] = "Seabrook, Lafayette Rd";
        expectedOutput2[1] = "13448";
        expectedOutput2[2] = "Lunch";
        expectedOutput2[3] = "603-474-3674";
        expectedOutput2[4] = "Geoff";
    }
    

    @Test
    public void whenCellHasQuotedStringOrEmbeddedCommaParseLineUsingCode() {

        int lineAfterParserSize = Parser.parseLineUsingCode(testInput1).length;
        int expectedSize = 5;
        assertThat(lineAfterParserSize).isEqualTo(expectedSize);

        String[] parseFirstRowUsingCode = Parser.parseLineUsingCode(testInput1);
        assertThat(parseFirstRowUsingCode).isEqualTo(expectedOutput1);

        String[] parseFirstRowUsingCode2 = Parser.parseLineUsingCode(testInput2);
        assertThat(parseFirstRowUsingCode2).isEqualTo(expectedOutput2);

    }

    @Test
    public void whenCellHasEmptySpacesWithinQuotesUsingRegex(){

        String[] parseFirstRowUsingRegex = myCSVParser.Parser.parseLineUsingRegex(testInput1);
        assertThat(parseFirstRowUsingRegex).isEqualTo(expectedOutput1);

        String[] parseFirstRowUsingRegex2 = myCSVParser.Parser.parseLineUsingRegex(testInput2);
        assertThat(parseFirstRowUsingRegex2).isEqualTo(expectedOutput2);

    }
}
