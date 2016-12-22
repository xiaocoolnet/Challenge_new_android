package com.example.chy.challenge.login.register.register_bean;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class UserInfo{
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

    private String logo;//公司logo
    private String companyid;//公司id
    private String company_name;//公司名称
    private String company_web;//公司网址
    private String industry;//公司职位类型
    private String count;//公司规模
    private String financing;//融资阶段
    private String com_introduce;//公司介绍
    private String produte_info;//产品介绍

    private String schoolname;//学校名称
    private String schoolmajor;//专业


    private String commany_industry;//公司行业
    private String Position_type;//职位类型
    private String skills_show1;//技能展示1
    private String skills_show2;//技能展示2
    private String skills_show3;//技能展示3
    private String skills_show;//技能展示
    private String company_nameone;//公司名称

    private String personal_type;//个人期望职位
    private String personal_industry;//个人期望行业

    private String projectname;//项目名称
    private String projectdescrip;//项目描述
    private String starttime;//项目开始时间
    private String endtime;//项目结束时间

    public String getStarttime() {
        if("".equals(mySharedPreferences.getString("Starttime","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Starttime","").toString();
    }

    public void setStarttime(String starttime) {
        editor.putString("Starttime",starttime);
        editor.commit();
        this.starttime = starttime;
    }

    public String getEndtime() {
        if("".equals(mySharedPreferences.getString("Endtime","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Endtime","").toString();
    }

    public void setEndtime(String endtime) {
        editor.putString("Endtime",endtime);
        editor.commit();
        this.endtime = endtime;
    }

    public String getProjectname() {
        if("".equals(mySharedPreferences.getString("Projectname","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Projectname","").toString();
    }

    public void setProjectname(String projectname) {
        editor.putString("Projectname",projectname);
        editor.commit();
        this.projectname = projectname;
    }

    public String getProjectdescrip() {
        if("".equals(mySharedPreferences.getString("Projectdescrip","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Projectdescrip","").toString();
    }

    public void setProjectdescrip(String projectdescrip) {
        editor.putString("Projectdescrip",projectdescrip);
        editor.commit();
        this.projectdescrip = projectdescrip;
    }

    public String getPersonal_type() {
        if("".equals(mySharedPreferences.getString("Personal_type","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Personal_type","").toString();
    }

    public void setPersonal_type(String personal_type) {
        editor.putString("Personal_type",personal_type);
        editor.commit();
        this.personal_type = personal_type;
    }

    public String getPersonal_industry() {
        if("".equals(mySharedPreferences.getString("Personal_industry","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Personal_industry","").toString();
    }

    public void setPersonal_industry(String personal_industry) {
        editor.putString("Personal_industry",personal_industry);
        editor.commit();
        this.personal_industry = personal_industry;
    }

    public String getSchoolname() {
        if("".equals(mySharedPreferences.getString("Schoolname","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Schoolname","").toString();
    }

    public void setSchoolname(String schoolname) {
        editor.putString("Schoolname",schoolname);
        editor.commit();
        this.schoolname = schoolname;
    }

    public String getSchoolmajor() {
        if("".equals(mySharedPreferences.getString("Schoolmajor","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Schoolmajor","").toString();
    }

    public void setSchoolmajor(String schoolmajor) {
        editor.putString("Schoolmajor",schoolmajor);
        editor.commit();
        this.schoolmajor = schoolmajor;
    }

    public String getCompany_nameone() {
        if("".equals(mySharedPreferences.getString("Company_nameone","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Company_nameone","").toString();
    }

    public void setCompany_nameone(String company_nameone) {
        editor.putString("Company_nameone",company_nameone);
        editor.commit();
        this.company_nameone = company_nameone;
    }

    private Context context;//activity的

    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;

    public UserInfo(Context context) {
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


    public String getCommany_industry() {
        if("".equals(mySharedPreferences.getString("Commany_industry","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Commany_industry","").toString();
    }

    public void setCommany_industry(String commany_industry) {
        editor.putString("Commany_industry",commany_industry);
        editor.commit();
        this.commany_industry = commany_industry;
    }

    public String getPosition_type() {
        if("".equals(mySharedPreferences.getString("Position_type","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Position_type","").toString();
    }

    public void setPosition_type(String position_type) {
        editor.putString("Position_type",position_type);
        editor.commit();
        Position_type = position_type;
    }

    public String getSkills_show1() {
        if("".equals(mySharedPreferences.getString("Skills_show1","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Skills_show1","").toString();
    }

    public void setSkills_show1(String skills_show1) {
        editor.putString("Skills_show1",skills_show1);
        editor.commit();
        this.skills_show1 = skills_show1;
    }

    public String getSkills_show2() {
        if("".equals(mySharedPreferences.getString("Skills_show2","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Skills_show2","").toString();
    }

    public void setSkills_show2(String skills_show2) {
        editor.putString("Skills_show2",skills_show2);
        editor.commit();
        this.skills_show2 = skills_show2;
    }

    public String getSkills_show3() {
        if("".equals(mySharedPreferences.getString("Skills_show3","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Skills_show3","").toString();
    }

    public void setSkills_show3(String skills_show3) {
        editor.putString("Skills_show3",skills_show3);
        editor.commit();
        this.skills_show3 = skills_show3;
    }

    public String getSkills_show() {
        if("".equals(mySharedPreferences.getString("Skills_show","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Skills_show","").toString();
    }

    public void setSkills_show(String skills_show) {
        editor.putString("Skills_show",skills_show);
        editor.commit();
        this.skills_show = skills_show;
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

    public String getLogo() {
        if("".equals(mySharedPreferences.getString("Logo","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Logo","").toString();
    }

    public void setLogo(String logo) {
        editor.putString("Logo",logo);
        editor.commit();
        this.logo = logo;
    }

    public String getCompanyid() {
        if("".equals(mySharedPreferences.getString("Companyid","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Companyid","").toString();
    }

    public void setCompanyid(String companyid) {
        editor.putString("Companyid",companyid);
        editor.commit();
        this.companyid = companyid;
    }

    public String getCompany_name() {
        if("".equals(mySharedPreferences.getString("Company_name","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Company_name","").toString();
    }

    public void setCompany_name(String company_name) {
        editor.putString("Company_name",company_name);
        editor.commit();
        this.company_name = company_name;
    }

    public String getIndustry() {
        if("".equals(mySharedPreferences.getString("Industry","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Industry","").toString();
    }

    public void setIndustry(String industry) {
        editor.putString("Industry",industry);
        editor.commit();
        this.industry = industry;
    }

    public String getCompany_web() {
        if("".equals(mySharedPreferences.getString("Company_web","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Company_web","").toString();
    }

    public void setCompany_web(String company_web) {
        editor.putString("Company_web",company_web);
        editor.commit();
        this.company_web = company_web;
    }

    public String getCount() {
        if("".equals(mySharedPreferences.getString("Count","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Count","").toString();
    }

    public void setCount(String count) {
        editor.putString("Count",count);
        editor.commit();
        this.count = count;
    }

    public String getFinancing() {
        if("".equals(mySharedPreferences.getString("Financing","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Financing","").toString();
    }

    public void setFinancing(String financing) {
        editor.putString("Financing",financing);
        editor.commit();
        this.financing = financing;
    }

    public String getProdute_info() {
        if("".equals(mySharedPreferences.getString("Produte_info","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Produte_info","").toString();
    }

    public void setProdute_info(String produte_info) {
        editor.putString("Produte_info",produte_info);
        editor.commit();
        this.produte_info = produte_info;
    }

    public String getCom_introduce() {
        if("".equals(mySharedPreferences.getString("Com_introduce","").toString())){
            return "";
        }
        return mySharedPreferences.getString("Com_introduce","").toString();
    }

    public void setCom_introduce(String com_introduce) {
        editor.putString("Com_introduce",com_introduce);
        editor.commit();
        this.com_introduce = com_introduce;
    }
}
