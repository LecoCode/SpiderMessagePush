package com.bangtaoche.messagepush.client.analysis;

import com.bangtaoche.messagepush.client.analysis.pojo.WholeX;
import com.bangtaoche.messagepush.client.analysis.pojo.bean.carBean;
import com.bangtaoche.messagepush.client.analysis.pojo.bean.mHtmlPages;
import com.bangtaoche.messagepush.client.analysis.pojo.result.CheResultProcessing;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.entity.SendCarMessage;
import com.bangtaoche.messagepush.client.entity.SendPriceMessage;
import com.bangtaoche.messagepush.client.message.MessageSends;
import com.bangtaoche.messagepush.util.CommonUtil;
import com.bangtaoche.messagepush.util.ThreadLocalClientFactory;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;
import java.util.concurrent.*;

import static com.bangtaoche.messagepush.util.CommonUtil.GeneratePage;


/**
 * HTML整体解析
 */
public class WholeAnalysis{
    //页面路径
    private String url;
    //线程池
    private ExecutorService executorService;
    private ExecutorService executorService1;
    //WebClient工厂
    private ThreadLocalClientFactory clientFactory;
    private WebClient webClient;
    private Date date = new Date();
    private RenRenCheDAO dao = new RenRenCheDAO();
    String iPs;

    /**
     * 构造方法
     * @param url 解析的页面
     * @param ThreadMax 线程池的最大值
     */
    public WholeAnalysis(String url, int ThreadMax){
       this.url=url;
       executorService = Executors.newFixedThreadPool(ThreadMax);
        executorService1 = Executors.newFixedThreadPool(ThreadMax);
       clientFactory = new ThreadLocalClientFactory();
        webClient = clientFactory.getWebClient();
    }

    /**
     * 解析开始
     */
    public String analyStart(){
        //获取WebClient对象
        ProxyConfig proxyConfig =
                webClient.getOptions().getProxyConfig();
        //获取Html页面
        Document document = null;
        try {
//            iPs="61.135.217.7:80";
            HtmlPage htmlPage = GeneratePage(webClient, url);
        //将页面信息交给Jsoup生成Dom节点树
            document = Jsoup.parse(htmlPage.asXml());
            if (document.text().contains("出错啦，您访问的页面走丢啦！")){
                return"出错啦，您访问的页面走丢啦！";
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            CommonUtil.outputERROR("[ERROR] 列表页获取到脏的IP了"+e.getMessage());
            analyStart();
        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.outputERROR("[ERROR] WholeAnalysis:"+e.getMessage());
            analyStart();
        }
        WholeX wholeX = new WholeX(document,new CheResultProcessing());
//        Set<String> twoLevelPageUrls = wholeX.analysisCarUrlAll();
        Set<String> twoLevelPageUrls = new HashSet<String>();
        List<carBean> carBeans =
                wholeX.analysisSingleLiAll();
        MessageSends messageSends = new MessageSends();
        for (carBean c:
             carBeans) {
            boolean isStatus=false;
            try {
                Che168Car che168Car =
                        dao.getChe168Car(c.getUrl());
                if (che168Car!=null){
                    int newStatus = che168Car.getNewStatus();
                    String shangXing = c.getShangXing();
                    int news = isNewStatus(shangXing);
                    if (news!=-1){
                        isStatus=true;
                        if (news!=newStatus){
                            /**
                             * 取消上新
                             */
                            che168Car.setNewStatus(2);
                            SendCarMessage sendCarMessage = new SendCarMessage();
                            sendCarMessage.setCarId(che168Car.getCarId());
                            sendCarMessage.setSourceId(5);
                            messageSends.sendMessageCarCancleNew(sendCarMessage);
                        }
                    }
                    double inshoujia =isJiaQian(c.getShouJia());
                    double ownerPrice = che168Car.getOwnerPrice();
                    if (inshoujia!=ownerPrice){
                        isStatus=true;
                        if (inshoujia>ownerPrice){
                           //如果新价格大于旧价格：取消降价
                            che168Car.setOwnerPrice(inshoujia);
                            che168Car.setPriceReduction(2);
                            double price = inshoujia-ownerPrice;//升了多少钱
                            SendPriceMessage sendPriceMessage = new SendPriceMessage();
                            sendPriceMessage.setCarId(che168Car.getCarId());
                            sendPriceMessage.setPrice(inshoujia);
                            sendPriceMessage.setPriceChange(price);
                            sendPriceMessage.setSourceId(5);

                            messageSends.sendMessagePriceCancleDown(sendPriceMessage);
                        }else if (inshoujia<ownerPrice){
                            //如果新价格小于旧价格：降价
                            che168Car.setOwnerPrice(inshoujia);
                            che168Car.setPriceReduction(1);
                            double price = ownerPrice-inshoujia;//降了多少钱
                            SendPriceMessage sendPriceMessage = new SendPriceMessage();
                            sendPriceMessage.setCarId(che168Car.getCarId());
                            sendPriceMessage.setPrice(inshoujia);
                            sendPriceMessage.setPriceChange(price);
                            sendPriceMessage.setSourceId(5);
                            messageSends.sendMessagePriceCancleDown(sendPriceMessage);
                        }
                    }
                    if (isStatus){
                        dao.updateChe168Car(che168Car);
                    }
                }else{
                    /**
                     * 上新
                     */
                    twoLevelPageUrls.add(c.getUrl());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //启动线程解析二级页面
        try {
            ThreadStarts(twoLevelPageUrls);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    private double isJiaQian(String shouJia) {
        String s = (Double.parseDouble(shouJia) * 10000) + "";
        shouJia=s.substring(0,s.indexOf(".")+2);
        return Double.parseDouble(shouJia);
    }

    public void ThreadStarts(Set<String> Urls) throws ExecutionException, InterruptedException {
        List<mHtmlPages> htmlPages = new ArrayList<mHtmlPages>();
        ThreadLocalClientFactory threadLocalClientFactory = ThreadLocalClientFactory.getInstance();

        int i = 1;
        for (String u:
             Urls) {
            Future<HtmlPage> submit;
            WebClient webClient = threadLocalClientFactory.getWebClient();
            try {
                submit = executorService.submit(new AnalysisHtmlPage(webClient, u));
                HtmlPage copyhtmlPage = submit.get();
                if (copyhtmlPage!=null) {
                       htmlPages.add(new mHtmlPages(submit.get(),u));
                       CommonUtil.outputFileXXXX(submit.get().asText(),"asText");
               }
            } catch (Exception e) {
                e.printStackTrace();
               continue;
            }
            CommonUtil.outputFileXXXX(htmlPages.size()+"","htmls");
        }

        CommonUtil.outputFileXXXX(htmlPages.size()+"","htmls");
        for (mHtmlPages h:
                htmlPages) {
            executorService1.execute(new WholeAnalysisRunnable(h.getUrls(),h.getHtmlPage()));
        }
        executorService.shutdown();
        executorService1.shutdown();


    }

    public static void main(String []args){

        /*
        "https://www.che168.com/beijing/a0_0msdgscncgpi1ltocsp"+index+"exb112y96x0/"
"https://www.che168.com/beijing/a0_0msdgscncgpi1ltocsp"+index+"exb112y96x0/"

https://www.che168.com/beijing/a0_0msdgscncgpi1ltocsp1exb112y96x0/
         */
//        new Thread(new CreateAgencyIP()).start();;
        String url = "https://www.che168.com/beijing/a0_0msdgscncgpi1ltocsp3exb112y96x0/";
        WholeAnalysis wholeAnalysis = new WholeAnalysis(url,10);
        wholeAnalysis.analyStart();
//        wholeAnalysis.ThreadStart(sets);


    }

    public static int isNewStatus(String news){
        int status=-1;
        if ("新上".equals(news)){
            status=1;
        }else if ("-".equals(news)){
            status=2;
        }
        return status;
    }


    class AnalysisHtmlPage implements Callable<HtmlPage>{
        WebClient webClient;
        String url;
        public AnalysisHtmlPage(WebClient webClient,String url){
           this.webClient=webClient;
           this.url=url;
        }

        public HtmlPage call() throws Exception {
            HtmlPage htmlPage = GeneratePage(webClient, url);
            CommonUtil.outputFileXXXX(htmlPage.asText(),"Callable");
            return htmlPage;
        }
    }

}
