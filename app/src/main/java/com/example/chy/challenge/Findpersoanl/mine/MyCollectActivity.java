package com.example.chy.challenge.Findpersoanl.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chy.challenge.Findpersoanl.mine.bean.MyCollectBean;
import com.example.chy.challenge.NetInfo.UserNetConstant;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.CommonAdapter;
import com.example.chy.challenge.Utils.JsonResult;
import com.example.chy.challenge.Utils.ToastUtil;
import com.example.chy.challenge.Utils.ViewHolder;
import com.example.chy.challenge.Utils.VolleyUtil;
import com.example.chy.challenge.button.WaveView;
import com.example.chy.challenge.login.register.register_bean.UserInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectActivity extends Activity {

    @BindView(R.id.back)
    WaveView back;
    @BindView(R.id.btn_delete)
    WaveView btnDelete;
    @BindView(R.id.btn_cancel)
    WaveView btnCancel;
    @BindView(R.id.lv_collect)
    ListView lvCollect;
    @BindView(R.id.tv_delete)
    TextView tvDelete;

    private Context context;
    private UserInfoBean userInfoBean;
    private List<MyCollectBean> myCollectBeans;
    private CommonAdapter<MyCollectBean> adapter1;
    private CommonAdapter<MyCollectBean> adapter2;
    private String mResult;
    private boolean state = false; //删除选择框的开关
    private List<String> selected; //记录选择item的id集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        context = this;
        userInfoBean = new UserInfoBean(context);
        myCollectBeans = new ArrayList<>();
        selected = new ArrayList<>();
        getData();
    }

    /**
     * 获取收藏列表
     */
    private void getData() {
        String url = UserNetConstant.GETFAVORITE
                + "&userid=" + userInfoBean.getUserid()
                + "&type=2";
        VolleyUtil.VolleyGetRequest(context, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if (JsonResult.JSONparser(context, result)) {
                    mResult = result;
                    setAdpter();
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    /**
     * 设置适配器
     */
    private void setAdpter() {
        myCollectBeans.clear();
        myCollectBeans.addAll(getBeanFromJson(mResult));
        adapter1 = new CommonAdapter<MyCollectBean>(context, myCollectBeans, R.layout.item_collect_deletable) {
            @Override
            public void convert(ViewHolder holder, MyCollectBean myCollectBean) {
                holder.setText(R.id.adapter_realname, myCollectBean.getRealname())
                        .setImageResource(R.id.adapter_realname_sex, myCollectBean.getSex().equals("1") ? R.mipmap.ic_nansheng : R.mipmap.ic_nvsheng)
                        .setText(R.id.adapter_position_type, myCollectBean.getPosition_type())
                        .setImageByUrl(R.id.adapter_head_image, myCollectBean.getPhoto())
                        .setText(R.id.adapter_late_ornowe, myCollectBean.getJobstate().equals("离职") ? "曾任" : "现任")
                        .setText(R.id.adapter_myjob, myCollectBean.getMyjob())
                        .setText(R.id.adapter_company, myCollectBean.getCompany())
                        .setText(R.id.adapter_salary, myCollectBean.getWantsalary())
                        .setText(R.id.adapter_personal_profile, myCollectBean.getAdvantage())
                        .setText(R.id.adapter_location_city, myCollectBean.getCity())
                        .setText(R.id.adapter_jobtime_worklife, myCollectBean.getWork_life())
                        .setText(R.id.adapter_personal_degree, myCollectBean.getEducation().size() > 0 ? myCollectBean.getEducation().get(0).getDegree() : "")
                        .setText(R.id.adapter_work_property, myCollectBean.getWork_property());
                holder.getView(R.id.rl_check).setVisibility(View.GONE);

            }
        };
        adapter2 = new CommonAdapter<MyCollectBean>(context, myCollectBeans, R.layout.item_collect_deletable) {
            @Override
            public void convert(ViewHolder holder, final MyCollectBean myCollectBean) {
                holder.setText(R.id.adapter_realname, myCollectBean.getRealname())
                        .setImageResource(R.id.adapter_realname_sex, myCollectBean.getSex().equals("1") ? R.mipmap.ic_nansheng : R.mipmap.ic_nvsheng)
                        .setText(R.id.adapter_position_type, myCollectBean.getPosition_type())
                        .setImageByUrl(R.id.adapter_head_image, myCollectBean.getPhoto())
                        .setText(R.id.adapter_late_ornowe, myCollectBean.getJobstate().equals("离职") ? "曾任" : "现任")
                        .setText(R.id.adapter_myjob, myCollectBean.getMyjob())
                        .setText(R.id.adapter_company, myCollectBean.getCompany())
                        .setText(R.id.adapter_salary, myCollectBean.getWantsalary())
                        .setText(R.id.adapter_personal_profile, myCollectBean.getAdvantage())
                        .setText(R.id.adapter_location_city, myCollectBean.getCity())
                        .setText(R.id.adapter_jobtime_worklife, myCollectBean.getWork_life())
                        .setText(R.id.adapter_personal_degree, myCollectBean.getEducation().size() > 0 ? myCollectBean.getEducation().get(0).getDegree() : "")
                        .setText(R.id.adapter_work_property, myCollectBean.getWork_property());
                holder.getView(R.id.rl_check).setVisibility(View.VISIBLE);
                final CheckBox cb = holder.getView(R.id.cb_item);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb.isChecked()) {
                            add(myCollectBean.getResumes_id());
                        } else {
                            remove(myCollectBean.getResumes_id());
                        }
                    }
                });
            }
        };
        if (state) {
            lvCollect.setAdapter(adapter2);
        } else {
            lvCollect.setAdapter(adapter1);
        }
    }

    /**
     * 从集合中移除选中id
     *
     * @param resumes_id
     */
    private void remove(String resumes_id) {
        if (selected.contains(resumes_id)) {
            selected.remove(resumes_id);
        }
    }

    /**
     * 添加选中id到集合中
     *
     * @param resumes_id
     */
    private void add(String resumes_id) {
        if (!selected.contains(resumes_id)) {
            selected.add(resumes_id);
        }
    }

    @OnClick({R.id.back, R.id.btn_delete, R.id.btn_cancel, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_delete:
                state = true;
                btnDelete.setVisibility(View.GONE);
                btnCancel.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.VISIBLE);
                setAdpter();
                break;
            case R.id.btn_cancel:
                state = false;
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.GONE);
                tvDelete.setVisibility(View.GONE);
                selected.clear();
                setAdpter();
                break;
            case R.id.tv_delete:
                deleteMyCollect();
                break;
        }
    }

    /**
     * 删除我的收藏
     */
    private void deleteMyCollect() {
        String url = UserNetConstant.CANCELFAVORITE
                + "&userid=" + userInfoBean.getUserid()
                + "&object_id=" + listToString()
                + "&type=2";
        VolleyUtil.VolleyGetRequest(this, url, new VolleyUtil.VolleyJsonCallback() {
            @Override
            public void onSuccess(String result) {
                if(JsonResult.JSONparser(context,result)){
                    ToastUtil.showShort(context,"删除成功");
                    selected.clear();
                    state = false;
                    btnDelete.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    getData();
                }else{
                    ToastUtil.showShort(context,"删除失败");
                    selected.clear();
                    state = false;
                    btnDelete.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    getData();
                }
            }

            @Override
            public void onError() {

            }
        });

    }

    /**
     * @return
     */
    private String listToString() {
        String str = "";
        if (selected.size() == 0) {
            return "";
        } else {
            for (int i = 0; i < selected.size() - 1; i++) {
                str += selected.get(i) + ",";
            }
            str += selected.get(selected.size() - 1);
        }
        return str;
    }

    private List<MyCollectBean> getBeanFromJson(String result) {
        String data = "";
        try {
            JSONObject json = new JSONObject(result);
            data = json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(data, new TypeToken<List<MyCollectBean>>() {
        }.getType());
    }
}
