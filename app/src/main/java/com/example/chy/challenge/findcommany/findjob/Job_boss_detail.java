package com.example.chy.challenge.findcommany.findjob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.FindPersonal;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.NoPosition;
import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.Utils.NoScrollListView;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.findjob.adapter.Job_boss_adapter;
import com.example.chy.challenge.findcommany.findjob.bean.Find_Job_company_bean;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class Job_boss_detail extends Activity implements View.OnClickListener{
    private static final int GETMYCOMMANY = 1;
    private Context mContext;
    private WaveView back,evaluation_the_interview;
    private RoundImageView detail_headimage;
    private TextView tv_company_name,tv_company_type,tv_company_introduce,tv_product_introduce,evaluation_tv_interview;
    private NoScrollListView lv_job;
    private RelativeLayout company_detail_image;
    private Intent intent;
    private Bundle bundle;
    private List<Find_Job_company_bean.DataBean.JobsBean> jobcompanylist = new ArrayList<>();
    private Job_boss_adapter jobbean;
    private ProgressDialog dialog;
    private UserInfoBean infobean;
    private FindPersonalBean.DataBean findpb;
    private FindPersonalBean.DataBean.JobsBean findbj;
    private List<FindPersonalBean.DataBean.JobsBean> findbjlist;
    private List<FindPersonalBean.DataBean> findlist = new ArrayList<>();
    private String companyid;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETMYCOMMANY:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        findlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject jsonobj = new JSONObject(json.getString("data"));
                                findpb = new FindPersonalBean.DataBean();
                                findpb.setLogo(jsonobj.getString("logo"));
                                findpb.setCompanyid(jsonobj.getString("companyid"));
                                findpb.setCompany_name(jsonobj.getString("company_name"));
                                findpb.setCompany_web(jsonobj.getString("company_web"));
                                findpb.setIndustry(jsonobj.getString("industry"));
                                findpb.setCount(jsonobj.getString("count"));
                                findpb.setFinancing(jsonobj.getString("financing"));
                                findpb.setCreat_time(jsonobj.getString("creat_time"));
                                findpb.setAuthentication(jsonobj.getString("authentication"));
                                findpb.setCompany_score(jsonobj.getString("company_score"));
                                findpb.setDistance(jsonobj.getString("distance"));
                                findpb.setCom_introduce(jsonobj.getString("com_introduce"));
                                findpb.setProdute_info(jsonobj.getString("produte_info"));
                                findpb.setEvaluate_count(jsonobj.getString("evaluate_count"));
                                findbjlist = new ArrayList<>();
                                JSONArray jsonjob = jsonobj.getJSONArray("jobs");
                                for (int j = 0;j < jsonjob.length();j++){
                                    JSONObject jsonobjob = jsonjob.getJSONObject(j);
                                    findbj = new FindPersonalBean.DataBean.JobsBean();
                                    findbj.setRealname(jsonobjob.getString("realname"));
                                    findbj.setPhoto(jsonobjob.getString("photo"));
                                    findbj.setMyjob(jsonobjob.getString("myjob"));// "myjob": "iOS",
                                    findbj.setJobid(jsonobjob.getString("jobid"));//  "jobid": "4",
                                    findbj.setUserid(jsonobjob.getString("userid"));// "userid": "301",
                                    findbj.setJobtype(jsonobjob.getString("jobtype"));//"jobtype": "技术专员/助理",
                                    findbj.setTitle(jsonobjob.getString("title"));// "title": "职位2",
                                    findbj.setSkill(jsonobjob.getString("skill"));//  "skill": "3个技能",
                                    findbj.setSalary(jsonobjob.getString("salary"));//  "salary": "2至2",
                                    findbj.setExperience(jsonobjob.getString("experience"));//"experience": "应届生",
                                    findbj.setEducation(jsonobjob.getString("education"));//"education": "研究生",
                                    findbj.setCity(jsonobjob.getString("city"));// "city": "云南省-迪庆藏族自治州",
                                    findbj.setAddress(jsonobjob.getString("address"));// "address": "The ",
                                    findbj.setDescription_job(jsonobjob.getString("description_job"));// "description_job": "1~2万",
                                    findbj.setCreate_time(jsonobjob.getString("create_time"));// "create_time": "",
                                    findbj.setWork_property(jsonobjob.getString("work_property"));// "work_property": ""
                                    findbj.setWelfare(jsonobjob.getString("welfare"));
                                    findbjlist.add(findbj);
                                }
                                findpb.setJobs(findbjlist);
                                findlist.add(findpb);
                                getcompanyinfo();
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
            }
        }

        private void getcompanyinfo() {
            tv_company_name.setText(findpb.getCompany_name()+"");//公司名称
            if (findpb.getIndustry() != null&&findpb.getIndustry().length() > 0){
                if (findpb.getFinancing() != null&&findpb.getFinancing().length() > 0){
                    if (findpb.getCount() != null&&findpb.getCount().length() > 0){
                        tv_company_type.setText(findpb.getIndustry()+"/"+findpb.getFinancing()+"/"+findpb.getCount());//移动互联网/未融资/20-30人
                    }else{
                        tv_company_type.setText(findpb.getIndustry()+"/"+findpb.getFinancing());//移动互联网/未融资/20-30人
                    }
                }else{
                    if (findpb.getCount() != null&&findpb.getCount().length() > 0){
                        tv_company_type.setText(findpb.getIndustry()+"/"+findpb.getCount());//移动互联网/未融资/20-30人
                    }else{
                        tv_company_type.setText(findpb.getIndustry());//移动互联网/未融资/20-30人
                    }
                }
            }else{
                if (findpb.getFinancing() != null&&findpb.getFinancing().length() > 0){
                    if (findpb.getCount() != null&&findpb.getCount().length() > 0){
                        tv_company_type.setText(findpb.getFinancing()+"/"+findpb.getCount());//移动互联网/未融资/20-30人
                    }else{
                        tv_company_type.setText(findpb.getFinancing());//移动互联网/未融资/20-30人
                    }
                }else{
                    if (findpb.getCount() != null&&findpb.getCount().length() > 0){
                        tv_company_type.setText(findpb.getCount());//移动互联网/未融资/20-30人
                    }else{
                        tv_company_type.setText("");//移动互联网/未融资/20-30人
                    }
                }
            }
            tv_company_introduce.setText(findpb.getCom_introduce()+"");//公司介绍
            tv_product_introduce.setText(findpb.getProdute_info()+"");//产品介绍
            if (findpb.getEvaluate_count() != null&&findpb.getEvaluate_count().length() > 0){
                evaluation_tv_interview.setText("面试评价("+findpb.getEvaluate_count()+")");
            }else{
                evaluation_tv_interview.setText("面试评价(0)");
            }
            jobbean = new Job_boss_adapter(mContext,findbjlist,0);
            lv_job.setAdapter(jobbean);
            jobbean.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.company_details);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
        intent = getIntent();
        companyid = intent.getStringExtra("companyid");
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        detail_headimage = (RoundImageView) findViewById(R.id.company_detail_headimage);
        tv_company_name = (TextView) findViewById(R.id.tv_company_name);//公司名称
        tv_company_type = (TextView) findViewById(R.id.tv_company_type);//公司规模  移动互联网/未融资/20-30人
        tv_company_introduce = (TextView) findViewById(R.id.tv_company_introduce);//公司介绍
        tv_product_introduce = (TextView) findViewById(R.id.tv_product_introduce);//产品介绍
        lv_job = (NoScrollListView) findViewById(R.id.lv_job);//公司职位
        lv_job.setFocusable(false);
        company_detail_image = (RelativeLayout) findViewById(R.id.company_detail_image);//公司图片
        evaluation_the_interview = (WaveView) findViewById(R.id.evaluation_the_interview);//面试评价
        evaluation_the_interview.setOnClickListener(this);
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        evaluation_tv_interview = (TextView) findViewById(R.id.evaluation_tv_interview);

        lv_job.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(mContext,JobInfo.class);
                bundle = new Bundle();
                bundle.putSerializable("jobcompanybean",findbjlist.get(position));
                intent.putExtras(bundle);
                intent.putExtra("company",tv_company_name.getText().toString());
                intent.putExtra("count",String.valueOf(findbjlist.size()));
                intent.putExtra("companyid",String.valueOf(companyid));
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.evaluation_the_interview:
                intent = new Intent(mContext,Job_Evaluation_interview.class);
                intent.putExtra("companyid",String.valueOf(companyid));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新信息");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYCOMMANY(companyid,GETMYCOMMANY);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
