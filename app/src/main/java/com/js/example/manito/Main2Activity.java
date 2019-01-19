package com.js.example.manito;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    /* Variables */
    //  args to give APIExamDatalabTrend.main()
    String[] args = new String[6];
    //  Age
    CheckBox cb_Age_10;
    CheckBox cb_Age_20;
    CheckBox cb_Age_30;
    CheckBox cb_Age_40;
    CheckBox cb_Age_50;
    CheckBox cb_Age_60_plus;
    // Gender
    CheckBox cb_Gender_f;
    CheckBox cb_Gender_m;
    // Name
    CheckBox cb_Name_FashionClothings;
    CheckBox cb_Name_Cosmetics;
    CheckBox cb_Name_Foods;
    CheckBox cb_Name_LifeHealth;
    CheckBox cb_Name_TripCulture;
    // Param
    String param1;
    String param2;
    //  Confirm Button
    Button btn_confirm;
    //  Flags
    boolean ageFlag;
    boolean nameFlag;
    boolean genderFlag;

    /* End of Variables */

    public class NaverShoppingTask extends AsyncTask<String[], Void, String> {
        //  Parameters : AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
        @Override
        public String doInBackground(String[]... String) {
//            APIExamDatalabTrend.main(new String[]{"10"});
            APIExamDatalabTrend.main(args);
            return null;
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //  Confirm Button
        btn_confirm = (Button) findViewById(R.id.button111);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("알림", "확인");
                if(!ageFlag) System.out.println("나이를 선택하세요.");
                if(!genderFlag) System.out.println("성별을 선택하세요.");
                if(!nameFlag) System.out.println("관심사를 선택하세요.");
                if(ageFlag && genderFlag && nameFlag){
                NaverShoppingTask mNaverShoppingTask = new NaverShoppingTask();
                mNaverShoppingTask.execute();
                }

            }
        });

        //  Age
        cb_Age_10 = (CheckBox) findViewById(R.id.checkBox1);
        cb_Age_20 = (CheckBox) findViewById(R.id.checkBox4);
        cb_Age_30 = (CheckBox) findViewById(R.id.checkBox11);
        cb_Age_40 = (CheckBox) findViewById(R.id.checkBox10);
        cb_Age_50 = (CheckBox) findViewById(R.id.checkBox9);
        cb_Age_60_plus = (CheckBox) findViewById(R.id.checkBox12);
        cb_Age_10.setOnCheckedChangeListener(this);
        cb_Age_20.setOnCheckedChangeListener(this);
        cb_Age_30.setOnCheckedChangeListener(this);
        cb_Age_40.setOnCheckedChangeListener(this);
        cb_Age_50.setOnCheckedChangeListener(this);
        cb_Age_60_plus.setOnCheckedChangeListener(this);

        // Gender
        cb_Gender_f = (CheckBox) findViewById(R.id.checkBox2);
        cb_Gender_m = (CheckBox) findViewById(R.id.checkBox3);
        cb_Gender_f.setOnCheckedChangeListener(this);
        cb_Gender_m.setOnCheckedChangeListener(this);

        // Name
        cb_Name_FashionClothings = (CheckBox) findViewById(R.id.checkBox17);
        cb_Name_Cosmetics = (CheckBox) findViewById(R.id.checkBox16);
        cb_Name_Foods = (CheckBox) findViewById(R.id.checkBox15);
        cb_Name_LifeHealth = (CheckBox) findViewById(R.id.checkBox14);
        cb_Name_TripCulture = (CheckBox) findViewById(R.id.checkBox13);
        cb_Name_FashionClothings.setOnCheckedChangeListener(this);
        cb_Name_Cosmetics.setOnCheckedChangeListener(this);
        cb_Name_Foods.setOnCheckedChangeListener(this);
        cb_Name_LifeHealth.setOnCheckedChangeListener(this);
        cb_Name_TripCulture.setOnCheckedChangeListener(this);

        // Param1


        // Param2


    }   //  end of onCreate

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //  age, gender, name1, name2, param1, param2

        try {
            //  Age
            if (cb_Age_10.isChecked()){ args[0] = "10"; ageFlag = true;}
            if (cb_Age_20.isChecked()){ args[0] = "20"; ageFlag = true;}
            if (cb_Age_30.isChecked()){ args[0] = "30"; ageFlag = true;}
            if (cb_Age_40.isChecked()){ args[0] = "40"; ageFlag = true;}
            if (cb_Age_50.isChecked()){ args[0] = "50"; ageFlag = true;}
            if (cb_Age_60_plus.isChecked()){ args[0] = "60"; ageFlag = true;}
//            else throw new IOException();

            // Gender : empty String means both gender.
            if (cb_Gender_f.isChecked() && cb_Gender_m.isChecked()) { args[1] = ""; genderFlag = true;}
            if (cb_Gender_f.isChecked()) { args[1] = "f"; genderFlag = true; }
            if (cb_Gender_m.isChecked()) { args[1] = "m"; genderFlag = true; }
//            else throw new IOException();

            //  Name
            if (args[2] == null) {
                if (cb_Name_FashionClothings.isChecked()){
                    args[2] = "패션의류";
                    args[4] = "50000000";
                }
                if (cb_Name_Cosmetics.isChecked()){
                    args[2] = "화장품/미용";
                    args[4] = "50000002";
                }
                if (cb_Name_Foods.isChecked()){
                    args[2] = "식품";
                    args[4] = "50000006";
                }
                if (cb_Name_LifeHealth.isChecked()){
                    args[2] = "생활/건강";
                    args[4] = "50000008";
                }
                if (cb_Name_TripCulture.isChecked()){
                    args[2] = "여행/문화";
                    args[4] = "50000009";
                }

//                nameFlag = true;
            }
            else if (/*args[2] != null && */args[3] == null) {  //  args[2]가 채워져있고, args[3]이 비어있으면,
                if (cb_Name_FashionClothings.isChecked() && !args[2].equals("패션잡화")){
                    args[3] = "패션의류";    //  체크가 되어있고, args[2]에 이미 저장되어있지 않으면
                    args[5] = "50000000";
                }
                if (cb_Name_Cosmetics.isChecked() && !args[2].equals("화장품/미용")){
                    args[3] = "화장품/미용";
                    args[5] = "50000002";
                }
                if (cb_Name_Foods.isChecked() && !args[2].equals("식품")){
                    args[3] = "식품";
                    args[5] = "50000006";
                }
                if (cb_Name_LifeHealth.isChecked() && !args[2].equals("생활/건강")){
                    args[3] = "생활/건강";
                    args[5] = "50000008";
                }
                if (cb_Name_TripCulture.isChecked() && !args[2].equals("여행/문화")){
                    args[3] = "여행/문화";
                    args[5] = "50000009";
                }

                nameFlag = true;
            }
            else {   //  Exceptions
                throw new IOException();
            }

        } catch (IOException e) {
            System.out.println("CheckBox Error");
        }
    }

}
