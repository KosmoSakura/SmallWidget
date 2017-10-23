package widget.small.com.smallwidget.activity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.BaseActivity;
import widget.small.com.smallwidget.tools.glide.GlideUtils;

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

    @Override
    protected void initData() {
        alpha = 0;
        findView(R.id.ssss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation showAnimation = new AlphaAnimation(0, 1);
                showAnimation.setDuration(2000);

                splasher.startAnimation(showAnimation);
                showAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        AlphaAnimation dismisAnimation = new AlphaAnimation(1, 0);
                        dismisAnimation.setDuration(2000);
                        splasher.startAnimation(dismisAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        getMoreClick();

    }

    SeekBar seekBar;
    TextView texts;

    private void getMoreClick() {
        seekBar = findView(R.id.pb);
        texts = findView(R.id.text);
        findView(R.id.ssss20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation showAnimation = new AlphaAnimation(0, 1);
                showAnimation.setDuration(2000);
                showAnimation.setFillAfter(true);
                splasher.startAnimation(showAnimation);
            }
        });
        findView(R.id.ssss50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation dismisAnimation = new AlphaAnimation(1, 0);
                dismisAnimation.setDuration(2000);
                splasher.startAnimation(dismisAnimation);
            }
        });
        findView(R.id.ssss100).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(100);
                setAlph(100);
            }
        });
        findView(R.id.ssss200).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(200);
                setAlph(200);
            }
        });
        findView(R.id.ssss240).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setProgress(240);
                setAlph(240);
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                texts.setText("-->:" + progress + "---------FU:" + fromUser);
                setAlph(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setAlph(int x) {
//        splasher.getBackground().mutate().setAlpha(x);
    }


}
