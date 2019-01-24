package com.js.example.manito;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    TextView textView;  //결과를 띄어줄 TextView
    TextView reload;    //reload버튼
    Elements contents;
    Document doc = null;
    String Top10;   //결과를 저장할 문자열변수
    String[] bigSmall;
    String[] args;
    String catID;
    private RecyclerView recyclerView;
    private ArrayList<ItemObject> list = new ArrayList();

    public class NaverShoppingTask extends AsyncTask<String[], Void, String> {  //  API 호출 helper class
        //  Parameters : AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
        @Override
        public String doInBackground(String[]... String) {
//            APIExamDatalabTrend.main(args); //  args를 넘겨주면서 API 호출
            APIExamDatalabTrend API = new APIExamDatalabTrend();
            bigSmall = API.APIAccess(args);

            return null;
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }   //  end of NaverShoppingTask

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Intent prevIntent = getIntent();
//        bigSmall = prevIntent.getStringArrayExtra("big_Small");
        args = prevIntent.getStringArrayExtra("args");

        NaverShoppingTask mNaverShoppingTask = new NaverShoppingTask();
        mNaverShoppingTask.execute();


//        textView = (TextView) findViewById(R.id.textBox);
//        reload = (TextView) findViewById(R.id.reload);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

//        catID = bigSmall[0];

        new DataParsing().execute();

    }

    public class DataParsing extends AsyncTask<String, Void, Object> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //  진행다일로그 시작
            progressDialog = new ProgressDialog(Main6Activity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();
        }

        @Override
        public Void doInBackground(String[] params) {   //  네이버 쇼핑 파싱

            try {
//                doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=" + catID + "&kwdType=KWD").get(); //naver페이지를 불러옴
                if (args[4].equals("50000000"))
                    doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=50000000&kwdType=KWD").get(); //naver페이지를 불러옴
                else if (args[4].equals("50000002"))
                    doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=50000002&kwdType=KWD").get(); //naver페이지를 불러옴
                else if (args[4].equals("50000006"))
                    doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=50000006&kwdType=KWD").get(); //naver페이지를 불러옴
                else if (args[4].equals("50000008"))
                    doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=50000008&kwdType=KWD").get(); //naver페이지를 불러옴
                else if (args[4].equals("50000009"))
                    doc = Jsoup.connect("https://search.shopping.naver.com/best100v2/detail/kwd.nhn?catId=50000009&kwdType=KWD").get(); //naver페이지를 불러옴

                contents = doc.select("div.section a");
                Top10 = "";
                int cnt = 0;//숫자를 세기위한 변수

                for (Element element : contents) {
                    cnt++;
                    String myTitle = element.attr("title");
                    list.add(new ItemObject(myTitle));

                    Top10 += cnt + ". " + myTitle + "\n";
                    if (cnt == 10) {  //10위까지 파싱하므로
//                     catID = bigSmall[1];
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
//        protected void onPostExecute(Void result) {
        protected void onPostExecute(Object o) {
            //ArraList를 인자로 해서 어답터와 연결한다.

            Log.i("TAG", "" + Top10);

            MyAdapter myAdapter = new MyAdapter(list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

            progressDialog.dismiss();
        }


       /* @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i("TAG",""+Top10);
            textView.setText(Top10);
        }*/

    }
}
