package com.example.chy.challenge.Findpersoanl.mine.setup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.Talent;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.SalaryMain;
import com.example.chy.challenge.login.register.Register_Commany_info;
import com.example.chy.challenge.login.register.Register_personal_info;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class ChangeMineNews extends Activity implements View.OnClickListener {
    private WaveView back, lookpersonal, lookcompany;
    private Context mContext;
    private UserInfoBean infobean;
    private Intent intent;
    private String usertype;
    private TextView tvfora_personal,tvfora_commany;
    private ImageView imagefora_personal,imagefora_commany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_mine_news);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        intent = getIntent();
        usertype = intent.getStringExtra("usertype");
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        lookpersonal = (WaveView) findViewById(R.id.look_fora_personal);
        lookpersonal.setOnClickListener(this);
        lookcompany = (WaveView) findViewById(R.id.look_fora_commany);
        lookcompany.setOnClickListener(this);
        tvfora_personal = (TextView) findViewById(R.id.look_tvfora_personal);
        tvfora_commany = (TextView) findViewById(R.id.look_tvfora_commany);
        imagefora_personal = (ImageView) findViewById(R.id.look_imagefora_personal);//图片
        imagefora_commany = (ImageView) findViewById(R.id.look_imagefora_commany);
        if ("company".equals(usertype)){
            lookpersonal.setBackgroundResource(R.drawable.border_tv_00green);
            lookcompany.setBackgroundResource(R.drawable.border_white_green_25);
            tvfora_personal.setTextColor(getResources().getColor(R.color.white));
            tvfora_commany.setTextColor(getResources().getColor(R.color.green));
        }else if ("personal".equals(usertype)){
            lookpersonal.setBackgroundResource(R.drawable.border_white_green_25);
            lookcompany.setBackgroundResource(R.drawable.border_tv_00green);
            tvfora_personal.setTextColor(getResources().getColor(R.color.green));
            tvfora_commany.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.look_fora_personal://企业帐号
                //1是个人2是企业3是都是
                if ("personal".equals(usertype)) {
                    if ("3".equals(infobean.getUsertype())) {
                        intent = new Intent(mContext, Talent.class);
                        startActivity(intent);
                        finish();
                    } else {
                        intent = new Intent(mContext, Register_personal_info.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    new AlertDialog.Builder(mContext).setTitle("系统提示").setMessage("您已经是企业用户").
                            setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
                break;
            case R.id.look_fora_commany://个人帐号
                if ("company".equals(usertype)) {
                    if ("3".equals(infobean.getUsertype())) {
                        intent = new Intent(mContext, SalaryMain.class);
                        startActivity(intent);
                        finish();
                    } else {
                        intent = new Intent(mContext, Register_Commany_info.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    new AlertDialog.Builder(mContext).setTitle("系统提示").setMessage("您已经是个人用户").
                            setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
                break;
        }
    }
}
