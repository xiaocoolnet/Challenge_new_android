package com.example.chy.challenge.findcommany.resume;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.adapter.Resume_education_adapter;
import com.example.chy.challenge.findcommany.resume.bean.Resume_education_bean;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;
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
 * Created by Administrator on 2016/12/21 0021.
 */

public class Write_resume_education extends Activity implements View.OnClickListener{
    private static final int GETMYEDULIST = 1;
    private Context mContext;
    private UserInfoBean infobean;
    private WaveView back,education_update,add_education;
    private RelativeLayout ril_back;
    private TextView title_top;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private View viewH;
    private Resume_education_bean.DataBean educationbean;
    private List<Resume_education_bean.DataBean> educationlist = new ArrayList<>();
    private Resume_education_adapter educationadapter;
    private Intent intent;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETMYEDULIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        educationlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        educationbean = new Resume_education_bean.DataBean();
                                        educationbean.setUserid(jsonobj.getString("userid"));
                                        educationbean.setSchool(jsonobj.getString("school"));
                                        educationbean.setMajor(jsonobj.getString("major"));
                                        educationbean.setDegree(jsonobj.getString("degree"));
                                        educationbean.setTime(jsonobj.getString("time"));
                                        educationbean.setExperience(jsonobj.getString("experience"));
                                        educationbean.setCreate_time(jsonobj.getString("create_time"));
                                        educationlist.add(educationbean);
                                    }
                                }
                                educationadapter = new Resume_education_adapter(educationlist,0,mContext);
                                lv_view.setAdapter(educationadapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.findpersonalfragment);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("教育经历");
        education_update = (WaveView) findViewById(R.id.education_update);
        education_update.setOnClickListener(this);
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        pulllistview = (PullToRefreshListView) findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getinfo();
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
        viewH = LayoutInflater.from(mContext).inflate(R.layout.education_footer, null);//添加listview头（position-1）
        lv_view.addFooterView(viewH);
        add_education = (WaveView) viewH.findViewById(R.id.add_education);
        add_education.setOnClickListener(this);
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
            case R.id.education_update://编辑
                break;
            case R.id.add_education://添加
                intent = new Intent(mContext,Register_next_education.class);
                intent.putExtra("pagetype","add");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getinfo();
    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYEDULIST(infobean.getUserid(),  GETMYEDULIST);
//            new UserRequest(mContext, handler).GETBLACKlIST("301", "2", GETBLACKlIST);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
