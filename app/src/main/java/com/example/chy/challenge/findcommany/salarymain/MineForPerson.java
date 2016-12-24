package com.example.chy.challenge.findcommany.salarymain;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chy.challenge.Findpersoanl.mine.MyCollectActivity;
import com.example.chy.challenge.Findpersoanl.mine.Set_Up;
import com.example.chy.challenge.R;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.findcommany.mine.Black_personal_Friend;
import com.example.chy.challenge.findcommany.mine.Submit_Resume;

/**
 * Created by 77588 on 2016/9/1.
 */
public class MineForPerson extends Fragment implements View.OnClickListener{
    private WaveView personal_collection,personal_reward,personal_record,mine_black_friend,company_setting;
    private Context mContext;
    private View rootView;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mine_person, container, false);
        mContext = getActivity();
        initview();
        return rootView;
    }

    private void initview() {
        personal_collection = (WaveView) rootView.findViewById(R.id.mine_personal_collection);//我的收藏
        personal_collection.setOnClickListener(this);

        personal_reward = (WaveView) rootView.findViewById(R.id.ming_personal_reward);//我的奖金
        personal_reward.setOnClickListener(this);

        personal_record = (WaveView) rootView.findViewById(R.id.mine_personal_record);//我的投递记录
        personal_record.setOnClickListener(this);

        mine_black_friend = (WaveView) rootView.findViewById(R.id.mine_black_friend);//我的黑名单
        mine_black_friend.setOnClickListener(this);

        company_setting = (WaveView) rootView.findViewById(R.id.mine_company_setting);//设置
        company_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mine_personal_collection://我的收藏
                intent = new Intent(mContext, MyCollectActivity.class);
                startActivity(intent);
                break;
            case R.id.ming_personal_reward://我的奖金
                Toast.makeText(mContext,"暂未开放此功能",Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_personal_record://我的投递记录
                intent = new Intent(mContext, Submit_Resume.class);
                startActivity(intent);
                break;
            case R.id.mine_black_friend://我的黑名单
                intent = new Intent(mContext, Black_personal_Friend.class);
                startActivity(intent);
                break;
            case R.id.mine_company_setting:
                intent = new Intent(mContext, Set_Up.class);
                intent.putExtra("usertype","personal");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

