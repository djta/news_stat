package qunat2.wrap.etl;

import domain.MarketDomain;
import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.*;

import java.util.ArrayList;
import java.util.List;


/*
    1、标准特征分型
    2、底分型和顶分型
    3、区分分型1,2元素是否包含gap

 */
public class Segment {

    public static void main(String args[]) {

    }

    public static List<SegmentDomain> getSegment(List<PenDomain> penDomainlist) {
//        penDomainlist = penDomainlist.subList(1, penDomainlist.size());
        for (int i = 0; i < penDomainlist.size(); i++) {
            penDomainlist.get(i).setPenSeq(i);//添加笔序号
        }
        int startSegIndex = 0;
        boolean isContainSeg = false;

        List<SegmentDomain> segmentDomainList = new ArrayList<SegmentDomain>();
        for (PenDomain penDomain : penDomainlist) {
            System.out.println(penDomain);
        }

        List<PenDomain> sdtDomainList = getStdFeature(penDomainlist);
        System.out.println("sdtDomainList.size:" + sdtDomainList.size());
        for (PenDomain penDomain : sdtDomainList) {
            System.out.println(penDomain);
        }

        for (int i = 3; i < penDomainlist.size(); i++) {
//            System.out.println(penDomainlist.get(i));
            if (!isContainSeg) {//验证是否满足线段条件
                isContainSeg = isSegment(penDomainlist.subList(startSegIndex, i));
            }
            if (!isContainSeg) {
                continue;
            }

            List<PenDomain> subPenList = penDomainlist.subList(startSegIndex, i);
            if (subPenList.size() <= 3) {
                continue;
            }
            List<PenDomain> stdFeatureList = getStdFeature(subPenList);

            //标准特征序列第一，第二元素无缺口(向上顶分型，向下底分型)
            FeaturePartDomain featurePartDomain = isFeaturePart(stdFeatureList);
            if (featurePartDomain.isPart() && !featurePartDomain.isGap()) {//有分型，1,2元素无缺口
                SegmentDomain segmentDomain = new SegmentDomain();
                PenDomain startPen = penDomainlist.get(startSegIndex);
                PenDomain endPen = penDomainlist.get(featurePartDomain.getPartIndex());
                segmentDomain.setSymbol(startPen.getSymbol());
                segmentDomain.setSegEnum(featurePartDomain.getSegmentEnum());
                segmentDomain.setStartId(startPen.getStartId());
                segmentDomain.setStartSeg(startPen.getStartPen());
                segmentDomain.setEndId(endPen.getEndId());
                segmentDomain.setEndSeg(endPen.getEndPen());
                isContainSeg = false;
                startSegIndex = featurePartDomain.getPartIndex();
                segmentDomainList.add(segmentDomain);
                System.out.println("not gap");

            } else if (featurePartDomain.isPart() && featurePartDomain.isGap()) {
                //标准特征序列第一，第二有缺口，需要判断是否分型（向上线段，底分型；向下线段，顶分型）
                List segment2 = penDomainlist.subList(featurePartDomain.getPartIndex(), featurePartDomain.getPartIndex() + 8);
                FeaturePartDomain featurePart2 = isFeaturePart(getStdFeature(segment2));
                if (featurePart2.isPart()) {
                    SegmentDomain segmentDomain = new SegmentDomain();
                    PenDomain startPen = penDomainlist.get(startSegIndex);
                    PenDomain endPen = penDomainlist.get(featurePartDomain.getPartIndex());
                    segmentDomain.setSymbol(startPen.getSymbol());
                    segmentDomain.setSegEnum(featurePartDomain.getSegmentEnum());
                    segmentDomain.setStartId(startPen.getStartId());
                    segmentDomain.setStartSeg(startPen.getStartPen());
                    segmentDomain.setEndId(endPen.getEndId());
                    segmentDomain.setEndSeg(endPen.getEndPen());
                    startSegIndex = featurePartDomain.getPartIndex();
                    segmentDomainList.add(segmentDomain);
                    isContainSeg = false;
                }
                System.out.println("gap");
            }


        }
        //test
        for (SegmentDomain segmentDomain : segmentDomainList) {
            System.out.println(segmentDomain);
        }
        return segmentDomainList;

    }

    //是否成线段判断标准：连续的三笔之间有重叠
    public static boolean isSegment(List<PenDomain> penDomainList) {
        if (penDomainList.size() < 2) {
            return false;
        }
        PenDomain pen1 = penDomainList.get(penDomainList.size() - 3);
        PenDomain pen2 = penDomainList.get(penDomainList.size() - 2);
        PenDomain pen3 = penDomainList.get(penDomainList.size() - 1);

        //向上的一笔
        if (pen1.getEndPen() > pen1.getStartPen() && pen2.getEndPen() <= pen1.getStartPen() && pen3.getEndPen() >= pen1.getStartPen()) {
            return true;

        }
        if (pen1.getEndPen() > pen1.getStartPen() && pen2.getEndPen() >= pen1.getStartPen() && pen2.getEndPen() <= pen1.getEndPen()) {
            return true;
        }
        //向下的一笔
        if (pen1.getStartPen() > pen1.getEndPen() && pen2.getEndPen() >= pen1.getStartPen() && pen3.getEndPen() <= pen1.getStartPen()) {
            return true;
        }
        if (pen1.getStartPen() > pen1.getEndPen() && pen2.getEndPen() <= pen1.getStartPen() && pen2.getEndPen() >= pen1.getEndPen()) {
            return true;
        }

        return false;
    }


    //判断分型的第一，第二元素是否有缺口
    public static boolean isGap(PenDomain feature1, PenDomain feature2) {
        if (feature1.getPenEnum() == PartEnum.BOTTOM &&
                feature2.getEndPen() > feature1.getStartPen()) {//向上的线段
            return true;
        } else if (feature1.getPenEnum() == PartEnum.TOP && feature2.getEndPen() < feature1.getStartPen()) {//向下的线段
            return true;
        }
        return false;

    }


    //标准特征序列判断分型
    public static FeaturePartDomain isFeaturePart(List<PenDomain> penDomainList) {
        FeaturePartDomain featurePartDomain = new FeaturePartDomain();
        if (penDomainList.size() < 3) {
            featurePartDomain.setPart(false);
            return featurePartDomain;
        }

        PenDomain feature1 = penDomainList.get(penDomainList.size() - 3);
        PenDomain feature2 = penDomainList.get(penDomainList.size() - 2);
        PenDomain feature3 = penDomainList.get(penDomainList.size() - 1);

        featurePartDomain.setSymbol(feature1.getSymbol());
        if (feature1.getPenEnum() == PartEnum.BOTTOM) {//向上的线段。顶分型
            if (feature2.getStartPen() > feature1.getStartPen()
                    && feature2.getStartPen() > feature3.getStartPen()
                    && feature2.getEndPen() > feature1.getEndPen()
                    && feature2.getEndPen() > feature3.getEndPen()) {
                featurePartDomain.setSegmentEnum(PartEnum.TOP);
                featurePartDomain.setPartEnum(PartEnum.TOP);
                featurePartDomain.setPartIndex(feature2.getPenSeq());
                featurePartDomain.setGap(isGap(feature1, feature2));
                featurePartDomain.setPart(true);
                return featurePartDomain;
            }
//            else if (feature2.getStartPen() < feature1.getStartPen()
//                    && feature2.getStartPen() < feature3.getStartPen()
//                    && feature2.getEndPen() < feature1.getEndPen()
//                    && feature2.getEndPen() < feature3.getEndPen()) {//向上线段，底分型
//                featurePartDomain.setSegmentEnum(PartEnum.TOP);
//                featurePartDomain.setPartEnum(PartEnum.BOTTOM);
//                featurePartDomain.setPartIndex(feature2.getPenSeq());
//                featurePartDomain.setGap(isGap(feature1, feature2));
//                featurePartDomain.setPart(true);
//                return featurePartDomain;
//            }
        } else if (feature1.getPenEnum() == PartEnum.TOP) {//向下的线段。底分型
            if (feature2.getStartPen() < feature1.getStartPen()
                    && feature2.getStartPen() < feature3.getStartPen()
                    && feature2.getEndPen() < feature1.getEndPen()
                    && feature2.getEndPen() < feature3.getEndPen()) {
                featurePartDomain.setSegmentEnum(PartEnum.BOTTOM);
                featurePartDomain.setPartEnum(PartEnum.BOTTOM);
                featurePartDomain.setPartIndex(feature2.getPenSeq());
                featurePartDomain.setGap(isGap(feature1, feature2));
                featurePartDomain.setPart(true);
                return featurePartDomain;
            }
//            else if (feature2.getStartPen() > feature3.getStartPen()
//                    && feature2.getStartPen() > feature1.getStartPen()
//                    && feature2.getEndPen() > feature3.getEndPen()
//                    && feature2.getEndPen() > feature1.getEndPen()) {//向下的线段，顶分型
//                featurePartDomain.setSegmentEnum(PartEnum.BOTTOM);
//                featurePartDomain.setPartEnum(PartEnum.TOP);
//                featurePartDomain.setPartIndex(feature2.getPenSeq());
//                featurePartDomain.setGap(isGap(feature1, feature2));
//                featurePartDomain.setPart(true);
//                return featurePartDomain;
//
//            }
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
        //feature contains
        List<PenDomain> stdFeatureList = new ArrayList<PenDomain>();
        stdFeatureList.add(featureList.get(0));
        stdFeatureList.add(featureList.get(1));
        PenDomain fistFeature = featureList.get(0);
        //处理包含关系，可能有出错的地方。分型右边的不需要处理包含关系
        for (int i = 1; i < featureList.size(); i++) {
            PenDomain feature2 = featureList.get(i - 1);
            PenDomain feature3 = featureList.get(i);
            PenDomain stdFeatureDomain = new PenDomain();
            stdFeatureDomain.setSymbol(feature2.getSymbol());
            stdFeatureDomain.setPenEnum(feature2.getPenEnum());
            if (fistFeature.getStartPen() > fistFeature.getEndPen()) {//向上线段的特征序列
                if (feature2.getStartPen() > feature3.getStartPen()
                        && feature2.getEndPen() < feature3.getEndPen()) {//feature2 contains feature3
                    stdFeatureDomain.setStartPen(feature2.getStartPen());
                    stdFeatureDomain.setStartId(feature2.getStartId());
                    stdFeatureDomain.setPenSeq(feature2.getPenSeq());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                    stdFeatureDomain.setEndId(feature3.getEndId());
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


