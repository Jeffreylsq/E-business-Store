package cn.tedu.store.mapper;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {

	   @Autowired AddressMapper addressmapper;
	   
	   @Test
	   public void insert() {
		  Address address = new Address();
		  address.setUid(1);
		  address.setName("zhaoliu");
		  Integer rows = addressmapper.insert(address);
		  System.err.println("rows=" + rows);
		  System.err.println("id=" + address.getUid());
	   }
	   
	   @Test
	   public void countByUid() {
		   Integer uid = 10;
		   Integer count = addressmapper.countByUid(uid);
		   System.err.println("Count=" + count);
	   }
	   
	   
	   
	   
	   
}
