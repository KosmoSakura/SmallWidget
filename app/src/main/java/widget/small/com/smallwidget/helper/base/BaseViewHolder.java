package widget.small.com.smallwidget.helper.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import autolayout.utils.AutoUtils;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/6/22 002214:54
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View contentView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
        contentView = itemView;
    }

    /**
     * 泛型转换findViewById（）方法
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) contentView.findViewById(resId);
    }

}
