package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.production.SelectPlanCodeActivity;
import com.bian.dan.blr.adapter.sales.ProductPlanAdapter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 生产计划
 */
public class ProductionPlanActivity extends BaseActivity implements MyRefreshLayoutListener{

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.tv_key)
    TextView tvKey;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    private ProductPlanAdapter productPlanAdapter;
    //页码
    private int page=1;
    private List<ProductPlan.ListBean> listAll=new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_plan);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvHead.setText("生产计划");
        imgRight.setImageResource(R.mipmap.add);
        reList.setMyRefreshLayoutListener(this);
        productPlanAdapter=new ProductPlanAdapter(this,listAll);
        listView.setAdapter(productPlanAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity,ProductPlanDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
            }
        });

        /**
         * 监听客户名称输入框
         */
        tvKey.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    imgClear.setVisibility(View.VISIBLE);
                }else{
                    imgClear.setVisibility(View.GONE);
                }
                //加载数据
                reList.startRefresh();
            }
        });
    }


    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back,R.id.img_right,R.id.tv_key,R.id.img_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //添加
            case R.id.img_right:
                setClass(AddProductPlanActivity.class,1000);
                break;
            //选择生成计划
            case R.id.tv_key:
                setClass(SelectPlanCodeActivity.class,600);
                break;
            case R.id.img_clear:
                 tvKey.setText(null);
                 break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getPlanList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getPlanList();
    }


    /**
     * 获取生产计划列表
     */
    private void getPlanList(){
        HttpMethod.getPlanList(tvKey.getText().toString().trim(), null,page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                ProductPlan productPlan= (ProductPlan) object;
                if(productPlan.isSussess()){
                    List<ProductPlan.ListBean> list=productPlan.getData().getRows();
                    listAll.addAll(list);
                    productPlanAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(productPlan.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            //增加成功后，重新刷新列表
            case 1000:
                //加载数据
                reList.startRefresh();
                break;
           //回执生产计划code
            case 600:
                 if(data!=null){
                     ProductPlan.ListBean listBean= (ProductPlan.ListBean) data.getSerializableExtra("listBean");
                     if(listBean!=null){
                         tvKey.setText(listBean.getPlanCode());
                     }
                 }
                 break;
            default:
                break;
        }
    }
}
