package com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.talentmain.talent.findpersonal.bean.FindPersonal_fragment_bean;
import com.example.chy.challenge.NetInfo.NetBaseConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.CommonAdapter;
import com.example.chy.challenge.Utils.ImgLoadUtil;
import com.example.chy.challenge.Utils.NoScrollListView;
import com.example.chy.challenge.Utils.ViewHolder;
import com.example.chy.challenge.button.RevealButton;
import com.example.chy.challenge.button.WaveView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailFindPersonalActivity extends FragmentActivity {

    @BindView(R.id.back)
    WaveView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_work_life)
    TextView tvWorkLife;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.tv_work_property)
    TextView tvWorkProperty;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_position_type)
    TextView tvPositionType;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.tv_categories)
    TextView tvCategories;
    @BindView(R.id.lv_education)
    NoScrollListView lvEducation;
    @BindView(R.id.tv_job_company)
    TextView tvJobCompany;
    @BindView(R.id.tv_job_time)
    TextView tvJobTime;
    @BindView(R.id.tv_job_type)
    TextView tvJobType;
    @BindView(R.id.fl_job_skill)
    TagFlowLayout flJobSkill;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_project_description)
    TextView tvProjectDescription;
    @BindView(R.id.tv_advantage)
    TextView tvAdvantage;
    @BindView(R.id.btn_get_contact)
    RevealButton btnGetContact;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.qiuzhiyixiang)
    TextView qiuzhiyixiang;
    @BindView(R.id.ll_bottom1)
    LinearLayout llBottom1;
    @BindView(R.id.btn_phone)
    TextView btnPhone;
    @BindView(R.id.btn_chat)
    TextView btnChat;
    @BindView(R.id.ll_bottom2)
    LinearLayout llBottom2;

    private Context context;
    private FindPersonal_fragment_bean.DataBean findItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_find_personal);
        ButterKnife.bind(this);
        context = this;
        findItem = (FindPersonal_fragment_bean.DataBean) getIntent().getSerializableExtra("findItem");
        setData();
    }

    /**
     * 设置数据
     */
    private void setData() {
        tvTitle.setText(findItem.getRealname());
        tvName.setText(findItem.getRealname());
        ImgLoadUtil.display(NetBaseConstant.NET_HOST + findItem.getPhoto(), ivAvatar);
        ivSex.setImageResource(findItem.getSex().equals("0") ? R.mipmap.ic_man : R.mipmap.ic_women);
        tvWorkProperty.setText(findItem.getWork_property());
        tvAddress.setText(findItem.getCity());
        tvWorkLife.setText(findItem.getWork_life());
        tvDegree.setText(findItem.getEducation().size() > 0 ? findItem.getEducation().get(0).getDegree() : "");
        tvState.setText(findItem.getJobstate());
        tvPositionType.setText(findItem.getPosition_type());
        tvSalary.setText(findItem.getWantsalary());
        tvCategories.setText(findItem.getCategories());
        lvEducation.setAdapter(new CommonAdapter<FindPersonal_fragment_bean.DataBean.EducationBean>
                (context, findItem.getEducation(), R.layout.item_education) {
            @Override
            public void convert(ViewHolder holder, FindPersonal_fragment_bean.DataBean.EducationBean educationBean) {
                holder.setText(R.id.tv_school_name, educationBean.getSchool())
                        .setText(R.id.tv_time, educationBean.getTime())
                        .setText(R.id.tv_major, educationBean.getMajor())
                        .setText(R.id.tv_degree, educationBean.getDegree());
            }
        });
        if (findItem.getWork().size() > 0) {
            tvJobCompany.setText(findItem.getWork().get(0).getCompany_name());
            tvJobTime.setText(findItem.getWork().get(0).getWork_period());
            tvJobType.setText(findItem.getWork().get(0).getJobtype());
            List<String> datas = Arrays.asList(findItem.getWork().get(0).getSkill().split("-"));
            flJobSkill.setAdapter(new TagAdapter<String>(datas) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.layout, flJobSkill, false);
                    tv.setText(s);
                    return tv;
                }
            });
        }
        tvProjectName.setText(findItem.getProject().size() > 0 ? findItem.getProject().get(0).getProject_name() : "");
        tvProjectDescription.setText(findItem.getProject().size() > 0 ? findItem.getProject().get(0).getDescription_project() : "");
        tvAdvantage.setText(findItem.getAdvantage());
    }

    @OnClick({R.id.back, R.id.btn_get_contact,R.id.btn_phone, R.id.btn_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_get_contact:
                showDialog();
                break;
            case R.id.btn_phone:

                break;
            case R.id.btn_chat:

                break;
        }
    }

    /**
     * 会员弹出框
     */
    private void showDialog() {
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_vip, null);
        Button button = (Button) view.findViewById(R.id.btn_observe);
        ImageView btn_cancel = (ImageView) view.findViewById(R.id.btn_close);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.rounded_white);
        dialog.setCancelable(false);
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showVipInfoDialog();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 弹出会员特权对话框
     */
    private void showVipInfoDialog() {
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.vip_info_dialog, null);
        TextView button = (TextView) view.findViewById(R.id.btn_pay);
        ImageView btn_cancel = (ImageView) view.findViewById(R.id.btn_close);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.vip_info_bg);
        dialog.setCancelable(false);
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //跳转
                llTop.setVisibility(View.VISIBLE);
                llBottom1.setVisibility(View.GONE);
                llBottom2.setVisibility(View.VISIBLE);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
