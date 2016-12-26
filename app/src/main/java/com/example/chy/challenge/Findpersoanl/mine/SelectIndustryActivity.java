package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.Dictionary;
import com.example.chy.challenge.NetInfo.UserNetConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.JsonResult;
import com.example.chy.challenge.Utils.ToastUtil;
import com.example.chy.challenge.Utils.VolleyUtil;
import com.example.chy.challenge.button.WaveView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectIndustryActivity extends Activity {

    @BindView(R.id.back)
    WaveView back;
    @BindView(R.id.fl_industry)
    TagFlowLayout flIndustry;
    @BindView(R.id.btn_cannot_found)
    TextView btnCannotFound;

    private Context context;
    private List<Dictionary> industries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_industry);
        ButterKnife.bind(this);
        context = this;
        industries = new ArrayList<>();
        getData();

    }

    /**
     * 获取公司行业
     */
    private void getData() {
        String url = UserNetConstant.GETDICTIONARYLIST + "&parentid=67";
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context, result)) {
                    industries = getBeanFromJson(result);
                    setAdapter();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 设置数据
     */
    private void setAdapter() {
        final ArrayList<String> datas = new ArrayList<>();
        if(industries.size()>0){
            for(int i=0;i<industries.size();i++){
                datas.add(industries.get(i).getName());
            }
        }
        flIndustry.setAdapter(new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.layout, flIndustry, false);
                tv.setText(s);
                return tv;
            }
        });
        flIndustry.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent();
                intent.putExtra("value",datas.get(position));
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        });


    }

    @OnClick({R.id.back, R.id.btn_cannot_found})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_cannot_found:
                showDialog();
                break;
        }
    }

    /**
     * 弹出输入框
     */
    private void showDialog() {
        final Dialog dialog = new Dialog(context,R.style.DialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_tag,null);
        final EditText ed_content = (EditText) view.findViewById(R.id.ed_content);
        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        TextView btn_confirm = (TextView) view.findViewById(R.id.btn_confirm);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.rounded_gray);
        dialog.setCancelable(false);
        dialog.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = ed_content.getText().toString();
                if(content.length() == 0){
                    ToastUtil.showShort(context,"输入的内容不能为空");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("value",content);
                setResult(RESULT_OK, intent);
                dialog.dismiss();
                finish();
            }
        });
    }

    private List<Dictionary> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<Dictionary>>() {
        }.getType());
    }
}
