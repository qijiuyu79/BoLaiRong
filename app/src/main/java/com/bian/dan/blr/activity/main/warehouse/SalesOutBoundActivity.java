package com.bian.dan.blr.activity.main.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.main.sales.SelectCustomerActivity;
import com.bian.dan.blr.adapter.sales.OutBoundAdapter;
import com.bian.dan.blr.persenter.warehouse.SalesOutBoundPersenter;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.OutBound;
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
 * 销售出库单
 */
public class SalesOutBoundActivity extends BaseActivity implements MyRefreshLayoutListener {
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_key)
    TextView tvKey;
    private OutBoundAdapter outBoundAdapter;
    //页码
    private int page = 1;
    private List<OutBound.ListBean> listAll = new ArrayList<>();
    private SalesOutBoundPersenter salesOutBoundPersenter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_outbound);
        ButterKnife.bind(this);
        initView();
        //加载数据
        reList.startRefresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("销售出库单");
        tvRight.setText("重置");
        salesOutBoundPersenter=new SalesOutBoundPersenter(this);
        reList.setMyRefreshLayoutListener(this);
        outBoundAdapter = new OutBoundAdapter(this, listAll);
        listView.setAdapter(outBoundAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(activity, SalesOutBoundDetailsActivity.class);
                intent.putExtra("listBean",listAll.get(position));
                startActivity(intent);
            }
        });


        /**
         * 监听开始日期
         */
        tvStartTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvEndTime.getText().toString())){
                    //获取手动入库列表
                    reList.startRefresh();
                }
            }
        });

        /**
         * 监听结束日期
         */
        tvEndTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                if(s.length()>0 && !TextUtils.isEmpty(tvStartTime.getText().toString())){
                    //获取手动入库列表
                    reList.startRefresh();
                }
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
                    //加载数据
                    reList.startRefresh();
                }else{
                    tvKey.setTag("");
                }
            }
        });
    }

    /**
     * 按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.lin_back,R.id.tv_right,R.id.tv_start_time, R.id.tv_end_time,R.id.tv_key})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //重置
            case R.id.tv_right:
                tvStartTime.setText(null);
                tvEndTime.setText(null);
                tvKey.setText(null);
                //加载数据
                reList.startRefresh();
                break;
            //选择开始日期
            case R.id.tv_start_time:
                salesOutBoundPersenter.selectStartTime(tvStartTime,tvEndTime);
                break;
            //选择结束日期
            case R.id.tv_end_time:
                salesOutBoundPersenter.selectEndTime(tvStartTime,tvEndTime);
                break;
            //选择客户名称
            case R.id.tv_key:
                setClass(SelectCustomerActivity.class,100);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(View view) {
        page = 1;
        listAll.clear();
        getOutBoundList();
    }

    @Override
    public void onLoadMore(View view) {
        page++;
        getOutBoundList();
    }


    /**
     * 获取出库单列表
     */
    private void getOutBoundList() {
        HttpMethod.getOutBoundList(tvStartTime.getText().toString(),tvEndTime.getText().toString(),(String) tvKey.getTag(), page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                OutBound outBound= (OutBound) object;
                if(outBound.isSussess()){
                    List<OutBound.ListBean> list=outBound.getData().getRows();
                    listAll.addAll(list);
                    outBoundAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }
                }else{
                    ToastUtil.showLong(outBound.getMsg());
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
            //选择客户名称回执
            case 100:
                if(data!=null){
                    Customer customer = (Customer) data.getSerializableExtra("customer");
                    if(customer!=null){
                        tvKey.setTag(String.valueOf(customer.getId()));
                        tvKey.setText(customer.getCustomerName());
                    }
                }
            default:
                break;
        }
    }
}
