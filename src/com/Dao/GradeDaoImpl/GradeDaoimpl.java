package com.Dao.GradeDaoImpl;

import com.Dao.GradeDao;
import com.Entity.grade;
import com.Util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GradeDaoimpl implements GradeDao {
    //链接mysql
    JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void insertGrade(grade g) {
        //1.声明sql，2.调用sql。
        String sql="Insert into  grade (user_id,paper,score,time) Values(?,?,?,now())";
        jdbcTemplate.update(sql,g.getUser_id(),g.getPaper(),g.getScore());
    }

    @Override
    public List<grade> queryByuser(int user_id) {
        //1.声明sql，2.调用sql
        String sql="Select * from grade where user_id=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(grade.class),user_id);
    }
}
