package com.twtstudio.bbs.bdpqchen.bbs.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.twtstudio.bbs.bdpqchen.bbs.R;
import com.twtstudio.bbs.bdpqchen.bbs.commons.base.BaseActivity;
import com.twtstudio.bbs.bdpqchen.bbs.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bdpqchen on 17-5-2.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.checkbox_save_password)
    AppCompatCheckBox mCheckboxSavePassword;
    @BindView(R.id.tx_forgot_password)
    TextView mTxForgotPassword;
    @BindView(R.id.loading_btn_login)
    LoadingButton mLoadingBtnLogin;
    @BindView(R.id.tv_goto_register)
    TextView mTvGotoRegister;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected Toolbar getToolbarView() {
        return null;
    }

    @Override
    protected boolean isShowBackArrow() {
        return false;
    }

    @Override
    protected boolean isSupportNightMode() {
        return false;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void loginResults() {
//        mLoadingBtnLogin.loadingFailed();
//        mLoadingBtnLogin.loadingSuccessful();
//        SnackBarUtil.error(this, "登录失败，请检查用户名与密码");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.checkbox_save_password, R.id.tx_forgot_password, R.id.loading_btn_login, R.id.tv_goto_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox_save_password:
                break;
            case R.id.tx_forgot_password:
                break;
            case R.id.loading_btn_login:
                mLoadingBtnLogin.startLoading();
                break;
            case R.id.tv_goto_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

        }

    }



}
