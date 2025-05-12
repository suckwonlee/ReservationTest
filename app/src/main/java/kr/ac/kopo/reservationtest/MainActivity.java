package kr.ac.kopo.reservationtest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    RadioGroup rg;
    RadioButton rbDate,rbTime;
    DatePicker calendar;
    TimePicker timePicker;
    TextView textResult;
//    Button btnStart,btnDone;
    int selectedYear, selectedMonth, selectedDay;
    int selectedHour,selectedMinute;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chronometer = findViewById(R.id.chrono);
        rbDate=findViewById(R.id.rb_date);
        rbTime=findViewById(R.id.rb_time);
        calendar=findViewById(R.id.calendar);
        timePicker=findViewById(R.id.time_pick);
        textResult=findViewById(R.id.text_result);
//        btnDone=findViewById(R.id.btn_done);

        calendar.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        rbDate.setOnClickListener(rbListener);
        rbTime.setOnClickListener(rbListener);
        chronometer.setOnClickListener(chronoListener);
        textResult.setOnLongClickListener(textListener);
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                selectedYear=year;
//                selectedMonth=month;
//                selectedDay=dayOfMonth;
//            }
//        });
    }
    View.OnLongClickListener textListener=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            chronometer.stop();
            chronometer.setTextColor(Color.BLUE);
            selectedYear=calendar.getYear();
            selectedMonth=calendar.getMonth();
            selectedHour=timePicker.getHour();
            selectedMinute=timePicker.getMinute();
            textResult.setText(selectedYear+"년 "+selectedMonth+"월 "+selectedDay+"일 "+selectedHour+"시 "+selectedMinute+"분 예약완료됨");
            return true;
        }
    };
    View.OnClickListener chronoListener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            chronometer.setTextColor(Color.RED);
        }
    };

    View.OnClickListener rbListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calendar.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            RadioButton rbEvent=(RadioButton) v;
            if(rbEvent==rbTime){
                calendar.setVisibility(View.VISIBLE);
            }else{
                timePicker.setVisibility(View.VISIBLE);
            }
        }
    };
}