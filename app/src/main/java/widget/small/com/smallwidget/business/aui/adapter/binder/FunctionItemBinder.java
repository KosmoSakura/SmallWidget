package widget.small.com.smallwidget.business.aui.adapter.binder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;
import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.bean.MainItemBean;
import widget.small.com.smallwidget.helper.base.BaseViewHolder;
import widget.small.com.smallwidget.helper.tools.TxtUtil;
import widget.small.com.smallwidget.helper.tools.glide.GlideUtils;


/**
 * Description:资讯筛选标签
 * <p>
 * Author: Kosmos
 * Email:ZeroProject@foxmail.com
 */
public class FunctionItemBinder extends ItemViewBinder<MainItemBean, FunctionItemBinder.ViewHolder> {
    private Context context;

    private EventListener eventListener;


    public interface EventListener {
        void onItemClick(int position);
    }

    public FunctionItemBinder(Context context) {
        this.context = context;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_function_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final MainItemBean tag) {
        holder.tText.setText(TxtUtil.isNull(tag.getName()));
        GlideUtils.loadNormalPic(context, tag.getResImage(), holder.iLogo);
    }


    class ViewHolder extends BaseViewHolder implements View.OnClickListener {
        private final View root;
        private final ImageView iLogo;
        private final TextView tText;

        public ViewHolder(View itemView) {
            super(itemView);
            root = findView(R.id.item_main_root);
            iLogo = findView(R.id.item_main_logo);
            tText = findView(R.id.item_main_text);
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_main_root:
                    if (eventListener != null) {
                        eventListener.onItemClick(getLayoutPosition());
                    }
                    break;
            }
        }
    }

}
