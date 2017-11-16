package com.bangtaoche.messagepush.client.analysis.pojo;

import com.bangtaoche.messagepush.client.analysis.pojo.bean.carBean;
import com.bangtaoche.messagepush.client.analysis.pojo.result.CheResultProcessing;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CheResultInterface;
import com.bangtaoche.messagepush.client.service.SpoderService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 列表页解析
 */
public class WholeX {
    private static final String UrlPath="https:";
    private static final String che168Path="https://www.che168.com";
    //结果Bean
    private carBean carBeans;
    //要分析的DOM树
    private Document document;
    //li节点集合
    private Elements lis;
    private Element a3;
    CheResultInterface cheResultInterface;
    public WholeX(Document document, CheResultInterface cheResultInterface){
        this.document=document;
        this.cheResultInterface=cheResultInterface;
        isCarLiNull();
    }


    //解析HTML页面中车辆列表的ul节点生成单个的li集合
    private void analysisCarList(){

        a3 = document.getElementById("a3");
        Element element1 = a3.getElementsByClass("list-source-wrap")
                .get(0)
                .getElementsByClass("tab-content")
                .get(0);
        Element elementById = element1.getElementById("tab-11");
        if (elementById.getElementsByClass("list-photo").size()>0){
            Element element2 = elementById.getElementsByClass("list-photo").get(0);
            Element element =
                    element2.getElementsByClass("fn-clear").get(0);
            lis = element.getElementsByTag("li");
        }

//        System.out.println("lis:"+lis.text());
//        setDiZhiMax();
    }

    /**
     * 解析整个li标签
     */
    public List<carBean> analysisSingleLiAll(){
        if (lis!=null){
            List<carBean> carBeans = new ArrayList<carBean>();
            for (Element e:
                    lis) {
                try {
                    carBean carBean = analysisSingleLi(e);
                    carBeans.add(carBean);
                }catch (Exception e1){
                    continue;
                }
            }
            return carBeans;
        }
       return null;
    }
    /**
     * 解析单个li
     * @param element
     */
    private carBean analysisSingleLi(Element element) throws Exception {

        //获取a标签
        Element a = element.getElementsByTag("a").get(0);
        //获取a标签中内容div节点
        Element div = a.getElementsByTag("div").get(0);
        //获取内容中介绍内容div节点
        Element divContext = div.getElementsByClass("time").get(0);
//        CommonUtil.outputFileINFO(a.toString());
//        CommonUtil.outputFileINFO(div.toString());
        String divContextText = divContext.text();
        //3.9万公里／2016-04／ 石家庄
        String Context_gongli = divContextText.split("／")[0];
        String Context_shijian = divContextText.split("／")[1];
        String Context_dizhi = divContextText.split("／")[2];
//        String[] split = Context_1.split("／");
        //获取内容中价钱内容div节点
        Element qian = div.getElementsByTag("div").get(2);
        //-------------------------------
        String url="-";
        String name="-";
        String gongLi="-";
        String time="-";
        String diZhi="-";
        String diZhiUrl="-";
        String shouJia="-";
        String yuanJia="-";
        String imagePath="-";
        String xinShang="-";
        //--------------------------------
        //解析URL
        url = analysisCarUrl(element);
        //解析车的名字
        name = analysisCarName(div);
        //解析车的公里表
        gongLi = Context_gongli;
        //解析时间
        time = Context_shijian;
        //解析地址
        diZhi = Context_dizhi;
        //解析地址URL
        diZhiUrl =url;
        //解析售价
           Element b = qian.getElementsByTag("b").get(0);
        shouJia = b.text();
        //解析原价
           Element s = qian.getElementsByTag("s").get(0);
        yuanJia = s.text();
        //解析图片
           Element img = a.getElementsByTag("img").get(0);
        imagePath = UrlPath+img.attr("src");

        Element tag_area = element.getElementsByClass("tag-area").get(0);
//        tag_area.getElementsByClass("tag-newup")
        if (tag_area.text().contains("新上")){
            xinShang="新上";
        }


        carBean carBean = new carBean();
        carBean.setUrl(url);
        carBean.setName(name);
        carBean.setGongLi(gongLi);
        carBean.setTime(time);
        carBean.setDiZhi(diZhi);
        carBean.setDiZhiUrl(diZhiUrl);
        carBean.setShouJia(shouJia);
        carBean.setYuanJia(yuanJia);
        carBean.setImagePath(imagePath);
        carBean.setShangXing(xinShang);
        cheResultInterface.Receive(carBean);
        return carBean;
    }
        //获取CarBean以及将Bean传递给回调接口
    public carBean getCarBean(){
        if (carBeans==null)new NullPointerException("carBean为空！");
        return carBeans;
    }
    /**
     * 获取单个li标签
     * @param index 下标
     * @return 单个节点
     */
    public Element getElementByLi(int index){
        return lis.get(index);
    }
    /**
     * 获取车的名称
     * @param element div节点
     * @return  名称
     */
       public String analysisCarName(Element element){
           Element h3 = element.getElementsByTag("h3").get(0);
           String name = h3.text();
           return name;
       }


//       //设置地址最大值
       public void setDiZhiMax(){
           //获取底部地址栏
           Element ListEl = a3.getElementsByClass("page fn-clear").get(0);
           //获取地址栏中的a标签
           Elements a = ListEl.getElementsByTag("a");
           //获取最后一个a标签的内容，也就是最大URL数目
           int a_max = Integer.parseInt(a.get(a.size() - 2).text());
           //做一个原来值的备份
           int copy_index_max = SpoderService.INDEX_MAX;
           //将获取的值付给INDEX_MAX
           SpoderService.INDEX_MAX=a_max;
           if (copy_index_max==SpoderService.INDEX_MAX){
           }
       }
    /**
     * 解析所有车节点的URL
     * @return URL集合
     */
       public Set<String> analysisCarUrlAll(){

           Set<String> urls = new HashSet<String>();
           for (Element e:
                lis) {
               String url = analysisCarUrl(e);
               if (url!=null){
                   urls.add(url);
               }
           }
           return urls;
       }
    /**
     * 解析单个车辆的详情页地址
     * @param element a节点
     * @return   URL地址
     */
    public String analysisCarUrl(Element element){

        Element a = element.getElementsByTag("a").get(0);
        String href = a.attr("href");
        //  https://www.che168.com/dealer/79434/24336284.html?pvareaid=100519#pos=36#page=1#rtype=0#isrecom=2#filter=0aa0_0a0_0a0_0#module=10
        String url = perfectURL(href);
        if(url.contains("i.che168.com")){
            return null;
        }else {
            return url;
        }
    }
    //完善URL
    private String perfectURL(String url){
        return che168Path+url;
    }
    //检查lis是否为空如果为空则调用analysisCarList方法解析Li节点
    private void isCarLiNull(){
        if (lis==null){
            try {
                analysisCarList();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("页面逻辑错误");
            }
        }
    }

    //-----------------测试---------------------------------------
    public static void main(String []args) throws IOException {
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(5000);
        String url="https://www.che168.com/beijing/benchi/";
        HtmlPage page = webClient.getPage(url);
        System.out.println(page.getUrl());
        Document parse = Jsoup.parse(page.asXml());
        WholeX wholeX = new WholeX(parse, new CheResultProcessing());
        wholeX.analysisSingleLiAll();
        Set<String> strings = wholeX.analysisCarUrlAll();
        for (String s:
             strings) {
            System.out.println(s);
        }


    }

}
