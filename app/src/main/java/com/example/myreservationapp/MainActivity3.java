package com.example.myreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private TextView day;
    private TextView time;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        day = (TextView) findViewById(R.id.dateView);
        time = (TextView) findViewById(R.id.timeView);
        button = (Button) findViewById(R.id.button2);

        //인텐트로 받은 데이터를 변수에 저장
        int year = intent.getIntExtra("year",1);
        int month = intent.getIntExtra("month",1);
        int date = intent.getIntExtra("date",1);
        int hour = intent.getIntExtra("hour",1);
        int min = intent.getIntExtra("min",1);
        String ampm = intent.getStringExtra("ampm");
        String ap;
        if(ampm.equals("AM")){
            if(hour==0){ //'0시'표기 방지
                hour += 12;
            }
            ap = "오전";
        }
        else{
            hour -= 12;
            ap = "오후";
        }

        day.setText(year + "년 " + month + "월 " + date + "일");
        time.setText(ap + " " + hour + "시 " + min + "분 ");

        //예약시간 변경하기 버튼 눌렀을 때 동작
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHandler(v);
            }
        });
    }

    //맨 처음 액티비티로 전환
    public void OnClickHandler(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}