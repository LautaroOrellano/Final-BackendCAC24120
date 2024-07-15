package infra;

import domain.models.User;

import java.util.ArrayList;

public interface IPersistencia {

    void saveUser(User usuario);
    User findByUsername(String username);
    ArrayList<User> getAllUsers();
    void deleteUser(Integer id);
}
