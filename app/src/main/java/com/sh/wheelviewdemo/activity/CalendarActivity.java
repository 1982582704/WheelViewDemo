package com.sh.wheelviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.sh.wheelviewdemo.R;

import java.text.SimpleDateFormat;

/**
 * 日历
 */
public class CalendarActivity extends Activity {

    private CalendarView calendar;

    //年/月/日
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendar);

        Long nowTime = calendar.getDate();
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日hh:mm:ss");
        String time = f.format(nowTime);
        System.out.println("-------------" + time);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int arg1,
                                            int arg2, int arg3) {
                arg2 = arg2 + 1;
                year = arg1;
                month = arg2;
                day = arg3;
                System.out.println("-------------" + arg1 + "-" + arg2 + "-"
                        + arg3);
            }
        });
    }
}
