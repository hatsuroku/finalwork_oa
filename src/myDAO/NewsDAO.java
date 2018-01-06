package myDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import myModel.Article;
import myModel.PageBean;
import myUtil.DateUtil;
import myUtil.DbUtil;

/**
 * ���ݲ�����
 * 
 * @author WYP
 *
 */
public class NewsDAO {
	/**
	 * ��ȡ��������
	 */
	public List<Article> getAllArticles() {
		// ��������
		List<Article> list = new ArrayList<>();
		Article article = null;
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date d = null;
		// �������ӡ��Ự�������
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Date date = null;

		try {
			// ���ӵ����ݿ⣬�������Ự
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article order by date DESC,id DESC");
			// ִ�лỰ�������������
			resultSet = statement.executeQuery();
			// �����������ȡ�����ݿ��������Ϣ�������ʵ����
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				String department = resultSet.getString("department");
				// try {
				// d = sdf.parse(resultSet.getString("date"));
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// date = new Date(d.getTime());
				try {
					date = DateUtil.formatString(resultSet.getString("date"),
							"yyyy-MM-dd");
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ����һ�����ݾ�newһ�����������
				article = new Article(id, title, content, department, date);
				// ����ȡ����ʵ������Ϣ���뼯��
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(connection, statement, resultSet);
		}
		return list;
	}

	/**
	 * ���ݷ�����λ��ȡ��ƪ����
	 */
	public List<Article> getArticlesByDepart(String department) {
		// ��������
		List<Article> list = new ArrayList<>();
		
		// �������ӡ��Ự�������
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// ���ӵ����ݿ⣬�������Ự
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where department=?");
			statement.setString(1, department);
			// ִ�лỰ�������������
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment(resultSet.getString("department"));
				article.setDate(resultSet.getDate("date"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(connection, statement, resultSet);
		}
		return list;
	}

	/**
	 * ����id��ȡ��ƪ����
	 */
	public Article getArticleById(int id) {
		// ��������
		Article article = new Article();
		// �������ӡ��Ự�������
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// ���ӵ����ݿ⣬�������Ự
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where id=?");
			statement.setInt(1, id);
			// ִ�лỰ�������������
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment(resultSet.getString("department"));
				article.setDate(resultSet.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(connection, statement, resultSet);
		}
		return article;
	}

	/**
	 * ɾ��ָ����������
	 */
	public boolean deleteIdArticle(String title) {
		// ��������
		Connection connection = DbUtil.getConnection();
		// �����Ự
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("delete from article where title=?");
			statement.setString(1, title);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// �رջỰ �� ����
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * ��������
	 */
	public int addArticle(String title, String content, String department,
			Date date) {
		int affect = 0;
		// ��������
		Connection connection = DbUtil.getConnection();

		// �����Ự
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("insert into article(title,content,department,date) values(?,?,?,?)");
			statement.setString(1, title);
			statement.setString(2, content);
			statement.setString(3, department);
			statement.setDate(4, date);
			affect = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �رջỰ �� ����
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return affect;
	}

	/**
	 * ��������
	 */
	public int upDateArticle(String title, String content, String department,
			Date date) {
		int affect = 0;
		// ��������
		Connection connection = DbUtil.getConnection();
		// �����Ự
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("update article set content=?,department=?,date=? where title=?");
			statement.setString(1, content);
			statement.setString(2, department);
			statement.setDate(3, date);
			statement.setString(4, title);

			affect = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �رջỰ �� ����
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return affect;
	}

	/**
	 * ��ȡ��������
	 */

	public int newsCount() throws Exception {
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = (PreparedStatement) connection
					.prepareStatement("select count(*) as total from article");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("total");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �رջỰ �� ����
			DbUtil.close(connection, statement, resultSet);
		}
		return -1;

	}

	public ArrayList<Article> newsList(PageBean pageBean) throws Exception {
		ArrayList<Article> newsList = new ArrayList<Article>();
		Connection connection = DbUtil.getConnection();

		StringBuffer sb = new StringBuffer(
				"select id,title,content,department,date from article order by date DESC,id DESC");
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + ","
					+ pageBean.getPageSize());
		}
		PreparedStatement pstmt = (PreparedStatement) connection
				.prepareStatement(sb.toString());
		ResultSet resultSet = pstmt.executeQuery();

		while (resultSet.next()) {

			Article article = new Article();
			article.setId(resultSet.getInt("id"));
			article.setTitle(resultSet.getString("title"));
			article.setContent(resultSet.getString("content"));
			article.setDepartment(resultSet.getString("department"));
			article.setDate(resultSet.getDate("date"));
			newsList.add(article);
		}

		return newsList;
	}
}