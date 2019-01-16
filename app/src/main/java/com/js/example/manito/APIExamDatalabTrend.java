package com.js.example.manito;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class APIExamDatalabTrend {

    public static void main(String[] args) {
        String clientId = "DwcaibyBwIZ1e6LhFXI5";//애플리케이션 클라이언트 아이디 값";
        String clientSecret = "lqvMc6UEBC";//애플리케이션 클라이언트 시크릿 값";

        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();

            cal.add(cal.DATE, -1);
            String yesterday = date.format(cal.getTime());
//            System.out.println(yesterday);

            String apiURL = "https://openapi.naver.com/v1/datalab/shopping/categories";
//            유저가 변경할 수 있는 검색조건사항들 : 카테고리
//            검색은 무조건 어제날짜까지 1달간격으로 진행됨.
//            String body = "{\"startDate\":\"2018-08-01\",\"endDate\":\"2019-01-15\",\"timeUnit\":\"month\",\"category\":[{\"name\":\"패션의류\",\"param\":[\"50000000\"]},{\"name\":\"화장품/미용\",\"param\":[\"50000002\"]}],\"device\":\"mo\",\"ages\":[\"20\",\"30\"],\"gender\":\"\"}";
            String body = "{\"startDate\":\"2018-08-01\",\"endDate\":\""+yesterday+"\", \"timeUnit\":\"month\",\"category\":[{\"name\":\"패션의류\",\"param\":[\"50000000\"]},{\"name\":\"화장품/미용\",\"param\":[\"50000002\"]}],\"device\":\"mo\",\"ages\":[\"20\",\"30\"],\"gender\":\"\"}";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}