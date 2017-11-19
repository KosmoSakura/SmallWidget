package widget.small.com.smallwidget.helper.tools;

import android.app.Activity;
import android.content.Intent;

import widget.small.com.smallwidget.helper.tools.base.Code;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/4/25 002516:52
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class Ways {

    public static void sendBrad(String msg, Activity act) {
        Intent updateIntent = new Intent(Code.Broadcast.ActionUpdateAll);
        updateIntent.putExtra("weight", msg);
        act.sendBroadcast(updateIntent);
    }
}
