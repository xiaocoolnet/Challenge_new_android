package com.example.chy.challenge.findcommany.findjob;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.CustomDialog;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.findjob.bean.Find_Job_company_bean;
import com.example.chy.challenge.findcommany.findjob.pop.PopupWindow_shared;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class JobInfo extends Activity implements View.OnClickListener{
    private static final int APPLYJOB_JUDGE = 1;
    private static final int APPLYJOB = 2;
    private static final int CHECKHADFAVORITE = 3;
    private static final int ADDFAVORITE = 4;
    private static final int CHECKHAD = 5;
    public static final int CANCELFAVORITE = 6;
    private Context mContext;
    private WaveView back,show_all,send_resume,speak_with,collect_job,shared_job;
    private TextView tv_myjob,tv_salary,address,experience,education,work_property,tv_welfare,tv_companyname,tv_realname,tv_jobtype,
            tv_job_num,company_address,tv_description_job,view_gray;
    private RoundImageView roundImageView;
    private Boolean flag = true;
    private Intent intent;

    private FindPersonalBean.DataBean.JobsBean jobsbean;
    private String companyname,count,companyid;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();
    private ProgressDialog dialog;
    private UserInfoBean infoBean;
    private TextView tv_send_resume;
    private LinearLayout job_info_companyinfo;
    private PopupWindow_shared shared;
    private RelativeLayout textview_ril;
    private ImageView image_collect;

    private Handler handler = new Handler(Looper.myLooper()){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case APPLYJOB_JUDGE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                               if ("1".equals(json.getString("data"))){//已投递
//                                    send_resume//边框变灰色
                                    send_resume.setBackgroundResource(R.drawable.border_send_resume_gray);
                                    tv_send_resume.setText("已投递");
                                    tv_send_resume.setTextColor(mContext.getResources().getColor(R.color.gray4));
                                    send_resume.setClickable(false);
                                }else{
                                   send_resume.setClickable(true);
                               }
                            }else{
                                send_resume.setClickable(true);
                            }
                        } catch (JSONException e) {
                            send_resume.setClickable(true);
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("刷新失败");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                        send_resume.setOnClickListener((View.OnClickListener) mContext);
                        send_resume.setClickable(true);
                    }
                    break;
                case APPLYJOB:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                if ("1".equals(json.getString("data"))){//已投递
//                                    send_resume//边框变灰色
                                    send_resume.setBackgroundResource(R.drawable.border_send_resume_gray);
                                    tv_send_resume.setText("已投递");
                                    tv_send_resume.setTextColor(mContext.getResources().getColor(R.color.gray4));
                                    send_resume.setClickable(false);
                                }
                            }else{
                                send_resume.setClickable(true);
                            }
                        } catch (JSONException e) {
                            send_resume.setClickable(true);
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("投递失败");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                        send_resume.setClickable(true);
                    }
                    break;
                case CHECKHADFAVORITE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){//收藏
//                                    send_resume//边框变灰色
                                    image_collect.setBackgroundResource(R.mipmap.btn_isshoucang);
                            }else{//未收藏
                                image_collect.setBackgroundResource(R.mipmap.btn_shoucang);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("网络错误");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ADDFAVORITE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){//收藏
                                image_collect.setBackgroundResource(R.mipmap.btn_isshoucang);
                                Toast.makeText(mContext, "成功收藏",Toast.LENGTH_SHORT).show();
                            }else{//未收藏
                                image_collect.setBackgroundResource(R.mipmap.btn_shoucang);
                                Toast.makeText(mContext, "收藏失败",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("网络错误");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    collect_job.setClickable(true);
                    break;
                case CHECKHAD:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){//收藏
//                                    send_resume//边框变灰色
                                image_collect.setBackgroundResource(R.mipmap.btn_isshoucang);
                                if (NetBaseUtils.isConnnected(mContext)) {
                                    dialog.setMessage("正在取消收藏");
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.show();
                                    new UserRequest(mContext, handler).CANCELFAVORITE(infoBean.getUserid(),jobsbean.getJobid(),"1",CANCELFAVORITE);
                                }else{
                                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                                }
                            }else{//未收藏
                                if (NetBaseUtils.isConnnected(mContext)) {
                                    dialog.setMessage("正在收藏");
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.show();
                                    new UserRequest(mContext, handler).ADDFAVORITE(infoBean.getUserid(),jobsbean.getJobid(),"1",jobsbean.getTitle(),jobsbean.getDescription_job(),ADDFAVORITE);
                                }else{
                                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("网络错误");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CANCELFAVORITE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){//取消收藏
                                image_collect.setBackgroundResource(R.mipmap.btn_shoucang);
                                Toast.makeText(mContext, "成功取消收藏",Toast.LENGTH_SHORT).show();
                            }else{//未取消收藏
                                image_collect.setBackgroundResource(R.mipmap.btn_isshoucang);
                                Toast.makeText(mContext, "取消收藏失败",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("网络错误");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    collect_job.setClickable(true);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.job_info);
        mContext = this;
        infoBean = new UserInfoBean(mContext);
        shared = new PopupWindow_shared(mContext);
        getview();
        intent = getIntent();
        jobsbean = (FindPersonalBean.DataBean.JobsBean) intent.getSerializableExtra("jobcompanybean");
        companyname = intent.getStringExtra("company");
        count = intent.getStringExtra("count");
        companyid = intent.getStringExtra("companyid");
        if (jobsbean != null) {
            getinfo();
        }
    }

    private void getinfo() {
        tv_myjob.setText(jobsbean.getTitle()+"");
        if (jobsbean.getSalary() != null &&jobsbean.getSalary().length() > 0) {
            tv_salary.setText("【￥"+jobsbean.getSalary()+"】");
        }else{
            tv_salary.setText("");
        }
        address.setText(jobsbean.getCity().substring(0,2)+"");
        experience.setText(jobsbean.getExperience()+"");
        education.setText(jobsbean.getEducation()+"");
        work_property.setText(jobsbean.getWork_property()+"");
        tv_welfare.setText(jobsbean.getWelfare()+"");
        if (companyname != null &&companyname.length() > 0) {
            tv_companyname.setText(companyname);
        }else{
            tv_companyname.setText("");
        }
        if (jobsbean.getPhoto() != null&&jobsbean.getPhoto().length() > 0) {
            imageLoader.displayImage(NetBaseConstant.NET_HOST + jobsbean.getPhoto(), roundImageView, options);
        }else{
            roundImageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
        }
        tv_realname.setText(jobsbean.getRealname()+"");
//        tv_jobtype//职位分类
        if (count != null &&count.length() > 0) {
            tv_job_num.setText("共"+count+"个职位");
        }else{
            tv_job_num.setText("共0个职位");
        }
        if (jobsbean.getCity() != null &&jobsbean.getCity().length() > 0) {
            company_address.setText("上班地点:"+jobsbean.getCity()+jobsbean.getAddress());
        }else{
            company_address.setText(jobsbean.getCity()+jobsbean.getAddress()+"");
        }
        tv_description_job.setText(jobsbean.getDescription_job()+"");
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);

        tv_myjob = (TextView) findViewById(R.id.tv_myjob);//招聘职位
        tv_salary = (TextView) findViewById(R.id.tv_salary);//招聘薪资 【￥5K-6K】
        address = (TextView) findViewById(R.id.address);//地址城市
        experience = (TextView) findViewById(R.id.experience);//工作年限
        education = (TextView) findViewById(R.id.education);//学历
        work_property = (TextView) findViewById(R.id.work_property);//全职、兼职
        tv_welfare = (TextView) findViewById(R.id.tv_welfare);//职位诱惑
        tv_companyname = (TextView) findViewById(R.id.tv_companyname);//公司名称
        roundImageView = (RoundImageView) findViewById(R.id.roundImageView);//公司头像
        tv_realname = (TextView) findViewById(R.id.tv_realname);//招聘人事
        tv_jobtype = (TextView) findViewById(R.id.tv_jobtype);//招聘职位分类
        tv_job_num = (TextView) findViewById(R.id.tv_job_num);//共7个职位
        company_address = (TextView) findViewById(R.id.company_address);//上班地点：
        tv_description_job = (TextView) findViewById(R.id.tv_description_job);//职位描述

        show_all = (WaveView) findViewById(R.id.show_all);//显示全部
        show_all.setOnClickListener(this);

        send_resume = (WaveView) findViewById(R.id.send_resume);//发送简历
        send_resume.setOnClickListener(this);
        tv_send_resume = (TextView) findViewById(R.id.tv_send_resume);


        speak_with = (WaveView) findViewById(R.id.speak_with);//和他聊聊
        speak_with.setOnClickListener(this);

        view_gray = (TextView) findViewById(R.id.view_gray);

        dialog = new ProgressDialog(mContext, android.app.AlertDialog.THEME_HOLO_LIGHT);
        job_info_companyinfo = (LinearLayout) findViewById(R.id.job_info_companyinfo);
        job_info_companyinfo.setOnClickListener(this);

        collect_job = (WaveView) findViewById(R.id.collect_job);
        collect_job.setOnClickListener(this);
        shared_job = (WaveView) findViewById(R.id.shared_job);
        shared_job.setOnClickListener(this);
        textview_ril = (RelativeLayout) findViewById(R.id.textview_ril);
        image_collect = (ImageView) findViewById(R.id.image_collect);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.send_resume://发送简历
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("确定向企业发送简历么");
                builder.setTitle("!");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogone, int which) {
                        dialogone.dismiss();
                    }
                });
                builder.setNegativeButton("确定",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogone, int which) {
                                send_resume.setClickable(false);
                                if (NetBaseUtils.isConnnected(mContext)) {
                                    dialog.setMessage("正在投递简历");
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.show();
                                    new UserRequest(mContext, handler).APPLYJOB(infoBean.getUserid(),companyid,jobsbean.getJobid(),APPLYJOB);
                                }else{
                                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                                }
                                dialogone.dismiss();
                            }
                        });
                builder.create().show();
                break;
            case R.id.speak_with://和他聊聊

                break;
            case R.id.show_all://显示全部
                if(flag){
                    flag = false;
                    tv_description_job.setEllipsize(null); // 展开
                    tv_description_job.setSingleLine(flag);
                    view_gray.setVisibility(View.GONE);
                    show_all.setVisibility(View.GONE);
                }
                break;
            case R.id.job_info_companyinfo:
                intent = new Intent(mContext,JobInfo.class);
                intent.putExtra("companyid",companyid);
                startActivity(intent);
                break;
            case R.id.collect_job://收藏
                if (NetBaseUtils.isConnnected(mContext)) {
                    collect_job.setClickable(false);
                    dialog.setMessage("正在收藏");
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.show();
                    new UserRequest(mContext, handler).CHECKHADFAVORITE(infoBean.getUserid(),jobsbean.getJobid(),"1",CHECKHAD);
                }else{
                    Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shared_job://分享
                shared.showAsDropDown(textview_ril);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        send_resume.setClickable(false);
        issendresume();
    }

    private void issendresume() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新信息");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).APPLYJOB_JUDGE(infoBean.getUserid(),companyid,jobsbean.getJobid(),APPLYJOB_JUDGE);
            new UserRequest(mContext, handler).CHECKHADFAVORITE(infoBean.getUserid(),jobsbean.getJobid(),"1",CHECKHADFAVORITE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
