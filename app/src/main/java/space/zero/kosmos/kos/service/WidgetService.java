package space.zero.kosmos.kos.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import space.zero.kosmos.mos.tools.base.Code;

/*
 * @author : skywang <wangkuiwu@gmail.com>
 * description : 周期性更新AppWidget的服务
 */

public class WidgetService extends Service {


    // 周期性更新 widget 的周期
    private static final int UPDATE_TIME = 5000;
    // 周期性更新 widget 的线程
    private UpdateThread mUpdateThread;
    private Context mContext;
    // 更新周期的计数
    private int count = 0;

    @Override
    public void onCreate() {

        // 创建并开启线程UpdateThread
        mUpdateThread = new UpdateThread();
//        mUpdateThread.start();

        mContext = this.getApplicationContext();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // 中断线程，即结束线程。
        if (mUpdateThread != null) {
            mUpdateThread.interrupt();
        }

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * 服务开始时，即调用startService()时，onStartCommand()被执行。
     * onStartCommand() 这里的主要作用：
     * (01) 将 appWidgetIds 添加到队列sAppWidgetIds中
     * (02) 启动线程
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    private class UpdateThread extends Thread {

        @Override
        public void run() {
            super.run();

            try {
                count = 0;
                while (true) {
                    count++;
                    Intent updateIntent = new Intent(Code.Broadcast.ActionUpdateAll);
                    updateIntent.putExtra("weight", "112斤"+count);
                    mContext.sendBroadcast(updateIntent);
                    Thread.sleep(UPDATE_TIME);
                }
            } catch (InterruptedException e) {
                // 将 InterruptedException 定义在while循环之外，意味着抛出 InterruptedException 异常时，终止线程。
                e.printStackTrace();
            }
        }
    }

}
