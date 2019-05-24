package myServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import myDAO.DepartmentDAO;
import myDAO.NewsDAO;
import myModel.Article;
import myModel.DepartmentBean;
import myModel.PageBean;
import myUtil.DbUtil;
import myUtil.PropertiesUtil;
import myUtil.StringUtil;

@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDao = new NewsDAO();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	
	public NewsServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		try {
			newsList(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private  void newsList(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 分页
		String page=req.getParameter("page");
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Connection conn=null;
		try {
			conn=DbUtil.getConnection();
			int pageCount=0;
			int pageSize=Integer.parseInt(PropertiesUtil.getValue("pageSize"));
			int total=newsDao.newsCount();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			ArrayList<Article> newsList=newsDao.newsList(pageBean);
			ArrayList<DepartmentBean> departmentList = departmentDAO.getAllDepartment();
			
			//分页总数
			int rowCount=total;
			if(rowCount%pageSize==0){
				pageCount=rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			req.setAttribute("rowCount",rowCount);
			req.setAttribute("pageCount",pageCount);
			
			//判断
			if(Integer.parseInt(page)<1){
				page="1";
			}
			if(Integer.parseInt(page)>pageCount){
				page=pageCount+"";
			}
			req.setAttribute("page",page);
			
			//分页的下拉
			ArrayList<String> pageNumberList=new ArrayList<String>();;
			for(int i=1;i<=pageCount;i++){
				pageNumberList.add(i+"");
			}
			req.setAttribute("pageNumberList", pageNumberList);
			req.setAttribute("departmentList", departmentList);
			req.setAttribute("newsList", newsList);
//			req.setAttribute("mainPage", "index.jsp");

			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
