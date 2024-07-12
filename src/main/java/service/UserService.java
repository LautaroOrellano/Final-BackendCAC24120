package service;

import domain.models.User;
import infra.IPersistencia;
import infra.dataBase.MySQLRepository;

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
}
