package com.kaishengit.mapper;

import com.kaishengit.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/11
 */
public interface BookMapper {

    List<Book> finBookByName(@Param("name") String name);
    List<Book> findBookByNameAndName(Map<String, Object> map);
    List<Book> findBookByManyId(@Param("list") List<Integer> list);

}
