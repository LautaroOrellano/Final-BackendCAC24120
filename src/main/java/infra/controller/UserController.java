package infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/users")
public class UserController extends HttpServlet {

    private ObjectMapper mapper;
    private UserService service;

    public UserController() {
        this.mapper = new ObjectMapper();
        this.service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String gmail = req.getParameter("gmail");

        if(username != null ) {
            User user = service.findByUsername(username);
            if(user != null) {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(mapper.writeValueAsString(user));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Usuario no encontrado");
            }
        } else {
            ArrayList<User> users = service.getAllUsers();
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(mapper.writeValueAsString(users));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = mapper.readValue(req.getInputStream(), User.class);
        service.saveUser(user);
        System.out.println("se guardo la clase" + user.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idString = req.getParameter("id");

        if(idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            service.deleteUser(id);
            resp.setStatus(200);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("No se pudo eliminar el usuario");
        }
    }
}
