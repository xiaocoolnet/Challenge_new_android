package com.example.chy.challenge.login.register.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.JobshowDialog;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class Search_child_jobtype extends BaseAdapter{
    private List<String> list;
    private Context mContext;
    private int type;
    private HolderView holderview;
    private int mSelect = 0;//选中项

    public Search_child_jobtype(List<String> list, Context mContext, int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holderview = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.search_diationary_child_adapter, null);
                    holderview = new HolderView();
                    holderview.tv_child_jobtype = (TextView) convertView.findViewById(R.id.tv_child_jobtype);
                    break;

            }
            convertView.setTag(holderview);
        }else{
            holderview = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holderview.tv_child_jobtype.setText(list.get(position)+"");
                if(mSelect==position){
                    holderview.tv_child_jobtype.setBackgroundResource(R.color.white);  //选中项背景
                    holderview.tv_child_jobtype.setTextColor(mContext.getResources().getColor(R.color.green));
                }else{
                    holderview.tv_child_jobtype.setBackgroundResource(R.color.gray6);  //其他项背景
                    holderview.tv_child_jobtype.setTextColor(mContext.getResources().getColor(R.color.black));
                }
                break;
        }
        return convertView;
    }
    class HolderView{
        private TextView tv_child_jobtype;
    }
    public void changeSelected(int positon){ //刷新方法
        if(positon != mSelect){
            mSelect = positon;
            notifyDataSetChanged();
        }
    }
//    public void chiceState(int post)
//    {
//        isChice[post]=isChice[post]==true?false:true;
//        this.notifyDataSetChanged();
//    }
}
