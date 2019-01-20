package com.js.example.manito;

public class JSONProcessing {
    /* Variables */
    private String response;

    /* Constructor */
    public JSONProcessing() {
    }

    public JSONProcessing(String API_Data) {
        response = API_Data;
    }

    /* Methods */
    public static void main(String[] args) {
        JSONProcessing a = new JSONProcessing();
        String testStr = "{\"startDate\":\"2018-08-01\"," +
                "\"endDate\":\"2019-01-19\"," +
                "\"timeUnit\":\"month\"," +
                "\"results\":[{\"title\":\"\",\"category\":[\"50000006\"],\"data\":[{\"period\":\"2018-08-01\",\"ratio\":85.09753},{\"period\":\"2018-09-01\",\"ratio\":97.62581},{\"period\":\"2018-10-01\",\"ratio\":94.91993},{\"period\":\"2018-11-01\",\"ratio\":98.58082},{\"period\":\"2018-12-01\",\"ratio\":100},{\"period\":\"2019-01-01\",\"ratio\":82.20644}]}," +
                "{\"title\":\"�/8T\",\"category\":[\"50000009\"],\"data\":[{\"period\":\"2018-08-01\",\"ratio\":30.54581},{\"period\":\"2018-09-01\",\"ratio\":21.46524},{\"period\":\"2018-10-01\",\"ratio\":19.55394},{\"period\":\"2018-11-01\",\"ratio\":20.29133},{\"period\":\"2018-12-01\",\"ratio\":23.92203},{\"period\":\"2019-01-01\",\"ratio\":16.11354}]}]}";

//        a.JSONParse();
//        String results = testStr.split("\"results\"")[0];

//        String yesterdayTest = "\"2019-01-01\"";
//
//        String startDate = testStr.split(yesterdayTest)[0];
//        String test = testStr.split("}")[1];
//        System.out.println(startDate);
//        System.out.println(test);
        /*String startDate = testStr.split(",")[0];
        String endDate = testStr.split(",")[1];
        String timeUnit = testStr.split(",")[2];
        String result1 = testStr.split("\"title\" :")[0];
        String result2 = testStr.split("}\"")[0];

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(timeUnit);
        System.out.println(result1);
        System.out.println(result2);*/

//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(stringToParse);
//        Player p = g.fromJson(jsonString, Player.class)


    }
}
