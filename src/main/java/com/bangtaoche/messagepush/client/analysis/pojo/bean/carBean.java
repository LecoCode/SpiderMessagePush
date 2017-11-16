package com.bangtaoche.messagepush.client.analysis.pojo.bean;

public class carBean {
        private String url;//URL
        private String name;//名称
        private String gongLi;//历程
        private String time;//上牌时间
        private String diZhi;//出售地址
        private String diZhiUrl;//出售地址的URL
        private String shouJia;//售价
        private String yuanJia;//原价
        private String imagePath;//图片地址
        private String shangXing;

    public String getShangXing() {
        return shangXing;
    }

    public void setShangXing(String shangXing) {
        this.shangXing = shangXing;
    }

    public String getDiZhiUrl() {
        return diZhiUrl;
    }

    public void setDiZhiUrl(String diZhiUrl) {
        this.diZhiUrl = diZhiUrl;
    }

    public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGongLi() {
            return gongLi;
        }

        public void setGongLi(String gongLi) {
            this.gongLi = gongLi;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDiZhi() {
            return diZhi;
        }

        public void setDiZhi(String diZhi) {
            this.diZhi = diZhi;
        }

        public String getShouJia() {
            return shouJia;
        }

        public void setShouJia(String shouJia) {
            this.shouJia = shouJia;
        }

        public String getYuanJia() {
            return yuanJia;
        }

        public void setYuanJia(String yuanJia) {
            this.yuanJia = yuanJia;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", 名称='" + name + '\'' +
                    ", 公里='" + gongLi + '\'' +
                    ", 发布时间='" + time + '\'' +
                    ", 城市='" + diZhi + '\'' +
                    ", 城市URL='" + diZhiUrl + '\'' +
                    ", 售价='" + shouJia + '\'' +
                    ", 原价='" + yuanJia + '\'' +
                    ", 图片地址='" + imagePath + '\'' +
                    ", 是否新上='" + shangXing + '\'' +
                    '}';
        }
    }