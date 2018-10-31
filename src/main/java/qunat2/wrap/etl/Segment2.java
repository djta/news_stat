package qunat2.wrap.etl;

import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PenDomain;
import qunat2.wrap.domain.SegmentDomain;

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


    //根据疑似左右分型，进行数据包含处理
    public static List<SegmentDomain> segmentPart(List<PenDomain> penDomainList) {

        int startIndex = 0;//线段索引
        int selectIndex = 0;
        List<SegmentDomain> segmentDomainList = new ArrayList<SegmentDomain>();
        for (int j = 0; j < penDomainList.size(); j++) {
            penDomainList.get(j).setPenSeq(j);
        }
        for (int i = 0; i < penDomainList.size(); i++) {
            List<PenDomain> subList = penDomainList.subList(selectIndex, i);
            if (subList.size() < 3 || Segment.isSegment(subList)) {
                continue;
            }
//            for (int j = 0; j < subList.size(); j++) {
//                subList.get(j).setPenSeq(j);
//            }
            List<PenDomain> featureList = getFeature(subList);
            int returnIndex = segmentSelect(featureList);
            if (returnIndex < 0) {
                continue;
            }

            List<PenDomain> leftFeatureList = featureList.subList(0, returnIndex);
            List<PenDomain> rightFeatureList = featureList.subList(returnIndex, featureList.size());
            //分型左边数据提取标准化特征
            List<PenDomain> leftStdFeatureList = getStdFeature(leftFeatureList);
            //分型右边数据提取标准化特征
            List<PenDomain> rightStdFeatureList = getStdFeature(rightFeatureList);
            //
            int partIndex = leftStdFeatureList.size();
            //构造标准特征序列
            List<PenDomain> stdFeatureList = new ArrayList<PenDomain>();
            stdFeatureList.addAll(leftStdFeatureList);
            stdFeatureList.addAll(rightStdFeatureList);

            //根据标准型特征序列，区分第一，第二种情况
            if (stdFeatureList.size() < 3 || partIndex < 1) {
                continue;
            }

//            System.out.println("subList.size:" + subList.size());
//            System.out.println("partIndex:" + partIndex);
//            System.out.println("leftStdFeatureList:" + leftStdFeatureList.size());
//            System.out.println("selectIndex:" + selectIndex);
//            System.out.println("startIndex:" + startIndex);
//            System.out.println("stdFeatureList.size():" + stdFeatureList.size());
//            System.out.println("rightStdFeatureList.size():" + rightStdFeatureList.size());
            PenDomain firstDomain = stdFeatureList.get(partIndex - 1);
            PenDomain secondDomain = stdFeatureList.get(partIndex);
            PenDomain thirdDomain = stdFeatureList.get(partIndex + 1);
            System.out.println("secondDomain:" + secondDomain);
            if (secondDomain.getStartPen() > secondDomain.getEndPen()) {//向上线段
                if (secondDomain.getStartPen() > firstDomain.getStartPen()
                        && secondDomain.getStartPen() > thirdDomain.getStartPen()
                        && secondDomain.getEndPen() < firstDomain.getStartPen()) {//无缺口
                    //TODO
                    // 可能要区分，第一元素完全包含于第二元素
                    System.out.println("non gap up");
                    SegmentDomain segmentDomain = new SegmentDomain();
                    PenDomain startDomain = penDomainList.get(startIndex);
                    segmentDomain.setStartId(startDomain.getStartId());
                    segmentDomain.setStartSeg(startDomain.getStartPen());
                    segmentDomain.setEndId(secondDomain.getStartId());
                    segmentDomain.setEndSeg(secondDomain.getStartPen());
                    segmentDomain.setSegEnum(PartEnum.TOP);
                    segmentDomain.setSymbol(secondDomain.getSymbol());
                    segmentDomainList.add(segmentDomain);
                    startIndex = secondDomain.getPenSeq();
                    selectIndex = startIndex;
                } else if (secondDomain.getStartPen() > firstDomain.getStartPen()
                        && secondDomain.getStartPen() > thirdDomain.getStartPen()
                        && secondDomain.getEndPen() > firstDomain.getStartPen()) {//有缺口（向上线段考虑底分型）
                    for (int secondIndex = secondDomain.getPenSeq(); secondIndex < penDomainList.size(); secondIndex++) {

                        List<PenDomain> rightPenDomainList = penDomainList.subList(secondDomain.getPenSeq(), secondIndex);
                        List<PenDomain> rightPenStdFeatureList = getStdFeature(getFeature(rightPenDomainList));
                        if (rightPenStdFeatureList.size() < 3) {
                            continue;
                        }
                        PenDomain feature1 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 3);
                        PenDomain feature2 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 2);
                        PenDomain feature3 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 1);
                        if (feature2.getStartPen() < feature1.getStartPen()
                                && feature2.getStartPen() < feature3.getStartPen()
                                && feature2.getEndPen() < feature3.getEndPen()) {
                            SegmentDomain segmentDomain = new SegmentDomain();
                            PenDomain startDomain = penDomainList.get(startIndex);
                            segmentDomain.setStartId(startDomain.getStartId());
                            segmentDomain.setStartSeg(startDomain.getStartPen());
                            segmentDomain.setEndSeg(feature2.getStartPen());
                            segmentDomain.setEndId(feature2.getStartId());
                            segmentDomain.setSymbol(feature2.getSymbol());
                            segmentDomain.setSegEnum(PartEnum.TOP);
                            segmentDomainList.add(segmentDomain);
                            startIndex = secondDomain.getPenSeq();
                            selectIndex = startIndex;
                        } else {
                            selectIndex += 2;
                            System.out.println("here1");
                        }
                        break;
                    }

                } else {
                    System.out.println("here2");
                    selectIndex += 2;
                }

            } else {//向下的线段

                if (secondDomain.getStartPen() < firstDomain.getStartPen()
                        && secondDomain.getStartPen() < thirdDomain.getStartPen()
                        && secondDomain.getEndPen() > firstDomain.getStartPen()) {//无缺口
                    System.out.println("non gap down");
                    SegmentDomain segmentDomain = new SegmentDomain();
                    PenDomain startDomain = penDomainList.get(startIndex);
                    segmentDomain.setStartId(startDomain.getStartId());
                    segmentDomain.setStartSeg(startDomain.getStartPen());
                    segmentDomain.setEndId(secondDomain.getStartId());
                    segmentDomain.setEndSeg(secondDomain.getStartPen());
                    segmentDomain.setSymbol(secondDomain.getSymbol());
                    segmentDomain.setSegEnum(PartEnum.BOTTOM);
                    segmentDomainList.add(segmentDomain);
                    startIndex = secondDomain.getPenSeq();
                    selectIndex = startIndex;
                } else if (secondDomain.getStartPen() < firstDomain.getStartPen()
                        && secondDomain.getStartPen() < thirdDomain.getStartPen()
                        && secondDomain.getEndPen() < firstDomain.getStartPen()) {//有缺口（向下线段考虑顶分型）
                    for (int secondIndex = secondDomain.getPenSeq(); secondIndex < penDomainList.size(); secondIndex++) {

                        List<PenDomain> rightPenDomainList = penDomainList.subList(secondDomain.getPenSeq(), secondIndex);
                        List<PenDomain> rightPenStdFeatureList = getStdFeature(getFeature(rightPenDomainList));
                        if (rightPenStdFeatureList.size() < 3) {
                            continue;
                        }
                        PenDomain feature1 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 3);
                        PenDomain feature2 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 2);
                        PenDomain feature3 = rightPenStdFeatureList.get(rightPenStdFeatureList.size() - 1);
                        if (feature2.getStartPen() > feature1.getStartPen()
                                && feature2.getStartPen() > feature3.getStartPen()
                                && feature2.getEndPen() < feature3.getEndPen()) {
                            SegmentDomain segmentDomain = new SegmentDomain();
                            PenDomain startDomain = penDomainList.get(startIndex);
                            segmentDomain.setStartId(startDomain.getStartId());
                            segmentDomain.setStartSeg(startDomain.getStartPen());
                            segmentDomain.setEndSeg(feature2.getStartPen());
                            segmentDomain.setEndId(feature2.getStartId());
                            segmentDomain.setSegEnum(PartEnum.BOTTOM);
                            segmentDomain.setSymbol(feature2.getSymbol());
                            segmentDomainList.add(segmentDomain);
                            startIndex = secondDomain.getPenSeq();
                            selectIndex = startIndex;
                        } else {
                            selectIndex += 2;
                            System.out.println("here3");
                        }
                        break;
                    }
                } else {
                    System.out.println("here4");
                    selectIndex += 2;
                }
            }
        }//For END

        return segmentDomainList;
    }


    //疑似顶底分型
    private static int segmentSelect(List<PenDomain> featureList) {
        for (int i = 2; i < featureList.size(); i++) {
            PenDomain leftFeatureDomain = featureList.get(i - 2);
            PenDomain midFeatureDomain = featureList.get(i - 1);
            PenDomain rightFeatureDomain = featureList.get(i);
            if (midFeatureDomain.getStartPen() > midFeatureDomain.getEndPen()) {//向上的线段
                if (midFeatureDomain.getStartPen() > leftFeatureDomain.getStartPen() &&
                        midFeatureDomain.getStartPen() > rightFeatureDomain.getStartPen()) {//只考虑顶分型
                    return i - 1;
                }
            } else {//
                if (midFeatureDomain.getStartPen() < leftFeatureDomain.getStartPen()
                        && midFeatureDomain.getStartPen() < rightFeatureDomain.getStartPen()) {
                    return i - 1;
                }
            }
        }
        return -1;

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


    //获取标准特征序列
    public static List<PenDomain> getStdFeature(List<PenDomain> featureList) {
        if (featureList.size() < 2) {
            return featureList;
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
