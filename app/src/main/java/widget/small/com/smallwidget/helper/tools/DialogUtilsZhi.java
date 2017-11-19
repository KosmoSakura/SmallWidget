package widget.small.com.smallwidget.helper.tools;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import widget.small.com.smallwidget.R;


/**
 * Description:弹出统一对话框
 * <p>
 * Author: Kosmos
 * Time: 2016/10/10 0010 17:41
 * Email:ZeroProject@foxmail.com
 */
public class DialogUtilsZhi {
    private Context c;
    private Dialog upDialog;


    public DialogUtilsZhi(Context c, SureOnClickListener lis) {
        this.c = c;
        this.listener = lis;
        inits();
    }

    public interface SureOnClickListener {
        void OnClick();
    }

    private SureOnClickListener listener;

    private Button cancel, sure;

    private void inits() {
        LayoutInflater inflater = LayoutInflater.from(c);
        View view = inflater.inflate(R.layout.view_dialog_simple_zhi, null);
        upDialog = showAlertDialogMap(view);
        upDialog.setCanceledOnTouchOutside(false);
        upDialog.setCancelable(false);
        cancel = (Button) view.findViewById(R.id.dia_simple_cancel);
        sure = (Button) view.findViewById(R.id.dia_simple_sure);
        final RelativeLayout relTxt = (RelativeLayout) view.findViewById(R.id.dia_simple_rel_txt);
        final RelativeLayout relPic = (RelativeLayout) view.findViewById(R.id.dia_simple_rel_pic);

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (cancel.getText().equals("错了")) {
//                    listener.OnClick();
//                } else {
//                    cancel.setText("错了");
//                    sure.setText("我没错");
//                }
//            }
//        });
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sure.getText().equals("错了")) {
//                    listener.OnClick();
//                } else {
//                    cancel.setText("我没错");
//                    sure.setText("错了");
//                }
//            }
//        });
        sure.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    relTxt.setVisibility(View.GONE);
                    relPic.setVisibility(View.VISIBLE);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    relTxt.setVisibility(View.VISIBLE);
                    relPic.setVisibility(View.GONE);
                    return true;
                } else
                    return false;
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClick();
            }
        });
//        cancel.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    return true;
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//
//                    return true;
//                } else
//                    return false;
//            }
//        });
        upDialog.show();
    }

    private Dialog showAlertDialogMap(View view) {
        Dialog dlg = new Dialog(c, R.style.MyDialog);
        dlg.setCancelable(true);
        dlg.show();
        dlg.setContentView(view);
        dlg.getWindow().setBackgroundDrawableResource(R.color.T_all);
        return dlg;
    }

}
