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
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.SalaryMain;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_joblocation;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_jobnature;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_jobstate;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class Register_Mine_intention extends Activity implements View.OnClickListener{
    private static final int PUBLISHINTENSION = 1;
    private static final int GETMYINTENSION = 2;
    private Context mContext;
    private UserInfoBean infoBean;
    private UserInfo info;
    private WaveView job_nature,back,submit,job_location,job_type,industry_type,want_salary,job_state;
    private TextView tv_job_nature,tv_job_location,tv_job_type,tv_industry_type,tv_want_salary,tv_job_state,textview;
    private Intent intent;
    private String S_job_nature,S_job_location,S_job_type,S_industry_type,S_want_salary,S_job_state,pagetype;
    private ProgressDialog dialog;
    private Pop_mine_joblocation poplocat;
    private Pop_mine_jobnature popnature;
    private Pop_mine_jobstate popstate;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case PUBLISHINTENSION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                dialog.dismiss();
                                intent = new Intent(mContext,SalaryMain.class);
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
                        dialog.dismiss();
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
                case GETMYINTENSION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject josnobj = json.getJSONObject("data");
                                tv_job_nature.setText(josnobj.getString("work_property")+"");
                                tv_job_location.setText(josnobj.getString("address").substring(0,2)+"");
                                tv_job_type.setText(josnobj.getString("position_type")+"");
                                tv_industry_type.setText(josnobj.getString("categories")+"");
                                tv_want_salary.setText(josnobj.getString("wantsalary")+"");
                                tv_job_state.setText(josnobj.getString("jobstate")+"");
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
        setContentView(R.layout.register_mine_intention);
        mContext = this;
        infoBean = new UserInfoBean(mContext);
        info = new UserInfo(mContext);
        poplocat = new Pop_mine_joblocation(Register_Mine_intention.this);
        popnature = new Pop_mine_jobnature(Register_Mine_intention.this);
        popstate = new Pop_mine_jobstate(Register_Mine_intention.this);
        isJob(false);
        getvuiew();
        getinfo();
    }

    private void getvuiew() {
        textview = (TextView) findViewById(R.id.textview);

        job_nature = (WaveView) findViewById(R.id.intention_job_nature);//工作性质
        job_nature.setOnClickListener(this);
        tv_job_nature = (TextView) findViewById(R.id.intention_tv_job_nature);//tv工作性质

        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit = (WaveView) findViewById(R.id.intention_submit);
        submit.setOnClickListener(this);
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);

        job_location = (WaveView) findViewById(R.id.intention_job_location);//工作地点
        job_location.setOnClickListener(this);
        tv_job_location = (TextView) findViewById(R.id.intention_tv_job_location);//tv工作地点

        job_type = (WaveView) findViewById(R.id.intention_job_type);//职位类型
        job_type.setOnClickListener(this);
        tv_job_type = (TextView) findViewById(R.id.intention_tv_job_type);//tv职位类型

        industry_type = (WaveView) findViewById(R.id.intention_industry_type);//行业类型
        industry_type.setOnClickListener(this);
        tv_industry_type = (TextView) findViewById(R.id.intention_tv_industry_type);//tv行业类型

        want_salary = (WaveView) findViewById(R.id.intention_want_salary);//期望薪资
        want_salary.setOnClickListener(this);
        tv_want_salary = (TextView) findViewById(R.id.intention_tv_want_salary);//tv期望薪资

        job_state = (WaveView) findViewById(R.id.intention_job_state);//工作状态
        job_state.setOnClickListener(this);
        tv_job_state = (TextView) findViewById(R.id.intention_tv_job_state);//tv工作状态
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.intention_submit:
                submitview();
//                intent = new Intent(mContext,SalaryMain.class);
//                startActivity(intent);
                //提交
                break;
            case R.id.intention_job_nature://工作性质
                popnature.showAsDropDown(textview);
                break;
            case R.id.intention_job_location://工作地点
                pagetype = "location";
                poplocat.showAsDropDown(textview,pagetype);
                break;
            case R.id.intention_job_type://职位类型//
                intent = new Intent(mContext,Job_type.class);
                Public_static_all.isintenttionA = true;
                intent.putExtra("pagetype","intenttion");
                startActivity(intent);
                break;
            case R.id.intention_industry_type://行业类型//
                intent = new Intent(mContext,Job_experice.class);
                Public_static_all.isintenttionB = true;
                intent.putExtra("pagetype","intenttion");
                startActivity(intent);
                break;
            case R.id.intention_want_salary://期望薪资
                pagetype = "salary";
                poplocat.showAsDropDown(textview,pagetype);
                break;
            case R.id.intention_job_state://工作状态
                popstate.showAsDropDown(textview);
                break;
        }
    }

    private void submitview() {
        S_job_nature = tv_job_nature.getText().toString();
        S_job_location = tv_job_location.getText().toString();
        S_job_type = tv_job_type.getText().toString();
        S_industry_type = tv_industry_type.getText().toString();
        S_want_salary = tv_want_salary.getText().toString();
        S_job_state = tv_job_state.getText().toString();
        if (S_job_nature == null||S_job_nature.length() <= 0){
            Toast.makeText(mContext, "请填写工作性质", Toast.LENGTH_SHORT).show();
        }else if (S_job_location == null||S_job_location.length() <= 0){
            Toast.makeText(mContext, "请填写工作地点", Toast.LENGTH_SHORT).show();
        }else if (S_job_type == null||S_job_type.length() <= 0){
            Toast.makeText(mContext, "请选择职位类型", Toast.LENGTH_SHORT).show();
        }else if (S_industry_type == null||S_industry_type.length() <= 0){
            Toast.makeText(mContext, "请选择行业类型", Toast.LENGTH_SHORT).show();
        }else if (S_want_salary == null||S_want_salary.length() <= 0){
            Toast.makeText(mContext, "请选择期望薪资", Toast.LENGTH_SHORT).show();
        }else if (S_job_state == null||S_job_state.length() <= 0){
            Toast.makeText(mContext, "请选择工作状态", Toast.LENGTH_SHORT).show();
        }else{
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
//                  userid,work_property(工作性质),address（工作地点）,position_type（职位类型）,categories（行业类别）,wantsalary（期望薪资）,jobstate（工作状态）
                new UserRequest(mContext, handler).PUBLISHINTENSION(infoBean.getUserid(),S_job_nature,S_job_location,S_job_type,S_industry_type,S_want_salary,S_job_state, PUBLISHINTENSION);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Public_static_all.isintenttionA&&Public_static_all.isintenttiona){//公司行业
            tv_job_type.setText(info.getPersonal_type()+"");
        }else if (Public_static_all.isintenttionB&&Public_static_all.isintenttionb){
            tv_industry_type.setText(info.getPersonal_industry()+"");
        }

    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYINTENSION(infoBean.getUserid(), GETMYINTENSION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void isJob(boolean isJob){
        Public_static_all.isintenttionA = isJob;
        Public_static_all.isintenttiona = isJob;
        Public_static_all.isintenttionB = isJob;
        Public_static_all.isintenttionb = isJob;
    }
}
