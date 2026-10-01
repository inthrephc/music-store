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
import java.util.List;
import model.Genre;

/**
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name="GenreServlet", urlPatterns={"/genre"})
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
            GenreDAO dao = new GenreDAO();
            List<Genre> list = dao.getList();
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/genre/list.jsp").forward(request, response);
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
