package widget.small.com.smallwidget.helper.tools.photos;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;

import widget.small.com.smallwidget.helper.base.App;
import widget.small.com.smallwidget.helper.tools.TxtUtil;
import widget.small.com.smallwidget.helper.tools.base.Code;


/**
 * Description:图片采集器
 * <p>
 * Author: Kosmos
 * Time: 2017/7/25 002514:09
 * Email:ZeroProject@foxmail.com
 * Events:嗯嗯，名字一定要高大上
 */
public class ImageCollector {
    //拍照后相片path
    private String imagePath;
    private Activity activity;
    private Handler handler;
    private static final String EXTRA_RESULT = "select_result";

    ImageCollector(Activity act) {
        this.activity = act;
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                listener.onImageCollected(TxtUtil.isNull((String) msg.obj));
            }

        };
    }


    void getCameraPhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.verifyPermissions(activity, Manifest.permission.CAMERA)) {
                openCamera();
            }
        } else {
            openCamera();
        }
    }

    void getLocalPhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.verifySDCardPermissions(activity)) {
                openAlbum();
            }
        } else {
            openAlbum();
        }
    }


    private void openCamera() {
        imagePath = Code.Config.IMAGE_DOWNLOAD + System.currentTimeMillis() + ".jpg";
        File outputImage = new File(imagePath);
        //拍照后相片的Uri
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(App.getInstance(), "widget.small.com.smallwidget.FileProvider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机
        Intent intent = new Intent();
//        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, Code.System.TAKE_PHOTO);
    }

    private void openAlbum() {
        PhotoPickerIntent photoPickerIntent = new PhotoPickerIntent(activity);
        photoPickerIntent.setSelectModel(SelectModel.SINGLE);
        photoPickerIntent.setShowCarema(false); // 是否显示拍照
        photoPickerIntent.setMaxTotal(1); // 最多选择照片数量，默认为9

//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");

//        intent.setAction("android.intent.action.GET_CONTENT");
//        intent.setType("image/jpeg");
        activity.startActivityForResult(photoPickerIntent, Code.System.CHOOSE_PHOTO);
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Code.System.TAKE_PHOTO:
                if (resultCode == Code.System.Photo_Back) {
                    handler.sendMessage(handler.obtainMessage(Code.System.TAKE_PHOTO + 5, imagePath));
                }
                break;
            case Code.System.CHOOSE_PHOTO:
                if (resultCode == Code.System.Photo_Back) {
                    ArrayList<String> list = data.getStringArrayListExtra(EXTRA_RESULT);
                    handler.sendMessage(handler.obtainMessage(Code.System.CHOOSE_PHOTO + 5, list.get(0)));
//                    String path = "";
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        //4.4及以上系统使用这个方法处理图片
//                        path = handleImageOnKitKat(data);
//                    } else {
//                        //4.4以下使用这个方法处理图片
//                        path = handleImageBeforeKitKat(data);
//                    }
//                    handler.sendMessage(handler.obtainMessage(System.System.CHOOSE_PHOTO + 5, path));
                }
                break;
        }
    }


    private String handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        return getImagePath(uri, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String handleImageOnKitKat(Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(activity, uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath = getImagePath(contenturi, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，使用普通方法处理
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是File类型的Uri，直接获取图片路径即可
            imgPath = uri.getPath();
        }
        return imgPath;
    }

    //通过Uri和selection来获取真实的图片路径
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    void setOnImageCollectListener(ImageCollectListener listener) {
        this.listener = listener;
    }

    private ImageCollectListener listener;

    interface ImageCollectListener {
        void onImageCollected(String image_path);
    }

}
