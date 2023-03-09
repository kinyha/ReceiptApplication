package by.bratchykau.receipt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheFactory {
    private final Cache<String, Object> cache;

    public CacheFactory(@Value("${cache.implementation}") String implementation,
                        @Value("${cache.max-size}") int maxSize) {
        switch (implementation.toLowerCase()) {
            case "lru":
                cache = new LRUCache<>(maxSize);
                break;
            case "lfu":
                cache = new LFUCache<>(maxSize);
                break;
            default:
                throw new IllegalArgumentException("Invalid cache implementation: " + implementation);
        }
    }

    public Cache<String, Object> getCache() {
        return cache;
    }
}
