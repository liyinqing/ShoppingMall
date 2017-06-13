package atguigu.com.shoppingmall.user;


import android.view.View;
import android.widget.TextView;

import atguigu.com.shoppingmall.base.BaseFragment;

/**
 * 作者：李银庆 on 2017/6/11 22:54
 */
public class UserFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("用户");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
