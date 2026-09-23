package controller;

import dao.ArtistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Artist;

/**
 * Main servlet for the web
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name = "ArtistServlet", urlPatterns = {"/artist"})
public class ArtistServlet extends HttpServlet {

    // CRUD
    // R (List): https://localhost/list-artist/artist
    // C (Create): https://localhost/list-artist/artist?view=create
    // U (Update): https://localhost/list-artist/artist?view=edit&id=X
    // D (Delete): https://localhost/list-artist/artist?view=delete&id=X
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String view = request.getParameter("view");

        if (view == null || view.isEmpty()) {
            view = "list";
        }

        if ("create".equals(view)) {
            request.getRequestDispatcher("/WEB-INF/artist/create.jsp").forward(request, response);
        } else if ("edit".equals(view)) {
            request.getRequestDispatcher("/WEB-INF/artist/edit.jsp").forward(request, response);
        } else if ("delete".equals(view)) {
            request.getRequestDispatcher("/WEB-INF/artist/delete.jsp").forward(request, response);
        } else {

            // Goi DAO
            ArtistDAO artistDAO = new ArtistDAO();

            // Lay duoc danh sach Artists trong bang Artist
            List<Artist> list = artistDAO.getList();

            // Truyen du lieu de hien thi
            request.setAttribute("list", list);

            request.getRequestDispatcher("/WEB-INF/artist/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
