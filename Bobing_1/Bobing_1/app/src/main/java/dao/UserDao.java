package dao;

import com.example.bobing_1.Connect11;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import entity.User;
import utils.JDBCUtils;

public class UserDao {

    private static final String TAG = "mysql-party-UserDao";

    public int login(String userAccount, String userPassword) {

        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        int msg = 0;
        try {
            // mysql简单的查询语句。这里是根据user表的userAccount字段来查询某条记录
            String sql = "select * from user where userAccount = ?";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                System.out.println(11111);
                System.out.println(11111);
                System.out.println(11111);
                System.out.println(11111);
                if (ps != null) {
                    System.out.println(22222);
                    System.out.println(22222);
                    System.out.println(22222);
                    System.out.println(22222);
                    System.out.println(22222);
                    Log.e(TAG, "账号：" + userAccount);
                    //根据账号进行查询
                    ps.setString(1, userAccount);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    //将查到的内容储存在map里
                    while (rs.next()) {
                        // 注意：下标是从1开始的
                        for (int i = 1; i <= count; i++) {
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field, rs.getString(field));
                        }
                    }
                    connection.close();
                    ps.close();

                    if (map.size() != 0) {
                        System.out.println(map);
                        System.out.println(map);
                        System.out.println(map);
                        System.out.println(map);
                        System.out.println(map);
                        StringBuilder s = new StringBuilder();
                        //寻找密码是否匹配
                        for (String key : map.keySet()) {
                            if (key.equals("userPassword")) {

                                if (userPassword.equals(map.get(key))) {

                                    msg = 1;            //密码正确
                                } else {
                                    msg = 2;            //密码错误
                                }

                                break;
                            }
                        }
                    } else {
                        Log.e(TAG, "查询结果为空");
                        msg = 3;
                    }
                } else {
                    System.out.println(33333);
                    System.out.println(3333);
                    System.out.println(3333);
                    System.out.println(3333);
                    System.out.println(3333);
                    msg = 0;
                }
            } else {
                System.out.println(44444);
                System.out.println(4444);
                System.out.println(4444);
                System.out.println(4444);
                System.out.println(4444);

                msg = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "异常login：" + e.getMessage());
            msg = 0;
        }
        return msg;
    }


    /**
     * function: 注册
     */
    public boolean register(User user) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();

        try {
            String sql = "insert into user(userAccount,userPassword,userName) values (?,?,?)";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {

                    //将数据插入数据库
                    ps.setString(1, user.getUserAccount());
                    ps.setString(2, user.getUserPassword());
                    ps.setString(3, user.getUserName());

                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if (rs > 0)
                        return true;
                    else
                        return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }

    }

    /**
     * function: 根据账号进行查找该用户是否存在
     */
    public User findUser(String userAccount) {

        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        User user = null;
        try {
            String sql = "select * from user where userAccount = ?";
            if (connection != null) {// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, userAccount);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        //注意：下标是从1开始
                        int id = rs.getInt(1);
                        String userAccount1 = rs.getString(2);
                        String userPassword = rs.getString(3);
                        String userName = rs.getString(4);
                        user = new User(id, userAccount1, userPassword, userName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return user;
    }

}
