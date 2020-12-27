package com.thc.watchapi.config.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.mapper.grade.GdGradeMapper;
import com.thc.watchapi.mapper.grade.GdStudentMapper;
import com.thc.watchapi.mapper.grade.GdSubjectMapper;
import com.thc.watchapi.model.Student;
import com.thc.watchapi.model.excel.GradeExcelData;
import com.thc.watchapi.model.grade.GdGrade;
import com.thc.watchapi.model.grade.GdStudent;
import com.thc.watchapi.model.grade.GdSubject;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.config.listener
 * @Description: grade的excel操作
 * @date 2020/12/26 2:21 下午
 */
public class GradeExcelListener extends AnalysisEventListener<GradeExcelData> {

    // 因为GradeExcelListener不能交给spring管理，需要自己new，不能诸如其他对象
    // 需要使用这三个mapper，需要手动new
    public GdStudentMapper studentMapper;
    public GdSubjectMapper subjectMapper;
    public GdGradeMapper gradeMapper;

    public GradeExcelListener() {

    }

    public GradeExcelListener(GdStudentMapper gdStudentMapper, GdSubjectMapper gdSubjectMapper, GdGradeMapper gdGradeMapper) {
        this.studentMapper = gdStudentMapper;
        this.subjectMapper = gdSubjectMapper;
        this.gradeMapper = gdGradeMapper;
    }

    /**
     * 一行一行的处理, 操作存入数据库
     * @param gradeExcelData excel对象
     * @param analysisContext 不知道
     *
     */
    @Override
    public void invoke(GradeExcelData gradeExcelData, AnalysisContext analysisContext) {
        System.out.println(gradeExcelData);
        // GradeExcelData(stuNumber=37552019020, name=李鑫洁, subject=电子技术基础, score=92.0, weight=3.0, bjmc=2019-755)
        //                String                 String      String              Double      Double      String
        // 0. 先清空grade表(在service中调用一次)
        // 每条数据处理
        // 1. 判断学号在学生表中是否存在，不存在则存入，并取得student_id, 存在则直接取得student_id
        QueryWrapper<GdStudent> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("stu_number", gradeExcelData.getStuNumber());
        GdStudent student = studentMapper.selectOne(studentQueryWrapper);
        Integer studentId = null;
        if (student==null) {
            GdStudent gdStudent = new GdStudent();
            gdStudent.setName(gradeExcelData.getName());
            gdStudent.setStuNumber(gradeExcelData.getStuNumber());
            if (studentMapper.insert(gdStudent) != 0) {//如果返回值不是1就插入成功了
                studentId = gdStudent.getId();
            }
        } else {
            studentId = student.getId();
        }
        // 2. 判断科目是否存在，存在则存入，存入科目名称和学分，并取得subject_id, 存在则直接取得subject_id
        QueryWrapper<GdSubject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("name", gradeExcelData.getSubject());
        GdSubject subject = subjectMapper.selectOne(subjectQueryWrapper);
        Integer subjectId = null;
        if (subject==null) {
            GdSubject gdSubject = new GdSubject();
            gdSubject.setName(gradeExcelData.getSubject());
            gdSubject.setWeight(gradeExcelData.getWeight());
            if (subjectMapper.insert(gdSubject) != 0) {
                subjectId = gdSubject.getId();
            }
        } else {
            subjectId = subject.getId();
        }
        // 3. 存入grade数据
        GdGrade grade = new GdGrade();
        grade.setGrade(gradeExcelData.getScore());
        grade.setStudentId(studentId);
        grade.setSubjectId(subjectId);
        gradeMapper.insert(grade);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
