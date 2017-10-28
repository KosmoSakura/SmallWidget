package widget.small.com.smallwidget.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.BaseActivity;
import widget.small.com.smallwidget.tools.IOUtils;
import widget.small.com.smallwidget.tools.base.Code;
import widget.small.com.smallwidget.tools.glide.GlideUtils;

import static widget.small.com.smallwidget.tools.base.Code.Config.BASE_PATH;
import static widget.small.com.smallwidget.tools.photos.PermissionUtil.verifyPermissions;

public class SplashActivity extends BaseActivity {
    private ImageView splasher;
    private RelativeLayout lay;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        splasher = findView(R.id.splash_image);
        lay = findView(R.id.splash_image_lay);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (verifyPermissions(SplashActivity.this
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                initPathDatas(); //初始化设备信息
            }
        } else {
            initPathDatas(); //初始化设备信息
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        initView();
    }

    private void initPathDatas() {
        BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        Code.Config.CACHE_PATH = BASE_PATH + "Kosmos";//项目缓存SD卡路径的目录，主要用于缓存
        Code.Config.SAVE_SD_FLODER = Code.Config.CACHE_PATH + File.separator + "Filecache" + File.separator;//文件缓存SD卡路径的目录，主要用于缓存
        Code.Config.GLIDE_CACHE = Code.Config.CACHE_PATH + File.separator + "GlideCache" + File.separator;//图像缓存SD卡路径的目录，主要用于缓存
        Code.Config.IMAGE_DOWNLOAD = Code.Config.CACHE_PATH + File.separator + "Pictures" + File.separator;//图像下载地址

        IOUtils.checkFolderExists(Code.Config.CACHE_PATH);
        IOUtils.checkFolderExists(Code.Config.SAVE_SD_FLODER);
        IOUtils.checkFolderExists(Code.Config.GLIDE_CACHE);
        IOUtils.checkFolderExists(Code.Config.IMAGE_DOWNLOAD);
        GlideUtils.loadSplashImage(R.drawable.zero_reds, splasher);

        startAnim();
    }

    private void startAnim() {
        ObjectAnimator outAnimation = ObjectAnimator.ofFloat(lay, "alpha", 0f, 1f);
        outAnimation.setDuration(1000);
        outAnimation.start();
        ObjectAnimator showAnimation = ObjectAnimator.ofFloat(splasher, "alpha", 0f, 1.0f);
        ObjectAnimator rotationAnimation = ObjectAnimator.ofFloat(splasher, "rotation", 0, 360);
        rotationAnimation.setInterpolator(new LinearInterpolator());//not stop
        rotationAnimation.setRepeatCount(-1);//set repeat time forever
        final AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(outAnimation).before(showAnimation).with(rotationAnimation);
        animatorSet.playTogether(showAnimation, rotationAnimation);
        animatorSet.setDuration(3000);
        animatorSet.start();

        splasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator outAnimation = ObjectAnimator.ofFloat(lay, "alpha", 1f, 0f);
                outAnimation.setDuration(1000);
                outAnimation.start();
                outAnimation.addListener(new Animator.AnimatorListener() {
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
        });

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {

    }


}
