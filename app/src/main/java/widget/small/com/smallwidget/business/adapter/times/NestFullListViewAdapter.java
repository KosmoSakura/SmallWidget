package widget.small.com.smallwidget.business.adapter.times;

/**
 * 介绍：完全伸展开的ListView的适配器
 */

public abstract class NestFullListViewAdapter<T> {
    private int mItemLayoutId;//看名字
    private T mDatas;//数据源

    public NestFullListViewAdapter(int mItemLayoutId, T mDatas) {
        this.mItemLayoutId = mItemLayoutId;
        this.mDatas = mDatas;
    }

    /**
     * 被FullListView调用
     *
     * @param i
     * @param holder
     */
    public void onBind(int i, NestFullViewHolder holder) {
        //回调bind方法，多传一个data过去
        onBind(i, mDatas, holder);
    }

    /**
     * 数据绑定方法
     *
     * @param pos    位置
     * @param t      数据
     * @param holder ItemView的ViewHolder
     */
    public abstract void onBind(int pos, T t, NestFullViewHolder holder);

    public int getItemLayoutId() {
        return mItemLayoutId;
    }

    public void setItemLayoutId(int mItemLayoutId) {
        this.mItemLayoutId = mItemLayoutId;
    }

    public T getDatas() {
        return mDatas;
    }

    public void setDatas(T mDatas) {
        this.mDatas = mDatas;
    }

}
