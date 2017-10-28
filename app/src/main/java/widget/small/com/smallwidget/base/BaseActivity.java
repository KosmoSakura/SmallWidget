package widget.small.com.smallwidget.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import greendao.bean.Themes;
import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.db.DaoTheme;
import widget.small.com.smallwidget.tools.SystemBarTintManager;
import widget.small.com.smallwidget.tools.base.Code;



public abstract class BaseActivity extends AppCompatActivity {

/*
    private Intent serviceIntent;
    private TimeService binder;
*/



    //里面做接收跳转信息初始化等操作
    protected void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }

    }
    protected abstract int getLayoutResId();//所有子类必须实现，绑定Act视图

    protected abstract void initView();//所有子类必须实现，里面做页面初始化，找id，等操作

    protected abstract void initListener();//所有子类必须实现，里面做监听器的设置。

    protected abstract void initData();//所有子类必须实现，里面做数据方面等操作。

    //消息传到机制
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //屏幕常亮
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //全屏显示
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        //计时器服务
     /*   serviceIntent = new Intent(this, TimeService.class);
        bindService(serviceIntent, conn, Context.BIND_AUTO_CREATE);*/
        setContentView(getLayoutResId());

    }

    /**
     * 泛型转换findViewById（）方法
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) findViewById(resId);
    }
    /**
     * 计时器服务
     */
 /*   private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = ((TimeService.TimeBinder) service).getMyService();

            if (binder != null) {
                binder.setTimeChanged(new TimeService.OnTimeChanged() {
                    @Override
                    public void onTimeChanged(long time) {
                        Logger.kosmos_d("时间：" + time + "s");
//                        Zero("Act数目：" + baseApp.activityCount + "\n后台：" + isAppForeground());
                    }
                });
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };*/

    /**
     * 判断app是否处于前台
     */
    public boolean isAppForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (Code.Config.activityCount > 0 && processInfo.processName.equals(getPackageName()) &&
                processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                ZeroI("栈包名：" + processInfo.processName + "---App包名：" + getPackageName() + "......." + processInfo.importance);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initIntent(getIntent());
        initData();
        initListener();
    }


    @Override
    public void onStart() {
        super.onStart();
        Code.Config.activityCount++;
    }

    @Override
    public void onStop() {
        super.onStop();
        Code.Config.activityCount--;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeRunActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().addRunActivity(this);//添加act
    }


    /**
     * 统一左上角结束页面功能
     *
     * @param view
     */

    public void onEventClick(View view) {
    }


//    /**
//     * 统一结束页面动画，与进入动画相反
//     */
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.sys_push_right_in, R.anim.sys_push_right_out);
//    }
//
//
//    /**
//     * @param intent 统一启动页面动画，与结束动画相反
//     */
//    @Override
//    public void startActivity(Intent intent) {
//        // TODO Auto-generated method stub
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.sys_push_left_in, R.anim.sys_push_left_out);
//    }
//
//    /**
//     * 统一启动页面动画，与结束动画相反
//     *
//     * @param intent
//     * @param requestCode
//     */
//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        // TODO Auto-generated method stub
//        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.sys_push_left_in, R.anim.sys_push_left_out);
//    }


    /**
     * @param color 状态栏
     */
    public void setStatusBarColor(Activity act, int color) {
        int colorBar = -1;
        if (color == Code.System.DefaultColor) {
            Themes theme = null;
            try {
                theme = DaoTheme.getInstance().getUserTheme();
                colorBar = theme.getBase_color();
            } catch (Exception e) {
                colorBar = R.color.default_blue;
            }

        } else if (color == Code.System.Immersive) {
            colorBar = Color.TRANSPARENT;
        } else if (color == Code.System.DefaultDark) {
            colorBar = R.color.default_main;
        } else {
            colorBar = color;
        }

//        Logger.kosmos_d("当前版本：" + Build.VERSION.SDK_INT + "\n比较版本：" + Build.VERSION_CODES.KITKAT);
        //android版本：4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = act.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(act);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setStatusBarTintResource(colorBar);
    }


}
