package atguigu.com.shoppingmall.home.fragment.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.com.shoppingmall.home.fragment.bean.HomeBean;
import atguigu.com.shoppingmall.utils.Constants;

/**
 * 作者：李银庆 on 2017/6/12 16:25
 */
public class ActAdapter extends PagerAdapter {

    private final Context context;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ActAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.context = context;
        this.datas = act_info;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        HomeBean.ResultBean.ActInfoBean actInfoBean = datas.get(position);
        Glide.with(context).load(Constants.BASE_URL_IMAGE + actInfoBean.getIcon_url()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.OnOnItemClick(position);
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnOnItemClickListener {
        public void OnOnItemClick(int position);
    }

    private OnOnItemClickListener listener;

    public void setOnOnItemClickListener(OnOnItemClickListener listener) {
        this.listener = listener;
    }

}
