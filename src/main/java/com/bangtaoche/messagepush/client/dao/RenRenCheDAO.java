package com.bangtaoche.messagepush.client.dao;

import com.bangtaoche.messagepush.client.entity.Che168;
import com.bangtaoche.messagepush.client.entity.Che168Car;
import com.bangtaoche.messagepush.client.entity.Che168PriceChange;
import com.bangtaoche.messagepush.client.entity.IP;
import com.darengong.tools.dao.v2.DAOHelperV2;
import com.darengong.tools.dao.v2.DBQuery;
import com.darengong.tools.dao.v2.KeyValueParis;
import com.youche.scf.common.BaseDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RenRenCheDAO extends BaseDAO{

	private final static Object locker = new Object();
	private final static RenRenCheDAO dao = new RenRenCheDAO();
	static {
		dao.initDAOHelper();
	}
	public List<Che168Car> getXiaJia() throws Exception {
		DBQuery query = new DBQuery();
		query.column("status").equal(2);
		return daoHelper.getsByQuery(Che168Car.class, query, null, null);
	}
	
	public List<Che168Car> deleteCars(String url) throws Exception {
		DBQuery query = new DBQuery();
		query.column("source_url").equal(url);
		return daoHelper.getsByQuery(Che168Car.class, query, null, "car_id desc");
	}
	
	public int deletes(List<Long> ids) throws Exception {
		return daoHelper.deleteByIDList(Che168Car.class, ids);
	}
	
	
	/**
	 * 逻辑删除车辆
	 * @param car
	 * @throws Exception
	 */
	public void deleteRenRenCheCar(Che168Car car) throws Exception {
		KeyValueParis map = new KeyValueParis();
		map.add("status", 2);
		daoHelper.updateByID(Che168Car.class, map, car.getCarId());
	}
	
	public Che168Car getCar(long carId) throws Exception {
		return daoHelper.getByID(Che168Car.class, carId);
	}
	
	/**
	 * 修改车辆
	 * @param car
	 * @throws Exception
	 */
	public synchronized void updateChe168Car(Che168Car car) throws Exception {
		KeyValueParis map = new KeyValueParis();
		map.add("owner_price", car.getOwnerPrice());
		map.add("new_status", car.getNewStatus());
		map.add("price_reduction", car.getPriceReduction());
		daoHelper.updateByID(Che168Car.class, map, car.getCarId());
	}
	/**
	 * 修改下架
	 * @param car
	 * @throws Exception
	 */
	public synchronized void updateChe168CarXiaJia(Che168Car car) throws Exception {
		KeyValueParis map = new KeyValueParis();
		map.add("status",car.getStatus());
		daoHelper.updateByID(Che168Car.class, map, car.getCarId());
	}
	/**
	 * 批量修改车辆
	 * @param carUrls
	 * @return
	 * @throws Exception
	 */
	public long updateCars(List<String> carUrls) throws Exception {
		KeyValueParis map = new KeyValueParis();
		map.add("status", 2);
		DBQuery dbquery = new DBQuery();
		dbquery.column("source_url").inList(carUrls);
		return daoHelper.updateByQuery(Che168Car.class, map, dbquery);
	}

	public List<IP> getIP() throws Exception {
		DBQuery dbQuery = new DBQuery();
		return daoHelper.getsByQuery(IP.class,dbQuery,1,3,null,"id desc");
	}
	public List<Che168Car> getCars(List<Long> carIds) throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("car_id").inList(carIds);
		dbquery.and().column("status").equal(1);
		return daoHelper.getsByQuery(Che168Car.class, dbquery, null, null);
	}

	/**
	 * 获取所有表中的所有数据
	 * @return
	 * @throws Exception
	 */
	public List<Che168Car> getAll() throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.and().column("status").equal(1);
		return daoHelper.getsByQuery(Che168Car.class, dbquery, null, null);
	}
	/**
	 * 通过carUrl获取Car实体
	 * @param carUrl
	 * @return
	 * @throws Exception
	 */
	public Che168Car getChe168Car(String carUrl) throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("source_url").equal(carUrl);
		return daoHelper.getOneByQuery(Che168Car.class, dbquery, null, null);
	}

	/**
	 * 添加RenRenChe实体
	 * @param rrc
	 * @return
	 * @throws Exception
	 */
	public long insertRenRenChe(Che168 rrc) throws Exception {
		return daoHelper.insert(rrc);
	}
	
	/**
	 * 通过carUrl获取RenRenChe实体
	 * @param carUrl
	 * @return
	 * @throws Exception
	 */
	public Che168 getChe168Che(String carUrl) throws Exception {
		DBQuery query = new DBQuery();
		query.column("car_url").equal(carUrl);
		return daoHelper.getOneByQuery(Che168.class, query, null, null);
	}
	
	public Che168 getById(long id) throws Exception {
		return daoHelper.getByID(Che168.class, id);
	}
	
	public void deleteRenRenChe(String url) throws Exception {
		DBQuery query = new DBQuery();
		query.column("car_url").equal(url);
		daoHelper.deleteByQuery(Che168.class, query);
	}
	
	/**
	 * 通过city获取所有的carUrl
	 * @param //city
	 * @return
	 * @throws Exception
	 */
	public List<Che168Car> getCarUrls(List<String> list,List<String> cityList) throws Exception {
		DBQuery query = new DBQuery();
		query.column("status").equal(1);
		query.column("city_url").inList(cityList);
		query.and().column("source_url").notInList(list);
		return daoHelper.getsByQuery(Che168Car.class, query, null, null);
	}
	
	public List<Che168> getRenRenChes(String city) throws Exception {
		DBQuery query = new DBQuery();
		query.column("city_url").equal(city);
		return daoHelper.getsByQuery(Che168.class, query, null, null);
	}
	
	/**
	 * 修改RenRenChe
	 * @param rrc
	 * @return
	 * @throws Exception
	 */
	public int updateRenRenChe(Che168 rrc,long carId) throws Exception {
		KeyValueParis map = new KeyValueParis();
		map.add("car_price", rrc.getCarPrice());
		map.add("new_status", rrc.getNewStatus());
		map.add("price_reduction", rrc.getPriceReduction());
		return daoHelper.updateByID(Che168.class, map, carId);
	}

	/**
	 * 获取价格变化通过车辆ID
	 * @param carId
	 * @return
	 * @throws Exception
	 */
	public List<Che168PriceChange> getPC(long carId) throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("car_id").equal(carId);
		return daoHelper.getsByQuery(Che168PriceChange.class, dbquery, null, "add_time desc");
	}

	/**
	 * 添加价格变化
	 * @param pc
	 * @return
	 * @throws Exception
	 */
	public synchronized long insertPriceChange(Che168PriceChange pc) throws Exception {
		return daoHelper.insert(pc);
	}
	
	/**
	 * 添加车辆
	 * @param car
	 * @return
	 * @throws Exception
	 */
	public synchronized long insertRenRenCheCar(Che168Car car) throws Exception {
		return daoHelper.insert(car);
	}

	/**
	 * 获取某个城市所有车辆
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public List<Che168Car> getCars(String city) throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("status").equal(1);
		dbquery.and().column("city_url").equal(city);
		return daoHelper.getsByQuery(Che168Car.class, dbquery, null, "car_id desc");
	}
	
	/**
	 * 根据城市url获取所有车辆URL
	 * @param city
	 * @return
	 * @throws Exception
	 */
	public synchronized List<String> getCarUrl(String city)  throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("status").equal(1);
		dbquery.and().column("city_url").equal(city);
		List<Che168Car> carList = daoHelper.getsByQuery(Che168Car.class, dbquery, null, null);
		List<String> list = new ArrayList<String>();
		for(Che168Car car : carList) {
			list.add(car.getSourceUrl());
		}
		return list;
	}
	

	/**
	 * 批量获取车辆
	 * @param carIds
	 * @return
	 * @throws Exception
	 */
	public List<Che168Car> getRenRenCheCars(List<Long> carIds) throws Exception {
		DBQuery dbquery = new DBQuery();
		dbquery.column("status").equal(1);
		dbquery.and().column("car_id").inList(carIds);
		return daoHelper.getsByQuery(Che168Car.class, dbquery, null, null);
	}
	@Override
	public void initDAOHelper() {
		String orderDaoConfigPath = System.getProperty("com.youche.sprider.renrenche", "conf");
		try {
			if(System.getProperty("os.name").toLowerCase().contains("windows")){
				orderDaoConfigPath = "E:/opt/service/che168/conf";
			} else {
				orderDaoConfigPath = "/opt/service/che168/conf";
			}
			if (daoHelper == null) {
				synchronized (locker) {
					String dbFile = orderDaoConfigPath + "/spider.properties";
					File file = new File(dbFile);
					if (!file.exists()) {
						throw new Exception("file not exists:" + dbFile);
					}
					daoHelper = DAOHelperV2.createIntrance(dbFile);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	
}
