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
import java.util.ArrayList;
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
                int pageSize, currentPage;

                try {
                    currentPage = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    currentPage = 1;
                }

                try {
                    pageSize = Integer.parseInt(request.getParameter("size"));
                } catch (NumberFormatException e) {
                    pageSize = 10;
                }
                
                albumDAO = new AlbumDAO();
                int totalPages = albumDAO.getTotalPages(pageSize);
                int totalRows = albumDAO.getTotalRows();

                if (currentPage < 1) {
                    currentPage = 1;
                }
                if (totalPages > 0 && currentPage > totalPages) {
                    currentPage = totalPages;
                }

                if (pageSize < 1) {
                    pageSize = 10;
                }
                if (totalRows > 0 && pageSize > totalRows) {
                    pageSize = totalRows;
                }

                List<Integer> pages = new ArrayList<>();
                if (totalPages > 1) {
                    int delta = 2; // pagination offset
                    int left = Math.max(2, currentPage - delta);
                    int right = Math.min(totalPages - 1, currentPage + delta);

                    // Luon co trang 1
                    pages.add(1);

                    // Xu ly dau ... ben trai hoac cac trang dem
                    if (left > 2) {
                        pages.add(-1); // -1 bieu thi dau '...'
                    } else if (left == 2) {
                        pages.add(2);
                    }

                    // Cum trang o giua
                    for (int i = Math.max(left, 3); i <= Math.min(right, totalPages - 2); i++) {
                        pages.add(i);
                    }

                    // Xu ly dau '...' ben trai hoac cac trang dem
                    if (right < totalPages - 1) {
                        pages.add(-1);
                    } else if (right == totalPages - 1 && totalPages > 2) {
                        pages.add(totalPages - 1);
                    }

                    // luon co trang cuoi
                    if (totalPages > 1) {
                        pages.add(totalPages);
                    }
                }

                List<Album> list = albumDAO.getList(currentPage, pageSize);
                request.setAttribute("list", list);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pages", pages);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageSize", pageSize);
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
