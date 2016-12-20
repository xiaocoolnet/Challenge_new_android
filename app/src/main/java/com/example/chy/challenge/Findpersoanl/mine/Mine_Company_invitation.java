package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class Mine_Company_invitation extends Activity implements View.OnClickListener{
    private static final int GETCOMPANYLIST_NO = 1;
    private Context mContext;
    private UserInfoBean infobean;
    private UserInfo info;
    private WaveView back,save,invitation_headimage,invitation_companyname,invitation_URL,invitation_companytype,invitation_companycount,
            invitation_financing,invitation_introduce,invitation_productintroduce;
    private RoundImageView image_headimage;
    private TextView tv_companyname,tv_URL,tv_companytype,tv_companycount,tv_financing,tv_introduce,product_introduce;
    private ProgressDialog dialog;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GETCOMPANYLIST_NO:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_company_invitation);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        info = new UserInfo(mContext);
        getview();
        getCompany();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        save = (WaveView) findViewById(R.id.save);
        save.setOnClickListener(this);
        invitation_headimage = (WaveView) findViewById(R.id.invitation_headimage);//头像按钮
        invitation_headimage.setOnClickListener(this);
        image_headimage = (RoundImageView) findViewById(R.id.invitation_image_headimage);//方头像

        invitation_companyname = (WaveView) findViewById(R.id.invitation_companyname);//公司名称
        invitation_companyname.setOnClickListener(this);
        tv_companyname = (TextView) findViewById(R.id.invitation_tv_companyname);//公司名称tv

        invitation_URL = (WaveView) findViewById(R.id.invitation_URL);//公司官网
        invitation_URL.setOnClickListener(this);
        tv_URL = (TextView) findViewById(R.id.invitation_tv_URL);//公司官网tv

        invitation_companytype = (WaveView) findViewById(R.id.invitation_companytype);//所属行业
        invitation_companytype.setOnClickListener(this);
        tv_companytype = (TextView) findViewById(R.id.invitation_tv_companytype);//tv所属行业

        invitation_companycount = (WaveView) findViewById(R.id.invitation_companycount);//人员规模
        invitation_companycount.setOnClickListener(this);
        tv_companycount = (TextView) findViewById(R.id.invitation_tv_companycount);//tv人员规模

        invitation_financing = (WaveView) findViewById(R.id.invitation_financing);//融资阶段
        invitation_financing.setOnClickListener(this);
        tv_financing = (TextView) findViewById(R.id.invitation_tv_financing);//tv融资阶段

        invitation_introduce = (WaveView) findViewById(R.id.invitation_introduce);//公司介绍
        invitation_introduce.setOnClickListener(this);
        tv_introduce = (TextView) findViewById(R.id.invitation_tv_introduce);//tv公司介绍(已填写，未填写)

        invitation_productintroduce = (WaveView) findViewById(R.id.invitation_productintroduce);//产品介绍
        invitation_productintroduce.setOnClickListener(this);
        product_introduce = (TextView) findViewById(R.id.invitation_product_introduce);//tv产品介绍(绿白，灰白)

        dialog = new ProgressDialog(mContext,AlertDialog.THEME_HOLO_LIGHT);
    }
//
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.save:
                //提交修改信息
                if (NetBaseUtils.isConnnected(mContext)) {
                    dialog.setMessage("正在提交...");
                    new UserRequest(mContext, handler).COMPANY_INFO(infobean.getUserid(),"","","","","","","","", GETCOMPANYLIST_NO);
                } else {
                    Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.invitation_headimage://头像按钮

                break;
            case R.id.invitation_companyname://公司名称

                break;
            case R.id.invitation_URL://公司官网

                break;
            case R.id.invitation_companytype://所属行业

                break;
            case R.id.invitation_companycount://人员规模

                break;
            case R.id.invitation_financing://融资阶段

                break;
            case R.id.invitation_introduce://公司介绍

                break;
            case R.id.invitation_productintroduce://产品介绍

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Public_static_all.istrueA&&Public_static_all.istruea){

        }
        if (Public_static_all.istrueB&&Public_static_all.istrueb){

        }
        if (Public_static_all.istrueC&&Public_static_all.istruec){

        }
        if (Public_static_all.istrueD&&Public_static_all.istrued){

        }
        if (Public_static_all.istrueE&&Public_static_all.istruee){

        }
        if (Public_static_all.istrueF&&Public_static_all.istruef){

        }
        if (Public_static_all.istrueG&&Public_static_all.istrueg){

        }
        if (Public_static_all.istrueH&&Public_static_all.istrueh){

        }
    }
    private void getCompany() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETCOMPANYLIST_NO(infobean.getUserid(), GETCOMPANYLIST_NO);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
