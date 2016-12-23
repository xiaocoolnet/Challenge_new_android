package com.example.chy.challenge.findcommany.chance;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.FindPersonalFragment;
import com.example.chy.challenge.R;
import com.example.chy.challenge.findcommany.findwork.Find_Work_Fragment;
import com.example.chy.challenge.findcommany.findwork.pop.Pop_findwork_pakect;

/**
 * Created by Administrator on 2016/12/1 0001.
 * 找工作
 */

public class Find_work extends Fragment implements View.OnClickListener{
    private View view;
    private Context mContext;
    private Bundle bundle;
    private String pagename;
    private Find_Work_Fragment work;
    private RadioButton findwork_rbtn_news,findwork_rbtn_hot,findwork_rbtn_lately,findwork_rbtn_evaluation,findwork_rbtn_salary,findwork_rbtn_redenvelope;
    private TextView textview,findwork_tv_news,findwork_tv_hot,findwork_tv_lately,findwork_tv_evaluation,findwork_tv_salary,findwork_tv_redenvelope;
    private RadioGroup findwork_rg;
    private Pop_findwork_pakect poppaject;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_work,container,false);
        mContext = getActivity();
        poppaject = new Pop_findwork_pakect(Find_work.this);
        getview();
        bundle = getArguments();
        if (bundle != null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (bundle.getString("pagename") != null &&bundle.getString("pagename").length() > 0) {
                pagename = bundle.getString("pagename");
                if ("找工作".equals(pagename)) {
                    findwork_rbtn_news.setChecked(true);
                    findwork_tv_news.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "最新");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                } else if ("最新".equals(pagename)){
                    findwork_rbtn_news.setChecked(true);
                    findwork_tv_news.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "最新");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                } else if ("最热".equals(pagename)){
                    findwork_rbtn_hot.setChecked(true);
                    findwork_tv_hot.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "最热");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("最近".equals(pagename)){
                    findwork_rbtn_lately.setChecked(true);
                    findwork_tv_lately.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "最近");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("评价".equals(pagename)){
                    findwork_rbtn_evaluation.setChecked(true);
                    findwork_tv_evaluation.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "评价");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("薪资".equals(pagename)){
                    findwork_rbtn_salary.setChecked(true);
                    findwork_tv_salary.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "薪资");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("职位红包".equals(pagename)){
                    findwork_rbtn_redenvelope.setChecked(true);
                    findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "职位红包");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("面试红包".equals(pagename)){
                    findwork_rbtn_redenvelope.setChecked(true);
                    findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "面试红包");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("就职红包".equals(pagename)){
                    findwork_rbtn_redenvelope.setChecked(true);
                    findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "就职红包");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }else if ("全部红包".equals(pagename)){
                    findwork_rbtn_redenvelope.setChecked(true);
                    findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
                    work = new Find_Work_Fragment();
                    bundle = new Bundle();
                    bundle.putString("pagename", "全部红包");
                    work.setArguments(bundle);
                    transaction.replace(R.id.finadwork_ril_all, work);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
        return view;
    }
    private void getview() {
        textview = (TextView) view.findViewById(R.id.textview);
        findwork_rbtn_news = (RadioButton) view.findViewById(R.id.findwork_rbtn_news);//最新
        findwork_rbtn_news.setOnClickListener(this);
        findwork_tv_news = (TextView) view.findViewById(R.id.findwork_tv_news);//最新tv

        findwork_rbtn_hot = (RadioButton) view.findViewById(R.id.findwork_rbtn_hot);//最热
        findwork_rbtn_hot.setOnClickListener(this);
        findwork_tv_hot = (TextView) view.findViewById(R.id.findwork_tv_hot);//最热tv

        findwork_rbtn_lately = (RadioButton) view.findViewById(R.id.findwork_rbtn_lately);//最近
        findwork_rbtn_lately.setOnClickListener(this);
        findwork_tv_lately = (TextView) view.findViewById(R.id.findwork_tv_lately);//最近tv

        findwork_rbtn_evaluation = (RadioButton) view.findViewById(R.id.findwork_rbtn_evaluation);//评价
        findwork_rbtn_evaluation.setOnClickListener(this);
        findwork_tv_evaluation = (TextView) view.findViewById(R.id.findwork_tv_evaluation);//评价tv

        findwork_rbtn_salary = (RadioButton) view.findViewById(R.id.findwork_rbtn_salary);//薪资
        findwork_rbtn_salary.setOnClickListener(this);
        findwork_tv_salary = (TextView) view.findViewById(R.id.findwork_tv_salary);//薪资tv

        findwork_rbtn_redenvelope = (RadioButton) view.findViewById(R.id.findwork_rbtn_redenvelope);//红包
        findwork_rbtn_redenvelope.setOnClickListener(this);
        findwork_tv_redenvelope = (TextView) view.findViewById(R.id.findwork_tv_redenvelope);//红包tv

//        findwork_rg = (RadioGroup) view.findViewById(R.id.findwork_rg);
//        findwork_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                findwork_tv_news.setTextColor(getResources().getColor(R.color.gray));
//                findwork_tv_hot.setTextColor(getResources().getColor(R.color.gray));
//                findwork_tv_lately.setTextColor(getResources().getColor(R.color.gray));
//                findwork_tv_evaluation.setTextColor(getResources().getColor(R.color.gray));
//                findwork_tv_salary.setTextColor(getResources().getColor(R.color.gray));
//                findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.gray));
//                switch (checkedId){
//                    case R.id.findwork_rbtn_news://最新
//                        findwork_tv_news.setTextColor(getResources().getColor(R.color.green));
//                        work = new Find_Work_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("pagename", "最新");
//                        work.setArguments(bundle);
//                        transaction.replace(R.id.finadwork_ril_all, work);
//                        transaction.commitAllowingStateLoss();
//                        break;
//                    case R.id.findwork_rbtn_hot://最热
//                        findwork_tv_hot.setTextColor(getResources().getColor(R.color.green));
//                        work = new Find_Work_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("pagename", "最热");
//                        work.setArguments(bundle);
//                        transaction.replace(R.id.finadwork_ril_all, work);
//                        transaction.commitAllowingStateLoss();
//                        break;
//                    case R.id.findwork_rbtn_lately://最近
//                        findwork_tv_lately.setTextColor(getResources().getColor(R.color.green));
//                        work = new Find_Work_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("pagename", "最近");
//                        work.setArguments(bundle);
//                        transaction.replace(R.id.finadwork_ril_all, work);
//                        transaction.commitAllowingStateLoss();
//                        break;
//                    case R.id.findwork_rbtn_evaluation://评价
//                        findwork_tv_evaluation.setTextColor(getResources().getColor(R.color.green));
//                        work = new Find_Work_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("pagename", "评价");
//                        work.setArguments(bundle);
//                        transaction.replace(R.id.finadwork_ril_all, work);
//                        transaction.commitAllowingStateLoss();
//                        break;
//                    case R.id.findwork_rbtn_salary://薪资
//                        findwork_tv_salary.setTextColor(getResources().getColor(R.color.green));
//                        work = new Find_Work_Fragment();
//                        bundle = new Bundle();
//                        bundle.putString("pagename", "薪资");
//                        work.setArguments(bundle);
//                        transaction.replace(R.id.finadwork_ril_all, work);
//                        transaction.commitAllowingStateLoss();
//                        break;
//                    case R.id.findwork_rbtn_redenvelope://红包
//                        poppaject.showAsDropDown(textview);
////                        find_workfragment();
//                        break;
//                }
//            }
//        });
    }
    public void find_workfragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
        work = new Find_Work_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", "全部红包");
        work.setArguments(bundle);
        transaction.replace(R.id.finadwork_ril_all, work);
        transaction.commitAllowingStateLoss();
    }
    public void find_workfragment2(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
        work = new Find_Work_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", "职位红包");
        work.setArguments(bundle);
        transaction.replace(R.id.finadwork_ril_all, work);
        transaction.commitAllowingStateLoss();
    }
    public void find_workfragment3(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
        work = new Find_Work_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", "面试红包");
        work.setArguments(bundle);
        transaction.replace(R.id.finadwork_ril_all, work);
        transaction.commitAllowingStateLoss();
    }
    public void find_workfragment4(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
        work = new Find_Work_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", "就职红包");
        work.setArguments(bundle);
        transaction.replace(R.id.finadwork_ril_all, work);
        transaction.commitAllowingStateLoss();
    }
    public void find_workfragment5(String idlist){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.green));
        work = new Find_Work_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", idlist);
        work.setArguments(bundle);
        transaction.replace(R.id.finadwork_ril_all, work);
        transaction.commitAllowingStateLoss();
    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        findwork_tv_news.setTextColor(getResources().getColor(R.color.gray));
        findwork_tv_hot.setTextColor(getResources().getColor(R.color.gray));
        findwork_tv_lately.setTextColor(getResources().getColor(R.color.gray));
        findwork_tv_evaluation.setTextColor(getResources().getColor(R.color.gray));
        findwork_tv_salary.setTextColor(getResources().getColor(R.color.gray));
        findwork_tv_redenvelope.setTextColor(getResources().getColor(R.color.gray));
        switch (v.getId()){
            case R.id.findwork_rbtn_news://最新
                findwork_tv_news.setTextColor(getResources().getColor(R.color.green));
                work = new Find_Work_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "最新");
                work.setArguments(bundle);
                transaction.replace(R.id.finadwork_ril_all, work);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.findwork_rbtn_hot://最热
                findwork_tv_hot.setTextColor(getResources().getColor(R.color.green));
                work = new Find_Work_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "最热");
                work.setArguments(bundle);
                transaction.replace(R.id.finadwork_ril_all, work);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.findwork_rbtn_lately://最近
                findwork_tv_lately.setTextColor(getResources().getColor(R.color.green));
                work = new Find_Work_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "最近");
                work.setArguments(bundle);
                transaction.replace(R.id.finadwork_ril_all, work);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.findwork_rbtn_evaluation://评价
                findwork_tv_evaluation.setTextColor(getResources().getColor(R.color.green));
                work = new Find_Work_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "评价");
                work.setArguments(bundle);
                transaction.replace(R.id.finadwork_ril_all, work);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.findwork_rbtn_salary://薪资
                poppaject.showAsDropDown(textview,"salary");
                break;
            case R.id.findwork_rbtn_redenvelope://红包
                poppaject.showAsDropDown(textview,"packet");
//                        find_workfragment();
                break;
        }
    }
}
