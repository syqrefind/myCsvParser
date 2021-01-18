import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Created by babydeveloper on 1/17/21.
 */
public class Test {
    public static String[] parseLineUsingCode(String line) {

        List<String> res = new ArrayList<>();
        char[] lineChar = line.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        //"Se", 13,"L",60,6/22/12
        //                      i
        for (int i = 0; i < lineChar.length; i++) {
            // check quote
            if(lineChar[i] == '\"'){
                inQuotes = !inQuotes;
            }
            // when not inQuote
            if(inQuotes == false){
                if(lineChar[i] == ','){      // finish one cell
                    if(sb.length()==0) {
                       String emptyCell = " ";
                       sb.append(emptyCell);
                    }
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                } else if(i == lineChar.length-1){
                    sb.append(lineChar[i]);
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                }else{
                    sb.append(lineChar[i]);
                }
            }
            // when inQuote
            else if(inQuotes == true){
                if(lineChar[i] == '\"'){
                } else{
                    sb.append(lineChar[i]);
                }
            } else{
                sb.append(lineChar[i]);
            }
        }

        System.out.println(res.size());

        return res.toArray((new String[res.size()]));
    }



    public static void main(String[] args) {
        String line = "\"Seabrook, Lafayette Rd\", 13448,\"Lunch\",603-474-3674,6/22/12";
        String line2 = ",11854,\"Lunch, Oven-warmed Food, Reserve Amenity\",6036798789,6/22/12";
        String line3 ="Stop & Shop-Exeter #204,79420,,603-772-1783,6/22/12,\"Geoff\"";
        System.out.println(Arrays.toString(parseLineUsingCode(line)));
        System.out.println(Arrays.toString(parseLineUsingCode(line2)));
        System.out.println(Arrays.toString(parseLineUsingCode(line3)));
    }
}

