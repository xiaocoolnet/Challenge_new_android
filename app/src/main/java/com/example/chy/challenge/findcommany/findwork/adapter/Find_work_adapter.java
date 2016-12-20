package com.example.chy.challenge.findcommany.findwork.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.findcommany.findwork.bean.Find_work_bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Find_work_adapter extends BaseAdapter{
    private Context mContext;
    private List<Find_work_bean.DataBean> worlist;
    private int type;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();

    public Find_work_adapter(Context mContext, List<Find_work_bean.DataBean> worlist, int type) {
        this.mContext = mContext;
        this.worlist = worlist;
        this.type = type;
    }

    @Override
    public int getCount() {
        return worlist.size();
    }

    @Override
    public Object getItem(int position) {
        return worlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.find_company_adapter,null);
                    holder =  new HolderView();
                    holder.work_position = (TextView) convertView.findViewById(R.id.company_work_position);//职位
                    holder.company_name = (TextView) convertView.findViewById(R.id.company_tv_name);//公司名称
                    holder.adapter_head_image = (RoundImageView) convertView.findViewById(R.id.adapter_head_image);//logo
                    holder.company_tv_manager = (TextView) convertView.findViewById(R.id.company_tv_manager);//公司经理名字
                    holder.manager_position = (TextView) convertView.findViewById(R.id.company_manager_position);//招聘人职位
                    holder.company_tv_salary = (TextView) convertView.findViewById(R.id.company_tv_salary);//薪资
                    holder.company_tv_count = (TextView) convertView.findViewById(R.id.company_tv_count);//公司规模
                    holder.company_tv_score = (TextView) convertView.findViewById(R.id.company_tv_score);//公司评分
                    holder.company_tv_distance = (TextView) convertView.findViewById(R.id.company_tv_distance);//距离1.1km
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
                holder.work_position.setText(worlist.get(position).getTitle()+"");
                holder.company_name.setText(worlist.get(position).getCompany_name());
                if (worlist.get(position).getLogo() != null&&worlist.get(position).getLogo().length() > 0) {
                    imageLoader.displayImage(NetBaseConstant.NET_HOST + worlist.get(position).getLogo(), holder.adapter_head_image, options);
                }else{
                    holder.adapter_head_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
                }
                holder.company_tv_manager.setText(worlist.get(position).getRealname()+"");
                holder.manager_position.setText(worlist.get(position).getMyjob()+"");
                holder.company_tv_salary.setText(worlist.get(position).getSalary()+"");
                holder.company_tv_count.setText(worlist.get(position).getCount()+"");
                holder.company_tv_score.setText(worlist.get(position).getCompany_score()+"");
                holder.company_tv_distance.setText(worlist.get(position).getDistance()+"");
                holder.location_city.setText(worlist.get(position).getCity().substring(0,2)+"");
                holder.jobtime_worklife.setText(worlist.get(position).getExperience()+"");
                holder.personal_degree.setText(worlist.get(position).getEducation()+"");
                holder.work_property.setText(worlist.get(position).getWork_property()+"");
                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView work_position,company_name,company_tv_manager,manager_position,company_tv_salary,company_tv_count
                ,company_tv_score,company_tv_distance,location_city,jobtime_worklife,personal_degree,work_property;
        private RoundImageView adapter_head_image;
    }
}
