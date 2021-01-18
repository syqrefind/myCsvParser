import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * Created by babydeveloper on 1/15/21.
 */

public class ReadCSVDataTest {
    

    @Test
    public void whenCellHasQuotedStringOrEmbededComma() {

        String testInput = ", 11854, Lunch, Oven-warmed Food";
        String[] expectedOutput = new String[3];
        expectedOutput[0] = "";
        expectedOutput[1] = "11854";
        expectedOutput[2] = "Lunch, Oven-warmed Food";

        Parser parser = new Parser();
        String[] firstRow = parser.parseLineUsingCode(testInput);
        assertThat(firstRow).isEqualTo(expectedOutput);
    }



}
