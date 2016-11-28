package common.Cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by EDE67167 on 2016-01-21.
 */
public class MemCache {
    static private MemCache mInstance=null;
    private LruCache<String, Bitmap> mMemoryCache;
    private MemCache()
    {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory /8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }
    static public  MemCache GetInstance()
    {
        if(mInstance==null)
        {
            mInstance=new MemCache();
        }
        return mInstance;
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(mInstance!=null) {
            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);
            }
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        if(mInstance==null)
        {
            return null;
        }
        else
        {
            return mMemoryCache.get(key);
        }
    }
}
