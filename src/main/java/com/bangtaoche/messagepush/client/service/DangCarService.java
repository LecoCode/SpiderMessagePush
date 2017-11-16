package com.bangtaoche.messagepush.client.service;

import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.IP;
import com.bangtaoche.messagepush.util.CommonUtil;
import com.bangtaoche.messagepush.util.ThreadLocalClientFactory;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DangCarService {
   private static  ThreadLocalClientFactory instance = ThreadLocalClientFactory.getInstance();


    public static HtmlPage getPages(){
        try {


        RenRenCheDAO dao =new RenRenCheDAO();

        WebClient webClient = instance.getWebClient();
        ProxyConfig proxyConfig =
                webClient.getOptions().getProxyConfig();
        IP ipw = new IP();
        try {
            List<IP> ipd = dao.getIP();
            int rund_index=Math.round(ipd.size()-1);
            ipw = ipd.get(rund_index);
            CommonUtil.outputFileXXXX(ipw.toString(),"ipsssssss");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(">>>>>>>:IP获取错误");
        }
        String ip = ipw.getIP();
        int prot = ipw.getPort();
        proxyConfig.setProxyHost(ip);
        proxyConfig.setProxyPort(prot);
        HtmlPage page = webClient.getPage("http://www.51zxw.net/study.asp?vip=15381510");
        if (page.isHtmlPage()){
            return page;
        }
        }catch (Exception e){
            return getPages();
        }
        return getPages();
    }


    //http://www.51zxw.net/study.asp?vip=15381510
    public void start(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        HtmlPage page;
        while (true){
            try {
                page = getPages();
                executorService.execute(new JiFeng(page));
            }catch (Exception e){
                continue;
            }
        }


    }


    public static void main(String []args){
        DangCarService dangCarService = new DangCarService();
        dangCarService.start();
    }

    class JiFeng implements Runnable{
        HtmlPage page;
        JiFeng(HtmlPage page){
            this.page=page;
        }
        public void run() {
            try {
            System.out.println(page.asXml());
            HtmlForm frames = page.getForms().get(0);
            HtmlSubmitInput submit = frames.getInputByName("Submit");
            try {
                submit.click();
                submit.click();
            } catch (IOException e) {
                try {
                    submit.click();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            }catch (Exception e){

            }
        }
    }
}
