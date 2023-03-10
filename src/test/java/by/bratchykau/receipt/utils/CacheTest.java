package by.bratchykau.receipt.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CacheTest {

    private Cache<String, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new Cache<>(2, Cache.EvictionPolicy.LRU);
    }

    @Test
    void checkCachePutAndGet() {
        cache.put("a", 1);
        cache.put("b", 2);
        assertEquals(1, cache.get("a"));
        assertEquals(2, cache.get("b"));
    }

    @Test
    void checkCacheEviction() {
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        assertNull(cache.get("a"));
        assertEquals(2, cache.get("b"));
        assertEquals(3, cache.get("c"));
    }

    @Test
    void checkCacheRemove() {
        cache.put("a", 1);
        cache.put("b", 2);
        cache.remove("a");
        assertNull(cache.get("a"));
        assertEquals(2, cache.get("b"));
    }

    @Test
    void checkCacheClear() {
        cache.put("a", 1);
        cache.put("b", 2);
        cache.clear();
        assertNull(cache.get("a"));
        assertNull(cache.get("b"));
    }

    @Test
    void checkCacheLFU() {
        cache = new Cache<>(2, Cache.EvictionPolicy.LFU);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.get("a");
        cache.get("a");
        cache.put("c", 3);
        assertNull(cache.get("b"));
        assertEquals(1, cache.get("a"));
        assertEquals(3, cache.get("c"));
    }
}

