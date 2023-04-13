package book.controller;

import book.domain.OrderBean;
import book.domain.OrderItem;
import book.domain.User;
import book.service.OrderServiceImpl;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderServiceImpl orderService;

    //付款
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();

        Date now = new Date();
        int year = now.getYear();
        int month = now.getMonth();
        int day = now.getDate();
        int hour = now.getHours();
        int min = now.getMinutes();
        int sec = now.getSeconds();


        LocalDateTime localDateTime = LocalDateTime.now();
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+year+month+day+hour+min+sec); //
        orderBean.setOrderDate(now);
        User currUser = (User)session.getAttribute("currUser");
        orderBean.setOrderUser(currUser);
        orderBean.setOrderMoney(currUser.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);


        orderService.addOrderBean(orderBean);
        return "redirect:cart.do";
    }

    //查看订单
    public String getOrderList(HttpSession session){
        User currUser = (User)session.getAttribute("currUser");
        List<OrderBean> orderBeanList = orderService.getOrderList(currUser);
        currUser.setOrderList(orderBeanList);
        session.setAttribute("currUser",currUser);
        return "order/order";
    }
    //查看详情
    public String getOrderDetail(Integer orderBeanId,HttpSession session){
        List<OrderItem> orderItemList = orderService.getOrderDetail(orderBeanId);
        session.setAttribute("orderItemList",orderItemList);
        System.out.println(orderItemList);
        return "order/detail";
    }
    public String confirmPage(){
        return "order/confirm";
    }

    public String payAndAddressPage(){
        return "order/pay";
    }
}
