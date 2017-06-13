package com.atguigu.shop.controller.community.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shop.R;
import com.atguigu.shop.base.BaseFragment;
import com.atguigu.shop.controller.community.adapter.NewPostListViewAdapter;
import com.atguigu.shop.model.communitybean.NewPostBean;
import com.atguigu.shop.model.NetManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 作者：尚硅谷-杨光福 on 2016/12/24 14:55
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：新帖Fragment
 */
public class NewPostFragment extends BaseFragment {
    @BindView(R.id.lv_new_post)
    ListView lvNewPost;
    private List<NewPostBean.ResultBean> result;
    private NewPostListViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_new_post, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet(NetManager.NEW_POST_URL);
    }

    /**
     * 根据url联网请求数据
     * @param url
     */
    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "NewPostFragment联网失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {

        NewPostBean newPostBean = JSON.parseObject(response,NewPostBean.class);
        result = newPostBean.getResult();

        if(result != null && result.size() >0){
            //设置适配器
            adapter = new NewPostListViewAdapter(mContext,result);
            lvNewPost.setAdapter(adapter);
        }
    }
}
