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
import android.widget.EditText;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class Old_password extends Activity implements View.OnClickListener {
    private static final int KEY_GET_CODE = 1;
    private Context mContext;
    private EditText oldpass_et_password;
    private WaveView oldpass_next, back;
    private UserInfoBean infobean;
    private Intent intent;
    private ProgressDialog dialog;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case KEY_GET_CODE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject jsonobj = new JSONObject(json.getString("data"));
                                infobean.setUserid(jsonobj.getString("userid"));
                                infobean.setUsertype(jsonobj.getString("usertype"));
                                infobean.setPhone(jsonobj.getString("phone"));
                                infobean.setDecicestate(jsonobj.getString("devicestate"));
                                intent = new Intent(mContext, NewPassword.class);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }else{
                                Toast.makeText(mContext, "原始密码错误，请重新输入", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(mContext, "请填写原始密码", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.old_password);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        oldpass_next = (WaveView) findViewById(R.id.oldpass_next);
        oldpass_next.setOnClickListener(this);
        oldpass_et_password = (EditText) findViewById(R.id.oldpass_et_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.oldpass_next:
                if (oldpass_et_password.getText().toString() != null && oldpass_et_password.getText().toString().length() > 0) {
                    //验证原密码接口
                    if (NetBaseUtils.isConnnected(mContext)) {
                        dialog.setMessage("正在验证...");
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.show();
                        new UserRequest(mContext, handler).OLDPASSWORD("17718320703",oldpass_et_password.getText().toString(), KEY_GET_CODE);
                    } else {
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请填写原始密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
