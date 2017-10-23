package widget.small.com.smallwidget.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ImageView;

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


    @Override
    protected void initData() {
        ObjectAnimator showAnimation = ObjectAnimator.ofFloat(splasher, "alpha", 0f, 1.0f, 0f);
        ObjectAnimator dismisAnimation = ObjectAnimator.ofFloat(splasher, "rotation", 0, 360);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(showAnimation, dismisAnimation);
        animatorSet.setDuration(4000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
