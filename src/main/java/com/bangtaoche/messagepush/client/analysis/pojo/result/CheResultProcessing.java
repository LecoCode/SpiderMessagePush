package com.bangtaoche.messagepush.client.analysis.pojo.result;


import com.bangtaoche.messagepush.client.analysis.pojo.bean.carBean;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CheResultInterface;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168;
import com.bangtaoche.messagepush.util.CommonUtil;

import java.util.Date;

public class CheResultProcessing implements CheResultInterface {

    /**
     * 列表页结果集处理
     * @param car
     */
    public void Receive(carBean car) {
        if (car==null)return;
        /*
        与数据库交互
         */
        Che168 che168 = new Che168();
        //地址名字
        che168.setCityName(car.getDiZhi());
        //地址URL
        che168.setCityUrl(car.getDiZhiUrl());
        //添加时间s
        che168.setAddTime(new Date());
        //车地址
        che168.setCarUrl(car.getUrl());
        //是否上新
        if (car.getShangXing().contains("新上")){
            che168.setNewStatus(1);
        }else {
            che168.setNewStatus(2);
        }
        //是否降价
        che168.setPriceReduction(2);
        String s = (Double.parseDouble(car.getShouJia()) * 10000) + "";
        s.substring(0, s.indexOf(".") + 2);
        //价格
        che168.setCarPrice(Double.parseDouble(s.substring(0, s.indexOf(".") + 2)));
        //写入数据库
        RenRenCheDAO renRenCheDAO = new RenRenCheDAO();
//
//        long id = -1;
//        try {
//            if (renRenCheDAO.getChe168Che(che168.getCarUrl())==null){
//                id = renRenCheDAO.insertRenRenChe(che168);
//            if (id!=-1){
        CommonUtil.outputFileXXXX(che168.toString(),"SQL_LB");
//            }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
