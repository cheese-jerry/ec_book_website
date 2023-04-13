package book.controller;

import book.domain.Book;
import book.domain.Cart;
import book.domain.CartItem;
import book.domain.User;
import book.service.BookServiceImpl;
import book.service.CartItemServiceImpl;
import org.apache.catalina.Session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class CartController {
    private CartItemServiceImpl cartItemService = null;
    private BookServiceImpl bookService = null;

    public String index(HttpSession session){
        User user = (User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }
    public String addCart(Integer bookId, HttpSession session){
        //添加图书到购物车，如果存在图书，数量加一，否则新加一本书
        User user = (User) session.getAttribute("currUser") ;
        Book book = bookService.getBook(bookId);
        book.setBookCount(book.getBookCount()-1);
        bookService.editInventory(book);
        CartItem cartItem = new CartItem(book,1,user);
        cartItemService.add_Or_update(cartItem,user.getCart());
        return "redirect:cart.do";
        //再跳转回cart中 写一个index方法使operate=index
    }

    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "redirect:cart.do";
    }

    public String delCart(Cart cart){
        cartItemService.delCart(cart);
        return "redirect:cart.do";
    }

    public String delOneCartItem(Integer cartItemid){
        cartItemService.delOneCartItem(cartItemid);  //后面注意id问题 不连续
        return "redirect:cart.do";
    }
}
