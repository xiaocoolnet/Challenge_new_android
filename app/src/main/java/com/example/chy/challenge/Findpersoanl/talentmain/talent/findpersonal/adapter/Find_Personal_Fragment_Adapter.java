package com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;
import com.example.chy.challenge.login.register.adapter.List_Adapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static com.example.chy.challenge.NetInfo.NetBaseConstant.NET_BASE_HOST;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class Find_Personal_Fragment_Adapter extends BaseAdapter{
    private Context mContext;
    private List<FindPersonal_fragment_bean.DataBean> findlist;
    private int type;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_wode).showImageOnFail(R.mipmap.ic_wode).cacheInMemory(true).cacheOnDisc(true).build();

    public Find_Personal_Fragment_Adapter(Context mContext, List<FindPersonal_fragment_bean.DataBean> findlist,int type) {
        this.mContext = mContext;
        this.findlist = findlist;
        this.type = type;
    }

    @Override
    public int getCount() {
        return findlist.size();
    }

    @Override
    public Object getItem(int position) {
        return findlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderview = null;
        if (convertView == null){
             switch (type){
                 case 0:
                     convertView = View.inflate(mContext, R.layout.find_personal_adapter, null);
                     holderview = new HolderView();
                     holderview.realname = (TextView) convertView.findViewById(R.id.adapter_realname);//真实姓名
                     holderview.late_ornowe = (TextView) convertView.findViewById(R.id.adapter_late_ornowe);//现任/曾任
                     holderview.myjob = (TextView) convertView.findViewById(R.id.adapter_myjob);//现任职位
                     holderview.company = (TextView) convertView.findViewById(R.id.adapter_company);//现任公司
                     holderview.salary = (TextView) convertView.findViewById(R.id.adapter_salary);//期待薪资
                     holderview.personal_profile = (TextView) convertView.findViewById(R.id.adapter_personal_profile);//个人简介
                     holderview.location_city = (TextView) convertView.findViewById(R.id.adapter_location_city);//所在城市
                     holderview.jobtime_worklife = (TextView) convertView.findViewById(R.id.adapter_jobtime_worklife);//工作年限
                     holderview.personal_degree = (TextView) convertView.findViewById(R.id.adapter_personal_degree);//学历
                     holderview.adapter_work_property = (TextView) convertView.findViewById(R.id.adapter_work_property);//全职
                     holderview.realname_sex = (ImageView) convertView.findViewById(R.id.adapter_realname_sex);//男女
                     holderview.position_type = (TextView) convertView.findViewById(R.id.adapter_position_type);//网页设计
                     holderview.head_image = (RoundImageView) convertView.findViewById(R.id.adapter_head_image);//个人头像
                     break;
             }
            convertView.setTag(holderview);
        }else{
            holderview = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holderview.realname.setText(findlist.get(position).getRealname()+"");
                if ("离职".equals(findlist.get(position).getJobstate())) {
                    holderview.late_ornowe.setText("曾任");
                }else{
                    holderview.late_ornowe.setText("现任");
                }
                holderview.myjob.setText(findlist.get(position).getMyjob()+"");
                holderview.company.setText(findlist.get(position).getCompany()+"");
                holderview.salary.setText(findlist.get(position).getWantsalary()+"");
                holderview.personal_profile.setText(findlist.get(position).getAdvantage()+"");
                holderview.location_city.setText(findlist.get(position).getCity()+"");
                holderview.jobtime_worklife.setText(findlist.get(position).getWork_life()+"");
                if (findlist.get(position).getEducation() != null &&findlist.get(position).getEducation().size() > 0) {
                    holderview.personal_degree.setText(findlist.get(position).getEducation().get(0).getDegree() + "");
                }else{
                    holderview.personal_degree.setText("");
                }
                holderview.adapter_work_property.setText(findlist.get(position).getWork_property()+"");
                if ("0".equals(findlist.get(position).getSex())){
                    holderview.realname_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_nvsheng));
                }else if ("1".equals(findlist.get(position).getSex())){
                    holderview.realname_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_nansheng));
                }
                holderview.position_type.setText(findlist.get(position).getPosition_type()+"");
                if (findlist.get(position).getPhoto() != null&&findlist.get(position).getPhoto().length() > 0) {

                    imageLoader.displayImage(NetBaseConstant.NET_HOST + findlist.get(position).getPhoto(), holderview.head_image, options);
                }else{
                    holderview.head_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_wode));
                }
                break;
        }
        return convertView;
    }
   class HolderView{
        private TextView realname,late_ornowe,myjob,company,salary,personal_profile,location_city
                ,jobtime_worklife,personal_degree,adapter_work_property;//简历个人名称,现任/曾任,职位,公司名称，工资,个人简介,所在城市,工作时间,个人学历,全职
       private ImageView realname_sex;//性别图片
       private TextView position_type;//网页设计师
       private RoundImageView head_image;//个人头像
   }
}
