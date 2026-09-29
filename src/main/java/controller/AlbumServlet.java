/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AlbumDAO;
import dao.ArtistDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Album;
import model.Artist;

/**
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name="AlbumServlet", urlPatterns={"/album"})
public class AlbumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String view = request.getParameter("view");
        if (view == null || view.isEmpty()) {
            view = "list";
        }

        if ("create".equals(view)) {
            ArtistDAO artistDAO = new ArtistDAO();
            List<Artist> list = artistDAO.getList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/album/create.jsp").forward(request, response);
        } else if ("edit".equals(view)) {
            request.getRequestDispatcher("/WEB-INF/album/edit.jsp").forward(request, response);
        } else if ("delete".equals(view)) {
            request.getRequestDispatcher("/WEB-INF/album/delete.jsp").forward(request, response);
        } else {
            AlbumDAO albumDAO = new AlbumDAO();
            List<Album> list = albumDAO.getList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/album/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                //
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            case "edit":
                //
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            case "delete":
                //
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;
        }
    }

}
