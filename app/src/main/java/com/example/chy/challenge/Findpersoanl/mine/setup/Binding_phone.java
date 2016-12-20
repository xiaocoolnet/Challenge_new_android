package com.example.chy.challenge.Findpersoanl.mine.setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.example.chy.challenge.login.regular.RegexUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Binding_phone extends Activity implements View.OnClickListener{
    private static final int KEY_CHECK_PHONE = 1;
    private static final int CHANGE_GET_CODE_TEXT1 = 2;
    private static final int CHANGE_GET_CODE_TEXT2 = 3;
    private static final int KEY_GET_CODE = 4;
    private static final int KEY_UPDATE_PHONE = 5;
    private TextView biuding_tv_Phone,binding_et_phone,binding_et_code,tv_getCode;
    private WaveView getCode,binding_btn_submit,back;
    private Context mContext;
    private UserInfoBean infobean;
    private ProgressDialog dialog;
    private int i = 30;
    private String code;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KEY_CHECK_PHONE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                Toast.makeText(mContext,"该号码已被注册",Toast.LENGTH_SHORT).show();
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
                            }else{
                                dialog.setMessage("正在获取验证码...");
                                getVerificationCode();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
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
                case CHANGE_GET_CODE_TEXT1:
                    tv_getCode.setText("重发("+i+")");
                    dialog.dismiss();
                    break;
                case CHANGE_GET_CODE_TEXT2:
                    tv_getCode.setText("重新发送");
                    getCode.setClickable(true);
                    i = 60;
                    dialog.dismiss();
                    break;
                case KEY_GET_CODE:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonobj = new JSONObject(result);
                            String data = jsonobj.getString("data");
                            String status = jsonobj.getString("status");
                            if ("success".equals(status)){
                                JSONObject json = new JSONObject(data);
                                code = json.getString("code");
                                dialog.setMessage("发送成功");
                                Log.e("code220","+++++++++++++++"+code);
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
                            }else{
                                dialog.setMessage("发送失败");
                                Log.e("code220","+++++++++++++++"+code);
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
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
                case KEY_UPDATE_PHONE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                Toast.makeText(mContext, "修改成功",Toast.LENGTH_SHORT).show();
                                infobean.setPhone(binding_et_phone.getText().toString());
                                finish();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(mContext, "修改失败",Toast.LENGTH_SHORT).show();
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
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.phone_binding);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        biuding_tv_Phone = (TextView) findViewById(R.id.biuding_tv_Phone);//绑定的手机号
        binding_et_phone = (TextView) findViewById(R.id.binding_et_phone);//电话号码输入框
        if(infobean.getPhone().length() > 6 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < infobean.getPhone().length(); i++) {
                char c = infobean.getPhone().charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            biuding_tv_Phone.setText(sb.toString());
        }
        binding_et_code = (TextView) findViewById(R.id.binding_et_code);//验证码输入框
        getCode = (WaveView) findViewById(R.id.getCode);//获取验证码按钮
        getCode.setOnClickListener(this);
        binding_btn_submit = (WaveView) findViewById(R.id.binding_btn_submit);//提交按钮
        binding_btn_submit.setOnClickListener(this);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        tv_getCode = (TextView) findViewById(R.id.tv_getCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getCode:
                if (isCanGetCode()){
                    if (NetBaseUtils.isConnnected(mContext)){
                        dialog.setMessage("正在验证手机号码...");
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.show();
                        binding_et_code.setText("");
                        binding_et_code.clearFocus();
                        getCode.setClickable(false);
                            //验证手机号是否注册接口
                        new UserRequest(mContext, handler).CheckPhone(binding_et_phone.getText().toString(),KEY_CHECK_PHONE);
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.binding_btn_submit:
                submitviews();
                break;
            case R.id.back:
                finish();
                break;

        }
    }

    private void submitviews() {
        if (binding_et_phone.getText().toString() == null||binding_et_phone.getText().toString().length() <= 0){
            Toast.makeText(mContext,"请输入手机号码",Toast.LENGTH_SHORT).show();
        }else if (binding_et_code.getText().toString() == null||binding_et_code.getText().toString().length() <= 0){
            Toast.makeText(mContext,"请输入验证码",Toast.LENGTH_SHORT).show();
        }else if (binding_et_code.getText().toString().isEmpty()) {
            binding_et_code.setError(Html.fromHtml("<font color=#E10979>请输入验证码！</font>"));
        }else if (!code.equals(binding_et_code.getText().toString())) {
            binding_et_code.setError(Html.fromHtml("<font color=#E10979>验证码错误！</font>"));
        }else if (code == null){
            Log.i("code333","--------------->"+code);
            Toast.makeText(mContext,"请先获取验证码",Toast.LENGTH_SHORT).show();
        }else{
            //验证原密码接口
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                new UserRequest(mContext, handler).UPDATEPHONE(infobean.getUserid(),binding_et_phone.getText().toString(), code, KEY_UPDATE_PHONE);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    //正则验证手机号
    private Boolean isCanGetCode() {
        if (!RegexUtil.Mobile_phone(binding_et_phone.getText().toString())) {
            Toast.makeText(mContext,"请填写正确的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //在手机号码验证过之后显示
    private void getVerificationCode() {
        getCode.setClickable(false);
        tv_getCode.setText("重发("+i+")");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;i > 0;i--){
                    handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT1);
                    if (i <= 0){
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(CHANGE_GET_CODE_TEXT2);
            }
        }).start();
        dialog.setMessage("正在发送验证码");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        //获取验证码接口
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GetCode(binding_et_phone.getText().toString(),KEY_GET_CODE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
