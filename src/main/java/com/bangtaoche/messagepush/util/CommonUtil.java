package com.bangtaoche.messagepush.util;

import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.IP;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具
 */
public class CommonUtil {
    public static List<String> IPP_OOL = new ArrayList<String>();
    public static List<IP> ip;
    private static RenRenCheDAO renRenCheDAO = new RenRenCheDAO();
    private static String WINDOWS_PATH="E:/testfile/2/";
    private static String LINUX_PATH="/home/leco/Git/Log/";
    private static Random random = new Random();
    private static IP ipw_copy = new IP();
    /**
     * 使用正则表达式校验字符串
     *
     * @param pattern 表达式
     * @param str     需要校验的字符串
     * @return        结果
     */
    public static boolean Matcher(String pattern ,String str){
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(str);
        return matcher.matches();
    }
    public static String getIP(){
        IP ip = CommonUtil.ip.get((int) (CommonUtil.ip.size() * Math.random()));
        String ip1 = ip.getIP();
        int port = ip.getPort();
        outputFileXXXX("获取到的IP："+ip1+":"+port,"xip");
        return ip1+":"+port;
    }
    /**
     * 获取Html页面
     *
     * @param url  //页面地址
     * @return     //Html页面
     */
    public static HtmlPage GeneratePage(WebClient webClients,String url){
        System.out.println(IPP_OOL);
        HtmlPage htmlpage =null;
        ProxyConfig proxyConfig =
                webClients.getOptions().getProxyConfig();
        IP ipw = new IP();
        try {
            List<IP> ipd = renRenCheDAO.getIP();
            ipw = ipd.get(Math.round(ip.size()-1));
            ipw_copy=ipw;
            System.out.printf("ssssssssssssss"+ipw.toString());
        }catch (NullPointerException e){
            ipw=ipw_copy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ip = ipw.getIP();
        int prot = ipw.getPort();
        proxyConfig.setProxyHost(ip);
        proxyConfig.setProxyPort(prot);
        try {
            Page page = webClients.getPage(url);
            if (page.isHtmlPage()){
                htmlpage= (HtmlPage) page;
            }else {
                System.out.println("---------------------------");
                System.out.println("***************************");
                System.out.println("不是HTML页面");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            GeneratePage(webClients,url);
        }
        if (htmlpage!=null){
            return htmlpage;
        }
        return GeneratePage(webClients,url);
    }

    private static WebClient setHost(WebClient webClient){

        return webClient;
    }

    /**
     * 打印错误
     * @return
     */
    public static String ErrorToast(String TAG,String ErrorMessage){
        String error="************"+TAG+"："+ErrorMessage+"***************";
        return error;
    }
    public  static void outputFileXXXX(String car,String url){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+url,true);
            bw = new BufferedWriter(new OutputStreamWriter(out));
            pw = new PrintWriter(bw);
            pw.println(car.toString());
//            pw.println("**********************************************************");
//            pw.println("**********************************************************");
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();

        }
    }
    //输出详情页
    public  static void outputFileXiangXi(carDetailsBean car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"d",true);
            bw = new BufferedWriter(new OutputStreamWriter(out));
            pw = new PrintWriter(bw);
            pw.println(car.toString());
            pw.println("**********************************************************");
            pw.println("**********************************************************");
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();

        }
    }

    //输出详情页
    public  static void outputFileINFO(String s){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"w",true);
            bw = new BufferedWriter(new OutputStreamWriter(out));
            pw = new PrintWriter(bw);
            pw.println(s);
            pw.println("**********************************************************");
            pw.println("**********************************************************");
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();

        }
    }

    //-------------测试-------------------------

    @Test
    private void MatcherTest(){
        //\d+\.\d+\.\d+\.\d+
        boolean matcher = Matcher("\\d+\\.\\d+\\.\\d+\\.\\d+\\:\\d+", "123.123.123.123:52");
        System.out.println(matcher);
        //out:true

        System.out.println(5);
    }

    public static void main (String []aegs){
        Pattern patt = Pattern.compile("[^0-9]");
        Matcher matcher = patt.matcher(" 44-51");
        System.out.println("match-------------->:"+matcher.replaceAll("-"));
    }

}
