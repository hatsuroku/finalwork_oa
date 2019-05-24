package myServlet;

import myDAO.UserDAO;
import myModel.UserBean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DoLoginServlet
 */
@WebServlet("/DoLoginServlet")
public class DoLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        // TODO Auto-generated method stub
        String username = request.getParameter("username");// 获取登陆信息、
        String password = request.getParameter("password");
        // 防止中文乱码，设置字符集
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charSet=utf-8");
        String repath = request.getContextPath();// 获取当前路径上下文
        HttpSession session = request.getSession();

        //新建一个userbean保存结果
        UserDAO userSearch= new UserDAO();
        UserBean queryUser = new UserBean();
        queryUser = userSearch.getUserByUsername(username);
        //检测密码是否一样
        if (password.equals(queryUser.getPassword())) {
            // 登陆成功服务器内部转发
            session.setAttribute("username", username);// 在session里面保存一个用户登陆状态
            session.setAttribute("priority", queryUser.getPriority());// 在session里面保存一个用户权限信息
            request.getRequestDispatcher("/loginsuccess.jsp").forward(request,
                    response);// Dispatcher：分配器,内部转发不能加上下文
        } else {
            // 登陆失败则重定向"
            response.sendRedirect(repath + "/login.jsp?state=0");
        }
    }

}