package myServlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myDAO.NewsDAO;
import myModel.Article;

@WebServlet("/UpdateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateNewsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 防止中文乱码，设置字符集
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charSet=utf-8");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
				java.util.Date d = null;

				String title = "default";
				String content = "default";
//				int department_id = 0;
//				Date date = null;
				int id = Integer.parseInt(request.getParameter("id"));

				title = request.getParameter("title");
				content = request.getParameter("content");
//				department_id = Integer.parseInt(request.getParameter("department_id"));
//				try{
//					d = sdf.parse(request.getParameter("date"));
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				date = new Date(d.getTime());

				
				
				int affect = 0;
				String repath = request.getContextPath();// 获取当前路径上下文
		
			NewsDAO NewsSearch = new NewsDAO();
			affect = NewsSearch.upDateArticle(title, content, id); // affect表示影响的行数
			response.sendRedirect(repath + request.getContextPath());
		
	}
}
