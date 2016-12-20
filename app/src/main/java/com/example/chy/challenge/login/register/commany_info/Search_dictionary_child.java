package com.example.chy.challenge.login.register.commany_info;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.adapter.Search_child_jobtype;
import com.example.chy.challenge.login.register.adapter.Search_dictionary_adapter;
import com.example.chy.challenge.login.register.bean.Search_dictionary;
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
 * Created by Administrator on 2016/12/16 0016.
 */

public class Search_dictionary_child extends Activity{
    private static final int GETEDUCATION = 1;
    private Intent intent;
    private Search_dictionary.DataBean dictionarybean;
    private Context mContext;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private List<Search_dictionary.DataBean> dictionarylist = new ArrayList<>();
    private List<String> tvlist = new ArrayList<>();
    private Search_child_jobtype dictionaryadapter;
    private String pagetype,pagename;
    private WaveView back;
    private Bundle bundle;



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
                                dictionaryadapter = new Search_child_jobtype(tvlist,mContext,0);
                                lv_view.setAdapter(dictionaryadapter);
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                Job_type_child jobchild = new Job_type_child();
                                bundle = new Bundle();
                                bundle.putString("pageid", dictionarylist.get(0).getTerm_id());
                                bundle.putString("pagename", pagename);
                                jobchild.setArguments(bundle);
                                transaction.replace(R.id.jobtype_child_search,jobchild);
                                transaction.commit();
                                dictionaryadapter.notifyDataSetChanged();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_child_info);
        mContext = this;
        getview();
        intent = getIntent();
        dictionarybean = (Search_dictionary.DataBean) intent.getSerializableExtra("dictionary");
        pagename = intent.getStringExtra("pagetype");
        if (dictionarybean != null){
            pagetype = dictionarybean.getTerm_id();
        }
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) findViewById(R.id.pulllistview);
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
                dictionaryadapter.changeSelected(position);//刷新
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Job_type_child jobchild = new Job_type_child();
                bundle = new Bundle();
                bundle.putString("pageid", dictionarylist.get(position).getTerm_id());
                bundle.putString("pagename", pagename);
                jobchild.setArguments(bundle);
                transaction.replace(R.id.jobtype_child_search,jobchild);
                transaction.commit();

//                intent = new Intent(mContext,Search_dictionary_child.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("dictionary",dictionarylist.get(position));
//                intent.putExtras(bundle);
//                startActivity(intent);
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
    protected void onResume() {
        super.onResume();
        jobtype();
    }

    private void jobtype() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETDICTIONARYLIST(pagetype, GETEDUCATION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
    public void finishview(){
        finish();
    }
}
