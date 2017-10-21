package widget.small.com.smallwidget.tools.glide;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.base.App;
import widget.small.com.smallwidget.tools.TxtUtil;
import widget.small.com.smallwidget.tools.glide.trans.TransformBlur;
import widget.small.com.smallwidget.tools.glide.trans.TransformCircle;
import widget.small.com.smallwidget.tools.glide.trans.TransformRound;

/**
 * 使用Glide加载图片要注意以下几点：
 * 1、ScaleType不要用fixXY
 * 2、setTag不要直接设置在ImageView上，要设置在其父布局上
 * 3、列表在滑动的时候，可以调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求
 */


public class GlideUtils {

    public final static int FILLET_10 = 10;//圆角角度 10度

    public static void loadToastImage(int resourceId, ImageView tartgetIv) {
        Glide.with(App.getInstance())
            .load(resourceId)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.color.toast_black)
            .error(R.drawable.zero_gray)
            .centerCrop()
            .crossFade()
            .into(tartgetIv);
    }
    /****************************1、以下 圆形头像******************************/

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadCirleAvatar(Context context, String string, ImageView tartgetIv) {
        loadCirleAvatar(context, string, 0, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadCirleAvatar(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadCirleAvatar(context, string, 0, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadCirleAvatar(Context context, Integer resourceId, ImageView tartgetIv) {
        loadCirleAvatar(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadCirleAvatar(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadCirleAvatar(context, "", resourceId, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadCirleAvatar(Context context, File file, ImageView tartgetIv) {
        loadCirleAvatar(context, "", 0, file, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadCirleAvatar(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadCirleAvatar(context, "", 0, file, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadCirleAvatar(Context context, Uri uri, ImageView tartgetIv) {
        loadCirleAvatar(context, "", 0, null, uri, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadCirleAvatar(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadCirleAvatar(context, "", 0, null, uri, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadCirleAvatar(Context context, byte[] model, ImageView tartgetIv) {
        loadCirleAvatar(context, "", 0, null, null, model, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadCirleAvatar(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadCirleAvatar(context, "", 0, null, null, model, tartgetIv, w, h);
    }

    /**
     * 加载圆形图片（以头像为例）
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     */
    private static void loadCirleAvatar(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            }
        }

        //来源于Integer
        if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            }
        }

        //来源于File
        if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            }
        }

        //来源于Uri
        if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            }
        }

        //来源于byte[]
        if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformCircle(context))
                    .into(targetIv);
            }
        }
    }
    /****************************以上 圆形头像******************************/


    /****************************2、以下 圆角头像******************************/
    /**
     * 圆角为10
     *
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatar10(Context context, String string, ImageView tartgetIv) {
        loadRoundAvatar(context, string, 0, null, null, null, tartgetIv, 0, 0, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param string    图片来源为String
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatarCustom(Context context, String string, int fillet, ImageView tartgetIv) {
        loadRoundAvatar(context, string, 0, null, null, null, tartgetIv, 0, 0, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatar10(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, string, 0, null, null, null, tartgetIv, w, h, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param string    图片来源为String
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatarCustom(Context context, String string, int fillet, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, string, 0, null, null, null, tartgetIv, w, h, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadRoundAvatar10(Context context, Integer resourceId, ImageView tartgetIv) {
        loadRoundAvatar(context, "", resourceId, null, null, null, tartgetIv, 0, 0, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param resourceId 图片来源为Integer
     * @param fillet     圆角大小
     * @param tartgetIv  目标控件
     */
    public static void loadRoundAvatarCustom(Context context, Integer resourceId, int fillet, ImageView tartgetIv) {
        loadRoundAvatar(context, "", resourceId, null, null, null, tartgetIv, 0, 0, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadRoundAvatar10(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", resourceId, null, null, null, tartgetIv, w, h, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param resourceId 图片来源为Integer
     * @param fillet     圆角大小
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadRoundAvatarCustom(Context context, Integer resourceId, int fillet, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", resourceId, null, null, null, tartgetIv, w, h, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatar10(Context context, File file, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, file, null, null, tartgetIv, 0, 0, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param file      图片来源为File
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatarCustom(Context context, File file, int fillet, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, file, null, null, tartgetIv, 0, 0, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatar10(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, file, null, null, tartgetIv, w, h, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param file      图片来源为File
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatarCustom(Context context, File file, int fillet, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, file, null, null, tartgetIv, w, h, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatar10(Context context, Uri uri, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, null, uri, null, tartgetIv, 0, 0, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param uri       图片来源为Uri
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatarCustom(Context context, Uri uri, int fillet, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, null, uri, null, tartgetIv, 0, 0, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatar10(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, null, uri, null, tartgetIv, w, h, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param uri       图片来源为Uri
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatarCustom(Context context, Uri uri, int fillet, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, null, uri, null, tartgetIv, w, h, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatar10(Context context, byte[] model, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, null, null, model, tartgetIv, 0, 0, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param model     图片来源为byte[]
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     */
    public static void loadRoundAvatarCustom(Context context, byte[] model, int fillet, ImageView tartgetIv) {
        loadRoundAvatar(context, "", 0, null, null, model, tartgetIv, 0, 0, fillet);
    }

    /**
     * 圆角为10
     *
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatar10(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, null, null, model, tartgetIv, w, h, FILLET_10);
    }

    /**
     * 自定义圆角大小
     *
     * @param context
     * @param model     图片来源为byte[]
     * @param fillet    圆角大小
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadRoundAvatarCustom(Context context, byte[] model, int fillet, ImageView tartgetIv, int w, int h) {
        loadRoundAvatar(context, "", 0, null, null, model, tartgetIv, w, h, fillet);
    }

    /**
     * 加载圆角图片
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     * @param fillet     圆角切角角度
     */
    private static void loadRoundAvatar(Context context, String string, Integer resourceId, File file,
                                        Uri uri, byte[] model, ImageView targetIv, int w, int h, int fillet) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            }
        }

        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .transform(new TransformRound(context, fillet))
                    .into(targetIv);
            }
        }
    }
    /****************************以上 圆形头像******************************/


    /**************************3、以下 方形banner图****************************/

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadBannerPic(Context context, String string, ImageView tartgetIv) {
        loadBannerPic(context, string, 0, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBannerPic(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadBannerPic(context, string, 0, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadBannerPic(Context context, Integer resourceId, ImageView tartgetIv) {
        loadBannerPic(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadBannerPic(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadBannerPic(context, "", resourceId, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadBannerPic(Context context, File file, ImageView tartgetIv) {
        loadBannerPic(context, "", 0, file, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBannerPic(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadBannerPic(context, "", 0, file, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadBannerPic(Context context, Uri uri, ImageView tartgetIv) {
        loadBannerPic(context, "", 0, null, uri, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBannerPic(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadBannerPic(context, "", 0, null, uri, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadBannerPic(Context context, byte[] model, ImageView tartgetIv) {
        loadBannerPic(context, "", 0, null, null, model, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBannerPic(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadBannerPic(context, "", 0, null, null, model, tartgetIv, w, h);
    }

    /**
     * 加载banner图
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     */
    private static void loadBannerPic(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .priority(Priority.HIGH)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .priority(Priority.HIGH)
                    .into(targetIv);
            }
        }

        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .priority(Priority.HIGH)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .priority(Priority.HIGH)
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .priority(Priority.HIGH)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .priority(Priority.HIGH)
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .priority(Priority.HIGH)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .priority(Priority.HIGH)
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .priority(Priority.HIGH)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .priority(Priority.HIGH)
                    .into(targetIv);
            }
        }
    }
    /**************************以上 方形banner图****************************/


    /**************************4、以下 高清大图***************************/

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadLargePic(Context context, String string, ImageView tartgetIv) {
        loadLargePic(context, string, 0, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadLargePic(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadLargePic(context, string, 0, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadLargePic(Context context, Integer resourceId, ImageView tartgetIv) {
        loadLargePic(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadLargePic(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadLargePic(context, "", resourceId, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadLargePic(Context context, File file, ImageView tartgetIv) {
        loadLargePic(context, "", 0, file, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadLargePic(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadLargePic(context, "", 0, file, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadLargePic(Context context, Uri uri, ImageView tartgetIv) {
        loadLargePic(context, "", 0, null, uri, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadLargePic(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadLargePic(context, "", 0, null, uri, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadLargePic(Context context, byte[] model, ImageView tartgetIv) {
        loadLargePic(context, "", 0, null, null, model, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadLargePic(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadLargePic(context, "", 0, null, null, model, tartgetIv, w, h);
    }

    /**
     * 加载大图
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     */
    private static void loadLargePic(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.5f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.5f)
                    .into(targetIv);
            }
        }

        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
//                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.5f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
//                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.5f)
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.5f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.5f)
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .crossFade()
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.zero_gray)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .crossFade()
                    .into(targetIv);
            }
        }
    }
    /**************************以上 高清大图***************************/


    /**************************5、以下 普通图***************************/

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadNormalPic(Context context, String string, ImageView tartgetIv) {
        loadNormalPic(context, string, 0, null, null, null, tartgetIv, 0, 0);
    }

    public static void loadNormalPic(Context context, String string, int error, ImageView tartgetIv) {
        loadNormalPic(context, string, 0, null, null, null, tartgetIv, error, R.color.T_all);
    }

    public static void loadNormalPic(Context context, String string, int error, int place, ImageView tartgetIv) {
        loadNormalPic(context, string, 0, null, null, null, tartgetIv, error, place);
    }

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadNormalPic(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadNormalPic(context, string, 0, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadNormalPic(Context context, Integer resourceId, ImageView tartgetIv) {
        loadNormalPic(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadNormalPic(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadNormalPic(context, "", resourceId, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadNormalPic(Context context, File file, ImageView tartgetIv) {
        loadNormalPic(context, "", 0, file, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadNormalPic(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadNormalPic(context, "", 0, file, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadNormalPic(Context context, Uri uri, ImageView tartgetIv) {
        loadNormalPic(context, "", 0, null, uri, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadNormalPic(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadNormalPic(context, "", 0, null, uri, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadNormalPic(Context context, byte[] model, ImageView tartgetIv) {
        loadNormalPic(context, "", 0, null, null, model, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadNormalPic(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadNormalPic(context, "", 0, null, null, model, tartgetIv, w, h);
    }

    /**
     * 加载普通图
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     */
    private static void loadNormalPic(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .into(targetIv);
            }
        }

        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.drawable.image_error)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }
    }
    /**************************以上 普通图***************************/


    /**************************6、以下 高斯模糊图***************************/
    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     */
    public static void loadBlurPic(Context context, String string, ImageView tartgetIv) {
        loadBlurPic(context, string, 0, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param string    图片来源为String
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBlurPic(Context context, String string, ImageView tartgetIv, int w, int h) {
        loadBlurPic(context, string, 0, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     */
    public static void loadBlurPic(Context context, Integer resourceId, ImageView tartgetIv) {
        loadBlurPic(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param resourceId 图片来源为Integer
     * @param tartgetIv  目标控件
     * @param w          宽
     * @param h          高
     */
    public static void loadBlurPic(Context context, Integer resourceId, ImageView tartgetIv, int w, int h) {
        loadBlurPic(context, "", resourceId, null, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     */
    public static void loadBlurPic(Context context, File file, ImageView tartgetIv) {
        loadBlurPic(context, "", 0, file, null, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param file      图片来源为File
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBlurPic(Context context, File file, ImageView tartgetIv, int w, int h) {
        loadBlurPic(context, "", 0, file, null, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     */
    public static void loadBlurPic(Context context, Uri uri, ImageView tartgetIv) {
        loadBlurPic(context, "", 0, null, uri, null, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param uri       图片来源为Uri
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBlurPic(Context context, Uri uri, ImageView tartgetIv, int w, int h) {
        loadBlurPic(context, "", 0, null, uri, null, tartgetIv, w, h);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     */
    public static void loadBlurPic(Context context, byte[] model, ImageView tartgetIv) {
        loadBlurPic(context, "", 0, null, null, model, tartgetIv, 0, 0);
    }

    /**
     * @param context
     * @param model     图片来源为byte[]
     * @param tartgetIv 目标控件
     * @param w         宽
     * @param h         高
     */
    public static void loadBlurPic(Context context, byte[] model, ImageView tartgetIv, int w, int h) {
        loadBlurPic(context, "", 0, null, null, model, tartgetIv, w, h);
    }

    /**
     * 加载模糊图片
     * 以下1-5个来源中，每次只能有一个有值
     *
     * @param context
     * @param string     来源 1
     * @param resourceId 来源 2
     * @param file       来源 3
     * @param uri        来源 4
     * @param model      来源 5
     * @param targetIv
     * @param w
     * @param h
     */
    private static void loadBlurPic(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .crossFade()
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            }
        }

        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.default_bg)
                    .error(R.color.default_bg)
                    .centerCrop()
                    .crossFade()
                    .bitmapTransform(new TransformBlur(context, TransformBlur.MAX_RADIUS))
                    .into(targetIv);
            }
        }
    }

    /**************************以上 高斯模糊图***************************/
    public static void loadBackgroundPic(Context context, Integer resourceId, final ImageView tartgetIv) {
        Glide.with(context)
            .load(resourceId)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(new RequestListener<Integer, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    if (tartgetIv == null) {
                        return false;
                    }
                    if (tartgetIv.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        tartgetIv.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    ViewGroup.LayoutParams params = tartgetIv.getLayoutParams();
                    int vw = tartgetIv.getWidth() - tartgetIv.getPaddingLeft() - tartgetIv.getPaddingRight();
                    float scale = (float) vw / (float) resource.getIntrinsicWidth();
                    int vh = Math.round(resource.getIntrinsicHeight() * scale);
                    params.height = vh + tartgetIv.getPaddingTop() + tartgetIv.getPaddingBottom();
                    tartgetIv.setLayoutParams(params);
                    return false;
                }
            })
            .error(R.drawable.image_error)
            .crossFade()
            .thumbnail(0.5f)
            .into(tartgetIv);
    }

    /**************************6、以下 没有占位图***************************/
    public static void loadNormalPicNoPlace(Context context, Integer resourceId, ImageView tartgetIv) {
        loadNormalPicNoPlace(context, "", resourceId, null, null, null, tartgetIv, 0, 0);
    }

    private static void loadNormalPicNoPlace(Context context, String string, Integer resourceId, File file, Uri uri, byte[] model, ImageView targetIv, int w, int h) {

        //来源于String
        if (!TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(string)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .into(targetIv);
            }
        }
        //来源于Integer
        else if (resourceId != 0 && TxtUtil.isEmpty(string) && file == null && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(resourceId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .into(targetIv);
            }
        }

        //来源于File
        else if (file != null && TxtUtil.isEmpty(string) && resourceId == 0 && uri == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .override(w, h)
                    .thumbnail(0.8f)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于Uri
        else if (uri != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && model == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.8f)
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }

        //来源于byte[]
        else if (model != null && TxtUtil.isEmpty(string) && resourceId == 0 && file == null && uri == null) {
            if (w != 0 && h != 0) {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .override(w, h)
                    .into(targetIv);
            } else {
                Glide.with(context)
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .crossFade()
                    .into(targetIv);
            }
        }
    }
}
