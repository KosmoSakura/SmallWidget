package widget.small.com.smallwidget.tools;

import android.content.Context;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import widget.small.com.smallwidget.tools.base.TxtBase;

/**
 * Description:该类写所有的URL以及相关字符串拼接
 * <p>
 * Author: Kosmos
 * Time: 2016/8/29 0029 11:32
 * Email:ZeroProject@foxmail.com
 * Events:Kosmos-2017/2/21-修改
 * ------>1.新增字符串处理函数
 * ------>2.原有字符串工具增强
 */
public class TxtUtil extends TxtBase {


    public static String replace(String old, String newChar, String original) {
        return original.replace(old, newChar);
    }

    public static boolean isEmpty(TextView tv) {
        if (tv == null) {
            return true;
        } else
            return isEmpty(tv.getText().toString());
    }

    public static String isNull(TextView tv) {
        String str = tv.getText().toString();
        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    public static int getLength(TextView tv) {
        String str = tv.getText().toString();
        if (isEmpty(str)) {
            return 0;
        } else {
            return str.length();
        }
    }

    public static String arrayTostr(String[] arr, String space) {
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str).append(space);
        }
        return sb.toString();
    }

    /**
     * 转半角函数
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 移除list中为null的元素
     */
    public static void clearNullOfList(List list) {
        if (list.contains(null)) {
            list.remove(null);
            if (list.contains(null)) {
                clearNullOfList(list);
            }
        }
    }

    /**
     * 根据资源获取字符串的值
     */
    public static String getString(Context context, int resID) {
        return context.getResources().getString(resID);
    }

    public static boolean urlFromQiniu(String str) {
        return !urlFromSDCard(str) && containsIgnoreCase(str, "tianyi");
    }

    public static boolean urlFromSDCard(String str) {
        return !TxtUtil.isEmpty(str) && TxtUtil.containsIgnoreCase(str, "storage") || TxtUtil.containsIgnoreCase(str, "emulated");
    }

    public static boolean containsIgnoreCase(String str, String key) {
        return (str.toUpperCase()).contains(key.toUpperCase());
    }


    /**
     * 获取文件名
     */
    public static String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? System.currentTimeMillis() + "" : path.substring(separatorIndex + 1, path.length());
    }

    /**
     * 获取后缀名
     */
    public static String getFileSuffix(String path) {
        int separatorIndex = path.lastIndexOf(".");
        return (separatorIndex < 0) ? ".jpg" : path.substring(separatorIndex, path.length());
    }




    /**
     * 过滤html标签
     *
     * 调用的时候非常方便，如下：
     * String content = data.getNoteContent();//content含有HTML标签
     * noteContent.setText(TxtUtil.delHTMLTag(content));
     */
    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";
    /**
     * 定义空格回车换行符
     */
    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";

    public static String delHTMLTag(String htmlStr) {
        // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        // 过滤style标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        // 过滤html标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        // 过滤空格回车标签
        Pattern p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        return htmlStr.trim(); // 返回文本字符串
    }

}
