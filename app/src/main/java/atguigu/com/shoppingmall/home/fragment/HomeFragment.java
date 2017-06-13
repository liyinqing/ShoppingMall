package atguigu.com.shoppingmall.home.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.com.shoppingmall.R;
import atguigu.com.shoppingmall.base.BaseFragment;
import atguigu.com.shoppingmall.home.fragment.adapter.HomeAdapter;
import atguigu.com.shoppingmall.home.fragment.bean.HomeBean;
import atguigu.com.shoppingmall.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 作者：李银庆 on 2017/6/12 11:08
 */
public class HomeFragment extends BaseFragment {
    private String TAG = HomeFragment.class.getSimpleName();
    private HomeAdapter adapter;
    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    android.support.v7.widget.RecyclerView rvHome;
    @BindView(R.id.ib_top)
    ImageButton ibTop;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(context, "信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                Toast.makeText(context, "回到", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }
    class  MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "联网失败"+e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            processData(response);
        }
    }

    private void processData(String json) {
        if(!TextUtils.isEmpty(json)) {
            HomeBean homeBean = JSON.parseObject(json, HomeBean.class);

            adapter = new HomeAdapter(context,homeBean.getResult());
            rvHome.setAdapter(adapter);
            rvHome.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        }

    }
}
