package by.bratchykau.receipt.service;

import by.bratchykau.receipt.model.User;

import java.util.List;

/**

 This interface defines the methods for interacting with a user service.
 */
public interface UserService {

    /**

     Retrieves a user by their ID.
     @param id the ID of the user to retrieve
     @return the user with the specified ID, or null if no such user exists
     */
    User getById(Long id);
    /**

     Saves a user.
     @param user the user to save
     @return the saved user
     */
    User save(User user);
    /**

     Updates a user by their ID.
     @param id the ID of the user to update
     @param user the new user data
     @return the updated user, or null if no such user exists
     */
    User update(long id, User user);
    /**

     Deletes a user by their ID.
     @param id the ID of the user to delete
     */
    void deleteById(Long id);
}
