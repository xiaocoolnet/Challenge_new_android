package com.example.chy.challenge.login.register.register_bean;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class UserInfoBean {
//    {
//        "status": "success",
//            "data": {
//                "userid": "657",
//                "realname": null,
//                "usertype": "1",
//                "phone": "17718320703",
//                "password": "###d78daca780e24d968bf098830035aac9",
//                "sex": null,
//                "email": null,
//                "qq": null,
//                "weixin": null,
//                "photo": null,
//                "devicestate": "1",
//                "city": null,
//                "weibo": null,
//                "work_life": null,
//                "company": null,
//                "myjob": null
//    }
//    }
     private String userid;
     private String realname;//个人真实姓名
     private String usertype;//用户类型
     private String phone;
     private String password;
     private String sex;
     private String email;//获取简历邮箱
     private String qq;//关联QQ
     private String weixin;//关联微信
     private String photo;//个人头像
     private String decicestate;
     private String city;
     private String weibo;//关联微博
     private String work_life;
     private String company;//当前公司
     private String myjob;//我的职位
     private Context context;//activity的

    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;

    public UserInfoBean(Context context) {
        this.context = context;
        mySharedPreferences= context.getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        editor = mySharedPreferences.edit();
    }
    public SharedPreferences getMySharedPreferences() {
        return mySharedPreferences;
    }

    public void setMySharedPreferences(SharedPreferences mySharedPreferences) {
        this.mySharedPreferences = mySharedPreferences;
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }
    public void removeUserid(){
        editor.remove("Userid");
        editor.commit();
    }
    /**
     * 第二次自动登录
     *
     * @return
     */
    public boolean isLogined() {
        if ("".equals(mySharedPreferences.getString("Userid",""))) {
            return false;
        }
        return true;
    }
    public String getUsertype() {
        if("".equals(mySharedPreferences.getString("Usertype","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Usertype","").toString();
    }

    public void setUsertype(String usertype) {
        editor.putString("Usertype",usertype);
        editor.commit();
        this.usertype = usertype;
    }

    public String getPhone() {
        if("".equals(mySharedPreferences.getString("Phone","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Phone","").toString();
    }

    public void setPhone(String phone) {
        editor.putString("Phone",phone);
        editor.commit();
        this.phone = phone;
    }

    public String getSex() {
        if("".equals(mySharedPreferences.getString("Sex","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Sex","").toString();
    }

    public void setSex(String sex) {
        editor.putString("Sex",sex);
        editor.commit();
        this.sex = sex;
    }

    public String getPassword() {
        if("".equals(mySharedPreferences.getString("Password","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Password","").toString();
    }

    public void setPassword(String password) {
        editor.putString("Password",password);
        editor.commit();
        this.password = password;
    }

    public String getDecicestate() {
        if("".equals(mySharedPreferences.getString("Decicestate","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Decicestate","").toString();
    }

    public void setDecicestate(String decicestate) {
        editor.putString("Decicestate",decicestate);
        editor.commit();
        this.decicestate = decicestate;
    }

    public String getCity() {
        if("".equals(mySharedPreferences.getString("City","").toString())){
            return "";
        }
        return mySharedPreferences.getString("City","").toString();
    }

    public void setCity(String city) {
        editor.putString("City",city);
        editor.commit();
        this.city = city;
    }

    public String getWork_life() {
        if("".equals(mySharedPreferences.getString("Work_Life","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Work_Life","").toString();
    }

    public void setWork_life(String work_life) {
        editor.putString("Work_Life",work_life);
        editor.commit();
        this.work_life = work_life;
    }

    public String getUserid() {
        if("".equals(mySharedPreferences.getString("Userid","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Userid","").toString();
    }

    public void setUserid(String userid) {
        editor.putString("Userid",userid);
        editor.commit();
        this.userid = userid;
    }

    public String getWeibo() {
        if("".equals(mySharedPreferences.getString("Weibo","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Weibo","").toString();
    }

    public void setWeibo(String weibo) {
        editor.putString("Weibo",weibo);
        editor.commit();
        this.weibo = weibo;
    }

    public String getQq() {
        if("".equals(mySharedPreferences.getString("Qq","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Qq","").toString();
    }

    public void setQq(String qq) {
        editor.putString("Qq",qq);
        editor.commit();
        this.qq = qq;
    }

    public String getWeixin() {
        if("".equals(mySharedPreferences.getString("Weixin","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Weixin","").toString();
    }

    public void setWeixin(String weixin) {
        editor.putString("Weixin",weixin);
        editor.commit();
        this.weixin = weixin;
    }

    public String getCompany() {
        if("".equals(mySharedPreferences.getString("Company","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Company","").toString();
    }

    public void setCompany(String company) {
        editor.putString("Company",company);
        editor.commit();
        this.company = company;
    }

    public String getEmail() {
        if("".equals(mySharedPreferences.getString("Email","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Email","").toString();
    }

    public void setEmail(String email) {
        editor.putString("Email",email);
        editor.commit();
        this.email = email;
    }

    public String getMyjob() {
        if("".equals(mySharedPreferences.getString("Myjob","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Myjob","").toString();
    }

    public void setMyjob(String myjob) {
        editor.putString("Myjob",myjob);
        editor.commit();
        this.myjob = myjob;
    }

    public String getPhoto() {
        if("".equals(mySharedPreferences.getString("Photo","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Photo","").toString();
    }

    public void setPhoto(String photo) {
        editor.putString("Photo",photo);
        editor.commit();
        this.photo = photo;
    }

    public String getRealname() {
        if("".equals(mySharedPreferences.getString("Realname","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Realname","").toString();
    }

    public void setRealname(String realname) {
        editor.putString("Realname",realname);
        editor.commit();
        this.realname = realname;
    }
}
