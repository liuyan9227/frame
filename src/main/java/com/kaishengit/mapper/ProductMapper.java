package com.kaishengit.mapper;

import com.kaishengit.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author liuyan
 * @date 2018/7/10
 */
public interface ProductMapper {

    int save(Product product);
    List<Product> findAll();
    Product findNameById(Integer id);
    int deleteById(Integer id);
    int updateById(Product product);
    List<Product> findPage(@Param("start") Integer start, @Param("total") Integer total);
    List<Product> findPageByMap(Map params);

}
