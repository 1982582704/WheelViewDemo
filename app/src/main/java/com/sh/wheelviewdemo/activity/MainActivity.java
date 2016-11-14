package com.sh.wheelviewdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sh.wheelviewdemo.R;

/**
 * 作用:三级联动
 * autour: Sh
 * date: 2016/11/14
 */
public class MainActivity extends Activity {

    private ListView listview;

    private MyAdapter adapter;
    private static String[] list = new String[]{"全国省市区", "时间"};
    private static Class[] classes = new Class[]{ProvincialCityActivity.class, TimeActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //沉浸顶部状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //沉浸底部虚拟键
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().setStatusBarColor();
        }
        listview = (ListView) findViewById(R.id.listview);
        adapter = new MyAdapter();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this, classes[i]));
            }
        });

    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(MainActivity.this);
            textView.setTextColor(Color.BLUE);
            textView.setTextSize(25);
            textView.setGravity(Gravity.CENTER);
            textView.setText(list[i]);
            return textView;
        }
    }
}
