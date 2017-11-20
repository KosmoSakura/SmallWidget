package space.zero.kosmos.kos.bean;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/4/8 000823:21
 * Email:ZeroProject@foxmil.com
 * Events:
 */
public class Month implements Serializable {

    private int month;
    private int year;
    private Event[] daylist;

    public Month(int year, int month) {
        this.month = month;
        this.year = year;
    }

    public Month(int year, int month, Event... day) {
        this.month = month;
        this.year = year;
        this.daylist = day;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Event[] getDaylist() {
        return daylist;
    }

    public void setDaylist(Event[] daylist) {
        this.daylist = daylist;
    }
}


