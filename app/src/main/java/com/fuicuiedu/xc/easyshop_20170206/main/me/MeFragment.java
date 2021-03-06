package com.fuicuiedu.xc.easyshop_20170206.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuicuiedu.xc.easyshop_20170206.R;
import com.fuicuiedu.xc.easyshop_20170206.commons.ActivityUtils;
import com.fuicuiedu.xc.easyshop_20170206.main.me.personInfo.PersonActivity;
import com.fuicuiedu.xc.easyshop_20170206.model.CachePreferences;
import com.fuicuiedu.xc.easyshop_20170206.user.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的页面
 */

public class MeFragment extends Fragment {

    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;//用户头像
    @BindView(R.id.tv_login)
    TextView tv_login;//用户名

    private View view;
    private ActivityUtils activityUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_me,container,false);
            ButterKnife.bind(this,view);
            activityUtils = new ActivityUtils(this);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: 2017/2/14 0014 判断是否登录，显示用户名和头像
    }

    @OnClick({R.id.iv_user_head,R.id.tv_person_info, R.id.tv_login, R.id.tv_person_goods, R.id.tv_goods_upload})
    public void onClick(View view) {
        // 需要判断是否登录，从而决定跳转位置
        if (CachePreferences.getUser().getName() == null){
            activityUtils.startActivity(LoginActivity.class);
            return;
        }

        switch (view.getId()){
            case R.id.iv_user_head:
            case R.id.tv_login:
            case R.id.tv_person_info:
                activityUtils.startActivity(PersonActivity.class);
                break;
            case R.id.tv_person_goods:
                // TODO: 2017/2/14 0014 跳转到我的商品页面
                activityUtils.showToast("我的商品页面，待实现");
                break;
            case R.id.tv_goods_upload:
                // TODO: 2017/2/14 0014 跳转到商品上传页面
                activityUtils.showToast("商品上传页面，待实现");
                break;
        }
    }
}
