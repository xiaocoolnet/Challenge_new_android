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
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.adapter.Black_Friend_Adapter;
import com.example.chy.challenge.Findpersoanl.mine.bean.Black_friend_bean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.adapter.Black_personal_Adapter;
import com.example.chy.challenge.findcommany.mine.bean.Black_personal_bean;
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

public class Black_personal_Friend extends Activity implements View.OnClickListener {
    private Context mContext;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private UserInfoBean infobean;
    private RelativeLayout ril_back;
    private WaveView back;
    private Black_personal_bean.DataBean blackbean;
    private Black_personal_bean.DataBean.BlacksBean blacksbean;
    private List<Black_personal_bean.DataBean> blacklist = new ArrayList<>();
    private List<Black_personal_bean.DataBean.BlacksBean> blackslist;

    private final static int GETBLACKlIST = 1;
    public final static int DELBLACKlIST = 3;
    private Black_personal_Adapter blackadapter;
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
                                        blackbean = new Black_personal_bean.DataBean();
                                        blackbean.setId(jsonobj.getString("id"));
                                        blackbean.setUserid(jsonobj.getString("userid"));
                                        blackbean.setBlackid(jsonobj.getString("blackid"));
                                        blackbean.setCreate_time(jsonobj.getString("create_time"));
                                        blackbean.setStatus(jsonobj.getString("status"));
                                        blackbean.setReason(jsonobj.getString("reason"));
                                        blackbean.setType(jsonobj.getString("type"));
                                        blackslist = new ArrayList<>();
                                        JSONArray jsoneducation = jsonobj.getJSONArray("blacks");
                                        for (int m = 0; m < jsoneducation.length(); m++) {
                                            JSONObject objeducation = jsoneducation.getJSONObject(i);
                                            blacksbean = new Black_personal_bean.DataBean.BlacksBean();
                                            blacksbean.setUserid(objeducation.getString("userid"));
                                            blacksbean.setLogo(objeducation.getString("logo"));
                                            blacksbean.setCompanyid(objeducation.getString("companyid"));
                                            blacksbean.setCompany_name(objeducation.getString("company_name"));
                                            blacksbean.setCompany_web(objeducation.getString("company_web"));
                                            blacksbean.setIndustry(objeducation.getString("industry"));
                                            blacksbean.setCount(objeducation.getString("count"));
                                            blacksbean.setFinancing(objeducation.getString("financing"));
                                            blacksbean.setCreat_time(objeducation.getString("creat_time"));
                                            blacksbean.setAuthentication(objeducation.getString("authentication"));
                                            blacksbean.setCompany_score(objeducation.getString("company_score"));
                                            blacksbean.setDistance(objeducation.getString("distance"));
                                            blacksbean.setProdute_info(objeducation.getString("produte_info"));
                                            blacksbean.setCom_introduce(objeducation.getString("com_introduce"));
                                            blackslist.add(blacksbean);
                                        }
                                        blackbean.setBlacks(blackslist);
                                        blacklist.add(blackbean);
                                    }
                                }
                                blackadapter = new Black_personal_Adapter(blackslist,mContext,0,handler);
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
                        new UserRequest(mContext, handler).DELBLACKLIST(infobean.getUserid(), "1",blackslist.get(position).getUserid(), DELBLACKlIST);
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
//        lv_view.addHeaderView(viewH);

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
//            new UserRequest(mContext, handler).GETBLACKlIST(infobean.getUserid(), "1", GETBLACKlIST);
            new UserRequest(mContext, handler).GETBLACKlIST("301", "1", GETBLACKlIST);
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
