/**
 * @author Felix
 * @describe
 * @date 2020/6/24 14:16
 */
package felix.dao.Imp0;


import felix.util.JDBCUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class BaseDao {
    public <T> ArrayList<T> queryAll(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //得到结果集
            ResultSet rs = ps.executeQuery();

            //得到结果的元数据（修饰结果集的数据）
            ResultSetMetaData rsmd = rs.getMetaData();
            //利用元数据得到列数
            int columnCount = rsmd.getColumnCount();
            //new一个list备用
            ArrayList<T> list = new ArrayList();
            while (rs.next()) {
                //利用泛型创建一个对应类对象
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //利用元数据获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //用对应列数获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //1.借助反射获取当前列对应的成员属性
                    Field field = clazz.getDeclaredField(columnLabel);
                    //2.将私有成员属性设置为允许访问
                    field.setAccessible(true);
                    //3.将属性放入
                    field.set(t, columnValue);
                }
                //将得到的对象放入数组
                list.add(t);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return null;
    }

    public <T> T queryOne(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //得到结果集
            ResultSet rs = ps.executeQuery();

            //得到结果的元数据（修饰结果集的数据）
            ResultSetMetaData rsmd = rs.getMetaData();
            //利用元数据得到列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                //利用泛型创建一个对应类对象
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //利用元数据获取列名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //用对应列数获取列值
                    Object columnValue = rs.getObject(i + 1);

                    //1.借助反射获取当前列对应的成员属性
                    Field field = clazz.getDeclaredField(columnLabel);
                    //2.将私有成员属性设置为允许访问
                    field.setAccessible(true);
                    //3.将属性放入
                    field.set(t, columnValue);
                }
                //将得到的对象放入数组
                return t;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return null;
    }

    public int update(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }
}
