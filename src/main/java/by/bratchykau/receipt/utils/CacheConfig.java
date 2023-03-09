package by.bratchykau.receipt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Component
@ConfigurationProperties("cache")
public class CacheConfig {
    @Value("${cache.implementation}")
    private String implementation;

    @Value("${cache.max-size}")
    private int maxSize;

    public String getImplementation() {
        return implementation;
    }

    public int getMaxSize() {
        return maxSize;
    }
}

