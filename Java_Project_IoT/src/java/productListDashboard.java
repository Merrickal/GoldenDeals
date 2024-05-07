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
import java.util.Base64;
import java.util.List;

/**
 *
 * @author dalyk
 */
@WebServlet(name = "productListDashboard", urlPatterns = {"/productListDashboard"}) 
public class productListDashboard extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private productDAO productDAO;
    
    public productListDashboard(){
        this.productDAO = new productDAO();
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
        System.out.println("The Action:"+action);
        if (action != null)
        {
            action = action.substring("/productListDashboard".length());
            System.out.println("The Now Action:"+action);
            switch (action) {
                case "/new": 
                    addNewProduct(request, response);
                    break;
                case "/insert":  
                    insertProduct(request, response);
                    break;
                case "/delete":  
                    deleteProduct(request, response);
                    break;
                case "/edit":
                    editProduct(request, response);
                    break;
                case "/update":  
                    updateProduct(request, response);
                    break;
                default:
                    listProducts(request, response);
                    break;
            }
        }else
        {
            listProducts(request, response);
        }  
    }
    protected void addNewProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/addProductDashboard.jsp").forward(request, response);
    }
    protected void insertProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        int productQte = Integer.parseInt(request.getParameter("productQte"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String productCategorie =  request.getParameter("productCategorie");
        Product newProduct = new Product(productName, productDesc, productQte, productPrice, productCategorie);
        productDAO.insertProduct(newProduct);
        response.sendRedirect(request.getContextPath() + "/productListDashboard");

    }
    protected void deleteProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id =Integer.parseInt(request.getParameter("Id"));
        productDAO.deleteProduct(Id);
        response.sendRedirect(request.getContextPath() + "/productListDashboard");
    }
    protected void editProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id =Integer.parseInt(request.getParameter("Id"));
        Product existingProduct = productDAO.selectProduct(Id);
        request.setAttribute("Id", Id);
        request.setAttribute("existingProduct", existingProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/modifyProductDashboard.jsp");
        dispatcher.forward(request, response);
    }
    protected void updateProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id =Integer.parseInt(request.getParameter("Id"));
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        int productQte = Integer.parseInt(request.getParameter("productQte"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String productCategorie =  request.getParameter("productCategorie");
        Product newProduct = new Product(Id,productName, productDesc, productQte, productPrice, productCategorie);
        productDAO.updateProduct(newProduct);
        response.sendRedirect(request.getContextPath() + "/productListDashboard");
    }
    protected void listProducts (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProducts = productDAO.selectProducts();
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/productsListDashboard.jsp");
        dispatcher.forward(request, response);
            
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
