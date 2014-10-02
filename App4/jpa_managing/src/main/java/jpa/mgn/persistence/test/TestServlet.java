package jpa.mgn.persistence.test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import jpa.mgn.core.Author;
import jpa.mgn.core.AuthorRegistry;
import jpa.mgn.core.Book;
import jpa.mgn.core.Library;
import jpa.mgn.core.Publication;
import jpa.mgn.core.PublicationsList;

/**
 * If we would like to do ocular inspection of tables use this (if using
 * Arquillian we can't)
 *
 * @author hajo
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(TestServlet.class.getName());

    @Inject
    Library library;
    @Inject
    PublicationsList pubList;
    @Inject
    AuthorRegistry authReg;
    @Inject
    UserTransaction utx;  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //clearAll();
            //testPersistAndRemovePublication();
            testRemoveAuthor();
            // More teste here
        } catch (Exception ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testPersistAndRemovePublication() throws Exception {
        Book book = new Book("book", 111F, "aaaa");
        Author author = new Author("author");
        Publication pub = new Publication(book, author);

        // This should cascade Book and Author
        pubList.create(pub);
        // Book and Author should still be there
        LOG.log(Level.INFO, "**** Publication id {0}", pub.getId());
        List<Publication> publications = pubList.findAll();
        pubList.delete(publications.get(0).getId());
    }

    public void testRemoveAuthor() throws Exception {
        String[] titles = "aaa, bbb, ccc, ddd, eee".split(",");
        // The below must run in single transaction
        utx.begin();
        Author a1 = new Author("xxx");
        Author a2 = new Author("yyy");
        for (int i = 0; i < titles.length; i++) {
            Book b = new Book(titles[i], 111F, "blabla");
            if (i % 2 == 0) {  //Even
                // Will cascade book, author
                pubList.create(new Publication(b, a1));
            } else {
                pubList.create(new Publication(b, a2));
            }
        }
        utx.commit();
        List<Author> authors = authReg.findByName("xxx");
        authReg.delete(authors.get(0).getId());
    }

    private void clearAll() throws Exception {
        utx.begin();
        EntityManager p = pubList.getEntityManager();
        p.joinTransaction();
        p.createQuery("delete from Publication").executeUpdate();
        EntityManager a = authReg.getEntityManager();
        a.joinTransaction();
        a.createQuery("delete from Author").executeUpdate();
        EntityManager l = library.getEntityManager();
        l.joinTransaction();
        l.createQuery("delete from Book").executeUpdate();
        utx.commit();
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
