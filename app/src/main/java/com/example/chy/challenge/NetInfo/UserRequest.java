package com.example.chy.challenge.NetInfo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

import com.example.chy.challenge.Utils.LogUtils;
import com.example.chy.challenge.Utils.NetBaseUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/20.
 */
public class UserRequest {
    private Context mContext;
    private Handler handler;

    public UserRequest(Context mContext, Handler handler) {

        this.mContext = mContext;
        this.handler = handler;
    }

    /**
     * 登录
     * @param phone
     * @param pwd
     * @param KEY
     */
    public void Login(final String phone, final String pwd,final int KEY){
        new Thread() {
            Message msg = Message.obtain();
            public void run() {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone",phone));
                params.add(new BasicNameValuePair("password",pwd));
                LogUtils.i("TuiSong", "device_token" + params.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.NET_USER_LOGIN, params, mContext);
                LogUtils.i("Tip",result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            };
        }.start();
    }

    /**
     * 获取验证码
     * @param phone
     */
    public void GetCode(final String phone,final int KEY){
        new Thread() {
            Message msg = Message.obtain();
            public void run() {
                Message msg = new Message();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("phone",phone));
                LogUtils.i("TuiSong", "device_token" + params.toString());
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.NET_USER_CODE, params, mContext);
                LogUtils.i("Tip","原数据"+result);
                        msg.what = KEY;
                        msg.obj = result;
                        LogUtils.i("Tip",msg.obj.toString());
                        handler.sendMessage(msg);
            };
        }.start();
    }

    /**
     * 检查号码是否可用
     * @param phone
     * @param KEY
     */
    public void CheckPhone(final String phone,final int KEY){
        new Thread(){
            Message msg = Message.obtain();

            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone",phone));
//                LogUtils.i("","");
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.CHECK_PHONE,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     *注册
     * @param phone,password,code,devicestate(1=>iOS 2=>android)
     */
    public void Regist(final String phone, final String password, final String code,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone",phone));
                parmas.add(new BasicNameValuePair("password",password));
                parmas.add(new BasicNameValuePair("code",code));
                parmas.add(new BasicNameValuePair("devicestate","2"));//1.iso2.android
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.REGIST,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     *获取我的公司信息
     */
    public void GETMYCOMMANY(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForPost(UserNetConstant.GETMYCOMMANY,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 修改密码
     * @param phone
     * @param password
     * @param code
     * @param KEY
     */
    public void ChangePwd(final String phone,final String password,final String code,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone",phone));
                parmas.add(new BasicNameValuePair("code",code));
                parmas.add(new BasicNameValuePair("password",password));
                String resault = NetBaseUtils.getResponseForPost(UserNetConstant.CHANGE_PASSWORD,parmas,mContext);
                msg.what = KEY;
                msg.obj = resault;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 上传头像
     */
    public void uoloadavator(final String upfile,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("upfile",upfile));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPLOADAVATAR,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改企业资料
     */
    public void UPDATECOMMANY(final String userid,final String avatar,final String realname,final String myjob,final String email,final String company,final String qq,final String weixin,final String weibo,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("avatar",avatar));
                parmas.add(new BasicNameValuePair("realname",realname));
                parmas.add(new BasicNameValuePair("myjob",myjob));
                parmas.add(new BasicNameValuePair("email",email));
                parmas.add(new BasicNameValuePair("company",company));
                parmas.add(new BasicNameValuePair("qq",qq));
                parmas.add(new BasicNameValuePair("weixin",weixin));
                parmas.add(new BasicNameValuePair("weibo",weibo));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPDATECOMMANY,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改企业资料
     */
//    public void UPDATECOMMANY(final String userid,final int KEY){
//        new Thread(){
//            Message msg = Message.obtain();
//            @Override
//            public void run() {
//                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
//                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPDATECOMMANY,parmas,mContext);
//                msg.what = KEY;
//                msg.obj = result;
//                handler.sendMessage(msg);
//            }
//        }.start();
//    }
    /**
     * 获取招聘列表
     */
    public void GETJOBLIST(final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();

                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETJOBLIST,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取简历列表
     */
    public void GETRESUME(final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();

                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETRESUME,parmas,mContext);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改个人资料
     */
    public void UPDATEPERSONAL(final String userid,final String avatar,final String realname,final String sex,final String city,final String work_life,final String qq,final String weixin,final String weibo,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("avatar",avatar));
                parmas.add(new BasicNameValuePair("realname",realname));
                parmas.add(new BasicNameValuePair("sex",sex));
                parmas.add(new BasicNameValuePair("city",city));
                parmas.add(new BasicNameValuePair("work_life",work_life));
                parmas.add(new BasicNameValuePair("qq",qq));
                parmas.add(new BasicNameValuePair("weixin",weixin));
                parmas.add(new BasicNameValuePair("weibo",weibo));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPDATEPERSONAL,parmas,mContext);
                Log.i("修改个人资料","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 发布教育经历
     * userid,school学校,major专业,degree学历,time时间段,experience在校经历
     */
    public void PUBLISHEDUCATION(final String userid,final String school,final String major,final String degree,final String time,final String experience,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("school",school));
                parmas.add(new BasicNameValuePair("major",major));
                parmas.add(new BasicNameValuePair("degree",degree));
                parmas.add(new BasicNameValuePair("time",time));
                parmas.add(new BasicNameValuePair("experience",experience));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.PUBLISHEDUCATION,parmas,mContext);
                Log.i("发布教育经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取黑名单列表type=1个人获取被拉黑的企业列表，type=2企业获取被拉黑的个人列表
     */
    public void GETBLACKlIST(final String userid,final String type,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("type",type));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETBLACKlIST,parmas,mContext);
                Log.i("发布教育经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 取消拉黑（1个人拉黑企业，2企业拉黑个人),blackid(被拉黑用户id,不写则删除全部)
     */
    public void DELBLACKLIST(final String userid,final String type,final String blackid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("type",type));
                parmas.add(new BasicNameValuePair("blackid",blackid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.DELBLACKLIST,parmas,mContext);
                Log.i("发布教育经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 验证旧密码
     */
    public void OLDPASSWORD(final String phone,final String password,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone",phone));
                parmas.add(new BasicNameValuePair("password",password));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.OLDPASSWORD,parmas,mContext);
                Log.i("发布教育经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改密码
     */
    public void UPDATEPASS(final String phone,final String password,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("phone",phone));
                parmas.add(new BasicNameValuePair("password",password));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPDATEPASS,parmas,mContext);
                Log.i("修改密码","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改手机号码
     */
    public void UPDATEPHONE(final String userid,final String phone,final String code,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("phone",phone));
                parmas.add(new BasicNameValuePair("code",code));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.UPDATEPHONE,parmas,mContext);
                Log.i("修改手机号","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取个人和企业的基本信息
     */
    public void GETMYINFO(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYINFO,parmas,mContext);
                Log.i("获取个人和企业的基本信息","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 修改手机号码
     */
    public void GETMYINVITED(final String userid,final String type,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("type",type));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYINVITED,parmas,mContext);
                Log.i("获取个人和企业的基本信息","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }/**
     * 获取企业信息列表(以及我的) (不含招聘列表)
     */
    public void GETCOMPANYLIST_NO(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETCOMPANYLIST_NO,parmas,mContext);
                Log.i("获取企业信息列表(以及我的) (不含招聘列表)","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取企业信息列表(以及我的) (不含招聘列表)
     */
    public void GETCOMPANYLIST(final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETCOMPANYLIST,parmas,mContext);
                Log.i("获取企业信息列表(以及我的) (含招聘列表)","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }/**
     * 添加公司信息
     * logo（公司LOGO）,company_name公司全称,company_web官网,industry所属行业,count人员规模,financing融资阶段,com_introduce公司介绍,produte_info产品介绍
     */
    public void COMPANY_INFO(final String userid,final String logo,final String company_name,final String company_web,final String industry,final String count,final String financing,final String com_introduce,final String produte_info,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("logo",logo));
                parmas.add(new BasicNameValuePair("company_name",company_name));
                parmas.add(new BasicNameValuePair("company_web",company_web));
                parmas.add(new BasicNameValuePair("industry",industry));
                parmas.add(new BasicNameValuePair("count",count));
                parmas.add(new BasicNameValuePair("financing",financing));
                parmas.add(new BasicNameValuePair("com_introduce",com_introduce));
                parmas.add(new BasicNameValuePair("produte_info",produte_info));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.COMPANY_INFO,parmas,mContext);
                Log.i("添加公司信息","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 发布 工作经历
     * userid,company_name公司名称,company_industry公司行业,jobtype职位类别,skill技能,work_period任职时间段,content工作内容
     */
    public void PUBLISHWORK(final String userid,final String company_name,final String company_industry,final String jobtype,final String skill,final String work_period,final String content,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("company_name",company_name));
                parmas.add(new BasicNameValuePair("company_industry",company_industry));
                parmas.add(new BasicNameValuePair("jobtype",jobtype));
                parmas.add(new BasicNameValuePair("skill",skill));
                parmas.add(new BasicNameValuePair("work_period",work_period));
                parmas.add(new BasicNameValuePair("content",content));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.PUBLISHWORK,parmas,mContext);
                Log.i("发布 工作经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 发布 工作经历
     * userid,company_name公司名称,company_industry公司行业,jobtype职位类别,skill技能,work_period任职时间段,content工作内容
     */
    public void PUBLISHADVANTAGE(final String userid,final String advantage,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("advantage",advantage));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.PUBLISHADVANTAGE,parmas,mContext);
                Log.i("发布 工作经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 发布 求职意向
     * userid,work_property(工作性质),address（工作地点）,position_type（职位类型）,categories（行业类别）,wantsalary（期望薪资）,jobstate（工作状态）
     */
    public void PUBLISHINTENSION(final String userid,final String work_property,final String address,final String position_type,final String categories,final String wantsalary,final String jobstate,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("work_property",work_property));
                parmas.add(new BasicNameValuePair("address",address));
                parmas.add(new BasicNameValuePair("position_type",position_type));
                parmas.add(new BasicNameValuePair("categories",categories));
                parmas.add(new BasicNameValuePair("wantsalary",wantsalary));
                parmas.add(new BasicNameValuePair("jobstate",jobstate));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.PUBLISHINTENSION,parmas,mContext);
                Log.i("发布 求职意向","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的投递记录
     */
    public void GETMYAPPLYJOB(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYAPPLYJOB,parmas,mContext);
                Log.i("获取我的投递记录","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的简历
     */
    public void GETMYRESUME(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYRESUME,parmas,mContext);
                Log.i("获取我的简历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的优势
     */
    public void GETMYADVANTAGE(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYADVANTAGE,parmas,mContext);
                Log.i("获取我的优势","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的求职意向
     */
    public void GETMYINTENSION(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYINTENSION,parmas,mContext);
                Log.i("获取我的求职意向","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的工作实习经历
     */
    public void GETMYWORKLIST(final String userid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETMYWORKLIST,parmas,mContext);
                Log.i("获取我的工作实习经历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取我的投递记录
     */
    public void GETFAVORITE(final String userid,final String type,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("type",type));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETFAVORITE,parmas,mContext);
                Log.i("获取我的收藏","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 判断是否投递过简历companyid,jobid,userid
     */
    public void APPLYJOB_JUDGE(final String userid,final String companyid,final String jobid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("companyid",companyid));
                parmas.add(new BasicNameValuePair("jobid",jobid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.APPLYJOB_JUDGE,parmas,mContext);
                Log.i("判断是否投递过简历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 投递过简历companyid,jobid,userid
     */
    public void APPLYJOB(final String userid,final String companyid,final String jobid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("companyid",companyid));
                parmas.add(new BasicNameValuePair("jobid",jobid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.APPLYJOB,parmas,mContext);
                Log.i("投递过简历","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 检测是否收藏userid,object_id,type:1、招聘、2、简历
     */
    public void CHECKHADFAVORITE(final String userid,final String object_id,final String type,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("object_id",object_id));
                parmas.add(new BasicNameValuePair("type",type));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.CHECKHADFAVORITE,parmas,mContext);
                Log.i("是否收藏","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 收藏userid,object_id,type:1、招聘、2、简历, title,description
     */
    public void ADDFAVORITE(final String userid,final String object_id,final String type,final String title,final String description,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("object_id",object_id));
                parmas.add(new BasicNameValuePair("type",type));
                parmas.add(new BasicNameValuePair("title",title));
                parmas.add(new BasicNameValuePair("description",description));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.ADDFAVORITE,parmas,mContext);
                Log.i("收藏","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 取消收藏userid,object_id(招聘或简历的id)(可以是一个id的字符串中间用英文逗号隔开),type:(1、招聘、2、简历)
     */
    public void CANCELFAVORITE(final String userid,final String object_id,final String type,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("userid",userid));
                parmas.add(new BasicNameValuePair("object_id",object_id));
                parmas.add(new BasicNameValuePair("type",type));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.CANCELFAVORITE,parmas,mContext);
                Log.i("取消收藏","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
    /**
     * 获取字典内容
     */
    public void GETDICTIONARYLIST(final String parentid,final int KEY){
        new Thread(){
            Message msg = Message.obtain();
            @Override
            public void run() {
                List<NameValuePair> parmas = new ArrayList<NameValuePair>();
                parmas.add(new BasicNameValuePair("parentid",parentid));
                String result = NetBaseUtils.getResponseForImg(UserNetConstant.GETDICTIONARYLIST,parmas,mContext);
                Log.i("获取字典内容","--------------->"+result);
                msg.what = KEY;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }.start();
    }
}
