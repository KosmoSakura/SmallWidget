package widget.small.com.smallwidget.tools.photos;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.bean.PopupWindowListBean;
import widget.small.com.smallwidget.tools.ToastUtil;
import widget.small.com.smallwidget.tools.TxtUtil;
import widget.small.com.smallwidget.widget.pop.PopupWindowListDialog;


/**
 * Description:图片采集器的帮助工具
 * <p>
 * Author: Kosmos
 * Time: 2017/7/25 002515:35
 * Email:ZeroProject@foxmail.com
 * Events:名字还算亲民
 */
public class ImageCollectorHelper {
    private ImageCollector collector;
    private Activity act;

    public ImageCollectorHelper(Activity activity, ImageCollectListener liser) {
        this.act = activity;
        this.listener = liser;
        if (collector == null) {
            collector = new ImageCollector(activity);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        collector.onActivityResult(requestCode, resultCode, data);
    }

    public void pickFromCamera() {
        collector.getCameraPhoto();
    }
    public void pickFromAlbum() {
        collector.getLocalPhoto();
    }

    public void showCollectorPop() {
        collector.setOnImageCollectListener(new ImageCollector.ImageCollectListener() {
            @Override
            public void onImageCollected(String image_path) {
                if (listener != null) {
//                    Logger.e("图片地址:" + image_path);
                    if (TxtUtil.isEmpty(image_path)) {
                        ToastUtil.CustomShort("图片选择失败");
                    } else {
                        listener.onImageCollected(image_path);
                    }
                }
            }
        });
        final PopupWindowListDialog.Builder builder = new PopupWindowListDialog.Builder(act);
        builder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    collector.getCameraPhoto();
                } else if (position == 1) {
                    collector.getLocalPhoto();
                }
            }
        });
        List<PopupWindowListBean> items = new ArrayList<PopupWindowListBean>();
        PopupWindowListBean bean1 = new PopupWindowListBean("拍照", R.color.default_black);
        PopupWindowListBean bean2 = new PopupWindowListBean("从相册选择", R.color.default_black);
        items.add(bean1);
        items.add(bean2);
        builder.create(items);
    }


    private ImageCollectListener listener;

    public interface ImageCollectListener {
        void onImageCollected(String image_path);
    }
}
