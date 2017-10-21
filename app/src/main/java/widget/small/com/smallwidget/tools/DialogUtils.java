package widget.small.com.smallwidget.tools;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.tools.base.Code;


/**
 * Description:弹出统一对话框
 * <p>
 * Author: Kosmos
 * Time: 2016/10/10 0010 17:41
 * Email:ZeroProject@foxmail.com
 */
public class DialogUtils {
    private Context c;
    private Dialog upDialog;
    private Drawable res = null;
    private String sureText, cancelText, txt, titleText;
    private int flag;
    private EditText edt;
    private ImageView dia_simple_line_2, dia_simple_line_3;

    private Button cancel, sure;

    public interface SureOnClickListener {
        void OnClick(String result, Dialog dia);
    }

    public interface CancelOnClickListener {
        void OnClick();
    }

    private SureOnClickListener listener;
    private CancelOnClickListener cancleListener;

    public void setSureOnClickListener(SureOnClickListener listener) {
        this.listener = listener;
    }

    public void setCancelOnClickListener(CancelOnClickListener listener) {
        cancleListener = listener;
    }

    public DialogUtils(Context c) {
        this.c = c;
        inits();
    }

    public DialogUtils(Context c, Drawable res, String title, String sureText, String cancelText, String txt, SureOnClickListener listener) {
        this.c = c;
        this.res = res;
        this.sureText = sureText;
        this.cancelText = cancelText;
        this.txt = txt;
        this.titleText = title;
        setSureOnClickListener(listener);
        inits();
    }

    public DialogUtils(Context c, Drawable res, String title, String sureText, String cancelText, String txt, SureOnClickListener listener, CancelOnClickListener cancleListener) {
        this.c = c;
        this.res = res;
        this.sureText = sureText;
        this.cancelText = cancelText;
        this.txt = txt;
        this.titleText = title;
        setSureOnClickListener(listener);
        setCancelOnClickListener(cancleListener);
        inits();
    }

    public DialogUtils(Context c, Drawable res, String sureText, String txt, SureOnClickListener listener) {
        this.c = c;
        this.res = res;
        this.sureText = sureText;
        this.txt = txt;
        setSureOnClickListener(listener);
        inits();
    }

    public DialogUtils(Context c, int flag, String title, String txt, SureOnClickListener listener) {
        this.c = c;
        this.flag = flag;
        this.sureText = "确认";
        this.cancelText = "取消";
        this.txt = txt;
        this.titleText = title;
        setSureOnClickListener(listener);
        inits();
    }

    public DialogUtils(Context c, int flag, Drawable res, String title, String sureText, String cancelText, String txt, SureOnClickListener listener) {
        this.c = c;
        this.res = res;
        this.sureText = sureText;
        this.cancelText = cancelText;
        this.txt = txt;
        this.titleText = title;
        this.flag = flag;
        setSureOnClickListener(listener);
        inits();
    }

    public void setCancelable(boolean cancelable) {
        upDialog.setCancelable(cancelable);
        upDialog.setCanceledOnTouchOutside(cancelable);
    }

    private void inits() {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.view_dialog_simple, null);
        upDialog = showAlertDialogMap(view);
        upDialog.setCanceledOnTouchOutside(true);
        cancel = (Button) view.findViewById(R.id.dia_simple_cancel);
        sure = (Button) view.findViewById(R.id.dia_simple_sure);
        ImageView image = (ImageView) view.findViewById(R.id.dia_simple_img);
        ImageView line = (ImageView) view.findViewById(R.id.dia_simple_line_1);
        TextView text = (TextView) view.findViewById(R.id.dia_simple_msg);
        TextView title = (TextView) view.findViewById(R.id.dia_simple_title);
        edt = (EditText) view.findViewById(R.id.dia_simple_edt);
        dia_simple_line_2 = (ImageView) view.findViewById(R.id.dia_simple_line_2);
        dia_simple_line_3 = (ImageView) view.findViewById(R.id.dia_simple_line_v_3);

        switch (flag) {
            case Code.System.HideDialgeEdt:
                edt.setVisibility(View.GONE);
                edt.setHint(txt);
                break;
            case Code.System.ShowDialgeEdt:
                edt.setVisibility(View.VISIBLE);
                edt.setHint(txt);
                break;
            default:
                break;
        }
        if (res != null) {
            image.setVisibility(View.VISIBLE);
            image.setImageDrawable(res);
        } else {
            image.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(cancelText)) {
            cancel.setVisibility(View.GONE);
//            dia_simple_line_2.setVisibility(View.GONE);
            dia_simple_line_2.setBackgroundColor(c.getResources().getColor(R.color.default_line));
            dia_simple_line_3.setVisibility(View.GONE);
            sure.setBackgroundResource(R.drawable.iamge_java);
            sure.setTextColor(c.getResources().getColor(R.color.default_blue));
        }
        if (!TextUtils.isEmpty(sureText)) {
            sure.setText(sureText);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            cancel.setText(cancelText);
        }
        if (!TextUtils.isEmpty(txt)) {
            text.setText(txt);
            text.setVisibility(View.VISIBLE);
        } else {
            text.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(titleText)) {
            title.setText(titleText);
            title.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDialog.dismiss();
                if (cancleListener != null) {
                    cancleListener.OnClick();
                }

            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (!TextUtils.isEmpty(edt.getText())) {
                        listener.OnClick(edt.getText().toString(), upDialog);
                    } else {
                        if (flag == Code.System.ShowDialgeEdt) {
                            ToastUtil.LongMessage("未输入内容");
                            return;
                        } else listener.OnClick("", upDialog);
                    }
                }
            }
        });
        upDialog.show();
    }

    private Dialog showAlertDialogMap(View view) {
        Dialog dlg = new Dialog(c, R.style.MyDialog);
        dlg.setCancelable(false);
        dlg.show();
        dlg.setContentView(view);
        dlg.getWindow().setBackgroundDrawableResource(R.color.transparent);
        return dlg;
    }

    public void setLineColor(int color) {
        dia_simple_line_2.setBackgroundColor(color);
        dia_simple_line_3.setBackgroundColor(color);
    }

    public void setButtonColor(int cacelTxtColor, int cacelBgColor, int okTxtColor, int okBgColor) {
        cancel.setTextColor(cacelTxtColor);
        sure.setTextColor(okTxtColor);
        if (cacelBgColor == 0) {
            cancel.setBackgroundColor(cacelBgColor);
        } else {
            cancel.setBackgroundColor(cacelBgColor);
        }

        sure.setBackgroundColor(okBgColor);
    }

    public void setButtonColor(int cacelTxtColor, int cacelBgColor, int okTxtColor, Drawable okBg) {
        cancel.setTextColor(cacelTxtColor);
        sure.setTextColor(okTxtColor);
        cancel.setBackgroundColor(cacelBgColor);
        sure.setBackgroundDrawable(okBg);
    }
}
