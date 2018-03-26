package ru.profi.test.myapplication.model.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

public class CacheUtil {

    private static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private static final Object mDiskCacheLock = new Object();
    private static final int cacheSize = maxMemory / 8;
    private static LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // The cache size will be measured in kilobytes rather than
            // number of items.
            return bitmap.getByteCount() / 1024;
        }
    };



    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//        synchronized (mDiskCacheLock) {
            if (getBitmapFromMemCache(key) == null) {
                mMemoryCache.put(key, bitmap);
            }
//        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
//        synchronized (mDiskCacheLock) {
            return mMemoryCache.get(key);
//        }
    }


}
