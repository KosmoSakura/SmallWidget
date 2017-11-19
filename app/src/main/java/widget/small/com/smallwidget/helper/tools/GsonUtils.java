package widget.small.com.smallwidget.helper.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZeroProject on 2016/8/29 0029 16:24
 * Email:ZeroProject@foxmail.com
 */
public class GsonUtils {


    private static Gson gson ;

    static  {
        gson = new GsonBuilder()
                 .setDateFormat("yyyy-MM-dd HH:mm:ss")
                 .create();

    }


    /**
     * @param jsonString
     * @param cls
     * @param <T>
     * @return 返回一个实体类对象
     */
    public static <T> T getPerson(String jsonString, Class<T> cls) throws Exception {
        T t  = gson.fromJson(jsonString, cls);

        return t;
    }

    /**
     * @param jsonString
     * @param cls
     * @param <T>
     * @return 返回一个列表
     */
    public static <T> ArrayList<T> getPersons(String jsonString, Class<T> cls) throws Exception {
        ArrayList<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();

        for(final JsonElement elem : array){

            list.add(gson.fromJson(elem, cls));
        }
//        Gson gson = new Gson();
//        list = gson.fromJson(jsonString, new TypeToken<List<T>>() {}.getType());
//        return list;

        return list;
    }

    /**
     * @param jsonString
     * @return 返回一个带map的列表
     */
    public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = gson.fromJson(jsonString,
                new TypeToken<List<Map<String, Object>>>() {
                }.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    //类型转换
    public static <T> ArrayList<T> convertDto(ArrayList list,Class<T> cls){
        ArrayList<T> dtoList = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            String json = gson.toJson(object);
            T dto = gson.fromJson(json, cls);

            dtoList.add(dto);
        }
        return dtoList;
    }
}
