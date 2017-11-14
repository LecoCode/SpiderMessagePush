package com.bangtaoche.messagepush.client;

import com.bangtaoche.messagepush.client.analysis.WholeAnalysis;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.message.MessageRules;
import com.bangtaoche.messagepush.client.message.MessageSends;
import com.bangtaoche.util.msg.factory.ProducerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainService {

    private static RenRenCheDAO dao;
    static {
        dao = new RenRenCheDAO();
        ProducerFactory.getInstanceTest(MessageRules.PID_CAR,MessageRules.TOPIC_CAR);
        ProducerFactory.getInstanceTest(MessageRules.PID_PRICE,MessageRules.TOPIC_PRICE);
    }

    /**
     * 总量发送
     * @throws Exception
     */
    @Test
    public void startInitSendMessage() throws Exception {
        List<Long> ids = new ArrayList<Long>();
        List<Che168Car> renRenCheCars = null;
        boolean off=false;
        int IndexMax = 180028;
        long IndexCount = 179000;
        while (true){
            ids.clear();
            for (int i = 0; i <3000 ; i++) {
                ids.add(++IndexCount);
                if (IndexCount>=IndexMax){
                    off=true;
                    break;
                }
            }
            renRenCheCars = dao.getRenRenCheCars(ids);
            for (Che168Car c:renRenCheCars) {
                String sourceUrl = c.getSourceUrl();
                System.out.println(IndexCount+":"+sourceUrl+","+c.getCarId());
            }
            if (off)break;
        }
    }

    @Test
    public void startInitSendMessage_copy() throws Exception {
        List<Long> ids = new ArrayList<Long>();
        List<Che168Car> renRenCheCars = null;
        MessageSends messageSends = new MessageSends();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);
        ids.add(4l);
        renRenCheCars = dao.getRenRenCheCars(ids);
        for (Che168Car c:renRenCheCars) {
              c.setSellTime("");
            messageSends.sendMessageCarNew(c);
        }

        }

    /**
     * 增量发送数据
     */
    public static void startAddSendMessage(){

        WholeAnalysis wholeAnalysis = new WholeAnalysis("",20);
    }

    public static void main(String []args) throws Exception {
        MainService mainService = new MainService();
    }

}
