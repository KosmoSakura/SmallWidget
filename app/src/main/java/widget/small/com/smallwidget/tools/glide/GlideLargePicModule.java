package widget.small.com.smallwidget.tools.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

/**
 * Description:自定义Glide的配置,这些配置将会在第一个Glide请求发起的时候被调用
 * <p>
 * Author: yi.zhang
 * Time: 2017/3/28 0028
 * E-mail: yi.zhang@rato360.com
 */
public class GlideLargePicModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        Log.v("Glide","------GlideLargePicModule---applyOptions---");

//        //自定义缓存 路径 和 缓存大小
//        String diskCachePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/glideCache" ;
//        //定义缓存大小为100M
//        int  diskCacheSize =  100 * 1024 * 1024;

        //设置格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //缓存到data目录下最大50M
        //缓存目录为程序内部缓存目录/data/data/your_package_name/image_manager_disk_cache/(不能被其它应用访问)且缓存最大为250MB
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR,DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));
        //缓存到外部磁盘SD卡上,字节
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));
        //自定义磁盘缓存：这种缓存存在SD卡上，所有的应用都可以访问到
        //builder.setDiskCache(new DiskLruCacheFactory(diskCachePath , diskCacheSize));
        //设置内存缓存大小
        //MemoryCache和BitmapPool的默认大小由MemorySizeCalculator类决定，MemorySizeCalculator会根据给定屏幕大小可用内存算出合适的缓存大小，这也是推荐的缓存大小
        // 目前根据这个推荐大小做出调整，推荐大小乘以1.2倍
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        Log.v("Glide","------GlideLargePicModule---registerComponents---");
        glide.register(GlideUrl.class,InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }
}
