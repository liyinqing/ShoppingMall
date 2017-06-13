package atguigu.com.shoppingmall.home.fragment.adapter;


import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.shoppingmall.R;
import atguigu.com.shoppingmall.home.fragment.bean.HomeBean;
import atguigu.com.shoppingmall.home.fragment.utils.GlideImageLoader;
import atguigu.com.shoppingmall.home.fragment.view.NoScrollGridView;
import atguigu.com.shoppingmall.utils.Constants;
import cn.iwgang.countdownview.CountdownView;

/**
 * 作者：李银庆 on 2017/6/12 14:52
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final HomeBean.ResultBean result;
    private LayoutInflater inflater;
    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    public int currentType = BANNER;


    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.context = context;
        this.result = result;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_viewpager, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflater.inflate(R.layout.act_viewpager, null));
        } else if (viewType == SECKILL) {
            return new SeceillViewHolder(context, inflater.inflate(R.layout.seceill_viewpager, null));
        } else  if(viewType == RECOMMEND) {
            return new RecommenHolder(context, inflater.inflate(R.layout.recommen_viewpager, null));
        } else if(viewType == HOT){
            return new HotViewHolder(context,inflater.inflate(R.layout.hot_viewpager,null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeceillViewHolder seceillViewHolder = (SeceillViewHolder) holder;
            seceillViewHolder.setData(result.getSeckill_info());
        }else if (getItemViewType(position) == RECOMMEND) {
            RecommenHolder recommen = (RecommenHolder) holder;
            recommen.setData(result.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {

            //设置Banner 数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(images)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Toast.makeText(context, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ChannelAdapter adapter;
        private GridView gv;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gv = (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(final List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {

            adapter = new ChannelAdapter(context, channel_info);
            gv.setAdapter(adapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "" + channel_info.get(position).getChannel_name(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ViewPager act_viewpager;
        private ActAdapter adapter;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            adapter = new ActAdapter(context, act_info);
            act_viewpager.setAdapter(adapter);
            act_viewpager.setPageMargin(20);

            act_viewpager.setPageTransformer(true, new
                    RotateDownPageTransformer());
            adapter.setOnOnItemClickListener(new ActAdapter.OnOnItemClickListener() {
                @Override
                public void OnOnItemClick(int position) {
                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
                    Toast.makeText(context, "" + actInfoBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class SeceillViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        CountdownView cv_countdownViewTest1;
        TextView tv_more_seckill;
        RecyclerView recyclerview;
        SeceillAdapter adapter;

        Handler handler = new Handler();

        private boolean isStart = false;

        public SeceillViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            cv_countdownViewTest1 = (CountdownView) itemView.findViewById(R.id.cv_countdownViewTest1);
            //查看更多
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview);

        }

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            if (!isStart) {
                isStart = true;
                //计算倒计时持续时间
                long totalTime = Long.valueOf(seckill_info.getEnd_time()) - Long.valueOf(seckill_info.getStart_time());

                // 校对倒计时
                long curTime = System.currentTimeMillis();
                //重新设置结束数据时间
                seckill_info.setEnd_time((curTime + totalTime + ""));
                //开始刷新
                startRefreshTime();
            }
            adapter = new SeceillAdapter(context, seckill_info.getList());
            recyclerview.setAdapter(adapter);
            recyclerview.addItemDecoration(new SpacesItemDecoration(10));
            recyclerview.setHasFixedSize(true);
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            adapter.setOnItemClickListener(new SeceillAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(context, ""+seckill_info.getList().get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        private void startRefreshTime() {
            handler.postDelayed(MyRunnable, 10);
        }

        Runnable MyRunnable = new Runnable() {
            @Override
            public void run() {
                //得到当前时间
                long currentTime = System.currentTimeMillis();

                if (currentTime >= Long.parseLong(result.getSeckill_info().getEnd_time())) {
                    // 倒计时结束
                    handler.removeCallbacksAndMessages(null);
                } else {
                    //更新时间
                    cv_countdownViewTest1.updateShow(Long.parseLong(result.getSeckill_info().getEnd_time()) - currentTime);
                    //每隔1000毫秒更新一次
                    handler.postDelayed(MyRunnable, 1000);
                }
            }
        };


        public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

            private int space;

            public SpacesItemDecoration(int space) {
                this.space = space;
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
                outRect.top = space;
            }
        }


    }

    class RecommenHolder extends  RecyclerView.ViewHolder{
        private final Context context;
        private Recommen adapter;
        GridView gv;
        public RecommenHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gv = (GridView) itemView.findViewById(R.id.gv);
        }

        public void setData(List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            adapter = new Recommen(context,recommend_info);
            gv.setAdapter(adapter);
            gv.setNumColumns(3);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder{

        private final Context context;
        private HotAdapter adapter;
        private NoScrollGridView gv_hot;
        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gv_hot = (NoScrollGridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            adapter = new HotAdapter(context,hot_info);
            gv_hot.setAdapter(adapter);
        }
    }
}



