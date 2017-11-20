package space.zero.kosmos.kos.aui.fragment;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import space.zero.kosmos.kos.aui.activity.ThemeActivity;
import space.zero.kosmos.kos.aui.adapter.binder.FunctionItemBinder;
import space.zero.kosmos.kos.bean.MainItemBean;
import space.zero.kosmos.mos.base.BaseFragment;
import space.zero.kosmos.mos.db.DaoTheme;
import space.zero.kosmos.mos.tools.ToastUtil;
import space.zero.kosmos.mos.tools.base.Code;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 * <p>
 * 为Android提供IOS平台自有的界面视图切换动画而开发此库，工作量也不小，感谢支持SwitchLayout
 * <p>
 * 如果想自定义特效动画时长的话，请在此四个变量对应设置 SwitchLayout.animDuration = 1000;
 * SwitchLayout.longAnimDuration = 2000; BaseAnimViewS.animDuration = 1000;
 * BaseAnimViewS.longAnimDuration = 2000;即可。单位毫秒。
 * <p>
 * 以后SwitchLayout将会划分入我的SmartUI库下面
 */
public class SettingFrag extends BaseFragment {
    private List<MainItemBean> rvList;
    private FunctionItemBinder binder;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_setting;
    }

    @Override
    protected void initView(View view) {
        rvListView = findView(R.id.rv_main_list);

        rvList = new ArrayList<>();
        binder = new FunctionItemBinder(getActivity());
        recInit(2, MainItemBean.class, binder);
    }


    @Override
    protected void initListener() {
        binder.setEventListener(new FunctionItemBinder.EventListener() {
            @Override
            public void onItemClick(int position) {
                if (position > -1 && position < rvList.size()) {
                    int id = rvList.get(position).getId();
                    Intent intent = null;
                    switch (id) {
                        case Code.StatusCode.Setting_To_Theme:
                            intent = new Intent(context, ThemeActivity.class);
                            startActivity(intent);
                            break;
                        case Code.StatusCode.Setting_to_ResetData:
                            DaoTheme.getInstance().deleteAll();
                            ToastUtil.CustomShort("估计主题哪里现在应该正常了吧");
                            break;
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        rvList.add(new MainItemBean(R.drawable.ic_theme_g, "主题", Code.StatusCode.Setting_To_Theme));
        rvList.add(new MainItemBean(R.drawable.ic_data_g, "重置数据", Code.StatusCode.Setting_to_ResetData));
        recNotifyData(rvList);
    }


}
