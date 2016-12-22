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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.Write_personal_info;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_jobtime;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class Write_personal_jobnews extends Activity implements View.OnClickListener{
    private static final int PUBLISHWORK = 1;
    private static final int GETMYWORKLIST = 2;
    private WaveView back,commany_name,commany_industry,Position_type,skills_show,work_time,submit_job_experice;
    private EditText et_describe;
    private TextView work_tv_commany,tv_commany_industry,tv_Position_type,tv_skills_show,tv_work_time,look_other_personal,txtnum,textview;
    private Intent intent;
    private Context mContext;
    private UserInfoBean infoBean;
    private UserInfo info;
    private ProgressDialog dialog;
    private int num = 300;
    private String companyname,companyindustry,companytype,companyshow,companyjobtime,companycontent;
    private Pop_mine_jobtime popjobtime;
    private ScrollView scrollview;
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
                                work_tv_commany.setText(josnobj.getString("company_name")+"");
                                tv_Position_type.setText(josnobj.getString("jobtype")+"");
                                tv_commany_industry.setText(josnobj.getString("company_industry")+"");
                                tv_skills_show.setText(josnobj.getString("skill")+"");
                                tv_work_time.setText(josnobj.getString("work_period")+"");
                                et_describe.setText(josnobj.getString("content")+"");
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.resume_work_experience);
        mContext = this;
        infoBean = new UserInfoBean(mContext);
        info = new UserInfo(mContext);
        popjobtime = new Pop_mine_jobtime(Write_personal_jobnews.this);
        isJob(false);
        getView();
        getinfo();
    }

    private void getView() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        commany_name = (WaveView) findViewById(R.id.work_commany_name);
        commany_name.setOnClickListener(this);
        work_tv_commany = (TextView) findViewById(R.id.work_tv_commany);//公司名称
        commany_industry = (WaveView) findViewById(R.id.work_commany_industry);//公司行业点击
        commany_industry.setOnClickListener(this);
        tv_commany_industry = (TextView) findViewById(R.id.work_tv_commany_industry);//公司行业tv

        Position_type = (WaveView) findViewById(R.id.work_Position_type);//职位类型
        Position_type.setOnClickListener(this);
        tv_Position_type = (TextView) findViewById(R.id.work_tv_Position_type);//职位类型tv

        skills_show = (WaveView) findViewById(R.id.work_skills_show);//技能展示
        skills_show.setOnClickListener(this);
        tv_skills_show = (TextView) findViewById(R.id.work_tv_skills_show);//技能展示tv

        work_time = (WaveView) findViewById(R.id.work_work_time);//任职时间段
        work_time.setOnClickListener(this);
        tv_work_time = (TextView) findViewById(R.id.work_tv_work_time);//任职时间段tv

        et_describe = (EditText) findViewById(R.id.work_et_describe);//工作内容
        et_describe.setOnClickListener(this);
        look_other_personal = (TextView) findViewById(R.id.work_look_other_personal);//看看别人怎么写
        look_other_personal.setOnClickListener(this);
        txtnum = (TextView) findViewById(R.id.txtnum);//实时字数

        textview = (TextView) findViewById(R.id.textview);

        submit_job_experice = (WaveView) findViewById(R.id.submit_job_experice);//提交按钮
        submit_job_experice.setOnClickListener(this);

        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        et_describe.addTextChangedListener(new TextWatcher() {
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
                txtnum.setText("" + number);
                selectionStart = et_describe.getSelectionStart();
                selectionEnd = et_describe.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    et_describe.setText(s);
                    et_describe.setSelection(tempSelection);//设置光标在最后
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
            case R.id.work_commany_name://公司名称
                intent = new Intent(mContext, Write_personal_info.class);
                intent.putExtra("type","companyname");
                Public_static_all.isJobD = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.work_commany_industry://公司行业
                intent = new Intent(mContext,Job_experice.class);
                Public_static_all.isJobA = true;
                intent.putExtra("pagetype","jobnews");
                startActivity(intent);
                break;
            case R.id.work_Position_type://职位类型
                intent = new Intent(mContext,Job_type.class);
                Public_static_all.isJobB = true;
                intent.putExtra("pagetype","jobnews");
                startActivity(intent);
                break;
            case R.id.work_skills_show://技能展示
                intent = new Intent(mContext,Job_Show.class);
                Public_static_all.isJobC = true;
                startActivity(intent);
                break;
            case R.id.work_work_time://任职时间段
                popjobtime.showAsDropDown(textview);
                break;
            case R.id.work_look_other_personal://看看别人怎么写
                Toast.makeText(mContext,"此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submit_job_experice://提交
                submitjob();
//                intent = new Intent(mContext,Register_Mine_advantage.class);
//                startActivity(intent);
                break;
            case R.id.work_et_describe:
                et_describe.setFocusable(true);
                et_describe.setFocusableInTouchMode(true);
                et_describe.requestFocus();
                mHandler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        //将ScrollView滚动到底
                        scrollview.fullScroll(View.FOCUS_DOWN);
                    }
                },100);
                break;
        }
    }

    private void submitjob() {
        companyname = work_tv_commany.getText().toString();
        companyindustry = tv_commany_industry.getText().toString();
        companytype = tv_Position_type.getText().toString();
        companyshow = tv_skills_show.getText().toString();
        companyjobtime = tv_work_time.getText().toString();
        companycontent = et_describe.getText().toString();
        if ("请输入公司名称".equals(companyname)||companyname == null||companyname.length() <= 0){
            Toast.makeText(mContext, "请输入公司名称", Toast.LENGTH_SHORT).show();
        }else if ("请选择行业".equals(companyindustry)||companyindustry == null||companyindustry.length() <= 0){
            Toast.makeText(mContext, "请选择公司行业", Toast.LENGTH_SHORT).show();
        }else if ("选择职位类型".equals(companytype)||companytype == null||companytype.length() <= 0){
            Toast.makeText(mContext, "请选择职位类型", Toast.LENGTH_SHORT).show();
        }else if ("选择技能".equals(companytype)||companyshow == null||companyshow.length() <= 0){
            Toast.makeText(mContext, "请选择技能", Toast.LENGTH_SHORT).show();
        }else if ("选择任职时间段".equals(companytype)||companyjobtime == null||companyjobtime.length() <= 0){
            Toast.makeText(mContext, "请选择任职时间", Toast.LENGTH_SHORT).show();
        }else if (companycontent == null||companycontent.length() <= 0){
            Toast.makeText(mContext, "请填写工作内容", Toast.LENGTH_SHORT).show();
        }else{
            companyshow = info.getSkills_show1()+"-"+info.getSkills_show2()+"-"+info.getSkills_show3();
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
//                   userid,company_name公司名称,company_industry公司行业,jobtype职位类别,skill技能,work_period任职时间段,content工作内容
                new UserRequest(mContext, handler).PUBLISHWORK(infoBean.getUserid(),companyname,companyindustry,companytype,companyshow,companyjobtime,companycontent, PUBLISHWORK);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Public_static_all.isJobA&&Public_static_all.isJoba){//公司行业
            tv_commany_industry.setText(info.getCommany_industry());
        }
        if (Public_static_all.isJobB&&Public_static_all.isJobb){//职位类型
            tv_Position_type.setText(info.getPosition_type());
        }
        if (Public_static_all.isJobC&&Public_static_all.isJobc){//技能展示
            tv_skills_show.setText(info.getSkills_show());
        }
        if (Public_static_all.isJobD&&Public_static_all.isJobd){//公司行业
            work_tv_commany.setText(info.getCompany_nameone());
        }

    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYWORKLIST(infoBean.getUserid(), GETMYWORKLIST);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void isJob(boolean isJob){
        Public_static_all.isJobA = isJob;
        Public_static_all.isJoba = isJob;
        Public_static_all.isJobB = isJob;
        Public_static_all.isJobb = isJob;
        Public_static_all.isJobC = isJob;
        Public_static_all.isJobc = isJob;
        Public_static_all.isJobD = isJob;
        Public_static_all.isJobd = isJob;
    }
}
