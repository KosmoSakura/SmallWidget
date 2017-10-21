package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class KosmosDaoGenerator {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
//        Schema schema = new Schema(1, "greendao");
//      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
        Schema schema = new Schema(1, "greendao.bean");
        schema.setDefaultJavaPackageDao("greendao.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
//        addNoteLogin(schema);
        addWeight(schema);
        addTheme(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "D:\\ZeroSpace\\SVNDatas\\SmallWidget\\app\\src\\main\\java-gen");
    }

    //视频
    private static void addTheme(Schema schema) {
        Entity note = schema.addEntity("Themes");
        note.addIdProperty();
        note.addBooleanProperty("select");
        note.addStringProperty("name");
        note.addIntProperty("theme_id");
        note.addIntProperty("base_color");
    }

    //api接口数据缓存
    private static void addWeight(Schema schema) {
        Entity note = schema.addEntity("Weight");
        note.addIdProperty();
        note.addStringProperty("name");//姓名
        note.addFloatProperty("value");//体重
        note.addIntProperty("unit");//单位：1-斤，-1-千克
        note.addStringProperty("time");// 时间
        note.addIntProperty("year");
        note.addIntProperty("month");
        note.addIntProperty("day");
        note.addIntProperty("hour");
        note.addIntProperty("min");
        note.addStringProperty("hour_str");
        note.addBooleanProperty("defaultVaule");//默认
    }

    private static void addNoteLogin(Schema schema) {
        Entity note = schema.addEntity("User");
        note.addIdProperty();
        note.addIntProperty("UserId");//用户id
        note.addIntProperty("percentage");//资料完整度
        note.addStringProperty("city");//城市
        note.addStringProperty("sex");//性别
        note.addStringProperty("email");//邮件
        note.addStringProperty("headicon");//头像
        note.addStringProperty("industrytag");//行业标签
        note.addStringProperty("interesttags");//兴趣标签
        note.addStringProperty("introduction");//简介
        note.addStringProperty("realname");//姓名
        note.addStringProperty("telephone");//电话
        note.addStringProperty("username");//用户名
        note.addBooleanProperty("hasShop");//教育经历
        note.addStringProperty("birthday");//生日
        note.addStringProperty("idcard");//身份证号码
        note.addStringProperty("classify");//账户分类(默认0，个人账户；1：管理账户 2：企业账户)
        note.addStringProperty("status");////账户状态（默认0，未激活；1：激活；2：锁定；3：注销）
        note.addBooleanProperty("online");//是否在线
    }
}
