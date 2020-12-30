package com.thc.watchapi.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thc.watchapi.config.listener.GradeExcelListener;
import com.thc.watchapi.dto.Grade2Dto;
import com.thc.watchapi.dto.Grade2StudentDto;
import com.thc.watchapi.dto.GradeDto;
import com.thc.watchapi.dto.GradeStudentDto;
import com.thc.watchapi.mapper.grade.*;
import com.thc.watchapi.mapper.GdGroupMapper;
import com.thc.watchapi.model.excel.GradeExcelData;
import com.thc.watchapi.model.grade.GdGrade;
import com.thc.watchapi.model.grade.GdStudent;
import com.thc.watchapi.model.grade.GdSubject;
import com.thc.watchapi.model.grade2.BctStudentInfo;
import com.thc.watchapi.model.grade2.BizWListAcademic;
import com.thc.watchapi.utils.GradeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
 * bct_student_info 学生信息表     根据字段（GRADE_-年级、COLLEGE_-学院）筛选
 *
 * biz_w_list_academic  学业成绩表  根据字段（EXAM_TIME_-考试时间）筛选
 *
 * 接口根据以上字段关联两表筛选查询出学生总评标准分，最后根据 STU_NO_ 筛选出单个学生的结果返回给前端。
 *
 * @date 2020/12/11 7:57 下午
 */
@Service
public class GradeService {

    @Autowired
    private GdGradeMapper gdGradeMapper;

    @Autowired
    private GdStudentMapper gdStudentMapper;

    @Autowired
    private GdSubjectMapper gdSubjectMapper;

    @Autowired
    private GdGroupMapper gdGroupMapper;

    @Autowired
    private GdBctStudentMapper bctStudentMapper;

    @Autowired
    private GdBizWListAcaMapper wListAcaMapper;



    /**
     * 查找所有学生的信息，成绩
     *          0. 先多表查询所有成绩信息进入List<GradeDto>中
     *          1. 查询课程表，然后在grade表中分别查询不同课程id的平均分，赋值到List<GradeDto>中课程id对应的项中
     *          2. 查询课程表，计算所有课程的标准差，存入List<GradeDto>中课程id对应的项中
     *          3. 查询List<GradeDto>，根据标准差s，计算所有课程的标准分zi，存入自己那一项中
     *          4. 查询List<GradeDto>，找到最大的标准分Max和最小的min
     *          5. 查询List<GradeDto>，根据Max和min计算百分制标准分，存入自己那一项中
     *          6. 根据学生id查询List<GradeDto>，算出总评标准分S
     * @return
     */
    public List<GradeStudentDto> queryStudentGrade() {
        // 0. 先多表查询所有成绩信息进入List<GradeDto>中
        List<GradeDto> gradeDtoList = gdGroupMapper.queryInfoByStuIdOrSubId(null, null);
        // 1. 查询课程表，然后在grade表中分别查询不同课程id的平均分，赋值到List<GradeDto>中课程id对应的项中
        List<GdSubject> gdSubjectList = gdSubjectMapper.selectList(null);
        for (GdSubject subject: gdSubjectList) {
            System.out.println("subjectName:"+subject.getName());
            Integer subjectId = subject.getId();
            // 根据subjectId查询grade表，算出平均分
            QueryWrapper<GdGrade> wrapper = new QueryWrapper<>();
            wrapper.eq("subject_id", subjectId);
            List<GdGrade> gdGradeList = gdGradeMapper.selectList(wrapper);
            List<Double> scoreList = gdGradeList.stream().map(item-> item.getGrade()).collect(Collectors.toList());
            scoreList.forEach(System.out::println);
            // avgScore是该科目的平均分
            Double avgScore = GradeUtils.calculateScoreMean(scoreList);
            Double standardDeviation = GradeUtils.calculateSTD(scoreList, avgScore);
            // 然后在gradeDtoList中赋值平均值和标准差，这些和个人没有关系，只和学科有关系
            for (GradeDto gradeDto: gradeDtoList) {
                if (gradeDto.getSubjectId().equals(subjectId)) {
                    // 1. 平均分
                    gradeDto.setAverageScore(avgScore);
                    // 2. 标准差
                    gradeDto.setStandardDeviation(standardDeviation);
                    // 3. 计算单科标准分，利用grade，avgScore, 和standardDeviation
                    gradeDto.setSingleStandardScore(GradeUtils.calculateStandardScore(gradeDto.getGrade(), avgScore, standardDeviation));
                }
            }
            System.out.println("平均分，标准差和单科标准分计算完毕，科目为："+subject.getName());
            gradeDtoList.forEach(System.out::println);
        }
        // 找出所有grade中最大的和最小的标准分
        Double maxStandardScore = 0.0;
        Double minStandardScore = 1.0;
        for (GradeDto gradeDto: gradeDtoList) {
            if (gradeDto.getSingleStandardScore()>maxStandardScore){
                maxStandardScore = gradeDto.getSingleStandardScore();
            }
            if (gradeDto.getSingleStandardScore()<minStandardScore) {
                minStandardScore = gradeDto.getSingleStandardScore();
            }
        }
        System.out.println("max:"+maxStandardScore);
        System.out.println("min:"+minStandardScore);

        for (GradeDto gradeDto: gradeDtoList)  {
            // 5. 查询List<GradeDto>，根据Max和min计算百分制标准分，存入自己那一项中
            gradeDto.setHundredStandardScore(GradeUtils.hundredStandardScore(minStandardScore, maxStandardScore, gradeDto.getSingleStandardScore()));
            System.out.println("name:"+gradeDto.getStudentName());
            System.out.println("subject:"+gradeDto.getSubjectName());
            System.out.println("百分之:"+gradeDto.getHundredStandardScore());
        }
        System.out.println("gradeDtoList");
        gradeDtoList.forEach(System.out::println);
        List<GradeStudentDto> gradeStudentDtoList = new ArrayList<>();
        // 6. 根据学生id查询List<GradeDto>，算出总评标准分S, 并把数据存入List<GradeStudentDto>
        List<GdStudent> studentList = gdStudentMapper.selectList(null);
        for (GdStudent student: studentList) {
            GradeStudentDto gradeStudentDto = new GradeStudentDto();
            List<GradeDto> gradeDtoListForStu = new ArrayList<>();
            gradeStudentDto.setName(student.getName());
            // 计算总评标准分, 用S表示
            // 学分和
            Double totalWeight = 0.0;
            Double S = 0.0;
            for (GradeDto gradeDto: gradeDtoList){
                // 如果学生id是id则加入列表
                if (gradeDto.getStudentId().equals(student.getId())) {
                    if (gradeStudentDto.getMajor()==null) {
                        System.out.println("setMajor:"+gradeDto.getMajor() );
                        gradeStudentDto.setMajor(gradeDto.getMajor());
                    }
                    gradeDtoListForStu.add(gradeDto);
                    totalWeight += gradeDto.getWeight();
                    S = S + gradeDto.getWeight() * gradeDto.getHundredStandardScore();
                }
            }
            System.out.println("totalWeight:"+totalWeight);
            // S 还要除以weight的和
            S = S/totalWeight;
            gradeStudentDto.setScore(gradeDtoListForStu);
            gradeStudentDto.setTotalStandardScore(S);
            gradeStudentDtoList.add(gradeStudentDto);
        }
        System.out.println("result");
        gradeStudentDtoList.forEach(System.out::println);
        return gradeStudentDtoList;
    }

    /**
     * 从excel倒入数据到数据库
     */
    public void importExcelData(MultipartFile multipartFile){
        try {
            gdStudentMapper.deleteAll();
            gdSubjectMapper.deleteAll();
            gdGradeMapper.deleteAll();
            // 文件输入流
            InputStream in = multipartFile.getInputStream();
            // 调用方法读取
            EasyExcel.read(in, GradeExcelData.class, new GradeExcelListener(gdStudentMapper, gdSubjectMapper, gdGradeMapper)).sheet().doRead();

        }catch (Exception e){

        }
    }

    public List<BctStudentInfo> queryBctStu(String grade, String college) {
        QueryWrapper<BctStudentInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(grade)){
            wrapper.eq("GRADE_", grade);
        }
        if (!StringUtils.isEmpty(college)) {
            wrapper.eq("COLLEGE_", college);
        }
        return bctStudentMapper.selectList(wrapper);
    }

    public List<BizWListAcademic> queryAca(String startTime, String endTime) {
        QueryWrapper<BizWListAcademic> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            wrapper.between("EXAM_TIME_", startTime, endTime).orderByAsc("EXAM_TIME_");
        }
        return wListAcaMapper.selectList(wrapper);
    }

    // STU_NO_
    public List<Grade2StudentDto> queryStuDto(String stuNo) {
        // 0. 先多表查询所有成绩信息进入List<GradeDto>中
        // TODO delete
        List<GradeDto> gradeDtoList = gdGroupMapper.queryInfoByStuIdOrSubId(null, null);

        List<BizWListAcademic> academicList = wListAcaMapper.selectList(null);
        // 赋值到grade2dto中法方便计算
        List<Grade2Dto> grade2DtoList = new ArrayList<>();
        for (BizWListAcademic academic: academicList){
            Grade2Dto grade2Dto = new Grade2Dto();
            BeanUtils.copyProperties(academic, grade2Dto);
            grade2DtoList.add(grade2Dto);
        }
        // 生成课程名列表
        List<String> subjectList = new ArrayList<>();
        for (BizWListAcademic academic: academicList) {
            if (!subjectList.contains(academic.getProject())) {
                subjectList.add(academic.getProject());
            }
        }
        // 根据课程遍历
        for (String subject: subjectList) {
            // 根据subjectName查询表格，算出平均分
            QueryWrapper<BizWListAcademic> acaWrapper = new QueryWrapper<>();
            acaWrapper.eq("PROJECT_", subject);
            List<BizWListAcademic> academicList1 = wListAcaMapper.selectList(acaWrapper);
            List<Double> scoreList = academicList1.stream().map(item -> item.getScore()).collect(Collectors.toList());
            // avgScore是该科目的平均分
            Double avgScore = GradeUtils.calculateScoreMean(scoreList);
            // 算标准差
            Double standardDeviation = GradeUtils.calculateSTD(scoreList, avgScore);
            // 在 grade2DtoList 中赋值avgScore 和 standardDeviation
            for (Grade2Dto grade2Dto: grade2DtoList) {
                if (grade2Dto.getProject().equals(subject)) {
                    // 1. 平均分
                    grade2Dto.setAverageScore(avgScore);
                    // 2. 标准差
                    grade2Dto.setStandardDeviation(standardDeviation);
                    // 3. 计算单科标准分，利用grade，avgScore, 和standardDeviation
                    grade2Dto.setSingleStandardScore(GradeUtils.calculateStandardScore(grade2Dto.getScore(), avgScore, standardDeviation));
                }
            }
        }
        // 找出所有grade中最大的和最小的标准分
        Double maxStandardScore = 0.0;
        Double minStandardScore = 1.0;
        for (Grade2Dto grade2Dto: grade2DtoList) {
            if (grade2Dto.getSingleStandardScore()>maxStandardScore) {
                maxStandardScore = grade2Dto.getSingleStandardScore();
            }
            if (grade2Dto.getSingleStandardScore()<minStandardScore) {
                minStandardScore = grade2Dto.getSingleStandardScore();
            }
        }
        for (Grade2Dto grade2Dto: grade2DtoList)  {
            // 5. 查询List<GradeDto>，根据Max和min计算百分制标准分，存入自己那一项中
            grade2Dto.setHundredStandardScore(GradeUtils.hundredStandardScore(minStandardScore, maxStandardScore, grade2Dto.getSingleStandardScore()));
        }
        System.out.println("grade2Dto:");
        for (Grade2Dto grade2Dto: grade2DtoList) {
            System.out.println(grade2Dto.getXf());
        }
        List<Grade2StudentDto> grade2StudentDtoList = new ArrayList<>();
        if (!StringUtils.isEmpty(stuNo)) {
            // 只查询一个学生
            // 6. 根据学生id查询List<GradeDto>，算出总评标准分S, 并把数据存入List<GradeStudentDto>
            List<BctStudentInfo> bctStudentInfoList = bctStudentMapper.selectList(null);
            for (BctStudentInfo studentInfo: bctStudentInfoList) {
                if (studentInfo.getStuNoInStu().equals(stuNo)) {
                    Grade2StudentDto grade2StudentDto = new Grade2StudentDto();
                    // 记录学生的学分
                    List<Grade2Dto> grade2DtoListForStu = new ArrayList<>();
                    // 学生基本信息
                    BeanUtils.copyProperties(studentInfo, grade2StudentDto);
                    Double totalWeight = 0.0;
                    Double S = 0.0;
                    for (Grade2Dto grade2Dto: grade2DtoList) {
                        // 如果stuNO是的就加入列表
                        if (grade2Dto.getStuNoInAca().equals(studentInfo.getStuNoInStu())) {
                            grade2DtoListForStu.add(grade2Dto);
                            totalWeight += grade2Dto.getXf();
                            S = S+grade2Dto.getXf() * grade2Dto.getHundredStandardScore();
                        }
                    }
                    // S 还要除以weight的和
                    S = S/totalWeight;
                    grade2StudentDto.setScores(grade2DtoListForStu);
                    grade2StudentDto.setTotalStandardScore(S);
                    grade2StudentDtoList.add(grade2StudentDto);
                }
            }
            return  grade2StudentDtoList;
        }
        // 6. 根据学生id查询List<GradeDto>，算出总评标准分S, 并把数据存入List<GradeStudentDto>
        List<BctStudentInfo> bctStudentInfoList = bctStudentMapper.selectList(null);
        for (BctStudentInfo studentInfo: bctStudentInfoList) {
            Grade2StudentDto grade2StudentDto = new Grade2StudentDto();
            // 记录学生的学分
            List<Grade2Dto> grade2DtoListForStu = new ArrayList<>();
            // 学生基本信息
            BeanUtils.copyProperties(studentInfo, grade2StudentDto);
            Double totalWeight = 0.0;
            Double S = 0.0;
            for (Grade2Dto grade2Dto: grade2DtoList) {
                // 如果stuNO是的就加入列表
                if (grade2Dto.getStuNoInAca().equals(studentInfo.getStuNoInStu())) {
                    grade2DtoListForStu.add(grade2Dto);
                    Double xf = grade2Dto.getXf()==null? 0.0: grade2Dto.getXf();
                    totalWeight += xf;
                    S = S+xf * grade2Dto.getHundredStandardScore();
                }
            }
            // S 还要除以weight的和
            S = S/totalWeight;
            grade2StudentDto.setScores(grade2DtoListForStu);
            grade2StudentDto.setTotalStandardScore(S);
            grade2StudentDtoList.add(grade2StudentDto);

        }
        return  grade2StudentDtoList;
    }
}
