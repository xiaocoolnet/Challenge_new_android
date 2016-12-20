package com.example.chy.challenge.Findpersoanl.mine.setup;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.switchload.SwitchButton;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class Message_remind extends Activity implements View.OnClickListener{
    private WaveView back;
    private SwitchButton remind_zhiweiyaoqing,remind_toudifankui,remind_liaotian;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context mContext;
    private String isopen,isfankui,isliaotian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_tip);
        mContext = this;
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        remind_zhiweiyaoqing = (SwitchButton) findViewById(R.id.remind_zhiweiyaoqing);
        remind_toudifankui = (SwitchButton) findViewById(R.id.remind_toudifankui);
        remind_liaotian = (SwitchButton) findViewById(R.id.remind_liaotian);
        preferences=getSharedPreferences("NewsRemind", Context.MODE_PRIVATE);
        editor=preferences.edit();

        remind_zhiweiyaoqing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putString("isopen","true");
                    Log.e("isopen","-------------->true");
                    editor.commit();
                }else{
                    editor.putString("isopen","false");
                    Log.e("isopen","-------------->false");
                    editor.commit();
                }
            }
        });
        remind_toudifankui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putString("isfankui","true");
                    Log.e("isfankui","-------------->true");
                    editor.commit();
                }else{
                    editor.putString("isfankui","false");
                    Log.e("isfankui","-------------->false");
                    editor.commit();
                }
            }
        });
        remind_liaotian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putString("isliaotian","true");
                    Log.e("isliaotian","-------------->true");
                    editor.commit();
                }else{
                    editor.putString("isliaotian","false");
                    Log.e("isliaotian","-------------->false");
                    editor.commit();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isopen = preferences.getString("isopen",null);
        isfankui = preferences.getString("isfankui",null);
        isliaotian = preferences.getString("isliaotain",null);
        if (isopen != null &&isopen.length() > 0){
            if ("false".equals(isopen)){
                remind_zhiweiyaoqing.setChecked(false);
            }else{
                remind_zhiweiyaoqing.setChecked(true);
            }
        }else {
            remind_zhiweiyaoqing.setChecked(false);
        }
        if (isfankui != null&&isfankui.length() > 0){
            if ("false".equals(isopen)){
                remind_toudifankui.setChecked(false);
            }else{
                remind_toudifankui.setChecked(true);
            }
        }else { remind_toudifankui.setChecked(true);}
        if (isliaotian != null&&isliaotian.length() > 0){
            if ("false".equals(isopen)){
                remind_liaotian.setChecked(false);
            }else{
                remind_liaotian.setChecked(true);
            }
        }else{
            remind_liaotian.setChecked(true);
        }
    }
}
