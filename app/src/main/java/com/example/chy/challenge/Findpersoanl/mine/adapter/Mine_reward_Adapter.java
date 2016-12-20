package com.example.chy.challenge.Findpersoanl.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/11/20 0020.
 */

public class Mine_reward_Adapter extends BaseAdapter{
    private Context mContext;
    private int type;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView != null){
            switch (type){
                case 0:
                    break;
            }
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView) convertView.getTag();
        }
        return convertView;
    }
    class HolderView{

    }
}
