package com.example.chy.challenge.findcommany.resume;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.Utils.NoScrollListView;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.adapter.Resume_education_mineadapter;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Mine_Resume extends Activity implements View.OnClickListener{
    private static final int GETMYRESUME = 1;
    private Context mContext;
    private UserInfoBean infobean;
    private FindPersonal_fragment_bean.DataBean findlistbean;
    private FindPersonal_fragment_bean.DataBean.EducationBean findedulistbean;
    private FindPersonal_fragment_bean.DataBean.WorkBean findworklistbean;
    private FindPersonal_fragment_bean.DataBean.ProjectBean findprojectlistbean;
    private List<FindPersonal_fragment_bean.DataBean.EducationBean> findedulist;
    private List<FindPersonal_fragment_bean.DataBean.WorkBean> findworklist;
    private List<FindPersonal_fragment_bean.DataBean.ProjectBean> findprojectlist;
    private WaveView back;
    private TextView resume_realname;
    private Resume_education_mineadapter resume;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETMYRESUME:
                    if (msg.obj != null) {
                        String result = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))) {
                                JSONObject jsonobj = json.getJSONObject("data");
                                findlistbean = new FindPersonal_fragment_bean.DataBean();
                                findlistbean.setUserid(jsonobj.getString("userid"));//"userid": "301",
                                findlistbean.setRealname(jsonobj.getString("realname"));//"realname": "高扬",
                                findlistbean.setUsertype(jsonobj.getString("usertype"));//"usertype": "3",
                                findlistbean.setPhone(jsonobj.getString("phone"));// "phone": "18363867129",
                                findlistbean.setPassword(jsonobj.getString("password"));//"password": "###7b156c659264be1b6892219fb25e5b23",
                                findlistbean.setSex(jsonobj.getString("sex"));//  "sex": "1",
                                findlistbean.setEmail(jsonobj.getString("email"));//"email": "",
                                findlistbean.setQq(jsonobj.getString("qq"));// "qq": "",
                                findlistbean.setWeixin(jsonobj.getString("weixin"));// "weixin": "",
                                findlistbean.setPhoto(jsonobj.getString("photo"));//"photo": "avatar20161103112919301.png",
                                findlistbean.setDevicestate(jsonobj.getString("devicestate"));//"devicestate": "1",
                                findlistbean.setCity(jsonobj.getString("city"));// "city": "广州",
                                findlistbean.setWeibo(jsonobj.getString("weibo"));//"weibo": "",
                                findlistbean.setWork_life(jsonobj.getString("work_life"));//"work_life": "1年以内",
                                findlistbean.setCompany(jsonobj.getString("company"));//"company": "校酷",
                                findlistbean.setMyjob(jsonobj.getString("myjob"));//"myjob": "iOS",
                                findlistbean.setResumes_id(jsonobj.getString("resumes_id"));//"resumes_id": "2",
                                findlistbean.setWork_property(jsonobj.getString("work_property"));// "work_property": "全职",
                                findlistbean.setAddress(jsonobj.getString("address"));//"address": "上海市-上海市",
                                findlistbean.setPosition_type(jsonobj.getString("position_type"));//  "position_type": "软件工程师",
                                findlistbean.setCategories(jsonobj.getString("categories"));//"categories": "无线产品",
                                findlistbean.setWantsalary(jsonobj.getString("wantsalary"));//"wantsalary": "1-5",
                                findlistbean.setJobstate(jsonobj.getString("jobstate"));// "jobstate": "离职",
                                findlistbean.setAdvantage(jsonobj.getString("advantage"));// "advantage": "4~5万",
                                findedulist = new ArrayList<>();
                                findedulist.clear();
                                JSONArray jsoneducation = jsonobj.getJSONArray("education");
                                if (jsoneducation != null && jsoneducation.length() > 0) {
                                    for (int j = 0; j < jsoneducation.length(); j++) {
                                        JSONObject objjson = jsoneducation.getJSONObject(j);
                                        findedulistbean = new FindPersonal_fragment_bean.DataBean.EducationBean();
                                        findedulistbean.setUserid(objjson.getString("userid")); //"userid": "301",
                                        findedulistbean.setSchool(objjson.getString("school"));// "school": "陆大",
                                        findedulistbean.setMajor(objjson.getString("major"));// "major": "数学",
                                        findedulistbean.setDegree(objjson.getString("degree"));// "degree": "硕士",
                                        findedulistbean.setTime(objjson.getString("time"));//"time": "2010年-2010年",
                                        findedulistbean.setExperience(objjson.getString("experience"));// "experience": "撒发生的",
                                        findedulistbean.setCreate_time(objjson.getString("create_time"));//"create_time": "1476933396"
                                        findedulist.add(findedulistbean);
                                    }
                                    findlistbean.setEducation(findedulist);
                                } else {
                                    findedulistbean = new FindPersonal_fragment_bean.DataBean.EducationBean();
                                    findedulistbean.setUserid(""); //"userid": "301",
                                    findedulistbean.setSchool("");// "school": "陆大",
                                    findedulistbean.setMajor("");// "major": "数学",
                                    findedulistbean.setDegree("");// "degree": "硕士",
                                    findedulistbean.setTime("");//"time": "2010年-2010年",
                                    findedulistbean.setExperience("");// "experience": "撒发生的",
                                    findedulistbean.setCreate_time("");//"create_time": "1476933396"
                                    findedulist.add(findedulistbean);
                                    findlistbean.setEducation(findedulist);
                                }
                                findworklist = new ArrayList<>();
                                findworklist.clear();
                                JSONArray jsonwork = jsonobj.getJSONArray("work");
                                if (jsonwork != null && jsonwork.length() > 0) {
                                    for (int j = 0; j < jsonwork.length(); j++) {
                                        JSONObject objjson = jsonwork.getJSONObject(j);
                                        findworklistbean = new FindPersonal_fragment_bean.DataBean.WorkBean();
                                        findworklistbean.setUserid(objjson.getString("userid"));// "userid": "301",
                                        findworklistbean.setCompany_name(objjson.getString("company_name"));// "company_name": "校酷",
                                        findworklistbean.setCompany_industry(objjson.getString("company_industry"));// "company_industry": "交互设计",
                                        findworklistbean.setJobtype(objjson.getString("jobtype"));// "jobtype": "技术专员/助理",
                                        findworklistbean.setSkill(objjson.getString("skill"));// "skill": "电子商务-智能硬件-用户研究",
                                        findworklistbean.setCreate_time(objjson.getString("create_time"));//"create_time": "1478143981",
                                        findworklistbean.setWork_period(objjson.getString("work_period"));//"create_time": "1478143981",
                                        findworklistbean.setContent(objjson.getString("content"));//"content": "4~5万"
                                        findworklist.add(findworklistbean);
                                    }
                                    findlistbean.setWork(findworklist);
                                } else {
                                    findworklistbean = new FindPersonal_fragment_bean.DataBean.WorkBean();
                                    findworklistbean.setUserid("");// "userid": "301",
                                    findworklistbean.setCompany_name("");// "company_name": "校酷",
                                    findworklistbean.setCompany_industry("");// "company_industry": "交互设计",
                                    findworklistbean.setJobtype("");// "jobtype": "技术专员/助理",
                                    findworklistbean.setSkill("");// "skill": "电子商务-智能硬件-用户研究",
                                    findworklistbean.setCreate_time("");//"create_time": "1478143981",
                                    findworklistbean.setContent("");//"content": "4~5万"
                                    findworklist.add(findworklistbean);
                                    findlistbean.setWork(findworklist);
                                }
                                findprojectlist = new ArrayList<>();
                                findprojectlist.clear();
                                JSONArray jsonproject = jsonobj.getJSONArray("project");
                                if (jsonproject != null && jsonproject.length() > 0) {
                                    for (int j = 0; j < jsonproject.length(); j++) {
                                        JSONObject objjson = jsonproject.getJSONObject(j);
                                        findprojectlistbean = new FindPersonal_fragment_bean.DataBean.ProjectBean();
                                        findprojectlistbean.setUserid(objjson.getString("userid"));// " "userid": "301",
                                        findprojectlistbean.setProject_name(objjson.getString("project_name"));// "project_name": "可口可乐了",
                                        findprojectlistbean.setStart_time(objjson.getString("start_time"));// "start_time": "2011年.1月",
                                        findprojectlistbean.setEnd_time(objjson.getString("end_time"));// ""end_time": "2012年.1月",
                                        findprojectlistbean.setDescription_project(objjson.getString("description_project"));//  "description_project": "项目描述湖酒店睡了快放假啊圣诞快乐附",
                                        findprojectlistbean.setCreate_time(objjson.getString("create_time"));//  "create_time": "1478143911"
                                        findprojectlist.add(findprojectlistbean);
                                    }
                                    findlistbean.setProject(findprojectlist);
                                } else {
                                    findprojectlistbean = new FindPersonal_fragment_bean.DataBean.ProjectBean();
                                    findprojectlistbean.setUserid("");// " "userid": "301",
                                    findprojectlistbean.setProject_name("");// "project_name": "可口可乐了",
                                    findprojectlistbean.setStart_time("");// "start_time": "2011年.1月",
                                    findprojectlistbean.setEnd_time("");// ""end_time": "2012年.1月",
                                    findprojectlistbean.setDescription_project("");//  "description_project": "项目描述湖酒店睡了快放假啊圣诞快乐附",
                                    findprojectlistbean.setCreate_time("");//  "create_time": "1478143911"
                                    findprojectlist.add(findprojectlistbean);
                                    findlistbean.setProject(findprojectlist);
                                }
                                getinfoView();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

        private void getinfoView() {
            if (findlistbean.getSex() != null&&findlistbean.getSex().length() > 0){
                if ("0".equals(findlistbean.getSex().toString())){
                    image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.ic_nvsheng));
                }else if ("1".equals(findlistbean.getSex().toString())){
                    image_sex.setImageDrawable(getResources().getDrawable(R.mipmap.ic_nansheng));
                }
            }else {
                image_sex.setVisibility(View.GONE);
            }
            if (findlistbean.getPhoto()!=null&&findlistbean.getPhoto().length() > 0){
                imageLoader.displayImage(NetBaseConstant.NET_HOST + findlistbean.getPhoto(), headimage, options);
            }
            if (findlistbean.getEducation() != null&&findlistbean.getEducation().size()>0){
                resume = new Resume_education_mineadapter(findedulist,1,mContext);
                resume_education_list.setAdapter(resume);
            }
            resume_realname.setText(findlistbean.getRealname()+"");
            adapter_location_city.setText(findlistbean.getCity().substring(0,3)+"");
            adapter_jobtime_worklife.setText(findlistbean.getWork_life()+"");
            adapter_personal_degree.setText(findlistbean.getEducation().get(0).getDegree()+"");
            adapter_work_property.setText(findlistbean.getWork_property()+"");
            resume_wantjobtyindus.setText(findlistbean.getPosition_type()+"");
            resume_wantjobsalary.setText(findlistbean.getWantsalary()+"");
            resume_wantjobtype.setText(findlistbean.getCategories()+"");
            resume_wantjobtime.setText(findlistbean.getJobstate()+"");
            resume_jobcompany.setText(findlistbean.getWork().get(0).getCompany_name()+"");
            resume_company_jobtime.setText(findlistbean.getWork().get(0).getWork_period()+"");
            resume_company_jobposition.setText(findlistbean.getWork().get(0).getJobtype()+"");
            String s = new String(findlistbean.getWork().get(0).getSkill());
            String[]  destString = s.split("-");
            if (destString[0] != null&&destString[0].length() > 0){
                resume_skillone.setVisibility(View.VISIBLE);
                resume_skillone.setText(destString[0]+"");
            }else if (destString[1] != null&&destString[1].length() > 0){
                resume_skilltwo.setVisibility(View.VISIBLE);
                resume_skilltwo.setText(destString[1]+"");
            }else if (destString[2] != null&&destString[2].length() > 0){
                resume_skillthree.setVisibility(View.VISIBLE);
                resume_skillthree.setText(destString[2]+"");
            }
            resume_projectname.setText(findlistbean.getProject().get(0).getProject_name()+"");
            resume_projectdescrib.setText(findlistbean.getProject().get(0).getDescription_project()+"");
            resume_mineadvantage.setText(findlistbean.getAdvantage()+"");
        }
    };

   private TextView adapter_location_city,adapter_jobtime_worklife,adapter_personal_degree,adapter_work_property,resume_wantjobtyindus
           ,resume_wantjobsalary,resume_wantjobtype,resume_wantjobtime,resume_jobcompany,resume_company_jobtime
           ,resume_company_jobposition,resume_skillone,resume_skilltwo,resume_skillthree,resume_projectname
           ,resume_projectdescrib,resume_mineadvantage;
    private RoundImageView headimage;
    private NoScrollListView resume_education_list;
    private ImageView image_sex;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_resume);
        mContext = this;
        infobean = new UserInfoBean(mContext);
        getview();
    }

    private void getview() {
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        resume_realname = (TextView) findViewById(R.id.resume_realname);//真实姓名
        adapter_location_city = (TextView) findViewById(R.id.adapter_location_city);//城市
        adapter_jobtime_worklife = (TextView) findViewById(R.id.adapter_jobtime_worklife);//工作年限
        adapter_personal_degree = (TextView) findViewById(R.id.adapter_personal_degree);//学历
        adapter_work_property = (TextView) findViewById(R.id.adapter_work_property);//全职/兼职
        headimage = (RoundImageView) findViewById(R.id.headimage);//头像
        resume_wantjobtyindus = (TextView) findViewById(R.id.resume_wantjobtyindus);//求职意向 工作类型
        resume_wantjobsalary = (TextView) findViewById(R.id.resume_wantjobsalary);//求职意向 薪资
        resume_wantjobtype = (TextView) findViewById(R.id.resume_wantjobtype);//求职意向 工作行业
        resume_wantjobtime = (TextView) findViewById(R.id.resume_wantjobtime);//求职意向 到岗时间
        resume_education_list = (NoScrollListView) findViewById(R.id.resume_education_list);//学历列表
        resume_jobcompany = (TextView) findViewById(R.id.resume_jobcompany);//工作单位
        resume_company_jobtime = (TextView) findViewById(R.id.resume_company_jobtime);//最近一次工作时间
        resume_company_jobposition = (TextView) findViewById(R.id.resume_company_jobposition);//在职职位
        resume_skillone = (TextView) findViewById(R.id.resume_skillone);//技能1
        resume_skilltwo = (TextView) findViewById(R.id.resume_skilltwo);//技能2
        resume_skillthree = (TextView) findViewById(R.id.resume_skillthree);//技能3
        resume_projectname = (TextView) findViewById(R.id.resume_projectname);//项目名称
        resume_projectdescrib = (TextView) findViewById(R.id.resume_projectdescrib);//项目描述
        resume_mineadvantage = (TextView) findViewById(R.id.resume_mineadvantage);//我的优势
        image_sex = (ImageView) findViewById(R.id.image_sex);//性别图
    }

    @Override
    protected void onResume() {
        super.onResume();
        getinfo();
    }

    private void getinfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYRESUME(infobean.getUserid(), GETMYRESUME);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
