package widget.small.com.smallwidget.activity;

import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.adapter.times.TimeLine;
import widget.small.com.smallwidget.adapter.times.TimeLineAdapter;
import widget.small.com.smallwidget.base.BaseActivity;
import widget.small.com.smallwidget.bean.Event;
import widget.small.com.smallwidget.bean.Month;
import widget.small.com.smallwidget.tools.base.Code;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Email:ZeroProject@foxmail.com
 */
public class TimeActivity extends BaseActivity {
    private ExpandableListView elv_Main;
    private TimeLineAdapter timeLineAdapter;
    private TimeLine timeLine;
    private List<Month> list;
    @Override
    protected void initView() {
        setStatusBarColor(this, Code.System.Immersive);
        elv_Main = findView(R.id.main_elv);

    }


    @Override
    protected void initData() {
        list = new ArrayList<>();

        Event day1 = new Event(25, "今天和倩倩相恋", R.drawable.love_2);
        list.add(new Month(2016, 3, day1));
        Event day2 = new Event(9, "第一次去酒吧", R.drawable.love_1);
        list.add(new Month(2016, 4, day2));
        Event day3 = new Event(1, "今天我把倩倩带回了老家", R.drawable.love_3);
        Event day4 = new Event(17, "倩倩生气一个人去了天台山", R.drawable.love);
        list.add(new Month(2016, 5, day3, day4));
        Event day5 = new Event(10, "今天教师节，我和倩倩去找我的高中老师", R.drawable.love_4);
        Event day52 = new Event(10, "倩倩给我过生日", R.drawable.love_5);
        Event day51 = new Event(19, "我今天搬到了隔壁单元", R.drawable.love5);
        list.add(new Month(2016, 9, day5, day52, day51));
        Event day6 = new Event(29, "今天是倩倩的生日", R.drawable.love_6);
        list.add(new Month(2016, 10, day6));
        Event day7 = new Event(29, "情人节快乐", R.drawable.love_7);
        list.add(new Month(2017, 10, day7));
        Event day8 = new Event(25, "我们一周年啦~", R.drawable.love_8);
        list.add(new Month(2017, 3, day8));
        timeLineAdapter = new TimeLineAdapter(this, list);
        elv_Main.setAdapter(timeLineAdapter);
        elv_Main.setDivider(null);
        elv_Main.setGroupIndicator(null);

        for (int i = 0; i < timeLineAdapter.getGroupCount(); i++) {
            elv_Main.expandGroup(i);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_time;
    }

    @Override
    protected void initListener() {

    }

}
