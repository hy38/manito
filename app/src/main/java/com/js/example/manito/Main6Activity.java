package com.js.example.manito;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Main6Activity extends AppCompatActivity {
    TextView textView; //결과를 띄어줄 TextView
    TextView reload; //reload버튼
    Elements contents;
    Document doc = null;
    String Top10;//결과를 저장할 문자열변수
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        textView = (TextView) findViewById(R.id.textBox);
        reload = (TextView) findViewById(R.id.reload);

        reload.setOnClickListener(new View.OnClickListener() {//onclicklistener를 연결하여 터치시 실행됨
            @Override
            public void onClick(View v) {
                new AsyncTask() {//AsyncTask객체 생성
                    @Override
                    protected Object doInBackground(Object[] params) {
                        try {
//                            doc = Jsoup.connect("https://www.naver.com/").get(); //naver페이지를 불러옴
                            doc = Jsoup.connect("https://datalab.naver.com/shoppingInsight/sCategory.naver?cid=50000000/").get(); //naver페이지를 불러옴
                            contents = doc.select("div.rank_top1000_scroll");//셀렉터로 span태그중 class값이 ah_k인 내용을 가져옴
//                            contents = doc.select("ul.rank_top1000_list");//셀렉터로 span태그중 class값이 ah_k인 내용을 가져옴
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Top10 = "";
                        int cnt = 0;//숫자를 세기위한 변수

                        for(Element el : contents.select("li")) {    // 하위 뉴스 기사들을 for문 돌면서 출력
                            cnt++;
                            System.out.println(el.text());
                            Top10 += cnt+". "+el.text() + "\n";
                            if(cnt == 10)//10위까지 파싱하므로
                                break;
                        }
//                        for(Element element: contents) {
//                            cnt++;
//                            Top10 += cnt+". "+element.text() + "\n";
//                            System.out.println(element.text());
//                            if(cnt == 10)//10위까지 파싱하므로
//                                break;
//                        }
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        Log.i("TAG",""+Top10);
                        textView.setText(Top10);
                    }
                }.execute();
            }
        });
    }
}