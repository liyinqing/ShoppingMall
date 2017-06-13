package atguigu.com.shoppingmall.home.fragment.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.shoppingmall.R;
import atguigu.com.shoppingmall.home.fragment.bean.HomeBean;
import atguigu.com.shoppingmall.utils.Constants;

/**
 * 作者：李银庆 on 2017/6/12 19:39
 */
public class SeceillAdapter extends RecyclerView.Adapter<SeceillAdapter.MyViewHolder> {

    private final Context context;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;



    public SeceillAdapter(Context context, List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_seceill, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        holder.tvCoverPrice.setText("￥"+ listBean.getCover_price());
        holder.tvOriginPrice.setText("￥"+listBean.getOrigin_price());
        Glide.with(context).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.ivFigure);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivFigure;
        TextView tvCoverPrice;
        TextView tvOriginPrice;
        public MyViewHolder(View itemView) {
            super(itemView);
           ivFigure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tvCoverPrice = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tvOriginPrice = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

    }
    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    public OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
