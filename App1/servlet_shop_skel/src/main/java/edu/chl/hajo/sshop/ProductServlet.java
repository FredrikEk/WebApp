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
import javax.servlet.http.HttpSession;

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
        boolean redirect = false;
        String title = null;
        String content = null;
            
        // This is navigation only
        if (view != null) {
            long modId;
			Product mod_product;
            switch(view){
                case "add":
                    title = "add";
                    content = "products/addProduct";
                    break;
                case "delete":
                    title = "Products";
                    content = "products/delProduct";
                    modId = Integer.parseInt(request.getParameter("prodId"));     
					mod_product = shop.getProductCatalogue().find(modId);
					request.setAttribute("mod_product", mod_product);
                    break;
                case "edit":
                    title = "Products";
                    content = "products/editProduct";
					modId = Integer.parseInt(request.getParameter("prodId"));     
					mod_product = shop.getProductCatalogue().find(modId);
					request.setAttribute("mod_product", mod_product);
                    break;
                default:;            
            }
            
        }
        
        // State change! Then Navigation
        if (action != null) {
			long mod_id;
            switch(action) {
                case "delete":
                    mod_id = Integer.parseInt(request.getParameter("mod_id"));
					shop.getProductCatalogue().delete(mod_id);
					redirect = true;
                    break;
                case "edit":
					mod_id = Integer.parseInt(request.getParameter("mod_id"));
					shop.getProductCatalogue().delete(mod_id);
					String name = request.getParameter("name");
					double price = Double.parseDouble(request.getParameter("price"));
					Product new_product = new Product(name, price);
					shop.getProductCatalogue().create(new_product);
					redirect = true;
                    break;
				case "add":
					double price1 =Double.parseDouble(request.getParameter("price"));
					String name1 = request.getParameter("name");
					Product new_product1 = new Product(name1, price1);
					shop.getProductCatalogue().create(new_product1);
					break;
                default:;            
            }
        }
        if(content == null) {
            title = "Products";
            content = "products/products";
            
        }
        if(title == null) {
            title = "Products";
        }
		String page = request.getParameter("page");
		int currPage = 0;
		if (page != null) {
			currPage = Integer.valueOf(page);
		}
		HttpSession session = request.getSession();
		request.setAttribute(Keys.CURRENT_PAGE.toString(), currPage);
		int pageSize = (int) session.getAttribute(Keys.PAGE_SIZE.toString());
		List<Product> ps = shop.getProductCatalogue().findRange(currPage * pageSize, pageSize);
		request.setAttribute(Keys.PRODUCT_LIST.toString(), ps);
		request.setAttribute(Keys.COUNT.toString(), shop.getProductCatalogue().count()/pageSize);
		request.setAttribute("title", title);
        request.setAttribute("content", content);
		if(!redirect) {
			request.getRequestDispatcher("WEB-INF/jsp/template.jspx").forward(request, response); 
		} else {
			response.sendRedirect("shop?view=products");					
		}
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


    public void mainProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
    }
}
