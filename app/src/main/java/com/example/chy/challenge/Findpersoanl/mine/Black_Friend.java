package com.example.chy.challenge.Findpersoanl.mine;

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
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.adapter.Black_Friend_Adapter;
import com.example.chy.challenge.Findpersoanl.mine.bean.Black_friend_bean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
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
 * Created by Administrator on 2016/11/19 0019.
 */

public class Black_Friend extends Activity implements View.OnClickListener {
    private Context mContext;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private UserInfoBean infobean;
    private RelativeLayout ril_back;
    private WaveView back;
    private Black_friend_bean.DataBean blackbean;
    private Black_friend_bean.DataBean.BlacksBean blacksbean;
    private Black_friend_bean.DataBean.BlacksBean.EducationBean educationbean;
    private Black_friend_bean.DataBean.BlacksBean.WorkBean workbean;
    private Black_friend_bean.DataBean.BlacksBean.ProjectBean projectbean;
    private List<Black_friend_bean.DataBean> blacklist = new ArrayList<>();
    private List<Black_friend_bean.DataBean.BlacksBean.EducationBean> educationlist;
    private List<Black_friend_bean.DataBean.BlacksBean.WorkBean> worklist;
    private List<Black_friend_bean.DataBean.BlacksBean.ProjectBean> projectlist;
    private final static int GETBLACKlIST = 1;
    public final static int DELBLACKlIST = 3;
    private Black_Friend_Adapter blackadapter;
    private int position;


    private Handler handler = new Handler(Looper.myLooper()) {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETBLACKlIST:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        blacklist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))) {
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null && jsonarray.length() > 0) {
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        blackbean = new Black_friend_bean.DataBean();
                                        blackbean.setId(jsonobj.getString("id"));
                                        blackbean.setUserid(jsonobj.getString("userid"));
                                        blackbean.setBlackid(jsonobj.getString("blackid"));
                                        blackbean.setCreate_time(jsonobj.getString("create_time"));
                                        blackbean.setStatus(jsonobj.getString("status"));
                                        blackbean.setReason(jsonobj.getString("reason"));
                                        blackbean.setType(jsonobj.getString("type"));
                                        JSONObject objblack = new JSONObject(jsonobj.getString("blacks"));
                                        blacksbean = new Black_friend_bean.DataBean.BlacksBean();
                                        blacksbean.setUserid(objblack.getString("userid"));
                                        blacksbean.setRealname(objblack.getString("realname"));
                                        blacksbean.setUsertype(objblack.getString("usertype"));
                                        blacksbean.setPhone(objblack.getString("phone"));
                                        blacksbean.setPassword(objblack.getString("password"));
                                        blacksbean.setSex(objblack.getString("sex"));
                                        blacksbean.setEmail(objblack.getString("email"));
                                        blacksbean.setQq(objblack.getString("qq"));
                                        blacksbean.setWeixin(objblack.getString("weixin"));
                                        blacksbean.setPhoto(objblack.getString("photo"));
                                        blacksbean.setDevicestate(objblack.getString("devicestate"));
                                        blacksbean.setCity(objblack.getString("city"));
                                        blacksbean.setWeibo(objblack.getString("weibo"));
                                        blacksbean.setWork_life(objblack.getString("work_life"));
                                        blacksbean.setCompany(objblack.getString("company"));
                                        blacksbean.setMyjob(objblack.getString("myjob"));
                                        blacksbean.setResumes_id(objblack.getString("resumes_id"));
                                        blacksbean.setWork_property(objblack.getString("work_property"));
                                        blacksbean.setAddress(objblack.getString("address"));
                                        blacksbean.setPosition_type(objblack.getString("position_type"));
                                        blacksbean.setCategories(objblack.getString("categories"));
                                        blacksbean.setWantsalary(objblack.getString("wantsalary"));
                                        blacksbean.setJobstate(objblack.getString("jobstate"));
                                        blacksbean.setAdvantage(objblack.getString("advantage"));
                                        educationlist = new ArrayList<>();
                                        JSONArray jsoneducation = objblack.getJSONArray("education");
                                        for (int m = 0; m < jsoneducation.length(); m++) {
                                            JSONObject objeducation = jsoneducation.getJSONObject(i);
                                            educationbean = new Black_friend_bean.DataBean.BlacksBean.EducationBean();
                                            educationbean.setUserid(objeducation.getString("userid"));
                                            educationbean.setSchool(objeducation.getString("school"));
                                            educationbean.setMajor(objeducation.getString("major"));
                                            educationbean.setDegree(objeducation.getString("degree"));
                                            educationbean.setTime(objeducation.getString("time"));
                                            educationbean.setExperience(objeducation.getString("experience"));
                                            educationbean.setCreate_time(objeducation.getString("create_time"));
                                            educationlist.add(educationbean);
                                        }
                                        blacksbean.setEducation(educationlist);
                                        worklist = new ArrayList<>();
                                        JSONArray objwork = objblack.getJSONArray("work");
                                        for (int l = 0; l < objwork.length(); l++) {
                                            JSONObject jsonwork = objwork.getJSONObject(l);
                                            workbean = new Black_friend_bean.DataBean.BlacksBean.WorkBean();
                                            workbean.setUserid(jsonwork.getString("userid"));
                                            workbean.setCompany_name(jsonwork.getString("company_name"));
                                            workbean.setCompany_industry(jsonwork.getString("company_industry"));
                                            workbean.setJobtype(jsonwork.getString("jobtype"));
                                            workbean.setSkill(jsonwork.getString("skill"));
                                            workbean.setWork_period(jsonwork.getString("work_period"));
                                            workbean.setCreate_time(jsonwork.getString("create_time"));
                                            workbean.setContent(jsonwork.getString("content"));
                                            worklist.add(workbean);
                                        }
                                        blacksbean.setWork(worklist);
                                        projectlist = new ArrayList<>();
                                        JSONArray jsonproject = objblack.getJSONArray("project");
                                        for (int k = 0; k < jsonproject.length(); k++) {
                                            JSONObject objproject = jsonproject.getJSONObject(k);
                                            projectbean = new Black_friend_bean.DataBean.BlacksBean.ProjectBean();
                                            projectbean.setUserid(objproject.getString("userid"));
                                            projectbean.setProject_name(objproject.getString("project_name"));
                                            projectbean.setStart_time(objproject.getString("start_time"));
                                            projectbean.setEnd_time(objproject.getString("end_time"));
                                            projectbean.setDescription_project(objproject.getString("description_project"));
                                            projectbean.setCreate_time(objproject.getString("create_time"));
                                            projectlist.add(projectbean);
                                        }
                                        blacksbean.setProject(projectlist);
                                        blackbean.setBlacks(blacksbean);
                                        blacklist.add(blackbean);
                                    }
                                }
                                blackadapter = new Black_Friend_Adapter(blacklist,mContext,0,handler);
                                lv_view.setAdapter(blackadapter);
                                blackadapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(mContext,"没有数据", Toast.LENGTH_SHORT).show();
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
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        Toast.makeText(mContext,R.string.net_error, Toast.LENGTH_SHORT).show();
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
                    }
                    dialog.dismiss();
                    break;
                case 2:
                    position = (int) msg.obj;
                    if (NetBaseUtils.isConnnected(mContext)) {
                        dialog.setMessage("正在取消...");
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.show();
                        new UserRequest(mContext, handler).DELBLACKLIST(infobean.getUserid(), "2",blacklist.get(position).getBlackid(), DELBLACKlIST);
                    } else {
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DELBLACKlIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                Toast.makeText(mContext,"成功取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                findpersonfragmentview();
                            }else{
                                Toast.makeText(mContext,"取消失败", Toast.LENGTH_SHORT).show();
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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext,R.string.net_error, Toast.LENGTH_SHORT).show();
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
        getfragmentView();
    }

    private void getfragmentView() {
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        //弹出刷新提示框
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
//        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                findpersonfragmentview();
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
//        lv_view.addFooterView(viewH);

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
    protected void onResume() {
        super.onResume();
        findpersonfragmentview();
    }

    private void findpersonfragmentview() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETBLACKlIST(infobean.getUserid(), "2", GETBLACKlIST);
//            new UserRequest(mContext, handler).GETBLACKlIST("301", "2", GETBLACKlIST);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
