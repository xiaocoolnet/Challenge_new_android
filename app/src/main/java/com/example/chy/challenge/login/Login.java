package com.example.chy.challenge.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chy.challenge.button.Constants;
import com.example.chy.challenge.choose_personal_type.Identity;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.example.chy.challenge.login.weibo.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class Login extends Activity implements View.OnClickListener{
    private EditText phone,passWord;
    private RevealButton login;
    private WaveView forgetPwd,regist;
    private WaveView QQLogin,WechatLogin,WeiboLogin;
    private Context mContext;
    private ProgressDialog dialog;
    private UserInfoBean infobean;
    //微博登录
    private AuthInfo mAuthInfo;
    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;

    private String telPhone,pass;
    private static final int KEY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        if(infobean.isLogined()){
            Intent intent = new Intent(mContext,Identity.class);
            intent.putExtra("pagetype","login");
            startActivity(intent);
        }
        // 创建微博实例
        //mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        // 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
        mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(Login.this, mAuthInfo);
        initview();

    }

    private void initview() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        phone = (EditText) findViewById(R.id.login_phone);//用户名输入框
        passWord = (EditText) findViewById(R.id.login_passWord);//密码输入框
        login = (RevealButton) findViewById(R.id.login);//登录
        login.setOnClickListener(this);
        forgetPwd = (WaveView) findViewById(R.id.login_forgetPwd);//忘记密码按钮
        forgetPwd.setOnClickListener(this);
        regist = (WaveView) findViewById(R.id.login_regist);//立即注册按钮
        regist.setOnClickListener(this);
        QQLogin = (WaveView) findViewById(R.id.QQLogin);//QQ
        QQLogin.setOnClickListener(this);
        WechatLogin = (WaveView) findViewById(R.id.WechatLogin);
        WechatLogin.setOnClickListener(this);
        WeiboLogin = (WaveView) findViewById(R.id.WeboLogin);
        WeiboLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                loginooo();
//                startActivity(new Intent(mContext,Identity.class));
                break;
            case R.id.login_forgetPwd:
                startActivity(new Intent(this,ForgetPassWord.class));
                break;
            case R.id.login_regist:
                startActivity(new Intent(this,Regist.class));
                break;
            case R.id.QQLogin:
                break;
            case R.id.WechatLogin:
                break;
            case R.id.WeboLogin:
                mSsoHandler.authorize(new AuthListener());
                break;
            default:
                break;
        }

    }

    private void loginooo() {
        telPhone = phone.getText().toString().trim();
        pass = passWord.getText().toString().trim();
        if (telPhone == null||"".equals(telPhone)){
            Toast.makeText(mContext,"用户名不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass == null||"".equals(pass)){
            Toast.makeText(mContext,"密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在登录...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).Login(telPhone, pass, KEY);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }

    }



    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEY:
                    if (msg.obj!=null){
                        String resault = (String) msg.obj;
                          try {
                              JSONObject jsonObject = new JSONObject(resault);
                              if ("success".equals(jsonObject.optString("status"))){
                                  JSONObject json = new JSONObject(jsonObject.getString("data"));
                                  infobean.setUserid(json.getString("userid"));
                                  infobean.setRealname(json.getString("realname"));
                                  infobean.setUsertype(json.getString("usertype"));
                                  infobean.setPhone(json.getString("phone"));
                                  infobean.setPassword(json.getString("password"));
                                  infobean.setSex(json.getString("sex"));
                                  infobean.setEmail(json.getString("email"));
                                  infobean.setQq(json.getString("qq"));
                                  infobean.setWeixin(json.getString("weixin"));
                                  infobean.setPhoto(json.getString("photo"));
                                  infobean.setDecicestate(json.getString("devicestate"));
                                  infobean.setCity(json.getString("city"));
                                  infobean.setWeibo(json.getString("weibo"));
                                  infobean.setWork_life(json.getString("work_life"));
                                  infobean.setCompany(json.getString("company"));
                                  infobean.setMyjob(json.getString("myjob"));
                                  Intent intent = new Intent(mContext,Identity.class);
                                  intent.putExtra("pagetype","login");
                                  startActivity(intent);
                                  finish();
                              }else {
                                  Toast.makeText(mContext, R.string.login_error,Toast.LENGTH_SHORT).show();
                                  new Thread(){
                                      @Override
                                      public void run() {
                                          try {
                                              Thread.sleep(2000);
                                          } catch (InterruptedException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  }.start();
                              }
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                        dialog.dismiss();
                      }else {
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        dialog.dismiss();
                    }
                    break;
            }
        };
    };

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息
            String  phoneNum =  mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);

                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(Login.this, mAccessToken);
                Toast.makeText(Login.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(Login.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(Login.this, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 显示当前 Token 信息。
     *
     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
     */
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
//        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
//        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

//        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
//            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
//        mTokenText.setText(message);
    }
}
