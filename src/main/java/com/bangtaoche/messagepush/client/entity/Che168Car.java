package com.bangtaoche.messagepush.client.entity;


import com.darengong.tools.dao.annotation.Column;
import com.darengong.tools.dao.annotation.Id;
import com.darengong.tools.dao.annotation.Table;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/22.
 */
@Table(name = "tbl_che168_car")
public class Che168Car {
    /** 车辆ID */
    @Id
    @Column(name = "car_id" )
    private long carId;

    /** 车辆名 */
    @Column(name = "car_name" )
    private String carName;

    /** 添加时间 */
    @Column(name = "add_time" )
    private Date addTime;

    /** 车主报价（万元） */
    @Column(name = "owner_price" )
    private double ownerPrice;

    /**
     * 变速箱
     */
    private String gearbox;
    
    /** 指导价（万元） */
    @Column(name = "direct_price" )
    private double directPrice;

    /** 上牌时间 */
    @Column(name = "reg_time" )
    private String regTime;

    /** 里程 */
    private double mileage;

    /** 上牌地 */
    @Column(name = "reg_city" )
    private String regCity;

    /** 排放标准 */
    private String standard;

    /** 过户次数 */
    @Column(name = "deal_count" )
    private int dealCount;

    /** 排量 */
    private String displacement;

    /** 看车地址 */
    @Column(name = "see_car_city" )
    private String seeCarCity;

    /** 交强险到期时间 */
    @Column(name = "traffic_insurance_time" )
    private String trafficInsuranceTime;

    /** 年检时间 */
    @Column(name = "year_examine_time" )
    private String yearExamineTime;

    /** 商业险到期时间 */
    @Column(name = "commercial_insurance_time" )
    private String commercialInsuranceTime;

    /** 车源编号 */
    @Column(name = "source_id" )
    private String sourceId;

    /** 高科技配置 */
    @Column(name = "high_tech_deploy" )
    private String highTechDeploy;

    /** 服务费 */
    @Column(name = "service_price" )
    private double servicePrice;

    /** 是否4s店保养（1：是；2：否；-1未知） */
    @Column(name = "4s_service" )
    private int fourService;

    /** 车辆来源URL*/
    @Column(name = "source_url")
    private String sourceUrl;
    
    /**
     * 卖家描述
     */
    @Column(name = "owner_depict")
    private String ownerDepict;
    
    /**
     * 是否降价
     */
    @Column(name = "price_reduction")
    private int priceReduction;
    
    /**
     * 是否新上架
     */
    @Column(name = "new_status")
    private int newStatus;
    
    /**
     * 是否在售
     */
    @Column(name = "status")
    private int status;
    
    /**
     * 车辆图片
     */
    @Column(name = "car_images")
    private String 	carImages;
    
    
    /**
     * 城市URL
     */
    @Column(name = "city_url")
    private String cityUrl;
    
    /**
     * 咨询电话
     */
    private String phone;
    
    @Column(name = "bright_points")
    private String brightPoints;
    
    /**
     * 所在城市
     */
    private String location;

    /**
     * 添加时间
     */
    @Column(name = "sell_time")
    private String SellTime;

    public String getSellTime() {
        return SellTime;
    }

    public void setSellTime(String sellTime) {
        SellTime = sellTime;
    }

    /** 设置 车辆ID */
    public void setCarId(long carId) {
        this.carId = carId;
    }

    /** 获取 车辆ID */
    public long getCarId() {
        return carId;
    }

    /** 设置 车辆名 */
    public void setCarName(String carName) {
        this.carName = carName;
    }



    /** 获取 车辆名 */
    public String getCarName() {
        return carName;
    }

    /** 设置 添加时间 */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /** 获取 添加时间 */
    public Date getAddTime() {
        return addTime;
    }

    /** 设置 车主报价（万元） */
    public void setOwnerPrice(double ownerPrice) {
        this.ownerPrice = ownerPrice;
    }



    /** 获取 车主报价（万元） */
    public double getOwnerPrice() {
        return ownerPrice;
    }

    /** 设置 指导价（万元） */
    public void setDirectPrice(double directPrice) {
        this.directPrice = directPrice;
    }


    /** 获取 指导价（万元） */
    public double getDirectPrice() {
        return directPrice;
    }

    /** 设置 上牌时间 */
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }


    /** 获取 上牌时间 */
    public String getRegTime() {
        return regTime;
    }

    /** 设置 里程 */
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }


    /** 获取 里程 */
    public double getMileage() {
        return mileage;
    }

    /** 设置 上牌地 */
    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }


    /** 获取 上牌地 */
    public String getRegCity() {
        return regCity;
    }

    /** 设置 排放标准 */
    public void setStandard(String standard) {
        this.standard = standard;
    }


    /** 获取 排放标准 */
    public String getStandard() {
        return standard;
    }

    /** 设置 过户次数 */
    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }


    /** 获取 过户次数 */
    public int getDealCount() {
        return dealCount;
    }

    /** 设置 排量 */
    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }


    /** 获取 排量 */
    public String getDisplacement() {
        return displacement;
    }

    /** 设置 看车地址 */
    public void setSeeCarCity(String seeCarCity) {
        this.seeCarCity = seeCarCity;
    }



    /** 获取 看车地址 */
    public String getSeeCarCity() {
        return seeCarCity;
    }

    /** 设置 交强险到期时间 */
    public void setTrafficInsuranceTime(String trafficInsuranceTime) {
        this.trafficInsuranceTime = trafficInsuranceTime;
    }



    /** 获取 交强险到期时间 */
    public String getTrafficInsuranceTime() {
        return trafficInsuranceTime;
    }

    /** 设置 年检时间 */
    public void setYearExamineTime(String yearExamineTime) {
        this.yearExamineTime = yearExamineTime;
    }



    /** 获取 年检时间 */
    public String getYearExamineTime() {
        return yearExamineTime;
    }

    /** 设置 商业险到期时间 */
    public void setCommercialInsuranceTime(String commercialInsuranceTime) {
        this.commercialInsuranceTime = commercialInsuranceTime;
    }

    /** 获取 商业险到期时间 */
    public String getCommercialInsuranceTime() {
        return commercialInsuranceTime;
    }

    /** 设置 车源编号 */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }



    /** 获取 车源编号 */
    public String getSourceId() {
        return sourceId;
    }

    /** 设置 高科技配置 */
    public void setHighTechDeploy(String highTechDeploy) {
        this.highTechDeploy = highTechDeploy;
    }



    /** 获取 高科技配置 */
    public String getHighTechDeploy() {
        return highTechDeploy;
    }

    /** 设置 服务费 */
    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    /** 获取 服务费 */
    public double getServicePrice() {
        return servicePrice;
    }

    /** 设置 是否4s店保养（1：是；2：否；-1未知） */
    public void set4sService(int fourService) {
        this.fourService = fourService;
    }

    /** 获取 是否4s店保养（1：是；2：否；-1未知） */
    public int get4sService() {
        return fourService;
    }

    public int getFourService() {
		return fourService;
	}

	public void setFourService(int fourService) {
		this.fourService = fourService;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getOwnerDepict() {
		return ownerDepict;
	}

	public void setOwnerDepict(String ownerDepict) {
		this.ownerDepict = ownerDepict;
	}

	public int getPriceReduction() {
		return priceReduction;
	}

	public void setPriceReduction(int priceReduction) {
		this.priceReduction = priceReduction;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCarImages() {
		return carImages;
	}

	public void setCarImages(String carImages) {
		this.carImages = carImages;
	}


	public String getCityUrl() {
		return cityUrl;
	}

	public void setCityUrl(String cityUrl) {
		this.cityUrl = cityUrl;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBrightPoints() {
		return brightPoints;
	}

	public void setBrightPoints(String brightPoints) {
		this.brightPoints = brightPoints;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	@Override
	public String toString() {
		return "[carId=" + carId + ", carName=" + carName + ", addTime=" + addTime + ", ownerPrice="
				+ ownerPrice + ", gearbox=" + gearbox + ", directPrice=" + directPrice + ", regTime=" + regTime
				+ ", mileage=" + mileage + ", regCity=" + regCity + ", standard=" + standard + ", dealCount="
				+ dealCount + ", displacement=" + displacement + ", seeCarCity=" + seeCarCity
				+ ", trafficInsuranceTime=" + trafficInsuranceTime + ", yearExamineTime=" + yearExamineTime
				+ ", commercialInsuranceTime=" + commercialInsuranceTime + ", sourceId=" + sourceId
				+ ", highTechDeploy=" + highTechDeploy + ", servicePrice=" + servicePrice + ", fourService="
				+ fourService + ", sourceUrl=" + sourceUrl + ", ownerDepict=" + ownerDepict + ", priceReduction="
				+ priceReduction + ", newStatus=" + newStatus + ", status=" + status + ", carImages=" + carImages
				+ ", cityUrl=" + cityUrl + ", phone=" + phone + ", brightPoints=" + brightPoints + ", location="
				+ location + "]";
	}
}
