package com.example.chy.challenge.findcommany.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.Black_friend_bean;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.bean.Black_personal_bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Black_personal_Adapter extends BaseAdapter{
    private List<Black_personal_bean.DataBean.BlacksBean> blacklist;
    private Context mContext;
    private int type;
    private Handler handler;

    public Black_personal_Adapter(List<Black_personal_bean.DataBean.BlacksBean> blacklist, Context mContext, int type, Handler handler) {
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
                    convertView = View.inflate(mContext, R.layout.black_personal_adapter, null);
                    holder = new HolderView();
                    holder.black_cancel = (WaveView) convertView.findViewById(R.id.black_cancel);//取消黑名单
                    holder.black_companyname = (TextView) convertView.findViewById(R.id.black_companyname);//公司名称
                    holder.black_companytype = (TextView) convertView.findViewById(R.id.black_companytype);//公司类型
                    holder.adapter_location_city = (TextView) convertView.findViewById(R.id.adapter_location_city);//公司所在城市
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holder.black_companyname.setText(blacklist.get(position).getCompany_name()+"");
                holder.black_companytype.setText(blacklist.get(position).getIndustry()+"");
                holder.adapter_location_city.setText(blacklist.get(position).getCompany_name()+"");

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
        private TextView black_companyname,black_companytype,adapter_location_city,jobtime_worklife,personal_degree,work_property;
    }
}
