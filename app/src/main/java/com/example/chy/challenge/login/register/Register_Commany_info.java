package com.example.chy.challenge.login.register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.Login;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;
import com.example.chy.challenge.login.register.commany_info.location.ActivityAddlocation;
import com.example.chy.challenge.login.register.personal_pop.Commany_camera;
import com.example.chy.challenge.login.register.personal_pop.Commany_personal_jobtime;
import com.example.chy.challenge.login.register.personal_pop.Pop_mine_jobtime;
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
 * Created by Administrator on 2016/11/11 0011.
 */

public class Register_Commany_info extends Activity implements View.OnClickListener{
    private static final int KEY = 1;
    private static final int UPDATAPERSONAL = 2;
    private static final int GETMYINFO = 3;
    private WaveView head_avater,real_name,current_local,job_time,commany_qq,commany_weixin,commany_weibo,back,relevance_submit;
    private RoundImageView heafimage;
    private TextView tv_realname,tv_local,tv_jobtime,tv_qq,tv_weixin,tv_weibo,textview;
    private RadioGroup rg_sex;
    private RadioButton rbtn_woman,rbtn_man;
    private Context mContext;
    private Intent intent = new Intent(),intentpage;
    private Commany_camera commcamera;
    private static final int PHOTO_REQUEST_CUT = 3;// 相册
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_ALBUM = 2;// 剪裁
    private String path = "",pagetype,filepath = "/sdcard/myheader",picname = "newpic",head,sex = "";
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private UserInfo info;
    private UserInfoBean infobean;
//    private Commany_personal_news commanynews;
    private ProgressDialog dialog;
    private Commany_personal_jobtime personaljobtime;


    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KEY:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                path = json.optString("data");
                                if (path != null &&path.length() > 0) {
                                    info.setPhoto(path);
                                    Public_static_all.iscompanyi = true;
                                    imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + path, heafimage, options);
                                }
                            }else{
                                Toast.makeText(mContext, "上传失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case UPDATAPERSONAL:
                    if(msg.obj != null){
                        String result = (String) msg.obj;
                        try {
                            JSONObject jsonobj = new JSONObject(result);
                            if ("success".equals(jsonobj.optString("status"))){
                                Toast.makeText(mContext,"提交成功", Toast.LENGTH_SHORT).show();
                                infobean.setPhoto(path);
                                infobean.setRealname(info.getRealname());
                                infobean.setSex(sex);
                                infobean.setCity(info.getCity());
                                infobean.setWork_life(info.getWork_life());
                                infobean.setQq(info.getQq());
                                infobean.setWeixin(info.getWeixin());
                                infobean.setWeibo(info.getWeibo());
                                dialog.dismiss();
                                Intent intent = new Intent(mContext, Register_next_education.class);
//                                intent.putExtra("pagetype",pagetype);
                                startActivity(intent);
                                getiscompany(false);
                                finish();
                            }else{
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
                                Toast.makeText(mContext,"提交失败，请重新提交", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private void getmineninfomation() {
        if (infobean.getRealname() != null && infobean.getRealname().length() > 0) {
            tv_realname.setText(infobean.getRealname() + "");
        } else if (infobean.getCity() != null && infobean.getCity().length() > 0) {
            tv_local.setText(infobean.getCity() + "");
        } else if (infobean.getWork_life() != null && infobean.getWork_life().length() > 0) {
            tv_jobtime.setText(infobean.getWork_life() + "");
        } else if (infobean.getQq() != null && infobean.getQq().length() > 0) {
            tv_qq.setText(infobean.getQq() + "");
        } else if (infobean.getWeixin() != null && infobean.getWeixin().length() > 0) {
            tv_weixin.setText(infobean.getWeixin() + "");
        } else if (infobean.getWeibo() != null && infobean.getWeibo().length() > 0) {
            tv_weibo.setText(infobean.getWeibo() + "");
        }else if (infobean.getPhoto() != null && infobean.getPhoto().length() > 0) {
            imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + infobean.getPhoto(), heafimage, options);
        }else if (infobean.getSex() != null && infobean.getSex().length() > 0){
            if ("0".equals(infobean.getSex())){//女
                rbtn_woman.setChecked(true);
            }else if ("1".equals(infobean.getSex())){//男
                rbtn_man.setChecked(true);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_commany_info);
        mContext = this;
        info = new UserInfo(mContext);
        infobean = new UserInfoBean(mContext);
        commcamera = new Commany_camera(Register_Commany_info.this);
        personaljobtime = new Commany_personal_jobtime(Register_Commany_info.this);
        intentpage = getIntent();
        pagetype = intentpage.getStringExtra("pagetype");
        getView();
        getiscompany(false);
        if ("login0".equals(pagetype)||"register".equals(pagetype)) {
            if (Public_static_all.iscompanyI&&Public_static_all.iscompanyi){
                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + info.getPhoto(), heafimage, options);
            }
            if (Public_static_all.iscompanyJ&&Public_static_all.iscompanyj){
                tv_realname.setText(info.getRealname());
            }
            if (Public_static_all.iscompanyK&&Public_static_all.iscompanyk){
                tv_local.setText(info.getCity());
            }
            if (Public_static_all.iscompanyL&&Public_static_all.iscompanyl){
                tv_jobtime.setText(info.getEmail());
            }
            if (Public_static_all.iscompanyM&&Public_static_all.iscompanym){
                tv_qq.setText(info.getQq());
            }
            if (Public_static_all.iscompanyN&&Public_static_all.iscompanyn){
                tv_weixin.setText(info.getWeixin());
            }
            if (Public_static_all.iscompanyO&&Public_static_all.iscompanyo){
                tv_weibo.setText(info.getWeibo());
            }
        }else if ("company".equals(pagetype)){
            getpersonanews();
        }else if ("login2".equals(pagetype)){
            if (infobean.getPhoto() != null&&infobean.getPhoto().length() > 0){
                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + infobean.getPhoto(), heafimage, options);
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

    private void getView() {
        dialog = new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        head_avater = (WaveView) findViewById(R.id.commany_head_avater);
        head_avater.setOnClickListener(this);
        heafimage = (RoundImageView) findViewById(R.id.commany_head_avater_rundimage);
        real_name = (WaveView) findViewById(R.id.commany_real_name);
        real_name.setOnClickListener(this);
        current_local = (WaveView) findViewById(R.id.commany_current_local);
        current_local.setOnClickListener(this);
        job_time = (WaveView) findViewById(R.id.commany_job_time);
        job_time.setOnClickListener(this);
        commany_qq = (WaveView) findViewById(R.id.commany_qq);
        commany_qq.setOnClickListener(this);
        commany_weixin = (WaveView) findViewById(R.id.commany_weixin);
        commany_weixin.setOnClickListener(this);
        commany_weibo = (WaveView) findViewById(R.id.commany_weibo);
        commany_weibo.setOnClickListener(this);
        tv_realname = (TextView) findViewById(R.id.commany_tv_realname);
        tv_local = (TextView) findViewById(R.id.commany_tv_local);//选择
        tv_jobtime = (TextView) findViewById(R.id.commany_tv_jobtime);
        tv_qq = (TextView) findViewById(R.id.commany_tv_qq);
        tv_weixin = (TextView) findViewById(R.id.commany_tv_weixin);
        tv_weibo = (TextView) findViewById(R.id.commany_tv_weibo);
        rbtn_woman = (RadioButton) findViewById(R.id.commany_rbtn_woman);
        rbtn_man = (RadioButton) findViewById(R.id.commany_rbtn_man);
        // 显示图片的配置
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.color.gray).showImageOnFail(R.color.gray).cacheInMemory(true).cacheOnDisc(true).build();
        textview = (TextView) findViewById(R.id.textview);

        relevance_submit = (WaveView) findViewById(R.id.company_relevance_submit);
        relevance_submit.setOnClickListener(this);
        rg_sex = (RadioGroup) findViewById(R.id.commany_rg_sex);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.commany_rbtn_woman:
                        sex = "0";
                        info.setSex(sex);
                        break;
                    case R.id.commany_rbtn_man:
                        sex = "1";
                        info.setSex(sex);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.commany_head_avater:
                Public_static_all.iscompanyI = true;
                commcamera.showAsDropDown(textview);
                break;
            case R.id.commany_real_name:
                intent.setClass(mContext, Write_personal_info.class);
                intent.putExtra("type","realname1");
                Public_static_all.iscompanyJ = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.commany_current_local:
//                Public_static_all.iscompanyK = true;
                intent.setClass(mContext, ActivityAddlocation.class);
//                intent.putExtra("type","realname1");
                Public_static_all.iscompanyJ = true;
                startActivity(intent);
                //设置切换动画，从左边进入，上面退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.commany_job_time:
                Public_static_all.iscompanyL = true;
                personaljobtime.showAsDropDown(textview);
                break;
            case R.id.commany_qq:
                intent.setClass(mContext, Write_personal_info.class);
                intent.putExtra("type","QQ1");
                Public_static_all.iscompanyM = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.commany_weixin:
                intent.setClass(mContext, Write_personal_info.class);
                intent.putExtra("type","weixin1");
                Public_static_all.iscompanyN = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.commany_weibo:
                intent.setClass(mContext, Write_personal_info.class);
                intent.putExtra("type","weibo1");
                Public_static_all.iscompanyO = true;
                startActivity(intent);
                //设置切换动画，从左边进入，左边退出
                overridePendingTransition(R.anim.activity_int_left, R.anim.activity_out_top);
                break;
            case R.id.company_relevance_submit:
                Intent intent = new Intent(mContext,Register_next_education.class);
                intent.putExtra("pagetype","register");
                startActivity(intent);
//                getpersonal();
                break;

        }
    }
    private void getpersonal() {
        if (path == null && path.length() <= 0) {
            Toast.makeText(mContext, "请添加头像", Toast.LENGTH_SHORT).show();
        } else if (infobean.getUserid() == null && infobean.getUserid().length() <= 0) {
            Intent intent = new Intent(mContext, Login.class);
            startActivity(intent);
            finish();
        } else if (info.getRealname() == null && info.getRealname().length() <= 0) {
            Toast.makeText(mContext, "请填写真实姓名", Toast.LENGTH_SHORT).show();
        } else if (sex == null && sex.length() <= 0) {
            Toast.makeText(mContext, "请选择您的性别", Toast.LENGTH_SHORT).show();
        } else if (info.getCity() == null && info.getCity().length() <= 0) {
            Toast.makeText(mContext, "请填写您目前所在的城市", Toast.LENGTH_SHORT).show();
        } else if (info.getWork_life() == null && info.getWork_life().length() <= 0) {
            Toast.makeText(mContext, "请选择您的工作年限", Toast.LENGTH_SHORT).show();
        } else {
            if (NetBaseUtils.isConnnected(mContext)) {
                dialog.setMessage("正在提交..");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                new UserRequest(mContext, handler).UPDATEPERSONAL(infobean.getUserid(),info.getPhoto(),info.getRealname(),sex,info.getCity(),info.getWork_life(),info.getQq(),info.getWeixin(),info.getWeibo(), UPDATAPERSONAL);
            } else {
                Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
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
            heafimage.setImageDrawable(drawable);
            picname = "avatar" + infobean.getUserid() + String.valueOf(new Date().getTime());
            storeImageToSDCARD(photo, picname, filepath);
            if (head != null&&head.length() > 0) {
                if (NetBaseUtils.isConnnected(mContext)) {
                    new UserRequest(mContext, handler).uoloadavator(head, KEY);
                } else {
                    Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(mContext, "请重新裁剪照片", Toast.LENGTH_SHORT).show();
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
        if (Public_static_all.iscompanyI&&Public_static_all.iscompanyi){
            imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + info.getPhoto(), heafimage, options);
        }
        if (Public_static_all.iscompanyJ&&Public_static_all.iscompanyj){
            tv_realname.setText(info.getRealname());
        }
        if (Public_static_all.iscompanyK&&Public_static_all.iscompanyk){
            tv_local.setText(info.getCity());
        }
        if (Public_static_all.iscompanyL&&Public_static_all.iscompanyl){
            tv_jobtime.setText(info.getEmail());
        }
        if (Public_static_all.iscompanyM&&Public_static_all.iscompanym){
            tv_qq.setText(info.getQq());
        }
        if (Public_static_all.iscompanyN&&Public_static_all.iscompanyn){
            tv_weixin.setText(info.getWeixin());
        }
        if (Public_static_all.iscompanyO&&Public_static_all.iscompanyo){
            tv_weibo.setText(info.getWeibo());
        }
    }

    private void getiscompany(boolean iscompany){
        Public_static_all.iscompanyI = iscompany;
        Public_static_all.iscompanyi = iscompany;
        Public_static_all.iscompanyJ = iscompany;
        Public_static_all.iscompanyj = iscompany;
        Public_static_all.iscompanyK = iscompany;
        Public_static_all.iscompanyk = iscompany;
        Public_static_all.iscompanyL = iscompany;
        Public_static_all.iscompanyl = iscompany;
        Public_static_all.iscompanyM = iscompany;
        Public_static_all.iscompanym = iscompany;
        Public_static_all.iscompanyN = iscompany;
        Public_static_all.iscompanyn = iscompany;
        Public_static_all.iscompanyO = iscompany;
        Public_static_all.iscompanyo = iscompany;
    }
    private void getpersonanews() {
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新..");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETMYINFO(infobean.getUserid(), GETMYINFO);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
