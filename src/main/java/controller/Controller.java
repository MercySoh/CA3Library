package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import business.Book;
import business.Genre;
import business.Loan;
import business.Users;
import commands.*;
import daos.UsersDao;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    public static String pageTitle;
    public static List<Book> books;
    public static List<Loan> loans;

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
        Command c = null;
        String action = request.getParameter("action");
        String dest = "index.jsp";

        if(action == null){
            action = "dashboard";
        }

        switch (action){
            case "dashboard":
                pageTitle = "dashboard";
                int genreID;
                String title;
                c = new DisplayBookCommand(request, response);

                if(request.getParameter("genreID") != null){
                    genreID = Integer.parseInt(request.getParameter("genreID"));
                    dest = ((DisplayBookCommand)c).execute(genreID);
                }
                else if(request.getParameter("searchTitle") != null){
                    title = request.getParameter("searchTitle");
                    dest = ((DisplayBookCommand)c).execute(title);
                }
                else{
                    dest = c.execute();
                }
                break;
            case "register":
                c = new RegisterCommand(request, response);
                dest = c.execute();
                break;

            case "show_register":
                pageTitle = "register";
                dest = "register.jsp";
                break;

            case "show_login":
                pageTitle = "login";
                dest = "login.jsp";
                break;

            case "logout":
                session.invalidate();
                dest = "index.jsp";
                break;

            case "borrow":
                c=new BorrowBookCommand(request,response);
                dest= c.execute();
                break;

            case "show_profile":
                pageTitle = "profile";
                dest = "profile.jsp";
                break;

            case "login":
                c = new LoginCommand(request, response);
                dest = c.execute();
                break;

            case "currentLoans":
                c = new CurrentLoansCommand(request,response);
                dest= c.execute();
                break;
        }

        response.sendRedirect(dest);
    }

    public void destroy() {
    }
}