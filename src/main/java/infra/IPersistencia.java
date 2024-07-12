package infra;

import domain.models.User;

public interface IPersistencia {

    void saveUser(User usuario);
    User findByUsername(String username);
}
