package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.User;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.PasswordNotMatchException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.ex.UsernameDuplicateException;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		
		// 根据参数user对象中的username属性查询数据：userMapper.findByUsername()
		String username = user.getUsername();
		User result = usermapper.findByUsername(username);
		// 判断查询结果是否不为null（查询结果是存在的）
		if(result != null) {
			
			throw new UsernameDuplicateException("注册失败! + 尝试注册的用户名"+ (username) +"已经被占用");
		}
		
		//TODO 得到盐值
		String salt = UUID.randomUUID().toString().toUpperCase();
		// 基于参数user对象中的password进行加密，得到加密后的密码
		String md5Password = getMd5Password(user.getPassword(), salt);
		user.setSalt(salt);
		user.setPassword(md5Password);
		System.err.println("password:");
		System.err.println("reg() >Salt:" + salt );
		System.err.println("reg() > md5Password:" + md5Password);
		// 将user中的isDelete设置为0
		user.setIsDelete(0);
		
		Date now = new Date();
		user.setCreatedTime(now);
		user.setCreatedUser(username);
	    user.setModifiedTime(now);
		user.setModifiedUser(username);
		Integer rows = usermapper.insert(user);
		
		if(rows != 1) {
			
			throw new InsertException("注册失败！ 写入数据时出现未知错误！");
		}
		
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		
		User result = usermapper.findByUsername(username);
		if(result == null) {
			throw new UserNotFoundException("登录失败! + 尝试注册的用户名"+ (username) +"不存在");
		}
		
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("登录失败! + 尝试注册的用户名"+ (username) +"不存在");
		}
		
		//从查询结果中获取盐值
		String sal = result.getSalt();
		
		//根据参数password和盐值以器进行加密，得到加密后的值
		String targetPassword = getMd5Password(password, sal);
		
		if(!targetPassword.equals(result.getPassword())) {
			
			throw new PasswordNotMatchException("登录失败! 密码不正确");
		}
		
	   result.setPassword(null);
	   result.setSalt(null);
	   result.setIsDelete(null);
		
		return result;
	}
	
    
	
	
	
	
	
	@Override
	
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		
		System.err.println("changePassword() ---> BEGIN:");
		System.err.println("changePassword() 原密码=" + oldPassword);
		System.err.println("changePassword() 新密码=" + newPassword);
		
		
		//根据参数uid查询用户数据
		User result = usermapper.findByUid(uid);
		
		//判断查询结果是否为null
		//抛出UserNotFoundException
		if(result == null) {
			throw new UserNotFoundException("修改密码失败！用户名不存在！");
		}
		
		//判断查询结果中的isDelete为1
		//抛出UserNotFoundException
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("修改密码失败！用户名不存在！");
		}
		
		// 从查询结果中获取盐值
		String sal = result.getSalt();
		// 根据参数oldPassword和盐值一起进行加密，得到加密后的密码
		String oldMd5Password = getMd5Password(oldPassword, sal);
		
		System.err.println("changePassword() 盐值=" + sal);
		System.err.println("changePassword() 原密码加密=" + oldMd5Password);
		System.err.println("changePassword() 正确密码=" + result.getPassword());
		
		if(!oldMd5Password.equals(result.getPassword())) {
			throw new PasswordNotMatchException("修改密码失败！原密码错误！");
		}
		//根据参数newPassword和盐值一起进行加密，得到加密后的密码
		String newMd5Password = getMd5Password(newPassword, sal);
		System.out.println("changePassword() 新密码： " + newMd5Password);
		// 创建当前时间对象
		Date modifiedTime = new Date();
		
	    // 执行更新密码，并获取返回的受影响的行数
		Integer row = usermapper.updatePassword(uid, newMd5Password, username, modifiedTime);
	    // 判断受影响的行数是否不为1
		
		if(row != 1) {
			throw new UpdateException("更新失败");
		}
	    // 抛出：UpdateException
		
		System.err.println("changePassword() ---> END.");
		
		
	}
	
	
	/**
	 * 对密码进行加密
	 * @param password 原始密码
	 * @param salt 盐值
	 * @return  加密后的密码
	 */
	private String getMd5Password(String password, String salt) {
		
		//规则：对 password + salt进行三重加密
		
		String str = password + salt;
	    for(int i = 0 ; i < 3; i++) {
	    	str = DigestUtils.md5Hex(str).toUpperCase();
	    }
		
	    return str;
	}

	@Override
	public User getByUid(Integer uid) {
		 // 根据uid查询用户数据
         User user = usermapper.findByUid(uid);
	    // 如果查询到数据，则需要将查询结果中的password、salt、is_delete设置为null
         if(user != null) {
          user.setPassword(null);
          user.setSalt(null);
          user.setIsDelete(null);
         }
	    // 将查询结果返回
		return user;
	}

	
	
	
	
	@Override
	public void changeAvatar(Integer uid, String username, String avatar)
			throws UserNotFoundException, UpdateException {
		
		// 根据参数uid查询用户数据
		
		User result = usermapper.findByUid(uid);
		
	    // 判断查询结果是否为null
	    // 抛出：UserNotFoundException
		if(result == null) {
			
			throw new UserNotFoundException("上传头像失败 ，用户名不存在！");
		}

	    // 判断查询结果中的isDelete为1
	    // 抛出：UserNotFoundException
		if(result.getIsDelete() == 1) {
			throw new UserNotFoundException("上传头像失败，该用户已被标记删除");
		}
		

	    // 创建当前时间对象
		Date modifiedTime = new Date();
		
	    // 执行更新头像，并获取返回的受影响的行数
		Integer rows = usermapper.updateAvatar(uid, avatar, username, modifiedTime);
		
	    // 判断受影响的行数是否不为1
	    // 抛出：UpdateException
		
		if(rows != 1) {
			
			throw new UpdateException("上传头像失败，未知错误，请联系管理员");
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		  User result = usermapper.findByUid(user.getUid());
		    // 检查查询结果是否存在，是否标记为删除
		    // 判断查询结果是否为null
		    if(result == null) {
		        // 抛出：UserNotFoundException
		        throw new UserNotFoundException(
		            "修改个人资料失败！用户数据不存在！");
		    }

		    // 判断查询结果中的isDelete为1
		    if (result.getIsDelete() == 1) {
		        // 抛出：UserNotFoundException
		        throw new UserNotFoundException(
		            "修改个人资料失败！用户数据不存在！");
		    }

		    // 创建当前时间对象
		    Date now = new Date();
		    // 将时间封装到参数user中
		    user.setModifiedUser(user.getUsername());
		    user.setModifiedTime(now);
		    // 执行修改个人资料：mapper.updateInfo(user) > update t_user set phone=?, email=?, gender=?, modified_user=?, modified_time=? where uid=?
		    Integer rows = usermapper.updateInfo(user);
		    // 判断以上修改时的返回值是否不为1
		    if (rows != 1) {
		        // 抛出：UpdateException 
		        throw new UpdateException(
		            "修改个人资料失败！更新用户数据时出现未知错误！");
		    }
		
	}

	
	

	

}
