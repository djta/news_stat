package util;

import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class Constants {
    //usdt主区
    private static final HashSet<String> USDT_MAIN = Sets.newHashSet("btc", "bch", "eth", "etc", "ltc", "eos", "xrp", "omg", "dash", "zec");
    //usdt 创新区
    private static final HashSet<String> USDT_CX = Sets.newHashSet("itc", "nas", "ruff", "zil", "dta", "let", "ht", "theta", "hsr", "qtum", "snt", "iost",
            "neo", "storj", "gnt", "cvc", "smt", "ven", "elf", "xem");
    //BTC 主区
    private static final HashSet<String> BTC_MAIN = Sets.newHashSet("bch", "eth", "ltc", "etc", "eos", "omg", "dash", "xrp", "zec");
    //BTC 创新区
    private static final HashSet<String> BTC_CX = Sets.newHashSet("eng", "wpr", "mtx", "mtn", "snc", "lsk", "stk", "ht", "ela", "srn", "zla", "trx",
            "ocn", "lun", "iost", "hsr", "smt", "let", "swftc", "wax", "elf", "mds", "tnb", "nas", "btm", "itc", "theta", "wicc", "gnx", "ven", "dta",
            "mana", "qash", "propy", "snt", "qun", "qtum", "dat", "tnt", "cmt", "yee", "gas", "aidoc", "storj", "xem", "pay", "neo", "cvc", "qsp", "topc", "rcn", "ast", "bat",
            "dbc", "rpx", "act", "icx", "knc", "mco", "zrx", "mtl", "gnt", "req", "rdn", "salt", "mee", "zil", "chat", "powr", "dgd", "appc", "ost", "soc", "eko", "link", "utk", "evx",
            "adx", "ruff");
    //BTC 分叉区
    private static final HashSet<String> BTC_FC = Sets.newHashSet("bcd", "bcx", "bifi", "sbtc", "btg");
    //ETH 主区
    private static final HashSet<String> ETH_MAIN = Sets.newHashSet("eos", "omg");
    //ETH 创新
    private static final HashSet<String> ETH_FC = Sets.newHashSet("eng", "wpr", "mtx", "mtn", "snc", "lsk", "stk", "ht", "ela", "srn", "zla", "trx",
            "ocn", "lun", "smt", "iost", "nas", "hsr", "mds", "wax", "elf", "itc", "tnb", "swftc", "btm", "dta",
            "wicc", "let", "qash", "gnx", "ven", "theta", "propy", "mana", "aidoc", "yee", "qtum", "gas", "zil", "cmt", "pay", "tnt", "qun", "mee", "rcn", "cvc", "icx", "topc",
            "qsp", "act", "bat", "rdn", "dat", "gnt", "dbc", "chat", "appc", "mco", "soc", "req", "salt", "powr", "eko", "dgd", "ost", "link", "utk", "evx", "adx", "ruff");

    /*
    周期
     */
    public static enum Period {
        //1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
        min_1("1min"),

        min_5("5min"),

        min_15("15min"),

        min_30("30min"),

        min_60("60min"),

        day("1day"),

        mon("1mon"),

        week("1week"),

        year("1year");

        String period;

        private Period(String period) {
            this.period = period;
        }
    }

}
