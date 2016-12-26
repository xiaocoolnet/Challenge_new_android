package com.example.chy.challenge.login.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.TalentMain;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.Login;
import com.example.chy.challenge.login.register.personal_pop.Personal_camera;
import com.example.chy.challenge.login.register.register_bean.UserInfo;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


/**
 * Created by Administrator on 2016/10/13 0013.
 * 个人信息编辑界面
 */

public class Register_personal_info extends Activity implements View.OnClickListener {
    private static final int KEY = 4;
    private static final int SAVEKEY = 5;
    private static final int GETMYINFO = 6;
    private WaveView back, personal_relevance_submit,personal_head_avater, personal_real_name, persoanl_mine_position, personal_mine_email, personal_current_company, personal_relevance_QQ, personal_relevance_weixin, personal_relevance_weibo;
    private Personal_camera Pcamear;
    private RoundImageView roundimage;
    private Activity mactivity;
    private String path, pagetype, filepath = "/sdcard/myheader", picname = "newpic", head,email="" ;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private Intent intent = new Intent(), intentpage;
    private UserInfo info;
    private UserInfoBean infobean;
    private TextView tv_realname, tv_position, tv_email, tv_company, tv_qq, tv_weixin, tv_weibo;
    private static final int PHOTO_REQUEST_CUT = 3;// 相册
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_ALBUM = 2;// 剪裁
    private ProgressDialog dialog;


    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case KEY:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))) {
                                path = json.optString("data");
                                if (path != null && path.length() > 0) {
                                    imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + path, roundimage, options);
                                    Public_static_all.iscompanya = true;
                                    info.setPhoto(path);
                                }
                            } else {
                                Toast.makeText(mactivity, "上传失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case SAVEKEY:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))) {
                                infobean.setPhoto(path);
                                infobean.setRealname(info.getRealname());
                                infobean.setMyjob(info.getMyjob());
                                infobean.setEmail(info.getEmail());
                                infobean.setQq(info.getQq());
                                infobean.setWeixin(info.getWeixin());
                                infobean.setWeibo(info.getWeibo());
                                infobean.setCompany(info.getCompany());
                                dialog.dismiss();
                                Intent intent = new Intent(mactivity, TalentMain.class);
                                startActivity(intent);
                                getiscompany(false);
                                finish();
                            } else {
                                dialog.setMessage("提交失败..");
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                dialog.dismiss();
                                Toast.makeText(mactivity, "请重新提交", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        dialog.setMessage("网络错误..");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        dialog.dismiss();
                        Toast.makeText(mactivity, "3", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case GETMYINFO:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONObject jsonobj = new JSONObject(json.getString("data"));
                                infobean.setUserid(jsonobj.getString("userid"));
                                infobean.setRealname(jsonobj.getString("realname"));
                                infobean.setUsertype(jsonobj.getString("usertype"));
                                infobean.setPhone(jsonobj.getString("phone"));
                                infobean.setPassword(jsonobj.getString("password"));
                                infobean.setSex(jsonobj.getString("sex"));
                                infobean.setEmail(jsonobj.getString("email"));
                                infobean.setQq(jsonobj.getString("qq"));
                                infobean.setWeixin(jsonobj.getString("weixin"));
                                infobean.setPhoto(jsonobj.getString("photo"));
                                infobean.setDecicestate(jsonobj.getString("devicestate"));
                                infobean.setCity(jsonobj.getString("city"));
                                infobean.setWeibo(jsonobj.getString("weibo"));
                                infobean.setWork_life(jsonobj.getString("work_life"));
                                infobean.setCompany(jsonobj.getString("company"));
                                infobean.setMyjob(jsonobj.getString("myjob"));
                                info.setRealname(jsonobj.getString("realname"));
                                info.setUsertype(jsonobj.getString("usertype"));
                                info.setPhone(jsonobj.getString("phone"));
                                info.setPassword(jsonobj.getString("password"));
                                info.setSex(jsonobj.getString("sex"));
                                info.setEmail(jsonobj.getString("email"));
                                info.setQq(jsonobj.getString("qq"));
                                info.setWeixin(jsonobj.getString("weixin"));
                                info.setPhoto(jsonobj.getString("photo"));
                                info.setDecicestate(jsonobj.getString("devicestate"));
                                info.setCity(jsonobj.getString("city"));
                                info.setWeibo(jsonobj.getString("weibo"));
                                info.setWork_life(jsonobj.getString("work_life"));
                                info.setCompany(jsonobj.getString("company"));
                                info.setMyjob(jsonobj.getString("myjob"));
                                getmineninfomation();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("网络错误..");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        dialog.dismiss();
                        Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void getmineninfomation() {
        if (infobean.getRealname() != null && infobean.getRealname().length() > 0) {
            tv_realname.setText(infobean.getRealname() + "");
        } else if (info.getMyjob() != null && info.getMyjob().length() > 0) {
            tv_position.setText(infobean.getMyjob() + "");
        } else if (infobean.getEmail() != null && infobean.getEmail().length() > 0) {
            tv_email.setText(infobean.getEmail() + "");
        } else if (infobean.getCompany() != null && infobean.getCompany().length() > 0) {
            tv_company.setText(infobean.getCompany() + "");
        } else if (infobean.getQq() != null && infobean.getQq().length() > 0) {
            tv_qq.setText(infobean.getQq() + "");
        } else if (infobean.getWeixin() != null && infobean.getWeixin().length() > 0) {
            tv_weixin.setText(infobean.getWeixin() + "");
        } else if (infobean.getWeibo() != null && infobean.getWeibo().length() > 0) {
            tv_weibo.setText(infobean.getWeibo() + "");
        }else if (infobean.getPhoto() != null && infobean.getPhoto().length() > 0) {
            imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + infobean.getPhoto(), roundimage, options);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_personal_info);
        mactivity = this;
        info = new UserInfo(mactivity);
        infobean = new UserInfoBean(mactivity);
        Pcamear = new Personal_camera(Register_personal_info.this);
        intentpage = getIntent();
        pagetype = intentpage.getStringExtra("pagetype");
        getinfo();
        if ("login0".equals(pagetype)||"register".equals(pagetype)) {
            if (Public_static_all.iscompanyA&& Public_static_all.iscompanya){
                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + info.getPhoto(), roundimage, options);
            }
            if (Public_static_all.iscompanyB&& Public_static_all.iscompanyb){
                tv_realname.setText(info.getRealname());
            }
            if (Public_static_all.iscompanyC&& Public_static_all.iscompanyc){
                tv_position.setText(info.getMyjob());
            }
            if (Public_static_all.iscompanyD&& Public_static_all.iscompanyd){
                tv_email.setText(info.getEmail());
            }
            if (Public_static_all.iscompanyE&& Public_static_all.iscompanye){
                tv_company.setText(info.getCompany());
            }
            if (Public_static_all.iscompanyF&& Public_static_all.iscompanyf){
                tv_qq.setText(info.getQq());
            }
            if (Public_static_all.iscompanyG&& Public_static_all.iscompanyg){
                tv_weixin.setText(info.getWeixin());
            }
            if (Public_static_all.iscompanyH&& Public_static_all.iscompanyh){
                tv_weibo.setText(info.getWeibo());
            }
        }else if ("company".equals(pagetype)){
            getiscompany(false);
            getpersonanews();
        }else if ("login1".equals(pagetype)){
            getiscompany(false);
            if (infobean.getPhoto() != null&&infobean.getPhoto().length() > 0){
                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + infobean.getPhoto(), roundimage, options);
            }else if (infobean.getRealname() != null&&infobean.getRealname().length() > 0){
                tv_realname.setText(infobean.getRealname());
            }else if (infobean.getQq() != null&&infobean.getQq().length() > 0){
                tv_qq.setText(infobean.getQq());
            }else if (infobean.getWeixin() != null&&infobean.getWeixin().length() > 0){
                tv_weixin.setText(infobean.getWeixin());
            }else if (infobean.getWeibo() != null&&infobean.getWeibo().length() > 0){
                tv_weibo.setText(infobean.getWeibo());
            }
        }
    }

    private void getinfo() {
        dialog = new ProgressDialog(mactivity, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        personal_head_avater = (WaveView) findViewById(R.id.personal_head_avater);//头像
        personal_head_avater.setOnClickListener(this);
        personal_real_name = (WaveView) findViewById(R.id.personal_real_name);//真实姓名
        tv_realname = (TextView) findViewById(R.id.personal_tv_realname);
        personal_real_name.setOnClickListener(this);
        persoanl_mine_position = (WaveView) findViewById(R.id.persoanl_mine_position);//我的职位
        tv_position = (TextView) findViewById(R.id.personal_tv_position);
        persoanl_mine_position.setOnClickListener(this);
        personal_mine_email = (WaveView) findViewById(R.id.personal_mine_email);//接受简历的邮箱
        tv_email = (TextView) findViewById(R.id.personal_tv_email);
        personal_mine_email.setOnClickListener(this);
        personal_current_company = (WaveView) findViewById(R.id.personal_current_company);//当前公司
        tv_company = (TextView) findViewById(R.id.personal_tv_company);
        personal_current_company.setOnClickListener(this);
        personal_relevance_QQ = (WaveView) findViewById(R.id.personal_relevance_QQ);//QQ关联
        tv_qq = (TextView) findViewById(R.id.personal_tv_qq);
        personal_relevance_QQ.setOnClickListener(this);
        personal_relevance_weixin = (WaveView) findViewById(R.id.personal_relevance_weixin);//微信关联
        tv_weixin = (TextView) findViewById(R.id.personal_tv_weixin);
        personal_relevance_weixin.setOnClickListener(this);
        personal_relevance_weibo = (WaveView) findViewById(R.id.personal_relevance_weibo);//微博关联
        tv_weibo = (TextView) findViewById(R.id.personal_tv_weibo);
        personal_relevance_weibo.setOnClickListener(this);
        personal_relevance_submit = (WaveView) findViewById(R.id.personal_relevance_submit);//保存
        personal_relevance_submit.setOnClickListener(this);
        roundimage = (RoundImageView) findViewById(R.id.personal_head_avater_rundimage);
        // 显示图片的配置
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.color.gray).showImageOnFail(R.color.gray).cacheInMemory(true).cacheOnDisc(true).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.personal_head_avater:
                Pcamear.showAsDropDown(personal_relevance_submit);
                Public_static_all.iscompanyA = true;
                break;
            case R.id.personal_real_name:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "realname");
                Public_static_all.iscompanyB = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.persoanl_mine_position:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "myposition");
                Public_static_all.iscompanyC = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_mine_email:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "myemail");
                Public_static_all.iscompanyD = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_current_company:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "currentcommany");
                Public_static_all.iscompanyE = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_QQ:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "QQ");
                Public_static_all.iscompanyF = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_weixin:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "weixin");
                Public_static_all.iscompanyG = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_weibo:
                intent.setClass(mactivity, Write_personal_info.class);
                intent.putExtra("type", "weibo");
                Public_static_all.iscompanyH = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.personal_relevance_submit://提交
//                Intent intent = new Intent(mactivity, TalentMain.class);
//                intent.putExtra("pagetype", pagetype);
//                startActivity(intent);
//                finish();
                getsubmit();
                break;
        }
    }

    private void getsubmit() {
        if ("选填".equals(tv_email.getText().toString())||tv_email.getText().toString() == null || tv_email.getText().toString().length() <= 0){
            email = "";
        }else{
            email = tv_email.getText().toString();
        }
       if ("请输入真实姓名".equals(tv_realname.getText().toString())||tv_realname.getText().toString()==null||tv_realname.getText().toString().length() <= 0) {
            Toast.makeText(mactivity, "请填写真实姓名", Toast.LENGTH_SHORT).show();
        } else if (tv_position.getText().toString() == null ||tv_position.getText().toString().length() <= 0) {
            Toast.makeText(mactivity, "请填写您的职位", Toast.LENGTH_SHORT).show();
        } else if ("请输入公司名称".equals(tv_company.getText().toString())||tv_company.getText().toString()==null||tv_company.getText().toString().length() <= 0) {
            Toast.makeText(mactivity, "请填写您的当前公司", Toast.LENGTH_SHORT).show();
        }else if (info.getPhoto() == null || info.getPhoto().length() <= 0) {
            Toast.makeText(mactivity, "请添加头像", Toast.LENGTH_SHORT).show();
        }else  if (infobean.getUserid() == null || infobean.getUserid().length() <= 0) {
           Intent intent = new Intent(mactivity, Login.class);
           startActivity(intent);
           finish();
       }else {
            if (NetBaseUtils.isConnnected(mactivity)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                new UserRequest(mactivity, handler).UPDATECOMMANY(infobean.getUserid(), info.getPhoto(), tv_realname.getText().toString(), tv_position.getText().toString(), email, tv_company.getText().toString(), info.getQq(), info.getWeixin(), info.getWeibo(), SAVEKEY);
            } else {
                Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case PHOTO_REQUEST_CAMERA:// 相册
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        File tempFile = new File(path, "newpic.jpg");
                        startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(getApplicationContext(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PHOTO_REQUEST_ALBUM:// 图库
                    startPhotoZoom(data.getData());
                    break;
                case PHOTO_REQUEST_CUT: // 图片缩放完成后
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 340);
        intent.putExtra("outputY", 340);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param data
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(this.getResources(), photo);
            roundimage.setImageDrawable(drawable);
            picname = "avatar" + infobean.getUserid() + String.valueOf(new Date().getTime());
            storeImageToSDCARD(photo, picname, filepath);
            if (head !=null && head.length() > 0) {
                if (NetBaseUtils.isConnnected(mactivity)) {
                    new UserRequest(mactivity, handler).uoloadavator(head, KEY);
                } else {
                    Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    /**
     * storeImageToSDCARD 将bitmap存放到sdcard中
     */
    public void storeImageToSDCARD(Bitmap colorImage, String ImageName, String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, ImageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            colorImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            head = imagefile.getPath();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Public_static_all.iscompanyA&& Public_static_all.iscompanya){
            imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + info.getPhoto(), roundimage, options);
        }
        if (Public_static_all.iscompanyB&& Public_static_all.iscompanyb){
            tv_realname.setText(info.getRealname());
        }
        if (Public_static_all.iscompanyC&& Public_static_all.iscompanyc){
            tv_position.setText(info.getMyjob());
        }
        if (Public_static_all.iscompanyD&& Public_static_all.iscompanyd){
            tv_email.setText(info.getEmail());
        }
        if (Public_static_all.iscompanyE&& Public_static_all.iscompanye){
            tv_company.setText(info.getCompany());
        }
        if (Public_static_all.iscompanyF&& Public_static_all.iscompanyf){
            tv_qq.setText(info.getQq());
        }
        if (Public_static_all.iscompanyG&& Public_static_all.iscompanyg){
            tv_weixin.setText(info.getWeixin());
        }
        if (Public_static_all.iscompanyH&& Public_static_all.iscompanyh){
            tv_weibo.setText(info.getWeibo());
        }
    }

    private void getpersonanews() {
        if (NetBaseUtils.isConnnected(mactivity)) {
            dialog.setMessage("正在刷新..");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mactivity, handler).GETMYINFO(infobean.getUserid(), GETMYINFO);
        } else {
            Toast.makeText(mactivity, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
    private void getiscompany(boolean iscompany){
        Public_static_all.iscompanyA = iscompany;
        Public_static_all.iscompanya = iscompany;
        Public_static_all.iscompanyB = iscompany;
        Public_static_all.iscompanyb = iscompany;
        Public_static_all.iscompanyC = iscompany;
        Public_static_all.iscompanyc = iscompany;
        Public_static_all.iscompanyD = iscompany;
        Public_static_all.iscompanyd = iscompany;
        Public_static_all.iscompanyE = iscompany;
        Public_static_all.iscompanye = iscompany;
        Public_static_all.iscompanyF = iscompany;
        Public_static_all.iscompanyf = iscompany;
        Public_static_all.iscompanyG = iscompany;
        Public_static_all.iscompanyg = iscompany;
        Public_static_all.iscompanyH = iscompany;
        Public_static_all.iscompanyh = iscompany;
    }
}
