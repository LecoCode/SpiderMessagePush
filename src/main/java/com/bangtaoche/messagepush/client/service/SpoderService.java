package com.bangtaoche.messagepush.client.service;

import com.bangtaoche.messagepush.client.analysis.WholeAnalysis;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.entity.SendCarMessage;
import com.bangtaoche.messagepush.client.message.MessageSends;
import com.bangtaoche.messagepush.util.CommonUtil;
import com.bangtaoche.messagepush.util.ThreadLocalClientFactory;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpoderService {
    public static int INDEX_MAX=2;
    private static String[] DIZHI_1 = {"wenchang", "wanning", "dongfang", "dingan", "tunchang", "chengmai", "lingao", "baisha",
            "changjiang", "ledong", "lingshui", "baoting", "qiongzhong", "zhengzhou", "kaifeng", "luoyang",
            "pingdingshan", "anyang", "hebi", "xinxiang", "jiaozuo", "puyang", "xuchang", "luohe", "sanmenxia",
            "nanyang", "shangqiu", "xinyang", "zhoukou", "zhumadian", "jiyuan", "wuhan", "huangshi", "shiyan",
            "yichang", "xiangyang", "ezhou", "jingmen","jingzhou", "huanggang", "xianning", "suizhou",
            "enshi", "xiantao", "qianjiang", "tianmen", "shennongjia", "changsha", "zhuzhou", "xiangtan", "hengyang",
            "shaoyang", "yueyang", "changde", "zhangjiajie", "yiyang", "chenzhou", "yongzhou", "huaihua", "loudi",
            "xiangxi", "shijiazhuang", "tangshan", "qinhuangdao", "handan", "xingtai", "baoding", "zhangjiakou",
            "chengde", "cangzhou", "langfang", "hengshui", "haerbin", "qiqihaer", "jixi", "hegang", "shuangyashan",
            "daqing", "yichun", "jiamusi", "qitaihe", "mudanjiang", "heihe", "suihua", "daxinganling", "nanjing",
            "wuxi", "xuzhou", "changzhou", "suzhou", "nantong", "lianyungang", "huaian", "yancheng", "yangzhou",
            "zhenjiang", "tai_zhou", "suqian", "nanchang", "jingdezhen", "ping_xiang", "jiujiang", "xinyu", "yingtan",
            "ganzhou", "jian", "yi_chun", "fu_zhou", "shangrao", "changchun", "jilinshi", "siping", "liaoyuan",
            "tonghua", "baishan", "songyuan", "baicheng", "yanbian", "shenyang", "dalian", "anshan", "fushun", "benxi",
            "dandong", "jinzhou", "yingkou", "fuxin", "liaoyang", "panjin", "tieling", "chaoyang", "huludao",
            "huhehaote", "baotou", "wuhai", "chifeng", "tongliao", "eerduosi", "hulunbeier", "bayannaoer", "wulanchabu",
            "xinganmeng", "xilinguolemeng", "alashanmeng", "yinchuan", "shizuishan", "wuzhong", "guyuan", "zhongwei",
            "xining", "haidong", "haibei", "huangnan", "hai_nan", "guoluo", "yushu", "haixi", "xian", "tongchuan",
            "baoji", "xianyang", "weinan", "yanan", "hanzhong", "ankang", "shangluo", "xixianxinqu", "chengdu",
            "zigong", "panzhihua", "luzhou", "deyang", "mianyang", "guangyuan", "suining", "neijiang", "leshan",
            "nanchong", "meishan", "yibin", "guangan", "dazhou", "yaan", "bazhong", "ziyang", "aba", "ganzi",
            "liangshan", "shanghai", "taiyuan", "datong", "yangquan", "zhangzhi", "jincheng", "shuozhou", "jinzhong",
            "yuncheng", "xinzhou", "linfen", "lvliang", "jinan", "qingdao", "zibo", "zaozhuang", "dongying", "yantai",
            "weifang", "jining", "taian", "weihai", "rizhao", "laiwu", "linyi", "dezhou", "liaocheng", "binzhou",
            "heze", "tianjin", "wulumuqi", "kelamayi", "turpan", "hami", "changji", "boertala", "bayinguoleng", "akesu",
            "kezilesu", "kashen", "hetian", "yili", "tacheng", "aletai", "shihezi", "aral", "tumxuk", "wujiaqu",
            "beitun", "tiemenguan", "shuanghe", "kokdala", "kunyu", "lasa", "rikaze", "qamdo", "nyingchi", "shannan",
            "naqu", "ali", "kunming", "qujing", "yuxi", "baoshan", "zhaotong", "lijiang", "puer", "lincang", "chuxiong",
            "honghe", "wenshan", "xishuangbanna", "dali", "dehong", "nujiang", "diqing", "hangzhou", "ningbo",
            "wenzhou", "jiaxing", "huzhou", "shaoxing", "jinhua", "quzhou", "zhoushan", "taizhou", "lishui"};
    private static int DIZHI_INDEX=3;
    
    //开始运行
    public void startService(){
        startAnalysis();
    }

    public void startAnalysis(){
        int index;
        for (int i = 0; i <DIZHI_1.length ; i++) {
         for (index = 1; index <=INDEX_MAX; index++) {
            String url = "https://www.che168.com/"+DIZHI_1[i]+"/a0_0msdgscncgpi1ltocsp"+index+"exb112y96x0/";
            CommonUtil.outputFileXXXX("[INFO] 正在运行"+i+"个城市："+DIZHI_1[i]+"的第"+index+"个页面,最多页面"+INDEX_MAX+"，地址为："+url,"DIZHI_1");
            WholeAnalysis wholeAnalysis = new WholeAnalysis(url,20);
             String s = null;
             try {
                 s = wholeAnalysis.analyStart();
             } catch (Exception e) {
                 e.printStackTrace();
                 continue;
             }
             if (s.contains("出错啦，您访问的页面走丢啦！")&&"".equals(s)){
                break;
            }
         }

        }
    }

    /**
     * 下架
     */
    public void SoldOut(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        RenRenCheDAO dao = new RenRenCheDAO();
        MessageSends sends = new MessageSends();
        ThreadLocalClientFactory instance = ThreadLocalClientFactory.getInstance();
        List<Che168Car> all = null;
        try {
            all = dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Che168Car c:
                all) {
            executorService.execute(new SolidRunnable(instance.getWebClient(),c,sends,dao));
        }
    }


    //------测试----------------
    public static void main (String []args){
        SpoderService spoderService = new SpoderService();
        spoderService.startService();

//        spoderService.SoldOut();
    }

    class SolidRunnable implements Runnable{
        WebClient webClient;
        Che168Car c;
        MessageSends sends;
        RenRenCheDAO dao;
        SolidRunnable(WebClient webClient,Che168Car c,MessageSends messageSends,RenRenCheDAO dao){
            this.webClient=webClient;
            this.c=c;
            this.sends=messageSends;
            this.dao=dao;
        }

        public void run() {
            HtmlPage htmlPage = CommonUtil.GeneratePage(webClient, c.getSourceUrl());
            if (htmlPage.isHtmlPage()){
                if (htmlPage.asText().contains("出错啦，您访问的页面走丢啦！")){
                    c.setStatus(2);
                    SendCarMessage sendCarMessage=new SendCarMessage();
                    sendCarMessage.setCarId(c.getCarId());
                    sendCarMessage.setSourceId(5);
                    sends.sendMessageCarDownSale(sendCarMessage);
                    try {
                        dao.updateChe168CarXiaJia(c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CommonUtil.outputFileXXXX("下架ID："+c.getCarId(),"下架");
                }
            }
        }
    }
}
