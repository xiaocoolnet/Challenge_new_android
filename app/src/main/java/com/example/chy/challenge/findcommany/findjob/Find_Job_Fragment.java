package com.example.chy.challenge.findcommany.findjob;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.findcommany.findjob.adapter.Find_Job_comapny_adapter;
import com.example.chy.challenge.findcommany.findjob.bean.Find_Job_company_bean;
import com.example.chy.challenge.findcommany.findwork.adapter.Find_work_adapter;
import com.example.chy.challenge.findcommany.findwork.bean.Find_work_bean;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
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
 * Created by Administrator on 2016/12/2 0002.
 */

public class Find_Job_Fragment extends Fragment{
    private static final int GETCOMPANYLIST = 1;
    private View view;
    private Context mContext;
    private UserInfoBean infoBean;
    private Bundle bundle;
    private String pagename;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private List<Find_Job_company_bean.DataBean> companylist = new ArrayList<>();
    private List<Find_Job_company_bean.DataBean.JobsBean> joblist;
    private Find_Job_comapny_adapter companyadapter;
    private Find_Job_company_bean.DataBean companybean;
    private Find_Job_company_bean.DataBean.JobsBean jobbean;
    private Intent intent;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETCOMPANYLIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        companylist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        companybean = new Find_Job_company_bean.DataBean();
                                        companybean.setUserid(jsonobj.getString("userid"));
                                        companybean.setLogo(jsonobj.getString("logo"));
                                        companybean.setCompanyid(jsonobj.getString("companyid"));
                                        companybean.setCompany_name(jsonobj.getString("company_name"));
                                        companybean.setCompany_web(jsonobj.getString("company_web"));
                                        companybean.setIndustry(jsonobj.getString("industry"));
                                        companybean.setCount(jsonobj.getString("count"));
                                        companybean.setFinancing(jsonobj.getString("financing"));
                                        companybean.setAuthentication(jsonobj.getString("authentication"));
                                        companybean.setCreat_time(jsonobj.getString("creat_time"));
                                        companybean.setCompany_score(jsonobj.getString("company_score"));
                                        companybean.setDistance(jsonobj.getString("distance"));
                                        companybean.setProdute_info(jsonobj.getString("produte_info"));
                                        companybean.setCom_introduce(jsonobj.getString("com_introduce"));
                                        joblist = new ArrayList<>();
                                        JSONArray jobjson = jsonobj.getJSONArray("jobs");
                                        if (jobjson != null &&jobjson.length() > 0){
                                            for (int j = 0;j < jobjson.length();j++){
                                                JSONObject job= jobjson.getJSONObject(j);
                                                jobbean = new Find_Job_company_bean.DataBean.JobsBean();
                                                jobbean.setRealname(job.getString("realname"));
                                                jobbean.setPhoto(job.getString("photo"));
                                                jobbean.setMyjob(job.getString("myjob"));
                                                jobbean.setJobid(job.getString("jobid"));
                                                jobbean.setUserid(job.getString("userid"));
                                                jobbean.setJobtype(job.getString("jobtype"));
                                                jobbean.setTitle(job.getString("title"));
                                                jobbean.setSkill(job.getString("skill"));
                                                jobbean.setSalary(job.getString("salary"));
                                                jobbean.setExperience(job.getString("experience"));
                                                jobbean.setEducation(job.getString("education"));
                                                jobbean.setCity(job.getString("city"));
                                                jobbean.setAddress(job.getString("address"));
                                                jobbean.setDescription_job(job.getString("description_job"));
                                                jobbean.setCreate_time(job.getString("create_time"));
                                                jobbean.setWork_property(job.getString("work_property"));
                                                jobbean.setWelfare(job.getString("welfare"));
                                                joblist.add(jobbean);
                                            }
                                            companybean.setJobs(joblist);
                                        }
                                        companylist.add(companybean);
                                    }
                                }
                                companyadapter = new Find_Job_comapny_adapter(mContext,companylist,0);
                                lv_view.setAdapter(companyadapter);
                                companyadapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("加载失败");
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
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findpersonalfragment,container,false);
        mContext = getActivity();
        infoBean = new UserInfoBean(mContext);
        bundle = getArguments();
        if (bundle != null){
            pagename = bundle.getString("pagename");
        }
        getview();
        return view;
    }

    private void getview() {
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
//        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                findwork();
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
//        viewH = LayoutInflater.from(getActivity()).inflate(R.layout.firstpagenew, null);//添加listview头（position-1）
//        lv_view.addHeaderView(viewH);
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(mContext,Job_boss_detail.class);
                intent.putExtra("companyid",companylist.get(position).getUserid());
                startActivity(intent);
            }
        });
    }
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
    public void onResume() {
        super.onResume();
        findwork();
    }

    private void findwork() {
        if ("最新".equals(pagename)){

        }else if ("范围".equals(pagename)){

        }
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETCOMPANYLIST(GETCOMPANYLIST);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
