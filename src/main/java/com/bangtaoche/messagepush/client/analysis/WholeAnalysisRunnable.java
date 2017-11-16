package com.bangtaoche.messagepush.client.analysis;

import com.bangtaoche.messagepush.client.analysis.pojo.SecondX;
import com.bangtaoche.messagepush.client.analysis.pojo.result.ResultProcessing;
import com.bangtaoche.messagepush.client.analysis.resultinterface.CarResultInterface;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.util.CommonUtil;
import com.bangtaoche.messagepush.util.ThreadLocalClientFactory;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

/**
 *  多线程解析二级页面
 */
public class WholeAnalysisRunnable implements Runnable {

    private String url;//页面地址
    private CarResultInterface carResult;//结果回调接口
    private ThreadLocalClientFactory threadLocalClientFactory;
    private WebClient webClient;
    HtmlPage htmlPage;
    private Date date = new Date();
    private String ips;




    private HtmlPage page;
    /**
     * 多线程构造
     * @param url  页面地址
     * @param carResult 解析后的结果回调接口
     */
    public WholeAnalysisRunnable(String url, CarResultInterface carResult){
        this.url=url;
        this.carResult=carResult;
        threadLocalClientFactory = new ThreadLocalClientFactory();
        webClient = threadLocalClientFactory.getWebClient();
    }

    public WholeAnalysisRunnable(String u, HtmlPage page){
        if (page==null){
        }
        this.url=u;
        this.page=page;

    }
    public void run() {
        CommonUtil.outputFileXXXX("开始解析了详情页","jxxq");
        Document parse = Jsoup.parse(page.asXml());
        SecondX secondX = new SecondX(parse,new ResultProcessing(),url);
        secondX.HTMLshunt();
        /*
        上新 下架
         */
        CommonUtil.outputFileXXXX("详情页解析完了一个","endjxxq");


 }
    private void analysisPage(WebClient webClient){
        //获取构建HTML页面对象
        htmlPage = CommonUtil.GeneratePage(webClient, url);
        Document documentPage=null;
        try {
            documentPage = Jsoup.parse(htmlPage.asXml());
        }catch (NullPointerException e){
            analysisPage(webClient);
        }
        if(documentPage.text().contains("出错啦，您访问的页面走丢啦！")){
            Thread.interrupted();
        }
         SecondX secondX = new SecondX(documentPage,new ResultProcessing(),url);


    }
    public void output(String text){
        OutputStream outputStream = null;
        try {//461113085268
            outputStream = new FileOutputStream("/home/leco/d",true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            new FileNotFoundException("没有这个文件！");
        }
        PrintWriter pw = new PrintWriter(outputStream);
        pw.print(text);
        pw.flush();
        pw.close();
    }

public static void main(String []args){

}

    //填充Che168Car对象
    private Che168Car FillEntity(){

        return null;
    }
}
