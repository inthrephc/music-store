/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.GenreDAO;
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
import model.Genre;
import model.User;

/**
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name = "GenreServlet", urlPatterns = {"/genre"})
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {

            String view = request.getParameter("view");

            if (view == null || view.isEmpty()) {
                view = "list";
            }

            if ("create".equals(view)) {
                request.getRequestDispatcher("/WEB-INF/genre/create.jsp").forward(request, response);
            } else if ("edit".equals(view)) {
                int id = Integer.parseInt(request.getParameter("id"));
                GenreDAO dao = new GenreDAO();
                Genre genre = dao.getById(id);
                request.setAttribute("id", genre.getId());
                request.setAttribute("name", genre.getName());
                request.getRequestDispatcher("/WEB-INF/genre/edit.jsp").forward(request, response);
            } else if ("delete".equals(view)) {
                int id = Integer.parseInt(request.getParameter("id"));
                GenreDAO dao = new GenreDAO();
                Genre genre = dao.getById(id);
                request.setAttribute("id", genre.getId());
                request.setAttribute("name", genre.getName());
                request.getRequestDispatcher("/WEB-INF/genre/delete.jsp").forward(request, response);
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

                GenreDAO genreDAO = new GenreDAO();
                int totalPages = genreDAO.getTotalPages(pageSize);
                int totalRows = genreDAO.getTotalRows();

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
                        if (!pages.contains(i)) {
                            pages.add(i);
                        }
                    }

                    // Xu ly dau '...' ben trai hoac cac trang dem
                    if (right < totalPages - 1) {
                        pages.add(-1);
                    } else if (right == totalPages - 1 && totalPages > 2) {
                        if (!pages.contains(totalPages - 1)) {
                            pages.add(totalPages - 1);
                        }
                    }

                    // luon co trang cuoi
                    if (totalPages > 1) {
                        if (!pages.contains(totalPages)) {
                            pages.add(totalPages);
                        }
                    }
                }

                List<Genre> list = genreDAO.getList(currentPage, pageSize);
                request.setAttribute("list", list);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pages", pages);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageSize", pageSize);
                request.getRequestDispatcher("/WEB-INF/genre/list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id;
        String name;
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                name = request.getParameter("name");
                GenreDAO dao = new GenreDAO();
                dao.create(name);
                response.sendRedirect(request.getContextPath() + "/genre?view=list");
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                dao = new GenreDAO();
                dao.update(id, name);
                response.sendRedirect(request.getContextPath() + "/genre?view=list");
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                dao = new GenreDAO();
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/genre?view=list");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/genre?view=list");
                break;
        }
    }

}
