package com.example.chy.challenge.login.register.personal_pop;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.WheelView.WheelView;
import com.example.chy.challenge.WheelView.adapters.ArrayWheelAdapter;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Commany_personal_education implements PopupWindow.OnDismissListener, View.OnClickListener{

    private static final int GETEDUCATION = 1;
    private Register_next_education mactivity;
    private PopupWindow popupWindow;
    private TextView mine_pop_cancle, mine_pop_sure;
    private WheelView id_education, id_rightyear,id_centeryear;
    private String et_content;
    private View rootView;
    private TextView resume_tv_education;
    private ArrayWheelAdapter<String> adapter_leftyear,adapter_rightyear,adapter_centeryear;

    /**
     * 左边年,右边年
     */
    public static String[] education,RightYears,centerYears;

    public String mYear;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETEDUCATION:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null&&jsonarray.length() > 0){
                                    education = new String[jsonarray.length()];
                                    for (int i = 0;i < jsonarray.length();i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
                                        education[i] = jsonobj.getString("name");
                                    }
                                }
                                adapter_leftyear = new ArrayWheelAdapter<String>(mactivity, education);
                                id_education.setViewAdapter(adapter_leftyear);
                                id_education.setVisibleItems(3);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };




    public Commany_personal_education(Register_next_education mActivity) {
        this.mactivity = mActivity;
        View view = LayoutInflater.from(mActivity).inflate(R.layout.mine_location_popupwindow, null);
        mine_pop_cancle = (TextView) view.findViewById(R.id.mine_pop_cancle);
        mine_pop_cancle.setOnClickListener(this);
        mine_pop_sure = (TextView) view.findViewById(R.id.mine_pop_sure);
        mine_pop_sure.setOnClickListener(this);
        id_education = (WheelView) view.findViewById(R.id.id_leftyear);
//        id_leftyear.addChangingListener(this);
        id_rightyear = (WheelView) view.findViewById(R.id.id_rightyear);
        id_rightyear.setVisibility(View.GONE);
//        id_rightyear.addChangingListener(this);
        id_centeryear = (WheelView) view.findViewById(R.id.id_centeryear);
        id_centeryear.setVisibility(View.GONE);
        //获取activity根视图,rootView设为全局变量
        rootView = mactivity.getWindow().getDecorView();
        resume_tv_education = (TextView) rootView.findViewById(R.id.resume_tv_education);

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
        if (NetBaseUtils.isConnnected(mactivity)) {
            new UserRequest(mactivity, handler).GETDICTIONARYLIST("60", GETEDUCATION);
        } else {
            Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
//        adapter_rightyear = new ArrayWheelAdapter<String>(mactivity, RightYears);
//        adapter_centeryear = new ArrayWheelAdapter<String>(mactivity, centerYears);
        // 文本颜色
//        adapter_leftyear.setTextColor(mactivity.getResources().getColor(R.color.green));
//        adapter_rightyear.setTextColor(mactivity.getResources().getColor(R.color.green));

//        id_rightyear.setViewAdapter(adapter_rightyear);
//        id_centeryear.setViewAdapter(adapter_centeryear);

        // 可见数目，默认是3

//        id_rightyear.setVisibleItems(3);
//        id_centeryear.setVisibleItems(3);
//        id_centeryear.setEnabled(false);
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
                    int leftyear = id_education.getCurrentItem();
                resume_tv_education.setText(education[leftyear]);
                    dissmiss();
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
