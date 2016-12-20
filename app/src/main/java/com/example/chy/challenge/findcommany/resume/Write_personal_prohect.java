package com.example.chy.challenge.findcommany.resume;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

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
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.pop.Pop_project_jobtime;
import com.example.chy.challenge.login.register.Write_personal_info;
import com.example.chy.challenge.login.register.commany_info.Job_Show;
import com.example.chy.challenge.login.register.commany_info.Job_type;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_advantage;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_jobtime;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class Write_personal_prohect extends Activity implements View.OnClickListener{
    private static final int PUBLISHWORK = 1;
    private static final int GETMYWORKLIST = 2;
    private WaveView back,project_name,project_starttime,project_endtime,project_describ,submit_project;
    private TextView tv_project_name,tv_project_starttime,tv_project_endtime,tv_project_describ,textview;
    private Intent intent;
    private Context mContext;
    private UserInfoBean infoBean;
    private UserInfo info;
    private ProgressDialog dialog;
    private String projectname,projectstarttime,projectendtime,projectdescrib;
    private Pop_project_jobtime popjobtime;
    private Handler mHandler = new Handler();

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case PUBLISHWORK:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                dialog.dismiss();
                                intent = new Intent(mContext,Register_Mine_advantage.class);
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
                case GETMYWORKLIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject josnobj = json.getJSONObject("data");
                                tv_project_name.setText(josnobj.getString("project_name")+"");
                                tv_project_starttime.setText(josnobj.getString("start_time")+"");
                                tv_project_endtime.setText(josnobj.getString("end_time")+"");
                                tv_project_describ.setText(josnobj.getString("description_project")+"");
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
        setContentView(R.layout.resume_work_experience);
        mContext = this;
        infoBean = new UserInfoBean(mContext);
        info = new UserInfo(mContext);
        popjobtime = new Pop_project_jobtime(Write_personal_prohect.this);
        isJob(false);
        getView();
    }

    private void getView() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);


        project_name = (WaveView) findViewById(R.id.project_name);
        project_name.setOnClickListener(this);
        tv_project_name = (TextView) findViewById(R.id.tv_project_name);//项目名称


        project_starttime = (WaveView) findViewById(R.id.project_starttime);//项目开始时间
        project_starttime.setOnClickListener(this);
        tv_project_starttime = (TextView) findViewById(R.id.work_tv_commany_industry);//项目开始时间

        project_endtime = (WaveView) findViewById(R.id.project_endtime);//项目结束时间
        project_endtime.setOnClickListener(this);
        tv_project_endtime = (TextView) findViewById(R.id.work_tv_Position_type);//项目结束时间

        project_describ = (WaveView) findViewById(R.id.project_describ);//项目描述
        project_describ.setOnClickListener(this);
        tv_project_describ = (TextView) findViewById(R.id.work_tv_skills_show);//项目描述

        submit_project = (WaveView) findViewById(R.id.submit_project);//提交
        submit_project.setOnClickListener(this);

        textview = (TextView) findViewById(R.id.textview);
        dialog = new ProgressDialog(mContext,AlertDialog.THEME_HOLO_LIGHT);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.project_name://项目名称
                intent = new Intent(mContext, Write_personal_info.class);
                intent.putExtra("type","projectname");
                Public_static_all.idprojectnameA = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.project_starttime://项目开始时间
                popjobtime.showAsDropDown(textview,"start");
                break;
            case R.id.project_endtime://项目结束时间
                popjobtime.showAsDropDown(textview,"end");
                break;
            case R.id.project_describ://项目描述
                intent = new Intent(mContext,Job_Show.class);
                Public_static_all.idprojectnameB = true;
                startActivity(intent);
                break;
            case R.id.submit_project://提交
                Toast.makeText(mContext,"此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void submitjob() {
        projectname = tv_project_name.getText().toString();
        projectstarttime = tv_project_starttime.getText().toString();
        projectendtime = tv_project_endtime.getText().toString();
        projectdescrib = tv_project_describ.getText().toString();
        if (projectname == null||projectname.length() <= 0){
            Toast.makeText(mContext, "请输入项目名称", Toast.LENGTH_SHORT).show();
        }else if (projectstarttime == null||projectstarttime.length() <= 0){
            Toast.makeText(mContext, "请选择开始时间", Toast.LENGTH_SHORT).show();
        }else if (projectendtime == null||projectendtime.length() <= 0){
            Toast.makeText(mContext, "请选择结束时间", Toast.LENGTH_SHORT).show();
        }else if (projectdescrib == null||projectdescrib.length() <= 0){
            Toast.makeText(mContext, "请填写项目描述", Toast.LENGTH_SHORT).show();
        }else{
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                //                   userid,company_name公司名称,company_industry公司行业,jobtype职位类别,skill技能,work_period任职时间段,content工作内容
//                new UserRequest(mContext, handler).PUBLISHWORK(infoBean.getUserid(),companyname,companyindustry,companytype,companyshow,companyjobtime,companycontent, PUBLISHWORK);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Public_static_all.idprojectnameA&&Public_static_all.idprojectnamea){//公司行业
            tv_project_name.setText(info.getProjectname());
        }
        if (Public_static_all.idprojectnameB&&Public_static_all.idprojectnameb){//职位类型
            tv_project_describ.setText("已填写");
        }
        getinfo();
    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYWORKLIST(infoBean.getUserid(), GETMYWORKLIST);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void isJob(boolean isJob){
        Public_static_all.idprojectnameA = isJob;
        Public_static_all.idprojectnamea = isJob;
        Public_static_all.idprojectnameB = isJob;
        Public_static_all.idprojectnameb = isJob;
    }

}
