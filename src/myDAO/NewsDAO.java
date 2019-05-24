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
 * 数据操作类
 * 
 * @author WYP
 *
 */
public class NewsDAO {
	/**
	 * 获取所有文章
	 */
	public List<Article> getAllArticles() {
		// 建立集合
		List<Article> list = new ArrayList<>();
		Article article = null;
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// java.util.Date d = null;
		// 建立连接、会话、结果集
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Date date = null;

		try {
			// 连接到数据库，并开启会话
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article order by date DESC,id DESC");
			// 执行会话，并建立结果集
			resultSet = statement.executeQuery();
			// 遍历结果集，取出数据库里面的信息，并组成实体类
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				int department_id = resultSet.getInt("department_id");
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
				// 遍历一组数据就new一个对象出来。
				article = new Article(id, title, content, department_id, date);
				// 将获取到的实体类信息存入集合
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
	 * 根据文章标题获取多篇文章
	 */
	public List<Article> getArticlesByTitle(String title) {
		// 建立对象
		List<Article> list = new ArrayList<>();

		// 建立连接、会话、结果集
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接到数据库，并开启会话
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where title LIKE \'%"+title+"%\'");
//			statement.setString(1, title);
			// 执行会话，并建立结果集
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment_id(resultSet.getInt("department_id"));
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
	 * 根据发布单位获取多篇文章
	 */
	public List<Article> getArticlesByDepart(int department_id) {
		// 建立对象
		List<Article> list = new ArrayList<>();
		
		// 建立连接、会话、结果集
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接到数据库，并开启会话
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where department_id=?");
			statement.setInt(1, department_id);
			// 执行会话，并建立结果集
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment_id(resultSet.getInt("department_id"));
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
	 * 根据文章内容获取多篇文章
	 */
	public List<Article> getArticlesByContent(String content) {
		// 建立对象
		List<Article> list = new ArrayList<>();

		// 建立连接、会话、结果集
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接到数据库，并开启会话
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where content LIKE \'%"+content+"%\'");
//			statement.setString(1, content);
			// 执行会话，并建立结果集
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment_id(resultSet.getInt("department_id"));
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
	 * 根据id获取单篇文章
	 */
	public Article getArticleById(int id) {
		// 建立对象
		Article article = new Article();
		// 建立连接、会话、结果集
		Connection connection = DbUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			// 连接到数据库，并开启会话
			statement = (PreparedStatement) connection
					.prepareStatement("select * from article where id=?");
			statement.setInt(1, id);
			// 执行会话，并建立结果集
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				article.setId(resultSet.getInt("id"));
				article.setTitle(resultSet.getString("title"));
				article.setContent(resultSet.getString("content"));
				article.setDepartment_id(resultSet.getInt("department_id"));
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
	 * 删除指定标题文章
	 */

	/**
	 * 更改命名以及内容
	 */
	public boolean deleteArticleById(int id) {
		// 建立连接
		Connection connection = DbUtil.getConnection();
		// 开启会话
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("delete from article where id=?");
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// 关闭会话 和 连接
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
	 * 添加文章
	 */
	public int addArticle(String title, String content, int department_id,
			Date date) {
		int affect = 0;
		// 建立连接
		Connection connection = DbUtil.getConnection();

		// 开启会话
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("insert into article(title,content,department_id,date) values(?,?,?,?)");
			statement.setString(1, title);
			statement.setString(2, content);
			statement.setInt(3, department_id);
			statement.setDate(4, date);
			affect = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭会话 和 连接
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
	 * 更改文章
	 */

	public int upDateArticle(String title, String content, int id) {
		int affect = 0;
		// 建立连接
		Connection connection = DbUtil.getConnection();
		// 开启会话
		PreparedStatement statement = null;
		try {
			statement = (PreparedStatement) connection
					.prepareStatement("update article set content=?,title=? where id=?");
			statement.setString(1, content);
			statement.setString(2, title);
			statement.setInt(3, id);

			affect = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭会话 和 连接
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
	 * 获取文章总数
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
			// 关闭会话 和 连接
			DbUtil.close(connection, statement, resultSet);
		}
		return -1;

	}

	public ArrayList<Article> newsList(PageBean pageBean) throws Exception {
		ArrayList<Article> newsList = new ArrayList<Article>();
		Connection connection = DbUtil.getConnection();

		StringBuffer sb = new StringBuffer(
				"select id,title,content,department_id,date from article order by date DESC,id DESC");
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
			article.setDepartment_id(resultSet.getInt("department_id"));
			article.setDate(resultSet.getDate("date"));
			newsList.add(article);
		}

		return newsList;
	}

}
