package com.sh.wheelviewdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sh.wheelviewdemo.R;
import com.sh.wheelviewdemo.pickerview.lib.TimePopupWindow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 时间
 * 时区转换  Wed Oct 19 17:21:00 GMT+08:00 2016
 * <p>
 * 地址：http://www.chengxuyuans.com/Android/83452.html
 */
public class TimeActivity extends Activity implements View.OnClickListener, TimePopupWindow.OnTimeSelectListener {

    private TextView editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        editText = (TextView) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);
        editText.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    TimePopupWindow.Type type;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Intent intent = new Intent(TimeActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.et:
                // TYPE四种选择模式，年月日时分，年月日，时分，月日时分(可改变模式)
                type = TimePopupWindow.Type.ALL;
                TimePopupWindow popupWindow = new TimePopupWindow(TimeActivity.this, type);
                popupWindow.showAtLocation(editText, Gravity.BOTTOM, 0, 0);
                popupWindow.setOnTimeSelectListener(this);
                popupWindow.setCyclic(true);//是否循环
                break;
        }
    }

    @Override
    public void onTimeSelect(Date date) {
        //String str = "Wed Oct 19 17:21:00 GMT+08:00 2016";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US);
        try {
            String date1 = sdf.format(date);
            Date da = sdf.parse(date1);
            if (type == TimePopupWindow.Type.ALL) {//年月日时分
                sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                Log.i("TAG", "date1 = " + date);
                Log.i("TAG", "sdf.format(date1)-- " + sdf.format(da));
                editText.setText(sdf.format(da));
            } else if (type == TimePopupWindow.Type.YEAR_MONTH_DAY) {//年月日
                sdf = new SimpleDateFormat("yyyy年MM月dd日");
                Log.i("TAG", "date1 = " + date);
                Log.i("TAG", "sdf.format(date1)-- " + sdf.format(da));
                editText.setText(sdf.format(da));
            } else if (type == TimePopupWindow.Type.MONTH_DAY_HOUR_MIN) {//月日时分
                sdf = new SimpleDateFormat("MM月dd日 HH:mm");
                Log.i("TAG", "date1 = " + date);
                Log.i("TAG", "sdf.format(date1)-- " + sdf.format(da));
                editText.setText(sdf.format(da));
            } else if (type == TimePopupWindow.Type.HOURS_MINS) {//时分
                sdf = new SimpleDateFormat("HH:mm");
                Log.i("TAG", "date1 = " + date);
                Log.i("TAG", "sdf.format(date1)-- " + sdf.format(da));
                editText.setText(sdf.format(da));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
