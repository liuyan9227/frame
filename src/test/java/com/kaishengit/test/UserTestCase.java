package com.kaishengit.test;

import com.kaishengit.entity.Product;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.util.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liuyan
 * @date 2018/7/9
 */
public class UserTestCase {

    Logger logger = LoggerFactory.getLogger(UserTestCase.class);

    private SqlSession sqlSession;
    private ProductMapper productMapper;

    @Before
    public void before(){
        // 获取sqlSession, 从myBatisUtils中
        sqlSession = MyBatisUtils.getSqlSession();
        // 动态代理, sqlSession的对象根据接口的class动态来创建动态的实现类
        productMapper = sqlSession.getMapper(ProductMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testSave() throws IOException {
        // 读取mybatis文件
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        // 创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 创建sqlsession对象
        sqlSession = sqlSessionFactory.openSession(true);
        // 封装对象写入sql
        Product product = new Product();
        product.setName("max");
        product.setAge(26);
        // 调用ProductMapper.xml的方法, 将对象传过去
        int res = sqlSession.insert("com.kaishengit.mapper.ProductMapper.save", product);
        logger.debug("int:{}"+ res);

        // 断言是否相同
        // Assert.assertEquals(1, res);
        // 关闭资源
        // sqlSession.close();
    }

    @Test
    public void testFindAll(){
        List<Product> productList = productMapper.findAll();
        for (Product product : productList){
            logger.debug("product:{}", product.toString());
        }
    }

    @Test
    public void testFindNameById(){
        Product product = productMapper.findNameById(1);
        logger.debug("product:{}", product.toString());
    }

    @Test
    public void testDeleteById(){
        int count = productMapper.deleteById(2);
        logger.debug("受影响行数为:", count);
        sqlSession.commit();
    }

    @Test
    public void testUpdate(){
        Product product = new Product();
        product.setId(1);
        product.setName("mini");
        product.setAge(25);
        int count = productMapper.updateById(product);
        sqlSession.commit();
    }

    @Test
    public void findPage(){
        int start = 0;
        int total = 2;
        List<Product> productList = productMapper.findPage(start,total);
        for (Product res : productList){
            logger.debug("res:{}", res);
        }
    }

    @Test
    public void findPageByMap(){
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("total", 2);
        List<Product> productList = productMapper.findPageByMap(params);
        for (Product res : productList){
            logger.debug("user:{}", res.toString());
        }
        sqlSession.commit();
    }

}
