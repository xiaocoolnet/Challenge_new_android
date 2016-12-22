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
import android.view.MotionEvent;
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
import com.example.chy.challenge.findcommany.resume.bean.Resume_education_bean;
import com.example.chy.challenge.login.register.Write_personal_info;
import com.example.chy.challenge.login.register.personal_pop.Commany_personal_education;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_readtime;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class Register_next_education extends Activity implements View.OnClickListener{
    private static final int PUBLISHEDUCATION = 1;
    private WaveView back,resume_education,resume_Atthe_time,resume_education_next,graduate_institutions,graduate_major;
    private EditText resume_et_experience;
    private TextView resume_et_school,resume_et_major,resume_tv_education,resume_tv_readtime,resume_tv_experience,textview_pop;
    private Commany_personal_education education;
    private Pop_mine_readtime readtime;
    private Context mContext;
    private UserInfoBean infobean;
    private UserInfo info;
    private ProgressDialog dialog;
    private int num = 300;
    private String Sschool,Smajor,Sreadtime,Seducation,Sexperice,pagetype;
    private Intent intent;
    private ScrollView scrollview;
    private Handler mHandler = new Handler();
    private Resume_education_bean.DataBean educationbean;

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
                                intent = new Intent(mContext,Write_personal_jobnews.class);
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
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.resume_education);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        info = new UserInfo(mContext);
        intent = getIntent();
        pagetype = intent.getStringExtra("pagetype");
        if (pagetype != null&&pagetype.length() > 0){
            if ("update".equals(pagetype)) {
                educationbean = (Resume_education_bean.DataBean) intent.getSerializableExtra("educationbean");
                if (educationbean != null){
                    resume_et_school.setText(educationbean.getSchool()+"");
                    resume_et_major.setText(educationbean.getMajor()+"");
                    resume_tv_readtime.setText(educationbean.getTime()+"");
                    resume_tv_education.setText(educationbean.getDegree()+"");
                    resume_et_experience.setText(educationbean.getExperience()+"");
                }
            }
        }
        education = new Commany_personal_education(Register_next_education.this);
        readtime = new Pop_mine_readtime(Register_next_education.this);
        isJob(false);
        getView();
    }

    private void getView() {
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        resume_education_next = (WaveView) findViewById(R.id.resume_education_next);
        resume_education_next.setOnClickListener(this);

        graduate_institutions = (WaveView) findViewById(R.id.resume_graduate_institutions);
        graduate_institutions.setOnClickListener(this);
        graduate_major = (WaveView) findViewById(R.id.resume_graduate_major);
        graduate_major.setOnClickListener(this);
        resume_education = (WaveView) findViewById(R.id.resume_education);//学历
        resume_education.setOnClickListener(this);
        resume_Atthe_time = (WaveView) findViewById(R.id.resume_Atthe_time);//就读时间
        resume_Atthe_time.setOnClickListener(this);
        resume_et_school = (TextView) findViewById(R.id.resume_et_school);
        resume_et_major = (TextView) findViewById(R.id.resume_et_major);
        resume_tv_education = (TextView) findViewById(R.id.resume_tv_education);
        resume_tv_readtime = (TextView) findViewById(R.id.resume_tv_readtime);
        resume_et_experience = (EditText) findViewById(R.id.resume_et_experience);//教育经历
        resume_et_experience.setOnClickListener(this);
        resume_tv_experience = (TextView) findViewById(R.id.resume_tv_experience);//300
        textview_pop = (TextView) findViewById(R.id.textview_pop);
        resume_et_experience.addTextChangedListener(new TextWatcher() {
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
                resume_tv_experience.setText("" + number);
                selectionStart = resume_et_experience.getSelectionStart();
                selectionEnd = resume_et_experience.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    resume_et_experience.setText(s);
                    resume_et_experience.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                scrollview.fullScroll(View.FOCUS_DOWN);
//            }
//        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.resume_education_next:
                updatasubmit();
//                intent = new Intent(mContext,Write_personal_jobnews.class);
//                startActivity(intent);
                break;
            case R.id.resume_education:
                education.showAsDropDown(textview_pop);
                break;
            case R.id.resume_Atthe_time:
                readtime.showAsDropDown(textview_pop);
                break;
            case R.id.resume_graduate_institutions://学校名称
                intent = new Intent(mContext, Write_personal_info.class);
                intent.putExtra("type","schoolname");
                Public_static_all.iseducationA = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.resume_graduate_major://专业名称
                intent = new Intent(mContext, Write_personal_info.class);
                intent.putExtra("type","schoolmajor");
                Public_static_all.iseducationB = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.resume_et_experience:
                resume_et_experience.setFocusable(true);
                resume_et_experience.setFocusableInTouchMode(true);
                resume_et_experience.requestFocus();
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

    private void updatasubmit() {
        Sschool = resume_et_school.getText().toString();
        Smajor = resume_et_major.getText().toString();
        Sreadtime = resume_tv_readtime.getText().toString();
        Seducation = resume_tv_education.getText().toString();
        Sexperice = resume_et_experience.getText().toString();
        if (Sschool == null||Sschool.length() <= 0){
            Toast.makeText(mContext, "请输入毕业院校", Toast.LENGTH_SHORT).show();
        }else if (Smajor == null||Smajor.length() <= 0){
            Toast.makeText(mContext, "请输入所学专业", Toast.LENGTH_SHORT).show();
        }else if (Sreadtime == null||Sreadtime.length() <= 0){
            Toast.makeText(mContext, "请选择就读时间段", Toast.LENGTH_SHORT).show();
        }else if (Seducation == null||Seducation.length() <= 0){
            Toast.makeText(mContext, "请输入学历", Toast.LENGTH_SHORT).show();
        }else if (Sexperice == null||Sexperice.length() <= 0){
            Toast.makeText(mContext, "请输入教育经历", Toast.LENGTH_SHORT).show();
        }else{
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
//                    userid,school学校,major专业,degree学历,time时间段,experience在校经历
                new UserRequest(mContext, handler).PUBLISHEDUCATION(infobean.getUserid(),Sschool,Smajor,Seducation,Sreadtime,Sexperice, PUBLISHEDUCATION);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Public_static_all.iseducationA&&Public_static_all.iseducationa){//学校名称
            resume_et_school.setText(info.getSchoolname());
        }
        if (Public_static_all.iseducationB&&Public_static_all.iseducationb){//职位类型
            resume_et_major.setText(info.getSchoolmajor());
        }
    }
    private void isJob(boolean isJob){
        Public_static_all.iseducationA = isJob;
        Public_static_all.iseducationa = isJob;
        Public_static_all.iseducationB = isJob;
        Public_static_all.iseducationb = isJob;
    }
}
