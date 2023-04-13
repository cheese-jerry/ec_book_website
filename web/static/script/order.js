function searchdetail(orderBeanid){
    window.location.href="order.do?operate=getOrderDetail&orderBeanId="+orderBeanid; //跳转到order控制器调用getOrderDetail方法参数是orderbeanid
}