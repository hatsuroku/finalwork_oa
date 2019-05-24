package myServlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myDAO.UserDAO;

@WebServlet("/DeleteUserServlet")

public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 防止中文乱码，设置字符集
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charSet=utf-8");
        int id = 0;
        id = Integer.parseInt(request.getParameter("id"));
        String repath = request.getContextPath();// 获取当前路径上下文

        UserDAO DeleteUser = new UserDAO();
        DeleteUser.deleteUserById(id);
        response.sendRedirect(repath + "/seeAllUsers.jsp");

    }
}
