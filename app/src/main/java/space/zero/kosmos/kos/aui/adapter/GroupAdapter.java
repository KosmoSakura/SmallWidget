package space.zero.kosmos.kos.aui.adapter;

import android.content.Context;
import android.widget.TextView;

import space.zero.kosmos.mos.base.ABaseAdapter;
import space.zero.kosmos.mos.base.IViewHolder;
import space.zero.kosmos.mos.tools.TxtUtil;
import space.zero.kosmos.R;


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
