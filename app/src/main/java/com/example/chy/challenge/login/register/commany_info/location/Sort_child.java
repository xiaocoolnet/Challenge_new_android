package com.example.chy.challenge.login.register.commany_info.location;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class Sort_child extends BaseAdapter{
    private Context mContext;
    private List<String> list;
    private int type;

    public Sort_child(Context mContext, List<String> list,int type) {
        this.mContext = mContext;
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView view = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.item_group_gridview,null);
                    view = new HolderView();
                    view.tvtitle = (TextView) convertView.findViewById(R.id.title);
                    break;
            }
            convertView.setTag(view);
        }else{
            view = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                view.tvtitle.setText(list.get(position));
            break;
        }
        return convertView;
    }
    class HolderView{
        private TextView tvtitle;
    }
}
