package widget.small.com.smallwidget.helper.widget.time;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import widget.small.com.smallwidget.helper.widget.time.TimePopupWindow.Type;
import widget.small.com.smallwidget.R;


public class WheelTime {
    private View view;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private LinearLayout ly;
    private LinearLayout lm;
    private LinearLayout ld;
    private LinearLayout lh;
    private LinearLayout lmin;

    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    public int screenheight;
    public boolean isShowHanzi = false;

    private Type type;
    private static int START_YEAR = 1990, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelTime(View view) {
        super();
        this.view = view;
        type = Type.ALL;
        setView(view);
    }

    public WheelTime(View view, Type type) {
        super();
        this.view = view;
        this.type = type;
        setView(view);
    }

    public void setPicker(int year, int month, int day) {
        this.setPicker(year, month, day, 0, 0);
    }

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void setPicker(int year, int month, int day, int h, int m) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        Context context = view.getContext();
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        ly = (LinearLayout) view.findViewById(R.id.y_l);
        wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
        if (isShowHanzi) {
            wv_year.setLabel(context.getString(R.string.pickerview_year));// 添加文字
        }

        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        lm = (LinearLayout) view.findViewById(R.id.m_l);
        wv_month.setAdapter(new NumericWheelAdapter(1, 12));

        if (isShowHanzi) {
            wv_month.setLabel(context.getString(R.string.pickerview_month));
        }
        wv_month.setCurrentItem(month);

        // 日
        wv_day = (WheelView) view.findViewById(R.id.day);
        ld = (LinearLayout) view.findViewById(R.id.d_l);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                wv_day.setAdapter(new NumericWheelAdapter(1, 29));
            else
                wv_day.setAdapter(new NumericWheelAdapter(1, 28));
        }

        if (isShowHanzi) {
            wv_day.setLabel(context.getString(R.string.pickerview_day));
        }
        wv_day.setCurrentItem(day - 1);


        wv_hours = (WheelView) view.findViewById(R.id.hour);
        lh = (LinearLayout) view.findViewById(R.id.h_l);
        wv_hours.setAdapter(new NumericWheelAdapter(0, 23));

        if (isShowHanzi) {
            wv_hours.setLabel(context.getString(R.string.pickerview_hours));// 添加文字
        }
        wv_hours.setCurrentItem(h);

        wv_mins = (WheelView) view.findViewById(R.id.min);
        lmin = (LinearLayout) view.findViewById(R.id.mim_l);
        wv_mins.setAdapter(new NumericWheelAdapter(0, 59));

        if (isShowHanzi) {
            wv_mins.setLabel(context.getString(R.string.pickerview_minutes));// 添加文字
        }
        wv_mins.setCurrentItem(m);

        // 添加"年"监听
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem = 30;
                if (list_big
                    .contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (list_little.contains(String.valueOf(wv_month
                    .getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                        || year_num % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }
            }
        };
        // 添加"月"监听
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                int maxItem = 30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                    maxItem = 31;
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                    maxItem = 30;
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
                        .getCurrentItem() + START_YEAR) % 100 != 0)
                        || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0) {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                        maxItem = 29;
                    } else {
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                        maxItem = 28;
                    }
                }
                if (wv_day.getCurrentItem() > maxItem - 1) {
                    wv_day.setCurrentItem(maxItem - 1);
                }

            }
        };
        wv_year.addChangingListener(wheelListener_year);
        wv_month.addChangingListener(wheelListener_month);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        switch (type) {
            case ALL:
                textSize = (screenheight / 100) * 3;
                break;
            case YEAR:
                textSize = (screenheight / 100) * 4;
                lm.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                ld.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                lh.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                lmin.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                break;
            case YEAR_MONTH_DAY:
                textSize = (screenheight / 100) * 4;
                lh.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                lmin.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = (screenheight / 100) * 4;
                ly.setVisibility(View.GONE);
                wv_year.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                wv_month.setVisibility(View.GONE);
                ld.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = (screenheight / 100) * 3;
                ly.setVisibility(View.GONE);
                wv_year.setVisibility(View.GONE);
                break;
            case YEAR_MONTH:
                textSize = (screenheight / 100) * 4;
                ld.setVisibility(View.GONE);
                wv_day.setVisibility(View.GONE);
                lh.setVisibility(View.GONE);
                wv_hours.setVisibility(View.GONE);
                lmin.setVisibility(View.GONE);
                wv_mins.setVisibility(View.GONE);
        }

        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;
        wv_hours.TEXT_SIZE = textSize;
        wv_mins.TEXT_SIZE = textSize;

    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wv_year.setCyclic(cyclic);
        wv_month.setCyclic(cyclic);
        wv_day.setCyclic(cyclic);
        wv_hours.setCyclic(cyclic);
        wv_mins.setCyclic(cyclic);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        if (type == Type.ALL) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("年")
                .append((wv_month.getCurrentItem() + 1)).append("月")
                .append((wv_day.getCurrentItem() + 1)).append("日 ")
                .append(wv_hours.getCurrentItem()).append(":")
                .append(wv_mins.getCurrentItem());
        } else if (type == Type.YEAR_MONTH_DAY) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("年")
                .append((wv_month.getCurrentItem() + 1)).append("月")
                .append((wv_day.getCurrentItem() + 1)).append("日 ");
        } else if (type == Type.HOURS_MINS) {
            sb.append(wv_hours.getCurrentItem()).append(":")
                .append(wv_mins.getCurrentItem());
        } else if (type == Type.MONTH_DAY_HOUR_MIN) {
            sb.append((wv_month.getCurrentItem() + 1)).append("月")
                .append((wv_day.getCurrentItem() + 1)).append("日 ")
                .append(wv_hours.getCurrentItem()).append(":")
                .append(wv_mins.getCurrentItem());
        } else if (type == Type.YEAR_MONTH) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("年")
                .append((wv_month.getCurrentItem() + 1)).append("月");
        } else if (type == Type.YEAR) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("年");
        }
        return sb.toString();
    }

    public int getYear() {
        return wv_year.getCurrentItem() + START_YEAR;
    }

    public int getMonth() {
        return wv_month.getCurrentItem() + 1;
    }

    public int getDay() {
        return wv_day.getCurrentItem() + 1;
    }

    public int getHour() {
        return wv_hours.getCurrentItem();
    }

    public int getMin() {
        return wv_mins.getCurrentItem();
    }

    public String getHours() {
        return wv_hours.getCurrentItem() + ":" + wv_mins.getCurrentItem();
    }
}