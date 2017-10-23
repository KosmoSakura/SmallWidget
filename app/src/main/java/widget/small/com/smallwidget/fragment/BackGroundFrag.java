package widget.small.com.smallwidget.fragment;

import android.view.View;
import android.widget.ImageView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.BaseFragment;
import widget.small.com.smallwidget.tools.glide.GlideUtils;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 * 233
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
