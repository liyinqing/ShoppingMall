package atguigu.com.shoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class welcomeActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomeActivity.this,MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity(new Intent(this,MainActivity.class));
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
