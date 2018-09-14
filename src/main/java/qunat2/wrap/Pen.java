package qunat2.wrap;

import qunat2.wrap.domain.PartDomain;

import java.util.ArrayList;
import java.util.List;

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
        PartDomain topDomain = null;
        int topCount = 0;
        PartDomain bottomDomain = null;
        int bottomCount = 0;
        List<PartDomain> penDomains = new ArrayList<PartDomain>();
        PartEnum handleEnum = null;
        int count1 = 0;
        int count2 = 0;
        PartDomain partDomain1 = null;
        PartDomain partDomain2 = null;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);

            if (partDomain.getPartEnum() != PartEnum.LEVEL) {
                if (partDomain1 == null) {//start run 1 times
                    partDomain1 = partDomain;
                    count1 = i;
                    continue;
                }

                if (partDomain1.getPartEnum() != partDomain.getPartEnum()) {//不重复
                    if (Math.abs(count1 - i) > 1) {//匹配成功
                        penDomains.add(partDomain1);
                        partDomain1 = null;
                    }
                } else if (partDomain1.getPartEnum() == partDomain.getPartEnum()) {//和原来的重复
//                    if ((partDomain1.getPartEnum() == PartEnum.BOTTOM &&
//                            partDomain1.getLow() > partDomain.getLow())
//                            || (partDomain1.getPartEnum() == PartEnum.TOP &&
//                            partDomain1.getHigh() < partDomain.getHigh())) {
                    penDomains.remove(penDomains.size() - 1);
                    partDomain1 = partDomain;
                    penDomains.add(partDomain1);
                }
                continue;
            }
            if (handleEnum != null && handleEnum == partDomain.getPartEnum()) {

            }


            if (partDomain1 != null && Math.abs(count1 - count2) > 1) {//写入
                penDomains.add(partDomain);
                handleEnum = partDomain.getPartEnum();
                partDomain1 = null;
            }
        }


        return penDomains;
    }

}
