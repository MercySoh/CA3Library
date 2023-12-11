package controller;

import java.io.*;

import daos.UsersDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

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
        String dest = "register.jsp";

        if(action != null){
            switch (action){
                case "test":
                    dest = "test.jsp";
                    break;
                case "register":
                    dest=RegisterCommand(request,response);
                    break;

                case "registering":
                    dest = "register.jsp";
                    break;

            }
        }
        else {
            dest = "register.jsp";
            String error = "No action supplied. Please try again.";
            session.setAttribute("errorMessage", error);
        }
        response.sendRedirect(dest);
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
                destination="index.jsp";
                String msg = "registration was not successful!";
                session.setAttribute("msg", msg);
            }
        }
      return destination;
    }
}