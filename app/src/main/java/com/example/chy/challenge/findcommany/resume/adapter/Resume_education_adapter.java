package com.example.chy.challenge.findcommany.resume.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.resume.bean.Resume_education_bean;
import com.example.chy.challenge.login.register.commany_info.Register_next_education;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Resume_education_adapter extends BaseAdapter{
    private List<Resume_education_bean.DataBean> educationlist;
    private Context mContext;
    private int type;
    private Intent  intent = new Intent();
    private Bundle bundle;
    private Resume_education_bean.DataBean educationbean;

    public Resume_education_adapter(List<Resume_education_bean.DataBean> educationlist, int type, Context mContext) {
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
                    convertView = View.inflate(mContext, R.layout.resume_education_adapter,null);
                    view = new HolderView();
                    view.tv_item_school = (TextView) convertView.findViewById(R.id.tv_item_school);
                    view.tv_item_time = (TextView) convertView.findViewById(R.id.tv_item_time);
                    view.ril_item_education = (WaveView) convertView.findViewById(R.id.ril_item_education);
                    convertView.setTag(view);
                    break;
            }
        }else{
            view = (HolderView) convertView.getTag();
        }
        switch (type) {
            case 0:
                view.tv_item_school.setText(educationlist.get(position).getSchool() + "");
                view.tv_item_time.setText(educationlist.get(position).getTime() + "");
                view.ril_item_education.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.setClass(mContext, Register_next_education.class);
                    educationbean = educationlist.get(position);
                    bundle = new Bundle();
                    bundle.putSerializable("educationbean", educationbean);
                    intent.putExtras(bundle);
                    intent.putExtra("pagetype", "update");
                    mContext.startActivity(intent);
                }
                });
                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView tv_item_school,tv_item_time;
        private WaveView ril_item_education;
    }
}
