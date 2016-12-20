package com.example.chy.challenge.Findpersoanl.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.Invitation_wait_bean;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class InVitation_wait_Adapter extends BaseAdapter{
    private int type;
    private Context mContext;
    private List<Invitation_wait_bean.DataBean> invationlist;

    public InVitation_wait_Adapter(int type, Context mContext, List<Invitation_wait_bean.DataBean> invationlist) {
        this.type = type;
        this.mContext = mContext;
        this.invationlist = invationlist;
    }

    @Override
    public int getCount() {
        return invationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return invationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext,R.layout.invitation_wait_adapter,null);
                    holderView = new HolderView();
                    holderView.invitation_realname = (TextView) convertView.findViewById(R.id.invitation_realname);
                    holderView.invitation_personal = (TextView) convertView.findViewById(R.id.invitation_personal);
                    holderView.invitation_action = (TextView) convertView.findViewById(R.id.invitation_action);
                    holderView.invitation_time = (TextView) convertView.findViewById(R.id.invitation_time);
                    holderView.invatation_location = (TextView) convertView.findViewById(R.id.invatation_location);
                    holderView.invitation_sex = (ImageView) convertView.findViewById(R.id.invitation_sex);
                    holderView.image_head = (RoundImageView) convertView.findViewById(R.id.image_head);

                    break;
            }
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holderView.invitation_realname.setText(invationlist.get(position).getRealname()+"");
                holderView.invitation_personal.setText(invationlist.get(position).getJobtype()+"");
                holderView.invitation_action.setText(invationlist.get(position).getJobtype()+"");//面试邀请
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"+"\t\t\t"+"hh:mm");
                long time = Long.parseLong(invationlist.get(position).getCreate_time()+"");
                holderView.invitation_time.setText(sDateFormat.format(new Date(time * 1000)));//时间
                holderView.invatation_location.setText(invationlist.get(position).getAddress()+"");//地理位置
                if ("0".equals(invationlist.get(position).getSex())){
                    holderView.invitation_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_nvsheng));
                }else if ("1".equals(invationlist.get(position).getSex())){
                    holderView.invitation_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_nansheng));
                }
                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView invitation_realname,invitation_personal,invitation_action,invitation_time,invatation_location;//姓名,移动产品经理,面试邀请,时间,地理位置
        private ImageView invitation_sex;//性别图片
        private RoundImageView image_head;//头像
    }
}
