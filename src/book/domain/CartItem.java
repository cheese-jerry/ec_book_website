package book.domain;

public class CartItem {
    private Integer id ;
    private Book book ; //书的id
    private Integer buyCount ;
    private User orderBean ; //user

    public CartItem(){}
    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Book book, Integer buyCount, User orderBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.orderBean = orderBean;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(User orderBean) {
        this.orderBean = orderBean;
    }
}

