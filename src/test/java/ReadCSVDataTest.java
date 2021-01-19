import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
 * Created by babydeveloper on 1/15/21.
 */

public class ReadCSVDataTest {
    

    @Test
    public void whenCellHasQuotedStringOrEmbededComma() {

        String testInput = ",11854,\"        Lunch,         Oven-warmed Food, Reserve Amenity\",6036798789,6/22/12  ";
        String[] expectedOutput = new String[5];
        expectedOutput[0] = "";
        expectedOutput[1] = "11854";
        expectedOutput[2] = "Lunch, Oven-warmed Food, Reserve Amenity";
        expectedOutput[3] = "6036798789";
        expectedOutput[4] = "6/22/12";

        int lineAfterParserSize = Parser.parseLineUsingCode(testInput).length;
        int expectedSize = 5;
        assertThat(lineAfterParserSize).isEqualTo(expectedSize);

        String[] firstRow = Parser.parseLineUsingCode(testInput);
        assertThat(firstRow).isEqualTo(expectedOutput);

    }

}
