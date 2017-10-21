package widget.small.com.smallwidget.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import greendao.bean.Themes;
import greendao.dao.ThemesDao;
import widget.small.com.smallwidget.base.App;
import widget.small.com.smallwidget.tools.TxtUtil;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/3/23 002315:48
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class DaoTheme {
    private static class SingletonHolder {
        private static final DaoTheme INSTANCE = new DaoTheme();
    }

    public static final DaoTheme getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DaoTheme() {
    }

    //---增删改查-------------------------------------------------------------------------------------------

    /**
     * 查询是否有记录
     */
    public Themes getUserTheme() {
        Themes theme = null;
        List<Themes> list = DaoTheme.getInstance().searchAll();
        if (TxtUtil.isEmpty(list)) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSelect()) {
                    return list.get(i);
                }
            }
        }
        return theme;
    }

    /**
     * 保存
     */
    public boolean  save(int theme_id) {
        try {
            List<Themes> list = searchAll();
            for (int i = 0; i < list.size(); i++) {
                Themes theme = list.get(i);
                if (theme.getTheme_id() == theme_id) {
                    theme.setSelect(true);
                } else {
                    theme.setSelect(false);
                }
                update(theme);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查记录文件是否为空
     * 清除为空的记录
     */
    public boolean checkEmpt() {
        boolean isEmpt = true;
        List<Themes> list = DaoTheme.getInstance().searchAll();
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Themes video = list.get(i);
                if (video != null) {
                    isEmpt = false;
                    break;
                } else {
                    isEmpt = true;
                }
            }
        } else {
            isEmpt = true;
        }
        return isEmpt;
    }

    public void addList(List<Themes> list) {
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    /**
     * 添加数据库
     */
    public synchronized void add(Themes note) {
        getNoteDao().insert(note);
    }

    public synchronized Themes search(int Theme_id) {
        Query<Themes> query = getNoteDao().queryBuilder()
            .where(ThemesDao.Properties.Theme_id.eq(Theme_id))
            .build();
        List<Themes> temp = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        if (temp.size() != 0) {
            return temp.get(0);
        } else return null;
    }

    public synchronized List<Themes> searchAll() {
        List<Themes> list = getNoteDao().loadAll();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return list;
    }

    /**
     * 删除全部
     */
    public synchronized void deleteAll() {
        getNoteDao().deleteAll();//全部删除
    }

    /**
     * 删除某一条
     */
    public synchronized void deleteOne(int ids) {
        Themes video = search(ids);
        getNoteDao().delete(video);
    }

    /**
     * 修改
     */
    public synchronized void update(Themes load) {
        getNoteDao().update(load);
    }

    //---获取-------------------------------------------------------------------------------------------
    private ThemesDao getNoteDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return App.getInstance().getDaoSession().getThemesDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return App.getInstance().getDb();
    }
}
