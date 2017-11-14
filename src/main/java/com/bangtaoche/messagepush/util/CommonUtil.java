package com.bangtaoche.messagepush.util;

import com.bangtaoche.messagepush.client.analysis.pojo.bean.carBean;
import com.bangtaoche.messagepush.client.analysis.pojo.bean.carDetailsBean;
import com.bangtaoche.messagepush.client.dao.RenRenCheDAO;
import com.bangtaoche.messagepush.client.entity.IP;
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
            int rund_index=random.nextInt(ipd.size());
            ipw = ipd.get(rund_index);
            CommonUtil.outputFileXXXX(ipw.toString(),"ipsssssss");
        } catch (Exception e) {
            e.printStackTrace();
            GeneratePage(webClients, url);
        }
        String ip = ipw.getIP();
        int prot = ipw.getPort();
        proxyConfig.setProxyHost(ip);
        proxyConfig.setProxyPort(prot);
        try {
            HtmlPage page = webClients.getPage(url);
            if (page.isHtmlPage()){
                htmlpage=  page;
                CommonUtil.outputINFO("[INFO] GeneratePage:"+htmlpage.getBaseURI());
            }else {
               CommonUtil.outputERROR("[ERROR] 不是一个HTML页面"+url);
            }
            if (htmlpage!=null){
                CommonUtil.outputINFO("页面解析成功");
                return htmlpage;
            }
        }catch (Exception e){
            e.printStackTrace();
            CommonUtil.outputERROR("[ERROR] GeneratePage: Exception"+e.getMessage() );
            GeneratePage(webClients,url);
        }

        return GeneratePage(webClients,url);
    }

    private static WebClient setHost(WebClient webClient){

        return webClient;
    }
//    /**
//     * 解析一级页面的车辆<ul>标签
//     * @param document Html文档树
//     * @return  //解析后的节点树
//     */
//    public static Elements analysisOneLevelUl(Document document){
//        Elements lis = document.getElementsByClass("list-base").get(0).getElementsByTag("li");
//        return lis;
//    }

//    /**
//     * 获取单个节点中a标签的href属性
//     * @param element 节点
//     * @return        url地址
//     */
//    public static String analysisOneLevelLi(Element element){
//        String attr = element.getElementsByTag("a").get(0).attr("href");
//        return attr;
//    }

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
    //输出列表页
    public  static void outputFileList(carBean car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"b",true);
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
    //输出ip
    public  static void outputFileIP(String car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"ip",true);
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
    //错误日志的打印
    public  static void outputERROR(String car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"error",true);
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
    public  static void outputINFO(String car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"info",true);
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
    public  static void outputRun(String car){
        FileOutputStream out = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            out = new FileOutputStream(LINUX_PATH+"run",true);
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
