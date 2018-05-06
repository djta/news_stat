package quant.PatternRecognition;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import domain.MarketDomain;
import quant.PatternRecognition.domain.PRResultDomain;
import quant.constant.TendencySign;
import util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/*
     https://zhuanlan.zhihu.com/p/24883961

 */
public abstract class PatternRecognitionUnit {
    public static Core core = new Core();
    int lookback = 0;
    static MInteger begin = new MInteger();
    static MInteger length = new MInteger();

    public abstract TendencySign getTendencySign(List<MarketDomain> marketDomainList);

    public static List<PRResultDomain> patternRecognition(List<MarketDomain> marketDomainList) {
        int size = marketDomainList.size();
        double inOpen[] = new double[size];
        double inHigh[] = new double[size];
        double inLow[] = new double[size];
        double inClose[] = new double[size];
        List<PRResultDomain> prResultDomains = new ArrayList<PRResultDomain>();
        for (int i = 0; i < size; i++) {
            MarketDomain md = marketDomainList.get(i);
            PRResultDomain prResultDomain = new PRResultDomain();
            inOpen[i] = md.getOpen();
            inHigh[i] = md.getHigh();
            inLow[i] = md.getLow();
            inClose[i] = md.getClose();
            prResultDomain.setDateStr(DateUtil.ts2DateStr(String.valueOf(md.getId())));
            prResultDomain.setSymbol(md.getSymbol());
            prResultDomains.add(prResultDomain);
        }
        int[] output = new int[size];

//        名称：Two Crows 两只乌鸦
//        简介：三日K线模式，第一天长阳，第二天高开收阴，第三天再次高开继续收阴，收盘比前一日收盘价低，预示股价下跌。
        core.cdl2Crows(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL2CROWS(PRSign.DOWN2);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL2CROWS(PRSign.UP2);
            }
        }
//        名称：Three Black Crows 三只乌鸦
//        简介：三日K线模式，连续三根阴线，每日收盘价都下跌且接近最低价，每日开盘价都在上根K线实体内，预示股价下跌。
        core.cdl3BlackCrows(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3BLACKCROWS(PRSign.DOWN2);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3BLACKCROWS(PRSign.UP2);
            }
        }
//        函数名：CDL3INSIDE
//        名称：Three Inside Up/Down 三内部上涨和下跌
//        简介：三日K线模式，母子信号+长K线，以三内部上涨为例，K线为阴阳阳，第三天收盘价高于第一天开盘价，第二天K线在第一天K线内部，预示着股价上涨。
        core.cdl3Inside(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3INSIDE(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3INSIDE(PRSign.DOWN1);
            }
        }
//        函数名：CDL3LINESTRIKE
//        名称：Three-Line Strike 三线打击
//        简介：四日K线模式，前三根阳线，每日收盘价都比前一日高，开盘价在前一日实体内，第四日市场高开，收盘价低于第一日开盘价，预示股价下跌。
        core.cdl3LineStrike(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3LINESTRIKE(PRSign.DOWN2);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3LINESTRIKE(PRSign.UP2);
            }

        }
//        函数名：CDL3OUTSIDE
//        名称：Three Outside Up/Down 三外部上涨和下跌
//        简介：三日K线模式，与三内部上涨和下跌类似，K线为阴阳阳，但第一日与第二日的K线形态相反，以三外部上涨为例，第一日K线在第二日K线内部，预示着股价上涨。
        core.cdl3Outside(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3OUTSIDE(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3OUTSIDE(PRSign.DOWN1);
            }

        }
//        函数名：CDL3STARSINSOUTH
//        名称：Three Stars In The South 南方三星
//        简介：三日K线模式，与大敌当前相反，三日K线皆阴，第一日有长下影线，
// 第二日与第一日类似，K线整体小于第一日，第三日无下影线实体信号，成交价格都在第一日振幅之内，预示下跌趋势反转，股价上升。
        core.cdl3StarsInSouth(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3STARSINSOUTH(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3STARSINSOUTH(PRSign.DOWN1);
            }

        }
//        函数名：CDL3WHITESOLDIERS
//        名称：Three Advancing White Soldiers 三个白兵
//        简介：三日K线模式，三日K线皆阳，每日收盘价变高且接近最高价，开盘价在前一日实体上半部，预示股价上升。
        core.cdl3WhiteSoldiers(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDL3WHITESOLDIERS(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDL3WHITESOLDIERS(PRSign.DOWN1);
            }

        }
//        函数名：CDLABANDONEDBABY
//        名称：Abandoned Baby 弃婴
//        简介：三日K线模式，第二日价格跳空且收十字星（开盘价与收盘价接近，最高价最低价相差不大），预示趋势反转，发生在顶部下跌，底部上涨。
        core.cdlAbandonedBaby(0, size - 1, inOpen, inHigh, inLow, inClose, 0, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDLABANDONEDBABY(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDLABANDONEDBABY(PRSign.DOWN1);
            }

        }
//        函数名：CDLADVANCEBLOCK
//        名称：Advance Block 大敌当前
//        简介：三日K线模式，三日都收阳，每日收盘价都比前一日高，开盘价都在前一日实体以内，实体变短，上影线变长。
        core.cdlAdvanceBlock(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDLADVANCEBLOCK(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDLADVANCEBLOCK(PRSign.DOWN1);
            }

        }

//        函数名：CDLTRISTAR
//        名称：Tristar Pattern 三星
//        简介：三日K线模式，由三个十字组成，第二日十字必须高于或者低于第一日和第三日，预示着反转。

        core.cdlTristar(0, size - 1, inOpen, inHigh, inLow, inClose, begin, length, output);
        for (int i = 0; i < output.length; i++) {
            int value = output[i];
            if (value == 100) {
                prResultDomains.get(i).setCDLTRISTAR(PRSign.UP1);
            } else if (value == -100) {
                prResultDomains.get(i).setCDLTRISTAR(PRSign.DOWN1);
            }

        }


        return prResultDomains;

    }
}
