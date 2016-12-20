package com.example.chy.challenge.findcommany.findjob.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.findcommany.findjob.bean.Find_Job_company_bean;
import com.example.chy.challenge.findcommany.findwork.bean.Find_work_bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Find_Job_comapny_adapter extends BaseAdapter{
    private Context mContext;
    private List<Find_Job_company_bean.DataBean> worlist;
    private int type;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();

    public Find_Job_comapny_adapter(Context mContext, List<Find_Job_company_bean.DataBean> worlist, int type) {
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
                    convertView = View.inflate(mContext, R.layout.activity_new_company_item,null);
                    holder =  new HolderView();
                    holder.iv_logo = (ImageView)convertView.findViewById(R.id.iv_logo);//企业图片
                    holder.tv_company_name = (TextView)convertView.findViewById(R.id.tv_company_name);//企业名字
                    holder.tv_count = (TextView)convertView.findViewById(R.id.tv_company_count);//企业发布职位总数
                    holder.tv_industry =(TextView) convertView.findViewById(R.id.tv_industry);//企业类别
                    holder.tv_rongzi = (TextView) convertView.findViewById(R.id.tv_rongzi);//企业融资
                    holder.textView6 = (TextView) convertView.findViewById(R.id.textView6);//企业规模
                    holder.company_image = (ImageView) convertView.findViewById(R.id.company_image);//企业图片
                    holder.ll_company_click = (LinearLayout)convertView.findViewById(R.id.ll_company_click);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                if (worlist.get(position).getLogo() != null&&worlist.get(position).getLogo().length() > 0) {
                    imageLoader.displayImage(NetBaseConstant.NET_HOST + worlist.get(position).getLogo(), holder.iv_logo, options);
                }else{
                    holder.iv_logo.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
                }
                holder.tv_company_name.setText(worlist.get(position).getCompany_name()+"");
                if (worlist.get(position).getJobs() != null&&worlist.get(position).getJobs().size() > 0) {
                    holder.tv_count.setText(worlist.get(position).getJobs().size() + "");
                }else{
                    holder.tv_count.setText("");
                }
                holder.tv_industry.setText(worlist.get(position).getIndustry()+"");
                holder.tv_rongzi.setText(worlist.get(position).getFinancing()+"");
                holder.textView6.setText(worlist.get(position).getCount()+"");
//                if (worlist.get(position).getLogo() != null&&worlist.get(position).getLogo().length() > 0) {
//                    imageLoader.displayImage(NetBaseConstant.NET_HOST + worlist.get(position).getLogo(), holder.iv_logo, options);
//                }else{
//                    holder.iv_logo.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
//                }
                //企业图片
                break;
        }
        return convertView;
    }
    class HolderView{
        ImageView iv_logo,company_image;
        TextView tv_company_name,tv_count,tv_industry,tv_rongzi,textView6;
        LinearLayout ll_company_click;
    }
}
