package widget.small.com.smallwidget.tools.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

import framework.constants.Config;

/**
 * Description:自定义Glide的配置,这些配置将会在第一个Glide请求发起的时候被调用
 * <p>
 * Author: yi.zhang
 * Time: 2017/3/28 0028
 * E-mail: yi.zhang@rato360.com
 */
public class GlidePicModule implements GlideModule {
    //设置缓存的大小为100M
    int cacheSize = 100*1000*1000;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //自定义磁盘缓存：这种缓存存在SD卡上，所有的应用都可以访问到
        builder.setDiskCache(new DiskLruCacheFactory(Config.GLIDE_CACHE, cacheSize));
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
        glide.register(GlideUrl.class,InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }
}
