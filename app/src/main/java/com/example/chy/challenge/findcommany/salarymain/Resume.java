package com.example.chy.challenge.findcommany.salarymain;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.Mine_Resume;
import com.example.chy.challenge.findcommany.resume.Write_personal_prohect;
import com.example.chy.challenge.findcommany.resume.Write_resume_education;
import com.example.chy.challenge.login.register.Register_Commany_info;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_advantage;
import com.example.chy.challenge.login.register.commany_info.Register_Mine_intention;
import com.example.chy.challenge.login.register.commany_info.Write_personal_jobnews;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77588 on 2016/9/1.
 */
public class Resume extends Fragment implements View.OnClickListener {
    private static final int GETMYRESUME = 1;
    private WaveView objective, education, work_experience, project_experience, advantage, updata_personal_info;
    private TextView tv_advantage, tv_project_experience, tv_work_experience, tv_education, tv_objective, personal_city, personal_jobtime, personal_sex, personal_realname;
    private RevealButton btn_look_allresume;
    private RoundImageView imageView;
    private Context mContext;
    private Intent intent;
    private UserInfoBean infobean;
    private FindPersonal_fragment_bean.DataBean findlistbean;
    private FindPersonal_fragment_bean.DataBean.EducationBean findedulistbean;
    private FindPersonal_fragment_bean.DataBean.WorkBean findworklistbean;
    private FindPersonal_fragment_bean.DataBean.ProjectBean findprojectlistbean;
    private List<FindPersonal_fragment_bean.DataBean.EducationBean> findedulist;
    private List<FindPersonal_fragment_bean.DataBean.WorkBean> findworklist;
    private List<FindPersonal_fragment_bean.DataBean.ProjectBean> findprojectlist;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

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
                                getinfo();
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

        private void getinfo() {
            if (findlistbean.getRealname() != null && findlistbean.getRealname().length() > 0) {
                personal_realname.setText(findlistbean.getRealname() + "");
            } else if (findlistbean.getSex() != null && findlistbean.getSex().length() > 0) {
                if ("0".equals(findlistbean.getSex())) {
                    personal_sex.setText("女");
                } else if ("1".equals(findlistbean.getSex())) {
                    personal_sex.setText("男");
                }
            } else if (findlistbean.getWork_life() != null && findlistbean.getWork_life().length() > 0) {
                personal_jobtime.setText(findlistbean.getWork_life());
            } else if (findlistbean.getCity() != null && findlistbean.getCity().length() > 0) {
                personal_city.setText(findlistbean.getCity());
            } else if (findlistbean.getPhoto() != null && findlistbean.getPhoto().length() > 0) {
                imageLoader.displayImage(NetBaseConstant.NET_HOST + "/" + infobean.getPhoto(), imageView, options);
            }
            if (findlistbean.getEducation() != null && findlistbean.getEducation().size() > 0
                    && findlistbean.getEducation().get(0).getSchool() != null && findlistbean.getEducation().get(0).getSchool().length() > 0
                    && findlistbean.getEducation().get(0).getMajor() != null && findlistbean.getEducation().get(0).getMajor().length() > 0
                    && findlistbean.getEducation().get(0).getDegree() != null && findlistbean.getEducation().get(0).getDegree().length() > 0
                    && findlistbean.getEducation().get(0).getTime() != null && findlistbean.getEducation().get(0).getTime().length() > 0
                    && findlistbean.getEducation().get(0).getExperience() != null && findlistbean.getEducation().get(0).getExperience().length() > 0
                    ) {
                tv_education.setText("完整");
                tv_education.setTextColor(getResources().getColor(R.color.gray4));
            } else {
                tv_education.setText("待完善");
                tv_education.setTextColor(getResources().getColor(R.color.green));
            }
            if (findlistbean.getWork() != null && findlistbean.getWork().size() > 0
                    && findlistbean.getWork().get(0).getCompany_name() != null && findlistbean.getWork().get(0).getCompany_name().length() > 0
                    && findlistbean.getWork().get(0).getJobtype() != null && findlistbean.getWork().get(0).getJobtype().length() > 0
                    && findlistbean.getWork().get(0).getCompany_industry() != null && findlistbean.getWork().get(0).getCompany_industry().length() > 0
                    && findlistbean.getWork().get(0).getSkill() != null && findlistbean.getWork().get(0).getSkill().length() > 0
                    && findlistbean.getWork().get(0).getContent() != null && findlistbean.getWork().get(0).getContent().length() > 0
                    && findlistbean.getWork().get(0).getWork_period() != null && findlistbean.getWork().get(0).getWork_period().length() > 0
                    ) {
                tv_work_experience.setText("完整");
                tv_work_experience.setTextColor(getResources().getColor(R.color.gray4));
            } else {
                tv_work_experience.setText("待完善");
                tv_work_experience.setTextColor(getResources().getColor(R.color.green));
            }
            if (findlistbean.getProject() != null && findlistbean.getProject().size() > 0
                    && findlistbean.getProject().get(0).getProject_name() != null && findlistbean.getProject().get(0).getProject_name().length() > 0
                    && findlistbean.getProject().get(0).getStart_time() != null && findlistbean.getProject().get(0).getStart_time().length() > 0
                    && findlistbean.getProject().get(0).getEnd_time() != null && findlistbean.getProject().get(0).getEnd_time().length() > 0
                    && findlistbean.getProject().get(0).getDescription_project() != null && findlistbean.getProject().get(0).getDescription_project().length() > 0
                    ) {
                tv_project_experience.setText("完整");
                tv_project_experience.setTextColor(getResources().getColor(R.color.gray4));
            } else {
                tv_project_experience.setText("待完善");
                tv_project_experience.setTextColor(getResources().getColor(R.color.green));
            }
            if (findlistbean.getWork_property() != null && findlistbean.getWork_property().length() > 0
                    && findlistbean.getAddress() != null && findlistbean.getAddress().length() > 0
                    && findlistbean.getPosition_type() != null && findlistbean.getPosition_type().length() > 0
                    && findlistbean.getCategories() != null && findlistbean.getCategories().length() > 0
                    && findlistbean.getWantsalary() != null && findlistbean.getWantsalary().length() > 0
                    ) {
                tv_objective.setText("完整");
                tv_objective.setTextColor(getResources().getColor(R.color.gray4));
            } else {
                tv_objective.setText("待完善");
                tv_objective.setTextColor(getResources().getColor(R.color.green));
            }
        }
    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resume, container, false);
        mContext = getActivity();
        infobean = new UserInfoBean(mContext);
        initview(rootView);
        return rootView;
    }

    private void initview(View view) {
        // 显示图片的配置
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.color.gray).showImageOnFail(R.color.gray).cacheInMemory(true).cacheOnDisc(true).build();
        objective = (WaveView) view.findViewById(R.id.objective);
        objective.setOnClickListener(this);
        tv_objective = (TextView) view.findViewById(R.id.tv_objective);

        education = (WaveView) view.findViewById(R.id.education);
        education.setOnClickListener(this);
        tv_education = (TextView) view.findViewById(R.id.tv_education);

        work_experience = (WaveView) view.findViewById(R.id.work_experience);
        work_experience.setOnClickListener(this);
        tv_work_experience = (TextView) view.findViewById(R.id.tv_work_experience);

        project_experience = (WaveView) view.findViewById(R.id.project_experience);
        project_experience.setOnClickListener(this);
        tv_project_experience = (TextView) view.findViewById(R.id.tv_project_experience);

        advantage = (WaveView) view.findViewById(R.id.advantage);
        advantage.setOnClickListener(this);
        tv_advantage = (TextView) view.findViewById(R.id.tv_advantage);

        btn_look_allresume = (RevealButton) view.findViewById(R.id.btn_look_allresume);
        btn_look_allresume.setOnClickListener(this);

        updata_personal_info = (WaveView) view.findViewById(R.id.updata_personal_info);
        updata_personal_info.setOnClickListener(this);

        personal_city = (TextView) view.findViewById(R.id.personal_city);
        personal_jobtime = (TextView) view.findViewById(R.id.personal_jobtime);
        personal_sex = (TextView) view.findViewById(R.id.personal_sex);
        personal_realname = (TextView) view.findViewById(R.id.personal_realname);
        imageView = (RoundImageView) view.findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.objective:
                startActivity(new Intent(getActivity(), Objective.class));
                break;*/
            case R.id.education:
                intent = new Intent(mContext, Write_resume_education.class);
                startActivity(intent);
                break;
            case R.id.work_experience:
//                startActivity(new Intent(getActivity(), WorkExperience.class));
                intent = new Intent(mContext, Write_personal_jobnews.class);
                startActivity(intent);
                break;
            case R.id.project_experience:
//                startActivity(new Intent(getActivity(), ProjectExperience.class));
                intent = new Intent(mContext, Write_personal_prohect.class);
                startActivity(intent);
                break;
            case R.id.advantage://我的优势
                intent = new Intent(mContext, Register_Mine_advantage.class);
                startActivity(intent);
                break;
            case R.id.objective://意向
                intent = new Intent(mContext, Register_Mine_intention.class);
                startActivity(intent);
                break;
            case R.id.btn_look_allresume://预览简历
                intent = new Intent(mContext, Mine_Resume.class);
                startActivity(intent);
                break;
            case R.id.updata_personal_info://个人信息
                intent = new Intent(mContext, Register_Commany_info.class);
                intent.putExtra("pagetype","company");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPersonalInfo();
    }

    private void getPersonalInfo() {
        if (NetBaseUtils.isConnnected(mContext)) {
            new UserRequest(mContext, handler).GETMYRESUME(infobean.getUserid(), GETMYRESUME);
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }

}
