package com.bangtaoche.messagepush.client.analysis.pojo.bean;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class mHtmlPages {
    private HtmlPage htmlPage;
    private String urls;

    public mHtmlPages(HtmlPage htmlPage, String urls) {
        this.htmlPage = htmlPage;
        this.urls = urls;
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }
    public String getUrls() {
        return urls;
    }


}
