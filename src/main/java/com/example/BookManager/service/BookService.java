package com.example.BookManager.service;

import com.example.BookManager.model.Book;
import com.example.BookManager.model.enums.BookStatusEnum;
import java.util.List;

import com.example.BookManager.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    /*
        在BookService.java中，首先持有一个bookDAO的对象，这个对象由Spring自动帮你注入（@Autowired），
        你不用亲自去实例化，Spring已经很聪明的帮你实例化了。你需要的是将bookDAO的方法“包装”
        一下，供上层的类去调用
     */
    private BookDAO bookDAO;

    public List<Book> getAllBooks(){
        return bookDAO.selectAll();
    }
    public Book getBook(int id){
        return bookDAO.selectBookById(id);
    }
    public Book getBook(String name){
        return bookDAO.selectBookByName(name);
    }

    public int addBooks(Book book){
        return bookDAO.addBook(book);
    }
    public void deleteBooks(int id){
        bookDAO.updateBookStatus(id,BookStatusEnum.DELETE.getValue());
    }
    public void recoverBooks(int id){
        bookDAO.updateBookStatus(id,BookStatusEnum.NORMAL.getValue());
    }

}
