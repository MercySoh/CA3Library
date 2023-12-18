package controller;

import java.io.*;

import business.Users;
import daos.BookDao;
import daos.GenreDao;
import daos.UsersDao;

import daos.UsersDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    private GenreDao genreDao = new GenreDao("ca3library");
    private BookDao bookDao = new BookDao("ca3library");

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

        if(action == null){
            action = "dashboard";
        }

        switch (action){
            case "dashboard":
                session.setAttribute("pageTitle", "dashboard");
                dest = "dashboard.jsp";
                break;
            case "register":
                dest=RegisterCommand(request,response);
                break;

            case "show_register":
                session.setAttribute("pageTitle", "register");
                dest = "register.jsp";
                break;

            case "show_login":
                session.setAttribute("pageTitle", "login");
                dest = "login.jsp";
                break;

            case "logout":
                session.invalidate();
                dest = "index.jsp";
                break;
            case "login":
                dest=loginCommand(request,response);
                break;
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
            String msg = "Wrong password or UserName";
            session.setAttribute("errorMessage", msg);
            return "login.jsp";
        }
    }

    public void destroy() {
    }

    private String RegisterCommand(HttpServletRequest request, HttpServletResponse response) {
        String destination = "register.jsp";
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        if (username != null && password != null && email != null && address != null && phone != null && !username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
            UsersDao userDao = new UsersDao("ca3library");
          int state=  userDao.addUser(username,email,password,address,phone,0);
            if(state!=-1){
                destination="index.jsp";
                String msg = "You have been registered successfully!";
                session.setAttribute("msg", msg);
            }
            else{
                destination="error.jsp";
                String msg = "registration was not successful!";
                session.setAttribute("errorMessage", msg);
            }
        }
      return destination;
    }
}