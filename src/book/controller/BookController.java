package book.controller;

import book.domain.Book;
import book.service.BookServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookServiceImpl bookService = null;

    public String index(HttpSession session){
        List<Book> bookList = bookService.get_book_list();
        session.setAttribute("bookList",bookList);

        return "index";
    }

    public String search(String bookname,HttpSession session){
        //String bookname = (String)session.getAttribute("value");
        System.out.println("search has been invoked");
        System.out.println(bookname);
        List<Book> bookList = bookService.getBookByName(bookname);
        session.setAttribute("searchbooklist",bookList);
        return "search/search";
    }
}
