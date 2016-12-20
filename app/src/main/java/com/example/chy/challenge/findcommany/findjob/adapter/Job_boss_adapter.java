package com.example.chy.challenge.findcommany.findjob.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talentbean.FindPersonalBean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.findcommany.findjob.bean.Find_Job_company_bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Job_boss_adapter extends BaseAdapter{
    private Context mContext;
    private List<FindPersonalBean.DataBean.JobsBean> worlist;
    private int type;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();

    public Job_boss_adapter(Context mContext, List<FindPersonalBean.DataBean.JobsBean> worlist, int type) {
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
                    convertView = View.inflate(mContext, R.layout.rezhaozhiwei_item,null);
                    holder =  new HolderView();
                    holder.info_tx = (RoundImageView) convertView.findViewById(R.id.info_tx);//企业图片
                    holder.tv_myjob = (TextView)convertView.findViewById(R.id.tv_myjob);//titlt
                    holder.tv_salary = (TextView)convertView.findViewById(R.id.tv_salary);//工资
                    holder.address =(TextView) convertView.findViewById(R.id.address);//地址
                    holder.experience = (TextView) convertView.findViewById(R.id.experience);//工作经验
                    holder.education = (TextView) convertView.findViewById(R.id.education);//学历
                    holder.work_property = (TextView) convertView.findViewById(R.id.work_property);//全职，兼职
                    holder.realname = (TextView) convertView.findViewById(R.id.realname);//真实姓名
                    holder.tv_jobtype = (TextView) convertView.findViewById(R.id.tv_jobtype);//职位
                    holder.ll_company_click = (LinearLayout)convertView.findViewById(R.id.rl_rezhaozhiwei);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                if (worlist.get(position).getPhoto() != null&&worlist.get(position).getPhoto().length() > 0) {
                    imageLoader.displayImage(NetBaseConstant.NET_HOST + worlist.get(position).getPhoto(), holder.info_tx, options);
                }else{
                    holder.info_tx.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
                }
                holder.tv_myjob.setText(worlist.get(position).getTitle()+"");
                holder.tv_salary.setText(worlist.get(position).getSalary()+"");
                holder.address.setText(worlist.get(position).getCity().substring(0,2)+"");
                holder.experience.setText(worlist.get(position).getExperience()+"");
                holder.education.setText(worlist.get(position).getEducation()+"");
                holder.work_property.setText(worlist.get(position).getWork_property()+"");
                holder.realname.setText(worlist.get(position).getRealname()+"");
                holder.tv_jobtype.setText(worlist.get(position).getMyjob()+"");
//                holder.ll_company_click.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
                break;
        }
        return convertView;
    }
    class HolderView{
        private RoundImageView info_tx;
        private TextView tv_myjob,tv_salary,address,experience,education,work_property,realname,tv_jobtype;
        private LinearLayout ll_company_click;
    }
}
