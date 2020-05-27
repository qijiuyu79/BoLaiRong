package com.bian.dan.blr.activity.main.procurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectMaterialActivity;
import com.bian.dan.blr.view.DecimalDigitsInputFilter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Goods;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity5 extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_spec)
    TextView tvSpec;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_remark)
    EditText etRemark;
    //要编辑的对象
    private Goods editGood;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product5);
        ButterKnife.bind(this);
        initView();
        //显示要编辑的内容
        showEditData();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("添加物料");
        editGood= (Goods) getIntent().getSerializableExtra("goods");
        //显示小数点只能输入两位
        etPrice.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2), new InputFilter.LengthFilter(8)});
    }

    @OnClick({R.id.lin_back, R.id.tv_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //物料名称
            case R.id.tv_name:
                setClass(SelectMaterialActivity.class,100);
                break;
            case R.id.tv_submit:
                String name=tvName.getText().toString().trim();
                String price=etPrice.getText().toString().trim();
                String memo=etRemark.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    ToastUtil.showLong("请选择物料");
                    return;
                }
                if(TextUtils.isEmpty(price)){
                    ToastUtil.showLong("请输入价格");
                    return;
                }

                if(editGood!=null){ //编辑的
                    editGood.setPrice(price);
                    gotoIntent(editGood);
                }else{              //新增的
                    Goods goods=new Goods(listBean.getId(),listBean.getName(),listBean.getSpec(),listBean.getUnitStr(),listBean.getBrand(),0,price,null,memo,null);
                    gotoIntent(goods);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 返回上个页面
     * @param goods
     */
    private void gotoIntent(Goods goods){
        Intent intent=new Intent();
        intent.putExtra("goods",editGood);
        setResult(200,intent);
        finish();
    }


    /**
     * 显示要编辑的内容
     */
    private void showEditData(){
        if(editGood==null){
            return;
        }
        tvName.setText(editGood.getName());
        tvName.setTextColor(getResources().getColor(R.color.color_999999));
        tvName.setClickable(false);
        tvSpec.setText(editGood.getSpec());
        tvSpec.setTextColor(getResources().getColor(R.color.color_999999));
        tvUnit.setText(editGood.getUnitStr());
        tvUnit.setTextColor(getResources().getColor(R.color.color_999999));
        etPrice.setText(editGood.getPrice());
        etRemark.setText(editGood.getMemo());
    }


    private Material.ListBean listBean;//物料对象
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100 && data!=null){
            listBean= (Material.ListBean) data.getSerializableExtra("listBean");
            if(listBean==null){
                return;
            }
            tvName.setText(listBean.getName());
            tvSpec.setText(listBean.getSpec());
            tvUnit.setText(listBean.getUnitStr());
            etPrice.setText(String.valueOf(listBean.getPrice()));
        }
    }
}