package atguigu.com.shoppingmall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.shoppingmall.base.BaseFragment;
import atguigu.com.shoppingmall.community.CommunityFragment;
import atguigu.com.shoppingmall.home.fragment.HomeFragment;
import atguigu.com.shoppingmall.shoppingcart.ShoppingCartFragment;
import atguigu.com.shoppingmall.type.TypeFragment;
import atguigu.com.shoppingmall.user.UserFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    ArrayList<BaseFragment> baseFragments;

    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();

        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        position = 0 ;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2 ;
                        break;
                    case R.id.rb_cart:
                        position = 3 ;
                        break;
                    case R.id.rb_user:
                        position = 4 ;
                        break;
                }
                BaseFragment currentFragment = baseFragments.get(position);
                switchFragment(currentFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(BaseFragment currentFragment) {
        if(tempFragment != currentFragment){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main, currentFragment);
            }else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            ft.commit();
            tempFragment = currentFragment;
        }
    }

    private void initFragment() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());
        baseFragments.add(new TypeFragment());
        baseFragments.add(new CommunityFragment());
        baseFragments.add(new ShoppingCartFragment());
        baseFragments.add(new UserFragment());
    }
}
