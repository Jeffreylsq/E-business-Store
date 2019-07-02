package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;


/**
 * 处理省市区数据的业务层接口
 * @author soft01
 *
 */
public interface IDistrictService {

	
	 List<District> getByParent(String parent);
}
