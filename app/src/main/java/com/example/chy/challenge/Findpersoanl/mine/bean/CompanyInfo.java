package com.example.chy.challenge.Findpersoanl.mine.bean;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class CompanyInfo {
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

    private String logo;//公司logo
    private String companyid;//
    private String company_name;//公司全称
    private String company_web;//公司官网
    private String industry;//所属行业
    private String count;//人员规模
    private String financing;//融资
    private String com_introduce;//公司介绍
    private String produte_info;//产品介绍
    private Context context;//activity的

    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor editor;

    public CompanyInfo(Context context) {
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
}
