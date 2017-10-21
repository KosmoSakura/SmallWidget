package widget.small.com.smallwidget.service;

/**
 * Description:屏保计时服务
 * <p>
 * Author: Kosmos
 * Time: 2017/3/8 000815:53
 * Email:ZeroProject@foxmail.com
 * Events:
 */

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service {
    private long defaultValue = 4 + 1;//屏保等待时间:单位-秒（+1s初始值）
    private long time;
    //    private Timer t;
//    private TimerTask tt;
    private CountDownTimer timer;

    @SuppressLint("HandlerLeak")
    private Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            time -= 1;
            if (timeChanged != null) {
                timeChanged.onTimeChanged(time);
            }
        }

    };

    public interface OnTimeChanged {
        void onTimeChanged(long time);
    }

    private OnTimeChanged timeChanged;

    public void setTimeChanged(OnTimeChanged timeChanged) {
        this.timeChanged = timeChanged;
    }

    public class TimeBinder extends Binder {
        public TimeService getMyService() {
            return TimeService.this;
        }
    }


    public void reSetService() {
        time = defaultValue;
    }

    /**
     * 计时器开始工作
     */
    @Override
    public IBinder onBind(Intent intent) {
        time = defaultValue;
//        t = new Timer();
//        tt = new TimerTask() {
//            @Override
//            public void run() {
//                han.sendEmptyMessage(0x01);
//            }
//        };
//        t.schedule(tt, 0, 1000);

        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                han.sendEmptyMessage(0x01);
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
        return new TimeBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        clearTimer();
        super.onDestroy();
    }

    /**
     * 清理计时器
     */
    private void clearTimer() {
//        if (tt != null) {
//            tt.cancel();
//            tt = null;
//        }
//        if (t != null)
//            t.cancel();
//        t = null;
        timer.cancel();
        timer = null;
    }

    public void kosmosLog(String msg) {
        Log.v("kosmos", msg);
    }
}