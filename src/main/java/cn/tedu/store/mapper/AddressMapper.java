package cn.tedu.store.mapper;



import cn.tedu.store.entity.Address;
/**
 * 处理用户数据持久层接口
 * @author soft01
 *
 */
public interface AddressMapper {

	Integer insert(Address address);
	Integer countByUid(Integer uid);
	 
	 
}
