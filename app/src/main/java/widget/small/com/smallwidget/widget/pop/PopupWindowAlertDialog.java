package widget.small.com.smallwidget.widget.pop;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.tools.ScreenUtil;
import widget.small.com.smallwidget.tools.TxtUtil;


/**
 * Description:选择Dialog
 * <p>
 * Author: yi.zhang
 * Time: 2017/5/8 0008
 * E-mail: yi.zhang@rato360.com
 */
public class PopupWindowAlertDialog extends Dialog {

    public PopupWindowAlertDialog(Context context) {
        super(context);
    }

    PopupWindowAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;       //标题
        private String message, edt_txt;     //提示内容
        private String positiveButtonTextR;//右边按钮
        private String negativeButtonTextL;//左边按钮
        private int titleSize, messageSize, edt_size;

        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnDismissListener dismissListener;
        private TextView tvContent, tvTitle;
        private EditText edt;
        private Button btnPositive, btnNegative;
        private boolean outside = true, cancelAble = true;

        public Builder(Context context) {
            this.context = context;
        }


        public Builder setMessage(String message, int messageSize) {
            this.message = message;
            if (messageSize > 0) {
                this.messageSize = messageSize;
            }
            return this;
        }

        public Builder setEditText(String edt_txt, int edt_size) {
            this.edt_txt = edt_txt;
            if (messageSize > 0) {
                this.edt_size = edt_size;
            }
            return this;
        }

        public Builder setEditText(String edt_txt) {
            this.edt_txt = edt_txt;
            return this;
        }

        public Builder setCancelAble(boolean cancelAble) {
            this.cancelAble = cancelAble;
            return this;
        }


        public TextView getTvContent() {
            return tvContent;
        }

        public EditText getEdit() {
            return edt;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setMessage(String msg) {
            this.message = msg;
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public void setTitle(String titl) {
            this.title = titl;
            tvTitle.setText(titl);
        }

        public Builder setTitleStr(String titl) {
            this.title = titl;
            return this;
        }

        public String getTitle() {
            return TxtUtil.isNull(tvTitle.getText().toString());
        }

        /**
         * 设置标题文字和文字的大小
         *
         * @param title
         * @param tv_size 单位是dp
         * @return
         */
        public Builder setTitle(String title, int tv_size) {
            this.title = title;
            if (tv_size > 0)
                titleSize = tv_size;
            return this;
        }

        public Builder setOutsidedCancel(boolean out) {
            outside = out;
            return this;
        }

        public void setPositiveButtonTextR(int positiveButtonTextR) {
            this.positiveButtonTextR = (String) context.getText(positiveButtonTextR);
        }

        public void setPositiveButtonTextR(String positiveButtonTextR) {
            this.positiveButtonTextR = positiveButtonTextR;
        }

        public void setNegativeButtonTextL(int negativeButtonTextL) {
            this.negativeButtonTextL = (String) context.getText(negativeButtonTextL);
        }
        public void setNegativeButtonTextL(String negativeButtonTextL) {
            this.negativeButtonTextL = negativeButtonTextL;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public void setOnDismissListener(OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
        }

        public Builder setPositiveButton(OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonTextR = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonTextR = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonTextL = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonTextL = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public void setBtnChanged(String str) {
            btnNegative.setVisibility(View.GONE);
            btnPositive.setText(str);
        }


        public PopupWindowAlertDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //自定义透明背景的弹出窗
            final PopupWindowAlertDialog dialog = new PopupWindowAlertDialog(context, R.style.PopupWindowListDialog);
            View layout = inflater.inflate(R.layout.lay_popup_alert_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //点击弹出床主体内容之外消失Dialog
            FrameLayout main = (FrameLayout) layout.findViewById(R.id.fl_popup);
            LinearLayout lay = (LinearLayout) layout.findViewById(R.id.fl_popup_lay);
            lay.setOnClickListener(null);
            main.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dimss(dialog, v);
                    return false;
                }
            });

            tvContent = (TextView) layout.findViewById(R.id.tv_content); //Message
            btnPositive = ((Button) layout.findViewById(R.id.btnPositive));  //确定按钮
            btnNegative = ((Button) layout.findViewById(R.id.btnNegative));  //取消按钮
            tvTitle = (TextView) layout.findViewById(R.id.tv_title);
            edt = (EditText) layout.findViewById(R.id.et_content);


            // 设置弹出窗标题
            if (title != null) {
                tvTitle.setText(title);
                if (titleSize > 0)
                    tvTitle.setTextSize(titleSize);
                tvTitle.setVisibility(View.VISIBLE);
            } else {
                layout.findViewById(R.id.tv_title).setVisibility(View.GONE);
            }

            // 设置确定按钮文字
            if (!TxtUtil.isEmpty(positiveButtonTextR)) {
                btnPositive.setText(positiveButtonTextR);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (positiveButtonClickListener != null) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        } else if (TxtUtil.equals(positiveButtonTextR, "取消")) {
                            dialog.dismiss();
                        }
                    }
                });

            } else {
                btnPositive.setVisibility(View.GONE);
            }
            // 设置取消按钮文字显示
            if (!TxtUtil.isEmpty(negativeButtonTextL)) {
                btnNegative.setText(negativeButtonTextL);
                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        } else if (TxtUtil.equals(negativeButtonTextL, "取消")) {
                            dialog.dismiss();
                        }
                    }
                });
            } else {
                btnNegative.setVisibility(View.GONE);
            }

            if (!TxtUtil.isEmpty(message) || !TxtUtil.isEmpty(edt_txt)) {
                if (!TxtUtil.isEmpty(message)) {
                    tvContent.setText(message);
                    tvContent.setVisibility(View.VISIBLE);
                    if (messageSize > 0)
                        tvContent.setTextSize(messageSize);
                } else tvContent.setVisibility(View.GONE);

                if (!TxtUtil.isEmpty(edt_txt)) {
                    edt.setHint(TxtUtil.isNull(edt_txt));
                    edt.setVisibility(View.VISIBLE);
                    if (edt_size > 0)
                        edt.setTextSize(edt_size);
                } else edt.setVisibility(View.GONE);

            } else if (contentView != null && TxtUtil.isEmpty(message) && TxtUtil.isEmpty(edt_txt)) {
                ((LinearLayout) layout.findViewById(R.id.ll_content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.ll_content))
                    .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT));
            } else if (contentView == null && TxtUtil.isEmpty(message) && TxtUtil.isEmpty(edt_txt)) {
                ((LinearLayout) layout.findViewById(R.id.ll_content)).setVisibility(View.GONE);
            }
            if (dismissListener != null) {
                dialog.setOnDismissListener(dismissListener);
            }
            dialog.setContentView(layout);
            dialog.setCancelable(cancelAble);
            show(dialog);

            return dialog;
        }

        private void show(PopupWindowAlertDialog dialog) {
            dialog.show();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = ScreenUtil.getScreenWidth(context);
            params.height = ScreenUtil.getScreenHeight(context);
            window.setAttributes(params);
        }

        /**
         * 隐藏窗口
         */
        public void dimss(PopupWindowAlertDialog dialog, View v) {
            if (outside) {
                dialog.dismiss();
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out));
            }
        }


    }
}
