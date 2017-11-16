package com.bangtaoche.messagepush.client;

import com.bangtaoche.messagepush.client.analysis.WholeAnalysis;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.message.MessageRules;
import com.bangtaoche.messagepush.client.message.MessageSends;
import com.bangtaoche.messagepush.util.CommonUtil;
import com.bangtaoche.util.msg.factory.ProducerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        MessageSends messageSends = new MessageSends();
        List<Long> ids = new ArrayList<Long>();
        List<Che168Car> renRenCheCars = null;
        for (long i = 10001; i <=110000 ; i++) {
            ids.add(i);
        }
        renRenCheCars = dao.getRenRenCheCars(ids);
        for (Che168Car c:renRenCheCars) {
            long sourceUrl = c.getCarId();
            System.out.println("第:"+sourceUrl);
            messageSends.sendMessageCarNew(c);
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("sssssssssssssss");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,100);
    }



    /**
     * 增量发送数据
     */
    public static void startAddSendMessage(){

        WholeAnalysis wholeAnalysis = new WholeAnalysis("",20);
    }

    public static void main(String []args){
//        MainService mainService = new MainService();
                System.out.println("sssssssssssssss");
                MessageSends messageSends = new MessageSends();
                List<Long> ids = new ArrayList<Long>();
                List<Che168Car> renRenCheCars = null;
                for (long i = 100001; i <=180027 ; i++) {
                    ids.add(i);
                }
                try {
                    renRenCheCars = dao.getRenRenCheCars(ids);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (Che168Car c:renRenCheCars) {
                    long sourceUrl = c.getCarId();
                    String carImages = c.getCarImages();

                    //图片
                    c.setCarImages(carImages.substring(0,carImages.lastIndexOf(",}"))+"}");

                    //亮点配置
                    if (!"-".equals(c.getBrightPoints())&&c.getBrightPoints()!=null){
                        String brightPoints = c.getBrightPoints();
                        String brightPointSub = brightPoints.substring(1,brightPoints.lastIndexOf(","));
                        c.setBrightPoints(brightPointSub);
                        System.out.println(brightPointSub);
                    }

                    //里程
//                   if ((c.getMileage()+"").length()<4){
                       double mileage = c.getMileage();
                       String mileage_copy=(mileage*10000)+"";
                       mileage_copy.substring(0,mileage_copy.indexOf(".")+2);
                       mileage=Double.parseDouble(mileage_copy);
                       c.setMileage(mileage);
                       System.out.println(mileage);
                    CommonUtil.outputFileXXXX("ID："+c.getCarId()+",公里："+mileage,"gongli");
//                   }

                   if (c.getOwnerDepict()!=null){
                       String ownerDepict = c.getOwnerDepict();
                       ownerDepict=ownerDepict.replaceAll("�N","km");
                       ownerDepict=ownerDepict.replaceAll("�","");
                       c.setOwnerDepict(ownerDepict);
                       System.out.println(ownerDepict);
                   }
                    System.out.println("第:"+sourceUrl);
                    messageSends.sendMessageCarNew(c);
                }

            }



            @Test
            public void test() throws Exception {
                List<Che168Car> all = dao.getAll();
                for (Che168Car c:
                     all) {
                    System.out.println(c.getCarId());
                }

            }
}
