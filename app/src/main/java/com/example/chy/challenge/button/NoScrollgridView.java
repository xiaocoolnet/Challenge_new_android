package com.example.chy.challenge.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by SJL on 2016/11/2.
 */
public class NoScrollgridView extends GridView {
    public NoScrollgridView(Context context, AttributeSet attrs){
        super(context,attrs);

    }
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
