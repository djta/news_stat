package qunat2.wrap.etl;

import qunat2.wrap.PartEnum;
import qunat2.wrap.domain.PartDomain;
import qunat2.wrap.domain.PartEtlDomain;
import qunat2.wrap.domain.PenDomain;

import java.util.ArrayList;
import java.util.List;


public class Pen {
    public static void main(String args[]) {


    }

    public static List<PenDomain> getPenInfo(List<PartDomain> partDomainList) {
        List<PartEtlDomain> partEtlDomains = new ArrayList<PartEtlDomain>();
        List<PenDomain> penDomainList = new ArrayList<PenDomain>();
        boolean isContainsLevel = false;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (partEtlDomains.size() == 0) {//初始化
                if (partDomain.getPartEnum() == PartEnum.TOP) {//顶分型
                    PartEtlDomain partEtlDomain = new PartEtlDomain();
                    partEtlDomain.setId(partDomain.getId());
                    partEtlDomain.setSymbol(partDomain.getSymbol());
                    partEtlDomain.setValue(partDomain.getHigh());
                    partEtlDomain.setPartEnum(PartEnum.BOTTOM);//第一笔向下
                    partEtlDomains.add(partEtlDomain);

                } else if (partDomain.getPartEnum() == PartEnum.BOTTOM) {//底分型
                    PartEtlDomain partEtlDomain = new PartEtlDomain();
                    partEtlDomain.setId(partDomain.getId());
                    partEtlDomain.setSymbol(partDomain.getSymbol());
                    partEtlDomain.setValue(partDomain.getLow());
                    partEtlDomain.setPartEnum(PartEnum.TOP);//第一笔向上
                    partEtlDomains.add(partEtlDomain);
                }

            } else if (partEtlDomains.size() == 1) {
                PartEtlDomain tmp1 = partEtlDomains.get(0);
                if (partDomain.getPartEnum() == PartEnum.TOP) {//顶分型
                    if (tmp1.getPartEnum() == PartEnum.TOP) {//向上一笔
                        if (isContainsLevel && tmp1.getValue() < partDomain.getLow()) {
                            PartEtlDomain partEtlDomain = new PartEtlDomain();
                            partEtlDomain.setPartEnum(PartEnum.BOTTOM);//接着的笔向下
                            partEtlDomain.setValue(partDomain.getHigh());
                            partEtlDomain.setId(partDomain.getId());
                            partEtlDomain.setSymbol(partDomain.getSymbol());
                            partEtlDomains.add(partEtlDomain);
                            isContainsLevel = false;
                        }
                    } else if (tmp1.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                        if (tmp1.getValue() < partDomain.getHigh()) {
                            tmp1.setValue(partDomain.getHigh());
                            tmp1.setId(partDomain.getId());
                        }

                    }

                } else if (partDomain.getPartEnum() == PartEnum.BOTTOM) {//底分型
                    if (tmp1.getPartEnum() == PartEnum.TOP) {//向上一笔

                        if (tmp1.getValue() > partDomain.getLow()) {
                            tmp1.setValue(partDomain.getLow());
                            tmp1.setId(partDomain.getId());
                        }
                    } else if (tmp1.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                        if (isContainsLevel && tmp1.getValue() > partDomain.getHigh()) {
                            PartEtlDomain partEtlDomain = new PartEtlDomain();
                            partEtlDomain.setPartEnum(PartEnum.TOP);//接着的笔向上
                            partEtlDomain.setValue(partDomain.getLow());
                            partEtlDomain.setId(partDomain.getId());
                            partEtlDomain.setSymbol(partDomain.getSymbol());
                            partEtlDomains.add(partEtlDomain);
                            isContainsLevel = false;
                        }
                    }
                } else {
                    isContainsLevel = true;
                }

            } else if (partEtlDomains.size() == 2) {
                PartEtlDomain tmp2 = partEtlDomains.get(1);
                if (partDomain.getPartEnum() == PartEnum.TOP) {//顶分型
                    if (tmp2.getPartEnum() == PartEnum.TOP) {//向上一笔
                        if (isContainsLevel && tmp2.getValue() < partDomain.getLow()) {
                            PartEtlDomain partEtlDomain = new PartEtlDomain();
                            partEtlDomain.setPartEnum(PartEnum.BOTTOM);//接着的笔向下
                            partEtlDomain.setValue(partDomain.getHigh());
                            partEtlDomain.setSymbol(partDomain.getSymbol());
                            partEtlDomain.setId(partDomain.getId());
                            partEtlDomains.add(partEtlDomain);
                            isContainsLevel = false;
                        }
                    } else if (tmp2.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                        if (tmp2.getValue() < partDomain.getHigh()) {
                            tmp2.setValue(partDomain.getHigh());
                            tmp2.setId(partDomain.getId());
                        }

                    }

                } else if (partDomain.getPartEnum() == PartEnum.BOTTOM) {//底分型
                    if (tmp2.getPartEnum() == PartEnum.TOP) {//向上一笔

                        if (tmp2.getValue() > partDomain.getLow()) {
                            tmp2.setValue(partDomain.getLow());
                            tmp2.setId(partDomain.getId());
                        }
                    } else if (tmp2.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                        if (isContainsLevel && tmp2.getValue() > partDomain.getHigh()) {
                            PartEtlDomain partEtlDomain = new PartEtlDomain();
                            partEtlDomain.setPartEnum(PartEnum.TOP);//接着的笔向上
                            partEtlDomain.setValue(partDomain.getLow());
                            partEtlDomain.setSymbol(partDomain.getSymbol());
                            partEtlDomain.setId(partDomain.getId());
                            partEtlDomains.add(partEtlDomain);
                            isContainsLevel = false;
                        }

                    }

                } else {
                    isContainsLevel = true;
                }

            }
            if (partEtlDomains.size() == 3) {
                PenDomain penDomain = new PenDomain();
                PartEtlDomain etl1 = partEtlDomains.get(0);
                PartEtlDomain etl2 = partEtlDomains.get(1);
                penDomain.setStartId(etl1.getId());
                penDomain.setStartPen(etl1.getValue());
                penDomain.setSymbol(etl1.getSymbol());
                penDomain.setPenEnum(etl1.getPartEnum());
                penDomain.setEndId(etl2.getId());
                penDomain.setEndPen(etl2.getValue());
                partEtlDomains.remove(0);
                penDomainList.add(penDomain);
            }

        }//--FOR END
//        for (PartEtlDomain partEtlDomain : partEtlDomains) {
//            System.out.println(partEtlDomain);
//        }

        return penDomainList;

    }

}
