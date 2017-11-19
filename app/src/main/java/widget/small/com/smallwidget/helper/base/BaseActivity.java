package widget.small.com.smallwidget.helper.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import autolayout.AutoLayoutActivity;
import greendao.bean.Themes;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;
import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.helper.db.DaoTheme;
import widget.small.com.smallwidget.helper.tools.SystemBarTintManager;
import widget.small.com.smallwidget.helper.tools.base.Code;
import widget.small.com.smallwidget.helper.widget.adapterview.EmptyRecyclerView;
import widget.small.com.smallwidget.helper.widget.recyclerview.DividerItemDecoration;
import widget.small.com.smallwidget.helper.widget.switchlayout.BaseAnimViewS;
import widget.small.com.smallwidget.helper.widget.switchlayout.BaseEffects;
import widget.small.com.smallwidget.helper.widget.switchlayout.SwichLayoutInterFace;
import widget.small.com.smallwidget.helper.widget.switchlayout.SwitchLayout;


/**
 *
 */
public abstract class BaseActivity extends AutoLayoutActivity implements SwichLayoutInterFace {

/*
    private Intent serviceIntent;
    private TimeService binder;
*/
    /**
     * 顶部划入
     */
    protected final int Anim_Top_Slide = 1;

    /**
     * 左上角缩放
     */
    protected final int Anim_Left_Scale = 2;
    /**
     * 左侧中心旋转
     */
    protected final int Anim_Left_Rotate_Cneter = 3;
    /**
     * 左上角旋转
     */
    protected final int Anim_Left_Rotate_Top = 4;
    /**
     * 横向展开
     */
    protected final int Anim_Horizontal_Fold = 5;
    /**
     * 纵向展开
     */
    protected final int Anim_Vertical_Fold = 6;

    /**
     * 为Android提供IOS平台自有的界面视图切换动画而开发此库，工作量也不小，感谢支持SwitchLayout
     * <p>
     * 设置进入Activity的Activity特效动画，同理可拓展为布局动画
     * <p>
     * 如果想自定义特效动画时长的话，请在此四个变量对应设置即可。单位毫秒。
     * SwitchLayout.animDuration = 1000;
     * SwitchLayout.longAnimDuration = 2000;
     * BaseAnimViewS.animDuration = 1000;
     * BaseAnimViewS.longAnimDuration = 2000;
     * <p>
     * 以后SwitchLayout将会划分入我的SmartUI库下面
     */
    protected int initScreen() {
        SwitchLayout.animDuration = 800;
        BaseAnimViewS.animDuration = 800;
        SwitchLayout.longAnimDuration = 800;
        BaseAnimViewS.longAnimDuration = 800;
        return 0;
    }

    @Override
    public void onBackPressed() {
        if (initScreen() == 0) {
            super.onBackPressed();
        } else {
            setExitSwichLayout();
        }
    }

    protected abstract int getLayoutResId();//所有子类必须实现，绑定Act视图

    protected abstract void initView();//所有子类必须实现，里面做页面初始化，找id，等操作

    protected void initListener() {
    }


    protected abstract void initData();//所有子类必须实现，里面做数据方面等操作。

    //消息传到机制
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //屏幕常亮
//        this.getWindow().setFlags(WindowManager.AutoLayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.AutoLayoutParams.FLAG_KEEP_SCREEN_ON);
        //全屏显示
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.AutoLayoutParams.FLAG_FULLSCREEN, WindowManager.AutoLayoutParams.FLAG_FULLSCREEN);

//        //计时器服务
     /*   serviceIntent = new Intent(this, TimeService.class);
        bindService(serviceIntent, conn, Context.BIND_AUTO_CREATE);*/
        setContentView(getLayoutResId());

    }

    /**
     * 泛型转换findViewById（）方法
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) findViewById(resId);
    }

    /**
     * 计时器服务
     */
 /*   private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = ((TimeService.TimeBinder) service).getMyService();

            if (binder != null) {
                binder.setTimeChanged(new TimeService.OnTimeChanged() {
                    @Override
                    public void onTimeChanged(long time) {
                        Logger.kosmos_d("时间：" + time + "s");
//                        Zero("Act数目：" + baseApp.activityCount + "\n后台：" + isAppForeground());
                    }
                });
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };*/
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        if (initScreen() != 0) {
            setEnterSwichLayout();
        }
        initView();
        initIntent(getIntent());
        initData();
        initListener();
    }

    protected void initIntent(Intent intent) {

    }

    @Override
    public void setEnterSwichLayout() {
        // 三个参数分别为（Activity/View，是否关闭Activity，特效（可为空））
        switch (initScreen()) {
            case Anim_Top_Slide://顶部划入
                SwitchLayout.getSlideFromTop(this, false, BaseEffects.getReScrollEffect());
                break;
            case Anim_Left_Scale://左上角缩放
                SwitchLayout.ScaleBigLeftTop(this, false, null);
                break;
            case Anim_Left_Rotate_Cneter://左侧中心旋转
                SwitchLayout.RotateLeftCenterIn(this, false, null);
                break;
            case Anim_Left_Rotate_Top://左上角旋转
                SwitchLayout.RotateLeftTopIn(this, false, null);
                break;
            case Anim_Horizontal_Fold://横向展开
                SwitchLayout.ScaleToBigHorizontalIn(this, false, null);
                break;
            case Anim_Vertical_Fold://纵向展开
                SwitchLayout.ScaleToBigVerticalIn(this, false, null);
                break;
        }

//        SwitchLayout.get3DRotateFromLeft(this, false, null);//3D翻转
//        SwitchLayout.getSlideFromBottom(this, false, BaseEffects.getMoreSlowEffect());//底部划入
//        SwitchLayout.getSlideFromLeft(this, false, BaseEffects.getLinearInterEffect());//左侧划入
//        SwitchLayout.getSlideFromRight(this, false, null);//右侧划入
//        SwitchLayout.getFadingIn(this);//淡入淡出
//        SwitchLayout.ScaleBig(this, false, null);//中心缩放
//        SwitchLayout.FlipUpDown(this, false, BaseEffects.getQuickToSlowEffect()); //上下翻转
//        SwitchLayout.getShakeMode(this, false, null, 1);//震动模式
//        SwitchLayout.RotateCenterIn(this, false, null); //中心旋转
    }

    @Override
    public void setExitSwichLayout() {
//        三个参数分别为（Activity/View，是否关闭Activity，特效（可为空））
        switch (initScreen()) {
            case Anim_Top_Slide://顶部划入
                SwitchLayout.getSlideToTop(this, true, BaseEffects.getReScrollEffect());
                break;
            case Anim_Left_Scale://左上角缩放
                SwitchLayout.ScaleSmallLeftTop(this, true, null);
                break;
            case Anim_Left_Rotate_Cneter://左侧中心旋转
                SwitchLayout.RotateLeftCenterOut(this, true, null);
                break;
            case Anim_Left_Rotate_Top://左上角旋转
                SwitchLayout.RotateLeftTopOut(this, true, null);
                break;
            case Anim_Horizontal_Fold://横向展开
                SwitchLayout.ScaleToBigHorizontalOut(this, true, null);
                break;
            case Anim_Vertical_Fold://纵向展开
                SwitchLayout.ScaleToBigVerticalOut(this, true, null);
                break;
        }

//        SwitchLayout.get3DRotateFromRight(this, true, null);//3D
//        SwitchLayout.getSlideToBottom(this, true, BaseEffects.getMoreSlowEffect());//底部划入
//        SwitchLayout.getSlideToLeft(this, true, BaseEffects.getLinearInterEffect());//左侧划入
//        SwitchLayout.getSlideToRight(this, true, null);//右侧划入
//        SwitchLayout.getFadingOut(this, true);//淡入淡出
//        SwitchLayout.ScaleSmall(this, true, null);//中心缩放
//        SwitchLayout.FlipUpDown(this, true, BaseEffects.getQuickToSlowEffect());//上下翻转
//        SwitchLayout.getShakeMode(this, true, null, 1);  //震动模式
//        SwitchLayout.RotateCenterOut(this, true, null);//中心旋转
    }


    /**
     * @param color 状态栏
     */
    public void setStatusBarColor(Activity act, int color) {
        int colorBar = -1;
        if (color == Code.System.DefaultColor) {
            Themes theme = null;
            try {
                theme = DaoTheme.getInstance().getUserTheme();
                colorBar = theme.getBase_color();
            } catch (Exception e) {
                colorBar = R.color.default_blue;
            }

        } else if (color == Code.System.Immersive) {
            colorBar = Color.TRANSPARENT;
        } else if (color == Code.System.DefaultDark) {
            colorBar = R.color.default_main;
        } else {
            colorBar = color;
        }

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
    //-----------------------------------------------------------------------------------------------------
    protected MultiTypeAdapter adapter;
    protected EmptyRecyclerView rvListView;

    protected void recInit() {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(new LinearLayoutManager(this));
        rvListView.setAdapter(adapter);
    }

    protected void recInit(int span) {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(new GridLayoutManager(this, span));
        rvListView.setAdapter(adapter);
    }

    protected void recInit(RecyclerView.LayoutManager layoutManager) {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
    }

    protected <T> void recInit(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(new LinearLayoutManager(this));
        rvListView.setAdapter(adapter);
        adapter.register(clazz, binder);
    }

    protected <T> void recInit(int span, @NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(new GridLayoutManager(this, span));
        rvListView.setAdapter(adapter);
        adapter.register(clazz, binder);
    }

    protected <T> void recInit(RecyclerView.LayoutManager layoutManager, @NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        adapter = new MultiTypeAdapter();
        rvListView.setLayoutManager(layoutManager);
        rvListView.setAdapter(adapter);
        adapter.register(clazz, binder);
    }

    protected void removeDivider(RecyclerView.ItemDecoration decoration) {
        rvListView.removeItemDecoration(decoration);
    }

    protected void addVerticalDivider() {
        rvListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    protected void recNotifyData(List datas) {
        adapter.setItems(datas);
        adapter.notifyDataSetChanged();
    }

    protected <T> void recRegistBinder(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        if (adapter == null) {
            return;
        }
        adapter.register(clazz, binder);
    }

    //-----------------------------------------------------------------------------------------------------
    private boolean moreThanOneScreen;//超过一屏

    protected void recDoubleTaps(View view) {
        moreThanOneScreen = false;
        if (view != null) {
            final long[] mHits = new long[2];
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                    mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                    if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
                        if (rvListView != null && moreThanOneScreen) {
                            rvListView.smoothScrollToPosition(0);
                        }

                    }
                    return false;
                }
            });
        }

        if (rvListView != null) {
            rvListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                    // 当不滚动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 判断是否滚动超过一屏
                        if (firstVisibleItemPosition != 0) {
                            moreThanOneScreen = true;
                        } else {
                            moreThanOneScreen = false;
                        }
                    }
                }
            });
        }
    }

}
