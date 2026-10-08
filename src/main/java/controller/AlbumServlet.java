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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Album;
import model.Artist;
import model.User;

/**
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name = "AlbumServlet", urlPatterns = {"/album"})
public class AlbumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {

            String view = request.getParameter("view");
            AlbumDAO albumDAO;
            if (view == null || view.isEmpty()) {
                view = "list";
            }

            if ("create".equals(view)) {
                int artistId = -1;
                try {
                    artistId = Integer.parseInt(request.getParameter("artistId"));
                    request.setAttribute("artistId", artistId);
                } catch (Exception e) {
                    artistId = -1;
                    request.setAttribute("artistId", artistId);
                }
                ArtistDAO artistDAO = new ArtistDAO();
                List<Artist> list = artistDAO.getList();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/album/create.jsp").forward(request, response);
            } else if ("edit".equals(view)) {
                ArtistDAO artistDAO = new ArtistDAO();
                List<Artist> list = artistDAO.getList();
                request.setAttribute("list", list);
                int id = Integer.parseInt(request.getParameter("id"));
                albumDAO = new AlbumDAO();
                Album album = albumDAO.getById(id);
                request.setAttribute("id", album.getId());
                request.setAttribute("title", album.getTitle());
                request.setAttribute("artistId", album.getArtist().getId());
                request.getRequestDispatcher("/WEB-INF/album/edit.jsp").forward(request, response);
            } else if ("delete".equals(view)) {
                int id = Integer.parseInt(request.getParameter("id"));
                albumDAO = new AlbumDAO();
                Album album = albumDAO.getById(id);
                request.setAttribute("id", album.getId());
                request.setAttribute("title", album.getTitle());
                request.getRequestDispatcher("/WEB-INF/album/delete.jsp").forward(request, response);
            } else {
                albumDAO = new AlbumDAO();
                List<Album> list = albumDAO.getList();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/WEB-INF/album/list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id;
        String title;
        int artistId;
        AlbumDAO dao;

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                title = request.getParameter("title");
                artistId = Integer.parseInt(request.getParameter("artist"));
                dao = new AlbumDAO();
                dao.create(title, artistId);
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                title = request.getParameter("title");
                artistId = Integer.parseInt(request.getParameter("artist"));
                dao = new AlbumDAO();
                dao.update(id, title, artistId);
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                dao = new AlbumDAO();
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/album?view=list");
                break;
        }
    }

}
