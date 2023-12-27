package commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public LogoutCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    /**
     * logout
     * @return back to index, which will be redirected to the dashboard
     */
    @Override
    public String execute() {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "index.jsp";
    }
}
