package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {

	   @Autowired UserMapper usermapper;
	  @Test
	  public void testSelect() {
		  
		  String username = "root";
		  User user = usermapper.findByUsername(username);
		  System.out.println(user);
	  }
	  
	  @Test
	  public void testFindByUid() {
		  
		  Integer uid = 12;
		  User user = usermapper.findByUid(uid);
		  System.err.println(user);
	  }
	  @Test
     public void testUpdateUser() {
		  
		  Integer uid = 15;
		  String password = "qqqqq";
		  String modifiedUser = "bella";
		  Date modifiedTime = new Date();
		  Integer row =usermapper.updatePassword(uid, password, modifiedUser, modifiedTime);
	      System.out.println(row);
	      
	  }
	  
	  @Test
	     public void findByUserPassword() {
			  
			 User user = new User();
			 user.setUid(7);
			 user.setPhone("13800138007");
			 user.setEmail("root@qq.com");
		      
		  }
	  
	  
	  @Test
	     public void updateInfo() {
			  
			 User user = new User();
			 user.setUid(16);
			 user.setPhone("13800138007");
			 user.setEmail("root@qq.com");
			 user.setGender(1);
			 Integer rows = usermapper.updateInfo(user);
			 System.err.println("rows=" + rows);
		      
		  }
	  
	  @Test
	     public void updateAvatar() {
			  
			
			 Integer uid = 15;
			String avatar = "asdas/asdas/asd";
			String modifiedUser = "bebe";
			Date modifiedTime = new Date();
			 Integer rows = usermapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		      System.out.println(rows);
		  }
	  
}
