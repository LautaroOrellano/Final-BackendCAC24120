package infra.dataBase;

import domain.models.User;
import infra.IPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLRepository implements IPersistencia {

    private Connection connection;

    public MySQLRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    @Override
    public void saveUser(User usuario) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                User user = new User();
                user.setId(result.getString("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
