package com.example.myreservationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button button;
    private TextView text;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        button = (Button) findViewById(R.id.button);
        text = findViewById(R.id.dateView);

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int date = datePicker.getDayOfMonth();
        showTime(year,month,date);

        //datePicker위젯에서 날짜를 선택했을 때 텍스트뷰 변경
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                displayTime(view);
            }
        });

        //선택 버튼 클릭 시
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickHandler(v);
            }
        });
    }

    //선택한 날짜데이터를 다음 액티비티로 보냄
    public void OnClickHandler(View view){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int date = datePicker.getDayOfMonth();

        Intent intent = new Intent(this, MainActivity2.class);

        intent.putExtra("year", year);
        intent.putExtra("month", month+1);
        intent.putExtra("date", date);

        startActivity(intent);
    }

    //선택된 날짜를 showTime함수로 보내기
    public void displayTime(View view){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int date = datePicker.getDayOfMonth();

        showTime(year,month,date);
    }

    //선택된 날짜를 텍스트뷰에 보여주기
    public void showTime(int year, int month, int date){
        text.setText(String.format("%d/%d/%d",year,month+1,date));
    }

}