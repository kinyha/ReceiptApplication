package by.bratchykau.receipt.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Cache<K, V> {
    private final int maxSize;
    private final EvictionPolicy evictionPolicy;
    private final Map<K, V> cache;
    private final Map<K, CacheEntry<K>> entryMap;
    private final PriorityQueue<CacheEntry<K>> evictionQueue;

    public Cache(int maxSize, EvictionPolicy evictionPolicy) {
        this.maxSize = maxSize;
        this.evictionPolicy = evictionPolicy;
        if (evictionPolicy == EvictionPolicy.LRU) {
            this.cache = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                    return size() > maxSize;
                }
            };
        } else {
            this.cache = new HashMap<>();
        }
        this.entryMap = new HashMap<>();
        this.evictionQueue = new PriorityQueue<>();
    }

    public V get(K key) {
        V value = cache.get(key);
        if (value != null && evictionPolicy == EvictionPolicy.LFU) {
            updatePriorityQueue(key);
        }
        return value;
    }

    public void put(K key, V value) {
        if (cache.size() >= maxSize) {
            evict();
        }
        cache.put(key, value);
        if (evictionPolicy == EvictionPolicy.LFU) {
            updatePriorityQueue(key);
        }
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    private void evict() {
        if (evictionPolicy == EvictionPolicy.LRU) {
            K keyToRemove = cache.keySet().iterator().next();
            cache.remove(keyToRemove);
        } else {
            CacheEntry<K> entryToRemove = evictionQueue.poll();
            if (entryToRemove != null) {
                cache.remove(entryToRemove.getKey());
                entryMap.remove(entryToRemove.getKey());
            }
        }
    }

    private void updatePriorityQueue(K key) {
        CacheEntry<K> entry = entryMap.get(key);
        if (entry != null) {
            entry.incrementCount();
            evictionQueue.remove(entry);
            evictionQueue.offer(entry);
        } else {
            entry = new CacheEntry<>(key);
            entryMap.put(key, entry);
            evictionQueue.offer(entry);
        }
    }

    private static class CacheEntry<K> implements Comparable<CacheEntry<K>> {
        private final K key;
        private int count;

        public CacheEntry(K key) {
            this.key = key;
            this.count = 1;
        }

        public void incrementCount() {
            count++;
        }

        public K getKey() {
            return key;
        }

        @Override
        public int compareTo(CacheEntry<K> other) {
            return Integer.compare(count, other.count);
        }
    }

    public enum EvictionPolicy {
        LRU, LFU
    }
}



