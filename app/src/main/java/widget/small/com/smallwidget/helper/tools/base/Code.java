package widget.small.com.smallwidget.helper.tools.base;

/**
 * Description:消息类型定义
 * <p>
 * Author: Kosmos
 * Time: 2017/4/19 001911:35
 * Email:ZeroProject@foxmail.com
 * Events:新增接口分类
 */
public interface Code {
    /**
     * 新增接口错误Code
     */
    interface System {
        int BASE = 0xFF000000;
        int Immersive = BASE + 1;//启用沉浸式状态栏
        int DefaultColor = BASE + 2;//启用默认颜色
        int DefaultDark = BASE + 3;//启用暗色
        int TypeColorParse = BASE + 4;//转换颜色
        int TypeColorRes = BASE + 5;//资源颜色
        int HideDialgeEdt = BASE + 6;//隐藏对话框输入
        int ShowDialgeEdt = BASE + 7;//隐藏对话框输入


        int TAKE_PHOTO = 25;//拍照
        int CHOOSE_PHOTO = 77;//相册
        int Photo_Back = 77;//相册
        int REQUEST_PREVIEW = 99;//预览请求状态码
    }

    /**
     * 新增接口错误Code
     */
    interface StatusCode {
        int BASE = 0xFF000001;
        int kg = BASE + 1;
        int jing = BASE - 1;

        int Setting_To_Theme = BASE + 1;
        int Setting_to_ResetData = BASE + 2;
        int Main_To_Face = BASE + 3;
        int Main_To_Timer = BASE + 4;
    }

    interface Broadcast {
        String ActionUpdateAll = "com.kosmos.widget.UPDATE_ALL"; // 更新widget的广播对应的action
        String ActionIntentService = "android.appwidget.kosmos.APP_WIDGET_SERVICE"; //启动WidgetService服务对应的action
    }

    interface Constants {
        String TerminalCode = "android_app";
        String TerminalVersion = "1.0.1";//格式：1.0.0
        String IMEID_MD5 = "";//TerminalSim
        int clientVersion = 0;//客户端版本号
        String phoneMODEL = "";//获取手机型号
        String versionName = "";//获取手机版本号
    }

    class Config {
        public static String BASE_PATH = null;
        public static String CACHE_PATH = null;
        public static String SAVE_SD_FLODER = null;//图像缓存SD卡路径的目录，主要用于缓存
        public static String GLIDE_CACHE = null;
        public static String IMAGE_DOWNLOAD = null;
    }

}
