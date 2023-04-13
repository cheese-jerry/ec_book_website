package book.domain;

public class Book {
    private Integer id ;
    private String bookimg ;
    private String bookName ;
    private Double price ;
    private String author ;
    private Integer saleCount ; //销量
    private Integer bookCount ; //库存
    private Integer bookStatus ;        //0:正常  -1：无效

    public Book(){}
    public Book(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookimg() {
        return bookimg;
    }

    public void setBookImg(String bookimg) {
        this.bookimg = bookimg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookImg='" + bookimg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", saleCount=" + saleCount +
                ", bookCount=" + bookCount +
                ", bookStatus=" + bookStatus +
                '}';
    }
}


