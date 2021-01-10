package com.example.BookManager.controllers;

import com.example.BookManager.model.Book;
import com.example.BookManager.model.User;
import com.example.BookManager.service.BookService;
import com.example.BookManager.service.HostHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.net.ssl.HostnameVerifier;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private HostHolder hostHolder;

    //the annotation is used to map web requests to Spring Controller methods.
    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadAllBooksView(model);
        return "book/books";
        //templates
    }

    //requestMethod enum
    @RequestMapping(path = {"/books/add"},method = {RequestMethod.GET})
    public String addBook(){
        return "book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"},method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("author") String author) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setAuthor(author);
            bookService.addBooks(book);
            return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"},method={RequestMethod.GET})
    public String deleteBook(@PathVariable("bookId") int bookId){
        bookService.deleteBooks(bookId);
        return "redirect:/index";
        //back to the origin book url
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"},method = {RequestMethod.GET})
    public String recoverBook(@PathVariable("bookId") int bookId){
        bookService.recoverBooks(bookId);
        return "redirect:/index";
    }

    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
