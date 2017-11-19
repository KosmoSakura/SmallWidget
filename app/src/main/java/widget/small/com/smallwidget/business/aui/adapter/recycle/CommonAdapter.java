package widget.small.com.smallwidget.business.aui.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

import widget.small.com.smallwidget.helper.widget.recycler.MultiItemTypeAdapter;
import widget.small.com.smallwidget.helper.widget.recycler.base.ItemViewDelegate;
import widget.small.com.smallwidget.helper.widget.recycler.base.ViewHolder;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, T old, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, T old, int position) {
                CommonAdapter.this.convert(holder, t, old, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, T old, int position);


}
