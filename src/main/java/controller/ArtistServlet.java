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
    
    
//    1) Thực hiện xây dựng logic bắt giá trị của param view
//        - view == "create" -> fwd qua view để hiển thị form thêm mới
//        - view == "edit" -> fwd qua view tương ứng để hiển thị form edit
//        - view == "delete" -> fwd qua view tương ứng để hiển thị form delete
//        - view == "list" hoặc không có view (null) hoặc view == "" -> fwd qua list.jsp
//    2) Tạo view create.jsp. Trong create.jsp, viết 1 form để thêm mới artist gồm:
//        - 1 input "name"
//        - 1 button "Save/Submit"
//        - 1 btn "Clear"
//        - Các thành phần khác trong giao diện giữ nguyên như giao diện danh sách artist (header, nav, title,...)
//    3) Xử lý logic thêm mới (DAO) -> query, statement, execute
//    4) doPost() -> xử lý thêm mới, sau đó chuyển tiếp người dùng về lại trang danh sách.

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
