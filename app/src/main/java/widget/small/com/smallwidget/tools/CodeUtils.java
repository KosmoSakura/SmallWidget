package widget.small.com.smallwidget.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.Selection;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.widget.pop.PopupWindowAlertDialog;


/**
 * Description:这里写偷懒的代码
 * <p>
 * Author: Kosmos
 * Time: 2017/8/7 000713:55
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class CodeUtils {
    public static interface PasswordCheck {
        void onResult(DialogInterface dialog, String password);
    }

    public static EditText showEditDialog(final Context act, String msgs, String hints, final PasswordCheck listener) {
        final PopupWindowAlertDialog.Builder builder = new PopupWindowAlertDialog.Builder(act);
        builder.setOutsidedCancel(false)
            .setEditText(hints, 10)
            .setMessage(msgs, 14);

        builder.setPositiveButtonTextR("确定");
        builder.setNegativeButtonTextL("取消");
        builder.create();
        final EditText edt = builder.getEdit();
        edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        builder.setPositiveButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (edt.length() < 4) {
                    VibrateHelp.vSimple(act, 100);
                    ToastUtil.CustomShort("密码位数不够");
                } else {
                    listener.onResult(dialogInterface, edt.getText().toString());
                }
            }
        });
        return edt;
    }

    public static void showAcknowDialog(Context act, String title, DialogInterface.OnClickListener listener) {
        PopupWindowAlertDialog.Builder builder = new PopupWindowAlertDialog.Builder(act);
        builder.setMessage(title).setOutsidedCancel(true);
        builder.setPositiveButtonTextR(R.string.pickerview_submit);
        builder.setPositiveButton(listener);
        builder.create();
    }

    public static void showSimpleDialog(Context act, String title, DialogInterface.OnClickListener listener) {
        PopupWindowAlertDialog.Builder builder = new PopupWindowAlertDialog.Builder(act);
        builder.setMessage(title).setOutsidedCancel(true);
        builder.setPositiveButtonTextR(R.string.pickerview_submit);
        builder.setNegativeButtonTextL(R.string.pickerview_cancel);
        builder.setPositiveButton(listener);
        builder.create();
    }

    public static void showSimpleDialog(Context act, String title, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        PopupWindowAlertDialog.Builder builder = new PopupWindowAlertDialog.Builder(act);
        builder.setMessage(title).setOutsidedCancel(true);
        builder.setPositiveButtonTextR(R.string.pickerview_submit);
        builder.setNegativeButtonTextL(R.string.pickerview_cancel);
        builder.setPositiveButton(positive);
        builder.setNegativeButton(negative);
        builder.create();
    }

    public static void showSimpleDialog(Context act, String title, String message, DialogInterface.OnClickListener listener) {
        PopupWindowAlertDialog.Builder builder = new PopupWindowAlertDialog.Builder(act);
        builder.setTitle(title, 16).setMessage(message).setOutsidedCancel(true);
        builder.setPositiveButtonTextR(R.string.pickerview_submit);
        builder.setNegativeButtonTextL(R.string.pickerview_cancel);
        builder.setPositiveButton(listener);
        builder.create();
    }


    public static void showNetStateDialog(Context act, DialogInterface.OnClickListener listener) {
        NetworkUtil.NetState netState = NetworkUtil.getNetState(act);
        String desc = "";
        if (netState == NetworkUtil.NetState.NET_2G) {
            desc = "当前处于2G流量状态";
        } else if (netState == NetworkUtil.NetState.NET_3G) {
            desc = "当前处于3G流量状态";
        } else if (netState == NetworkUtil.NetState.NET_4G) {
            desc = "当前处于4G流量状态";
        } else if (netState == NetworkUtil.NetState.NET_NO || netState == NetworkUtil.NetState.NET_UNKNOWN) {
            desc = "网络未连接";
        }
        showSimpleDialog(act, desc, listener);
    }


    /**
     * @param type    0---限定缩略图的长边最多为<LongEdge>，短边最多为<ShortEdge>，
     *                进行等比缩放，不裁剪。如果只指定 w 参数则表示限定长边（短边自适应），
     *                只指定 h 参数则表示限定短边（长边自适应）。
     *                <p>
     *                1---限定缩略图的宽最少为<Width>，高最少为<Height>，
     *                进行等比缩放，居中裁剪。转后的缩略图通常恰好是 <Width>x<Height> 的大小
     *                （有一个边缩放的时候会因为超出矩形框而被裁剪掉多余部分）。如果只指定 w 参数或只指定 h 参数，代表限定为长宽相等的正方图。
     *                <p>
     *                2---限定缩略图的宽最多为<Width>，高最多为<Height>，
     *                进行等比缩放，不裁剪。如果只指定 w 参数则表示限定宽（长自适应），
     *                只指定 h 参数则表示限定长（宽自适应）。它和模式0类似，区别只是限定宽和高，
     *                不是限定长边和短边。从应用场景来说，模式0适合移动设备上做缩略图，模式2适合PC上做缩略图。
     *                <p>
     *                3---限定缩略图的宽最少为<Width>，高最少为<Height>，
     *                进行等比缩放，不裁剪。如果只指定 w 参数或只指定 h 参数，代表长宽限定为同样的值。
     *                你可以理解为模式1是模式3的结果再做居中裁剪得到的。
     *                <p>
     *                4---限定缩略图的长边最少为<LongEdge>，短边最少为<ShortEdge>，
     *                进行等比缩放，不裁剪。如果只指定 w 参数或只指定 h 参数，表示长边短边限定为同样的值。
     *                这个模式很适合在手持设备做图片的全屏查看（把这里的长边短边分别设为手机屏幕的分辨率即可），
     *                生成的图片尺寸刚好充满整个屏幕（某一个边可能会超出屏幕）。
     *                <p>
     *                5---限定缩略图的长边最少为<LongEdge>，短边最少为<ShortEdge>，进行等比缩放，
     *                居中裁剪。如果只指定 w 参数或只指定 h 参数，表示长边短边限定为同样的值。
     *                同上模式4，但超出限定的矩形部分会被裁剪。
     *                ----------------<p>-------------------------------------------------------------------------------
     *                <p> 注意：
     *                可以仅指定w参数或h参数。
     *                新图的宽/高/长边/短边，不会比原图大，即本接口总是缩小图片。
     *                所有模式都可以只指定w参数或只指定h参数，并获得合理结果。在w、h为限定最大值时，未指定某参数等价于将该参数设置为无穷大（自适应）；在w、h为限定最小值时，未指定参数等于给定的参数，也就限定的矩形是正方形。
     *                处理后的图片w和h参数不能超过9999像素，总像素不得超过24999999（2500w-1）像素。
     *                处理前的图片w和h参数不能超过3万像素，总像素不能超过2亿像素。
     *                ----------------<p>-------------------------------------------------------------------------------
     * @param quality 新图的图片质量
     *                取值范围是[1, 100]，默认75。
     *                七牛会根据原图质量算出一个修正值，取修正值和指定值中的小值。
     *                注意：
     *                ● 如果图片的质量值本身大于90，会根据指定值进行处理，此时修正值会失效。
     *                ● 指定值后面可以增加 !，表示强制使用指定值，如100!。
     *                ● 支持图片类型：jpg。
     */
    public static String compressQiNiu(int type, int width, int height, int quality) {
        if (width == 0) {
            return "?imageView2/" + type + "/h/" + height + "/interlace/1/q/" + quality;
        } else if (height == 0) {
            return "?imageView2/" + type + "/w/" + width + "/interlace/1/q/" + quality;
        } else {
            return "?imageView2/" + type + "/w/" + width + "/h/" + height + "/interlace/1/q/" + quality;
        }
    }

    /**
     * 将文本光标放到最后
     */
    public static void putTextCursorToLast(EditText editText) {
        Editable etext = editText.getText();
        Selection.setSelection(etext, editText.length());
    }
}
