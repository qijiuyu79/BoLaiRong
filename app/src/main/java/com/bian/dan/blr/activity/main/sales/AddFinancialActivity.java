package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.GridViewImgAdapter;
import com.bian.dan.blr.persenter.sales.AddFinancialPersenter;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bian.dan.blr.view.MyWatcher;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.parameter.AddFinancialP;
import com.zxdc.utils.library.bean.parameter.FileList;
import com.zxdc.utils.library.bean.parameter.UpdateFinancial;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加财务报销
 */
public class AddFinancialActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.gridView)
    MyGridView gridView;
    //选择的照片
    private List<FileBean> imgList=new ArrayList<>();
    private GridViewImgAdapter gridViewImgAdapter;
    private AddFinancialPersenter financialPersenter;
    //要编辑的对象
    private FinancialDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_financial);
        ButterKnife.bind(this);
        initView();
        //编辑前显示对应的数据
        showData();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("新增报销单");
        financialPersenter=new AddFinancialPersenter(this);
        detailsBean= (FinancialDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");
        //限制小数点前后
        etMoney.addTextChangedListener(new MyWatcher(7,2));

        //图片列表
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==imgList.size()){
                    financialPersenter.SelectPhoto();
                }
            }
        });
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                 break;
            //选择用户
            case R.id.tv_name:
                setClass(SelectUserActivity.class,400);
                break;
            case R.id.tv_submit:
                String name=tvName.getText().toString().trim();
                String money=etMoney.getText().toString().trim();
                String remark=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择申请人");
                    return;
                }
                if(TextUtils.isEmpty(money)){
                    ToastUtil.showLong("请输入金额");
                    return;
                }
                if(Double.parseDouble(money)==0){
                    ToastUtil.showLong("金额不能为0");
                    return;
                }
                if(TextUtils.isEmpty(remark)){
                    ToastUtil.showLong("款项用途及金额");
                    return;
                }
                AddFinancialP addFinancialP=new AddFinancialP();
                addFinancialP.setUserId((int)tvName.getTag());
                addFinancialP.setAmount(Double.parseDouble(money));
                addFinancialP.setMemo(remark);

                if(detailsBean==null){
                    List<FileList> list=new ArrayList<>();
                    for (int i = 0; i < imgList.size(); i++) {
                        FileList fileList=new FileList();
                        fileList.setUrl(imgList.get(i).getUrl());
                        list.add(fileList);
                    }
                    addFinancialP.setFileList(list);
                }

                if(detailsBean==null){
                    financialPersenter.addFinancial(addFinancialP);
                    LogUtils.e("+++++++++++++"+new Gson().toJson(addFinancialP));
                }else{
                    UpdateFinancial updateFinancial=new UpdateFinancial();
                    updateFinancial.setId(detailsBean.getId());
                    updateFinancial.setState(0);
                    updateFinancial.setAmount(Double.parseDouble(money));
                    updateFinancial.setMemo(remark);
                    financialPersenter.updateFinancial(updateFinancial);
                    LogUtils.e("+++++++++++++"+new Gson().toJson(addFinancialP));
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        List<LocalMedia> list=new ArrayList<>();
                        LocalMedia localMedia=new LocalMedia();
                        localMedia.setCompressPath(SelectPhoto.pai);
                        list.add(localMedia);
                        if(detailsBean==null){
                            //增加-上传图片
                            financialPersenter.uploadFile(list);
                        }else{
                            //编辑-上传图片
                            financialPersenter.uploadByFileAndTypeAndFid(detailsBean.getId(),list);
                        }
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()==0){
                    return;
                }
                if(detailsBean==null){
                    //增加-上传图片
                    financialPersenter.uploadFile(list);
                }else{
                    //编辑-上传图片
                    financialPersenter.uploadByFileAndTypeAndFid(detailsBean.getId(),list);
                }
                break;
            //返回用户信息
            case 400:
                 if(data==null){
                     return;
                 }
                 UserList.ListBean listBean= (UserList.ListBean) data.getSerializableExtra("listBean");
                 if(listBean!=null){
                     tvName.setText(listBean.getName());
                     tvName.setTag(listBean.getUserId());
                     tvAccount.setText(listBean.getAccount());
                     tvBank.setText(listBean.getOpenBank());
                     tvMobile.setText(listBean.getMobile());
                 }
                 break;
            default:
                break;
        }
    }


    /**
     * 编辑前显示对应的数据
     */
    private void showData(){
        if(detailsBean==null){
            return;
        }
        tvHead.setText("编辑报销单");
        //禁止修改申请人
        tvName.setEnabled(false);
        tvName.setText(detailsBean.getName());
        tvName.setTextColor(getResources().getColor(R.color.color_999999));
        tvName.setTag(detailsBean.getUserId());
        tvAccount.setText(detailsBean.getAccount());
        tvAccount.setTextColor(getResources().getColor(R.color.color_999999));
        tvBank.setText(detailsBean.getOpenBank());
        tvBank.setTextColor(getResources().getColor(R.color.color_999999));
        tvMobile.setText(detailsBean.getMobile());
        tvMobile.setTextColor(getResources().getColor(R.color.color_999999));
        etMoney.setText(detailsBean.getAmount()+"");
        etRemark.setText(detailsBean.getMemo());
        /**
         * 显示图片
         */
        imgList.addAll(detailsBean.getFileList());
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
    }


    /**
     * 编辑的时候删除图片
     */
    public void deleteImg(FileBean fileBean){
        financialPersenter.deleteFile(String.valueOf(fileBean.getId()));
    }


    /**
     * 图片上传成功
     * @param path
     */
    public void uploadSuccess(final String path, final int pid){
        runOnUiThread(new Runnable() {
            public void run() {
                FileBean fileBean;
                fileBean = new FileBean(pid,path);
                imgList.add(fileBean);
                gridViewImgAdapter=new GridViewImgAdapter(activity,imgList);
                gridView.setAdapter(gridViewImgAdapter);
            }
        });
    }
}
