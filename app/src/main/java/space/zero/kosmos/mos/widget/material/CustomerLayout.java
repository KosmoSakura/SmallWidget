package space.zero.kosmos.mos.widget.material;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import widget.small.com.smallwidget.R;

/**
 * Created by cjj on 2016/2/22.
 */
public class CustomerLayout extends FrameLayout implements MaterialHeadListener {

    private final static String Tag = CustomerLayout.class.getSimpleName();

    CustomerFaceView customerFaceView;


    private MaterialWaveView materialWaveView;
    private int waveColor;


    public CustomerLayout(Context context) {
        this(context, null);
    }

    public CustomerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        materialWaveView = new MaterialWaveView(getContext());
        materialWaveView.setColor(waveColor);
        addView(materialWaveView);
        View layoutview = LayoutInflater.from(context).inflate(R.layout.material_wait, null);
        customerFaceView = (CustomerFaceView) layoutview.findViewById(R.id.customer);
        addView(layoutview);

        /*CustomerFaceView customerFaceView=new CustomerFaceView(context);
        addView(customerFaceView);*/

    }


    public int getWaveColor() {
        return waveColor;
    }

    public void setWaveColor(int waveColor) {
        this.waveColor = waveColor;
        if (null != materialWaveView) {
            materialWaveView.setColor(this.waveColor);
        }
    }

    @Override
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        if (materialWaveView != null) {
            materialWaveView.onComlete(materialRefreshLayout);
        }
    }

    @Override
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
        Log.e("chenli", "onBegin");
        if (materialWaveView != null) {
            materialWaveView.onBegin(materialRefreshLayout);
        }
        ViewCompat.setScaleX(this, 0.001f);
        ViewCompat.setScaleY(this, 0.001f);
    }

    @Override
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float fraction) {
        if (materialWaveView != null) {
            materialWaveView.onPull(materialRefreshLayout, fraction);
        }
        float a = Util.limitValue(1, fraction);
        if (a >= 0.1) {
            customerFaceView.setVisibility(View.VISIBLE);
        } else {
            customerFaceView.setVisibility(View.GONE);
        }
        ViewCompat.setScaleX(this, a);
        ViewCompat.setScaleY(this, a);
        ViewCompat.setAlpha(this, a);

    }

    @Override
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float fraction) {

    }

    @Override
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        if (materialWaveView != null) {
            materialWaveView.onRefreshing(materialRefreshLayout);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        /*materialWaveView = new MaterialWaveView(getContext());
        materialWaveView.setColor(waveColor);
        addView(materialWaveView);*/

    }


}
