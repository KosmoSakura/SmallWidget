package widget.small.com.smallwidget.business.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.List;


/**
 * Description:Fragment切换适配器
 * <p>
 * Author: ZeroProject
 * Time: 2016/9/7 0009 20:14
 * Email:ZeroProject@foxmail.com
 */
public class MenuTabAdapter {

    private List<Fragment> mMenuFragment;
    private FragmentActivity mFragmentActivity;
    private int mFragmentContentId;
    private int mCurrentMenu;
    private boolean mAnimationFlag = false;


    public interface onIndexChangeListener {
        void onIndexChange(int index);
    }

    private onIndexChangeListener listener;

    public void setonIndexChangeListener(onIndexChangeListener listener) {
        this.listener = listener;
    }

    public MenuTabAdapter(FragmentActivity activity, List<Fragment> list, int fragmentContentId) {
        this.mMenuFragment = list;
        this.mFragmentActivity = activity;
        this.mFragmentContentId = fragmentContentId;

        // 默认
        FragmentTransaction ft = this.mFragmentActivity
            .getSupportFragmentManager().beginTransaction();
        String[] arr = {"home", "game", "found", "calendar", "user"};
        for (int i = 0; i < mMenuFragment.size() && i < 5; i++) {
            ft.add(fragmentContentId, this.mMenuFragment.get(i), arr[i]);
            if (i == 0) {
                ft.show(this.mMenuFragment.get(i));
            } else {
                ft.hide(this.mMenuFragment.get(i));
            }
        }

        ft.commit();
//        changeMenu(0);
    }


    public void changeMenu(int index) {
        Fragment fragment = this.mMenuFragment.get(index);
        FragmentTransaction ft = this.mFragmentActivity
            .getSupportFragmentManager().beginTransaction();
        ;

        getCurrentMenuFragment().setUserVisibleHint(false);
        getCurrentMenuFragment().onPause();

        if (fragment.isAdded()) {
            fragment.setUserVisibleHint(true);
            fragment.onResume();
        }
        showMenuContent(index);
        ft.commitAllowingStateLoss();
    }

    private void showMenuContent(int index) {
        int size = this.mMenuFragment.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = this.mMenuFragment.get(i);
            FragmentTransaction ft = this.mFragmentActivity
                .getSupportFragmentManager().beginTransaction();
            ;

            if (index == i) {
                ft.show(fragment);
//                ft.replace(mFragmentContentId, fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        mCurrentMenu = index; // set current menu

        if (listener != null) {
            listener.onIndexChange(mCurrentMenu);
        }
    }


    public int getCurrentMenu() {
        return mCurrentMenu;
    }

    public Fragment getCurrentMenuFragment() {
        return this.mMenuFragment.get(mCurrentMenu);
    }

    public Fragment getMenuFragment(int i) {
        return this.mMenuFragment.get(i);
    }


}
