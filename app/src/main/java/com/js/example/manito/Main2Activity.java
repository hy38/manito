package com.js.example.manito;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    /* Variables */
    //  args to give APIExamDatalabTrend.main()
    String[] args = new String[6];
    //  Age
    RadioGroup rg;
    // Gender
    CheckBox cb_Gender_f;
    CheckBox cb_Gender_m;
    CheckBox cb_Gender_all;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //  Age
        rg = (RadioGroup) findViewById(R.id.radioGroup);

        // Gender
        cb_Gender_f = (CheckBox) findViewById(R.id.checkBox2);
        cb_Gender_m = (CheckBox) findViewById(R.id.checkBox3);
        cb_Gender_all = (CheckBox) findViewById(R.id.checkBox);

        // Name
        cb_Name_FashionClothings = (CheckBox) findViewById(R.id.checkBox17);
        cb_Name_Cosmetics = (CheckBox) findViewById(R.id.checkBox16);
        cb_Name_Foods = (CheckBox) findViewById(R.id.checkBox15);
        cb_Name_LifeHealth = (CheckBox) findViewById(R.id.checkBox14);
        cb_Name_TripCulture = (CheckBox) findViewById(R.id.checkBox13);

        //  Confirm Button

        btn_confirm = (Button) findViewById(R.id.button111);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("알림", "확인");

                try {
                    //  Age
                    int id = rg.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(id);
                    args[0] = rb.getText().toString().substring(0, 2);
                    ageFlag = true;

                    // Gender : empty String means both gender.
                    if (cb_Gender_all.isChecked()) {
                        args[1] = "";
                        genderFlag = true;
                    }
                    if (cb_Gender_f.isChecked()) {
                        args[1] = "f";
                        genderFlag = true;
                    }
                    if (cb_Gender_m.isChecked()) {
                        args[1] = "m";
                        genderFlag = true;
                    }
//            else throw new IOException();

                    //  Name
                    if (args[2] == null) {
                        if (cb_Name_FashionClothings.isChecked()) {
                            args[2] = "패션의류";
                            args[4] = "50000000";
                        }
                        else if (cb_Name_Cosmetics.isChecked()) {
                            args[2] = "화장품/미용";
                            args[4] = "50000002";
                        }
                        else if (cb_Name_Foods.isChecked()) {
                            args[2] = "식품";
                            args[4] = "50000006";
                        }
                        else if (cb_Name_LifeHealth.isChecked()) {
                            args[2] = "생활/건강";
                            args[4] = "50000008";
                        }
                        else if (cb_Name_TripCulture.isChecked()) {
                            args[2] = "여행/문화";
                            args[4] = "50000009";
                        }

                        nameFlag = true;
                    }
                    if (args[2] != null && args[3] == null) {  //  args[2]가 채워져있고, args[3]이 비어있으면,
                        if (cb_Name_FashionClothings.isChecked() && !args[2].equals("패션의류")) {
                            args[3] = "패션의류";    //  체크가 되어있고, args[2]에 이미 저장되어있지 않으면
                            args[5] = "50000000";
                        }
                        if (cb_Name_Cosmetics.isChecked() && !args[2].equals("화장품/미용")) {
                            args[3] = "화장품/미용";
                            args[5] = "50000002";
                        }
                        if (cb_Name_Foods.isChecked() && !args[2].equals("식품")) {
                            args[3] = "식품";
                            args[5] = "50000006";
                        }
                        if (cb_Name_LifeHealth.isChecked() && !args[2].equals("생활/건강")) {
                            args[3] = "생활/건강";
                            args[5] = "50000008";
                        }
                        if (cb_Name_TripCulture.isChecked() && !args[2].equals("여행/문화")) {
                            args[3] = "여행/문화";
                            args[5] = "50000009";
                        }

                        nameFlag = true;
                    } else {   //  Exceptions
                        throw new IOException();
                    }

                    for(int i=0; i<args.length; i++) {
                        System.out.println(args[i]);
                    }

                } catch (IOException e) {
                    System.out.println("CheckBox Error");
                }

                if (!ageFlag) System.out.println("나이를 선택하세요.");
                if (!genderFlag) System.out.println("성별을 선택하세요.");
                if (!nameFlag) System.out.println("관심사를 선택하세요.");
                if (ageFlag && genderFlag && nameFlag) {
                    NaverShoppingTask mNaverShoppingTask = new NaverShoppingTask();
                    mNaverShoppingTask.execute();
                }

            }   // end onClick
        }); //  end setOnClickListener

    }   //  end of onCreate

    public class NaverShoppingTask extends AsyncTask<String[], Void, String> {
        //  Parameters : AsyncTask<doInBackground()의 변수 종류, onProgressUpdate()에서 사용할 변수 종류, onPostExecute()에서 사용할 변수종류>
        @Override
        public String doInBackground(String[]... String) {
            APIExamDatalabTrend.main(args);
            return null;
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }   //  end of NaverShoppingTask
}   //  end of Main2Activity.java
