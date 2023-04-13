package book.dao;

import book.domain.Book;
import book.domain.CartItem;
import book.domain.User;

import java.util.List;

public class BookDAOImpl extends Basic_DAO<Book>{
    //字段不一致 用别的方法

    public List<Book> get_book_list(){
        return super.executeQuery("select * from t_book");
        //return super.muti_query("select * from t_book", Book.class);
    }

    public Book getBook(Integer id){
        return super.load("select * from t_book where id = ?",id);
        //return super.single_query("select * from t_book where id = ?;", Book.class,id);
    }

    public void editInventory(Book book){
        super.executeUpdate("update t_book set bookCount = ? where id = ?",book.getBookCount(),book.getId());
    }

    public void editSale(Book book){
        super.executeUpdate("update t_book set saleCount = ? where id = ?",book.getSaleCount(),book.getId());
    }

    public List<Book> getBookByName(String bookname){
        return super.executeQuery("select * from t_book where bookName like ? or bookName like ?"
                ,bookname+"%","%"+bookname+"%");
    }

}
