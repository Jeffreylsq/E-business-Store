package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

public interface DistrictMapper {

	  /**
	   * 根据
	   * @param parent
	   * @return
	   */
	   List<District> findByParent(String parent);
}
