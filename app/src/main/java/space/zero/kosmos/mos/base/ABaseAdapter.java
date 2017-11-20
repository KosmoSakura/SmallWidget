package space.zero.kosmos.mos.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import autolayout.utils.AutoUtils;
import space.zero.kosmos.R;

/**
 * Created by ZeroProject on 2016/8/29 0029 16:24
 * Email:ZeroProject@foxmail.com
 * @param <T>
 */
public abstract class ABaseAdapter<T> extends BaseAdapter {
    protected Context context;
    private LayoutInflater layoutInflater;
    private List<T> listData;
    protected int resId;

    public ABaseAdapter(Context context, List<T> listData, int resId) {
        super();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.resId = resId;
    }

    public ABaseAdapter(Context context, int resId) {
        super();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listData = new ArrayList<T>();
        this.resId = resId;
    }

    /**
     * 获取当前数据列表
     * */
    public List<T> getList() {
        return listData;
    }

    /**
     * 此方法不会清空数据
     * 传入list需要指定泛型，如List<XXBean>，适配器会在内部处理泛型数据
     * */
    public void appendList(List<T> list) {
        if (list != null) {
            this.listData.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 此方法会先清空数据
     * 传入list需要指定泛型，如List<XXBean>，适配器会在内部处理泛型数据
     */
    public void addList(List<T> list) {
        this.listData.clear();
        if (list != null) {
            this.listData.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     * */
    public void addItem(T bean) {
        this.listData.add(bean);
        notifyDataSetChanged();
    }

    /**
     * @param bean
     * 添加一个数据到position=1的位置
     */
    public void addFristItem(T bean) {
        this.listData.add(0, bean);
        notifyDataSetChanged();
    }

    public int getItemForPosition(T obj) {
        int index = -1;
        for(int i=0; i<this.listData.size(); i++) {
            if(this.listData.get(i).equals(obj))
                index = i;
        }
        return index;
    }

    /**
     * @param bean
     * 删除某个指定的item
     */
    public void delItem(T bean) {
        this.listData.remove(bean);
        notifyDataSetChanged();
    }
    public void clear() {
        this.listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public T getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    protected int getItemViewId(int position) {
        return resId;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IViewHolder<T> viewHolder = null;
        if (convertView == null|| convertView.getTag(R.id.tag_viewholder) == null) {
            convertView = layoutInflater.inflate(getItemViewId(position), null);
            AutoUtils.autoSize(convertView);
            viewHolder = getViewHolder(position);
            viewHolder.createView(convertView);
            convertView.setTag(R.id.tag_viewholder, viewHolder);
        } else {
            viewHolder = (IViewHolder<T>) convertView.getTag(R.id.tag_viewholder);
        }
        viewHolder.buildData(position, getItem(position));
        return convertView;
    }

    protected abstract IViewHolder<T> getViewHolder(int position);

    public Context getContext() {
        return context;
    }

}
