package by.bratchykau.receipt.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAspect {
    private CacheFactory cacheFactory;

    public CacheAspect(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    @Around("@annotation(by.bratchykau.receipt.utils.Cached)")
    public Object cacheMethodResult(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long key = (Long) args[0];
        Cache cache = cacheFactory.getCache();
        Object result = cache.get(key);
        if (result != null) {
            return result;
        }

        result = joinPoint.proceed(args);
        cache.put(key, result);
        return result;
    }
}
