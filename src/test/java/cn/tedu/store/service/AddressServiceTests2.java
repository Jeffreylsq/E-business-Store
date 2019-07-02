package cn.tedu.store.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;
import cn.tedu.store.ex.Service;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests2 {

	  @Autowired
	  IAddressService addressService;
	  
	 @Test
	 public void addAddress() {
		 
		
		
		 try {
	            Integer uid = 2;
	            String username = "系统管理员";
	            Address address = new Address();
	            address.setName("小孙同学");
	            addressService.addNew(address, uid, username);
	            System.err.println("OK.");
	        } catch (Service e) {
	            System.err.println(e.getClass().getName());
	            System.err.println(e.getMessage());
	        }
	 
	 }
}
