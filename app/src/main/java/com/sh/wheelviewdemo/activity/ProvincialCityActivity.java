package com.sh.wheelviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sh.wheelviewdemo.R;
import com.sh.wheelviewdemo.pickerview.lib.OptionsPopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * 全国省市区
 */
public class ProvincialCityActivity extends Activity implements OptionsPopupWindow.OnOptionsSelectListener, View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincial_city);
        textView = (TextView) findViewById(R.id.et);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getWheelList();
        OptionsPopupWindow popupWindow = new OptionsPopupWindow(ProvincialCityActivity.this);
        popupWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);//textView
        popupWindow.setPicker(ProvinceList, CityList, CountyList, true);
        popupWindow.setOnoptionsSelectListener(this);
        popupWindow.setCyclic(false);
    }

    @Override
    public void onOptionsSelect(int options1, int option2, int options3) {
        textView.setText(ProvinceList.get(options1) + CityList.get(options1).get(option2) + CountyList.get(options1).get(option2).get(options3));
        Toast.makeText(ProvincialCityActivity.this, "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    // 城区数组
    ArrayList<String> ProvinceList = new ArrayList<String>();
    ArrayList<ArrayList<String>> CityList = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<ArrayList<String>>> CountyList = new ArrayList<ArrayList<ArrayList<String>>>();

    private void getWheelList() {
        try {
            // 获取json文件输入流
            InputStream is = getResources().getAssets().open("china_address.json");

            // 将json文件读入为一个字符串
            byte[] bytearray = new byte[is.available()];
            is.read(bytearray);
            String address_json = new String(bytearray, "UTF-8");

            // 将json转化为JSONArray对象,这是所有省的JSONArray
            JSONArray jsonArraySheng = new JSONArray(address_json);

            // 遍历这个JSONArray对象
            for (int i = 0; i < jsonArraySheng.length(); i++) {
                // 取出第i个省对象，并将其转化为JSONObject对象
                JSONObject jsonObjectSheng = jsonArraySheng.getJSONObject(i);
                // 将省的名字存入一维数组
                StringBuffer provincename = new StringBuffer(jsonObjectSheng.getString("areaName"));
                ProvinceList.add(provincename.toString());
                // 存储第i个省的城市名的数组
                ArrayList<String> tempj = new ArrayList<String>();
                // 存储第i个省的所有城市的城区名的二维数组
                ArrayList<ArrayList<String>> tempk = new ArrayList<ArrayList<String>>();
                // 取出第i个省对象中的城市数组，并将其转化为JSONArray对象
                JSONArray jsonArrayShi = jsonObjectSheng.getJSONArray("cities");
                // 遍历第i个省的城市JSONArray
                for (int j = 0; j < jsonArrayShi.length(); j++) {
                    // 取出第i个省的第j个市，并将其转化为JSONObject对象
                    JSONObject jsonObjectShi = jsonArrayShi.getJSONObject(j);
                    // 将市的名字存入第i个省的城市名数组
                    StringBuffer cityname = new StringBuffer(jsonObjectShi.getString("areaName"));
                    tempj.add(cityname.toString());
                    // 存储第i个省第j个市的城区名的数组
                    ArrayList<String> tempkk = new ArrayList<String>();
                    // 取出第i个省第j个市中的城区数组，并将其转化为JSONArray对象
                    JSONArray jsonArrayQu = jsonObjectShi.getJSONArray("counties");
                    // 遍历第i个省第j个市的城区JSONArray
                    for (int k = 0; k < jsonArrayQu.length(); k++) {
                        // 第i个省第j个市第k个区
                        JSONObject jsonObjectQu = jsonArrayQu.getJSONObject(k);
                        // 名字存入数组
                        StringBuffer countyname = new StringBuffer(jsonObjectQu.getString("areaName"));
                        tempkk.add(countyname.toString());
                    }
                    // 第i个省第j个市的城区名的数组添加到第i个省的所有城市的城区名的二维数组
                    tempk.add(tempkk);
                }
                CityList.add(tempj);
                CountyList.add(tempk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
