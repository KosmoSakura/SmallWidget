package widget.small.com.smallwidget.base;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import autolayout.config.AutoLayoutConifg;
import greendao.dao.DaoMaster;
import greendao.dao.DaoSession;
import widget.small.com.smallwidget.net.PersistentCookieStore;
import widget.small.com.smallwidget.tools.logger.Logger;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/4/12 001213:51
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class App extends Application {
    private static App instance;
    public static SharedPreferences cookiePrefs;
    private ArrayList<Activity> runActivity = new ArrayList<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();
        //默认使用的高度是设备的可用高度，也就是不包括状态栏和底部的操作栏的，如果你希望拿设备的物理高度进行百分比化
        AutoLayoutConifg.getInstance().useDeviceSize();
        instance = this;
        Logger.init("Logger");
        cookiePrefs = getApplicationContext().getSharedPreferences(PersistentCookieStore.COOKIE_PREFS, 0);
        setDatabase();
    }

    public static App getInstance() {
        return instance;
    }

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    /**
     * 初始化数据库
     */
    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "rato-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}
