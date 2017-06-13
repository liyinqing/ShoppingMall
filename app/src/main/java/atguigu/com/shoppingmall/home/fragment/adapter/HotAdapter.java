package atguigu.com.shoppingmall.home.fragment.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.shoppingmall.R;
import atguigu.com.shoppingmall.home.fragment.bean.HomeBean;
import atguigu.com.shoppingmall.utils.Constants;

/**
 * 作者：李银庆 on 2017/6/13 08:45
 */
public class HotAdapter extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.context = context;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        Glide.with(context)
                .load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure())
                .into(holder.ivHot);
        holder.tvName.setText(hotInfoBean.getName());
        holder.tvPrice.setText("￥" + hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivHot;
        TextView tvName;
        TextView tvPrice;

        ViewHolder(View view) {
            ivHot = (ImageView) view.findViewById(R.id.iv_hot);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
        }
    }
}
