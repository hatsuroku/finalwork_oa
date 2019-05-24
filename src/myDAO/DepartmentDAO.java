package myDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import myModel.DepartmentBean;
import myUtil.DateUtil;
import myUtil.DbUtil;

/**
 * 数据操作类
 *
 * @author WYP
 *
 */

public class DepartmentDAO {

    /**
     *      获取所有部门
     */
    public ArrayList<DepartmentBean> getAllDepartment(){

        ArrayList<DepartmentBean> list = new ArrayList<>();

        DepartmentBean department = null;
        // 建立连接、会话、结果集
        Connection connection = DbUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 连接到数据库，并开启会话
            statement = (PreparedStatement) connection
                    .prepareStatement("select * from department order by id ASC");
            // 执行会话，并建立结果集
            resultSet = statement.executeQuery();
            // 遍历结果集，取出数据库里面的信息，并组成实体类
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // 遍历一组数据就new一个对象出来。
                department = new DepartmentBean(id, name);
                // 将获取到的实体类信息存入集合
                list.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(connection, statement, resultSet);
        }
        return list;
    }


    /**
     *      根据id获取部门名称
     */
    public String getNameById(int id){

        // 建立对象
        String name = null;
        // 建立连接、会话、结果集
        Connection connection = DbUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 连接到数据库，并开启会话
            statement = (PreparedStatement) connection
                    .prepareStatement("select * from department where id=?");
            statement.setInt(1, id);
            // 执行会话，并建立结果集
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(connection, statement, resultSet);
        }
        return name;
    }

    /**
     * 添加部门
     */
    public void addDepartment(String name) {
        // 建立连接
        Connection connection = DbUtil.getConnection();

        // 开启会话
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection
                    .prepareStatement("insert into department(name) values(?)");
            statement.setString(1, name);
            statement.executeUpdate();
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
    }

    /**
     * 删除部门
     */
    public boolean deleteDepartmentById(int id) {
        // 建立连接
        Connection connection = DbUtil.getConnection();

        // 开启会话
        PreparedStatement statement = null;
        try {
            statement = (PreparedStatement) connection
                    .prepareStatement("delete from department where id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
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

}
