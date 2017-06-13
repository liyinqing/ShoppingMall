package com.atguigu.shop.utils.zxing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atguigu.shop.R;
import com.atguigu.shop.controller.home.adapter.HomeAdapter;
import com.atguigu.shop.model.NetManager;
import com.atguigu.shop.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.shop.utils.zxing.encoding.EncodingUtils.createQRCode;

/**
 * 生成二维码
 */
public class CreateQRCodeActivity extends AppCompatActivity {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.activity_shared)
    RelativeLayout activityShared;
    /**
     * 图片地址
     */
    private String figure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        ButterKnife.bind(this);
        getData();
        //创建二维码
    }

    private void getData() {
        figure = getIntent().getStringExtra(HomeAdapter.FIGURE);
        figure = NetManager.BASE_URL_IMAGE + figure;

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Bitmap myBitmap = Glide.with(CreateQRCodeActivity.this)
                            .load(figure)
                            .asBitmap() //必须
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    Message message = Message.obtain();
                    message.obj = myBitmap;
                    handler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            if (bitmap != null) {
                create(bitmap);
            }

        }
    };


    /**
     * 创建二维码并将图片保存在本地
     */
    private void create(Bitmap myBitmap) {
        int width = DensityUtil.dip2px(this, 200);

        Bitmap bitmap = createQRCode(figure,
                width, width, myBitmap);
        //图片
        ivShare.setImageBitmap(bitmap);

        //把二维码图片保存到本地
        saveBitmap(bitmap);

    }

    /**
     * 将Bitmap保存在本地
     *
     * @param bitmap
     */
    public void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "zxing_image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "zxing_image" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + "/sdcard/namecard/")));
    }

    @OnClick(R.id.ibn_back)
    public void onClick() {
        finish();
    }
}
