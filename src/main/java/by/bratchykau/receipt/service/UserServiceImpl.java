package by.bratchykau.receipt.service;

import by.bratchykau.receipt.DAO.UserDao;
import by.bratchykau.receipt.model.User;
import by.bratchykau.receipt.utils.Cache;
import by.bratchykau.receipt.utils.CacheFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Cache<String, Object> cache;

    public UserServiceImpl(UserDao userDao, CacheFactory cacheFactory) {
        this.userDao = userDao;
        this.cache = cacheFactory.getCache();
    }

    public User getById(Long id) {
        String key = "user:" + id;
        User user = (User) cache.get(key);
        if (user == null) {
            user = userDao.getById(id);
            if (user != null) {
                cache.put(key, user);
            }
        }
        return user;
    }


    @Override
    public User save(User user) {
        userDao.save(user);
        cache.put("user:" + user.getId(), user);
        cache.remove("users");
        return user;
    }


    @Override
    public User update(long id,User user) {
        userDao.update(id,user);
        cache.put("user:" + id, user);
        cache.remove("users");
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
        cache.remove("user:" + id);
        cache.remove("users");
    }
}
