package com.charl.common.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 *
 * 进程内缓存,异步刷新缓存,不影响当前的old value
 *
 * 尤其适用于量级不是很大，但是频次很高，能极好的解决redis带宽瓶颈
 *
 * 同时也适用与一些静态的数据，比如数据库中，商品的品类信息，套装风格等
 *
 *
 *
 */
public class LocalCache {


    private static final Logger logger = LoggerFactory.getLogger(LocalCache.class);

    //缓存保存对象
    private LoadingCache<String,Object> localCache = null ;

    /**
     * 初始化Guava Cache以及创建刷新线程
     * @param name 缓存业务名称,主要用于区别线程名称
     * @param refreshTime 缓存刷新时间
     * @param unit  时间单位
     * @param callback  回调接口, 用于刷新时回调
     */
    public void init(String name, int refreshTime, TimeUnit unit,final LocalCacheCallback callback){

        logger.info("local cache init finish, sevice name:{}, refresh time:{}, time unit:{} " , name, refreshTime, unit);

        //加载缓存所使用的线程
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(name+"-cache-reload-pool-%d").setDaemon(true).build();
        ExecutorService parentExecutor = Executors.newSingleThreadExecutor(threadFactory);
        final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(parentExecutor);

        //定期刷新缓存,间隔为refreshTime
        localCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(refreshTime,unit)
                .build(new CacheLoader<String, Object>() {

                    /**
                     * 第一次加载
                     * @param key
                     * @return
                     */
                    @Override
                    public Object load(String key) throws Exception {
                        logger.info("init load cache, key : {} ", key);
                        try {
                            return callback.load(key, null);
                        } catch (Exception e) {
                            logger.error("exception happend when init load cache failed, key:{}, ex:{} ", key, e);
                            /**
                             * reload过程中发生异常,直接抛出
                             * 下次get key的时候,会继续触发load
                             */
                            throw e ;
                        }
                    }

                    /**
                     * 定时刷新缓存
                     * @param key
                     * @param oldvalue
                     * @return
                     */
                    @Override
                    public ListenableFuture reload(final String key,final Object oldvalue) throws Exception{
                        //此处主要使用一个守护线程固定时间去刷新缓存
                        return executorService.submit(new Callable() {
                            @Override
                            public Object call() throws Exception {
                                logger.info("reload for cache refresh, key : {} ", key);
                                try {
                                    return callback.load(key, oldvalue);
                                } catch (Exception e) {
                                    logger.error("exception happend when reload for cache refresh, key:{}, ex:{} ", key, e);

                                    /**
                                     * reload过程中发生异常,直接抛出
                                     * 下次get key的时候,仍然会继续触发reload
                                     */
                                    throw e;
                                }
                            }
                        });
                    }

                });

        logger.info("local cache init finish, sevice name:{}, refresh time:{}, time unit:{} " , name, refreshTime, unit);
    }


    /**
     * 从cache中获取值,初次会触发load
     * @param key
     * @return
     */
    public Object get(String key){

        try {
            return localCache.get(key);
        } catch (Exception e) {
            logger.error("exception happend when query local cache, key:{}, ex:{} " , key, e);
        }

        return null ;
    }

}