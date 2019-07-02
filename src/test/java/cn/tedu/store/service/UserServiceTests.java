package cn.tedu.store.service;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.ex.Service;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	  @Autowired
	  IUserService userService;
	  @Test
	  public void a() {
		  
		  try {
			  User user = new User();
			  user.setUsername("jeffrey11");
			  user.setPassword("100000");
			  
				userService.reg(user);  
			  
			  
		  }catch(Service e) {
			  System.out.println(e.getClass().getName());
			  System.out.println(e.getMessage());
		  }
	  }
	  
	  
	  @Test
	  public void messageDigest() {
		  
		  String pass = "0000000";
		  String salt = UUID.randomUUID()+"";
		  for(int i = 0 ; i < 3 ; i++) {
			  pass = pass + salt;
			  pass = DigestUtils.md5Hex(pass);
			  System.out.println(pass);
			  
		  }
		  
	  }
	  
	  @Test
	  public void testLogin() {
		  
		 try { 
		  String username = "root";
		  String password = "1234";
		  User user =userService.login(username, password);
		  System.err.println(user);
		 }catch(Service e) {
			 System.out.println(e.getClass());
			 System.out.println(e.getMessage());
		 }
	  }
	  
	  @Test
	  public void testChangePassword() {
		  
		 try { 
		   //  Integer uid = (Integer) session.getAttribute("uid");
			 Integer uid = 15;
			 String username = "管理员";
			 String oldPassword = "1234";
			 String newPassword = "11234";
			 userService.changePassword(uid,username, oldPassword, newPassword);
			 
	        
			 
		 }catch(Service e) {
			 System.out.println(e.getClass());
			 System.out.println(e.getMessage());
		 }
	  }
	  
	  @Test
	  public void getByUid() {
		  Integer uid = 16;
		  User user = userService.getByUid(uid);
		  System.err.println(user);
	  }
	  
	  @Test
	  public void changeUserInfo() {
		  
		  try {
		        User user = new User();
		        user.setUid(7);
		        user.setUsername("超级管理员X");
		        user.setPhone("13700137007");
		        userService.changeInfo(user);
		        System.err.println("OK.");
		    } catch (Service e) {
		        System.err.println(e.getClass().getName());
		        System.err.println(e.getMessage());
		    }
	  }
	  @Test
	  public void changeAvatar() {
		
		  try {
		  Integer uid = 15;
		  String username = "administrator";
		  String avatar = "lujing";
		   userService.changeAvatar(uid, username, avatar);
		   System.err.println("OK.");
		  }catch(Service e) {
			  System.err.println(e.getClass().getName());
		        System.err.println(e.getMessage());
		  }
	  }
	 
}
