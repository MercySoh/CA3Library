package controller;

import java.io.*;

import business.Users;
import daos.UsersDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        String dest = "index.jsp";

        if(action != null){
            switch (action){
                case "test":
                    dest = "test.jsp";
                    break;

                case "logout":
                    session.invalidate();
                    dest = "index.jsp";
                    break;
            }
        }
        response.sendRedirect(dest);
    }

    private String loginCommand(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsersDao userDao = new UsersDao("ca3library");
        Users user = userDao.findUserByUsernamePassword(username, password);

        if(user != null){
            session.setAttribute("user", user);
            return "dashboard.jsp";
        }
        else{
            return "login.jsp";
        }
    }

    public void destroy() {
    }
}