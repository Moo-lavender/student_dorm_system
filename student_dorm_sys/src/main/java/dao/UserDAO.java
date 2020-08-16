package dao;

import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO {

    public static User query(User user) {
        User queryUser = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DBUtil.getConnection();
            String sql = "select id,nickname,email from user where username=? and password=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            while(rs.next()){
                queryUser = user;
                queryUser.setId(rs.getInt("id"));
                queryUser.setNickname(rs.getString("nickname"));
                queryUser.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            throw new RuntimeException("登录校验用户名密码出错", e);
        } finally {
            DBUtil.close(c, ps, rs);
        }
        return queryUser;
    }

    public static List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select username, password, nickname, email, create_time from user ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setNickname(resultSet.getString("nickname"));
                user.setEmail(resultSet.getString("email"));
                user.setCreateTime(new Date(resultSet.getTimestamp("create_time").getTime()));
                list.add(user);
            }
        }catch (Exception e){
            throw new RuntimeException("查询用户信息出错");
        }finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }

        return list;
    }

    public int insert(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            String sql = "insert into user(username, password, nickname, email) values (?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("插入用户信息出错", e);
        } finally {
            DBUtil.close(c, ps);
        }
    }
}
