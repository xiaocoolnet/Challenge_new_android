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
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.adapter.Mine_personal_Collect_Adapter;
import com.example.chy.challenge.findcommany.mine.adapter.Submit_Resume_Adapter;
import com.example.chy.challenge.findcommany.mine.bean.Mine_personal_Collect_bean;
import com.example.chy.challenge.findcommany.mine.bean.Submit_Resume_bean;
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
 * Created by Administrator on 2016/12/5 0005.
 */

public class Mine_personal_Collect extends Activity implements View.OnClickListener{
    private static final int GETFAVORITE = 1;
    private Context mContext;
    private WaveView back,collect_delet;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private UserInfoBean infobean;
    private List<Mine_personal_Collect_bean.DataBean> collectlist = new ArrayList<>();
    private Mine_personal_Collect_bean.DataBean collectbean;
    private Mine_personal_Collect_Adapter collectadapter;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETFAVORITE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        collectlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        collectbean = new Mine_personal_Collect_bean.DataBean();
                                        collectbean.setF_id(jsonobj.getString("f_id"));
                                        collectbean.setF_userid(jsonobj.getString("f_userid"));
                                        collectbean.setF_title(jsonobj.getString("f_title"));
                                        collectbean.setF_description(jsonobj.getString("f_description"));
                                        collectbean.setF_type(jsonobj.getString("f_type"));
                                        collectbean.setF_object_id(jsonobj.getString("f_object_id"));
                                        collectbean.setF_create_time(jsonobj.getString("f_create_time"));
                                        collectbean.setRealname(jsonobj.getString("realname"));
                                        collectbean.setMyjob(jsonobj.getString("myjob"));
                                        collectbean.setJobid(jsonobj.getString("jobid"));
                                        collectbean.setUserid(jsonobj.getString("userid"));
                                        collectbean.setJobtype(jsonobj.getString("jobtype"));
                                        collectbean.setWork_property(jsonobj.getString("work_property"));
                                        collectbean.setTitle(jsonobj.getString("title"));
                                        collectbean.setSkill(jsonobj.getString("skill"));
                                        collectbean.setSalary(jsonobj.getString("salary"));
                                        collectbean.setExperience(jsonobj.getString("experience"));
                                        collectbean.setEducation(jsonobj.getString("education"));
                                        collectbean.setCity(jsonobj.getString("city"));
                                        collectbean.setAddress(jsonobj.getString("address"));
                                        collectbean.setDescription_job(jsonobj.getString("description_job"));
                                        collectbean.setCreate_time(jsonobj.getString("create_time"));
                                        collectbean.setWelfare(jsonobj.getString("welfare"));
                                        collectbean.setLogo(jsonobj.getString("logo"));
                                        collectbean.setCompanyid(jsonobj.getString("companyid"));
                                        collectbean.setCompany_name(jsonobj.getString("company_name"));
                                        collectbean.setCompany_web(jsonobj.getString("company_web"));
                                        collectbean.setIndustry(jsonobj.getString("industry"));
                                        collectbean.setCount(jsonobj.getString("count"));
                                        collectbean.setFinancing(jsonobj.getString("financing"));
                                        collectbean.setAuthentication(jsonobj.getString("authentication"));
                                        collectbean.setCompany_score(jsonobj.getString("company_score"));
                                        collectbean.setDistance(jsonobj.getString("distance"));
                                        collectbean.setCom_introduce(jsonobj.getString("com_introduce"));
                                        collectbean.setProdute_info(jsonobj.getString("produte_info"));
                                        collectlist.add(collectbean);
                                    }
                                }
                                collectadapter = new Mine_personal_Collect_Adapter(collectlist,mContext,0);
                                lv_view.setAdapter(collectadapter);
                                collectadapter.notifyDataSetChanged();
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
        setContentView(R.layout.mine_collect);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        collect_delet = (WaveView) findViewById(R.id.collect_delet);
        collect_delet.setOnClickListener(this);

        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.collect_delet:
                Toast.makeText(mContext,"暂未开放此功能",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sublitresume();
    }

    private void sublitresume() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETFAVORITE("301","1",GETFAVORITE);
//            new UserRequest(mContext, handler).GETFAVORITE(infobean.getUserid(),"1",GETFAVORITE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
