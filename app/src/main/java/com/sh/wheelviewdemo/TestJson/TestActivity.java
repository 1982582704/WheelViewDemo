package com.sh.wheelviewdemo.TestJson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sh.wheelviewdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TestActivity extends Activity {

    private TextView writeText, readText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        readText = (TextView) findViewById(R.id.readJsonText);
        writeText = (TextView) findViewById(R.id.writeJsonText);

         /*读取Json数据*/
        findViewById(R.id.readJsioBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*获取到assets文件下的TExt.json文件的数据，并以输出流形式返回。*/
                InputStream is = TestActivity.this.getClass().getClassLoader().getResourceAsStream("assets/" + "Text.json");
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    while ((line = reader.readLine()) != null) {
                        // stringBuilder.append(line);
                        stringBuilder.append(line);
                    }
                    reader.close();
                    reader.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject person = null;
                    try {
                        person = new JSONObject(stringBuilder.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray infArray = person.getJSONArray("inf");
                    for (int i = 0; i < infArray.length(); i++) {
                        JSONObject inf_Array = infArray.getJSONObject(i);
                        readText.append("name:" + inf_Array.getString("name") + "\n");
                        readText.append("IdCard:" + inf_Array.getString("IdCard"));
                        readText.append("age:" + inf_Array.getInt("age"));
                        readText.append("married:" + inf_Array.getBoolean("married"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
              /*创建Json数据并显示*/
        findViewById(R.id.writeJsioBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject info = new JSONObject();
                    info.put("number", 200);

                    JSONArray array = new JSONArray();

                    //直辖市
                    JSONObject arr_1 = new JSONObject();
                    arr_1.put("zxs", "北京市");
                    arr_1.put("area", "朝阳区");
//                    arr_1.put("age", "其他区");

                    //省
                    JSONObject arr_2 = new JSONObject();
                    arr_2.put("province", "河北省");
                    arr_2.put("city", "石家庄");
                    arr_2.put("area", "裕华区");

                    array.put(0, arr_1);
                    array.put(1, arr_2);
                    info.put("info", array);
                    writeText.setText(info.toString());
                    Log.i("test", info.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
