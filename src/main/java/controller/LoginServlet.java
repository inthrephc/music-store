/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author NguyenNSCE200377
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // Luu trang thai dang nhap
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            // Luu cookie chua ten dang nhap
            Cookie cookie1 = new Cookie("tenDangNhap", user.getUsername());
            Cookie cookie2 = new Cookie("theme", "light");

            // Set thoi gian song cho cookie
            cookie1.setMaxAge(3600 * 24 * 3);
            cookie2.setMaxAge(3600 * 24 * 3);
            
//            Code nay vi phan nguyen tac bao mat
//            if (user.getUsername().equals("admin")) {
//                Cookie cookie3 = new Cookie("isAdmin", "1");
//                cookie3.setMaxAge(3600);
//                response.addCookie(cookie3);
//            }

            // Truyen cookie theo response ve cho client
            response.addCookie(cookie1);
            response.addCookie(cookie2);

            response.sendRedirect(request.getContextPath() + "/artist?view=list");
        }
    }
}
