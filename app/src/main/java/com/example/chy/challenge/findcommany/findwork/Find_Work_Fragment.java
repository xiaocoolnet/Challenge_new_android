package com.example.chy.challenge.findcommany.findwork;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.findcommany.findjob.JobInfo;
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

public class Find_Work_Fragment extends Fragment{
    private static final int GETJOBLIST = 1;
    private View view;
    private Context mContext;
    private UserInfoBean infoBean;
    private Bundle bundle;
    private String pagename;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private Find_work_bean.DataBean workbean;
    private List<Find_work_bean.DataBean> worlist = new ArrayList<>();
    private Find_work_adapter woradapter;
    private Intent intent;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETJOBLIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        worlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        workbean = new Find_work_bean.DataBean();
                                        workbean.setRealname(jsonobj.getString("realname"));
                                        workbean.setMyjob(jsonobj.getString("myjob"));
                                        workbean.setJobid(jsonobj.getString("jobid"));
                                        workbean.setUserid(jsonobj.getString("userid"));
                                        workbean.setJobtype(jsonobj.getString("jobtype"));
                                        workbean.setWork_property(jsonobj.getString("work_property"));
                                        workbean.setTitle(jsonobj.getString("title"));
                                        workbean.setSkill(jsonobj.getString("skill"));
                                        workbean.setSalary(jsonobj.getString("salary"));
                                        workbean.setExperience(jsonobj.getString("experience"));
                                        workbean.setEducation(jsonobj.getString("education"));
                                        workbean.setCity(jsonobj.getString("city"));
                                        workbean.setAddress(jsonobj.getString("address"));
                                        workbean.setDescription_job(jsonobj.getString("description_job"));
                                        workbean.setCreate_time(jsonobj.getString("create_time"));
                                        workbean.setWelfare(jsonobj.getString("welfare"));
                                        workbean.setLogo(jsonobj.getString("logo"));
                                        workbean.setCompanyid(jsonobj.getString("companyid"));
                                        workbean.setCompany_name(jsonobj.getString("company_name"));
                                        workbean.setCompany_web(jsonobj.getString("company_web"));
                                        workbean.setIndustry(jsonobj.getString("industry"));
                                        workbean.setCount(jsonobj.getString("count"));
                                        workbean.setFinancing(jsonobj.getString("financing"));
                                        workbean.setAuthentication(jsonobj.getString("authentication"));
                                        workbean.setCompany_score(jsonobj.getString("company_score"));
                                        workbean.setDistance(jsonobj.getString("distance"));
                                        workbean.setCom_introduce(jsonobj.getString("com_introduce"));
                                        workbean.setProdute_info(jsonobj.getString("produte_info"));
                                        workbean.setJob_count(jsonobj.getString("job_count"));
                                        worlist.add(workbean);
                                    }
                                }
                                woradapter = new Find_work_adapter(mContext,worlist,0);
                                lv_view.setAdapter(woradapter);
                                woradapter.notifyDataSetChanged();
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
                intent = new Intent(mContext,WorkInfo.class);
                bundle = new Bundle();
                bundle.putSerializable("jobcompanybean",worlist.get(position));
                intent.putExtras(bundle);
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

        }else if ("最热".equals(pagename)){

        }else if ("最近".equals(pagename)){

        }else if ("评价".equals(pagename)){

        }else if ("职位红包".equals(pagename)){

        }else if ("面试红包".equals(pagename)){

        }else if ("就职红包".equals(pagename)){

        }else if ("全部红包".equals(pagename)){

        }else{

        }
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETJOBLIST(GETJOBLIST);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
