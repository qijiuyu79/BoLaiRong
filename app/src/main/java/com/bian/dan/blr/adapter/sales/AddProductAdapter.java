package com.bian.dan.blr.adapter.sales;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.zxdc.utils.library.bean.Goods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductAdapter extends BaseAdapter {

    private Activity activity;
    private List<Goods> list;

    public AddProductAdapter(Activity activity, List<Goods> list) {
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
            view = LayoutInflater.from(activity).inflate(R.layout.item_add_product, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Goods goods = list.get(position);
        holder.tvName.setText(Html.fromHtml("物料名称：<font color=\"#000000\">" + goods.getName() + "</font>"));
        holder.tvUnit.setText(Html.fromHtml("单位：<font color=\"#000000\">" + goods.getUnitStr() + "</font>"));
        holder.tvBrand.setText(Html.fromHtml("牌号：<font color=\"#000000\">" + goods.getBrand() + "</font>"));
        holder.tvSpec.setText(Html.fromHtml("规格/型号：<font color=\"#000000\">" + goods.getSpec() + "</font>"));
        holder.tvNum.setText(Html.fromHtml("数量：<font color=\"#000000\">" + goods.getNum() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("单价(元)：<font color=\"#000000\">" + goods.getPrice() + "</font>"));
        holder.tvMonety.setText(Html.fromHtml("金额(元)：<font color=\"#FF4B4C\">" + goods.getTotalMoney() + "</font>"));
        holder.tvRemark.setText("备注：" + goods.getMemo());
        if (goods.getIsInvoice().equals("1")) {
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">是</font>"));
        } else {
            holder.tvInvoice.setText(Html.fromHtml("是否开票：<font color=\"#000000\">否</font>"));
        }
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_unit)
        TextView tvUnit;
        @BindView(R.id.tv_brand)
        TextView tvBrand;
        @BindView(R.id.tv_spec)
        TextView tvSpec;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_monety)
        TextView tvMonety;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_invoice)
        TextView tvInvoice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

