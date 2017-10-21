package widget.small.com.smallwidget.base;


import android.view.View;

/**
 * Created by ZeroProject on 2016/8/29 0029 16:24
 * Email:ZeroProject@foxmail.com
 * 所有ViewHolder的基类
 */
public abstract class IViewHolder<T> {
    public View contentView;

    /**
     * initView(这里用一句话描述这个方法的作用)
     *
     * @param v
     * @return void
     * @Title: initView 初始化布局
     * @Description: TODO
     */
    public final void createView(View v) {
        this.contentView = v;
        initView();
    }

    /**
     * 泛型转换findViewById（）方法
     * */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) contentView.findViewById(resId);
    }

    public abstract void initView();

    /**
     *
     * @param dto
     * @return void
     * @Title: buildData 绑定数据
     * @Description: TODO
     */
    public abstract void buildData(int position, T dto);
}
