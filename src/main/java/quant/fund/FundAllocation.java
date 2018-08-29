package quant.fund;

/*
    凯利公式
    http://baijiahao.baidu.com/s?id=1601035390240792465&wfr=spider&for=pc

 */
public class FundAllocation {
    public static void main(String args[]) {
        double winsRate =0.45;
        double winToLossRatio =2;
        FundDomain fundDomain = kaiLiStat(winsRate, winToLossRatio);
        System.out.println(fundDomain);
        //两个返回值都要为正值。

    }

    /*

       f=p/a-q/b
     */
    public static FundDomain kaiLiStat(double winsRate, double winToLossRatio) {
        FundDomain fundDomain=new FundDomain();
        //kaili
        double p = winsRate;
        double q = 1 - winsRate;
        double a = 1;
        double b = winToLossRatio;
        double fValue = p / a - q / b;
        fundDomain.setKailiResult(fValue);
        //expect value
        double expValue=p*b-q*a;
        fundDomain.setExpect(expValue);
        return fundDomain;
    }
}
