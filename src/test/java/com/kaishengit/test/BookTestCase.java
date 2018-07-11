package com.kaishengit.test;

import com.kaishengit.entity.Book;
import com.kaishengit.mapper.BookMapper;
import com.kaishengit.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/11
 */
public class BookTestCase {

    Logger logger = LoggerFactory.getLogger(BookTestCase.class);

    private SqlSession sqlSession;
    private BookMapper bookMapper;

    @Before
    public void before(){
        sqlSession = MyBatisUtils.getSqlSession(true);
        bookMapper = sqlSession.getMapper(BookMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testFindNameByName(){
        List<Book> bookList = bookMapper.finBookByName("%ja%");
        for (Book book : bookList){
            logger.debug("book:{}", book);
        }
    }

    @Test
    public void testFindBookByNameAndName(){
        Map<String, Object> bookMap = new HashMap<>();
        // bookMap.put("first", "%java%");
        bookMap.put("second", "%S%");
        List<Book> bookList = bookMapper.findBookByNameAndName(bookMap);
        for (Book book : bookList){
            logger.debug("book:{}", book);
        }
    }

    @Test
    public void findBookByManyId(){
        List<Integer> list = Arrays.asList(4,16,23,26);
        List<Book> bookList = bookMapper.findBookByManyId(list);
        for (Book book : bookList){
            logger.debug("book:{}", book);
        }
    }
}
