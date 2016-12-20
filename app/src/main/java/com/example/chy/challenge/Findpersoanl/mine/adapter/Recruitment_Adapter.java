package com.example.chy.challenge.Findpersoanl.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Recruitment_Adapter extends BaseAdapter {
    private List<FindPersonalBean.DataBean.JobsBean> findbjlist;
    private Context mContext;
    private int type;

    public Recruitment_Adapter(List<FindPersonalBean.DataBean.JobsBean> findbjlist, Context mContext, int type) {
        this.findbjlist = findbjlist;
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    public int getCount() {
        return findbjlist.size();
    }

    @Override
    public Object getItem(int position) {
        return findbjlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = View.inflate(mContext, R.layout.recruitment_adapter, null);
                    holderView = new HolderView();
                    holderView.commany_position = (TextView) convertView.findViewById(R.id.commany_position);
                    holderView.commany_salary = (TextView) convertView.findViewById(R.id.commany_salary);
                    holderView.location_city = (TextView) convertView.findViewById(R.id.adapter_location_city);
                    holderView.jobtime_worklife = (TextView) convertView.findViewById(R.id.adapter_jobtime_worklife);
                    holderView.personal_degree = (TextView) convertView.findViewById(R.id.adapter_personal_degree);
                    holderView.work_property = (TextView) convertView.findViewById(R.id.adapter_work_property);
                    break;
            }
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                if (findbjlist.size() > 0&&findbjlist != null) {
                    holderView.commany_position.setText(findbjlist.get(position).getTitle() + "");
                    holderView.commany_salary.setText(findbjlist.get(position).getSalary() + "");
                    String city = new String(findbjlist.get(position).getCity());
                    String[]  destString = city.split("-");
                    holderView.location_city.setText(destString[0] + "");
                    holderView.jobtime_worklife.setText(findbjlist.get(position).getExperience() + "");
                    holderView.personal_degree.setText(findbjlist.get(position).getEducation() + "");
                    if (findbjlist.get(position).getWork_property() != null&&findbjlist.get(position).getWork_property().length() > 0){
                        holderView.work_property.setText(findbjlist.get(position).getWork_property() + "");
                    }else{
                        holderView.work_property.setText("\t\t\t\t");
                    }

                }
                break;
        }
        return convertView;
    }

    class HolderView {
        private TextView commany_position, commany_salary, location_city, jobtime_worklife, personal_degree, work_property;
    }
}
