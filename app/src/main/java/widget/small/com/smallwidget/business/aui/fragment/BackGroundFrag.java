package widget.small.com.smallwidget.business.aui.fragment;

import android.view.View;
import android.widget.ImageView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.helper.base.BaseFragment;
import widget.small.com.smallwidget.helper.tools.glide.GlideUtils;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 */
public class BackGroundFrag extends BaseFragment {
    private ImageView background;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_background;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView(View view) {
        setlazyLoadOff();
        background = findView(R.id.bg_background);
    }

    @Override
    protected void initData() {
        GlideUtils.loadLargePic(context, R.drawable.background, background);
    }

    @Override
    protected void initListener() {
    }

}
