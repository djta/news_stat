package qunat2.wrap.etl;

import quant.tendencyStat.otherStat.KlineSlopeStat;
import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.SegmentDomain;

import java.util.ArrayList;
import java.util.List;

public class Pivot {

    public static void main(String args[]) {
        System.out.println("test");
    }

    //https://zhuanlan.zhihu.com/p/24597726
    public static List<PartEnum> getTrend(List<SegmentDomain> segmentDomainList) {
        List<PartEnum> partEnums = new ArrayList<PartEnum>();
        for (int i = 0; i < segmentDomainList.size() - 5; i++) {
            SegmentDomain seg1 = segmentDomainList.get(i);
            SegmentDomain seg5 = segmentDomainList.get(i + 5);
            if (seg1.getStartSeg() > seg1.getEndSeg()) {//向下的线段
                if (seg1.getStartSeg() > seg5.getStartSeg()) {//向下的趋势
//                    System.out.println("down");
                    partEnums.add(PartEnum.BOTTOM);
                } else {
//                    System.out.println("up");
                    partEnums.add(PartEnum.TOP);
                }

            } else {//向上的线段
                if (seg1.getEndSeg() > seg5.getEndSeg()) {
//                    System.out.println("down");
                    partEnums.add(PartEnum.BOTTOM);
                } else {
//                    System.out.println("up");
                    partEnums.add(PartEnum.TOP);
                }

            }
        }
        return partEnums;
    }

    public static void getPivot(List<SegmentDomain> segmentDomainList, List<PartEnum> tendencyList) {
        double dd = 0;
        double gg = 0;
        double zg = 0;
        double zd = 0;
        SegmentDomain seg1 = segmentDomainList.get(0);//构建中枢开始
        for (int i = 0; i < segmentDomainList.size(); i++) {
            SegmentDomain segmentDomain = segmentDomainList.get(i);
            if (i == 0) {//构建中枢开始的一段
                PartEnum startSegmentTendency = tendencyList.get(i);
                if (segmentDomain.getStartSeg() > segmentDomain.getEndSeg()) {//向下的线段
                    if (startSegmentTendency == PartEnum.TOP) {//向上的趋势

                    }

                }
            }
        }
    }
}
