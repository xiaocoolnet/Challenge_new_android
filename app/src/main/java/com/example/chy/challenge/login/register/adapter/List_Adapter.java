package com.example.chy.challenge.login.register.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.adapter.Find_Personal_Fragment_Adapter;
import com.example.chy.challenge.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class List_Adapter extends BaseAdapter{
    private List<String> list;
    private Context mContext;
    private int type;

    public List_Adapter(List<String> list, Context mContext,int type) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderview = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.string_list_item, null);
                    holderview = new HolderView();
                    holderview.tvlist = (TextView) convertView.findViewById(R.id.tv_list_item);
                    break;

            }
            convertView.setTag(holderview);
        }else{
            holderview = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                holderview.tvlist.setText(list.get(position)+"");
                break;
        }
        return convertView;
    }
    class HolderView{
       private TextView tvlist;
    }
}
