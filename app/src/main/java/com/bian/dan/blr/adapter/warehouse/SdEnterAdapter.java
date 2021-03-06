package com.bian.dan.blr.adapter.warehouse;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.SdEnter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SdEnterAdapter extends BaseAdapter {

    private Activity activity;
    private List<SdEnter.ListBean> list;
    public SdEnterAdapter(Activity activity,List<SdEnter.ListBean> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_sd_enter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SdEnter.ListBean listBean=list.get(position);
        holder.tvCreatePeople.setText(Html.fromHtml("入库员：<font color=\"#000000\">"+listBean.getCreateName()+"</font>"));
        holder.tvName.setText(Html.fromHtml("采购员：<font color=\"#000000\">"+listBean.getPurcName()+"</font>"));
        holder.tvTime.setText(Html.fromHtml("采购日期：<font color=\"#000000\">"+listBean.getPurcDate()+"</font>"));
        holder.tvTime2.setText(listBean.getCreateDate());
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_create_people)
        TextView tvCreatePeople;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time2)
        TextView tvTime2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

