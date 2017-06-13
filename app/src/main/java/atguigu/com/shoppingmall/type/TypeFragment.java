package atguigu.com.shoppingmall.type;


import android.view.View;
import android.widget.TextView;

import atguigu.com.shoppingmall.base.BaseFragment;

/**
 * 作者：李银庆 on 2017/6/11 22:54
 */
public class TypeFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("分页");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
