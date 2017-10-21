package widget.small.com.smallwidget.widget.material;

public abstract class MaterialRefreshListener {
    public void onfinish(){};
    public abstract void onRefresh(MaterialRefreshLayout materialRefreshLayout);//下拉
    public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){};//上拉
}
