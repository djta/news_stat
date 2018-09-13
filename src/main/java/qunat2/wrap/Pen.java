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
        PartDomain handleDomain = null;
        int start = 0;
        int end = 0;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);

            if (partDomain.getPartEnum() != PartEnum.LEVEL) {
                if (penDomains.size() == 0) {//start run 1 times
                    handleDomain = partDomain;
                    penDomains.add(partDomain);
                    start = i;
                    continue;
                }

                if (handleDomain != null && handleDomain.getPartEnum() != partDomain.getPartEnum()) {
                    handleDomain = partDomain;
                    end = i;
                } else if (handleDomain != null && handleDomain.getPartEnum() == partDomain.getPartEnum()) {//和原来的重复
                    if ((handleDomain.getPartEnum() == PartEnum.BOTTOM &&
                            handleDomain.getLow() > partDomain.getLow())
                            || (handleDomain.getPartEnum() == PartEnum.TOP &&
                            handleDomain.getHigh() < partDomain.getHigh())) {
                        penDomains.remove(penDomains.size() - 1);
                        handleDomain = partDomain;
                        penDomains.add(handleDomain);
                    }
                    continue;
                }
                if (handleEnum != null && handleEnum == partDomain.getPartEnum()) {

                }


                if (handleDomain != null && Math.abs(end - start) > 1) {//写入
                    penDomains.add(partDomain);
                    handleEnum = partDomain.getPartEnum();
                    handleDomain = null;
                }
            }


        }
        return penDomains;
    }

}
