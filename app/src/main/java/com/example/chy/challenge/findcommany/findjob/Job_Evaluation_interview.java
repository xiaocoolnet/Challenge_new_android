package com.example.chy.challenge.findcommany.findjob;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class Job_Evaluation_interview extends Activity{
    private Context mContext;
    private Intent intent;
    private String companyid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mContext = this;
        intent = getIntent();
        companyid = intent.getStringExtra("companyid");
    }
}
