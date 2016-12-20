package com.example.chy.challenge.findcommany.findjob.pop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.login.register.Register_Commany_info;

import java.io.File;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

public class PopupWindow_shared implements PopupWindow.OnDismissListener,View.OnClickListener{
    private Context mContext;
    private View view;
    private RevealButton btn_cancel;
    private PopupWindow popupWindow;
    private LinearLayout btn_qq,btn_haoyou,btn_pengyouquan;

    public PopupWindow_shared(Context mactivity) {
        this.mContext = mactivity;

        view = LayoutInflater.from(mactivity).inflate(R.layout.popupwindow_shared, null);
        btn_qq = (LinearLayout) view.findViewById(R.id.btn_qq);//qq
        btn_qq.setOnClickListener(this);
        btn_haoyou = (LinearLayout) view.findViewById(R.id.btn_haoyou);//微信好友
        btn_haoyou.setOnClickListener(this);
        btn_pengyouquan = (LinearLayout) view.findViewById(R.id.btn_pengyouquan);//取消
        btn_pengyouquan.setOnClickListener(this);
        btn_cancel = (RevealButton) view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        //获取pop所在页面的布局
//            rootview = mactivity.getWindow().getDecorView();
//            roundImageView = (RoundImageView) rootview.findViewById(R.id.personal_head_avater_rundimage);

            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //设置popwindow的动画效果
            popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
        }

        @Override
        public void onDismiss() {

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_qq://qq
                    Toast.makeText(mContext,"此功能暂未开放",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_haoyou://微信好友
                    Toast.makeText(mContext,"此功能暂未开放",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_pengyouquan://朋友圈
                    Toast.makeText(mContext,"此功能暂未开放",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_cancel:
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
            WindowManager manager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            int screenHeight = display.getHeight();
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, screenHeight - location[1]);

            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.update();
        }

}
