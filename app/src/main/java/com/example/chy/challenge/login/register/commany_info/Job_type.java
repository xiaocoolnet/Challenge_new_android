package com.example.chy.challenge.login.register.commany_info;

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
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.login.register.adapter.Search_dictionary_adapter;
import com.example.chy.challenge.login.register.bean.Search_dictionary;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
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
 * Created by Administrator on 2016/11/29 0029.
 */

public class Job_type extends Activity implements View.OnClickListener{
    private static final int GETEDUCATION = 1;
    private RelativeLayout ril_back;
    private WaveView back;
    private TextView title_top;
    private Context mContext;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Search_dictionary.DataBean dictionarybean;
    private List<Search_dictionary.DataBean> dictionarylist = new ArrayList<>();
    private Search_dictionary_adapter dictionaryadapter;
    private Intent intent;
    private String pagetype;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        dictionarylist.clear();
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
                                    }
                                }
                                dictionaryadapter = new Search_dictionary_adapter(dictionarylist,mContext,0);
                                lv_view.setAdapter(dictionaryadapter);
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
        setContentView(R.layout.findpersonalfragment);
        mContext = this;
        Public_static_all.JobType = this;
        getview();
        intent = getIntent();
        pagetype = intent.getStringExtra("pagetype");
    }

    private void getview() {
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("选择职位");

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
                intent = new Intent(mContext,Search_dictionary_child.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dictionary",dictionarylist.get(position));
                intent.putExtra("pagetype",pagetype);
                intent.putExtras(bundle);
                startActivity(intent);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
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
            new UserRequest(mContext, handler).GETDICTIONARYLIST("1", GETEDUCATION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
    public void finishview(){
        finish();
    }
}
