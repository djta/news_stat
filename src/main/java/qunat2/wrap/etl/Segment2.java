package qunat2.wrap.etl;

import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.FeaturePartDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.ArrayList;
import java.util.List;


/*
     https://wenku.baidu.com/view/7c7ade28192e45361066f56d.html
     https://wenku.baidu.com/view/84b6eadbce2f0066f53322bd.html
     https://wenku.baidu.com/view/38e4bb993c1ec5da51e270bb.html?rec_flag=default
    （1）假设某转折点是两线段的分界点， 
     （2）然后对此用线段划分的两种情况去考察是否满足，
    （3）如果满足其中一种，那么这点就是真正的线段的分界点；     
    （4）如果不满足，那就不是，原来的线段依然延续，就这么简单。
 */

public class Segment2 {
    public static void main(String args[]) {
        System.out.println("test");
    }

    //获取特征序列顶底分型
    public static void getSegmentPart(List<PenDomain> penDomainList) {
        //获取特征序列
        List<PenDomain> featureList = getFeature(penDomainList);
        //找分型
        FeaturePartDomain featurePartDomain = getFeaturePart(featureList);
        if (!featurePartDomain.isPart()) {
            return;
        }
        //获取分型序号
        int partIndex = featurePartDomain.getPartIndex();
        List<PenDomain> partLeftList = penDomainList.subList(0, partIndex);
        List<PenDomain> partRightList = penDomainList.subList(partIndex, penDomainList.size());
        //获取第二元素
        PenDomain feature2 = penDomainList.get(partIndex);
        //分型前后，分别转为标准特征序列
        List<PenDomain> leftStdFeatureList = getStdFeature(partLeftList);
        List<PenDomain> rightStdFeatureList = getStdFeature(partRightList);
        //获取第一元素
        PenDomain feature1 = leftStdFeatureList.get(leftStdFeatureList.size() - 1);
        //获取第三元素

        //判断第一，第二元素是否有缺口

        //对于第二特征元序列的分型判断，必须严格按照包含关系处理来，
        // 这里不存在第一种情况中的假设分界点两边不能进行包含关系处理的要求。


        //标准特征序列分型成立：
        //第一种情况：特征：一、二元素没有缺口，二、三元素没有包含关系
        //第二种情况之一：缺口最终被封闭；第三元素前后元素没有包含关系
        //第二种情况之二：缺口最终没被封闭；第三元素前后元素没有包含关系
        //
    }

    //第一种情况：特征：一、二元素没有缺口，二、三元素没有包含关系
    private static void getSegmentTurn(List<PenDomain> penDomainList) {
        List<PenDomain> featureList = getFeature(penDomainList);
        PenDomain feature1 = featureList.get(featureList.size() - 3);
        PenDomain feature2 = featureList.get(featureList.size() - 2);
        PenDomain feature3 = featureList.get(featureList.size() - 1);
        if (feature1.getStartPen() > feature1.getEndPen()) {//向上的线段
            if (feature2.getStartPen() > feature1.getStartPen()
                    && feature2.getStartPen() > feature3.getStartPen()) {//（只考虑）顶分型
                if (feature1.getStartPen() > feature2.getEndPen()) {//无缺口(即：线段被笔破坏)
                    if (feature3.getEndPen() < feature2.getEndPen()) {//第二元素没有包含第三元素、转折成立
                        // TODO 转折成立
                    } else {//有包含关系
                        if (featureList.get(featureList.size() - 4).getEndPen() < feature2.getEndPen()) {
                            //TODO 转折成立
                        }
                    }

                }


            }
        }


    }

    public static void getSegment1(List<PenDomain> penDomainList) {



    }


    //获取特征序列
    private static List<PenDomain> getFeature(List<PenDomain> penDomainList) {
        List<PenDomain> featureList = new ArrayList<PenDomain>();
        for (int i = 0; i < penDomainList.size(); i++) {
            if (i % 2 == 1) {
                featureList.add(penDomainList.get(i));
            }
        }
        return featureList;
    }

    //获取分型
    private static FeaturePartDomain getFeaturePart(List<PenDomain> penDomainList) {
        FeaturePartDomain featurePartDomain = new FeaturePartDomain();
        if (penDomainList.size() < 3) {
            featurePartDomain.setPart(false);
            return featurePartDomain;
        }

        PenDomain feature1 = penDomainList.get(penDomainList.size() - 3);
        PenDomain feature2 = penDomainList.get(penDomainList.size() - 2);
        PenDomain feature3 = penDomainList.get(penDomainList.size() - 1);

        featurePartDomain.setSymbol(feature1.getSymbol());
        //判断顶分型标准：第二元素最高
        //判断底分型标准：第二元素最低
        if (feature1.getPenEnum() == PartEnum.BOTTOM) {//向上的线段。顶分型
            if (feature2.getStartPen() > feature1.getStartPen()
                    && feature2.getStartPen() > feature3.getStartPen()) {
                //分型后，在转化为标准特征序列

                featurePartDomain.setSegmentEnum(PartEnum.TOP);
                featurePartDomain.setPartEnum(PartEnum.TOP);
                featurePartDomain.setPartIndex(feature2.getPenSeq());
//                featurePartDomain.setGap(isGap(feature1, feature2));
                featurePartDomain.setPart(true);
                return featurePartDomain;
            }

        } else if (feature1.getPenEnum() == PartEnum.TOP) {//向下的线段。底分型
            if (feature2.getStartPen() < feature1.getStartPen()
                    && feature2.getStartPen() < feature3.getStartPen()) {
                featurePartDomain.setSegmentEnum(PartEnum.BOTTOM);
                featurePartDomain.setPartEnum(PartEnum.BOTTOM);
                featurePartDomain.setPartIndex(feature2.getPenSeq());
//                featurePartDomain.setGap(isGap(feature1, feature2));
                featurePartDomain.setPart(true);
                return featurePartDomain;
            }
        }
        featurePartDomain.setPart(false);
        featurePartDomain.setGap(false);
        return featurePartDomain;

    }

    //获取标准特征序列
    public static List<PenDomain> getStdFeature(List<PenDomain> penDomainList) {
        List<PenDomain> featureList = new ArrayList<PenDomain>();
        for (int i = 0; i < penDomainList.size(); i++) {
            if (i % 2 == 1) {
                featureList.add(penDomainList.get(i));
            }
        }
        PenDomain fistFeature = featureList.get(0);
        List<PenDomain> stdFeatureList = new ArrayList<PenDomain>();
        stdFeatureList.add(featureList.get(0));
        stdFeatureList.add(featureList.get(1));
        //处理包含关系，可能有出错的地方。分型右边的不需要处理包含关系
        for (int i = 1; i < featureList.size(); i++) {
            PenDomain feature3 = featureList.get(i);
            PenDomain feature2 = featureList.get(i - 1);
            PenDomain stdFeatureDomain = new PenDomain();
            stdFeatureDomain.setSymbol(feature2.getSymbol());
            stdFeatureDomain.setPenEnum(feature2.getPenEnum());
            if (fistFeature.getStartPen() > fistFeature.getEndPen()) {//向上线段的特征序列
                if (feature2.getStartPen() > feature3.getStartPen()
                        && feature2.getEndPen() < feature3.getEndPen()) {//feature2 contains feature3
                    stdFeatureDomain.setPenSeq(feature2.getPenSeq());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                    stdFeatureDomain.setEndId(feature3.getEndId());
                    stdFeatureDomain.setStartPen(feature2.getStartPen());
                    stdFeatureDomain.setStartId(feature2.getStartId());
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else if (feature3.getStartPen() > feature2.getStartPen()
                        && feature3.getEndPen() < feature2.getStartPen()) {//feature3 contains feature2
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setEndPen(feature2.getEndPen());
                    stdFeatureDomain.setStartId(feature3.getStartId());
                    stdFeatureDomain.setPenSeq(feature3.getPenSeq());
                    stdFeatureDomain.setEndId(feature2.getEndId());
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else {
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                    stdFeatureDomain.setStartId(feature3.getStartId());
                    stdFeatureDomain.setEndId(feature3.getEndId());
                    stdFeatureDomain.setPenSeq(feature3.getPenSeq());
                }

            } else {//向下线段的特征序列
                if (feature2.getEndPen() > feature3.getEndPen()
                        && feature2.getStartPen() < feature3.getStartPen()) {//feature2 contains feature3
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                    stdFeatureDomain.setEndId(feature3.getEndId());
                    stdFeatureDomain.setStartPen(feature2.getStartPen());
                    stdFeatureDomain.setStartId(feature2.getStartId());
                    stdFeatureDomain.setPenSeq(feature2.getPenSeq());
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else if (feature3.getEndPen() > feature2.getEndPen()
                        && feature3.getStartPen() < feature2.getStartPen()) {//feature3 cotains feature2
                    stdFeatureDomain.setPenSeq(feature3.getPenSeq());
                    stdFeatureDomain.setEndPen(feature2.getEndPen());
                    stdFeatureDomain.setEndId(feature2.getEndId());
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setStartId(feature3.getStartId());
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else {
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setStartId(feature3.getStartId());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                    stdFeatureDomain.setEndId(feature3.getEndId());
                    stdFeatureDomain.setPenSeq(feature3.getPenSeq());
                }
            }
            stdFeatureList.add(stdFeatureDomain);

        }
        return stdFeatureList;
    }

}
