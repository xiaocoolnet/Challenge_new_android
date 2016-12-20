package com.example.chy.challenge.login.register.commany_info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.button.XCFlowLayout;
import com.example.chy.challenge.login.register.register_bean.UserInfo;

import android.view.ViewGroup.MarginLayoutParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class Job_experice extends Activity {
    private static final int GETEDUCATION = 1;
    private Context mContext;
    private WaveView back, submit_job_experice;
    private TextView view;
    private GridView jobshow_gridview;
    private List<String> skilllist = new ArrayList<>();
    private UserInfo info;
    private XCFlowLayout flowlayout;
    private MarginLayoutParams lp;
    private Intent intent;
    private String pagetype;


    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETEDUCATION:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        skilllist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))) {
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null && jsonarray.length() > 0) {
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        final JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        skilllist.add(jsonobj.getString("name"));

                                        view = new TextView(mContext);
                                        view.setText(jsonobj.getString("name"));
                                        view.setTextColor(getResources().getColor(R.color.gray4));
                                        view.setPadding(55, 20, 55, 20);
                                        view.setTextSize(16);
                                        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.job_show_skills));
                                        flowlayout.addView(view, lp);
                                        view.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if ("jobnews".equals(pagetype)) {
                                                    try {
                                                        info.setCommany_industry(jsonobj.getString("name") + "");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Public_static_all.isJoba = true;
                                                    finish();
                                                }else if ("intenttion".equals(pagetype)){
                                                    try {
                                                        info.setPersonal_industry(jsonobj.getString("name") + "");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Public_static_all.isintenttionb = true;
                                                    finish();
                                                }
                                            }
                                        });
                                    }
                                }
//                                gridviewadapter = new Job_experice_Adapter( skilllist,mContext,0);
//                                jobshow_gridview.setAdapter(gridviewadapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.job_experice);
        mContext = this;
        info = new UserInfo(mContext);
        getview();
        intent = getIntent();
        pagetype = intent.getStringExtra("pagetype");
        jobexperice();
    }

    private void getview() {
        flowlayout = (XCFlowLayout) findViewById(R.id.flowlayout);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        jobshow_gridview = (GridView) findViewById(R.id.jobshow_gridview);
//        jobshow_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                info.setCommany_industry(skilllist.get(position)+"");
//                finish();
//            }
//        });

        lp = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 30;
        lp.rightMargin = 10;
        lp.topMargin = 20;
        lp.bottomMargin = 10;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }

    private void jobexperice() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETDICTIONARYLIST("67", GETEDUCATION);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
