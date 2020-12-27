package com.thc.watchapi.mapper.grade;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thc.watchapi.model.grade.GdStudent;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.mapper.grade
 * @Description:
 * @date 2020/12/12 4:25 下午
 */
@Repository
public interface GdStudentMapper extends BaseMapper<GdStudent> {

    // 清空数据库
    @Update("delete from gd_student")
    void deleteAll();
}
