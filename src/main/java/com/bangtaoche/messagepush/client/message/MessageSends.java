package com.bangtaoche.messagepush.client.message;

import com.aliyun.openservices.ons.api.SendResult;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.entity.SendCarMessage;
import com.bangtaoche.messagepush.client.entity.SendPriceMessage;
import com.bangtaoche.util.msg.factory.ProducerFactory;
import static com.bangtaoche.messagepush.client.message.MessageRules.*;
public class MessageSends extends ProducerFactory{


    /**
     * 车辆上新
     * @param che168Car
     */
    public SendResult sendMessageCarNew(Che168Car che168Car){
        SendResult newCarResult = null;
        try {
            newCarResult = super.sendMsg(TOPIC_CAR, TARG_CAR_NEW,"5" ,che168Car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCarResult;
    }

    /**
     * 取消上新
     * @param sendCarMessage
     */
    public SendResult sendMessageCarCancleNew(SendCarMessage sendCarMessage){
        SendResult newCarResult = null;
        try {
            newCarResult = super.sendMsg(TOPIC_CAR, TARG_CAR_CANCLE_NEW,"5" , sendCarMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCarResult;
    }

    /**
     * 车辆下架
     * @param sendCarMessage
     */
    public SendResult sendMessageCarDownSale(SendCarMessage sendCarMessage){
        SendResult newCarResult = null;
        try {
            newCarResult = super.sendMsg(TOPIC_CAR, TARG_CAR_DOWN_SALE,"5" , sendCarMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCarResult;
    }

    /**
     * 升价或降价
     * @param sendPriceMessage
     */
    public SendResult sendMessagePriceChange(SendPriceMessage sendPriceMessage){
        SendResult newCarResult = null;
        try {
            newCarResult = super.sendMsg(TOPIC_PRICE, TARG_PRICE_CHANGE,"5" , sendPriceMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCarResult;
    }

    /**
     * 取消降价
     * @param sendPriceMessage
     */
    public SendResult sendMessagePriceCancleDown(SendPriceMessage sendPriceMessage){
        SendResult newCarResult = null;
        try {
            newCarResult = super.sendMsg(TOPIC_PRICE, TARG_PRICE_CANCLE_DOWN, "5" ,sendPriceMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCarResult;
    }










}
