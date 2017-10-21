package widget.small.com.smallwidget.adapter.times;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.bean.Event;
import widget.small.com.smallwidget.bean.Month;
import widget.small.com.smallwidget.tools.glide.GlideUtils;


/**
 * Created by Administrator on 2017/2/18.
 */

public class TimeLineAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Month> rowsBeanList;

    public TimeLineAdapter(Context context, List<Month> rowsBeanList) {
        this.context = context;
        if (rowsBeanList == null) {
            this.rowsBeanList = new ArrayList<>();
        } else {
            this.rowsBeanList = rowsBeanList;
        }
    }

    @Override
    public int getGroupCount() {
        return rowsBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return rowsBeanList.get(groupPosition).getDaylist().length;
    }

    @Override
    public Month getGroup(int groupPosition) {
        return rowsBeanList.get(groupPosition);
    }

    @Override
    public Event getChild(int groupPosition, int childPosition) {
        Event[] days = rowsBeanList.get(groupPosition).getDaylist();
        return days[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_timeline, null);
            groupHolder.itemGroupTopLine = convertView.findViewById(R.id.item_group_top_line);
            groupHolder.itemGroupYearMonth = (TextView) convertView.findViewById(R.id.item_group_year_month);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        if (groupPosition == 0) {
            groupHolder.itemGroupTopLine.setVisibility(View.INVISIBLE);
        } else {
            groupHolder.itemGroupTopLine.setVisibility(View.VISIBLE);
        }
        Month rowsBean = getGroup(groupPosition);
        if (null != rowsBean) {
            groupHolder.itemGroupYearMonth.setText(rowsBean.getMonth() + "月" + "\n" + rowsBean.getYear());
        }
        return convertView;
    }

    class GroupHolder {
        View itemGroupTopLine;
        TextView itemGroupYearMonth;
        View itemGroupBottomLine;

    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_timeline, null);
            childHolder.date = (TextView) convertView.findViewById(R.id.item_child_month_day);
            childHolder.desc = (TextView) convertView.findViewById(R.id.item_child_sr_name);
            childHolder.show = (ImageView) convertView.findViewById(R.id.item_child_sr_progress);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        Month rowsBean = getGroup(groupPosition);
        Event  events = getChild(groupPosition, childPosition);
        if (null != events) {
            childHolder.date.setText(rowsBean.getMonth() + "月" + events.getDay() + "日");
            childHolder.desc.setText(events.getDesc());
//            childHolder.show.setImageResource(events.getResId());
            final ChildHolder finalChildHolder = childHolder;
            GlideUtils.loadLargePic(context, events.getResId(), finalChildHolder.show);
        }

        return convertView;
    }

    class ChildHolder {
        TextView desc;
        TextView date;
        ImageView show;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void add(List<Month> rowsBeanList) {
        if (null != rowsBeanList && rowsBeanList.size() != 0) {
            this.rowsBeanList.addAll(rowsBeanList);
            notifyDataSetChanged();
        }
    }
}
