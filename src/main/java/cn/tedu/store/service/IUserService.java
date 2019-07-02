package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.PasswordNotMatchException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务层接口
 * @author soft01
 *
 */
public interface IUserService {

	/**
	 * 
	 * @param user
	 * @throws UsernameDuplicateException 用户名重复异常
	 * @throws InsertException  插入用户异常
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 * @throws UserNotFoundException  用户不存在
	 * @throws PasswordNotMatchException  密码不存在
	 */
	User login(String username, String password)throws UserNotFoundException, PasswordNotMatchException;
	
	/**
	 * 更改用户密码
	 * @param uid 用户id
	 * @param oldPassword 老密码  需要验证
	 * @param newPassword 新密码
	 * @throws UserNotFoundException  用户未找到 异常
	 * @throws PasswordNotMatchException 
	 * @throws UpdateException
	 */
	void changePassword(Integer uid, String username ,String oldPassword, String newPassword)throws UserNotFoundException, PasswordNotMatchException,UpdateException;
	
	
	/**
	 * 根据用户id 查询用户数据
	 * @param uid 用户的id
	 * @return  匹配的用户数据， 
	 */
	User getByUid(Integer uid);
	
	
	/**
	 * 更新了用户基本资料的对象，至少需要封装用户的id和用户名， 可选择性的封装用户的手机号码，和电子邮箱，
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotFoundException, UpdateException;
	
	
	
	/**
	 * 更改用户头像
	 * @param uid 用户id
	 * @param username 用户名
	 * @param avatar 头像的路径
	 * @throws UserNotFoundException  用户未找到 异常
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid, String username ,String avatar)throws UserNotFoundException, UpdateException;
	
	
	
	
}
