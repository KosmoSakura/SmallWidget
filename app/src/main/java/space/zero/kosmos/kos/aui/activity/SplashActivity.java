package space.zero.kosmos.kos.aui.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
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

import space.zero.kosmos.R;
import space.zero.kosmos.mos.base.BaseActivity;
import space.zero.kosmos.mos.tools.CodeUtils;
import space.zero.kosmos.mos.tools.IOUtils;
import space.zero.kosmos.mos.tools.base.Code;
import space.zero.kosmos.mos.tools.glide.GlideUtils;

import static space.zero.kosmos.mos.tools.base.Code.Config.BASE_PATH;
import static space.zero.kosmos.mos.tools.photos.PermissionUtil.verifyPermissions;

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
    }

    @Override
    protected void onResume() {
        super.onResume();


        /*  <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-feature android:name="android.hardware.camera" />*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (verifyPermissions(SplashActivity.this
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA)) {
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

        String pkName = getPackageName();
        CodeUtils.showAcknowDialog(this, pkName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        startAnim();
    }

    private void startAnim() {
        ObjectAnimator outAnimation = ObjectAnimator.ofFloat(lay, "alpha", 0f, 1f);
        outAnimation.setDuration(1000);
        outAnimation.start();
        ObjectAnimator showAnimation = ObjectAnimator.ofFloat(splasher, "alpha", 0f, 1.0f);
        final ObjectAnimator rotationAnimation = ObjectAnimator.ofFloat(splasher, "rotation", 0, 360);
        rotationAnimation.setInterpolator(new LinearInterpolator());//not stop
        rotationAnimation.setRepeatCount(-1);//set repeat time forever
        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(outAnimation).before(showAnimation).with(rotationAnimation);
        animatorSet.playTogether(showAnimation, rotationAnimation);
        animatorSet.setDuration(3000);
        animatorSet.start();

        splasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator outAnimation = ObjectAnimator.ofFloat(lay, "alpha", 1f, 0f);
                animatorSet.playTogether(outAnimation, rotationAnimation);
                animatorSet.setDuration(500);
                animatorSet.start();

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
