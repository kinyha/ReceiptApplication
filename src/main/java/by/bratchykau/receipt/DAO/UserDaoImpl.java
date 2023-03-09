package by.bratchykau.receipt.DAO;

import by.bratchykau.receipt.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(long id, User user) {
        users.put(id, user);
    }


    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

}
