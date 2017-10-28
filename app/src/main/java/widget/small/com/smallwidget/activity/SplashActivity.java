package widget.small.com.smallwidget.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.BaseActivity;
import widget.small.com.smallwidget.tools.glide.GlideUtils;
import widget.small.com.smallwidget.tools.logger.Logger;

public class SplashActivity extends BaseActivity {
    private ImageView splasher;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        splasher = findView(R.id.splash_image);
        GlideUtils.loadToastImage(R.drawable.zero_move, splasher);
    }

    @Override
    protected void initListener() {

    }

    private int alpha;
    private int x;

    @Override
    protected void initData() {
        @SuppressLint("HandlerLeak") final Handler hans = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                int what = msg.what;
                if (what == 1) {
                    alpha += 10;
                } else {
                    alpha -= 10;
                }
                if (alpha > 20) {
                    splasher.setVisibility(View.VISIBLE);
                } else {
                    splasher.setVisibility(View.GONE);
                }
                splasher.getBackground().mutate().setAlpha(alpha);
            }
        };
        alpha = 0;
        splasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.kosmos_d("-++->" + x);
                Log.d("Kosmos", "-+-+->" + x);
                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long l) {
                        x++;
                        Log.d("Kosmos", "-++->" + x);
                        Logger.kosmos_d("-->" + x);
                        //0--255--0
                        //510
                        //2550
                        if (l > 2550) {
                            hans.sendEmptyMessage(1);
                        } else {
                            hans.sendEmptyMessage(2);
                        }
                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }.start();
            }
        });


    }
}
