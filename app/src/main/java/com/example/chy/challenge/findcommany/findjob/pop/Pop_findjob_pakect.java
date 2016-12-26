package com.example.chy.challenge.findcommany.findjob.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.Utils.NoScrollListView;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.findcommany.chance.Find_Job;
import com.example.chy.challenge.findcommany.chance.Find_work;
import com.example.chy.challenge.login.register.adapter.List_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class Pop_findjob_pakect implements PopupWindow.OnDismissListener{
    private static final int GETDICTIONARYLIST = 1;
    private Find_Job mactivity;
    private View view,rootview;
    private RevealButton pop_all_redenvelope,pop_post_redpacket,popstate_Interview_redenvelope,popstate_inauguration_redenvelope;
    private PopupWindow popupWindow;
    private TextView tv_job_state,pop_title;
    private NoScrollListView salary_listview;
    private String pagetype;
    private LinearLayout linear_pop;
    private List<String> namelist  = new ArrayList<>();
    private List<String> idlist  = new ArrayList<>();
    private List_Adapter listadapter;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETDICTIONARYLIST:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        namelist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length();i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        namelist.add(jsonobj.getString("name"));
                                        idlist.add(jsonobj.getString("term_id"));
                                    }
                                }
                                listadapter = new List_Adapter(namelist,mactivity.getActivity(),0);
                                salary_listview.setAdapter(listadapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity.getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };



    public Pop_findjob_pakect(Find_Job mactivity) {
        this.mactivity = mactivity;
        view = LayoutInflater.from(mactivity.getActivity()).inflate(R.layout.pop_pack, null);
        pop_title = (TextView) view.findViewById(R.id.pop_title);
        pop_all_redenvelope = (RevealButton) view.findViewById(R.id.pop_all_redenvelope);//全部
        pop_post_redpacket = (RevealButton) view.findViewById(R.id.pop_post_redpacket);//职位红包
        popstate_Interview_redenvelope = (RevealButton) view.findViewById(R.id.popstate_Interview_redenvelope);//面试红包
        popstate_inauguration_redenvelope = (RevealButton) view.findViewById(R.id.popstate_inauguration_redenvelope);//就职红包
        salary_listview = (NoScrollListView) view.findViewById(R.id.salary_listview);
        linear_pop = (LinearLayout) view.findViewById(R.id.linear_pop);
        //获取pop所在页面的布局
//        rootview = mactivity.getWindow().getDecorView();
//        tv_job_state = (TextView) rootview.findViewById(R.id.intention_tv_job_state);//tv工作状态
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
    }

    @Override
    public void onDismiss() {

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.pop_all_redenvelope://全部
//                mactivity.find_workfragment();
//                dissmiss();
//                break;
//            case R.id.pop_post_redpacket://职位红包
//                mactivity.find_workfragment2();
//                dissmiss();
//                break;
//            case R.id.popstate_Interview_redenvelope://面试红包
//                mactivity.find_workfragment3();
//                dissmiss();
//                break;
//            case R.id.popstate_inauguration_redenvelope://就职红包
//                mactivity.find_workfragment4();
//                dissmiss();
//                break;
//        }
//    }
    /**
     * 消除弹窗
     */
    public void dissmiss() {
        popupWindow.dismiss();
    }
    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent,String pagetype) {
        this.pagetype = pagetype;
        if ("scale".equals(pagetype)){
            salary_listview.setVisibility(View.VISIBLE);
            linear_pop.setVisibility(View.GONE);
            salary_listview.setDivider(null);
            pop_title.setText("规模");
            if (NetBaseUtils.isConnnected(mactivity.getActivity())) {
                new UserRequest(mactivity.getActivity(), handler).GETDICTIONARYLIST("18",GETDICTIONARYLIST);
            } else {
                Toast.makeText(mactivity.getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
            }
            salary_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mactivity.find_jobfragment(idlist.get(position));
                    dissmiss();
                }
            });
        }
//        else if ("packet".equals(pagetype)){
//            salary_listview.setVisibility(View.GONE);
//            linear_pop.setVisibility(View.VISIBLE);
//            pop_all_redenvelope.setOnClickListener(this);
//            pop_post_redpacket.setOnClickListener(this);
//            popstate_Interview_redenvelope.setOnClickListener(this);
//            popstate_inauguration_redenvelope.setOnClickListener(this);
//            pop_title.setText("红包");
//        }
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        WindowManager manager = (WindowManager) mactivity.getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenHeight = display.getHeight();
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }
}
