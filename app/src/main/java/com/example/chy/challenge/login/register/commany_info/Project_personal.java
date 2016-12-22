package com.example.chy.challenge.login.register.commany_info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chy.challenge.R;
import com.example.chy.challenge.button.Public_static_all;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfo;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Project_personal extends Activity implements View.OnClickListener{
    private Context mContext;
    private WaveView back,submit_mine_advantage;
    private TextView title_top,look_other_personal,txtlength;
    private UserInfo info;
    private EditText advantage_content;
    private Intent intent;
    private String decrib;
    private int num = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_myadvantage);
        mContext = this;
        info = new UserInfo(mContext);
        getview();
        intent = getIntent();
        decrib = intent.getStringExtra("decrib");
        if (decrib != null&&decrib.length() > 0){
            advantage_content.setText(decrib);
        }else{
            advantage_content.setHint("请输入项目描述，1-150字");
        }
    }

    private void getview() {
        back = (WaveView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title_top = (TextView) findViewById(R.id.title_top);
        title_top.setText("项目描述");
        submit_mine_advantage = (WaveView) findViewById(R.id.submit_mine_advantage);
        submit_mine_advantage.setOnClickListener(this);
        advantage_content = (EditText) findViewById(R.id.advantage_content);
        look_other_personal = (TextView) findViewById(R.id.look_other_personal);
        look_other_personal.setOnClickListener(this);
        txtlength = (TextView) findViewById(R.id.txtlength);
        advantage_content.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
                System.out.println("s="+s);
            }
            @Override
            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                txtlength.setText("" + number);
                selectionStart = advantage_content.getSelectionStart();
                selectionEnd = advantage_content.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    advantage_content.setText(s);
                    advantage_content.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.submit_mine_advantage:
                if (advantage_content.getText().toString() != null&&advantage_content.getText().toString().length() > 0) {
                    Public_static_all.idprojectnamed = true;
                    info.setProjectdescrip(advantage_content.getText().toString());
                    finish();
                }else{
                    Toast.makeText(mContext, "请填写项目描述", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.look_other_personal:
                Toast.makeText(mContext, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
