package widget.small.com.smallwidget.helper.widget.recycler.base;


/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, T old, int position);

    void convert(HYViewHolder holder, T t, T old, int position);

}
