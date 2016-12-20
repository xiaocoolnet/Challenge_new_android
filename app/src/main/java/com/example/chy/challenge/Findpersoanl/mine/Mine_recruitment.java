package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.adapter.Recruitment_Adapter;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.NoPosition;
import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.pnlllist.PullToRefreshBase;
import com.example.chy.challenge.pnlllist.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Mine_recruitment extends Activity implements View.OnClickListener{
    private static final int KEY_GET_CODE = 1;
    private RelativeLayout ril_back;
    private WaveView back;
    private TextView title_top;
    private Context mContext;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private FindPersonalBean.DataBean findpb;
    private FindPersonalBean.DataBean.JobsBean findbj;
    private List<FindPersonalBean.DataBean.JobsBean> findbjlist;
    private Recruitment_Adapter recruitmentadapter;


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
                                    findbjlist.add(findbj);
                                }
                                findpb.setJobs(findbjlist);
                                recruitmentadapter = new Recruitment_Adapter(findbjlist,mContext,0);
                                lv_view.setAdapter(recruitmentadapter);
                                recruitmentadapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
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
                                //没有数据
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
                        //网络错误
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpersonalfragment);
        mContext = this;
        getview();
    }

    private void getview() {
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("我的招聘");
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
//        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView)findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                iniview();
                stopRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                stopRefresh();
            }
        });
        setLastData();
        lv_view = pulllistview.getRefreshableView();
        lv_view.setDivider(null);//去掉listview自带的分割线

    }
    //获取当前时间
    private void setLastData() {
        String text = formatdatatime(System.currentTimeMillis());
        pulllistview.setLastUpdatedLabel(text);
        Log.i("time", "-------->" + text);
    }

    //停止刷新
    private void stopRefresh() {
        pulllistview.postDelayed(new Runnable() {
            @Override
            public void run() {
                pulllistview.onPullUpRefreshComplete();
                pulllistview.onPullDownRefreshComplete();
                setLastData();
            }
        }, 2000);
    }
    private String formatdatatime(long time) {
        if (0 == time) {
            return "";
        }
        return mdata.format(new Date(time));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniview();
    }

    private void iniview() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在加载...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
//            new UserRequest(mContext, handler).GETMYCOMMANY(userinfo.getUserid(),KEY_GET_CODE);
            new UserRequest(mContext, handler).GETMYCOMMANY("301",KEY_GET_CODE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
