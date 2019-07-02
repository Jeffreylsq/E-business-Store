package cn.tedu.store.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIoException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

/**
 * 处理用户数据相关请求的控制器类
 * @author soft01
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    public static final long AVATAR_MAX_SIZE = 2 * 1024 * 1024;
    /**
     * 上传时允许的头像文件类型
     */
    public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<>();
    public static final String AVATAR_DIR = "upload";
    
    /**
     * 初始化上传时的文件类型
     */
    static {
    	AVATAR_CONTENT_TYPES.add("image/jpeg");
    	AVATAR_CONTENT_TYPES.add("image/png");
    	
    }
    
	@Autowired IUserService userService;
	
	/**
	 * 操作结果成功状态
	 */
	
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		userService.reg(user);
		return new JsonResult<>(SUCCESS);

	}
    
	@RequestMapping("login")
	public JsonResult<User> login(String username, String password, HttpSession session){
		
		//执行登录 获取登录返回结果
		User user = userService.login(username, password);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		return new JsonResult<>(SUCCESS,user);
		
	}
	//@RequestParam("old_password")添加这个是因为表单中不会驼峰命名法
	@RequestMapping("change_password")
	public  JsonResult<Void> changePassword(@RequestParam("old_password")String oldPassword,
			 @RequestParam("new_password") String newPassword, HttpSession session){
		
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changePassword(uid, username, oldPassword, newPassword);
		
		return new JsonResult<>(SUCCESS);
	}

	
	@GetMapping("get_info")
	public JsonResult<User> getByUid(HttpSession session){
		 // 从session中获取uid
	    Integer uid = getUidFromSession(session);
	    // 查询匹配的数据
	    User data = userService.getByUid(uid);
	    // 响应
	    return new JsonResult<>(SUCCESS, data);
		
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		
		    // 从session中获取uid和username
		    Integer uid = getUidFromSession(session);
		    String username = getUsernameFromSession(session);
		    // 将uid和username封装到user中
		    user.setUid(uid);
		    user.setUsername(username);
		    // 执行修改
		    userService.changeInfo(user);
		    // 响应
		    return new JsonResult<>(SUCCESS);
		
	}
	
	@PostMapping("change_avatar")
	 public  JsonResult<String> changeAvatar(HttpServletRequest request,@RequestParam("file") MultipartFile file){
		
		
		//检查文件是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("文件为空");
		}
		
		//检查文件大小
		if(file.getSize() > AVATAR_MAX_SIZE) {
			
			throw new FileSizeException("上传失败文件过大"  );
		}
		
		//检查文件类型
		if(!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("文件种类有问题");
		}
		
		
		
		//确定文件夹
		 
		String dirPath = request.getServletContext().getRealPath(AVATAR_DIR);
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//确定文件名
		String originalFileName = file.getOriginalFilename();
		String suffix = "";
		int beginIndex = originalFileName.lastIndexOf(".");
		
		if(beginIndex != -1) {
			suffix = originalFileName.substring(beginIndex);
		}
		
		String fileName = UUID.randomUUID() + suffix;
		
		
		//执行保存： file.transferTo(dest);
		File dest = new File(dir,fileName);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileUploadStateException("上传失败 检查源文件");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileIoException("上传失败 未知问题");
			
		}

		//更新数据表: avatar = "/upload/"+ filename
		String avatar = "/" + AVATAR_DIR +"/" + fileName;
		HttpSession session = request.getSession();
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		userService.changeAvatar(uid, username, avatar);
		
		//返回
		JsonResult<String> jr = new JsonResult<>();
		jr.setData(avatar);
		jr.setStatus(SUCCESS);
		
		
		return jr;
	}
	
	

	

}

