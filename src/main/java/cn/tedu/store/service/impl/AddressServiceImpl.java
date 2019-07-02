package cn.tedu.store.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.AddressCountLimitException;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired 
	 private AddressMapper addressMapper;

	@Override
	public void addNew(Address address,Integer uid, String username) throws AddressCountLimitException,InsertException {
		
		//补全数据
		 Integer count = countByUid(uid);
		//插入收货地址数据
		 if(count >= ADDRESS_MAX_COUNT) {
			 throw new AddressCountLimitException("设置地址数量超出范围" + "最多创建" + ADDRESS_MAX_COUNT +" 已经创建" + count);
		 }
		 //判断当前用户的收货地址 为0 说明这个是第一个地址默认为default
		 Integer isDefault = count==0?1:0;
		
		 address.setIsDefault(isDefault);
		 
		 //当前时间对象
		 Date date = new Date();
		 address.setUid(uid);
		 address.setModifiedTime(date);
		 address.setModifiedUser(username);
		 address.setCreatedTime(date);
		 address.setCreatedUser(username);
		 insert(address);
		 
	}
	
	
	private void insert(Address address)  {
		
		Integer row  = addressMapper.insert(address);
		if(row != 1) {
			
			throw new InsertException("增加收货地址失败 插入数据存在未知错误");
		}
		
	}
	
	
	/**
	 * 统计有几个地址
	 * @param uid
	 * @return
	 */
	private Integer countByUid(Integer uid) {
		
		Integer count = addressMapper.countByUid(uid);
		return count;
	}
	
	
	 
	 
}
