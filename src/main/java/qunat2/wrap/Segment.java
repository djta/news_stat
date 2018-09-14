package qunat2.wrap;

import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.SegmentFetureDomain;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    public static void main(String args[]) {

    }

    public static void getSegment(List<PartDomain> partDomainList) {
        PartEnum penStart = null;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            //特征分型-》标准特征分型-》分两类处理：缺口和非缺口
            if (i == 0) {
                penStart = partDomain.getPartEnum();
            }
            if (penStart == PartEnum.TOP) {

            }
            if (penStart == PartEnum.BOTTOM) {

            }
        }
    }


    public static void getFeature(PartEnum partEnum, List<PartDomain> partDomains) {

        PartDomain tempDomain = null;
        List<SegmentFetureDomain> segmentFetureDomains = new ArrayList<SegmentFetureDomain>();
        for (int i = 0; i < partDomains.size(); i++) {
            PartDomain partDomain = partDomains.get(i);

            if (i % 2 == 1) {
                tempDomain = partDomain;
            } else {
                SegmentFetureDomain segmentFetureDomain = new SegmentFetureDomain();
                if (partEnum == PartEnum.TOP) {
                    segmentFetureDomain.setStart(tempDomain.getHigh());
                    segmentFetureDomain.setEnd(partDomain.getLow());
                    segmentFetureDomain.setPartEnum(PartEnum.BOTTOM);
                    segmentFetureDomains.add(segmentFetureDomain);
                }
                if (partEnum == PartEnum.BOTTOM) {
                    segmentFetureDomain.setStart(tempDomain.getLow());
                    segmentFetureDomain.setEnd(partDomain.getHigh());
                    segmentFetureDomain.setPartEnum(PartEnum.TOP);
                    segmentFetureDomains.add(segmentFetureDomain);
                }

            }

        }
        //查找是否重叠，符合1,2标准。


    }
}
