package quant.PatternRecognition.domain;

import quant.PatternRecognition.PRSign;

/*
https://blog.csdn.net/shuigge/article/details/72825723
 */
public class PRResultDomain {
    private String symbol;
    private String dateStr;
    private PRSign CDL2CROWS;//Two Crows 两只乌鸦
    private PRSign CDL3BLACKCROWS;//Three Black Crows 三只乌鸦
    private PRSign CDL3INSIDE;//Three Inside Up/Down 三内部上涨和下跌
    private PRSign CDL3LINESTRIKE;//Three-Line Strike 三线打击 100,down,-100,up
    private PRSign CDL3OUTSIDE;//Three Outside Up/Down 三外部上涨和下跌
    private PRSign CDL3STARSINSOUTH;//Three Stars In The South 南方三星
    private PRSign CDL3WHITESOLDIERS;//Three Advancing White Soldiers 三个白兵
    private PRSign CDLABANDONEDBABY;//Abandoned Baby 弃婴
    private PRSign CDLADVANCEBLOCK;//Advance Block 大敌当前
    private PRSign CDLBELTHOLD;//Belt-hold 捉腰带线
    private PRSign CDLBREAKAWAY;//Breakaway 脱离
    private PRSign CDLCLOSINGMARUBOZU;//Closing Marubozu 收盘缺影线
    private PRSign CDLCONCEALBABYSWALL;//Concealing Baby Swallow 藏婴吞没
    private PRSign CDLCOUNTERATTACK;//Counterattack 反击线
    private PRSign CDLDARKCLOUDCOVER;//Dark Cloud Cover 乌云压顶
    private PRSign CDLDOJI;//Doji 十字
    private PRSign CDLDOJISTAR;//Doji Star 十字星
    private PRSign CDLDRAGONFLYDOJI;//Dragonfly Doji 蜻蜓十字/T形十字
    private PRSign CDLENGULFING;//Engulfing Pattern 吞噬模式
    private PRSign CDLEVENINGDOJISTAR;//Evening Doji Star 十字暮星
    private PRSign CDLEVENINGSTAR;//Evening Star 暮星
    private PRSign CDLGAPSIDESIDEWHITE;//Up/Down-gap side-by-side white lines 向上/下跳空并列阳线
    private PRSign CDLGRAVESTONEDOJI;//Gravestone Doji 墓碑十字/倒T十字
    private PRSign CDLHAMMER;//Hammer 锤头
    private PRSign CDLHANGINGMAN;//Hanging Man 上吊线
    private PRSign CDLHARAMI;//Harami Pattern 母子线
    private PRSign CDLHARAMICROSS;//Harami Cross Pattern 十字孕线
    private PRSign CDLHIGHWAVE;//High-Wave Candle 风高浪大线
    private PRSign CDLHIKKAKE;//Hikkake Pattern 陷阱
    private PRSign CDLHIKKAKEMOD;//Modified Hikkake Pattern 修正陷阱
    private PRSign CDLHOMINGPIGEON;//Homing Pigeon 家鸽
    private PRSign CDLIDENTICAL3CROWS;//Identical Three Crows 三胞胎乌鸦
    private PRSign CDLINNECK;//In-Neck Pattern 颈内线
    private PRSign CDLINVERTEDHAMMER;//Inverted Hammer 倒锤头
    private PRSign CDLKICKING;//Kicking 反冲形态
    private PRSign CDLKICKINGBYLENGTH;//Kicking - bull/bear determined by the longer marubozu 由较长缺影线决定的反冲形态
    private PRSign CDLLADDERBOTTOM;//Ladder Bottom 梯底
    private PRSign CDLLONGLEGGEDDOJI;//Long Legged Doji 长脚十字
    private PRSign CDLLONGLINE;//Long Line Candle 长蜡烛
    private PRSign CDLMARUBOZU;//Marubozu 光头光脚/缺影线
    private PRSign CDLMATCHINGLOW;//Matching Low 相同低价
    private PRSign CDLMATHOLD;//Mat Hold 铺垫
    private PRSign CDLMORNINGDOJISTAR;//Morning Doji Star 十字晨星
    private PRSign CDLMORNINGSTAR;//Morning Star 晨星
    private PRSign CDLONNECK;//On-Neck Pattern 颈上线
    private PRSign CDLPIERCING;//Piercing Pattern 刺透形态
    private PRSign CDLRICKSHAWMAN;//Rickshaw Man 黄包车夫
    private PRSign CDLRISEFALL3METHODS;//Rising/Falling Three Methods 上升/下降三法
    private PRSign CDLSEPARATINGLINES;//Separating Lines 分离线
    private PRSign CDLSHOOTINGSTAR;//Shooting Star 射击之星
    private PRSign CDLSHORTLINE;//Short Line Candle 短蜡烛
    private PRSign CDLSPINNINGTOP;//Spinning Top 纺锤
    private PRSign CDLSTALLEDPATTERN;//Stalled Pattern 停顿形态
    private PRSign CDLSTICKSANDWICH;//Stick Sandwich 条形三明治
    private PRSign CDLTAKURI;//Takuri (Dragonfly Doji with very long lower shadow) 探水竿
    private PRSign CDLTASUKIGAP;//Tasuki Gap 跳空并列阴阳线
    private PRSign CDLTHRUSTING;//Thrusting Pattern 插入
    private PRSign CDLTRISTAR;//Tristar Pattern 三星
    private PRSign CDLUNIQUE3RIVER;//Unique 3 River 奇特三河床
    private PRSign CDLUPSIDEGAP2CROWS;//Upside Gap Two Crows 向上跳空的两只乌鸦
    private PRSign CDLXSIDEGAP3METHODS;//Upside/Downside Gap Three Methods 上升/下降跳空三法


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public PRSign getCDL2CROWS() {
        return CDL2CROWS;
    }

    public void setCDL2CROWS(PRSign CDL2CROWS) {
        this.CDL2CROWS = CDL2CROWS;
    }

    public PRSign getCDL3BLACKCROWS() {
        return CDL3BLACKCROWS;
    }

    public void setCDL3BLACKCROWS(PRSign CDL3BLACKCROWS) {
        this.CDL3BLACKCROWS = CDL3BLACKCROWS;
    }

    public PRSign getCDL3INSIDE() {
        return CDL3INSIDE;
    }

    public void setCDL3INSIDE(PRSign CDL3INSIDE) {
        this.CDL3INSIDE = CDL3INSIDE;
    }

    public PRSign getCDL3LINESTRIKE() {
        return CDL3LINESTRIKE;
    }

    public void setCDL3LINESTRIKE(PRSign CDL3LINESTRIKE) {
        this.CDL3LINESTRIKE = CDL3LINESTRIKE;
    }

    public PRSign getCDL3OUTSIDE() {
        return CDL3OUTSIDE;
    }

    public void setCDL3OUTSIDE(PRSign CDL3OUTSIDE) {
        this.CDL3OUTSIDE = CDL3OUTSIDE;
    }

    public PRSign getCDL3STARSINSOUTH() {
        return CDL3STARSINSOUTH;
    }

    public void setCDL3STARSINSOUTH(PRSign CDL3STARSINSOUTH) {
        this.CDL3STARSINSOUTH = CDL3STARSINSOUTH;
    }

    public PRSign getCDL3WHITESOLDIERS() {
        return CDL3WHITESOLDIERS;
    }

    public void setCDL3WHITESOLDIERS(PRSign CDL3WHITESOLDIERS) {
        this.CDL3WHITESOLDIERS = CDL3WHITESOLDIERS;
    }

    public PRSign getCDLABANDONEDBABY() {
        return CDLABANDONEDBABY;
    }

    public void setCDLABANDONEDBABY(PRSign CDLABANDONEDBABY) {
        this.CDLABANDONEDBABY = CDLABANDONEDBABY;
    }

    public PRSign getCDLADVANCEBLOCK() {
        return CDLADVANCEBLOCK;
    }

    public void setCDLADVANCEBLOCK(PRSign CDLADVANCEBLOCK) {
        this.CDLADVANCEBLOCK = CDLADVANCEBLOCK;
    }

    public PRSign getCDLBELTHOLD() {
        return CDLBELTHOLD;
    }

    public void setCDLBELTHOLD(PRSign CDLBELTHOLD) {
        this.CDLBELTHOLD = CDLBELTHOLD;
    }

    public PRSign getCDLBREAKAWAY() {
        return CDLBREAKAWAY;
    }

    public void setCDLBREAKAWAY(PRSign CDLBREAKAWAY) {
        this.CDLBREAKAWAY = CDLBREAKAWAY;
    }

    public PRSign getCDLCLOSINGMARUBOZU() {
        return CDLCLOSINGMARUBOZU;
    }

    public void setCDLCLOSINGMARUBOZU(PRSign CDLCLOSINGMARUBOZU) {
        this.CDLCLOSINGMARUBOZU = CDLCLOSINGMARUBOZU;
    }

    public PRSign getCDLCONCEALBABYSWALL() {
        return CDLCONCEALBABYSWALL;
    }

    public void setCDLCONCEALBABYSWALL(PRSign CDLCONCEALBABYSWALL) {
        this.CDLCONCEALBABYSWALL = CDLCONCEALBABYSWALL;
    }

    public PRSign getCDLCOUNTERATTACK() {
        return CDLCOUNTERATTACK;
    }

    public void setCDLCOUNTERATTACK(PRSign CDLCOUNTERATTACK) {
        this.CDLCOUNTERATTACK = CDLCOUNTERATTACK;
    }

    public PRSign getCDLDARKCLOUDCOVER() {
        return CDLDARKCLOUDCOVER;
    }

    public void setCDLDARKCLOUDCOVER(PRSign CDLDARKCLOUDCOVER) {
        this.CDLDARKCLOUDCOVER = CDLDARKCLOUDCOVER;
    }

    public PRSign getCDLDOJI() {
        return CDLDOJI;
    }

    public void setCDLDOJI(PRSign CDLDOJI) {
        this.CDLDOJI = CDLDOJI;
    }

    public PRSign getCDLDOJISTAR() {
        return CDLDOJISTAR;
    }

    public void setCDLDOJISTAR(PRSign CDLDOJISTAR) {
        this.CDLDOJISTAR = CDLDOJISTAR;
    }

    public PRSign getCDLDRAGONFLYDOJI() {
        return CDLDRAGONFLYDOJI;
    }

    public void setCDLDRAGONFLYDOJI(PRSign CDLDRAGONFLYDOJI) {
        this.CDLDRAGONFLYDOJI = CDLDRAGONFLYDOJI;
    }

    public PRSign getCDLENGULFING() {
        return CDLENGULFING;
    }

    public void setCDLENGULFING(PRSign CDLENGULFING) {
        this.CDLENGULFING = CDLENGULFING;
    }

    public PRSign getCDLEVENINGDOJISTAR() {
        return CDLEVENINGDOJISTAR;
    }

    public void setCDLEVENINGDOJISTAR(PRSign CDLEVENINGDOJISTAR) {
        this.CDLEVENINGDOJISTAR = CDLEVENINGDOJISTAR;
    }

    public PRSign getCDLEVENINGSTAR() {
        return CDLEVENINGSTAR;
    }

    public void setCDLEVENINGSTAR(PRSign CDLEVENINGSTAR) {
        this.CDLEVENINGSTAR = CDLEVENINGSTAR;
    }

    public PRSign getCDLGAPSIDESIDEWHITE() {
        return CDLGAPSIDESIDEWHITE;
    }

    public void setCDLGAPSIDESIDEWHITE(PRSign CDLGAPSIDESIDEWHITE) {
        this.CDLGAPSIDESIDEWHITE = CDLGAPSIDESIDEWHITE;
    }

    public PRSign getCDLGRAVESTONEDOJI() {
        return CDLGRAVESTONEDOJI;
    }

    public void setCDLGRAVESTONEDOJI(PRSign CDLGRAVESTONEDOJI) {
        this.CDLGRAVESTONEDOJI = CDLGRAVESTONEDOJI;
    }

    public PRSign getCDLHAMMER() {
        return CDLHAMMER;
    }

    public void setCDLHAMMER(PRSign CDLHAMMER) {
        this.CDLHAMMER = CDLHAMMER;
    }

    public PRSign getCDLHANGINGMAN() {
        return CDLHANGINGMAN;
    }

    public void setCDLHANGINGMAN(PRSign CDLHANGINGMAN) {
        this.CDLHANGINGMAN = CDLHANGINGMAN;
    }

    public PRSign getCDLHARAMI() {
        return CDLHARAMI;
    }

    public void setCDLHARAMI(PRSign CDLHARAMI) {
        this.CDLHARAMI = CDLHARAMI;
    }

    public PRSign getCDLHARAMICROSS() {
        return CDLHARAMICROSS;
    }

    public void setCDLHARAMICROSS(PRSign CDLHARAMICROSS) {
        this.CDLHARAMICROSS = CDLHARAMICROSS;
    }

    public PRSign getCDLHIGHWAVE() {
        return CDLHIGHWAVE;
    }

    public void setCDLHIGHWAVE(PRSign CDLHIGHWAVE) {
        this.CDLHIGHWAVE = CDLHIGHWAVE;
    }

    public PRSign getCDLHIKKAKE() {
        return CDLHIKKAKE;
    }

    public void setCDLHIKKAKE(PRSign CDLHIKKAKE) {
        this.CDLHIKKAKE = CDLHIKKAKE;
    }

    public PRSign getCDLHIKKAKEMOD() {
        return CDLHIKKAKEMOD;
    }

    public void setCDLHIKKAKEMOD(PRSign CDLHIKKAKEMOD) {
        this.CDLHIKKAKEMOD = CDLHIKKAKEMOD;
    }

    public PRSign getCDLHOMINGPIGEON() {
        return CDLHOMINGPIGEON;
    }

    public void setCDLHOMINGPIGEON(PRSign CDLHOMINGPIGEON) {
        this.CDLHOMINGPIGEON = CDLHOMINGPIGEON;
    }

    public PRSign getCDLIDENTICAL3CROWS() {
        return CDLIDENTICAL3CROWS;
    }

    public void setCDLIDENTICAL3CROWS(PRSign CDLIDENTICAL3CROWS) {
        this.CDLIDENTICAL3CROWS = CDLIDENTICAL3CROWS;
    }

    public PRSign getCDLINNECK() {
        return CDLINNECK;
    }

    public void setCDLINNECK(PRSign CDLINNECK) {
        this.CDLINNECK = CDLINNECK;
    }

    public PRSign getCDLINVERTEDHAMMER() {
        return CDLINVERTEDHAMMER;
    }

    public void setCDLINVERTEDHAMMER(PRSign CDLINVERTEDHAMMER) {
        this.CDLINVERTEDHAMMER = CDLINVERTEDHAMMER;
    }

    public PRSign getCDLKICKING() {
        return CDLKICKING;
    }

    public void setCDLKICKING(PRSign CDLKICKING) {
        this.CDLKICKING = CDLKICKING;
    }

    public PRSign getCDLKICKINGBYLENGTH() {
        return CDLKICKINGBYLENGTH;
    }

    public void setCDLKICKINGBYLENGTH(PRSign CDLKICKINGBYLENGTH) {
        this.CDLKICKINGBYLENGTH = CDLKICKINGBYLENGTH;
    }

    public PRSign getCDLLADDERBOTTOM() {
        return CDLLADDERBOTTOM;
    }

    public void setCDLLADDERBOTTOM(PRSign CDLLADDERBOTTOM) {
        this.CDLLADDERBOTTOM = CDLLADDERBOTTOM;
    }

    public PRSign getCDLLONGLEGGEDDOJI() {
        return CDLLONGLEGGEDDOJI;
    }

    public void setCDLLONGLEGGEDDOJI(PRSign CDLLONGLEGGEDDOJI) {
        this.CDLLONGLEGGEDDOJI = CDLLONGLEGGEDDOJI;
    }

    public PRSign getCDLLONGLINE() {
        return CDLLONGLINE;
    }

    public void setCDLLONGLINE(PRSign CDLLONGLINE) {
        this.CDLLONGLINE = CDLLONGLINE;
    }

    public PRSign getCDLMARUBOZU() {
        return CDLMARUBOZU;
    }

    public void setCDLMARUBOZU(PRSign CDLMARUBOZU) {
        this.CDLMARUBOZU = CDLMARUBOZU;
    }

    public PRSign getCDLMATCHINGLOW() {
        return CDLMATCHINGLOW;
    }

    public void setCDLMATCHINGLOW(PRSign CDLMATCHINGLOW) {
        this.CDLMATCHINGLOW = CDLMATCHINGLOW;
    }

    public PRSign getCDLMATHOLD() {
        return CDLMATHOLD;
    }

    public void setCDLMATHOLD(PRSign CDLMATHOLD) {
        this.CDLMATHOLD = CDLMATHOLD;
    }

    public PRSign getCDLMORNINGDOJISTAR() {
        return CDLMORNINGDOJISTAR;
    }

    public void setCDLMORNINGDOJISTAR(PRSign CDLMORNINGDOJISTAR) {
        this.CDLMORNINGDOJISTAR = CDLMORNINGDOJISTAR;
    }

    public PRSign getCDLMORNINGSTAR() {
        return CDLMORNINGSTAR;
    }

    public void setCDLMORNINGSTAR(PRSign CDLMORNINGSTAR) {
        this.CDLMORNINGSTAR = CDLMORNINGSTAR;
    }

    public PRSign getCDLONNECK() {
        return CDLONNECK;
    }

    public void setCDLONNECK(PRSign CDLONNECK) {
        this.CDLONNECK = CDLONNECK;
    }

    public PRSign getCDLPIERCING() {
        return CDLPIERCING;
    }

    public void setCDLPIERCING(PRSign CDLPIERCING) {
        this.CDLPIERCING = CDLPIERCING;
    }

    public PRSign getCDLRICKSHAWMAN() {
        return CDLRICKSHAWMAN;
    }

    public void setCDLRICKSHAWMAN(PRSign CDLRICKSHAWMAN) {
        this.CDLRICKSHAWMAN = CDLRICKSHAWMAN;
    }

    public PRSign getCDLRISEFALL3METHODS() {
        return CDLRISEFALL3METHODS;
    }

    public void setCDLRISEFALL3METHODS(PRSign CDLRISEFALL3METHODS) {
        this.CDLRISEFALL3METHODS = CDLRISEFALL3METHODS;
    }

    public PRSign getCDLSEPARATINGLINES() {
        return CDLSEPARATINGLINES;
    }

    public void setCDLSEPARATINGLINES(PRSign CDLSEPARATINGLINES) {
        this.CDLSEPARATINGLINES = CDLSEPARATINGLINES;
    }

    public PRSign getCDLSHOOTINGSTAR() {
        return CDLSHOOTINGSTAR;
    }

    public void setCDLSHOOTINGSTAR(PRSign CDLSHOOTINGSTAR) {
        this.CDLSHOOTINGSTAR = CDLSHOOTINGSTAR;
    }

    public PRSign getCDLSHORTLINE() {
        return CDLSHORTLINE;
    }

    public void setCDLSHORTLINE(PRSign CDLSHORTLINE) {
        this.CDLSHORTLINE = CDLSHORTLINE;
    }

    public PRSign getCDLSPINNINGTOP() {
        return CDLSPINNINGTOP;
    }

    public void setCDLSPINNINGTOP(PRSign CDLSPINNINGTOP) {
        this.CDLSPINNINGTOP = CDLSPINNINGTOP;
    }

    public PRSign getCDLSTALLEDPATTERN() {
        return CDLSTALLEDPATTERN;
    }

    public void setCDLSTALLEDPATTERN(PRSign CDLSTALLEDPATTERN) {
        this.CDLSTALLEDPATTERN = CDLSTALLEDPATTERN;
    }

    public PRSign getCDLSTICKSANDWICH() {
        return CDLSTICKSANDWICH;
    }

    public void setCDLSTICKSANDWICH(PRSign CDLSTICKSANDWICH) {
        this.CDLSTICKSANDWICH = CDLSTICKSANDWICH;
    }

    public PRSign getCDLTAKURI() {
        return CDLTAKURI;
    }

    public void setCDLTAKURI(PRSign CDLTAKURI) {
        this.CDLTAKURI = CDLTAKURI;
    }

    public PRSign getCDLTASUKIGAP() {
        return CDLTASUKIGAP;
    }

    public void setCDLTASUKIGAP(PRSign CDLTASUKIGAP) {
        this.CDLTASUKIGAP = CDLTASUKIGAP;
    }

    public PRSign getCDLTHRUSTING() {
        return CDLTHRUSTING;
    }

    public void setCDLTHRUSTING(PRSign CDLTHRUSTING) {
        this.CDLTHRUSTING = CDLTHRUSTING;
    }

    public PRSign getCDLTRISTAR() {
        return CDLTRISTAR;
    }

    public void setCDLTRISTAR(PRSign CDLTRISTAR) {
        this.CDLTRISTAR = CDLTRISTAR;
    }

    public PRSign getCDLUNIQUE3RIVER() {
        return CDLUNIQUE3RIVER;
    }

    public void setCDLUNIQUE3RIVER(PRSign CDLUNIQUE3RIVER) {
        this.CDLUNIQUE3RIVER = CDLUNIQUE3RIVER;
    }

    public PRSign getCDLUPSIDEGAP2CROWS() {
        return CDLUPSIDEGAP2CROWS;
    }

    public void setCDLUPSIDEGAP2CROWS(PRSign CDLUPSIDEGAP2CROWS) {
        this.CDLUPSIDEGAP2CROWS = CDLUPSIDEGAP2CROWS;
    }

    public PRSign getCDLXSIDEGAP3METHODS() {
        return CDLXSIDEGAP3METHODS;
    }

    public void setCDLXSIDEGAP3METHODS(PRSign CDLXSIDEGAP3METHODS) {
        this.CDLXSIDEGAP3METHODS = CDLXSIDEGAP3METHODS;
    }

    @Override
    public String toString() {
        return "PRResultDomain{" +
                "symbol='" + symbol + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", CDL2CROWS=" + CDL2CROWS +
                ", CDL3BLACKCROWS=" + CDL3BLACKCROWS +
                ", CDL3INSIDE=" + CDL3INSIDE +
                ", CDL3LINESTRIKE=" + CDL3LINESTRIKE +
                ", CDL3OUTSIDE=" + CDL3OUTSIDE +
                ", CDL3STARSINSOUTH=" + CDL3STARSINSOUTH +
                ", CDL3WHITESOLDIERS=" + CDL3WHITESOLDIERS +
                ", CDLABANDONEDBABY=" + CDLABANDONEDBABY +
                ", CDLADVANCEBLOCK=" + CDLADVANCEBLOCK +
                ", CDLBELTHOLD=" + CDLBELTHOLD +
                ", CDLBREAKAWAY=" + CDLBREAKAWAY +
                ", CDLCLOSINGMARUBOZU=" + CDLCLOSINGMARUBOZU +
                ", CDLCONCEALBABYSWALL=" + CDLCONCEALBABYSWALL +
                ", CDLCOUNTERATTACK=" + CDLCOUNTERATTACK +
                ", CDLDARKCLOUDCOVER=" + CDLDARKCLOUDCOVER +
                ", CDLDOJI=" + CDLDOJI +
                ", CDLDOJISTAR=" + CDLDOJISTAR +
                ", CDLDRAGONFLYDOJI=" + CDLDRAGONFLYDOJI +
                ", CDLENGULFING=" + CDLENGULFING +
                ", CDLEVENINGDOJISTAR=" + CDLEVENINGDOJISTAR +
                ", CDLEVENINGSTAR=" + CDLEVENINGSTAR +
                ", CDLGAPSIDESIDEWHITE=" + CDLGAPSIDESIDEWHITE +
                ", CDLGRAVESTONEDOJI=" + CDLGRAVESTONEDOJI +
                ", CDLHAMMER=" + CDLHAMMER +
                ", CDLHANGINGMAN=" + CDLHANGINGMAN +
                ", CDLHARAMI=" + CDLHARAMI +
                ", CDLHARAMICROSS=" + CDLHARAMICROSS +
                ", CDLHIGHWAVE=" + CDLHIGHWAVE +
                ", CDLHIKKAKE=" + CDLHIKKAKE +
                ", CDLHIKKAKEMOD=" + CDLHIKKAKEMOD +
                ", CDLHOMINGPIGEON=" + CDLHOMINGPIGEON +
                ", CDLIDENTICAL3CROWS=" + CDLIDENTICAL3CROWS +
                ", CDLINNECK=" + CDLINNECK +
                ", CDLINVERTEDHAMMER=" + CDLINVERTEDHAMMER +
                ", CDLKICKING=" + CDLKICKING +
                ", CDLKICKINGBYLENGTH=" + CDLKICKINGBYLENGTH +
                ", CDLLADDERBOTTOM=" + CDLLADDERBOTTOM +
                ", CDLLONGLEGGEDDOJI=" + CDLLONGLEGGEDDOJI +
                ", CDLLONGLINE=" + CDLLONGLINE +
                ", CDLMARUBOZU=" + CDLMARUBOZU +
                ", CDLMATCHINGLOW=" + CDLMATCHINGLOW +
                ", CDLMATHOLD=" + CDLMATHOLD +
                ", CDLMORNINGDOJISTAR=" + CDLMORNINGDOJISTAR +
                ", CDLMORNINGSTAR=" + CDLMORNINGSTAR +
                ", CDLONNECK=" + CDLONNECK +
                ", CDLPIERCING=" + CDLPIERCING +
                ", CDLRICKSHAWMAN=" + CDLRICKSHAWMAN +
                ", CDLRISEFALL3METHODS=" + CDLRISEFALL3METHODS +
                ", CDLSEPARATINGLINES=" + CDLSEPARATINGLINES +
                ", CDLSHOOTINGSTAR=" + CDLSHOOTINGSTAR +
                ", CDLSHORTLINE=" + CDLSHORTLINE +
                ", CDLSPINNINGTOP=" + CDLSPINNINGTOP +
                ", CDLSTALLEDPATTERN=" + CDLSTALLEDPATTERN +
                ", CDLSTICKSANDWICH=" + CDLSTICKSANDWICH +
                ", CDLTAKURI=" + CDLTAKURI +
                ", CDLTASUKIGAP=" + CDLTASUKIGAP +
                ", CDLTHRUSTING=" + CDLTHRUSTING +
                ", CDLTRISTAR=" + CDLTRISTAR +
                ", CDLUNIQUE3RIVER=" + CDLUNIQUE3RIVER +
                ", CDLUPSIDEGAP2CROWS=" + CDLUPSIDEGAP2CROWS +
                ", CDLXSIDEGAP3METHODS=" + CDLXSIDEGAP3METHODS +
                '}';
    }
}
