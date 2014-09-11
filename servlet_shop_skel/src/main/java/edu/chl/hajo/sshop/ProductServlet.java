package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to handle Product pages
 *
 * @author hajo
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {

    private static final String TEMPLATE = "WEB-INF/jsp/template.jspx";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String view = request.getParameter("view");
        IShop shop = (IShop) getServletContext().getAttribute(Keys.SHOP.toString());
        
        String title = null;
        String content = null;
            
        // This is navigation only
        if (view != null) {
            
            switch(view){
                case "add":
                    title = "add";
                    content = "products/addProduct";
                    break;
                default:;
            }
            
        }
        // State change! Then Navigation
        if (action != null) {
            long prodId = Integer.parseInt(request.getParameter("prodId"));
            switch(action) {
                case "delete":
                    title = "Products";
                    content = "products/delProduct";
                    break;
                case "edit":
                    title = "Products";
                    content = "products/editProduct";
                    break;
                default:;
            }
        }
        
        int pageSize = 4;
        int currPage = 0;
        String page = request.getParameter("page");
        if (page != null) {
            currPage = Integer.valueOf(page);
        }
        if(title == null) {
            title = "Products";
        }
        if(content == null) {
            content = "products/products";
        }
        
        List<Product> ps = shop.getProductCatalogue().findRange(currPage * pageSize, pageSize);
        request.setAttribute(Keys.PRODUCT_LIST.toString(), ps);
        request.setAttribute(Keys.COUNT.toString(), shop.getProductCatalogue().count());

        request.setAttribute(Keys.CURRENT_PAGE.toString(), currPage);
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.getRequestDispatcher("WEB-INF/jsp/template.jspx").forward(request, response);
        LOG.log(Level.INFO, "Inte här" + pageSize + " " + ps.size() + " " + currPage);
    }
    private static final Logger LOG = Logger.getLogger(ProductServlet.class.getName());
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
