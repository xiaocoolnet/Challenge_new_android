package com.example.chy.challenge.findcommany.chance;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class Search_company extends Activity implements View.OnClickListener{
    private WaveView cancel;
    private EditText search_editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_company);
        getview();
    }

    private void getview() {
        cancel = (WaveView) findViewById(R.id.search_cancel);
        cancel.setOnClickListener(this);
        search_editText = (EditText) findViewById(R.id.search_editText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_cancel:
                finish();
                break;

        }
    }
}
