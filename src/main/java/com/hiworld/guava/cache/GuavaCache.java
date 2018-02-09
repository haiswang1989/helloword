package com.hiworld.guava.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * guava cache
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月21日 上午10:23:29
 */
public class GuavaCache {

    public static void main(String[] args) {
        
        //CacheLoader
        //定义的是所有key获取value的方式
        //guava的没有使用额外的线程做定时清理和加载的功能,而是依赖于查询请求,在查询的时候去判定是否需要进行加载或者刷新
            
        LoadingCache<Integer, String> loadingCache = 
                CacheBuilder.newBuilder()
                //当缓存项在指定时间段内没有被"读或者写"就会被回收 
                //.expireAfterAccess(10, TimeUnit.SECONDS)
                //当缓存项在指定时间段内没有被"写"就会被回收
                //guava严格控制每次只有一次加载操作,这样每个请求轮流获取锁信息加载,严重影响性能
                //.expireAfterWrite(10, TimeUnit.SECONDS) 
                //当缓存项上一次更新操作后的多久被回收
                //严格控制只有"一个"重新加载的操作,其他的先使用"旧值",这样性能相对来说比较好,但是到达时间不能严格保证获取到最新的值
                //如果吞吐量很低的时候,可能会拿到很旧的值(获取值的时候又么有拿到重新加载的操作的lock)
                //.refreshAfterWrite(10, TimeUnit.SECONDS) 
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
                        //***这边控制load函数实现但线程汇源DB,防止DB雪崩
                        return "null";
                    } 
                });
        
        LoadingCache<Integer, String> goodLoadingCache = 
                CacheBuilder.newBuilder()
                //控制缓存每1s进行refresh，如果超过2s没有访问，那么则让缓存失效，下次访问时不会得到旧值
                .expireAfterWrite(2, TimeUnit.SECONDS) //2S没有被写入,直接失效
                .refreshAfterWrite(1, TimeUnit.SECONDS) //1S刷新一次
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
                        return "null";
                    } 
                });
        
        loadingCache.put(1, "wanghaisheng");
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(loadingCache.getUnchecked(1));
        loadingCache.put(1, "wanghaiyun");
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(loadingCache.getUnchecked(1));
        
        
        
//        //移除单个key
//        loadingCache.invalidate(1);
//        //移除所有的key
//        loadingCache.invalidateAll();
//        List<Integer> removeKeys = new LinkedList<>();
//        //移除多个key
//        loadingCache.invalidateAll(removeKeys);
//        System.out.println(loadingCache.getUnchecked(1));
        
        //Callable
        //比较灵活,允许在get的时候指定key不存在的时候的获取方式
//        Cache<Integer, String> callableCache = CacheBuilder.newBuilder().maximumSize(100).build();
//        final Integer key = 1;
//        try {
//            callableCache.get(key, new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    System.out.println("create key ["+key+"]...");
//                    return createKey(key);
//                }
//            });
//            
//            callableCache.get(key, new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    System.out.println("create key ["+key+"]...");
//                    return createKey(key);
//                }
//            });
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
    
    private static String createKey(Integer key) {
        String value = null;
        switch (key) {
        case 1:
            value = "wanghaiyun";
            break;
        case 2:
            value = "wanghaida";
            break;
        case 3:
            value = "wanghaifei";
            break;
        case 4:
            value = "wanghaiming";
            break;
        case 5:
            value = "wanghaisheng";
            break;
        }
        
        return value;
    }
}
