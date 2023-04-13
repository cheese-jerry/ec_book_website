package book.domain;

import book.service.BookServiceImpl;

import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer,CartItem> cartItemMap ; //书的id，购物车项
    private Double totalMoney;    //总金额
    private Integer totalCount;   //购物项的数量
    private Integer totalBookCount;

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalMoney(){
        totalMoney = 0.0;

        if(cartItemMap!=null && cartItemMap.size()>0){
            Set<Map.Entry<Integer,CartItem>> entries = cartItemMap.entrySet();
            for(Map.Entry<Integer, CartItem> cartItemEntry : entries){
                CartItem cartItem = cartItemEntry.getValue();

                Book book = cartItem.getBook();
                totalMoney = totalMoney + book.getPrice()*cartItem.getBuyCount();
            }
        }

        return totalMoney;
    }

    public Integer getTotalCount(){
        totalCount = 0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalCount = 0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            for(CartItem cartItem:cartItemMap.values()){
                totalBookCount = totalBookCount + cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
