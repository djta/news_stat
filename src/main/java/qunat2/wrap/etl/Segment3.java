package qunat2.wrap.etl;

import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PenDomain;
import qunat2.wrap.domain.SegmentDomain;

import java.util.ArrayList;
import java.util.List;

public class Segment3 {
    public static void main(String args[]) {

    }

    public static List<SegmentDomain> getSegment(List<PenDomain> penDomainList) {
        int segIndex = 0;
        int tmpIndex = 0;
        List<SegmentDomain> segmentDomains = new ArrayList<SegmentDomain>();

        for (int j = 0; j < penDomainList.size(); j++) {
            penDomainList.get(j).setPenSeq(j);
        }
        while (tmpIndex < penDomainList.size()) {

//            System.out.println("run");
//            System.out.println("tmpIndex:" + tmpIndex);

            List<PenDomain> segPenDomains = penDomainList.subList(tmpIndex, penDomainList.size());
            List<PenDomain> partPenDomains = getPartSegment(segPenDomains);
            if (partPenDomains.size() < 3) {
                break;
            }
            PenDomain firstPen = partPenDomains.get(0);
            PenDomain secondPen = partPenDomains.get(1);
            PenDomain thirdPen = partPenDomains.get(2);
            if (secondPen.getStartPen() > secondPen.getEndPen()) {//向上的线段
                if (secondPen.getStartPen() > firstPen.getStartPen()
                        && secondPen.getStartPen() > thirdPen.getStartPen()
                        && secondPen.getEndPen() > thirdPen.getEndPen()) {//顶分型
                    if (secondPen.getEndPen() < firstPen.getStartPen()) {//无缺口
                        SegmentDomain segmentDomain = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                penDomainList, PartEnum.TOP);
                        segIndex = secondPen.getPenSeq();
//                        tmpIndex = segIndex;
                        if (segmentDomain != null) {
                            segmentDomains.add(segmentDomain);
                        }
                    } else {//有缺口
                        List<PenDomain> gapPenDomains = penDomainList.subList(secondPen.getPenSeq(), penDomainList.size());
                        List<PenDomain> gapPartPenDomains = getPartSegment(gapPenDomains);
                        if (gapPartPenDomains.size() < 3) {
                            continue;
                        }
                        PenDomain firstGapPen = gapPartPenDomains.get(0);
                        PenDomain secondGapPen = gapPartPenDomains.get(1);
                        PenDomain thirdGapPen = gapPartPenDomains.get(2);
                        if (secondGapPen.getStartPen() < thirdGapPen.getStartPen()
                                && secondGapPen.getEndPen() < thirdGapPen.getEndPen()
                                && secondGapPen.getEndPen() > firstGapPen.getStartPen()) {//向上线段，只考虑底分型
                            if (secondGapPen.getEndPen() > firstGapPen.getStartPen()) {//1,2元素无缺口
                                SegmentDomain segmentDomain1 = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                        penDomainList, PartEnum.TOP);
                                if (segmentDomain1 != null) {
                                    segmentDomains.add(segmentDomain1);
                                }
                                segIndex = secondPen.getPenSeq();
                                SegmentDomain segmentDomain2 = getSegmentDomain(segIndex, secondGapPen.getPenSeq(),
                                        penDomainList, PartEnum.BOTTOM);
                                if (segmentDomain2 != null) {
                                    segmentDomains.add(segmentDomain2);
                                }
                                segIndex = secondGapPen.getPenSeq();
//                                tmpIndex = segIndex;

                            } else {//1,2元素有缺口
                                SegmentDomain segmentDomain = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                        penDomainList, PartEnum.TOP);
                                if (segmentDomain != null) {
                                    segmentDomains.add(segmentDomain);
                                }
                                segIndex = secondPen.getPenSeq();
//                                tmpIndex = segIndex;
                            }
                        }


                    }
                }

            } else {//向下的线段
                if (secondPen.getStartPen() < thirdPen.getStartPen()
                        && secondPen.getEndPen() < thirdPen.getEndPen()
                        && secondPen.getStartPen() < firstPen.getStartPen()) {//底分型
                    if (secondPen.getEndPen() > firstPen.getStartPen()) {//无缺口
                        SegmentDomain segmentDomain = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                penDomainList, PartEnum.BOTTOM);
                        segIndex = secondPen.getPenSeq();
//                        tmpIndex = segIndex;
                        if (segmentDomain != null) {
                            segmentDomains.add(segmentDomain);
                        }

                    } else {//有缺口
                        List<PenDomain> gapPenDomains = penDomainList.subList(secondPen.getPenSeq(), penDomainList.size());
                        List<PenDomain> gapPartPenDomains = getPartSegment(gapPenDomains);
                        if (gapPartPenDomains.size() < 3) {
                            continue;
                        }
                        PenDomain firstGapPen = gapPartPenDomains.get(0);
                        PenDomain secondGapPen = gapPartPenDomains.get(1);
                        PenDomain thirdGapPen = gapPartPenDomains.get(2);
                        if (secondGapPen.getStartPen() > thirdGapPen.getStartPen()
                                && secondGapPen.getEndPen() > thirdGapPen.getEndPen()
                                && secondGapPen.getStartPen() > firstGapPen.getStartPen()) {//向下线段，考虑第二分型-顶分型
                            if (secondGapPen.getEndPen() < firstGapPen.getStartPen()) {//1,2元素无缺口
                                SegmentDomain segmentDomain1 = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                        penDomainList, PartEnum.BOTTOM);
                                if (segmentDomain1 != null) {
                                    segmentDomains.add(segmentDomain1);
                                }
                                segIndex = secondPen.getPenSeq();
                                SegmentDomain segmentDomain2 = getSegmentDomain(segIndex, secondGapPen.getPenSeq(),
                                        penDomainList, PartEnum.TOP);
                                if (segmentDomain2 != null) {
                                    segmentDomains.add(segmentDomain2);
                                }
                                segIndex = secondGapPen.getPenSeq();
//                                tmpIndex = segIndex;
                            } else {//1,2元素有缺口
                                SegmentDomain segmentDomain1 = getSegmentDomain(segIndex, secondPen.getPenSeq(),
                                        penDomainList, PartEnum.BOTTOM);
                                if (segmentDomain1 != null) {
                                    segmentDomains.add(segmentDomain1);
                                }
                                segIndex = secondPen.getPenSeq();
//                                tmpIndex = segIndex;
                            }
                        }

                    }

                }

            }
            tmpIndex += 2;
            if (tmpIndex < segIndex) {
                tmpIndex = segIndex;
            }

        }
        return segmentDomains;
    }


    public static SegmentDomain getSegmentDomain(int startSegmentIndex,
                                                 int endSegmentIndex,
                                                 List<PenDomain> penDomainList, PartEnum partEnum) {
//        System.out.println("start:" + startSegmentIndex + ",end:" + endSegmentIndex);
        if (startSegmentIndex == endSegmentIndex) {
            return null;
        }
        SegmentDomain segmentDomain = new SegmentDomain();
        PenDomain startPenDomain = penDomainList.get(startSegmentIndex);
        segmentDomain.setSymbol(startPenDomain.getSymbol());
        segmentDomain.setSegEnum(partEnum);
        segmentDomain.setStartId(startPenDomain.getStartId());
        segmentDomain.setStartSeg(startPenDomain.getStartPen());
        //
        PenDomain endPenDomain = penDomainList.get(endSegmentIndex);
        segmentDomain.setEndId(endPenDomain.getStartId());
        segmentDomain.setEndSeg(endPenDomain.getStartPen());
        System.out.println(segmentDomain);
        return segmentDomain;
    }


    public static List<PenDomain> getPartSegment(List<PenDomain> segPenDomains) {
        //获取特征序列
        List<PenDomain> featureList = Segment2.getFeature(segPenDomains);
        //找疑似分型
        int partIndex = Segment2.segmentSelect(featureList);
        if (partIndex < 0) {
            return new ArrayList<PenDomain>();
        }
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
        PenDomain firstPen = new PenDomain();
        firstPen.setStartPen(firstPenValue);
        List<PenDomain> resultList = new ArrayList<PenDomain>();
        resultList.add(firstPen);
        resultList.add(secondPen);
        resultList.add(thirdPen);
        return resultList;
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