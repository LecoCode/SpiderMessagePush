package com.bangtaoche.messagepush.client.analysis.pojo.result;

import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CarResultInterface;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.message.MessageSends;
import com.bangtaoche.messagepush.util.CommonUtil;

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
        CommonUtil.outputRun("[run] 详情页任务结束");
        if (car == null) return;
        System.out.println("******************************************************************");
        System.out.println("******************************************************************");
        System.out.println("******************************************************************");
        System.out.println(car.toString());
        CommonUtil.outputFileXiangXi(car);


        //------------------------------------------------------------------------------------------------
        MessageSends messageSends = new MessageSends();
        Che168Car che = new Che168Car();
        //设置车名
        che.setCarName(car.getName());
        //设置发布时间
        che.setAddTime(new Date());
        //设置车主报价
        String s = (Double.parseDouble(car.getJiaqian()) * 10000) + "";
        s.substring(0, s.indexOf(".") + 2);
        //价格
        che.setOwnerPrice(Double.parseDouble(s.substring(0, s.indexOf(".") + 2)));
        //变速箱
        che.setGearbox(car.getBianSuQi());
        //上牌时间
        che.setRegTime(car.getShouCiPaiZhao());
        //排放标准
        che.setStandard(car.getPaiFang());
        //过户次数
        che.setDealCount(Integer.parseInt(car.getGuoHu().substring(0,1)));
        //排量
        che.setDisplacement(car.getPaiLiang());
        //看车地址
        che.setSeeCarCity(car.getKanCheDiDian());
        //交强险到期时间
        //年检时间
        che.setYearExamineTime(car.getNianJian());
        //商业险
        che.setCommercialInsuranceTime(car.getBaoXian());
        //车源编号
        che.setSourceId("5");
        //高端配置
        che.setBrightPoints(car.getGaoDuanPeiZhi());
        //服务费
        //是否4s保养
        if (car.getBaoYang().contains("定期4S保养")){
            che.setFourService(1);
        }else if (car.getBaoYang().contains("-")){
            che.setFourService(-1);
        }
        //车辆来源URL
        che.setSourceUrl(car.getUrl());
        //卖家描述
        che.setOwnerDepict(car.getCarMessage());
        //是否降价
        che.setPriceReduction(2);
        //是否在售
        che.setStatus(1);
        //所在城市
        che.setLocation(car.getSuoZaiDi());
        //车辆图片
        che.setCarImages(car.getImages());
        //城市URL
        //咨询电话
        che.setPhone(car.getIphone());
        //所在城市
        che.setLocation(car.getSuoZaiDi());
        RenRenCheDAO renRenCheDAO = new RenRenCheDAO();
        long id =-1;
        try {
            id = renRenCheDAO.insertRenRenCheCar(che);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id!=-1){
            CommonUtil.outputINFO("[INFO] 插入；详细数据成功"+car.getName());
            messageSends.sendMessageCarNew(che);
        }else {
            CommonUtil.outputERROR("[ERROR] 插入；详细数据失败"+car.getName());
        }

    }



}
