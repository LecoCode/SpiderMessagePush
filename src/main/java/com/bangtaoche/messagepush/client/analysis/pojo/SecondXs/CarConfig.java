package com.bangtaoche.messagepush.client.analysis.pojo.SecondXs;

import com.bangtaoche.messagepush.util.CommonUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class CarConfig {
    public static final String [] CONFIG_INDEX = new String []{"发动机","变速器","车辆级别","颜色","燃油标号","驱动方式","高端配置"};
    private Element element;
    public CarConfig(Element element){
        this.element=element;

    }

    public HashMap<String, String> HTMLshunt() throws Exception{
        String faDongji="-";//发动机
        String bianSuQi="-";//变速器
        String cheLiangJiBie="-";//车辆级别
        String yanSe="-";//颜色
        String ranYouBiaoHao="-";//燃油标号
        String quDongBiaoHao="-";//驱动方式
        String GaoDuanPeiZhi = "-";
        Elements ul = element.
                getElementsByClass
                        ("infotext-list fn-clear").get(0).getElementsByTag("li");
        //发动机

        faDongji = getText(ul.get(0));
//        System.out.println("------------>"+faDongji);
        //变速器
        bianSuQi = getText(ul.get(1));
//        System.out.println("------------>"+bianSuQi);

        //车辆级别
        cheLiangJiBie = getText(ul.get(2));
//        System.out.println("------------>"+cheLiangJiBie);
        //颜色
        yanSe = getText(ul.get(3));
//        System.out.println("------------>"+yanSe);

        //燃油标号
        ranYouBiaoHao = getText(ul.get(4));
//        System.out.println("------------>"+ranYouBiaoHao);

        //驱动方式
        quDongBiaoHao = getText(ul.get(5));

        //高端配置

        Elements scrollPics = element.getElementsByClass("scrollPic");
        if (scrollPics.size()>2) {
            Element scrollPic = scrollPics.get(0);
            if (scrollPic != null) {
                Element ul1 = scrollPic.getElementsByTag("ul").get(0);
                Elements li = ul1.getElementsByTag("li");
                for (Element e :
                        li) {
                    Element p = e.getElementsByTag("p").get(0);
                    GaoDuanPeiZhi += p.text();
                }
            }
        }
//        System.out.println("------------>"+quDongBiaoHao);
        CommonUtil.outputRun("[run] CarConfig 结束");
        HashMap<String, String> textAll = getTextAll(faDongji, bianSuQi, cheLiangJiBie, yanSe, ranYouBiaoHao, quDongBiaoHao,GaoDuanPeiZhi);
        return textAll;
    }

    private String getText(Element element){
        String text = element.text().
                substring(6).trim();
        return text;
    }
    /**
     * 打包
     * @param strings
     * @return
     */
    public HashMap<String, String> getTextAll(String... strings){
        HashMap<String,String> message  = new HashMap<String, String>();
        for (int i = 0; i <CONFIG_INDEX.length ; i++) {
            message.put(CONFIG_INDEX[i],strings[i]);
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
        Element anchor01 = parse.getElementById("anchor02");
        CarConfig carConfig = new CarConfig(anchor01);
//        carConfig.HTMLshunt();
    }
}
