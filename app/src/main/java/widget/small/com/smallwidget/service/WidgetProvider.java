package widget.small.com.smallwidget.service;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.tools.base.Code;
import widget.small.com.smallwidget.tools.ToastUtil;
import widget.small.com.smallwidget.tools.logger.Logger;

/*
 * @author : skywang <wangkuiwu@gmail.com>
 * description : 提供App Widget
 */

public class WidgetProvider extends AppWidgetProvider {

    // 启动ExampleAppWidgetService服务对应的action
//    private final Intent serverIntent = new Intent();
    // 保存 widget 的id的HashSet，每新建一个 widget 都会为该 widget 分配一个 id。
    private static Set idsSet = new HashSet();
    // 按钮信息
    private static final int BUTTON_SHOW = 1;

    //每次更新都调用一次该方法，使用频繁
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // 每次 widget 被创建时，对应的将widget的id添加到set中
        for (int appWidgetId : appWidgetIds) {
            idsSet.add(Integer.valueOf(appWidgetId));
        }
        prtSet();
    }

    // 当 widget 被初次添加 或者 当 widget 的大小被改变时，被调用 
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }


    //当该Widget第一次添加到桌面是调用该方法，可添加多次但只第一次调用
    @Override
    public void onEnabled(Context context) {
        // 在第一个 widget 被创建时，开启服务
//        serverIntent.setClass(context, WidgetService.class);
//        context.startService(serverIntent);
        super.onEnabled(context);
    }

    //删除一个就调用一次
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // 当 widget 被删除时，对应的删除set中保存的widget的id
        for (int appWidgetId : appWidgetIds) {
            idsSet.remove(Integer.valueOf(appWidgetId));
        }
        prtSet();
        super.onDeleted(context, appWidgetIds);
    }

    // 最后一个widget被删除时调用
    @Override
    public void onDisabled(Context context) {
        // 在最后一个 widget 被删除时，终止服务
//        serverIntent.setClass(context, WidgetService.class);
//        context.stopService(serverIntent);
        super.onDisabled(context);
    }


    //接收一次广播消息就调用一次，使用频繁
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (Code.Broadcast.ActionUpdateAll.equals(action)) {
            // “更新”广播
            updateAllAppWidgets(context, AppWidgetManager.getInstance(context), idsSet, intent);
        } else if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE)) {
            // “按钮点击”广播
            Uri data = intent.getData();
            int buttonId = Integer.parseInt(data.getSchemeSpecificPart());
            if (buttonId == BUTTON_SHOW) {
                ToastUtil.CustomShort("点击");
            }
        }

        super.onReceive(context, intent);
    }

    /**
     * 更新所有的 widget
     */
    private void updateAllAppWidgets(Context context, AppWidgetManager appWidgetManager, Set set, Intent intent) {
        String txt = intent.getStringExtra("weight");
        // widget 的id
        int appID;
        // 迭代器，用于遍历所有保存的widget的id
        Iterator it = set.iterator();
        while (it.hasNext()) {
            appID = ((Integer) it.next()).intValue();
            // 获取 app_widget的RemoteViews
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.app_widget);
            remoteView.setTextViewText(R.id.widget_show_txt, txt);
            // 设置显示图片
//            remoteView.setImageViewResource(R.id.widget_show_i, R.drawable.appicon);
            // 设置点击按钮对应的PendingIntent：即点击按钮时，发送广播。
            remoteView.setOnClickPendingIntent(R.id.widget_show_lay, getPendingIntent(context, BUTTON_SHOW));
            // 更新 widget
            appWidgetManager.updateAppWidget(appID, remoteView);
        }
    }

    private PendingIntent getPendingIntent(Context context, int buttonId) {
        Intent intent = new Intent();
        intent.setClass(context, WidgetProvider.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        intent.setData(Uri.parse("custom:" + buttonId));
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pi;
    }

    // 调试用：遍历set
    private void prtSet() {
//        int index = 0;
//        int size = idsSet.size();
//        Iterator it = idsSet.iterator();
//        while (it.hasNext()) {
//            lods(index + " -- " + ((Integer) it.next()).intValue());
//        }
    }

    private void lods(String s) {
        Logger.kosmos_e(s);
    }

    /**
     * 隐式转换为显示
     */
    private Intent getExplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
