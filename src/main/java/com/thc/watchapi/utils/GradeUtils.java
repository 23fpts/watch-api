package com.thc.watchapi.utils;

import javax.security.sasl.SaslClient;
import java.util.List;
import java.util.Map;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.utils
 * @Description:
 *
 * 1.单科标准分：根据原始分x1到xn先算出每门课程的平均分x、标准差s、标准值yi，最后算出单科标准分zi。此时单科标准分为0-1区间，注意考虑s是否为0，yi是否为0情况
 * 2.总评标准分：先根据步骤1中的单科标准分x=zi、当前所有学员所有课程中的单科标准分最小值m、
 * 当前所有学员所有课程中的单科标准分最大值M，通过换算公式换算成百分制，得出单科标准分y。
 * 根据单个学员全部课程的单科标准分y1到yk，及其课程对应的学分T1-Tk，算出这名学员的总评标准分S
 *
 * @date 2020/12/11 7:57 下午
 */
public class GradeUtils {

    /**
     * 计算平均分 yi
     * @param rawScores 分数数组
     * @return 平均分
     */
    public static Double calculateScoreMean(List<Double> rawScores) {
        Double scoreAll = 0.0;
        for (Double score: rawScores) {
            scoreAll += score;
        }
        return scoreAll/ rawScores.size();
    }

    /**
     * 计算标准差 s
     * @param rawScores 原始分数 科目数组
     * @param avgScore 平均分 科目的平均分
     * @return
     */
    public static Double calculateSTD(List<Double> rawScores, Double avgScore) {
        double allSquare = 0.0;
        for (Double rawScore: rawScores) {
            allSquare += (rawScore - avgScore)*(rawScore - avgScore);
        }
        double denominator = (rawScores.size() - 1);
        return Math.sqrt(allSquare/denominator);
    }

    /**
     * 计算标准分 s (单科标准分)
     * @param score xi 科目的分数
     * @param avgScore x平均 科目的平均分
     * @param standardDeviation s 科目的白噢准差
     * @return
     */
    public static Double calculateStandardScore(Double score, Double avgScore, Double standardDeviation){
        // yi = (xi - avgX)/s 如果s=0, yi=0
        double yi = standardDeviation==0 ? 0: (score - avgScore)/standardDeviation;
        double zi = 1/(1+Math.pow(Math.E, -2*yi));
        return zi;
    }

    /**
     *
     * @param minStandardScore 所有学员所有科目成绩标准分最小值m
     * @param maxStandardScore 所有学员所有科目成绩标准分最大值M
     * @param score 某学员某科目的分数x(标准分)
     * @return
     */
    public static Double hundredStandardScore(Double minStandardScore, Double maxStandardScore, Double score) {
        return ((score - minStandardScore)/(maxStandardScore - minStandardScore))*40 + 60;
    }


    /**
     * 总共的加上权重的分数S
     * @param weights 各科目的权重
     * @param scores  各科目分别得分数
     *                注意： 权重数组和分数数组的顺序要对应，比如数学是weights的0，那y0必须是数学的分数
     * @return S
     */
    public static Double generalStandardScore(List<Double> weights, List<Double> scores){
        Double totalT = 0.0;
        for (Double weight: weights) {
            totalT = totalT + weight;
        }
        Double S = 0.0;
        for (int i=0; i< weights.size(); i++){
            S = S + weights.get(i) * scores.get(i);
        }
        S = S/totalT;
        return S;
    }



}
