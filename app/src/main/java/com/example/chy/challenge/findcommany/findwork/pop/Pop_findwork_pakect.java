package com.example.chy.challenge.findcommany.findwork.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.findcommany.chance.Find_work;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_intention;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class Pop_findwork_pakect implements PopupWindow.OnDismissListener,View.OnClickListener{
    private Find_work mactivity;
    private View view,rootview;
    private RevealButton pop_all_redenvelope,pop_post_redpacket,popstate_Interview_redenvelope,popstate_inauguration_redenvelope;
    private PopupWindow popupWindow;
    private TextView tv_job_state;

    public Pop_findwork_pakect(Find_work mactivity) {
        this.mactivity = mactivity;
        view = LayoutInflater.from(mactivity.getActivity()).inflate(R.layout.pop_pack, null);
        pop_all_redenvelope = (RevealButton) view.findViewById(R.id.pop_all_redenvelope);//全部
        pop_all_redenvelope.setOnClickListener(this);
        pop_post_redpacket = (RevealButton) view.findViewById(R.id.pop_post_redpacket);//职位红包
        pop_post_redpacket.setOnClickListener(this);
        popstate_Interview_redenvelope = (RevealButton) view.findViewById(R.id.popstate_Interview_redenvelope);//面试红包
        popstate_Interview_redenvelope.setOnClickListener(this);
        popstate_inauguration_redenvelope = (RevealButton) view.findViewById(R.id.popstate_inauguration_redenvelope);//就职红包
        popstate_inauguration_redenvelope.setOnClickListener(this);
        //获取pop所在页面的布局
//        rootview = mactivity.getWindow().getDecorView();
//        tv_job_state = (TextView) rootview.findViewById(R.id.intention_tv_job_state);//tv工作状态
//        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pop_all_redenvelope://全部
                dissmiss();
                break;
            case R.id.pop_post_redpacket://职位红包
                dissmiss();
                break;
            case R.id.popstate_Interview_redenvelope://面试红包
                dissmiss();
                break;
            case R.id.popstate_inauguration_redenvelope://就职红包
                dissmiss();
                break;
        }
    }
    /**
     * 消除弹窗
     */
    public void dissmiss() {
        popupWindow.dismiss();
    }
    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
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
