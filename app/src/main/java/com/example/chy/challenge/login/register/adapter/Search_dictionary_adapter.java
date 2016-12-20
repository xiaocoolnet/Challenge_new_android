package com.example.chy.challenge.login.register.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chy.challenge.R;
import com.example.chy.challenge.login.register.bean.Search_dictionary;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class Search_dictionary_adapter extends BaseAdapter{
    private List<Search_dictionary.DataBean> dictionarylist;
    private Context mContext;
    private int type;

    public Search_dictionary_adapter(List<Search_dictionary.DataBean> dictionarylist, Context mContext, int type) {
        this.dictionarylist = dictionarylist;
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    public int getCount() {
        return dictionarylist.size();
    }

    @Override
    public Object getItem(int position) {
        return dictionarylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdview = null;
        if (convertView == null){
            switch (type){
                case 0:
                    convertView = View.inflate(mContext, R.layout.search_dictionary_adapter,null);
                    holdview = new HoldView();
                    holdview.search_tv_title = (TextView) convertView.findViewById(R.id.search_tv_title);
                    holdview.search_tv_description = (TextView) convertView.findViewById(R.id.search_tv_description);
                    break;
            }
            convertView.setTag(holdview);
        }else{
            holdview = (HoldView) convertView.getTag();
        }
        switch (type){
            case 0:
                holdview.search_tv_title.setText(dictionarylist.get(position).getName()+"");
                holdview.search_tv_description.setText(dictionarylist.get(position).getDescription()+"");
                break;
        }
        return convertView;
    }
    class HoldView{
        private TextView search_tv_title,search_tv_description;
    }
}
