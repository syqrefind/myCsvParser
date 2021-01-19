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
        //0123
        //"Sd",  13,"Lunch"
        //     i
        for (int i = 0; i < lineChar.length; i++) {
            // check quote
            if(lineChar[i] == '\"'){
                inQuotes = !inQuotes;
            }
            // when not inQuote
            if(inQuotes == false){
                if(lineChar[i] == ','){      // finish one cell
                    // if the field is empty
                    if(sb.length()==0) {
                       String emptyCell = " ";
                       sb.append(emptyCell);
                    }
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                }
                else if(i == lineChar.length-1){
                    if(lineChar[i] != '\"') {
                        sb.append(lineChar[i]);
                    }
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                } else if(lineChar[i] == '\"' && lineChar[i+1] == ',' ||
                        lineChar[i] == '\"' && i == lineChar.length-1){
                    continue;
                } else{
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
        return removeSpaces(res);
    }

    // trim all leading and trailing whitespace, including those in quotes
    //res.set(i, res.get(i).toString().replaceAll("\\s+", " "));   // didn't work as expected, still left one empty space
    private static String[] removeSpaces(List<String> res) {

        int end = 0;


        //[Sd,          13,        Lunch]   list of string -> tmpArr for each string elem
        // i
        for(int i = 0; i < res.size(); i++ ){           //         j
            char[] tmpArr = res.get(i).toCharArray(); // tmpArr: { 13448}
            char[] resArr = new char[tmpArr.length];
            for(int j = 0; j < tmpArr.length; j++){
                if (tmpArr[j] == ' ' && (j==0 || tmpArr[j-1] == ' ')){
                    continue;
                }
                resArr[end++] = tmpArr[j];
            }
            if(end>0 && tmpArr[end-1] == ' ') {
                String newString = new String(resArr,0,end-1);
                res.set(i,newString);
            } else {
                String newString = new String(resArr, 0, end);
                res.set(i, newString);
            }
            //reset
            resArr = null;
            end = 0;

        }

        String[] arrayToReturn = res.toArray(new String[0]);

        return arrayToReturn;
    }


    public static void main(String[] args) {

        String line0 = "\"Sd\",         13,\"       Lunch\"";
        String line = "\"Seabrook,       Lafayette Rd\", 13448,\"Lunch\",603-474-3674,6/22/12";
        String line2 = ",11854,\"Lunch, Oven-warmed Food, Reserve Amenity\",6036798789,6/22/12";
        String line3 ="      Stop & Shop-Exeter #204,79420,,        603-772-1783,6/22/12,\"Geoff\"";
//        System.out.println(Arrays.toString(parseLineUsingCode(line0)));
        System.out.println(Arrays.toString(parseLineUsingCode(line)));
//        System.out.println(Arrays.toString(parseLineUsingCode(line2)));
//        System.out.println(Arrays.toString(parseLineUsingCode(line3)));
    }
}

