package qunat2.wrap;

import qunat2.wrap.domain.PartDomain;

import java.util.List;

public class Pen {
    public static void main(String args[]) {

    }

    public static void getPen(List<PartDomain> partDomainList) {
        PartEnum partEnum=null;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (partDomain.getPartEnum() == PartEnum.TOP || partDomain.getPartEnum() == PartEnum.BOTTOM) {
                partEnum=partDomain.getPartEnum();
            }
        }
    }

}
