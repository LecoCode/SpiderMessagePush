package com.bangtaoche.messagepush.client.analysis.resultinterface;


import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;

/**
 * 车页面解析后结果的回调接口
 */
public interface CarResultInterface {

    void Receive(carDetailsBean car);

}
