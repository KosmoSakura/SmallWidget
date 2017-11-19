package widget.small.com.smallwidget.helper.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import greendao.bean.Weight;
import greendao.dao.WeightDao;
import widget.small.com.smallwidget.helper.base.App;
import widget.small.com.smallwidget.helper.tools.TxtUtil;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/3/23 002315:48
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class DaoWeight {
    private static class SingletonHolder {
        private static final DaoWeight INSTANCE = new DaoWeight();
    }

    public static final DaoWeight getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DaoWeight() {
    }

    //---增删改查-------------------------------------------------------------------------------------------

    /**
     * 修改
     */
    public void update(Weight load) {
        getNoteDao().update(load);
    }

    /**
     * 添加数据库
     */
    public synchronized void add(Weight note) {
        getNoteDao().insert(note);
    }

    /**
     * 检查记录文件是否为空
     * 清除为空的记录
     */
    public boolean checkEmpt() {
        boolean isEmpt = true;
        List<Weight> list = DaoWeight.getInstance().searchAll();
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Weight video = list.get(i);
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

    public void addList(List<Weight> list) {
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    public long getDbSize() {
        List<Weight> list = searchAll();
        if (TxtUtil.isEmpty(list)) {
            return 0;
        } else {
            return list.size();
        }
    }

    public List<String> searchAllGroup() {
        Set<String> set = new HashSet<>();
        List<Weight> list = searchAll();
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                set.add(list.get(i).getName());
            }
            List<String> strings = new ArrayList<>();
            for (String str : set) {
                strings.add(str);
            }
            set.clear();
            set = null;
            list.clear();
            list = null;
            return strings;
        } else return null;
    }

    public String getDefaultName() {
        String name = "";
        List<Weight> list = searchAll();
        if (!TxtUtil.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDefaultVaule() != null && list.get(i).getDefaultVaule()) {
                    name = list.get(i).getName();
                    break;
                }
            }
        }
        return name;
    }

    public void setDefaultName(String name) {
        List<Weight> weights = searchAll();
        for (int i = 0; i < weights.size(); i++) {
            Weight weight = weights.get(i);
            weight.setDefaultVaule(weight.getName().equalsIgnoreCase(name));
            update(weight);
        }
    }


    /**
     * 删除全部
     */
    public synchronized void deleteNameAll(String name) {
        Query<Weight> query = getNoteDao().queryBuilder()
            .where(WeightDao.Properties.Name.eq(name))
            .build();
        List<Weight> temp = query.list();
        getNoteDao().deleteInTx(temp);
    }

    public synchronized void deleteAll() {
        getNoteDao().deleteAll();//全部删除
    }

    /**
     * 删除某一条
     */
    public synchronized void deleteOne(long ids) {
        getNoteDao().deleteByKey(ids);
    }

    public synchronized List<Weight> searchByName(String name) {
        Query<Weight> query = getNoteDao().queryBuilder()
            .where(WeightDao.Properties.Name.eq(name))
            .orderDesc(WeightDao.Properties.Year)
            .orderDesc(WeightDao.Properties.Month)
            .orderDesc(WeightDao.Properties.Day)
            .orderDesc(WeightDao.Properties.Hour)
            .orderDesc(WeightDao.Properties.Month)
            .build();
        List<Weight> temp = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return temp;
    }

    //---获取-------------------------------------------------------------------------------------------
    public synchronized List<Weight> searchAll() {
//        List<Weight> list = getNoteDao().loadAll();
        Query<Weight> query = getNoteDao().queryBuilder()
            .orderDesc(WeightDao.Properties.Year)
            .orderDesc(WeightDao.Properties.Month)
            .orderDesc(WeightDao.Properties.Day)
            .orderDesc(WeightDao.Properties.Hour)
            .orderDesc(WeightDao.Properties.Month)
            .build();
        List<Weight> list = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return list;
    }

    private WeightDao getNoteDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return App.getInstance().getDaoSession().getWeightDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return App.getInstance().getDb();
    }
}
