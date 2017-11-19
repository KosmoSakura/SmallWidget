package widget.small.com.smallwidget.business.adapter;

import android.content.Context;
import android.widget.TextView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.helper.base.ABaseAdapter;
import widget.small.com.smallwidget.helper.base.IViewHolder;
import widget.small.com.smallwidget.helper.tools.TxtUtil;


/**
 * Description:行业标签
 * <p>
 * Author: ZeroProject
 * Time: 2016/10/9 0009 20:14
 * Email:ZeroProject@foxmail.com
 */
public class GroupAdapter extends ABaseAdapter<String> {
    private Context context;
    private String nox;

    public GroupAdapter(Context context) {
        super(context, R.layout.item_group);
        this.context = context;
    }

    public void setNox(String nox) {
        this.nox = nox;
    }

    @Override
    protected IViewHolder<String> getViewHolder(int position) {
        return new IViewHolder<String>() {

            TextView wei;

            @Override
            public void initView() {
                wei = findView(R.id.item_group_wei);
            }

            @Override
            public void buildData(final int position, final String dto) {
                wei.setText(dto + "");
                if (!TxtUtil.isEmpty(nox) && nox.equalsIgnoreCase(dto)) {
                    wei.setTextColor(context.getResources().getColor(R.color.default_blue));
                } else {
                    wei.setTextColor(context.getResources().getColor(R.color.default_gray));
                }
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
