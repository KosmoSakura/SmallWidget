package widget.small.com.smallwidget.helper.widget.pop;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.bean.PopupWindowListBean;
import widget.small.com.smallwidget.helper.tools.ScreenUtil;


/**
 * Description:列表Dialog
 * <p>
 * Author: yi.zhang
 * Time: 2017/5/8 0008
 * E-mail: yi.zhang@rato360.com
 */
public class PopupWindowListDialog extends Dialog {

    public PopupWindowListDialog(Context context) {
        super(context);
    }

    public PopupWindowListDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private TextView tHead;
        private Context context;
        private PopupWindowAlertDialog dialog;
        private AdapterView.OnItemClickListener itemClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public PopupWindowAlertDialog create(List<PopupWindowListBean> datas) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //自定义透明背景的弹出窗
            dialog = new PopupWindowAlertDialog(context, R.style.PopupWindowListDialog);
            View layout = inflater.inflate(R.layout.lay_popup_list_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(true);
            //点击弹出床主体内容之外消失Dialog
            RelativeLayout main = (RelativeLayout) layout.findViewById(R.id.fl_popup);
            tHead = (TextView) main.findViewById(R.id.title_t);
            main.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dimss(v);
                    return false;
                }
            });

            ListView lvChoice = (ListView) layout.findViewById(R.id.lv_choice);
            lvChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dimss(view);
                    itemClickListener.onItemClick(parent, view, position, id);
                }
            });

            PopupWindowListAdapter adapter = new PopupWindowListAdapter(context, datas, R.layout.item_lv_pop_dialog);
            lvChoice.setAdapter(adapter);

            dialog.setContentView(layout);
            show();

            return dialog;
        }

        public void setHeadTxt(String str) {
            tHead.setVisibility(View.VISIBLE);
            tHead.setText(str);
        }
        /**
         * 显示dialog窗口
         */
        public void show() {
            if (!dialog.isShowing()) {
                dialog.show();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = ScreenUtil.getScreenWidth(context);
                params.height = ScreenUtil.getScreenHeight(context);
                window.setAttributes(params);
            }
        }

        /**
         * 隐藏窗口
         */
        public void dimss() {
            dialog.dismiss();
        }

        /**
         * 隐藏窗口
         */
        public void dimss(View v) {
            dialog.dismiss();
            v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out));
        }
    }
}