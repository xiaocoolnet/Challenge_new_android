package com.example.chy.challenge.login.register.personal_pop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.chy.challenge.login.register.Register_personal_info;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_intention;

import java.io.File;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class Pop_mine_jobnature implements PopupWindow.OnDismissListener,View.OnClickListener{
    private Register_Mine_intention mactivity;
    private View view,rootview;
    private RevealButton popcamera_photograph,popcamera_photo_album;
    private PopupWindow popupWindow;
    private TextView tv_job_nature;

    public Pop_mine_jobnature(Register_Mine_intention mactivity) {
        this.mactivity = mactivity;
        view = LayoutInflater.from(mactivity).inflate(R.layout.pop_nature, null);
        popcamera_photograph = (RevealButton) view.findViewById(R.id.popcamera_photograph);//全职
        popcamera_photograph.setOnClickListener(this);
        popcamera_photo_album = (RevealButton) view.findViewById(R.id.popcamera_photo_album);//兼职
        popcamera_photo_album.setOnClickListener(this);
        //获取pop所在页面的布局
        rootview = mactivity.getWindow().getDecorView();
        tv_job_nature = (TextView) rootview.findViewById(R.id.intention_tv_job_nature);//tv工作性质
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
            case R.id.popcamera_photograph:
                tv_job_nature.setText(popcamera_photograph.getText().toString()+"");
                dissmiss();
                break;
            case R.id.popcamera_photo_album:
                tv_job_nature.setText(popcamera_photo_album.getText().toString()+"");
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
        WindowManager manager = (WindowManager) mactivity
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int screenHeight = display.getHeight();
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }
}
