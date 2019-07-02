package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService  {

	@Autowired DistrictMapper districtMapper;
	@Override
	public List<District> getByParent(String parent) {
		
		return findByParent(parent);
	}
	
	/**
	 * 根据父级代号获取所有省市
	 * @param parent
	 * @return
	 */
	private List<District> findByParent(String parent){
		
		return districtMapper.findByParent(parent);
	}

}
