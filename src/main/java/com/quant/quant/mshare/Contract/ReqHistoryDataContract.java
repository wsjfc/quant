package com.quant.quant.mshare.Contract;

import com.ib.client.Contract;
import com.quant.quant.mshare.model.ReqHistoryDataModel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.quant.quant.mshare.utils.Tools.formatInterval;

public class ReqHistoryDataContract {
    private List<ReqHistoryDataModel> source;
    public final static ReqHistoryDataContract reqHistoryDataContract = new ReqHistoryDataContract();

    private ReqHistoryDataContract(){
        read();
    }

    public static ReqHistoryDataContract getInstance(){
        return reqHistoryDataContract;
    }

    public List<ReqHistoryDataModel> getSource(){
        return source;
    }

    private void read(){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File("src/main/resources/reqHisDataCont.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        //将解析出来的allresource下的resourceitem放在list中
        List list  = root.elements("resourceitem");
        //创建source存放每一个resourceitem中资源
        source = new ArrayList<ReqHistoryDataModel>();
        for(Iterator i = list.iterator(); i.hasNext();) {
            Element resourceitem = (Element) i.next();
            String symbol = resourceitem.element("symbol").getText();
            String secType = resourceitem.element("secType").getText();
            String currency = resourceitem.element("currency").getText();
            String exchange = resourceitem.element("exchange").getText();
            String interval = resourceitem.element("interval").getText();
            String duration = resourceitem.element("duration").getText();
            String dataType = resourceitem.element("dataType").getText();
            String start = duration.split(",")[0];
            String end = duration.split(",")[1];
            String tableName = symbol + "_" + interval + "_" + dataType;
            String formattedInterval = formatInterval(interval);
            Contract contract = new Contract();
            contract.symbol(symbol);
            contract.secType(secType);
            contract.currency(currency);
            contract.exchange(exchange);
            source.add(new ReqHistoryDataModel(contract,start,end,tableName,formattedInterval,dataType));
        }
        return;
    }
    public static void main(String[] args){
        ReqHistoryDataContract reqHistoryDataContract = ReqHistoryDataContract.getInstance();
        System.out.println(reqHistoryDataContract.getSource().get(0).getContract().secType());
    }
}
