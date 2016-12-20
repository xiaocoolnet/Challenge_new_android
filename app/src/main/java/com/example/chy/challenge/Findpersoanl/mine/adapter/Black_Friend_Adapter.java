package com.example.chy.challenge.Findpersoanl.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.Black_friend_bean;
import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.adapter.Find_Personal_Fragment_Adapter;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Black_Friend_Adapter extends BaseAdapter{
    private List<Black_friend_bean.DataBean> blacklist;
    private Context mContext;
    private int type;
    private Handler handler;

    public Black_Friend_Adapter(List<Black_friend_bean.DataBean> blacklist, Context mContext, int type,Handler handler) {
        this.blacklist = blacklist;
        this.mContext = mContext;
        this.type = type;
        this.handler = handler;
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
                    convertView = View.inflate(mContext, R.layout.black_friend_adapter, null);
                    holder = new HolderView();
                    holder.black_cancel = (WaveView) convertView.findViewById(R.id.black_cancel);
                    holder.black_realname = (TextView) convertView.findViewById(R.id.black_realname);
                    holder.black_position = (TextView) convertView.findViewById(R.id.black_position);
                    holder.location_city = (TextView) convertView.findViewById(R.id.adapter_location_city);
                    holder.jobtime_worklife = (TextView) convertView.findViewById(R.id.adapter_jobtime_worklife);
                    holder.personal_degree = (TextView) convertView.findViewById(R.id.adapter_personal_degree);
                    holder.work_property = (TextView) convertView.findViewById(R.id.adapter_work_property);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holder.black_realname.setText(blacklist.get(position).getBlacks().getRealname()+"");
                if (blacklist.get(position).getBlacks().getWork().get(0).getCompany_industry() != null &&blacklist.get(position).getBlacks().getWork().get(0).getCompany_industry().length() > 0) {
                    holder.black_position.setText(blacklist.get(position).getBlacks().getWork().get(0).getCompany_industry());
                }else{
                    holder.black_position.setText("无");
                }
                holder.location_city.setText(blacklist.get(position).getBlacks().getCity()+"");
                holder.jobtime_worklife.setText(blacklist.get(position).getBlacks().getWork_life()+"");
                if (blacklist.get(position).getBlacks().getEducation().get(0).getDegree() != null &&blacklist.get(position).getBlacks().getEducation().get(0).getDegree().length() > 0) {
                    holder.personal_degree.setText(blacklist.get(position).getBlacks().getEducation().get(0).getDegree());
                }else{
                    holder.personal_degree.setText("无");
                }
                holder.work_property.setText(blacklist.get(position).getBlacks().getWork_property()+"");
                holder.black_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = position;
                        handler.sendMessage(msg);
                    }
                });
                break;
        }
        return convertView;
    }
    class HolderView{
        private WaveView black_cancel;//取消
        private TextView black_realname,black_position,location_city,jobtime_worklife,personal_degree,work_property;
    }
}
