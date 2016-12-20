package com.example.chy.challenge.login.register.commany_info;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.login.register.adapter.Search_child_job;
import com.example.chy.challenge.login.register.adapter.Search_child_jobtype;
import com.example.chy.challenge.login.register.bean.Search_dictionary;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
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
 * Created by Administrator on 2016/12/17 0017.
 */

public class Job_type_child extends Fragment{
    private static final int GETEDUCATION = 1;
    private View view;
    private Bundle bundle;
    private String pageid,pagename;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context mContext;
    private List<Search_dictionary.DataBean> dictionarylist = new ArrayList<>();
    private List<String> tvlist = new ArrayList<>();
    private Search_dictionary.DataBean dictionarybean;
    private Search_child_job dictionaryadapter;
    private UserInfo info;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        dictionarylist.clear();
                        tvlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0) {
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        dictionarybean = new Search_dictionary.DataBean();
                                        dictionarybean.setTerm_id(jsonobj.getString("term_id"));
                                        dictionarybean.setName(jsonobj.getString("name"));
                                        dictionarybean.setDescription(jsonobj.getString("description"));
                                        dictionarylist.add(dictionarybean);
                                        tvlist.add(jsonobj.getString("name"));
                                    }
                                }
                                dictionaryadapter = new Search_child_job(tvlist,mContext,0);
                                lv_view.setAdapter(dictionaryadapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
//                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findpersonalfragment,container,false);
        mContext = getActivity();
        info = new UserInfo(mContext);
        bundle = getArguments();
        if (bundle != null){
            pageid = bundle.getString("pageid");
            pagename = bundle.getString("pagename");
        }
        getview();
        return view;
    }

    private void getview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.pulllistview);
        pulllistview.setBackgroundResource(R.color.white);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                jobtype();
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
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("jobnews".equals(pagename)){
                    Public_static_all.isJobb = true;
                    info.setPosition_type(tvlist.get(position)+"");
                    Search_dictionary_child jobchild = (Search_dictionary_child) getActivity();
                    jobchild.finishview();
                    Public_static_all.JobType.finish();
                }else if ("intenttion".equals(pagename)){
                    Public_static_all.isintenttiona = true;
                    info.setPersonal_type(tvlist.get(position)+"");
                    Search_dictionary_child jobchild = (Search_dictionary_child) getActivity();
                    jobchild.finishview();
                    Public_static_all.JobType.finish();
                }

            }
        });
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
    public void onResume() {
        super.onResume();
        jobtype();
    }

    private void jobtype() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETDICTIONARYLIST(pageid, GETEDUCATION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
