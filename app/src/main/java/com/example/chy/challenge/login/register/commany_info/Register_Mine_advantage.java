package com.example.chy.challenge.login.register.commany_info;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class Register_Mine_advantage extends Activity implements View.OnClickListener{
    private static final int PUBLISHEDUCATION = 1;
    private static final int GETMYRESUME = 2;
    private Context mContext;
    private UserInfoBean infobean;
    private WaveView back,submit;
    private ProgressDialog dialog;
    private EditText advantage_content;
    private Intent intent;
    private TextView look_other_personal,txtlength;
    private int num = 150;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case PUBLISHEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                dialog.dismiss();
                                intent = new Intent(mContext,Register_Mine_intention.class);
                                startActivity(intent);
                                finish();
                            }else{
                                dialog.setMessage("提交失败..");
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
                                Toast.makeText(mContext, "请重新提交", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        dialog.setMessage("网络错误..");
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
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case GETMYRESUME:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject josnobj = json.getJSONObject("data");
                                String advantage = josnobj.getString("advantage");
                                if (advantage != null&&advantage.length() > 0){
                                    advantage_content.setText(advantage);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.resume_myadvantage);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit = (WaveView) findViewById(R.id.submit_mine_advantage);
        submit.setOnClickListener(this);
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        advantage_content = (EditText) findViewById(R.id.advantage_content);
        look_other_personal = (TextView) findViewById(R.id.look_other_personal);
        look_other_personal.setOnClickListener(this);
        txtlength = (TextView) findViewById(R.id.txtlength);
        advantage_content.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
                System.out.println("s="+s);
            }
            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                txtlength.setText("" + number);
                selectionStart = advantage_content.getSelectionStart();
                selectionEnd = advantage_content.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    advantage_content.setText(s);
                    advantage_content.setSelection(tempSelection);//设置光标在最后
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
           case R.id.submit_mine_advantage:
               if (advantage_content.getText().toString() != null&&advantage_content.getText().toString().length() > 0) {
                   if (NetBaseUtils.isConnnected(mContext)) {
                       dialog.setMessage("正在提交..");
                       dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                       dialog.show();
//                    userid,school学校,major专业,degree学历,time时间段,experience在校经历
                       new UserRequest(mContext, handler).PUBLISHADVANTAGE(infobean.getUserid(), advantage_content.getText().toString(), PUBLISHEDUCATION);
                   } else {
                       Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                   }
               }else{
                   Toast.makeText(mContext, "请输入优势描述", Toast.LENGTH_SHORT).show();
               }
//               intent = new Intent(mContext,Register_Mine_intention.class);
//               startActivity(intent);
               break;
           case R.id.look_other_personal:
               //看看别人怎么写
               Toast.makeText(mContext, "暂未开通此功能", Toast.LENGTH_SHORT).show();
               break;
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getinfo();
    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYADVANTAGE(infobean.getUserid(), GETMYRESUME);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
