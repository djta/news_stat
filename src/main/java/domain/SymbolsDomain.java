package domain;

/**
 * Created by hzyuyongmao on 2018/2/26.
 *
 * @Description
 */
public class SymbolsDomain {

    private String base_currency;//基础币种
    private String quote_currency;//计价币种
    private int price_precision;//价格精度位数（0为个位）
    private int amount_precision;//数量精度位数（0为个位）
    private String symbol_partition;//交易区	main主区，innovation创新区，bifurcation分叉区

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public int getPrice_precision() {
        return price_precision;
    }

    public void setPrice_precision(int price_precision) {
        this.price_precision = price_precision;
    }

    public int getAmount_precision() {
        return amount_precision;
    }

    public void setAmount_precision(int amount_precision) {
        this.amount_precision = amount_precision;
    }

    public String getSymbol_partition() {
        return symbol_partition;
    }

    public void setSymbol_partition(String symbol_partition) {
        this.symbol_partition = symbol_partition;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public void setQuote_currency(String quote_currency) {
        this.quote_currency = quote_currency;
    }

    @Override
    public String toString() {
        return "SymbolsDomain{" +
                "base_currency='" + base_currency + '\'' +
                ", quote_currency='" + quote_currency + '\'' +
                ", price_precision=" + price_precision +
                ", amount_precision=" + amount_precision +
                ", symbol_partition='" + symbol_partition + '\'' +
                '}';
    }
}
