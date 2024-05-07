/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dalyk
 */
public class mainPage extends HttpServlet {

    int IdUsersets;
    
    
    private final productDAO productDAO;
    private final orderDAO orderDAO;
    private final userDAO userDAO;
    private final shipOrderDAO shipOrderDAO;
    
    public mainPage(){
        this.productDAO = new productDAO();
        this.orderDAO = new orderDAO();
        this.userDAO = new userDAO();
        this.shipOrderDAO = new shipOrderDAO();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");   
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if(IdUsersets !=0)
            {
            if (action != null)
            {
                action = action.substring("/mainPage".length());
                System.out.println("The Now Action:"+action);
                switch (action) {
                    case "/viewProfile": 
                        viewProfile(request, response);
                        break;
                    case "/viewProduct": 
                        viewProduct(request, response);
                        break;
                    case "/addToCart":  
                        addToCart(request, response);
                        break;
                    case "/viewCart":  
                        viewCart(request, response);
                        break;
                    case "/confirmOrder":
                        confirmOrder(request, response);
                        break;
                    case "/deleteOrder":  
                        deleteOrder(request, response);
                        break;
                    case "/filterProducts":  
                        if(request.getParameter("ProductPricecheck")!=null && request.getParameter("ProductCategoriecheck")!=null)
                        {
                            filterProductsByAll(request, response);
                        }
                        else if(request.getParameter("ProductPricecheck")!=null)
                        {
                            filterProductsPrice(request, response);
                        }
                        else if(request.getParameter("ProductCategoriecheck")!=null)
                        {
                            filterProductsCategorie(request, response);
                        }
                        else
                        {
                            listProducts(request, response);
                        }
                        break;
                    case "/searchProducts":  
                        searchProducts(request, response);
                        break;
                    case "/shippingOrder":  
                        shippingOrder(request, response);
                        break;
                    case "/history":  
                        history(request, response);
                        break;
                    case "/signup":  
                        signup(request, response);
                        break;
                    case "/signupWhenEmpty":  
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signupMainPage.jsp");
                            dispatcher.forward(request, response);
                        break;
                    case "/loginWhenEmpty":  
                            dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                            dispatcher.forward(request, response);
                        break;
                    case "/login":  
                        login(request, response);
                        break;
                    case "/logout":  
                        IdUsersets = 0;
                        dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                        dispatcher.forward(request, response);
                        break;
                    default:
                        listProducts(request, response);
                        break;
                } 
            }
            else
            {
                listProducts(request, response);
            }
        }
        else
        {
            action = action.substring("/mainPage".length());
            System.out.println("The Now Action:"+action);
            switch (action) {
                case "/signup":  
                    signup(request, response);
                    break;
                case "/signupWhenEmpty":  
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signupMainPage.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "/login":  
                    login(request, response);
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }  
    }
    protected void viewProfile (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdUser =Integer.parseInt(request.getParameter("IdUser"));
        User userProfile = userDAO.selectUser(IdUser);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("Nom", userProfile.getNom());
        request.setAttribute("Prenom", userProfile.getPrenom());
        request.setAttribute("Email", userProfile.getEmail());
        request.setAttribute("Password", userProfile.getPassword());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userProfile.jsp");
        dispatcher.forward(request, response);
        
        
    }
    protected void listProducts (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProducts = productDAO.selectProducts();
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mainPage.jsp");
        dispatcher.forward(request, response);
            
    }
    protected void viewProduct (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdProduct =Integer.parseInt(request.getParameter("IdProduct"));
        Product product = productDAO.selectProduct(IdProduct);
        request.setAttribute("IdProduct", product.getId());
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("productName", product.getName());
        request.setAttribute("productDesc", product.getDescription());
        request.setAttribute("productQte", product.getQte());
        request.setAttribute("productPrice", product.getPrice());
        //byte[] productImage =  request.getParameter("productImage").getBytes();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/productInfo.jsp");
        dispatcher.forward(request, response);
    }
    protected void addToCart (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdProduct = Integer.parseInt(request.getParameter("IdProduct"));
        int IdUser = Integer.parseInt(request.getParameter("IdUser"));
        int QteOrder = Integer.parseInt(request.getParameter("QteOrder"));
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        Order order = new Order(IdProduct, IdUser, QteOrder, productName, productDesc, productPrice);
        orderDAO.addProductToCart(order);
        response.sendRedirect(request.getContextPath() + "/mainPage");
    }
    protected void viewCart (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orders = orderDAO.selectOrders(IdUsersets);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Cart.jsp");
        dispatcher.forward(request, response);    
    }
    protected void history (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orders = orderDAO.selectOrdersInHistory(IdUsersets);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/History.jsp");
        dispatcher.forward(request, response);    
    }
    protected void deleteOrder (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdCommande =Integer.parseInt(request.getParameter("IdCommande"));
        orderDAO.deleteProductFromCart(IdCommande);
        response.sendRedirect(request.getContextPath() + "/mainPage/viewCart");
    }
    protected void confirmOrder (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdCommande =Integer.parseInt(request.getParameter("IdCommande"));
        Order orderToBeConfirmed = orderDAO.selectOrder(IdCommande);
        request.setAttribute("IdCommande", IdCommande);
        request.setAttribute("IdProduct", orderToBeConfirmed.getId());
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("productName", orderToBeConfirmed.getName());
        request.setAttribute("productDesc", orderToBeConfirmed.getDescription());
        request.setAttribute("QteOrder", orderToBeConfirmed.getQteOrder());
        request.setAttribute("productPrice", orderToBeConfirmed.getPrice());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ConfirmPage.jsp");
        dispatcher.forward(request, response);    
    }
    protected void shippingOrder (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int IdCommande =Integer.parseInt(request.getParameter("IdCommande"));
        int IdProduct = Integer.parseInt(request.getParameter("IdProduct"));
        int IdUser = Integer.parseInt(request.getParameter("IdUser"));
        int QteOrder = Integer.parseInt(request.getParameter("QteOrder"));
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        String Location = request.getParameter("Location");
        String BankDetails = request.getParameter("Bank_Details");
        String shippingStatus = "Shipping In Progress...";
        
        shipOrder shiporder = new shipOrder(IdCommande, IdProduct, IdUser, QteOrder, productName, productDesc, productPrice, shippingStatus, Location, BankDetails);
        
        shipOrderDAO.shipProduct(shiporder);
        orderDAO.deleteProductFromCart(IdCommande);
        response.sendRedirect(request.getContextPath() + "/mainPage/viewCart");
    }
    protected void filterProductsPrice (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productPriceRange =Integer.parseInt(request.getParameter("productPriceRange"));

        List<Product> listProducts = productDAO.queryProductsByPrice(productPriceRange);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("productPriceRange", productPriceRange);
        request.setAttribute("ProductCategoriecheck", request.getParameter("ProductCategoriecheck"));
        request.setAttribute("ProductPricecheck", request.getParameter("ProductPricecheck"));
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mainPage.jsp");
        dispatcher.forward(request, response);
    }
    protected void filterProductsCategorie (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCategorie =request.getParameter("productCategorie");

        List<Product> listProducts = productDAO.queryProductsByCategorie(productCategorie);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("productCategorie", productCategorie);
        request.setAttribute("ProductCategoriecheck", request.getParameter("ProductCategoriecheck"));
        request.setAttribute("ProductPricecheck", request.getParameter("ProductPricecheck"));
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mainPage.jsp");
        dispatcher.forward(request, response);
    }
    protected void filterProductsByAll (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productPriceRange =Integer.parseInt(request.getParameter("productPriceRange"));
        String productCategorie =request.getParameter("productCategorie");

        List<Product> listProducts = productDAO.queryProductsByAll(productPriceRange, productCategorie);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("productPriceRange", productPriceRange);
        request.setAttribute("productCategorie", productCategorie);
        request.setAttribute("ProductCategoriecheck", request.getParameter("ProductCategoriecheck"));
        request.setAttribute("ProductPricecheck", request.getParameter("ProductPricecheck"));
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mainPage.jsp");
        dispatcher.forward(request, response);
    }
    protected void searchProducts (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName =request.getParameter("productName");

        List<Product> listProducts = productDAO.queryProductsByName(productName);
        request.setAttribute("IdUser", IdUsersets);
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mainPage.jsp");
        dispatcher.forward(request, response);
    }
    protected void login (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Email =request.getParameter("Email");
        String Password =request.getParameter("Password");

        int Id = userDAO.selectUserEmailPassword(Email, Password);
        IdUsersets = Id;
        request.setAttribute("IdUser", Id);
        if (Email.equals("admin") && Password.equals("admin"))response.sendRedirect(request.getContextPath() + "/usersListDashboard");

        else response.sendRedirect(request.getContextPath() + "/mainPage");
    }
    protected void signup (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String Nom = request.getParameter("Nom");
            String Prenom = request.getParameter("Prenom");
            String Email = request.getParameter("Email");
            String Password = request.getParameter("Password");
            String Confirm_Password = request.getParameter("Confirm_Password");
            if (Password.equals(Confirm_Password)) {
                User newUser = new User(Nom, Prenom, Email, Password);
                userDAO.insertUser(newUser);
                int Id = userDAO.selectUserEmailPassword(Email, Password);
                IdUsersets = Id;
                response.sendRedirect(request.getContextPath() + "/mainPage");
            }else
            {
                String message = "Password doesn't match!";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signupMainPage.jsp");
                dispatcher.forward(request, response);

            }
        
    }
    
    
    public static String shortenSentence(String sentence) {
    if (sentence == null || sentence.isEmpty()) {
      return "";  // Handle empty sentence case
    }

    // Split the sentence into words
    String[] words = sentence.split(" ");

    // Check if there are less than or equal to 8 words
    if (words.length <= 8) {
      return sentence;  // No need to shorten
    }

    // Create a new string with the first 8 words joined by spaces
    StringBuilder shortenedSentence = new StringBuilder(String.join(" ", Arrays.copyOfRange(words, 0, 8)));

    // Add ellipsis to indicate there's more
    shortenedSentence.append("...");

    return shortenedSentence.toString();
  }

    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
