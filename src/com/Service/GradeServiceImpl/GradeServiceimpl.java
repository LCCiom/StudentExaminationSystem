package com.Service.GradeServiceImpl;

import com.Dao.GradeDao;
import com.Dao.GradeDaoImpl.GradeDaoimpl;
import com.Entity.grade;
import com.Service.GradeService;

import java.util.List;

public class GradeServiceimpl implements GradeService {
    //获取Gradedao对象
    GradeDao gradeDao=new GradeDaoimpl();
    @Override
    public void insertGrade(grade g) {
        gradeDao.insertGrade(g);
    }

    @Override
    public List<grade> queryByuser(int user_id) {
       return gradeDao.queryByuser(user_id);
    }
}
