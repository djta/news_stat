package qunat2.wrap.etl;

import qunat2.wrap.domain.FeaturePartDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.List;

public class Segment2 {
    public static void main(String args[]) {
        System.out.println("test");
    }

    //获取特征序列顶底分型
    public static void getSegmentPart(List<PenDomain> penDomainList) {
        //判断顶分型标准：第二元素最高，且第三元素跌破第二元素最低点。
        //判断底分型标准：第二元素最低，且第三元素升破第二元素最高点。
        FeaturePartDomain featurePartDomain = new FeaturePartDomain();
        if (penDomainList.size() < 3) {
            featurePartDomain.setPart(false);
//           return featurePartDomain;
        }
    }

}
