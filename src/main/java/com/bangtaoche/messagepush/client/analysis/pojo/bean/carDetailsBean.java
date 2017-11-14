package com.bangtaoche.messagepush.client.analysis.pojo.bean;

import com.bangtaoche.messagepush.util.CommonUtil;

public class carDetailsBean {
    // {"名称","价格","省钱","新税","过户","里程","上牌","档位","排量","所在地","电话","看地","发时","已售状态"};
    // {"年检到期","保险到期","质保到期","排放标准","过户次数","维修保养","车主描述"}
    // {"发动机","变速器","车辆级别","颜色","燃油标号","驱动方式"}

    private String name;//名称
    private String url;
    private String jiaqian ;//价钱
    private String images;//图片
    private String sheng;//比新车省了
    private String xingJiaQian ;//新车含税价格
    private String guoHuFei  ;//是否包含过户费
    private String liCheng  ;//行驶里程
    private String shouCiPaiZhao  ; //首次上牌照
    private String dangWei  ;//档位
    private String paiLiang  ;//排量
    private String suoZaiDi  ; //所在地
    private String iphone  ;//电话
    private String kanCheDiDian  ;//看车地点
    private String faBuShiJian  ; //发布时间
    private String yishouZhuangTai  ;//已售状态
    private String nianJian ;//年检到期
    private String baoXian ;//保险到期
    private String zhiBao ;//质保到期
    private String paiFang ;//排放标准
    private String guoHu ;//过户
    private String baoYang ;//保养
    private String GaoDuanPeiZhi;//高端配置
    private String carMessage ;//车主描述
    private String faDongji ;//发动机
    private String bianSuQi ;//变速器
    private String cheLiangJiBie ;//车辆级别
    private String yanSe ;//颜色
    private String ranYouBiaoHao ;//燃油标号
    private String quDongBiaoHao ;//驱动方式
    private String endImages;//结尾图片

    public String getGaoDuanPeiZhi() {
        return GaoDuanPeiZhi;
    }

    public void setGaoDuanPeiZhi(String gaoDuanPeiZhi) {
        GaoDuanPeiZhi = gaoDuanPeiZhi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndImages() {
        return endImages;
    }

    public void setEndImages(String endImages) {
        this.endImages = endImages;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getJiaqian() {
        return jiaqian;
    }

    public void setJiaqian(String jiaqian) {
        this.jiaqian = jiaqian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getXingJiaQian() {
        CommonUtil.outputFileINFO("[carDatailsBean]"+xingJiaQian);
        return xingJiaQian;
    }

    public void setXingJiaQian(String xingJiaQian) {
        this.xingJiaQian = xingJiaQian;
    }

    public String getGuoHuFei() {
        return guoHuFei;
    }

    public void setGuoHuFei(String guoHuFei) {
        this.guoHuFei = guoHuFei;
    }

    public String getLiCheng() {
        return liCheng;
    }

    public void setLiCheng(String liCheng) {
        this.liCheng = liCheng;
    }

    public String getShouCiPaiZhao() {
        return shouCiPaiZhao;
    }

    public void setShouCiPaiZhao(String shouCiPaiZhao) {
        this.shouCiPaiZhao = shouCiPaiZhao;
    }

    public String getDangWei() {
        return dangWei;
    }

    public void setDangWei(String dangWei) {
        this.dangWei = dangWei;
    }

    public String getPaiLiang() {
        return paiLiang;
    }

    public void setPaiLiang(String paiLiang) {
        this.paiLiang = paiLiang;
    }

    public String getSuoZaiDi() {
        return suoZaiDi;
    }

    public void setSuoZaiDi(String suoZaiDi) {
        this.suoZaiDi = suoZaiDi;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getKanCheDiDian() {
        return kanCheDiDian;
    }

    public void setKanCheDiDian(String kanCheDiDian) {
        this.kanCheDiDian = kanCheDiDian;
    }

    public String getFaBuShiJian() {
        return faBuShiJian;
    }

    public void setFaBuShiJian(String faBuShiJian) {
        this.faBuShiJian = faBuShiJian;
    }

    public String getYishouZhuangTai() {
        return yishouZhuangTai;
    }

    public void setYishouZhuangTai(String yishouZhuangTai) {
        this.yishouZhuangTai = yishouZhuangTai;
    }

    public String getNianJian() {
        return nianJian;
    }

    public void setNianJian(String nianJian) {
        this.nianJian = nianJian;
    }

    public String getBaoXian() {
        return baoXian;
    }

    public void setBaoXian(String baoXian) {
        this.baoXian = baoXian;
    }

    public String getZhiBao() {
        return zhiBao;
    }

    public void setZhiBao(String zhiBao) {
        this.zhiBao = zhiBao;
    }

    public String getPaiFang() {
        return paiFang;
    }

    public void setPaiFang(String paiFang) {
        this.paiFang = paiFang;
    }

    public String getGuoHu() {
        return guoHu;
    }

    public void setGuoHu(String guoHu) {
        this.guoHu = guoHu;
    }

    public String getBaoYang() {
        return baoYang;
    }

    public void setBaoYang(String baoYang) {
        this.baoYang = baoYang;
    }

    public String getCarMessage() {
        return carMessage;
    }

    public void setCarMessage(String carMessage) {
        this.carMessage = carMessage;
    }

    public String getFaDongji() {
        return faDongji;
    }

    public void setFaDongji(String faDongji) {
        this.faDongji = faDongji;
    }

    public String getBianSuQi() {
        return bianSuQi;
    }

    public void setBianSuQi(String bianSuQi) {
        this.bianSuQi = bianSuQi;
    }

    public String getCheLiangJiBie() {
        return cheLiangJiBie;
    }

    public void setCheLiangJiBie(String cheLiangJiBie) {
        this.cheLiangJiBie = cheLiangJiBie;
    }

    public String getYanSe() {
        return yanSe;
    }

    public void setYanSe(String yanSe) {
        this.yanSe = yanSe;
    }

    public String getRanYouBiaoHao() {
        return ranYouBiaoHao;
    }

    public void setRanYouBiaoHao(String ranYouBiaoHao) {
        this.ranYouBiaoHao = ranYouBiaoHao;
    }

    public String getQuDongBiaoHao() {
        return quDongBiaoHao;
    }

    public void setQuDongBiaoHao(String quDongBiaoHao) {
        this.quDongBiaoHao = quDongBiaoHao;
    }


    @Override
    public String toString() {
        return  "  车名 ='" + name + '\'' +
                ", 链接='" + url + '\'' +
                ", 价钱='" + jiaqian + '\'' +
                ", 图片='" + images + '\'' +
                ", 比买新车便宜='" + sheng + '\'' +
                ", 新车价钱='" + xingJiaQian + '\'' +
                ", 是否包含过户费='" + guoHuFei + '\'' +
                ", 里程='" + liCheng + '\'' +
                ", 首次上牌照='" + shouCiPaiZhao + '\'' +
                ", 档位='" + dangWei + '\'' +
                ", 排量='" + paiLiang + '\'' +
                ", 所在地='" + suoZaiDi + '\'' +
                ", 联系方式='" + iphone + '\'' +
                ", 看车地点='" + kanCheDiDian + '\'' +
                ", 发布时间='" + faBuShiJian + '\'' +
                ", 已售状态='" + yishouZhuangTai + '\'' +
                ", 年检时间='" + nianJian + '\'' +
                ", 保险时间='" + baoXian + '\'' +
                ", 质保时间='" + zhiBao + '\'' +
                ", 排放='" + paiFang + '\'' +
                ", 过户次数='" + guoHu + '\'' +
                ", 保养='" + baoYang + '\'' +
                ", 车主描述='" + carMessage + '\'' +
                ", 发动机='" + faDongji + '\'' +
                ", 变速器='" + bianSuQi + '\'' +
                ", 车辆级别='" + cheLiangJiBie + '\'' +
                ", 颜色='" + yanSe + '\'' +
                ", 燃油标号='" + ranYouBiaoHao + '\'' +
                ", 驱动标号='" + quDongBiaoHao + '\'' +
                ", 结尾图片='" + endImages + '\'' +
                '}';
    }
}
