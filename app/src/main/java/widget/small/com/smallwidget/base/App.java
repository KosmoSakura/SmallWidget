package widget.small.com.smallwidget.base;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

import greendao.dao.DaoMaster;
import greendao.dao.DaoSession;
import widget.small.com.smallwidget.constants.Constants;
import widget.small.com.smallwidget.net.PersistentCookieStore;
import widget.small.com.smallwidget.tools.logger.Logger;

import static widget.small.com.smallwidget.constants.Constants.SAVE_SD_FLODER;
import static widget.small.com.smallwidget.tools.IOUtils.checkFolderExists;

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

        instance = this;
        Logger.init("Logger");
        cookiePrefs = getApplicationContext().getSharedPreferences(PersistentCookieStore.COOKIE_PREFS, 0);
        initPathDatas();
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

    private void initPathDatas() {
        SAVE_SD_FLODER = Environment//图像缓存SD卡路径的目录，主要用于缓存
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "rato" + File.separator + "filecache" + File.separator;
        checkFolderExists(SAVE_SD_FLODER);

        Constants.phoneMODEL = Build.MODEL;//获取手机型号
    }

    /**
     * 循环遍历 退出所有Act
     */
    public void exitApp() {
        if (this.runActivity != null) {
            for (Activity act : this.runActivity) {
                act.finish();
            }
        }
    }

    /**
     * 添加act
     */
    public void addRunActivity(Activity _value) {
        if (this.runActivity == null) {
            this.runActivity = new ArrayList<Activity>();
        }
        if (!this.runActivity.contains(_value)) {
            this.runActivity.add(_value);
        }
    }

    /**
     * 移除act
     */
    public void removeRunActivity(Activity _value) {
        if (this.runActivity != null) {
            this.runActivity.remove(_value);
        }
    }


    public ArrayList<Activity> getRunActivity() {
        if (this.runActivity == null) {
            this.runActivity = new ArrayList<Activity>();
        }
        return this.runActivity;
    }

    public void setRunActivity(ArrayList<Activity> runActivity) {
        this.runActivity = runActivity;
    }

    public void releaseData() {
        if (this.runActivity != null) {
            this.runActivity.clear();
            this.runActivity = null;
        }

    }
}
