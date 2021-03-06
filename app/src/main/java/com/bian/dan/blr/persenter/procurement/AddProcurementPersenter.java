package com.bian.dan.blr.persenter.procurement;

import android.content.Intent;
import android.widget.TextView;

import com.bian.dan.blr.activity.main.procurement.AddProcurementActivity;
import com.bian.dan.blr.view.time.CustomDatePicker;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.parameter.AddProcurementP;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddProcurementPersenter {

    private AddProcurementActivity activity;

    public AddProcurementPersenter(AddProcurementActivity activity){
        this.activity=activity;
    }


    /**
     * 选择时间
     */
    public void selectTime(final TextView tvTime){
        CustomDatePicker customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time.split(" ")[0]);
            }
        }, "1920-01-01 00:00", "2050-12-31 23:59");
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(now.split(" ")[0]);
    }


    /**
     * 新增采购单
     */
    public void AddProcurement(AddProcurementP addProcurementP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.AddProcurement(addProcurementP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }


    /**
     * 修改采购单
     */
    public void EditProcurement(AddProcurementP addProcurementP){
        DialogUtil.showProgress(activity,"数据提交中");
        HttpMethod.EditProcurement(addProcurementP, new NetWorkCallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseBean baseBean= (BaseBean) object;
                if(baseBean.isSussess()){
                    Intent intent=new Intent();
                    activity.setResult(1000,intent);
                    activity.finish();
                }
                ToastUtil.showLong(baseBean.getMsg());
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
    }
}
