package widget.small.com.smallwidget.business.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.activity.FaceActivity;
import widget.small.com.smallwidget.business.activity.ThemeActivity;
import widget.small.com.smallwidget.business.activity.TimeActivity;
import widget.small.com.smallwidget.helper.base.BaseFragment;
import widget.small.com.smallwidget.helper.db.DaoTheme;
import widget.small.com.smallwidget.helper.tools.CodeUtils;
import widget.small.com.smallwidget.helper.tools.ToastUtil;
import widget.small.com.smallwidget.helper.tools.VibrateHelp;

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
public class SettingFrag extends BaseFragment implements View.OnClickListener {
    private View time, theme, clear, more;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_setting;
    }

    @Override
    protected void initView(View view) {
        time = findView(R.id.setting_time);
        theme = findView(R.id.setting_theme);
        clear = findView(R.id.setting_card_3);
        more = findView(R.id.setting_card_4);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.setting_time://时光轴:
                CodeUtils.showEditDialog(getActivity(), "进入时光轴需要校验你的身份", "请输入密码", new CodeUtils.PasswordCheck() {
                    @Override
                    public void onResult(DialogInterface dialog, String password) {
                        if (password.equalsIgnoreCase("5277")) {
                            dialog.dismiss();
                            Intent intent = new Intent(context, TimeActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtil.CustomShort("密码错误", ToastUtil.SHOW_DEFAULT_ICON);
                            //10,200,10,200,10,200,10,200,400,200,10,200,10,200,10
                            VibrateHelp.vComplicated(getActivity(), new long[]{10, 200, 400, 200, 400, 200, 10, 200, 10, 200, 400, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10}, -1);
                        }
                    }
                });
                break;
            case R.id.setting_theme://主题
                intent = new Intent(context, ThemeActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_card_3://清空数据库
                DaoTheme.getInstance().deleteAll();
                ToastUtil.CustomShort("估计主题哪里现在应该正常了吧");
                break;
            case R.id.setting_card_4://
                intent = new Intent(context, FaceActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initListener() {
        time.setOnClickListener(this);
        theme.setOnClickListener(this);
        clear.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


}
