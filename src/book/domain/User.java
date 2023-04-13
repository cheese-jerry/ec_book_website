package book.domain;

import java.util.List;

public class User {
        private Integer id ;
        private String uname ;
        private String pwd ;
        private String email;
        private String role ;

        private Cart cart; //数据库没有这个表，表示用户的购物车
        private List<OrderBean> orderList ;     //1:N

        public User(){}
        public User(Integer id) {
            this.id = id;
        }

        public User(String uname, String pwd, String email, String role) {
                this.uname = uname;
                this.pwd = pwd;
                this.email = email;
                this.role = role;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", uname='" + uname + '\'' +
                        ", pwd='" + pwd + '\'' +
                        ", email='" + email + '\'' +
                        ", role=" + role+"}";
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getUname() {
                return uname;
        }

        public void setUname(String uname) {
                this.uname = uname;
        }

        public String getPwd() {
                return pwd;
        }

        public void setPwd(String pwd) {
                this.pwd = pwd;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getRole() {
                return role;
        }

        public void setRole(String role) {
                this.role = role;
        }

        public List<OrderBean> getOrderList() {
                return orderList;
        }

        public void setOrderList(List<OrderBean> orderList) {
                this.orderList = orderList;
        }

        public Cart getCart() {
                return cart;
        }

        public void setCart(Cart cart) {
                this.cart = cart;
        }
}




