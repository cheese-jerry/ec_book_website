package book.service;

import book.dao.CartItemDAOImpl;
import book.domain.Book;
import book.domain.Cart;
import book.domain.CartItem;
import book.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl {
    private CartItemDAOImpl cartItemDAO = null;
    private BookServiceImpl bookService = null;

    public void addCartItem(CartItem cartItem){
        cartItemDAO.addCartItem(cartItem);

    }

    public void updateCartItem(CartItem cartItem){
        cartItemDAO.updateCartItem(cartItem);
    }

    public void add_Or_update(CartItem cartItem, Cart cart){
        if(cart!=null){
            Map<Integer,CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap == null){
                cartItemMap = new HashMap<>();
            }
            //存在则更新
            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem temp = cartItemMap.get(cartItem.getBook().getId());
                temp.setBuyCount(temp.getBuyCount()+1);
                updateCartItem(temp);
            }else{
                //不存在直接添加
                addCartItem(cartItem);
            }
        }else {
            addCartItem(cartItem);
        }

    }

    //加载特定用户购物车信息
    public Cart getCart(User user){
        List<CartItem> cartItemList= getCartItemList(user);
        Map<Integer,CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
            //如果当前用户的购物车项为空，那么我们cart也不为空，只不过map为空
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
        }


    public List<CartItem> getCartItemList(User user){
        List<CartItem> cartItemList= cartItemDAO.get_cart(user);
        for(CartItem cartItem:cartItemList){
            Book book = bookService.getBook(cartItem.getBook().getId());
            cartItem.setBook(book);
        }
        return cartItemList;
    }

    public void delCart(Cart cart){
        for(CartItem cartItem:cart.getCartItemMap().values()) {
            Integer bookid = cartItem.getBook().getId(); //从自己购物车删除一个商品总库存得加一
            Book book = bookService.getBook(bookid);
            book.setBookCount(book.getBookCount()+1);
            bookService.editInventory(book);
            cartItemDAO.del_cart(cartItem);
        }
        cartItemDAO.set_id_to_zero(); //如果多人操作以后得改
    }

    public void delOneCartItem(Integer cartItemid){
        Integer bookid = cartItemDAO.get_cart_by_id(cartItemid).getBook().getId(); //从自己购物车删除一个商品总库存得加一
        Book book = bookService.getBook(bookid);
        book.setBookCount(book.getBookCount()+1);
        bookService.editInventory(book);
        cartItemDAO.del_cart_by_id(cartItemid);
    }
}


