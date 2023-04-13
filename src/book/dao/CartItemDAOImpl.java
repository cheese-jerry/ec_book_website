package book.dao;

import book.domain.CartItem;
import book.domain.User;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBC_C3P0;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartItemDAOImpl extends Basic_DAO<CartItem> {

    public void updateCartItem(CartItem cartItem) {
        super.executeUpdate("update t_cart_item set buyCount = ? where id = ?", cartItem.getBuyCount(), cartItem.getId());
        //super.update("update t_cart_item set buyCount = ? where id = ?",cartItem.getBuyCount(),cartItem.getId());
    }

    public void addCartItem(CartItem cartItem) {
        super.executeUpdate("insert into t_cart_item values(null,?,?,?)",
                cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getOrderBean().getId());
        //super.update("insert into t_cart_item values(null,?,?,?)"
        //,cartItem.getBook(),cartItem.getBuyCount(),cartItem.getOrderBean());

    }

    public List<CartItem> get_cart(User user){
        return super.executeQuery("select * from t_cart_item where orderBean = ?",user.getId());
    //super.muti_query("select * from t_cart_item where orderBean = ?;",CartItem.class,user.getId());
    }

    public void del_cart(CartItem cartItem){
        super.executeUpdate("delete from t_cart_item where id=?;",cartItem.getId());
    }

    public void del_cart_by_id(Integer cartItemid){
        super.executeUpdate("delete from t_cart_item where id=?;",cartItemid);
    }

    public void set_id_to_zero(){
        super.executeUpdate("truncate table t_cart_item;");
    }

    public CartItem get_cart_by_id(Integer cartItemid){
        return super.load("select * from t_cart_item where id = ?",cartItemid);
    }

}
