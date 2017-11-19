package widget.small.com.smallwidget.helper.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.helper.tools.base.Code;
import widget.small.com.smallwidget.helper.tools.SystemBarTintManager;


/**
 * Created by ZeroProject on 2016/8/26 0026 15:50
 * Email:ZeroProject@foxmail.com
 */
public abstract class BaseFragment extends Fragment {


    private View contentView;
    protected Context context;
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;


    protected abstract int getLayoutId();//所有子类必须实现，绑定Act视图

    protected void initReceiver() {

    }//里面做接收广播等操作

    protected abstract void initView(View view);//所有子类必须实现，里面做页面初始化，找id，等操作

    protected abstract void initListener();//所有子类必须实现，里面做监听器的设置。

    protected abstract void initData();//所有子类必须实现，里面做数据方面等操作。

    protected void initIntent() {

    }//里面做接收跳转信息初始化等操作

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getLayoutId(), container, false);
        initView(contentView);
        initIntent();
        initReceiver();
        initListener();

        return contentView;
    }

    /**
     * 泛型转换findViewById（）方法
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) contentView.findViewById(resId);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void setlazyLoadOff() {
        isLazy = false;
    }

    boolean isLazy = true;

    protected void lazyLoad() {
        if (isLazy) {
            if (!isVisible || !isPrepared) {
                return;
            }
        }

        initData();//数据请求
    }
    //不可见时
    protected void onInvisible() {
    }

    //可见时
    protected void onVisible() {
        lazyLoad();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }
    /**
     * @param color 状态栏
     */
    public void setStatusBarColor(Activity act, int color) {
        int colorBar = -1;
        if (color == Code.System.DefaultColor) {
            colorBar = R.color.default_blue;
        } else if (color == Code.System.Immersive) {
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
