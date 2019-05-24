package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myDAO.DepartmentDAO;

@WebServlet("/AddDepartmentServlet")
public class AddDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码，设置字符集
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charSet=utf-8");

        String name = "default";

        name = request.getParameter("name");
        DepartmentDAO userQuery = new DepartmentDAO();

        userQuery.addDepartment(name);

        response.sendRedirect(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
