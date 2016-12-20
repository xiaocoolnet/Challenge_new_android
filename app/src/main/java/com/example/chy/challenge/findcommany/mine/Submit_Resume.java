package com.example.chy.challenge.findcommany.mine;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.findwork.adapter.Find_work_adapter;
import com.example.chy.challenge.findcommany.findwork.bean.Find_work_bean;
import com.example.chy.challenge.findcommany.mine.adapter.Submit_Resume_Adapter;
import com.example.chy.challenge.findcommany.mine.bean.Submit_Resume_bean;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
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
 * Created by Administrator on 2016/12/3 0003.
 */

public class Submit_Resume extends Activity{
    private static final int GETMYAPPLYJOB = 1;
    private Context mContext;
    private UserInfoBean infobean;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private Submit_Resume_bean.DataBean.ApplysBean resumebean;
    private Submit_Resume_bean.DataBean databean;
    private List<Submit_Resume_bean.DataBean> resumelist = new ArrayList<>();
    private Submit_Resume_Adapter resumtadapter;
    private RelativeLayout ril_back;
    private WaveView back;
    private TextView title_top;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETMYAPPLYJOB:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        resumelist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        databean = new Submit_Resume_bean.DataBean();
                                        databean.setJr_companyid(jsonobj.getString("jr_companyid"));
                                        databean.setJr_jobid(jsonobj.getString("jr_jobid"));
                                        databean.setJr_userid(jsonobj.getString("jr_userid"));
                                        databean.setJr_create_time(jsonobj.getString("jr_create_time"));
                                        if(jsonobj.getString("applys") != null&&jsonobj.getString("applys").length() > 0) {
                                            JSONObject objapplys = new JSONObject(jsonobj.getString("applys"));
                                            resumebean = new Submit_Resume_bean.DataBean.ApplysBean();
                                            resumebean.setRealname(objapplys.getString("realname"));
                                            resumebean.setMyjob(objapplys.getString("myjob"));
                                            resumebean.setJobid(objapplys.getString("jobid"));
                                            resumebean.setUserid(objapplys.getString("userid"));
                                            resumebean.setJobtype(objapplys.getString("jobtype"));
                                            resumebean.setWork_property(objapplys.getString("work_property"));
                                            resumebean.setTitle(objapplys.getString("title"));
                                            resumebean.setSkill(objapplys.getString("skill"));
                                            resumebean.setSalary(objapplys.getString("salary"));
                                            resumebean.setExperience(objapplys.getString("experience"));
                                            resumebean.setEducation(objapplys.getString("education"));
                                            resumebean.setCity(objapplys.getString("city"));
                                            resumebean.setAddress(objapplys.getString("address"));
                                            resumebean.setDescription_job(objapplys.getString("description_job"));
                                            resumebean.setCreate_time(objapplys.getString("create_time"));
                                            resumebean.setWelfare(objapplys.getString("welfare"));
                                            resumebean.setLogo(objapplys.getString("logo"));
                                            resumebean.setCompanyid(objapplys.getString("companyid"));
                                            resumebean.setCompany_name(objapplys.getString("company_name"));
                                            resumebean.setCompany_web(objapplys.getString("company_web"));
                                            resumebean.setIndustry(objapplys.getString("industry"));
                                            resumebean.setCount(objapplys.getString("count"));
                                            resumebean.setFinancing(objapplys.getString("financing"));
                                            resumebean.setAuthentication(objapplys.getString("authentication"));
                                            resumebean.setCompany_score(objapplys.getString("company_score"));
                                            resumebean.setDistance(objapplys.getString("distance"));
                                            resumebean.setCom_introduce(objapplys.getString("com_introduce"));
                                            resumebean.setProdute_info(objapplys.getString("produte_info"));
                                            databean.setApplys(resumebean);
                                        }else{
                                            resumebean = new Submit_Resume_bean.DataBean.ApplysBean();
                                            resumebean.setRealname("");
                                            resumebean.setMyjob("");
                                            resumebean.setJobid("");
                                            resumebean.setUserid("");
                                            resumebean.setJobtype("");
                                            resumebean.setWork_property("");
                                            resumebean.setTitle("");
                                            resumebean.setSkill("");
                                            resumebean.setSalary("");
                                            resumebean.setExperience("");
                                            resumebean.setEducation("");
                                            resumebean.setCity("");
                                            resumebean.setAddress("");
                                            resumebean.setDescription_job("");
                                            resumebean.setCreate_time("");
                                            resumebean.setWelfare("");
                                            resumebean.setLogo("");
                                            resumebean.setCompanyid("");
                                            resumebean.setCompany_name("");
                                            resumebean.setCompany_web("");
                                            resumebean.setIndustry("");
                                            resumebean.setCount("");
                                            resumebean.setFinancing("");
                                            resumebean.setAuthentication("");
                                            resumebean.setCompany_score("");
                                            resumebean.setDistance("");
                                            resumebean.setCom_introduce("");
                                            resumebean.setProdute_info("");
                                            databean.setApplys(resumebean);
                                        }
                                        resumelist.add(databean);
                                    }
                                }
                                resumtadapter = new Submit_Resume_Adapter(resumelist,mContext,0);
                                lv_view.setAdapter(resumtadapter);
                                resumtadapter.notifyDataSetChanged();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpersonalfragment);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("我的投递记录");
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pulllistview = (PullToRefreshListView) findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                sublitresume();
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
    protected void onResume() {
        super.onResume();
        sublitresume();
    }
//    userid=301&object_id=1&type=1
    private void sublitresume() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYAPPLYJOB("301",GETMYAPPLYJOB);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
