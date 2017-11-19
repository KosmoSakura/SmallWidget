package widget.small.com.smallwidget.helper.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:单位换算工具类
 * <p>
 * Author: Kosmos
 * Time: 2017/5/26 002614:09
 * Email:ZeroProject@foxmail.com
 * Events: UnitUtil  UnitTool
 * 2017-7-14:添加数组转列表
 */
public class UnitUtil {
    /**
     * 格式化字节单位
     *
     * @param size 单位：字节
     */
    public static String sizeFormatbit(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "\tByte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tKB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tMB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tGB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tTB";
    }

    /**
     * 毫秒换成几天前几小时几分钟
     * 1天=86400000毫秒
     *
     * @return 60天 0小时 0分钟 0秒
     */
    public static String sizeFormatTimeAllShow(long millisecond) {
        StringBuilder stringBuilder = new StringBuilder();
        long year = millisecond / 31536000000L;
        long day = (millisecond % 31536000000L) / 86400000;
        long hour = (millisecond % 86400000) / 3600000;
        long minute = (millisecond % 86400000 % 3600000) / 60000;
        long ss = (millisecond % 86400000 % 3600000 % 60000) / 1000;

        if (year > 0) {
            stringBuilder.append(year).append("年\b");
        }
        if (day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(day)).append("天\b");
        }
        if (hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(hour)).append("小时\b");
        }
        if (minute > 0 || hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(minute)).append("分钟\b");
        }
        if (ss > 0 || minute > 0 || hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(ss)).append("秒\b");
        }
        return stringBuilder.toString();
    }

    /**
     * 毫秒换成几天前几小时几分钟
     * 1天=86400000毫秒
     *
     * @return 16年 160天 45秒
     */
    public static String sizeFormatTime(long millisecond) {
        StringBuilder stringBuilder = new StringBuilder();
        long year = millisecond / 31536000000L;
        long day = (millisecond % 31536000000L) / 86400000;
        long hour = (millisecond % 86400000) / 3600000;
        long minute = (millisecond % 86400000 % 3600000) / 60000;
        long ss = (millisecond % 86400000 % 3600000 % 60000) / 1000;

        if (year > 0) {
            stringBuilder.append(year).append("年\b");
        }
        if (day > 0) {
            stringBuilder.append(String.valueOf(day)).append("天\b");
        }
        if (hour > 0) {
            stringBuilder.append(String.valueOf(hour)).append("小时\b");
        }
        if (minute > 0) {
            stringBuilder.append(String.valueOf(minute)).append("分钟\b");
        }
        if (ss > 0) {
            stringBuilder.append(String.valueOf(ss)).append("秒\b");
        }
        return stringBuilder.toString();
    }

    public static int pxParse(float x, Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x, dm);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new NullPointerException("传入对象为空");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
            && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }



    /**
     * 数组转List
     */
    public static <T> List<T> arrayTolist(T[] arr) {
        List<T> list = new ArrayList<>();
        if (arr != null) {
            Collections.addAll(list, arr);
        }
        return list;
    }





}
