package domain.talib;

/**
 * Created by 范志伟 on 2018-03-26.
 */
public class DMADomain {
    private double dma;
    private double ama;

    public double getDma() {
        return dma;
    }

    public void setDma(double dma) {
        this.dma = dma;
    }

    public double getAma() {
        return ama;
    }

    public void setAma(double ama) {
        this.ama = ama;
    }

    @Override
    public String toString() {
        return "DMADomain{" +
                "dma=" + dma +
                ", ama=" + ama +
                '}';
    }
}
