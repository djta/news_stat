package qunat2.wrap.etl;

import qunat2.wrap.domain.PenDomain;

import java.util.List;

public class Segment3 {
    public static void main(String args[]) {

    }

    public static void getSegment(List<PenDomain> penDomainList) {
        int startIndex = 0;
        for (int i = 0; i < penDomainList.size(); i++) {
            List<PenDomain> segPenDomains = penDomainList.subList(0, startIndex);
            //获取特征序列
            List<PenDomain> featureList = Segment2.getFeature(segPenDomains);
            //找疑似分型
            int partIndex = Segment2.segmentSelect(featureList);
            List<PenDomain> leftPenDomainList = featureList.subList(0, partIndex);
            List<PenDomain> rightPenDomainList = featureList.subList(partIndex, featureList.size());
            //对分型两边分别做包含处理
            List<PenDomain> leftStdFeatureList = Segment2.getStdFeature(leftPenDomainList);
            List<PenDomain> rightStdFeatureList = Segment2.getStdFeature(rightPenDomainList);
            //获取分型第二元素
            PenDomain secondPen = rightStdFeatureList.get(0);
            //TODO 判断第三元素不存在（还未走完）
            PenDomain thirdPen = rightStdFeatureList.get(1);
            //对左边的标准特征序列，顶分型：找最高的特征序列作为第一元素；底分型：找最低的特征序列作为第一元素
            double firstPenValue = getExtremumPenDomain(leftStdFeatureList);
            /*
                判断分型第一，第二种情况
             */
            if (secondPen.getStartPen() > secondPen.getEndPen()) {//向上的线段
                if (secondPen.getStartPen() > thirdPen.getStartPen()
                        && secondPen.getEndPen() > thirdPen.getEndPen()) {//分型成立
                    if (secondPen.getEndPen() < firstPenValue) {//第一、二元素无缺口
                        //线段生成

                    } else {//有缺口


                    }


                }


            } else {//向下的线段

            }


        }
    }

    //获取极值
    private static double getExtremumPenDomain(List<PenDomain> penDomainList) {
        PenDomain penDomain = penDomainList.get(0);
        double extremumValue = 0;
        if (penDomain.getStartPen() > penDomain.getEndPen()) {//向上的线段
            double maxValue = Double.MIN_VALUE;
            for (PenDomain penDomain1 : penDomainList) {
                if (maxValue < penDomain1.getStartPen()) {
                    maxValue = penDomain1.getStartPen();
                }
            }
            extremumValue = maxValue;

        } else {//向下的线段
            double minValue = Double.MAX_VALUE;
            for (PenDomain penDomain1 : penDomainList) {
                if (minValue > penDomain1.getStartPen()) {
                    minValue = penDomain1.getStartPen();
                }
            }
            extremumValue = minValue;
        }
        return extremumValue;

    }


}