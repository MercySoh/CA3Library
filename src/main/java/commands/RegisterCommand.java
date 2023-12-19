package commands;

import daos.UsersDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegisterCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RegisterCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
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
                destination="register.jsp";
                String msg = "registration was not successful!";
                session.setAttribute("msg", msg);
            }
        }
        return destination;
    }
}
