package widget.small.com.smallwidget.business.bean;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/4/8 000823:29
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class Event implements Serializable {

    private int day;
    private String desc;
    private int resId;

    public Event(int day, String desc, int resId) {
        this.day = day;
        this.desc = desc;
        this.resId = resId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
