package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.PasswordNotMatchException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.ex.UsernameDuplicateException;
import cn.tedu.store.service.ex.AddressCountLimitException;

/**
 * 处理地址数据的业务层接口
 * @author soft01
 *
 */
public interface IAddressService {

	  int ADDRESS_MAX_COUNT = 2;
	//增删改返回void 
	  
	  /**
	   * 增加新的收货地址
	   * @param address 收货地址数据
	   * @param uid 用户id
	   * @param username  用户名
	   * @throws AddressCountLimitException  收货地址数量超出限制
	   * @throws InsertException  
	   */
	void addNew(Address address,Integer uid, String username) throws AddressCountLimitException,InsertException;
	
	
	
	
}
