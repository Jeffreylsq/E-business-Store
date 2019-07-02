package cn.tedu.store;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired UserMapper usermapper;
	@Test
	public void testFindByUsername() {
		
		String username = "Jeffrey";
		User user = usermapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void Insert() {
		String username = "Jeffrey";
	    String password = "7758521";
//	    String salt = "1062";
//	   Integer gender = 0;
//	    String phone = "6264000783";
//	    String email = "wty19921119@gmail.com";
//	    String avatar = "PHOTO";
//	    Integer is_delete = 0;
//	    String created_user = "Jeffrey";
//	     Date now = new Date();
//	    Date created_time = now;
//	    String modified_user = "Bella";
//	    Date  modified_time = now;
	   
	   User user=  new User();
	   user.setUsername(username);
	   user.setPassword(password);
	    Integer result = usermapper.insert(user);
	    System.out.println(result);
	}

}
