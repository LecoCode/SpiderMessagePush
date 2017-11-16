package com.bangtaoche.messagepush.client.analysis.pojo.result;


import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CarResultInterface;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.message.MessageSends;

import java.util.Date;

public class ResultProcessing implements CarResultInterface {
       private static final String TAG="----->ResultProcessing";
//       static {
//           ProducerFactory.getInstanceTest("PID_BANGTAOCHE_CAR_TEST","BANGTAO_CAR_TEST");
//       }
    /**
     * 详情页结果集处理
     * @param car Che168Car对象
     */
    public void Receive(carDetailsBean car) {
        if (car == null) return;
        System.out.println(car.toString());


        //------------------------------------------------------------------------------------------------
        Che168Car che = new Che168Car();
        //设置车名
        che.setCarName(car.getName());
        //设置添加时间
        che.setAddTime(new Date());
        //里程
        if (!"-".equals(car.getLiCheng())){
            String lccopy = (Double.parseDouble(car.getLiCheng()) * 10000) + "";
            che.setMileage(Double.parseDouble(lccopy.substring(0, lccopy.indexOf(".") + 2)));
        }else {
            che.setMileage(-1);
        }
        //设置车主报价
        if (!"-".equals(car.getJiaqian())){
            String s = (Double.parseDouble(car.getJiaqian()) * 10000) + "";
            s.substring(0, s.indexOf(".") + 2);
            //价格
            che.setOwnerPrice(Double.parseDouble(s.substring(0, s.indexOf(".") + 2)));
        }else {
            che.setOwnerPrice(-1);
        }
        //变速箱
//        che.setGearbox(;
        String gearbox = car.getBianSuQi();
        if (gearbox.contains("手动")) {
            che.setGearbox("1");
        }
        if (gearbox.contains("自动")) {
            che.setGearbox("2");
        }
        if (gearbox.contains("手自一体")) {
            che.setGearbox("3");
        }
        if (gearbox.contains("无级变速")) {
            che.setGearbox("4");
        }
        if (gearbox.contains("双离合")) {
            che.setGearbox("5");
        }
        //上牌时间
        che.setRegTime(car.getShouCiPaiZhao());
        //排放标准
        String standard = car.getPaiFang();
        if (standard.contains("国I")) {
            che.setStandard("1");
        }
        if (standard.contains("国II")) {
            che.setStandard("2");
        }
        if (standard.contains("国III")) {
            che.setStandard("3");
        }
        if (standard.contains("国IV")) {
            che.setStandard("4");
        }
        if (standard.contains("国V")) {
            che.setStandard("5");
        }
        if (standard.contains("欧I")) {
            che.setStandard("100");
        }
        if (standard.contains("欧II")) {
            che.setStandard("101");
        }
        if (standard.contains("欧III")) {
            che.setStandard("102");
        }
        if (standard.contains("欧IV")) {
            che.setStandard("103");
        }
        if (standard.contains("欧V")) {
            che.setStandard("104");
        }
        if (standard.contains("欧VI")) {
            che.setStandard("105");
        }
        //过户次数
        if (!car.getGuoHu().equals("-")){
            che.setDealCount(Integer.parseInt(car.getGuoHu().substring(0, 1)));
        }
        che.setDealCount(-1);
        //排量
        che.setDisplacement(car.getPaiLiang());
        //看车地址
        che.setSeeCarCity(car.getKanCheDiDian());
        //所在城市
        che.setRegCity(car.getSuoZaiDi());
        //交强险到期时间
        //年检时间
        che.setYearExamineTime(car.getNianJian());
        //商业险
        che.setCommercialInsuranceTime(car.getBaoXian());
        //车源编号
        che.setSourceId("5");
        //是否新上
        che.setNewStatus(1);
        //高端配置
        che.setBrightPoints(car.getGaoDuanPeiZhi());
        //服务费
        //是否4s保养
        if (car.getBaoYang().contains("定期4S保养")) {
            che.setFourService(1);
        } else if (car.getBaoYang().contains("-")) {
            che.setFourService(2);
        } else {
            che.setFourService(-1);
        }
        //车辆来源URL
        che.setSourceUrl(car.getUrl());
        //卖家描述
        if (car.getCarMessage().length() > 10) {
            String carMessage = car.getCarMessage();
            carMessage.replaceAll("�N", "km");
            carMessage.replaceAll("�", "");
            che.setOwnerDepict(carMessage);
        } else {
            che.setOwnerDepict(car.getCarMessage());
        }

        //是否降价
        che.setPriceReduction(2);
        //是否在售
        che.setStatus(1);
        //车辆图片
        che.setCarImages(car.getImages());
        //城市URL
        //咨询电话
        che.setPhone(car.getIphone());
        //所在城市
        che.setLocation(car.getSuoZaiDi());
        //发布时间
        che.setSellTime(car.getFaBuShiJian());
        RenRenCheDAO renRenCheDAO = new RenRenCheDAO();
        MessageSends messageSends = new MessageSends();
//        long id = -1;
//        try {
//            id = renRenCheDAO.insertRenRenCheCar(che);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (id != -1) {
//            CommonUtil.outputFileXXXX(che.toString(),"SQL_XX");
//            messageSends.sendMessageCarNew(che);
//        }

    }

}
