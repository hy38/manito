package com.js.example.manito;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JSONProcessing {
    /* Variables */
    private String response;
    private static String yesterday;    //  remove static keyword when deleting main
    private int[] returnArray = new int[2]; //  index 0 : big, index 1 : small
    private int biggerCategory = 0, smallerCategory = 0;    //  default value == 0
    /* Constructors */
    public JSONProcessing(String API_Data, String yesterday) {
        this.response = API_Data;
        this.yesterday = yesterday;
    }

    /* Methods */
    public String todayDate(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        cal.add(cal.DATE, 1);
        String today = date.format(cal.getTime());
        return  today;
    }
    public int[] returnBigAndSmallCategories(String[] args) {

//        String prefix = "{\"startDate\":\"2018-08-01\",\"endDate\":\"" + yesterday + "\", \"timeUnit\":\"month\"";
        String prefix = "{\"startDate\":\"2018-08-01\",\"endDate\":\"" + todayDate() + "\",\"timeUnit\":\"month\"";
//        String prefix = "{\"startDate\":\"2018-08-01\",\"endDate\":\"2019-01-19\",\"timeUnit\":\"month\"";
        String parts[], string_Ratio1, string_Ratio2;
        float float_ratio1, float_ratio2;
       /* if (testStr.startsWith(prefix)) {   //  1월의 ratio들의 값을 구하는 과정.
            testStr = testStr.substring(prefix.length());
            parts = testStr.split(",");*/
        if (response.startsWith(prefix)) {   //  1월의 ratio들의 값을 구하는 과정.
            response = response.substring(prefix.length());
            parts = response.split(",");

            /*
              ratio값을 substring함수를 이용하여 float값만 파싱

              parts[14] == 첫번째 ratio (2019년 1월기준)
              parts[28] == 두번째 ratio (2019년 1월기준)
              나중에 수식화해야함. yesterday의 month값과 2018년 8월의 month값의 차이 이용하여 수식화.
            */
            string_Ratio1 = parts[14].substring(8, 12);
            string_Ratio2 = parts[28].substring(8, 12);

            //  String값을 float으로 변환
            float_ratio1 = Float.parseFloat(string_Ratio1);
            float_ratio2 = Float.parseFloat(string_Ratio2);

            //  float이 잘 변환되었는지 test
            System.out.println(float_ratio1);
            System.out.println(float_ratio2);

            //  float끼리의 크기 비교
            //  args[4] == param1, args[5] == param2

            if (float_ratio1 >= float_ratio2) {
                returnArray[0] = biggerCategory = Integer.parseInt(args[4]);
                returnArray[1] = smallerCategory = Integer.parseInt(args[5]);

            } else if (float_ratio1 < float_ratio2) {
                returnArray[0] = biggerCategory = Integer.parseInt(args[5]);
                returnArray[1] = smallerCategory = Integer.parseInt(args[4]);
            }

            System.out.println(biggerCategory);
            System.out.println(smallerCategory);
        }
        return returnArray;

    }
}