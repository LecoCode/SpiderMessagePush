package com.bangtaoche.messagepush.client.analysis.pojo;

import com.bangtaoche.messagepush.client.analysis.pojo.SecondXs.BaseMessages;
import com.bangtaoche.messagepush.client.analysis.pojo.SecondXs.CarConfig;
import com.bangtaoche.messagepush.client.analysis.pojo.SecondXs.DetailHead;
import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;
import com.bangtaoche.messagepush.client.analysis.pojo.result.ResultProcessing;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CarResultInterface;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

/**
 * 详情页解析
 */
public class SecondX {
    private static final String HTTPS="https:";
    private Document document;
    private Element car_warp_content_fn_clear;
    private Element anchor01;
    private Element anchor04;
    private Element anchor02;
    private CarResultInterface resultProcessing;
    private String url;
    public SecondX(Document document, CarResultInterface resultProcessing,String url){
        if(document.text().contains("出错啦，您访问的页面走丢啦！")){
            Thread.interrupted();
        }
        this.url=url;
        this.resultProcessing=resultProcessing;
        this.document=document;
        SplitHtml();
    }
    /**
     * 截取Html文件，进行分块
     */
    public void SplitHtml(){
        //详情头

        car_warp_content_fn_clear = document.getElementsByClass("car-warp content fn-clear").get(0);
        //基本信息
        anchor01 = document.getElementById("anchor01");
        //车辆配置
        anchor02 = document.getElementById("anchor02");
        //车辆图片
        anchor04 = document.getElementById("anchor04");
    }

    //获取车辆图片
    public String imageShunt(Element element){
//        Set<String> urls=new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        Elements li = element.
                getElementsByTag("ul").get(0).getElementsByTag("li");
        System.out.println("length:"+li.size());
        for (Element e:
             li) {
            System.out.println(e);
            String attr = e.getElementsByTag("img").get(0).attr("src2");
            String url = HTTPS+attr;
//            urls.add(url);
            sb.append(url+",");
        }
        if (sb==null){
            sb.append("-");
        }
        String s = sb.toString();
        StringBuilder JSONImage = new StringBuilder("{");
        String[] split = s.split(",");
        for (int i = 0; i <split.length; i++) {
            String key = (i+1)+"";
            String value = split[i];
            String jsonString = "\""+key+"\":"+"\""+value+"\",";
            JSONImage.append(jsonString);
        }
        JSONImage.append("}");
        return JSONImage.toString();
    }

    //HTML分流处理
    public void HTMLshunt(){
        carDetailsBean carDetailsBean = new carDetailsBean();
        //头信息处理
        DetailHead detailHead = new DetailHead(car_warp_content_fn_clear);
        HashMap<String, String> DetailHeadContext = detailHead.HTMLshunt();
//        DetailHead.DATAIL_INDEX;
//{"名称","价格","车图片","比新车省","新车税","过户","里程","上牌","档位","排量","所在地","电话","看车地点","发布时间","已售状态"}

        carDetailsBean.setName(DetailHeadContext.get(DetailHead.DATAIL_INDEX[0]));
        carDetailsBean.setJiaqian(DetailHeadContext.get(DetailHead.DATAIL_INDEX[1]));
        carDetailsBean.setImages(DetailHeadContext.get(DetailHead.DATAIL_INDEX[2]));
        carDetailsBean.setSheng(DetailHeadContext.get(DetailHead.DATAIL_INDEX[3]));
        carDetailsBean.setXingJiaQian(DetailHeadContext.get(DetailHead.DATAIL_INDEX[4]));
        carDetailsBean.setGuoHuFei(DetailHeadContext.get(DetailHead.DATAIL_INDEX[5]));
        carDetailsBean.setLiCheng(DetailHeadContext.get(DetailHead.DATAIL_INDEX[6]));
        carDetailsBean.setShouCiPaiZhao(DetailHeadContext.get(DetailHead.DATAIL_INDEX[7]));
        carDetailsBean.setDangWei(DetailHeadContext.get(DetailHead.DATAIL_INDEX[8]));
        carDetailsBean.setPaiLiang(DetailHeadContext.get(DetailHead.DATAIL_INDEX[9]));
        carDetailsBean.setSuoZaiDi(DetailHeadContext.get(DetailHead.DATAIL_INDEX[10]));
        carDetailsBean.setIphone(DetailHeadContext.get(DetailHead.DATAIL_INDEX[11]));
        carDetailsBean.setKanCheDiDian(DetailHeadContext.get(DetailHead.DATAIL_INDEX[12]));
        carDetailsBean.setFaBuShiJian(DetailHeadContext.get(DetailHead.DATAIL_INDEX[13]));
        carDetailsBean.setYishouZhuangTai(DetailHeadContext.get(DetailHead.DATAIL_INDEX[14]));
        //基本信息
        BaseMessages baseMessages = new BaseMessages(anchor01);
        HashMap<String, String> BaseMessagesContext = null;
        try {
            BaseMessagesContext = baseMessages.HTMLshunt();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        BaseMessages.BASE_INDEX;
        //"年检到期","保险到期","质保到期","排放标准","过户次数","维修保养","车主描述"
        carDetailsBean.setNianJian(BaseMessagesContext.get(BaseMessages.BASE_INDEX[0]));
        carDetailsBean.setBaoXian(BaseMessagesContext.get(BaseMessages.BASE_INDEX[1]));
        carDetailsBean.setZhiBao(BaseMessagesContext.get(BaseMessages.BASE_INDEX[2]));
        carDetailsBean.setPaiFang(BaseMessagesContext.get(BaseMessages.BASE_INDEX[3]));
        carDetailsBean.setGuoHu(BaseMessagesContext.get(BaseMessages.BASE_INDEX[4]));
        carDetailsBean.setBaoYang(BaseMessagesContext.get(BaseMessages.BASE_INDEX[5]));
        carDetailsBean.setCarMessage(BaseMessagesContext.get(BaseMessages.BASE_INDEX[6]));
        //车辆配置
        CarConfig carConfig = new CarConfig(anchor02);
        HashMap<String, String> carConfigContext = null;
        try {
            carConfigContext = carConfig.HTMLshunt();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        CarConfig.CONFIG_INDEX
        //"发动机","变速器","车辆级别","颜色","燃油标号","驱动方式"
        carDetailsBean.setFaDongji(carConfigContext.get(CarConfig.CONFIG_INDEX[0]));
        carDetailsBean.setBianSuQi(carConfigContext.get(CarConfig.CONFIG_INDEX[1]));
        carDetailsBean.setCheLiangJiBie(carConfigContext.get(CarConfig.CONFIG_INDEX[2]));
        carDetailsBean.setYanSe(carConfigContext.get(CarConfig.CONFIG_INDEX[3]));
        carDetailsBean.setRanYouBiaoHao(carConfigContext.get(CarConfig.CONFIG_INDEX[4]));
        carDetailsBean.setQuDongBiaoHao(carConfigContext.get(CarConfig.CONFIG_INDEX[5]));
        carDetailsBean.setGaoDuanPeiZhi(carConfigContext.get(carConfig.CONFIG_INDEX[6]));
        //26
        //结尾车辆图片处理
        String strings = imageShunt(anchor04);
        carDetailsBean.setEndImages(strings);
//        CommonUtil.outputFileXiangXi(carDetailsBean);
        carDetailsBean.setUrl(url);
        resultProcessing.Receive(carDetailsBean);

    }



    //----------------------------
    public static void main(String []args) throws IOException {
        //https://www.che168.com/dealer/263568/24341824.html#pvareaid=100864#pos=2#isRecom=1#rtype=3#page=1#filter=0a0a0_0a0_0a0_0#module=3

        //https://www.che168.com/dealer/127110/24087113.html?pvareaid=100519#pos=35#page=1#rtype=0#isrecom=2#filter=0aa0_0a0_0a0_0#module=10"
        String url="https://www.che168.com/dealer/164392/23358911.html?pvareaid=100519";
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(5000);
//        HtmlPage page =CommonUtil.GeneratePage(webClient,url,"");
        HtmlPage page = webClient.getPage(url);
        Document parse = Jsoup.parse(page.asXml());
        SecondX secondX = new SecondX(parse,new ResultProcessing(),"https://www.che168.com/dealer/103289/24246592.html?pvareaid=100519");
//        secondX.HTMLshunt();

    }
}
