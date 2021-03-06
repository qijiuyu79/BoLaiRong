package com.bian.dan.blr.fragment.audit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.bian.dan.blr.R;
import com.bian.dan.blr.activity.audit.customer.AuditCustomerActivity;
import com.bian.dan.blr.activity.audit.customer.AuditCustomerDetailsActivity;
import com.bian.dan.blr.adapter.sales.CustomerAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.bean.Customer;
import com.zxdc.utils.library.bean.CustomerList;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyRefreshLayout;
import com.zxdc.utils.library.view.MyRefreshLayoutListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AuditCustomerFragment extends BaseFragment implements MyRefreshLayoutListener {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.re_list)
    MyRefreshLayout reList;
    Unbinder unbinder;
    //fragment是否可见
    private boolean isVisibleToUser=false;
    //页码
    private int page=1;
    //客户集合
    private List<Customer> listAll=new ArrayList<>();
    private CustomerAdapter customerAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview, container, false);
        unbinder = ButterKnife.bind(this, view);
        //刷新加载
        reList.setMyRefreshLayoutListener(this);
        customerAdapter=new CustomerAdapter(mActivity,listAll);
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mActivity, AuditCustomerDetailsActivity.class);
                intent.putExtra("detailsId",listAll.get(position).getId());
                startActivityForResult(intent,1000);
            }
        });

        //获取客户列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
        return view;
    }



    /**
     * 下刷
     * @param view
     */
    private Handler handler=new Handler();
    public void onRefresh(View view) {
        handler.postDelayed(new Runnable() {
            public void run() {
                page=1;
                listAll.clear();
                getCustomer();
            }
        },300);
    }

    /**
     * 上拉加载更多
     * @param view
     */
    public void onLoadMore(View view) {
        page++;
        getCustomer();
    }


    /**
     * 获取客户列表
     */
    private void getCustomer(){
        HttpMethod.getCustomer(((AuditCustomerActivity)mActivity).pageIndex==0 ? "0" : "1,2",null, null, null, page, new NetWorkCallBack() {
            public void onSuccess(Object object) {
                reList.refreshComplete();
                reList.loadMoreComplete();
                CustomerList customerList= (CustomerList) object;
                if(customerList.isSussess()){
                    List<Customer> list=customerList.getData().getRows();
                    listAll.addAll(list);
                    customerAdapter.notifyDataSetChanged();
                    if (list.size() < HttpMethod.limit) {
                        reList.setIsLoadingMoreEnabled(false);
                    }

                }else{
                    ToastUtil.showLong(customerList.getMsg());
                }
            }

            public void onFail(Throwable t) {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            reList.startRefresh();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser=isVisibleToUser;
        //获取客户列表
        if(isVisibleToUser && view!=null && listAll.size()==0){
            reList.startRefresh();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
