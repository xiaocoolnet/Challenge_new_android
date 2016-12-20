package com.example.chy.challenge.Findpersoanl.mine.invitation;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.adapter.InVitation_wait_Adapter;
import com.example.chy.challenge.Findpersoanl.mine.bean.Invitation_wait_bean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
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
 * Created by Administrator on 2016/11/20 0020.
 */

public class Invitation_Wait extends Fragment{
    private static final int GETMYINVITED = 1;
    private View view;
    private Bundle bundle;
    private String pagename;
    private Context mContext;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ListView lv_view;
    private UserInfoBean infoBean;
    private Invitation_wait_bean.DataBean invitationbean;
    private InVitation_wait_Adapter invitationadapter;
    private List<Invitation_wait_bean.DataBean> invitationlist = new ArrayList<>();

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETMYINVITED:
                    if (msg.obj != null){
                         String result = (String) msg.obj;
                        invitationlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        invitationbean = new Invitation_wait_bean.DataBean();
                                        invitationbean.setUserid(jsonobj.getString("userid"));
                                        invitationbean.setUserid(jsonobj.getString("realname"));
                                        invitationbean.setUserid(jsonobj.getString("sex"));
                                        invitationbean.setUserid(jsonobj.getString("photo"));
                                        invitationbean.setUserid(jsonobj.getString("jobtype"));
                                        invitationbean.setUserid(jsonobj.getString("address"));
                                        invitationbean.setUserid(jsonobj.getString("create_time"));
                                        invitationlist.add(invitationbean);
                                    }
                                }
                                invitationadapter = new InVitation_wait_Adapter(0,mContext,invitationlist);
                                lv_view.setAdapter(invitationadapter);
                                invitationadapter.notifyDataSetChanged();

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
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findpersonalfragment,container,false);
        mContext = getActivity();
        infoBean = new UserInfoBean(mContext);
        getview();
        bundle = getArguments();
        if (bundle != null){
            pagename = bundle.getString("pagename");
            if ("待面试".equals(pagename)){
                iniview();
            }else if ("已结束".equals(pagename)){
                iniviewwait();
            }
        }else{
            iniview();
        }

        return view;
    }

    private void getview() {
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if ("待面试".equals(pagename)){
                    iniview();
                }else if ("已结束".equals(pagename)){
                    iniviewwait();
                }
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
    private void iniview() {
        //我的待面试记录接口
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在提交..");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYINVITED(infoBean.getUserid(),"0", GETMYINVITED);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
    private void iniviewwait() {
        //我的结束面试记录
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在提交..");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYINVITED(infoBean.getUserid(),"1", GETMYINVITED);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
