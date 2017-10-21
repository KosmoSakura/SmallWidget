package widget.small.com.smallwidget.bean;

import widget.small.com.smallwidget.tools.base.Code;

/**
 * Description:主题
 * <p>
 * Author: Kosmos
 * Time: 2017/4/21 002115:32
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class ThemeBean {
    private boolean select;
    private int theme_id;
    private String name;
    private int base_color;
    private int color_type;

    public ThemeBean(boolean select, int theme_id, String name, int base_color, int color_type) {
        this.select = select;
        this.theme_id = theme_id;
        this.name = name;
        this.base_color = base_color;
        this.color_type = color_type;
    }

    public ThemeBean(boolean select, int theme_id, String name, int base_color) {
        this.select = select;
        this.theme_id = theme_id;
        this.name = name;
        this.base_color = base_color;
        this.color_type = Code.System.TypeColorRes;
    }

    public int getColor_type() {
        return color_type;
    }

    public void setColor_type(int color_type) {
        this.color_type = color_type;
    }

    public int getBase_color() {
        return base_color;
    }

    public void setBase_color(int base_color) {
        this.base_color = base_color;
    }

    public boolean getSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
