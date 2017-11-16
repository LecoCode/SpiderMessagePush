package com.bangtaoche.messagepush.client.analysis.pojo.SecondXs;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class BaseMessages {
    public static final String [] BASE_INDEX = new String []{"年检到期","保险到期","质保到期","排放标准","过户次数","维修保养","车主描述"};
    private static Logger logger = Logger.getLogger(BaseMessages.class);
    private Element element;
    public BaseMessages(Element element){
        this.element=element;

//        try {
//            HTMLshunt();
//        } catch (Exception e) {
//            e.printStackTrace();
//            CommonUtil.outputERROR("[ERROR] BaseMessage Exception :"+e.getMessage());
//        }

    }

    public HashMap<String, String> HTMLshunt() throws Exception{
        String nianJian="-";//年检到期
        String baoXian="-";//保险到期
        String zhiBao="-";//质保到期
        String paiFang="-";//排放标准
        String guoHu="-";//过户
        String baoYang="-";//保养
        String carMessage="-";//车主描述


        Elements ul = element.
                getElementsByTag("ul").get(0).
                getElementsByTag("li");

        //年检到期
        String grid_6_1 = ul.get(0).text();

        nianJian = grid_6_1.
                substring(grid_6_1.indexOf(" ")).trim();
//        System.out.println("--------------->"+nianJian);
        //保险到期
        String grid_6_2 = ul.get(1).text();

        baoXian = grid_6_2.
                substring(grid_6_2.indexOf(" ")).trim();
//        System.out.println("--------------->"+baoXian);
        //质保到期
        String grid_8 = ul.get(2).text();

        zhiBao = grid_8.
                substring(grid_8.indexOf(" ")).trim();
//        System.out.println("--------------->"+zhiBao);
        //排放标准
        String grid_6_3 = ul.get(3).text();
        paiFang = grid_6_3.
                substring(grid_6_3.indexOf(" ")).trim();
//        System.out.println("--------------->"+paiFang);
        //过户次数
        String grid_6_4 = ul.get(4).text();
        guoHu = grid_6_4.
                substring(grid_6_4.indexOf(" ")).trim();
//        System.out.println("--------------->"+guoHu);
        //维修保养
        String grid_6_5 = ul.get(6).text();
        baoYang = grid_6_5.
                substring(grid_6_5.indexOf(" ")).trim();
//        System.out.println("--------------->"+baoYang);
        //车主描述

        carMessage = ul.get(7).getElementsByClass("tip-content fn-clear").text();
//        System.out.println("--------------->"+carMessage);

        return getTextAll(nianJian,baoXian,zhiBao,paiFang,guoHu,baoYang,carMessage);
    }

    /**
     * 打包
     * @param strings
     * @return
     */
    public HashMap<String, String> getTextAll(String... strings){
        HashMap<String,String> message  = new HashMap<String, String>();
        for (int i = 0; i <BASE_INDEX.length ; i++) {
            message.put(BASE_INDEX[i],strings[i]);
            logger.info(BASE_INDEX[i]+":"+strings[i]);
        }
        return message;
    }
    //----------------------------
    public static void main(String []args) throws IOException {
        String url="https://www.che168.com/dealer/127110/24087113.html?pvareaid=100519#pos=35#page=1#rtype=0#isrecom=2#filter=0aa0_0a0_0a0_0#module=10";
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage page =webClient.getPage(url);
        Document parse = Jsoup.parse(page.asXml());
        Element anchor01 = parse.getElementById("anchor01");
        BaseMessages baseMessages = new BaseMessages(anchor01);

    }
}
