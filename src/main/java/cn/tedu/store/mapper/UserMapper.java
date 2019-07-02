package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
/**
 * 处理用户数据持久层接口
 * @author soft01
 *
 */
public interface UserMapper {

	/**
	 * 这是插入用户数据 
	 * @param user 用户数据对象
	 * @return 受影响的行数
	 */
	 Integer insert(User user);
	
	 
	 /**
	  * 更新用户信息
	  * @param user
	  * @return
	  */
	 Integer updateInfo(User user);
	 
	 /**
	  * 更新密码
	  * @param uid Integer uid
	  * @param password  String password
	  * @param modifiedUser String modifiedUser 
	  * @param modifiedTime Date modifiedTime
	  * @return
	  */
	 
	 Integer updatePassword(@Param("uid") Integer uid,@Param("password")String password,
			 @Param("modifiedUser")String modifiedUser
			 ,@Param("modifiedTime")Date modifiedTime );
	 
	 
	 
	 
	 /**
	  * 更新头像
	  * @param uid Integer uid
	  * @param avatar String 头像
	  * @param modifiedUser String modifiedUser 
	  * @param modifiedTime Date modifiedTime
	  * @return
	  */
	 
	 Integer updateAvatar(@Param("uid") Integer uid,@Param("avatar")String avatar,
			 @Param("modifiedUser")String modifiedUser
			 ,@Param("modifiedTime")Date modifiedTime );
	 
	 
	 
	 
	 
	 /**
	  * 根据用户名查询用户数据
	  * @param username 用户名
	  * @return 匹配的用户信息 如果没有匹配的用户信息 返回null
	  */
	 User findByUsername(String username);
	 
	
	 
	 /**
	  * 通过uid 查找 is_delete, password, salt
	  */
	 User findByUid(Integer uid); 
	 
	 
	 
}
