package space.zero.kosmos.kos.aui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import greendao.bean.Themes;
import widget.small.com.smallwidget.R;
import space.zero.kosmos.mos.base.ABaseAdapter;
import space.zero.kosmos.mos.base.IViewHolder;
import space.zero.kosmos.kos.bean.ThemeBean;
import space.zero.kosmos.mos.tools.base.Code;
import space.zero.kosmos.mos.db.DaoTheme;


/**
 * Description:行业标签
 * <p>
 * Author: ZeroProject
 * Time: 2016/10/9 0009 20:14
 * Email:ZeroProject@foxmail.com
 */
public class ThemeAdapter extends ABaseAdapter<ThemeBean> {
    private Context context;
    private int colorBar;

    public ThemeAdapter(Context context) {
        super(context, R.layout.item_theme);
        this.context = context;
        try {
            Themes theme = DaoTheme.getInstance().getUserTheme();
            colorBar = theme.getBase_color();
        } catch (Exception e) {
            colorBar = R.color.default_blue;
        }
    }


    @Override
    protected IViewHolder<ThemeBean> getViewHolder(int position) {
        return new IViewHolder<ThemeBean>() {

            RelativeLayout lay;
            TextView desc;
            CheckedTextView ctv;
            ImageView show;

            @Override
            public void initView() {
                desc = findView(R.id.item_theme_txt);
                ctv = findView(R.id.item_theme_ctv);
                lay = findView(R.id.item_theme_ll);
                show = findView(R.id.item_theme_color);
                desc.setTextColor(context.getResources().getColor(colorBar));
            }

            @Override
            public void buildData(final int position, final ThemeBean dto) {
                ctv.setChecked(dto.getSelect());
                desc.setText(dto.getName());
                if (dto.getColor_type() == Code.System.TypeColorRes) {
                    desc.setTextColor(context.getResources().getColor(dto.getBase_color()));
                } else {
                    desc.setTextColor(dto.getBase_color());
                }
                show.setImageResource(dto.getBase_color());

                lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ctv.toggle();
                        if (listener != null && ctv.isChecked()) {
                            listener.onCheckChenged(dto.getTheme_id());
                        }
                    }
                });
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
