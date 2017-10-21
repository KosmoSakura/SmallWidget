package widget.small.com.smallwidget.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import greendao.bean.Weight;
import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.ABaseAdapter;
import widget.small.com.smallwidget.base.IViewHolder;


/**
 * Description:行业标签
 * <p>
 * Author: ZeroProject
 * Time: 2016/10/9 0009 20:14
 * Email:ZeroProject@foxmail.com
 */
public class WeightAdapter extends ABaseAdapter<Weight> {
    private Context context;

    public WeightAdapter(Context context) {
        super(context, R.layout.item_weight);
        this.context = context;
    }


    @Override
    protected IViewHolder<Weight> getViewHolder(int position) {
        return new IViewHolder<Weight>() {

            LinearLayout lay;
            TextView time, wei;

            @Override
            public void initView() {
                wei = findView(R.id.item_theme_wei);
                time = findView(R.id.item_theme_time);
                lay = findView(R.id.item_theme_ll);
            }

            @Override
            public void buildData(final int position, final Weight dto) {
                wei.setText(dto.getValue() + "斤");
                time.setText(dto.getTime());
            }
        };
    }

    public interface OnCheckedListener {
        void onCheckChenged(int theme_id);
    }

    private OnCheckedListener listener;

    public void setOnCheckedListener(OnCheckedListener listener) {
        this.listener = listener;
    }
}
