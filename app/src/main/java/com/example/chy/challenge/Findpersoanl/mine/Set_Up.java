package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.setup.About_mine;
import com.example.chy.challenge.Findpersoanl.mine.setup.Binding_phone;
import com.example.chy.challenge.Findpersoanl.mine.setup.ChangeMineNews;
import com.example.chy.challenge.Findpersoanl.mine.setup.Message_remind;
import com.example.chy.challenge.Findpersoanl.mine.setup.Old_password;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.Login;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Set_Up extends Activity implements View.OnClickListener{
    private WaveView binding_phone,back,binding_password,getout_APP,binding_remind,binding_aboutmine,binding_change_mine;
    private Context mContext;
    private Intent intent;
    private UserInfoBean infobean;
    private String usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        intent = getIntent();
        usertype = intent.getStringExtra("usertype");
        getview();
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        binding_phone = (WaveView) findViewById(R.id.binding_phone);//手机绑定
        binding_phone.setOnClickListener(this);
        binding_password = (WaveView) findViewById(R.id.binding_password);//设置密码
        binding_password.setOnClickListener(this);
        binding_remind = (WaveView) findViewById(R.id.binding_remind);//消息提醒
        binding_remind.setOnClickListener(this);
        binding_aboutmine = (WaveView) findViewById(R.id.binding_aboutmine);//关于我们
        binding_aboutmine.setOnClickListener(this);
        binding_change_mine = (WaveView) findViewById(R.id.binding_change_mine);//切换身份
        binding_change_mine.setOnClickListener(this);
        getout_APP = (WaveView) findViewById(R.id.getout_APP);//退出当前账号
        getout_APP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.back:
               finish();
               break;
           case R.id.binding_phone://手机绑定
               intent = new Intent(mContext,Binding_phone.class);
               startActivity(intent);
               break;
           case R.id.binding_password://设置密码
               intent = new Intent(mContext,Old_password.class);
               startActivity(intent);
               break;
           case R.id.binding_remind://消息提醒
               intent = new Intent(mContext,Message_remind.class);
               startActivity(intent);
               break;
           case R.id.binding_aboutmine://关于我们
//               intent = new Intent(mContext,About_mine.class);
//               startActivity(intent);
               Toast.makeText(mContext,"暂未开放此功能",Toast.LENGTH_SHORT).show();
               break;
           case R.id.binding_change_mine://切换身份
               try {
                   intent = new Intent(mContext, ChangeMineNews.class);
                   intent.putExtra("usertype", usertype);
                   startActivity(intent);
               }catch (Exception e){
                   e.printStackTrace();
               }
               break;
           case R.id.getout_APP://退出当前帐号
               infobean.removeUserid();
               intent = new Intent(mContext, Login.class);
               startActivity(intent);
               break;
       }
    }
}
