package commands;

import business.Users;
import daos.UsersDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateProfileCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public UpdateProfileCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    @Override
    public String execute() {
        String destination = "updateProfile.jsp";
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        if (username != null && password != null && email != null && address != null && phone != null && !username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
            UsersDao userDao = new UsersDao("ca3library");
            Users user = new Users(username, password, email, address, phone, 0);
            int id = userDao.amendUser(user);
            if(id != -1){
                destination="index.jsp";
                String msg = "You have been update successfully!";
                Users updateuser = new Users(username, password, email, address, phone, 0);
                session.setAttribute("user", updateuser);
                session.setAttribute("msg", msg);
            }
            else{
                destination="updateProfile.jsp";
                String msg = "update was not successful!";
                session.setAttribute("msg", msg);
            }
        }
        return destination;
    }
}
