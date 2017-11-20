package space.zero.kosmos.kos.bean;

/**
 * Description:PopupWindowList填充对象
 * <p>
 * Author: yi.zhang
 * Time: 2017/5/9 0009
 * E-mail: yi.zhang@rato360.com
 */
public class PopupWindowListBean {
    private String content;//内容
    private int color;//内容的颜色
    private int drawable = 0;//图片

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public PopupWindowListBean(String content, int color) {
        this.content = content;
        this.color = color;
    }

    public PopupWindowListBean(String content, int color, int drawable) {
        this.content = content;
        this.color = color;
        this.drawable = drawable;
    }
}
