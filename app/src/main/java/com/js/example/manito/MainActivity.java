package com.js.example.manito;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
=======

>>>>>>> 64de9e1e785ac147ab0c0ca9046532af5f04fd3f
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("알림", "확인");
//                NaverShoppingTask mNaverShoppingTask = new NaverShoppingTask();
//                mNaverShoppingTask.execute();

            }

            public void on1(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }

        });

<<<<<<< HEAD
   /* public class NaverShoppingTask extends AsyncTask<String[], Void, String> {
//  Parameters : AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
        @Override
        public String doInBackground(String[]... String) {
            APIExamDatalabTrend.main(new String[]{"10"});
            return null;
=======
/*
        public class NaverShoppingTask extends AsyncTask<String[], Void, String> {

            @Override
            public String doInBackground(String[]... String) {
//            APIExamDatalabTrend.main(new String[]{""});
                return null;

            }
>>>>>>> 64de9e1e785ac147ab0c0ca9046532af5f04fd3f

            @Override
            public void onPostExecute(String result) {
                super.onPostExecute(result);
            }
        }

<<<<<<< HEAD
        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }*/
=======
    */
    }
>>>>>>> 64de9e1e785ac147ab0c0ca9046532af5f04fd3f
}
