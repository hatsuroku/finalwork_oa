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
        String username = request.getParameter("username");// ��ȡ��½��Ϣ��
        String password = request.getParameter("password");
        // ��ֹ�������룬�����ַ���
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charSet=utf-8");
        String repath = request.getContextPath();// ��ȡ��ǰ·��������
        HttpSession session = request.getSession();

        //�½�һ��userbean������
        UserDAO userSearch= new UserDAO();
        UserBean queryUser = new UserBean();
        queryUser = userSearch.getUserByUsername(username);
        //��������Ƿ�һ��
        if (password.equals(queryUser.getPassword())) {
            // ��½�ɹ��������ڲ�ת��
            session.setAttribute("username", username);// ��session���汣��һ���û���½״̬
            session.setAttribute("priority", queryUser.getPriority());// ��session���汣��һ���û�Ȩ����Ϣ
            request.getRequestDispatcher("/loginsuccess.jsp").forward(request,
                    response);// Dispatcher��������,�ڲ�ת�����ܼ�������
        } else {
            // ��½ʧ�����ض���"
            response.sendRedirect(repath + "/login.jsp?state=0");
        }
    }

}