package widget.small.com.smallwidget.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by cl on 2016/12/16.
 */

public class PicTools {
    private static String sdCardDir = Environment.getExternalStorageDirectory() + "/caheImage/";

    public static void clearCache() {
        deleteDir(new File(sdCardDir));
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private static ByteArrayInputStream compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        int options = 100;

        while (baos.toByteArray().length / 1024 > 100) {//循环判断如果压缩后图片是否大于100kb,大于继续压缩

            baos.reset();//重置baos即清空baos

            options -= 10;//每次都减少10

            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中

        //Bitmap bitmap = BitmapFactory.decodeStream(isBm,null,null);//把ByteArrayInputStream数据生成图片

        return isBm;


    }


    /**
     * 在缓存中生成新的上传图片，上传完毕以后删除
     *
     * @param srcPath
     * @return
     */
    public static String compressNewPath(String srcPath) {
        File tempFile = new File(srcPath.trim());
        String fileName = tempFile.getName();
        File dirFile = new File(sdCardDir);  //目录转化成文件夹
        if (!dirFile.exists()) {              //如果不存在，那就建立这个文件夹
            dirFile.mkdirs();
        }

        File file = new File(sdCardDir, fileName);

        FileOutputStream out = null;
        ByteArrayInputStream istemp = getimage(srcPath);
        Bitmap bitmap = BitmapFactory.decodeStream(istemp, null, null);//把ByteArrayInputStream数据生成图片
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bitmap.recycle();
            istemp.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }


    //图片按比例大小压缩方法（根据路径获取图片并压缩）：


    private static ByteArrayInputStream getimage(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();

        //开始读入图片，此时把options.inJustDecodeBounds 设回true了

        newOpts.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;

        int h = newOpts.outHeight;

        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 800f;//这里设置高度为800f

        float ww = 480f;//这里设置宽度为480f

        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = 1;//be=1表示不缩放

        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放

            be = (int) (newOpts.outWidth / ww);

        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放

            be = (int) (newOpts.outHeight / hh);

        }

        if (be <= 0)

            be = 1;

        newOpts.inSampleSize = be;//设置缩放比例

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }

    //图片按比例大小压缩方法（根据路径获取图片并压缩）：


    /*private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos =new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG,100, baos);

        if( baos.toByteArray().length /1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出

            baos.reset();//重置baos即清空baos

            image.compress(Bitmap.CompressFormat.JPEG,50, baos);//这里压缩50%，把压缩后的数据存放到baos中

        }

        ByteArrayInputStream isBm =new ByteArrayInputStream(baos.toByteArray());

        BitmapFactory.Options newOpts =new BitmapFactory.Options();

        //开始读入图片，此时把options.inJustDecodeBounds 设回true了

        newOpts.inJustDecodeBounds =true;

        Bitmap bitmap = BitmapFactory.decodeStream(isBm,null, newOpts);

        newOpts.inJustDecodeBounds =false;

        int w = newOpts.outWidth;

        int h = newOpts.outHeight;

        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 800f;//这里设置高度为800f

        float ww = 480f;//这里设置宽度为480f

        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be =1;//be=1表示不缩放

        if(w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放

            be = (int) (newOpts.outWidth / ww);

        }else if(w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放

            be = (int) (newOpts.outHeight / hh);

        }

        if(be <=0)

        be =1;

        newOpts.inSampleSize = be;//设置缩放比例

        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        isBm =new ByteArrayInputStream(baos.toByteArray());

        bitmap = BitmapFactory.decodeStream(isBm,null, newOpts);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }*/


}
