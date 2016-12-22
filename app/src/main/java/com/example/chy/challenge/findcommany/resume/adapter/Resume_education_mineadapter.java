package com.example.chy.challenge.findcommany.resume.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.bean.Resume_education_bean;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Resume_education_mineadapter extends BaseAdapter{
    private List<FindPersonal_fragment_bean.DataBean.EducationBean> educationlist;
    private Context mContext;
    private int type;

    public Resume_education_mineadapter(List<FindPersonal_fragment_bean.DataBean.EducationBean> educationlist, int type, Context mContext) {
        this.educationlist = educationlist;
        this.type = type;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return educationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return educationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView view = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.resume_mineeducation,null);
                    view = new HolderView();
                    view.resume_adapter_school = (TextView) convertView.findViewById(R.id.resume_adapter_school);
                    view.resume_adapter_readtime = (TextView) convertView.findViewById(R.id.resume_adapter_readtime);
                    view.resume_adapter_dgee = (TextView) convertView.findViewById(R.id.resume_adapter_dgee);
                    view.resume_adapter_type = (TextView) convertView.findViewById(R.id.resume_adapter_type);
                    convertView.setTag(view);
                    break;
            }
        }else{
            view = (HolderView) convertView.getTag();
        }
        switch (type) {
            case 0:
                view.resume_adapter_school.setText(educationlist.get(position).getSchool() + "");
                view.resume_adapter_readtime.setText(educationlist.get(position).getTime() + "");
                view.resume_adapter_dgee.setText(educationlist.get(position).getDegree() + "");
                view.resume_adapter_type.setText(educationlist.get(position).getMajor() + "");

                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView resume_adapter_school,resume_adapter_readtime,resume_adapter_dgee,resume_adapter_type;
    }
}
