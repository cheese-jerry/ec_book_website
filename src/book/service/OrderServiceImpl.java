package book.service;

import book.dao.CartItemDAOImpl;
import book.dao.OrderBeanDAOImpl;
import book.dao.OrderItemDAOImpl;
import book.domain.*;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl {
    private OrderBeanDAOImpl orderBeanDAO = null;
    private OrderItemDAOImpl orderItemDAO = null;
    private CartItemDAOImpl cartItemDAO = null;

    private BookServiceImpl bookService = null;

    public void addOrderBean(OrderBean orderBean){
        //1 定单表添加一条记录
        //2订单详情添加若干条记录
        //3购物车删除对应记录
        //4销量加一
        orderBeanDAO.addOrderBean(orderBean); //orderBean id 有值

        User curruser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = curruser.getCart().getCartItemMap();
        for(CartItem cartItem:cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }
        for(CartItem cartItem:cartItemMap.values()){
            Book book = bookService.getBook(cartItem.getBook().getId());
            book.setSaleCount(book.getSaleCount()+1);
            bookService.editSale(book);
            cartItemDAO.del_cart(cartItem);
        }
        cartItemDAO.set_id_to_zero();
    }

    public List<OrderBean> getOrderList(User user){
        return orderBeanDAO.getOrderList(user);
    }

    public List<OrderItem> getOrderDetail(Integer orderBeanid){
        List<OrderItem> orderItemList =  orderItemDAO.getOrderItem(orderBeanid);
        for(OrderItem orderItem:orderItemList){
            Book book = bookService.getBook(orderItem.getBook().getId());
            orderItem.setBook(book);
        }
        return orderItemList;
    }
}
