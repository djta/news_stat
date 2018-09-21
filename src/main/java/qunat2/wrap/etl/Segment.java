package qunat2.wrap.etl;

import domain.MarketDomain;
import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    public static void main(String args[]) {

    }

    public static void getSegment(List<PenDomain> penDomainlist) {

        int startSegIndex = 0;
        boolean isContainSeg = false;
        for (int i = 3; i < penDomainlist.size(); i++) {
            if (!isContainSeg) {
                isContainSeg = isSegment(penDomainlist.subList(startSegIndex, i));
            }
            if (!isContainSeg) {
                continue;
            }
            PenDomain pen1 = penDomainlist.get(i - 3);
            PenDomain pen2 = penDomainlist.get(i - 2);
            PenDomain pen3 = penDomainlist.get(i - 1);
            PenDomain pen4 = penDomainlist.get(i);
            //第一笔向上
            if (pen1.getPenEnum() == PartEnum.TOP && pen4.getEndPen() < pen1.getEndPen()) {
                isContainSeg = false;
                startSegIndex = i;
            }
            //第一笔向下
            if (pen1.getPenEnum() == PartEnum.BOTTOM && pen4.getEndPen() >= pen1.getEndPen()) {

            }


        }

    }

    //是否成线段判断标准：连续的三笔之间有重叠
    public static boolean isSegment(List<PenDomain> penDomainList) {
        if (penDomainList.size() < 2) {
            return false;
        }
        PenDomain pen1 = penDomainList.get(0);
        PenDomain pen2 = penDomainList.get(1);
        PenDomain pen3 = penDomainList.get(2);

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

    //
    public static List<PenDomain> getFeature(List<PenDomain> penDomainList) {
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
        for (int i = 2; i < featureList.size(); i++) {
            PenDomain feature1 = featureList.get(i - 2);
            PenDomain feature2 = featureList.get(i - 1);
            PenDomain feature3 = featureList.get(i);
            PenDomain stdFeatureDomain = new PenDomain();
            stdFeatureDomain.setSymbol(feature2.getSymbol());
            stdFeatureDomain.setPenEnum(feature2.getPenEnum());
            if (feature1.getStartPen() > feature1.getEndPen()) {//up's feature
                if (feature2.getStartPen() > feature3.getStartPen()
                        && feature2.getEndPen() < feature3.getEndPen()) {//feature2 contains feature3
                    if (feature1.getStartPen() < feature2.getStartPen()) {//up part
                        stdFeatureDomain.setStartPen(feature2.getStartPen());
                        stdFeatureDomain.setEndPen(feature3.getEndPen());

                    } else if (feature1.getStartPen() > feature2.getStartPen()) {//down part
                        stdFeatureDomain.setStartPen(feature3.getStartPen());
                        stdFeatureDomain.setEndPen(feature2.getEndPen());
                    }
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else if (feature3.getStartPen() > feature2.getStartPen()
                        && feature3.getEndPen() < feature2.getStartPen()) {//feature3 contains feature2
                    if (feature1.getStartPen() < feature2.getStartPen()) {//up part
                        stdFeatureDomain.setStartPen(feature3.getStartPen());
                        stdFeatureDomain.setEndPen(feature2.getEndPen());
                    } else if (feature1.getStartPen() < feature2.getStartPen()) {//down part
                        stdFeatureDomain.setStartPen(feature2.getStartPen());
                        stdFeatureDomain.setEndPen(feature3.getEndPen());
                    }
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else {
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                }

            } else {//down's feature
                if (feature2.getEndPen() > feature3.getEndPen()
                        && feature2.getStartPen() < feature3.getStartPen()) {//feature2 contains feature3
                    if (feature2.getEndPen() > feature1.getEndPen()) {//up's part
                        stdFeatureDomain.setEndPen(feature2.getEndPen());
                        stdFeatureDomain.setStartPen(feature3.getStartPen());
                    } else if (feature2.getEndPen() < feature1.getEndPen()) {//down's part
                        stdFeatureDomain.setEndPen(feature3.getEndPen());
                        stdFeatureDomain.setStartPen(feature2.getStartPen());

                    }
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else if (feature3.getEndPen() > feature2.getEndPen()
                        && feature3.getStartPen() < feature2.getStartPen()) {//feature3 cotains feature2
                    if (feature2.getEndPen() > feature1.getEndPen()) {//up's part
                        stdFeatureDomain.setEndPen(feature3.getEndPen());
                        stdFeatureDomain.setStartPen(feature2.getStartPen());
                    } else if (feature2.getEndPen() < feature1.getEndPen()) {   //down's part
                        stdFeatureDomain.setStartPen(feature3.getStartPen());
                        stdFeatureDomain.setEndPen(feature2.getEndPen());
                    }
                    stdFeatureList.remove(stdFeatureList.size() - 1);
                } else {
                    stdFeatureDomain.setStartPen(feature3.getStartPen());
                    stdFeatureDomain.setEndPen(feature3.getEndPen());
                }
            }
            stdFeatureList.add(stdFeatureDomain);

        }
        return stdFeatureList;
    }

}


