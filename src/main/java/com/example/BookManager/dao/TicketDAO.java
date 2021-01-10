package com.example.BookManager.dao;

import com.example.BookManager.model.Ticket;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface TicketDAO {
    String table_name="ticket";
    String insert_field="user_id,ticket,expired_at";
    String select_field="id,"+insert_field;

//    INSERT INTO table_name ( field1, field2,...fieldN )
//    VALUES
//    ( value1, value2,...valueN );
    @Insert({"insert into",table_name,"(",insert_field,
    ") values (#{userId},#{ticket},#{expiredAt})"})
    int addTicket(Ticket ticket);

    @Select({"select",select_field,"from",table_name,"where user_id=#{uid}"})
    Ticket SelectByUserId(int uid);

    @Select({"select",select_field,"from",table_name,"where ticket=#{ticket}"})
    Ticket SelectByTicket(String ticket);

    @Delete({"delete from",table_name,"where id=#{id}",})
    void deleteById(int id);

    @Delete({"delete from",table_name,"where ticket=#{t}"})
    void deleteByTicket(String t);
}
