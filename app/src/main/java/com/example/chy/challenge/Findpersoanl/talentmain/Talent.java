package com.example.chy.challenge.Findpersoanl.talentmain;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.Choose_Searching;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.FindPersonal;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.NoPosition;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.Send_Commany_news;
import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.UserRequest;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.NetBaseUtils;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.Work_Searching;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 77588 on 2016/9/2.
 *
 */
public class Talent extends Fragment implements View.OnClickListener{
    private static final int KEY_GET_CODE = 1;
    private TextView tv;
    private View rootView;
    private Context mContext;
    private UserInfoBean userinfo;
    private FindPersonalBean.DataBean findpb;
    private FindPersonalBean.DataBean.JobsBean findbj;
    private List<FindPersonalBean.DataBean.JobsBean> findbjlist;
    private List<FindPersonalBean.DataBean> findlist;
    private WaveView intalent_retrieva,talent_location_city;
    private int pagetype;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private ProgressDialog dialog;


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
                                        JSONObject jsonobj = new JSONObject(json.getString("data"));
                                        findpb = new FindPersonalBean.DataBean();
                                        findpb.setLogo(jsonobj.getString("logo"));
                                        findpb.setCompanyid(jsonobj.getString("companyid"));
                                        findpb.setCompany_name(jsonobj.getString("company_name"));
                                        findpb.setCompany_web(jsonobj.getString("company_web"));
                                        findpb.setIndustry(jsonobj.getString("industry"));
                                        findpb.setCount(jsonobj.getString("count"));
                                        findpb.setFinancing(jsonobj.getString("financing"));
                                        findpb.setCreat_time(jsonobj.getString("creat_time"));
                                        findpb.setAuthentication(jsonobj.getString("authentication"));
                                        findpb.setCompany_score(jsonobj.getString("company_score"));
                                        findpb.setDistance(jsonobj.getString("distance"));
                                        findpb.setCom_introduce(jsonobj.getString("com_introduce"));
                                        findpb.setProdute_info(jsonobj.getString("produte_info"));
                                        findbjlist = new ArrayList<>();
                                        JSONArray jsonjob = jsonobj.getJSONArray("jobs");
                                        for (int j = 0;j < jsonjob.length();j++){
                                            JSONObject jsonobjob = jsonjob.getJSONObject(j);
                                            findbj = new FindPersonalBean.DataBean.JobsBean();
                                            findbj.setRealname(jsonobjob.getString("realname"));
                                            findbj.setPhoto(jsonobjob.getString("photo"));
                                            findbj.setMyjob(jsonobjob.getString("myjob"));// "myjob": "iOS",
                                            findbj.setJobid(jsonobjob.getString("jobid"));//  "jobid": "4",
                                            findbj.setUserid(jsonobjob.getString("userid"));// "userid": "301",
                                            findbj.setJobtype(jsonobjob.getString("jobtype"));//"jobtype": "技术专员/助理",
                                            findbj.setTitle(jsonobjob.getString("title"));// "title": "职位2",
                                            findbj.setSkill(jsonobjob.getString("skill"));//  "skill": "3个技能",
                                            findbj.setSalary(jsonobjob.getString("salary"));//  "salary": "2至2",
                                            findbj.setExperience(jsonobjob.getString("experience"));//"experience": "应届生",
                                            findbj.setEducation(jsonobjob.getString("education"));//"education": "研究生",
                                            findbj.setCity(jsonobjob.getString("city"));// "city": "云南省-迪庆藏族自治州",
                                            findbj.setAddress(jsonobjob.getString("address"));// "address": "The ",
                                            findbj.setDescription_job(jsonobjob.getString("description_job"));// "description_job": "1~2万",
                                            findbj.setCreate_time(jsonobjob.getString("create_time"));// "create_time": "",
                                            findbj.setWork_property(jsonobjob.getString("work_property"));// "work_property": ""
                                            findbjlist.add(findbj);
                                        }
                                        findpb.setJobs(findbjlist);
                                        findlist.add(findpb);
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                if (findlist != null &&findlist.size() > 0){
                                    Log.e("pagetype","-------------->");
                                        if (3==pagetype){
                                            getIntentView1();
                                        }else if (4 == pagetype){
                                            getIntentView2();
                                        }else if (5 == pagetype){
                                            getIntentView3();
                                        }else if (6 == pagetype){
                                            getIntentView4();
                                        }else if (7 == pagetype){
                                            getIntentView5();
                                        }else if (8 == pagetype){
                                            getIntentView6();
                                        } else {
                                        FindPersonal findpersonal = new FindPersonal();
                                        bundle = new Bundle();
                                        bundle.putString("pagename", "推荐");
                                        findpersonal.setArguments(bundle);
                                        transaction.replace(R.id.intalent_layout, findpersonal);
                                        transaction.commitAllowingStateLoss();
                                        pagetype = 1;
                                    }

                                }else {
                                    NoPosition noPosition = new NoPosition();
                                    transaction.replace(R.id.intalent_layout, noPosition);
                                    transaction.commitAllowingStateLoss();
                                    pagetype = 2;
                                }
                                dialog.dismiss();
                            }else{
//                                FragmentManager fragmentManager = getActivity().getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                NoPosition noPosition = new NoPosition();
                                transaction.replace(R.id.intalent_layout, noPosition);
                                transaction.commitAllowingStateLoss();
                                pagetype = 2;
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }else{
                        Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
//                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        NoPosition noPosition = new NoPosition();
                        transaction.replace(R.id.intalent_layout, noPosition);
                        transaction.commitAllowingStateLoss();
                        pagetype = 2;
                        dialog.dismiss();
                    }
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_talent,container,false);
        mContext = getActivity();
        fragmentManager = getFragmentManager();
        userinfo = new UserInfoBean(mContext);
        findlist =  new ArrayList<>();
        initview();
        getMyCompanynews(0);
        return rootView;
    }
    private void initview() {
        dialog = new ProgressDialog(mContext, android.app.AlertDialog.THEME_HOLO_LIGHT);
        dialog.setCancelable(false);
        intalent_retrieva = (WaveView) rootView.findViewById(R.id.intalent_retrieva);
        intalent_retrieva.setOnClickListener(this);
        talent_location_city = (WaveView) rootView.findViewById(R.id.talent_location_city);
        talent_location_city.setOnClickListener(this);
        tv = (TextView) rootView.findViewById(R.id.tv);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.intalent_retrieva:
                if (2 == pagetype) {
                    new AlertDialog.Builder(getActivity()).setTitle("系统提示").setMessage("您还未发布招聘信息，请先发布招聘信息")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(mContext,Send_Commany_news.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }else {
                    Intent intent = new Intent(mContext, Choose_Searching.class);
                    startActivity(intent);
                }
                break;
            case R.id.talent_location_city:
                //定位
                break;
            default:
                break;
        }

    }
    public void getIntentView1() {
        pagetype = 3;
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "推荐");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();
    }
    public void getIntentView2() {
        pagetype = 4;
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "最近");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();

    }
    public void getIntentView3() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "最热");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();
        pagetype = 5;
    }
    public void getIntentView4() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "最新");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();
        pagetype = 6;
    }
    public void getIntentView5() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "好评");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();
        pagetype = 7;
    }
    public void getIntentView6() {
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FindPersonal findpersonal = new FindPersonal();
        bundle = new Bundle();
        bundle.putString("pagename", "在线");
        findpersonal.setArguments(bundle);
        transaction.replace(R.id.intalent_layout, findpersonal);
        transaction.commitAllowingStateLoss();
        pagetype = 8;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getMyCompanynews();
//    }

    public void getMyCompanynews(int pagetype) {
        this.pagetype = pagetype;
        //获取验证码接口
        if (NetBaseUtils.isConnnected(mContext)) {
            dialog.setMessage("正在刷新");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
//            new UserRequest(mContext, handler).GETMYCOMMANY(userinfo.getUserid(),KEY_GET_CODE);
            new UserRequest(mContext, handler).GETMYCOMMANY("301",KEY_GET_CODE);
        }else{
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            NoPosition noPosition = new NoPosition();
            transaction.replace(R.id.intalent_layout, noPosition);
            transaction.commit();
            pagetype = 2;
            Toast.makeText(mContext, R.string.net_error,Toast.LENGTH_SHORT).show();
        }
    }
}
