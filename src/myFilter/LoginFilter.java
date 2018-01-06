package myFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	private List<String> list;
	private List<String> forbidList;

	
	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		StringBuffer paths = req.getRequestURL();
		String path = paths.toString();
		int lastIndex = path.lastIndexOf("/");// Ѱ�� / ���һ�γ��ֵ�λ��
		String newPath = path.substring(lastIndex);
		//���л�������
		if (path.contains(".jpg") || path.contains(".png") || path.contains(".css") || list.contains(newPath)) {// ����ָ����ҳ������У��������ָ������
			chain.doFilter(request, response);
			return;
		}
		
		if (forbidList.contains(newPath)) {
			rep.sendRedirect(req.getContextPath() + "/main.jsp");
		}
		
		String username = (String) session.getAttribute("username");
		if (username != null) {
			// ���������Ϣ�а����û���������С�
			chain.doFilter(request, response);
		} else {
			rep.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// �����б��������е�ҳ��
		list = new ArrayList<>();
		list.add("/login.jsp");
		list.add("/main.jsp");
		list.add("/newsdetail.jsp");
		list.add("/news.jsp");
		list.add("/searchNews.jsp");
		list.add("/DoLoginServlet");
		
		forbidList = new ArrayList<>();
		forbidList.add("/index.jsp");
	}
}

