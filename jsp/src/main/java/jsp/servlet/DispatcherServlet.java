package jsp.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jsp.beans.DataBean;
import jsp.beans.RandomBean;
import jsp.beans.TableBean;

/**
 * A Servlet to handle incoming data and forward to JSP (possible add or modify
 * incoming data)
 *
 * @author hajo
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/dispatcher"})
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DispatcherServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String to = request.getParameter("to");
        LOG.log(Level.INFO, "Incomming parameter to = {0}", to);

        switch (to) {
            case "el":
                //The objects will be found by the EL in the JSPs 
                session.setAttribute("data", new DataBean());
                request.setAttribute("rand", new RandomBean());
                request.getRequestDispatcher("/el.jspx").forward(request, response);
                break;
            case "ass":
                session.setAttribute("data", new DataBean());
                request.getRequestDispatcher("/jstl/assignment.jspx").forward(request, response);
                break;
            case "sel":
                request.getRequestDispatcher("/jstl/selection.jspx").forward(request, response);
                break;
            case "iter":
                session.setAttribute("table", new TableBean());
                request.getRequestDispatcher("/jstl/iteration.jspx").forward(request, response);
                break;
            default: ;
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
