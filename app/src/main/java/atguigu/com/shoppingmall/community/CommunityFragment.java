package atguigu.com.shoppingmall.community;


import android.view.View;
import android.widget.TextView;

import atguigu.com.shoppingmall.base.BaseFragment;

/**
 * 作者：李银庆 on 2017/6/11 22:54
 */
public class CommunityFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("发现");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
