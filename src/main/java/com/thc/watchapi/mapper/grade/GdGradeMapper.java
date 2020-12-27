package com.thc.watchapi.mapper.grade;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thc.watchapi.model.grade.GdGrade;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.mapper.grade
 * @Description:
 * @date 2020/12/12 4:24 下午
 */
@Repository
public interface GdGradeMapper extends BaseMapper<GdGrade> {

    // 清空数据库
    @Update("delete from gd_grade")
    void deleteAll();
}
