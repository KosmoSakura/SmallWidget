package widget.small.com.smallwidget.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.adapter.MenuTabAdapter;
import widget.small.com.smallwidget.helper.base.BaseActivity;
import widget.small.com.smallwidget.business.fragment.BackGroundFrag;
import widget.small.com.smallwidget.business.fragment.CodeScanFragment;
import widget.small.com.smallwidget.business.fragment.SettingFrag;
import widget.small.com.smallwidget.business.fragment.WeightFrag;
import widget.small.com.smallwidget.business.fragment.ZhiFuFrag;
import widget.small.com.smallwidget.helper.tools.ToastUtil;
import widget.small.com.smallwidget.helper.tools.base.Code;
import widget.small.com.smallwidget.helper.tools.glide.GlideUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private DrawerLayout drawer;

    private ImageView iFtb, icon, head_icon, background;


    private MenuTabAdapter adapter;
    public List<Fragment> publicMenuFragment = new ArrayList<Fragment>();
    private int select;
    private List<RelativeLayout> menus;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void initView() {
        setStatusBarColor(this, Code.System.DefaultColor);
        drawer = findView(R.id.drawer_layout);
        iFtb = findView(R.id.main_ftb_iv);
        FrameLayout frag = findView(R.id.main_frag);

        background = findView(R.id.main_head_back);
        head_icon = findView(R.id.main_head);
        icon = findView(R.id.main_iv_icon);

        RelativeLayout ral0 = findView(R.id.nav_camera);
        RelativeLayout ral1 = findView(R.id.nav_gallery);
        RelativeLayout ral2 = findView(R.id.nav_slideshow);
        RelativeLayout ral3 = findView(R.id.nav_manage);
        RelativeLayout ral4 = findView(R.id.nav_share);
        RelativeLayout ral5 = findView(R.id.nav_send);
        menus = new ArrayList<>();
        menus.add(ral0);
        menus.add(ral1);
        menus.add(ral2);
        menus.add(ral3);
        menus.add(ral4);
        menus.add(ral5);
    }

    @Override
    protected void initListener() {
        iFtb.setOnClickListener(this);
        for (RelativeLayout rel : menus) {
            rel.setOnClickListener(this);
        }

        select = R.id.nav_camera;
        setTitleBar();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (iFtb.isSelected()) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }


    @Override
    public void onClick(View v) {
        iFtb.setSelected(false);
        switch (v.getId()) {
            case R.id.main_ftb_iv://diandian:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.nav_camera://首页
                gotoPage(0);
                drawer.closeDrawer(GravityCompat.START);
                select = R.id.nav_camera;
                break;
            case R.id.nav_gallery://支付宝
                drawer.closeDrawer(GravityCompat.START);
                gotoPage(1);
                select = R.id.nav_gallery;
                break;
            case R.id.nav_slideshow://体重
                drawer.closeDrawer(GravityCompat.START);
                gotoPage(2);
                select = R.id.nav_slideshow;
                iFtb.setSelected(true);
                break;
            case R.id.nav_manage://设置
                drawer.closeDrawer(GravityCompat.START);
                gotoPage(3);
                select = R.id.nav_manage;
                break;
            case R.id.nav_share://二维码
                gotoPage(4);
                select = R.id.nav_share;
                iFtb.setSelected(true);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_send://发送
                ToastUtil.CustomShort("这个还没写");
                select = R.id.nav_send;
                break;
        }

        if (iFtb.isSelected()) {
            iFtb.setVisibility(View.GONE);
        } else {
            iFtb.setVisibility(View.VISIBLE);
        }
        for (RelativeLayout rel : menus) {
            if (rel.getId() == select) {
                rel.setBackgroundResource(R.drawable.rect_side_nav_bar);
            } else {
                rel.setBackgroundResource(R.color.T_all);
            }

        }
        setTitleBar();
    }

    private void setTitleBar() {
        switch (select) {
            case R.id.nav_camera://首页
                setStatusBarColor(MainActivity.this, Code.System.DefaultColor);
                break;
            case R.id.nav_gallery://支付宝
                setStatusBarColor(MainActivity.this, R.color.white);
                break;
            case R.id.nav_slideshow://视频
                setStatusBarColor(MainActivity.this, R.color.white);
                break;
            case R.id.nav_manage://设置
                setStatusBarColor(MainActivity.this, Code.System.DefaultColor);
                break;
            case R.id.nav_share://分享:
                setStatusBarColor(MainActivity.this, R.color.white);
                break;
            default:
                setStatusBarColor(MainActivity.this, R.color.white);
                break;
        }
    }

    public void gotoPage(int index) {
        if (adapter != null) {
            adapter.changeMenu(index);
        }
    }

    public void otherToOnePage(final int index) {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoPage(index);
            }
        }, 100);
    }

    @Override
    protected void initData() {
        BackGroundFrag frag0 = new BackGroundFrag();
        ZhiFuFrag frag1 = new ZhiFuFrag();
        WeightFrag frag2 = new WeightFrag();
        SettingFrag frag3 = new SettingFrag();
//        ThemeFrag frag4 = new ThemeFrag();
        CodeScanFragment scanFragment = new CodeScanFragment();
        checkFrag(frag0);
        checkFrag(frag1);
        checkFrag(frag2);
        checkFrag(frag3);
        checkFrag(scanFragment);
        adapter = new MenuTabAdapter(this, publicMenuFragment,
            R.id.main_frag);
        GlideUtils.loadCirleAvatar(this, R.drawable.zero, head_icon);
        GlideUtils.loadBlurPic(this, R.drawable.zero, background);
        GlideUtils.loadCirleAvatar(this, R.drawable.rinn_kakashi, iFtb);
        head_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.CustomShort("假如我已经换了头像了");
            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.CustomShort("我在考虑要不要换背景");
            }
        });
    }

    private void checkFrag(Fragment fragment) {
        if (!fragment.isAdded()) {
            publicMenuFragment.remove(fragment);
            publicMenuFragment.add(fragment);
        }
    }

}
