package widget.small.com.smallwidget.business.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import greendao.bean.Themes;
import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.adapter.ThemeAdapter;
import widget.small.com.smallwidget.helper.base.BaseActivity;
import widget.small.com.smallwidget.business.bean.ThemeBean;
import widget.small.com.smallwidget.helper.db.DaoTheme;
import widget.small.com.smallwidget.helper.tools.ToastUtil;
import widget.small.com.smallwidget.helper.tools.TxtUtil;
import widget.small.com.smallwidget.helper.tools.base.Code;
import widget.small.com.smallwidget.helper.widget.NoScrollListView;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Email:ZeroProject@foxmail.com
 */
public class ThemeActivity extends BaseActivity {
    private NoScrollListView lv;
    private TextView save;

    private ThemeAdapter adapter;
    private List<ThemeBean> list;
    private DaoTheme theme;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        setStatusBarColor(this, Code.System.DefaultColor);

        lv = findView(R.id.theme_lv);
        save = findView(R.id.theme_save);

        list = new ArrayList<>();
        adapter = new ThemeAdapter(this);
        lv.setAdapter(adapter);

        theme = DaoTheme.getInstance();
        if (theme.checkEmpt()) {
            addDatas();
        }
    }

    private void addDatas() {
        List<Themes> listDb = new ArrayList<>();
        listDb.add(new Themes((long) 0, false, "主题暗红", Code.System.BASE + 1, R.color.red_dark));
        listDb.add(new Themes((long) 1, false, "主题藏蓝", Code.System.BASE + 2, R.color.default_blue));
        listDb.add(new Themes((long) 2, false, "主题深绿", Code.System.BASE + 3, R.color.android));
        listDb.add(new Themes((long) 3, false, "主题橘色", Code.System.BASE + 4, R.color.orange));
        listDb.add(new Themes((long) 4, false, "主题黑色", Code.System.BASE + 5, R.color.default_black));
        listDb.add(new Themes((long) 5, false, "主题粉色", Code.System.BASE + 6, R.color.pink));
        listDb.add(new Themes((long) 6, false, "主题深紫", Code.System.BASE + 7, R.color.default_main));
        theme.addList(listDb);
        listDb.clear();
        listDb = null;
    }


    @Override
    protected void initData() {
        List<Themes> themes = theme.searchAll();
        list.clear();
        if (!TxtUtil.isEmpty(themes)) {
            for (int i = 0; i < themes.size(); i++) {
                Themes eme = themes.get(i);
                list.add(new ThemeBean(eme.getSelect(), eme.getTheme_id(), eme.getName(), eme.getBase_color()));
            }
        }

        adapter.addList(list);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_theme;
    }


    @Override
    protected void initListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeBean bean = null;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSelect()) {
                        bean = list.get(i);
                        break;
                    }
                }
                if (bean != null) {
                    if (theme.save(bean.getTheme_id())) {
                        ToastUtil.CustomShort("保存成功");
                        finish();
                    } else {
                        ToastUtil.CustomShort("保存失败");
                    }

                } else {
                    ToastUtil.CustomShort("需要先选择主题");
                }
            }
        });

        adapter.setOnCheckedListener(new ThemeAdapter.OnCheckedListener() {
            @Override
            public void onCheckChenged(int theme_id) {
                ArrayList<ThemeBean> tempList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    ThemeBean dto = list.get(i);
                    if (dto.getTheme_id() == theme_id) {
                        dto.setSelect(true);
                    } else {
                        dto.setSelect(false);
                    }
                    tempList.add(dto);
                }
                adapter.addList(tempList);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
