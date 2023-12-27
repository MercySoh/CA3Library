package commands;

import business.Users;
import daos.UsersDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LoginCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * login user
     * @return the destination link
     */
    @Override
    public String execute() {
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
            session.setAttribute("msg", msg);
            return "login.jsp";
        }
    }
}
