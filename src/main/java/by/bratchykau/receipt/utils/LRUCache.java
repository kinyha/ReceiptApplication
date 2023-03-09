package by.bratchykau.receipt.utils;

public class LRUCache<K, V> extends Cache<K, V> {
    public LRUCache(int maxSize) {
        super(maxSize, EvictionPolicy.LRU);
    }
}
