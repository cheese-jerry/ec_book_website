package book.controller;

import book.domain.Cart;
import book.domain.User;
import book.service.CartItemServiceImpl;
import book.service.UserServiceImpl;

import javax.servlet.http.HttpSession;

public class UserController {
    private UserServiceImpl userService = null;
    private CartItemServiceImpl cartItemService = null;

    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname,pwd);
        if(user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart); //在登录时就把用户的购物车与用户绑定
            session.setAttribute("currUser",user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String regist(String verifyCode,String uname, String pwd, String email,HttpSession session){
        Object kaptchaobj = session.getAttribute("KAPTCHA_SESSION_KEY");
        System.out.println(verifyCode);
        if(kaptchaobj == null){
            return "user/regist";
        }else{
            if(verifyCode.equals((String) kaptchaobj)){
                userService.regist(new User(uname,pwd,email,"0"));
                return "user/login";
            }
        }
        return"user/regist";
    }
}
