package controller;

import dao.SinhVienDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SinhVien;

import java.io.IOException;
import java.util.List;

@WebServlet("/search-sv")
public class SearchSinhVienServlet extends HttpServlet {
    private SinhVienDAO dao = new SinhVienDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.isEmpty()) {
            List<SinhVien> result = dao.searchSinhVien(keyword);
            request.setAttribute("resultList", result);
            request.setAttribute("keyword", keyword);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("search_sv.jsp");
        dispatcher.forward(request, response);
    }
}