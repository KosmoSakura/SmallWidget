package space.zero.kosmos.kos.bean;

/**
 * @Description:
 * @Author: Kosmos
 * @Time: 2017/11/19 001920:05
 * @Email:ZeroProject@foxmail.com
 */
public class MainItemBean {
    private int resImage;
    private String name;
    private int id;


    public MainItemBean(int resImage, String name) {
        this.resImage = resImage;
        this.name = name;
    }

    public MainItemBean(int resImage, String name, int id) {
        this.resImage = resImage;
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
