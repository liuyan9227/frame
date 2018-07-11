package com.kaishengit.test;

import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/10
 */
public class StudentTestCase {

    Logger logger = LoggerFactory.getLogger(StudentTestCase.class);
    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    @Before
    public void before(){
        sqlSession = MyBatisUtils.getSqlSession(true);
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testFindSchoolByStudentId(){
        List<Student> students = studentMapper.findSchoolByStudentId(1);
        for (Student stu : students){
            logger.debug("stu:{}", stu);
        }
    }

    @Test
    public void testFindTagByStuId(){
        List<Student> studentList = studentMapper.findTagByStudent(1);
        for (Student stu : studentList){
            logger.debug("stu:{}", stu);
        }
    }


}
