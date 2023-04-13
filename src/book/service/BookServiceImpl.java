package book.service;

import book.dao.BookDAOImpl;
import book.domain.Book;

import java.util.List;

public class BookServiceImpl {
    private BookDAOImpl bookDAO = null;
    public List<Book> get_book_list(){
        return bookDAO.get_book_list();
    }

    public Book getBook(Integer id){
        System.out.println("getbook"+id);
        System.out.println(bookDAO.getBook(id));
        return bookDAO.getBook(id);
    }

    public void editInventory(Book book){
        bookDAO.editInventory(book);
    }

    public void editSale(Book book){
        bookDAO.editSale(book);
    }

    public List<Book> getBookByName(String bookname){
        return bookDAO.getBookByName(bookname);
    }
}
