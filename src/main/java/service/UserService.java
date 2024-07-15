package service;

import domain.models.User;
import infra.IPersistencia;
import infra.dataBase.MySQLRepository;

import java.util.ArrayList;

public class UserService implements IPersistencia {

    private IPersistencia persistencia = new MySQLRepository();

    @Override
    public void saveUser(User user) {
        persistencia.saveUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return persistencia.findByUsername(username);
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return persistencia.getAllUsers();
    }

    @Override
    public void deleteUser(Integer id) {
        persistencia.deleteUser(id);
    }
}
