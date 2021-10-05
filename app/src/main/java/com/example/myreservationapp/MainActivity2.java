package com.example.myreservationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private TimePicker timePicker1;
    private TextView time;
    private String format = "";
    private Button button;
    private Calendar calendar;
    int year,month,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //인텐트로 받은 데이터 저장
        Intent intent = getIntent();
        year = intent.getIntExtra("year",1);
        month = intent.getIntExtra("month",1);
        date = intent.getIntExtra("date",1);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker);
        time = (TextView) findViewById(R.id.timeView);
        button = (Button) findViewById(R.id.button);
        calendar = Calendar.getInstance();

        //현재 시간 보여주기
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour,min);

        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                displayTime(view);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHandler(v);
            }
        });
    }

    //다음 액티비티로 데이터 넘기기
    public void OnClickHandler(View view)
    {
        int hour = timePicker1.getHour();
        int min = timePicker1.getMinute();

        Intent intent = new Intent(this, MainActivity3.class);

        intent.putExtra("year",year);
        intent.putExtra("month",month);
        intent.putExtra("date",date);
        intent.putExtra("hour", hour);
        intent.putExtra("min", min);
        intent.putExtra("ampm", format);

        startActivity(intent);
    }

    public void displayTime(View view){
        int hour = timePicker1.getHour();
        int min = timePicker1.getMinute();
        showTime(hour,min);
    }

    //텍스트뷰에 선택된 시간 보여주기
    public void showTime(int hour, int min){
        if(hour == 0){
            hour+=12;
            format = "AM";
        }
        else if(hour == 12){
            format = "AM";
        }
        else if(hour > 12){
            hour-=12;
            format = "PM";
        }
        else{
            format = "AM";
        }

        time.setText(String.format("%02d : %02d %s", hour, min, format));
    }
}