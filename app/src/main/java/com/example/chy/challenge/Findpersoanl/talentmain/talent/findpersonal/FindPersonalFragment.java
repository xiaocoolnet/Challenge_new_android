package com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.adapter.Find_Personal_Fragment_Adapter;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.example.chy.challenge.pnlllist.PullToRefreshBase;
import com.example.chy.challenge.pnlllist.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class FindPersonalFragment extends Fragment{
    private static final int KEY_GET_CODE = 1;
    private View view,viewH;
    private Bundle bundle;
    private String pagename;
    private UserInfoBean userinfo;
    private Context mContext;
    private ProgressDialog dialog;
    private PullToRefreshListView pulllistview;
    private SimpleDateFormat mdata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private FindPersonal_fragment_bean.DataBean findlistbean;
    private FindPersonal_fragment_bean.DataBean.EducationBean findedulistbean;
    private FindPersonal_fragment_bean.DataBean.WorkBean findworklistbean;
    private FindPersonal_fragment_bean.DataBean.ProjectBean findprojectlistbean;
    private List<FindPersonal_fragment_bean.DataBean> findlist = new ArrayList<>();
    private List<FindPersonal_fragment_bean.DataBean.EducationBean> findedulist;
    private List<FindPersonal_fragment_bean.DataBean.WorkBean> findworklist;
    private List<FindPersonal_fragment_bean.DataBean.ProjectBean> findprojectlist;
    private Find_Personal_Fragment_Adapter fpfa;
    private ListView lv_view;

    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case KEY_GET_CODE:
                    if (msg.obj != null){
                        String result = (String) msg.obj;
                        findlist.clear();
                        try {
                            JSONObject json = new JSONObject(result);
                            if ("success".equals(json.optString("status"))){
                                JSONArray jsonarray = json.getJSONArray("data");
                                if (jsonarray != null &&jsonarray.length() > 0){
                                    for (int i = 0;i < jsonarray.length(); i++){
                                        JSONObject jsonobj = jsonarray.getJSONObject(i);
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
                                        for (int j = 0;j < jsoneducation.length();j++){
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
                                        findworklist = new ArrayList<>();
                                        findworklist.clear();
                                        JSONArray jsonwork = jsonobj.getJSONArray("work");
                                        for (int j = 0;j < jsonwork.length();j++){
                                            JSONObject objjson = jsonwork.getJSONObject(j);
                                            findworklistbean = new FindPersonal_fragment_bean.DataBean.WorkBean();
                                            findworklistbean.setUserid(objjson.getString("userid"));// "userid": "301",
                                            findworklistbean.setCompany_name(objjson.getString("company_name"));// "company_name": "校酷",
                                            findworklistbean.setCompany_industry(objjson.getString("company_industry"));// "company_industry": "交互设计",
                                            findworklistbean.setJobtype(objjson.getString("jobtype"));// "jobtype": "技术专员/助理",
                                            findworklistbean.setSkill(objjson.getString("skill"));// "skill": "电子商务-智能硬件-用户研究",
                                            findworklistbean.setCreate_time(objjson.getString("create_time"));//"create_time": "1478143981",
                                            findworklistbean.setContent(objjson.getString("content"));//"content": "4~5万"
                                            findworklistbean.setWork_period(objjson.getString("work_period"));
                                            findworklist.add(findworklistbean);
                                        }
                                        findlistbean.setWork(findworklist);
                                        findprojectlist = new ArrayList<>();
                                        findprojectlist.clear();
                                        JSONArray jsonproject = jsonobj.getJSONArray("project");
                                        for (int j = 0;j < jsonproject.length();j++){
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
                                        findlist.add(findlistbean);
                                    }
                                }
                                fpfa = new Find_Personal_Fragment_Adapter(mContext,findlist,0);
                                lv_view.setAdapter(fpfa);
                                fpfa.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }else{
                        dialog.setMessage("加载失败");
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
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.findpersonalfragment,container,false);
        mContext = getActivity();
        userinfo = new UserInfoBean(mContext);
        bundle = getArguments();
        if (bundle != null){
            pagename = bundle.getString("pagename");
        }
        getfragmentView();

        return view;
    }

    private void getfragmentView() {
        //弹出刷新提示框
        dialog=new ProgressDialog(mContext, AlertDialog.THEME_HOLO_LIGHT);
//        dialog.setCancelable(false);
        pulllistview = (PullToRefreshListView) view.findViewById(R.id.pulllistview);
        pulllistview.setPullLoadEnabled(true);
        pulllistview.setScrollLoadEnabled(false);
        pulllistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                findpersonfragmentview();
                stopRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                stopRefresh();
            }
        });
        setLastData();
        lv_view = pulllistview.getRefreshableView();
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,DetailFindPersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("findItem",findlist.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lv_view.setDivider(null);//去掉listview自带的分割线
//        viewH = LayoutInflater.from(getActivity()).inflate(R.layout.firstpagenew, null);//添加listview头（position-1）
//        lv_view.addHeaderView(viewH);

    }
    //获取当前时间
    private void setLastData() {
        String text = formatdatatime(System.currentTimeMillis());
        pulllistview.setLastUpdatedLabel(text);
        Log.i("time", "-------->" + text);
    }

    //停止刷新
    private void stopRefresh() {
        pulllistview.postDelayed(new Runnable() {
            @Override
            public void run() {
                pulllistview.onPullUpRefreshComplete();
                pulllistview.onPullDownRefreshComplete();
                setLastData();
            }
        }, 2000);
    }
    private String formatdatatime(long time) {
        if (0 == time) {
            return "";
        }
        return mdata.format(new Date(time));
    }

    @Override
    public void onResume() {
        super.onResume();
        findpersonfragmentview();
    }

    private void findpersonfragmentview() {
        if ("推荐".equals(pagename)){

        }else if ("最近".equals(pagename)){

        }else if ("最热".equals(pagename)){

        }else if ("最新".equals(pagename)){

        }else if ("好评".equals(pagename)){

        }else if ("在线".equals(pagename)){

        }
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
            new UserRequest(mContext, handler).GETRESUME(KEY_GET_CODE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
