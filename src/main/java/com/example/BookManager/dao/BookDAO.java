package com.example.BookManager.dao;

import com.example.BookManager.model.Book;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// option+command+l
@Mapper
public interface BookDAO {
    String table_name = " book ";
    String insert_field = " name, author, price ";
    String select_field = " id,status, " + insert_field;

    @Select({"select",select_field,"from",table_name,"where id=#{id}"})
    Book selectBookById(int id);

    @Select({"select",select_field,"from",table_name,"where name=#{name}"})
    Book selectBookByName(String name);

    @Select({"select", select_field, "from", table_name})
    //select columns
    List<Book> selectAll();
    @Insert({"insert into", table_name,"(", insert_field,
            ") values (#{name},#{author},#{price})"})
    int addBook (Book book);

    @Update({"update", table_name," set status=#{status} where id=#{id}"})
    void updateBookStatus(@Param("id") int id,@Param("status") int status);
}
