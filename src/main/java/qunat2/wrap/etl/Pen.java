package qunat2.wrap.etl;

import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.ArrayList;
import java.util.List;

public class Pen {
    public static void main(String args[]) {

    }

    public static void getPen(List<PartDomain> partDomainList) {
        List<PenDomain> penDomainList = new ArrayList<PenDomain>();
        boolean isContainLevel = false;
        PenDomain penDomain = null;
        for (int i = 0; i < partDomainList.size(); i++) {

            PartDomain partDomain = partDomainList.get(i);
            if (partDomain.getPartEnum() == PartEnum.BOTTOM) {
                if (penDomain != null) {
                   //


                }
            }

        }


    }
}
