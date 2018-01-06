package myUtil;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import myUtil.PropertiesUtil;

/**
 * ���ݿ�������
 * 
 * @author WYP
 *
 */
public class DbUtil
{
	/**
	 * ��������
	 */
	static Connection connection = null; // �������ӳ���
	static PreparedStatement statement = null; // ����Ự����
	static ResultSet resultSet = null; // ������������
//	private final static String URL = "jdbc:mysql://localhost:3306/mynews?useSSL=false"; // �������ӳ���,news
//																						// ��Ҫ���ӵ����ݿ�����
//	private final static String UESR_NAME = "root"; // �����û�������
//	private final static String PASSWORD = "307619"; // �������볣��

	/**
	 * ��ȡ����
	 */
	public static Connection getConnection()
	{
		try
		{
//			Class.forName("com.mysql.jdbc.Driver");// ����MySQL jdbc��������
//			connection = (Connection) DriverManager.getConnection(URL, UESR_NAME, PASSWORD); // ��ȡ����
			Class.forName(PropertiesUtil.getValue("jdbcName"));
			connection=(Connection) DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"), PropertiesUtil.getValue("dbUserName"), PropertiesUtil.getValue("dbPassword"));
		} catch (Exception e)
		{
			System.out.println("��������ʧ�ܣ�");
			e.printStackTrace();
		}
		return connection; // ��������
	}

	/**
	 * �ر�����
	 */
	public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet)
	{
		// �ȹرռ�
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		// �ٹرջỰ
		if (statement != null)
		{
			try
			{
				statement.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		// ���ر����ӣ��رյ�˳�򲻿��Ը���
		if (connection != null)
		{
			try
			{
				connection.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void close(Connection conn) throws SQLException{
		if (conn != null)
		{
			try
			{
				conn.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
