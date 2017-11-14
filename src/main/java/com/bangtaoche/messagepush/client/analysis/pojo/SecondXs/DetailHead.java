package com.bangtaoche.messagepush.client.analysis.pojo.SecondXs;

import com.bangtaoche.messagepush.util.CommonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class DetailHead {
    public static final String [] DATAIL_INDEX = new String []{"名称","价格","车图片","比新车省","新车税","过户","里程","上牌","档位","排量","所在地","联系","看车地点","发布时间","已售状态"};
    private static final String UrlPath="https:";
    private Element element;
    private Element imagNode;
    private Element contextNode;
    public DetailHead(Element element){
        this.element=element;

    }

    /**
     * HTML分流
     */
    public HashMap<String, String> HTMLshunt(){
        Element divs = element.getElementsByTag("div").get(0);
        imagNode = divs.getElementsByClass("car-foucs").get(0);
        contextNode = divs.getElementsByClass("car-info").get(0);
        HashMap<String, String> DetailHeadContext = null;
        try {
            DetailHeadContext = contextNodeAnalysis();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtil.outputERROR("[ERROR] DetailHead Exception:"+e.getMessage());
            HTMLshunt();
        }
//        CommonUtil.outputFileINFO("新车价："+DetailHeadContext.get("新车税"));
        return DetailHeadContext;
    }

    /**
     * 获取图片
     * @return 图片URL集合
     */
    private String ImageNodeAnalysis(){
//        Set<String> images=new HashSet<String>();
        String images = new String();
        Element ul = imagNode.getElementsByTag("ul").get(0);
        Elements li = ul.getElementsByTag("li");
        for (Element el:
             li) {
            String alt = el.attr("alt");
//            System.out.println(alt);
//            images.add(UrlPath+alt);
            String url = UrlPath+alt;
            images +=url+",";
        }
        /*
          {"1":"www.baidu.com",}
         */
        StringBuilder JSONImage = new StringBuilder("{");
        String[] split = images.split(",");
        for (int i = 0; i <split.length; i++) {
            String key = (i+1)+"";
            String value = split[i];
            String jsonString = "\""+key+"\":"+"\""+value+"\",";
            JSONImage.append(jsonString);
        }
        JSONImage.append("}");
        return JSONImage.toString();
    }

    //解析处理
    private HashMap<String,String> contextNodeAnalysis() throws Exception{
        String name ="-";//名称
        String jiaqian ="-";//价钱
        String images = "-";//介绍图片
        String sheng ="-";//比新车省了
        String xingJiaQian ="-";//新车含税价格
        String guoHuFei ="-";//是否包含过户费
        String liCheng ="-";//行驶里程
        String shouCiPaiZhao ="-"; //首次上牌照
        String dangWei ="-";//档位
        String paiLiang ="-";//排量
        String suoZaiDi ="-"; //所在地
        String iphone ="-";//电话
        String kanCheDiDian ="-";//看车地点
        String faBuShiJian ="-"; //发布时间
        String yishouZhuangTai ="-";//已售状态

        Element car_title = contextNode.
                getElementsByClass("car-title").get(0);
        name = car_title.
                getElementsByTag("h2").get(0).text();
//        System.out.println("----------->"+name);


        Element car_key_fn_clear = contextNode.
                getElementsByClass("car-key fn-clear").get(0);
        Element ins = car_key_fn_clear
                .getElementsByTag("ins").get(0);
        jiaqian = ins.text().
                substring(1, ins.text().length());
//        System.out.println("----------->"+jiaqian);

        images =ImageNodeAnalysis();

        Element base_compriceHead = car_key_fn_clear.
                getElementById("base_compriceHead");
        sheng = base_compriceHead.
                text().substring(5,base_compriceHead.text().length()-1);
//        System.out.println("----------->"+sheng);

        //67.10万
        if (car_key_fn_clear.getElementById("CarNewPrice").text().contains("2")){
            xingJiaQian = car_key_fn_clear
                    .getElementById("CarNewPrice").text();
        }

//        CommonUtil.outputFileINFO("新车价钱："+xingJiaQian);
//        System.out.println("----------->"+xingJiaQian);


        guoHuFei = car_key_fn_clear.
                getElementsByClass("car-price").get(0).getElementsByClass("tag").get(0).text();
//        System.out.println("----------->"+guoHuFei);
        //----------------------------------


        //车辆信息
        Element details = contextNode.
                getElementsByClass("details").get(0);
        Element ul = details.
                getElementsByTag("ul").get(0);
        Elements li = ul.
                getElementsByTag("li");
        liCheng = li.get(0).
                getElementsByTag("span").get(0).text();
//        System.out.println("----------->"+liCheng);


        shouCiPaiZhao = li.get(1).
                getElementsByTag("span").get(0).text();
//        System.out.println("----------->"+shouCiPaiZhao);


        String dw_pl = li.get(2).
                getElementsByTag("span").get(0).text();
        String[] split = dw_pl.split("／");
        dangWei = split[0];
//        System.out.println("----------->"+dangWei);


        paiLiang = split[1];
//        System.out.println("----------->"+paiLiang);


        suoZaiDi = li.get(3).
                getElementsByTag("span").get(0).text();
//        System.out.println("----------->"+suoZaiDi);


        //联系人信息
        Element car_results_fn_clear = contextNode.
                getElementsByClass("car-results fn-clear").get(0);
        iphone = car_results_fn_clear.
                getElementsByClass("btn btn-iphone3").get(0).text();

        CommonUtil.outputFileXXXX(iphone,"iphone_match");
//        System.out.println("----------->"+iphone);


        String car_address = contextNode.
                getElementsByClass("car-address").get(0).text();
        kanCheDiDian = car_address.substring(0,car_address.indexOf("发")).trim().substring(5);

//        System.out.println("----------->"+kanCheDiDian);


        faBuShiJian = car_address.substring(car_address.lastIndexOf("发布时间：")+5);
//        System.out.println("----------->"+faBuShiJian);

        CommonUtil.outputRun("[run] DetailHead 结束");

        /*


         */
        HashMap<String, String> textAll = getTextAll(name, jiaqian,images, sheng, xingJiaQian, guoHuFei, liCheng, shouCiPaiZhao, dangWei, paiLiang, suoZaiDi, iphone, kanCheDiDian, faBuShiJian, yishouZhuangTai);
        return textAll;
    }
    /**
     * 打包
     * @param strings
     * @return
     */
    public HashMap<String, String> getTextAll(String... strings){
        HashMap<String,String> message  = new HashMap<String, String>();
        for (int i = 0; i <DATAIL_INDEX.length; i++) {
            message.put(DATAIL_INDEX[i],strings[i]);
        }
        return message;
    }

    //----------------------------
    public static void main(String []args) throws IOException {
//        String url="https://www.che168.com/dealer/127110/24087113.html?pvareaid=100519#pos=35#page=1#rtype=0#isrecom=2#filter=0aa0_0a0_0a0_0#module=10";
//        WebClient webClient = new WebClient();
//        webClient.getOptions().setJavaScriptEnabled(true);
//        webClient.getOptions().setCssEnabled(false);
//        webClient.getOptions().setRedirectEnabled(true);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//        webClient.getOptions().setTimeout(5000);
//        HtmlPage page =webClient.getPage(url);
//        Document parse = Jsoup.parse(page.asXml());
//        Element car_warp_content_fn_clear =
//                parse.getElementsByClass("car-warp content fn-clear").get(0);
//        DetailHead detailHead = new DetailHead(car_warp_content_fn_clear);
////        detailHead.contextNodeAnalysis();
////        detailHead.ImageNodeAnalysis();
//        HashMap<String, String> stringStringHashMap = detailHead.HTMLshunt();
//        for (String s:
//             stringStringHashMap.values()) {
//            System.out.println(s);
//        }
        String s ="联系人：李飞  发布时间：156-4156-156";
        String substring = s.substring(s.lastIndexOf("发布时间：")+5);
        System.out.println(substring);
    }


}
