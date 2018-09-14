package qunat2.wrap;

import qunat2.wrap.domain.PartDomain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Pen {
    public static void main(String args[]) {

    }

    public static List<PartDomain> getPen(List<PartDomain> partDomainList) {

        PartDomain topDomain = null;
        int topCount = 0;
        PartDomain bottomDomain = null;
        int bottomCount = 0;
        List<PartDomain> penDomains = new ArrayList<PartDomain>();
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (partDomain.getPartEnum() == PartEnum.TOP && (topDomain == null ||
                    (topDomain != null && partDomain.getHigh() > topDomain.getHigh()))) {
                topDomain = partDomain;
                topCount = i;
            } else if (partDomain.getPartEnum() == PartEnum.BOTTOM && (bottomDomain == null ||
                    (bottomDomain != null && partDomain.getLow() < bottomDomain.getLow()))) {
                bottomDomain = partDomain;
                bottomCount = i;
            }

            if (topDomain != null && bottomDomain != null
                    && Math.abs((bottomCount - topCount)) > 1
                    && (topDomain.getHigh() > bottomDomain.getHigh()
                    && topDomain.getLow() > bottomDomain.getLow())
            ) {
                if ((topCount < bottomCount && penDomains.size() == 0)
                        || (penDomains.size() > 0 && penDomains.get(penDomains.size() - 1).getPartEnum() == PartEnum.BOTTOM
                )) {//up
                    penDomains.add(topDomain);
                    topDomain = null;
                } else if ((topCount > bottomCount && penDomains.size() == 0)
                        || (penDomains.size() > 0 && penDomains.get(penDomains.size() - 1).getPartEnum() == PartEnum.TOP
                )) {
                    penDomains.add(bottomDomain);
                    bottomDomain = null;
                }
            }


        }
        return penDomains;
    }


    public static List<PartDomain> getPenTest(List<PartDomain> partDomainList) {

        List<PartDomain> resultDomains = new ArrayList<PartDomain>();
        List<PartDomain> handleList = new ArrayList<PartDomain>();
        List<Integer> handleIndex = new ArrayList<Integer>();
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (partDomain.getPartEnum() == PartEnum.LEVEL) {
                continue;
            }
            if (handleList.size() == 0) {//初始状态
                handleList.add(partDomain);
                handleIndex.add(i);
                continue;
            }
            int size = handleList.size();
            PartDomain partDomainLeft = handleList.get(size - 1);
            int indexLeft = handleIndex.get(size - 1);
            if (partDomainLeft.getPartEnum() == partDomain.getPartEnum()
                    && ((partDomainLeft.getPartEnum() == PartEnum.TOP
                    && partDomainLeft.getHigh() < partDomain.getHigh())
                    || (partDomainLeft.getPartEnum() == PartEnum.BOTTOM
                    && partDomainLeft.getLow() > partDomain.getLow()))
            ) {//同一,只保留最新的。
                handleList.remove(size - 1);
                handleList.add(partDomain);
                handleIndex.remove(size - 1);
                handleIndex.add(i);

            } else if (partDomainLeft.getPartEnum() != partDomain.getPartEnum()
                    && Math.abs(indexLeft - i) > 0) {//不相同
//                if ((partDomainLeft.getPartEnum() == PartEnum.BOTTOM &&
//                        partDomainLeft.getLow() < partDomain.getLow())
//                        || (partDomainLeft.getPartEnum() == PartEnum.TOP &&
//                        partDomainLeft.getHigh() > partDomain.getHigh())) {
                if ((partDomainLeft.getPartEnum() == PartEnum.BOTTOM &&
                        partDomainLeft.getHigh() < partDomain.getLow())
                        || (partDomainLeft.getPartEnum() == PartEnum.TOP &&
                        partDomainLeft.getLow() > partDomain.getHigh())) {
                    handleList.add(partDomain);
                    handleIndex.add(i);
                }

            }
            //查看handleList
            if (handleList.size() > 1) {
                resultDomains.add(handleList.get(0));
                handleList.remove(0);
                handleIndex.remove(0);
            }

        }
        return resultDomains;
    }

}
