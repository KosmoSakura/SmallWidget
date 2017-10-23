package widget.small.com.smallwidget.tools.photos;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import widget.small.com.smallwidget.tools.ToastUtil;


/**
 * Description:权限检查
 * <p>
 * Author: Kosmos
 * Time: 2017/6/21 002113:41
 * Email:ZeroProject@foxmail.com
 * Events:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class PermissionUtil {
    public static final int PERMISSION_REQUEST_CODE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 权限通过返回true
     */
    public static boolean verifySDCardPermissions(Activity activity) {

        boolean st = checkPermisssions(activity, PERMISSIONS_STORAGE);

        if (!st) {
            // 申请未有权限
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                PERMISSION_REQUEST_CODE);
        }
        return st;
    }

    public static boolean verifyPermissions(Activity activity, String... permissions) {
        boolean st = checkPermisssions(activity, permissions);
        if (!st) {
            // 申请未有权限
            ActivityCompat.requestPermissions(activity, permissions,
                PERMISSION_REQUEST_CODE);
        }
        return st;
    }

    /**
     * 检查已有权限
     */
    private static boolean checkPermisssions(Activity activity, String... permissions) {
        boolean st = false;
        for (int i = 0; i < permissions.length; i++) {
            int status = ActivityCompat.checkSelfPermission(activity, permissions[i]);
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
    public static void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults, final Activity activity) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.ShortMessage("需要权限才能运行...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activity.finish();
                        }
                    }, 200);
                }
            }
        }
    }
}
