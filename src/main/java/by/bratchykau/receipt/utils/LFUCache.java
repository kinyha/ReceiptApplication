package by.bratchykau.receipt.utils;

public class LFUCache<K, V> extends Cache<K, V> {
    public LFUCache(int maxSize) {
        super(maxSize, EvictionPolicy.LFU);
    }
}

