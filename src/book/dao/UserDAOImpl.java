package book.dao;

import book.domain.User;

public class UserDAOImpl extends Basic_DAO<User>{
    public User get_user(String uname,String pwd){
        return super.load("select * from t_user where uname = ? and pwd = ?;",uname,pwd);
        //return super.single_query("select * from t_user where uname = ? and pwd = ?;", User.class,uname,pwd);
    }

    public void addUser(User user){
        super.executeUpdate("insert into t_user values(0,?,?,?,'user')"
                ,user.getUname(),user.getPwd(),user.getEmail());
    }
}
