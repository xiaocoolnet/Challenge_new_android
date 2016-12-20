package com.example.chy.challenge.Findpersoanl.mine.setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.Login;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 77588 on 2016/9/7.
 */
public class NewPassword extends Activity implements View.OnClickListener {
    private static final int KEY_GET_CODE = 1;
    private WaveView back, new_submit_pass;
    private EditText new_et_pass, new_et_newpass;
    private String fnewpass, senwpass;
    private Context mContext;
    private UserInfoBean infobean;
    private ProgressDialog dialog;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KEY_GET_CODE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                Toast.makeText(mContext,"修改成功", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Intent intent = new Intent(mContext, Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(mContext, "修改失败，请重新修改", Toast.LENGTH_SHORT).show();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        dialog.dismiss();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        initview();
    }

    private void initview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        new_submit_pass = (WaveView) findViewById(R.id.new_submit_pass);
        new_submit_pass.setOnClickListener(this);
        new_et_newpass = (EditText) findViewById(R.id.new_et_newpass);
        new_et_pass = (EditText) findViewById(R.id.new_et_pass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_submit_pass:
                checkviews();
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private void checkviews() {
        fnewpass = new_et_pass.getText().toString();
        senwpass = new_et_newpass.getText().toString();
        if (fnewpass == null || fnewpass.length() <= 0) {
            Toast.makeText(mContext, "新密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (senwpass == null || senwpass.length() <= 0) {
            Toast.makeText(mContext, "请再次输入新密码", Toast.LENGTH_SHORT).show();
        } else if (!fnewpass.equals(senwpass)) {
            Toast.makeText(mContext, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            //验证原密码接口
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在修改...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                new UserRequest(mContext, handler).UPDATEPASS(infobean.getPhone(), fnewpass, KEY_GET_CODE);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
