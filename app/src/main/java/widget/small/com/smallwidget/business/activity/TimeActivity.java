package widget.small.com.smallwidget.business.activity;

import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.adapter.times.TimeLine;
import widget.small.com.smallwidget.business.adapter.times.TimeLineAdapter;
import widget.small.com.smallwidget.helper.base.BaseActivity;
import widget.small.com.smallwidget.business.bean.Event;
import widget.small.com.smallwidget.business.bean.Month;
import widget.small.com.smallwidget.helper.tools.base.Code;

/**
 * Description:时光轴
 * <p>
 * Author: Kosmos 逻辑
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

        Event day1 = new Event(25, "测试标题-->1", R.drawable.love_2);
        list.add(new Month(2016, 3, day1));
        Event day2 = new Event(9, "测试标题-->2", R.drawable.love_1);
        list.add(new Month(2016, 4, day2));
        Event day3 = new Event(1, "测试标题-->3", R.drawable.love_3);
        Event day4 = new Event(17, "测试标题-->4", R.drawable.love);
        list.add(new Month(2016, 5, day3, day4));
        Event day5 = new Event(10, "测试标题-->5", R.drawable.love_4);
        Event day52 = new Event(10, "测试标题-->6", R.drawable.love_5);
        Event day51 = new Event(19, "测试标题-->7", R.drawable.love5);
        list.add(new Month(2016, 9, day5, day52, day51));
        Event day6 = new Event(29, "测试标题-->8", R.drawable.love_6);
        list.add(new Month(2016, 10, day6));
        Event day7 = new Event(29, "测试标题-->9", R.drawable.love_7);
        list.add(new Month(2017, 10, day7));
        Event day8 = new Event(25, "测试标题-->10", R.drawable.love_8);
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
