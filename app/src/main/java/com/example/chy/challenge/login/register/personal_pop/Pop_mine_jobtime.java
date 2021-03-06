package com.example.chy.challenge.login.register.personal_pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.R;
import com.example.chy.challenge.WheelView.WheelView;
import com.example.chy.challenge.WheelView.adapters.ArrayWheelAdapter;
import com.example.chy.challenge.login.register.Register_Commany_info;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;
import com.example.chy.challenge.login.register.commany_info.Write_personal_jobnews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Pop_mine_jobtime implements PopupWindow.OnDismissListener, View.OnClickListener{

    private static final int UPDATEBIRTHDAY = 1;
    private Write_personal_jobnews mactivity;
    private PopupWindow popupWindow;
    private TextView mine_pop_cancle, mine_pop_sure;
    private WheelView id_leftyear, id_rightyear,id_centeryear;
    private String et_content;
    private View rootView;
    private TextView tv_work_time;
    private ArrayWheelAdapter<String> adapter_leftyear,adapter_rightyear,adapter_centeryear;
    private List<String> yearlist = new ArrayList<>();

    /**
     * 左边年,右边年
     */
    public static String[] LeftYears,RightYears,centerYears,l_all;
    String[][] l_string;

    public String mYear;
    public Pop_mine_jobtime(Write_personal_jobnews mActivity) {
        this.mactivity = mActivity;


        View view = LayoutInflater.from(mActivity).inflate(R.layout.mine_location_popupwindow, null);
        mine_pop_cancle = (TextView) view.findViewById(R.id.mine_pop_cancle);
        mine_pop_cancle.setOnClickListener(this);
        mine_pop_sure = (TextView) view.findViewById(R.id.mine_pop_sure);
        mine_pop_sure.setOnClickListener(this);
        id_leftyear = (WheelView) view.findViewById(R.id.id_leftyear);
//        id_leftyear.addChangingListener(this);
        id_rightyear = (WheelView) view.findViewById(R.id.id_rightyear);
//        id_rightyear.addChangingListener(this);
        id_centeryear = (WheelView) view.findViewById(R.id.id_centeryear);
        //获取activity根视图,rootView设为全局变量
        rootView = mactivity.getWindow().getDecorView();
        tv_work_time = (TextView)rootView.findViewById(R.id.work_tv_work_time);//任职时间段tv

        initProvinceDatas();
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听

    }
    private void initProvinceDatas() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy");
        String date = sDateFormat.format(new Date());
        //*/ 默认当前年月
        mYear = date;
        centerYears = new String[1];
        centerYears[0] = "至";
        yearlist.clear();
        for (int i = 1017; i <= Integer.valueOf(date); i++) {
            for (int j = 1; j <= 12; j++){
                yearlist.add(String.valueOf(i)+"年"+"\t"+String.valueOf(j)+"月");
            }
        }
        LeftYears = new String[yearlist.size()];
        for (int m = 0;m < yearlist.size();m++){
            LeftYears[ yearlist.size()-m-1] = yearlist.get(m);
        }

        adapter_leftyear = new ArrayWheelAdapter<String>(mactivity, LeftYears);
        adapter_rightyear = new ArrayWheelAdapter<String>(mactivity, LeftYears);
        adapter_centeryear = new ArrayWheelAdapter<String>(mactivity, centerYears);
        // 文本颜色
//        adapter_leftyear.setTextColor(mactivity.getResources().getColor(R.color.green));
//        adapter_rightyear.setTextColor(mactivity.getResources().getColor(R.color.green));
        id_leftyear.setViewAdapter(adapter_leftyear);
        id_rightyear.setViewAdapter(adapter_rightyear);
        id_centeryear.setViewAdapter(adapter_centeryear);
        // 可见数目，默认是3
        id_leftyear.setVisibleItems(3);
        id_rightyear.setVisibleItems(3);
        id_centeryear.setVisibleItems(3);
        id_centeryear.setEnabled(false);
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        backgroundAlpha(0.4f);
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

    /**
     * 消除弹窗
     */
    public void dissmiss() {

        backgroundAlpha(1f);
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_pop_cancle:
                dissmiss();
                break;
            case R.id.mine_pop_sure:
                int leftyear = id_leftyear.getCurrentItem();
                int rightyear = id_rightyear.getCurrentItem();
                if (leftyear > rightyear) {
                    Log.i("et_content", "--------->" + et_content);
                    tv_work_time.setText(LeftYears[leftyear] + "\t至\t" + LeftYears[rightyear]);
                    dissmiss();
                }else{
                    Toast.makeText(mactivity,"请选择正确的时间",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public String getString() {
        return et_content;
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = mactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mactivity.getWindow().setAttributes(lp);
    }
}
