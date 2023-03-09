package by.bratchykau.receipt.DAO;


import by.bratchykau.receipt.utils.Cached;
import by.bratchykau.receipt.model.User;

import java.util.List;

public interface UserDao {
    @Cached
    User getById(Long id);
    @Cached
    void save(User user);
    @Cached
    void update(long id, User user);
    @Cached
    void deleteById(Long id);
}
