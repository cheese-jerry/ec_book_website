package book.service;

import book.dao.UserDAOImpl;
import book.domain.User;

public class UserServiceImpl {
    private UserDAOImpl userDAO = null;

    public User login(String uname,String pwd){
        return userDAO.get_user(uname,pwd);
    }

    public void regist(User user){
        userDAO.addUser(user);
    }


}
