package com.bian.dan.blr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.application.MyApplication;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.UpdatePwdP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class UpdatePwdActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd2)
    EditText etNewPwd2;
    @BindView(R.id.img_old_pwd)
    ImageView imgOldPwd;
    @BindView(R.id.img_new_pwd)
    ImageView imgNewPwd;
    @BindView(R.id.img_new_pwd2)
    ImageView imgNewPwd2;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("修改密码");
        tvUsername.setText(MyApplication.getUser().getUser().getUsername());
    }

    @OnClick({R.id.lin_back, R.id.img_old_pwd, R.id.img_new_pwd, R.id.img_new_pwd2, R.id.img_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.img_old_pwd:
                if (view.getTag().equals("0")) {
                    view.setTag("1");
                    imgOldPwd.setImageResource(R.mipmap.yes_look);
                    etOldPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    view.setTag("0");
                    imgOldPwd.setImageResource(R.mipmap.no_look);
                    etOldPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_new_pwd:
                if (view.getTag().equals("0")) {
                    view.setTag("1");
                    imgNewPwd.setImageResource(R.mipmap.yes_look);
                    etNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    view.setTag("0");
                    imgNewPwd.setImageResource(R.mipmap.no_look);
                    etNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_new_pwd2:
                if (view.getTag().equals("0")) {
                    view.setTag("1");
                    imgNewPwd2.setImageResource(R.mipmap.yes_look);
                    etNewPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    view.setTag("0");
                    imgNewPwd2.setImageResource(R.mipmap.no_look);
                    etNewPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.img_confirm:
                String oldPwd=etOldPwd.getText().toString().trim();
                String newPwd=etNewPwd.getText().toString().trim();
                String newPwd2=etNewPwd2.getText().toString().trim();
                if(TextUtils.isEmpty(oldPwd)){
                    ToastUtil.showLong("请输入旧密码");
                    return;
                }
                if(TextUtils.isEmpty(newPwd)){
                    ToastUtil.showLong("请输入新密码");
                    return;
                }
                if(TextUtils.isEmpty(newPwd2)){
                    ToastUtil.showLong("请再次输入新密码");
                    return;
                }
                if(!newPwd.equals(newPwd2)){
                    ToastUtil.showLong("新密码输入不一致，请核对后重新输入");
                    return;
                }
                if(newPwd.equals(oldPwd)){
                    ToastUtil.showLong("新旧密码一致，请重新输入密码");
                    return;
                }
                UpdatePwdP updatePwdP=new UpdatePwdP();
                updatePwdP.setUsername(tvUsername.getText().toString().trim());
                updatePwdP.setPassword(oldPwd);
                updatePwdP.setNewPassword(newPwd);
                //修改密码
                updatePwd(updatePwdP);
                break;
            default:
                break;
        }
    }


    /**
     * 修改密码
     */
    private void updatePwd(UpdatePwdP updatePwdP){
        DialogUtil.showProgress(this,"修改中");
        HttpMethod.updatePwd(updatePwdP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    SPUtil.getInstance(activity).removeMessage(SPUtil.TOKEN);
                    Intent intent=new Intent(activity,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
