package yomo.study.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Title:LRUCacheMap
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/8/6 10:03
 */
public class LRUCacheMap<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    /**
     * 缓存的LRU数量
     * @param cache_size
     */
    public LRUCacheMap(int cache_size) {
        //按方粉循序来进行排序 最近访问的房头部，最老的放尾部
        super((int) Math.ceil(cache_size / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //map中数据大于指定缓存个数的时候，自动删除最老的数据
        return CACHE_SIZE < size();
    }
}