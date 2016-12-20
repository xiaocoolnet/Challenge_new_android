package com.example.chy.challenge.findcommany.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.bean.Black_personal_bean;
import com.example.chy.challenge.findcommany.mine.bean.Submit_Resume_bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Submit_Resume_Adapter extends BaseAdapter{
    private List<Submit_Resume_bean.DataBean> blacklist;
    private Context mContext;
    private int type;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();

    public Submit_Resume_Adapter(List<Submit_Resume_bean.DataBean> blacklist, Context mContext, int type) {
        this.blacklist = blacklist;
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    public int getCount() {
        return blacklist.size();
    }

    @Override
    public Object getItem(int position) {
        return blacklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.submit_resume, null);
                    holder = new HolderView();
                    holder.work_position = (TextView) convertView.findViewById(R.id.company_work_position);//职位
//                    holder.company_name = (TextView) convertView.findViewById(R.id.company_tv_name);//公司名称
                    holder.adapter_head_image = (RoundImageView) convertView.findViewById(R.id.adapter_head_image);//logo
                    holder.company_tv_manager = (TextView) convertView.findViewById(R.id.company_tv_manager);//公司经理名字
                    holder.manager_position = (TextView) convertView.findViewById(R.id.company_manager_position);//招聘人职位
                    holder.company_tv_salary = (TextView) convertView.findViewById(R.id.company_tv_salary);//薪资
                    holder.company_tv_count = (TextView) convertView.findViewById(R.id.company_tv_count);//公司规模
                    holder.location_city = (TextView) convertView.findViewById(R.id.adapter_location_city);//城市
                    holder.jobtime_worklife = (TextView) convertView.findViewById(R.id.adapter_jobtime_worklife);//工作年限
                    holder.personal_degree = (TextView) convertView.findViewById(R.id.adapter_personal_degree);//学历
                    holder.work_property = (TextView) convertView.findViewById(R.id.adapter_work_property);//全职，兼职
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holder.work_position.setText(blacklist.get(position).getApplys().getTitle()+"");
//                holder.company_name.setText(blacklist.get(position).getCompany_name());
                if (blacklist.get(position).getApplys().getLogo() != null&&blacklist.get(position).getApplys().getLogo().length() > 0) {
                    imageLoader.displayImage(NetBaseConstant.NET_HOST + blacklist.get(position).getApplys().getLogo(), holder.adapter_head_image, options);
                }else{
                    holder.adapter_head_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
                }
                holder.company_tv_manager.setText(blacklist.get(position).getApplys().getRealname()+"");
                holder.manager_position.setText(blacklist.get(position).getApplys().getMyjob()+"");
                holder.company_tv_salary.setText(blacklist.get(position).getApplys().getSalary()+"");
                holder.company_tv_count.setText(blacklist.get(position).getApplys().getCount()+"");
                if (blacklist.get(position).getApplys().getCity() != null&&blacklist.get(position).getApplys().getCity().length() > 0){
                    holder.location_city.setText(blacklist.get(position).getApplys().getCity().substring(0,2)+"");
                }else{
                    holder.location_city.setText("");
                }
                holder.jobtime_worklife.setText(blacklist.get(position).getApplys().getExperience()+"");
                holder.personal_degree.setText(blacklist.get(position).getApplys().getEducation()+"");
                holder.work_property.setText(blacklist.get(position).getApplys().getWork_property()+"");
                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView work_position,company_name,company_tv_manager,manager_position,company_tv_salary,company_tv_count
                ,location_city,jobtime_worklife,personal_degree,work_property;
        private RoundImageView adapter_head_image;
    }
}
