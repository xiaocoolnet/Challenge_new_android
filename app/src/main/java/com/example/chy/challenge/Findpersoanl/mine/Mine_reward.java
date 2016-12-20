package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.example.chy.challenge.pnlllist.PullToRefreshBase;
import com.example.chy.challenge.pnlllist.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Mine_reward extends Activity implements View.OnClickListener{
    private RelativeLayout ril_back;
    private WaveView back;
    private TextView title_top;
    private Context mContext;
    private ListView lv_view;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private UserInfoBean infobean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpersonalfragment);
        getview();
    }

    private void getview() {
        ril_back = (RelativeLayout) findViewById(R.id.ril_back);
        ril_back.setVisibility(View.VISIBLE);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("我的悬赏");
        //弹出刷新提示框
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
//        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
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
        gethongbao();
    }

    private void gethongbao() {

    }
}
