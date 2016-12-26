package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by SJL on 2016/12/23.
 */

public class MsgAdapter extends ArrayAdapter<MsgBeans.DataBean> {
    private int resourceId;
    private Context context;
    private List<MsgBeans.DataBean> msgBeans;
    public MsgAdapter(Context context,int resourc, List<MsgBeans.DataBean> msgBeans){
        super(context,resourc,msgBeans);
        resourceId = resourc;
        this.context = context;
        this.msgBeans = msgBeans;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MsgBeans.DataBean msgList = msgBeans.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            viewHolder.tv_name.setText(msgList.getMy_nickname());
            viewHolder.tv_con.setText(msgList.getLast_content());
            viewHolder.tv_creat_time.setText(msgList.getCreate_time());
            
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tv_name.setText(msgList.getMy_nickname());
            viewHolder.tv_con.setText(msgList.getLast_content());
            viewHolder.tv_creat_time.setText(msgList.getCreate_time());
        }
        return convertView;

    }
    public class ViewHolder{
        ImageView iv_tx;
        TextView tv_name,tv_con,tv_creat_time;

        public ViewHolder(View view){
            iv_tx = (ImageView)view.findViewById(R.id.msg_tx_c);
            tv_name = (TextView)view.findViewById(R.id.tv_name_c);
            tv_con = (TextView)view.findViewById(R.id.tv_con);
            tv_creat_time = (TextView)view.findViewById(R.id.tv_creat_time);
        }

    }
}
