package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Customer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerAdapter extends BaseAdapter {

    private Activity activity;
    private List<Customer> list;

    public CustomerAdapter(Activity activity, List<Customer> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_customer, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Customer customer = list.get(position);
        holder.tvName.setText(Html.fromHtml("客户名称：<font color=\"#000000\">" + customer.getCustomerName() + "</font>"));
        holder.tvPeople.setText(Html.fromHtml("联系人：<font color=\"#000000\">" + customer.getContacts() + "</font>"));
        holder.tvMobile.setText(Html.fromHtml("手机号：<font color=\"#000000\">" + customer.getPhone() + "</font>"));
        holder.tvPosition.setText(Html.fromHtml("职位：<font color=\"#000000\">" + customer.getPosition() + "</font>"));
        holder.tvStatus.setText(customer.getStatusName());
        holder.tvState.setText(customer.getStateStr());
        switch (customer.getState()){
            case 0:
                holder.tvState.setTextColor(activity.getResources().getColor(R.color.color_FE8E2C));
                break;
            case 1:
                holder.tvState.setTextColor(activity.getResources().getColor(R.color.color_70DF5D));
                break;
            case 2:
                holder.tvState.setTextColor(activity.getResources().getColor(R.color.color_FF4B4C));
                break;
            default:
                break;
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_people)
        TextView tvPeople;
        @BindView(R.id.tv_mobile)
        TextView tvMobile;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_state)
        TextView tvState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

