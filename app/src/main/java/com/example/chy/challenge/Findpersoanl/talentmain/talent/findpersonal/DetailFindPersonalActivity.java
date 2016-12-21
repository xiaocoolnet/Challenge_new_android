package com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.example.chy.challenge.R;

public class DetailFindPersonalActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_find_personal);
    }
}
