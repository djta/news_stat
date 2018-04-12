package crawl.market;

import com.alibaba.fastjson.JSON;
import domain.MarketDomain;
import domain.MarketMainDomain;
import jdbc.impl.MarketDaoImpl;
import util.Constants;
import util.HttpUtil;

import java.util.HashSet;

/**
 * Created by 范志伟 on 2018-02-23.
 */
public class MarketHistory {

    public static void main(String args[]) {


//      for(String set:usdtMain){
//          String symbol=set+"usdt";
//          String result = HttpUtil.doGetMarket(symbol, "1min", size);
//          MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//          for (MarketDomain md : mmd.getData()) {
//              md.setSymbol(symbol);
//          }
//          MarketDaoImpl mdImpl = new MarketDaoImpl();
//          for (MarketDomain md : mmd.getData()) {
//              mdImpl.insertMarket(md);
//          }
//      }
//        for(String set:usdtCx){
//            String symbol=set+"usdt";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }
//        for(String set:btcMain){
//            String symbol=set+"btc";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }
//        for(String set:btcCx){
//            String symbol=set+"btc";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }
//        for(String set:btcFx){
//            String symbol=set+"btc";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }
//        for(String set:ethMain){
//            String symbol=set+"eth";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }
//        for(String set:ethCx){
//            String symbol=set+"eth";
//            String result = HttpUtil.doGetMarket(symbol, "1min", size);
//            MarketMainDomain mmd = JSON.parseObject(result, MarketMainDomain.class);
//            for (MarketDomain md : mmd.getData()) {
//                md.setSymbol(symbol);
//            }
//            MarketDaoImpl mdImpl = new MarketDaoImpl();
//            for (MarketDomain md : mmd.getData()) {
//                mdImpl.insertMarket(md);
//            }
//        }

    }
}
