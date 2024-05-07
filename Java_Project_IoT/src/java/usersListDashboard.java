/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.List;

/**
 *
 * @author dalyk
 */
@WebServlet(name = "usersListDashboard", urlPatterns = {"/usersListDashboard"}) 
public class usersListDashboard extends HttpServlet {
    
    private userDAO userDAO;
    
    public usersListDashboard(){
        this.userDAO = new userDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Servlet invoked !!");
        String action = request.getServletPath();
        if (action != null)
        {
            action = action.substring("/usersListDashboard".length());
            System.out.println("The Now Action:"+action);
            switch (action) {
                case "/new": 
                    addNewUser(request, response);
                    break;
                case "/insert":  
                    insertUser(request, response);
                    break;
                case "/delete":  
                    deleteUser(request, response);
                    break;
                case "/edit":
                    editUser(request, response);
                    break;
                case "/update":  
                    updateUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        }else
        {
            listUsers(request, response);
        }  
    }
    protected void addNewUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = null;
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
    }
    protected void insertUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Nom = request.getParameter("Nom");
        String Prenom = request.getParameter("Prenom");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String Confirm_Password = request.getParameter("Confirm_Password");
        if (Password.equals(Confirm_Password)) {
            User newUser = new User(Nom, Prenom, Email, Password);
            userDAO.insertUser(newUser);
            response.sendRedirect(request.getContextPath() + "/usersListDashboard");
        }else
        {
            String message = "Password doesn't match!";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signup.jsp");
            dispatcher.forward(request, response);
            
        }

    }
    protected void deleteUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int Id =Integer.parseInt(request.getParameter("Id"));
            userDAO.deleteUser(Id);
            response.sendRedirect(request.getContextPath() + "/usersListDashboard");
    }
    protected void editUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String message = "hjgf";
            int Id =Integer.parseInt(request.getParameter("Id"));
            User existingUser = userDAO.selectUser(Id);
            request.setAttribute("Id", Id);
            request.setAttribute("existingUser", existingUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyUserDashboard.jsp");
            dispatcher.forward(request, response);
    }
    protected void updateUser (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id =Integer.parseInt(request.getParameter("Id"));
        String Nom = request.getParameter("Nom");
        String Prenom = request.getParameter("Prenom");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        //String Confirm_Password = request.getParameter("Confirm_Password");
    
        User user = new User(Id, Nom, Prenom, Email, Password);
        System.out.print(userDAO.updateUser(user));
        response.sendRedirect(request.getContextPath() + "/usersListDashboard");
        
            //String message = "Password doesn't match!";
            //request.setAttribute("message", message);
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyUserDashboard.jsp");
            //dispatcher.forward(request, response);
            
        
    }
    protected void listUsers (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            List<User> listUsers = userDAO.selectUsers();
            
            request.setAttribute("listUsers", listUsers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/usersListDashboard.jsp");
            dispatcher.forward(request, response);
            
    }
    
    
   

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
