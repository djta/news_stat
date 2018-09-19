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
        return penDomainList;

    }


    public static void exchangeValue(PartEtlDomain tmp, PartDomain partDomain, boolean isContainsLevel, List<PartEtlDomain> partEtlDomains) {
        if (partDomain.getPartEnum() == PartEnum.TOP) {//顶分型
            if (tmp.getPartEnum() == PartEnum.TOP) {//向上一笔
                if (isContainsLevel && tmp.getValue() < partDomain.getLow()) {
                    PartEtlDomain partEtlDomain = new PartEtlDomain();
                    partEtlDomain.setPartEnum(PartEnum.BOTTOM);//接着的笔向下
                    partEtlDomain.setValue(partDomain.getHigh());
                    partEtlDomain.setId(partDomain.getId());
                    partEtlDomains.add(partEtlDomain);
                    isContainsLevel = false;
                }
            } else if (tmp.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                if (tmp.getValue() < partDomain.getHigh()) {
                    tmp.setValue(partDomain.getHigh());
                    tmp.setId(partDomain.getId());
                }

            }

        } else if (partDomain.getPartEnum() == PartEnum.BOTTOM) {//底分型
            if (tmp.getPartEnum() == PartEnum.TOP) {//向上一笔

                if (tmp.getValue() > partDomain.getLow()) {
                    tmp.setValue(partDomain.getLow());
                    tmp.setId(partDomain.getId());
                }
            } else if (tmp.getPartEnum() == PartEnum.BOTTOM) {//向下一笔
                if (isContainsLevel && tmp.getValue() > partDomain.getHigh()) {
                    PartEtlDomain partEtlDomain = new PartEtlDomain();
                    partEtlDomain.setPartEnum(PartEnum.TOP);//接着的笔向上
                    partEtlDomain.setValue(partDomain.getLow());
                    partEtlDomain.setId(partDomain.getId());
                    partEtlDomains.add(partEtlDomain);
                    isContainsLevel = false;
                }

            }

        } else {
            isContainsLevel = true;
        }
    }


    public static void getPenTest(List<PartDomain> partDomainList) {
        List<PenDomain> penDomainList = new ArrayList<PenDomain>();
        PenDomain penDomain = null;
        PenDomain penDomain1 = null;
        boolean isContainsLevel = false;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            PartEnum partEnum = partDomain.getPartEnum();
            if (partEnum == PartEnum.BOTTOM) {
                if (penDomain == null) {//初始状态
                    penDomain = new PenDomain();
                    penDomain.setStart(true);
                    penDomain.setStartPen(partDomain.getLow());
                    penDomain.setStartId(partDomain.getId());
                    penDomain.setSymbol(partDomain.getSymbol());
                    penDomain.setPenEnum(PartEnum.TOP);
                } else if (penDomain.getPenEnum() == PartEnum.TOP) {//比较start
                    if (penDomain.getStartPen() > partDomain.getLow()) {
                        penDomain.setStartPen(partDomain.getLow());
                        penDomain.setStartId(partDomain.getId());
                    }
                } else if (penDomain.getPenEnum() == PartEnum.BOTTOM) {
                    if ((!penDomain.isEnd()) && isContainsLevel
                            && penDomain.getStartPen() > partDomain.getHigh()) {//可能要放开条件
                        penDomain.setEndPen(partDomain.getLow());
                        penDomain.setEndId(partDomain.getId());
                        penDomain.setEnd(true);
                        isContainsLevel = false;

                    } else if (penDomain.isEnd()) {
                        if (penDomain.getEndPen() > partDomain.getLow()) {
                            penDomain.setEndPen(partDomain.getLow());
                            penDomain.setEndId(partDomain.getId());

                        }
                    }
                }
                //penDomain1
                if (penDomain1 != null && penDomain1.getPenEnum() == PartEnum.TOP) {

                }


            } else if (partEnum == PartEnum.TOP) {
                if (penDomain == null) {//初始状态
                    penDomain = new PenDomain();
                    penDomain.setStart(true);
                    penDomain.setStartPen(partDomain.getHigh());
                    penDomain.setStartId(partDomain.getId());
                    penDomain.setSymbol(partDomain.getSymbol());
                    penDomain.setPenEnum(PartEnum.BOTTOM);
                } else if (penDomain.getPenEnum() == PartEnum.BOTTOM) {//比较start
                    if (penDomain.getStartPen() > partDomain.getHigh()) {
                        penDomain.setStartPen(partDomain.getHigh());
                        penDomain.setStartId(partDomain.getId());
                    }
                } else if (penDomain.getPenEnum() == PartEnum.TOP) {
                    if ((!penDomain.isEnd()) && isContainsLevel
                            && penDomain.getStartPen() < partDomain.getLow()) {
                        penDomain.setEndPen(partDomain.getHigh());
                        penDomain.setEndId(partDomain.getId());
                        penDomain.setEnd(true);
                        isContainsLevel = false;
                    } else if (penDomain.isEnd()) {
                        if (penDomain.getEndPen() > partDomain.getHigh()) {
                            penDomain.setEndPen(partDomain.getHigh());
                            penDomain.setEndId(partDomain.getId());
                        }
                    }
                }
            } else {
                if (penDomain != null) {
                    isContainsLevel = true;
                }
            }

            if (penDomain != null && penDomain.isEnd()) {
                penDomainList.add(penDomain);
                penDomain1 = new PenDomain();
                penDomain1.setStartPen(penDomain.getEndPen());
                penDomain1.setStartId(penDomain.getEndId());
                penDomain1.setSymbol(penDomain.getSymbol());
                if (penDomain.getPenEnum() == PartEnum.TOP) {
                    penDomain1.setPenEnum(PartEnum.BOTTOM);
                } else if (penDomain.getPenEnum() == PartEnum.BOTTOM) {
                    penDomain1.setPenEnum(PartEnum.TOP);
                }
                isContainsLevel = false;

            }
        }

    }

    public static void getPenTest3(List<PartDomain> partDomainList) {
        boolean isFinishinit = false;
        PenDomain penDomain1 = null;
        boolean isContainsLevel = false;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (!isFinishinit) {//初始化第一笔的start
                if (partDomain.getPartEnum() != PartEnum.LEVEL) {
                    penDomain1 = initPen(partDomain);
                    isFinishinit = true;
                }
                continue;
            }
            PartEnum partEnum1 = penDomain1.getPenEnum();
            if (partEnum1 == PartEnum.BOTTOM) {
                if (partDomain.getPartEnum() == PartEnum.BOTTOM) {
                    if (penDomain1.isEnd() && (!penDomain1.isFinish())
                            && penDomain1.getEndPen() > partDomain.getLow()) {
                        penDomain1.setEndPen(partDomain.getLow());
                        penDomain1.setEndId(partDomain.getId());
                    } else if ((!penDomain1.isEnd()) && isContainsLevel) {
                        penDomain1.setEndPen(partDomain.getLow());
                        penDomain1.setEndId(partDomain.getId());
                    }

                } else if (partDomain.getPartEnum() == PartEnum.TOP) {

                } else {
                    isContainsLevel = true;
                }

            }

        }

    }


    public static void getPenTest2(List<PartDomain> partDomainList) {
        boolean isFinishinit = false;
        PenDomain penDomain1 = null;
        boolean isContainsLevel = false;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (!isFinishinit) {//初始化第一笔的start
                if (partDomain.getPartEnum() != PartEnum.LEVEL) {
                    penDomain1 = initPen(partDomain);
                    isFinishinit = true;
                }
                continue;
            }
            PartEnum partEnum1 = penDomain1.getPenEnum();
            if (partDomain.getPartEnum() == PartEnum.BOTTOM) {
                if (partEnum1 == PartEnum.BOTTOM) {
                    if ((penDomain1.isEnd() && penDomain1.getEndPen() > partDomain.getLow()) ||
                            ((penDomain1.isEnd()) && isContainsLevel)) {
                        penDomain1.setEndId(partDomain.getId());
                        penDomain1.setEndPen(partDomain.getLow());
                    } else if ((!penDomain1.isEnd()) && isContainsLevel
                            && penDomain1.getStartPen() > partDomain.getHigh()) {
                        penDomain1.setEndId(partDomain.getId());
                        penDomain1.setEndPen(partDomain.getLow());
                        penDomain1.setEnd(true);
                        isContainsLevel = false;
                    }
                } else if (partEnum1 == PartEnum.TOP) {
                    if (penDomain1.isStart() && penDomain1.getStartPen() < partDomain.getHigh()) {
                        penDomain1.setStartId(partDomain.getId());
                        penDomain1.setEndPen(partDomain.getHigh());
                    }
                }

            } else if (partDomain.getPartEnum() == PartEnum.TOP) {
                if (partEnum1 == PartEnum.BOTTOM) {
                    if (penDomain1.isStart() && penDomain1.getStartPen() < partDomain.getHigh()) {
                        penDomain1.setStartId(partDomain.getId());
                        penDomain1.setEndPen(partDomain.getHigh());
                    }
                } else if (partEnum1 == PartEnum.TOP) {
                    if (penDomain1.isEnd() && penDomain1.getEndPen() < partDomain.getHigh()) {
                        penDomain1.setEndPen(partDomain.getHigh());
                        penDomain1.setEndId(partDomain.getId());
                    } else if ((!penDomain1.isEnd()) && isContainsLevel
                            && penDomain1.getStartPen() < partDomain.getLow()) {
                        penDomain1.setEnd(true);
                        isContainsLevel = false;
                        penDomain1.setEndId(partDomain.getId());
                        penDomain1.setEndPen(partDomain.getHigh());
                    }

                }

            } else {
                isContainsLevel = true;
            }


        }
    }


    public static PenDomain initPen(PartDomain partDomain) {
        PartEnum partEnum = partDomain.getPartEnum();
        PenDomain penDomain = new PenDomain();
        penDomain.setStart(true);
        penDomain.setStartId(partDomain.getId());
        penDomain.setSymbol(partDomain.getSymbol());
        if (partEnum == PartEnum.BOTTOM) {
            penDomain.setStartPen(partDomain.getLow());
            penDomain.setPenEnum(PartEnum.TOP);
        } else if (partEnum == PartEnum.TOP) {
            penDomain.setStartPen(partDomain.getHigh());
            penDomain.setPenEnum(PartEnum.BOTTOM);
        }

        return penDomain;
    }


    public static void getPen(List<PartDomain> partDomainList) {
        List<PenDomain> penDomainList = new ArrayList<PenDomain>();
        boolean isContainLevel = false;
        PenDomain penDomain1 = null;//
        PenDomain penDomain2 = null;
        for (int i = 0; i < partDomainList.size(); i++) {
            PartDomain partDomain = partDomainList.get(i);
            if (partDomain.getPartEnum() == PartEnum.BOTTOM) {
                if (penDomain1 == null) {
                    penDomain1 = new PenDomain();
                    penDomain1.setStartId(partDomain.getId());
                    penDomain1.setStartPen(partDomain.getLow());
                    penDomain1.setSymbol(partDomain.getSymbol());
                    penDomain1.setPenEnum(PartEnum.TOP);
                    penDomain1.setStart(true);
                } else if ((penDomain1.isStart() && penDomain1.getPenEnum() == PartEnum.TOP)
                        || (penDomain1.isEnd() && penDomain1.getPenEnum() == PartEnum.BOTTOM)) {//
                    if (partDomain.getLow() < penDomain1.getStartPen()) {
                        penDomain1.setStartPen(partDomain.getLow());
                        penDomain1.setStartId(partDomain.getId());
                    }
                } else if ((!penDomain1.isEnd()) && penDomain1.getPenEnum() == PartEnum.BOTTOM) {
                    penDomain1.setEnd(true);
                    penDomain1.setEndId(partDomain.getId());
                    penDomain1.setEndPen(partDomain.getLow());
                    //
                    penDomain2 = new PenDomain();
                    penDomain2.setStartId(partDomain.getId());
                    penDomain2.setStartPen(partDomain.getLow());
                    penDomain2.setStart(true);

                }

                if (penDomain1.isFinish()) {
                    penDomainList.add(penDomain1);
                }
            }


        }


    }
}
