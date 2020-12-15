package com.thc.watchapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thc.watchapi.dto.GradeDto;
import com.thc.watchapi.dto.GradeStudentDto;
import com.thc.watchapi.mapper.grade.GdGradeMapper;
import com.thc.watchapi.mapper.GdGroupMapper;
import com.thc.watchapi.mapper.grade.GdStudentMapper;
import com.thc.watchapi.mapper.grade.GdSubjectMapper;
import com.thc.watchapi.model.grade.GdGrade;
import com.thc.watchapi.model.grade.GdStudent;
import com.thc.watchapi.model.grade.GdSubject;
import com.thc.watchapi.utils.GradeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.service
 * @Description:
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
}
