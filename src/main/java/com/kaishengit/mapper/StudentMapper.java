package com.kaishengit.mapper;

import com.kaishengit.entity.Student;

import java.util.List;

/**
 * @author liuyan
 * @date 2018/7/10
 */
public interface StudentMapper {

    List<Student> findSchoolByStudentId(Integer id);
    List<Student> findTagByStudent(Integer id);

}
