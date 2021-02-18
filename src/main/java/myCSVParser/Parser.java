package myCSVParser;/*
 * Created by babydeveloper on 1/17/21.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser{
    public static String[] parseLineUsingRegex(String line)  {

        // split the cell by commas, ignore commas in double quotes
        String[] lineAfterParse = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < lineAfterParse.length; i++) {
                lineAfterParse[i] = lineAfterParse[i].trim()
                        .replaceAll("\"", "");
        }
        List<String> stringList = Arrays.asList(lineAfterParse);
        return removeSpaces(stringList);
    }

    public static String[] parseLineUsingCode(String line) {
        List<String> res = new ArrayList<>();
        char[] lineCharArray = line.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < lineCharArray.length; i++) {
            // check quote
            if(lineCharArray[i] == '\"'){
                inQuotes = !inQuotes;
            }
            // when not inQuote
            if(inQuotes == false){
                if(lineCharArray[i] == ','){      // finish one cell
                    // if the field is empty, append empty cell to sb
                    if(sb.length()==0) {
                        String emptyCell = " ";
                        sb.append(emptyCell);
                    }
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                }
                // hit the last element in lineCharArray
                else if(i == lineCharArray.length-1){
                    // add the last element to sb if it is not a quote
                    if(lineCharArray[i] != '\"') {
                        sb.append(lineCharArray[i]);
                    }
                    // add finished cell to res
                    res.add(sb.toString());
                    // flash sb
                    sb = new StringBuilder();
                }
                else if(lineCharArray[i] == '\"' && lineCharArray[i+1] == ',' ||
                        lineCharArray[i] == '\"' && i == lineCharArray.length-1){
                    continue;
                } else{
                    sb.append(lineCharArray[i]);
                }
            }
            // when inQuote
            else if(inQuotes == true){
                if(lineCharArray[i] == '\"'){
                } else{
                    sb.append(lineCharArray[i]);
                }
            }
        }
        return removeSpaces(res);
    }

    // remove leading and tailing spaces within quotes
    private static String[] removeSpaces(List<String> res) {
        int end = 0;  // index in resArr which we will use to build the newString

        for(int i = 0; i < res.size(); i++ ){
            // List<String> -> char[]tmpArr. keep each string in scope
            char[] tmpArr = res.get(i).toCharArray();
            // create an empty resArr
            char[] resArr = new char[tmpArr.length];
            for(int j = 0; j < tmpArr.length; j++){
                // if tmpArr[j] is a space, and is at the first position, or if we have two or more spaces continuously, then ignore
                if (tmpArr[j] == ' ' && (j==0 || tmpArr[j-1] == ' ')){
                    continue;
                }
                resArr[end++] = tmpArr[j];
            }
            String newString = null;
            // post-processing: it is possible that we still left one space at the end, trim it
            // e.g., "geoff "
            //             j
            if(end>0 && tmpArr[end-1] == ' ') {
                newString = new String(resArr, 0, end - 1);
            }
            else {
                newString = new String(resArr, 0, end);
            }
            res.set(i,newString);
            //reset
            resArr = null;
            end = 0;

        }

        String[] arrayToReturn = res.toArray(new String[0]);

        return arrayToReturn;
    }



//    public static void assignProperDataType(String line) {
//
////        List<String[]> records = new ArrayList<>();
////
////        for(String[] field: records) {
////            String name = field[0];
////            Integer storeNumber = Integer.parseInt(field[1].trim());
////            String features = field[2];
////            String phoneNumber = field[3];
////            //TODO: string -> parseToDateTime
////            String insertDate = field[4];
//////            String insertDate = parseToDateTime(field[4]);
////            if (field.length == 5) {
////                Starbucks sb2 = new Starbucks(name, storeNumber, features, phoneNumber, insertDate);
////                recordsAfterParsed.add(sb2);
////            } else {
////                String owner = field[5];
////                Starbucks sb1 = new Starbucks(name, storeNumber, features, phoneNumber, insertDate, owner);
////                recordsAfterParsed.add(sb1);
////            }
////        }
////        for(Object o: recordsAfterParsed)
////        System.out.println(o);
//
//    }
//    private static String parseToDateTime(String s) {
//
//    }

}



