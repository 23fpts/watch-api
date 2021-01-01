package com.thc.watchapi.mapper;

import com.thc.watchapi.dto.GradeDto;
import com.thc.watchapi.mapper.grade.GdBizWListAcaMapper;
import com.thc.watchapi.model.grade2.BizWListAcademic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.mapper.grade
 * @Description:
 * @date 2020/12/12 5:02 下午
 */
@Repository
public interface GdGroupMapper {

    // 根据学生id or 课程id 查询成绩的完整信息
    List<GradeDto> queryInfoByStuIdOrSubId1(@Param("studentId") Integer studentId ,
                                           @Param("subjectId") Integer subjectId);
    @Select("<script> " +
            "        select g.id as gradeId, g.student_id as studentId, g.subject_id as subjectId, g.grade as grade, s.name as studentName, s.major as major, sub.name as subjectName, sub.weight as weight\n" +
            "        from gd_grade g\n" +
            "        left join gd_student s on s.id = g.student_id\n" +
            "        left join gd_subject sub on sub.id = g.subject_id\n" +
            "        <trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\" suffixOverrides=\"AND|OR\">\n" +
            "            <if test=\"studentId !=null and studentId !='' \">\n" +
            "                AND g.student_id = #{studentId}\n" +
            "            </if>\n" +
            "            <if test=\"subjectId !=null and subjectId != '' \" >\n" +
            "                AND g.subject_id = #{subjectId}\n" +
            "            </if>\n" +
            "        </trim>\n" +
            "    </script>")
    List<GradeDto> queryInfoByStuIdOrSubId(@Param("studentId") Integer studentId ,
                        @Param("subjectId") Integer subjectId);


    @Select("<script>   select g.ID_ as acaId, g.CREATE_USER_ as acaCreateUser, g.CREATE_TIME_ as createTime, g.STU_NO_ as stuNoInAca, g.PROJECT_ as project, g.SCORE_ as score, g.EXAM_TIME_ as examTime, g.XF_ as xf \n" +
            " from biz_w_list_academic g\n" +
            " left join bct_student_info s on s.STU_NO_ = g.STU_NO_" +
            "        <trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\" suffixOverrides=\"AND|OR\">\n" +
            "            <if test=\"grade !=null and grade !='' \">\n" +
            "                AND g.GRADE_ = #{grade}\n" +
            "            </if>\n" +
            "            <if test=\"college !=null and college != '' \" >\n" +
            "                AND s.COLLEGE_ = #{college}\n" +
            "            </if>\n" +
            "            <if test=\"startTime !=null and startTime != '' and endTime !=null and endTime != '' \" >\n" +
            "                AND g.EXAM_TIME_ between #{startTime} and #{endTime} \n" +
            "            </if>\n" +
            "        </trim>\n" +
            "</script>")
    // bct 和 biz多表查询
    List<BizWListAcademic> queryAcaByTimeGradeAndCollege(@Param("grade") String grade,
                                                         @Param("college") String college,
                                                         @Param("startTime") String startTime,
                                                         @Param("endTime") String endTime);


}
