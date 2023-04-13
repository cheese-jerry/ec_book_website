package book.dao;

import book.domain.OrderBean;
import book.domain.OrderItem;

import java.util.List;

public class OrderItemDAOImpl extends Basic_DAO<OrderItem>{
    public void addOrderItem(OrderItem orderItem){
        super.executeUpdate("insert into t_order_item values(0,?,?,?);"
                ,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());

    }

    public List<OrderItem> getOrderItem(Integer orderBeanid){
        return super.executeQuery("select * from t_order_item where orderBean = ?",orderBeanid);
    }
}
