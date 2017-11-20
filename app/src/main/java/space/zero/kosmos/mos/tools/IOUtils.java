package space.zero.kosmos.mos.tools;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.io.File;

/**
 * Description:IO数据操作
 * <p>
 * Author: Kosmos
 * Time: 2017/3/28 002809:49
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class IOUtils {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static boolean checkFolderExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdir();
        }
        return true;
    }

    /**
     * 权限通过返回true
     */
    public static boolean verifyStoragePermissions(Activity activity) {

        boolean st = checkPermisssions(activity);

        if (!st) {
            // 申请未有权限
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE);
        }
        return st;
    }

    /**
     * 检查已有权限
     */
    private static boolean checkPermisssions(Activity activity) {
        boolean st = false;
        for (int i = 0; i < PERMISSIONS_STORAGE.length; i++) {
            int status = ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[i]);
            if (status != PackageManager.PERMISSION_GRANTED) {
                st = false;
                break;
            } else {
                st = true;
            }
        }
        return st;
    }

    /**
     * 权限回调
     */
    public static void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.CustomShort("需要权限才能运行...");
                }

            }
        }
    }
    /**
     * 检查记录文件是否为空
     * 清除为空的记录
     * 文件不存在返回false
     */
    public static boolean checkPathEmpt(String path) {
        if (TxtUtil.isEmpty(path)) {
            return false;
        } else {
            File file = new File(path);
            if (file.exists()) {
                return true;
            } else return false;
        }
    }
}
