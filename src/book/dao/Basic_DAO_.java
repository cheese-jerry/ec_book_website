package book.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBC_C3P0;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Basic_DAO_<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Connection connection = null;

    //dml
    public int update(String sql,Object... parameters){
        connection = JDBC_C3P0.get_connection();
        try {
            int row = queryRunner.update(connection,sql,parameters);
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_C3P0.close(connection,null,null);
        }
    }

    //query
    public List<T> muti_query(String sql,Class<T> clazz,Object... parameters){
        connection = JDBC_C3P0.get_connection();
        try {
            List<T> list = queryRunner.query(connection,sql,new BeanListHandler<>(clazz),parameters);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_C3P0.close(connection,null,null);
        }
    }

    public T single_query(String sql,Class<T> clazz,Object... parameters){
        connection = JDBC_C3P0.get_connection();
        try {
            T res = queryRunner.query(connection,sql,new BeanHandler<>(clazz),parameters);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_C3P0.close(connection,null,null);
        }
    }

    public Object scalar_query(String sql,Object... parameters){
        connection = JDBC_C3P0.get_connection();
        try {
            Object res = queryRunner.query(connection,sql,new ScalarHandler<>(),parameters);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_C3P0.close(connection,null,null);
        }
    }

}