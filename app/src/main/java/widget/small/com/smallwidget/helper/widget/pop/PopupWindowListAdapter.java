package widget.small.com.smallwidget.helper.widget.pop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.helper.base.ABaseAdapter;
import widget.small.com.smallwidget.helper.base.IViewHolder;
import widget.small.com.smallwidget.business.bean.PopupWindowListBean;
import widget.small.com.smallwidget.helper.tools.UnitUtil;

/**
 * Description:PopupWindowListAdapter
 * <p>
 * Author: yi.zhang
 * Time: 2017/6/22 0022
 * E-mail: yi.zhang@rato360.com
 */
public class PopupWindowListAdapter extends ABaseAdapter<PopupWindowListBean> {

    public PopupWindowListAdapter(Context context, List<PopupWindowListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    protected IViewHolder<PopupWindowListBean> getViewHolder(int position) {
        return new IViewHolder<PopupWindowListBean>() {
            TextView tv_item;

            @Override
            public void initView() {
                tv_item = findView(R.id.tv_item);
            }

            @Override
            public void buildData(int position, PopupWindowListBean bean) {
                tv_item.setText(bean.getContent());
                tv_item.setTextColor(context.getResources().getColor(bean.getColor()));

                if (bean.getDrawable() != 0) {//如果有图片，则加上
                    Drawable drawable = ContextCompat.getDrawable(context, bean.getDrawable());
                    drawable.setBounds(0, 0, 32, 32);
                    tv_item.setCompoundDrawables(drawable, null, null, null);
                    tv_item.setCompoundDrawablePadding(UnitUtil.dip2px(context, 10));
                }
            }
        };
    }
}