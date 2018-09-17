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

    public static void getSegmentTest(List<PartDomain> partDomains) {
        PartEnum currentTendency=null;
        SegmentFetureDomain segStart = new SegmentFetureDomain();
         int startSegIndex=0;
        for (int i = 9; i < partDomains.size(); i++) {
            PartDomain part1 = partDomains.get(i - 9);
            PartDomain part2 = partDomains.get(i - 8);
            PartDomain part3 = partDomains.get(i - 7);
            PartDomain part4 = partDomains.get(i - 6);
            PartDomain part5 = partDomains.get(i - 5);
            PartDomain part6 = partDomains.get(i - 4);
            PartDomain part7 = partDomains.get(i - 3);
            PartDomain part8 = partDomains.get(i - 2);
            PartDomain part9 = partDomains.get(i - 1);
            PartDomain part10 = partDomains.get(i);


                //验证连续3笔（4个点）是否有重叠，
                //判断起始位置
                if(part2.getLow()>part1.getHigh()
                        &&(part3.getHigh()<part2.getLow()&&part3.getLow()>part1.getHigh())
                        &&(part4.getLow()>part2.getHigh())){//向上趋势
                    currentTendency=PartEnum.TOP;
                    segStart.setPartEnum(PartEnum.TOP);
                    segStart.setStart(part1.getLow());
                    startSegIndex=i;
                }else if(part1.getHigh()<part1.getLow()
                        &&(part3.getLow()>part2.getHigh()&&part3.getHigh()<part1.getLow())
                        &&(part4.getHigh()<part2.getLow())){//向下趋势
                    currentTendency=PartEnum.BOTTOM;
                    segStart.setPartEnum(PartEnum.BOTTOM);
                    segStart.setStart(part1.getHigh());
                    startSegIndex=i;
                }

               if(isSegment(currentTendency,partDomains.subList(startSegIndex,i))){//符合线段划分标准
                   //TODO  提取线段特征（标准特征值）


               }




        }
    }

    //判断是否满足线段条件（三线段重合）:partDomains.size>=4;
    public static boolean isSegment(PartEnum partEnum,List<PartDomain> partDomains) {
        if(partDomains.size()<4){
            return false;
        }
        for (int i = 3; i < partDomains.size(); i++) {
            PartDomain part1 = partDomains.get(i - 3);
            PartDomain part2 = partDomains.get(i - 2);
            PartDomain part3 = partDomains.get(i - 1);
            PartDomain part4 = partDomains.get(i);
            if(partEnum==PartEnum.TOP&&(part2.getLow()>part1.getHigh()
                    &&(part3.getHigh()<part2.getLow()&&part3.getLow()>part1.getHigh())
                    &&(part4.getLow()>part2.getHigh()))){
                return true;
            }
            if(partEnum==PartEnum.BOTTOM&&(part1.getHigh()<part1.getLow()
                    &&(part3.getLow()>part2.getHigh()&&part3.getHigh()<part1.getLow())
                    &&(part4.getHigh()<part2.getLow()))){
                return true;
            }
        }
        return false;
    }
      //获取特征，并转化为标准特征
    public static void getSegFeture(PartEnum partEnum,List<PartDomain> partDomains){
        List<SegmentFetureDomain> segmentFetureDomains=new ArrayList<SegmentFetureDomain>();
        SegmentFetureDomain segmentFetureDomain=null;
        for(int i=0;i<partDomains.size();i++){
            if(partEnum==PartEnum.TOP){
                if(i%2==1){
                    segmentFetureDomain=new SegmentFetureDomain();
                    segmentFetureDomain.setStart(partDomains.get(i).getHigh());
                    continue;
                }
                if(i>0){
                    segmentFetureDomain.setEnd(partDomains.get(i).getLow());
                    segmentFetureDomain.setPartEnum(PartEnum.BOTTOM);
                    segmentFetureDomains.add(segmentFetureDomain);
                }
            }else if(partEnum==PartEnum.BOTTOM){
                if(i%2==1){
                    segmentFetureDomain=new SegmentFetureDomain();
                    segmentFetureDomain.setStart(partDomains.get(i).getHigh());
                    continue;
                }
                if(i>0){
                    segmentFetureDomain.setEnd(partDomains.get(i).getLow());
                    segmentFetureDomain.setPartEnum(PartEnum.TOP);
                    segmentFetureDomains.add(segmentFetureDomain);
                }
            }


           }

           //处理标准特征序列



    }

    public static void getStdFeture(List<SegmentFetureDomain> segmentFetureDomains){
        for(int i=1;i<segmentFetureDomains.size();i++){
            SegmentFetureDomain segment1=segmentFetureDomains.get(i-1);
            SegmentFetureDomain segment2=segmentFetureDomains.get(i);
            //TODO 标准特征，1，有缺口，2无缺口，找标准特征序列分型
         if(segment1.getStart()>segment2.getStart()&&segment1.getEnd()>segment2.getEnd()){


         }
        }

    }



}
