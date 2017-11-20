package space.zero.kosmos.kos.aui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.vision.face.FaceDetector;

import java.io.InputStream;

import widget.small.com.smallwidget.R;
import space.zero.kosmos.mos.base.BaseActivity;
import space.zero.kosmos.mos.tools.glide.GlideUtils;
import space.zero.kosmos.mos.tools.photos.ImageCollectorHelper;
import space.zero.kosmos.mos.widget.FaceOverlayView;

/**
 * @Description:
 * @Author: Kosmos
 * @Email:ZeroProject@foxmail.com
 */
public class FaceActivity extends BaseActivity {
    private FaceOverlayView mFaceOverlayView;
    private ImageView face_show;

    private ImageCollectorHelper helper;

    @Override
    protected void initView() {
        mFaceOverlayView = findView(R.id.face_overlay);
        face_show = findView(R.id.face_show);
    }


    @Override
    protected void initData() {
        InputStream stream = getResources().openRawResource(R.raw.face);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
//        face_show.setImageBitmap(bitmap);
        mFaceOverlayView.setBitmap(bitmap);
        GlideUtils.loadNormalPic(this, R.raw.face, face_show);

        FaceDetector detector = new FaceDetector.Builder(this)
            .setTrackingEnabled(true)
            .setLandmarkType(FaceDetector.ALL_LANDMARKS)
            .setMode(FaceDetector.ACCURATE_MODE)
            .build();
    }

    @Override
    protected void initListener() {
        helper = new ImageCollectorHelper(this, new ImageCollectorHelper.ImageCollectListener() {
            @Override
            public void onImageCollected(String image_path) {
                GlideUtils.loadNormalPic(FaceActivity.this, image_path, face_show);
            }
        });
        face_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.showCollectorPop();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        helper.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_face;
    }

}
