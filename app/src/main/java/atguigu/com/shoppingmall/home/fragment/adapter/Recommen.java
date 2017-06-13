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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：李银庆 on 2017/6/12 22:12
 */
public class Recommen extends BaseAdapter {
    private final Context context;
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;

    public Recommen(Context context, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.context = context;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_recommen, null);
            viewHolder  = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);

        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText("￥"+recommendInfoBean.getCover_price());
        Glide.with(context).load(Constants.BASE_URL_IMAGE +recommendInfoBean.getFigure()).into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
