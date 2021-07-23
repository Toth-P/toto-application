package data;

public class ParseService {


    public String getParsableDate(String s) {
        String date = s.replaceAll("\\.", "-");
        date = date.substring(0, date.length() - 1);
        return date;
    }
}
