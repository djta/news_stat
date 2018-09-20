package qunat2.wrap.etl;

import qunat2.wrap.domain.PenDomain;

import java.util.List;

public class Segment {
    public static void main(String args[]) {

    }

    public static void getSegment(List<PenDomain> penDomainlist) {

        for (int i = 0; i < penDomainlist.size(); i++) {
            PenDomain penDomain = penDomainlist.get(i);

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


}
