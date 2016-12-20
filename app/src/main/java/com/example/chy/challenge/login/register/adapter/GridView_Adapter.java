package com.example.chy.challenge.login.register.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.JobshowDialog;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class GridView_Adapter extends BaseAdapter{
    private List<String> list;
    private Context mContext;
    private int type;
    private boolean isChice[];
    private HolderView holderview;
    private String[] gridviewitem;

    public GridView_Adapter(List<String> list, Context mContext, int type,String[] gridviewitem) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;
        this.gridviewitem = gridviewitem;
        isChice=new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            isChice[i]=false;
        }
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
                    convertView = View.inflate(mContext, R.layout.griview_adapter_item, null);
                    holderview = new HolderView();
                    holderview.tvlist = (CheckBox) convertView.findViewById(R.id.tv_list_item);
                    holderview.add_tv_list_item = (TextView) convertView.findViewById(R.id.add_tv_list_item);
                    break;

            }
            convertView.setTag(holderview);
        }else{
            holderview = (HolderView) convertView.getTag();
        }
        switch (type){
            case 0:
                if (position == list.size()-1){
                    holderview.tvlist.setVisibility(View.GONE);
                    holderview.add_tv_list_item.setVisibility(View.VISIBLE);
                    holderview.add_tv_list_item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final JobshowDialog.Builder builder = new JobshowDialog.Builder(mContext);
//                            builder.setMessage("输入您的技能标签");
                            builder.setTitle("输入您的技能标签");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    if (builder.et_content() != null&&builder.et_content().length() > 0 ){
                                        Toast.makeText(mContext,builder.et_content() , Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(mContext, "111", Toast.LENGTH_SHORT).show();
                                    }
                                    //设置你的操作事项
                                }
                            });

                            builder.setNegativeButton("取消",
                                    new android.content.DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            builder.create().show();
                        }
                    });
                }else {
                    holderview.tvlist.setText(list.get(position) + "");
                    if ("1".equals(gridviewitem[position])) {
                        holderview.tvlist.setChecked(true);
                    } else if ("0".equals(gridviewitem[position])) {
                        holderview.tvlist.setChecked(false);
                    }
                }
                break;
        }
        return convertView;
    }
    class HolderView{
       private CheckBox tvlist;
        private TextView add_tv_list_item;
    }
//    public void chiceState(int post)
//    {
//        isChice[post]=isChice[post]==true?false:true;
//        this.notifyDataSetChanged();
//    }
}
