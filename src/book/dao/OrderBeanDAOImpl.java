package book.dao;

import book.domain.OrderBean;
import book.domain.User;

import java.util.List;


public class OrderBeanDAOImpl extends Basic_DAO<OrderBean>{
    public void addOrderBean(OrderBean orderBean){
        int orderBeanid = super.executeUpdate("insert into t_order values(0,?,?,?,?,?);"
                ,orderBean.getOrderNo(),orderBean.getOrderDate()
                ,orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(orderBeanid);
    }

    public List<OrderBean> getOrderList(User user){
        return super.executeQuery("select * from t_order where orderUser = ?",user.getId());
    }
}
