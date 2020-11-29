package com.Dao;

import com.Entity.grade;

import java.util.List;

public interface GradeDao {
    void insertGrade(grade g);

    List<grade> queryByuser(int user_id);
}
