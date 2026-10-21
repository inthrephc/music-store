package controller;

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
import model.Artist;
import model.User;

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
                request.getRequestDispatcher("/WEB-INF/artist/create.jsp").forward(request, response);
            } else if ("edit".equals(view)) {
                int id = Integer.parseInt(request.getParameter("id"));
                ArtistDAO dao = new ArtistDAO();
                Artist artist = dao.getById(id);
                request.setAttribute("artistId", artist.getId());
                request.setAttribute("artistName", artist.getName());
                request.getRequestDispatcher("/WEB-INF/artist/edit.jsp").forward(request, response);
            } else if ("delete".equals(view)) {
                int id = Integer.parseInt(request.getParameter("id"));
                ArtistDAO dao = new ArtistDAO();
                Artist artist = dao.getById(id);
                request.setAttribute("artistId", artist.getId());
                request.setAttribute("artistName", artist.getName());
                request.getRequestDispatcher("/WEB-INF/artist/delete.jsp").forward(request, response);
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

                ArtistDAO artistDAO = new ArtistDAO();
                int totalPages = artistDAO.getTotalPages(pageSize);
                int totalRows = artistDAO.getTotalRows();

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

                List<Artist> list = artistDAO.getList(currentPage, pageSize);
                request.setAttribute("list", list);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pages", pages);
                request.setAttribute("totalPages", totalPages);
                request.setAttribute("pageSize", pageSize);
                request.getRequestDispatcher("/WEB-INF/artist/list.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int artistId;
        String artistName;
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                artistName = request.getParameter("name");
                ArtistDAO dao = new ArtistDAO();
                dao.create(artistName);
                response.sendRedirect(request.getContextPath() + "/artist?view=list");
                break;

            case "edit":
                artistId = Integer.parseInt(request.getParameter("id"));
                artistName = request.getParameter("name");
                dao = new ArtistDAO();
                dao.update(artistId, artistName);
                response.sendRedirect(request.getContextPath() + "/artist?view=list");
                break;

            case "delete":
                artistId = Integer.parseInt(request.getParameter("id"));
                dao = new ArtistDAO();
                dao.delete(artistId);
                response.sendRedirect(request.getContextPath() + "/artist?view=list");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/artist?view=list");
                break;
        }
    }
}
