package com.Service;

import com.Entity.grade;

import java.util.List;

public interface GradeService {
    void insertGrade(grade g);

    List<grade> queryByuser(int user_id);
}
