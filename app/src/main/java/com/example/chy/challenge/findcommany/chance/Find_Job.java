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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.findcommany.findjob.Find_Job_Fragment;
import com.example.chy.challenge.findcommany.findjob.pop.Pop_findjob_pakect;
import com.example.chy.challenge.findcommany.findwork.Find_Work_Fragment;

/**
 * Created by Administrator on 2016/12/1 0001.
 * 找雇主
 */

public class Find_Job extends Fragment implements View.OnClickListener{
    private View view;
    private Context mContext;
    private LinearLayout job_news,job_scale,job_scope;
    private Find_Job_Fragment job;
    private Bundle bundle;
    private TextView tv_news,tv_hot,tv_lately,tv_news_text,tv_scale_text,tv_scope_text,textview;
    private Pop_findjob_pakect popfindjob;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.find_job,container,false);
        mContext = getActivity();
        popfindjob = new Pop_findjob_pakect(Find_Job.this);
        getview();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tv_news.setBackgroundResource(R.color.green);
        tv_news_text.setTextColor(mContext.getResources().getColor(R.color.green));
        job = new Find_Job_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", "最新");
        job.setArguments(bundle);
        transaction.replace(R.id.findjob_ril_all, job);
        transaction.commitAllowingStateLoss();
        return view;
    }

    private void getview() {
        textview = (TextView) view.findViewById(R.id.textview);
        job_news = (LinearLayout) view.findViewById(R.id.personal_job_news);//最新
        job_news.setOnClickListener(this);
        tv_news = (TextView) view.findViewById(R.id.findwork_tv_news);
        tv_news_text = (TextView) view.findViewById(R.id.tv_news_text);
        job_scale = (LinearLayout) view.findViewById(R.id.personal_job_scale);//规模
        job_scale.setOnClickListener(this);
        tv_hot = (TextView) view.findViewById(R.id.findwork_tv_hot);
        tv_scale_text = (TextView) view.findViewById(R.id.tv_scale_text);
        job_scope = (LinearLayout) view.findViewById(R.id.personal_job_scope);//范围
        job_scope.setOnClickListener(this);
        tv_lately = (TextView) view.findViewById(R.id.findwork_tv_lately);
        tv_scope_text = (TextView) view.findViewById(R.id.tv_scope_text);
    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tv_news.setBackgroundResource(R.color.white);
        tv_hot.setBackgroundResource(R.color.white);
        tv_lately.setBackgroundResource(R.color.white);
        tv_news_text.setTextColor(mContext.getResources().getColor(R.color.gray4));
        tv_scale_text.setTextColor(mContext.getResources().getColor(R.color.gray4));
        tv_scope_text.setTextColor(mContext.getResources().getColor(R.color.gray4));
        switch (v.getId()){
            case R.id.personal_job_news://最新
                tv_news.setBackgroundResource(R.color.green);
                tv_news_text.setTextColor(mContext.getResources().getColor(R.color.green));
                job = new Find_Job_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "最新");
                job.setArguments(bundle);
                transaction.replace(R.id.findjob_ril_all, job);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.personal_job_scale://规模
                popfindjob.showAsDropDown(textview,"scale");
//                tv_hot.setBackgroundResource(R.color.green);
//                tv_scale_text.setTextColor(mContext.getResources().getColor(R.color.green));
//                job = new Find_Job_Fragment();
//                bundle = new Bundle();
//                bundle.putString("pagename", "规模");
//                job.setArguments(bundle);
//                transaction.replace(R.id.findjob_ril_all, job);
//                transaction.commitAllowingStateLoss();
                break;
            case R.id.personal_job_scope://范围
                tv_lately.setBackgroundResource(R.color.green);
                tv_scope_text.setTextColor(mContext.getResources().getColor(R.color.green));
                job = new Find_Job_Fragment();
                bundle = new Bundle();
                bundle.putString("pagename", "范围");
                job.setArguments(bundle);
                transaction.replace(R.id.findjob_ril_all, job);
                transaction.commitAllowingStateLoss();
                break;
        }
    }
    public void find_jobfragment(String id){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tv_hot.setBackgroundResource(R.color.green);
        tv_scale_text.setTextColor(mContext.getResources().getColor(R.color.green));
        job = new Find_Job_Fragment();
        bundle = new Bundle();
        bundle.putString("pagename", id);
        job.setArguments(bundle);
        transaction.replace(R.id.findjob_ril_all, job);
        transaction.commitAllowingStateLoss();
    }
}
